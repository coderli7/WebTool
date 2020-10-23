package com.lxl.webtool.dao.api;

import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.dao.mapper.TbImageCaseInfoMapper;
import com.lxl.webtool.dao.mapper.TbImageMapper;
import com.lxl.webtool.dao.pojo.TbImage;
import com.lxl.webtool.dao.pojo.TbImageCaseInfo;
import com.lxl.webtool.model.api.request.BHCaseUploadApiRequest;
import com.lxl.webtool.model.api.request.BaseApiRequest;
import com.lxl.webtool.model.api.response.BaseApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service("bhCaseUploadService")
public class BHCaseUploadService implements IBHBaseService {

	@Autowired
	private TbImageCaseInfoMapper imageCaseInfoMapper;

	@Autowired
	private TbImageMapper imageMapper;

	@Override
	public BaseApiResponse excute(BaseApiRequest apiRequest) {
		BaseApiResponse apiResponse = new BaseApiResponse();
		try {

			BHCaseUploadApiRequest bhCaseUploadApiRequest = (BHCaseUploadApiRequest) apiRequest;
			List<String> imgs = bhCaseUploadApiRequest.getImages();
			if (imgs != null && imgs.size() != 1) {

				// 校验图片总计，数量必须为1
				apiResponse.setCode("-10000");
				apiResponse.setMessage("案件票据数量必须为1");
				return apiResponse;
			}

			/**
			 * 1.先插入表头数据，注意，行数据中标明，api用户 2.按照明细插入行数据，注意，不记录存储路径 3.返回插入成功
			 */
			List<TbImageCaseInfo> findCaseList = imageCaseInfoMapper
					.findByCaseId(bhCaseUploadApiRequest.getCaseId());
			if (findCaseList != null && findCaseList.size() == 0) {

				String curCaseId = bhCaseUploadApiRequest.getCaseId();
				// 插入表头数据
				TbImageCaseInfo imageCaseInfo = new TbImageCaseInfo();
				imageCaseInfo.setCaseid(curCaseId);
				imageCaseInfo
						.setUsercode(bhCaseUploadApiRequest.getCompanyId());
				imageCaseInfo.setCasestatus("未完成");
				imageCaseInfo.setCasedate(MyDateUtils.getDateTimeNow(null));
				// 标记为api提交数据
				imageCaseInfo.setApisign("1");
				imageCaseInfo.setApipushsign("0");
				imageCaseInfoMapper.insert(imageCaseInfo);

				// 插入明细数据
				List<String> images = bhCaseUploadApiRequest.getImages();
				for (String imgName : images) {
					TbImage imageInfo = new TbImage();
					imageInfo.setCaseid(curCaseId);
					imageInfo.setImgname(imgName);
					imageInfo.setDelstatus("0");
					imageInfo.setImgpath("");
					imageInfo.setImagedate(MyDateUtils.getDateTimeNow(null));
					imageMapper.insert(imageInfo);
				}
				apiResponse.setCode("0");
				apiResponse.setMessage("新增成功");
			} else {
				apiResponse.setCode("-10000");
				apiResponse.setMessage("信息已存在");
			}
		} catch (Exception e) {
			apiResponse.setCode("-10000");
			apiResponse.setMessage("新增信息异常!");
		}
		return apiResponse;
	}

}
