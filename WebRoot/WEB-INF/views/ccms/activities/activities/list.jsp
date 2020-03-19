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
		<li class="active"><a href="${ctx}/oc/activities/activities/">活动列表</a></li>
		<shiro:hasPermission name="activities:activities:edit"><li><a href="${ctx}/oc/activities/activities/form">活动添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>活动类型</th>
				<th>活动名称</th>
				
				<shiro:hasPermission name="activities:activities:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="activities">
			<tr>
			   <td>
					${activities.aid}
				</td>
				<td>
					<a href="${ctx}/oc/activities/activities/demand?id=${activities.id}">${activities.aname}</a>
				
				</td>
				<shiro:hasPermission name="activities:activities:edit"><td>
    				<a href="${ctx}/oc/activities/activities/form?id=${activities.id}">修改</a>
					<a href="${ctx}/oc/activities/activities/delete?id=${activities.id}" onclick="return confirmx('确认要删除该活动吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>