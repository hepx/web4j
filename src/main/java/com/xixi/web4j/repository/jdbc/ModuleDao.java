package com.xixi.web4j.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.xixi.web4j.model.ModuleBean;

@Repository
public class ModuleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate NPJdbcTemplate;
	
	private SimpleJdbcInsert moduleInsert;
	
	private String SQL_SELECT="select A.moduleId,A.moduleCode,A.moduleDesc,A.moduleName," +
			"A.parentCode,A.moduleIcon,A.moduleUrl,A.sort from tb_module A";
	
	private String SQL_MAP="select moduleId,moduleName,moduleCode from tb_module";
	
	public List<ModuleBean> list(Integer start,Integer limit)throws DataAccessException{
		String sql=SQL_SELECT+" order by moduleId desc";
		sql+=" limit "+start+","+limit;
		return this.jdbcTemplate.query(sql,new ModuleRowMapper());
	}
	
	public List<Map<String,Object>>list()throws DataAccessException{
		String sqlone=SQL_MAP+" where parentCode is null or parentCode='' order by sort";
		String sqltwo=SQL_MAP+" where parentCode=? order by sort";
		List<Map<String,Object>> modules=this.jdbcTemplate.queryForList(sqlone);
		for(Map<String,Object> map:modules){
			String moduleCode=(String)map.get("moduleCode");
			List<Map<String,Object>> subModules=this.jdbcTemplate.queryForList(sqltwo, moduleCode);
			map.put("subModules", subModules);
		}
		return modules;
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
	
	public List<ModuleBean> listOneLevelMenu(Integer roleId)throws DataAccessException{
		String oneLeveSql=SQL_SELECT+" inner join tb_auth B on A.moduleId=B.moduleId " +
				"where (A.parentCode is null or A.parentCode='') and B.roleId=? order by A.sort";
		return this.jdbcTemplate.query(oneLeveSql, new ModuleRowMapper(),roleId);
	}
	
	public List<ModuleBean>listTwoLevelMenu(Integer roleId,String parentCode)throws DataAccessException{
		String twoLeveSql=SQL_SELECT+" inner join tb_auth B on A.moduleId=B.moduleId " +
				"where B.roleId=? and A.parentCode=? order by A.sort";
		return this.jdbcTemplate.query(twoLeveSql, new ModuleRowMapper(), roleId,parentCode);
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
