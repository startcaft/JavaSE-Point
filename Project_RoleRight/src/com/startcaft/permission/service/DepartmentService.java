package com.startcaft.permission.service;

import java.util.List;

import com.startcaft.permission.domain.Department;

public interface DepartmentService {
	
	/**
	 * 根据AppId获取应用程序的所有根节点的部门
	 * @param appId
	 */
	public List<Department> getRootDepartmentByApp(Integer appId);
}
