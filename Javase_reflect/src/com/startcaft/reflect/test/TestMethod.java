package com.startcaft.reflect.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.startcaft.reflect.Person;

public class TestMethod {

	/**获取运行时类的方法**/
	@Test
	public void test1() {
		Class clazz = Person.class;
		//1.getMethods()只能获取到运行时类[及其直接和简介父类]声明为public的方法
		Method[] methods = clazz.getMethods();
		for(Method m : methods){
			System.out.println(m);
		}
		System.out.println("/***********************/");
		//2.getDeclaredMethods()只能获取运行时类[本身]定义的所有方法
		methods = clazz.getDeclaredMethods();
		for(Method m : methods){
			System.out.println(m);
		}
	}
	
	/**获取注解 权限修饰符 返回值类型 方法名 形参列表 异常**/
	@Test
	public void test2(){
		Class clazz = Person.class;
		
		Method[] methods = clazz.getDeclaredMethods();
		for(Method m : methods){
			//1.注解
			Annotation[] ann = m.getAnnotations();
			for(Annotation a : ann){
				System.out.print(a + "---");
			}
			
			//2.方法的权限修饰符
			String s = Modifier.toString(m.getModifiers());
			System.out.print(s + "  ");
			
			//3.方法的返回值类型
			Class returnClass = m.getReturnType();
			System.out.print(returnClass.getName() + "  ");
			
			//4.方法名
			System.out.print(m.getName() + " ");
			
			//5.形参列表
			System.out.print("(");
			Class[] params = m.getParameterTypes();
			for(Class c : params){
				System.out.print(c.getName() + " ");
			}
			System.out.println(")");
			
			//6.异常
			Class[] exs = m.getExceptionTypes();
			for(Class e : exs){
				System.out.print(" trows " + e.getName());
			}
		}
	}
}
