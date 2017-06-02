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
         "dojo/topic",
         "dojo/html",
         "dojo/dnd/Moveable",
         "dojo/text!./templates/Template.html"],
    function(declare,ArcGISDynamicMapServiceLayer,ArcGISTiledMapServiceLayer,BaseWidget,ajaxUtil,lang,on,query, 
    		domStyle,domClass,domAttr,domConstruct, dom,topic,html,Moveable,template) {
	
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
            
            bindEvent:function(){
            	on(this.clear,"click",lang.hitch(this,function(){           	          		
        			topic.publish("temClear","tempreture");  
        			topic.publish("windClear","wind");
        			topic.publish("rainClear","rain");
        			topic.publish("preClear","pressure");
        			topic.publish("humidClear","humid");
            	}));    	
            },        
        })
    }
);