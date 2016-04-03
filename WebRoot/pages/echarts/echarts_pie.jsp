<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>echarts饼状图</title>
<script src="<%=request.getContextPath() %>/pages/javascript/jquery-1.11.3.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/pages/javascript/echarts.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/pages/javascript/echarts.myplugin.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
	         url: "<%=request.getContextPath()%>/echarts/initPie.do",
	         type: "post",
	         dataType:"json",
	         success: function(data) {
				var data1 = data[0];
				var data2 = data[1];
				pluginpie(data1,data2);
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
    <div id="pie" style="width:100%;height:400px;"></div>
</body>
</html>