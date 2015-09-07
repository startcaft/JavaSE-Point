package com.startcaft.struts2.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

public class UserLoginAction implements SessionAware,ApplicationAware {
	
	private Map<String,Object> session;
	private Map<String,Object> application;
	
	private String username;
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String execute(){
		
		//1.把用户信息保存到Session中
		session.put("username", username);
		
		//2.获取当前的在线人数
		Integer count = (Integer) application.get("count");
		if(count == null)
			count = 0;
		
		count++;
		
		application.put("count", count);
		
		return "login-success";
	}
	
	public String logout(){
		
		//1.在线数量-1
		Integer count = (Integer) application.get("count");
		if(count != null && count > 0){
			count--;
			application.put("count", count);
		}
		
		//2.session失效
		SessionMap sm = (SessionMap) session;
		sm.invalidate();
		
		return "login-success";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
}
