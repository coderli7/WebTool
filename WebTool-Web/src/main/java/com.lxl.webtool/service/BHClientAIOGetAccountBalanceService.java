package com.lxl.webtool.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxl.webtool.commonutils.MyCommonUtils;
import com.lxl.webtool.commonutils.MyHttpUtils;
import com.lxl.webtool.commonutils.TokenService;
import com.lxl.webtool.model.BaseAcResponse;
import com.lxl.webtool.model.WebApiResponse.BaseResponse;

import java.math.BigDecimal;


public class BHClientAIOGetAccountBalanceService extends BaseService {

	@Override
	public void Excute() {
		response = new BaseResponse();
		try {
			String requParams = String.format("?token=%s&companyId=%s",
					TokenService.getAcToken(), TokenService.companyId);
			String getAccountBalanceUrl = String.format("%s%s%s",
					TokenService.url, "/AIOGetAccountBalance", requParams);
			String getAccountBalanceResult = MyHttpUtils
					.Get(getAccountBalanceUrl);
			if (getAccountBalanceResult != null && getAccountBalanceUrl != "") {
				BaseAcResponse baseAcResponse = JSON.toJavaObject(
						JSONObject.parseObject(getAccountBalanceResult),
						BaseAcResponse.class);

				// 保留两位小数展示
				BigDecimal decimalVal = MyCommonUtils
						.getBigDecimalVal(baseAcResponse.getData());
				baseAcResponse.setData(decimalVal.toString());
				response.Info = baseAcResponse;
			}
			response.ErrCode = 0;
			response.ErrMsg = "查询完成!";
		} catch (Exception e) {
			response.ErrCode = -10000;
			response.ErrMsg = String.format(
					"BHClientAIOGetAccountBalanceService执行异常%s",
					e.getMessage());
		}
	}

}
