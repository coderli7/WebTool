package com.lxl.webtool.model.api.push;

import java.util.List;

public class BHPushMedicalResultModel {

	private String province;

	private String city;

	private String gov_supervised;

	private String type;

	private String hospital_name;

	private String medical_organization_type;

	private String note_no;

	private String medical_insurance_type;

	private String start_hospital_date;

	private String end_hospital_date;

	private Number hospital_days;

	private String section;

	private Number total_cost;

	private Number fund_payments;

	private Number payments_class_b;

	private Number payments_class_c;

	private Number other_deduct;

	private List<BHPushCostDetail> cost_detail_list;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGov_supervised() {
		return gov_supervised;
	}

	public void setGov_supervised(String gov_supervised) {
		this.gov_supervised = gov_supervised;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public String getMedical_organization_type() {
		return medical_organization_type;
	}

	public void setMedical_organization_type(String medical_organization_type) {
		this.medical_organization_type = medical_organization_type;
	}

	public String getNote_no() {
		return note_no;
	}

	public void setNote_no(String note_no) {
		this.note_no = note_no;
	}

	public String getMedical_insurance_type() {
		return medical_insurance_type;
	}

	public void setMedical_insurance_type(String medical_insurance_type) {
		this.medical_insurance_type = medical_insurance_type;
	}

	public String getStart_hospital_date() {
		return start_hospital_date;
	}

	public void setStart_hospital_date(String start_hospital_date) {
		this.start_hospital_date = start_hospital_date;
	}

	public String getEnd_hospital_date() {
		return end_hospital_date;
	}

	public void setEnd_hospital_date(String end_hospital_date) {
		this.end_hospital_date = end_hospital_date;
	}

	public Number getHospital_days() {
		return hospital_days;
	}

	public void setHospital_days(Number hospital_days) {
		this.hospital_days = hospital_days;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Number getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(Number total_cost) {
		this.total_cost = total_cost;
	}

	public Number getFund_payments() {
		return fund_payments;
	}

	public void setFund_payments(Number fund_payments) {
		this.fund_payments = fund_payments;
	}

	public Number getPayments_class_b() {
		return payments_class_b;
	}

	public void setPayments_class_b(Number payments_class_b) {
		this.payments_class_b = payments_class_b;
	}

	public Number getPayments_class_c() {
		return payments_class_c;
	}

	public void setPayments_class_c(Number payments_class_c) {
		this.payments_class_c = payments_class_c;
	}

	public Number getOther_deduct() {
		return other_deduct;
	}

	public void setOther_deduct(Number other_deduct) {
		this.other_deduct = other_deduct;
	}

	public List<BHPushCostDetail> getCost_detail_list() {
		return cost_detail_list;
	}

	public void setCost_detail_list(List<BHPushCostDetail> cost_detail_list) {
		this.cost_detail_list = cost_detail_list;
	}

}
