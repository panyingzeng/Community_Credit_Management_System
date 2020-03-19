package cn.edu.ccnu.imd.ccms.opencourse.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.dao.CourseDao;
import cn.edu.ccnu.imd.ccms.opencourse.basic.dao.StudentDao;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;
import cn.edu.ccnu.imd.ccms.opencourse.common.BasicConstant;
import cn.edu.ccnu.imd.ccms.opencourse.manager.dao.SelectCourseDao;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.SelectCourse;

@Service
@Transactional(readOnly = true)
public class SelectCourseService extends CrudService<SelectCourseDao, SelectCourse> {

	@Autowired
	private SelectCourseDao selectCourseDao;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private StudentDao studentDao;

	/**
	 * 查询每门课程的所有学生信息
	 * @param selectCourse
	 * @return
	 */
	public List<SelectCourse> getAllStudent(SelectCourse selectCourse) {

		Page<SelectCourse> pageSelect = new Page<SelectCourse>();
		Page<SelectCourse> page = super.findPage(pageSelect, selectCourse);
		List<SelectCourse> list = page.getList();
		return list;
	}

	/**
	 * 老师查询自己教的课
	 * 
	 * @param selectCourse
	 * @return
	 */
	public List<Course> getAllCourse(Course course) {

		// 获取老师的职工号
		String tno = UserUtils.getUser().getLoginName();
		course.setTno(tno);

		List<Course> list = courseDao.findList(course);

		return list;
	}

	/**
	 * 学生查询选课列表
	 * 
	 * @param selectCourse
	 * @return
	 */
	public List<Course> findAllCourse(SelectCourse selectCourse) {
		// 获取当前登录的用户名,并获取年纪的信息
		String sno = UserUtils.getUser().getLoginName();
		Student student = new Student();
		student.setSno(sno);
		System.out.println(student);
		student = studentDao.getBySno(student);

		//如果不是学生
		if(student==null){
					List<Course> list = new ArrayList<Course>();
					list.add(new Course());
					return list;
				}
				
		String grade = student.getGrade();// 获取年级
		// 查询所有的课程
		Course course = new Course();
		course.setStatus(BasicConstant.COURSE_STATUS_SELECT);
		course.setGrade(grade);
		List<Course> listCourse = courseDao.findList(course);

		// 查询，该用户所选的所有课程
		selectCourse.setStudent(student);
		selectCourse.setCourse(course);
		List<SelectCourse> listSelect = selectCourseDao.findAllCourse(selectCourse);

		for (SelectCourse selectCourse2 : listSelect) {
			for (Course course2 : listCourse) {
				if (course2.getCno().equals(selectCourse2.getCourse().getCno())) {
					List<Student> listStudent = new ArrayList<Student>();
					listStudent.add(selectCourse2.getStudent());
					course2.setStudenList(listStudent);
				}
			}
		}
		return listCourse;
	}

	/**
	 * 选课
	 */
	@Transactional(readOnly = false)
	public boolean saveEntity(SelectCourse selectCourse) {

		String openCourseMax = Global.getConfig("courseMaxNumber");
		int nOpenCourseMax = Integer.parseInt(openCourseMax);

		// 查询状态为可选的课程
		String courseStatus = BasicConstant.COURSE_STATUS_SELECT;
		Course course = selectCourse.getCourse();
		course.setStatus(courseStatus);
		selectCourse.setCourse(course);

		int count = selectCourseDao.countCourse(selectCourse);
		if (count >= nOpenCourseMax) {
			return false;
		}

		super.save(selectCourse);
		return true;
	}

	/**
	 * 退课
	 * 
	 * @param selectCourse
	 */
	@Transactional(readOnly = false)
	public void dele(SelectCourse selectCourse) {
		super.delete(selectCourse);
	}

	
	/**
	 * 老师查询本门课的学生
	 */
	
	@Override
	@Transactional(readOnly = false)
	public List<SelectCourse> findList( SelectCourse selectCourse){
		
		return super.findList(selectCourse);
		
	}
	
}
