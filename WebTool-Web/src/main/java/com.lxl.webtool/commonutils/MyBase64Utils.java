package com.lxl.webtool.commonutils;

import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



public class MyBase64Utils {

	/**
	 * 本地图片转换成base64字符串
	 * 
	 * @param imgFile
	 *            图片本地路径
	 * @return
	 *
	 * @author ZHANGJL
	 * @dateTime 2018-02-23 14:40:46
	 */
	public static String ImageToBase64ByLocal(String imgFile) {
		MyLoggerFactory.log(LoggerEnum.ServiceController,
				String.format("获取图片%s", imgFile));

		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			// e.printStackTrace();
			MyLoggerFactory.log(LoggerEnum.ServiceController,
					String.format("获取图片%s异常%s", imgFile, e.getStackTrace()));
		}
		Base64 base64 = new Base64();
		
		// 对字节数组Base64编码
		String val = base64.encodeAsString(data);// 返回Base64编码过的字节数组字符串
		MyLoggerFactory.log(LoggerEnum.ServiceController,
				String.format("获取图片结果%s", val));
		return val;
	}

}
