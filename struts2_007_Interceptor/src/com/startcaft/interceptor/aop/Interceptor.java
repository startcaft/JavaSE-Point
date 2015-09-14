package com.startcaft.interceptor.aop;

/**拦截器**/
public class Interceptor {
	
	public void before(){
		System.out.println("invocation before");
	}
	
	public void after(){
		System.out.println("invocation after");
	}
}
