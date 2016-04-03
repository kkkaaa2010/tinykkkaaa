package com.tinykkkaaa.platform.framework.config.plugin;

import javax.servlet.ServletContext;

import com.tinykkkaaa.platform.framework.config.ComponentConfig;

public class ComponentPlugin{
	
	public void init(ServletContext context, String componentPath){
		try {
			ComponentConfig componentConfig = new ComponentConfig(context, componentPath);
			componentConfig.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
