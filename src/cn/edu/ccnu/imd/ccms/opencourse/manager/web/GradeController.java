package cn.edu.ccnu.imd.ccms.opencourse.manager.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.CourseService;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.Grade;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.SelectCourse;
import cn.edu.ccnu.imd.ccms.opencourse.manager.service.GradeService;
import cn.edu.ccnu.imd.ccms.opencourse.manager.service.SelectCourseService;

@Controller
@RequestMapping(value = "${adminPath}/oc/manager/grade")
public class GradeController extends BaseController {

	@Autowired
	private GradeService gradeService;
	
	
	@Autowired
	private SelectCourseService  selectCourseService;
	
	@Autowired
	private CourseService courseService;
	
	
	@ModelAttribute
	public Grade get(@RequestParam(required=false) String id) {
		Grade entity = null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(id)){
			entity = gradeService.get(id);
		}
		if (entity == null){
			entity = new Grade();
		}
		return entity;
	}
	
	/**
	 * 学生查看自己的分数
	 */
	@RequiresPermissions("manager:grade:view")
	@RequestMapping(value = "sgradeList")
	public String sgradeList(Grade grade, HttpServletRequest request, HttpServletResponse response, Model model) {
		String  LoginName =UserUtils.getUser().getLoginName();
		grade.setSno(LoginName);
		Page<Grade> page = gradeService.findPage(new Page<Grade>(request, response), grade); 
		model.addAttribute("page", page);
		return "ccms/opencourse/manager/grade/sgradeList";
	}

	
	/**分数管理中的登记分数
	 * 老师查看自己所教的课程
	 */
	@RequiresPermissions("manager:grade:view")
	@RequestMapping(value = "courseList")
	public String courseList(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		model.addAttribute("course", course);
		return "ccms/opencourse/manager/grade/courseList";
	}
	
	/**
	 * 分数管理中的登记分数
	 * 老师查看自己的本门课的学生并登记学生分数
	 */
	@RequiresPermissions("manager:grade:edit")
	@RequestMapping(value = "gradeList")
	public String gradeList(SelectCourse selectCourse, Model model){
		//selectCourseService查询该门课程的选课学生
		List<SelectCourse> list=selectCourseService.findList(selectCourse);
		//gradeService查询出本门课的所有成绩
		Grade grade = new Grade();
		grade.setCourse(selectCourse.getCourse());
		List<Grade> gradeList=gradeService.findList(grade);		
		//把成绩转为map结构（key，value）
		Map<String ,Grade> map=new HashMap<String ,Grade>();
		
		for (Grade grade2 : gradeList) {
			map.put(grade2.getSno(), grade2);
			
		}
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		model.addAttribute("selectCourse", selectCourse);
		return "ccms/opencourse/manager/grade/gradeList";
		
	} 
	
	/**
	 * 老师保存多个学生成绩
	 */
	@RequiresPermissions("manager:grade:edit")
	@RequestMapping(value = "saveList")
	public String save(Course course, Model model, RedirectAttributes redirectAttributes) {
		gradeService.save(course);
		addMessage(redirectAttributes, "保存成绩成功");
		return "redirect:"+Global.getAdminPath()+"/oc/manager/grade/gradeList?course.cno="+course.getCno()+"&course.cname="+course.getCname()+"repage";
	}
	

	/**分数管理中成绩查询的
	 * 老师查看自己所教的课程
	 */
	@RequiresPermissions("manager:grade:view")
	@RequestMapping(value = "tcourseList")
	public String tcourseList(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		model.addAttribute("course", course);
		return "ccms/opencourse/manager/grade/tcourseList";
	}
	

	/**分数管理中成绩查询的
	 * 老师查看自己所有学生的分数
	 */
	@RequiresPermissions("manager:grade:edit")
	@RequestMapping(value = "studentGrade")
	public String studentGrade(Grade grade, HttpServletRequest request, HttpServletResponse response, Model model) {
		String  LoginName =UserUtils.getUser().getLoginName();

		if (grade.getCourse()!=null) {
			grade.getCourse().setTno(LoginName);
			}else{
				Course course =new Course();
		        grade.setCourse(course);
				grade.getCourse().setTno(LoginName);
			}
		Page<Grade> page = gradeService.findPage(new Page<Grade>(request, response), grade); 
		model.addAttribute("page", page);
		model.addAttribute("grade", grade);
		return "ccms/opencourse/manager/grade/studentGradeList";
      }
	
	/**分数管理中成绩查询
	 * 老师添加单个学生成绩
	 */

	@RequiresPermissions("manager:grade:edit")
	@RequestMapping(value = "addForm")
	public String addForm(Grade grade, Model model) {
		model.addAttribute("grade", grade);
		return "ccms/opencourse/manager/grade/addForm";
	}
	
	/**分数管理中成绩查询
	 * 老师添加单个成绩之后保存
	 */
	
	@RequiresPermissions("manager:grade:edit")
	@RequestMapping(value = "saveForm")
	public String saveForm(Grade grade, Model model,RedirectAttributes redirectAttributes) {
		//查询数据库中是否存在此学生
		boolean b=gradeService.queryStudent(grade);
		if(!b){
			addMessage(redirectAttributes,"此学生已存在!");
		}else{
		gradeService.saveOne(grade);
		}
		return "redirect:"+Global.getAdminPath()+"/oc/manager/grade/tcourseList?repage";
	}
	
	
	/**分数管理中成绩查询
	 * 老师修改单个学生成绩form表单
	 */
	@RequiresPermissions("manager:grade:view")
	@RequestMapping(value = "form")
	public String form(Grade grade, Model model) {
		model.addAttribute("grade", grade);
		return "ccms/opencourse/manager/grade/gradeForm";
	}
	
	/**分数管理中成绩查询
	 * 老师修改单个学生成绩并保存
	 */
	@RequiresPermissions("manager:grade:edit")
	@RequestMapping(value = "saveOne")
	public String saveOne(Grade grade, Model model, RedirectAttributes redirectAttributes) {
			if (!beanValidator(model, grade)){
				return form(grade, model);
			}
			gradeService.save(grade);
			addMessage(redirectAttributes, "保存成绩成功");
			return "redirect:"+Global.getAdminPath()+"/oc/manager/grade/studentGrade?course.cno="+grade.getCno()+"&course.cname="+grade.getCourse().getCname()+"";
		}
	

	
}
