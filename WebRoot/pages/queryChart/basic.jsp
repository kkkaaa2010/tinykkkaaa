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
	<h2>Basic Draggable</h2>
	<p>Move the boxes below by clicking on it with mouse.</p>
	<div style="margin:20px 0;"></div>
	<div class="easyui-draggable" style="width:200px;height:150px;background:#fafafa;border:1px solid #ccc"></div>
	<div class="easyui-draggable" data-options="handle:'#title'" style="width:200px;height:150px;background:#fafafa;border:1px solid #ccc;margin-top:10px">
		<div id="title" style="padding:5px;background:#ccc;color:#fff">Title</div>
	</div>
</body>
</html>