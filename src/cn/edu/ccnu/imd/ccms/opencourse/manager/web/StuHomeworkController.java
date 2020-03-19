/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.ccms.opencourse.manager.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.CourseService;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.StuHomework;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.TeaHomework;
import cn.edu.ccnu.imd.ccms.opencourse.manager.service.StuHomeworkService;

/**
 * 学生作业Controller
 * @author 姚进
 * @version 2016-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/oc/manager/stuHomework")
public class StuHomeworkController extends BaseController {

	@Autowired
	private StuHomeworkService stuHomeworkService;
	
	@Autowired
	private CourseService courseService;
	
	/**
	 * 查询学生所学的课程
	 * @param course
	 * @param model
	 * @return
	 */
	@RequiresPermissions("stuHomework:stuHomework:view")
	@RequestMapping(value = "courseList")
	public String courseList(Course course, Model model) {
		String loginName=UserUtils.getUser().getLoginName();
		Student student=new Student();
		student.setSno(loginName);
		course.setStudent(student);
		List<Course>list=courseService.findCourse(course);
		model.addAttribute("list", list);
		return "ccms/opencourse/manager/stuhomework/courseList";
	}
	
	/**
	 * 查询这门课的所有作业
	 * @param stuHomework
	 * @param model
	 * @return
	 */
	@RequiresPermissions("stuHomework:stuHomework:view")
	@RequestMapping(value = "homeworkList")
	public String homeworkList(StuHomework stuHomework, Model model) {
		List<TeaHomework>list=stuHomeworkService.findWorkByCno(stuHomework);
		model.addAttribute("list", list);
		return "ccms/opencourse/manager/stuhomework/homeworkList";
	}
	
	
	@RequiresPermissions("stuHomework:stuHomework:view")
	@RequestMapping(value = {"list", ""})
	public String list(StuHomework stuHomework, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StuHomework> page = stuHomeworkService.findPage(new Page<StuHomework>(request, response), stuHomework); 
		model.addAttribute("page", page);
		return "ccms/opencourse/manager/stuhomework/stuHomeworkList";
	}

	/**
	 * 跳转到提交作业的页面
	 * @param stuHomework
	 * @param model
	 * @return
	 */
	@RequiresPermissions("stuHomework:stuHomework:view")
	@RequestMapping(value = "form")
	public String form(StuHomework stuHomework, Model model) {
		Course course = courseService.get(stuHomework.getCno());
		stuHomework.setCourse(course);
		model.addAttribute("stuHomework", stuHomework);
		return "ccms/opencourse/manager/stuhomework/form";
	}

	/**
	 * 保存课程
	 * @param stuHomework
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("stuHomework:stuHomework:edit")
	@RequestMapping(value = "save")
	public String save(StuHomework stuHomework, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, stuHomework)){
			return form(stuHomework, model);
		}
		String sno = UserUtils.getUser().getLoginName();
		stuHomework.setSno(sno);
		stuHomeworkService.save(stuHomework);
		addMessage(redirectAttributes, "保存作业成功");
		return "redirect:"+Global.getAdminPath()+"/oc/manager/stuHomework/homeworkList/?sno="+stuHomework.getSno()+"&cno="+stuHomework.getCno()+"";
	}
	
	
	@RequiresPermissions("stuHomework:stuHomework:edit")
	@RequestMapping(value = "delete")
	public String delete(StuHomework stuHomework, RedirectAttributes redirectAttributes) {
		stuHomeworkService.delete(stuHomework);
		addMessage(redirectAttributes, "删除作业成功");
		return "redirect:"+Global.getAdminPath()+"/oc/manager/stuHomework?repage";
	}

}