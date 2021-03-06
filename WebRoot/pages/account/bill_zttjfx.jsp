<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>账单统计分析</title>
<script src="<%=request.getContextPath() %>/pages/javascript/jquery-1.11.3.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/pages/javascript/echarts.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/pages/account/javascript/echarts.myplugin.account.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
	         url: "<%=request.getContextPath()%>/sh/account/fx/initZttjfx.do",
	         type: "post",
	         dataType:"json",
	         success: function(data) {
				var legendData 	= data.legendData;
				var seriesData 	= data.seriesData;
				drawZttjFx(legendData, seriesData);
		     },
		     error: function(e) {
		   	 	alert("error!"); 
		   	 } 
		});
	});
</script>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="zttjPie" style="width:100%;height:400px;"></div>
</body>
</html>