package com.lxl.webtool.log;

import java.io.IOException;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import com.lxl.webtool.commonutils.MyDateUtils;

@SuppressWarnings("rawtypes")
public class MyLoggerFactory {

	/**
	 * 存储logger引用
	 */
	private static HashMap<String, Mylogger> loggers;

	/**
	 * 初始化默认loggers
	 */
	static {
		initModelLoggers();
	}

	/**
	 * 初始化默认loggers
	 */
	private static void initModelLoggers() {
		loggers = new HashMap<String, Mylogger>();
		LoggerEnum[] values = LoggerEnum.values();
		for (LoggerEnum loggerEnum : values) {
			String curLoggerName = loggerEnum.name();
			Mylogger curLogger = createLogger(curLoggerName);
			loggers.put(curLoggerName, curLogger);
		}
	}

	/**
	 * 初始化新logger
	 * 
	 * @param loggerName
	 * @return
	 * @throws IOException
	 */
	private static Mylogger createLogger(String loggerName) {
		Mylogger mylogger = null;
		Logger curLogger = Logger.getLogger(loggerName);
		try {
			PatternLayout patternLayout = new PatternLayout(
					"%-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n");
			String basePath = "";
			basePath = MyLoggerFactory.class.getResource("/").getPath();
			String logPath = String.format("%s\\logs\\%s\\%s\\log.log",
					basePath, loggerName,
					MyDateUtils.getDateTimeNow("yyyy-MM-dd"));
			RollingFileAppender fileAppender = new RollingFileAppender(
					patternLayout, logPath);
			curLogger.addAppender(fileAppender);
			curLogger.setLevel(Level.DEBUG);
			mylogger = new Mylogger(curLogger);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mylogger;
	}

	/**
	 * 获取Logger,如不存在则新增 一般用于先初始化Logger操作
	 * 
	 * @param loggerClass
	 */
	public static Mylogger getLogger(Class modelclass) {
		String curClsName = modelclass.getSimpleName();
		Mylogger logger = loggers.get(curClsName);
		if (logger == null) {
			// 如不存在则新创建
			logger = createLogger(curClsName);
			// 引用存入
			loggers.put(curClsName, logger);
		}
		return logger;
	}

	/**
	 * 写入日志-静态调用 如不存在logger,则将值记录至默认路径下
	 * 
	 * @param loggerEnum
	 *            模块儿枚举
	 * @param info
	 *            日志信息
	 */
	public static void log(LoggerEnum loggerEnum, String message) {
		try {

			Mylogger wrLogger = null;
			if (loggerEnum == null) {
				loggerEnum = LoggerEnum.Default;
			}
			String loggerName = loggerEnum.name();
			Mylogger curLogger = loggers.get(loggerName);
			if (curLogger != null) {
				wrLogger = curLogger;
			} else {
				wrLogger = loggers.get(loggerEnum.Default.name());
			}
			wrLogger.logMsg(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
