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
	<h2>Constrain Draggable</h2>
	<p>The draggable object can only be moved within its parent container.</p>
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" style="position:relative;overflow:hidden;width:500px;height:300px">
		<div class="easyui-draggable" data-options="onDrag:onDrag" style="width:100px;height:100px;background:#fafafa;border:1px solid #ccc;">
		</div>
	</div>
	<script>
		function onDrag(e){
			var d = e.data;
			if (d.left < 0){d.left = 0}
			if (d.top < 0){d.top = 0}
			if (d.left + $(d.target).outerWidth() > $(d.parent).width()){
				d.left = $(d.parent).width() - $(d.target).outerWidth();
			}
			if (d.top + $(d.target).outerHeight() > $(d.parent).height()){
				d.top = $(d.parent).height() - $(d.target).outerHeight();
			}
		}
	</script>

</body>
</html>