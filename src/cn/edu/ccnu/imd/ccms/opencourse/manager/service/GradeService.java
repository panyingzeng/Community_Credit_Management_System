package cn.edu.ccnu.imd.ccms.opencourse.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Course;
import cn.edu.ccnu.imd.ccms.opencourse.manager.dao.GradeDao;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.Grade;

@Service
@Transactional(readOnly = true)
public class GradeService extends CrudService<GradeDao, Grade> {
	
	@Autowired
	private GradeDao gradeDao;

	@Override
	public Grade get(String id) {
		return super.get(id);
	}

	@Override
	public List<Grade> findList(Grade grade) {

		return super.findList(grade);
	}

	@Override
	public Page<Grade> findPage(Page<Grade> page, Grade grade) {

		return super.findPage(page, grade);
	}
	
	/**
	 * 查询成绩表中是否存在此学生
	 */
	
	@Transactional(readOnly = false)
	public boolean queryStudent( Grade grade){
		//查询数据库是否已存在此学生
		int count=gradeDao.queryStudent(grade);
		if (count>=1) {
			return false;
			
		} 
		return true;
	}

	/**
     *老师保存单个成绩	
	 */
	@Transactional(readOnly = false)
	public void saveOne(Grade grade) {
		super.save(grade);
	}

	/**
     *老师保存列表成绩	
	 */
	@Transactional(readOnly = false)
	public void save(Course course) {
		for (Grade grade : course.getGradeList()) {
			super.save(grade);
		}

	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Grade grade) {
		super.delete(grade);
	}

}