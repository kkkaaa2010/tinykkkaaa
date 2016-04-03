package com.tinykkkaaa.platform.core.console.util.primarykey;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tinykkkaaa.platform.framework.config.Component;
import com.tinykkkaaa.platform.framework.util.DOMTool;

public class DBPrimaryKeyGenertorInitComponent implements Component {

	@Override
	public void dealNode(Node node) throws Exception {
	    NodeList generators = DOMTool.getMultiNodes(node, "generator");
	    for (int i = 0; i < generators.getLength(); i++) {
	    	Node generator = generators.item(i);
	    	String className = DOMTool.getAttributeValue((Element)generator, "class");
	    	String siteCode = DOMTool.getSingleNodeValue(generator, "siteCode");
	    	String hashCodeLength = DOMTool.getSingleNodeValue(generator, "hashCodeLength");
	    	String keyLength = DOMTool.getSingleNodeValue(generator, "keyLength");
	    	String fetchSize = DOMTool.getSingleNodeValue(generator, "fetchSize");
	    	String increment = DOMTool.getSingleNodeValue(generator, "increment");

	    	Map<String, String> parameters = new HashMap<String, String>();
	    	parameters.put("siteCode", siteCode);
	    	parameters.put("hashCodeLength", hashCodeLength);
	    	parameters.put("keyLength", keyLength);
	    	parameters.put("fetchSize", fetchSize);
	    	parameters.put("increment", increment);

	    	setGenerator(className, parameters);
	    }
	}
	    
	private void setGenerator(String className, Map<String, String> parameters)
			throws Exception {
		IDBPrimaryKeyGenerator generator = (IDBPrimaryKeyGenerator) Class
				.forName(className).getConstructor(new Class[] { Map.class })
				.newInstance(new Object[] { parameters });
		DBPrimaryKeyGeneratorMgr.getInstance()
				.setPrimaryKeyGenerator(generator);
	}

}
