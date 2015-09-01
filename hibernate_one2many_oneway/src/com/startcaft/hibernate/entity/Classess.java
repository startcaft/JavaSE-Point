package com.startcaft.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_classess")
public class Classess {
	
	private int id;
	
	private String name;
	
	//一对多通常使用Set接口来映射，因为Hibernate有其自己的实现(主要是延迟加载)
	private Set<Student> students = new HashSet<Student>();
	
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="classess_name",nullable=false,length=30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * 使用@OneToMany进行一对多的注解
	 * @JoinColumn必须声明，不然会生成中间表。
	 */
	@OneToMany
	@JoinColumn(name="classessId")
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
