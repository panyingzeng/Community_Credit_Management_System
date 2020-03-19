package cn.edu.ccnu.imd.ccms.activities.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.activities.dao.ActivitiesDao;
import cn.edu.ccnu.imd.ccms.activities.entity.Activities;

@Service
@Transactional(readOnly=true)
public class ActivitiesService extends CrudService<ActivitiesDao, Activities>{

	
	public Activities get(String id){
		return super.get(id);
	}
	
	public List<Activities>findList(Activities activities){
		return super.findList(activities);		
	}
	
	
	public Page<Activities> findPage(Page<Activities> page,Activities activities){
		return super.findPage(page, activities);
	}
	
	
	@Transactional(readOnly=false)
	public void save(Activities activities){
		super.save(activities);
	}

	@Transactional(readOnly=false)
	public void delete(Activities activities){
		super.delete(activities);
	}
}
