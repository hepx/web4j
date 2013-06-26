package com.xixi.web4j.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.xixi.web4j.model.LogInfo;

@Repository
public class LogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcInsert logInsert;
	
	String LOG_SQL="select logId,operator,msg,logTime from tb_log";
	
	public List<LogInfo> list(String msg,Integer start,Integer limit)throws DataAccessException{
		String sql="";
		if(!StringUtils.isEmpty(msg)){
			sql=LOG_SQL+" where msg like '"+msg+"%'";
		}else{
			sql=LOG_SQL;
		}
		sql+=" order by logId desc";
		sql+=" limit "+start+","+limit;
		return this.jdbcTemplate.query(sql, new LogRowMapper());
	}
	
	public Integer cout(String msg){
		String sql="select count(logId) as rows from tb_log";
		if(!StringUtils.isEmpty(msg)){
			sql+=" where msg like '"+msg+"%'";
		}
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public void insert(LogInfo log)throws DataAccessException{
		BeanPropertySqlParameterSource  parameterSource =new BeanPropertySqlParameterSource(log);
		if(logInsert==null){
			logInsert=new SimpleJdbcInsert(this.jdbcTemplate).withTableName("tb_log").usingGeneratedKeyColumns("logId");
		}
		this.logInsert.execute(parameterSource);
	}
	
	public void deleteById(Integer logId)throws DataAccessException{
		String sql="delete from tb_log where logId=?";
		this.jdbcTemplate.update(sql, logId);
	}
	
	private class LogRowMapper implements RowMapper<LogInfo>{
		public LogInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			LogInfo log=new LogInfo();
			log.setLogId(rs.getInt("logId"));
			log.setOperator(rs.getString("operator"));
			log.setMsg(rs.getString("msg"));
			log.setLogTime(rs.getTimestamp("logTime"));
			return log;
		}
	}
}
