define([ "dojo/_base/declare",
         "esri/layers/ArcGISDynamicMapServiceLayer",
         "esri/layers/ArcGISTiledMapServiceLayer",
         "Widgets/baseWidget/BaseWidget",
         "Widgets/util/ajaxUtil",
         "dojo/_base/lang",
         "dojo/on",
         "dojo/topic",
         "dojo/query",
         "dojo/dom-style",
         "dojo/dom-class",
         "dojo/dom-attr",
         "dojo/dom-construct", 
         "dojo/dom",
         "dojo/html",
         "dojo/dnd/Moveable",
         "dojo/text!./templates/Template.html"],
    function(declare,ArcGISDynamicMapServiceLayer,ArcGISTiledMapServiceLayer,BaseWidget,ajaxUtil,lang,on,topic,query, 
    		domStyle,domClass,domAttr,domConstruct, dom,html,Moveable,template) {
	    var index;
	    var staName = [];
        return declare([BaseWidget], {
            templateString : template,           
            constructor : function(args) {
                this.inherited(arguments);
                this.map=args.map;
            },
            
            postCreate : function() {
                this.inherited(arguments);
                this.init();
                this.bindEvent();
                
            },
            
            startup:function(){
                this.inherited(arguments);
                
            },
            
            destroy:function(){
                this.inherited(arguments);
            },
            
            init:function(){ 
            	//记录下传过来的点击哪个数据类型
            	topic.subscribe('option',lang.hitch(this,function(arg){
					index = arg;					
					//温度	            	
					if(index==0){
						setTimeout(lang.hitch(this,function(){           		
		              		  this.temCharts();            		               	
		                  }),0); 
					}					
					//最高最低气温
					else if(index==1){									            	
						setTimeout(lang.hitch(this,function(){           		
		              		  this.temMaxMin();            		               	
		                  }),0);
					}
					//降雨
					else if(index==2){
						setTimeout(lang.hitch(this,function(){           		
		              		  this.rainCharts();            		               	
		                  }),0); 
					}
					//气压
					else if(index==3){
						setTimeout(lang.hitch(this,function(){           		
		              		  this.preCharts();            		               	
		                  }),0);
					}
					//相对湿度
					else if(index==4){
						setTimeout(lang.hitch(this,function(){           		
		              		  this.humCharts();            		               	
		                  }),0);
					}
					//风
					else if(index==6){
						setTimeout(lang.hitch(this,function(){           		
		              		  this.windCharts();            		               	
		                  }),0);
					}
					
				})); 
            	
                       	       
            },
            
            temCharts:function(){
            	var myChart = echarts.init(document.getElementById('main'));           	
            	topic.subscribe('tem',lang.hitch(this,function(data){
            	  topic.subscribe('temName',lang.hitch(this,function(name){
            		myChart.setOption({
                		title: {
                	        text: '各站点气温曲线图',
                	        textStyle: {  
                	            fontWeight: 'normal',              //标题颜色  
                	            color: 'white'  
                	        },  
                	    },
                	    tooltip: {},
                	    xAxis: {
                	        data: name,
                	        axisLine: {
            	                lineStyle: {
            	                    color: "white",
            	                    width:2
            	                }
            	            }
                	    },
                	    yAxis:  {
                	    	axisLine: {
        	                lineStyle: {
        	                    color: "white",
        	                    width:2
        	                }
        	            }
                	    },
                	    visualMap:[{
                        	type:'piecewise',
                        	min:-9,
                        	max:12,
                        	inRange:{
                        		color:['#FFFFE0','#FFD700','#FF6347','#FF0000','#7A378B'],
                        		symbolSize: [-9, 12]
                        	},
                        	colorLightness: [0.2, 1],
                        	outOfRange:{
                        		symbolSize:[-9, 12]
                        	},
                        	right:0,
                        	top:0,
                        	textStyle: {  
                	            fontWeight: 'normal',         
                	            color: 'white'  
                	        }, 
                        }],
                	    dataZoom:[{
                	    	type:'slider',
                	    	start:0,
                	    	end:40
                	    },{
                	    	type:'inside',
                	    	start:0,
                	    	end:40
                	    }],
                	    toolbox: {
                            left: 'center',
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                restore: {},
                                magicType: {type: ['line', 'bar']},
                                saveAsImage: {}
                            }
                        },
                        series: {
                	        name: '气温',
                	        type: 'line',
                	        data: data,               	        
                	        blendMode:"lighter",
                	        markLine:{
                	        	silent:true,
                	        	data:[{
                	        		yAxis:5
                	        	}               	        	
                	        	]
                	        }
                	    }        		
                	});
				}));
             }));  
            },
            
            rainCharts:function(){
            	var myChart = echarts.init(document.getElementById('main'));             	
            	topic.subscribe('rain',lang.hitch(this,function(data){
            	  topic.subscribe('rainName',lang.hitch(this,function(name){
            		var bins = myChart.histogram(data);
            		myChart.setOption({
                		title: {
                	        text: '降雨柱状图',
                	        textStyle: {  
                	            fontWeight: 'normal',              //标题颜色  
                	            color: 'white'  
                	        },
                	    },
                	    color:['rgb(25, 183, 207)'],
                	    legend: {
                	        data:{name:'降雨',
                	              textStyle:{color:white}}
                	    },
                	    xAxis: {
                	    	scale:true,
                	        data: name,
                	        axisLine: {
            	                lineStyle: {
            	                    color: "white",
            	                    width:2
            	                }
            	            }
                	    },
                	    toolbox: {
                            left: 'center',
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                restore: {},
                                magicType: {type: ['line', 'bar']},
                                saveAsImage: {}
                            }
                        },
                	    yAxis: {
                	    	type:'value',
                	    	axisLine: {
            	                lineStyle: {
            	                    color: "white",
            	                    width:2
            	                }
            	            }
                	    },
                	    dataZoom:[{
                	    	type:'slider',
                	    	start:0,
                	    	end:40
                	    },{
                	    	type:'inside',
                	    	start:0,
                	    	end:40
                	    }],
                	    visualMap:[{
                        	type:'piecewise',                        	
                        	inRange:{
                        		color:['#E0FFFF','#87CEFA','#00BFFF','#1E90FF'],
                        		symbolSize: [30, 100]
                        	},
                        	outOfRange:{
                        		symbolSize:[30,100]
                        	},
                        	right:0,
                        	top:0
                        }],
                	    series: [{
                	        name: '降雨',
                	        type: 'bar',
                	        barWidth: '99.3%',
                	        label: {
                	            normal: {
                	                show: true,
                	                position: 'insideTop',
                	                formatter: function(params) {
                	                    return params.value[1];
                	                }
                	            }
                	        },
                	        data: bins.data
                	    }]        		
                	});
				}));
             }));  
            },
            
            preCharts:function(){
            	var myChart = echarts.init(document.getElementById('main'));           	
            	topic.subscribe('pre',lang.hitch(this,function(data){
            	  topic.subscribe('preName',lang.hitch(this,function(name){
            		myChart.setOption({
                		title: {
                	        text: '气压折线图',
                	        textStyle: {  
                	            fontWeight: 'normal',              //标题颜色  
                	            color: 'white'  
                	        },
                	    },
                	    tooltip: 'axis',               	    
                	    xAxis: {
                	        data: name,
                	        axisLine: {
            	                lineStyle: {
            	                    color: "white",
            	                    width:2
            	                }
            	            }
                	    },
                	    yAxis: {
                	    	axisLine: {
        	                lineStyle: {
        	                    color: "white",
        	                    width:2
        	                }
        	            }},
                	    visualMap:[{
                        	type:'piecewise',
                        	inRange:{
                        		color:['#FFE7BA','#FFD39B','#FFA54F','#FF7F24'],
                        		symbolSize: [30, 100]
                        	},
                        	outOfRange:{
                        		symbolSize:[30,100]
                        	},
                        	right:0,
                        	top:0
                        }],
                        dataZoom:[{
                	    	type:'slider',
                	    	start:0,
                	    	end:40
                	    },{
                	    	type:'inside',
                	    	start:0,
                	    	end:40
                	    }],
                	    toolbox: {
                            left: 'center',
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                restore: {},
                                magicType: {type: ['line', 'bar']},
                                saveAsImage: {}
                            }
                        },
                	    series: {
                	        name: '气压',
                	        type: 'line',
                	        data: data,
                	        markLine:{
                	        	silent:true,
                	        	data:[{
                	        		yAxis:300
                	        	},{
                	        		yAxis:500
                	        	},
                	        	{
                	        		yAxis:700
                	        	}
                	        	]
                	        }
                	    }       		
                	});
				}));           	  
             }));           	
            },
            humCharts:function(){
            	var myChart = echarts.init(document.getElementById('main')); 
            	
            	topic.subscribe('hum',lang.hitch(this,function(data){
            	  topic.subscribe('humName',lang.hitch(this,function(name){
            		myChart.setOption({
                		title: {
                	        text: '湿度流量图',
                	        textStyle: {  
                	            fontWeight: 'normal',              //标题颜色  
                	            color: 'white'  
                	        },
                	    },               	                   	    
                	    xAxis: {
                	    	type: 'category',
                	        boundaryGap: false,
                	        data: name ,
                	        axisLine: {
            	                lineStyle: {
            	                    color: "white",
            	                    width:2
            	                }
            	            }
                	    },
                	    yAxis: {
                	    	type: 'value',
                	        boundaryGap: [0,'100%'],
                	        axisLine: {
            	                lineStyle: {
            	                    color: "white",
            	                    width:2
            	                }
            	            }
                	    },
                	    dataZoom:[{
                	    	type: 'inside',
                	        start: 0,
                	        end: 30
                	    }, {
                	        start: 0,
                	        end: 10,
                	        handleSize: '80%',
                	        handleStyle: {
                	            color: '#fff',
                	            shadowBlur: 3,
                	            shadowColor: 'rgba(0, 0, 0, 0.6)',
                	            shadowOffsetX: 2,
                	            shadowOffsetY: 2
                	        }
                	    }],
                	    toolbox: {
                            left: 'center',
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                restore: {},
                                magicType: {type: ['line', 'bar']},
                                saveAsImage: {}
                            }
                        },                     
                        series: [
                                 {
                                	 name:'湿度数据',
                                     type:'line',
                                     smooth:true,
                                     symbol: 'none',
                                     sampling: 'average',
                                     itemStyle: {
                                         normal: {
                                             color: 'rgb(0,255,255)'
                                         }
                                     },
                                     areaStyle: {
                                         normal: {
                                             color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                                 offset: 0,
                                                 color: 'rgb(0,245,255)'
                                             }, {
                                                 offset: 1,
                                                 color: 'rgb(0,255,255)'
                                             }])
                                         }
                                     },
                                     data: data
                                 }
                         ]    		
                	});
				}));           	  
             })); 
            },
            windCharts:function(){ 
              var myChart = echarts.init(document.getElementById('main'));              
            	topic.subscribe('wind',lang.hitch(this,function(data){
            	  topic.subscribe('direction',lang.hitch(this,function(direct){
            		    myChart.setOption({
            		    	title:{
            		    		text:'风速风向图',
            		    		textStyle: {  
                    	            fontWeight: 'normal',              //标题颜色  
                    	            color: 'white'  
                    	        },
            		    	},
            		    	lengend:{
            		    		data:{name:'风速',
            		    			textStyle:{color:"white"}},
            		    		left:'right'
            		    	},
            		    	polar:[{
            		    		tooltip:{
            		    			show:true,	
            		    		}
            		    	}],
            		    	tooltip: {
            		            formatter: '风速:'+'{c0}'
            		        },
            		    	toolbox: {
                                left: 'center',
                                feature: {
                                    dataZoom: {
                                        yAxisIndex: 'none'
                                    },
                                    restore: {},
                                    saveAsImage: {}
                                }
                            },
                            angleAxis: {
                                type: 'category',
                                data: ['45','90','135','180','225','270','315','360'],
                                boundaryGap: false,
                                splitLine: {
                                    show: true,
                                    lineStyle: {
                                        color: '#999',
                                        type: 'dashed'
                                    }
                                },
                                axisLine: {
                                    show: false
                                }
                            },
                            radiusAxis: {
                                type: 'category',
                                data: data,
                                axisLine: {
                                    show: false
                                },
                                axisLabel: {
                                    rotate: 45
                                }
                            },
                            series: [{
                                name: '风速风向图',
                                type: 'scatter',
                                coordinateSystem: 'polar',
                                symbolSize: function (val) {
                                    return val[2] * 2;
                                },
                                data: data,
                                animationDelay: function (idx) {
                                    return idx * 5;
                                }
                            }]
            		    	
            		    });
            		}));           	  
                }));            	  
            },
            
            temMaxMin:function(){           	
            	var myChart = echarts.init(document.getElementById('main'));              
            	topic.subscribe('temMax',lang.hitch(this,function(max){
            	  topic.subscribe('temMin',lang.hitch(this,function(min){
//            		  topic.subscribe('max_minName',lang.hitch(this,function(temName){		            	
            		    myChart.setOption({
            		    	title: {
            		            text: '站点最高/低气温曲线',
            		            subtext: '/小时',
            		            textStyle: {  
                    	            fontWeight: 'normal',              //标题颜色  
                    	            color: 'white'  
                    	        },
            		        },
            		        tooltip: {
            		            trigger: 'axis'
            		        },
            		        legend: {
            		            data:[{name:'最高气温',textStyle:{color:'white'}},
            		                  {name:'最低气温',textStyle:{color:'white'}}]
            		        },
            		        dataZoom:[{
                    	    	type:'slider',
                    	    	start:0,
                    	    	end:40
                    	    },{
                    	    	type:'inside',
                    	    	start:0,
                    	    	end:40
                    	    }],
            		        toolbox: {
            		            show: true,
            		            feature: {
            		                dataZoom: {
            		                    yAxisIndex: 'none'
            		                },
            		                dataView: {readOnly: false},
            		                magicType: {type: ['line', 'bar']},
            		                restore: {},
            		                saveAsImage: {}
            		            }
            		        },
            		        xAxis:  {
            		            type: 'category',
            		            boundaryGap: false,
            		            data: ["柴湖", "东川", "三堰淌村", "埠河", "藕池", "卷桥水库", "周老嘴", "朱河", "福田", "汴河", "程集", "长江村", "螺山", "乌林", "峰口", "滨湖", "长大东区", "郢城", "菱角湖", "岑河", "观音挡", "熊河", "荆州老南门", "宜汉高速太湖港", "彭店", "刘集", "夏店", "黄站", "余河", "赵谷冲", "陈店", "田店", "天鹅", "盛滩", "汈汊湖", "王店", "周巷", "白沙", "义堂", "曾店", "伍洛", "肖港", "朱湖", "渔薪镇", "张港镇", "佛子山镇", "汪场镇", "马湾镇", "多宝", "石河镇", "沉湖", "卢市", "高石碑镇", "广华镇", "浩口镇", "熊口镇", "张金镇", "后湖农场", "总口农场", "周矶农场", "熊口农场", "市农科所", "郑场", "毛嘴", "三伏潭", "周帮", "杨林尾", "剅河", "沔城", "胡场", "彭场", "城区", "张公", "李家井水库", "苍梧岭", "三乐闸", "铜山村", "官塘", "柳山湖", "张家坝", "老河泵站", "沙坪", "肖岭", "大湖山", "路口", "塘口", "东源村", "白螺", "麦市", "关刀桥", "阔田", "石南桥", "神龙坪水库", "东冲水库", "云溪水库", "盘石", "下畈村", "闯王", "黄沙", "杨芳","望江岭", "慈口", "梅田", "富有", "下陆", "西塞山", "铁矿", "棋盘洲", "东方山", "军分区", "殷祖镇", "大箕铺镇", "保安镇", "刘仁八镇", "黄岗村", "还地桥镇", "港湖", "金山店铁矿", "灵乡", "岩山", "董家口", "石家皖", "红峰水库", "保安湖", "均畈村", "", "富池镇", "洋港镇", "浮屠镇", "木港镇", "军垦", "白沙镇", "网湖", "吉山", "鸡笼山", "太平村", "燕矶", "杨叶", "杜山", "程潮铁矿", "庙岭", "蒲团", "段店", "临江", "葛店", "太和", "涂家垴", "沼山", "梁子湖", "狮子口水库", "觅儿寺", "太平桥", "永佳河", "戴长塘", "依河墩", "三河口", "熊家铺", "顺河集", "百果", "中驿", "长岭岗", "南湖", "明山", "麻城老站", "匡河", "天堂寨风景区", "三里畈", "大崎", "严家畈", "四级电站", "", "方家咀", "雷家店", "石头咀", "桃花冲", "张家咀", "乐园", "四口冲", "吴家山", "吉子山", "胡家山", "东庄畈", "兰溪", "白莲河", "竹瓦", "洗马", "蔡河镇", "浠水", "叶家冲", "高家坳", "丰山", "黄河厂", "牛皮坳", "彭思", "管窑", "张榜", "赤东", "狮子", "青石", "浏河曹庙"],
            		            axisLine: {
                	                lineStyle: {
                	                    color: "white",
                	                    width:2
                	                }
                	            }
            		        },
            		        yAxis: {
            		            type: 'value',
            		            axisLabel: {
            		                formatter: '{value} °C'
            		            },
            		            axisLine: {
                	                lineStyle: {
                	                    color: "white",
                	                    width:2
                	                }
                	            }
            		        },
            		        series: [
            		                 {
            		                     name:'最高气温',
            		                     type:'line',
            		                     data: max,
            		                     markPoint: {
            		                         data: [
            		                             {type: 'max', name: '最大值'},
            		                             {type: 'min', name: '最小值'}
            		                         ]
            		                     },
            		                     markLine: {
            		                         data: [
            		                             {type: 'average', name: '平均值',textStyle:{color:'white'}}
            		                         ]
            		                     },
            		                     itemStyle : {    
            		                         normal : {    
            		                             lineStyle:{    
            		                                 color:'#FF3030'    
            		                             }    
            		                         }    
            		                     },    
            		                 },
            		                 {
            		                     name:'最低气温',
            		                     type:'line',
            		                     data:min,
            		                     markPoint: {
            		                         data: [
            		                             {name: '最低', value: -2, xAxis: 1, yAxis: -1.5}
            		                         ]
            		                     },
            		                     markLine: {
            		                         data: [
            		                             {type: 'average', name: '平均值'},
            		                             [{
            		                                 symbol: 'none',
            		                                 x: '90%',
            		                                 yAxis: 'max'
            		                             }, {
            		                                 symbol: 'circle',
            		                                 label: {
            		                                     normal: {
            		                                         position: 'start',
            		                                         formatter: '最大值',
            		                                         textStyle:{
            		                                        	 color:'#FF3030',
            		                                        	 width:2
            		                                         }
            		                                     }
            		                                 },
            		                                 type: 'max',
            		                                 name: '最高点'
            		                             }]
            		                         ]
            		                     },
            		                     itemStyle : {    
            		                         normal : {    
            		                             lineStyle:{    
            		                                 color:'#00FFFF'    
            		                             }    
            		                         }    
            		                     },
            		                 }
            		             ]           		    	
            		    });
            		}));           	  
                })); 
            	//}));
            },
            
            bindEvent:function(){
            	on(this.clear,"click",lang.hitch(this,function(){            		
            		this.destroy();	
        	    }));             	
            },           
           
    		           
        })
    }
);