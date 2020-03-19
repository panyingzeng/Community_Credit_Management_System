<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>教师表管理</title>
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
		<li class="active"><a href="javascript:void(0);">教师表列表</a></li>
		<shiro:hasPermission name="teacher:teacher:edit"><li><a href="${ctx}/oc/teacher/teacher/tform/?tno=${teacher.tno}">信息修改</a></li></shiro:hasPermission>
	
	</ul>
    <sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	 <tbody>
	    <c:forEach items="${page.list}" var="teacher">
			<tr>
				<td style="width:100px">职工号</td>
				<td>
					${teacher.tno}
				</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td>
					${teacher.tname}
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					${fns:getDictLabel(teacher.tsex, 'sex', '')}
				</td>
			</tr>
			
			<tr>
				<td>职称</td>
				<td>
					${fns:getDictLabel(teacher.position, 'ccms_teacher', '')}
				</td>
			</tr>
		
			</c:forEach>
		</tbody>
	</table>
</body>
</html>