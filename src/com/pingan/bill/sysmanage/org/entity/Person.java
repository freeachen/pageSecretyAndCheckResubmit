package com.pingan.bill.sysmanage.org.entity;

public class Person {

	String pid;
	String loginName;
	String password;
	String s_ip; // 登录状态 0 未登录  1已登录
	
	public String getS_ip() {
		return s_ip;
	}
	public void setS_ip(String s_ip) {
		this.s_ip = s_ip;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
