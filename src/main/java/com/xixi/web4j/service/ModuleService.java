package com.xixi.web4j.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xixi.web4j.model.ModuleBean;
import com.xixi.web4j.repository.jdbc.ModuleDao;

@Service
public class ModuleService {

	@Autowired
	private ModuleDao moduleDao;
	
	@Transactional(readOnly=true)
	public List<ModuleBean> list(Integer start,Integer limit)throws DataAccessException{
		return this.moduleDao.list(start,limit);
	}
	
	@Transactional(readOnly=true)
	public ModuleBean findById(Integer moduleId)throws DataAccessException{
		return this.moduleDao.findById(moduleId);
	}
	
	@Transactional(readOnly=true)
	public Integer count()throws DataAccessException{
		return this.moduleDao.count();
	}
	
	@Transactional(readOnly=true)
	public List<ModuleBean> listOneLevelMenu(Integer roleId)throws DataAccessException{
		return this.moduleDao.listOneLevelMenu(roleId);
	}
	
	@Transactional(readOnly=true)
	public List<ModuleBean> listTwoLevelMenu(Integer roleId,String parentCode)throws DataAccessException{
		return this.moduleDao.listTwoLevelMenu(roleId,parentCode);
	}
	
	@Transactional
	@CacheEvict(value="moduleMap",allEntries=true)
	public void saveOrUpdate(ModuleBean moduleBean)throws DataAccessException{
		this.moduleDao.saveOrUpdate(moduleBean);
	}
	
	@Transactional
	@CacheEvict(value="moduleMap",allEntries=true)
	public void deleteById(Integer moduleId)throws DataAccessException{
		this.moduleDao.deleteById(moduleId);
	}
	
	@Transactional(readOnly=true)
	public boolean isExistsModule(String moduleName)throws DataAccessException{
		return this.moduleDao.isExistsModule(moduleName);
	}
}
