/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.ccms.opencourse.manager.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.StuHomework;

/**
 * 学生作业DAO接口
 * @author 姚进
 * @version 2016-10-11
 */
@MyBatisDao
public interface StuHomeworkDao extends CrudDao<StuHomework> {

}