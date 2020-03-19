<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选课管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});

		function denyCourse(cno,sno,info){
			var flag =  confirmx(info,function(){
				
				$("#course_cno").val(cno);
				$("#student_sno").val(sno);
			
				$("#sForm").submit();
				
			});
		}
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a  href="${ctx}/oc/manager/selectCourse/listSudent?cno=${selectCourse.course.cno}">学生列表</a></li>
		<shiro:hasPermission name="manager:selectCourse:edit"><li><a href="javascript:void(0);" onclick="$('#fForm').submit();">添加学生</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>年级</th>
				<th>专业</th>
				<th>性别</th>
				<shiro:hasPermission name="manager:selectCourse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="selectCourse" >
			<tr>
				<td>
					${selectCourse.student.sno}
				</td>
				<td>
					${selectCourse.student.sname}
				</td>
				<td>
					${fns:getDictLabel(selectCourse.student.grade, 'ccms_grade', '')}
				</td>
				<td>
					${fns:getDictLabel(selectCourse.student.major, 'ccms_major', '')}
				</td>
				<td>
					${fns:getDictLabel(selectCourse.student.ssex, 'sex', '')}
				</td>
				<shiro:hasPermission name="manager:selectCourse:edit">
				<td>
					<a style="color:red" href="javascript:void(0);" onclick="denyCourse('${selectCourse.course.cno}','${selectCourse.student.sno}','确定拒绝这个学生吗？');">拒绝</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<form action="${ctx}/oc/manager/selectCourse/denyStudent" method="post" style="dispaly:none;" id="sForm">
		<input type="hidden" name="course.cno" id="course_cno"/>
		<input type="hidden" name="student.sno" id="student_sno"/>
	</form>
	<form action="${ctx}/oc/manager/selectCourse/forwordSave" method="post" style="dispaly:none;" id="fForm">
		<input type="hidden" name="course.cno" id="fForm_course_cno" value="${selectCourse.course.cno}"/>
	</form>
</body>
</html>



