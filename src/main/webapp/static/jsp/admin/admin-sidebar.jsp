<%--
  Created by IntelliJ IDEA.
  User: ajv
  Date: 2022/11/17
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="col-sm-3 col-md-2 sidebar">
	<div style="font-size: 16px" class="tree">
		<ul class="nav nav-pills nav-stacked">
			<li class="active"><a href="static/jsp/admin/main-index.jsp">
				<i class="glyphicon glyphicon-home"></i> 首页</a>
			</li>
			<li><a href="student?keyword=adminInfo"><i class="glyphicon glyphicon-file"></i> 学生信息</a></li>
			<li><a href="teacher?keyword=adminInfo"><i class="glyphicon glyphicon-file"></i> 教师信息</a></li>
			<li><a href="clazz"><i class="glyphicon glyphicon-file"></i> 班级信息</a></li>
			<li><a href="grade"><i class="glyphicon glyphicon-file"></i> 年级信息</a></li>
			<li><a href="admin"><i class="glyphicon glyphicon-file"></i> 管理员信息</a></li>
			<li class="a_post">
				<a href="admin?method=toEdit&account=${account}"><i class="glyphicon glyphicon-user"></i> 我的信息</a>
			</li>
		</ul>
	</div>
</div>