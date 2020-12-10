package com.lxl.webtool.dao;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.dao.mapper.TbBhChannelinfoMapper;
import com.lxl.webtool.dao.pojo.TbBhChannelinfo;
import com.lxl.webtool.dao.pojo.TbBhChannelinfoExample;
import com.lxl.webtool.enums.ResultEnum;
import com.lxl.webtool.model.BHChannelRemarkInfoRequest;
import com.lxl.webtool.model.BhLoginInfoRequest;
import com.lxl.webtool.pojo.BaseResult;
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
        channelinfo.setLogininfo(cookie);
        channelinfo.setCreatedate(System.currentTimeMillis() / 1000);
        Object loginInfoRequestData = loginInfoRequest.getData();
        //插入渠道信息
        if (loginInfoRequestData != null) {
            String data = loginInfoRequestData.toString();
            //将传入的对象转化为本地存储对象,暂时不做任何处理，直接将本地cookie存储下即可
            channelinfo.setRemark(data);
        }

        try {
            bhChannelinfoMapper.insert(channelinfo);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ResultEnum.Falied.getCode());
            baseResult.setMessage(ResultEnum.Falied.getMessage());
        }
        return baseResult;
    }

    @Override
    public BaseResult getChannelCookie(String channelKey) {

        BaseResult baseResult = new BaseResult();
        try {
            /**
             * 1.根据渠道列表，获取缓存，需要获取最新一条记录
             */
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


            List<TbBhChannelinfo> channelinfos = bhChannelinfoMapper.selectByExample(example);
            if (channelinfos.size() > 0) {
                /**
                 * 获取到最新的一条
                 */
                // channelinfos.sort(Comparator.comparing(TbBhChannelinfo::getCreatedate).reversed());
                // Collections.sort(channelinfos, new Comparator<TbBhChannelinfo>() {
                //     @Override
                //     public int compare(TbBhChannelinfo o1, TbBhChannelinfo o2) {
                //         return o1.getCreatedate().compareTo(o2.getCreatedate());
                //     }
                // });

                TbBhChannelinfo channelinfo = channelinfos.get(channelinfos.size() - 1);
                baseResult.setData(channelinfo.getLogininfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ResultEnum.Falied.getCode());
            baseResult.setMessage(ResultEnum.Falied.getMessage());
        }

        return baseResult;
    }

}
