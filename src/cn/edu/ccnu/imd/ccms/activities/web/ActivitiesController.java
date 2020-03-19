package cn.edu.ccnu.imd.ccms.activities.web;

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

import cn.edu.ccnu.imd.ccms.activities.entity.Activities;
import cn.edu.ccnu.imd.ccms.activities.service.ActivitiesService;

@Controller
@RequestMapping(value="${adminPath}/oc/activities/activities")
public class ActivitiesController extends BaseController {

	@Autowired
	private ActivitiesService activitiesService;
	
	
	@ModelAttribute
	public Activities get(@RequestParam(required=false) String id) {
		Activities entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = activitiesService.get(id);
		}
		if (entity == null){
			entity = new Activities();
		}
		return entity;
	}
	
	/**
	 * 根据活动的id，查询活动的要求
	 */
	
	@RequiresPermissions("activities:activities:view")
	@RequestMapping(value = "demand", method = RequestMethod.GET)
	public String demand(String id, Model model) {
		Activities activities = activitiesService.get(id);
		model.addAttribute("activities", activities);
		return "ccms/activities/activities/demandList";
	}
	
	
	@RequiresPermissions("activities:activities:view")
	@RequestMapping(value ="list")
	public String list(Activities activities,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<Activities>page=activitiesService.findPage(new Page<Activities>(request,response), activities);
		model.addAttribute("page", page);
		return "ccms/activities/activities/list";
	}
	
	@RequiresPermissions("activities:activities:view")
	@RequestMapping(value="form")
	public String form(Activities activities,Model model){
	  
		model.addAttribute("activities", activities);
		return "ccms/activities/activities/form";
	}
	
	@RequiresPermissions("activities:activities:edit")
	@RequestMapping(value ="save")
	public String save (Activities activities,Model model,RedirectAttributes redirectAttributes){
			activitiesService.save(activities);
			addMessage(redirectAttributes, "保存成功");
		
		return "redirect:"+Global.getAdminPath()+"/oc/activities/activities/list?repage";
	}
	
	@RequiresPermissions("activities:activities:edit")
	@RequestMapping(value ="delete")
	public String delete (Activities activities,RedirectAttributes redirectAttributes){
		
		activitiesService.delete(activities);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/oc/activities/activities/list?repage";
	}
}
