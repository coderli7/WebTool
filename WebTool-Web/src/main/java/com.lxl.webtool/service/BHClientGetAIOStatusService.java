package com.lxl.webtool.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxl.webtool.commonutils.MyCommonUtils;
import com.lxl.webtool.commonutils.MyHttpUtils;
import com.lxl.webtool.commonutils.TokenService;
import com.lxl.webtool.model.BaseAcResponse;
import com.lxl.webtool.model.WebApiResponse.BaseResponse;

public class BHClientGetAIOStatusService extends BaseService {

	@Override
	public void Excute() {

		response = new BaseResponse();
		String token_req = TokenService.getAcToken();
		String companyId_req = TokenService.companyId;
		String key_req = TokenService.key;
		String caseId = request.CaseId;
		String timeStamp = String.valueOf(System.currentTimeMillis());
		String signature_before_cal = "token=" + token_req + "&" + "companyId="
				+ companyId_req + "&" + "caseId=" + caseId + "&" + "timeStamp="
				+ timeStamp + "&key=" + key_req;
		String signature = MyCommonUtils.getMD5Str(signature_before_cal);
		String requestData = String.format(
				"?token=%s&companyId=%s&caseId=%s&timeStamp=%s&signature=%s",
				token_req, companyId_req, caseId, timeStamp, signature);
		String reqUrl = String.format("%s%s%s", TokenService.url,
				"/AIOGetCaseStatus", requestData);
		String res = null;
		try {
			res = MyHttpUtils.Get(reqUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseAcResponse acResponse = JSON.toJavaObject(
				JSONObject.parseObject(res), BaseAcResponse.class);
		response.ErrCode = 0;
		response.ErrMsg = "接受完成！";
		response.Info = acResponse;
	}

}
