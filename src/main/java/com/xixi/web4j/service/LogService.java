package com.xixi.web4j.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xixi.web4j.model.LogInfo;
import com.xixi.web4j.repository.jdbc.LogDao;

@Service
public class LogService {

	@Autowired
	private LogDao logDao;
	
	@Transactional(readOnly=true)
	public List<LogInfo> list(String msg,Integer start,Integer limit)throws DataAccessException{
		return logDao.list(msg, start, limit); 
	}
	
	@Transactional(readOnly=true)
	public Integer count(String msg)throws DataAccessException{
		return logDao.cout(msg);
	}
	
	@Transactional
	public void add(LogInfo log)throws DataAccessException{
		logDao.insert(log);
	}
	
	@Transactional
	public void deleteById(Integer logId)throws DataAccessException{
		logDao.deleteById(logId);
	}
}
