package com.startcaft.interceptor.aop;

public class Client {

	public static void main(String[] args) {
		ITargetInterface target = new Target();
		MyProxy proxyFactory = new MyProxy();
		ITargetInterface proxy =(ITargetInterface) proxyFactory.getProxy(target);
		
		System.out.println("被代理类真实的类型:" + target.getClass().getName());
		System.out.println("代理出来的类型:" + proxy.getClass().getName());
		
		proxy.doSomething();
	}
}
