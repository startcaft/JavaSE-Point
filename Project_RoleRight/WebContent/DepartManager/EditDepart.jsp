<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑部门信息</title>
<link href="<%=request.getContextPath()%>/css/main.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<form action="<%=request.getContextPath() %>/depart?action=add" method="post" class="elegant-aero">
		<h1>
			编辑用户 <span>为方便测试，这里只选用根节点作为父节点</span>
		</h1>
		<label> 
			<span>部门名称:</span> 
			<input id="name" type="text" name="name" placeholder="请输入部门名称" />
			<input id="appid" type="hidden" name="appid" value="1" />
		</label> 
		<label> 
			<span>上级部门 :</span>
			<select name="pid" id="pid">
				<option value="1">党政办</option>
				<option value="2">计生办</option>
			</select>
		</label>  
		<label> 
			<span>Message :</span> 
			<textarea id="desc"
				name="desc" placeholder="请输入对部门的描述信息"></textarea>
		</label> 
		
		<label>
			<span>&nbsp;</span>
			<input type="checkbox" id="isenable" checked="checked" name="isenable" value="true">是否启用
		</label>
		<label> 
			<span>&nbsp;</span> 
			<input type="submit" class="button" value="Send" />
		</label>
	</form>
</body>
</html>