package com.yzeng.userserver.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import org.springframework.util.StringUtils;

/**
 * 加密解密
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月17日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
public class DesUtils {
	private final static String DES = "DES";
	private final static String CHARSET = "UTF-8";

    private final static String KEY = "88a9hc1a7cd84b898fe804cc17d7bn44";
    private final static String APP_KEY = "824813f4f20354611bc64fc8b449f706";

    /**
     * 描述：url编码
     * @param data
     * @return
     * @throws Exception
     */
    public static String urlEncrypt(String data) throws Exception
    {
        return urlEncrypt(data, KEY);
    }

    /**
     * 描述：url编码
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String urlEncrypt(String data, String key) throws Exception
    {
        return URLEncoder.encode(encrypt(data, key), CHARSET);
    }

    /**
     * 描述：根据键值进行加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception
    {
        return encrypt(data, KEY);
    }

    /**
     * 描述：解密
     * @param data
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data) throws IOException, Exception
    {
        return decrypt(data, KEY);
    }

    
    /** 
     * 正常加密
     * @param data
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String encryptApp(String data) throws Exception
    {
        return encryptDes(data, APP_KEY);
    }
    
    /** 
     * 正常解密
     * @param data
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String decryptApp(String data) throws Exception
    {
        return decryptDes(data, APP_KEY);
    }
    
    /**
     * Description 根据键值进行加密
     * @param data
     * @param key 加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception
    {
        if (StringUtils.isEmpty(data))
            return null;
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = org.apache.commons.codec.binary.Base64.encodeBase64String(bt);
        return strs;
    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key 加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException, Exception
    {
        if (StringUtils.isEmpty(data))
            return null;
        byte[] buf = org.apache.commons.codec.binary.Base64.decodeBase64(data);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key 加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception
    {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
    
    /** 
     * 3des 正常加密方式 
     * @param src
     * @param key
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String encryptDes(String src,String key) throws Exception  
    {  
        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(CHARSET));  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");  
        SecretKey securekey = keyFactory.generateSecret(dks);  
          
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");  
        cipher.init(Cipher.ENCRYPT_MODE, securekey);  
        byte[] b=cipher.doFinal(src.getBytes());  
          
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(b).replaceAll("\r", "").replaceAll("\n", "");  
          
    } 
    
    /** 
     * 3des 正常解密
     * 3DESECB解密,key必须是长度大于等于 3*8 = 24 位  
     * @param src
     * @param key
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String decryptDes(String src,String key) throws Exception  
    {  
        //--通过base64,将字符串转成byte数组  
    	Decoder decoder = Base64.getDecoder();
        byte[] bytesrc = decoder.decode(src);  
        //--解密的key  
        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(CHARSET));  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");  
        SecretKey securekey = keyFactory.generateSecret(dks);  
          
        //--Chipher对象解密  
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");  
        cipher.init(Cipher.DECRYPT_MODE, securekey);  
        byte[] retByte = cipher.doFinal(bytesrc);  
          
        return new String(retByte);  
    }  

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key 加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception
    {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
    
    /**
     * 
     * @Title: encryptMapObject 
     * @Description: 单个map加密
     * @param map
     * @return
     * @throws Exception Map<String,Object>  
     *
     */
    public static Map<String, Object> encryptMapObject(Map<String,Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        // 解析map
        Set<String> set = map.keySet();
        
        for (String key : set)
        {
            resultMap.put(transObjToStr(key),
                DesUtils.encryptApp(transObjToStr(map.get(key))));
        }
        return resultMap;
    }
    
    /**
     * 
     * @Title: decryptMapObject 
     * @Description: 单个map解密
     * @param map
     * @return
     * @throws Exception Map<String,Object>  
     *
     */
    public static Map<String, Object> decryptMapObject(Map<String, Object> map)
        throws Exception
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        Set<String> set = map.keySet();
        
        for (String key : set)
        {
            resultMap.put(transObjToStr(key),
                DesUtils.decryptApp(transObjToStr(map.get(key))));
        }
        return resultMap;
    }
    
    /**
     * 
     * @Title: encryptMapObjectList 
     * @Description: 加密listMap
     * @param list
     * @return
     * @throws Exception List<Map<String,Object>>  
     *
     */
    public static List<Map<String, Object>> encryptMapObjectList(List<Map<String,Object>> list) throws Exception
    {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        for (Map<String, Object> map : list)
        {
            resultMap = new HashMap<String, Object>();
            
            // 解析map
            Set<String> set = map.keySet();
            
            for (String key : set)
            {
                resultMap.put(transObjToStr(key),
                    DesUtils.encryptApp(transObjToStr(map.get(key))));
            }
            
            resultList.add(resultMap);
        }
        return resultList;
    }
    
    /**
     * 
     * @Title: decryptMapObjectList 
     * @Description: 解密listmap
     * @param list
     * @return
     * @throws Exception List<Map<String,Object>>  
     *
     */
    public static List<Map<String, Object>> decryptMapObjectList(List<Map<String,Object>> list) throws Exception
    {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        for (Map<String, Object> map : list)
        {
            resultMap = new HashMap<String, Object>();
            
            // 解析map
            Set<String> set = map.keySet();
            
            for (String key : set)
            {
                resultMap.put(transObjToStr(key),
                    DesUtils.decryptApp(transObjToStr(map.get(key))));
            }
            
            resultList.add(resultMap);
        }
        return resultList;
    }

    
    
   /**
    * 
    * (BASE64加密工具)<BR>
    * 方法名：encryptBASE64<BR>
    * @param str
    * @return String<BR>
    * @exception <BR>
    */
	public static String encryptBASE64(String str) {
		byte[] key = str.getBytes();
		Encoder encoder = Base64.getEncoder();
		String strs = encoder.encodeToString(key);
		return strs;
	}
	
	/**
	 * 将对象转换为字符串 
	 * @title transObjToStr
	 * @param obj
	 * @return String
	 */
	public static String transObjToStr(Object obj)
    {
        if (obj == null)
            return "";
        return String.valueOf(obj).trim();
    }
	
	public static void main(String[] args) {
		try {
			String string = DesUtils.encrypt("123");
			System.out.println(string);
			System.out.println(DesUtils.decrypt(string));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
