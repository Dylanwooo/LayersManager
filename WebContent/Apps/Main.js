define(
		    [
		        "dojo/dom",
		        "dojo/on",
		        "dojo/_base/array",
		        "dojo/_base/lang",
		        "dojo/topic",
		        "dojo/window",
		        "dojo/dom-style",
		        "esri/config",
		        "esri/nls/jsapi",
		        "esri/layers/FeatureLayer",
		        "esri/map",
		        "esri/geometry/Point",
		        "esri/layers/ArcGISTiledMapServiceLayer",
		        "../Widgets/LayersManager/LayersManager",     
		        "esri/dijit/OverviewMap",
		        "esri/InfoTemplate",
		        "esri/symbols/SimpleMarkerSymbol", 
		        "esri/SpatialReference",
		        "esri/layers/GraphicsLayer",
		        "esri/geometry/Point",
		        "esri/graphic",
		        "esri/symbols/PictureMarkerSymbol",
		        "esri/layers/WebTiledLayer",
		        "../Widgets/LayersManager/LayersManager",
		        "../Widgets/LeftPaneWidget/LeftPaneWidget",
		        "../Widgets/TitleWidget/TitleWidget", 
		        "../Widgets/LayerListWidget/LayerListWidget",
		        "../Widgets/ChartWidget/ChartWidget",
		        "../Widgets/AnalysisWidget/AnalysisWidget",
		        "../Widgets/ClearLayerWidget/ClearLayerWidget",
		        "../Widgets/weatherWidget/weatherWidget",
		        "../Widgets/weatherPredict/weatherPredict",
		        "dojo/domReady!"
		    ],
		    function (dom, on, array, lang, topic,win,domStyle,esriConfig, esriBundle,
		    		FeatureLayer,Map,Point,ArcGISTiledMapServiceLayer,LayersManager,
		    		OverviewMap,InfoTemplate,SimpleMarkerSymbol,SpatialReference,GraphicsLayer,
		    		Point,Graphic,PictureMarkerSymbol,WebTiledLayer,LayersManager,LeftPaneWidget,
		    		TitleWidget,LayerListWidget,ChartWidget,AnalysisWidget,ClearLayerWidget,weatherWidget,weatherPredict) {   
		    	
		        var m = {   		
		            initApp:function () 
		            {
		            	esriConfig.defaults.io.proxyUrl = "proxy.jsp";
		                esriConfig.defaults.io.corsDetection = false;
		                esriBundle.root.toolbars.draw.addPoint = "单击以添加点，按ESC键退出";
		                esriBundle.root.toolbars.draw.start = "单击以开始绘制，按ESC键退出";
		                esriBundle.root.toolbars.draw.freehand = "按下后开始并直到完成，按ESC键退出";
		                esriBundle.root.toolbars.draw.addShape = "单击以添加形状，按ESC键退出";       
		                window.Global={};               
		                this.loadMap();   
		                this.addEvent();	                
		            },
		            addEvent:function(){
		            	//topic.subscribe("mapLoaded", lang.hitch(this, this.handleMapLoaded));
		            	on(this.map,"load",lang.hitch(this, this.handleMapLoaded));
		            },
		            
		            loadMap:function(){		            			            	
		            	this.map = new Map("map",{
		            			logo:false,
		            			slider:true,
		            			sliderPosition:"top-right",
		            			//basemap:"streets-night-vector"
		            			basemap:"dark-gray"
		            		});
		            	var center=new Point(112.1,30.9);
		            	this.map.centerAndZoom(center,8);		            			            	
		            },
		            
		            handleMapLoaded:function(){
		            	this.loadWidgets();
		            	this.showStation();
		            	this.Welcome();
		            },
		            
		            loadWidgets:function(){
//		            	var layerManager = new LayersManager({
//		            		map:this.map
//		            	});
//		            	layerManager.startup();
//		            	layerManager.placeAt(dojo.body());
		            	//加入Title控件
		            	var weather=new weatherWidget({
		            		map:this.map
		                });
		            	weather.startup();
		            	weather.placeAt(dojo.body());
		            	
		            	var Title=new TitleWidget({
		            		map:this.map
		                });
		            	Title.startup();
		            	Title.placeAt(dojo.body());
		            	
		            	//加入leftpane控件
		            	var left=new LeftPaneWidget({
		            		map:this.map		            		
		                });
		            	left.startup();
		            	left.placeAt(dojo.body());
		          	
		            	//加入鹰眼控件
		            	var overviewMapDijit = new OverviewMap({
		                    map: this.map,
		                    visible: true,
		                    attachTo:'bottom-right'
		                  });            	  
		                  overviewMapDijit.startup(); 
		                  
		                var clearWidget = new ClearLayerWidget({
		                	map: this.map	                
		                });
		                clearWidget.startup();
		                clearWidget.placeAt(dojo.body());
		            },
		            
		            Welcome:function(){
		            	layui.use(['layer', 'form'], function(){
		            		  var layer = layui.layer
		            		  ,form = layui.form();           		  
		            		  layer.msg('欢迎进入系统');
		            		});		            	
		            },
		         		              		                                        
		            //站点显示
		            showStation:function(){	
		            	//ajax请求     
		            	var layer = new GraphicsLayer();		            	 
		            	$.ajax({type:"post",url:"/LayersManager/station",success:function(mList){  
		              	   //解析JSON文本            	   
		              	   var list = JSON.parse(mList);              	   
		              	   for(var i=0;i<list.length;i++)
		                     {              		   
		               	       var pt = new Point(list[i].longtitude,list[i].lat);            	  
		               	       var attr = list[i];
		               	       var infoTemplate = new InfoTemplate("测站基本信息：","区站号:${staNum}<br>站名:${staName}<br>行政区代码:${code}<br>省份:${province}<br>地市:${city}<br>区县:${county}<br>测站级别:${staEvl}级<br>测站类型:${staType}<br>纬度:${lat}°<br>经度:${longtitude}°<br>测站高度:${alt}米");		               	       
		               	       var PointSymbol = new PictureMarkerSymbol("station.png",25,25);
		               	       
		               	       //根据测站级别设置symbol大小
		               	       if(list[i].staEvl==11)
		               	         {
		               	    	   PointSymbol.setWidth(30);	
		               	    	   PointSymbol.setHeight(30);
		               	         }
		               	       else if(list[i].staEvl==12)
		               	    	 {
		               	    	   PointSymbol.setWidth(25);
		               	    	   PointSymbol.setHeight(25);
		               	    	 }
		               	       else if(list[i].staEvl==13)
		               	    	 {
		               	    	   PointSymbol.setWidth(20);
		               	    	   PointSymbol.setHeight(20);
		               	    	 }
		               	       else if(list[i].staEvl==13)
		          	    	     {
		          	    	       PointSymbol.setWidth(17);
		          	    	       PointSymbol.setHeight(17);
		          	    	     }
		               	       else
		               	    	 {
		               	    	   PointSymbol.setWidth(15);
		               	    	   PointSymbol.setHeight(15);
		 	                       
		               	    	 }
		               	       var gra = new Graphic(pt, PointSymbol, attr,infoTemplate);			               	   
		               	       layer.add(gra);               	                                     	       
		                     } 
		              	}})	
		              	//layer.hide();
		              	this.map.addLayer(layer);
		              	
		            },           
		        };
		        return m;
});

