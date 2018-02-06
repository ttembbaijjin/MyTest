package cn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.poi.pojo.Member;

public class MyFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request2 = (HttpServletRequest) request;
		Member member = (Member)request2.getSession().getAttribute("member");
		if(member==null){
			// post解决乱码
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("请先登录,未登录不能直接下单");
			//response.setStatus(302);
			// 下面的代码是用来跳转的
			//response.setHeader("Location", "/login2/login/index2.html");
			
		}else {
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
