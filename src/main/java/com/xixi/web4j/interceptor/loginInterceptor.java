package com.xixi.web4j.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ser.FilterProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xixi.web4j.model.UserInfoBean;
/**
 * 检查登录拦截器
 * @author xixi
 * @date 2013-6-26
 *
 */
public class loginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String path=request.getServletPath();
		//不好配置这个放行的URL，所以硬编码了
		if("/".equals(path)||"/login".equals(path)||path.startsWith("/resources")){
			return true;
		}else{
			UserInfoBean loginUser=(UserInfoBean)request.getSession().getAttribute("loginUser");
			if(loginUser!=null&&loginUser.getUserName()!=null){//用户已登录
				return true;
			}else{//没有登录
				response.sendRedirect(request.getContextPath()+"/");
				return false;
			}
		}
	}
}
