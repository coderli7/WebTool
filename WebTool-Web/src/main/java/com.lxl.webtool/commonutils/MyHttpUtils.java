package com.lxl.webtool.commonutils;

import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MyHttpUtils {

	public static String Get(String url) throws Exception {
		String getResult = "";
		HttpClient client = HttpClients.createDefault();
		if (url.startsWith("https:")) {
			client = new SSLClient();
		}
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			getResult = EntityUtils.toString(entity, "UTF-8");
			return getResult;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			MyLoggerFactory.log(LoggerEnum.Error,
					String.format("执行函数Get发生异常:%s", e.getMessage()));
		}
		return getResult;
	}

	public static String Get_Https(String url) throws Exception {
		String getResult = "";
		// HttpClient client = HttpClients.createDefault();
		@SuppressWarnings("resource")
		HttpClient client = new SSLClient();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			getResult = EntityUtils.toString(entity, "UTF-8");
			return getResult;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			MyLoggerFactory.log(LoggerEnum.Error,
					String.format("执行函数Get发生异常:%s", e.getMessage()));
		}
		return getResult;
	}

	public static String Post(String url, String postData) {
		String postResult = "";
		try {
			HttpClient client = HttpClients.createDefault();
			if (url.startsWith("https:")) {
				client = new SSLClient();
			}
			HttpPost post = new HttpPost(url);
			try {
				post.addHeader("Content-Type", "application/json");
				StringEntity stringEntity = new StringEntity(postData, "UTF-8");
				post.setEntity(stringEntity);
				HttpResponse response = client.execute(post);
				HttpEntity entity = response.getEntity();
				postResult = EntityUtils.toString(entity, "UTF-8");
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			MyLoggerFactory.log(LoggerEnum.Error,
					String.format("执行函数Post发生异常:%s", e.getMessage()));
		}
		return postResult;
	}

	@SuppressWarnings("resource")
	public static String Post_Https(String url, String postData) {
		String postResult = "";
		try {
			HttpClient client = new SSLClient();
			HttpPost post = new HttpPost(url);
			try {
				// post.addHeader("Content-Type", "application/json");
				post.addHeader("Content-Type", "text/css");
				// text/css


				if (StringUtils.isNotBlank(postData)) {
					StringEntity stringEntity = new StringEntity(postData,
							"UTF-8");
					post.setEntity(stringEntity);
				}
				HttpResponse response = client.execute(post);
				HttpEntity entity = response.getEntity();
				postResult = EntityUtils.toString(entity, "UTF-8");
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			MyLoggerFactory.log(LoggerEnum.Error,
					String.format("执行函数Post发生异常:%s", e.getMessage()));
		}
		return postResult;
	}

	public static String readInputStream(InputStream inputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toString();
	}

}
