package com.startcaft.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Subject {
	void action();
}

// 被代理类
class RealSubject implements Subject {

	@Override
	public void action() {
		System.out.println("我是被代理类，记得要执行我...");
	}
}

class MyInvocationHandler implements InvocationHandler {

	// 被代理类
	Object target;

	/**
	 * 返回代理类对象
	 * 
	 * @param target
	 *            ---被代理类对象
	 * @return 代理对象
	 */
	public Object bindTarget(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}
	
	/**
	 * 对被代理类的重写的方法的调用都会转化为调用invoke方法
	 */
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		System.out.println("invoke");
		Object returnObj = arg1.invoke(arg0, arg2);
		return returnObj;
	}

}

public class TestDymanicProxy {

	public static void main(String[] args) {
		//1.创建一个被代理类对象
		RealSubject real = new RealSubject();
		
		//2.创建一个实现了InvocationHandler接口的对象
		MyInvocationHandler myInvokeHandler = new MyInvocationHandler();
		
		//3.返回代理对象
		Object obj = myInvokeHandler.bindTarget(real);
		
		//4.强制类型转换
		Subject proxy = (Subject) obj;
		
		proxy.action();
	}
}
