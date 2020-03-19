<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动表管理</title>
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
		<li class="active"><a href="${ctx}/oc/activities/stuActivities/">学生活动列表</a></li>
		<shiro:hasPermission name="activities:stuActivities:edit"><li><a href="${ctx}/oc/activities/stuActivities/form">学生活动添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>学号</th>
			    <th>姓名</th>
			    <th>活动类型</th>
				<th>附件</th>
				<th>状态</th>
				<th>活动学分</th>
				<shiro:hasPermission name="activities:stuActivities:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="stuActivities">
			<tr>
			    <td>
					${stuActivities.student.sno}
				</td>
				<td>
					${stuActivities.student.sname}
				</td>
				<td>
					${stuActivities.aid}
				</td>
				
				<td>
						<c:choose>
							<c:when test="${stuActivities.path!=null}">
								<a href="${stuActivities.path}">证书附件</a>
							</c:when>
							<c:otherwise>
								<span>无附件</span>
							</c:otherwise>
						</c:choose>
					</td>	
				<!-- 
				<td>
					${stuActivities.path}
				</td>
				 -->
				<td>
				${fns:getDictLabel(stuActivities.status, 'stuActivities_status', '')}
				   
				</td>
				<td>
					${stuActivities.score}
				</td>
				<shiro:hasPermission name="activities:stuActivities:edit"><td>
    				<a href="${ctx}/oc/activities/stuActivities/form?id=${stuActivities.id}">修改</a>
					<a href="${ctx}/oc/activities/stuActivities/delete?id=${stuActivities.id}" onclick="return confirmx('确认要删除该活动吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>