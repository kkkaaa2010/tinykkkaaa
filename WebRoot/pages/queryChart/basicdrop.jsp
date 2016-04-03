<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>Basic Draggable - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/demo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/css.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>Basic Droppable</h2>
	<p>Drag the boxed on left to the target area on right.</p>
	<div style="margin:20px 0;"></div>
	<div style="float:left;width:200px;margin-right:20px;">
		<div class="title">Source</div>
		<div>
			<div class="dragitem">Apple</div>
			<div class="dragitem">Peach</div>
			<div class="dragitem">Orange</div>
		</div>
	</div>
	<div style="float:left;width:200px;">
		<div class="title">Target</div>
		<div class="easyui-droppable targetarea"
				data-options="
					accept: '.dragitem',
					onDragEnter:function(e,source){
						$(this).html('enter');
					},
					onDragLeave: function(e,source){
						$(this).html('leave');
					},
					onDrop: function(e,source){
						$(this).html($(source).html() + ' dropped');
					}
				">
		</div>
	</div>
	<div style="clear:both"></div>
	<style type="text/css">
		.title{
			margin-bottom:10px;
		}
		.dragitem{
			border:1px solid #ccc;
			width:50px;
			height:50px;
			margin-bottom:10px;
		}
		.targetarea{
			border:1px solid red;
			height:150px;
		}
		.proxy{
			border:1px solid #ccc;
			width:80px;
			background:#fafafa;
		}
	</style>
	<script>
		$(function(){
			$('.dragitem').draggable({
				revert:true,
				deltaX:10,
				deltaY:10,
				proxy:function(source){
					var n = $('<div class="proxy"></div>');
					n.html($(source).html()).appendTo('body');
					return n;
				}
			});
		});
	</script>
</body>
</html>