/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.ccms.opencourse.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.opencourse.basic.dao.StudentDao;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;
import cn.edu.ccnu.imd.ccms.opencourse.common.HomeworkStatus;
import cn.edu.ccnu.imd.ccms.opencourse.manager.dao.StuHomeworkDao;
import cn.edu.ccnu.imd.ccms.opencourse.manager.dao.TeaHomeworkDao;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.StuHomework;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.TeaHomework;

/**
 * 学生作业Service
 * @author 姚进
 * @version 2016-10-11
 */
@Service
@Transactional(readOnly = true)
public class StuHomeworkService extends CrudService<StuHomeworkDao, StuHomework> {

	@Autowired
	private TeaHomeworkDao teaHomeworkDao;
	
	@Autowired
	private StudentDao studentDao;
	
	/**
	 * 查询作业,设置是否提交和是否超期
	 * @return
	 */
	public List<TeaHomework> findWorkByCno(StuHomework stuHomework){
		
		//查询这门课的所有作业
		TeaHomework teaHomework = new TeaHomework();
		teaHomework.setCno(stuHomework.getCno());
		List<TeaHomework> listTea = teaHomeworkDao.findList(teaHomework);
		
		List<TeaHomework> newList = new ArrayList<TeaHomework>();
		//过滤，如设置是否提交，
		
		if(listTea==null || listTea.size()==0){ //没有查询到作业直接返回
			return newList;
		}
		
		for(TeaHomework teaHomework2:listTea){
			stuHomework.setPid(teaHomework2.getId());
			List<StuHomework> listStu = super.findList(stuHomework);
			if(listStu!=null && listStu.size()!=0){//说明这个作业已经提交
				teaHomework2.setStatus(HomeworkStatus.SUBMITED);
				newList.add(teaHomework2);
			}
			else{//没有提交，判断是否超期，超期则不显示
				Date nowDate = new Date();
				Date endTime = teaHomework2.getEndTime();//获取作业的最后期限
				if(nowDate.getTime()<endTime.getTime()){//没有超期
					teaHomework2.setStatus(HomeworkStatus.NO_SUBMIT);
					newList.add(teaHomework2);
				}
			}
		}
		return newList;
	}
	
	/**
	 * 根据pid查询作业 和对应的学生信息
	 */
	public List<StuHomework> findStuWork(StuHomework stuHomework) {
		List<StuHomework> stuList =  super.findList(stuHomework);
		
		if(stuList==null || stuList.size()==0){//如果没有人交作业，返回null
			return stuList;
		}
		// 不为null
		List<StuHomework> newList = new ArrayList<StuHomework>();//存放查询了学生信息的StudenHomeWork
		for(StuHomework stuHomework2:stuList){
			String sno = stuHomework2.getSno();
			Student student = new Student();
			student.setSno(sno);
			student = studentDao.getBySno(student);
			stuHomework2.setStudent(student);
			newList.add(stuHomework2);
		}
		
		return newList;
	}
	
	
	@Override
	public StuHomework get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<StuHomework> findList(StuHomework stuHomework) {
		return super.findList(stuHomework);
	}
	
	@Override
	public Page<StuHomework> findPage(Page<StuHomework> page, StuHomework stuHomework) {
		return super.findPage(page, stuHomework);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(StuHomework stuHomework) {
		super.save(stuHomework);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(StuHomework stuHomework) {
		super.delete(stuHomework);
	}
	
}