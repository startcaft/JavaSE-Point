package com.startcaft.permission.test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.permission.domain.Application;
import com.startcaft.permission.service.ApplicationService;

public class TestApplicationService {


	private ApplicationContext context;
	private ApplicationService appService;
	
	{
		context = new ClassPathXmlApplicationContext("application.xml");
		appService = context.getBean(ApplicationService.class);
	}
	
	@Test
	public void testGetAppInfo(){
		
		Application app = appService.getAppInfo(1);
		
		if(app != null){
			System.out.println(app.getId() + "/" + app.getName());
		}
		else{
			System.out.println("没有指定的对象");
		}
	}
}
