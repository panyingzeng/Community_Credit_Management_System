<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选课管理</title>
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
		<li class="active"><a href="${ctx}/oc/manager/selectCourse/detail?cno=${course.cno}">课程详情</a></li>
	</ul>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="width:100px">教学内容</td>
				<td>
					${course.content}
				</td>
			</tr>
			<tr>
				<td>课程要求</td>
				<td>
					${course.demand}
				</td>
			</tr>
			<tr>
				<td>课程目标</td>
				<td>
					${course.target}
				</td>
			</tr>
		</tbody>
	</table>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
</body>
</html>



