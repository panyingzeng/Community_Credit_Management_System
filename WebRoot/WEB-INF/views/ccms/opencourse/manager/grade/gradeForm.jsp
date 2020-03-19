<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>成绩管理</title>
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
		
		<li class="active"><a href="${ctx}/oc/manager/grade/form?id=${grade.id}">成绩<shiro:hasPermission name="manager:grade:edit">${not empty grade.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="manager:grade:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="grade" action="${ctx}/oc/manager/grade/saveOne" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="student.sname" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
			<input type="hidden" name="course.cno" value="${grade.course.cno}"/>
			<input type="hidden" name="course.cname" value="${grade.course.cname}"/>
		<div class="control-group">
			<label class="control-label">成绩：</label>
			<div class="controls">
				<form:input path="grade" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="manager:grade:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>