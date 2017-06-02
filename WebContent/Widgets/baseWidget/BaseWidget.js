define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "Widgets/tooltip/Tooltip"],
    function(declare,_WidgetBase,_TemplatedMixin,Tooltip){
	    return declare([_WidgetBase,_TemplatedMixin],{
	    	/**
	         * 显示自定义的提示消息
	         * @param msg 提示消息
	         * @param time 持续时间
	         * @param dom 参照的节点
	         * @param bError 是否为错误提示
	         */
	        showMessage:function (msg, time, dom, bError) {
	            //time = time ? time : 2500;
	            var speed = 8;//8字符/秒
	            time = Math.ceil(msg.length / speed) * 1000;
	            dom = dom ? dom : this.domNode;
	            bError = (bError != undefined) ? bError : true;
	            new Tooltip({"contentStr":msg, time:time, relativeParent:dom, bError:bError});
	        }
	});
});