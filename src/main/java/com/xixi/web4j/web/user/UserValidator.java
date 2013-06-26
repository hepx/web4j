package com.xixi.web4j.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xixi.web4j.model.UserInfoBean;
import com.xixi.web4j.service.UserInfoService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserInfoService userInfoService;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserInfoBean.class==clazz;
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserInfoBean user=(UserInfoBean)target;
		String userName=user.getUserName();
		if(user.isNew()&&this.userInfoService.isExistCurrentUser(userName)){
			errors.rejectValue("userName", "field.exists", new Object[]{userName}, "user is exists");
		}
	}
}
