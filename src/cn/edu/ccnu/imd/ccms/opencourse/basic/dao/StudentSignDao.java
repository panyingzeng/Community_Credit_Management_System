package cn.edu.ccnu.imd.ccms.opencourse.basic.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.StudentSign;

@MyBatisDao
public interface StudentSignDao extends CrudDao<StudentSign> {
	
	public int countSign(StudentSign studentSign);
	
	public List<StudentSign> query(StudentSign studentSign);
	
}