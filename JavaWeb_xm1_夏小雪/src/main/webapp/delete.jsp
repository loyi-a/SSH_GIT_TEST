<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>删除页面</title>
	</head>
	<body>
	   <%
	    String userId = request.getParameter("userId");
       %>
	        <p>确定要删除编号为 <%= userId %> 的用户吗？此操作无法撤销！</p >
	        
	        <!-- 确认删除  -->
	        <form action="/JavaWeb_xm1_夏小雪/UserServlet?pd=userDelete" method="post" >
	            <input type="hidden" name="userId" value="<%= userId %>" />
	            <input type="hidden" name="pd" value="userDelete" />
	            <input type="submit" value="确认删除" />
            </form>
	         <!-- 取消删除  -->
	          <form action="success.jsp" method="post">
	              <input type="submit" value="取消删除" />
	          </form>

		<% String massages = (String)request.getAttribute("massages"); %>
		<% if(massages != null){ %>
			<h4 style="color: red;"><%= massages %></h4>
		<% } %>
	</body>
</html>