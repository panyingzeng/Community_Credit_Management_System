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
		<li class="active"><a href="#">作业提交</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="stuHomework" action="${ctx}/oc/manager/stuHomework/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="pid"/>
		<form:hidden path="cno"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">课程名：</label>
			<div class="controls">
				${stuHomework.course.cname}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件上传：</label>
			<div class="controls">
				<form:hidden path="path" htmlEscape="false" maxlength="128" class="input-xlarge"/>
				<sys:ckfinder input="path" type="files" uploadPath="/userFiles/ccms/stuHomework" selectMultiple="false"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="stuHomework:stuHomework:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>