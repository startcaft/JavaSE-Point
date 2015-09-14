package com.startcaft.struts2.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dao {
	
	private static Map<Integer,Employee> emps = new HashMap<Integer,Employee>();
	
	static{
		emps.put(1001, new Employee(1001, "AA", "aa", "aa@163.com"));
		emps.put(1002, new Employee(1002, "BB", "bb", "bb@163.com"));
		emps.put(1003, new Employee(1003, "CC", "cc", "cc@163.com"));
		emps.put(1004, new Employee(1004, "DD", "dd", "dd@163.com"));
		emps.put(1005, new Employee(1005, "EE", "ee", "ee@163.com"));
		emps.put(1006, new Employee(1006, "FF", "ff", "ff@163.com"));
	}
	
	public List<Employee> getEmployees(){
		return new ArrayList<Employee>(emps.values());
	}
	
	public void delete(Integer id){
		emps.remove(id);
	}
	
	public void save(Employee emp){
		long time = System.currentTimeMillis();
		emp.setEmployeeId((int)time);
		
		emps.put(emp.getEmployeeId(), emp);
	}
	
	public Employee get(Integer id){
		return emps.get(id);
	}
	
	public void update(Employee emp){
		emps.put(emp.getEmployeeId(), emp);
	}
}
