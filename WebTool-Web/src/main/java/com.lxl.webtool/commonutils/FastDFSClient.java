package com.lxl.webtool.commonutils;/**
 * Description:
 *
 * @author Administrator
 * @date 2020/7/28 13:37
 */

import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.model.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

/**
 * ClassName FastDFSClient
 * 文件上传工具类
 *
 * @Author Administrator
 * @Date 2020/7/28  13:37
 * Version 1.0
 **/
public class FastDFSClient {


    private static StorageServer storageServer = null;

    private static StorageClient storageClient = null;

    private static TrackerServer trackerServer = null;

    private static TrackerClient trackerClient = null;


    static {
        try {
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            MyLoggerFactory.log(LoggerEnum.Error, e.getStackTrace().toString());
        }
    }

    /**
     * 上传
     *
     * @param file
     * @return
     */

    public static String[] upload(FastDFSFile file) {

        // NameValuePair[] meta_list = new NameValuePair[1];
        // meta_list[0] = new NameValuePair("author", file.getAuthor());


        NameValuePair[] meta_list = null;

        String[] uploadResult = null;
        storageClient = new StorageClient(trackerServer, storageServer);
        try {
            uploadResult = storageClient.upload_file(file.getContent(), file
                    .getExt(), meta_list);
        } catch (Exception e) {
            MyLoggerFactory.log(LoggerEnum.Error, e.getStackTrace().toString());
        }
        return uploadResult;
    }


}
