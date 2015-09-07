package com.startcaft.permission.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.permission.domain.Application;
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
		for (Department department : roots) {
			System.out.println("根节点：" + department.getName());
		}
	}
	
	
	@Test
	public void testGetChildDepartmentByParent(){
		
		List<Department> roots = departService.getChildDepartmentByParent(1);
		for (Department department : roots) {
			System.out.println(department.getName());
		}
	}
	
	@Test
	public void testGetDepartmentInfo(){
		
		Department depart = departService.getDepartmentInfo(1);
		System.out.println(depart.getId());
		System.out.println(depart.getName());
		System.out.println(depart.isEnable());
	}
	
	
	@Test
	public void testInsertDepart(){
		
		Application app = new Application();
		app.setId(1);
		
		Department pDeartment = new Department();
		pDeartment.setId(1);
		
		
		Department depart = new Department();
		depart.setApplication(app);
		depart.setParent(pDeartment);
		depart.setName("党政办CCC");
		depart.setEnable(true);//enbale一定要赋值，
		depart.setDesc("我也不知道描述写什么");
		
		
		boolean result = departService.addDepartment(depart);
		
		Assert.assertTrue(result);
	}
}	
