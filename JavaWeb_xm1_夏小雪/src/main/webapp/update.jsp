<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>修改信息的界面</title>
	</head>
	<body>
	   <form action="/JavaWeb_xm1_夏小雪/UserServlet?pd=userUpdate" method="post">
			<h4>修改信息</h4>
			请输入要修改的用户编号：<input type="text" name="userId" /><br />
			请输入修改后的账号：<input type="text" name="userName" /><br />
			请输入修改后的密码：<input type="password" name="userPass" /><br />
			<input type="submit" value="提交" />
		</form>
		<% String massages = (String)request.getAttribute("massages"); %>
		<% if(massages != null){ %>
			<h4 style="color: black;"><%= massages %></h4>
		<% } %>
	</body>
</html>