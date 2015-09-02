package com.startcaft.permission.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**应用程序实体类**/
@Entity
@Table(name="Tb_Application")
public class Application {
	
	private Integer id;
	
	private String name;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="app_name",nullable=false,length=100,unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
