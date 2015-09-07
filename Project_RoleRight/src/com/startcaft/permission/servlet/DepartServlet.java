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
		
		String action = request.getParameter("action");
		switch (action) {
		case "query":
			query(request, response);
			break;
		case "queryChild":
			queryChild(request,response);
		}
	}
	
	/**
	 * 生成子节点部门信息XML文档
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setHeader("Content-type", "text/xml;charset=UTF-8"); 
		Document document  = DocumentHelper.createDocument();
		//根节点---departments
		Element root = document.addElement("departments");
		
		String id = request.getParameter("pid");
		Integer parentId = 0;
		try {
			parentId = Integer.valueOf(id);
			List<Department> roots = departService.getChildDepartmentByParent(parentId);
			if(roots != null && roots.size() > 0){
				for (Department department : roots) {
					
					//一级节点---depart
					Element depart = root.addElement("depart");
					
					Element departId = depart.addElement("id");
					departId.setText(department.getId().toString());
					
					Element name = depart.addElement("name");
					name.setText(department.getName());
				}
			}
		} catch (Exception e) {
			Element error = root.addElement("error");
			error.setText("param of appid cast error");
		}
		
		String xml = document.asXML();
		response.getWriter().println(xml);
	}
	
	/**
	 * 查询生成根节点部门信息XML文档
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setHeader("Content-type", "text/xml;charset=UTF-8"); 
		Document document  = DocumentHelper.createDocument();
		//根节点---departments
		Element root = document.addElement("departments");
		
		String id = request.getParameter("appid");
		Integer appId = 0;
		try {
			appId = Integer.valueOf(id);
			List<Department> roots = departService.getRootDepartmentByApp(appId);
			if(roots != null && roots.size() > 0){
				for (Department department : roots) {
					
					//一级节点---depart
					Element depart = root.addElement("depart");
					
					Element departId = depart.addElement("id");
					departId.setText(department.getId().toString());
					
					Element name = depart.addElement("name");
					name.setText(department.getName());
				}
			}
		} catch (Exception e) {
			Element error = root.addElement("error");
			error.setText("param of appid cast error");
		}
		
		String xml = document.asXML();
		response.getWriter().println(xml);
	}
	
//	private void prtitNode(Department depart){
//		if(depart == null){return;}
//		System.out.println(depart.getName() + " : [childsize:" + depart.getChildren().size() + "]");
//		Set<Department> children = depart.getChildren();
//		for(Iterator<Department> it = children.iterator();it.hasNext();){
//			Department child = it.next();
//			prtitNode(child);
//		}
//	}
}
