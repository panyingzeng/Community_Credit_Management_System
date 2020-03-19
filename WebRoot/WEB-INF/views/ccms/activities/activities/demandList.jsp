<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
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
		<li class="active">活动要求详情</li>
	</ul>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		 <tbody>
			<tr>
				<td style="width:100px">活动要求</td>
				<td>
					${activities.demand}
				</td>
			</tr>
		</tbody>
	</table>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
</body>
</html>