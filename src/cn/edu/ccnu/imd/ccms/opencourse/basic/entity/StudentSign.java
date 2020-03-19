package cn.edu.ccnu.imd.ccms.opencourse.basic.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

public class StudentSign extends DataEntity<StudentSign> {
	
	private static final long serialVersionUID = 1L;
	private String sno;		// sno
	private String cno;		// cno
	private String status;		// stauts
	private String tid;		// tid
	private Date time;		// time
	private Student student;
	private TeacherSign teacherSign;
	private Course course;
	public StudentSign() {
		super();
	}

	public StudentSign(String id){
		super(id);
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public TeacherSign getTeacherSign() {
		return teacherSign;
	}

	public void setTeacherSign(TeacherSign teacherSign) {
		this.teacherSign = teacherSign;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}