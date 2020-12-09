package com.lxl.webtool.commonutils;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片处理工具
 * 
 * @author Administrator
 *
 */
public class ImageUtils {

	@Test
	/**
	 * 将base64转化为本机图片存储
	 * 
	 * @param base64Str
	 *            base64字符串
	 * @param outFilePath
	 *            输出文件存储目录
	 * @throws IOException
	 */
	public static void saveImageFromBase64(String base64Str, String outFilePath)
			throws IOException {
		// 使用正则替换
		String replaceRegex = "data:image/[\\S|\\s]*?base64,";
		String imgVal = base64Str.replaceFirst(replaceRegex, "");
		FileOutputStream out = new FileOutputStream(outFilePath);
		// Base64解码
		byte[] b = Base64.decodeBase64(imgVal.toString());
		out.write(b);
		out.close();
	}
}
