<%--
  Created by IntelliJ IDEA.
  User: ajv
  Date: 2022/11/1
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>登录</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui-v2.7.6/css/layui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.6.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/bootstrap-3.4.1/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/layui-v2.7.6/layui.js"></script>
	<style>
		*{
			margin: 0;
			padding: 0;
		}
		body{
			width: 100%;
			height: 100%;
			background: url("static/image/2.jpg") no-repeat;
			background-size: 100% 100%;
		}
		.container{
			width: 600px;
			height: 400px;
			margin: auto;
			padding: 50px 0 0 50px;
			position: relative;
			top: 25%;
			box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
			background-color: rgba(0,0,0,.5);
			color: #ffffff;
		}
		.container input[id="account"],input[id="password"]{
			width:350px;
			color: #ffffff;
			background: rgba(57, 61, 82, 0);
		}
		#login{
			color: #ffffff;
			margin-top: 20px;
			padding: 8px 120px;
			border: 2px solid #4FA1D9;
			background: rgba(57, 61, 82, 0);
		}
		#login:hover{
			color: white !important;
			background: rgba(79, 161, 217,.5);
		}
	</style>
</head>
<body>
<div class="container">
	<form class="form-horizontal" onsubmit="return false">
		<h1 style="text-align: center;padding-bottom:15px;margin-left:-50px">系统登录</h1>
		<div class="form-group">
			<label for="account" class="col-sm-2 control-label">账号：</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="account" placeholder="Account">
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">密码：</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="password" placeholder="Password">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label>
						<input type="radio" name="user" value="student">学生
					</label>
					<label>
						<input type="radio" name="user" value="teacher">老师
					</label>
					<label>
						<input type="radio" name="user" value="admin">管理员
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="login" class="btn btn-default">登录</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">

	$(function (){

		$('#login').click(function (){
			let account = $('#account').val();
			let password = $('#password').val();
			let type = $('input[name="user"]:checked').val();

			if (account === ""){
				layer.msg("账号不能为空！");
				return false;
			}
			if (password === ""){
				layer.msg("密码不能为空！")
				return false;
			}
			if (type === undefined){
				layer.msg("必须选择一个登录身份！")
				return false;
			}

			$.ajax({
				url:'login',
				type:'POST',
				data:{
					'account':account,
					'password':password,
					'type':type
				},
				success:function (res){
					if (res === "true"){

						if("admin"===type){
							window.location = "static/jsp/admin/main-index.jsp";
						}else if ("teacher"===type){
							window.location = "static/jsp/teacher/teacher-index.jsp";
						}else if ("student"===type){
							window.location = "static/jsp/student/student-index.jsp";
						}
					}else{
						window.location = "error.jsp";
					}
				}
			})
		})
	})
</script>
</body>
</html>
