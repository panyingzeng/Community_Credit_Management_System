<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生表管理</title>
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
		<li class="active"><a href="${ctx}/oc/student/student/">学生表列表</a></li>
		<shiro:hasPermission name="student:student:edit"><li><a href="${ctx}/oc/student/student/form">学生表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="student" action="${ctx}/oc/student/student/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学号：</label>
				<form:input path="sno" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="sname" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>年级：</label>
				<form:select path="grade" class="input-xlarge">
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
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年级</th>
				<th>专业</th>
				<shiro:hasPermission name="student:student:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="student">
			<tr>
				<td>		
				${student.sno}
				</td>
				<td>
					${student.sname}
				</td>
				<td>
					${fns:getDictLabel(student.ssex, 'sex', '')}
				</td>
				<td>
					${fns:getDictLabel(student.grade, 'ccms_grade', '')}
				</td>
				<td>
					${fns:getDictLabel(student.major, 'ccms_major', '')}
				</td>
				<shiro:hasPermission name="student:student:edit"><td>
    				<a href="${ctx}/oc/student/student/form?id=${student.id}">修改</a>
					<a href="${ctx}/oc/student/student/delete?id=${student.id}" onclick="return confirmx('确认要删除该学生表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>