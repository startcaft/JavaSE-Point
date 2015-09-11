package com.startcaft.action;

import com.startcaft.service.PersonService;

public class PersonAction {
	
	private PersonService personSrevice;
	
	public void setPersonSrevice(PersonService personSrevice) {
		this.personSrevice = personSrevice;
	}
	
	public String execute(){
		personSrevice.save();
		System.out.println("execute");
		return "success";
	}
}
