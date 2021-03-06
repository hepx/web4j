package com.xixi.web4j.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.xixi.web4j.model.UserInfoBean;

@Repository
public class UserInfoDao{

	@Autowired
	private NamedParameterJdbcTemplate NPJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcInsert insertUser;
	
	//private String SQL_SELECT="select userId,userName,email,phone,status from tb_userinfo";
	
	private String SQL_SELECT_FK="select A.userId,A.userName,A.email,A.phone,A.status,B.roleId,B.roleName from tb_userinfo A " +
				"left join tb_role B on A.roleId=B.roleId";
	
	public List<Map<String,Object>> listMap(Integer start,Integer limit)throws DataAccessException{
		String sql=SQL_SELECT_FK+" order by userId desc limit :start,:limit";
		Map<String,Object>paramMap=new HashMap<String, Object>();
		paramMap.put("start", start);
		paramMap.put("limit", limit);
		return this.NPJdbcTemplate.queryForList(sql, paramMap);
	}
	
	public Integer count()throws DataAccessException{
		String sql="select count(userId) as rows from tb_userinfo";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public UserInfoBean find(String userName,String password)throws DataAccessException{
		String sql=SQL_SELECT_FK+" where A.userName=:userName and A.password=:password";
		Map<String,Object>paramMap=new HashMap<String,Object>();
		paramMap.put("userName", userName);
		paramMap.put("password", password);
		List<UserInfoBean> users= this.NPJdbcTemplate.query(sql, paramMap,new UserInfoRowMapper(true));
		if(users!=null&&users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
	}
	
	public UserInfoBean findById(Integer userId)throws DataAccessException{
		String sql=SQL_SELECT_FK+" where userId="+userId;
		return this.jdbcTemplate.queryForObject(sql, new UserInfoRowMapper(true));
	}
	
	public boolean isExistCurrentUser(String userName)throws DataAccessException{
		String sql="select count(userId) as rows from tb_userinfo where userName='"+userName+"'";
		Number num= this.jdbcTemplate.queryForObject(sql, Integer.class);
		return (num.intValue()>0?true:false);
	}
	
	public void saveOrUpdate(UserInfoBean user)throws DataAccessException{
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		if(user.isNew()){
			if(this.insertUser==null){
				this.insertUser=new SimpleJdbcInsert(this.jdbcTemplate).withTableName("tb_userinfo").usingGeneratedKeyColumns("userId");
			}
			Number newKey=this.insertUser.executeAndReturnKey(parameterSource);
			user.setUserId(newKey.intValue());
		}else{
			this.NPJdbcTemplate.update("update tb_userinfo set userName=:userName,email=:email,phone=:phone,status=:status" +
					",roleId=:roleId where userId=:userId",parameterSource);
		}
	}
	
	public void modifyPwd(Integer userId,String newPwd)throws DataAccessException{
		String uppwd_sql="update tb_userinfo set password=? where userId=?";
		this.jdbcTemplate.update(uppwd_sql, newPwd,userId);
	}
	
	public void deleteById(Integer userId)throws DataAccessException{
		this.jdbcTemplate.update("delete from tb_userinfo where userId=?", userId);
	}
	
	private class UserInfoRowMapper implements RowMapper<UserInfoBean>{
		
		private boolean isFetchRole=false;
		//public UserInfoRowMapper(){}
		
		public UserInfoRowMapper(boolean isFetchRole){
			this.isFetchRole=isFetchRole;
		}
		public UserInfoBean mapRow(ResultSet re, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			UserInfoBean user=new UserInfoBean();
			user.setUserId(re.getInt("userId"));
			user.setUserName(re.getString("userName"));
			user.setEmail(re.getString("email"));
			user.setPhone(re.getString("phone"));
			user.setStatus(re.getBoolean("status"));
			if(this.isFetchRole){
				user.setRoleId(re.getInt("roleId"));
			}
			return user;
		}
	}
	
}
