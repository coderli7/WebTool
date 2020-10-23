package com.lxl.webtool.schduletask;

import com.alibaba.fastjson.JSON;
import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.commonutils.TokenService;
import com.lxl.webtool.dao.ImageCaseInfoService;
import com.lxl.webtool.dao.ImageService;
import com.lxl.webtool.dao.pojo.TbImage;
import com.lxl.webtool.dao.pojo.TbImageCaseInfo;
import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.model.BaseAcResponse;
import com.lxl.webtool.model.WebApiRequest.BaseRequest;
import com.lxl.webtool.service.BHClientGetAIOStatusService;
import com.lxl.webtool.service.BHClientUploadFileToAIOService;
import com.lxl.webtool.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/*
@Component("OnlineTask")
*/
public class MyTask {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageCaseInfoService imageCaseInfoService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 1.维持登录token (每天的5点至23点之间，每隔10分钟执行一次)
	 * 
	 */
	@Scheduled(cron = "0 0/20 5-23 * * ? ")
	public void getToken() {
		try {
			// 维持Token,记录在redis中去
			MyLoggerFactory.log(LoggerEnum.TaskService,
					String.format("刷新最新Token-开始"));
			TokenService.refreshAcToken();
			MyLoggerFactory.log(LoggerEnum.TaskService, String
					.format("刷新最新Token-结束[%s]", TokenService.getAcToken()));
		} catch (Exception e) {
			MyLoggerFactory.log(LoggerEnum.TaskService,
					String.format("刷新最新Token-异常:[%s]", e.getMessage()));
		}
	}

	/**
	 * 2.发送未完成数据 (每天的5点至23点之间，每隔5分钟检测一次，是否存在异常流向数据)
	 * 
	 */
	@Scheduled(cron = "0 0/2 5-23 * * ? ")
	public void reSendFailedCase() {
		try {
			String startDate = MyDateUtils.getDateTimeNow("yyyy-MM-dd");
			String endDate = startDate;
			List<TbImageCaseInfo> caseInfos = imageCaseInfoService
					.findByDate("admin", startDate, endDate, "未完成");
			for (TbImageCaseInfo tbImageCaseInfo : caseInfos) {
				/*
				 * 1.考虑api对接，案件状态必须为未处理，且图片列表中必须已完成图片添加 2.查询当前案件对应安诚状态
				 * 3.如当前案件状态为未接受,再次发送即可
				 */
				String caseid = tbImageCaseInfo.getCaseid();
				List<TbImage> curImgList = imageService.findByCaseId(caseid);
				int emptyImg = 0;
				for (TbImage tbImage : curImgList) {
					if (StringUtils.isBlank(tbImage.getImgpath())) {
						emptyImg++;
						break;
					}
				}
				if (emptyImg > 0) {
					continue;
				}
				BaseService service = new BHClientGetAIOStatusService();
				service.request = new BaseRequest();
				service.request.CaseId = caseid;
				service.Excute();
				if (service.response.Info != null) {
					BaseAcResponse acResponse = (BaseAcResponse) service.response.Info;
					if ("赔案不存在".equals(acResponse.getMessage())) {
						BaseService reSendService = new BHClientUploadFileToAIOService();
						reSendService.request = new BaseRequest();
						List<TbImageCaseInfo> imageCaseInfos = imageCaseInfoService
								.findByCaseId(caseid);
						if (imageCaseInfos != null
								&& imageCaseInfos.size() >= 0) {
							List<TbImage> curCaseImages = imageService
									.findByCaseId(caseid);
							int size = curCaseImages.size();
							if (size > 0) {
								reSendService.request.Info = curCaseImages;
								reSendService.Excute();
							}
						}
						BaseAcResponse reSendacResponse = null;
						if (reSendService.response != null) {
							reSendacResponse = (BaseAcResponse) reSendService.response.Info;
							MyLoggerFactory.log(LoggerEnum.TaskService,
									String.format("案件%s重新发送成功:%s", caseid, JSON
											.toJSONString(reSendacResponse)));
						} else {
							MyLoggerFactory.log(LoggerEnum.TaskService,
									String.format("案件%s重新发送失败:%s", caseid, JSON
											.toJSONString(reSendacResponse)));
						}
					}
				}
			}
		} catch (Exception e) {
			MyLoggerFactory.log(LoggerEnum.TokenService,
					String.format("执行reSendFailedCase异常:%s", e.getMessage()));
		}
	}

}
