/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.ccms.opencourse.manager.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;

/**
 * 作业表Entity
 * @author 潘英增
 * @version 2016-07-19
 */
public class TeaHomework extends DataEntity<TeaHomework> {
	
	private static final long serialVersionUID = 1L;
	private String tno;		// 职工号
	private String cno;		// 课程号
	private String status;		// 作业状态
	private String content;		// 作业描述
	private String path;	//作业资料的路径
	private Date startTime;	//作业开始时间
	private Date endTime;	//作业开始时间
	private Course course;
	
	public TeaHomework() {
		super();
	}

	public TeaHomework(String id){
		super(id);
	}

	@Length(min=0, max=32, message="课程号长度必须介于 0 和 32 之间")
	public String getCno() {
		return cno;
	}

	public String getContent() {
		return content;
	}

	public Course getCourse() {
		return course;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="startime不能为空")
	public Date getEndTime() {
		return endTime;
	}

	public String getPath() {
		return path;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="startime不能为空")
	public Date getStartTime() {
		return startTime;
	}

	@Length(min=0, max=8, message="作业状态长度必须介于 0 和 8 之间")
	public String getStatus() {
		return status;
	}

	@Length(min=0, max=32, message="学号长度必须介于 0 和 32 之间")
	public String getTno() {
		return tno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}
}