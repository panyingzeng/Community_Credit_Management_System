package cn.edu.ccnu.imd.ccms.opencourse.manager.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.CourseService;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.SelectCourse;
import cn.edu.ccnu.imd.ccms.opencourse.manager.service.SelectCourseService;

@Controller
@RequestMapping(value = "${adminPath}/oc/manager/selectCourse")
public class SelectCourseController extends BaseController {

	@Autowired
	private SelectCourseService selectCourseService;

	@Autowired
	private CourseService courseService;

	/**
	 * 跳转到添加学生页面的方法
	 * @param selectCourse
	 * @return
	 */
	@RequiresPermissions("manager:selectCourse:edit")
	@RequestMapping(value =  "forwordSave")
	public String forwordSave(SelectCourse selectCourse,Model model) {
		
		selectCourse.setCourse(courseService.get(selectCourse.getCourse())); 

		model.addAttribute("selectCourse",selectCourse );
		return "ccms/opencourse/manager/checkCourse/form";
	}
	
	/**
	 * 添加学生
	 * @param selectCourse
	 * @return
	 */
	@RequiresPermissions("manager:selectCourse:edit")
	@RequestMapping(value =  "saveStudent")
	public String saveStudent(SelectCourse selectCourse) {
		selectCourseService.saveEntity(selectCourse);
		return "redirect:" + Global.getAdminPath() + "/oc/manager/selectCourse/listTeaCourse/?repage";
	}
	/**
	 * 踢出学生的功能
	 * @param selectCourse
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:selectCourse:edit")
	@RequestMapping(value =  "denyStudent")
	public String denyStudent(SelectCourse selectCourse) {
		selectCourseService.dele(selectCourse);
		return "redirect:" + Global.getAdminPath() + "/oc/manager/selectCourse/listTeaCourse/?repage";
	}
	/**
	 * 查询每门课程的所有学生信息
	 * @param selectCourse
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:selectCourse:view")
	@RequestMapping(value =  "listSudent")
	public String listSudent(Course course,SelectCourse selectCourse, Model model) {
		
		selectCourse.setCourse(course);
		List<SelectCourse> list = selectCourseService.getAllStudent(selectCourse);
		model.addAttribute("list", list);
		return "ccms/opencourse/manager/checkCourse/listStudent";
	}
	/**
	 * 老师查询教的全部课程
	 * @param selectCourse
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:selectCourse:view")
	@RequestMapping(value =  "listTeaCourse")
	public String listTeaCourse(Course course, Model model) {

		List<Course> list = selectCourseService.getAllCourse(course);
		model.addAttribute("list", list);
		return "ccms/opencourse/manager/checkCourse/list";
	}
	/**
	 * 学生查询选课的全部课程
	 * 
	 * @param selectCourse
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:selectCourse:view")
	@RequestMapping(value = { "list", "" })
	public String list(SelectCourse selectCourse, Model model) {

		List<Course> list = selectCourseService.findAllCourse(selectCourse);
		model.addAttribute("list", list);
		return "ccms/opencourse/manager/selectCourse/list";
	}

	/**
	 * 根据课程号的id，查询课程的目标，要求
	 */
	@RequiresPermissions("manager:selectCourse:view")
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(String cno, Model model) {
		Course course = courseService.get(cno);
		model.addAttribute("course", course);
		return "ccms/opencourse/manager/selectCourse/detail";
	}

	/**
	 * 选课
	 * 
	 * @param studentCourse
	 * @return
	 */
	@RequiresPermissions("manager:selectCourse:edit")
	@RequestMapping(value = "save")
	public String save(SelectCourse selectCourse, RedirectAttributes redirectAttributes) {

		// 获取当前登录的用户名,并获取年纪的信息
		String sno = UserUtils.getUser().getLoginName();
		Student student = new Student();
		student.setSno(sno);

		selectCourse.setStudent(student);
		String openCourseMax = Global.getConfig("courseMaxNumber");
		boolean b = selectCourseService.saveEntity(selectCourse);
		if (!b) {
			addMessage(redirectAttributes, "选课数量多于"+openCourseMax+"门");
		}
		return "redirect:" + Global.getAdminPath() + "/oc/manager/selectCourse/?repage";
	}

	/**
	 * 退课
	 * 
	 * @param studentCourse
	 * @return
	 */
	@RequiresPermissions("manager:selectCourse:edit")
	@RequestMapping(value = "delete")
	public String delete(SelectCourse selectCourse, RedirectAttributes redirectAttributes) {

		// 获取当前登录的用户名,并获取年纪的信息
		String sno = UserUtils.getUser().getLoginName();
		Student student = new Student();
		student.setSno(sno);

		selectCourse.setStudent(student);

		selectCourseService.dele(selectCourse);

		return "redirect:" + Global.getAdminPath() + "/oc/manager/selectCourse/?repage";
	}

}
