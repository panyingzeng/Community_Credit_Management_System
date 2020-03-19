package cn.edu.ccnu.imd.ccms.opencourse.manager.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Manager;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;

public class GradeCount extends DataEntity<GradeCount>{

	
	private static final long serialVersionUID = 1L;
	
	 private Grade grade;
	 private Student student;
	 private Course course;
	 private float count;
     private Manager manager;
	 
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	
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

	public float getCount() {
		return count;
	}

	public void setCount(float count) {
		this.count = count;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	 
	 
	

}
