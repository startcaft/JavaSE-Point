package com.startcaft.springweb.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringServletContextListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent event)  { 
    	
    	ServletContext context = event.getServletContext();
    	
    	//1,获取到Spring 配置文件的名称和位置
    	String fileName = context.getInitParameter("configLocation");
    	
    	//2,创建IOC容器
    	ApplicationContext ctx = new ClassPathXmlApplicationContext(fileName);
    	
    	//3,将IOC容器放入到ServletContext中的一个属性中
    	context.setAttribute("applicationContext", ctx);
    }
	
}
