<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>湖北省自动气象站综合显示分析系统</title>
<style type="text/css"> 	
	@import "http://js.arcgis.com/3.20/dijit/themes/tundra/tundra.css";
    @import "http://js.arcgis.com/3.20/esri/css/esri.css";    
</style> 
    <link rel="stylesheet" type="text/css" href="Styles/index.css" />   
    <link rel="stylesheet" type="text/css" href="Widgets/TitleWidget/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/LayersManager/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/LeftPaneWidget/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/TemPopup/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/tableWidget/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/LayerListWidget/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/ChartWidget/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/DataTabWidget/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/AnalysisWidget/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/staAnalysis/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/ClearLayerWidget/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/weatherWidget/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/weatherPop/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/weatherPredict/css/Style.css" />
    <link href=" //netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"  rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="Styles/jquery-ui-1.10.0.custom.css" />
    <link rel="stylesheet" type="text/css" href="Styles/layui.css" />  
    <link rel="stylesheet" type="text/css" href="Styles/polaris.css" /> 
    <link href="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.css" rel="stylesheet"/>
</head>

<body class="claro">
    <div id="wrapper"> 
	    <div id="map"></div> 
	    <div id="heatmapContainer"></div> 	            	   	     	   	  
    </div>     	   	      
</body>

<script>
    var path = window.document.location.href;
    dojoConfig = {
    	parseOnLoad: true,
        serverIP: location.host,
        gfxRenderer: "svg,canvas,vml",
        packages: [
            { name:"Widgets", location:path+"Widgets/" },
            { name:"Apps", location:path+"Apps/" }
        ]
    }; 
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/3.5.4/echarts.js"></script>
<script src="Libs/heatmap.js"></script>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script type="text/javascript" src="http://js.arcgis.com/3.20"> </script>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="Libs/icheck.min.js"></script>
<script src="Libs/bootstrap/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script>
<script src="Libs/jquery-ui-1.10.0.custom.min.js"></script>
<script src="Libs/layui.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="Scripts/init.js"></script>

<script>
    require(["dojo/domReady!","Apps/Main"], function(domReady,Main) {
        Main.initApp();
   });
</script>
</html>