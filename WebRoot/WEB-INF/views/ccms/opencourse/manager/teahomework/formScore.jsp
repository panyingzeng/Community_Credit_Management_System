<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>作业管理</title>
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
		<li class="active"><a href="#">登记分数</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="stuHomework" action="${ctx}/oc/manager/homework/saveScore" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="pid"/>
		<form:hidden path="sno"/>
		<form:hidden path="cno"/>
		<form:hidden path="path"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">作业附件：</label>
			<div class="controls">
				<a href="${stuHomework.path}">作业附件</a>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分数：</label>
			<div class="controls">
				<form:input path="score" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="manager:homework:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>