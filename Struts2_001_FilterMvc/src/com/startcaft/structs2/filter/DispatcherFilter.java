package com.startcaft.structs2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("*.action")
public class DispatcherFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//1.先获取到servletPath
		String servletPath = req.getServletPath();
		String path = null;
		
		if(servletPath.equals("/product_input.action")){
			path = "/WEB-INF/pages/product_input.jsp";
		}
		
		if(servletPath.equals("/product_save.action")){
			
			//获取客户端提交的参数
			String productName = request.getParameter("productName");
			String productDesc = request.getParameter("productDesc");
			String productPrice = request.getParameter("productPrice");
			
			Product product = new Product(null, productName, productDesc, Double.parseDouble(productPrice));
			
			System.out.println("save product:" + product);
			
			product.setProductId(1001);
			
			request.setAttribute("product", product);
			
			path = "/WEB-INF/pages/product_save.jsp";
		}
		
		if(path != null){
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
