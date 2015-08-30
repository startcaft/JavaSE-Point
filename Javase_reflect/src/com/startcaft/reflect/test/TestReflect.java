package com.startcaft.reflect.test;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.startcaft.reflect.Person;

public class TestReflect {

	
	//在使用反射之前
	@Test
	public void testBeforeReflect(){
		
		Person person = new Person();
		person.setAge(20);
		person.setName("startcaft");
		
		person.say();
		person.show("中国");
	}
	
	//在使用反射之后
	@Test
	public void testAfterReflect() throws Exception{
		
		Class<Person> clazz = Person.class;
		Person p = clazz.newInstance();//通过默认的构造函数创建对象
		System.out.println(p);
		
		//字段
		//Field ageFiled = clazz.getField("age");只能获取public定义的字段
		Field ageField = clazz.getDeclaredField("age");//获取所有定义的字段
		//ageFiled.set(p, 20);//只能给public的字段赋值
		ageField.setAccessible(true);//允许给private的字段赋值
		ageField.set(p, 20);
		System.out.println(p);
		
		Field nameField = clazz.getDeclaredField("name");
		nameField.setAccessible(true);
		nameField.set(p, "startcaft");
		System.out.println(p);
		
		//方法
		Method sayMethod = clazz.getMethod("say");
		sayMethod.invoke(p);
		
		Method showMethod = clazz.getMethod("show", String.class);
		showMethod.invoke(p, "中国");
	}
	
	/**
	 * java.lang.Class : 它是java反射的源头；
	 * 我们创建的每一个类，通过编译(javax.ex)，生成对应的.class文件(java字节码);
	 * 之后使用java.exe加载(jvm的类加载器完成).class文件；
	 * .class文件被加载到内存以后，就是一个运行时类，存放在【缓存区】，那么运行时类本身就是一个Class类的实例；
	 * 【一个运行时类只加载一次！】;
	 */
	@Test
	public void testClass(){
		
		Person p = new Person();
		Class clazz = p.getClass();
		System.out.println(clazz);
	}
}
