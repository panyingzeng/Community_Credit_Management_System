<%@ page  contentType="text/html;charset=UTF-8"  %>
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
		<li class="active"><a href="${ctx}/oc/course/course/squeryCourse">课程列表</a></li>
	</ul>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程号</th>
				<th>课程名</th>
				<th>授课对象</th>
				<th>课程学分</th>
				<th>授课时间</th>
				<th>学时</th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="course">
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
					${course.period}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
