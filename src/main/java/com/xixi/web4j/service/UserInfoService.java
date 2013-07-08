package com.xixi.web4j.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xixi.web4j.model.UserInfoBean;
import com.xixi.web4j.repository.jdbc.UserInfoDao;
import com.xixi.web4j.util.MD5Util;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Transactional(readOnly=true)
	public List<Map<String,Object>> listMap(Integer start,Integer limit) throws DataAccessException{
		return this.userInfoDao.listMap(start,limit);
	}
	
	@Transactional(readOnly=true)
	public Integer count()throws DataAccessException{
		return this.userInfoDao.count();
	}
	
	@Transactional(readOnly=true)
	public UserInfoBean find(String userName,String password)throws DataAccessException{
		return this.userInfoDao.find(userName, MD5Util.MD5(password));
	}
	
	@Transactional(readOnly=true)
	public UserInfoBean findById(Integer userId)throws DataAccessException{
		return this.userInfoDao.findById(userId);
	}
	
	@Transactional
	public void saveOrUpdate(UserInfoBean user)throws DataAccessException{
		this.userInfoDao.saveOrUpdate(user);
	}

	@Transactional
	public void modifyPwd(Integer userId,String newPwd)throws DataAccessException{
		this.userInfoDao.modifyPwd(userId, newPwd);
	}
	
	@Transactional
	public void deleteById(Integer userId)throws DataAccessException{
		this.userInfoDao.deleteById(userId);
	}
	
	@Transactional(readOnly=true)
	public boolean isExistCurrentUser(String userName)throws DataAccessException{
		return this.userInfoDao.isExistCurrentUser(userName);
	}
}
