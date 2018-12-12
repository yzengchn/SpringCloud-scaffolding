package com.yzeng.userserver.VO;

import java.io.Serializable;

import com.yzeng.userserver.enums.ResultEnum;

public class ResultVO implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    

	public ResultVO() {
		super();
	}

	public ResultVO(ResultEnum resultEnum) {
		super();
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
	
}
