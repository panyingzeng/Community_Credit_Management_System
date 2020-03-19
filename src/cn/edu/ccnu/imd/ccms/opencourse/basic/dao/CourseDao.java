package cn.edu.ccnu.imd.ccms.opencourse.basic.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;

@MyBatisDao
public interface CourseDao extends CrudDao<Course>{
	public List<Course> findCourse(Course course);

}
