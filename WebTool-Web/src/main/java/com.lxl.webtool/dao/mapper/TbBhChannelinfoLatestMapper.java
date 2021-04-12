package com.lxl.webtool.dao.mapper;

import com.lxl.webtool.dao.pojo.TbBhChannelinfoLatest;
import com.lxl.webtool.dao.pojo.TbBhChannelinfoLatestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbBhChannelinfoLatestMapper {
    int countByExample(TbBhChannelinfoLatestExample example);

    int deleteByExample(TbBhChannelinfoLatestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBhChannelinfoLatest record);

    int insertSelective(TbBhChannelinfoLatest record);

    List<TbBhChannelinfoLatest> selectByExample(TbBhChannelinfoLatestExample example);

    TbBhChannelinfoLatest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBhChannelinfoLatest record, @Param("example") TbBhChannelinfoLatestExample example);

    int updateByExample(@Param("record") TbBhChannelinfoLatest record, @Param("example") TbBhChannelinfoLatestExample example);

    int updateByPrimaryKeySelective(TbBhChannelinfoLatest record);

    int updateByPrimaryKey(TbBhChannelinfoLatest record);

    List<TbBhChannelinfoLatest> selectByChannelKey(@Param("channelKey")String channelKey);

}