package com.lxl.webtool.schduletask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.dao.ImageCaseInfoService;
import com.lxl.webtool.dao.pojo.TbImageCaseInfo;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.log.Mylogger;
import com.lxl.webtool.model.api.response.BHPushResopnse;

/**
 * 识别结果推送至第三方
 * 
 * @author Administrator
 *
 */
/*
@Component
*/
public class SendToTXTask {

	private static final Mylogger logger = MyLoggerFactory
			.getLogger(SendToTXTask.class);

	@Autowired
	private ImageCaseInfoService imageCaseInfoService;

	/**
	 * 1.将结果推送至第三方，如果有的话 可以在插入字段中，区分本机用户和第三方用户 用于区分对应操作 最初设计为向第三方推送数据
	 */
	@Scheduled(cron = "0 0/2 5-23 * * ? ")
	public void sendResultToTx() {

		/*
		 * 1.获取到需要传递第三方，但并传递或者未成功传递的数据 2.获取到数据，并做相应序列化操作 3.发送数据至TX
		 * 4.根据甜新返回状态，更新对应表数据
		 */

		// 1.查询
		TbImageCaseInfo findImgInfo = new TbImageCaseInfo();
		findImgInfo.setCasestatus("完成");
		findImgInfo.setApisign("1");
		findImgInfo.setApipushsign("0");
		List<TbImageCaseInfo> caseInfos = imageCaseInfoService
				.findApiCase(findImgInfo);

		// 2.遍历更新
		for (TbImageCaseInfo tbImageCaseInfo : caseInfos) {

			logger.logMsg("开始推送数据1,案件号:" + tbImageCaseInfo.getCaseid());
			String curImgResult = tbImageCaseInfo.getResult();

			// 3.转换推送msg
			// String postData = TaskUtils.convertAPIResult(curImgResult);
			String postData = TaskUtils.convertAPIResultNew(curImgResult);
			logger.logMsg("开始推送数据2,推送请求postData:" + postData);

			// 4.推送
			BHPushResopnse pushResponse = TaskUtils
					.sendResultToPartner(postData);
			logger.logMsg("推送数据结束3,推送结果:" + JSON.toJSONString(pushResponse));

			// 考虑新增重试机制,例重试5次之后，即不再重试
			if ("0".equals(pushResponse.getErrCode())) {

				// 5.更新
				tbImageCaseInfo.setApipushsign("1");
				tbImageCaseInfo
						.setApipushdata(MyDateUtils.getDateTimeNow(null));
				tbImageCaseInfo.setInfo1("");
				imageCaseInfoService.update(tbImageCaseInfo);
				logger.logMsg("开始推送数据4,推送结束:");
			}
		}
	}
}
