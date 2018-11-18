<!DOCTYPE HTML>
<head>
<#assign basePath=request.contextPath>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=0.9,user-scalable=no"/>
<meta name="MobileOptimized" content="320">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="${basePath}/css/logistics.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/top.css">
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>
<div class="expressBox">
<#include "../publicHeader2_ftl.ftl" />
	<div class="expressLogo">
		<img src="#">
		<ul>
			<li><h3>${orderExpress.expressName!}</h3></li>
			<li><label>订单号：</label><span>2015985302155</span></li>
			<li><label>状态：</label><span>派件中</span></li>
		</ul>
	</div>
	<div class="expressMes">
		<h3>物流跟踪</h3>
		<ul>
			<li><i></i><em>2015-09-20 14:30:20</em><span>北京市邮政速递大柳树区域分公司三间房营投部，安排投递，操作员辣辣人儿。</span></li>
			<li><i></i><em>2015-09-20 14:30:20</em><span>北京市邮政速递大柳树区域分公司三间房营投部，安排投递，操作员辣辣人儿。</span></li>
			<li><i></i><em>2015-09-20 14:30:20</em><span>北京市邮政速递大柳树区域分公司三间房营投部，安排投递，操作员辣辣人儿。</span></li>
		</ul>
	</div>
</div>
</body>