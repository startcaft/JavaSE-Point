package com.startcaft.struts2.action;

public class MulAction {
	
	public String save(){
		System.out.println("save");
		return "save-success";
	}
	public String delete(){
		System.out.println("delete");
		return "delete-success";
	}
	public String query(){
		System.out.println("query");
		return "query-success";
	}
	public String update(){
		System.out.println("update");
		return "update-success";
	}
}
