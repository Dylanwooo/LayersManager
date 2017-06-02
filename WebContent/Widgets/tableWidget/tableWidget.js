
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
         "Widgets/staAnalysis/staAnalysis",
         "dojo/text!./templates/Template.html"],
    function(declare,ArcGISDynamicMapServiceLayer,ArcGISTiledMapServiceLayer,BaseWidget,ajaxUtil,lang,on,query, 
    		domStyle,domClass,domAttr,domConstruct, dom,html,Moveable,staAnalysis,template) {
	   
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
                setTimeout(lang.hitch(this,function(){
                	this.showTab();
                }),0);
                
            },
            
            destroy:function(){
                this.inherited(arguments);
            },
            
            showTab:function(){   
            	var self = this;
            	var $table = $('#myTable');
                $table.bootstrapTable({
                url: "/LayersManager/station", 
                dataType: "json",    
                method: 'get',                
                pagination: true, //分页·
                pageSize:10,
                striped:true,     //各行变色
                width:800,
                height:500,
                pageList: [10, 25, 50, 100],
                singleSelect: false,
                search: true, //显示搜索框
                clickToSelect: true,               
                      columns: [
                              {
                                  title: '测站编号',
                                  field: 'staNum',
                                  align: 'center',
                                  valign: 'middle'
                              }, 
                              {
                                  title: '测站名',
                                  field: 'staName',
                                  align: 'center',
                                  valign: 'middle',
                              }, 
                              {
                                  title: '测站编码',
                                  field: 'code',
                                  align: 'center'
                              },
                              {
                                  title: '地市',
                                  field: 'city',
                                  align: 'center'
                              },
                              {
                                  title: '县',
                                  field: 'county',
                                  align: 'center',
                              },
                              {
                                  title: '海拔',
                                  field: 'staEvl',
                                  align: 'center',
                              },
                              {
                                  title: '纬度',
                                  field: 'lat',
                                  align: 'center',
                              },
                              {
                                  title: '经度',
                                  field: 'longtitude',
                                  align: 'center',
                              },                             
                          ],
                 onLoadSuccess: function(){          	 
                	 console.log("成功");
                 },
                 onLoadError:function(){
                	 console.log("失败");
                 }
                 }); 
                //表格点击事件
                $("#myTable")
                .on('click-row.bs.table', function (row,ele,field) {
                    console.log(ele.staName);
                    var object=new staAnalysis({
                		map:this.map,
                		name:ele.staName
                    });
                    self.destroy();
            		object.startup();               	                	
            		object.placeAt("wrapper");
                })
            },
            
            tabEvent:function(){
            	
            },
            
            init:function(){  
            	
            }, 
            
            bindEvent:function(){
            	on(this.guanbi,"click",lang.hitch(this,function(){
            		this.destroy();
            	})); 
            	
            },        
    		           
        })
    }
);