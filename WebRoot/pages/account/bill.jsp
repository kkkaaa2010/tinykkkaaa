<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>查看我的账单</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/demo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/css.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.myplugin.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		init();
		
		function init(){
			initTotal();//初始化支出与收入总计数
			initClothesAccount();//初始化第一个tab
			
			$('#accountTabs').tabs({
				onSelect: function(title,index){
					updateTabContent(index);
				}
			});
		}
		
		function initTotal(){
			$.ajax({
		         url: "<%=request.getContextPath()%>/sh/account/getBillTotal.do",
		         type: 'post',
		         dataType:'json',
		         success: function(data) {
					$('#billLayout').layout('panel','center').panel('setTitle','支出('+ data.expenses +')');
					$('#billLayout').layout('panel','east').panel('setTitle','收入('+ data.income +')');

					$('#accountTabs').tabs('update', {
						tab: $('#accountTabs').tabs('getTab',0),options: {title: '衣:' + data.expenses01}
					});
					$('#accountTabs').tabs('update', {
						tab: $('#accountTabs').tabs('getTab',1),options: {title: '食:' + data.expenses02}
					});
					$('#accountTabs').tabs('update', {
						tab: $('#accountTabs').tabs('getTab',2),options: {title: '住:' + data.expenses03}
					});
					$('#accountTabs').tabs('update', {
						tab: $('#accountTabs').tabs('getTab',3),options: {title: '行:' + data.expenses04}
					});
					$('#accountTabs').tabs('update', {
						tab: $('#accountTabs').tabs('getTab',4),options: {title: '其他:' + data.expenses05}
					});
			     },
			     error: function(e) { 
			   	 	alert("error!"); 
			   	 } 
			});
		}
		
		function initClothesAccount(){
			var mycolumns = [[
    			{field:'name',title:'消费项目',width:'50%',halign:'center'},
   		    	{field:'value',title:'消费金额',width:'50%',halign:'center'}
        	]];
			
			$('#clothesTable').propertygrid({
				url: '<%=request.getContextPath()%>/sh/account/getAccountxmList.do?id=0',
				method: 	'post',
				showGroup: 	true, 
				fit:		true,
		 		scrollbarSize: 0,
				groupFormatter: groupFormatter,
				columns: 		mycolumns	
			});
		}
		
		//更新支出tab中内容
		function updateTabContent(indexNum){
			var mycolumns = "[[{field:'name',title:'消费项目',width:'50%',halign:'center'},"
                + "{field:'value',title:'消费金额',width:'50%',halign:'center'}]]";
			var content = "<table class=\"easyui-propertygrid\" style=\"width:100%;overflow:hidden;\" data-options=\""
				          + "url: '" + "<%=request.getContextPath()%>/sh/account/getAccountxmList.do?id="
				          + indexNum + "',"
						  + "method: 'post',"
						  + "showGroup: true, fit:true,"
				 		  + "scrollbarSize: 0,"
						  + "groupFormatter: groupFormatter,"
						  + "columns: " + mycolumns + "\"></table>";
			var tab = $('#accountTabs').tabs('getSelected');  // 获取选择的面板
			$('#accountTabs').tabs('update', {
				tab: tab,
				options: {
					content: content
				}
			});
		}
		
	});
	
	function groupFormatter(fvalue, rows){
		//隐藏横向滚动条，直接设置css时，缩小窗口等操作css失效，改为直接修改css样式
		//$('#foodAccount .datagrid-view2 .datagrid-body').css('overflow', 'hidden');
		return fvalue + " - " + rows.length + "项";
	}
</script>
</head>
<body>
	<div class="easyui-panel" data-options="border: true" title="我的账单（金额单位：元）" style="width:100%; height:500px;">
		<div id="billLayout" class="easyui-layout" data-options="fit:true" style="overflow:hidden">
			<div id="incomeID"  data-options="region:'east', border:false, split:true" title="收入" style="width: 400px; padding: 10px">
				本月收入
			</div>
			<div id="accountID" data-options="region:'center', border:false" title="支出" style="padding: 10px">
				<div id="accountTabs" class="easyui-tabs" 
					 data-options="tabPosition:'left', plain:true, justified:true, fit:true, " 
				     style="">
					<div id="clothesAccount" title="衣" style="overflow:hidden;padding: 10px">
						<table id="clothesTable" class="easyui-propertygrid" style="width:100%;overflow:hidden;"></table>
					</div>
					<div id="foodAccount" title="食" style="overflow:hidden;padding: 10px"></div>
					<div id="liveAccount" title="住" style="overflow:hidden;padding: 10px"></div>
					<div id="walkAccount" title="行" style="padding: 10px"></div>
					<div id="otherAccount" title="其他" style="padding: 10px"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>