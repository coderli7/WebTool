package com.lxl.webtool.schduletask;

import com.alibaba.fastjson.JSON;
import com.lxl.webtool.commonutils.MyCommonUtils;
import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.commonutils.MyHttpUtils;
import com.lxl.webtool.commonutils.TokenService;
import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.model.AIOJsonModel.*;
import com.lxl.webtool.model.AIOJsonModelNew.Invoice_info;
import com.lxl.webtool.model.FieldAnnoInfo;
import com.lxl.webtool.model.api.push.BHPushCostDetail;
import com.lxl.webtool.model.api.push.BHPushImageModel;
import com.lxl.webtool.model.api.push.BHPushMedicalResultModel;
import com.lxl.webtool.model.api.push.BHPushRequestModel;
import com.lxl.webtool.model.api.response.BHPushResopnse;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TaskUtils {

    /**
     * 0.将数据转化为符合第三方格式 如：移除一些不必须要属性，赋予一些默认值等
     *
     * @param orgResult
     * @return
     */
    public static String convertAPIResultNew(String orgResult) {
        // JsonRootBeanNew rootBean = JSON.toJavaObject(
        // 		JSON.parseObject(orgResult), JsonRootBeanNew.class);
        // AIOJsonModelNew.ResultData resultData = rootBean
        // 		.getResultData();
        // // 0.案件信息
        // BHPushRequestModel pushRequestModel = new BHPushRequestModel();
        // String curCaseId = rootBean.getCaseId();
        // pushRequestModel.setCaseId(curCaseId);
        //
        // // 1.解析model
        // List<Invoice_info> invoice_info = resultData.getInvoice_info();
        // List<BHPushImageModel> bhPushImageModels = getImgInfoFromInvoiceinfos(
        // 		invoice_info);
        // pushRequestModel.setImages(bhPushImageModels);
        // String pushResult = JSON.toJSONString(pushRequestModel);
        // return pushResult;

        return null;
    }

    /**
     * 解析新json
     *
     * @param invoice_infos
     * @return
     */
    private static List<BHPushImageModel> getImgInfoFromInvoiceinfos(
            List<Invoice_info> invoice_infos) {
        List<BHPushImageModel> imageModels = null;
        if (invoice_infos != null && invoice_infos.size() > 0) {
            imageModels = new ArrayList<>();
            for (Invoice_info invoice_info : invoice_infos) {
                BHPushImageModel imageModel = getImgInfoFromInvoiceinfo(
                        invoice_info);
                if (imageModel != null) {
                    imageModels.add(imageModel);
                }
            }
        }
        return imageModels;
    }

    /**
     * 核心转换解析
     *
     * @param invoice_info
     * @return
     */
    private static BHPushImageModel getImgInfoFromInvoiceinfo(
            Invoice_info invoice_info) {

        BHPushImageModel imageModel = new BHPushImageModel();
        imageModel.setStatus(true);
        imageModel.setFileName(
                getImgNameWithoutSubffix(invoice_info.getFile_name()));


        List<com.lxl.webtool.model.AIOJsonModelNew.Fee_detail> fee_detail = invoice_info
                .getFee_detail();
        // 票据类型，暂时先根据清单列表数量判断即可
        String docType = "invoice";
        String number = invoice_info.getNumber();
        if (StringUtils.isNotBlank(number) && number.contains("清单")
                || (fee_detail != null && fee_detail.size() > 15)) {
            docType = "detail";
        }
        imageModel.setBill_type(docType);
        BHPushMedicalResultModel medicalResultModel = new BHPushMedicalResultModel();
        medicalResultModel.setCity(invoice_info.getHospital_City());
        medicalResultModel.setHospital_name(invoice_info.getHospital_name());
        medicalResultModel.setProvince(invoice_info.getHospital_province());
        medicalResultModel.setNote_no("detail".equals(docType) ? "" : number);
        medicalResultModel.setType(invoice_info.getInvoice_type());

        // 判断属于门诊还是住院发票
        // 0 门诊
        // 1 住院
        // 2 生育
        // 3 门诊特殊病。
        String invoice_type_Val = "";
        if (!"detail".equals(docType)) {
            // 发票（类型:2-住院；1-门诊；0-其他(api值)）
            String invoice_type = invoice_info.getInvoice_type();
            if ("0".equals(invoice_type)) {
                invoice_type_Val = "1";
            } else if ("1".equals(invoice_type)) {
                invoice_type_Val = "2";
            } else {
                invoice_type_Val = "0";
            }
        } else {
            // 清单
            invoice_type_Val = "0";
        }
        medicalResultModel.setType(invoice_type_Val);
        if ("2".equals(invoice_type_Val)) {
            // 住院票据-日期参数
            String startDate = MyDateUtils.getNewDateStr(
                    invoice_info.getAdmit_time(), "yyyy/MM/dd", "yyyy-MM-dd");
            String endDate = MyDateUtils.getNewDateStr(
                    invoice_info.getLeave_time(), "yyyy/MM/dd", "yyyy-MM-dd");

            medicalResultModel.setStart_hospital_date(startDate);
            medicalResultModel.setEnd_hospital_date(endDate);
        }

        // 医保类型（暂时定义为医保和非医保）
        // 1 医保
        // 2 非医保
        String getMedicareVal = "1".equals(invoice_info.getMedicare_type())
                ? "医保"
                : "非医保";
        medicalResultModel.setMedical_insurance_type(getMedicareVal);

        // 总计金额
        medicalResultModel.setTotal_cost(
                MyCommonUtils.getBigDecimalVal(invoice_info.getAmount()));

        // 社保，统筹
        medicalResultModel.setFund_payments(MyCommonUtils
                .getBigDecimalVal(invoice_info.getPlan_pay_amount()));

        // 乙类自费
        medicalResultModel.setPayments_class_b(MyCommonUtils
                .getBigDecimalVal(invoice_info.getPartial_reasonable_amount()));

        // 丙类自费
        medicalResultModel.setPayments_class_c(MyCommonUtils
                .getBigDecimalVal(invoice_info.getPatient_charge_amount()));

        // 其他

        // 明细
        List<BHPushCostDetail> costList = new ArrayList<>();
        if (fee_detail != null && fee_detail.size() > 0) {
            for (com.lxl.webtool.model.AIOJsonModelNew.Fee_detail detail : fee_detail) {
                BHPushCostDetail costDetail = new BHPushCostDetail();
                // 明细
                costDetail.setName(detail.getName());
                // 大类
                costDetail.setClassName(detail.getItem_name());
                // 金额
                costDetail.setPrice(
                        MyCommonUtils.getBigDecimalVal(detail.getAmount()));
                // 医保类型（甲乙丙类）
                // 0 甲类
                // 1 乙类
                // 2 丙类
                // 9 无
                costDetail.setMedical_level(
                        convertMedicareTypeVal(detail.getMedicare_type()));
                costDetail.setSelfpay_money(MyCommonUtils
                        .getBigDecimalVal(detail.getPatient_charge_amount()));
                costDetail.setSelfpay_ratio(MyCommonUtils
                        .getBigDecimalVal(detail.getPatient_charge_ratio()));
                costList.add(costDetail);
            }
        }
        medicalResultModel.setCost_detail_list(costList);
        imageModel.setMedical_result(medicalResultModel);
        return imageModel;
    }

    /**
     * 转化为医保类型为甲乙类
     *
     * @return
     */
    public static int convertMedicareTypeVal(String medicareType) {
        int val = 0;
        if ("0".equals(medicareType)) {
            val = 1;
        } else if ("1".equals(medicareType)) {
            val = 2;
        } else if ("2".equals(medicareType)) {
            val = 3;
        }
        return val;

    }

    /**
     * 1.将数据转化为符合第三方格式 如：移除一些不必须要属性，赋予一些默认值等
     *
     * @param orgResult
     * @return
     */
    public static String convertAPIResult(String orgResult) {
        JsonRootBean rootBean = JSON.toJavaObject(JSON.parseObject(orgResult),
                JsonRootBean.class);
        ResultData resultData = rootBean.getResultData();
        BHPushRequestModel pushRequestModel = new BHPushRequestModel();
        String curCaseId = rootBean.getCaseId();
        pushRequestModel.setCaseId(curCaseId);

        // 1.门诊信息
        List<BHPushImageModel> images_clinic = getImgInfoFromClinicRecord(
                resultData.getClinic_info());
        pushRequestModel.setImages(images_clinic);

        // 2.住院信息
        List<BHPushImageModel> images_inpatient = getImgInfoFromInpatientRecords(
                resultData.getInpatient_info());
        pushRequestModel.getImages().addAll(images_inpatient);

        String pushResult = JSON.toJSONString(pushRequestModel);
        return pushResult;
    }

    /**
     * 2.将数据发送至合作伙伴
     *
     * @param orgResult
     * @return
     */
    public static BHPushResopnse sendResultToPartner(String postData) {
        BHPushResopnse bhPushResopnse = new BHPushResopnse();
        try {
            String postUrl = String.format(TokenService.ApiUrl);
            String postResult = MyHttpUtils.Post(postUrl, postData);
            if (StringUtils.isNotBlank(postResult)) {
                bhPushResopnse = JSON.toJavaObject(JSON.parseObject(postResult),
                        BHPushResopnse.class);
            }
        } catch (Exception e) {
            bhPushResopnse.setErrCode("-10000");
            bhPushResopnse.setErrMsg("推送异常:" + e.getStackTrace());
        }
        return bhPushResopnse;
    }

    /**
     * 门诊信息，转化出image报文 信息
     *
     * @param clinicRecords
     * @return
     */
    private static List<BHPushImageModel> getImgInfoFromClinicRecord(
            Clinic_info clinic_info) {
        List<BHPushImageModel> imgList = new ArrayList<>();
        if ("0".equals(clinic_info.getRecord_counts())) {
            return imgList;
        }
        List<Clinic_record> clinicRecords = clinic_info.getClinic_record();
        for (Clinic_record clinic_record : clinicRecords) {
            BHPushImageModel imageModel = new BHPushImageModel();
            imageModel.setStatus(true);
            String imgName = clinic_record.getAccident_status();
            imgName = getImgNameWithoutSubffix(imgName);
            imageModel.setFileName(imgName);

            BHPushMedicalResultModel medicalResultModel = new BHPushMedicalResultModel();
            Fee_info curFeeInfo = clinic_record.getFee_info();

            // 如果发票号和起始日期一致，则认为是清单
            String docType = "invoice";
            if (clinic_record.getClinic_no()
                    .equals(clinic_record.getFirst_date())) {
                docType = "detail";
            }
            // invoice-发票；detail-医院清单
            imageModel.setBill_type(docType);

            // 发票明细
            medicalResultModel
                    .setHospital_name(clinic_record.getHospital_code());
            medicalResultModel.setNote_no(clinic_record.getClinic_no());
            medicalResultModel.setType("1");

            // 门诊信息，不需要日期
            // String startDate = MyDateUtils.getNewDateStr(
            // clinic_record.getFirst_date(), "yyyyMMdd", "yyyy-MM-dd");
            // String endDate = MyDateUtils.getNewDateStr(
            // clinic_record.getEnd_date(), "yyyyMMdd", "yyyy-MM-dd");
            //
            // medicalResultModel.setStart_hospital_date(startDate);
            // medicalResultModel.setEnd_hospital_date(endDate);

            String calc_amount = clinic_record.getFee_info().getSum_amount();

            medicalResultModel
                    .setTotal_cost(MyCommonUtils.getBigDecimalVal(calc_amount));

            BigDecimal socialAmount = MyCommonUtils
                    .getBigDecimalVal(curFeeInfo.getDeduct_sum().getDeduct1());

            medicalResultModel.setFund_payments(socialAmount);

            medicalResultModel.setOther_deduct(MyCommonUtils
                    .getBigDecimalVal(curFeeInfo.getDeduct_sum().getDeduct2()));

            List<Fee_detail> fee_details = curFeeInfo.getFee_details()
                    .getFee_detail();
            List<BHPushCostDetail> costList = new ArrayList<>();

            for (Fee_detail fee_detail : fee_details) {

                BHPushCostDetail costDetail = new BHPushCostDetail();
                costDetail.setName(fee_detail.getItem_name());
                costDetail.setPrice(MyCommonUtils
                        .getBigDecimalVal(fee_detail.getItem_amount()));
                costList.add(costDetail);
            }

            if (costList == null || costList.size() == 0) {
                // 如果明细为空，则新增将汇总信息同步
                List<BHPushCostDetail> sumCostList = getSumDetail(
                        curFeeInfo.getMedical_fee_info());
                costList.addAll(sumCostList);
            }

            medicalResultModel.setCost_detail_list(costList);
            imageModel.setMedical_result(medicalResultModel);
            imgList.add(imageModel);
        }
        return imgList;
    }

    /**
     * 住院信息，转化出image报文 信息
     *
     * @param clinicRecords
     * @return
     */
    private static List<BHPushImageModel> getImgInfoFromInpatientRecords(
            Inpatient_info inpatient_info) {
        List<BHPushImageModel> imgList = new ArrayList<>();
        if ("0".equals(inpatient_info.getRecord_counts())) {
            return imgList;
        }
        List<Inpatient_record> inpatientRecords = inpatient_info
                .getInpatient_record();

        for (Inpatient_record inpatient_record : inpatientRecords) {
            BHPushImageModel imageModel = new BHPushImageModel();
            imageModel.setStatus(true);
            String imgName = inpatient_record.getAccident_status();

            imgName = getImgNameWithoutSubffix(imgName);

            imageModel.setFileName(imgName);

            // 如果发票号和起始日期一致，则认为是清单
            String docType = "invoice";
            if (inpatient_record.getOperation_no()
                    .equals(inpatient_record.getIn_date())) {
                docType = "detail";
            }
            // invoice-发票；detail-医院清单
            imageModel.setBill_type(docType);

            BHPushMedicalResultModel medicalResultModel = new BHPushMedicalResultModel();
            Fee_info curFeeInfo = inpatient_record.getFee_info();
            // 发票明细
            medicalResultModel
                    .setHospital_name(inpatient_record.getHospital_code());
            medicalResultModel.setNote_no(inpatient_record.getOperation_no());
            medicalResultModel.setType("2");

            String startDate = MyDateUtils.getNewDateStr(
                    inpatient_record.getIn_date(), "yyyyMMdd", "yyyy-MM-dd");
            String endDate = MyDateUtils.getNewDateStr(
                    inpatient_record.getOut_date(), "yyyyMMdd", "yyyy-MM-dd");

            medicalResultModel.setStart_hospital_date(startDate);
            medicalResultModel.setEnd_hospital_date(endDate);

            String calc_amount = inpatient_record.getFee_info().getSum_amount();
            medicalResultModel
                    .setTotal_cost(MyCommonUtils.getBigDecimalVal(calc_amount));
            BigDecimal socialAmount = MyCommonUtils
                    .getBigDecimalVal(curFeeInfo.getDeduct_sum().getDeduct1());
            medicalResultModel.setFund_payments(socialAmount);
            medicalResultModel.setOther_deduct(MyCommonUtils
                    .getBigDecimalVal(curFeeInfo.getDeduct_sum().getDeduct2()));
            List<Fee_detail> fee_details = curFeeInfo.getFee_details()
                    .getFee_detail();
            List<BHPushCostDetail> costList = new ArrayList<>();

            // 先汇总在明细
            List<BHPushCostDetail> sumCostList = getSumDetail(
                    curFeeInfo.getMedical_fee_info());
            costList.addAll(sumCostList);

            for (Fee_detail fee_detail : fee_details) {
                BHPushCostDetail costDetail = new BHPushCostDetail();
                costDetail.setName(fee_detail.getItem_name());
                costDetail.setPrice(MyCommonUtils
                        .getBigDecimalVal(fee_detail.getItem_amount()));
                costList.add(costDetail);
            }
            medicalResultModel.setCost_detail_list(costList);
            imageModel.setMedical_result(medicalResultModel);
            imgList.add(imageModel);
        }
        return imgList;
    }

    /**
     * 获取不包含后缀名的imgName
     *
     * @param imgName
     * @return
     */
    private static String getImgNameWithoutSubffix(String imgName) {
        // 此处针对返回数据,需要去掉文件后缀名,与原始文件保持一致
        int subffixIdx = imgName.lastIndexOf(".");
        if (subffixIdx > 0) {
            imgName = imgName.substring(0, subffixIdx);
        }
        return imgName;
    }

    /**
     * 获取票据汇总清单
     *
     * @param medical_fee_info
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private static List<BHPushCostDetail> getSumDetail(
            Medical_fee_info medical_fee_info) {
        List<BHPushCostDetail> costList = null;
        // 解析汇总信息
        try {
            List<FieldAnnoInfo> modelFieldAnnoInfo = MyCommonUtils
                    .getModelFieldAnnoInfo(medical_fee_info);
            costList = new ArrayList<>();
            for (FieldAnnoInfo fieldAnnoInfo : modelFieldAnnoInfo) {
                BHPushCostDetail costDetail = new BHPushCostDetail();
                costDetail.setName(fieldAnnoInfo.getFieldAnno());
                costDetail.setPrice(MyCommonUtils
                        .getBigDecimalVal(fieldAnnoInfo.getFieldValue()));
                costList.add(costDetail);
            }
        } catch (Exception e) {
            MyLoggerFactory.log(LoggerEnum.Error,
                    String.format("函数getSumDetail执行异常:%s", e.getMessage()));
        }
        return costList;
    }

}
