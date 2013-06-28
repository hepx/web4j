package com.xixi.web4j.web.user;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xixi.web4j.model.PageDataBean;
import com.xixi.web4j.model.RoleBean;
import com.xixi.web4j.model.UserInfoBean;
import com.xixi.web4j.service.RoleService;
import com.xixi.web4j.service.UserInfoService;
import com.xixi.web4j.util.MD5Util;

/**
 * 用户管理ACTION
 * @author xixi
 * @date 2013-6-8
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserAction {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserValidator userValidator;
	
	@InitBinder("user")
	public void initBinder(WebDataBinder binder){
		binder.addValidators(userValidator);
	}
	
	@ModelAttribute("roles")//角色下拉项
	public List<RoleBean> getRoleBeans(){
		return this.roleService.list();
	}
	
	@RequestMapping("show")
	public String showUsers(Model model){
		return "/user/userList";
	}
	
	@RequestMapping("list")
	public @ResponseBody PageDataBean<Map<String,Object>> list(
			@RequestParam(defaultValue = "0") Integer start,
			@RequestParam(defaultValue = "20") Integer limit,
			@RequestParam(required = false) Integer maxPage) {
		PageDataBean<Map<String,Object>> pageData=new PageDataBean<Map<String,Object>>(start, limit, maxPage);
		List<Map<String,Object>> userInfos = this.userInfoService.listMap(start, limit);
		pageData.setRows(userInfos);
		if(pageData.getMaxPage()==null){
			Integer count=this.userInfoService.count();
			pageData.countMaxPage(count);
		}
		return pageData;
	}
	
	@RequestMapping(value="new",method=RequestMethod.GET)
	public String initNewUserForm(@ModelAttribute("user") UserInfoBean user) {
		return "/user/createOrUpdateUserForm";
	}
	
	@RequestMapping(value="new",method=RequestMethod.POST)
	public String processNewUserForm(@Valid @ModelAttribute("user")UserInfoBean user,BindingResult result){
		if(result.hasErrors()){
			return "/user/createOrUpdateUserForm";
		}else{
			user.setStatus(true);//默认开启
			user.setPassword(MD5Util.MD5(user.getPassword()));//MD5加密
			this.userInfoService.saveOrUpdate(user);
			return "/user/userList";
		}
	}

	@RequestMapping(value="update/{userId}",method=RequestMethod.GET)
	public String initUpdateUserForm(@PathVariable("userId") Integer userId,Model model) {
		UserInfoBean user = this.userInfoService.findById(userId);
		model.addAttribute("user", user);
		return "/user/createOrUpdateUserForm";
	}
	
	@RequestMapping(value="update/{userId}",method=RequestMethod.POST)
	public String processUpdateUserForm(@Valid @ModelAttribute("user")UserInfoBean user,BindingResult result,Model model){
		if(result.hasErrors()){
			return "/user/createOrUpdateUserForm";
		}else{
			this.userInfoService.saveOrUpdate(user);
			return "/user/userList";
		}
	}
	
	@RequestMapping("delete/{userId}")
	public @ResponseBody String deleteUser(@PathVariable("userId")Integer userId){
		this.userInfoService.deleteById(userId);
		return "success";
	}
}
