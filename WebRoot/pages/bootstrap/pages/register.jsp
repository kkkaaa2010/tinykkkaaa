<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../platform/pages/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>tinyKA  | 用户注册</title>
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
	$(function () {
		$('input').iCheck({
	 		checkboxClass: 'icheckbox_square-blue',
	    	radioClass: 'iradio_square-blue',
	    	increaseArea: '20%'
		});
	});
</script>
</head>
<body class="hold-transition register-page">
	<div class="register-box">
		<div class="register-logo">
			<a href="/webroot/pages/index.jsp"><b>tiny</b>KA</a>
		</div>

		<div class="register-box-body">
			<p class="login-box-msg">注册新用户</p>

			<springf:form modelAttribute="registerUserModel" method="POST">
				<div class="form-group has-feedback">
					<springf:input path="username" class="form-control" placeholder="请输入用户名"/>
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
					<p class="text-danger"><springf:errors path="username"></springf:errors></p>
				</div>
				<div class="form-group has-feedback">
					<springf:input path="useraccount" class="form-control" placeholder="请输入Email"/>
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
					<p class="text-danger"><springf:errors path="useraccount"></springf:errors></p>
				</div>
				<div class="form-group has-feedback">
					<springf:password path="password" class="form-control" placeholder="输入密码"/>
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
					<p class="text-danger"><springf:errors path="password"></springf:errors></p>
				</div>
				<div class="form-group has-feedback">
					<springf:password path="confirmpassword" class="form-control" placeholder="确认密码"/>
					<span class="glyphicon glyphicon-log-in form-control-feedback"></span>
					<p class="text-danger"><springf:errors path="confirmpassword"></springf:errors></p>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> 
								<input type="checkbox"> I agree to the <a href="#">terms</a> 
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">注 册</button>
					</div>
					<!-- /.col -->
				</div>
			</springf:form>

			<div class="social-auth-links text-center">
				<p>- 使用第三方账号 -</p>
				<a href="#" class="btn btn-block btn-social btn-facebook btn-flat">
					<i class="fa fa-facebook"></i> Sign up using Facebook
				</a> 
				<a href="#" class="btn btn-block btn-social btn-google btn-flat">
					<i class="fa fa-google-plus"></i> Sign up using Google+
				</a>
			</div>

			<a href="<%=request.getContextPath()%>/rest/bp/user/login" class="text-center">我已经注册了一个账号</a>
		</div>
		<!-- /.form-box -->
	</div>
	<!-- /.register-box -->
</body>
</html>
