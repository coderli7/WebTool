package com.lxl.webtool.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.dao.mapper.TbBhVersionMapper;
import com.lxl.webtool.dao.pojo.TbBhVersion;
import com.lxl.webtool.dao.pojo.TbBhVersionExample;
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
public class BhVersionServiceImpl implements BhVersionService {

    @Autowired
    private TbBhVersionMapper bhVersionMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbBhVersion> findAll() {
        return bhVersionMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBhVersion> page = (Page<TbBhVersion>) bhVersionMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbBhVersion bhVersion) {

        bhVersion.setCreatedate(MyDateUtils.getCurTimespan());
        bhVersionMapper.insert(bhVersion);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbBhVersion bhVersion) {
        bhVersion.setUpdatedate(MyDateUtils.getCurTimespan());
        bhVersionMapper.updateByPrimaryKey(bhVersion);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbBhVersion findOne(Long id) {
        return bhVersionMapper.selectByPrimaryKey(id.intValue());
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            bhVersionMapper.deleteByPrimaryKey(id.intValue());
        }
    }


    @Override
    public PageResult findPage(TbBhVersion bhVersion, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbBhVersionExample example = new TbBhVersionExample();
        TbBhVersionExample.Criteria criteria = example.createCriteria();

        if (bhVersion != null) {
            if (bhVersion.getVersionCode() != null && bhVersion.getVersionCode().length() > 0) {
                criteria.andVersionCodeLike("%" + bhVersion.getVersionCode() + "%");
            }
            if (bhVersion.getVersionDesc() != null && bhVersion.getVersionDesc().length() > 0) {
                criteria.andVersionDescLike("%" + bhVersion.getVersionDesc() + "%");
            }
            if (bhVersion.getVersionStatus() != null && bhVersion.getVersionStatus().length() > 0) {
                criteria.andVersionStatusLike("%" + bhVersion.getVersionStatus() + "%");
            }
            if (bhVersion.getRemark1() != null && bhVersion.getRemark1().length() > 0) {
                criteria.andRemark1Like("%" + bhVersion.getRemark1() + "%");
            }
            if (bhVersion.getRemark2() != null && bhVersion.getRemark2().length() > 0) {
                criteria.andRemark2Like("%" + bhVersion.getRemark2() + "%");
            }

        }

        Page<TbBhVersion> page = (Page<TbBhVersion>) bhVersionMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

}
