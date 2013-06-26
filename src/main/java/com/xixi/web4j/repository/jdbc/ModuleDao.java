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

import com.xixi.web4j.model.ModuleBean;

@Repository
public class ModuleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate NPJdbcTemplate;
	
	private SimpleJdbcInsert moduleInsert;
	
	private String SQL_SELECT="select moduleId,moduleCode,moduleDesc,moduleName,parentCode,moduleIcon,moduleUrl,sort from tb_module";
	
	public List<ModuleBean> list(Integer start,Integer limit)throws DataAccessException{
		String sql=SQL_SELECT+" order by moduleId desc";
		sql+=" limit "+start+","+limit;
		return this.jdbcTemplate.query(sql,new ModuleRowMapper());
	}
	
	public ModuleBean findById(Integer moduleId)throws DataAccessException{
		String sql=SQL_SELECT+" where moduleId="+moduleId;
		return jdbcTemplate.queryForObject(sql, new ModuleRowMapper());
	}
	
	public boolean isExistsModule(String moduleName)throws DataAccessException{
		String sql="select count(moduleId) as rows from tb_module where moduleName='"+moduleName+"'";
		Number num=this.jdbcTemplate.queryForObject(sql, Integer.class);
		return num.intValue()>0?true:false;
	}
	
	public Integer count()throws DataAccessException{
		String sql="select count(moduleId) as rows from tb_module";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<ModuleBean> listOneLevelMenu()throws DataAccessException{
		String oneLeveSql=SQL_SELECT+" where parentCode is null or parentCode='' order by sort";
		return this.jdbcTemplate.query(oneLeveSql, new ModuleRowMapper());
	}
	
	public List<ModuleBean>listTwoLevelMenu(String parentCode)throws DataAccessException{
		String twoLeveSql=SQL_SELECT+" where parentCode=? order by sort";
		return this.jdbcTemplate.query(twoLeveSql, new ModuleRowMapper(), parentCode);
	}
	
	public void saveOrUpdate(ModuleBean module)throws DataAccessException{
		BeanPropertySqlParameterSource parameterSource=new BeanPropertySqlParameterSource(module);
		if(module.isNew()){
			if(moduleInsert==null){
				moduleInsert=new SimpleJdbcInsert(this.jdbcTemplate).withTableName("tb_module").usingGeneratedKeyColumns("moduleId");
			}
			Number key=moduleInsert.executeAndReturnKey(parameterSource);
			module.setModuleId(key.intValue());
		}else{
			this.NPJdbcTemplate.update("update tb_module set moduleCode=:moduleCode,moduleDesc=:moduleDesc," +
					"moduleName=:moduleName,parentCode=:parentCode,moduleUrl=:moduleUrl,sort=:sort where moduleId=:moduleId",parameterSource);
		}
	}
	
	public void deleteById(Integer moduleId)throws DataAccessException{
		String sql="delete from tb_module where moduleId="+moduleId;
		this.jdbcTemplate.update(sql);
	}

	private class ModuleRowMapper implements RowMapper<ModuleBean>{

		public ModuleBean mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			ModuleBean module=new ModuleBean();
			module.setModuleId(rs.getInt("moduleId"));
			module.setModuleCode(rs.getString("moduleCode"));
			module.setModuleDesc(rs.getString("moduleDesc"));
			module.setModuleName(rs.getString("moduleName"));
			module.setParentCode(rs.getString("parentCode"));
			module.setModuleIcon(rs.getString("moduleIcon"));
			module.setModuleUrl(rs.getString("moduleUrl"));
			module.setSort(rs.getInt("sort"));
			return module;
		}
		
	}
}
