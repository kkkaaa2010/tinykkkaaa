<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
	$(document).ready(function(){
		init();
		
		function init(){
			var rowNumIndexid1 = '${rowNumIndex}';
			var up1_accountid = '${detailAccountVO.id}';
			var up1_detailid = '${detailAccountVO.detailid}';
			var srxm = '${detailAccountVO.detailxm_dm}';
			var srsm = '${detailAccountVO.detail_content}';
			var srsj = '${detailAccountVO.detail_time}';
			var srje = '${detailAccountVO.detail_je}';
			
			$('#updateFormID1').form('load',{
				up1_srsm: srsm,
				up1_srsj: srsj,
				up1_srje: srje
			});
			
			$('#up1_srxm').combobox({    
			    url:'<%=request.getContextPath()%>/sh/account/getDetailXm.do?accounttype_dm=01',    
			    valueField:'detailxm_dm',    
			    textField:'detailxm_mc',
			    value: srxm
			});
			
			$('#rowNumIndexid1').val(rowNumIndexid1);
			$('#up1_accountid').val(up1_accountid);
			$('#up1_detailid').val(up1_detailid);
		}
	});
	
	//修改收入
	function updateSave1(){
		var rowNumIndexid1 = $('#rowNumIndexid1').val();
		
		$('#updateFormID1').form('submit', {
		    url: "<%=request.getContextPath()%>/sh/account/updateBill.do",    
		    onSubmit: function(param){
		    	param.accountid = $('#up1_accountid').val();
		    	param.detailid = $('#up1_detailid').val();
		    	var srxmStr = $('#up1_srxm').combobox('getValue');
		    	param.srxm = srxmStr;
		    	param.srsm = $('#up1_srsm').val();
		    	param.srsj = $('#up1_srsj').val();
		    	param.srje = $('#up1_srje').val();
		    	param.accounttype_dm = "01";
		    },    
		    success:function(data){  
		    	var accountid = $('#up1_accountid').val();
		    	var detailid = $('#up1_detailid').val();
		    	var accounttype_dm = "01";
		    	
		    	var data = eval('(' + data + ')');
		        alert(data.message);
		        $('#updateIncome').window('close');
		        //$('#billList').datagrid('reload');
		        //window.location.reload();
		        //$('#billList').datagrid('refreshRow',0);
		        
		        var params = {'accountid':accountid, 'detailid':detailid, 'accounttype_dm':accounttype_dm};
		        $.ajax({
			         url: "<%=request.getContextPath()%>/sh/account/getDetailAccountById.do",
			         type: "post",
			         dataType:"json",
			         data: {"params" : JSON.stringify(params)},
			         success: function(data) {
					        $('#billList').datagrid('updateRow',{
					        	index: rowNumIndexid1,
					        	row: data
					        });
					        setRowNum(rowNumIndexid1);
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
<form id="updateFormID1" method="post">
<input type="hidden" id="up1_accountid"/>
<input type="hidden" id="up1_detailid"/>
<input type="hidden" id="rowNumIndexid1"/>
	<table style="width: 100%; border: 2px">
		<tr>
			<td>收入项目</td>
			<td>
				<select id="up1_srxm" name="up1_srxm" class="easyui-combobox" data-options="" panelHeight="auto" style="width:120px">
				</select>
			</td>
		</tr>
		<tr>
			<td>收入说明</td>
			<td>
				<input id="up1_srsm" name="up1_srsm" class="easyui-validatebox" data-options="" style="width:115px" /> 
			</td>
		</tr>
		<tr>
			<td>收入时间</td>
			<td>
				<input id="up1_srsj" name="up1_srsj" class="easyui-timespinner" data-options="showSeconds:true" style="width:120px" /> 
			</td>
		</tr>
		<tr>
			<td>收入金额</td>
			<td>
				<input id="up1_srje" name="up1_srje" class="easyui-numberbox" data-options="required:true, precision:2" style="width:120px" /> 
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<a id="saveupdateID1" href="#" class="easyui-linkbutton" onclick="updateSave1();" data-options="iconCls:'icon-save'">保  存</a> 
			</td>
		</tr>
	</table>
</form>