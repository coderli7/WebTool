package com.lxl.webtool.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lxl.webtool.commonutils.MyBase64Utils;
import com.lxl.webtool.dao.pojo.TbImage;
import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.model.ImageACResponse;
import com.lxl.webtool.model.WebApiRequest.ImageRequest;
import com.lxl.webtool.model.WebApiResponse.BaseResponse;

public class AIOGetImgService extends BaseService {

	@Override
	public void Excute() {
		response = new BaseResponse();
		ImageRequest imageRequest = (ImageRequest) request;
		TbImage image = new TbImage();
		image.setCaseid(imageRequest.CaseId);
		image.setImgname(imageRequest.fileName);
		List<TbImage> images = imageService.findByModel(image);
		if (images != null && images.size() == 1) {
			TbImage tbImage = images.get(0);
			String imageToBase64ByLocal = MyBase64Utils
					.ImageToBase64ByLocal(tbImage.getImgpath());
			ImageACResponse imageACResponse = new ImageACResponse();
			imageACResponse.setCode(0);;
			imageACResponse.setMessage("OK");
			imageACResponse.setData(imageToBase64ByLocal);

			MyLoggerFactory.log(LoggerEnum.ServiceController, String
					.format("获取图片结果为:%s", JSON.toJSONString(imageACResponse)));
			
			response.Info = imageACResponse;
		}
	}

}
