package com.startcaft.struts2.action;

public class TestResultAction {
	
	private Integer number;
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String execute(){
		
		String result = null;
		
		if(number % 4 == 0){
			result = "login";
		}
		else if(number % 4 == 1){
			result = "success";
		}
		else if(number % 4 == 2){
			result = "index";
		}
		else{
			result = "test";
		}
		
		return result;
	}
}
