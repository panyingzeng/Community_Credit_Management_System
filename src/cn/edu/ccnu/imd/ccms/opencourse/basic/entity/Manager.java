package cn.edu.ccnu.imd.ccms.opencourse.basic.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class Manager extends DataEntity<Manager>{

	private static final long serialVersionUID = 1L;
	
	private String mno; // 职工号
	private String mname; // 姓名
	private String tsex; // 性别
	private String position; // 职称
	
	
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getTsex() {
		return tsex;
	}
	public void setTsex(String tsex) {
		this.tsex = tsex;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
