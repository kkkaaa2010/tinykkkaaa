package com.tinykkkaaa.spring.service.impl;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.tinykkkaaa.spring.dao.UIDao;
import com.tinykkkaaa.spring.service.UIService;

@Service("uiService")
public class UIServiceImpl implements UIService {
	
	@Resource
	private UIDao uiDao;

	@Override
	public JSONArray getNavTree(String ptreeid) throws Exception {
		return uiDao.getNavTree(ptreeid);
	}

}
