package cn.edu.ccnu.imd.ccms.opencourse.manager.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;

public class SelectCourse extends DataEntity<SelectCourse>{

	private static final long serialVersionUID = 1L;
	
	private Student student;
	private Course course;

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

}
