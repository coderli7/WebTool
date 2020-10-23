package com.lxl.webtool.controller;

import com.lxl.webtool.commonutils.FastDFSClient;
import com.lxl.webtool.dao.FileService;
import com.lxl.webtool.dao.pojo.TbFile;
import com.lxl.webtool.model.FastDFSFile;
import com.lxl.webtool.pojo.PageResult;
import com.lxl.webtool.pojo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * controller
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
     * @return
     * @throws IOException
     */
    @RequestMapping("/fileUpload")
    public Result fileUpload(MultipartFile file, String index)
            throws IOException {

        Result uploadResult = new Result();
        uploadResult.setMessage(index);


        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename + "4");

        String extName = "";
        int extIdx = originalFilename.lastIndexOf('.');
        if (extIdx > 0) {
            extName = originalFilename.substring(extIdx + 1);
        }

        byte[] fileBuff = null;
        InputStream inputStream = file.getInputStream();
        if (inputStream != null) {
            int fileLen = inputStream.available();
            fileBuff = new byte[fileLen];
            inputStream.read(fileBuff);
        }
        inputStream.close();

        String author = "";

        // 0.初始化文件上传封装model
        FastDFSFile fastDFSFile = new FastDFSFile();
        fastDFSFile.setName(originalFilename);
        fastDFSFile.setContent(fileBuff);
        fastDFSFile.setExt(extName);
        fastDFSFile.setAuthor(author);

        //1.先上传到fastdfs服务器
        String[] upLoadResult = FastDFSClient.upload(fastDFSFile);

        if (upLoadResult != null) {

            //2.插入数据库
            TbFile tbFile = new TbFile();
            tbFile.setFilename(originalFilename);
            tbFile.setUsercode(author);
            String orgPath = StringUtils.join(upLoadResult, '/');
            String httpPath = String.format("%s/%s", "http://192.168.4.53:8089", orgPath);
            tbFile.setFilepath(orgPath);
            tbFile.setHttpfilepath(httpPath);
            fileService.add(tbFile);
            uploadResult.setSuccess(true);
        } else {
            uploadResult.setSuccess(false);
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
     * @param brand
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbFile file, int page, int rows) {
        return fileService.findPage(file, page, rows);
    }

}
