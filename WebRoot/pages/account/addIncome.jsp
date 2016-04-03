<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>今日收入</title>
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
		if ($('#income_list').datagrid('validateRow', editIndex)){
			var ed2 = $('#income_list').datagrid('getEditor', {index:editIndex,field:'detailxm_dm'});
			var detailxm_mc = $(ed2.target).combobox('getText');
			$('#income_list').datagrid('getRows')[editIndex]['detailxm_mc'] = detailxm_mc;
			
			$('#income_list').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell(index, field){
		if (editIndex != index){
			if (endEditing()){
				$('#income_list').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				var ed = $('#income_list').datagrid('getEditor', {index:index,field:field});
				if (ed){
					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
				}
				editIndex = index;
			} else {
				$('#income_list').datagrid('selectRow', editIndex);
			}
		}
	}
	function append(){
		if (endEditing()){
			$('#income_list').datagrid('appendRow',{});
			editIndex = $('#income_list').datagrid('getRows').length-1;
			$('#income_list').datagrid('selectRow', editIndex)
					.datagrid('beginEdit', editIndex);
		}
	}
	function removeit(){
		if (editIndex == undefined){return}
		$('#income_list').datagrid('cancelEdit', editIndex)
				.datagrid('deleteRow', editIndex);
		editIndex = undefined;
	}
	function saveAccount(){
		if (endEditing()){
			$('#income_list').datagrid('acceptChanges');
		}
		var accounts = [];
		var account = {};
		var rows = $('#income_list').datagrid('getRows');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			//var rowNumber = $('#income_list').datagrid('getRowIndex', row);
			account = {'id':i+1, 'detailtype_dm':null, 'detailxm_dm':row.detailxm_dm,
					   'detail_content':row.detail_content, 'detail_time':row.detail_time,
					   'detail_je':row.detail_je};
			accounts[i] = account;
		}
		$.ajax({
	         url: "<%=request.getContextPath()%>/sh/account/saveAccount.do?accounttype_dm=01",
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
		$('#income_list').datagrid('rejectChanges');
		editIndex = undefined;
	}
	//获取消费总金额
	function getTotalje(){
		var je = 0.0;
		var rows = $('#income_list').datagrid('getRows');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			je += parseFloat(row.detail_je);
		}
		return je;
	}
	//设置总计金额
	function setTotalje(){
		$('#income_list').datagrid('reloadFooter',[
			{detail_je: "总计消费：" + getTotalje()}
		]);
	}
</script>
</head>
<body>
	<table id="income_list" class="easyui-datagrid" title="今日收入" style="width:'100%';height:auto"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '<%=request.getContextPath()%>/sh/account/getAccountList.do?accounttype_dm=01',
				method: 'post',
				onClickCell: onClickCell,
				fitColumns: true,
				rownumbers: true,
				showFooter: true
			">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'detailxm_dm',width:'20%',halign:'center',
						formatter:function(value,row){
							return row.detailxm_mc;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'detailxm_dm',
								textField: 'detailxm_mc',
								method:'get',
								url:'<%=request.getContextPath()%>/sh/account/getDetailXm.do?accounttype_dm=01',
								required:true
							}
						}">收入项目</th>
				<th data-options="field:'detail_content',width:'30%',halign:'center',editor:'textbox'">收入说明</th>
				<th data-options="field:'detail_time',width:'20%',align:'center',halign:'center',
								  editor:{type:'timespinner',options:{showSeconds:true}}">收入时间</th>
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