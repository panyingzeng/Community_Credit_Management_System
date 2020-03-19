package cn.edu.ccnu.imd.ccms.opencourse.manager.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.CourseService;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.StuHomework;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.TeaHomework;
import cn.edu.ccnu.imd.ccms.opencourse.manager.service.StuHomeworkService;
import cn.edu.ccnu.imd.ccms.opencourse.manager.service.TeaHomeworkService;

@Controller
@RequestMapping(value = "${adminPath}/oc/manager/homework")
public class TeaHomeworkController extends BaseController {

	@Autowired
	private TeaHomeworkService teaHomeworkService;

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StuHomeworkService stuHomeworkService;

	/**
	 * 查询老师的所有课程
	 * 
	 * @param course
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:homework:view")
	@RequestMapping(value = "courseList")
	public String courseList(Course course, Model model) {
		List<Course> list = teaHomeworkService.findCourse(course);
		model.addAttribute("list", list);
		return "ccms/opencourse/manager/teahomework/courseList";
	}

	/**
	 * 查询已经发布的作业
	 * 
	 * @param course
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:homework:view")
	@RequestMapping(value = "publishedHomework")
	public String publishedHomework(TeaHomework teaHomework, Model model) {
		// 获取老师的职工号
		String tno = UserUtils.getUser().getLoginName();
		teaHomework.setTno(tno);
		Course course = courseService.get(teaHomework.getCno());
		List<TeaHomework> list = teaHomeworkService.publishedHomework(teaHomework);
		model.addAttribute("course", course);
		model.addAttribute("list", list);
		return "ccms/opencourse/manager/teahomework/publishList";
	}

	/**
	 * 跳转到作业添加或是修改的页面
	 * 
	 * @param teaHomework
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:homework:view")
	@RequestMapping(value = "form")
	public String form(TeaHomework teaHomework, Model model) {

		String tno = UserUtils.getUser().getLoginName();
		teaHomework.setTno(tno);
		model.addAttribute("teaHomework", teaHomework);
		return "ccms/opencourse/manager/teahomework/form";
	}

	/**
	 * 跳转到修改的页面
	 * @param teaHomework
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:homework:view")
	@RequestMapping(value = "editForm")
	public String editForm(TeaHomework teaHomework, Model model) {

		teaHomework = teaHomeworkService.get(teaHomework.getId());
		String cno = teaHomework.getCno();
		teaHomework.setCourse(courseService.get(cno));
		model.addAttribute("teaHomework", teaHomework);
		return "ccms/opencourse/manager/teahomework/form";
	}

	/**
	 * 保存作业
	 * 
	 * @param teaHomework
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("manager:homework:edit")
	@RequestMapping(value = "save")
	public String save(TeaHomework teaHomework, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, teaHomework)) {
			return form(teaHomework, model);
		}
		teaHomeworkService.save(teaHomework);
		addMessage(redirectAttributes, "保存作业成功");
		return "redirect:" + Global.getAdminPath() + "/oc/manager/homework/publishedHomework?cno="
				+ teaHomework.getCno();
	}

	/**
	 * 根据pid 查询这次学生所提交的所有作业
	 * @param stuHomework
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:homework:view")
	@RequestMapping(value = "findStuWorkList")
	public String findStuWorkList(StuHomework stuHomework, Model model) {
		List<StuHomework>list=stuHomeworkService.findStuWork(stuHomework);
		model.addAttribute("list", list);
		return "ccms/opencourse/manager/teahomework/stuWorkList";
	}
	
	/**
	 * 跳转至 学生作业列表
	 * @param teaHomework
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:homework:view")
	@RequestMapping(value = "stuWorkform")
	public String stuWorkform(TeaHomework teaHomework, Model model) {

		String tno = UserUtils.getUser().getLoginName();
		teaHomework.setTno(tno);
		model.addAttribute("teaHomework", teaHomework);
		return "ccms/opencourse/manager/teahomework/form";
	}
	
	/**
	 * 修改分数和保存分数
	 * @param stuHomework
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("manager:homework:edit")
	@RequestMapping(value = "saveScore")
	public String saveScore(StuHomework stuHomework, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, stuHomework)){
			return formScore(stuHomework, model);
		}
		stuHomeworkService.save(stuHomework);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/oc/manager/homework/findStuWorkList?pid="+stuHomework.getPid();
	}
	
	/**
	 *跳转到成绩的登记界面
	 * @param teaHomework
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:homework:view")
	@RequestMapping(value = "formScore")
	public String formScore(StuHomework stuHomework, Model model) {
		stuHomework = stuHomeworkService.get(stuHomework.getId());
		model.addAttribute("stuHomework",stuHomework);
		return "ccms/opencourse/manager/teahomework/formScore";
	}
	
	/**
	 * 删除作业
	 * @param teaHomework
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("manager:homework:edit")
	@RequestMapping(value = "delete")
	public String delete(TeaHomework teaHomework, RedirectAttributes redirectAttributes) {
		teaHomeworkService.delete(teaHomework);
		teaHomework = teaHomeworkService.get(teaHomework.getId());
		addMessage(redirectAttributes, "删除作业成功");
		return "redirect:" + Global.getAdminPath() + "/oc/manager/homework/publishedHomework?cno="
				+ teaHomework.getCno();
	}

}
