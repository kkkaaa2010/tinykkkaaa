package com.tinykkkaaa.platform.framework.config;

import java.io.InputStream;
import javax.servlet.ServletContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tinykkkaaa.platform.framework.util.DOMTool;

public class ComponentConfig {
	private Document doc = null;
	private static final String CONFIG_COMPONENT = "component";
	private static final String CONFIG_HANDLER = "handler";
	private static final String CONFIG_NAME = "name";
	private static final String CONFIG_ISENABLED  = "isEnabled";
	
	public ComponentConfig(ServletContext context, String filePath){
		InputStream is = getInputStream(context, filePath);
		this.doc = DOMTool.loadDocumentFromInputStream(is);
	}
	
	public void load() throws Exception {

		NodeList nl = null;

		nl = DOMTool.getMultiNodes(this.doc, CONFIG_COMPONENT);
		if (nl == null) {
			return;
		}
		Node node = null;
		String className = null;
		Object handler = null;
		Component component = null;
		String name = null;
		String isEnabled = null;

		for (int i = 0; i < nl.getLength(); i++) {
			node = nl.item(i);
			name = DOMTool.getAttributeValue((Element) node, CONFIG_NAME);
			isEnabled = DOMTool.getAttributeValue((Element) node, CONFIG_ISENABLED);
			if ((isEnabled != null) && (isEnabled.equalsIgnoreCase("false"))) {
			} else {
				className = DOMTool.getAttributeValue((Element) node, CONFIG_HANDLER);
				if ((className == null) || (className.trim().length() == 0)) {
				} else {
					try {
						handler = Class.forName(className).newInstance();
						component = (Component) handler;
						component.dealNode(node);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static InputStream getInputStream(ServletContext context, String path) {
		InputStream is = context.getResourceAsStream(path);
		return is;
	}

}
