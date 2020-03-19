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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Teacher;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.TeacherService;

/**
 * 教师表Controller
 * @author 潘英增
 * @version 2016-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/oc/teacher/teacher")
public class TeacherController extends BaseController {

	@Autowired
	private TeacherService teacherService;
	
	@ModelAttribute
	public Teacher get(@RequestParam(required=false) String id) {
		Teacher entity = null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(id)){
			entity = teacherService.get(id);
		}
		if (entity == null){
			entity = new Teacher();
		}
		return entity;
	}
	
	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = {"list", ""})
	public String list(Teacher teacher, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Teacher> page = teacherService.findPage(new Page<Teacher>(request, response), teacher); 
		model.addAttribute("page", page);
		return "ccms/opencourse/basic/teacher/list";
	}
	
	
	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = "form")
	public String form(Teacher teacher, Model model) {
		model.addAttribute("teacher", teacher);
		return "ccms/opencourse/basic/teacher/form";
	}

	@RequiresPermissions("teacher:teacher:edit")
	@RequestMapping(value = "save")
	public String save(Teacher teacher, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, teacher)){
			return form(teacher, model);
		}
		teacherService.save(teacher);
		addMessage(redirectAttributes, "保存教师表成功");
		return "redirect:"+Global.getAdminPath()+"/oc/teacher/teacher/list?repage";
	}
	
	@RequiresPermissions("teacher:teacher:edit")
	@RequestMapping(value = "delete")
	public String delete(Teacher teacher, RedirectAttributes redirectAttributes) {
		teacherService.delete(teacher);
		addMessage(redirectAttributes, "删除教师表成功");
		return "redirect:"+Global.getAdminPath()+"/oc/teacher/teacher/list?repage";
	}

	
	/**
	 * 老师个人信息查询
	 * 
	 */
	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = "tlist")
	public String tlist(Teacher teacher, HttpServletRequest request, HttpServletResponse response, Model model) {
		 String LoginName=UserUtils.getUser().getLoginName();
		 teacher.setTno(LoginName);
		Page<Teacher> page = teacherService.findPage(new Page<Teacher>(request, response), teacher); 
		model.addAttribute("page", page);
		return "ccms/opencourse/basic/teacher/tlist";
	}
	

	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = "tform")
	public String tform(Teacher teacher, Model model) {
		teacher=teacherService.getByTno(teacher);
		model.addAttribute("teacher", teacher);
		return "ccms/opencourse/basic/teacher/tform";
	}

	@RequiresPermissions("teacher:teacher:edit")
	@RequestMapping(value = "tsave")
	public String tsave(Teacher teacher, Model model, RedirectAttributes redirectAttributes) {
		teacherService.save(teacher);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/oc/teacher/teacher/tlist?repage";
	}

}