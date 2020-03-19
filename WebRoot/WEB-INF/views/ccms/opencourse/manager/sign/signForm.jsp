<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>签到管理</title>
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
		
		
		var loadStudentInfo = function(v) {
			$.getJSON(ctx + "/oc/student/student/studentInfo?sno=" + v, function(data) {
				if (data) {
					$("input[name='student.sname']").val(data.sname);
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oc/manager/sign/saveOne">签到<shiro:hasPermission name="manager:sign:edit">${not empty studentSign.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="manager:sign:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="studentSign" action="${ctx}/oc/manager/sign/saveOne" method="post" class="form-horizontal">
		<input type="hidden" name="cno" value="${studentSign.cno}"/>
		<input type="hidden" name="tid" value="${studentSign.tid}"/>
		<sys:message content="${message}"/>	
		
		<div class="control-group">
			<label class="control-label">学号：</label>
			<div class="controls">
				<form:input path="sno" htmlEscape="false" maxlength="32"
				class="input-xlarge required" onchange="loadStudentInfo(this.value);" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="student.sname" htmlEscape="false" maxlength="32"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">签到状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccms_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-xlarge required" />
					<span class="help-inline"><font color="red">*</font></span>
				</form:select>
			</div>
		</div>
		<!--  
		<div class="control-group">
			<label class="control-label">签到状态</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		-->
		<div class="form-actions">
		    
			<shiro:hasPermission name="manager:sign:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>