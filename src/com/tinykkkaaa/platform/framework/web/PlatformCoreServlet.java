package com.tinykkkaaa.platform.framework.web;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import org.springframework.web.context.ServletContextAware;

public class PlatformCoreServlet implements ServletContextAware {

	protected ServletContext servletContext;
	
	@PostConstruct
	public void run(){}

	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

}
