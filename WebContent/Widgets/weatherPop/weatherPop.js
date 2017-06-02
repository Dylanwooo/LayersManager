
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
         "dojo/dnd/Moveable",
         "Widgets/weatherPredict/weatherPredict",
         "dojo/text!./templates/Template.html"],
    function(declare,ArcGISDynamicMapServiceLayer,ArcGISTiledMapServiceLayer,BaseWidget,ajaxUtil,lang,on,query, 
    		domStyle,domClass,domAttr,domConstruct, dom,html,Moveable,weatherPredict,template) {
	
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
            	 this.getWeatherInfo();
            },
            
            getWeatherInfo:function(){
            	$.ajax({type:"post",url:"GetWeather",
            		success:function(msg){ 
            			var list = JSON.parse(msg);            			            			
            			console.log(list);           			
            			console.log(list.results[0].currentCity);
            			document.getElementById("date").innerHTML = list.date;  
            			document.getElementById("city").innerHTML = list.results[0].currentCity;
            			document.getElementById("date2").innerHTML = list.results[0].weather_data[0].date;
            			document.getElementById("tem").innerHTML = list.results[0].weather_data[0].temperature;
            			document.getElementById('dayPic').setAttribute("src",list.results[0].weather_data[0].dayPictureUrl); 
            			document.getElementById('night').setAttribute("src",list.results[0].weather_data[0].nightPictureUrl); 
            			document.getElementById('wind').innerHTML = list.results[0].weather_data[0].wind;
            			document.getElementById('myweather').innerHTML = list.results[0].weather_data[0].weather;
            			document.getElementById('pm25').innerHTML = list.results[0].pm25+"μg/m3";
            			document.getElementById('wearTitle').innerHTML = list.results[0].index[0].tipt;
            			document.getElementById('wearTip').innerHTML = list.results[0].index[0].des;
            			document.getElementById('zs').innerHTML = list.results[0].index[0].zs;
            			document.getElementById('healthTitle').innerHTML = list.results[0].index[2].tipt;
            			document.getElementById('healthTip').innerHTML = list.results[0].index[2].des;
            			document.getElementById('zs2').innerHTML = list.results[0].index[2].zs;
            			document.getElementById('sportTitle').innerHTML = list.results[0].index[3].tipt;
            			document.getElementById('sportTip').innerHTML = list.results[0].index[3].des;
            			document.getElementById('zs3').innerHTML = list.results[0].index[3].zs;
            			document.getElementById('sunTitle').innerHTML = list.results[0].index[4].tipt;
            			document.getElementById('sunTip').innerHTML = list.results[0].index[4].des;
            			document.getElementById('zs4').innerHTML = list.results[0].index[4].zs;
            		}})
            },
            
            bindEvent:function(){
            	on(this.close,"click",lang.hitch(this,function(){            		            		            			         			           		               	
            		 this.destroy();
        	    }))   
        	    on(this.predict,"click",lang.hitch(this,function(){         	    
        	    	var predict = new weatherPredict({
	                    map: this.map,	                  
	                  });            	  
        	    	predict.startup(); 
        	    	predict.placeAt(dojo.body());
       	        }))
            },
                
        })
    }
);