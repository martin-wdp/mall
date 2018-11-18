<!DOCTYPE html>
<html lang="zh-cn">
  <head>
  	<#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
    <#if (sys.bsetName)??>
    	<title>${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>
    <style type="text/css">
	    html,body{margin:0;padding:0;}
	    .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
	    .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
	</style>
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
 <script type="text/javascript" src="${basePath}/js/shoppingcart/jsOperation.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=6zNboXV3yyGsxE5j2EO8bQxe"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
</head>
<body style="background:#CBE1FF">
      <input id="text_" type="hidden" value="${ad!""}" style="margin-right:100px;"/>
         <#--百度地图容器-->
  	<div style="width:100%;height:1200px;border:#ccc solid 1px;" id="container"></div>
</body>
<script type="text/javascript">
    var map = new BMap.Map("container");
    map.centerAndZoom("南京", 12);
    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
    map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开

    var localSearch = new BMap.LocalSearch(map);
    localSearch.enableAutoViewport(); //允许自动调节窗体大小
	map.clearOverlays();//清空原来的标注
	var keyword = document.getElementById("text_").value;
	localSearch.setSearchCompleteCallback(function (searchResult) {
	        var poi = searchResult.getPoi(0);
	        map.centerAndZoom(poi.point, 13);
	      	var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
	        map.addOverlay(marker);
	        var content = keyword + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
	        var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
	        marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
    });
    localSearch.search(keyword);
</script>
</html>