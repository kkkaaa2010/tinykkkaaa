package com.tinykkkaaa.spring.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.tinykkkaaa.comm.constant.Const;
import com.tinykkkaaa.comm.constant.Week;
import com.tinykkkaaa.spring.dao.EchartsDao;
import com.tinykkkaaa.spring.service.EchartsService;

@Service("echartsService")
public class EchartsServiceImpl implements EchartsService {
	
	@Resource
	private EchartsDao echartsDao;

	@Override
	public Object[] queryBarData() throws Exception {
		return echartsDao.queryBarData();
	}

	@Override
	public Object[] queryPieData() throws Exception {
		Object[] objs = new Object[2];
		JSONArray jarray = echartsDao.queryPieData();
		
		List<String> list = new ArrayList<String>();
		
		Iterator<JSONObject> it = jarray.iterator();
		while(it.hasNext()){
			list.add(it.next().getString("name"));
		}
		
		objs[0] = list;
		objs[1] = jarray;
		return objs;
	}

	@Override
	public Object[] queryHeatmapData() throws Exception {
		Object[] objs = new Object[3];
		
		objs[0] = Const.HOURS;
		objs[1] = new String[]{Week.Sun.getName(),Week.Mon.getName(),Week.Thu.getName(),
				Week.Wed.getName(),Week.Tue.getName(),Week.Fri.getName(),Week.Sat.getName()};
		objs[2] = echartsDao.queryHeatmapData();
		
		return objs;
	}

}
