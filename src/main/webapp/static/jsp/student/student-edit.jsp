<%--
  Created by IntelliJ IDEA.
  User: ajv
  Date: 2022/11/21
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="student-head.jsp"%>
<body>
<%@include file="student-nav.jsp"%>
<div class="container-fluid">
	<div class="row">
		<%@include file="student-sidebar.jsp"%>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">
						<i class="glyphicon glyphicon-th"></i> 修改信息
					</h1>
				</div>
				<div class="panel-body">
					<form id="editForm" action="student" method="post" onsubmit="return commit()">
						<div class="modal-body">
							<div style="margin-bottom: 20px" class="input-group">
								<span class="input-group-addon" id="sizing-addon0">id</span>
								<input type="text" name="id" class="form-control" readonly
								       aria-describedby="sizing-addon0" value="${info.id}">
							</div>
							<div style="margin-bottom: 20px" class="input-group">
								<span class="input-group-addon" id="sizing-addon6">account</span>
								<input type="text" name="name" class="form-control" readonly
								       aria-describedby="sizing-addon6" value="${info.name}">
							</div>
							<div style="margin-bottom: 20px" class="input-group">
								<span class="input-group-addon" id="sizing-addon7">性别</span>
								<select name="gender" class="form-control">
									<c:if test="${info.gender == '男'}">
										<option value="男">男</option>
										<option value="女">女</option>
										<option value="保密">保密</option>
									</c:if>
									<c:if test="${info.gender == '女'}">
										<option value="女">女</option>
										<option value="男">男</option>
										<option value="保密">保密</option>
									</c:if>
									<c:if test="${info.gender == '保密'}">
										<option value="保密">保密</option>
										<option value="男">男</option>
										<option value="女">女</option>
									</c:if>
								</select>
							</div>
							<div style="margin-bottom: 20px" class="input-group">
								<span class="input-group-addon" id="sizing-addon1">电话</span>
								<input type="text" name="telephone" class="form-control" placeholder="Telephone"
								       aria-describedby="sizing-addon1" value="${info.telephone}">
							</div>
							<div style="margin-bottom: 20px" class="input-group">
								<span class="input-group-addon" id="sizing-addon2">邮箱</span>
								<input type="text" name="email" class="form-control" placeholder="Email"
								       aria-describedby="sizing-addon2" value="${info.email}">
							</div>
							<div style="margin-bottom: 20px" class="input-group">
								<span class="input-group-addon" id="sizing-addon3">家庭住址</span>
								<input type="text" name="address" class="form-control" placeholder="Address"
								       aria-describedby="sizing-addon3" value="${info.address}">
							</div>
							<div style="margin-bottom: 20px" class="input-group">
								<span class="input-group-addon" id="sizing-addon8">自我介绍</span>
								<input type="text" name="introducation" class="form-control" placeholder="Introduction"
								       aria-describedby="sizing-addon8" value="${info.introducation}">
							</div>
							<div style="margin-bottom: 20px" class="input-group">
								<span class="input-group-addon" id="sizing-addon4">密码</span>
								<input type="password" id="pwd" name="password" class="form-control" placeholder="password"
								       aria-describedby="sizing-addon4" value="${info.password}">
							</div>
							<div class="input-group">
								<span class="input-group-addon" id="sizing-addon5">再次确认密码</span>
								<input type="password" id="rePwd" class="form-control" placeholder="password"
								       aria-describedby="sizing-addon5" value="${info.password}">
							</div>
						</div>
						<div class="modal-footer">
							<button id="editBtn" class="btn btn-primary">确认修改</button>
							<button type="button" onclick="window.location='student?method=toEdit&account=${account}'"
							        class="btn btn-default">取消
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
		function commit() {
			let pwd = $("#pwd").val();
			let rePwd = $("#rePwd").val();
			if (pwd != rePwd) {
				layer.msg("两次输入的密码不一致，请重新输入！")
				return false;
			}
			return true;
		}

</script>
</body>
</html>
