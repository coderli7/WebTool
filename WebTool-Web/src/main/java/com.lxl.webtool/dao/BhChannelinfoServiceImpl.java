package com.lxl.webtool.dao;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.dao.mapper.TbBhChannelinfoMapper;
import com.lxl.webtool.dao.pojo.TbBhChannelinfo;
import com.lxl.webtool.dao.pojo.TbBhChannelinfoExample;
import com.lxl.webtool.dao.pojo.TbBhChannelinfoLatest;
import com.lxl.webtool.enums.ResultEnum;
import com.lxl.webtool.model.BHChannelRemarkInfoRequest;
import com.lxl.webtool.model.BhLoginInfoRequest;
import com.lxl.webtool.pojo.BaseResult;
import com.lxl.webtool.pojo.ChannelInfoResult;
import com.lxl.webtool.pojo.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class BhChannelinfoServiceImpl implements BhChannelinfoService {

    @Autowired
    private TbBhChannelinfoMapper bhChannelinfoMapper;

    @Autowired
    private BhChannelinfoLatestService bhChannelinfoLatestService;


    /**
     * 查询全部
     */
    @Override
    public List<TbBhChannelinfo> findAll() {
        return bhChannelinfoMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBhChannelinfo> page = (Page<TbBhChannelinfo>) bhChannelinfoMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbBhChannelinfo bhChannelinfo) {
        bhChannelinfoMapper.insert(bhChannelinfo);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbBhChannelinfo bhChannelinfo) {
        bhChannelinfoMapper.updateByPrimaryKey(bhChannelinfo);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbBhChannelinfo findOne(Long id) {
        TbBhChannelinfo channelinfo = bhChannelinfoMapper.selectByPrimaryKey(id.intValue());

        return channelinfo;
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            bhChannelinfoMapper.deleteByPrimaryKey(id.intValue());
        }
    }


    @Override
    public PageResult findPage(TbBhChannelinfo bhChannelinfo, int pageNum, int pageSize) {
        // PageHelper.startPage(pageNum, pageSize, true);

        //需要根据remark字段,转化时间戳
        Long startDateVal = 0L, endDateVal = 0L;
        String channelinfoRemark = bhChannelinfo.getRemark();
        if (StringUtils.isNotBlank(channelinfoRemark)) {
            BHChannelRemarkInfoRequest channelRemark = JSON.parseObject(channelinfoRemark, BHChannelRemarkInfoRequest.class);
            String startDate = channelRemark.getStartDate();
            if (StringUtils.isNotBlank(startDate)) {
                //将字符串转化为时间戳
                startDateVal = MyDateUtils.convertDateStr(startDate, null);
            }

            String endDate = channelRemark.getEndDate();
            if (StringUtils.isNotBlank(endDate)) {
                //将字符串转化为时间戳
                endDateVal = MyDateUtils.convertDateStr(endDate, null);
            }
        }

        TbBhChannelinfoExample example = new TbBhChannelinfoExample();
        TbBhChannelinfoExample.Criteria criteria = example.createCriteria();
        if (bhChannelinfo != null) {
            if (bhChannelinfo.getChannelkey() != null && bhChannelinfo.getChannelkey().length() > 0) {
                criteria.andChannelkeyLike("%" + bhChannelinfo.getChannelkey() + "%");
            }
            if (bhChannelinfo.getLogininfo() != null && bhChannelinfo.getLogininfo().length() > 0) {
                criteria.andLogininfoLike("%" + bhChannelinfo.getLogininfo() + "%");
            }
            // if (bhChannelinfo.getRemark() != null && bhChannelinfo.getRemark().length() > 0) {
            //     criteria.andRemarkLike("%" + bhChannelinfo.getRemark() + "%");
            // }

            if (startDateVal > 0) {
                criteria.andCreatedateGreaterThanOrEqualTo(startDateVal.intValue());
            }
            if (endDateVal > 0 && endDateVal > startDateVal) {
                criteria.andCreatedateLessThanOrEqualTo(endDateVal.intValue());
            }
        }
        List<TbBhChannelinfo> channelinfos = bhChannelinfoMapper.selectByExampleNew(example, pageSize * (pageNum - 1), pageSize);
        int totalRows = bhChannelinfoMapper.countSelectByExampleNew(example);
        // int totalRows = bhChannelinfoMapper.countByExample(null);

        // Page<TbBhChannelinfo> page = (Page<TbBhChannelinfo>) channelinfos;
        return new PageResult(totalRows, channelinfos);
        // return new PageResult(totalRows, page.getResult());
    }

    @Override
    public BaseResult addChannelCookie(BhLoginInfoRequest loginInfoRequest) {
        String channelKey = loginInfoRequest.getChannelKey(), cookie = loginInfoRequest.getCookie();
        BaseResult baseResult = new BaseResult();
        TbBhChannelinfo channelinfo = new TbBhChannelinfo();
        channelinfo.setChannelkey(channelKey);
        if (cookie != null && cookie.length() > 990) {
            cookie = cookie.substring(0, 990);
        }
        channelinfo.setLogininfo(cookie);
        channelinfo.setCreatedate(System.currentTimeMillis() / 1000);
        Object loginInfoRequestData = loginInfoRequest.getData();
        channelinfo.setProxyUrl(loginInfoRequest.getProxyUrl());
        //插入渠道信息
        if (loginInfoRequestData != null) {
            String data = loginInfoRequestData.toString();
            //将传入的对象转化为本地存储对象,暂时不做任何处理，直接将本地cookie存储下即可
            channelinfo.setRemark(data);
        }

        try {
            bhChannelinfoMapper.insert(channelinfo);
            //更新记录表
            bhChannelinfoLatestService.AddOrUpdateRecord(channelinfo);
            baseResult.setMessage("增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ResultEnum.Falied.getCode());
            baseResult.setMessage(ResultEnum.Falied.getMessage());
        }
        return baseResult;
    }

    @Override
    public BaseResult getChannelCookie(String channelKey) {
        ChannelInfoResult baseResult = new ChannelInfoResult();
        try {

            /**
             * 1.根据渠道列表，获取缓存，需要获取最新一条记录
             */
            TbBhChannelinfoLatest channelinfoLatest = bhChannelinfoLatestService.GetInfoByChannelKey(channelKey);

            //为空，或者更新时间为空，或者更新时间小于今天零点，则均不操作此记录
            if (channelinfoLatest == null || channelinfoLatest.getUpdatedate() == null || channelinfoLatest.getUpdatedate() < MyDateUtils.getCurDayBeginTime()) {
                //表中不存在
                TbBhChannelinfoExample example = new TbBhChannelinfoExample();
                TbBhChannelinfoExample.Criteria criteria = example.createCriteria();
                if (StringUtils.isNotBlank(channelKey)) {
                    criteria.andChannelkeyEqualTo(channelKey);
                }

                //接口只查询当天有效
                Long curDayBeginTime = MyDateUtils.getCurDayBeginTime();
                if (curDayBeginTime > 0) {
                    criteria.andCreatedateGreaterThanOrEqualTo(curDayBeginTime.intValue());
                }

                example.setOrderByClause(" id  desc limit 1");
                List<TbBhChannelinfo> channelinfos = bhChannelinfoMapper.selectByExample(example);
                if (channelinfos.size() > 0) {
                    TbBhChannelinfo channelinfo = channelinfos.get(channelinfos.size() - 1);
                    baseResult.setData(channelinfo.getLogininfo());
                    baseResult.setProxyUrl(channelinfo.getProxyUrl());
                    // BHChannelInfoDataResponse channelInfoDataResponse = new BHChannelInfoDataResponse();
                    // channelInfoDataResponse.setCookie(channelinfo.getLogininfo());
                    // channelInfoDataResponse.setProxyUrl(channelinfo.getProxyUrl());
                    // baseResult.setData(channelInfoDataResponse);
                }

            } else {
                baseResult.setData(channelinfoLatest.getLogininfo());
                baseResult.setProxyUrl(channelinfoLatest.getProxyUrl());
            }


        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ResultEnum.Falied.getCode());
            baseResult.setMessage(ResultEnum.Falied.getMessage());
        }
        return baseResult;
    }

}
