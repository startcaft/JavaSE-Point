package com.startcaft.permission.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.startcaft.permission.domain.Department;
import com.startcaft.permission.service.DepartmentService;

/**部门相关业务的Servlet处理器**/
public class DepartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private WebApplicationContext context;

	private DepartmentService departService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		departService = context.getBean(DepartmentService.class);
		
		String id = request.getParameter("appid");
		Integer appId = 0;
		try {
			appId = Integer.valueOf(id);
			List<Department> roots = departService.getRootDepartmentByApp(appId);
			response.setHeader("Content-type", "text/xml;charset=UTF-8"); 
			String xml = this.writerRootDepartXml(response, roots);
			response.getWriter().println(xml);
		} catch (Exception e) {
			response.getWriter().println("no support app");
		}
	}
	
	private String writerRootDepartXml(HttpServletResponse response,List<Department> list) throws ServletException, IOException{
		
		Document document  = DocumentHelper.createDocument();
		//根节点---departments
		Element root = document.addElement("departments");
		for (Department department : list) {
			
			//一级节点---depart
			Element depart = root.addElement("depart");
			
			Element id = depart.addElement("id");
			id.setText(department.getId().toString());
			
			Element name = depart.addElement("name");
			name.setText(department.getName());
		}
		
		return document.asXML();
	}
}
