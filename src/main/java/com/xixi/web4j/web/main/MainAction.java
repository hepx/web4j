package com.xixi.web4j.web.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.xixi.web4j.model.LogInfo;
import com.xixi.web4j.model.ModuleBean;
import com.xixi.web4j.model.UserInfoBean;
import com.xixi.web4j.service.LogService;
import com.xixi.web4j.service.ModuleService;
import com.xixi.web4j.service.UserInfoService;
import com.xixi.web4j.util.ToolUtils;

@Controller
@Scope("prototype")
@SessionAttributes(value={"loginUser","navMenus","leftMenus"})
public class MainAction {
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private LogService logService;
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST,params={"userName","password"})
	public String login(String userName,String password,HttpServletRequest request,Model model){
		if(ToolUtils.isEmpty(userName)){
			model.addAttribute("error","用户名不能为空！");
			return "login";
		}
		if(ToolUtils.isEmpty(password)){
			model.addAttribute("error","密码不能为空！");
			return "login";
		}
		UserInfoBean loginUser=userInfoService.find(userName, password);
		if(loginUser==null){
			model.addAttribute("error","登录失败，用户名或密码错误！");
			return "login";
		}
		//记录登录日志
		String ip=null;
		if (request.getHeader("x-forwarded-for") == null) {  
	        ip=request.getRemoteAddr(); 
	    }else{
	    	ip=request.getHeader("x-forwarded-for");
	    }
		logService.add(new LogInfo(loginUser.getUserName(), "用户登录("+ip+")"));
		List<ModuleBean> navMenus=moduleService.listOneLevelMenu();
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("navMenus", navMenus);
		return "main/home";
	}
	
	@RequestMapping(value="/navModule",method=RequestMethod.GET)
	public String navModule(@RequestParam String moduleCode,Model model){
		List<ModuleBean> leftMenus=moduleService.listTwoLevelMenu(moduleCode);
		model.addAttribute("leftMenus",leftMenus);
		return "main/main";
	}
	
	@RequestMapping("/home")
	public String goHome(@ModelAttribute(value="loginUser")UserInfoBean loginUser){
		if(loginUser!=null&&loginUser.getUserName()!=null){
			return "main/home";
		}else{
			return "login";
		}
	}
	
	@RequestMapping(value="/quit")
	public String quit(SessionStatus status){
		//退出之前清险session中的对象
		status.setComplete();
		return "login";
	}
}
