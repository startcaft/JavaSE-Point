package com.startcaft.reflect;

@MyAnnotation(value="startcaft")
public class Person extends Parent<String> implements Comparable,MyInterface {
	
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person() {
		super();
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	public void say(){
		System.out.println("hello,我是 :" + this.name);
	}
	
	@MyAnnotation(value="abc123")
	public void show(String nv) throws Exception{
		System.out.println("我来自:" + nv);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void display(){
		
	}
	
	class Bird{
		
	}
}
