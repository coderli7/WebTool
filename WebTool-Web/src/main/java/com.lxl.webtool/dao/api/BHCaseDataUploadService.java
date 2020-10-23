package com.lxl.webtool.dao.api;

import com.lxl.webtool.commonutils.ImageUtils;
import com.lxl.webtool.commonutils.MyCommonUtils;
import com.lxl.webtool.dao.mapper.TbImageMapper;
import com.lxl.webtool.dao.pojo.TbImage;
import com.lxl.webtool.dao.pojo.TbImageExample;
import com.lxl.webtool.dao.pojo.TbImageExample.Criteria;
import com.lxl.webtool.model.api.request.BHCaseDataUploadApiRequest;
import com.lxl.webtool.model.api.request.BaseApiRequest;
import com.lxl.webtool.model.api.response.BaseApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("bhCaseDataUploadService")
public class BHCaseDataUploadService implements IBHBaseService {

	@Autowired
	private TbImageMapper imageMapper;

	@Override
	public BaseApiResponse excute(BaseApiRequest apiRequest) {
		BaseApiResponse apiResponse = new BaseApiResponse();
		try {
			/*
			 * 1.先校验案件上传合理性，如是否存在，是否已上传等 2.获取票据model，将base64图片解压缩，后存储，再更新model即可
			 * 3.(后续存储考虑转移存储fastdfs)
			 */
			BHCaseDataUploadApiRequest bhCaseDataUploadApiRequest = (BHCaseDataUploadApiRequest) apiRequest;

			String newImgName = getNewFileName(bhCaseDataUploadApiRequest);
			// bhCaseDataUploadApiRequest.setFileName(newImgName);

			// 兼容客户不传递后缀名，需要生成从base64中生成新的后缀名
			String newImgPath = String.format("%s\\%s",
					bhCaseDataUploadApiRequest.getFilePath(), newImgName);
			bhCaseDataUploadApiRequest.setFilePath(newImgPath);

			String curCaseId = bhCaseDataUploadApiRequest.getCaseId();
			String fileName = bhCaseDataUploadApiRequest.getFileName();

			TbImageExample imageExample = new TbImageExample();
			Criteria criteria = imageExample.createCriteria();
			if (!StringUtils.isBlank(curCaseId)) {
				criteria.andCaseidEqualTo(curCaseId);
			}
			if (!StringUtils.isBlank(fileName)) {
				criteria.andImgnameEqualTo(fileName);
			}
			List<TbImage> images = imageMapper.selectByExample(imageExample);
			if (images != null && images.size() > 0) {
				TbImage findImage = images.get(0);
				String saveImgPath = bhCaseDataUploadApiRequest.getFilePath();

				findImage.setImgname(bhCaseDataUploadApiRequest.getFileName());

				// 存储文件
				ImageUtils.saveImageFromBase64(
						bhCaseDataUploadApiRequest.getData(), saveImgPath);
				findImage.setImgpath(saveImgPath);
				// 生成缩略图
				MyCommonUtils.genThumbnail(saveImgPath);

				// 更新相关数据
				findImage.setImgname(newImgName);
				imageMapper.updateByPrimaryKey(findImage);
				apiResponse.setCode("0");
				apiResponse.setMessage("数据新增成功!");
			} else {
				apiResponse.setCode("-10000");
				apiResponse.setMessage("案件不存在");
			}
		} catch (Exception e) {
			apiResponse.setCode("-10000");
			apiResponse.setMessage("数据新增异常!");
		}
		return apiResponse;
	}

	/**
	 * 生成新的imgName 兼容客户不传递后缀名，需要生成从base64中生成新的后缀名
	 * 
	 * @param request
	 * @return
	 */
	String getNewFileName(BHCaseDataUploadApiRequest request) {
		String curImgName = request.getFileName();

		// 适配安诚接口，图片名，必须为jpg格式
		int subffixIdx = curImgName.lastIndexOf(".");
		if (subffixIdx > 0) {
			curImgName.substring(0, subffixIdx);
		}
		curImgName = String.format("%s.jpg", curImgName);
		return curImgName;

		// if (curImgName.lastIndexOf(".") < 0) {
		// curImgName = String.format("%s.jpg", curImgName);
		// 不包含后缀名
		// Pattern pattern =
		// Pattern.compile("data:image/[\\S|\\s]*?base64");
		// Matcher matcher = pattern.matcher(request.getData());
		// if (matcher.find()) {
		// String paternMatchVal = matcher.group().trim();
		// paternMatchVal = paternMatchVal.replace("data:image/", "")
		// .replace(";base64", "");
		// curImgName = String.format("%s.%s", curImgName, paternMatchVal);
		// }
		// }
	}
}
