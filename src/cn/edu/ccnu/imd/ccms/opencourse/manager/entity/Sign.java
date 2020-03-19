package cn.edu.ccnu.imd.ccms.opencourse.manager.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.StudentSign;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.TeacherSign;

public class Sign extends DataEntity<Sign> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Student student;
	private Course course;
	private StudentSign studentSign;
	private TeacherSign teacherSign;
   
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public StudentSign getStudentSign() {
		return studentSign;
	}
	public void setStudentSign(StudentSign studentSign) {
		this.studentSign = studentSign;
	}
	public TeacherSign getTeacherSign() {
		return teacherSign;
	}
	public void setTeacherSign(TeacherSign teacherSign) {
		this.teacherSign = teacherSign;
	}
	
}
