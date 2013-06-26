package com.xixi.web4j.web.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xixi.web4j.model.LogInfo;
import com.xixi.web4j.model.PageDataBean;
import com.xixi.web4j.service.LogService;

/**
 * 日志ACTION
 * @author hepx
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/log")
public class LogAction {

	@Autowired
	private LogService logService;
	
	@RequestMapping("show")
	public String showPage(){
		return "/log/logList";
	}
	
	@RequestMapping("list")
	public @ResponseBody PageDataBean<LogInfo> list(
			@RequestParam(required=false,defaultValue="0")Integer start,
			@RequestParam(required=false,defaultValue="20")Integer limit,
			@RequestParam(required=false)Integer maxPage,
			@RequestParam(required=false)String msg,
			Model model){
		PageDataBean<LogInfo> pageData=new PageDataBean<LogInfo>(start,limit,maxPage);
		List<LogInfo>logs=logService.list(msg, start, limit);
		pageData.setRows(logs);
		if(pageData.getMaxPage()==null){
			Integer total=logService.count(msg);
			pageData.countMaxPage(total);
		}
		return pageData;
	}
	
	@RequestMapping(value="delete/{logId}")
	public @ResponseBody String delete(@PathVariable("logId")Integer logId){
		logService.deleteById(logId);
		return "success";
	}
	
}
