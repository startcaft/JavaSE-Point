package com.startcaft.permission.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.permission.domain.Department;
import com.startcaft.permission.service.DepartmentService;

public class TestDeprtmentService {
	
	private ApplicationContext context;
	private DepartmentService departService;
	
	{
		context = new ClassPathXmlApplicationContext("application.xml");
		departService = context.getBean(DepartmentService.class);
	}
	
	@Test
	public void testRootDepartmentByApp(){
		
		List<Department> roots = departService.getRootDepartmentByApp(1);
		System.out.println(roots);
	}
}
