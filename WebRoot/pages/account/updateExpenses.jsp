<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
	$(document).ready(function(){
		init();
		
		function init(){
			var rowNumIndexid2 = '${rowNumIndex}';
			var up2_accountid = '${detailAccountVO.id}';
			var up2_detailid = '${detailAccountVO.detailid}';
			var zclx = '${detailAccountVO.detailtype_dm}';
			var zcxm = '${detailAccountVO.detailxm_dm}';
			var zcsm = '${detailAccountVO.detail_content}';
			var zcsj = '${detailAccountVO.detail_time}';
			var zcje = '${detailAccountVO.detail_je}';
			
			$('#updateFormID2').form('load',{
				up2_zcsm: zcsm,
				up2_zcsj: zcsj,
				up2_zcje: zcje
			});
			
			$('#up2_zclx').combobox({    
			    url:'<%=request.getContextPath()%>/sh/account/getDetailType.do',    
			    valueField:'detailtype_dm',    
			    textField:'detailtype_mc',
			    value: zclx,
		        onSelect: function(rec){
		        	//默认选择支出项目显示,如果支出类型与数据库存储一样,不改变显示值,否则置为'--请选择--'
		        	var showStr = "--请选择--";
		        	if(zclx == rec.detailtype_dm){
		        		showStr = zcxm;	
		        	}
		        	
		            var url = '<%=request.getContextPath()%>/sh/account/getDetailXm.do?accounttype_dm=02&dm='+rec.detailtype_dm;    
					$('#up2_zcxm').combobox({    
					    url:url,    
					    valueField:'detailxm_dm',    
					    textField:'detailxm_mc',
					    value: showStr,
					    onShowPanel: onSelectXm
					});
		        }
			});
			
			$('#up2_zcxm').combobox({    
			    url:'<%=request.getContextPath()%>/sh/account/getDetailXm.do?accounttype_dm=02',    
			    valueField:'detailxm_dm',    
			    textField:'detailxm_mc',
			    value: zcxm,
			    onShowPanel: onSelectXm
			});
			
			$('#rowNumIndexid2').val(rowNumIndexid2);
			$('#up2_accountid').val(up2_accountid);
			$('#up2_detailid').val(up2_detailid);
		}
	});
	
	function onSelectXm(record){
		var cellValue = $('#up2_zclx').combobox('getValue');
		if(cellValue==null || cellValue==""){
			$('#up2_zcxm').combobox('reload','<%=request.getContextPath()%>/sh/account/getDetailXm.do?dm=none');
			alert("请选择支出类型！");
			return;
		}else{
			var dm = cellValue;
			$('#up2_zcxm').combobox('reload','<%=request.getContextPath()%>/sh/account/getDetailXm.do?dm='+dm);
		}
	}
	
	//修改支出
	function updateSave2(){
		var rowNumIndexid2 = $('#rowNumIndexid2').val();
		
		$('#updateFormID2').form('submit', {
		    url: "<%=request.getContextPath()%>/sh/account/updateBill.do",    
		    onSubmit: function(param){
		    	param.accountid = $('#up2_accountid').val();
		    	param.detailid = $('#up2_detailid').val();
		    	var zclxStr = $('#up2_zclx').combobox('getValue');
		    	var zcxmStr = $('#up2_zcxm').combobox('getValue');
		    	param.srtype = zclxStr;
		    	param.srxm = zcxmStr;
		    	param.srsm = $('#up2_zcsm').val();
		    	param.srsj = $('#up2_zcsj').val();
		    	param.srje = $('#up2_zcje').val();
		    	param.accounttype_dm = "02";
		    },    
		    success:function(data){  
		    	var accountid = $('#up2_accountid').val();
		    	var detailid = $('#up2_detailid').val();
		    	var accounttype_dm = "02";
		    	var rowNumIndexid2 = $('#rowNumIndexid2').val();
		    	
		    	var data = eval('(' + data + ')');
		        alert(data.message);
		        $('#updateExpenses').window('close');
		        
		        var params = {'accountid':accountid, 'detailid':detailid, 'accounttype_dm':accounttype_dm};
		        $.ajax({
			         url: "<%=request.getContextPath()%>/sh/account/getDetailAccountById.do",
			         type: "post",
			         dataType:"json",
			         data: {"params" : JSON.stringify(params)},
			         success: function(data) {
					        $('#billList').datagrid('updateRow',{
					        	index: rowNumIndexid2,
					        	row: data
					        });
					        setRowNum(rowNumIndexid2);
				     },
				     error: function(textStatus) { 
				   	 	alert("error!"); 
				   	 }
				});
		    }    
		});
	}
	
	//解决easyui更新行后，行号显示问题
	//根据rowIndex设置行号
	function setRowNum(rowIndexid){
		var tmpRowidAdd = parseInt(rowIndexid)+1;
		$('.datagrid-view1 .datagrid-btable .datagrid-row:eq('+ rowIndexid +') .datagrid-cell-rownumber').html(tmpRowidAdd);
	}
</script>
<form id="updateFormID2" method="post">
<input type="hidden" id="up2_accountid"/>
<input type="hidden" id="up2_detailid"/>
<input type="hidden" id="rowNumIndexid2"/>
	<table style="width: 100%; border: 2px">
		<tr>
			<td>支出类型</td>
			<td>
				<select id="up2_zclx" name="up2_zclx" class="easyui-combobox" data-options="" panelHeight="auto" style="width:120px">
				</select>
			</td>
		</tr>
		<tr>
			<td>支出项目</td>
			<td>
				<select id="up2_zcxm" name="up2_zcxm" class="easyui-combobox" data-options="" panelHeight="auto" style="width:120px">
				</select>
			</td>
		</tr>
		<tr>
			<td>支出说明</td>
			<td>
				<input id="up2_zcsm" name="up2_zcsm" class="easyui-validatebox" data-options="" style="width:115px" /> 
			</td>
		</tr>
		<tr>
			<td>支出时间</td>
			<td>
				<input id="up2_zcsj" name="up2_zcsj" class="easyui-timespinner" data-options="showSeconds:true" style="width:120px" /> 
			</td>
		</tr>
		<tr>
			<td>支出金额</td>
			<td>
				<input id="up2_zcje" name="up2_zcje" class="easyui-numberbox" data-options="required:true, precision:2" style="width:120px" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<a id="saveupdateID2" href="#" class="easyui-linkbutton" onclick="updateSave2();" data-options="iconCls:'icon-save'">保  存</a> 
			</td>
		</tr>
	</table>
</form>