package com.pingan.bill.sysmanage.org.controller;

import org.json.simple.JSONObject;

public class ResultObject extends JSONObject {
	private static final long serialVersionUID = 1L;
	private ResultObject(){}
	
	private ResultObject(String code, Object data){
		setCode(code);
		setData(data);
		
	}
	public static void build(String code, Object data){
		new ResultObject(code, data);
	}

	private void setCode(String code) {
		put("code", code);
	}
	private void setData(Object data) {
		put("data", data);
	}
	
	public String getCode(){
		return (String) get("code");
	}
	public Object getData(){
		return get("data");
	}
}
