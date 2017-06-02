/**
 * User: liuzj
 * Date: 15-11-12
 * Time: 下午4:40
 * Geometry辅助类
 */

define(
    [
        "require",
        "dojo/_base/declare",
        "dojo/_base/lang",
        "dojo/_base/array",
        "esri/geometry/Point",
        "esri/geometry/Polyline",
        "esri/SpatialReference",
        "esri/geometry/webMercatorUtils"
    ],
    function (require, declare, lang, array,Point,Polyline,SpatialReference,WebMercatorUtils) {
    	return{
    		getPoint:function (x,y,spatia,isTrans) {
    			if (isTrans){
    				var nums=WebMercatorUtils.lngLatToXY(x, y);
    				return new Point(nums[0], nums[1], spatia);
    			}else{
    				return new Point(x, y, spatia);
    			}
            },
            
            getPolyline:function(pints,spatia){
            	var polyline=new Polyline(spatia);
            	polyline.addPath(pints);
            	return polyline;
            },
            
            getOffsetPoint:function(x,y,spatia){
            	var ret=this.offset(y,x);
            	return this.getPoint(ret[1], ret[0],spatia,true);
            },
            
            /**
             * 坐标偏移
             */
            offset: function(lat, lon) {
		        var a = 6378245.0;	// WGS长轴半径
		        var ee = 0.00669342162296594323;	// WGS偏心率的平方
		        var dLat = this._lat(lon - 105.0, lat - 35.0);
		        var dLon = this._lon(lon - 105.0, lat - 35.0);
		        var radLat = lat / 180.0 * Math.PI;
		        var magic = Math.sin(radLat);
		        magic = 1 - ee * magic * magic;

		        var sqrtMagic = Math.sqrt(magic);
		        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * Math.PI);
		        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * Math.PI);
		        return [lat + dLat, lon + dLon];
		    },

	        _lon: function(x, y) {
		        var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
		        ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
		        ret += (20.0 * Math.sin(x * Math.PI) + 40.0 * Math.sin(x / 3.0 * Math.PI)) * 2.0 / 3.0;
		        ret += (150.0 * Math.sin(x / 12.0 * Math.PI) + 300.0 * Math.sin(x / 30.0 * Math.PI)) * 2.0 / 3.0;
		        return ret;
	        },
	
	        _lat: function(x, y) {
		        var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
		        ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
		        ret += (20.0 * Math.sin(y * Math.PI) + 40.0 * Math.sin(y / 3.0 * Math.PI)) * 2.0 / 3.0;
		        ret += (160.0 * Math.sin(y / 12.0 * Math.PI) + 320 * Math.sin(y * Math.PI / 30.0)) * 2.0 / 3.0;
		        return ret;
	        },
	        
	        baiduToGaode:function(bd_lon, bd_lat){
	        	var x_pi = 3.14159265358979324 * 3000.0 / 180.0;
	            var x = bd_lon - 0.0065;
	            var y = bd_lat - 0.006;
	            var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
	            var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
	            var gg_lng = z * Math.cos(theta);
	            var gg_lat = z * Math.sin(theta);
	            return [gg_lng, gg_lat]
	        },
	        
	        getBaiduToGaodePoint:function(x,y,spatia){
	        	var nums=WebMercatorUtils.xyToLngLat(x, y);
	        	nums=this.baiduToGaode(nums[0],nums[1]);
	        	
	        	return this.getPoint(nums[0],nums[1],spatia,true);
	        },
    	};
    })
