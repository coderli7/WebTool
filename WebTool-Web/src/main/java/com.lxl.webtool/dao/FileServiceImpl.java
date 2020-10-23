package com.lxl.webtool.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxl.webtool.dao.mapper.TbFileMapper;
import com.lxl.webtool.dao.pojo.TbFile;
import com.lxl.webtool.dao.pojo.TbFileExample;
import com.lxl.webtool.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private TbFileMapper fileMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbFile> findAll() {
        return fileMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbFile> page = (Page<TbFile>) fileMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbFile file) {
        fileMapper.insert(file);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbFile file) {
        fileMapper.updateByPrimaryKey(file);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbFile findOne(Long id) {
        return fileMapper.selectByPrimaryKey(id.intValue());
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            fileMapper.deleteByPrimaryKey(id.intValue());
        }
    }


    @Override
    public PageResult findPage(TbFile file, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbFileExample example = new TbFileExample();
        TbFileExample.Criteria criteria = example.createCriteria();

        if (file != null) {
            // if (file.getFileName() != null && file.getFileName().length() > 0) {
            //     criteria.andFileNameLike("%" + file.getFileName() + "%");
            // }
            // if (file.getRemark1() != null && file.getRemark1().length() > 0) {
            //     criteria.andRemark1Like("%" + file.getRemark1() + "%");
            // }
            // if (file.getRemark2() != null && file.getRemark2().length() > 0) {
            //     criteria.andRemark2Like("%" + file.getRemark2() + "%");
            // }
            // if (file.getFilePath() != null && file.getFilePath().length() > 0) {
            //     criteria.andFilePathLike("%" + file.getFilePath() + "%");
            // }
            // if (file.getHttpFilePath() != null && file.getHttpFilePath().length() > 0) {
            //     criteria.andHttpFilePathLike("%" + file.getHttpFilePath() + "%");
            // }d
            // if (file.getUserCode() != null && file.getUserCode().length() > 0) {
            //     criteria.andUserCodeLike("%" + file.getUserCode() + "%");
            // }
        }

        Page<TbFile> page = (Page<TbFile>) fileMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

}
