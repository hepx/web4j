package com.xixi.web4j.web.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xixi.web4j.service.AuthService;

@Controller
@Scope("prototype")
@RequestMapping("/auth")
public class AuthAction {

	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="queryAuth",params="roleId")
	public @ResponseBody Map<String,Object> queryAuth(Integer roleId){
		//查询所有模块
		List<Map<String,Object>>modules=this.authService.queryAllModules();
		//根据角色ID，查询分配给些角色的模块ID
		List<Integer>moduleIds=this.authService.queryRoleModules(roleId);
		//封将返回JSON
		Map<String,Object>authMap=new HashMap<String,Object>();
		authMap.put("modules", modules);
		authMap.put("moduleIds", moduleIds);
		return authMap;
	}
	
	@RequestMapping(value="saveAuth",method=RequestMethod.POST)
	public @ResponseBody String saveOrUpdateAuth(@RequestParam("roleId")Integer roleId,
			@RequestParam(value="moduleIds",required=false,defaultValue="")List<Integer>moduleIds){
		String result="success";
		if(roleId!=null&&moduleIds!=null){
			this.authService.saveOrUpdateAuth(roleId, moduleIds);
		}else{
			result="缺少必要参数";
		}
		return result;
	}
}
