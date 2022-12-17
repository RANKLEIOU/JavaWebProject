<%--
  Created by IntelliJ IDEA.
  User: ajv
  Date: 2022/11/13
  Time: 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
	<title>error</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
	<script type="javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.6.1.js"></script>
	<script type="javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<div>
				<a class="navbar-brand" href="#" style="font-size: 32px;">教务管理系统</a>
			</div>
		</div>
	</div>
</nav>
<div class="container">
	<h2 class="form-signin-heading" style="text-align: center;">
		<i class="glyphicon glyphicon-log-in"></i> 教务管理系统消息
	</h2>
	<h3 style="text-align: center;">${msg }</h3>
	<button style="width: 150px;margin: 50px auto 0px auto;" class="btn btn-lg btn-success btn-block" onclick="window.location = 'index.jsp'">点击去登录</button>
</div>
</body>
</html>
