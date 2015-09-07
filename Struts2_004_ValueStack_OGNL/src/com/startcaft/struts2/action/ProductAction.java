package com.startcaft.struts2.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class ProductAction implements RequestAware,SessionAware {

	private Map<String,Object> sessionMap;
	private Map<String,Object> requestMap;
	
	
	private String productName;
	private String productDesc;
	private double productPrice;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	@Override
	public String toString() {
		return "ProductAction [productName=" + productName + ", productDesc="
				+ productDesc + ", productPrice=" + productPrice + "]";
	}

	public String save(){
		System.out.println("save:" + this);
		
		//1，获取ValueStack对象
		ValueStack vs = ActionContext.getContext().getValueStack();
		
		//2.创建一个对象
		Test test = new Test();
		test.setProductName("startcaft");
		
		//4.将对象压入ValueStack对象中
		//5.再在页面中查看ValueStack对象状态，Test对象会在栈顶
		vs.push(test);
		
		sessionMap.put("product", this);
		requestMap.put("test", test);
		
		
		//产生一个数学异常
		int i = 10 / 0;
		
		return "success";
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.requestMap = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}
}	
