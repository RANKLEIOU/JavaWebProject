<%--
  Created by IntelliJ IDEA.
  User: ajv
  Date: 2022/11/17
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<div>
				<a class="navbar-brand" style="font-size: 32px;" href="static/jsp/teacher/teacher-index.jsp">教务信息管理系统</a>
			</div>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li style="padding-top: 8px;">
					<div class="btn-group">
						<button type="button"
						        class="btn btn-default btn-success dropdown-toggle"
						        data-toggle="dropdown">
							<i class="glyphicon glyphicon-user"></i>
							${account } <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="teacher?method=toEdit&account=${account}"><i class="glyphicon glyphicon-cog"></i>
								个人设置</a></li>
							<li class="divider"></li>
							<li><a href="login?account=${sessionScope.get("account")}"><i
									class="glyphicon glyphicon-off"></i> 退出系统</a></li>
						</ul>
					</div>
				</li>
			</ul>
			<span style="color: #ffffff;font-size: 25px" class="navbar-form navbar-right">
				欢迎您！
			</span>
		</div>
	</div>
</nav>