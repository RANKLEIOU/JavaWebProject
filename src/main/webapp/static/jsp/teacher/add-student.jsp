<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ajv
  Date: 2022/11/22
  Time: 12:12
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
            <i class="glyphicon glyphicon-th"></i> 修改信息
          </h1>
        </div>
        <div class="panel-body">
          <form id="editForm" action="teacher" method="post" onsubmit="return commit()">
            <input type="hidden" name="method" value="add"/>
            <div class="modal-body">
              <div style="margin-bottom: 20px" class="input-group">
                <span class="input-group-addon" id="sizing-addon6">account</span>
                  <input type="text" id="account" name="name" class="form-control"
                         aria-describedby="sizing-addon6" placeholder="Account">
              </div>
              <div style="margin-bottom: 20px" class="input-group">
                <span class="input-group-addon" id="sizing-addon4">学号</span>
                <input type="text" id="sno" name="sno" class="form-control"
                       aria-describedby="sizing-addon4" placeholder="School Number">
              </div>
              <div style="margin-bottom: 20px" class="input-group">
                <span class="input-group-addon" id="sizing-addon7">性别</span>
                <select name="gender" class="form-control">
                    <option value="男">男</option>
                    <option value="女">女</option>
                    <option value="保密">保密</option>
                </select>
              </div>
              <div style="margin-bottom: 20px" class="input-group">
                <span class="input-group-addon" id="sizing-addon1">电话</span>
                <input type="text" id="telephone" name="telephone" class="form-control" placeholder="Telephone"
                       aria-describedby="sizing-addon1">
              </div>
              <div style="margin-bottom: 20px" class="input-group">
                <span class="input-group-addon" id="sizing-addon2">邮箱</span>
                <input type="text" name="email" class="form-control" placeholder="Email"
                       aria-describedby="sizing-addon2">
              </div>
              <div style="margin-bottom: 20px" class="input-group">
                <span class="input-group-addon" id="sizing-addon3">家庭住址</span>
                <input type="text" name="address" class="form-control" placeholder="Address"
                       aria-describedby="sizing-addon3">
              </div>
              <div style="margin-bottom: 20px" class="input-group">
                <span class="input-group-addon" id="sizing-addon8">自我介绍</span>
                <input type="text" name="introducation" class="form-control" placeholder="Introduction"
                       aria-describedby="sizing-addon8">
              </div>
              <div style="margin-bottom: 20px" class="input-group">
                <span class="input-group-addon" id="sizing-addon5">班级</span>
                <c:if test="${classList.data.data == null}">
                  <select name="clazz_name" class="form-control">
                    <option value="无班级">请选择班级</option>
                  </select>
                </c:if>
                <c:if test="${classList.data.data != null}">
                  <select name="clazz_name" class="form-control">
                    <option value="无班级">请选择班级</option>
                    <c:forEach items="${classList.data.data}" var="c">
                      <option value="${c.name}">${c.name}</option>
                    </c:forEach>

                  </select>
                </c:if>
              </div>
            </div>
            <div class="modal-footer">
              <button class="btn btn-primary">确认添加</button>
              <button type="button" onclick="window.location='student?keyword=teacherInfo'"
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
    let account = $("#account").val();
    let sno = $("#sno").val();
    let telephone = $("#telephone").val();
    if (account == ""){
      layer.msg("用户名不能为空！")
      return false;
    }
    if (sno == ""){
      layer.msg("学号不能为空！")
      return false;
    }
    if (telephone == "") {
      layer.msg("电话号码不能为空！")
      return false;
    }
    return true;
  }

</script>
</body>
</html>
