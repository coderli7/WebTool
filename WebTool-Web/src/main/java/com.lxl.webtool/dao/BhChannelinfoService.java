package com.lxl.webtool.dao;

import com.lxl.webtool.dao.pojo.TbBhChannelinfo;
import com.lxl.webtool.model.BhLoginInfoRequest;
import com.lxl.webtool.pojo.BaseResult;
import com.lxl.webtool.pojo.PageResult;

import java.util.List;


/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface BhChannelinfoService {

    /**
     * 返回全部列表
     *
     * @return
     */
    public List<TbBhChannelinfo> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    public void add(TbBhChannelinfo bhChannelinfo);


    /**
     * 修改
     */
    public void update(TbBhChannelinfo bhChannelinfo);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    public TbBhChannelinfo findOne(Long id);


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
    public PageResult findPage(TbBhChannelinfo bhChannelinfo, int pageNum, int pageSize);

    /**
     * 同步缓存信息
     *
     * @param loginInfoRequest
     * @return
     */
    public BaseResult addChannelCookie(BhLoginInfoRequest loginInfoRequest);

    /**
     * @param channelKey
     * @return
     */
    public BaseResult getChannelCookie(String channelKey);
}
