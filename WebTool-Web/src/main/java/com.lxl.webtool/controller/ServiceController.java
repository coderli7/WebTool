package com.lxl.webtool.controller;

import com.alibaba.fastjson.JSON;
import com.lxl.webtool.dao.ImageCaseInfoService;
import com.lxl.webtool.dao.ImageService;
import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.model.BaseAcResponse;
import com.lxl.webtool.model.DataReceiveACRequest;
import com.lxl.webtool.model.ImageACResponse;
import com.lxl.webtool.model.WebApiRequest.BHClientGetAIODataRequest;
import com.lxl.webtool.model.WebApiRequest.BHClientUploadImgRequest;
import com.lxl.webtool.model.WebApiRequest.BaseRequest;
import com.lxl.webtool.model.WebApiRequest.ImageRequest;
import com.lxl.webtool.model.WebApiResponse.BaseResponse;
import com.lxl.webtool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

@RestController
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageCaseInfoService imageCaseInfoService;

	/**
	 * 0.壁虎客户端获取服务器信息
	 * 
	 * @return
	 */
	@RequestMapping("/BHClientGetServerStatus")
	public BaseResponse BHClientGetServerStatus() {
		BaseResponse response = new BaseResponse();
		String date = Calendar.getInstance().getTime().toString();
		response.ErrMsg = date;
		return response;
	}

	/**
	 * 1.********此处不用实现*********壁虎客户端上传图片
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse BHClientUploadImg(BHClientUploadImgRequest request) {

		return null;
	}

	/**
	 * 2.********此处不用实现*********壁虎客户端，发送上传图片消息给安诚
	 * 
	 * @param postData
	 * @return
	 */
	public BaseResponse BHClientUploadFileToAIO() {
		return null;
	}

	/**
	 * 3.安诚影像文件下载接口（壁虎获取也可使用）
	 * 
	 * @param caseId
	 * @param fileName
	 * @param timeStamp
	 * @param signature
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/AIOGetImg")
	public ImageACResponse AIOGetImg(String caseId, String fileName,
									 long timeStamp, String signature)
			throws UnsupportedEncodingException {
		MyLoggerFactory.log(LoggerEnum.ServiceController, String.format("安诚获取图片:%s", fileName));
		BaseService service = new AIOGetImgService();
		ImageRequest imageRequest = new ImageRequest();
		imageRequest.CaseId = caseId;
		// 解决get请求中文乱码
		String orgFileName = new String(fileName.getBytes("utf-8"), "utf-8");
		imageRequest.fileName = orgFileName;
		service.request = imageRequest;
		service.imageService = imageService;
		service.Excute();
		return (ImageACResponse) service.response.Info;
	}

	/**
	 * 4.安诚赔案结果数据接收
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/AIODataReceive")
	public BaseAcResponse AIODataReceive(
			@RequestBody DataReceiveACRequest request) {
		MyLoggerFactory.log(LoggerEnum.ServiceController, String.format("安诚回传结果:%s", JSON.toJSONString(request)));		
		BaseService service = new AIODataReceiveService();
		BaseRequest baseRequest = new BaseRequest();
		baseRequest.Info = request;
		service.request = baseRequest;
		service.imageService = imageService;
		service.imageCaseInfoService = imageCaseInfoService;
		service.Excute();
		return (BaseAcResponse) service.response.Info;
	}

	/**
	 * 5.壁虎客户端，根据报案号获取解析结果
	 * 
	 * @return
	 */
	public BaseResponse BHClientGetAIOData(
			@RequestBody BHClientGetAIODataRequest request) {
		return null;
	}

	/**
	 * 6.壁虎客户端，根据报案号，获取解析状态Server端，再去抓取状态返回
	 * 
	 * @return
	 */
	public BaseResponse BHClientGetAIOStatus(String caseId) {
		BaseService service = new BHClientGetAIOStatusService();
		return service.response;
	}

	/**
	 * 7.账户余额查询
	 * 
	 * @return
	 */
	@RequestMapping("/BHClientAIOGetAccountBalance")
	public BaseResponse BHClientAIOGetAccountBalance() {
		BaseService service = new BHClientAIOGetAccountBalanceService();
		service.Excute();
		return service.response;
	}

	/**
	 * 8.账户明细查询接口
	 * 
	 * @return
	 */
	@RequestMapping("/BHClientAIOGetAccountBalanceDetail")
	public BaseResponse BHClientAIOGetAccountBalanceDetail() {
		BaseService service = new BHClientAIOGetAccountBalanceDetailService();
		service.Excute();
		return service.response;
	}

	/**
	 * 9.加班通知接口
	 * 
	 * @return
	 */
	@RequestMapping("/BHClientAIOOvertimeNotice")
	public BaseResponse BHClientAIOOvertimeNotice() {
		BaseService service = new BHClientAIOOvertimeNoticeService();
		service.Excute();
		return service.response;
	}
}