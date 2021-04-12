package com.lxl.webtool.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxl.webtool.dao.mapper.TbBhChannelinfoLatestMapper;
import com.lxl.webtool.dao.pojo.TbBhChannelinfo;
import com.lxl.webtool.dao.pojo.TbBhChannelinfoLatest;
import com.lxl.webtool.dao.pojo.TbBhChannelinfoLatestExample;
import com.lxl.webtool.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class BhChannelinfoLatestServiceImpl implements BhChannelinfoLatestService {

    @Autowired
    private TbBhChannelinfoLatestMapper bhChannelinfoLatestMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbBhChannelinfoLatest> findAll() {
        return bhChannelinfoLatestMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBhChannelinfoLatest> page = (Page<TbBhChannelinfoLatest>) bhChannelinfoLatestMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbBhChannelinfoLatest bhChannelinfoLatest) {
        bhChannelinfoLatestMapper.insert(bhChannelinfoLatest);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbBhChannelinfoLatest bhChannelinfoLatest) {
        bhChannelinfoLatestMapper.updateByPrimaryKey(bhChannelinfoLatest);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbBhChannelinfoLatest findOne(Long id) {
        return bhChannelinfoLatestMapper.selectByPrimaryKey(id.intValue());
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            bhChannelinfoLatestMapper.deleteByPrimaryKey(id.intValue());
        }
    }


    @Override
    public PageResult findPage(TbBhChannelinfoLatest bhChannelinfoLatest, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbBhChannelinfoLatestExample example = new TbBhChannelinfoLatestExample();
        TbBhChannelinfoLatestExample.Criteria criteria = example.createCriteria();

        if (bhChannelinfoLatest != null) {
            if (bhChannelinfoLatest.getChannelkey() != null && bhChannelinfoLatest.getChannelkey().length() > 0) {
                criteria.andChannelkeyLike("%" + bhChannelinfoLatest.getChannelkey() + "%");
            }
            if (bhChannelinfoLatest.getLogininfo() != null && bhChannelinfoLatest.getLogininfo().length() > 0) {
                criteria.andLogininfoLike("%" + bhChannelinfoLatest.getLogininfo() + "%");
            }
            if (bhChannelinfoLatest.getRemark() != null && bhChannelinfoLatest.getRemark().length() > 0) {
                criteria.andRemarkLike("%" + bhChannelinfoLatest.getRemark() + "%");
            }
            // if(bhChannelinfoLatest.getProxyUrl()!=null && bhChannelinfoLatest.getProxyUrl().length()>0){
            // 	criteria.andProxyUrlLike("%"+bhChannelinfoLatest.getProxyUrl()+"%");
            // }

        }

        Page<TbBhChannelinfoLatest> page = (Page<TbBhChannelinfoLatest>) bhChannelinfoLatestMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }


    @Override
    public TbBhChannelinfoLatest GetInfoByChannelKey(String channelKey) {
        List<TbBhChannelinfoLatest> tbBhChannelinfoLatest = bhChannelinfoLatestMapper.selectByChannelKey(channelKey);
        if (tbBhChannelinfoLatest != null && tbBhChannelinfoLatest.size() > 0) {
            return tbBhChannelinfoLatest.get(0);
        } else {
            return null;
        }
    }


    @Override
    public void AddOrUpdateRecord(TbBhChannelinfo channelinfo) {

        try {
            /**
             * 1.插入或更新渠道信息直记录表
             *(减少查询操作)
             */
            TbBhChannelinfoLatest channelinfoLatest = new TbBhChannelinfoLatest();
            TbBhChannelinfoLatest channelinfoLatestTmp = GetInfoByChannelKey(channelinfo.getChannelkey());
            if (channelinfoLatestTmp != null && channelinfoLatestTmp.getId() > 0) {
                //已存在-更新
                channelinfoLatest = channelinfoLatestTmp;
                channelinfoLatest.setUpdatedate(System.currentTimeMillis() / 1000);
            } else {
                //不存在-新增
                channelinfoLatest.setCreatedate(System.currentTimeMillis() / 1000);
                channelinfoLatest.setUpdatedate(System.currentTimeMillis() / 1000);
            }

            channelinfoLatest.setLogininfo(channelinfo.getLogininfo());
            channelinfoLatest.setChannelkey(channelinfo.getChannelkey());
            channelinfoLatest.setProxyUrl(channelinfo.getProxyUrl());
            channelinfoLatest.setRemark(channelinfo.getRemark());
            if (channelinfoLatest.getId() != null && channelinfoLatest.getId() > 0) {
                bhChannelinfoLatestMapper.updateByPrimaryKey(channelinfoLatest);
            } else {
                bhChannelinfoLatestMapper.insert(channelinfoLatest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
