package com.lxl.webtool.model.fileinfo;/**
 * Description:
 *
 * @author lxl
 * @date ${date} ${time}
 */

import java.io.Serializable;

/**
 * ClassName FileInfo
 * 文件下载model
 *
 * @Author bruce
 * @Date 2020/12/10  11:43
 * Version 1.0
 **/
public class FileInfo implements Serializable {


    private String fileName;

    private String downLoadPath;

    private String remark;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    private String size;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownLoadPath() {
        return downLoadPath;
    }

    public void setDownLoadPath(String downLoadPath) {
        this.downLoadPath = downLoadPath;
    }
}
