package com.lxl.webtool.data;/**
 * Description:
 *
 * @author lxl
 * @date ${date} ${time}
 */

import java.io.Serializable;

/**
 * ClassName VersionData
 *
 * @Author bruce
 * @Date 2021/1/18  11:02
 * Version 1.0
 **/
public class VersionData implements Serializable {


    private String fileName;

    private String versionType;

    private String versionNumber;

    private String downLoadUrl;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getDownLoadUrl() {
        return downLoadUrl;
    }

    public void setDownLoadUrl(String downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
    }
}
