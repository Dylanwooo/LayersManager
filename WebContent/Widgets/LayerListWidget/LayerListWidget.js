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
         "dojo/text!./templates/Template.html"],
    function(declare,ArcGISDynamicMapServiceLayer,ArcGISTiledMapServiceLayer,BaseWidget,ajaxUtil,lang,on,topic,query, 
    		domStyle,domClass,domAttr,domConstruct,dom,html,Moveable,TemPopWidget,template) {
	
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
            	on(this.close,"click",lang.hitch(this,function(){ 
            		   this.destroy();       		           		
            	}));
            	
            },                                     		           
        })
    }
);