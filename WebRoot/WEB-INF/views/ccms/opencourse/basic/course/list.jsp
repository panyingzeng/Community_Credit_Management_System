<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程表管理</title>
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
		<li class="active"><a href="${ctx}/oc/course/course/">课程列表</a></li>
		<shiro:hasPermission name="course:course:edit"><li><a href="${ctx}/oc/course/course/form">课程添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/oc/course/course/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>课程名：</label>
				<form:input path="cname" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>授课对象：</label>
				<form:select path="grade" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccms_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程号</th>
				<th>课程名</th>
				<th>职工号</th>
				<th>授课对象</th>
				<th>课程学分</th>
				<th>授课时间</th>
				<th>授课地点</th>
				<th>学时</th>
				<shiro:hasPermission name="course:course:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="course">
			<tr>
				<td>
					<a href="${ctx}/oc/course/course/detail?cno=${course.cno}">${course.cno}</a>
				</td>
				<td>
					${course.cname}
				</td>
				<td>
					${course.tno}
				</td>
				<td>
					${fns:getDictLabel(course.grade, 'ccms_grade', '')}
				</td>
				<td>
					${course.score}
				</td>
				<td>
					${course.time}
				</td>
				<td>
					${course.place}
				</td>
				<td>
					${course.period}
				</td>
				<shiro:hasPermission name="course:course:edit"><td>
    				<a href="${ctx}/oc/course/course/form?cno=${course.cno}">修改</a>
					<a href="${ctx}/oc/course/course/delete?id=${course.id}" onclick="return confirmx('确认要删除该课程吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>