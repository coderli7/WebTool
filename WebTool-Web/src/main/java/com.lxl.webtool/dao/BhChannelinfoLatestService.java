package com.lxl.webtool.dao;

import com.lxl.webtool.dao.pojo.TbBhChannelinfo;
import com.lxl.webtool.dao.pojo.TbBhChannelinfoLatest;
import com.lxl.webtool.pojo.PageResult;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface BhChannelinfoLatestService {

    /**
     * 返回全部列表
     *
     * @return
     */
    public List<TbBhChannelinfoLatest> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    public void add(TbBhChannelinfoLatest bhChannelinfoLatest);


    /**
     * 修改
     */
    public void update(TbBhChannelinfoLatest bhChannelinfoLatest);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    public TbBhChannelinfoLatest findOne(Long id);


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
    public PageResult findPage(TbBhChannelinfoLatest bhChannelinfoLatest, int pageNum, int pageSize);

    /**
     * 新增或更新记录
     *
     * @param channelinfo
     */
    public void AddOrUpdateRecord(TbBhChannelinfo channelinfo);

    /**
     * 根据渠道key获取渠道信息
     *
     * @param channelKey
     */
    public TbBhChannelinfoLatest GetInfoByChannelKey(String channelKey);

}
