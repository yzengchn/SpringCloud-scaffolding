package com.yzeng.userserver.utils;

import org.springframework.util.DigestUtils;

public class Md5Utils {
	/**
	 * 获取当前时间戳MD5加密字符
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月14日 上午11:46:38
	 * @title getCurrentMd5Str
	 * @return String
	 */
	public static String getCurrentDateMd5Str() {
		String millis = String.valueOf(System.currentTimeMillis());
		String salt = DigestUtils.md5DigestAsHex(millis.getBytes()); 
		return salt;
	}
	
	/**
	 * 获得MD5加盐后的字符串 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月14日 上午11:40:03
	 * @title getMd5AddSalt
	 * @param salt
	 * @param password
	 * @return String
	 */
	public static String getMd5AddSalt(String salt, String password) {
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
		String md5AddSaltStr = md5Password + salt;
		return DigestUtils.md5DigestAsHex(md5AddSaltStr.getBytes());
	}
}
