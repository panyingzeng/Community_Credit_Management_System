<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动审核管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oc/activities/stuActivities/stuList">学生活动审核列表</a></li>
		<li class="active"><a href="${ctx}/oc/activities/stuActivities/form?id=${stuActivities.id}">学生活动表审核</a></li>
		</ul><br/>
	<form:form id="inputForm" modelAttribute="stuActivities" action="${ctx}/oc/activities/stuActivities/saveCheck" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="score"/>
		<form:hidden path="path"/>
		<form:hidden path="aid"/>
		<form:hidden path="sno"/>
		<sys:message content="${message}"/>		
	
		<div class="control-group">
			<label class="control-label">审核结果：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('stuActivities_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="activities:stuActivities:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>