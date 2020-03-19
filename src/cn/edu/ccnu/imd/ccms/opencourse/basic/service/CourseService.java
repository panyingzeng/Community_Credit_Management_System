package cn.edu.ccnu.imd.ccms.opencourse.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.dao.CourseDao;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;

@Service
@Transactional(readOnly = false)
public class CourseService extends CrudService<CourseDao, Course>{
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public Course get(String cno) {
		return super.get(cno);
	}
	/**
	 * 获取课程号
	 */
	@Override
	public Course get(Course course) {
		return super.get(course);
	}

	@Override
	public Page<Course> findPage(Page<Course> page, Course course) {
		String  LoginName =UserUtils.getUser().getLoginName();
		course.setTno(LoginName);
		return super.findPage(page, course);
		
	}
	
	/**
	 * 查询所有学生的成绩
	 * @param page
	 * @param course
	 * @return
	 */
	public Page<Course> findAllCourse(Page<Course> page, Course course) {
		return super.findPage(page, course);
		
	}
	
	/**
	 * 学生查询所学的课程
	 */
	public List<Course> findCourse(Course course){
		return courseDao.findCourse(course);
	}
	
	@Override
	public void save(Course course) {
		super.save(course);
	}
	
	
	@Override
	public void delete(Course course) {
		super.delete(course);
	}
	
}
