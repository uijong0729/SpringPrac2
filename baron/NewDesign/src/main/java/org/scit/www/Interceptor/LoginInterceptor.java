package org.scit.www.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		
		if(loginId == null)
		{	
			//현재 url + 액션발동
			response.sendRedirect(request.getContextPath() + "/signin");
			return false;
		}
		else 
		{

			return super.preHandle(request, response, handler);
		}
	}
	
	
}
