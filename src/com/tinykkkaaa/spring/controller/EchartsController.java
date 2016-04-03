package com.tinykkkaaa.spring.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinykkkaaa.spring.service.EchartsService;

@Controller
@RequestMapping("/echarts")
public class EchartsController {
	
	@Resource
	private EchartsService echartsService;
	
	@RequestMapping("/bar")
	public String bar() throws Exception{
		String forward = "echarts/echarts_bar";
		return forward;
	}
	
	@RequestMapping("/initBar")
	@ResponseBody
	public Object[] initBar() throws Exception{
		return echartsService.queryBarData();
	}
	
	@RequestMapping("/pie")
	public String pie() throws Exception{
		String forward = "echarts/echarts_pie";
		return forward;
	}
	
	@RequestMapping("/initPie")
	@ResponseBody
	public Object[] initPie() throws Exception{
		return echartsService.queryPieData();
	}
	
	@RequestMapping("/heatmap")
	public String heatmap() throws Exception{
		String forward = "echarts/echarts_heatmap";
		return forward;
	}
	
	@RequestMapping("/initHeatmap")
	@ResponseBody
	public Object[] initHeatmap() throws Exception{
		return echartsService.queryHeatmapData();
	}
}
