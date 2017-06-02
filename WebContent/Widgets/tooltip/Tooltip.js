define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "dojo/text!./templates/Template.html",
    "dojo/_base/lang",
    "dojo/dom-style",
    "dojo/dom-construct",
    "dojo/on",
    "dojo/_base/connect"],
    function(declare,_WidgetBase,_TemplatedMixin,template,lang,style,domConstruct,on,eventconnect){
        return declare([_WidgetBase,_TemplatedMixin],{
        	
            templateString:template,

            resetAttr:function(){
                this.inherited(arguments);
            },

            postCreate:function(){
                this.inherited(arguments);
            },

            startup:function(){
                this.inherited(arguments);
            },
          //内容
            contentStr:"",

            //消失的时间
            time:1500,
            
            hasConfig:false,

            //相对于谁进行定位，默认相对于body
            relativeParent:document.body,

            //表示该提示是显示错误信息还是正确信息,默认表示错误信息
            bError:true,

            constructor:function (args) {
                if (args) {
                    if (args.contentStr) {
                        this.contentStr = args.contentStr;
                    }
                    if (args.time) {
                        this.time = args.time;
                    }
                    if(args.relativeParent){
                        this.relativeParent = args.relativeParent;
                    }
                    if(args.bError!=undefined){
                        this.bError = args.bError;
                    }
                }
            },

            postCreate:function () {
                this.setContent();
                if(this.bError == false){
                    var mainContainer = dojo.query(".mainContainer",this.domNode)[0]
//                    var greenUrl = dojo.moduleUrl(this._module,"images/green.png").path;
                    var greenUrl = "widgets/tooltip/images/green.png";
                    dojo.style(mainContainer,"backgroundImage","url("+greenUrl+")");
                }
                dojo.place(this.domNode, dojo.body());
                window.setTimeout(dojo.hitch(this, function () {
                    var animation = dojo.fadeOut({
                        duration:2000,
                        node:this.domNode
                    });
                    dojo.connect(animation, "onEnd", this, this.onClose);
                    animation.play();
                }), this.time);
                dojo.connect(window, "onresize", this, this.onWidowResize);

                this._position();
            },

            //设置提示内容
            setContent:function () {
                this.content.innerHTML = this.contentStr;
            },

            //关闭的listener
            onCloseClick:function () {
                this.onClose();
            },

            //关闭事件
            onClose:function () {
                this.destroy();
            },

            //window尺寸改变事件
            onWidowResize:function (evt) {
                this._position();
            },


            _position:function () {
                if(this.domNode){
                    var thisWidth = this.domNode.clientWidth;
                    var thisHeight = this.domNode.clientHeight;
                    if(this.relativeParent == document.body){
                        var box = dojo.window.getBox();
                        var left = (box.w - thisWidth) / 2;
                        var top = (box.h - thisHeight) / 2;
                    }
                    else{
                        var clientRect = this.relativeParent.getBoundingClientRect();//left、right、top、bottom、width、height
                        var relativcOffsetX = (clientRect.width - thisWidth)/2;
                        var relativeOffsetY = (clientRect.height - thisHeight)/2;
                        var left = clientRect.left + relativcOffsetX;
                        var top = clientRect.top + relativeOffsetY;
                    }
                    dojo.style(this.domNode, "left", left + "px");
                    dojo.style(this.domNode, "top", top + "px");
                }
            }
    });
});

