/**
  * Copyright 2019 bejson.com 
  */
package com.lxl.webtool.model.AIOJsonModel;

import com.lxl.webtool.commonutils.MyAnnotation;

import java.util.List;


/**
 * Auto-generated: 2019-11-07 14:8:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Surgery_info {

	@MyAnnotation(name="手术信息记录")
    private String record_counts;
	
	@MyAnnotation(name="手术信息明细")
    private List<Surgery_record> surgery_record;
    
    
    public void setRecord_counts(String record_counts) {
         this.record_counts = record_counts;
     }
     public String getRecord_counts() {
         return record_counts;
     }

    public void setSurgery_record(List<Surgery_record> surgery_record) {
         this.surgery_record = surgery_record;
     }
     public List<Surgery_record> getSurgery_record() {
         return surgery_record;
     }

}