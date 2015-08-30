package com.startcaft.reflect.test;

import org.junit.Test;

import com.startcaft.reflect.Person;

public class TestConstructor {

	/**
	 * 使用反射构造运行时类的对象
	 */
	@Test
	public void testFeflectConstructor() throws Exception{
		
		String className = "com.startcaft.reflect.Person";
		Class clazz = Class.forName(className);
		
		//构造运行时类的对象，Class类的newInstance()方法，调用无参的构造器
		//要注意无参构造器的权限
		Object obj = clazz.newInstance();
		Person p = (Person) obj;
		System.out.println(p);
	}	
}
