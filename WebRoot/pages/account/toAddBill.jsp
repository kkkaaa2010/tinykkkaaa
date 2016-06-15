<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
	$(document).ready(function(){
		init();
		onChoiseType(null);
		
		function init(){
			
			$('#accounttype').combobox({    
			    url:'<%=request.getContextPath()%>/sh/account/getAccountType.do',    
			    valueField:'accouonttype_dm',    
			    textField:'accouonttype_mc',
			    value: '02', //默认选择支出
			    onSelect: onChoiseType
			});
			
			$('#zc_zclx').combobox({
			    url:'<%=request.getContextPath()%>/sh/account/getDetailType.do',    
			    valueField:'detailtype_dm',    
			    textField:'detailtype_mc',
		        onSelect: function(rec){
		        	var showStr = "--请选择--";
		        	
		            var url = '<%=request.getContextPath()%>/sh/account/getDetailXm.do?accounttype_dm=02&dm='+rec.detailtype_dm;    
					$('#zc_zcxm').combobox({    
					    url:url,    
					    valueField:'detailxm_dm',    
					    textField:'detailxm_mc',
					    value: showStr,
					    onShowPanel: onSelectXm
					});
		        }
			});
			
			$('#zc_zcxm').combobox({    
			    url:'<%=request.getContextPath()%>/sh/account/getDetailXm.do?accounttype_dm=02',    
			    valueField:'detailxm_dm',    
			    textField:'detailxm_mc',
			    onShowPanel: onSelectXm
			});
			
			$('#sr_srxm').combobox({    
			    url:'<%=request.getContextPath()%>/sh/account/getDetailXm.do?accounttype_dm=01',    
			    valueField:'detailxm_dm',    
			    textField:'detailxm_mc'
			});
		}
	});
	
	function onChoiseType(record){
		var choValue =  $('#accounttype').combobox('getValue');
		if(choValue == '01'){
			$('#zcx1').css('display', 'none');
			$('#zcx2').css('display', 'none');
			$('#zcx3').css('display', 'none');
			
			$('#srx1').css('display', 'block');
			$('#srx2').css('display', 'block');
		}
		if(choValue == '02'){
			$('#srx1').css('display', 'none');
			$('#srx2').css('display', 'none');
			
			$('#zcx1').css('display', 'block');
			$('#zcx2').css('display', 'block');
			$('#zcx3').css('display', 'block');
		}
	}
	
	function onSelectXm(record){
		var cellValue = $('#zc_zclx').combobox('getValue');
		if(cellValue==null || cellValue==""){
			$('#zc_zcxm').combobox('reload','<%=request.getContextPath()%>/sh/account/getDetailXm.do?dm=none');
			alert("请选择支出类型！");
			return;
		}else{
			var dm = cellValue;
			$('#zc_zcxm').combobox('reload','<%=request.getContextPath()%>/sh/account/getDetailXm.do?dm='+dm);
		}
	}
	
	//增加历史账单信息
	function addBill(){
		$('#addFormID').form('submit', {
		    url: "<%=request.getContextPath()%>/sh/account/addBill.do",    
		    onSubmit: function(param){
		    	var accounttype = $('#accounttype').combobox('getValue');
		    	var accounttime = $('#accounttime').datebox('getValue');
		    	if(accounttime == null || accounttime.trim() == ""){
		    		alert("请选择账单发生日期!");
		    		return false;
		    	}
		    	var sr_srxm = $('#sr_srxm').combobox('getValue');
		    	var sr_srsm = $('#sr_srsm').val();
		    	if(sr_srsm == '--请选择--'){
		    		sr_srsm = null;
		    	}
		    	var sr_srsj = $('#sr_srsj').val();
		    	var sr_srje = $('#sr_srje').val();
		    	
		    	var zc_zclx = $('#zc_zclx').combobox('getValue');
		    	var zc_zcxm = $('#zc_zcxm').combobox('getValue');
		    	if(zc_zcxm == '--请选择--'){
		    		zc_zcxm = null;
		    	}
		    	var zc_zcsm = $('#zc_zcsm').val();
		    	var zc_zcsj = $('#zc_zcsj').val();
		    	var zc_zcje = $('#zc_zcje').val();
		    	
		    	var params = {'accounttype':accounttype, 'accounttime':accounttime, 
		    			      'sr_srxm':sr_srxm, 'sr_srsm':sr_srsm, 
		    			      'sr_srsj':sr_srsj, 'sr_srje':sr_srje, 
		    			      'zc_zclx':zc_zclx, 'zc_zcxm':zc_zcxm, 
		    			      'zc_zcsm':zc_zcsm, 'zc_zcsj':zc_zcsj, 
		    			      'zc_zcje':zc_zcje};
		    	
		    	param.paramsStr = JSON.stringify(params);
		    },    
		    success:function(data){
		    	var data = eval('(' + data + ')');
		    	var msg = data.message;
		    	if(msg == "01"){
		    		alert("历史账单补录成功!");
		    		$('#addHisWin').window('close');
		    	}else{
		    		alert("历史账单补录失败, 请检查您填写的账单信息!");
		    	}
		    }    
		});
	}
</script>
<form id="addFormID" method="post">
	<table style="width:100%; border:2px;">
		<tr id="tr1" style="display: block;" align="center">
			<td width="20%">账单类型</td>
			<td width="30%">
				<select id="accounttype" class="easyui-combobox" panelHeight="auto" style="width:200px">
				</select>
			</td>
			<td width="20%">账单发生日期</td>
			<td width="30%">
				<input id="accounttime" class="easyui-datebox" style="width:200px"/>
			</td>
		</tr>
		<!-- 收入录入项 -->
		<tr id="srx1" align="center">
			<td width="20%">收入项目</td>
			<td width="30%">
				<select id="sr_srxm" class="easyui-combobox" panelHeight="auto" style="width:200px">
				</select>
			</td>
			<td width="20%">收入说明</td>
			<td width="30%">
				<input id="sr_srsm" class="easyui-validatebox" style="width:195px" />
			</td>
		</tr>
		<tr id="srx2" align="center">
			<td width="20%">收入时间</td>
			<td width="30%">
				<input id="sr_srsj" class="easyui-timespinner" data-options="showSeconds:true" style="width:200px" />
			</td>
			<td width="20%">收入金额</td>
			<td width="30%">
				<input id="sr_srje" class="easyui-numberbox" data-options="required:true, precision:2" style="width:200px" /> 
			</td>
		</tr>
		<!-- 支出录入项 -->
		<tr id="zcx1" align="center">
			<td width="20%">支出类型</td>
			<td width="30%">
				<select id="zc_zclx" class="easyui-combobox" panelHeight="auto" style="width:200px">
				</select>
			</td>
			<td width="20%">支出项目</td>
			<td width="30%">
				<select id="zc_zcxm" class="easyui-combobox" panelHeight="auto" style="width:200px">
				</select>
			</td>
		</tr>
		<tr id="zcx2" align="center">
			<td width="20%">支出说明</td>
			<td width="30%">
				<input id="zc_zcsm" class="easyui-validatebox" style="width:195px" /> 
			</td>
			<td width="20%">支出时间</td>
			<td width="30%">
				<input id="zc_zcsj" class="easyui-timespinner" data-options="showSeconds:true" style="width:200px" /> 
			</td>
		</tr>
		<tr id="zcx3" align="center">
			<td width="20%">支出金额</td>
			<td width="30%">
				<input id="zc_zcje" class="easyui-numberbox" data-options="required:true, precision:2" style="width:200px" />
			</td>
			<td></td><td></td>
		</tr>
		<tr id="button" style="display: block;" align="right">
			<td>
				<a id="save" href="#" class="easyui-linkbutton" onclick="addBill();" data-options="iconCls:'icon-save'">保  存</a> 
			</td>
		</tr>
	</table>
</form>