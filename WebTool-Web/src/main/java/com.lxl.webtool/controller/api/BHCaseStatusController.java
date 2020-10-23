package com.lxl.webtool.controller.api;

import com.alibaba.fastjson.JSON;
import com.lxl.webtool.dao.api.BHCaseStatusService;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.log.Mylogger;
import com.lxl.webtool.model.api.request.BHCaseStatusApiRequest;
import com.lxl.webtool.model.api.response.BaseApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BHCaseStatusController {

	private static final Mylogger logger = MyLoggerFactory
			.getLogger(BHCaseStatusController.class);

	@Autowired
	private BHCaseStatusService bhCaseStatusService;

	@RequestMapping("/CaseStatus")
	public BaseApiResponse caseStatus(String companyId, String token,
									  String caseId) {
		logger.logMsg(String.format("Begin:caseId=%s", caseId));
		BHCaseStatusApiRequest request = new BHCaseStatusApiRequest();
		request.setCaseId(caseId);
		request.setToken(token);
		request.setCompanyId(companyId);
		BaseApiResponse apiResponse = bhCaseStatusService.excute(request);
		logger.logMsg(String.format("End:%s", JSON.toJSONString(apiResponse)));
		return apiResponse;
	}
}
