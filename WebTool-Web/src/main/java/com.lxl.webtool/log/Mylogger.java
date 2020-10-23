package com.lxl.webtool.log;

import java.util.Enumeration;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import com.lxl.webtool.commonutils.MyDateUtils;

public class Mylogger {

	public Mylogger(Logger loggerParam) {
		this.logger = loggerParam;
	}

	private Logger logger;

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * 记录log
	 * 
	 * @param message
	 */
	public void logMsg(String message) {

		if (logger != null && StringUtils.isNotBlank(message)) {
			// 记录
			Enumeration<?> allAppenders = logger.getAllAppenders();
			RollingFileAppender appender = (RollingFileAppender) allAppenders
					.nextElement();
			// 实现替换文本文件
			if (appender != null) {
				String orgFilePath = appender.getFile();
				// 正则替换路径
				String newFilePath = orgFilePath.replaceFirst(
						"[\\d]{4}-[\\d]{2}-[\\d]{2}",
						MyDateUtils.getDateTimeNow("yyyy-MM-dd"));
				appender.setFile(newFilePath);
			}
			logger.info(message);
		}

	}

}
