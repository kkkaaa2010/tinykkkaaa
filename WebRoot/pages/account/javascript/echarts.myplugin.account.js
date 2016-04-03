function drawExpensesFx(legendData, inData, outData){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('expensesPie'));
    // 指定图表的配置项和数据
    option = {
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c}元 ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        x: 'left',
	        data:legendData
	    },
	    series: [
	        {
	            name:'支出',
	            type:'pie',
	            selectedMode: 'single',
	            radius: [0, '30%'],

	            label: {
	                normal: {
	                    position: 'inner'
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:inData
	        },
	        {
	            name:'支出项目',
	            type:'pie',
	            radius: ['40%', '55%'],
	            label: {
	                normal: {
	                	formatter: '{b}: {c}￥'
	                }
	            },
	            data:outData
	        }
	    ]
	};
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function drawIncomeFx(legendData, seriesData){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('incomePie'));
    // 指定图表的配置项和数据
    option = {
	    title : {
	        text: '收入分析',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c}元 ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data: legendData
	    },
	    series : [
	        {
	            name: '收入',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '50%'],
	            data: seriesData,
	            label: {
	                normal: {
	                	formatter: '{b}: {c}￥'
	                }
	            },
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function drawZttjFx(legendData, seriesData){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('zttjPie'));
    // 指定图表的配置项和数据
    option = {
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c}元"
	    },
	    legend: {
	        orient: 'vertical',
	        x: 'left',
	        data: legendData
	    },
	    series: [
	        {
	            name:'账单',
	            type:'pie',
	            center : ['50%', '50%'],
	            radius: ['50%', '70%'],
	            avoidLabelOverlap: true,
	            label: {
	                normal: {
	                    show: true,
	                    position: 'center',
	                    formatter: function(params){
	                    	var res = params.name + ":" + params.percent + "%";
	                    	return res;
	                    },
                        textStyle: {
	                        fontSize: '20',
	                        baseline: 'bottom',
	                        fontWeight: 'bold'
                        }
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data: seriesData
	        }
	    ]
	};
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
function drawRecentlyAccount(dmData, xAxisData, maxYAxis, timelineData, optionData){
	var myChart = echarts.init(document.getElementById('reAccountID'));

	option = {
	    baseOption: {
	        timeline: {
	            axisType: 'category',
	            autoPlay: true,
	            playInterval: 2000,
	            data: timelineData,
	            label: {
	                formatter : function(s) {
	                    return s.substring(0,4) + '-' + s.substring(4,6) + '-' + s.substring(6,8);
	                }
	            }
	        },
	        tooltip: {},
	        //legend: {
	        //    x: 'right',
	        //    data: ['第一产业', '第二产业', '第三产业', 'GDP', '金融', '房地产'],
	        //    selected: {
	        //        'GDP': false, '金融': false, '房地产': false
	        //    }
	        //},
	        calculable : true,
	        grid: {
	            top: 50,
	            bottom: 70
	        },
	        xAxis: [
	            {
	                'type':'category',
	                'axisLabel':{'interval':0},
	                'data': xAxisData,
	                splitLine: {show: false}
	            }
	        ],
	        yAxis: [
	            {
	                type: 'value',
	                name: '支出（元）',
	                max: maxYAxis
	            }
	        ],
	        series: [
	            {	name: '支出类型', 
	            	type: 'bar', 
	            	barWidth: 30,
	                label: {
	                    normal: {
	                        show: true,
	                        position: 'top',
	                        formatter: '{c}元'
	                    }
	                },
	            },
	            {
	                name: '支出类型占比',
	                type: 'pie',
	                center: ['75%', '35%'],
	                radius: '30%',
	                label: {
	                    normal: {
	                        show: true,
	                        position: 'inside',
	                        formatter: '{b}:{d}%'
	                    }
	                }
	            }
	        ]
	    },
	    options: optionData
	};
	
	myChart.setOption(option);
}