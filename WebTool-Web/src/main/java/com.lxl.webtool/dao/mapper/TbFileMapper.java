package com.lxl.webtool.dao.mapper;

import com.lxl.webtool.dao.pojo.TbFile;
import com.lxl.webtool.dao.pojo.TbFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbFileMapper {
    int countByExample(TbFileExample example);

    int deleteByExample(TbFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbFile record);

    int insertSelective(TbFile record);

    List<TbFile> selectByExample(TbFileExample example);

    TbFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbFile record, @Param("example") TbFileExample example);

    int updateByExample(@Param("record") TbFile record, @Param("example") TbFileExample example);

    int updateByPrimaryKeySelective(TbFile record);

    int updateByPrimaryKey(TbFile record);
}