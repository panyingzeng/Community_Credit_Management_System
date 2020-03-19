package cn.edu.ccnu.imd.ccms.opencourse.manager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.opencourse.manager.dao.GradeCountDao;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.GradeCount;

@Service
@Transactional(readOnly=true)
public class GradeCountService extends CrudService<GradeCountDao,GradeCount>{

@Override
public Page<GradeCount> findPage(Page<GradeCount> page, GradeCount gradeCount) {
		
		return super.findPage(page, gradeCount);
	} 
}
