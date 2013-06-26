package com.xixi.web4j.web.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xixi.web4j.model.ModuleBean;
import com.xixi.web4j.service.ModuleService;

@Component
public class ModuleValidator implements Validator {

	@Autowired
	private ModuleService moduleService;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ModuleBean.class==clazz;
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ModuleBean module=(ModuleBean)target;
		String moduleName=module.getModuleName();
		if(module.isNew()&&this.moduleService.isExistsModule(moduleName)){
			errors.rejectValue("moduleName", "field.exists", new Object[]{moduleName}, "module is exists");
		}
	}
}
