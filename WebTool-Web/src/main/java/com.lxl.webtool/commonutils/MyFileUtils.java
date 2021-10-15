package com.lxl.webtool.commonutils;/**
 * Description:
 *
 * @author lxl
 * @date ${date} ${time}
 */

import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.utils.OSInfoUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件处理公共类
 * ClassName MyFileUtils
 *
 * @Author bruce
 * @Date 2020/12/9  16:59
 * Version 1.0
 **/
public class MyFileUtils {


    /**
     * 1.存储文件到服务器本地
     *
     * @param file
     * @param filePath
     * @param originalFilename
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void saveFileLocal(MultipartFile file, String filePath,
                                     String originalFilename) throws IOException, FileNotFoundException {
        try {

            //区分linux和windows
            filePath = filePath + (OSInfoUtil.isLinux() ? "/" : "\\");
            // 1.保存本地
            String imgPath = String.format("%s%s", filePath,
                    originalFilename);
            byte[] byteImg = file.getBytes();
            FileImageOutputStream imageOutput = new FileImageOutputStream(
                    new File(imgPath));
            imageOutput.write(byteImg, 0, byteImg.length);
            imageOutput.close();

        } catch (Exception e) {
            MyLoggerFactory.log(LoggerEnum.UploadController,
                    String.format("执行函数saveImgToLocal发生异常:%s", e.getMessage()));
        }
    }

}
