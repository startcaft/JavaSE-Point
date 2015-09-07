package com.startcaft.permission.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.startcaft.permission.common.XMLHelper;
import com.startcaft.permission.domain.Application;
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
		
		//action为空时的提示信息
		if(action == null){
			response.setHeader("Content-type", "text/xml;charset=UTF-8"); 
			String xml = XMLHelper.printlnErrorElement("没有指定的请求目标");
			response.getWriter().println(xml);
			return;
		}
		
		switch (action) {
		case "query":
			query(request, response);
			break;
		case "queryChild":
			queryChild(request,response);
			break;
		case "add":
			addDepartment(request,response);
		case "update":
			updateDepartment(request,response);	
		case "get":
			getDepartment(request,response);
		}
	}
	
	private void getDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setHeader("Content-type", "text/xml;charset=UTF-8"); 
		String departId = request.getParameter("id");
		Document document = DocumentHelper.createDocument();
		Element departEle = document.addElement("Department");
		
		try {
			Integer id = Integer.parseInt(departId);
			Department depart = departService.getDepartmentInfo(id);
			
			Element idEle = departEle.addElement("id");
			Element nameEle = departEle.addElement("name");
			Element descEle = departEle.addElement("desc");
			Element enableEle = departEle.addElement("isenable");
			
			idEle.addText(depart.getId().toString());
			nameEle.addText(depart.getName());
			descEle.addText(depart.getDesc() == null ? "" : depart.getDesc());
			enableEle.addText(String.valueOf(depart.isEnable()));
			
		} catch (Exception e) {
			Element error = departEle.addElement("error");
			error.setText(e.getMessage());
		}
		
		String xml = document.asXML();
		response.getWriter().println(xml);
	}
	
	/**
	 * 修改部门信息，并返回XML文档 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		String pid = request.getParameter("pid");
		String isenable = request.getParameter("isenable");//0为false,非0为true
		
		Department department = new Department();
		
		String returnVal = null;
		
		try {			
			//获取根节点的部门信息,如果 pid>0 就认为是有父节点的，如果不是 则当前Department对象为根节点部门
			Department parent = null;
			if(Integer.parseInt(pid) > 0){
				parent = new Department();
				parent.setId(Integer.parseInt(pid));
			}
			department.setParent(parent);
			
			department.setName(name);
			department.setDesc(desc);
			department.setEnable(Boolean.valueOf(isenable));
			
			boolean result = departService.addDepartment(department);
			returnVal = String.valueOf(result);
			
		} catch (Exception e) {
			returnVal = "服务器异常";
		}
		
		response.getWriter().print(XMLHelper.getMessageXml(returnVal));
	}
	
	/**
	 * 添加一个新的部门,并生成XML文档
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String appid = request.getParameter("appid");
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		String pid = request.getParameter("pid");
		String isenable = request.getParameter("isenable");//0为false,非0为true
		
		Department department = new Department();
		
		String returnVal = null;
		
		try {
			//获取相关联的应用程序信息
			Application app = new Application();
			app.setId(Integer.parseInt(appid));
			department.setApplication(app);
			
			//获取根节点的部门信息,如果 pid>0 就认为是有父节点的，如果不是 则当前Department对象为根节点部门
			Department parent = null;
			if(Integer.parseInt(pid) > 0){
				parent = new Department();
				parent.setId(Integer.parseInt(pid));
			}
			department.setParent(parent);
			
			department.setName(name);
			department.setDesc(desc);
			department.setEnable(Boolean.valueOf(isenable));
			
			boolean result = departService.addDepartment(department);
			returnVal = String.valueOf(result);
			
		} catch (Exception e) {
			returnVal = "服务器异常";
		}
		
		response.getWriter().print(XMLHelper.getMessageXml(returnVal));
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
			error.setText(e.getMessage());
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
