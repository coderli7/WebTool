package com.lxl.webtool.dao.api;

import com.lxl.webtool.commonutils.MyCommonUtils;
import com.lxl.webtool.model.BaseAcResponse;
import com.lxl.webtool.model.WebApiRequest.BaseRequest;
import com.lxl.webtool.model.api.request.BHCaseStatusApiRequest;
import com.lxl.webtool.model.api.request.BaseApiRequest;
import com.lxl.webtool.model.api.response.BHCaseStatusApiResponse;
import com.lxl.webtool.model.api.response.BaseApiResponse;
import com.lxl.webtool.service.BHClientGetAIOStatusService;
import com.lxl.webtool.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service("bhCaseStatusService")
public class BHCaseStatusService implements IBHBaseService {

	@Override
	public BaseApiResponse excute(BaseApiRequest apiRequest) {
		BHCaseStatusApiResponse apiResponse = new BHCaseStatusApiResponse();
		try {
			BHCaseStatusApiRequest bhCaseStatusApiRequest = (BHCaseStatusApiRequest) apiRequest;
			String curCaseId = bhCaseStatusApiRequest.getCaseId();
			BaseService service = new BHClientGetAIOStatusService();
			service.request = new BaseRequest();
			service.request.CaseId = curCaseId;
			service.Excute();
			String retTips = "";
			if (service.response.Info != null) {
				BaseAcResponse acResponse = (BaseAcResponse) service.response.Info;
				retTips = StringUtils.isEmpty(acResponse.getData())
						? acResponse.getMessage()
						: MyCommonUtils.getCaseTips(acResponse.getData());
			} else {
				apiResponse.setCode("-10000");
				retTips = "未获取到最新状态";
			}

			apiResponse.setCode("0");
			apiResponse.setMessage("处理完成");
			apiResponse.setData(retTips);
		} catch (Exception e) {
			apiResponse.setCode("-10000");
			apiResponse.setMessage("查询信息异常!");
		}
		return apiResponse;
	}

}
