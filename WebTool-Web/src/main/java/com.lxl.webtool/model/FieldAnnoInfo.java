package com.lxl.webtool.model;

/**
 * 主要用于解析含注释的Model 便于理解
 * 
 * @author Administrator
 *
 */
public class FieldAnnoInfo {

	/**
	 * 字段名
	 */
	private String fieldName;

	/**
	 * 字段注解
	 */
	private String fieldAnno;

	/**
	 * 字段值
	 */
	private String fieldValue;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldAnno() {
		return fieldAnno;
	}

	public void setFieldAnno(String fieldAnno) {
		this.fieldAnno = fieldAnno;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	

}
