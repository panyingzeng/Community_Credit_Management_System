package cn.edu.ccnu.imd.ccms.opencourse.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.opencourse.basic.dao.StudentDao;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;

@Service
@Transactional(readOnly = true)
public class StudentService extends CrudService<StudentDao, Student>{

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public Student get(String id) {
		return super.get(id);
	}
	
	public Student getBysno(Student student) {
		return studentDao.getBySno(student);
	}
	
	@Override
	public List<Student> findList(Student student) {
		return super.findList(student);
	}
	
	@Override
	public Page<Student> findPage(Page<Student> page, Student student) {
		return super.findPage(page, student);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(Student student) {
		super.save(student);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(Student student) {
		super.delete(student);
	}
}
