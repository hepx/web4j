package com.xixi.web4j.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomSimpleExceptionResolver extends
		SimpleMappingExceptionResolver {
	
	private String ajaxErrorView;
	private String ajaxDefaultErrorMessage = "unknown exception system";
	private boolean ajaxShowTechMessage = false;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		if(isAjax(request)){
			String exceptionMessage=this.ajaxDefaultErrorMessage;
			if(this.ajaxShowTechMessage){
				exceptionMessage+="\n"+getExceptionMessage(ex);
			}
			ModelAndView m=new ModelAndView(this.ajaxErrorView);
			m.addObject("exceptionMessage", exceptionMessage);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return m;
		}else{
			return super.doResolveException(request, response, handler, ex);
		}
	}
	 private String getExceptionMessage(Throwable e) {
        String message = "";
        while( e != null ) {
            message += e.getMessage() + "\n";
            e = e.getCause();
        }
        return message;
	 }
	 
	 private boolean isAjax(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	 }

	public String getAjaxErrorView() {
		return ajaxErrorView;
	}

	public void setAjaxErrorView(String ajaxErrorView) {
		this.ajaxErrorView = ajaxErrorView;
	}

	public String getAjaxDefaultErrorMessage() {
		return ajaxDefaultErrorMessage;
	}

	public void setAjaxDefaultErrorMessage(String ajaxDefaultErrorMessage) {
		this.ajaxDefaultErrorMessage = ajaxDefaultErrorMessage;
	}

	public boolean isAjaxShowTechMessage() {
		return ajaxShowTechMessage;
	}

	public void setAjaxShowTechMessage(boolean ajaxShowTechMessage) {
		this.ajaxShowTechMessage = ajaxShowTechMessage;
	}
	 
	 
}
