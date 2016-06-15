package com.tinykkkaaa.bootstrap.share.dao;

import java.util.List;

import com.tinykkkaaa.bootstrap.homepage.vo.NavVO;

public interface ShareDao {
	public List<NavVO> getNavList(String parentid) throws Exception;
	public Object[] getDefaultSetting(String parentid) throws Exception;
}
