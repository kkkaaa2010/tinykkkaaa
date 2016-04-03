<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>账单支出（收入）项目维护</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.myplugin.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	});
	
	function add(){
		$('#addDX').window({
			title: '新增支出（收入）项目',
			modal:true,
			iconCls:'icon-save',
			width:'500px',
			href:'<%=request.getContextPath()%>/sh/account/toAddDetailXm.do'
		});
	}
	function update(){
		var row = $('#detailxm_list').datagrid('getSelected');
		if(row == null){
			alert("请选择一条信息进行修改操作！");
			return false;
		}else{
			var rows = $('#detailxm_list').datagrid('getSelections');
			if(rows.length > 1){
				alert("只能选择一条信息进行修改操作！");
				return false;	
			}else{
				var dm = row.xmdm;
				$('#updateDX').window({
					title: '修改支出（收入）项目',
					modal:true,
					iconCls:'icon-edit',
					width:'500px',
					href:'<%=request.getContextPath()%>/sh/account/toUpdateDetailXm.do?dm='+dm
				});		
			}
		}
	}
	//删除账单支出（收入）项目
	function del(){
		var rows = $('#detailxm_list').datagrid('getSelections');
		var dms = "";
		var ids = [];
		for(var i=0; i<rows.length; i++){
			ids[i] = $('#detailxm_list').datagrid('getRowIndex',rows[i]);
			dms += rows[i].xmdm + ",";
		}
		$.ajax({
	         url: "<%=request.getContextPath()%>/sh/account/delDetailXm.do?dms="+dms,
	         type: "post",
	         dataType:"text",
	         success: function(result) {
	        	alert(result);
	     		for(var j=0; i<ids.length; j++){			
	    			$('#detailxm_list').datagrid('deleteRow',ids[j]);
	    		}
	     		$('#detailxm_list').datagrid('reload');
		     },
		     error: function(textStatus) { 
		   	 	alert("error!"); 
		   	 } 
		});
	}
	function reject(){
		$('#detailxm_list').datagrid('rejectChanges');
		editIndex = undefined;
	}
</script>
</head>
<body>
	<table id="detailxm_list" class="easyui-datagrid" title="账单支出（收入）项目维护" style="width:'100%';height:auto"
			data-options="
				iconCls: 'icon-edit',
				checkOnSelect:true,
				toolbar: '#tb',
				url: '<%=request.getContextPath()%>/sh/account/getDetailXmList.do',
				method: 'post',
				fitColumns: true,
				rownumbers: true,
				rowStyler: function(index,row){
					if (row.accounttype_dm == '01'){
						return 'background-color:#7CCD7C;color:#fff;font-weight:bold;';
					}
				}
			">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'accounttype_dm',halign:'center',hidden:true">账单类型代码</th>
				<th data-options="field:'accounttype_mc',width:'20%',halign:'center'">账单类型</th>
				<th data-options="field:'detailtype_dm',width:'40%',halign:'center',
						formatter:function(value,row){
							return row.detailtype_mc;
						}">消费类型</th>
				<th data-options="field:'xmdm',halign:'center',hidden:true">支出（收入）项目代码</th>
				<th data-options="field:'xm',width:'38%',halign:'center'">支出（收入）项目</th>
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">返回</a>
	</div>
	<div id="addDX" style="height:auto;padding:10px;"></div>
	<div id="updateDX" style="height:auto;padding:10px;"></div>
</body>
</html>