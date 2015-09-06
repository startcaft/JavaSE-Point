package com.startcaft.struts2.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

/**
 * 在Structs2的Action中通过ServletActionContext类来 获取原生的Servlet API
 * 
 * ServletActionContext类继承自ActionContext
 */
public class TestServletActionContext {
	
	private static final String SUCCESS = "success";
	
	public String execute(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		ServletContext servletContext = ServletActionContext.getServletContext();
		
		System.out.println(request);
		System.out.println(session);
		System.out.println(response);
		System.out.println(servletContext);
		
		
		return SUCCESS;
	}
}
