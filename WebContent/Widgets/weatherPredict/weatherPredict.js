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
         "dojo/text!./templates/Template.html"],
    function(declare,ArcGISDynamicMapServiceLayer,ArcGISTiledMapServiceLayer,BaseWidget,ajaxUtil,lang,on,query, 
    		domStyle,domClass,domAttr,domConstruct, dom,html,Moveable,template) {
	
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
                this.predictWeather();
            },
            predictWeather:function(){            	  
              	$.ajax({type:"post",url:"GetWeather",
              		success:function(msg){ 
              			var list = JSON.parse(msg);              			
              			document.getElementById("date1").innerHTML = list.results[0].weather_data[1].date;  
              			document.getElementById("city1").innerHTML = list.results[0].currentCity;
              			document.getElementById("tem1").innerHTML = list.results[0].weather_data[1].temperature;
              			document.getElementById('dayPic1').setAttribute("src",list.results[0].weather_data[1].dayPictureUrl); 
            			document.getElementById('night1').setAttribute("src",list.results[0].weather_data[1].nightPictureUrl); 
            			document.getElementById('wind1').innerHTML = list.results[0].weather_data[1].wind;
            			document.getElementById('myweather1').innerHTML = list.results[0].weather_data[1].weather; 
            			
            			document.getElementById("date_2").innerHTML = list.results[0].weather_data[2].date;  
              			document.getElementById("city2").innerHTML = list.results[0].currentCity;
              			document.getElementById("tem2").innerHTML = list.results[0].weather_data[2].temperature;
              			document.getElementById('dayPic2').setAttribute("src",list.results[0].weather_data[2].dayPictureUrl); 
            			document.getElementById('night2').setAttribute("src",list.results[0].weather_data[2].nightPictureUrl); 
            			document.getElementById('wind2').innerHTML = list.results[0].weather_data[2].wind;
            			document.getElementById('myweather2').innerHTML = list.results[0].weather_data[2].weather;
            			
            			document.getElementById("date3").innerHTML = list.results[0].weather_data[3].date;  
              			document.getElementById("city3").innerHTML = list.results[0].currentCity;
              			document.getElementById("tem3").innerHTML = list.results[0].weather_data[3].temperature;
              			document.getElementById('dayPic3').setAttribute("src",list.results[0].weather_data[3].dayPictureUrl); 
            			document.getElementById('night3').setAttribute("src",list.results[0].weather_data[3].nightPictureUrl); 
            			document.getElementById('wind3').innerHTML = list.results[0].weather_data[3].wind;
            			document.getElementById('myweather3').innerHTML = list.results[0].weather_data[3].weather;
              		}})
            },
            bindEvent:function(){
            	on(this.close,"click",lang.hitch(this,function(){            		            		            			         			           		               	
           		 this.destroy();
       	        }))     	
            },
        
        })
    }
);