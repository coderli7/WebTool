/**
  * Copyright 2020 bejson.com 
  */
package com.lxl.webtool.model.AIOJsonModelNew;
import java.util.List;

/**
 * Auto-generated: 2020-04-06 21:14:36
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Disease {

    private String name;
    private List<String> fee_item;
    private String icd10_id;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setFee_item(List<String> fee_item) {
         this.fee_item = fee_item;
     }
     public List<String> getFee_item() {
         return fee_item;
     }

    public void setIcd10_id(String icd10_id) {
         this.icd10_id = icd10_id;
     }
     public String getIcd10_id() {
         return icd10_id;
     }

}