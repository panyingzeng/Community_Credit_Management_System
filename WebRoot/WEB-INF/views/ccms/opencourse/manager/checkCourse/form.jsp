<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>选课管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {

				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
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
		<li class="active"><a href="#">添加学生</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="selectCourse"
		action="${ctx}/oc/manager/selectCourse/saveStudent" method="post"
		class="form-horizontal">
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">课程名：</label>
			<div class="controls">
				${selectCourse.course.cname}
				<form:hidden path="course.cno" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学号：</label>
			<div class="controls">
				<form:input path="student.sno" htmlEscape="false" maxlength="32"
					class="input-xlarge required"
					onchange="loadStudentInfo(this.value);" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="student.sname" htmlEscape="false" maxlength="32"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="manager:selectCourse:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>