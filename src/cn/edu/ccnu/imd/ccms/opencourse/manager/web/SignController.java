package cn.edu.ccnu.imd.ccms.opencourse.manager.web;

import java.util.Date;
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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.StudentSign;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.TeacherSign;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.CourseService;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.StudentSignService;
import cn.edu.ccnu.imd.ccms.opencourse.basic.service.TeacherSignService;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.Sign;
import cn.edu.ccnu.imd.ccms.opencourse.manager.service.SignService;

@Controller
@RequestMapping(value="${adminPath}/oc/manager/sign")
public class SignController extends BaseController{
	
	@Autowired
	private SignService signService;
	
	@Autowired
	private TeacherSignService teacherSignService;
	
	@Autowired
	private StudentSignService studentSignService;
	
	@Autowired
	private CourseService courseService;
	
	@ModelAttribute
	public Sign get(@RequestParam(required=false) String id) {
		Sign entity = null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(id)){
			entity = signService.get(id);
		}
		if (entity == null){
			entity = new Sign();
		}
		return entity;
	}
	
	/**
	 * 老师查看自己教授的课程
	 */
	@RequiresPermissions("manager:sign:view")
	@RequestMapping(value = "courseList")
	public String courseList(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		String loginName= UserUtils.getUser().getLoginName();
		course.setTno(loginName);
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		model.addAttribute("course", course);
		return "ccms/opencourse/manager/sign/courseList";
	}
	
	/**
	 * 老师创建签到表单
	 */
	@RequiresPermissions("manager:sign:view")
	@RequestMapping(value = "createForm")
	public String createForm(TeacherSign teacherSign, Model model) {
		Date startTime=new Date();
		teacherSign.setStartTime(startTime);
		model.addAttribute("teacherSign", teacherSign);
		return "ccms/opencourse/manager/sign/createForm";
	}
	
	
	
	
	
	/**
	 * 老师启动签到
	 */
	@RequiresPermissions("manager:sign:edit")
	@RequestMapping(value = "publish")
	public String publish(TeacherSign teacherSign, Model model, RedirectAttributes redirectAttributes) {
		String loginName= UserUtils.getUser().getLoginName();
		teacherSign.setTno(loginName);
		
		
		teacherSign.setStatus("0");
		teacherSignService.save(teacherSign);
		addMessage(redirectAttributes, "启动签到成功");
		return "redirect:"+Global.getAdminPath()+"/oc/manager/sign/courseList/?repage";
	}
	
	/**
	 * 老师查看自己启动的签到
	 */
	@RequiresPermissions("manager:sign:view")
	@RequestMapping(value = "signList")
    public String signList(TeacherSign teacherSign,HttpServletRequest request, HttpServletResponse response, Model model) {
		String loginName= UserUtils.getUser().getLoginName();
		teacherSign.setTno(loginName);
		Page<TeacherSign> page = teacherSignService.findPage(new Page<TeacherSign>(request, response), teacherSign); 
		model.addAttribute("page", page);
		model.addAttribute("teacherSign", teacherSign);
		return "ccms/opencourse/manager/sign/signList";
		
	}
	
	/**
	 * 老师查看签到人数
	 */
	@RequiresPermissions("manager:sign:view")
	@RequestMapping(value = "studentList")
	public String list(StudentSign studentSign, HttpServletRequest request, HttpServletResponse response, Model model) {
		String loginName=UserUtils.getUser().getLoginName();
		TeacherSign teacherSign=new TeacherSign();
		studentSign.setTeacherSign(teacherSign);
		studentSign.getTeacherSign().setTno(loginName);
		Page<StudentSign> page = studentSignService.findPage(new Page<StudentSign>(request, response), studentSign); 
		model.addAttribute("page", page);
		model.addAttribute("studentSign", studentSign);
		return "ccms/opencourse/manager/sign/studentList";
	}
	
	/**
	 * 老师修改某位同学的签到状态form
	 */
	@RequiresPermissions("manager:sign:view")
	@RequestMapping(value = "signForm")
	public String signForm(StudentSign studentSign, Model model) {
		model.addAttribute("studentSign", studentSign);
		return "ccms/opencourse/manager/sign/signForm";
	}

	/**
	 * 老师修改某位同学的签到状态
	 */
	@RequiresPermissions("manager:sign:edit")
	@RequestMapping(value = "saveOne")
	public String  saveOne(StudentSign studentSign, Model model,RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, studentSign)){
			return signForm(studentSign, model);
		}
		boolean b=studentSignService.querySign(studentSign);
		if(!b){
			addMessage(redirectAttributes,"您已签过到了！");
		}else{
		Date time=new Date();
		studentSign.setTime(time);
		studentSignService.save(studentSign);
		addMessage(redirectAttributes, "签到成功");
		}
		return "redirect:"+Global.getAdminPath()+"/oc/manager/sign/signList/?cno="+studentSign.getCno()+"";
	}
	
	
	/**
	 * 学生查看自己的课表
	 */
	@RequiresPermissions("manager:sign:view")
	@RequestMapping(value = "scourseList")
	public String scourseList(Course course ,Model model ){
		String loginName=UserUtils.getUser().getLoginName();
		Student student=new Student();
		course.setStudent(student);
		course.getStudent().setSno(loginName);
		List<Course>list=courseService.findCourse(course);
    	model.addAttribute("list", list);
		model.addAttribute("course", course);
		return "ccms/opencourse/manager/sign/scourseList";
		
	}
	
	/**
	 * 学生查看需要签到的列表
	 */
	@RequiresPermissions("manager:sign:view")
	@RequestMapping(value = "seeSign")
    public String seeSign(Sign sign,HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sign> page = signService.findPage(new Page<Sign>(request, response), sign); 
		model.addAttribute("page", page);
		model.addAttribute("sign", sign);
		return "ccms/opencourse/manager/sign/seeSignList";
		
	}
	
	
	/**
	 * 学生签到
	**/
	
	@RequiresPermissions("manager:sign:edit")
	@RequestMapping(value = "saveSign")
	public String  saveSign(StudentSign studentSign, Model model,RedirectAttributes redirectAttributes) {
		//获取当前登录名
		String loginName= UserUtils.getUser().getLoginName();
		studentSign.setSno(loginName);
		boolean b=studentSignService.querySign(studentSign);
		if(!b){
			addMessage(redirectAttributes,"您已签过到了！");
		}else{
		Date time=new Date();
		studentSign.setTime(time);
		studentSign.setStatus("0");
		studentSignService.save(studentSign);
		}
		return "redirect:"+Global.getAdminPath()+"/oc/manager/sign/query?sno="+studentSign.getSno()+"&course.cno="+studentSign.getCno()+"";
	
	}
	
	/**
	 * 学生签到之后跳转的页面
	 */
	@RequiresPermissions("manager:sign:view")
	@RequestMapping(value = "query")
	public String query(StudentSign studentSign, Model model){
		List<StudentSign>list=studentSignService.query(studentSign);
		model.addAttribute("list", list);
		model.addAttribute("studentSign", studentSign);
		return "ccms/opencourse/manager/sign/queryList";
		
	}
		
	
	
}