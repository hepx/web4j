package com.xixi.web4j.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xixi.web4j.model.RoleBean;
import com.xixi.web4j.repository.jdbc.RoleDao;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Transactional(readOnly=true)
	@Cacheable(value="roles")
	public List<RoleBean> list()throws DataAccessException{
		return this.roleDao.list();
	}
	
	@Transactional(readOnly=true)
	public RoleBean findById(Integer roleId)throws DataAccessException{
		return this.roleDao.findById(roleId);
	}
	
	@Transactional
	public void saveOrUpdate(RoleBean role)throws DataAccessException{
		this.roleDao.saveOrUpdate(role);
	}
	
	@Transactional
	public void deleteById(Integer roleId)throws DataAccessException{
		this.roleDao.deleteById(roleId);
	}
}
