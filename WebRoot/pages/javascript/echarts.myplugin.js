function pluginbar(xAxisData,seriesData){
	
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('bar'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'bar'
        },
        tooltip: {},
        legend: {
            data:['数量']
        },
        xAxis: {
            data: xAxisData
        },
        yAxis: {},
        series: [{
            name: '数量',
            type: 'bar',
            data: seriesData
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function pluginpie(data1,data2){
	
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('pie'));

    option = {
    	    title : {
    	        text: '南丁格尔玫瑰图',
    	        x:'center'
    	    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	    },
    	    legend: {
    	        x : 'center',
    	        y : 'bottom',
    	        data: data1
    	    },
    	    toolbox: {
    	        show : true,
    	        feature : {
    	            mark : {show: true},
    	            dataView : {show: true, readOnly: false},
    	            magicType : {
    	                show: true,
    	                type: ['pie', 'funnel']
    	            },
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : true,
    	    label: {
    	    	normal: {
    	    		show: true,
    	    		formatter: '{b}: {c}'
    	    	},
    	    	emphasis: {
    	    		show: true,
    	    		formatter: '{b}: {d}%',
    	    		textStyle: {
    	    			fontStyle: 'italic',
    	    			fontWeight: 'bold',
    	    			fontSize: 16
    	    		}
    	    	}
    	    },
    	    labelLine: {
    	    	
    	    },
    	    series : [
    	        {
    	            name:'面积模式',
    	            type:'pie',
    	            radius : [10, '70%'],
    	            center : ['50%', '50%'],
    	            roseType : 'area',
    	            data : data2
    	        }
    	    ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function pluginheatmap(data0, data1, data2){
	
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('heatmap'));

	var option = {
	    title : {
	        text: '日常税务登记情况统计（热力图）',
	        x:'center'
	    },
		tooltip : {
			position : 'top',
    	    trigger: 'item',
    	    formatter: function (params) {
    	    	var pdata = params.value;
    	    	var xdata = pdata[0];
    	    	var ydata = pdata[1];
    	    	var data = pdata[2];
    	    	
    	    	if(ydata == 0){
    	    		ydata = "星期日";
    	    	}else if(ydata == 1){
    	    		ydata = "星期一";
    	    	}else if(ydata == 2){
    	    		ydata = "星期二";
    	    	}else if(ydata == 3){
    	    		ydata = "星期三";
    	    	}else if(ydata == 4){
    	    		ydata = "星期四";
    	    	}else if(ydata == 5){
    	    		ydata = "星期五";
    	    	}else if(ydata == 6){
    	    		ydata = "星期六";
    	    	}
    	        return ydata + "(" + xdata + "点" + ") : " + data + "人";
    	    }
		},
		animation : false,
		grid : {
			height : '50%'
		},
		xAxis : {
			type : 'category',
			data : data0
		},
		yAxis : {
			type : 'category',
			data : data1
		},
		visualMap : {
			min : 1,
			max : 411,
			calculable : true,
			orient : 'vertical',
			left : '90%',
			top : '10%',
			itemHeight : 270
		},
		series : [ {
			name : 'heatmap card',
			type : 'heatmap',
			data : data2,
			label : {
				normal : {
					show : true
				}
			},
			itemStyle : {
				emphasis : {
					shadowBlur : 10,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	};
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}