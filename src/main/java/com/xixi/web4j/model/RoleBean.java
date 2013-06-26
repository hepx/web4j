package com.xixi.web4j.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 角色
 * @author xixi
 * @date 2013-6-24
 *
 */
public class RoleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7135749185404420719L;

	private Integer roleId;
	
	@NotEmpty
	private String roleName;// 角色名称
	
	@NotEmpty
	private String roleCode;//角色代码

	private String roleDesc;// 描述
	
	@JsonIgnore
	public boolean isNew(){
		return (roleId==null);
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	
}
