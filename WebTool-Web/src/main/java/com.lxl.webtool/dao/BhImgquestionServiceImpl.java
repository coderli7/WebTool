package com.lxl.webtool.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.dao.mapper.TbBhImgquestionMapper;
import com.lxl.webtool.dao.pojo.TbBhImgquestion;
import com.lxl.webtool.dao.pojo.TbBhImgquestionExample;
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
public class BhImgquestionServiceImpl implements BhImgquestionService {

    @Autowired
    private TbBhImgquestionMapper bhImgquestionMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbBhImgquestion> findAll() {
        return bhImgquestionMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBhImgquestion> page = (Page<TbBhImgquestion>) bhImgquestionMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbBhImgquestion bhImgquestion) {
        bhImgquestion.setImgcreatedate(MyDateUtils.getCurTimespan());
        bhImgquestionMapper.insert(bhImgquestion);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbBhImgquestion bhImgquestion) {
        bhImgquestion.setImgupdatedate(MyDateUtils.getCurTimespan());
        bhImgquestionMapper.updateByPrimaryKey(bhImgquestion);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbBhImgquestion findOne(Long id) {
        return bhImgquestionMapper.selectByPrimaryKey(id.intValue());
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            bhImgquestionMapper.deleteByPrimaryKey(id.intValue());
        }
    }


    @Override
    public PageResult findPage(TbBhImgquestion bhImgquestion, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbBhImgquestionExample example = new TbBhImgquestionExample();
        TbBhImgquestionExample.Criteria criteria = example.createCriteria();

        if (bhImgquestion != null) {
            if (bhImgquestion.getChannelkey() != null && bhImgquestion.getChannelkey().length() > 0) {
                criteria.andChannelkeyLike("%" + bhImgquestion.getChannelkey() + "%");
            }
            if (bhImgquestion.getGuid() != null && bhImgquestion.getGuid().length() > 0) {
                criteria.andGuidLike("%" + bhImgquestion.getGuid() + "%");
            }
            if (bhImgquestion.getImgdata() != null && bhImgquestion.getImgdata().length() > 0) {
                criteria.andImgdataLike("%" + bhImgquestion.getImgdata() + "%");
            }
            if (bhImgquestion.getImgurl() != null && bhImgquestion.getImgurl().length() > 0) {
                criteria.andImgurlLike("%" + bhImgquestion.getImgurl() + "%");
            }
            if (bhImgquestion.getImganswer() != null && bhImgquestion.getImganswer().length() > 0) {
                criteria.andImganswerLike("%" + bhImgquestion.getImganswer() + "%");
            }
            if (bhImgquestion.getImganswerstatus() != null && bhImgquestion.getImganswerstatus().length() > 0) {
                criteria.andImganswerstatusLike("%" + bhImgquestion.getImganswerstatus() + "%");
            }
            if (bhImgquestion.getImgoperatestatus() != null && bhImgquestion.getImgoperatestatus().length() > 0) {
                criteria.andImgoperatestatusLike("%" + bhImgquestion.getImgoperatestatus() + "%");
            }
            if (bhImgquestion.getRemark1() != null && bhImgquestion.getRemark1().length() > 0) {
                criteria.andRemark1Like("%" + bhImgquestion.getRemark1() + "%");
            }
            if (bhImgquestion.getRemark2() != null && bhImgquestion.getRemark2().length() > 0) {
                criteria.andRemark2Like("%" + bhImgquestion.getRemark2() + "%");
            }
            if (bhImgquestion.getRemark3() != null && bhImgquestion.getRemark3().length() > 0) {
                criteria.andRemark3Like("%" + bhImgquestion.getRemark3() + "%");
            }
            if (bhImgquestion.getRemark4() != null && bhImgquestion.getRemark4().length() > 0) {
                criteria.andRemark4Like("%" + bhImgquestion.getRemark4() + "%");
            }

        }

        Page<TbBhImgquestion> page = (Page<TbBhImgquestion>) bhImgquestionMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public BaseResult findImgData(String channelkey, String guid) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(-1000);
        try {
            TbBhImgquestionExample example = new TbBhImgquestionExample();
            TbBhImgquestionExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(channelkey) && channelkey.length() > 0) {
                criteria.andChannelkeyEqualTo(channelkey);
            }
            if (StringUtils.isNotBlank(guid) && guid.length() > 0) {
                criteria.andGuidEqualTo(guid);
            }
            List<TbBhImgquestion> tbBhImgquestions = bhImgquestionMapper.selectByExample(example);
            if (tbBhImgquestions != null && tbBhImgquestions.size() > 0) {
                baseResult.setCode(0);
                baseResult.setMessage("获取成功");
                baseResult.setData(tbBhImgquestions.get(0));
            } else {
                baseResult.setMessage("获取失败,未查询到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setMessage(String.format("获取异常%s", e.getMessage()));
        }
        return baseResult;
    }

    @Override
    public BaseResult saveImgAnswer(String channelkey, String guid, String answser) {

        //响应直前端
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(-1000);

        try {
            TbBhImgquestionExample example = new TbBhImgquestionExample();
            TbBhImgquestionExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(channelkey) && channelkey.length() > 0) {
                criteria.andChannelkeyEqualTo(channelkey);
            }
            if (StringUtils.isNotBlank(guid) && guid.length() > 0) {
                criteria.andGuidEqualTo(guid);
            }
            List<TbBhImgquestion> tbBhImgquestions = bhImgquestionMapper.selectByExample(example);
            if (tbBhImgquestions != null && tbBhImgquestions.size() > 0) {
                baseResult.setCode(0);
                TbBhImgquestion curImg = tbBhImgquestions.get(0);
                curImg.setImganswer(answser);
                update(curImg);
                baseResult.setMessage("保存回复成功");
            } else {
                baseResult.setMessage("保存回复异常,未查询到数据");
            }
        } catch (Exception e) {
            baseResult.setMessage("保存回复异常:" + e.getMessage());
        }
        return baseResult;
    }
}
