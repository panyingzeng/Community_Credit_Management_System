package cn.edu.ccnu.imd.ccms.opencourse.basic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;

@MyBatisDao
public interface StudentDao extends CrudDao<Student>{
	
	public Student getBySno(Student student);
}
