package com.tinykkkaaa.platform.framework.web;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.tinykkkaaa.platform.framework.config.plugin.ComponentPlugin;

public class ComponentInitBean implements InitializingBean, ServletContextAware {
	
	private ServletContext context;
	private String componentPath;

	public String getComponentPath() {
		return componentPath;
	}

	public void setComponentPath(String componentPath) {
		this.componentPath = componentPath;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("------------------------------初始化系统组件开始------------------------------");
		new ComponentPlugin().init(this.context, componentPath);
		System.out.println("------------------------------初始化系统组件结束------------------------------");
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

}
