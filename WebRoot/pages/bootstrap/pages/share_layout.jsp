<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>资源分享</title>
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
			$('#contentIframe').attr('src', '${defaultSetting[0]}');	
		}
		
		$('#listItem a').on('click', function(){
			//面包屑导航条
			var navTitle = $(this).text();
			$('#navBreadcrumb .active').html(navTitle);
			// 链接列表样式修改
			$("#listItem a[class*='active']").removeClass('active');
			$(this).addClass('active');
		});
	});
	
	//通过js改变Iframe的高度
	function setIframeHeight(iframeId){
	    var cwin = document.getElementById(iframeId);
	    if (document.getElementById){
	        if (cwin && !window.opera){
	            if (cwin.contentDocument && cwin.contentDocument.body.offsetHeight){
	                cwin.height = cwin.contentDocument.body.offsetHeight + 5; //FF NS
	            }  
	            else if(cwin.Document && cwin.Document.body.scrollHeight){
	                cwin.height = cwin.Document.body.scrollHeight + 5;//IE
	            }
	        }else{
	            if(cwin.contentWindow.document && cwin.contentWindow.document.body.scrollHeight) 
	            cwin.height = cwin.contentWindow.document.body.scrollHeight;//Opera
	        } 
	    }
	}
</script>
</head>
<body>
	<%@ include file="nav.jsp" %>
	<div class="container">
		<!-- 一级导航按钮 -->
		<jsp:include page="navItem.jsp">
			<jsp:param name="itemParam" value="2"/>
		</jsp:include>
		<!-- 面包屑导航条 -->
		<ol id="navBreadcrumb" class="breadcrumb">
			<c:forEach var="item" items="${defaultSetting[1]}" varStatus="status">
				<c:if test="${status.last == false}">
					<li><a href="${item.url}">${item.name}</a></li>
				</c:if>
				<c:if test="${status.last == true}">
					<li class="active">${item.name}</li>
				</c:if>
			</c:forEach>
		</ol>
		
		<div class="row">
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading"><strong>资源分享</strong></div>
					<div id="listItem" class="list-group">
						<c:forEach var="item" items="${navList}" varStatus="status">
							<c:if test="${status.index == 0}">
								<a href="${item.url}" target="contentIframe" class="list-group-item active">${item.name}</a>
							</c:if>
							<c:if test="${status.index > 0}">
								<a href="${item.url}" target="contentIframe" class="list-group-item">${item.name}</a>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<iframe id="contentIframe" name="contentIframe" marginwidth="0" marginheight="0" frameborder=0 scrolling="no"
				 		width="100%" height="100%" style="margin-top:0px; margin:0px; padding:0px;" onload="setIframeHeight('contentIframe');"></iframe>
			</div>
		</div>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>