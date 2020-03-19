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

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.TeacherSign;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.TeacherSignService;

@Controller
@RequestMapping(value = "${adminPath}/oc/teachersign/teacherSign")
public class TeacherSignController extends BaseController {

	@Autowired
	private TeacherSignService teacherSignService;
	
	@ModelAttribute
	public TeacherSign get(@RequestParam(required=false) String id) {
		TeacherSign entity = null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(id)){
			entity = teacherSignService.get(id);
		}
		if (entity == null){
			entity = new TeacherSign();
		}
		return entity;
	}
	
	@RequiresPermissions("teachersign:teacherSign:view")
	@RequestMapping(value = {"list", ""})
	public String list(TeacherSign teacherSign, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeacherSign> page = teacherSignService.findPage(new Page<TeacherSign>(request, response), teacherSign); 
		model.addAttribute("page", page);
		return "ccms/opencourse/basic/teachersign/teachersignList";
	}
	
	@RequiresPermissions("teachersign:teacherSign:view")
	@RequestMapping(value = "form")
	public String form(TeacherSign teacherSign, Model model) {
		model.addAttribute("teacherSign", teacherSign);
		return "ccms/opencourse/basic/teachersign/teachersignForm";
	}
	
	@RequiresPermissions("teachersign:teacherSign:edit")
	@RequestMapping(value = "save")
	public String save(TeacherSign teacherSign, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, teacherSign)){
			return form(teacherSign, model);
		}
		teacherSignService.save(teacherSign);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/oc/teachersign/teacherSign/?repage";
	}
	
	@RequiresPermissions("teachersign:teacherSign:edit")
	@RequestMapping(value = "delete")
	public String delete(TeacherSign teacherSign, RedirectAttributes redirectAttributes) {
		teacherSignService.delete(teacherSign);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/oc/teachersign/teacherSign/?repage";
	}

	
}