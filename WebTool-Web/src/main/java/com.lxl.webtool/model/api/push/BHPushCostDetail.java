package com.lxl.webtool.model.api.push;

public class BHPushCostDetail {

	private String name;

	private String className;

	private Number amount;

	private Number price;

	private Number medical_type;

	private Number medical_level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Number getAmount() {
		return amount;
	}

	public void setAmount(Number amount) {
		this.amount = amount;
	}

	public Number getPrice() {
		return price;
	}

	public void setPrice(Number price) {
		this.price = price;
	}

	public Number getMedical_type() {
		return medical_type;
	}

	public void setMedical_type(Number medical_type) {
		this.medical_type = medical_type;
	}

	public Number getMedical_level() {
		return medical_level;
	}

	public void setMedical_level(Number medical_level) {
		this.medical_level = medical_level;
	}

	public Number getSelfpay_ratio() {
		return selfpay_ratio;
	}

	public void setSelfpay_ratio(Number selfpay_ratio) {
		this.selfpay_ratio = selfpay_ratio;
	}

	public Number getSelfpay_money() {
		return selfpay_money;
	}

	public void setSelfpay_money(Number selfpay_money) {
		this.selfpay_money = selfpay_money;
	}

	private Number selfpay_ratio;

	private Number selfpay_money;
}
