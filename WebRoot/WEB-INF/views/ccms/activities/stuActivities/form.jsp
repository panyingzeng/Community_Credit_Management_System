<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
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
		<li><a href="${ctx}/oc/activities/stuActivities/">学生活动列表</a></li>
		<li class="active"><a href="${ctx}/oc/activities/stuActivities/form?id=${stuActivities.id}">学生活动表<shiro:hasPermission name="activities:stuActivities:edit">${not empty stuActivities.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="activities:stuActivities:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="stuActivities" action="${ctx}/oc/activities/stuActivities/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
	
		<div class="control-group">
			<label class="control-label">学号：</label>
			<div class="controls">
				<form:input path="sno" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动类型：</label>
			<div class="controls">
				<form:select path="aid" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccms_aid')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件上传：</label>
			<div class="controls">
				<form:hidden path="path" htmlEscape="false" maxlength="300" class="input-xlarge"/>
			<!--  	<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			-->	<sys:ckfinder input="path" type="files" uploadPath="/userFiles/ccms/activities" selectMultiple="false"/>
			
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">活动学分：</label>
			<div class="controls">
				<form:input path="score" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="activities:stuActivities:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>