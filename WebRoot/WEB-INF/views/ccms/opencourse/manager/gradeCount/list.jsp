<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学分表管理</title>
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
		<li class="active"><a href="${ctx}/oc/manager/gradeCount/">学分表列表</a></li>
			</ul>
	<form:form id="searchForm" modelAttribute="gradeCount" action="${ctx}/oc/manager/gradeCount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	
	<ul class="ul-form">
			
			<li><label>年级：</label>
				<form:select path="student.grade" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccms_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>专业：</label>
			<form:select path="student.major" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccms_major')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>学号：</label>
				<form:input path="student.sno" htmlEscape="false" maxlength="32" class="input-medium"/>
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
				<th>专业</th>
				<th>年级</th>
				<th>课程总学分</th>
				</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gradeCount">
			<tr>
				<td>
					${gradeCount.student.sno}
				</td>
				<td>
					${gradeCount.student.sname}
				</td>
				
				<td>${fns:getDictLabel(gradeCount.student.ssex, 'sex', '')}
				</td>
					
				<td>
					${fns:getDictLabel(gradeCount.student.major, 'ccms_major', '')}
				</td>	
				<td>
				   ${fns:getDictLabel(gradeCount.student.grade, 'ccms_grade', '')}
				</td>
				<td>
					${gradeCount.count}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>