package com.lxl.webtool.model.api.request;

public class BaseApiRequest {

	private String companyId;
	
	private String token;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
