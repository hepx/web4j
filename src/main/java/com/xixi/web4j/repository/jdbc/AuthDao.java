package com.xixi.web4j.repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Integer> list(Integer roleId)throws DataAccessException{
		String sql="select moduleId from tb_auth where roleId="+roleId;
		return this.jdbcTemplate.queryForList(sql, Integer.class);
	}
	
	public void saveOrUpdate(final Integer roleId,final List<Integer>moduleIds)throws DataAccessException{
		String delete_sql="delete from tb_auth where roleId="+roleId;
		String install_sql="insert into tb_auth(roleId,moduleId) values(?,?)";
		this.jdbcTemplate.update(delete_sql);
		this.jdbcTemplate.batchUpdate(install_sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// TODO Auto-generated method stub
				Integer moduleId=moduleIds.get(i);
				ps.setInt(1, roleId);
				ps.setInt(2, moduleId);
			}
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return moduleIds.size();
			}
		});
	}
}
