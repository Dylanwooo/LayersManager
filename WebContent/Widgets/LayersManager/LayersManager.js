
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
                this.LayersID=[];
                this.type=0;
                this.layerscount=0;
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
            	on(this.urlInput,"focus",lang.hitch(this,function(){
            		domClass.remove(this.domNode,"turnShort");
            		domClass.add(this.domNode,"turnLong");
            	}));
            	on(this.addBtn, "click", lang.hitch(this, this.AddLayerClick));            	
            },
            
            AddLayerClick:function(){
            	
            	var serverurl=this.urlInput.value;
            	var reg=/http:\/\/[a-zA-Z0-9:.]+\/arcgis\/rest\/services\/[a-zA-Z0-9_/]+\/MapServer/gi;
            	var islegal=reg.test(serverurl);
            	if(!islegal){
            		alert("你输入的URL不符合要求，请重新输入")
            		return 
            	}
            	ajaxUtil.request(serverurl+"?f=json&pretty=true","",lang.hitch(this,function(result){
            		var layer=null;
            		if(result.tileInfo){
            			this.type=1;
            			layer= new ArcGISTiledMapServiceLayer(serverurl);
            		}else{
            			this.type=0;
            			layer= new ArcGISDynamicMapServiceLayer(serverurl);
            		}
            		on(layer,"error",function(){
            			alert("你输入的服务存在问题，请检查后重新输入")
            		})
            		on(layer,"load",lang.hitch(this,function(){
            			domClass.remove(this.domNode,"turnLong"); 
                		domClass.add(this.domNode,"turnShort");  
                    	if(serverurl!=""){
                    		if(this.layerscount==0){
                        		domConstruct.destroy(query(".server-list ul li")[0]);  
                        	}
                    		this.layerscount++;
                    		var layerID="mylayer"+this.LayersID.length;
                    		var html='<li class="list-group-item" layerid="'+layerID+'" index="'+this.layerscount+'">'
                		    			+'<span class="list-content">'
                		    			    +'<input type="text" title="'+serverurl+'" disabled="disabled" value="Layer'+this.layerscount+'">'
                		    			+'</span>'
                		    			+'<span class="glyphicon glyphicon-remove url-remove" title="Delete"></span>'
                		    			+'<span class="glyphicon glyphicon-pencil url-rename" title="Rename"></span>'
                		    			+'<input type="checkbox" dojoType="dijit.form.CheckBox" class="url-checkbox" checked="true">'		    			
                		    		+'</li>';
                    		var node = domConstruct.toDom(html);
                    		domConstruct.place(node, this.urlList,"first");
                    		//this.moveHandler = new Moveable(node, {});
                    		var listnum=query(".server-list ul li").length;
                    		
                    		var checknode=query("input",node)[1];
                    		var closenode=query("span",node)[1];
                    		var renamenode=query("span",node)[2];
                    		this.bindListEvent(checknode,closenode,renamenode,listnum);      
                    		this.urlInput.value="";
                    		this.AddLayer(serverurl,this.type,layerID,this.layerscount);   	
                    		
                    	}
            		}))           			
            	}));           	           	
            },
            
            bindListEvent:function(checknode,closenode,renamenode,listnum){
    			console.log(checknode)
    			on(checknode,"click",lang.hitch(this,function(){
    				console.log(this.LayersID[listnum-1])
        			var layerid=this.LayersID[listnum-1];
        			var checked=domAttr.get(checknode, "checked");
        			if(!checked){           				
        				this.map.getLayer(layerid).hide();
        			}else{
        				this.map.getLayer(layerid).show();
        			}
        		}));
        		
        		on(closenode,"click",lang.hitch(this,function(e){
        			var layerid=this.LayersID[listnum-1];          				
        			this.map.removeLayer(this.map.getLayer(layerid));
        			var parentNode=e.target.parentNode;
        			domConstruct.destroy(parentNode);
        			if(query(".server-list ul li").length==0){
        				var html='<li class="list-group-item"><p class="text-muted">暂无附加图层</p></li>';
        				var node = domConstruct.toDom(html);
                		domConstruct.place(node, this.urlList,"first");
                		this.layerscount=0;
                		this.LayersID.length=0;
        			}
        		}));
        		
        		on(renamenode,"click",lang.hitch(this,function(e){
        			var inputnode=e.target.parentNode.firstChild.firstChild; 
        			if(domClass.contains(renamenode, "glyphicon glyphicon-pencil")){//开始编辑           				        
        				domClass.remove(renamenode,"glyphicon glyphicon-pencil");
                		domClass.add(renamenode,"glyphicon glyphicon-floppy-disk");  
                		domAttr.set(renamenode, "title","Save");
                		domAttr.remove(inputnode, "disabled");

        			}else{//保存编辑
        				domClass.remove(renamenode,"glyphicon glyphicon-floppy-disk");  
        				domClass.add(renamenode,"glyphicon glyphicon-pencil");  
        				domAttr.set(renamenode, "title","Rename");
        				domAttr.set(inputnode, "disabled","true");
        			}		        			 			
        		}));
        		on(this.clear,"click",lang.hitch(this,function(){            		            		            			         			           		               	
            		this.destroy();
        	}))
        		
    		},
    		
            //添加图层函数 type=0 动态图层 type=1 瓦片图层
            AddLayer:function(url,type,layerID,index){ 
        		
            	var layer=null;
            	switch(type){
            	case 0:
            		layer= new ArcGISDynamicMapServiceLayer(url, {id:layerID});           		
            		break;
            	case 1:
            		layer= new ArcGISTiledMapServiceLayer(url, {id:layerID});           		
            		break;
            	default:
        			break;	
            	}
            	this.LayersID.push(layerID);
            	this.map.addLayer(layer,index);
            }
        })
    }
);