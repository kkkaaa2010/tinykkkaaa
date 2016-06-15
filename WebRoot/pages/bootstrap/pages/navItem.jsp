<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>一级导航按钮</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/bootstrap/css/css.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/bootstrap/js/bootstrap.min.js"></script>
<style>
</style>
<script type="text/javascript">
	$(document).ready(function(){
		init();
		
		function init(){
			var itemParam = '<%=request.getParameter("itemParam")%>';
			$.ajax({
		         url: "<%=request.getContextPath()%>/bp/homepage/getNavItem.do",
		         type: "post",
		         dataType:"json",
		         success: function(data) {
		         	$.each(data,function(i){
		         		$('#navItem').append("<li id='item"+ data[i].id +"'><a href='" 
		         				             + data[i].url +"'>"+ data[i].name +"</a></li>");
		         	});
		         	$('#navItem #item'+2).addClass('active');
			     },
			     error: function(textStatus) { 
			   	 	alert("error!"); 
			   	 } 
			});
		}
	});
</script>
</head>
<body>
	<div class="masthead">
		<nav>
			<ul id="navItem" class="nav nav-justified">
				<!-- 
				<li><a href="#">首页</a></li>
				<li class="active"><a href="#">KA服务</a></li>
				<li><a href="#">dashboard</a></li>
				<li><a href="#">下载专区</a></li>
				<li><a href="#">关于KA</a></li>
				<li><a href="#">联系我们</a></li>
				-->
			</ul>
		</nav>
	</div>
	<div style="height: 10px;"></div>
</body>
</html>