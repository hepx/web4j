package com.xixi.web4j.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 模块
 * @author xixi
 * @date 2013-6-8
 *
 */

public class ModuleBean implements Serializable {

	private static final long serialVersionUID = 562800776494123188L;

	private Integer moduleId;
	
	@NotEmpty
	private String moduleName;
	
	private String moduleDesc;
	
	@NotEmpty
	private String moduleCode;
	
	private String parentCode;
	
	private Integer sort;
	
	private String moduleIcon;
	
	@NotEmpty
	private String moduleUrl;
	
	@JsonIgnore
	public boolean isNew(){
		return (this.moduleId==null);
	}

	public Integer getModuleId() {
		return moduleId;
	}


	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}


	public String getModuleName() {
		return moduleName;
	}


	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}


	public String getModuleDesc() {
		return moduleDesc;
	}


	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}


	public String getModuleCode() {
		return moduleCode;
	}


	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}


	public String getParentCode() {
		return parentCode;
	}


	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}


	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public String getModuleUrl() {
		return moduleUrl;
	}


	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getModuleIcon() {
		return moduleIcon;
	}

	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}
	
}
