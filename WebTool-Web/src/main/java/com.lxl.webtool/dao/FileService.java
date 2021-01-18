package com.lxl.webtool.dao;

import com.lxl.webtool.dao.pojo.TbFile;
import com.lxl.webtool.pojo.BaseResult;
import com.lxl.webtool.pojo.PageResult;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface FileService {

    /**
     * 返回全部列表
     *
     * @return
     */
    public List<TbFile> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    public void add(TbFile file);


    /**
     * 修改
     */
    public void update(TbFile file);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    public TbFile findOne(Long id);


    /**
     * 批量删除
     *
     * @param ids
     */
    public void delete(Long[] ids);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(TbFile file, int pageNum, int pageSize);

    public BaseResult getLatestVersion(String versionType);


}
