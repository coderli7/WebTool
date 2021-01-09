package com.lxl.webtool.dao.mapper;

import com.lxl.webtool.dao.pojo.TbBhNote;
import com.lxl.webtool.dao.pojo.TbBhNoteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbBhNoteMapper {

    int countByExample(TbBhNoteExample example);

    int deleteByExample(TbBhNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBhNote record);

    int insertSelective(TbBhNote record);

    List<TbBhNote> selectByExample(TbBhNoteExample example);

    TbBhNote selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBhNote record, @Param("example") TbBhNoteExample example);

    int updateByExample(@Param("record") TbBhNote record, @Param("example") TbBhNoteExample example);

    int updateByPrimaryKeySelective(TbBhNote record);

    int updateByPrimaryKey(TbBhNote record);
}