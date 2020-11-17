package com.lxl.webtool.dao.pojo;

public class TbBhChannelinfo {
    private Integer id;

    private String channelkey;

    private String logininfo;

    private String remark;

    private Long createdate;

    private Long updatedate;

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

    public String getLogininfo() {
        return logininfo;
    }

    public void setLogininfo(String logininfo) {
        this.logininfo = logininfo == null ? null : logininfo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Long createdate) {
        this.createdate = createdate;
    }

    public Long getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Long updatedate) {
        this.updatedate = updatedate;
    }
}