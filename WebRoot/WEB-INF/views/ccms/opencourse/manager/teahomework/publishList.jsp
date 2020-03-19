<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>作业管理</title>
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
		<li class="active"><a href="${ctx}/oc/manager/homework/publishedHomework?cno=${teaHomework.cno}">发布列表</a></li>
		<shiro:hasPermission name="manager:homework:edit"><li><a href="${ctx}/oc/manager/homework/form?cno=${course.cno}&course.cname=${course.cname}">发布作业</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程名</th>
				<th>作业描述</th>
				<th>作业</th>
				<th>起始时间</th>
				<th>结束时间</th>
				<shiro:hasPermission name="manager:homework:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="teahomework">
			<tr>
				<td>
					${teahomework.course.cname}
				</td>
				<td>
					${teahomework.content}
				</td>
				<td>
				<c:choose>
						<c:when test="${teahomework.path!=null}">
							<a href="${teahomework.path}">作业附件</a>
						</c:when>
						<c:otherwise>
							<span>无附件</span>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${teahomework.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${teahomework.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="manager:homework:edit"><td>
    				<a href="${ctx}/oc/manager/homework/findStuWorkList?pid=${teahomework.id}">批改</a>
    				<a href="${ctx}/oc/manager/homework/editForm?id=${teahomework.id}">修改</a>
					<a href="${ctx}/oc/manager/homework/delete?id=${teahomework.id}" onclick="return confirmx('确认要删除该作业吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	<div class="pagination">${page}</div>
</body>
</html>