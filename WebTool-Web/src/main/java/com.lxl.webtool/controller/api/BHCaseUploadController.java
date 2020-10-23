package com.lxl.webtool.controller.api;

import com.alibaba.fastjson.JSON;
import com.lxl.webtool.dao.api.BHCaseUploadService;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.log.Mylogger;
import com.lxl.webtool.model.api.request.BHCaseUploadApiRequest;
import com.lxl.webtool.model.api.response.BaseApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class BHCaseUploadController {

	private static final Mylogger logger = MyLoggerFactory
			.getLogger(BHCaseUploadController.class);

	@Autowired
	private BHCaseUploadService bhCaseUploadService;

	@RequestMapping("/CaseUpload")
	public BaseApiResponse caseUpload(
			@RequestBody BHCaseUploadApiRequest request) {
		// System.out.println(JSON.toJSONString(request));
		logger.logMsg(String.format("Begin:%s", JSON.toJSONString(request)));
		BaseApiResponse apiResponse = bhCaseUploadService.excute(request);
		logger.logMsg(String.format("End:%s", JSON.toJSONString(apiResponse)));
		return apiResponse;
	}
}
