package com.lxl.webtool.model.api.push;

import java.util.List;

/**
 * 
 * 用于标记转换推送至第三方 相关字段 
 * 不能整体使用原有model
 * @author Administrator
 *
 */
public class BHPushRequestModel {
	
	private String caseId ;
	
	private List<BHPushImageModel> images;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public List<BHPushImageModel> getImages() {
		return images;
	}

	public void setImages(List<BHPushImageModel> images) {
		this.images = images;
	}
	
	

}
