package com.lxl.webtool.dao.mapper;

import com.lxl.webtool.dao.pojo.TbBhVersion;
import com.lxl.webtool.dao.pojo.TbBhVersionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbBhVersionMapper {
    int countByExample(TbBhVersionExample example);

    int deleteByExample(TbBhVersionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBhVersion record);

    int insertSelective(TbBhVersion record);

    List<TbBhVersion> selectByExample(TbBhVersionExample example);

    TbBhVersion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBhVersion record, @Param("example") TbBhVersionExample example);

    int updateByExample(@Param("record") TbBhVersion record, @Param("example") TbBhVersionExample example);

    int updateByPrimaryKeySelective(TbBhVersion record);

    int updateByPrimaryKey(TbBhVersion record);
}