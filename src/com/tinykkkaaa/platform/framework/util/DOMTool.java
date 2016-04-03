package com.tinykkkaaa.platform.framework.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DOMTool {
	public static Document createNewDocument() {
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return document;
	}

	public static Document loadDocumentFromStr(String str) {
		Document document = null;
		try {
			InputStream is = new ByteArrayInputStream(str.getBytes());
			document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(new InputSource(is));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public static Document loadDocumentFromStr(String str, String charsetName) {
		Document document = null;

		if ((str == null) || (str.indexOf(">") < 0))
			return null;
		try {
			InputStream is = new ByteArrayInputStream(str.getBytes(charsetName));
			document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(new InputSource(is));
		} catch (Exception localException) {
		}

		return document;
	}

	public static Document loadDocumentFromFile(File f) {
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public static Document loadDocumentFromInputStream(InputStream is) {
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public static Document loadDocumentFromUri(String uri) {
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public static void appendChild(Node node, Node subNode) {
		node.appendChild(subNode);
	}

	public static boolean containsNode(Node node, String tagName) {
		return getSingleNode(node, tagName) != null;
	}

	public static Node getSingleNode(Node node, String tagName) {
		if ((node == null) || (tagName == null)) {
			return null;
		}
		Node singleNode = null;
		NodeList nodeList = null;

		if ((node instanceof Document))
			nodeList = ((Document) node).getElementsByTagName(tagName);
		else {
			nodeList = ((Element) node).getElementsByTagName(tagName);
		}
		if ((nodeList != null) && (nodeList.getLength() > 0))
			singleNode = nodeList.item(0);
		return singleNode;
	}

	public static Element getSingleElement(Node node, String tagName) {
		Element singleElement = null;
		if (containsNode(node, tagName))
			singleElement = (Element) getSingleNode(node, tagName);
		return singleElement;
	}

	public static NodeList getMultiNodes(Node node, String tagName) {
		if ((node == null) || (tagName == null)) {
			return null;
		}
		NodeList nodeList = null;

		if ((node instanceof Document))
			nodeList = ((Document) node).getElementsByTagName(tagName);
		else {
			nodeList = ((Element) node).getElementsByTagName(tagName);
		}
		return nodeList;
	}

	public static Node getNode(Node node, String tagName, int offset) {
		if ((node == null) || (tagName == null) || (offset < 0)) {
			return null;
		}
		Node result = null;

		NodeList nodeList = getMultiNodes(node, tagName);
		if (offset < nodeList.getLength())
			result = nodeList.item(offset);
		return result;
	}

	public static Element getElement(Node node, String tagName, int offset) {
		Node result = getNode(node, tagName, offset);
		if (result == null)
			return null;
		return (Element) result;
	}

	public static String getNodeValue(Node node) {
		if ((node == null) || ((node instanceof Document))) {
			return null;
		}
		NodeList nodelist = node.getChildNodes();
		if (nodelist.getLength() <= 0) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node eachNode = nodelist.item(i);
			if (eachNode.getNodeType() == 3)
				stringBuffer.append(eachNode.getNodeValue());
		}
		return stringBuffer.toString();
	}

	public static String getSingleNodeValue(Node node, String tagName) {
		Node singleNode = getSingleNode(node, tagName);
		return getNodeValue(singleNode);
	}

	public static String[] getMultiNodeValues(Node node, String tagName) {
		NodeList nodeList = getMultiNodes(node, tagName);
		if (nodeList == null) {
			return new String[0];
		}
		String[] nodeValues = new String[nodeList.getLength()];

		for (int i = 0; i < nodeList.getLength(); i++) {
			nodeValues[i] = getNodeValue(nodeList.item(i));
		}

		return nodeValues;
	}

	public static String getAttributeValue(Element element, String attrName) {
		if ((element == null) || (attrName == null)) {
			return null;
		}
		return element.getAttribute(attrName);
	}

	public static HashMap getAttributeValues(Element element) {
		HashMap attributes = new HashMap();

		NamedNodeMap map = element.getAttributes();

		for (int i = 0; i < map.getLength(); i++) {
			Attr attr = (Attr) map.item(i);
			attributes.put(attr.getName(), attr.getValue());
		}

		return attributes;
	}

	public static String getSingleAttributeValue(Node node, String tagName,
			String attrName) {
		String attributeValue = null;
		Element element = getSingleElement(node, tagName);
		if (element != null)
			attributeValue = element.getAttribute(attrName);
		return attributeValue;
	}

	public static String[] getMultiAttributeValue(Node node, String tagName,
			String attrName) {
		NodeList nodeList = getMultiNodes(node, tagName);
		if (nodeList == null) {
			return new String[0];
		}
		String[] attributeValues = new String[nodeList.getLength()];

		for (int i = 0; i < nodeList.getLength(); i++) {
			attributeValues[i] = getAttributeValue((Element) nodeList.item(i),
					attrName);
		}

		return attributeValues;
	}

	public static Element createElement(Document document, String elementName,
			String elementValue) {
		if ((document == null) || (elementName == null)) {
			return null;
		}
		Element element = document.createElement(elementName);
		setNodeValue(element, elementValue);

		return element;
	}

	public static Element createAndAppendRoot(Document document,
			String elementName, String elementValue) {
		if ((document == null) || (elementName == null)) {
			return null;
		}
		Element element = document.createElement(elementName);
		document.appendChild(element);
		setNodeValue(element, elementValue);

		return element;
	}

	public static Element createAndAppendElement(Node node, String elementName,
			String elementValue) {
		if ((node == null) || (elementName == null)) {
			return null;
		}
		Document document = null;
		if ((node instanceof Document))
			document = (Document) node;
		else
			document = node.getOwnerDocument();
		Element element = document.createElement(elementName);
		node.appendChild(element);
		setNodeValue(element, elementValue);

		return element;
	}

	public static void createAndAppendMultiElement(Node node,
			String elementName, String[] elementValues) {
		if ((node == null) || (elementName == null) || (elementValues == null)
				|| (elementValues.length == 0) || ((node instanceof Document))) {
			return;
		}
		Document document = node.getOwnerDocument();
		for (int i = 0; i < elementValues.length; i++) {
			Element eachElement = document.createElement(elementName);
			node.appendChild(eachElement);
			setNodeValue(eachElement, elementValues[i]);
		}
	}

	public static void setNodeValue(Node node, String nodeValue) {
		if ((node == null) || ((node instanceof Document))) {
			return;
		}
		Document document = node.getOwnerDocument();
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node eachNode = nodeList.item(i);
			if (eachNode.getNodeType() == 3)
				node.removeChild(eachNode);
		}
		if (nodeValue != null)
			node.appendChild(document.createTextNode(nodeValue));
	}

	public static void setSingleNodeValue(Node node, String tagName,
			String nodeValue) {
		Node singleNode = getSingleNode(node, tagName);
		if (singleNode != null)
			setNodeValue(singleNode, nodeValue);
	}

	public static void setAttribute(Element element, String attributeName,
			String attributeValue) {
		if ((element != null) && (attributeName != null))
			element.setAttribute(attributeName, attributeValue);
	}

	public static void setAttributes(Element element, HashMap attributes) {
		if ((element == null) || (attributes == null)) {
			return;
		}

		for (Iterator e = attributes.keySet().iterator(); e.hasNext();) {
			String key = e.next().toString();
			element.setAttribute(key, attributes.get(key).toString());
		}
	}

	public static void setSingleNodeAttribute(Node node, String tagName,
			String attributeName, String attributeValue) {
		Element element = getSingleElement(node, tagName);
		setAttribute(element, attributeName, attributeValue);
	}

	public static String domToString(Node node) {
		StringWriter stringwriter = new StringWriter();
		XMLSerializer xmlserializer = new XMLSerializer(stringwriter, null);
		try {
			if ((node instanceof Document))
				xmlserializer.serialize((Document) node);
			if ((node instanceof Element))
				xmlserializer.serialize((Element) node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringwriter.toString();
	}
}