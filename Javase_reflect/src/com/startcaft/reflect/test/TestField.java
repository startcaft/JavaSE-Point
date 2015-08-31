package com.startcaft.reflect.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.startcaft.reflect.Person;

public class TestField {

	/**获取运行时类的属性**/
	@Test
	public void test1() {
		Class clazz = Person.class;
		
		//1.getFields()只能获取到运行时类[及其父类中]声明为public的属性
		Field[] fields = clazz.getFields();
		for(int i=0;i<fields.length;i++){
			System.out.println(fields[i].getName());
		}
		System.out.println("/***********************/");
		//2.getDeclaredFields()只能获取到运行时类[本身]声明的所有的属性
		fields = clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			System.out.println(fields[i].getName());
		}
	}
	
	/**权限修饰符 变量类型 变量名**/
	/**获取属性的各个部分的内容**/
	@Test
	public void test2(){
		Class clazz = Person.class;
		Field[] fields = clazz.getDeclaredFields();
		for(Field f : fields){
			//1.获取每个属性的权限修饰符
			//Modifier对类和成员访问修饰符进行解码，它提供了静态方法和常量，修饰符集被表示为整数
			int i = f.getModifiers();
			String s = Modifier.toString(i);
			System.out.print(s + "---");
			
			//2.获取属性的变量类型
			Class fClazz  = f.getType();
			System.out.print(fClazz.getName() + "---");
			
			//3.获取属性名
			System.out.println(f.getName());
		}
	}
}
