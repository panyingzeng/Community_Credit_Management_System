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
		<li class="active"><a href="javascript:void(0);">成绩列表</a></li>
	</ul>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程名</th>
				<th>姓名</th>
				<th>成绩</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="grade">
			<tr>
				<td>
				${grade.course.cname}
				</td>
				<td>
					${grade.student.sname}
				</td>
				<td>
					${grade.grade}
				</td>
				</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>