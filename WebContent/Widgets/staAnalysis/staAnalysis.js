
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
	    var name;   
        return declare([BaseWidget], {
            templateString : template,           
            constructor : function(args) {
                this.inherited(arguments);
                this.map=args.map;
                name = args.name;
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
            	this.showMap();            	                
            },
            
            showMap:function(){
            	var width = 800;
                var height =800;
                var right = 300;
                var top = 300;
                var svg = d3.select("#myMap").append("svg")
                    .attr("width", width)
                    .attr("height", height)
                    .attr("right",right)
                    .attr("top",top)
                    .append("g")
                    .attr("transform", "translate(0,0)");
               
                var projection = d3.geo.mercator()
                    .center([114, 31])
                    .scale(1500)
                    .translate([width/2, height/2]);

                var path = d3.geo.path()
                    .projection(projection);

                var color = d3.scale.category20(); 
              
            	d3.json("hubei.json", function(error, root) {
                    if (error)
                        return console.error(error);
                    console.log(root.features);

                    svg.selectAll("path")
                        .data( root.features )
                        .enter()
                        .append("path")
                        .attr("stroke","#000")
                        .attr("stroke-width",1)
                        .attr("fill", function(d,i){
                            return color(i);
                        })
                        .attr("d", path )
                        .on("mouseover",function(d,i){
                            d3.select(this)
                                .attr("fill","yellow");
                        })
                        .on("mouseout",function(d,i){
                            d3.select(this)
                                .attr("fill",color(i));
                        });
                });	                           
            },
            
            bindEvent:function(){
            	on(this.clear,"click",lang.hitch(this,function(){            		
            		this.destroy();
            	}));  	
            },
         
        })
    }
);