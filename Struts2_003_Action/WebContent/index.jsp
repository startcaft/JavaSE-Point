<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ActionContext类</title>
</head>
<body>
	
	<a href="actionContext.action?name=startcaft">Test ActionContext</a>
	<hr>
	<a href="awareAction.action?name=startcaft">Test Aware</a>
	<hr>
	<a href="servletAction">Test ServletActionContext</a>
	<hr>
	<a href="servletAwareAction">Test ServletxxxAware</a>
	<hr>
	<a href="login_ui">统计在线人数Demo</a>
	<hr>
	<a href="testResult.do?number=5">Action的result结果测试</a>
	<hr><hr><hr>
	<h4>通配符配置action</h4>
	<a href="mul-save.action">save</a>
	<br>
	<a href="mul-delete.action">delete</a>
	<br>
	<a href="mul-update.action">update</a>
	<br>
	<a href="mul-query.action">query</a>
	<br>
	
	
	<%
		if(application.getAttribute("date") == null)
			application.setAttribute("date", new Date(System.currentTimeMillis()));
	%>
	
</body>
</html>