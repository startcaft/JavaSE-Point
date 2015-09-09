package com.startcaft.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*普通的接口*/
interface TargetInterface {
	public int targetMethodA(int number);

	public int targetMethodB(int number);
}

/* 实现接口的类 */
class ConcreteClass implements TargetInterface {

	@Override
	public int targetMethodA(int number) {
		System.out.println("开始调用目标类的方法targetMethodA...");
		System.out.println("操作-打印数字:" + number);
		System.out.println("结束调用目标类的方法targetMethodA...");
		return number;
	}

	@Override
	public int targetMethodB(int number) {
		System.out.println("开始调用目标类的方法targetMethodB...");
		System.out.println("操作-打印数字:" + number);
		System.out.println("结束调用目标类的方法targetMethodB...");
		return number;
	}
}

/* 代理处理器类ProxyHandler,必须实现java.lang.reflect.InvocationHandler接口 */
class ProxyHandler implements InvocationHandler {

	private Object target;// 被代理的对象，也就是实现了TargetInterface接口的类的对象

	public ProxyHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("proxy:" + proxy.getClass().getName());
		System.out.println("method:" + method.getName());
		for (Object object : args) {
			System.out.println("method agrs:" + object.getClass().getName());
		}

		System.out.println("BeFore invoke method...");
		Object obj = method.invoke(target, args);
		System.out.println("After invoke method...");

		return obj;
	}
}

public class TestDymanicProxy {

	public static void main(String[] args) {

		// 元对象(被代理对象)
		ConcreteClass target = new ConcreteClass();

		// 代理处理程序
		InvocationHandler handler = new ProxyHandler(target);

		// 返回指定接口的代理类实例
		TargetInterface proxyTarget = (TargetInterface) Proxy.newProxyInstance(
				target.getClass().getClassLoader(), target.getClass()
						.getInterfaces(), handler);
		
		//调用代理类实力的方法，Java会执行InvocationHandler接口中的invoke方法
		int i = proxyTarget.targetMethodA(5);
		System.out.println(i);
		
		int j = proxyTarget.targetMethodB(15);
		System.out.println(j);

	}
}
