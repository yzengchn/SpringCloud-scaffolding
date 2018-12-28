package com.yzeng.comment.utils;

import java.util.Random;
import java.util.UUID;

import org.springframework.util.DigestUtils;


/**
 * ID和Key的生成工具类 
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月17日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public class IdKeyUtils {
	
	/**
	 * 生成独一无二的Key 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月17日 下午1:53:23
	 * @title genUniqueKey
	 * @return
	 * @return String
	 */
	public static synchronized String genUniqueKey() {
		Random random = new Random();
		int number = random.nextInt(900000) + 100000;
		long millis = System.currentTimeMillis();
		String keyStr = millis + String.valueOf(number);
		return DigestUtils.md5DigestAsHex(keyStr.getBytes());
	}
	
	/**
	 * 生成独一无二的UUID(32个字符) 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月17日 下午1:53:23
	 * @title genUniqueKey
	 * @return
	 * @return String
	 */
	public static synchronized String genUniqueUUId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
