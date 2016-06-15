<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../platform/pages/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>tinyKA | 用户登录</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="">
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/bootstrap/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/bootstrap/plugins/iCheck/square/blue.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="<%=request.getContextPath()%>/pages/bootstrap/plugins/iCheck/icheck.min.js"></script>
<script>
	$(function() {
		$('input').iCheck({
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue',
			increaseArea : '20%' // optional
		});
	});
</script>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="/webroot/pages/index.jsp"><b>tiny</b>KA</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">登录体验KA</p>

			<springf:form modelAttribute="userModel" method="post">
				<div class="form-group has-feedback">
					<springf:input path="useraccount" class="form-control" placeholder="email"/>
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
					<p class="text-danger"><springf:errors path="useraccount"></springf:errors></p>
				</div>
				<div class="form-group has-feedback">
					<springf:password path="password" class="form-control" placeholder="password"/>
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox"> 记住我 </label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">登 录</button>
					</div>
					<!-- /.col -->
				</div>
			</springf:form>
			
			<div class="social-auth-links text-center">
				<p>- 你也可以使用以下账号登录 -</p>
				<a href="#" class="btn btn-block btn-social btn-facebook btn-flat">
					<i class="fa fa-facebook"></i> Sign in using Facebook</a> 
				<a href="#" class="btn btn-block btn-social btn-google btn-flat">
					<i class="fa fa-google-plus"></i> Sign in using Google+</a>
			</div>
			<!-- /.social-auth-links -->

			<a href="#">忘记密码</a><br>
			<a href="<%=request.getContextPath()%>/rest/bp/user/register" class="text-center">注册新账号</a>

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->
</body>
</html>
