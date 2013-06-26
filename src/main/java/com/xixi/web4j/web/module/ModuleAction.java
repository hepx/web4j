package com.xixi.web4j.web.module;

import java.util.List;

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

import com.xixi.web4j.model.ModuleBean;
import com.xixi.web4j.model.PageDataBean;
import com.xixi.web4j.service.ModuleService;

@Controller
@Scope("prototype")
@RequestMapping("/module")
public class ModuleAction {

	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private ModuleValidator moduleValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(moduleValidator);
	}
	
	@RequestMapping(value="show",method=RequestMethod.GET)
	public String showModules(){
		return "module/moduleList";
	}
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	public @ResponseBody PageDataBean<ModuleBean> list(
			@RequestParam(defaultValue="0")Integer start,
			@RequestParam(defaultValue="20")Integer limit,
			@RequestParam(required=false)Integer maxPage){
		PageDataBean<ModuleBean> pageData=new PageDataBean<ModuleBean>(start, limit, maxPage);
		List<ModuleBean> modules=moduleService.list(start,limit);
		pageData.setRows(modules);
		if(pageData.getMaxPage()==null){
			Integer total=moduleService.count();
			pageData.countMaxPage(total);
		}
		return pageData;
	}
	
	@RequestMapping(value="new",method=RequestMethod.GET)
	public String initNewModuleForm(@ModelAttribute("module") ModuleBean module){
		return "module/createOrUpdateModuleForm";
	}
	
	@RequestMapping(value="new",method=RequestMethod.POST)
	public String processNewModuleForm(@Valid @ModelAttribute("module")ModuleBean module,BindingResult result){
		if(result.hasErrors()){
			return "module/createOrUpdateModuleForm";
		}else{
			moduleService.saveOrUpdate(module);
			return "module/moduleList";
		}
	}
	
	@RequestMapping(value="update/{moduleId}",method=RequestMethod.GET)
	public String initUpdateModuleForm(@PathVariable("moduleId")Integer moduleId,Model model){
		ModuleBean module=moduleService.findById(moduleId);
		model.addAttribute("module",module);
		return "module/createOrUpdateModuleForm";
	}

	@RequestMapping(value="update/{moduleId}",method=RequestMethod.POST)
	public String processUpdateModuleForm(@Valid @ModelAttribute("module")ModuleBean module,BindingResult result){
		if(result.hasErrors()){
			return "module/createOrUpdateModuleForm";
		}else{
			moduleService.saveOrUpdate(module);
			return "module/moduleList";
		}
	}
	
	@RequestMapping(value="delete/{moduleId}",method=RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("moduleId")Integer moduleId){
		moduleService.deleteById(moduleId);
		return "success";
	}
}
