package cn.edu.ccnu.imd.ccms.activities.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;

public class StuActivities extends DataEntity<StuActivities>{

	private static final long serialVersionUID = 1L;

	private String aid;
	private String sno;
	private String path;
	private String status;
	private String score;
	private Student student;
   
	public StuActivities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StuActivities(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	
}
