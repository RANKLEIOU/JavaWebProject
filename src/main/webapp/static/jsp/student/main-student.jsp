<%--
  Created by IntelliJ IDEA.
  User: ajv
  Date: 2022/10/20
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="student-head.jsp" %>
<script type="text/javascript">
	$(function () {
		$('.nav').find('a').each(function () {
			$(this).click(function () {
				$(this).parent().addClass('active')
				$(this).parent().siblings().removeClass("active")
			})
		})

		//调用分页函数，生成分页导航栏
		initPagination()
	})

	function initPagination() {
		//获取总页数
		let total = ${studentList.data.countSize}
		//配置分页属性
		const properties = {
			num_edge_entries: 3,                               //边缘页数
			num_display_entries: 5,                            //主体页数
			items_per_page:${studentList.data.limitSize},  //每页显示的数据量
			current_page:${studentList.data.currPage-1},   //当前页
			prev_text: "上一页",
			next_text: "下一页",
			callback: pageSelectCallback,
		}
		$("#Pagination").pagination(total, properties)
	}

	/**
	 * 用户点击导航栏后进行页面跳转
	 * @param pageIndex 由pagination传入，从0开始的页码
	 * @param jQuery jQuery对象
	 */
	function pageSelectCallback(pageIndex, jQuery) {
		//通过计算得到当前页码
		var pageNum = pageIndex + 1;
		//跳转页面
		window.location.href = "student?page=" + pageNum + "&sno=${param.sno}" + "&sName=${param.sName}";
		//超链接默认会跳转页面，返回false取消默认行为
		return false;
	}
</script>
<body>
<%@include file="student-nav.jsp" %>
<div class="container-fluid">
	<div class="row">
		<%@include file="student-sidebar.jsp" %>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-th"></i> 学生信息
					</h3>
				</div>
				<div class="panel-body">
					<form action="student" method="get" class="form-inline" role="form" style="float: left;">
						<div class="form-group has-feedback">
							<div class="input-group">
								<div class="input-group-addon">班级</div>
								<select name="clazz_name" class="form-control">
									<option value="">请选择</option>
									<c:forEach items="${classList}" var="c">
										<option value="${c.name}">${c.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group has-feedback">
							<div class="input-group">
								<div class="input-group-addon">姓名</div>
								<input name="sName" class="form-control has-success" type="text"
								       placeholder="请输入姓名">
							</div>
						</div>
						<button type="submit" class="btn btn-warning">
							<i class="glyphicon glyphicon-search"></i> 查询
						</button>
					</form>
					<br>
					<hr style="clear: both;">
					<div class="table-responsive">
						<table class="table  table-bordered">
							<thead>
							<tr>
								<th width="30">#</th>
								<th>学号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>邮箱</th>
								<th>电话</th>
								<th>籍贯</th>
								<th>班级</th>
								<th>介绍</th>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty studentList.data.data }">
								<tr>
									<td colspan="10" align="center">抱歉！没有查询到您要的数据！</td>
								</tr>
							</c:if>
							<c:if test="${!empty studentList.data.data }">
								<c:forEach items="${studentList.data.data }" var="s" varStatus="myStatus">
									<tr>
										<td>${s.getId()}</td>
										<td>${s.getSno()}</td>
										<td>${s.getName()}</td>
										<td>${s.getGender()}</td>
										<td>${s.getEmail()}</td>
										<td>${s.getTelephone()}</td>
										<td>${s.getAddress()}</td>
										<td>${s.getClazz_name()}</td>
										<td>${s.getIntroducation()}</td>
									</tr>
								</c:forEach>
							</c:if>
							</tbody>
							<tfoot>
							<tr>
								<td colspan="10" align="center">
									<div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
								</td>
							</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
