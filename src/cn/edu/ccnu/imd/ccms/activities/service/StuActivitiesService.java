package cn.edu.ccnu.imd.ccms.activities.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.activities.dao.StuActivitiesDao;
import cn.edu.ccnu.imd.ccms.activities.entity.StuActivities;


@Service
@Transactional(readOnly=true)
public class StuActivitiesService  extends CrudService<StuActivitiesDao, StuActivities>{

	
	public StuActivities get(String id){
		return super.get(id);
	}
	
	public List<StuActivities>findList(StuActivities stuActivities){
		return super.findList(stuActivities);		
	}
	
	
	public Page<StuActivities> findPage(Page<StuActivities> page,StuActivities stuActivities){
		return super.findPage(page, stuActivities);
	}
	
	
	@Transactional(readOnly=false)
	public void save(StuActivities stuActivities){
		super.save(stuActivities);
	}

	@Transactional(readOnly=false)
	public void delete(StuActivities stuActivities){
		super.delete(stuActivities);
	}
}
