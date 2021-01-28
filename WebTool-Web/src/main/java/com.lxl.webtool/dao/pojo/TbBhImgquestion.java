package com.lxl.webtool.dao.pojo;

import java.io.Serializable;

public class TbBhImgquestion implements Serializable {
    private Integer id;

    private String channelkey;

    private String guid;

    private String imgdata;

    private String imgurl;

    private String imganswer;

    private String imganswerstatus;

    private String imgoperatestatus;

    private Long imgcreatedate;

    private Long imgupdatedate;

    private String remark1;

    private String remark2;

    private String remark3;

    private String remark4;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelkey() {
        return channelkey;
    }

    public void setChannelkey(String channelkey) {
        this.channelkey = channelkey == null ? null : channelkey.trim();
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getImgdata() {
        return imgdata;
    }

    public void setImgdata(String imgdata) {
        this.imgdata = imgdata == null ? null : imgdata.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getImganswer() {
        return imganswer;
    }

    public void setImganswer(String imganswer) {
        this.imganswer = imganswer == null ? null : imganswer.trim();
    }

    public String getImganswerstatus() {
        return imganswerstatus;
    }

    public void setImganswerstatus(String imganswerstatus) {
        this.imganswerstatus = imganswerstatus == null ? null : imganswerstatus.trim();
    }

    public String getImgoperatestatus() {
        return imgoperatestatus;
    }

    public void setImgoperatestatus(String imgoperatestatus) {
        this.imgoperatestatus = imgoperatestatus == null ? null : imgoperatestatus.trim();
    }

    public Long getImgcreatedate() {
        return imgcreatedate;
    }

    public void setImgcreatedate(Long imgcreatedate) {
        this.imgcreatedate = imgcreatedate;
    }

    public Long getImgupdatedate() {
        return imgupdatedate;
    }

    public void setImgupdatedate(Long imgupdatedate) {
        this.imgupdatedate = imgupdatedate;
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

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }
}