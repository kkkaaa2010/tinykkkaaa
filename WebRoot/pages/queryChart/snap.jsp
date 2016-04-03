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
	<h2>Snap Draggable</h2>
	<p>This sample shows how to snap a draggable object to a 20x20 grid.</p>
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" style="position:relative;overflow:hidden;width:500px;height:300px">
		<div class="easyui-draggable" data-options="onDrag:onDrag" style="width:100px;height:100px;background:#fafafa;border:1px solid #ccc;">
		</div>
	</div>
	<script>
		function onDrag(e){
			var d = e.data;
			d.left = repair(d.left);
			d.top = repair(d.top);
			
			function repair(v){
				var r = parseInt(v/20)*20;
				if (Math.abs(v % 20) > 10){
					r += v > 0 ? 20 : -20;
				}
				return r;
			}
		}
	</script>

</body>
</html>