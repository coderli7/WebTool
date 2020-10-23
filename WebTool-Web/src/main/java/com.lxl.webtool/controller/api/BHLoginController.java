package com.lxl.webtool.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.lxl.webtool.dao.api.APITokenService;
import com.lxl.webtool.dao.api.BHLoginService;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.log.Mylogger;
import com.lxl.webtool.model.api.response.BHLoginApiResponse;
import com.lxl.webtool.model.api.response.BaseApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BHLoginController {

	private static final Mylogger logger = MyLoggerFactory
			.getLogger(BHLoginController.class);

	@Autowired
	private APITokenService apiTokenService;

	@Autowired
	private BHLoginService bhLoginService;

	@RequestMapping("/Login")
	public BaseApiResponse Login(String companyId, String timeStamp) {
		logger.logMsg(String.format("登录开始,用户名:%s", companyId));
		BHLoginApiResponse apiResponse = (BHLoginApiResponse) bhLoginService
				.Excute(companyId, timeStamp);
		if ("0".equals(apiResponse.getCode())) {
			apiTokenService.insertRedis(companyId, apiResponse.getData());
		}
		logger.logMsg(
				String.format("登录结束:%s", JSONObject.toJSONString(apiResponse)));

		return apiResponse;
	}
}
