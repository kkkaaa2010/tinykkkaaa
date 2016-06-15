package com.tinykkkaaa.platform.core.codetable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.tinykkkaaa.platform.core.cache.CacheManager;
import com.tinykkkaaa.platform.core.dao.DataPersistenceManager;
import com.tinykkkaaa.platform.core.dao.DataPersistenceObject;
import com.tinykkkaaa.platform.core.dao.DataSet;
import com.tinykkkaaa.platform.core.metadata.CodeTable;
import com.tinykkkaaa.platform.framework.web.PlatformCoreServlet;

public class CodeTableManager extends PlatformCoreServlet {
	private List<String> config;
	private static Map<String, CodeTable> codeTableConfigMap = new HashMap<String, CodeTable>();
	private static String codeTableDataCacheCatalog = "_ka_codetable_datacache_"; //代码表缓存分类名
	private static long expiredTime = 60*60*24*1000;
	private CacheManager cacheManager;
	private DataPersistenceManager dataPersistenceManager;
	private Map<String, DataPersistenceObject> dataPersistenceObjects = new HashMap<String, DataPersistenceObject>();
	
	@Override
	public void run(){
		if(this.config == null){
			return;
		}
		try {
			this.constructCodeTableConfigMap();
			this.cacheCodeTableData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 构建代码表信息Map
	 */
	private void constructCodeTableConfigMap() throws Exception{
		//1. 从XML获取codetable信息，放入codeTableConfigMap
		Iterator<String> it = this.config.iterator();
		while(it.hasNext()){
			String filePath = this.servletContext.getRealPath(it.next());
			File file = new File(filePath);
			if(file.exists() && file.isFile()){
				FileInputStream fis = new FileInputStream(file);
				List<CodeTable> codeTableList = this.getCodeTableConfigs(fis);
				if(codeTableList == null){
					continue;
				}
				for(CodeTable codeTable : codeTableList){
					codeTableConfigMap.put(codeTable.getName(), codeTable);
				}
			}
		}
		//2. 为codetable.parent赋值
		Iterator<String> iterator = codeTableConfigMap.keySet().iterator();
		while(iterator.hasNext()){
			CodeTable ct = this.getCodeTableConfig(iterator.next());
			String parentName = ct.getParentName();
			if(parentName!=null && parentName.length()>0){
				CodeTable parent = this.getCodeTableConfig(parentName);
				if(parent != null){
					ct.setParent(parent);
				}
			}
		}
	}
	
    /*
     * 读取XML配置文件中的代码表配置信息
     */
	@SuppressWarnings("unchecked")
	private List<CodeTable> getCodeTableConfigs(InputStream is){
		Digester digester = new Digester();
		digester.setValidating(false);
		XmlConfigRule configRule = new XmlConfigRule();
		digester.addRule("codetable", configRule);
		Object obj = null;
		try {
			obj = digester.parse(is);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		if(obj == null){
			return null;
		}else{
			return (List<CodeTable>)obj;
		}
	}
	
	/*
	 * 获取代码表配置信息
	 */
	private CodeTable getCodeTableConfig(String name){
		if(codeTableConfigMap == null){
			return null;
		}
		return codeTableConfigMap.get(name);
	}
	
	/*
	 * 缓存代码表数据
	 */
	private void cacheCodeTableData(){
		Iterator<String> iterator = codeTableConfigMap.keySet().iterator();
		while(iterator.hasNext()){
			CodeTable ct = this.getCodeTableConfig(iterator.next());
			if(ct==null || ct.getName()==null || ct.getName().length()==0){
				continue;
			}
			if(this.getCachedDataContainer(ct.getName()) == null){
				this.cacheData(ct.getName(), this.getDataContainer(ct));
			}
		}
		Iterator<Map.Entry<String, DataPersistenceObject>> it = this.dataPersistenceObjects.entrySet().iterator();
		while(it.hasNext()){
            Map.Entry<String, DataPersistenceObject> next = it.next();
            next.getValue().close();
		}
	}
	
	/*
	 * 从数据库获取代码表容器
	 */
	private CodeTableDB getDataContainer(CodeTable ct){
		DataPersistenceObject dpo = this.dataPersistenceObjects.get(ct.getDataSourceName());
		
		CodeTableDB ctb = new CodeTableDB(ct);
		if(dpo == null){
			dpo =  this.dataPersistenceManager.getDataPersistenceObject(ct.getDataSourceName());
            this.dataPersistenceObjects.put(ct.getDataSourceName(), dpo);
		}
		try {
			DataSet ds = dpo.executeQuery("SELECT * FROM " + ct.getTableName());
			while(ds.hasNext()){
				ds.next();
				String code = ds.getString(ct.getCodeColumnName().toUpperCase());
				String value = ds.getString(ct.getLabelColumnName().toUpperCase());
				Map<String, String> columMap = new HashMap<String, String>();
				columMap.put(code, value);
				ctb.add(columMap, code, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ctb;
	}
	
	/*
	 * 缓存数据
	 */
	private void cacheData(String name, CodeTableDB ctb){
		if(ctb!=null && name!=null){
			try {
				this.cacheManager.put(name, ctb, codeTableDataCacheCatalog, new Date((new Date()).getTime() + expiredTime));
			} catch (Exception e) {
				System.out.println("缓存服务无法正常使用！------------------------------");
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 	获取已经缓存的代码表容器
	 */
	private CodeTableDB getCachedDataContainer(String name){
		try {
			Object object = this.cacheManager.get(name, codeTableDataCacheCatalog);
			return object==null ? null : (CodeTableDB)object;
		} catch (Exception e) {
			System.out.println("缓存服务无法正常使用！------------------------------");
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 从缓存获取代码表数据容器，如果未缓存则从数据库获取并缓存
	 */
	protected CodeTableDB getCodeTableDB(String name){
		CodeTableDB ctb = this.getCachedDataContainer(name);
		if(ctb == null){
			CodeTable ct = this.getCodeTableConfig(name);
			if(ct == null){
				return null;
			}
			ctb = this.getDataContainer(ct);
			this.cacheData(name, ctb);
		}
		return ctb;
	}
	
	/*
	 * 获取代码表数据(数据结构：list<dm:mc>)
	 */
	public List<Map<String, String>> getData(String codeTableName){
		CodeTableDB ctb = this.getCodeTableDB(codeTableName);
		if(ctb == null){
			return null;
		}else{
			return ctb.getColumData();
		}
	}
	
	/*
	 * 获取代码表数据(数据结构：List<CodeTableVO>)
	 */
	public List<CodeTableVO> getTableList(String codeTableName){
		List<Map<String, String>> list = this.getData(codeTableName);
		List<CodeTableVO> tableList = new ArrayList<CodeTableVO>();
		for(Map<String, String> map : list){
			Iterator<String> it = map.keySet().iterator();
			String code = it.next();
			String value = map.get(code);
			
			CodeTableVO vo = new CodeTableVO();
			vo.setCode(code);
			vo.setValue(value);
			tableList.add(vo);
		}
		return tableList;
	}
	
	/*
	 * 根据表名、代码获取代码值
	 */
	public String getCodeValue(String codeTableName, String code){
		CodeTableDB ctb = this.getCodeTableDB(codeTableName);
		if(ctb == null){
			return null;
		}else{
			return ctb.getCodeValue(code);
		}
	}
	
	/*
	 * 根据表名、代码值获取代码
	 */
	public String getCode(String codeTableName, String codeValue){
		CodeTableDB ctb = this.getCodeTableDB(codeTableName);
		if(ctb == null){
			return null;
		}else{
			return ctb.getCode(codeValue);
		}
	}
	
    public void setConfig(List<String> config) {
        this.config = config;
    }
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    public void setDataPersistenceManager(DataPersistenceManager dataPersistenceManager) {
        this.dataPersistenceManager = dataPersistenceManager;
    }
}
