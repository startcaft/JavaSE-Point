package com.startcaft.permission.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**角色实体类**/
@Entity
@Table(name = "tb_group")
public class Group {
	
	private int id;
	
	private String name;
	
	private String desc;
	
	private boolean isEnable = true;
	
	private Set<Menu> menus = new HashSet<Menu>();
	
	private Set<User> users = new HashSet<User>();
	
	@Id
	@GeneratedValue()
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "group_name", length = 30, nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "group_desc", length = 255, nullable = true)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Column(name = "is_enbale", nullable = false)
	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	@ManyToMany
	@JoinTable(name="tb_menu_group"	//中间表
			,joinColumns={@JoinColumn(name="group_id")}	//当前类，在中间表的列名
			,inverseJoinColumns={@JoinColumn(name="menu_id")})	//关联类，在中间表的列名
	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
	// mappedBy="由One的一方指向Many的一方，并且，这个属性应该等于Many的一方中含有One类的属性的属性名"
	@OneToMany(mappedBy = "group")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	} 
}
