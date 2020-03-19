<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>登分管理</title>
	<meta name="decorator" content="default"/>
	 
</head>
<body>
	<form action="${ctx}/oc/manager/grade/saveList">
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0);"><!-- ${selectCourse.course.cname} -->登记分数</a></li>
		<li><a href="${ctx}/oc/manager/grade/courseList">成绩管理</a></li>
		</ul>
		<input type="hidden" name="cno" value="${selectCourse.course.cno}"/>
		<input type="hidden" name="cname"	value="${selectCourse.course.cname}"/>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>成绩</th>
          </tr>
		</thead>
		<tbody>

	
		<c:forEach items="${list}" var="selectCourse" varStatus="vs">
			<tr>
				<td>
					${selectCourse.student.sno}
					<input type="hidden" name="gradeList[${vs.index}].cno" value="${selectCourse.course.cno}"/>
					<input type="hidden" name="gradeList[${vs.index}].sno"	value="${selectCourse.student.sno}"/>	
				</td>
				<td>
					${selectCourse.student.sname}
				</td>
				<td>
				
				<c:choose>
					   <c:when test="${not empty  map[selectCourse.student.sno]}">  
					           <input type="text" name="gradeList[${vs.index}].grade" value="${map[selectCourse.student.sno].grade}" maxlength="200" class="input-mini" required="required"/>
					           <input type="hidden" name="gradeList[${vs.index}].id" value="${map[selectCourse.student.sno].id}"/>   
					   </c:when>
				       <c:otherwise> 
				           <input type="text" name="gradeList[${vs.index}].grade"  maxlength="200" class="input-xlarge required" />	
				       </c:otherwise>
  			 	</c:choose>
				
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<div class="pagination">
	   <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;
	   <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	</form>
</body>
</html>