<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>timeline</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/bootstrap/css/timeline.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/bootstrap/js/bootstrap.min.js"></script>
<style>
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="container" style="margin-top: 10px">
		<ul class="timeline">
			<li>
				<div class="timeline-badge">
					<i class="glyphicon glyphicon-check"></i>
				</div>
				<div class="timeline-badge-time">
					<small class="text-muted"> 
						2016-01-01
					</small>
				</div>
				<div class="timeline-panel">
					<div class="timeline-heading">
						<h4 class="timeline-title">加入KA</h4>
						<p>
							<small class="text-muted">
								<i class="glyphicon glyphicon-time"></i> 2016-01-01 09:12:45在沈阳加入
							</small>
						</p>
					</div>
					<div class="timeline-body">
						<p></p>
					</div>
				</div>
			</li>
			<li class="timeline-inverted">
				<div class="timeline-badge warning">
					<i class="glyphicon glyphicon-credit-card"></i>
				</div>
				<div class="timeline-badge-time-inverted">
					<small class="text-muted"> 
						2016-01-10
					</small>
				</div>
				<div class="timeline-panel">
					<div class="timeline-heading">
						<h4 class="timeline-title">第一次登录</h4>
					</div>
					<div class="timeline-body">
						<p></p>
						<p></p>
					</div>
				</div>
			</li>
			<li>
				<div class="timeline-badge danger">
					<i class="glyphicon glyphicon-credit-card"></i>
				</div>
				<div class="timeline-panel">
					<div class="timeline-heading">
						<h4 class="timeline-title">Mussum ipsum cacilds</h4>
					</div>
					<div class="timeline-body">
						<p></p>
					</div>
				</div>
			</li>
			<li class="timeline-inverted">
				<div class="timeline-badge">
					<i class="glyphicon glyphicon-check"></i>
				</div>
				<div class="timeline-panel">
					<div class="timeline-heading">
						<h4 class="timeline-title">Mussum ipsum cacilds</h4>
					</div>
					<div class="timeline-body">
						<p></p>
					</div>
				</div>
			</li>
			<li>
				<div class="timeline-badge info">
					<i class="glyphicon glyphicon-floppy-disk"></i>
				</div>
				<div class="timeline-panel">
					<div class="timeline-heading">
						<h4 class="timeline-title">Mussum ipsum cacilds</h4>
					</div>
					<div class="timeline-body">
						<p>Mussum ipsum cacilds, vidis litro abertis. Consetis
							adipiscings elitis. Pra lá , depois divoltis porris, paradis.
							Paisis, filhis, espiritis santis. Mé faiz elementum girarzis,
							nisi eros vermeio, in elementis mé pra quem é amistosis quis leo.
							Manduma pindureta quium dia nois paga. Sapien in monti palavris
							qui num significa nadis i pareci latim. Interessantiss quisso
							pudia ce receita de bolis, mais bolis eu num gostis.</p>
						<hr>
						<div class="btn-group">
							<button type="button"
								class="btn btn-primary btn-sm dropdown-toggle"
								data-toggle="dropdown">
								<i class="glyphicon glyphicon-cog"></i> <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">Action</a>
								</li>
								<li><a href="#">Another action</a>
								</li>
								<li><a href="#">Something else here</a>
								</li>
								<li class="divider"></li>
								<li><a href="#">Separated link</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="timeline-badge info">
					<i class="glyphicon glyphicon-floppy-disk"></i>
				</div>
				<div class="timeline-panel">
					<div class="timeline-heading">
						<h4 class="timeline-title">Mussum ipsum cacilds</h4>
					</div>
					<div class="timeline-body">
						<p></p>
					</div>
				</div>
			</li>
			<li class="timeline-inverted">
				<div class="timeline-badge success">
					<i class="glyphicon glyphicon-thumbs-up"></i>
				</div>
				<div class="timeline-panel">
					<div class="timeline-heading">
						<h4 class="timeline-title">Mussum ipsum cacilds</h4>
					</div>
					<div class="timeline-body">
						<p></p>
					</div>
				</div>
			</li>
		</ul>
	</div>
</body>
</html>