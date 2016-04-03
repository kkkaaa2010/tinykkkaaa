<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>title</title>
<script src="<%=request.getContextPath() %>/pages/javascript/jquery-1.11.3.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/pages/javascript/jquery.myplugin.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#testid').val("<%=request.getAttribute("message")%>");
		$('a').changeText({'color':'red', 'fontSize':'20px'});
	});
</script>
</head>
<body>
<ul>
	<li>
		<a href="http://www.webo.com/liuwayong">我的微博</a>
	</li>
	<li>
		<a href="http://http://www.cnblogs.com/Wayou/">我的博客</a>
	</li>
	<li>
		<a href="http://wayouliu.duapp.com/">我的小站</a>
	</li>
</ul>
<p>这是p标签不是a标签，我不会受影响</p>
<form action="">
	<input id="testid" type="text" />
</form>
</body>
</html>