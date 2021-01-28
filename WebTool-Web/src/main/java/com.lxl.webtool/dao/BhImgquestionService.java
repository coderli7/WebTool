package com.lxl.webtool.dao;

import com.lxl.webtool.dao.pojo.TbBhImgquestion;
import com.lxl.webtool.pojo.BaseResult;
import com.lxl.webtool.pojo.PageResult;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface BhImgquestionService {

    /**
     * 返回全部列表
     *
     * @return
     */
    public List<TbBhImgquestion> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    public void add(TbBhImgquestion bhImgquestion);


    /**
     * 修改
     */
    public void update(TbBhImgquestion bhImgquestion);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    public TbBhImgquestion findOne(Long id);


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
    public PageResult findPage(TbBhImgquestion bhImgquestion, int pageNum, int pageSize);

    /**
     * 根据渠道和guid获取唯一标识
     *
     * @param channelkey
     * @param guid
     */
    public BaseResult findImgData(String channelkey, String guid);


    /**
     * 保存用户回复结果
     *
     * @param channelkey
     * @param guid
     * @param answser
     * @return
     */
    public BaseResult saveImgAnswer(String channelkey, String guid, String answser);

}
