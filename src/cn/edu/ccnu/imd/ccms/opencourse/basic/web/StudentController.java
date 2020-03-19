/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.ccms.opencourse.basic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.StudentService;

/**
 * 学生表Controller
 * @author 潘英增
 * @version 2016-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/oc/student/student")
public class StudentController extends BaseController {

	@Autowired
	private StudentService studentService;
	
	@ModelAttribute
	public Student get(@RequestParam(required=false) String id) {
		Student entity = null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(id)){
			entity = studentService.get(id);
		}
		if (entity == null){
			entity = new Student();
		}
		return entity;
	}
	
	@RequiresPermissions("student:student:view")
	@RequestMapping(value = {"list", ""})
	public String list(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Student> page = studentService.findPage(new Page<Student>(request, response), student); 
		model.addAttribute("page", page);
		return "ccms/opencourse/basic/student/list";
	}
	

	@RequiresPermissions("student:student:view")
	@RequestMapping(value = "form")
	public String form(Student student, Model model) {
		model.addAttribute("student", student);
		return "ccms/opencourse/basic/student/form";
	}

	@RequiresPermissions("student:student:edit")
	@RequestMapping(value = "save")
	public String save(Student student, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, student)){
			return form(student, model);
		}
		studentService.save(student);
		addMessage(redirectAttributes, "保存学生表成功");
		return "redirect:"+Global.getAdminPath()+"/oc/student/student/list?repage";
	}
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping(value = "delete")
	public String delete(Student student, RedirectAttributes redirectAttributes) {
		studentService.delete(student);
		addMessage(redirectAttributes, "删除学生表成功");
		return "redirect:"+Global.getAdminPath()+"/oc/student/student/list?repage";
	}
	
	/**
	 * 学生个人信息查询
	 *
	 */
	@RequiresPermissions("student:student:view")
	@RequestMapping(value = {"slist"})
	public String slist(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		 String LoginName=UserUtils.getUser().getLoginName();
		 student.setSno(LoginName);
		Page<Student> page = studentService.findPage(new Page<Student>(request, response), student); 
		model.addAttribute("page", page);
		return "ccms/opencourse/basic/student/slist";
	}
	
	@RequiresPermissions("student:student:view")
	@RequestMapping(value = "sform")
	public String sform(Student student, Model model) {
		student=studentService.getBysno(student);
		model.addAttribute("student", student);
		return "ccms/opencourse/basic/student/sform";
	}

	@RequiresPermissions("student:student:edit")
	@RequestMapping(value = "ssave")
	public String ssave(Student student, Model model, RedirectAttributes redirectAttributes) {
		studentService.save(student);
		addMessage(redirectAttributes, "修改成功");
		return "redirect:"+Global.getAdminPath()+"/oc/student/student/slist?repage";
	}
	

	
	@RequiresPermissions("student:student:view")
	@ResponseBody
	@RequestMapping(value = "studentInfo")
	public Student studentInfo(Student student, RedirectAttributes redirectAttributes) {
		student = studentService.getBysno(student);
		return studentService.getBysno(student);
	}


}