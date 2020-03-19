package cn.edu.ccnu.imd.ccms.opencourse.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.opencourse.basic.dao.StudentSignDao;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.StudentSign;

@Service
@Transactional(readOnly = true)
public class StudentSignService extends CrudService<StudentSignDao, StudentSign> {

	@Autowired
	private StudentSignDao studentSignDao;
	
	@Override
	public StudentSign get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<StudentSign> findList(StudentSign studentSign) {
		return super.findList(studentSign);
	}
	
	@Override
	public Page<StudentSign> findPage(Page<StudentSign> page, StudentSign studentSign) {
		
		return super.findPage(page, studentSign);
	}
	
	/**
	 * 学生查看自己已经签过的签到
	 */
   public List<StudentSign> query(StudentSign studentSign) {
	
		return studentSignDao.query(studentSign);
	}
   
   
	@Override
	@Transactional(readOnly = false)
	public void save(StudentSign studentSign) {
		super.save(studentSign);
	}
	
	/**
	 *学生签到
	 */
	@Transactional(readOnly = false)
	public boolean querySign(StudentSign studentSign){
		//查询数据库是否已存在此签到
		int count=studentSignDao.countSign(studentSign);
		if (count>=1) {
			return false;
			
		} 
		return true;
	}
	
	
	
	
	@Override
	@Transactional(readOnly = false)
	public void delete(StudentSign studentSign) {
		super.delete(studentSign);
	}
	
}