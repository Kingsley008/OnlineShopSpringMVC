package com.netease.shop.web.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Loginfilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
	}

	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 先进行转换
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		if(session.getAttribute("username")==null){
			//判断session中是否有user这个属性 
			//如果没有将这个用户视为非法用户，重定向到登入界面
			HttpServletResponse res = (HttpServletResponse) response;
			request.setAttribute("error", "2");
			request.getRequestDispatcher("/wbd/login").forward(req, response);
		}else{
			chain.doFilter(request, response);
		}
		
		System.out.println("filter doFilter ");
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("filter destroy ");
	}
}