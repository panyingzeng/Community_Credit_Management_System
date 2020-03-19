package cn.edu.ccnu.imd.ccms.activities.web;

import java.util.List;

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

import cn.edu.ccnu.imd.ccms.activities.entity.StuActivities;
import cn.edu.ccnu.imd.ccms.activities.service.StuActivitiesService;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;


@Controller
@RequestMapping(value="${adminPath}/oc/activities/stuActivities")
public class StuActivitiesController extends BaseController {

	@Autowired
	private StuActivitiesService stuActivitiesService;
	
	@ModelAttribute
	public StuActivities get(@RequestParam(required=false)String id){
		StuActivities entity=null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(id)) {
           entity=stuActivitiesService.get(id);			
		}
		
		if (entity==null) {
			entity=new StuActivities();
		}
		return entity;
	}
	
	/**
	 * 学生查询活动列表
	 */
	@RequiresPermissions("activities:stuActivities:view")
	@RequestMapping(value ="list")
	public String list(StuActivities stuActivities,HttpServletRequest request,HttpServletResponse response,Model model){
		String LoginName =UserUtils.getUser().getLoginName();
        stuActivities.setSno(LoginName);
		
        Page<StuActivities> page=stuActivitiesService.findPage(new Page<StuActivities>(request,response), stuActivities);
		List<StuActivities> list = page.getList();
		model.addAttribute("page", page);
		model.addAttribute("stuActivities", stuActivities);
		return "ccms/activities/stuActivities/list";
	}
	/**
	 * 
	 *学生添加活动的form
	 */
	@RequiresPermissions("activities:stuActivities:view")
	@RequestMapping(value="form")
	public String form(StuActivities stuActivities,Model model){
		model.addAttribute("stuActivities", stuActivities);
		return "ccms/activities/stuActivities/form";
	}
	
	/**
	 * 
	 *保存学生添加的活动
	 */
	@RequiresPermissions("activities:stuActivities:edit")
	@RequestMapping(value ="save")
	public String save (StuActivities stuActivities,Model model,RedirectAttributes redirectAttributes){
			stuActivities.setStatus("0");
			stuActivitiesService.save(stuActivities);
			addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/oc/activities/stuActivities/list?repage";
	}
	
	/**
	 *学生删除活动
	 */
	@RequiresPermissions("activities:stuActivities:edit")
	@RequestMapping(value ="delete")
	public String delete (StuActivities stuActivities,RedirectAttributes redirectAttributes){
		
		stuActivitiesService.delete(stuActivities);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/oc/activities/stuActivities/list?repage";
	}
	
	/**
	 * 秘书查看学生的活动
	 */
	@RequiresPermissions("activities:stuActivities:view")
	@RequestMapping(value ="stuList")
	public String stuList(StuActivities stuActivities,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<StuActivities>page=stuActivitiesService.findPage(new Page<StuActivities>(request,response), stuActivities);
		model.addAttribute("page", page);
		return "ccms/activities/stuActivities/stuList";
	}
	
	/**
	 * 秘书审核学生活动
	 */
	@RequiresPermissions("activities:stuActivities:view")
	@RequestMapping(value ="checkFrom")
	public String checkFrom(StuActivities stuActivities,Model model){
		model.addAttribute("stuActivities", stuActivities);
		return "ccms/activities/stuActivities/checkForm";
	}
	
	/**
	 * 秘书保存审核
	 */
	@RequiresPermissions("activities:stuActivities:edit")
	@RequestMapping(value ="saveCheck")
	public String saveCheck(StuActivities stuActivities,Model model,RedirectAttributes redirectAttributes){
			stuActivitiesService.save(stuActivities);
			addMessage(redirectAttributes, "保存成功");
	
		return "redirect:"+Global.getAdminPath()+"/oc/activities/stuActivities/stuList?repage";
	}
}	
