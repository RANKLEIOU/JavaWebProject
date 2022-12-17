<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ajv
  Date: 2022/11/21
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%@include file="admin-head.jsp" %>
<body>
<%@include file="admin-nav.jsp" %>
<div class="container-fluid">
	<div class="row">
		<%@include file="admin-sidebar.jsp" %>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">
						<i class="glyphicon glyphicon-th"></i> 修改信息
					</h1>
				</div>
				<div class="panel-body">
					<form id="editForm" action="admin" method="post" onsubmit="return commit()">
						<input type="hidden" name="method" value="update"/>
						<input type="hidden" name="status" value="${status}"/>
						<div class="modal-body">
							
							<div style="margin-bottom: 20px" class="input-group">
								<span class="input-group-addon" id="sizing-addon0">id</span>
								<input type="text" name="id" class="form-control" readonly
								       aria-describedby="sizing-addon0" value="${info.id}">
							</div>
							
							<c:if test="${status=='isStudent'}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon9">学号</span>
									<input type="text" name="sno" class="form-control"
									       aria-describedby="sizing-addon6" value="${info.sno}">
								</div>
							</c:if>
							<c:if test="${status == 'isTeacher'}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon10">教师编号</span>
									<input type="text" name="tno" class="form-control"
									       aria-describedby="sizing-addon6" value="${info.tno}">
								</div>
							</c:if>
							
							<c:if test="${status=='isStudent' || status == 'isTeacher' || status == 'isAdmin' || status == null || status == ''}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon6">account</span>
									<input type="text" name="name" class="form-control"
									       aria-describedby="sizing-addon6" value="${info.name}">
								</div>
							</c:if>
							
							<c:if test="${status == 'isClass' || status == 'isGrade'}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon13">名称</span>
									<input type="text" name="name" class="form-control"
									       aria-describedby="sizing-addon6" placeholder="请输入名称" value="${info.name}">
								</div>
							</c:if>
							
							<c:if test="${status=='isStudent' || status == 'isTeacher' || status == 'isAdmin' || status == null || status == ''}">
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
							</c:if>
							
							<c:if test="${status == 'isClass'}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon11">班主任姓名</span>
									<input type="text" name="headmaster" class="form-control"
									       aria-describedby="sizing-addon4" placeholder="Headmaster Name"
									       value="${info.headmaster}">
								</div>
							</c:if>
							
							<c:if test="${status == 'isGrade'}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon12">年级主任姓名</span>
									<input type="text" name="manager" class="form-control"
									       aria-describedby="sizing-addon4" placeholder="Manager Name"
									       value="${info.manager}">
								</div>
							</c:if>
							
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
							
							<c:if test="${status=='isStudent' || status == 'isTeacher' || status == 'isAdmin' || status == null || status == ''}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon3">家庭住址</span>
									<input type="text" name="address" class="form-control" placeholder="Address"
									       aria-describedby="sizing-addon3" value="${info.address}">
								</div>
							</c:if>
							
							<c:if test="${status == 'isStudent' || status == 'isClass' || status == 'isGrade'}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon8">介绍</span>
									<input type="text" name="introducation" class="form-control" placeholder="Introduction"
									       aria-describedby="sizing-addon8" value="${info.introducation}"/>
								</div>
							</c:if>
							
							<c:if test="${status == 'isStudent' || status == 'isTeacher'}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon14">班级</span>
									<select name="clazzName" class="form-control">
										<option value="${info.clazz_name}">${info.clazz_name}</option>
										<option value="" disabled>--------</option>
										<c:forEach items="${classList}" var="c">
											<option value="${c.name}">${c.name}</option>
										</c:forEach>
									</select>
								</div>
							</c:if>
							
							<c:if test="${status == 'isClass'}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon15">年级</span>
									<select name="gradeName" class="form-control">
										<option value="${info.name}">${info.name}</option>
										<option value="" disabled>--------</option>
										<c:forEach items="${gradeList}" var="c">
											<option value="${c.name}">${c.name}</option>
										</c:forEach>
									</select>
								</div>
							</c:if>
							
							<c:if test="${status=='isStudent' || status == 'isTeacher' || status == 'isAdmin' || status == null || status == ''}">
								<div style="margin-bottom: 20px" class="input-group">
									<span class="input-group-addon" id="sizing-addon4">密码</span>
									<input type="password" id="pwd" name="password" class="form-control"
									       placeholder="password"
									       aria-describedby="sizing-addon4" value="${info.password}">
								</div>
								<div class="input-group">
									<span class="input-group-addon" id="sizing-addon5">再次确认密码</span>
									<input type="password" id="rePwd" class="form-control" placeholder="password"
									       aria-describedby="sizing-addon5" value="${info.password}">
								</div>
							</c:if>
						</div>
						<div class="modal-footer">
							<button class="btn btn-primary">确认修改</button>
							<c:if test="${status == 'isStudent'}">
								<button type="button" onclick="window.location='student?keyword=adminInfo'"
								        class="btn btn-default">取消
								</button>
							</c:if>
							<c:if test="${status == 'isTeacher'}">
								<button type="button" onclick="window.location='teacher?keyword=adminInfo'"
								        class="btn btn-default">取消
								</button>
							</c:if>
							<c:if test="${status == 'isAdmin'}">
								<button type="button" onclick="window.location='admin'"
								        class="btn btn-default">取消
								</button>
							</c:if>
							<c:if test="${status == 'isClass'}">
								<button type="button" onclick="window.location='clazz'"
								        class="btn btn-default">取消
								</button>
							</c:if>
							<c:if test="${status == 'isGrade'}">
								<button type="button" onclick="window.location='grade'"
								        class="btn btn-default">取消
								</button>
							</c:if>
							<c:if test="${status == null || status == ''}">
								<button type="button" onclick="window.location='admin?method=toEdit&account=${account}'"
								        class="btn btn-default">取消
								</button>
							</c:if>
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
