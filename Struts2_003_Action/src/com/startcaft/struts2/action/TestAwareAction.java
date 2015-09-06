package com.startcaft.struts2.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class TestAwareAction implements RequestAware,ApplicationAware,SessionAware,ParameterAware {
	
	private Map<String,Object> application;
	private Map<String,Object> session;
	private Map<String,Object> request;
	private Map<String,String[]> parameter;
	
	
	public String  execute(){
		
		//application
		application.put("applicationKey", "applicationValue1");
		
		Date date = (Date) application.get("date");
		System.out.println(date);
		
		//session
		session.put("sessionKey", "sessionValue1");
		System.out.println(session.getClass());
		if(session instanceof SessionMap){
			SessionMap sm = (SessionMap) session;
			sm.invalidate();
			System.out.println("session失效了");
		}
		
		
		//request
		request.put("requestKey", "requestValue1");
		
		//parameter
		String[] params = parameter.get("name");
		System.out.println(params[0]);
		
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameter = arg0;
	}
}
