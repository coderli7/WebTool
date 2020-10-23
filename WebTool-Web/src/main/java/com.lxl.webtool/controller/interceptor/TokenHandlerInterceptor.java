package com.lxl.webtool.controller.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.lxl.webtool.commonutils.TokenService;
import com.lxl.webtool.dao.api.APITokenService;
import com.lxl.webtool.model.api.request.BaseApiRequest;
import com.lxl.webtool.model.api.response.BaseApiResponse;

@Component
public class TokenHandlerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private APITokenService apiTokenService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if ("1".equals(TokenService.ApiTokenCheck)) {
			RequestWrapper myRequestWrapper = new RequestWrapper(
					(HttpServletRequest) request);
			String body = myRequestWrapper.getBody();
			System.out.println(body);
			// 获取到请求中的token字段
			String tokenId = getToken(body);
			System.out.println(tokenId);
			Boolean tokenStatus = apiTokenService.checkToken(tokenId);
			if (!tokenStatus) {
				// 如果token失效,直接返回提示重新登录获取token
				// 重置response
				response.reset();
				// 设置编码格式
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				String loginTips = "";
				BaseApiResponse apiResponse = new BaseApiResponse();
				apiResponse.setCode("-20000");
				apiResponse.setMessage("token无效,请重新获取token");
				loginTips = JSON.toJSONString(apiResponse);
				writer.write(loginTips);
				writer.flush();
				writer.close();
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

	private String getToken(String jsonBody) {
		String tokenId = null;
		BaseApiRequest baseApiRequest = JSON
				.toJavaObject(JSON.parseObject(jsonBody), BaseApiRequest.class);
		tokenId = baseApiRequest.getCompanyId();
		return tokenId;
	}
}
