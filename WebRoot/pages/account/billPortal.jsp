<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>账单分析</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/demo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/css.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.portal.js"></script>
<script>
	$(function(){
		$('#portal').portal({
			border:false,
			fit:true
		});
		
		addPortal();
	});
	function addPortal(){
		var urls = [];
		urls.push('/webroot/echarts/heatmap.do');
		urls.push('/webroot/echarts/pie.do');
		urls.push('/webroot/echarts/bar.do');
		for(var i=0; i<3; i++){
			var divHtml = "<div style='overflow:hidden;width:100%; height:100%;'>"
			     		  + "<iframe scrolling='no' width='100%' height='100%' frameborder='0' src='"
   				 	      + urls[i] + "'></iframe></div>"
			var pl = $(divHtml).appendTo('body');
			pl.panel({
				title:'Title'+i,
           		height: 450,
				closable:false,
				collapsible:true,
			  	tools: [{    
				    		iconCls:'icon-add',    
				    		handler:function(){alert('new')}    
						},{    
				    		iconCls:'icon-save',    
				    		handler:function(){alert('save')}    
				  		}]  
			});
			$('#portal').portal('add', {
				panel:pl,
				columnIndex:i
			});
		}
		/*
		var divs = [];
		divs.push('<div id="heatmap" style="overflow:hidden;width:100%;height:500px;"></div>');
		divs.push('<div id="pie" 	 style="overflow:hidden;width:100%;height:400px;"></div>');
		divs.push('<div id="bar" 	 style="overflow:hidden;width:100%;height:400px;"></div>');
		
		for(var i=0; i<3; i++){
			var pl = $(divs[i]).appendTo('body');
			pl.panel({
				title:'Title'+i,
				closable:true,
				collapsible:true
			});
			$('#portal').portal('add', {
				panel:pl,
				columnIndex:i
			});
		}
		ajaxData();
		*/
	}
	
	//暂时不用这种方式直接加载图形数据
	function ajaxData(){
		$.ajax({
	         url: "<%=request.getContextPath()%>/echarts/initHeatmap.do",
	         type: "post",
	         dataType:"json",
	         success: function(data) {
				var xAxisData = data[0];
				var yAxisData = data[1];
				var seriesData = data[2];
				
				seriesData = seriesData.map(function (item) {
				    return [item[1], item[0], item[2] || '-'];
				});
				
				pluginheatmap(xAxisData, yAxisData, seriesData);
		     },
		     error: function(e) { 
		   	 	alert("error!"); 
		   	 } 
		});
		$.ajax({
	         url: "<%=request.getContextPath()%>/echarts/initPie.do",
	         type: "post",
	         dataType:"json",
	         success: function(data) {
				var data1 = data[0];
				var data2 = data[1];
				pluginpie(data1,data2);
		     },
		     error: function(e) { 
		   	 	alert("error!"); 
		   	 } 
		});
		$.ajax({
	         url: "<%=request.getContextPath()%>/echarts/initBar.do",
	         type: "post",
	         dataType:"json",
	         success: function(data) {
				var xAxisData = data[0];
				var seriesData = data[1];
				pluginbar(xAxisData,seriesData);
		     },
		     error: function(e) { 
		   	 	alert("error!"); 
		   	 } 
		});
	}
</script>
</head>
<body>
	<div id="pan" class="easyui-panel" data-options="fit:true" title="账单分析" style="overflow:hidden; width:100%;">
		<div id="portal">
			<div style="width:30%;"></div>
			<div style="width:40%;"></div>
			<div style="width:30%;"></div>
		</div>
	</div>
</body>
</html>