package com.lxl.webtool.model.api.request;

import java.util.List;

public class BHCaseUploadApiRequest  extends BaseApiRequest{

	private String caseId;
	
	private String caseInfo ;
	
	private List<String> images ;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(String caseInfo) {
		this.caseInfo = caseInfo;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
	
	
}
