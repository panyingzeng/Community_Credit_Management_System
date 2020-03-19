package cn.edu.ccnu.imd.ccms.opencourse.basic.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

public class TeacherSign extends DataEntity<TeacherSign> {
	
	private static final long serialVersionUID = 1L;
	private String tno;		// 职工号
	private String cno;		// 课程号
	private String status;		// 课程状态
	private Date  startTime;	// 开始时间
	private Date  stopTime;		// 结束时间
	private Course course;
	
	
	public TeacherSign() {
		super();
	}

	public TeacherSign(String id){
		super(id);
	}

	@Length(min=0, max=32, message="职工号长度必须介于 0 和 32 之间")
	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}
	
	@Length(min=0, max=32, message="课程号长度必须介于 0 和 32 之间")
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
	
	
	
}