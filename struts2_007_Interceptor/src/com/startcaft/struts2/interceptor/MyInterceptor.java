package com.startcaft.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MyInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String hello;
	public String getHello() {
		return hello;
	}
	public void setHello(String hello) {
		this.hello = hello;
	}

	@Override
	public void destroy() {
		System.out.println("destory()");
	}

	@Override
	public void init() {
		System.out.println("init()");
		System.out.println(this.hello);
	}
	
	/**
	 * 拦截Action
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		System.out.println("interceptor");
		String result = invocation.invoke();
		System.out.println("finshed");
		return result;
	}

}
