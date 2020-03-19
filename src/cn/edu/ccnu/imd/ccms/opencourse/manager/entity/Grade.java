package cn.edu.ccnu.imd.ccms.opencourse.manager.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;

public class Grade  extends DataEntity<Grade> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cno;		// 课程号
	private String sno;		// 学号
	private String grade;   // 成绩
	private Date time;		// 录入时间
	private Course course;
	private Student student;
	
	
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}