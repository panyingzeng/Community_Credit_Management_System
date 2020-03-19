package cn.edu.ccnu.imd.ccms.opencourse.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.dao.CourseDao;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.common.BasicConstant;
import cn.edu.ccnu.imd.ccms.opencourse.manager.dao.TeaHomeworkDao;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.TeaHomework;

@Service
@Transactional(readOnly = true)
public class TeaHomeworkService extends CrudService<TeaHomeworkDao, TeaHomework> {

	@Autowired
	private CourseDao courseDao;

	/**
	 * 老师查询自己正在上的课程
	 * 
	 * @param course
	 * @return
	 */
	public List<Course> findCourse(Course course) {
		
		// 获取老师的职工号
		String tno = UserUtils.getUser().getLoginName();
		course.setTno(tno);
		
		//设置课程的状态为正在上的课
		String status = BasicConstant.COURSE_STATUS_ING;
		course.setStatus(status);

		List<Course> list = courseDao.findList(course);
		return list;
	}
	
	/**
	 * 查询已经发布的作业
	 * @param TeaHomework
	 * @return
	 */
	public List<TeaHomework> publishedHomework(TeaHomework  teaHomework) {
		
		return super.findList(teaHomework);
		}

	/**
	 * 添加，修改作业
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(TeaHomework  teaHomework) {
		super.save(teaHomework);
	}
	
	/**
	 * 删除作业
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(TeaHomework  teaHomework) {
		super.delete(teaHomework);
	}
}
