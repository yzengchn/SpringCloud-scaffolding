package com.yzeng.userserver.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * IP工具类
 * 
 * @author <a href="http://www.yzblog.xyz">yzblog</a>
 * @version [1.0, 2018年12月14日]
 * @Email yzengchn@163.com
 * @since [产品/模块版本]
 */
public class IPUtils {

	/**
	 * 调用淘宝开放平台查询ip归属地
	 * 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月14日 下午4:47:12
	 * @title getIpArea
	 * @param ip
	 * @throws IOException
	 * @return String
	 */
	public static String getIpArea(String ip) throws IOException {

		String path = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
		String inputline = "";
		String info = "";

		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10 * 1000);
		conn.setRequestMethod("GET");

		InputStreamReader inStream = new InputStreamReader(conn.getInputStream(), "UTF-8");
		BufferedReader buffer = new BufferedReader(inStream);

		while ((inputline = buffer.readLine()) != null) {
			info += inputline;
		}
		
		JSONObject jsonObject = JSON.parseObject(info);
		JSONObject jsonob = JSON.parseObject((jsonObject.getString("data")));
		String city = StringEscapeUtils.escapeSql(jsonob.getString("city"));
		
		if (StringUtils.isEmpty(city)) {
			return "未知归属地";
		}
		
		return city;
	}
	
	public static boolean isIpv4(String ipAddress) {  
		  
        String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."  
                +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."  
                +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."  
                +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";  
  
        Pattern pattern = Pattern.compile(ip);  
        Matcher matcher = pattern.matcher(ipAddress);  
        return matcher.matches();  
  
    }  

	/**
	 * 获取客户端IP
	 * 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月14日 上午10:21:16
	 * @title getIpAddr
	 * @param request
	 * @return
	 * @return String
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getLocalIp() {
		return LOCAL_IP;
	}

	/**
	 * 获取服务器IP地址
	 */
	private final static String LOCAL_IP;
	static {
		String sIP = "";
		InetAddress ip = null;
		try {
			boolean bFindIP = false;
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
					.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				if (bFindIP) {
					break;
				}
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = (InetAddress) ips.nextElement();
					if (!ip.isLoopbackAddress() && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
						bFindIP = true;
						break;
					}
				}
			}
		} catch (Exception e) {

		}
		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		LOCAL_IP = sIP;
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
	
}
