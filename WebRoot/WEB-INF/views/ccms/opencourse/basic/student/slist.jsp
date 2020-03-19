<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学生表管理</title>
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
		<li class="active"><a href="javascript:void(0);">学生列表</a></li>
		<shiro:hasPermission name="student:student:edit"><li><a href="${ctx}/oc/student/student/sform/?sno=${student.sno}">信息修改</a></li></shiro:hasPermission>
	
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		 <tbody>
	   <c:forEach items="${page.list}" var="student">
			<tr>
				<td style="width:100px">学号</td>
				<td>
					${student.sno}
				</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td>
					${student.sname}
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					${fns:getDictLabel(student.ssex, 'sex', '')}
				</td>
			</tr>
			
			<tr>
				<td>年级</td>
				<td>
					${fns:getDictLabel(student.grade, 'ccms_grade', '')}
				</td>
			</tr>
		
		    <tr>
				<td>专业</td>
				<td>
					${fns:getDictLabel(student.major, 'ccms_major', '')}
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>