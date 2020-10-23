package com.lxl.webtool.model.api.push;

public class BHPushImageModel {

	private String fileName;

	private String bill_type;

	private Boolean status;

	private BHPushMedicalResultModel medical_result;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public BHPushMedicalResultModel getMedical_result() {
		return medical_result;
	}

	public void setMedical_result(BHPushMedicalResultModel medical_result) {
		this.medical_result = medical_result;
	}

}
