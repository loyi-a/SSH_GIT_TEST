<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--导包 --%>
<%@page import="com.seiryo.entity.MyUser"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登陆成功后的界面</title>
	</head>
	<body>
		<%--拿到用户信息 --%>
		<% List<MyUser> userList = (List<MyUser>)request.getAttribute("userList"); %>
		<% for(MyUser user:userList){ %>
			<div>
			    <p>编号：<%= user.getUserId() %></p>
				<p>账号：<%= user.getUserName() %></p>
				<p>密码：<%= user.getUserPass() %></p>
				<p><a href="update.jsp">修改信息</a></p>
				<p><a href="delete.jsp">删除数据</a></p>
			</div>
		<% } %>
	</body>
</html>