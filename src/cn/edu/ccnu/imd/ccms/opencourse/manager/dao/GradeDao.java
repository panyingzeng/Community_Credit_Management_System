package cn.edu.ccnu.imd.ccms.opencourse.manager.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.Grade;

@MyBatisDao
public interface GradeDao extends CrudDao<Grade> {
	public int queryStudent(Grade grade);
	
}