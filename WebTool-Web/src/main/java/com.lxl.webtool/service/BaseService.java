package com.lxl.webtool.service;

import com.lxl.webtool.dao.ImageCaseInfoService;
import com.lxl.webtool.dao.ImageService;
import com.lxl.webtool.model.WebApiRequest.BaseRequest;
import com.lxl.webtool.model.WebApiResponse.BaseResponse;

public abstract class BaseService implements IBaseService {

	/**
	 * 请求参
	 */
	public BaseRequest request = null;

	/**
	 * 返回参
	 */
	public BaseResponse response = null;

	/**
	 * 图像记录服务
	 */
	public ImageService imageService = null;

	/**
	 * 案件信息服务
	 */
	public ImageCaseInfoService imageCaseInfoService = null;

	@Override
	public abstract void Excute();

}
