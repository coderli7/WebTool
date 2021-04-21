package com.lxl.webtool.dao.pojo;

public class TbBhDbcache {
    private Integer id;

    private String dbkey;

    private String dbcontent;

    private Long createdate;

    private Long updatedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDbkey() {
        return dbkey;
    }

    public void setDbkey(String dbkey) {
        this.dbkey = dbkey == null ? null : dbkey.trim();
    }

    public String getDbcontent() {
        return dbcontent;
    }

    public void setDbcontent(String dbcontent) {
        this.dbcontent = dbcontent == null ? null : dbcontent.trim();
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