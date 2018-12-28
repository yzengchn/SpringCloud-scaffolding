package com.yzeng.eurekaserver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
	/**
	 * ThreadLocal 可以确保每个线程都可以得到单独的一个 SimpleDateFormat 的对象
	 */
	private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
     };
	
	public static void main(String[] args) throws InterruptedException {
		/*AtomicLong atomicLong = new AtomicLong();
		while(true) {
			Thread.sleep(2000);
			System.out.println(atomicLong.incrementAndGet());
		}*/
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encode = encoder.encode("admin");
		System.out.println(encode);
		
		/*String format = simpleDateFormatThreadLocal.get().format(new Date());
		
		List<Map<String, String>> mapList = createMocksData();
		mapList
			.stream()
			.map(map -> {
				if(map.get("ID_NO").equals("413001196701162614")) {
					map.put("test", "test");
				}
				return map;
			}).collect(Collectors.toList());
		
		mapList
			.stream()
			.map(map -> map.get("ID_NO")).collect(Collectors.toList());
		
		
		mapList
			.stream()
			.filter(map -> 
				map.get("ID_NO").equals("413001196701162614")
			).collect(Collectors.toList());
		System.out.println(mapList);*/
	}
	
	
	
	static List<Map<String, String>> createMocksData (){
        List<Map<String, String>> list = new ArrayList<>();
        
        Map<String, String> mocks1 = new HashMap<String,String>();
        mocks1.put("ID_NO", "413001196701162614");
        mocks1.put("DUEDATE", "2018-11-29");
        mocks1.put("DEBIT_AMOUNT", "5317.00");
        
        Map<String, String> mocks2 = new HashMap<String,String>();
        mocks2.put("ID_NO", "622301199105183813");
        mocks2.put("DUEDATE", "2018-11-29");
        mocks2.put("DEBIT_AMOUNT", "4714.00");
        
        Map<String, String> mocks3 = new HashMap<String,String>();
        mocks3.put("ID_NO", "512924198804274814");
        mocks3.put("DUEDATE", "2018-11-29");
        mocks3.put("DEBIT_AMOUNT", "47134.00");
        
        Map<String, String> mocks4 = new HashMap<String,String>();
        mocks4.put("ID_NO", "42900619850816063X");
        mocks4.put("DUEDATE", "2018-11-29");
        mocks4.put("DEBIT_AMOUNT", "4714.00");
        
        list.add(mocks1);
        list.add(mocks2);
        list.add(mocks3);
        list.add(mocks4);
        
        return list;
    }
}
