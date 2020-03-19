<%@ page contentType="text/html;charset=UTF-8" %>
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
		<li class="active"><a href="javascript:void(0);">学生成绩列表</a></li>
	
		<shiro:hasPermission name="manager:grade:edit" >
			<li><a href="${ctx}/oc/manager/grade/addForm?cno=${grade.course.cno}&course.cname=${grade.course.cname}">添加学生成绩</a>
		</shiro:hasPermission>
	
	</ul>
	
	<form:form id="searchForm" modelAttribute="grade" action="${ctx}/oc/manager/grade/studentGrade" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<ul class="ul-form">
			<li><label>学号：</label>
					<form:input path="sno" htmlEscape="false" maxlength="32" class="input-medium"/>
					<input type="hidden" name="cno" value="${grade.course.cno}"/>
			</li>
			<li><label>姓名：</label>
					<form:input path="student.sname" htmlEscape="false" maxlength="32" class="input-medium"/>
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
				<th>年级</th>
				<th>课程名</th>
				<th>成绩</th>
				<shiro:hasPermission name="manager:grade:edit"><th>操作</th></shiro:hasPermission>
				</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="grade">
			<tr>
				<td>
				${grade.sno}
				</td>
				<td>
					${grade.student.sname}
				</td>
				<td>
				${fns:getDictLabel(grade.student.grade, 'ccms_grade', '')}
					
				</td>
				<td>
					${grade.course.cname}
				</td>
				<td>
					${grade.grade}
				</td>
				<shiro:hasPermission name="manager:grade:edit"><td>
    				<a href="${ctx}/oc/manager/grade/form?id=${grade.id}&cno=${grade.course.cno}&course.cname=${grade.course.cname}">修改</a>
    				</td></shiro:hasPermission>
			</tr>
			
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>