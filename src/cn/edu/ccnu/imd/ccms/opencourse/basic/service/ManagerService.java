package cn.edu.ccnu.imd.ccms.opencourse.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.opencourse.basic.dao.ManagerDao;
import cn.edu.ccnu.imd.ccms.opencourse.basic.entity.Manager;

@Service
@Transactional(readOnly=true)
public class ManagerService extends CrudService<ManagerDao, Manager> {

	@Override
	public Manager get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<Manager> findList(Manager manager) {
		return super.findList(manager);
	}
	
	@Override
	public Page<Manager> findPage(Page<Manager> page, Manager manager) {
		return super.findPage(page, manager);
	}
	
}
