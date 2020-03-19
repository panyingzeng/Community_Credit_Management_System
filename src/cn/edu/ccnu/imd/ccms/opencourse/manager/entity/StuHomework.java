/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.ccms.opencourse.manager.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;

/**
 * 学生作业Entity
 * @author 姚进
 * @version 2016-10-11
 */
public class StuHomework extends DataEntity<StuHomework> {
	
	private static final long serialVersionUID = 1L;
	private String sno;		// 学号
	private String cno;		// 课程号
	private String status;		// 作业状态
	private String path;		// 作业文件路径
	private Date time;		// 提交时间
	private float score;  //分数
	private String pid;//教师作业表的id
	private Course course;
	private Student student;
	
	public StuHomework() {
		super();
	}

	public StuHomework(String id){
		super(id);
	}

	@Length(min=1, max=32, message="课程号长度必须介于 1 和 32 之间")
	public String getCno() {
		return cno;
	}

	public Course getCourse() {
		return course;
	}

	@Length(min=1, max=300, message="请选择文件")
	public String getPath() {
		return path;
	}

	public String getPid() {
		return pid;
	}

	public float getScore() {
		return score;
	}

	@Length(min=1, max=32, message="学号长度必须介于 1 和 32 之间")
	public String getSno() {
		return sno;
	}

	@Length(min=0, max=8, message="作业状态长度必须介于 0 和 8 之间")
	public String getStatus() {
		return status;
	}

	public Student getStudent() {
		return student;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return time;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
	public void setSno(String sno) {
		this.sno = sno;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}