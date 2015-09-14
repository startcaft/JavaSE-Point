package com.startcaft.interceptor.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



/**接口**/
public interface ITargetInterface {
	public void doSomething();
}

/**实现了某个接口的目标对象**/
class Target implements ITargetInterface{

	@Override
	public void doSomething() {
		System.out.println("do something...");
	}
}

/**实现了java.lang.reflect.InvocationHandler接口的代理处理程序**/
class MyHandler implements InvocationHandler{
	
	//目标对象
	private Object obj;
	//注入目标对象
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	//拦截器对象
	private Interceptor interceptor = new Interceptor();
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		//目标对象方法的返回值;
		Object result = null;
		interceptor.before();
		//对目标对象方法的调用;
		result = method.invoke(obj, args);
		interceptor.after();
		
		return result;
	}
}

/**代理生产类**/
class MyProxy{
	
	//根据一个目标类产生一个代理对象
	public Object getProxy(Object object){
		MyHandler handler = new MyHandler();
		handler.setObj(object);
		return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), handler);
	}
}
