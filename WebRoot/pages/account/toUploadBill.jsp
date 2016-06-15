<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>批量导入账单信息</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.myplugin.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		init();
		
		function init(){
			var message = '${message}';
			if(message == "01"){
	    		alert("正在批量导入账单信息...");
			}
		}
	});
	function submitForm(){
		$('#fileUploadForm').form('submit');
	}
	function closeWin(){
		window.opener = null;
		window.open("", "_self");
		window.close();
		//window.open('<%=request.getContextPath()%>/sh/account/toUploadBill.do', '_self').close();	
	}
</script>
</head>
<body>
<div class="easyui-panel" title="批量导入账单信息" style="width:100%">
	<form id="fileUploadForm" method="post" action="uploadFile.do" enctype="multipart/form-data">
		<table cellpadding="5">
			<!-- 
			<tr>
				<td>Name:</td>
				<td><input class="easyui-textbox" type="text" name="name"
					data-options="required:true"></input>
				</td>
			</tr>
			 -->
			<tr>
				<td>账单EXCEL文件: </td>
				<td>
					<input type="file" name="excelFile"/>
				</td>
			</tr>
		</table>
	</form>
	<div style="text-align: center; padding: 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitForm()">上传</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="closeWin()">关闭</a>
	</div>
</div>
</body>
</html>