package com.startcaft.permission.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
//			request.setAttribute("departs", appService.getAppById(appId).getDepartments());
//			request.getRequestDispatcher("/Depart/index.jsp").forward(request, response);
			List<Department> roots = departService.getRootDepartmentByApp(appId);
			System.out.println(roots);
		} catch (Exception e) {
			response.getWriter().println("no support app");
		}
	}
	
	
	private void println(Department depart,int level){
		String preStr = "";
		for(int i=0;i<level;i++){
			 preStr += "|----";
		}
		System.out.println(preStr + depart.getName());
		for(Department cDepart : depart.getChildren()){
			println(cDepart,level + 1);
		}
	}
}
