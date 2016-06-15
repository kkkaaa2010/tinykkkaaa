package com.tinykkkaaa.bootstrap.homepage.service.impl;

import javax.annotation.Resource;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import com.tinykkkaaa.bootstrap.homepage.dao.BPHomePageDao;
import com.tinykkkaaa.bootstrap.homepage.service.BPHomePageService;

@Service("bpHomePageService")
public class BPHomePageServiceImpl implements BPHomePageService {
	
	@Resource
	private BPHomePageDao bpHomePageDao;

	@Override
	public JSONArray getNavItem(String itemParam) throws Exception {
		return bpHomePageDao.getNavItem(itemParam);
	}

	
}
