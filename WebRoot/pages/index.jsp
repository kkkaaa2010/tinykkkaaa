<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>tinyKA</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/bootstrap/css/css.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/bootstrap/js/bootstrap.min.js"></script>
<style>
</style>
<script type="text/javascript">
	$(document).ready(function(){
	});
</script>
</head>
<body>
	<nav class="nav navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a href="index.html" class="navbar-brand">
					<img style="height: 30px; margin-top: -5px" src="<%=request.getContextPath()%>/pages/bootstrap/images/logo.jpg">
				</a>
			</div>
			<div class="navbar-form navbar-right">
				<div class="form-group">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="请输入关键字" />
						<div class="input-group-btn">
							<button class="btn btn-block">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</div>
					</div>
				</div>
				<div class="form-group" style="padding-left: 10px; margin: 0px 12px;">
					<a href="<%=request.getContextPath()%>/rest/bp/user/login" class="navbar-link" style="color: #D2D2D2;">登录</a> 
					<span style="color: #D2D2D2; padding-left: 5px;">|</span> 
					<a href="<%=request.getContextPath()%>/rest/bp/user/register" class="navbar-link" style="color: #D2D2D2; padding-left: 5px;">注册</a>
				</div>
			</div>
		</div>
	</nav>
	<div style="height: 60px;"></div>
	<div class="container">
		<div class="masthead">
			<nav>
				<ul class="nav nav-justified">
					<li class="active"><a href="#">首页</a>
					</li>
					<li><a href="#">KA服务</a>
					</li>
					<li><a href="#">dashboard</a>
					</li>
					<li><a href="#">下载专区</a>
					</li>
					<li><a href="#">关于KA</a>
					</li>
					<li><a href="#">联系我们</a>
					</li>
				</ul>
			</nav>
		</div>
		<hr class="featurette-divider">
		<div class="row">
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="<%=request.getContextPath()%>/pages/bootstrap/images/index_1.jpg">
					<div class="caption">
						<h4 class="featurette-heading" style="text-align:center;"><a>个性定制</a></h4>
						<p class="text-muted" style="text-align:center;">定制你的KA内容</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="<%=request.getContextPath()%>/pages/bootstrap/images/index_2.jpg">
					<div class="caption">
						<h4 class="featurette-heading" style="text-align:center;"><a href="/webroot/bp/share/layout.do">资源分享</a></h4>
						<p class="text-muted" style="text-align:center;">KA知道的, 你也会知道</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="<%=request.getContextPath()%>/pages/bootstrap/images/index_3.jpg">
					<div class="caption">
						<h4 class="featurette-heading" style="text-align:center;"><a>信息实时更新</a></h4>
						<p class="text-muted" style="text-align:center;">爬虫come, 一网打尽!</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="<%=request.getContextPath()%>/pages/bootstrap/images/index_4.jpg">
					<div class="caption">
						<h4 class="featurette-heading" style="text-align:center;"><a>KA还没想好</a></h4>
						<p class="text-muted" style="text-align:center;">建设中...</p>
					</div>
				</div>
			</div>
		</div>
		<hr class="featurette-divider">
		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					<span class="glyphicon glyphicon-pencil"></span> 旅游服务建设中... ...
				</h2>
				<p class="lead">Donec ullamcorper nulla non metus auctor
					fringilla. Vestibulum id ligula porta felis euismod semper.
					Praesent commodo cursus magna, vel scelerisque nisl consectetur.
					Fusce dapibus, tellus ac cursus commodo.</p>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block"
					data-src="" alt="500x500"
					src="<%=request.getContextPath()%>/pages/bootstrap/images/index_5.jpg"
					data-holder-rendered="true">
			</div>
		</div>

		<hr class="featurette-divider">
		<footer>
			<p class="pull-right">
				<a href="#">返回顶部</a>
			</p>
			<p>
				Copyright © 1999-2016, tinykkkaaa, All Rights Reserved
			</p>
		</footer>
	</div>
</body>
</html>