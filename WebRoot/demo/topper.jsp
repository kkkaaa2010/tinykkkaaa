<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<link href="<%=request.getContextPath() %>/pages/css/topStyle.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/pages/css/baseStyle.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/pages/css/styles.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/pages/javascript/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/pages/javascript/index.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#blogTitle').delay(5000).hide(2000);
	});
</script>
</head>
<body>
	<!-- 顶部start -->
	<div id="topDiv" class="contain_topDiv">
		<div class="clearfix contain_wrap">
			<!-- 未登陆 -->
			<p class="fl">tinykkkaaa</p>
			<ul class="fr myhome">
				<li><a class="topA" href="">你好，请 登录</a></li>
				<li class="separator"></li>
				<li><a class="topA" href="" class="ui-font-blue">免费注册</a></li>
				<li class="separator"></li>
				<li><a class="topA" href="">我的账户</a></li>
				<li class="separator"></li>
				<li><a class="topA" href="">收件箱</a></li>
				<li class="separator"></li>
				<li><a class="topA" href=""></a></li>
			</ul>
		</div>
	</div>
	<!-- 顶部end -->
	<div class="header" id="header">
		<div id="blogTitle" class="blogTitle">
			<h1><a href="#">tinykkkaaa的小网站</a></h1>
			<h2>他不停的跑啊跑, 就为了追上那个曾经被寄予厚望的自己</h2>
		</div>
		<div class="navigator">
			<ul>
				<li class="clicked"><a href="javascript:void(0);">首页</a><li>
				<li><a href="javascript:void(0);">JAVA</a></li>
				<li><a href="javascript:void(0);">Andriod</a></li>
				<li><a href="javascript:void(0);">架构设计</a></li>
				<li><a href="javascript:void(0);">其他技术</a></li>
				<li><a href="javascript:void(0);">程序人生</a></li>
				<li><a href="javascript:void(0);">我的作品</a></li>
				<li><a href="javascript:void(0);">关于博主</a></li>
			</ul>
		</div>
	</div>
	<div class="bottom_tools">
		<a id="scrollUp" title="飞回顶部" href="javascript:;"></a>
	</div>
</body>
</html>