<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>最近一周账单分析</title>
<script src="<%=request.getContextPath() %>/pages/javascript/jquery-1.11.3.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/pages/javascript/echarts.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/pages/account/javascript/echarts.myplugin.account.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
	         url: "<%=request.getContextPath()%>/sh/account/fx/initRecentlyfx.do",
	         type: "post",
	         dataType:"json",
	         success: function(data) {
				var dmData 		= data.dmData;
				var xAxisData 	= data.xAxisData;
				var timelineData 	= data.timelineData;
				var maxYAxis 	= data.maxYAxis;
				drawRecentlyAccount(dmData, xAxisData, maxYAxis, timelineData, getOptionData(dmData, timelineData));
		     },
		     error: function(e) {
		   	 	alert("error!"); 
		   	 } 
		});
		
		function getOptionData(dmData, timelineData){
			var optionDatas = [];
			for(var i=0; i<timelineData.length; i++){
				var optionData = {};
				optionData = {
			            		title: {text: timelineData[i]+'支出', x:'center'},
			            		series: [
			                		{data: dmData[timelineData[i]]},
			                		{data: dmData[timelineData[i]+"pie"]}
			            		]
			    };
				optionDatas.push(optionData);
			}
			return optionDatas;
		}
	});
</script>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="reAccountID" style="width:100%;height:400px;"></div>
</body>
</html>