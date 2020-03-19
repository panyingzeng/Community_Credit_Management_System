package cn.edu.ccnu.imd.ccms.opencourse.basic.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class Teacher extends DataEntity<Teacher> {

	private static final long serialVersionUID = 1L;

	private String tno; // 职工号
	private String tname; // 姓名
	private String tsex; // 性别
	private String position; // 职称

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

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
}