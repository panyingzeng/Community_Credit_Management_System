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
		<li class="active"><a href="#">作业列表</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>年级</th>
				<th>作业</th>
				<th>分数</th>
				<shiro:hasPermission name="manager:homework:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var ="stuHomework">
				<tr>
					<td>${stuHomework.student.sname}</td>				
					<td>${fns:getDictLabel(stuHomework.student.grade, 'ccms_grade', '')}</td>	
					<td>
						<c:choose>
							<c:when test="${stuHomework.path!=null}">
								<a href="${stuHomework.path}">作业附件</a>
							</c:when>
							<c:otherwise>
								<span>无附件</span>
							</c:otherwise>
						</c:choose>
					</td>	
					<td>
							${stuHomework.score}
					</td>		
					<td>
						<c:choose>
							<c:when test="${stuHomework.score==0.0}">
								<a  style="color: red" href="${ctx}/oc/manager/homework/formScore?id=${stuHomework.id}">未评分</a>
							</c:when>
							<c:otherwise>
								<a  style="color: red" href="${ctx}/oc/manager/homework/formScore?id=${stuHomework.id}">已评分</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
		</c:forEach>	
		</tbody>
	</table>
	<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	<div class="pagination">${page}</div>
</body>
</html>