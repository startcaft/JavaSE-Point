package com.startcaft.struts2.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;


/**
 * 通过实现ServletxxxAware接口，也可以获取到原生的 Servlet API
 */
public class TestServletAwareAction implements ServletRequestAware,
			ServletContextAware,ServletResponseAware{
	
	private HttpServletRequest request;
	private ServletContext servletContext;
	private HttpSession session;
	private HttpServletResponse response;
	
	public String execute(){
		return "success";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession();
		
		System.out.println(request);
		System.out.println(session);
	}
	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
		
		System.out.println(servletContext);
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
		System.out.println(response);
	}
}
