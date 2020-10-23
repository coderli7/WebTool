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
public class ResultData {

    private List<Invoice_info> invoice_info;
    private List<Integer> problem_type;
    private String patient_name;
    private List<File_info> file_info;
    private String claim_list_id;
    public void setInvoice_info(List<Invoice_info> invoice_info) {
         this.invoice_info = invoice_info;
     }
     public List<Invoice_info> getInvoice_info() {
         return invoice_info;
     }

    public void setProblem_type(List<Integer> problem_type) {
         this.problem_type = problem_type;
     }
     public List<Integer> getProblem_type() {
         return problem_type;
     }

    public void setPatient_name(String patient_name) {
         this.patient_name = patient_name;
     }
     public String getPatient_name() {
         return patient_name;
     }

    public void setFile_info(List<File_info> file_info) {
         this.file_info = file_info;
     }
     public List<File_info> getFile_info() {
         return file_info;
     }

    public void setClaim_list_id(String claim_list_id) {
         this.claim_list_id = claim_list_id;
     }
     public String getClaim_list_id() {
         return claim_list_id;
     }

}