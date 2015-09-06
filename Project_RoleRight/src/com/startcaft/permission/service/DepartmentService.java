package com.startcaft.permission.service;

import java.util.List;

import com.startcaft.permission.domain.Department;

public interface DepartmentService {
	
	/**
	 * 根据AppId获取应用程序的所有根节点的部门
	 * @param appId---应用程序ID
	 */
	public List<Department> getRootDepartmentByApp(Integer appId);
	
	
	/**
	 * 根据部门父节点ID获取其下面一级的子节点部门
	 * @param parentId---部门父节点ID
	 * @return
	 */
	public List<Department> getChildDepartmentByParent(Integer parentId);
	
	
	/***
	 * 添加部门信息
	 * @param depart---Department对象
	 * @param appId---应用程序ID，不可能为null
	 * @param ParentId---部门父节点ID，添加根节点，该参数为null
	 * @return 
	 */
	public boolean addDepartment(Department depart);
}
