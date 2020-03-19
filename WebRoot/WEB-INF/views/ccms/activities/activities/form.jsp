<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理</title>
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
		<li><a href="${ctx}/oc/activities/activities/list">活动列表</a></li>
		<li class="active"><a href="${ctx}/oc/activities/activities/form?id=${activities.id}">活动<shiro:hasPermission name="activities:activities:edit">${not empty activities.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="activities:activities:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="activities" action="${ctx}/oc/activities/activities/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">活动类型：</label>
			<div class="controls">
				<form:input path="aid" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">活动名称：</label>
			<div class="controls">
				<form:input path="aname" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">活动要求详情：</label>
			<div class="controls">
				<form:textarea path="demand" htmlEscape="false"  rows="4" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="activities:activities:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>