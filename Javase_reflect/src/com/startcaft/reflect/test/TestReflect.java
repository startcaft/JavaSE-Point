package com.startcaft.reflect.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;

import com.startcaft.reflect.Person;

public class TestReflect {

	
	//在使用反射之前
	@Test
	public void testBeforeReflect() throws Exception{
		
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
	
	/**
	 * 获取java.lang.Class的几种方式
	 */
	@Test
	public void testGetClassObject() throws ClassNotFoundException{
		
		//1，调用运行时类本身的.class属性
		Class clazz1 = Person.class;
		System.out.println(clazz1.getName());
		
		Class clazz2 = String.class;
		System.out.println(clazz2.getName());
		
		
		//2，通过运行时类对象的getClass()方法获取(该方法从Object类继承而来)
		Person person = new Person();
		Class clazz3 = person.getClass();
		System.out.println(clazz3.getName());
		
		//3，通过Class类的静态方法forName(String className)，必须提供类的全类名
		Class clazz4 = Class.forName("com.startcaft.reflect.Person");
		System.out.println(clazz4.getName());
		
		
		//4，通过ClassLoader类加载器
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class clazz5 = classLoader.loadClass("com.startcaft.reflect.Person");
		System.out.println(clazz5.getName());
	}
	
	
	/**
	 * ClassLoader 类加载器:
	 * 引导加载器：加载Java 核心库，无法获取
	 * 扩展加载器：加载Java 扩展库
	 * 系统加载器：加载Java应用的类路径(CLASSPATH)下的java类
	 * @throws IOException 
	 */
	@Test
	public void testClassLoader() throws IOException{
		
		ClassLoader loader1 = ClassLoader.getSystemClassLoader();//系统加载器
		System.out.println(loader1);
		
		ClassLoader loader2 = loader1.getParent();//扩展加载器
		System.out.println(loader2);
		
		ClassLoader loader3 = loader2.getParent();//引导加载器，无法获取到
		System.out.println(loader3);
		
		
		//需要掌握以下的点，可以通过ClassLoader来获取指定文件夹下的文件
		//1,加载classpath下任意文件夹下的文件
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("com\\startcaft\\reflect\\jdbc.properties");
		//2，加载当前工程下的文件
		//FileInputStream is = new FileInputStream(new File("jdbc.properties"));
		Properties prop = new Properties();
		prop.load(is);
		System.out.println(prop);
	}
}
