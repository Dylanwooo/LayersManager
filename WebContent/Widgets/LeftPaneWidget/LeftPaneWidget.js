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
         "Widgets/TemPopup/TemPopWidget",
         "Widgets/LayerListWidget/LayerListWidget",
         "Widgets/ChartWidget/ChartWidget",
         "Widgets/AnalysisWidget/AnalysisWidget",
         "dojo/text!./templates/Template.html"],
    function(declare,ArcGISDynamicMapServiceLayer,ArcGISTiledMapServiceLayer,BaseWidget,ajaxUtil,lang,on,topic,query, 
    		domStyle,domClass,domAttr,domConstruct,dom,html,Moveable,TemPopWidget,LayerListWidget,ChartWidget,AnalysisWidget,template) {
	
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
            	
            },  
       
            //绑定事件
            bindEvent:function(){       	             	            	
            	on(this.showPane,"click",lang.hitch(this,function(){           		
            		var tem=new TemPopWidget({
                		map:this.map
                    });
                	tem.startup();
                	tem.placeAt(dojo.body());
            	}));
            	on(this.showList,"click",lang.hitch(this,function(){               		
	            		var list=new LayerListWidget({
	                		map:this.map
	                    });            		
	            			list.startup();
	            			list.placeAt("wrapper");  	            			 
            	})); 
            	on(this.show3D,"click",lang.hitch(this,function(){               		          	
            		layui.use(['layer', 'form'], function(){
	            		  var layer = layui.layer
	            		  ,form = layui.form();           		  
	            		  layer.msg('功能开发中，敬请期待!');
	            		});			
        	    }));
            	
            	on(this.tableAnalysis,"click",lang.hitch(this,function(){               		          	
            		var chart = new ChartWidget({
                		map:this.map
                    });
            		chart.startup();
            		chart.placeAt(dojo.body());	          		
        	    }));
            	
            	on(this.analysis,"click",lang.hitch(this,function(){               		          	
            		var analysisPanel = new AnalysisWidget({
                		map:this.map
                    });
            		analysisPanel.startup();
            		analysisPanel.placeAt(dojo.body());	          		
        	    }));
            	
            	on(this.Dynamic,"click",lang.hitch(this,function(){               		          	
            		layui.use(['layer', 'form'], function(){
	            		  var layer = layui.layer
	            		  ,form = layui.form();           		  
	            		  layer.msg('功能开发中，敬请期待!');
	            		});          		
        	    }));
        	   
            	
            	//以下两个为测试图层的隐藏和显示
//            	on(this.tableAnalysis,"click",lang.hitch(this,function(){           		           		
//            		var a = 0;
//                	//传递参数到风函数
//        			topic.publish("windGraphicLayer0",a);       			
//            	}));
//            	on(this.show3D,"click",lang.hitch(this,function(){           		           		
//            		var a = 1;
//                	//传递参数到风函数
//        			topic.publish("windGraphicLayer1",a);	
//            	}));
            },                                     		           
        })
    }
);