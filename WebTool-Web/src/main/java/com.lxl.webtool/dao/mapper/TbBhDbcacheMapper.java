package com.lxl.webtool.dao.mapper;

import com.lxl.webtool.dao.pojo.TbBhDbcache;
import com.lxl.webtool.dao.pojo.TbBhDbcacheExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbBhDbcacheMapper {
    int countByExample(TbBhDbcacheExample example);

    int deleteByExample(TbBhDbcacheExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBhDbcache record);

    int insertSelective(TbBhDbcache record);

    List<TbBhDbcache> selectByExample(TbBhDbcacheExample example);

    TbBhDbcache selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBhDbcache record, @Param("example") TbBhDbcacheExample example);

    int updateByExample(@Param("record") TbBhDbcache record, @Param("example") TbBhDbcacheExample example);

    int updateByPrimaryKeySelective(TbBhDbcache record);

    int updateByPrimaryKey(TbBhDbcache record);
}