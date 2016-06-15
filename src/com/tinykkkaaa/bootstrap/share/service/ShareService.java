package com.tinykkkaaa.bootstrap.share.service;

import java.util.List;

import com.tinykkkaaa.bootstrap.homepage.vo.NavVO;


public interface ShareService {
	public List<NavVO> getNavList(String parentid) throws Exception;
	public Object[] getDefaultSetting(String parentid) throws Exception;
}
