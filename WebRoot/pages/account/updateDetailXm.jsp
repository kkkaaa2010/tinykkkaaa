<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
	$(document).ready(function(){
		init();
		
		function init(){
			var xmdm = '${detailXmVO.xmdm}';
			var detailxmmc = '${detailXmVO.xmmc}';
			var accounttype_dm = '${detailXmVO.accounttype_dm}';
			var detailtype_dm = '${detailXmVO.detailtype_dm}';
			$('#updateFormID').form('load',{
				zdlx_up: accounttype_dm,
				xflx_up: detailtype_dm,
				zcsrxm_up: detailxmmc
			});
			$('#xmdm').val(xmdm);
		}
	});
	
	//修改账单支出（收入）项目
	function updateSave(){
		$('#updateFormID').form('submit', {
		    url: "<%=request.getContextPath()%>/sh/account/updateDetailXm.do",    
		    onSubmit: function(param){
		    	var zdlxStr = $('#zdlx_up').combobox('getValue');
		    	var xflxStr = $('#xflx_up').combobox('getValue');
		    	if(zdlxStr == "01" && xflxStr != ""){
		    		alert("账单类型为收入时，不能选择消费类型!");
		    		return false;
		    	}
		    	param.xmdm = $('#xmdm').val();
				param.zdlx = zdlxStr;
				param.xflx = xflxStr;
				param.zcsrxm = $('#zcsrxm_up').val();
		    },    
		    success:function(data){  
		    	var data = eval('(' + data + ')');
		        alert(data.message);
		        $('#updateDX').window('close');
		        $('#detailxm_list').datagrid('reload');
		    }    
		});
	}
</script>
<form id="updateFormID" method="post">
<input type="hidden" id="xmdm"/> 
	<table style="width: 100%; border: 2px">
		<tr>
			<td>账单类型:</td>
			<td>
				<select id="zdlx_up" name="zdlx_up" class="easyui-combobox" data-options="" panelHeight="auto" style="width:120px">
					<option value="">--请选择--</option>
					<option value="02">支出</option>
					<option value="01">收入</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>消费类型</td>
			<td>
				<select id="xflx_up" name="xflx_up" class="easyui-combobox" data-options="" panelHeight="auto" style="width:120px">
					<option value="">--请选择--</option>
					<option value="01">衣</option>
					<option value="02">食</option>
					<option value="03">住</option>
					<option value="04">行</option>
					<option value="05">其他</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>支出（收入）项目</td>
			<td>
				<input id="zcsrxm_up" name="zcsrxm_up" class="easyui-validatebox" data-options="required:true" style="width:120px" /> 
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<a id="saveupdateID" href="#" class="easyui-linkbutton" onclick="updateSave();" data-options="iconCls:'icon-save'">保  存</a> 
			</td>
		</tr>
	</table>
</form>