package com.lxl.webtool.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.dao.mapper.TbBhNoteMapper;
import com.lxl.webtool.dao.pojo.TbBhNote;
import com.lxl.webtool.dao.pojo.TbBhNoteExample;
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
public class BhNoteServiceImpl implements BhNoteService {

    @Autowired
    private TbBhNoteMapper bhNoteMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbBhNote> findAll() {
        return bhNoteMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBhNote> page = (Page<TbBhNote>) bhNoteMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbBhNote bhNote) {
        bhNote.setCreatedate(MyDateUtils.getCurTimespan());
        bhNoteMapper.insert(bhNote);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbBhNote bhNote) {
        bhNote.setUpdatedate(MyDateUtils.getCurTimespan());
        bhNoteMapper.updateByPrimaryKey(bhNote);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbBhNote findOne(Long id) {
        return bhNoteMapper.selectByPrimaryKey(id.intValue());
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            bhNoteMapper.deleteByPrimaryKey(id.intValue());
        }
    }


    @Override
    public PageResult findPage(TbBhNote bhNote, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbBhNoteExample example = new TbBhNoteExample();
        TbBhNoteExample.Criteria criteria = example.createCriteria();

        if (bhNote != null) {
            if (bhNote.getCreateuser() != null && bhNote.getCreateuser().length() > 0) {
                criteria.andCreateuserLike("%" + bhNote.getCreateuser() + "%");
            }
            if (bhNote.getContent() != null && bhNote.getContent().length() > 0) {
                criteria.andContentLike("%" + bhNote.getContent() + "%");
            }
        }

        Page<TbBhNote> page = (Page<TbBhNote>) bhNoteMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

}
