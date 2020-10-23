package com.lxl.webtool.controller;

import com.lxl.webtool.commonutils.MyCommonUtils;
import com.lxl.webtool.commonutils.MyDateUtils;
import com.lxl.webtool.dao.ImageCaseInfoService;
import com.lxl.webtool.dao.ImageService;
import com.lxl.webtool.dao.pojo.TbImage;
import com.lxl.webtool.dao.pojo.TbImageCaseInfo;
import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.model.WebApiRequest.BaseRequest;
import com.lxl.webtool.pojo.Result;
import com.lxl.webtool.service.BHClientUploadFileToAIOService;
import com.lxl.webtool.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@SuppressWarnings("unused")
@RestController
public class UploadController {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageCaseInfoService imageCaseInfoService;

	/**
	 * 上传资料
	 * 
	 * @throws IOException
	 */
	@RequestMapping("upload")
	public Result upload(MultipartFile file, String caseId, int count)
			throws IOException {

		/**
		 * 0.检查图片是否存在，如果存在，直接返回 1.先获取到中文名,生成Guid,目前直接传入 2.将图片保存到对应路径
		 * 3.将案件号和图片，一一对应,插入至数据库 4.发送消息至安诚迈科
		 */
		String originalFilename = file.getOriginalFilename();

		// 统一修改后缀名为jpg格式
		int subffixIdx = originalFilename.lastIndexOf(".");
		if (subffixIdx > 0) {
			originalFilename = originalFilename.substring(0, subffixIdx);
			originalFilename = String.format("%s.jpg", originalFilename);
		}

		if (isImgExist(caseId, originalFilename)) {
			return new Result(false, String.format(">>>>>>%s%s",
					originalFilename, "已存在,上传失败!!!"));
		}
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String filePath = httpServletRequest.getSession().getServletContext()
				.getRealPath("/image");
		String caseImagePath = String.format("%s\\%s", filePath, caseId);
		File curImgFile = new File(caseImagePath);
		if (!curImgFile.exists()) {
			curImgFile.mkdirs();
		}
		String imgPath = String.format("%s\\%s", caseImagePath,
				originalFilename);
		try {
			addImageCaseInfo(caseId);
			saveImgToLocal(file, caseImagePath, originalFilename);
			addImageInfo(caseId, originalFilename, imgPath);
			sendToAio(caseId, count);
			return new Result(true,
					String.format(">>>>>>%s%s", originalFilename, "上传成功!"));
		} catch (Exception e) {
			MyLoggerFactory.log(LoggerEnum.UploadController,
					String.format("执行函数upload发生异常:%s", e.getMessage()));
			return new Result(false,
					String.format(">>>>>>%s%s", originalFilename, "上传失败!!!"));
		}
	}

	/**
	 * 判断是否存在异常
	 * 
	 * @param imgName
	 * @return
	 */
	private Boolean isImgExist(String caseId, String imgName) {
		Boolean exist = false;
		try {

			TbImage image = new TbImage();
			image.setCaseid(caseId);
			image.setImgname(imgName);
			List<TbImage> findByModel = imageService.findByModel(image);
			if (findByModel != null && findByModel.size() > 0) {
				exist = true;
			}
		} catch (Exception e) {
			MyLoggerFactory.log(LoggerEnum.UploadController,
					String.format("执行函数isImgExist出现异常：%s", e.getMessage()));
		}
		return exist;
	}

	/**
	 * 1.添加案件表头信息(注意数据库入库,需要同步)
	 * 
	 * @param caseId
	 */
	private synchronized void addImageCaseInfo(String caseId) {
		List<TbImageCaseInfo> caseInfos = imageCaseInfoService
				.findByCaseId(caseId);
		if (caseInfos != null && caseInfos.size() == 0) {
			TbImageCaseInfo imageCaseInfo = new TbImageCaseInfo();
			imageCaseInfo.setUsercode(MyCommonUtils.getCurUser());
			imageCaseInfo.setCaseid(caseId);
			imageCaseInfo.setCasestatus("未完成");
			imageCaseInfo.setCasedate(MyDateUtils.getDateTimeNow(null));
			imageCaseInfoService.add(imageCaseInfo);
		}
	}

	/**
	 * 2.保存图片到本地
	 * 
	 * @param file
	 * @param caseImagePath
	 * @param originalFilename
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void saveImgToLocal(MultipartFile file, String caseImagePath,
			String originalFilename) throws IOException, FileNotFoundException {
		try {
			// 1.保存本地
			byte[] byteImg = file.getBytes();
			String imgPath = String.format("%s\\%s", caseImagePath,
					originalFilename);
			FileImageOutputStream imageOutput = new FileImageOutputStream(
					new File(imgPath));
			imageOutput.write(byteImg, 0, byteImg.length);
			imageOutput.close();

			// 2.保存本地缩略图
			MyCommonUtils.genThumbnail(imgPath);

		} catch (Exception e) {
			MyLoggerFactory.log(LoggerEnum.UploadController,
					String.format("执行函数saveImgToLocal发生异常:%s", e.getMessage()));
		}
	}

	/**
	 * 3.添加行明细信息(注意数据库入库,需要同步)
	 * 
	 * @param caseId
	 * @param originalFilename
	 * @param imgPath
	 */
	private void addImageInfo(String caseId, String originalFilename,
			String imgPath) {
		TbImage image = new TbImage();
		image.setCaseid(caseId);
		image.setImgname(originalFilename);
		image.setDelstatus("0");
		image.setImgpath(imgPath);
		image.setImagedate(MyDateUtils.getDateTimeNow(null));
		imageService.add(image);
	}

	/**
	 * 4.将图片信息发送至安诚，触发识别
	 * 
	 * @param caseId
	 * @param count
	 */
	private synchronized void sendToAio(String caseId, int count) {
		// 1.当本次所有图片都完成上传，再从数据库中读取，对应图片即可
		List<TbImageCaseInfo> imageCaseInfos = imageCaseInfoService
				.findByCaseId(caseId);
		if (imageCaseInfos != null && imageCaseInfos.size() >= 0) {
			TbImageCaseInfo imageCaseInfo = imageCaseInfos.get(0);
			List<TbImage> curCaseImages = imageService.findByCaseId(caseId);
			int size = curCaseImages.size();
			if (size > 0 && size == count
					&& !"OK".equals(imageCaseInfo.getInfo1())) {
				// 注意，此处为了标识，当前案件，是否已上传
				imageCaseInfo.setInfo1("OK");
				imageCaseInfoService.update(imageCaseInfo);
				BaseService service = new BHClientUploadFileToAIOService();
				service.request = new BaseRequest();
				// 将查询到的图片信息，记录，作为发送至安诚请求使用
				service.request.Info = curCaseImages;
				service.Excute();
			}
		}
	}

}
