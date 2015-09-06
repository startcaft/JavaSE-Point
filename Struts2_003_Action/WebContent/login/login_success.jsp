<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计在线人数Demo</title>
</head>
<body>
	Welcome: ${ sessionScope.username }
	<hr>
	当前在线人数: ${ applicationScope.count }
	<hr>
	<a href="user-logout">logout</a>
</body>
</html>