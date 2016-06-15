<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>历史账单补录</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.myplugin.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		/*
		$.messager.show({
			title:'批量导入账单信息提示',
			msg:'批量导入Excel成功!',
			timeout:5000,
			showType:'slide'
		});
		*/
		
		$('#addHis').bind('click', function(){
			$('#addHisWin').window({
				title: '补录历史账单信息',
				modal:true,
				iconCls:'icon-edit',
				width:'80%',
				href:'<%=request.getContextPath()%>/sh/account/toAddBill.do'
			});	
		});
		$('#loadHis').bind('click', function(){
			//$('#loadHisWin').window({
				//title: '批量导入账单信息',
				//modal:true,
				//iconCls:'icon-edit',
				//width:'500px',
				//href:'<%=request.getContextPath()%>/sh/account/toUploadBill.do'
			//});
			var iHeight = 150;
			var iWidth = 500;
			var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
			window.open ('<%=request.getContextPath()%>/sh/account/toUploadBill.do','批量导入账单信息',
					     'height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft
					     +',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') 
		});
	});  
</script>
</head>
<body>
	<div>
		<a id="addHis" href="javascript:void(0)" class="easyui-linkbutton">补录历史账单信息</a>
		<a id="loadHis" href="javascript:void(0)" class="easyui-linkbutton">批量导入账单信息</a>
	</div>
	
	<div id="addHisWin" style="height:auto;padding:10px;"></div>
</body>
</html>