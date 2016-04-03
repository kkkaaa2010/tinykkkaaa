<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>账单详情</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/demo.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.myplugin.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/javascript/jquery.easyui.pagination.myplugin.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		init();
		
		function init(){
			initDefaultValue();
			searchBillList({'fssj_q':$('#fssj_q').datebox('getValue'), 'fssj_z':$('#fssj_z').datebox('getValue'),
						    'zdlx':null, 'zdxm':null, 'je_q':null, 'je_z':null});
			$('#billList').datagrid('clientPaging');
		}
		
		//初始化日期默认值
		function initDefaultValue(){
			var date = new Date();
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			var dateStr = y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
			var dateStrQ = y+'-'+(m<10?('0'+m):m)+'-'+"01";
			$('#fssj_q').datebox('setValue',dateStrQ);
			$('#fssj_z').datebox('setValue',dateStr);
		}
		
		$('#searchID').bind('click', function(){
			var fssj_q = $('#fssj_q').datebox('getValue');
			var fssj_z = $('#fssj_z').datebox('getValue');
			var zdlx = $('#zdlx').combobox('getValue');
			var zdxm = $('#zdxm').combobox('getValue');
			var je_q = $('#je_q').val();
			var je_z = $('#je_z').val();
			
			var params = {'fssj_q':fssj_q, 'fssj_z':fssj_z, 'zdlx':zdlx, 'zdxm':zdxm, 'je_q':je_q, 'je_z':je_z};
			searchBillList(params);
		});
		
		//查询账单列表信息
		function searchBillList(params){
			$.ajax({
		         url: "<%=request.getContextPath()%>/sh/account/getBillList.do",
		         type: "post",
		         dataType:"json",
		         data: {"params" : JSON.stringify(params)},
		         success: function(result) {
		        	 $('#billList').datagrid('loadData', result);
			     },
			     error: function(textStatus) { 
			   	 	alert("error!"); 
			   	 } 
			});	
		}
	});
	
	function formatOper(val,row,index){
		var str = '<select onclick="operate(this,' + index+ ')" id="" class="easyui-combobox" data-options="value:00" panelHeight="auto">'
		          +'<option value="00">操作</option>'
		          +'<option value="01">修改</option>'
				  +'<option value="02">删除</option>'
				  +'</select>';
		return str;

	}
	function operate(obj, index){
		var operValue = obj.value;
		if(operValue == '01' || operValue == '02'){
			$('#billList').datagrid('clearSelections');//多次选择清除所选行
		    $('#billList').datagrid('selectRow',index);
		    var row = $('#billList').datagrid('getSelected');
		    
	    	var accountid 		= row.id;
	    	var detailid 		= row.detailid;
	    	var accounttype_dm 	= row.accounttype_dm;
		    
		    if(operValue == '01'){
		    	//修改
		    	if(accounttype_dm == '01'){
					$('#updateIncome').window({
						title: '修改收入信息('+ accountid +')',
						modal:true,
						iconCls:'icon-edit',
						width:'500px',
						href:'<%=request.getContextPath()%>/sh/account/toUpdateBill.do?accountid='
							 +accountid+'&detailid='+detailid+'&accounttype_dm='+accounttype_dm+'&rowNumIndex='+index
					});	
		    	}else if(accounttype_dm == '02'){
					$('#updateExpenses').window({
						title: '修改支出信息('+ accountid +')',
						modal:true,
						iconCls:'icon-edit',
						width:'500px',
						href:'<%=request.getContextPath()%>/sh/account/toUpdateBill.do?accountid='
							 +accountid+'&detailid='+detailid+'&accounttype_dm='+accounttype_dm+'&rowNumIndex='+index
					});		
		    	}
		    }else if(operValue == '02'){
		    	//删除
		    	var tempStr = "";
		    	if(accounttype_dm == '01'){
		    		tempStr = "收入账单"+accountid;
		    	}else{
		    		tempStr = "支出账单"+accountid;
		    	}
		    	var message = "你确定删除这条账单信息("+ tempStr +")吗?";
		    	
				$.messager.confirm('删除账单', message, function(r){
					if (r){
						var params = {'accountid':accountid, 'detailid':detailid, 'accounttype_dm':accounttype_dm};
				        $.ajax({
					         url: "<%=request.getContextPath()%>/sh/account/delDetailAccountById.do",
					         type: "post",
					         dataType:"text",
					         data: {"params" : JSON.stringify(params)},
					         success: function(data) {
							 	alert(data);
							 	$('#billList').datagrid('deleteRow',index);
						     },
						     error: function(textStatus) { 
						   	 	alert("error!"); 
						   	 }
						});
					}
				});
		    }
		}
	}  
</script>
</head>
<body>
	<table id="billList" class="easyui-datagrid" title="账单交易记录" style="width:'100%';height:auto"
			data-options="rownumbers:true,
						  method:'post',
						  checkOnSelect:true,
						  pagination:true,
						  pageSize:10,
						  toolbar:'#tb'">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:'10%',halign:'center',align:'left'">账单发生时间</th>
				<th data-options="field:'detailid',halign:'center',hidden:true">明细ID</th>
				<th data-options="field:'accounttype_dm',halign:'center',hidden:true">账单类型代码</th>
				<th data-options="field:'accounttype_mc',width:'10%',halign:'center',align:'left'">账单类型</th>
				<th data-options="field:'detailtype_mc',width:'10%',halign:'center',align:'left'">支出类型</th>
				<th data-options="field:'detailxm_mc',width:'20%',halign:'center',align:'left'">账单支出（收入）项目</th>
				<th data-options="field:'detail_je',width:'10%',halign:'center',align:'center'">金额</th>
				<th data-options="field:'lrsj',width:'15%',halign:'center'">创建时间</th>
				<th data-options="field:'detail_content',width:'13%',halign:'center',align:'left'">说明</th>
				<th data-options="field:'_operate',width:'10%',halign:'center',align:'center', formatter:formatOper">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:2px 5px;">
		<table style="border: 1px;width: 100%">
			<tr>
				<td>账单发生时间: </td>
				<td>
					<input id="fssj_q" class="easyui-datebox" style="width:120px">
					 - <input id="fssj_z" class="easyui-datebox" style="width:120px">
				</td>
				<td>账单类型:</td>
				<td> 
					<select id="zdlx" class="easyui-combobox" data-options="value:''" panelHeight="auto" style="width:120px">
						<option value="">--请选择--</option>
						<option value="02">支出</option>
						<option value="01">收入</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>账单支出（收入）项目:</td> 
				<td>
					<select id="zdxm" class="easyui-combobox" data-options="value:''" panelHeight="auto" style="width:120px">
						<option value="">--请选择--</option>
						<option value="01">早饭</option>
						<option value="03">晚饭</option>
						<option value="04">地铁</option>
					</select>
				</td>
				<td>金额:</td>
				<td>
					<input id="je_q" type="text" class="easyui-numberbox"  style="width:120px" value="" data-options="min:0,precision:2"></input>  
				 	 - <input id="je_z" type="text" class="easyui-numberbox" style="width:120px" value="" data-options="min:0,precision:2"></input>  
				 </td>
			</tr>
		</table>
		<a id="searchID" href="#" class="easyui-linkbutton" data-options="" style="width: 100px" iconCls="icon-search">搜  索</a>
	</div>
	
	<div id="updateIncome" style="height:auto;padding:10px;"></div>
	<div id="updateExpenses" style="height:auto;padding:10px;"></div>
</body>
</html>