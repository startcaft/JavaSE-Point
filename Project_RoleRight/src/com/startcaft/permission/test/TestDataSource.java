package com.startcaft.permission.test;


import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**测试数据源**/
public class TestDataSource {

	private ApplicationContext context;
	
	{
		context = new ClassPathXmlApplicationContext("application.xml");
	}
	
	@Test
	public void testDataSource(){
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource);
	}

}
