package com.lxl.webtool.controller.api;

import com.alibaba.fastjson.JSON;
import com.lxl.webtool.dao.api.BHCaseDataUploadService;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.log.Mylogger;
import com.lxl.webtool.model.api.request.BHCaseDataUploadApiRequest;
import com.lxl.webtool.model.api.response.BaseApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("/api")
public class BHCaseDataUploadController {

	private static final Mylogger logger = MyLoggerFactory
			.getLogger(BHCaseDataUploadController.class);

	@Autowired
	private BHCaseDataUploadService bhCaseDataUploadService;

	@RequestMapping("/CaseDataUpload")
	public BaseApiResponse caseDataUpload(
			@RequestBody BHCaseDataUploadApiRequest request) {

		logger.logMsg(String.format("Begin:%s", JSON.toJSONString(request)));
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String filePath = httpServletRequest.getSession().getServletContext()
				.getRealPath("/image");
		String caseImagePath = String.format("%s\\%s", filePath,
				request.getCaseId());
		File curImgFile = new File(caseImagePath);
		if (!curImgFile.exists()) {
			curImgFile.mkdirs();
		}

		// 兼容客户不传递后缀名，需要生成从base64中生成新的后缀名
		String imgPath = caseImagePath;
		request.setFilePath(imgPath);

		request.setFilePath(caseImagePath);
		BaseApiResponse apiResponse = bhCaseDataUploadService.excute(request);
		logger.logMsg(String.format("End:%s", JSON.toJSONString(apiResponse)));
		return apiResponse;
	}

}
