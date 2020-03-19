<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"  %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>签到管理</title>
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
		<li class="active"><a href="javascript:void(0);">签到列表</a></li>
		<li><a href="${ctx}/oc/manager/sign/scourseList">课程列表</a></li>
		</ul>
		
	<form:form id="searchForm" modelAttribute="sign" action="${ctx}/oc/manager/sign/scourseList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程号</th>
				<th>课程名</th>
				<th>课程状态</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<shiro:hasPermission name="manager:sign:edit"><th>操作</th></shiro:hasPermission>
			
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sign">
			<tr>
				<td>
					${sign.course.cno}
			  </td>
				<td>
					${sign.course.cname}
				</td>
				<td>
					${sign.teacherSign.status}
				</td>
				<td>
				 <fmt:formatDate value="${sign.teacherSign.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</td>
				<td>
				 <fmt:formatDate value="${sign.teacherSign.stopTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					
				</td>
				
				
				<shiro:hasPermission name="manager:sign:edit"> <td>
				<c:if test="${sign.teacherSign.status==0}">
				<c:set var="nowDate" value="<%=new Date()%>"></c:set> 
				<c:choose>
				<c:when test="${sign.teacherSign.startTime<=nowDate && nowDate<=sign.teacherSign.stopTime}">
    				  <a href="${ctx}/oc/manager/sign/saveSign?sno=${sign.student.sno}&cno=${sign.course.cno}
    				  &tid=${sign.teacherSign.id}&course.cname=${sign.course.cname}">签到</a>
    				</c:when>
    				<c:otherwise>
    				       
    				</c:otherwise>
    			</c:choose>
				</c:if>
			 </td> </shiro:hasPermission>
				</tr>
				</c:forEach>
		</tbody>
	</table>
		
	<div class="pagination">${page}</div>
</body>
</html>
				