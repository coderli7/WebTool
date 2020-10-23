package com.lxl.webtool.dao.pojo;

public class TbFile {
    private Integer id;

    private String filename;

    private Integer filestatus;

    private String remark1;

    private String remark2;

    private String filepath;

    private String httpfilepath;

    private String usercode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Integer getFilestatus() {
        return filestatus;
    }

    public void setFilestatus(Integer filestatus) {
        this.filestatus = filestatus;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public String getHttpfilepath() {
        return httpfilepath;
    }

    public void setHttpfilepath(String httpfilepath) {
        this.httpfilepath = httpfilepath == null ? null : httpfilepath.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }
}