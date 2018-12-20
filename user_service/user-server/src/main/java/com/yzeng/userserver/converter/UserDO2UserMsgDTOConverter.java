package com.yzeng.userserver.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yzeng.userserver.DO.MessageDO;
import com.yzeng.userserver.DO.UserDO;
import com.yzeng.userserver.DTO.UserMsgDTO;

public class UserDO2UserMsgDTOConverter {
	
	private static ThreadLocal<SimpleDateFormat> format = new ThreadLocal<SimpleDateFormat>() {
		@Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
	};
	
	public static UserMsgDTO userDO2UserMsgDTO(UserDO userDO) {
		//System.out.println(format.get().format(new Date()));
		UserMsgDTO dto = new UserMsgDTO();
		BeanUtils.copyProperties(userDO, dto);
		List<MessageDO> msgList = new ArrayList<>();
		msgList.add(new MessageDO(1, "内容", 1, 2, new Date(), 3, "是否离线", 0));
		dto.setMsg(msgList);
		return dto;
	}
	
	public static List<UserMsgDTO> UserDOList2UserMsgDTOList(List<UserDO> userDOList){
		List<UserMsgDTO> dtos = new ArrayList<UserMsgDTO>();
		for (UserDO userDO : userDOList) {
			dtos.add(userDO2UserMsgDTO(userDO));
		}
		return dtos;
	}
}
