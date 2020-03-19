
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>成绩管理</title>
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
		<li class="active"><a href="javascript:void(0);">成绩管理</a></li>
		</ul>
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/oc/manager/grade/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程号</th>
				<th>课程名</th>
				<th>授课对象</th>
				<th>课程学分</th>
				<th>授课时间</th>
				<th>授课地点</th>
				<th>学时</th>
				<shiro:hasPermission name="manager:grade:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="course">
			<tr>
				<td>
					${course.cno}
			  </td>
				<td>
					${course.cname}
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
				
				<shiro:hasPermission name="manager:grade:edit"><td>
    				<a href="${ctx}/oc/manager/grade/gradeList?course.cno=${course.cno}&course.cname=${course.cname}">登分</a>
					</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>