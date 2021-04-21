package com.lxl.webtool.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxl.webtool.dao.mapper.TbBhDbcacheMapper;
import com.lxl.webtool.dao.pojo.TbBhDbcache;
import com.lxl.webtool.dao.pojo.TbBhDbcacheExample;
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
public class BhDbcacheServiceImpl implements BhDbcacheService {

    @Autowired
    private TbBhDbcacheMapper bhDbcacheMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbBhDbcache> findAll() {
        return bhDbcacheMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBhDbcache> page = (Page<TbBhDbcache>) bhDbcacheMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbBhDbcache bhDbcache) {
        bhDbcacheMapper.insert(bhDbcache);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbBhDbcache bhDbcache) {
        bhDbcacheMapper.updateByPrimaryKey(bhDbcache);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbBhDbcache findOne(Long id) {
        return bhDbcacheMapper.selectByPrimaryKey(id.intValue());
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            bhDbcacheMapper.deleteByPrimaryKey(id.intValue());
        }
    }


    @Override
    public PageResult findPage(TbBhDbcache bhDbcache, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbBhDbcacheExample example = new TbBhDbcacheExample();
        TbBhDbcacheExample.Criteria criteria = example.createCriteria();

        if (bhDbcache != null) {
            if (bhDbcache.getDbkey() != null && bhDbcache.getDbkey().length() > 0) {
                criteria.andDbkeyLike("%" + bhDbcache.getDbkey() + "%");
            }
            if (bhDbcache.getDbcontent() != null && bhDbcache.getDbcontent().length() > 0) {
                criteria.andDbcontentLike("%" + bhDbcache.getDbcontent() + "%");
            }

        }

        Page<TbBhDbcache> page = (Page<TbBhDbcache>) bhDbcacheMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

}
