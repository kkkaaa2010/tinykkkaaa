package com.tinykkkaaa.bootstrap.share.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.tinykkkaaa.bootstrap.homepage.vo.NavVO;
import com.tinykkkaaa.bootstrap.share.dao.ShareDao;
import com.tinykkkaaa.bootstrap.share.service.ShareService;

@Service("shareService")
public class ShareServiceImpl implements ShareService {
	
	@Resource
	private ShareDao shareDao;

	@Override
	public List<NavVO> getNavList(String parentid) throws Exception {
		return shareDao.getNavList(parentid);
	}

	@Override
	public Object[] getDefaultSetting(String parentid) throws Exception {
		return shareDao.getDefaultSetting(parentid);
	}
}
