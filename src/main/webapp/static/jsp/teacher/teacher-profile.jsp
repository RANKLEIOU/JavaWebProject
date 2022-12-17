<%--
  Created by IntelliJ IDEA.
  User: ajv
  Date: 2022/11/17
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%@include file="teacher-head.jsp"%>
<body>
<%@include file="teacher-nav.jsp"%>
<div class="container-fluid">
	<div class="row">
		<%@include file="teacher-sidebar.jsp"%>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">
						<i class="glyphicon glyphicon-th"></i> 我的信息
					</h1>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table style="font-size: 25px" class="table">
							<p style="font-size: 50px">${info.name}</p>
							<br>

							<tr>
								<td>学号：${info.tno}</td>
								<td>班级：${info.clazz_name}</td>
							</tr>
							<tr>
								<td>电话：${info.telephone}</td>
								<td>邮箱：${info.email}</td>
							</tr>
							<tr>
								<td>家庭住址：${info.address}</td>
								<td></td>
							</tr>
							</p>
							<p>
								<button onclick="window.location='${pageContext.request.contextPath}/static/jsp/teacher/teacher-edit.jsp'" class="btn btn-primary">修改信息</button>
							</p>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
