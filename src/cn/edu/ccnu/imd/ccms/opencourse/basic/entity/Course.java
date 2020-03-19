package cn.edu.ccnu.imd.ccms.opencourse.basic.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.Grade;

public class Course extends DataEntity<Course> {

	private static final long serialVersionUID = 1L;

	private String cno; // 课程号
	private String cname; // 课程名
	private String tno; // 职工号
	private String grade; // 授课对象
	private String score; // 课程学分
	private String time; // 授课时间
	private String place; // 授课地点
	private String period; // 学时

	private String content; // 教学内容

	private String demand; // 课程要求
	private String target; // 课程目标
	private String status; // 状态
	private Teacher teacher; // 老师信息
	private Student student; //学生信息
	private List<Student> studentList;
	private List<Grade> gradeList = Lists.newArrayList();	// 课程成绩

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public String getCname() {
		return cname;
	}

	public String getCno() {
		return cno;
	}

	public String getContent() {
		return content;
	}

	public String getDemand() {
		return demand;
	}

	public String getGrade() {
		return grade;
	}

	public String getPeriod() {
		return period;
	}

	public String getPlace() {
		return place;
	}


	public String getScore() {
		return score;
	}

	public String getStatus() {
		return status;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public String getTarget() {
		return target;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public String getTime() {
		return time;
	}

	public String getTno() {
		return tno;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setPeriod(String period) {
		this.period = period;
	}


	public void setPlace(String place) {
		this.place = place;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStudenList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;

	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

 

	
}
