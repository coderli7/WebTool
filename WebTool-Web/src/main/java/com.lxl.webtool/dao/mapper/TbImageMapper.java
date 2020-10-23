package com.lxl.webtool.dao.mapper;


import com.lxl.webtool.dao.pojo.TbImage;
import com.lxl.webtool.dao.pojo.TbImageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbImageMapper {
	int countByExample(TbImageExample example);

	int deleteByExample(TbImageExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbImage record);

	int insertSelective(TbImage record);

	List<TbImage> selectByExample(TbImageExample example);

	TbImage selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbImage record,
                                 @Param("example") TbImageExample example);

	int updateByExample(@Param("record") TbImage record,
                        @Param("example") TbImageExample example);

	int updateByPrimaryKeySelective(TbImage record);

	int updateByPrimaryKey(TbImage record);

	List<TbImage> findByCaseId(String caseId);

	int updateByPrimaryKeyWithBLOBs(TbImage record);

	List<TbImage> selectByExampleWithBLOBs(TbImageExample example);

	List<TbImage> findByDate(@Param("startDate") String startDate,
                             @Param("endDate") String endDate);

}