package cn.edu.ccnu.imd.ccms.opencourse.basic.web;

 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.CourseService;

@Controller
@RequestMapping(value = "${adminPath}/oc/course/course")
public class CourseController extends BaseController{
 
	@Autowired
	 private CourseService courseService;
	
	
	@ModelAttribute
	public Course get(@RequestParam(required=false) String cno) {
		Course entity = null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(cno)){
			entity = courseService.get(cno);
		}
		if (entity == null){
			entity = new Course();
		}
		return entity;
	}
	

	/**
	 * 根据课程号的id，查询课程的目标，要求
	 */
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(String cno, Model model) {
		Course course = courseService.get(cno);
		model.addAttribute("course", course);
		return "ccms/opencourse/basic/course/detail";
	}

	/**
	 * 查询信息中的
	 * 学生查询自己的课表
	 */
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "squeryCourse")
	public String squeryCourse(Course course ,Model model ){
		String loginName=UserUtils.getUser().getLoginName();
		Student student=new Student();
		course.setStudent(student);
		course.getStudent().setSno(loginName);
		List<Course>list=courseService.findCourse(course);
    	model.addAttribute("list", list);
		model.addAttribute("course", course);
		return "ccms/opencourse/basic/course/squeryCourseList";
		
	}
	
	
	/**
	 * 查询信息中的
	 * 老师查询自己的课表
	 */
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "tqueryCourse")
	public String courseList(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		String loginName= UserUtils.getUser().getLoginName();
		course.setTno(loginName);
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		model.addAttribute("course", course);
		return "ccms/opencourse/basic/course/tqueryCourseList";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = {"list", ""})
	public String list(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Course> page = courseService.findAllCourse(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		return "ccms/opencourse/basic/course/list";
	}

	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "form")
	public String form(Course course, Model model) {
		model.addAttribute("course", course);
		return "ccms/opencourse/basic/course/form";
	}

	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "save")
	public String save(Course course, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, course)){
			return form(course, model);
		}
		courseService.save(course);
		addMessage(redirectAttributes, "保存课程表成功");
		return "redirect:"+Global.getAdminPath()+"/oc/course/course/?repage";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "delete")
	public String delete(Course course, RedirectAttributes redirectAttributes) {
		courseService.delete(course);
		addMessage(redirectAttributes, "删除课程表成功");
		return "redirect:"+Global.getAdminPath()+"/oc/course/course/?repage";
	}
	
}
