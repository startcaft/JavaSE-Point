package com.startcaft.permission.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**系统资源实体类**/
@Entity
@Table(name="tb_menu")
public class Menu {
	
	private int id;
	
	private Menu parent;//父节点
	
	private Set<Menu> children = new HashSet<Menu>();//子节点
	
	private String name;
	
	private String icon;
	
	private String url;
	
	private int sortBy = 0;
	
	private boolean enable = true;
	
	private Set<Ctrl> ctrls = new HashSet<Ctrl>();//一个菜单包含多个功能按钮，onetomany
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy="parent")
	public Set<Menu> getChildren() {
		return children;
	}

	public void setChildren(Set<Menu> children) {
		this.children = children;
	}
	
	@Column(name="menu_name",nullable=false,length=50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Column(name="menu_url",length=255)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="sort_index",nullable=false)
	public int getSortBy() {
		return sortBy;
	}

	public void setSortBy(int sortBy) {
		this.sortBy = sortBy;
	}
	
	@Column(name="is_enable")
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	@OneToMany
	@JoinColumn(name="menu_id")
	public Set<Ctrl> getCtrls() {
		return ctrls;
	}

	public void setCtrls(Set<Ctrl> ctrls) {
		this.ctrls = ctrls;
	}
}
