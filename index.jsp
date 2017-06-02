<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图层管理</title>
<style type="text/css"> 
	@import "http://js.arcgis.com/3.11/dijit/themes/tundra/tundra.css";
	@import "http://js.arcgis.com/3.11/esri/css/esri.css";	
	
</style> 
    <link rel="stylesheet" type="text/css" href="Libs/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="Widgets/LayersManager/css/Style.css" />
    <link rel="stylesheet" type="text/css" href="Styles/index.css" />

</head>

<body class="claro">
    <div id="wrapper">
    	<div id="map"></div>   	
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
<script type="text/javascript" src="http://js.arcgis.com/3.11"> </script>
<script src="Libs/jquery-1.9.1.min.js"></script>
<script src="Libs/bootstrap/js/bootstrap.min.js"></script>
<script src="Scripts/init.js"></script>
<script>
    require(["dojo/domReady!", "Apps/Main"], function(domReady,Main) {
        Main.initApp();
   });
</script>
</html>