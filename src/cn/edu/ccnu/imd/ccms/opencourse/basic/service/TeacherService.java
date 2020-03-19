/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.ccms.opencourse.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.opencourse.basic.dao.TeacherDao;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Teacher;

/**
 * 教师表Service
 * @author 潘英增
 * @version 2016-07-19
 */
@Service
@Transactional(readOnly = true)
public class TeacherService extends CrudService<TeacherDao, Teacher> {

	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public Teacher get(String id) {
		return super.get(id);
	}
	

	public Teacher getByTno(Teacher teacher) {
		return teacherDao.getByTno(teacher);
	}
	
	@Override
	public List<Teacher> findList(Teacher teacher) {
		return super.findList(teacher);
	}
	
	@Override
	public Page<Teacher> findPage(Page<Teacher> page, Teacher teacher) {
		return super.findPage(page, teacher);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(Teacher teacher) {
		super.save(teacher);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(Teacher teacher) {
		super.delete(teacher);
	}
	
}