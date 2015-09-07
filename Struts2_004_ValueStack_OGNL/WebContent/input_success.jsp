<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Input Result</title>
</head>
<body>
	<%
		System.out.println("真实的Request对象：" + request);
	%>
	
	<s:debug></s:debug>
	<h4>使用OGNL获取对象栈里对象的属性</h4>
	productName:<s:property value="[0].productName"/>
	<br>
	productDesc:<s:property value="productDesc"/>
	<br>
	productPrice:<s:property value="productPrice"/>
	<hr>
	
	<h4>使用EL获取作用域对象的属性</h4>
	${ sessionScope.product.productName }<br>
	${ requestScope.test.productName }
	<hr>
	
	<h4>使用OGNL获取作用域对象的属性</h4>
	<s:property value="#session.product.productName"/><br>
	<s:property value="#request.test.productName"/>
	<hr>
	
	<h4>使用OGNL调用静态字段和静态方法</h4>
	<s:property value="@java.lang.Math@PI"/><br>
	<s:property value="@java.lang.Math@cos(0)"/>
	<hr>
	
	<h4>使用OGNL调用Objectstack中对象的方法</h4>
	<s:property value="setProductName('zhangsan')"/><br>
	productName:<s:property value="[0].productName"/>
	<hr>
	
	<h4>使用OGNL访问数组类型的数据</h4>
	<%
		String[] names = new String[]{"AA","BB","CC"};
		request.setAttribute("names", names);
	%>
	Array's length:<s:property value="#attr.names.length"/><br>
	Array[2]:<s:property value="#attr.names[2]"/>
</body>
</html>