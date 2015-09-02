package com.startcaft.permission.vo;

public class Vdepartment {
	
	private Integer departId;
	private Integer appId;
	private String departName;
	private String appName;
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Vdepartment(Integer departId, Integer appId, String departName,
			String appName) {
		super();
		this.departId = departId;
		this.appId = appId;
		this.departName = departName;
		this.appName = appName;
	}
	public Vdepartment() {
	}
}
