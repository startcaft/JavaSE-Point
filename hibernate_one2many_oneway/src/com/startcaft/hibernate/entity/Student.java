package com.startcaft.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_student")
public class Student {
	
	private int id;
	
	private String name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="student_name",nullable=false,length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
