package cn.edu.ccnu.imd.ccms.opencourse.manager.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Manager;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.GradeCount;
import cn.edu.ccnu.imd.ccms.opencourse.manager.service.GradeCountService;

@Controller
@RequestMapping(value="${adminPath}/oc/manager/gradeCount")
public class GradeCountController extends BaseController {

	@Autowired
	private GradeCountService gradeCountService;
	

	/**
	 * 秘书查询学生总学分
	 */
	
	@RequiresPermissions("gradeCount:gradeCount:view")
	@RequestMapping(value ={"list",""})
    public String list(GradeCount gradeCount,HttpServletRequest request, HttpServletResponse response, Model model){	
	
		String LoginName= UserUtils.getUser().getLoginName();
		Manager manager=new Manager();
		gradeCount.setManager(manager);
		gradeCount.getManager().setMno(LoginName);
	Page<GradeCount> page=gradeCountService.findPage(new Page<GradeCount>(request,response), gradeCount);
	model.addAttribute("page", page);	
		return "ccms/opencourse/manager/gradeCount/list";  
  }
	
}
