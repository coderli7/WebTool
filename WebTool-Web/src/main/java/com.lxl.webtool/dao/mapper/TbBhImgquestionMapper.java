package com.lxl.webtool.dao.mapper;

import com.lxl.webtool.dao.pojo.TbBhImgquestion;
import com.lxl.webtool.dao.pojo.TbBhImgquestionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbBhImgquestionMapper {
    int countByExample(TbBhImgquestionExample example);

    int deleteByExample(TbBhImgquestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBhImgquestion record);

    int insertSelective(TbBhImgquestion record);

    List<TbBhImgquestion> selectByExample(TbBhImgquestionExample example);

    TbBhImgquestion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBhImgquestion record, @Param("example") TbBhImgquestionExample example);

    int updateByExample(@Param("record") TbBhImgquestion record, @Param("example") TbBhImgquestionExample example);

    int updateByPrimaryKeySelective(TbBhImgquestion record);

    int updateByPrimaryKey(TbBhImgquestion record);
}