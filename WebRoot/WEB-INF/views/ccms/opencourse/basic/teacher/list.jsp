<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oc/teacher/teacher/">教师表列表</a></li>
		<shiro:hasPermission name="teacher:teacher:edit"><li><a href="${ctx}/oc/teacher/teacher/form">教师表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="teacher" action="${ctx}/oc/teacher/teacher/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>职工号：</label>
				<form:input path="tno" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="tname" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>职工号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>职称</th>
				<shiro:hasPermission name="teacher:teacher:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="teacher">
			<tr>
				<td>
					${teacher.tno}
				</td>
				<td>
					${teacher.tname}
				</td>
				<td>
					${fns:getDictLabel(teacher.tsex, 'sex', '')}
				</td>
				<td>
					${fns:getDictLabel(teacher.position, 'ccms_teacher', '')}
				</td>
				<shiro:hasPermission name="teacher:teacher:edit"><td>
    				<a href="${ctx}/oc/teacher/teacher/form?id=${teacher.id}">修改</a>
					<a href="${ctx}/oc/teacher/teacher/delete?id=${teacher.id}" onclick="return confirmx('确认要删除该教师表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>