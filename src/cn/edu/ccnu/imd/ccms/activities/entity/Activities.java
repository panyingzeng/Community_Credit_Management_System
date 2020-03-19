package cn.edu.ccnu.imd.ccms.activities.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class Activities extends DataEntity <Activities>{

	private static final long serialVersionUID = 1L;
	
	private String aid;
	private String aname;
	
	private String demand;
	
	public Activities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Activities(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	
	
	

}
