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

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.StudentSign;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.StudentSignService;

@Controller
@RequestMapping(value = "${adminPath}/oc/sign/studentSign")
public class StudentSignController extends BaseController {

	@Autowired
	private StudentSignService studentSignService;
	
	@ModelAttribute
	public StudentSign get(@RequestParam(required=false) String id) {
		StudentSign entity = null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(id)){
			entity = studentSignService.get(id);
		}
		if (entity == null){
			entity = new StudentSign();
		}
		return entity;
	}
	
	@RequiresPermissions("sign:studentSign:view")
	@RequestMapping(value = {"list", ""})
	public String list(StudentSign studentSign, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StudentSign> page = studentSignService.findPage(new Page<StudentSign>(request, response), studentSign); 
		model.addAttribute("page", page);
		return "imd/sign/studentSignList";
	}

	@RequiresPermissions("sign:studentSign:view")
	@RequestMapping(value = "form")
	public String form(StudentSign studentSign, Model model) {
		model.addAttribute("studentSign", studentSign);
		return "imd/sign/studentSignForm";
	}

	@RequiresPermissions("sign:studentSign:edit")
	@RequestMapping(value = "save")
	public String save(StudentSign studentSign, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, studentSign)){
			return form(studentSign, model);
		}
		studentSignService.save(studentSign);
		addMessage(redirectAttributes, "保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/sign/studentSign/?repage";
	}
	
	@RequiresPermissions("sign:studentSign:edit")
	@RequestMapping(value = "delete")
	public String delete(StudentSign studentSign, RedirectAttributes redirectAttributes) {
		studentSignService.delete(studentSign);
		addMessage(redirectAttributes, "删除成功成功");
		return "redirect:"+Global.getAdminPath()+"/sign/studentSign/?repage";
	}

}
