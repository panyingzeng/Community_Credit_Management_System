package cn.edu.ccnu.imd.ccms.opencourse.basic.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class Student extends DataEntity<Student> {

	private static final long serialVersionUID = 1L;

	private String sno; // 学号
	private String sname; // 姓名
	private String ssex; // 性别
	private String grade; // 年级
	private String major; // 专业

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public String getGrade() {
		return grade;
	}

	public String getMajor() {
		return major;
	}

	public String getSname() {
		return sname;
	}

	public String getSno() {
		return sno;
	}
	public String getSsex() {
		return ssex;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
}