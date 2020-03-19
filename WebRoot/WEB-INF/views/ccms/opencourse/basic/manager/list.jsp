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
		<li class="active"><a href="${ctx}/oc/manager/manager/">秘书表列表</a></li>
	</ul>


	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<tbody>
			<c:forEach items="${page.list}" var="manager">
				<tr>
					<td>职工号:${manager.mno}</td>
				</tr>
				<tr>
					<td>姓名:${manager.mname}</td>
				</tr>
				<tr>
					<td>性别:${fns:getDictLabel(manager.tsex, 'sex', '')}</td>
				</tr>
				<tr>
					<td>职称:${fns:getDictLabel(manager.position, 'ccms_teacher', '')}
					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
</body>
</body>
</html>