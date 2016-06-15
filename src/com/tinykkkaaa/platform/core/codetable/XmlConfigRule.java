package com.tinykkkaaa.platform.core.codetable;

import java.util.ArrayList;

import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

import com.tinykkkaaa.platform.core.metadata.CodeTable;

public class XmlConfigRule extends Rule {
	
	@Override
	public void begin(String namespace, String name, Attributes attributes)
			throws Exception {
		
		this.digester.addObjectCreate("codetable", ArrayList.class);
		this.digester.addObjectCreate("codetable/codeinfo", CodeTable.class);
		this.digester.addSetNext("codetable/codeinfo", "add");
		
		this.digester.addBeanPropertySetter("codetable/codeinfo/name", "name");
		this.digester.addBeanPropertySetter("codetable/codeinfo/table_name", "tableName");
		this.digester.addBeanPropertySetter("codetable/codeinfo/code", "codeColumnName");
		this.digester.addBeanPropertySetter("codetable/codeinfo/code_name", "labelColumnName");
		this.digester.addBeanPropertySetter("codetable/codeinfo/parent_code_id", "parentColumnName");
		this.digester.addBeanPropertySetter("codetable/codeinfo/parent_table_name", "parentName");
		this.digester.addBeanPropertySetter("codetable/codeinfo/datasource", "dataSourceName");
		
		super.begin(namespace, name, attributes);
	}
	
}
