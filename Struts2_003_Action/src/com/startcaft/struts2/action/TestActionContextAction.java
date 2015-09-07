package com.startcaft.struts2.action;

import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class TestActionContextAction {
	
	public String execute(){
		
		//0,获取到ActionContext类，上下文对象是new不出来的，一般都是用工厂方法来创建
		ActionContext actionContext = ActionContext.getContext();
		
		//1,获取到Application对象 对应的Map
		Map<String,Object> appMap = actionContext.getApplication();
		//设值
		appMap.put("applicationKey", "applicationValue");
		//取值
		Date date = (Date) appMap.get("date");
		System.out.println(date);
		
		
		//2,获取到HttpSession对象 对应的Map
		Map<String,Object> sessionMap = actionContext.getSession();
		sessionMap.put("sessionKey", "sessionValue");
		
		
		//3,获取到HttpServletRequest对象 对应的Map
		//ActionContext类并没有提供getRequest()方法，而是使用get("request")来获取,它返回的是一个Object，必须强转成Map<String,Object>
		Map<String,Object> requestMap = (Map<String, Object>) actionContext.get("request");
		requestMap.put("requestKey", "requestValue");
		
		
		//4,获取到请求参数 对应的Map
		//ActionContext的getParameters()返回Map<String,Obejct>,而不是我们认为的Map<String,String[]>
		//无法向请求参数中赋值，虽然不会报错，但是没有任何作用
		Map<String,Object> paramMap = actionContext.getParameters();
		paramMap.put("age", 100);
		
		String name = ((String[])paramMap.get("name"))[0];
		System.out.println("request param:" + name);
		
		return "success";
	}
}
