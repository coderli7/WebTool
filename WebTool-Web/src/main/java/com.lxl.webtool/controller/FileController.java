package com.lxl.webtool.controller;

import com.lxl.webtool.commonutils.FastDFSClient;
import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.commonutils.MyFileUtils;
import com.lxl.webtool.dao.FileService;
import com.lxl.webtool.dao.pojo.TbFile;
import com.lxl.webtool.model.FastDFSFile;
import com.lxl.webtool.model.fileinfo.FileInfo;
import com.lxl.webtool.pojo.BaseResult;
import com.lxl.webtool.pojo.PageResult;
import com.lxl.webtool.pojo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * controller
 * 自定义文件上传
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     * @param index
     * @param localStoreSign
     * @return
     * @throws IOException
     */
    @RequestMapping("/fileUpload")
    public Result fileUpload(MultipartFile file, String index, String localStoreSign)
            throws IOException {

        Result uploadResult = new Result();
        uploadResult.setMessage(index);

        //0.获取基本信息
        String originalFilename = file.getOriginalFilename();
        String extName = (originalFilename.lastIndexOf('.') > 0) ? originalFilename.substring(originalFilename.lastIndexOf('.') + 1) : "";

        if ("1".equals(localStoreSign)) {
            //0.存储在本地目录中
            //0.解决本地目录的问题
            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            String filePath = httpServletRequest.getSession().getServletContext()
                    .getRealPath("/tools");
            File curFile = new File(filePath);
            if (!curFile.exists()) {
                curFile.mkdirs();
            }
            //1.存储本地
            MyFileUtils.saveFileLocal(file, filePath, originalFilename);
            TbFile tbFile = new TbFile();
            tbFile.setFilename(originalFilename);
            tbFile.setUsercode("");
            String orgPath = String.format("%s\\%s", filePath, originalFilename);
            tbFile.setFilepath(orgPath);
            fileService.add(tbFile);
            uploadResult.setSuccess(true);

        } else {
            //0.读取byte
            byte[] fileBuff = null;
            InputStream inputStream = file.getInputStream();
            if (inputStream != null) {
                int fileLen = inputStream.available();
                fileBuff = new byte[fileLen];
                inputStream.read(fileBuff);
            }
            inputStream.close();

            // 1.初始化文件上传封装model
            FastDFSFile fastDFSFile = new FastDFSFile();
            fastDFSFile.setName(originalFilename);
            fastDFSFile.setContent(fileBuff);
            fastDFSFile.setExt(extName);
            fastDFSFile.setAuthor("");
            //2.先上传到fastdfs服务器
            String[] upLoadResult = FastDFSClient.upload(fastDFSFile);
            //3.将信息存储到数据库中
            if (upLoadResult != null) {
                TbFile tbFile = new TbFile();
                tbFile.setFilename(originalFilename);
                tbFile.setUsercode("");
                String orgPath = StringUtils.join(upLoadResult, '/');
                String httpPath = String.format("%s/%s", "http://192.168.4.53:8089", orgPath);
                tbFile.setFilepath(orgPath);
                tbFile.setHttpfilepath(httpPath);
                fileService.add(tbFile);
                uploadResult.setSuccess(true);
            } else {
                uploadResult.setSuccess(false);
            }
        }
        return uploadResult;
    }

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbFile> findAll() {
        return fileService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return fileService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param file
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbFile file) {
        try {
            fileService.add(file);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param file
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbFile file) {
        try {
            fileService.update(file);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbFile findOne(Long id) {
        return fileService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            fileService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbFile file, int page, int rows) {
        return fileService.findPage(file, page, rows);
    }

    @RequestMapping("/getTools")
    public List<FileInfo> getTools() {
        List<FileInfo> fileInfoList = new ArrayList<>();
        try {
            //1.从服务器tool目录下读取文件列表
            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            String filePath = httpServletRequest.getSession().getServletContext()
                    .getRealPath("/tools");
            File toolsFileDirectory = new File(filePath);
            if (toolsFileDirectory.exists() && toolsFileDirectory.isDirectory()) {
                File[] files = toolsFileDirectory.listFiles();
                for (File fileItem :
                        files) {
                    if (fileItem.isFile()) {
                        String curFileName = fileItem.getName();

                        FileInfo curFileInfo = new FileInfo();
                        curFileInfo.setFileName(curFileName);
                        curFileInfo.setDownLoadPath(String.format("%s/%s", "tools", curFileName));
                        fileItem.lastModified();
                        String dtStr = MyDateUtils.convertDateLong(fileItem.lastModified(), null);
                        curFileInfo.setRemark(dtStr);
                        fileInfoList.add(curFileInfo);
                    }
                }
            }
        } finally {
        }
        return fileInfoList;
    }

    @RequestMapping("/delTools")
    public BaseResult delTools(String toolName) {
        BaseResult delResult = new BaseResult();
        try {
            //1.从服务器tool目录下读取文件列表
            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            String filePath = httpServletRequest.getSession().getServletContext()
                    .getRealPath("/tools");
            File toolsFileDirectory = new File(filePath);
            if (toolsFileDirectory.exists() && toolsFileDirectory.isDirectory()) {

                File[] files = toolsFileDirectory.listFiles();
                for (File fileItem :
                        files) {
                    if (fileItem.isFile()) {

                        String serverFileName = fileItem.getName();
                        if (serverFileName.equals(toolName)) {
                            fileItem.delete();
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            delResult.setCode(-10000);
            delResult.setMessage(e.getMessage());
        } finally {
        }
        return delResult;
    }
}
