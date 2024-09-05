package com.winter.app.home.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.winter.app.members.MemberVO;
import com.winter.app.members.RoleVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		
		if(memberVO == null) {
			response.sendRedirect("/member/login");
			return false;
		}
		
		for(RoleVO roleVO:memberVO.getVos()) {
			if(roleVO.getRoleName().equals("ROLE_ADMIN")) {
				return true;
				
			}
		}
		
		request.setAttribute("msg", "관리자 전용");
		request.setAttribute("path", "/");
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
		view.forward(request, response);
		
		return false;
	}

}
