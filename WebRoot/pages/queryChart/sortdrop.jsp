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
	<h2>Change Items Order</h2>
	<p>Drag the list items to change their order.</p>
	<div style="margin:20px 0;"></div>
	<ul style="margin:0;padding:0;margin-left:10px;">
		<li class="drag-item">Drag 1</li>
		<li class="drag-item">Drag 2</li>
		<li class="drag-item">Drag 3</li>
		<li class="drag-item">Drag 4</li>
		<li class="drag-item">Drag 5</li>
		<li class="drag-item">Drag 6</li>
	</ul>
	<style type="text/css">
		.drag-item{
			list-style-type:none;
			display:block;
			padding:5px;
			border:1px solid #ccc;
			margin:2px;
			width:300px;
			background:#fafafa;
			color:#444;
		}
		.indicator{
			position:absolute;
			font-size:9px;
			width:10px;
			height:10px;
			display:none;
			color:red;
		}
	</style>
	<script>
		$(function(){
			var indicator = $('<div class="indicator">>></div>').appendTo('body');
			$('.drag-item').draggable({
				revert:true,
				deltaX:0,
				deltaY:0
			}).droppable({
				onDragOver:function(e,source){
					indicator.css({
						display:'block',
						left:$(this).offset().left-10,
						top:$(this).offset().top+$(this).outerHeight()-5
					});
				},
				onDragLeave:function(e,source){
					indicator.hide();
				},
				onDrop:function(e,source){
					$(source).insertAfter(this);
					indicator.hide();
				}
			});
		});
	</script>

</body>
</html>