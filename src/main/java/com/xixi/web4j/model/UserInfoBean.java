package com.xixi.web4j.model;

import java.io.Serializable;

import javax.validation.constraints.Digits;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 用户信息Bean
 * @author xixi
 * @date 2013-6-5
 *
 */
public class UserInfoBean implements Serializable {

	private static final long serialVersionUID = 3971102391001431601L;

	private Integer userId;
	
	@NotEmpty
	private String userName;
	
	@Length(min=6,max=16)
	private String password;
	
	@Email
	private String email;
	
	@Digits(fraction=0,integer=11)
	private String phone;
	
	private Boolean status;
	
	//角色
	private Integer roleId;
	
	@JsonIgnore
	public boolean isNew(){
		return (this.userId==null);
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	
}
