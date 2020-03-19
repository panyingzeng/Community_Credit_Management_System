package cn.edu.ccnu.imd.ccms.opencourse.manager.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.SelectCourse;

@MyBatisDao
public interface SelectCourseDao extends CrudDao<SelectCourse> {

	public List<SelectCourse> findAllCourse(SelectCourse selectCourse);
	
	public int countCourse(SelectCourse selectCourse);
}
