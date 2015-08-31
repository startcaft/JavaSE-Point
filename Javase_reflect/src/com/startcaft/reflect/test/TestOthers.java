package com.startcaft.reflect.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

import com.startcaft.reflect.Person;

public class TestOthers {
	
	/**获取父类**/
	@Test
	public void test1(){
		Class clazz = Person.class;
		Class parentClazz = clazz.getSuperclass();
		System.out.println(parentClazz.getName());
	}
	
	/**获取带泛型的父类**/
	@Test
	public void test2(){
		Class clazz = Person.class;
		Type type = clazz.getGenericSuperclass();
		System.out.println(type);
	}
	
	/**获取父类的泛型参数，重要**/
	@Test
	public void test3(){
		Class clazz = Person.class;
		Type type = clazz.getGenericSuperclass();
		//ParameterizedType 表示参数化类型
		ParameterizedType param = (ParameterizedType)type;
		//返回表示此类型实际类型参数的 Type 对象的数组。
		Type[] gType = param.getActualTypeArguments();
		
		for(Type t : gType){
			System.out.println(((Class)t).getName());
		}
	}
	
	/**获取父类的接口**/
	@Test
	public void test4(){
		Class clazz = Person.class;
		
		Class[] iClzz = clazz.getInterfaces();
		for(Class c : iClzz){
			System.out.println(c.getName());
		}
	}
	
	/**获取所在的包**/
	@Test
	public void test5(){
		Class clazz = Person.class;
		Package packages = clazz.getPackage();
		System.out.println(packages.getName());
	}
	
	
	/**获取关于类的注解**/
	@Test
	public void test6(){
		Class clazz = Person.class;
		//注解的生命周期为Runtime才能被反射获取
		Annotation[] anns = clazz.getAnnotations();
		for(Annotation a : anns){
			System.out.println(anns);
		}
	}
}
