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
		<li class="active"><a href="#">发布列表</a></li>
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
				<shiro:hasPermission name="stuHomework:stuHomework:edit"><th>操作</th></shiro:hasPermission>
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
				<shiro:hasPermission name="stuHomework:stuHomework:edit">
					<c:choose>
						<c:when test="${teahomework.status==1}">
							<td style="color:blue">已提交</td>
						</c:when>
						<c:otherwise>
							<td><a style="color:red" href="${ctx}/oc/manager/stuHomework/form?cno=${teahomework.cno}&pid=${teahomework.id}">未提交</a></td>
						</c:otherwise>
					</c:choose>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	<div class="pagination">${page}</div>
</body>
</html>