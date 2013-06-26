package com.xixi.web4j.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
	@Cacheable(value="users")
	public List<UserInfoBean> list(Integer start,Integer limit) throws DataAccessException{
		return this.userInfoDao.list(start,limit);
	}
	
	@Transactional(readOnly=true)
	@Cacheable(value="total")
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
	@CacheEvict(value="users",allEntries=true)
	public void saveOrUpdate(UserInfoBean user)throws DataAccessException{
		this.userInfoDao.saveOrUpdate(user);
	}
	
	@Transactional
	@CacheEvict(value="users",allEntries=true)
	public void deleteById(Integer userId)throws DataAccessException{
		this.userInfoDao.deleteById(userId);
	}
	
	@Transactional(readOnly=true)
	public boolean isExistCurrentUser(String userName)throws DataAccessException{
		return this.userInfoDao.isExistCurrentUser(userName);
	}
}
