define([ "dojo/_base/declare",
         "esri/layers/ArcGISDynamicMapServiceLayer",
         "esri/layers/ArcGISTiledMapServiceLayer",
         "Widgets/baseWidget/BaseWidget",
         "Widgets/util/ajaxUtil",
         "dojo/_base/lang",
         "dojo/on",
         "dojo/query",
         "dojo/dom-style",
         "dojo/dom-class",
         "dojo/dom-attr",
         "dojo/dom-construct", 
         "dojo/dom",
         "dojo/html",
         "dojo/topic",
         "dojo/dnd/Moveable",
         "esri/renderers/HeatmapRenderer",
         "esri/layers/GraphicsLayer",
         "esri/InfoTemplate", 
         "esri/geometry/Point",
	     "esri/graphic",
	     "esri/layers/FeatureLayer",
         "esri/symbols/SimpleMarkerSymbol",         
         "Widgets/tableWidget/tableWidget",
         "esri/renderers/SimpleRenderer",
         "Widgets/HeatMapWidget/HeatMapLayer",
         "Widgets/DataTabWidget/DataTabWidget",
         "dojo/text!./templates/Template.html"],
    function(declare,ArcGISDynamicMapServiceLayer,ArcGISTiledMapServiceLayer,BaseWidget,ajaxUtil,lang,on,query, 
    		domStyle,domClass,domAttr,domConstruct, dom,html,topic,Moveable,HeatmapRenderer,GraphicsLayer,
    		InfoTemplate,Point,Graphic,FeatureLayer,SimpleMarkerSymbol,tableWidget,SimpleRenderer,HeatMapLayer,DataTabWidget,
    		template) {
	    var index;
	    //全局变量graphic图层
	    var layer = new GraphicsLayer();
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
            		$(function() {
            		    $( "#datepicker1" ).datepicker({ minDate: new Date(2015,12,1), maxDate:new Date(2016, 12, 31),dateFormat: 'dd-mm-yy',defaultDate:"01-01-2016"});
            		  });
            		           		
            		layui.use('element', function(){ 
            			var element = layui.element();           			  
          			    element.on('tab(tab)',function(data){
          				  //全局变量index来记录点击了哪个tab      				  
          			     index = data.index;
          			     topic.publish("option",index);
        			});
          			  
      			  })            		            		
            	},0);            	
            },            
            lonlatTomercator:function(lonlat) {
                var mercator={x:0,y:0};
                var x = lonlat.x *20037508.34/180;
                var y = Math.log(Math.tan((90+lonlat.y)*Math.PI/360))/(Math.PI/180);
                y = (y*20037508.34)/180;
                mercator.x = x-180000;         //随意加的数   
                mercator.y = y+11900000;                    
                return mercator ;                  
            },
            //去除重复值
            unique:function(arr){
            	var result = [], hash = {};
                for (var i = 0, elem; (elem = arr[i]) != null; i++) {
                    if (!hash[elem]) {
                        result.push(elem);
                        hash[elem] = true;
                    }
                }
                return result;
            },
                                 
            uploadDate:function(){
            	var date = $("#datepicker1").val();
            	var time = $("#time1").val();
            	var day = date.substr(0,2);
            	var month = date.substr(3,2);
            	var year = date.substr(6,4);
            	var hour = time.substr(0,2);
            	var title = month+day+year+hour;
            	//数据数组
            	var TemArray = [];
            	var HumidArray = [];
            	var PressArray = [];
            	var windArray = [];
            	var windDirect = [];
            	var rainArray = [];
            	var temMax = [];
            	var temMin = [];
            	//站名数组
            	var TemName = [];
            	var HumName = [];
            	var PreName = [];
            	var windName = [];
            	var rainName = [];
            	var TemMaxMinName = [];
            	//温度点经纬度坐标数组
        		var TemmX = [];
        		var TemmY = []; 
        		//湿度点经纬度坐标
        		var HummX = [];
        		var HummY = [];
        		//气压点经纬度坐标
        		var PreX =[];
        		var PreY =[];
        		//风点经纬度坐标
        		var windX = [];
        		var windY = [];
        		//降水经纬度坐标
        		var rainX = [];
        		var rainY = [];
        		
        		//记录当前this指针
          		var self = this;             		            
        		//ajax请求数据         
            	$.ajax({type:"post",url:"Data",data: {mtitle:title},
            		success:function(msg){   
            			
            			var list = JSON.parse(msg);             			
                 //温度函数
                 if(index==0||index==1){ 
	                	 for(var i=0;i<list.length;i++)
	         			{
	         			  if(list[i].Tem!="缺测")
	     				  {
	         				TemArray.push(list[i].Tem);
	         				TemmX.push(list[i].Lon);
	         				TemmY.push(list[i].La); 
	         				TemName.push(list[i].name);
	         				         				
	     				  }
	         			  if(list[i].max_Tem!="缺测"&&list[i].min_Tem){
	         				temMax.push(list[i].max_Tem);
		         			temMin.push(list[i].min_Tem);		         			
		         			TemMaxMinName.push(list[i].name);
	         			  }
	         			}
                		self.heatmapContainer = new HeatMapLayer({               			
                			config:{
                				"useLocalMaximum":false,
                				"radius":60, 
                				"gradient":{
                					0.45: "rgb(000,000,255)",
                                    0.55: "rgb(000,255,255)",
                                    0.65: "rgb(000,255,000)",
                                    0.95: "rgb(255,255,000)",
                                    1.00: "rgb(255,000,000)"
                                }
                			},              			
                			"map": self.map,
            		        "opacity": 0.65,
                		},"heatmapContainer");  
                		
                		for(var i=0;i<TemArray.length;i++){               			
                			var pt = new Point(TemmX[i],TemmY[i]); 
                			var ptNew = self.lonlatTomercator(pt);               			
                			var pt2 = new Point(ptNew.x,ptNew.y,self.map.spatialReference);               			
                			var PointSymbol = new SimpleMarkerSymbol();
		               	    PointSymbol.style = SimpleMarkerSymbol.STYLE_CIRCLE;
		               	    PointSymbol.setColor("FFCC00"); 
		               	    var attr = {a:1};
	               	        var infoTemplate = new InfoTemplate();
                			var gra = new Graphic(pt2,PointSymbol,attr,infoTemplate);                			
                			layer.add(gra);               			
          		       }                  		
                		layer.hide(); 
                		//设置监听是否清除图层
 						topic.subscribe('temClear',lang.hitch(this,function(arg){
 							if(arg=="tempreture") 								
 								var oDiv = document.getElementById("heatmapContainer");
 							    if(oDiv!=null){
 							    	oDiv.parentNode.removeChild(oDiv);
 							    }							    
 						}));
                		//传递TemArray						
 						topic.publish("tem",TemArray);
 						topic.publish("temName",TemName);
 						topic.publish("temMax",temMax);
 						topic.publish("temMin",temMin);
 						topic.publish("max_minName",TemMaxMinName);
                		self.map.addLayer(layer);                		
                		self.map.addLayer(self.heatmapContainer);
                		self.heatmapContainer.setData(layer.graphics);                		
                	}                
 			    //湿度函数
 				else if(index==4){ 
 					for(var i=0;i<list.length;i++)
        			{	 
        			  if(list[i].Humid!="缺测"||list[i].Humid!="无数据"){
        				 HumidArray.push(list[i].Humid);
        				 HummX.push(list[i].Lon);
        				 HummY.push(list[i].La);
        				 HumName.push(list[i].name);
        			  }
        			} 					
 					  if(HumidArray.length==0){
 						 layui.use(['layer', 'form'], function(){
		            		  var layer = layui.layer
		            		  ,form = layui.form();           		  
		            		  layer.msg('暂无数据！');
		            		});
 					  }
 					  else{
 						 self.heatmapContainer = new HeatMapLayer({               			
                 			config:{
                 				"useLocalMaximum":false,
                 				"radius":65, 
                 				"gradient":{
                 					 0.45: "rgb(240,248,255)",
                                     0.55: "rgb(173,216,230)",
                                     0.65: "rgb(135,206,235)",
                                     0.95: "rgb(0,191,255)",
                                     1.00: "rgb(0,0,255)"
                                 }
                 			},              			
                 			"map": self.map,
             		        "opacity": 0.5,
                 		},"heatmapContainer"); 						 
 						for(var i=0;i<HumidArray.length;i++){               			
                			var pt = new Point(HummX[i],HummY[i]); 
                			var ptNew = self.lonlatTomercator(pt);               			
                			var pt2 = new Point(ptNew.x,ptNew.y,self.map.spatialReference);               			
                			var PointSymbol = new SimpleMarkerSymbol();
		               	    PointSymbol.style = SimpleMarkerSymbol.STYLE_CIRCLE;
		               	    PointSymbol.setColor("0099FF"); 
		               	    var attr = {a:1};
	               	        var infoTemplate = new InfoTemplate();
                			var gra = new Graphic(pt2,PointSymbol,attr,infoTemplate);                			
                			layer.add(gra);               			
          		       }               		
                		layer.hide();    
                		//设置监听是否清除图层
// 						topic.subscribe('humidClear',lang.hitch(this,function(arg){
// 							if(arg=="humid")
// 								layer.clear();
// 						}));
                		//传递HumidArray						
 						topic.publish("hum",HumidArray);
 						topic.publish("humName",HumName);
                		self.map.addLayer(layer);                		
                		self.map.addLayer(self.heatmapContainer);
                		self.heatmapContainer.setData(layer.graphics); 
 					  } 						  
 				}
 			    //气压函数
 				else if(index==3){
 					for(var i=0;i<list.length;i++)
        			{           			  
        			  if(list[i].Pressure!="缺测"||list[i].Pressure!="无数据"){
        				  if(list[i].Pressure=="4637/11/25"){
        					  list[i].Pressure = 1024.0;
        				  }
        				  PressArray.push(list[i].Pressure);
        				  PreX.push(list[i].Lon);
        				  PreY.push(list[i].La);
        				  PreName.push(list[i].name);
        			  }
        			}
 					 if(PressArray.length==0){
 						 layui.use(['layer', 'form'], function(){
		            		  var layer = layui.layer
		            		  ,form = layui.form();           		  
		            		  layer.msg('暂无数据！');
		            		});
 					  }
 					 else{
 						self.heatmapContainer = new HeatMapLayer({               			
                 			config:{
                 				"useLocalMaximum":false,
                 				"radius":65, 
                 				"gradient":{
                 					 0.45: "rgb(255,255,244)",
                                     0.55: "rgb(255,255,0)",
                                     0.65: "rgb(255,215,0)",
                                     0.95: "rgb(255,165,0)",
                                     1.00: "rgb(255,0,0)"
                                 }
                 			},              			
                 			"map": self.map,
             		        "opacity": 0.8,
                 		},"heatmapContainer"); 	
 						
 						for(var i=0;i<PressArray.length;i++){               			
                			var pt = new Point(PreX[i],PreY[i]); 
                			var ptNew = self.lonlatTomercator(pt);               			
                			var pt2 = new Point(ptNew.x,ptNew.y,self.map.spatialReference);               			
                			var PointSymbol = new SimpleMarkerSymbol();
		               	    PointSymbol.style = SimpleMarkerSymbol.STYLE_CIRCLE;
		               	    PointSymbol.setColor("0099FF"); 
		               	    var attr = {a:1};
	               	        var infoTemplate = new InfoTemplate();
                			var gra = new Graphic(pt2,PointSymbol,attr,infoTemplate);                			
                			layer.add(gra);               			
          		       }               		
                		layer.hide();
                		//设置监听是否清除图层
 						topic.subscribe('preClear',lang.hitch(this,function(arg){
 							if(arg=="pressure")
 								layer.clear();
 						}));
                		//传递PressArray	
 						topic.publish("pre",PressArray);
 						topic.publish("preName",PreName);
                		self.map.addLayer(layer);                		
                		self.map.addLayer(self.heatmapContainer);
                		self.heatmapContainer.setData(layer.graphics); 
 					 }
 				}
 			    //风函数
 				else if(index==6){
 					for(var i=0;i<list.length-1;i++)
        			{           			  
        			  if(list[i].Wind!="缺测"&&list[i].WindDirection!="缺测"){
        				  windArray.push(list[i].Wind);
        				  TemArray.push(list[i].Tem);
        				  windName.push(list[i].name);
        				  if(list[i].WindDirection=="静风"){
        					  list[i].WindDirection = 90;       //静风定为90度方向
        				  }
        				  windDirect.push(parseInt(list[i].WindDirection));
        				  windX.push(list[i].Lon);
        				  windY.push(list[i].La);
        			  }
        			}
 					if(windArray.length==0){
						 layui.use(['layer', 'form'], function(){
		            		  var layer = layui.layer
		            		  ,form = layui.form();           		  
		            		  layer.msg('暂无数据！');
		            		});
					  }
 					else{												
 						for(var i=0;i<windArray.length-1;i++){               			
                			var pt = new Point(windX[i],windY[i]); 
                			var ptNew = self.lonlatTomercator(pt);               			
                			var pt2 = new Point(ptNew.x,ptNew.y,self.map.spatialReference);               			
                			var PointSymbol = new SimpleMarkerSymbol().setPath("M14.5,29 23.5,0 14.5,9 5.5,0z");		               	    		               	    
		               	    if(windArray[i]>=0&&windArray[i]<=0.2){
		               	    	PointSymbol.setSize(15);
		               	    }
		               	    if(windArray[i]>0.2&&windArray[i]<=0.5){
		               	    	PointSymbol.setSize(18);
		               	    }
		               	    if(windArray[i]>0.5&&windArray[i]<=1){
		               	    	PointSymbol.setSize(23);
		               	    }
		               	    if(TemArray[i]<5.0){
		               	    	PointSymbol.setColor("#FFFACD");
		               	    }
		               	    if(TemArray[i]>5.0&&TemArray[i]<10.0){
		               	    	PointSymbol.setColor("#FFDAB9");
		               	    }
		               	    else{
		               	    	PointSymbol.setSize(24);
		               	    	PointSymbol.setColor("#FF3030");
		               	    }
		               	    PointSymbol.setAngle(windDirect[i]);	               	    
 	               	        var infoTemplate = new InfoTemplate("风速风向信息：","风速:${Wind}<br>风向:${WindDirection}");
 	               	        var attr = windArray[i];
                			var gra = new Graphic(pt2,PointSymbol,attr,infoTemplate);                			
                			layer.add(gra);               			
          		       }  
 						//设置监听是否清除图层
 						topic.subscribe('windClear',lang.hitch(this,function(arg){
 							if(arg=="wind")
 								layer.clear();
 						}));						
 						
 						//传递windArray
 						var result = self.unique(windDirect);						
 						topic.publish("wind",windArray);
 						topic.publish("direction",result);
 						topic.publish("windName",windName);
                		self.map.addLayer(layer);               		
 					}
 				}        
		         	//显示降水
					else if(index==2){
					   for(var i=0;i<list.length-1;i++){
						   if(list[i].Rain!="缺测"||list[i].Rain!="无数据"){
							   rainArray.push(list[i].Rain);
							   rainX.push(list[i].Lon);
							   rainY.push(list[i].La);
							   rainName.push(list[i].name);
						   }
					   }
					   if(rainArray.length==0){
							 layui.use(['layer','form'], function(){
			            		  var layer = layui.layer
			            		  ,form = layui.form();           		  
			            		  layer.msg('暂无降雨！');
			            		});
						  }
					   else{
						  for(var i=0;i<rainArray.length-1;i++){
							    var pt = new Point(rainX[i],rainY[i]); 	               			
							    var PointSymbol = new SimpleMarkerSymbol();
			               	    PointSymbol.style = SimpleMarkerSymbol.STYLE_CIRCLE;
			               	    var render = new SimpleRenderer(PointSymbol);
			               	    var gra = new Graphic(pt,PointSymbol);
			               	    if(rainArray[i]==0){
			               	    	layui.use(['layer','form'], function(){
					            		  var layer = layui.layer
					            		  ,form = layui.form();           		  
					            		  layer.msg('暂无降雨！');
					            		});
			               	    }
			               	    if(rainArray[i]>0&&rainArray[i]<0.5){
			               	    	PointSymbol.setSize(18);
			               	    	PointSymbol.setColor("#E0FFFF");
			               	    	
			               	    }
			               	    if(rainArray[i]>0.5&&rainArray[i]<2.5){
			               	    	PointSymbol.setSize(28);
			               	    	PointSymbol.setColor("#00FFFF");
			               	    }
			               	    if(rainArray[i]>2.5){
			               	    	PointSymbol.setSize(38);
			               	    	PointSymbol.setColor("#00BFFF");
			               	    }
			               	    render.setVisualVariables([{
		               	    		"type":"opacityInfo",
		               	    		"field":rainArray[i],
		               	    		"stop":[{
		               	    			"value":0,
		               	    			"opacity":0
		               	    		},{
		               	    			"value":10,
		               	    			"opacity":100
		               	    		}]
		               	    	}])
			               	    layer.setRenderer(render);
						  }						    
					   }
					 //传递rainArray	
					 //设置监听是否清除图层
						topic.subscribe('rainClear',lang.hitch(this,function(arg){
							if(arg=="rain")
								layer.clear();
						}));
					   topic.publish("rainName",rainName);
					   topic.publish("rain",rainArray);
					   self.map.addLayer(layer); 
					}
		
				    //能见度函数
					else if(index==4){
						layui.use(['layer','form'], function(){
		            		  var layer = layui.layer
		            		  ,form = layui.form();           		  
		            		  layer.msg('暂无能见度数据！');
		            		});
					}
            }})                    	            	
           },       
            //检测输入时间是否为空
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
            //事件绑定
            bindEvent:function(){
            	on(this.guanbi,"click",lang.hitch(this,function(){
            		this.destroy();
            	}));
            	on(this.quxiao,"click",lang.hitch(this,function(){
            		this.destroy();
            	}));
            	//确定按钮
            	on(this.confirm,"click",lang.hitch(this,function(){ 
            		this.checkNull();        //检查日期是否为空
                    this.uploadDate();                           		            		            		
            	}));
            	//显示站点数据表
            	on(this.showTable,"click",lang.hitch(this,function(){           		           		
            		var table=new tableWidget({
                		map:this.map
                    });
            		this.destroy();
                	table.startup();               	                	
                	table.placeAt("wrapper");
            	}));
            	on(this.max_min,"click",lang.hitch(this,function(){   
            		this.uploadDate();
            	}));           	           	
            }, 		           
        })
    }
);