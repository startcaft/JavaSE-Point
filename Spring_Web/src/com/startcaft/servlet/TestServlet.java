package com.startcaft.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.startcaft.springweb.bean.Person;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		
		//1，从application 作用域中获取到 IOC容器的引用
		ApplicationContext ctx = (ApplicationContext) context.getAttribute("applicationContext");
		
		//2，获取指定的bean
		Person person = (Person) ctx.getBean("person");
		
		person.hello();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
