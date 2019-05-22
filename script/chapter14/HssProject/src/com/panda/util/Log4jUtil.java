
package com.panda.util;


import org.apache.log4j.Logger;

/**
 * <p>Title: Log4jUtil</p>
 * <p>Description: log4j的工具类</p>
 * <p>Company: www.com.panda</p>
 * @author	陈先森
 * @date	2017年3月14日上午10:32:26
 * @version 1.0
 */
public class Log4jUtil {
	private static Logger log = Logger.getLogger(Log4jUtil.class);

	public static void debug(String message) {
		log.debug(message);
	}

	public static void error(String message) {
		log.error(message);
	}
	public static void info(String message) {
		log.info(message);
	}
	public static void warn(String message) {
		log.warn(message);
	}
	public static void fatal(String message) {
		log.fatal(message);
	}
}
