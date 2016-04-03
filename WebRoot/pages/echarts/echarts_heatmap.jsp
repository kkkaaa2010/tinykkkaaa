<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>echarts热力图</title>
<script src="<%=request.getContextPath() %>/pages/javascript/jquery-1.11.3.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/pages/javascript/echarts.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/pages/javascript/echarts.myplugin.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
	         url: "<%=request.getContextPath()%>/echarts/initHeatmap.do",
	         type: "post",
	         dataType:"json",
	         success: function(data) {
				var xAxisData = data[0];
				var yAxisData = data[1];
				var seriesData = data[2];
				
				seriesData = seriesData.map(function (item) {
				    return [item[1], item[0], item[2] || '-'];
				});
				
				pluginheatmap(xAxisData, yAxisData, seriesData);
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
    <div id="heatmap" style="width:100%;height:500px;"></div>
</body>
</html>