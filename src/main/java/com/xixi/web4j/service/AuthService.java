package com.xixi.web4j.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xixi.web4j.repository.jdbc.AuthDao;
import com.xixi.web4j.repository.jdbc.ModuleDao;

@Service
public class AuthService {

	@Autowired
	private AuthDao authDao;
	
	@Autowired
	private ModuleDao moduleDao;
	
	//查询所有存在的模块
	@Transactional(readOnly=true)
	@Cacheable(value="moduleMap")
	public List<Map<String,Object>> queryAllModules()throws DataAccessException{
		return this.moduleDao.list();
	}
	
	//查询角色所对应的模块
	@Transactional(readOnly=true)
	public List<Integer> queryRoleModules(Integer roleId)throws DataAccessException{
		return this.authDao.list(roleId);
	}
	
	//保存更新权限
	@Transactional
	public void saveOrUpdateAuth(Integer roleId,List<Integer> moduleIds)throws DataAccessException{
		this.authDao.saveOrUpdate(roleId, moduleIds);
	}
}
