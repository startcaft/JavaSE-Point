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
		
		String hql = "from Department d join d.application where d.application.id = ? and d.parent = NULL";
		List<Department> roots = departDao.findByHQL(hql,appId);
		return roots;
	}
}
