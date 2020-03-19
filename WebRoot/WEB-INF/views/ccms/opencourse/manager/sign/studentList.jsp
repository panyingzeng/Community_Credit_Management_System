<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学生签到管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0);">学生签到列表</a></li>
		<li><a
			href="${ctx}/oc/manager/sign/signList?cno=${studentSign.cno}">签到列表</a></li>
		<li><a
			href="${ctx}/oc/manager/sign/signForm?cno=${studentSign.cno}&tid=${studentSign.tid}">添加签到</a></li>

	</ul>

	<form:form id="searchForm" modelAttribute="studentSign"
		action="${ctx}/oc/manager/sign/studentList/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>学号：</label> <form:input path="student.sno"
					htmlEscape="false" maxlength="32" class="input-medium" /> <input
				type="hidden" name="tid" value="${studentSign.tid}" /></li>
			<li><label>姓名：</label> <form:input path="student.sname"
					htmlEscape="false" maxlength="32" class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>

	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>签到时间</th>
				<th>签到状态</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="studentSign">
				<tr>
				<tr>
					<td>${studentSign.student.sno}</td>
					<td>${studentSign.student.sname}</td>

					<td><fmt:formatDate value="${studentSign.time}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${fns:getDictLabel(studentSign.status, 'ccms_status', '')}

					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>