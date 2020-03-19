package cn.edu.ccnu.imd.ccms.opencourse.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.opencourse.basic.dao.TeacherSignDao;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.TeacherSign;

@Service
@Transactional(readOnly = true)
public class TeacherSignService extends CrudService<TeacherSignDao, TeacherSign> {

	@Override
	public TeacherSign get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<TeacherSign> findList(TeacherSign teacherSign) {
		return super.findList(teacherSign);
	}
	
	@Override
	public Page<TeacherSign> findPage(Page<TeacherSign> page, TeacherSign teacherSign) {
		return super.findPage(page, teacherSign);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(TeacherSign teacherSign) {
		super.save(teacherSign);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(TeacherSign teacherSign) {
		super.delete(teacherSign);
	}
	
}