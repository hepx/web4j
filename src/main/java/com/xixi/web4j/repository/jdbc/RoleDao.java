package com.xixi.web4j.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.xixi.web4j.model.RoleBean;

@Repository
public class RoleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate NPJdbcTemplate;
	
	private SimpleJdbcInsert roleInsert;
	
	private String SELECT_SQL="select roleId,roleName,roleCode,roleDesc from tb_role";
	
	public List<RoleBean> list()throws DataAccessException{
		String sql=SELECT_SQL+" order by roleId asc";
		return this.jdbcTemplate.query(sql, new RoleRowMapper());
	}
	
	public RoleBean findById(Integer roleId)throws DataAccessException{
		String sql=SELECT_SQL+" where roleId="+roleId;
		return this.jdbcTemplate.queryForObject(sql, new RoleRowMapper());
	}
	
	public void saveOrUpdate(RoleBean role)throws DataAccessException{
		BeanPropertySqlParameterSource parameterSource=new BeanPropertySqlParameterSource(role);
		if(role.isNew()){
			if(this.roleInsert==null){
				this.roleInsert=new SimpleJdbcInsert(jdbcTemplate).withTableName("tb_role").usingGeneratedKeyColumns("roleId");
			}
			Number key=this.roleInsert.executeAndReturnKey(parameterSource);
			role.setRoleId(key.intValue());
		}else{
			this.NPJdbcTemplate
					.update("update tb_role set roleName=:roleName,roleCode=:roleCode,roleDesc=:roleDesc where roleId=:roleId",
							parameterSource);
		}
	}
	
	public void deleteById(Integer roleId)throws DataAccessException{
		this.jdbcTemplate.update("delete from tb_role where roleId="+roleId);
	}
	
	private class RoleRowMapper implements RowMapper<RoleBean>{
		public RoleBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			RoleBean role=new RoleBean();
			role.setRoleId(rs.getInt("roleId"));
			role.setRoleName(rs.getString("roleName"));
			role.setRoleCode(rs.getString("roleCode"));
			role.setRoleDesc(rs.getString("roleDesc"));
			return role;
		}
		
	}
}
