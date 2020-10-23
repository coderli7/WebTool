package com.lxl.webtool.dao.api;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("apiTokenService")
public class APITokenService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public Boolean checkToken(String tokenId) {

		Boolean tokenStatus = true;
		/*
		 * 1.根据tokenId,即登录返回的ID 如果为空,或者redis中不存在 则需要直接返回fase
		 * 2.如果存在，且未超期，则延长对应数据即可
		 *
		 */
		if (StringUtils.isBlank(tokenId)) {
			tokenStatus = false;
			return tokenStatus;
		}
		String tokenVal = (String) redisTemplate.boundValueOps(tokenId).get();
		if (StringUtils.isBlank(tokenVal)) {
			tokenStatus = false;
			return tokenStatus;
		} else {
			// 更新过期时间
			insertRedis(tokenId, tokenVal);
		}
		return tokenStatus;
	}

	public void insertRedis(String key, String val) {
		redisTemplate.boundValueOps(key).set(val, 60, TimeUnit.MINUTES);
	}
}
