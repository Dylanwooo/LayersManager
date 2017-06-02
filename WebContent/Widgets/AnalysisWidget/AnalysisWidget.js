
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
         "Widgets/DataTabWidget/DataTabWidget",
         "dojo/text!./templates/Template.html"],
    function(declare,ArcGISDynamicMapServiceLayer,ArcGISTiledMapServiceLayer,BaseWidget,ajaxUtil,lang,on,topic,query, 
    		domStyle,domClass,domAttr,domConstruct, dom,html,Moveable,DataTabWidget,template) {
	    var date;
	    var TemArray = [];
	    var RainArray = [];
	    var PreArray = [];
	    var HumArray = [];
	    var WindArray = [];
	    var staName = [];
	    var Tem = [];
	    var Humid = [];
	    var Pressure = [];
	    var Rain = [];
	    var Wind = [];
	    var a,b,c,d;                  //记录图表对象，用于清除图表
	    var x = [];
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
            	setTimeout(function(){ 
            		//加载日期控件,minDate和maxDate限制选择的日期范围          		
            		$(function(){
            		    $( "#datepicker1" ).datepicker({ minDate: new Date(2015,12,1), maxDate:new Date(2016, 12, 31),dateFormat: 'dd-mm-yy',defaultDate:"01-01-2016"});
            		    $( "#datepicker2" ).datepicker({ minDate: new Date(2015,12,1), maxDate:new Date(2016, 12, 31),dateFormat: 'dd-mm-yy',defaultDate:"01-01-2016"});
            		  });   			             		            		
            	},0); 
            },
            upLoadDate:function(){
            	var date = $("#datepicker1").val();
            	var time = $("#time1").val();
            	var day = date.substr(0,2);
            	var month = date.substr(3,2);
            	var year = date.substr(6,4);
            	var hour = time.substr(0,2);
            	var title = month+day+year+hour;
            	topic.publish('Datadate',title);           	
            },
            dailyTemAnalysis:function(){
            	var date = $("#datepicker2").val();
            	var startTime = $("#time2").val();
            	var endTime = $("#time3").val();
            	var day = date.substr(0,2);
            	var month = date.substr(3,2);
            	var year = date.substr(6,4);
            	var hour1 = startTime.substr(0,2);
            	var hour2 = endTime.substr(0,2);  
            	var selectDate = month+day+year;
            	console.log(selectDate+hour1+hour2);
            },
            //综合分析
            analysisData:function(){
            	var self = this;
            	var date = $("#datepicker1").val();
            	var time = $("#time1").val();
            	var day = date.substr(0,2);
            	var month = date.substr(3,2);
            	var year = date.substr(6,4);
            	var hour = time.substr(0,2);
            	var title = month+day+year+hour;  
            	$.ajax({type:"post",url:"Data",data: {mtitle:title},
            		success:function(msg){           			    			
            			var list = JSON.parse(msg);
            		    for(var i=0;i<list.length;i++){
            		    	if(list[i].Tem!="缺测"||list[i].Pressure!="缺测"||list[i].Humid!="缺测"||list[i].Rain!="缺测"||list[i].Wind){
            		    		TemArray.push(list[i].Tem);
            		    		PreArray.push(list[i].Pressure);
            		    		HumArray.push(list[i].Humid);
            		    		RainArray.push(list[i].Rain);
            		    		WindArray.push(list[i].Wind);
            		    		staName.push(list[i].name);
            		    	} 
            		    	if(list[i].Tem!="缺测")
            		    		Tem.push(list[i].Tem);
            		    	if(list[i].Pressure!="缺测")
            		    		Pressure.push(list[i].Pressure);
            		    	if(list[i].Humid!="缺测"){
            		    		Humid.push(list[i].Humid);
            		    	}
            		    	if(list[i].Rain!="缺测"&&list[i].Rain!="无数据"){
            		    		Rain.push(list[i].Rain);
            		    	}
            		    	if(list[i].Wind!="缺测"){
            		    		Wind.push(list[i].Wind);
            		    	}
            		    }            		      
               			self.showLineChart();           		               	
            		}});            	
            },
            //检查日期是否为空
            checkNull:function(){            				  
            	var startDate = $(".selector1").datepicker("getDate");     
            	var time1 = document.getElementById("time1");           	
            	if(startDate==null||time1==null)
            	{
            		layui.use(['layer', 'form'], function(){
	            		  var layer = layui.layer
	            		  ,form = layui.form();           		  
	            		  layer.msg('时间参数不能为空！');
	            		});	
            	}	             	
            }, 
            
            
            //综合分析图
            showLineChart:function(){
            	var myChart = echarts.init(document.getElementById('line'));   
            	a= myChart;
            	var colors = ['#FF7F00', '#1E90FF', '#FF6A6A','#00FFFF','#FFEC8B'];
            	myChart.setOption({
            		color: colors,

            	    tooltip: {
            	        trigger: 'axis',
            	        axisPointer: {
            	            type: 'cross'
            	        }
            	    },
            	    grid: {
            	        right: '20%'
            	    },
            	    toolbox: {
            	        feature: {
            	            dataView: {show: true, readOnly: false},
            	            restore: {show: true},
            	            saveAsImage: {show: true}
            	        }
            	    },
            	    legend: {
            	        data:[{name:'气压',
            	        	textStyle:{color:"white"}
            	        },{name:'相对湿度',
            	        	textStyle:{color:"white"}
            	        },{name:'温度',textStyle:{color:"white"}},
            	        {name:'降雨',textStyle:{color:"white"}},
            	        {name:'风速',textStyle:{color:"white"}}]
            	    },
            	    dataZoom:[{
            	    	type:'slider',
            	    	start:0,
            	    	end:50
            	    },{
            	    	type:'inside',
            	    	start:0,
            	    	end:50
            	    }],
            	    xAxis: [
            	        {
            	            type: 'category',
            	            axisTick: {
            	                alignWithLabel: true
            	            },
            	            data: staName,
            	            axisLine: {
            	                lineStyle: {
            	                    color: "white",
            	                    width:2
            	                }
            	            },
            	        }
            	    ],
            	    yAxis: [
            	        {
            	            type: 'value',
            	            name: '气压',
            	            min: 0,
            	            max: 1100,
            	            position: 'right',           	            
            	            axisLine: {
            	                lineStyle: {
            	                    color: colors[0],
            	                    width:2
            	                }
            	            },
            	            axisLabel: {
            	                formatter: '{value} Pa'
            	            }
            	        },
            	        {
            	            type: 'value',
            	            name: '相对湿度',
            	            min: 0,
            	            max: 100,
            	            position: 'right',            	            
            	            offset: 80,
            	            axisLine: {
            	                lineStyle: {
            	                    color: colors[1],
            	                    width:2
            	                }
            	            },
            	            axisLabel: {
            	                formatter: '{value} %'
            	            }
            	        },
            	        {
            	            type: 'value',
            	            name: '温度',
            	            min: -10,
            	            max: 20,
            	            position: 'left',
            	            axisLine: {
            	                lineStyle: {
            	                    color: colors[2],
            	                    width:2
            	                }
            	            },
            	            axisLabel: {
            	                formatter: '{value} °C'
            	            }
            	        },
            	        {
            	            type: 'value',
            	            name: '降雨',
            	            min: 0,
            	            max: 10,
            	            position: 'left',
            	            offset:50,
            	            axisLine: {
            	                lineStyle: {
            	                    color: colors[3],
            	                    width:2
            	                }
            	            },
            	            axisLabel: {
            	                formatter: '{value} ml'
            	            }
            	        },
            	        {
            	            type: 'value',
            	            name: '风速',
            	            min: 0,
            	            max: 10,
            	            position: 'right',
            	            offset:130,
            	            axisLine: {
            	                lineStyle: {
            	                    color: colors[4],
            	                    width:2
            	                }
            	            },
            	            axisLabel: {
            	                formatter: '{value} m/s'
            	            }
            	        }
            	        
            	    ],
            	    series: [
            	             {
            	                 name:'气压',
            	                 type:'bar',
            	                 data: PreArray
            	             },
            	             {
            	                 name:'相对湿度',
            	                 type:'bar',
            	                 yAxisIndex: 1,
            	                 data:HumArray
            	             },
            	             {
            	                 name:'温度',
            	                 type:'line',
            	                 yAxisIndex: 2,
            	                 data:TemArray
            	             },
            	             {
            	                 name:'降雨',
            	                 type:'line',
            	                 yAxisIndex: 3,
            	                 data:RainArray
            	             },
            	             {
            	                 name:'风速',
            	                 type:'line',
            	                 yAxisIndex: 4,
            	                 data:WindArray
            	             }
            	         ]
            	});
            	var myPieChart = echarts.init(document.getElementById('pie')); 
            	b = myPieChart;
            	myPieChart.setOption({
            		title:{
            			text:'数据密度分布',
            			x:'center',
            			textStyle: {  
            	            fontWeight: 'normal',              
            	            color: 'white'  
            	        },
            		},
            		tooltip : {
            	        trigger: 'item',
            	        formatter: "{a} <br/>{b} : {c} ({d}%)"
            	    },
            	    legend: {
            	        x : 'center',
            	        y : 'bottom',
            	        data:['温度','气压','相对湿度','降雨','风速']
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
            	    series:[           	            
            	            {           	    	
            	            name:'数据密度',
            	            type:'pie',
            	            radius : [30, 110],
            	            center : ['75%', '50%'],
            	            roseType : 'radius',
            	            data:[
            	            	{value:Tem.length,name:'温度'},
            	            	{value:Humid.length,name:'相对湿度'},
            	            	{value:Pressure.length,name:'气压'},
            	            	{value:Rain.length,name:'降雨'},
            	            	{value:Wind.length,name:'风速'}
            	            ]            	        
            	    }]
            	})
            },
            //小于10的数组前面加0
            addZero:function (vNumber) {
                return ((vNumber < 10) ? "0" : "") + vNumber;
            },
            
                      
            dailyTemAnalysis:function(){
            	var self = this;           	  
            	var date = $("#datepicker2").val();           	 
            	var day = date.substr(0,2);
            	var month = date.substr(3,2);
            	var year = date.substr(6,4);            	
            	var random = Math.floor(Math.random()*25);
            	var title = month+day+year+this.addZero(random);           	
            	
            	$.ajax({type:"post",url:"Data",data: {mtitle:title},
            		success:function(msg){            			
            			var list = JSON.parse(msg);
            		    for(var i=0;i<list.length;i++){
            		    	if(list[i].Tem!="缺测"){
            		    		TemArray.push(list[i].Tem);
            		    		staName.push(list[i].name);
            		    	} 
            		    }
            		    
            		    self.showDailyTem();
            		}
            	})
            },
            //取随机数
            getRandomArrayElements:function(arr,count) {
                var shuffled = arr.slice(0), i = arr.length, min = i - count, temp, index;
                while (i-- > min) {
                    index = Math.floor((i + 1) * Math.random());
                    temp = shuffled[index];
                    shuffled[index] = shuffled[i];
                    shuffled[i] = temp;
                }
                return shuffled.slice(min);
            },
            //日温变化曲线
            showDailyTem:function(){
            	var myChart = echarts.init(document.getElementById('line'));
            	c = myChart;
            	var item = staName[Math.floor(Math.random()*staName.length)];
            	var data = this.getRandomArrayElements(TemArray,24);
            	var avg = staName[Math.floor(Math.random()*staName.length)];
        		myChart.setOption({
            		title: {
            	        text: item+'观测点',
            	        textStyle: {  
            	            fontWeight: 'normal',              //标题颜色  
            	            color: 'white'  
            	        },  
            	    },
            	    tooltip: {},
            	    xAxis: {
            	        data: ['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'],
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
        		var gauge = echarts.init(document.getElementById('pie'));
        		d=gauge;
        		gauge.setOption({
       			
        			tooltip : {
        		        formatter: "{a} <br/>{b} : {c}%"
        		    },
        		    toolbox: {
        		        feature: {
        		            restore: {},
        		            saveAsImage: {}
        		        }
        		    },
        		    series: [
        		        {
        		            name: '数据指标',
        		            type: 'gauge',
        		            detail: {formatter:'{value}%'},
        		            data: [{value: (Math.random() * 50).toFixed(2) - 0, name: '炎热指数'}]
        		        }
        		    ]
        		});       		

            },
            
            bindEvent:function(){
            	on(this.clear,"click",lang.hitch(this,function(){               		          	
            		this.destroy();          		
        	    }));   
            	on(this.showTable,"click",lang.hitch(this,function(){  
            		this.upLoadDate();
            		var dataTab=new DataTabWidget({
            			args:date,
                		map:this.map            			
                    });            		
            		dataTab.startup();               	                	
            		dataTab.placeAt(dojo.body());            		
        	    })); 
            	on(this.analysis,"click",lang.hitch(this,function(){ 
            		this.checkNull();           		    
            		this.analysisData();           			           		               	
	                  
            	}));
            	on(this.daliyTem,"click",lang.hitch(this,function(){            		            		     
            			this.dailyTemAnalysis();           			           		               	
	                  
            	}));
            	on(this.clearCharts,"click",lang.hitch(this,function(){
            		if(a!=null)
            			a.clear();               		           		
            		if(b!=null)
            			b.clear();
            		if(c!=null)
            			c.clear();
            		if(d!=null)
            			d.clear();
        	}))
            },         
        })
    }
);