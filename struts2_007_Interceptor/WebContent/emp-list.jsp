<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employees</title>
</head>
<body>
	<s:form action="emp-save" method="post">
		<s:textfield name="firstName" label="FirstName"></s:textfield>
		<s:textfield name="lastName" label="LastName"></s:textfield>
		<s:textfield name="email" label="Email"></s:textfield>
		<s:submit value="添加"></s:submit>
	</s:form>
	
	<hr>
	
	<table cellpadding="10" cellspacing="0" border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Email</th>
				<th>Delete</th>
				<th>Edit</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="#request.emps">
				<tr>
					<td>${ employeeId }</td>
					<td>${ firstName }</td>
					<td>${ lastName }</td>
					<td>${ email }</td>
					<td><a href="emp-delete.action?employeeId=${ employeeId }">Delete</a></td>
					<td><a href="emp-edit.action?employeeId=${ employeeId }">Edit</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>