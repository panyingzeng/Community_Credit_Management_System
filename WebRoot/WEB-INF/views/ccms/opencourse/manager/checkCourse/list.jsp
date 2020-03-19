<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选课管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oc/manager/selectCourse/listTeaCourse">课程列表</a></li>
	</ul>
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
				<shiro:hasPermission name="manager:selectCourse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="course" >
			<tr>
				<td>
					<a href="${ctx}/oc/manager/selectCourse/detail?cno=${course.cno}">${course.cno}</a>
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
				<shiro:hasPermission name="manager:selectCourse:edit">
				<td>
					<a href="${ctx}/oc/manager/selectCourse/listSudent?cno=${course.cno}">查看</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>



