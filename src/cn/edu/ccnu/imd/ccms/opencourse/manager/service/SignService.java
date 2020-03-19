package cn.edu.ccnu.imd.ccms.opencourse.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.ccms.opencourse.manager.dao.SignDao;
import cn.edu.ccnu.imd.ccms.opencourse.manager.entity.Sign;

@Service
@Transactional(readOnly=true)
public class SignService extends CrudService<SignDao,Sign> {
	
	@Override
	public Sign get(String id) {
		return super.get(id);
	}

	@Override
	public Page<Sign> findPage(Page<Sign> page, Sign sign) {
		return super.findPage(page, sign);
	}
	
	@Override
	public List<Sign> findList (Sign sign){
		return super.findList(sign);
		
	}
}
