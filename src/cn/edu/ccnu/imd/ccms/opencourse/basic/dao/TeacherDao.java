/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.ccms.opencourse.basic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Student;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Teacher;

/**
 * 教师表DAO接口
 * @author 潘英增
 * @version 2016-07-19
 */
@MyBatisDao
public interface TeacherDao extends CrudDao<Teacher> {

	public Teacher getByTno(Teacher teacher);
}