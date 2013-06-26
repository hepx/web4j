package com.xixi.web4j.web.role;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xixi.web4j.model.RoleBean;
import com.xixi.web4j.service.RoleService;

@Controller
@Scope("prototype")
@RequestMapping("/role")
public class RoleAction {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="show",method=RequestMethod.GET)
	public String show(Model model){
		List<RoleBean> roles=this.roleService.list();
		model.addAttribute("roles", roles);
		return "role/roleList";
	}
	
	@RequestMapping(value="new",method=RequestMethod.GET)
	public String initNewRoleForm(@ModelAttribute("role")RoleBean role){
		return "role/createOrUpdateRoleForm";
	}
	
	@RequestMapping(value="new",method=RequestMethod.POST)
	public String processNewRoleForm(
			@Valid @ModelAttribute("role") RoleBean role, BindingResult result,Model model) {
		if(result.hasErrors()){
			return "role/createOrUpdateRoleForm";
		}else{
			this.roleService.saveOrUpdate(role);
			return show(model);
		}
	}
	
	@RequestMapping(value="update/{roleId}",method=RequestMethod.GET)
	public String initUpdateRoleForm(@PathVariable("roleId")Integer roleId,Model model){
		RoleBean role=this.roleService.findById(roleId);
		model.addAttribute("role",role);
		return "role/createOrUpdateRoleForm";
	}
	
	@RequestMapping(value="update/{roleId}",method=RequestMethod.POST)
	public String processUpdateRoleForm(@Valid @ModelAttribute("role")RoleBean role,BindingResult result,Model model){
		if(result.hasErrors()){
			return "role/createOrUpdateRoleForm";	
		}else{
			this.roleService.saveOrUpdate(role);
			return show(model);
		}
	}
	
	@RequestMapping(value="delete/{roleId}",method=RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("roleId")Integer roleId){
		this.roleService.deleteById(roleId);
		return "success";
	}
	
}
