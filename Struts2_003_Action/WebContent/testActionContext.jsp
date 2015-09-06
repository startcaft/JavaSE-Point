<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ActionContext类</title>
</head>
<body>
	application : ${ applicationScope.applicationKey }
	<hr>
	
	session : ${ sessionScope.sessionKey }
	<hr>
	
	request : ${ requestScope.requestKey }
	<hr>
	
	requestParameters : ${ parameters.name }
	<hr>
	
	age : ${ parameters.age }
</body>
</html>