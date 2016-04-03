<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
	//新增账单支出（收入）项目
	function save(){
		$('#addFormID').form('submit', {
		    url: "<%=request.getContextPath()%>/sh/account/addDetailXm.do",    
		    onSubmit: function(param){    
				param.zdlx = $('#zdlx').combobox('getValue');
				param.xflx = $('#xflx').combobox('getValue');
				param.zcsrxm = $('#zcsrxm').val();
		    },    
		    success:function(data){  
		    	var data = eval('(' + data + ')');
		        alert(data.message);
		        $('#addDX').window('close');
		        $('#detailxm_list').datagrid('reload');
		    }    
		});
	}
</script>
<form id="addFormID" method="post">
	<table style="width: 100%; border: 2px">
		<tr>
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
			<td>消费类型</td>
			<td>
				<select id="xflx" class="easyui-combobox" data-options="value:''" panelHeight="auto" style="width:120px">
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
				<input id="zcsrxm" class="easyui-validatebox" data-options="required:true" style="width:120px" /> 
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<a id="saveID" href="#" class="easyui-linkbutton" onclick="save();" data-options="iconCls:'icon-save'">保  存</a> 
			</td>
		</tr>
	</table>
</form>