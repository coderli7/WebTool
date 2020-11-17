package com.lxl.webtool.dao.mapper;

import com.lxl.webtool.dao.pojo.TbBhChannelinfo;
import com.lxl.webtool.dao.pojo.TbBhChannelinfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbBhChannelinfoMapper {
    int countByExample(TbBhChannelinfoExample example);

    int deleteByExample(TbBhChannelinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBhChannelinfo record);

    int insertSelective(TbBhChannelinfo record);

    List<TbBhChannelinfo> selectByExample(TbBhChannelinfoExample example);

    TbBhChannelinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBhChannelinfo record, @Param("example") TbBhChannelinfoExample example);

    int updateByExample(@Param("record") TbBhChannelinfo record, @Param("example") TbBhChannelinfoExample example);

    int updateByPrimaryKeySelective(TbBhChannelinfo record);

    int updateByPrimaryKey(TbBhChannelinfo record);
}