<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>今日账单</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.myplugin.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	});
	
	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){
			return true
		}
		if ($('#account_list').datagrid('validateRow', editIndex)){
			var ed = $('#account_list').datagrid('getEditor', {index:editIndex,field:'detailtype_dm'});
			var detailtype_mc = $(ed.target).combobox('getText');
			$('#account_list').datagrid('getRows')[editIndex]['detailtype_mc'] = detailtype_mc;
			
			var ed2 = $('#account_list').datagrid('getEditor', {index:editIndex,field:'detailxm_dm'});
			var detailxm_mc = $(ed2.target).combobox('getText');
			$('#account_list').datagrid('getRows')[editIndex]['detailxm_mc'] = detailxm_mc;
			
			$('#account_list').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell(index, field){
		if (editIndex != index){
			if (endEditing()){
				$('#account_list').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				var ed = $('#account_list').datagrid('getEditor', {index:index,field:field});
				if (ed){
					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
				}
				editIndex = index;
			} else {
				$('#account_list').datagrid('selectRow', editIndex);
			}
		}
	}
	function append(){
		if (endEditing()){
			$('#account_list').datagrid('appendRow',{});
			editIndex = $('#account_list').datagrid('getRows').length-1;
			$('#account_list').datagrid('selectRow', editIndex)
					.datagrid('beginEdit', editIndex);
		}
	}
	function removeit(){
		if (editIndex == undefined){return}
		$('#account_list').datagrid('cancelEdit', editIndex)
				.datagrid('deleteRow', editIndex);
		editIndex = undefined;
	}
	function saveAccount(){
		if (endEditing()){
			$('#account_list').datagrid('acceptChanges');
		}
		var accounts = [];
		var account = {};
		var rows = $('#account_list').datagrid('getRows');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			//var rowNumber = $('#account_list').datagrid('getRowIndex', row);
			account = {'id':i+1, 'detailtype_dm':row.detailtype_dm, 'detailxm_dm':row.detailxm_dm,
					   'detail_content':row.detail_content, 'detail_time':row.detail_time,
					   'detail_je':row.detail_je};
			accounts[i] = account;
		}
		$.ajax({
	         url: "<%=request.getContextPath()%>/sh/account/saveAccount.do",
	         type: "post",
	         dataType:"text",
	         data: {"accounts" : JSON.stringify(accounts)},
	         success: function(result) {
	        	 setTotalje();
	        	 alert(result);
		     },
		     error: function(textStatus) { 
		   	 	alert("error!"); 
		   	 } 
		});
	}
	function reject(){
		$('#account_list').datagrid('rejectChanges');
		editIndex = undefined;
	}
	//获取消费总金额
	function getTotalje(){
		var je = 0.0;
		var rows = $('#account_list').datagrid('getRows');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			je += parseFloat(row.detail_je);
		}
		return je;
	}
	//设置总计金额
	function setTotalje(){
		$('#account_list').datagrid('reloadFooter',[
			{detail_je: "总计消费：" + getTotalje()}
		]);
	}
	
	//点击消费类型
	function onSelectType(record){
		var row = $('#account_list').datagrid('getSelected');
		var index = $('#account_list').datagrid('getRowIndex', row);
		var detailxm = $('#account_list').datagrid('getEditor', {index:index,field:'detailxm_dm'});
		var cellValue = $(detailxm.target).combobox('getValue');
		
		if(cellValue==null || cellValue==""){
			var dm = record.detailtype_dm;
			$(detailxm.target).combobox('reload',"<%=request.getContextPath()%>/sh/account/getDetailXm.do?dm="+dm);
			/*
			第二种方法刷新combobox
			$.ajax({
		         url: "<%=request.getContextPath()%>/sh/account/getDetailXm.do?dm="+dm,
		         type: "post",
		         dataType:"json",
		         success: function(data) {
		        	 $(detailxm.target).combobox('loadData',data);
			     },
			     error: function(textStatus) { 
			   	 	alert("error!"); 
			   	 } 
			});
			*/
		}
	}
	
	//点击消费项目
	function onSelectXm(record){
		var row = $('#account_list').datagrid('getSelected');
		var index = $('#account_list').datagrid('getRowIndex', row);
		var detailtype = $('#account_list').datagrid('getEditor', {index:index,field:'detailtype_dm'});
		var cellValue = $(detailtype.target).combobox('getValue');
		
		var detailxm = $('#account_list').datagrid('getEditor', {index:index,field:'detailxm_dm'});
		if(cellValue==null || cellValue==""){
			$(detailxm.target).combobox('reload','<%=request.getContextPath()%>/sh/account/getDetailXm.do?dm=none');
			alert("请选择消费类型！");
			return;
		}else{
			var dm = cellValue;
			$(detailxm.target).combobox('reload','<%=request.getContextPath()%>/sh/account/getDetailXm.do?dm='+dm);
		}
	}
</script>
</head>
<body>
	<table id="account_list" class="easyui-datagrid" title="今日账单" style="width:'100%';height:auto"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '<%=request.getContextPath()%>/sh/account/getAccountList.do',
				method: 'post',
				onClickCell: onClickCell,
				fitColumns: true,
				rownumbers: true,
				showFooter: true
			">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'detailtype_dm',width:'10%',halign:'center',
						formatter:function(value,row){
							return row.detailtype_mc;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'detailtype_dm',
								textField: 'detailtype_mc',
								method:'get',
								url:'<%=request.getContextPath()%>/sh/account/getDetailType.do',
								required:true,
								onSelect: onSelectType
							}
						}">消费类型</th>
				<th data-options="field:'detailxm_dm',width:'10%',halign:'center',
						formatter:function(value,row){
							return row.detailxm_mc;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'detailxm_dm',
								textField: 'detailxm_mc',
								method:'get',
								url:'<%=request.getContextPath()%>/sh/account/getDetailXm.do',
								required:true,
								onShowPanel: onSelectXm
							}
						}">消费项目</th>
				<th data-options="field:'detail_content',width:'30%',halign:'center',editor:'textbox'">消费内容</th>
				<th data-options="field:'detail_time',width:'20%',align:'center',halign:'center',
								  editor:{type:'timespinner',options:{showSeconds:true}}">消费时间</th>
				<th data-options="field:'detail_je',width:'30%',align:'center',halign:'center',
				                  editor:{type:'numberbox',options:{precision:2}}">金额</th>
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="saveAccount()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">返回</a>
	</div>
</body>
</html>