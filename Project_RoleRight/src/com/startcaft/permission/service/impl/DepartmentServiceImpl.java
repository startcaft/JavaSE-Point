package com.startcaft.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcaft.permission.dao.DepartmentDao;
import com.startcaft.permission.domain.Department;
import com.startcaft.permission.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departDao;
	
	
		
	@Override
	public List<Department> getRootDepartmentByApp(Integer appId) {
		
		String hql = "select d from Department d where d.parent = NULL and d.application.id = ?";
		List<Department> roots = departDao.findByHQL(hql,appId);
		return roots;
	}

	@Override
	public List<Department> getChildDepartmentByParent(Integer parentId) {
		String hql = "select d from Department d where d.parent.id = ?";
		List<Department> childs = departDao.findByHQL(hql, parentId);
		return childs;
	}

	@Override
	public boolean addDepartment(Department depart) {
		
		boolean result = false;
		
		departDao.save(depart);
		
		result = true;
		
		return result;
	}

	@Override
	public Department getDepartmentInfo(Integer id) {
		
		Department department = departDao.findById(id);
		
		return department;
	}
}
