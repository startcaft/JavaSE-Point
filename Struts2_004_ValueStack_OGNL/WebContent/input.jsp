<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Input</title>
</head>
<body>
	<s:debug></s:debug>
	<!--  
	exceptionStack:<s:property value="exceptionStack"/><br>
	-->
	exception:<s:property value="exception"/> --- ${ exception } <br>
	exception.message:<s:property value="exception.message"/> --- ${ exception.message } <br>
	<hr>
	<form action="product/product_save.action" method="post">
		ProductName:<input type="text" name="productName" />
		<br><br>
		ProductDesc:<input type="text" name="productDesc" />
		<br><br>
		ProductPrice:<input type="text" name="productPrice" />
		<br><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>