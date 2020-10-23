package com.lxl.webtool.commonutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.log.SysKeyEnum;
import com.lxl.webtool.model.TokenACResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


@Component("tokenService")
public class TokenService {

	@Autowired
	private RedisTemplate<String, String> redisTemplateProxy;

	private static RedisTemplate<String, String> redisTemplate;

	@PostConstruct
	public void init() {
		TokenService.redisTemplate = this.redisTemplateProxy;
	}

	// private static String tokenKey;
	public static String companyId;
	public static String key;
	public static String url;
	public static String ImgCallback;
	public static String DatCallback;
	public static String UnSendToAIO;
	public static String ApiUrl;
	public static String ApiTokenCheck;

	static {
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
			companyId = resourceBundle.getString("companyId");
			key = resourceBundle.getString("key");
			url = resourceBundle.getString("url");
			ImgCallback = resourceBundle.getString("ImgCallback");
			DatCallback = resourceBundle.getString("DatCallback");
			UnSendToAIO = resourceBundle.getString("UnSendToAIO");
			ApiUrl = resourceBundle.getString("ApiUrl");
			ApiTokenCheck = resourceBundle.getString("ApiTokenCheck");

			// 初始化维持Token
			// String acToken = TokenService.refreshAcToken();

		} catch (Exception e) {
			MyLoggerFactory.log(LoggerEnum.TokenService,
					String.format("初始化Token异常:%s", e.getMessage()));
			e.printStackTrace();
		}
	}

	public synchronized static String refreshAcToken() {
		String tokenKey = null;
		try {
			long timeStamp = System.currentTimeMillis();
			String requestDataPre = String.format(
					"companyid=%s&timestamp=%s&key=%s", companyId, timeStamp,
					key);
			String urlParams = String.format(
					"companyid=%s&timestamp=%s&signature=%s", companyId,
					timeStamp, MyCommonUtils.getMD5Str(requestDataPre));
			String getTokenUrl = String.format("%s/AIOLogin?%s", url,
					urlParams);
			String tokenResult = MyHttpUtils.Get(getTokenUrl);
			if (!StringUtils.isEmpty(tokenResult)) {
				JSONObject jsonObject = JSON.parseObject(tokenResult);
				TokenACResponse acResponse = JSON.toJavaObject(jsonObject,
						TokenACResponse.class);
				if (acResponse != null && acResponse.getCode() == 0) {
					tokenKey = acResponse.getData();
					if (StringUtils.isNotBlank(tokenKey)) {
						String redisTokenKey = SysKeyEnum.AcToken.toString();
						redisTemplate.boundValueOps(redisTokenKey).set(tokenKey,
								20, TimeUnit.MINUTES);
					}
				}
			}
		} catch (Exception e) {
			String errorMsg = e.getMessage();
			MyLoggerFactory.log(LoggerEnum.TokenService, errorMsg);
		}
		return tokenKey;
	}

	public static String getAcToken() {
		String acToken = null;
		try {
			String redisAcTokenVal = redisTemplate
					.boundValueOps(SysKeyEnum.AcToken.toString()).get();
			if (!StringUtils.isNotBlank(redisAcTokenVal)) {
				acToken = refreshAcToken();
			} else {
				acToken = redisAcTokenVal;
			}
		} catch (Exception e) {
		}
		return acToken;
	}

}
