<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>首页</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/ui/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/ui/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/ui/css/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ui/javascript/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ui/javascript/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		init();
		function init(){
			$('#nav_tree').tree({
				url: "<%=request.getContextPath()%>/homepage/ui/getNavTree.do?ptreeid=0",
				method: "post",
				animate: true,
				lines: true,
				onBeforeExpand:function(node){
					//异步按需点击加载
		        	$('#nav_tree').tree('options').url = "<%=request.getContextPath()%>/homepage/ui/getNavTree.do?ptreeid="+node.id; 
				},
				onClick: function(node){
					var url = node.attributes.url;
					if(url != null && url != ""){
						var id = node.id;
						var title = node.text
						$('#centertabs').tabs('add',{
							title: title,
							content: "<div data-options='fit:true' style='overflow:hidden;width:100%; height:100%;'><iframe width='100%' height='100%' frameborder='0' src='"
                            		 + url + "'></iframe></div>",
                            //content: "<div class='easyui-layout' data-options='fit:true' style='overflow:hidden;'>" +
                              //  	 "<div data-options=\"region:'north'\">当前位置：" + title + "</div>" +
                              //  	 "<div data-options=\"region:'center'\" style='overflow:hidden;'><iframe width='100%' height='100%' frameborder='0' src='"
                              //  	 + url + "'></iframe></div></div>",
							closable: true
						});
					}
				}
			});
		}
	});
</script>
</head>
<body>
	<div class="easyui-layout" style="overflow:hidden" data-options="fit:true">
		<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px;overflow:hidden;">
			<div style="text-align: left; height:40px;line-height:40px; font-size: 20px">
				tinykkkaaa
			</div>
		</div>
		<div data-options="region:'west',split:true" title="菜单导航" style="width:15%;padding:10px;overflow:hidden">
			<ul id="nav_tree" class="easyui-tree"></ul>
		</div>
		<div data-options="region:'east',collapsed:true,hideExpandTool: true,
				expandMode: null,hideCollapsedContent: false,collapsedSize: 68,
				collapsedContent: function(){
					return $('#titlebar');
				}" 
			 title="East" style="width:100px;overflow:hidden">
		</div>
		<div data-options="region:'south',border:false" 
			 style="height:30px;background:#B3DFDA;padding:10px;overflow:hidden;">
			<div style="text-align: center; height:15px;line-height:15px;">
				Copyright © 2009-2016 WEIBO 我是陆亮kkkaaa2010[2011]2046-296号京ICP		
			</div>
		</div>
		<div data-options="region:'center'" style="overflow:hidden">
			<div id="centertabs" class="easyui-tabs" data-options="border: false, fit:true" style="width:100%; height:100%;overflow: hidden;">
			</div>
		</div>
	</div>
	
	<div id="titlebar" style="padding:2px">
		<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">Picture</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-shapes',size:'large',iconAlign:'top'">Shapes</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-smartart',size:'large',iconAlign:'top'">SmartArt</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">Chart</a>
	</div>
</body>
</html>