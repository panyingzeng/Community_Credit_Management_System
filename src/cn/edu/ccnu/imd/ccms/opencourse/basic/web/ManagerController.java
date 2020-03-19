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
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Manager;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.ManagerService;

@Controller
@RequestMapping(value = "${adminPath}/oc/manager/manager")
public class ManagerController extends BaseController{
	@Autowired
	private ManagerService managerService;
	
	@ModelAttribute
	public Manager get(@RequestParam(required=false)String id){
		Manager entity =null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(id)) {
			entity=managerService.get(id);
		}
		if (entity==null) {
			entity=new Manager();
		}
		return entity;
	}

	/**
	 * 秘书个人信息查询
	 * 
	 */
	@RequiresPermissions("manager:manager:view")
	@RequestMapping(value={"list",""})
	public String list(Manager manager,HttpServletRequest request,HttpServletResponse response,Model model){
		String LoginName=UserUtils.getUser().getLoginName();
		manager.setMno(LoginName);
		Page<Manager> page = managerService.findPage(new Page<Manager>(request, response), manager); 
		model.addAttribute("page", page);
		return "ccms/opencourse/basic/manager/list";
	}
	

	
}
