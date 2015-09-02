package com.startcaft.permission.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**功能操作实体类**/
@Entity
@Table(name="tb_ctrl")
public class Ctrl {
	
	private int id;
	
	private String name;
	
	private String icon;
	
	private boolean isEnable = true;
	
	private String desc;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="ctrl_name",nullable=false,length=50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}
	
	@Column(name="ctrl_icon")
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Column(name="is_enable",nullable=false)
	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name="ctrl_desc")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
