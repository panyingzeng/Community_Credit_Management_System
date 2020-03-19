<%@ page contentType="text/html;charset=UTF-8"  %>
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
		<li class="active"><a href="#">学生已签到列表</a></li>
		<li class="active"><a href="${ctx}/oc/manager/sign/scourseList/">课程列表</a></li>
		</ul>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程号</th>
				<th>课程名</th>
				<th>签到状态</th>
				<th>时间</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="studentSign">
			<tr>
				<td>
					${studentSign.course.cno}
			  </td>
				<td>
					${studentSign.course.cname}
				</td>
				
				<td>
					${studentSign.status}
				</td>
				<td>
				 <fmt:formatDate value="${studentSign.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
					
				</td>
				</tr>
				</c:forEach>
		</tbody>
	</table>
</body>
</html>
				