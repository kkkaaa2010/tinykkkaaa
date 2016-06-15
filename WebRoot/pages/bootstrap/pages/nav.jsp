<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>nav</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/bootstrap/css/css.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/bootstrap/js/bootstrap.min.js"></script>
<style>
</style>
<script type="text/javascript">
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
					<a href="#" class="navbar-link" style="color: #D2D2D2;">登录</a> 
					<span style="color: #D2D2D2; padding-left: 5px;">|</span> 
					<a href="#" class="navbar-link" style="color: #D2D2D2; padding-left: 5px;">注册</a>
				</div>
			</div>
		</div>
	</nav>
	<div style="height: 60px;"></div>
</body>
</html>