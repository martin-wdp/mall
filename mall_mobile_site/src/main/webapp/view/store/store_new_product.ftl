<!DOCTYPE html>
<html lang="zh-cn">
  <head>
  	<#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
  </head>
  <body>
  <#--新加头部－加搜索-->
    <div class="goods_list container-fluid">
        <#if map.pb.list??>
      <#list map.pb.list as product>
      	  <div class="col-xs-6">
	        <a href="${basePath}/item/${product.goodsInfoId}.html">
	          <img class="lazy" alt="" data-original="<#if  product.goodsInfoImgId??>${product.goodsInfoImgId}</#if>">
	          <noscript><img alt="" src="<#if  product.goodsInfoImgId?? >${product.goodsInfoImgId}</#if>"></noscript>
	          <span>${product.goodsInfoName!''}</span>
	          <span>￥<#if product.goodsInfoMarketPrice??>${product.goodsInfoMarketPrice?string('0.00')}</#if></span>
	        </a>
	      </div>
      </#list>
        </#if>
    </div>
    
    <div class="pages container-fluid">
      <#if (map.pb.pageNo==1)>
           <div class="col-xs-6">
      		 <a class="disabled"  href="javascript:;">&lt;&nbsp;上一页</a>
      	   </div>
      <#else>
      		<div class="col-xs-6">
      			<a class="changePages" pages="${map.pb.prePageNo}" href="javascript:;">&lt;&nbsp;上一页</a>
      		</div>
      </#if>
      <#if (map.pb.lastPageNo > map.pb.pageNo)>
           
           <div class="col-xs-6">
      			<a class="changePages" pages="${map.pb.nextPageNo}" href="javascript:;">下一页&nbsp;&gt;</a>
     	   </div>
      <#else>
			<div class="col-xs-6">
      			<a class="disabled" href="javascript:;">下一页&nbsp;&gt;</a>
      		</div>
	  </#if>
    </div>
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div>
    <!-- 引用公共脚部 -->
    <#include "../common/smart_menu.ftl" />
    <script src="${basePath}/js/jquery.js"></script>
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/jquery.lazyload.js"></script>
    <script src="${basePath}/js/goods/goods_list.js"></script>
    <script>
	  $(function(){
		FastClick.attach(document.body);
		$('.cate').click(function(){
		  if($(this).attr('class').indexOf('hover')>=0){
			$(this).removeClass('cate_hover');
		  }
		  else{
			$(this).addClass('cate_hover');
		  }
		});
          $("#keleyi-menu").keleyi({
              width: '100%',
              item_background_color: '#FAFAFA',
              item_background_color_hover: '#FAFAFA',
              item_margin: '0',
              bar_background_color: '#FAFAFA'
          });
          var _t = setTimeout(function(){
              $(".keleyi-menu").show();
          },100)
		$('img.lazy').lazyload({
		  palceholder : 'images/loading.gif',
		  effect : 'fadeIn'
		});
		<!-- 以下是分享部分 -->
		 var onBridgeReady=function(){
	   //发送给朋友
	   WeixinJSBridge.on('menu:share:appmessage', function(argv){
		  WeixinJSBridge.invoke('sendAppMessage',{
			 "img_url":dataForWeixin.MsgImg,
			 "img_width":"120",
			 "img_height":"120",
			 "link":dataForWeixin.url,
			 "desc":dataForWeixin.desc,
			 "title":dataForWeixin.title
		  }, function(res){(dataForWeixin.callback)();});
	   });
	   //发送到朋友圈
	   WeixinJSBridge.on('menu:share:timeline', function(argv){
		  WeixinJSBridge.invoke('shareTimeline',{
			 "img_url":dataForWeixin.MsgImg,
			 "img_width":"120",
			 "img_height":"120",
			 "link":dataForWeixin.url,
			 "desc":dataForWeixin.desc,
			 "title":dataForWeixin.title
		  }, function(res){(dataForWeixin.callback)();});});
		};
		if(document.addEventListener){
		   document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		}else if(document.attachEvent){
		   document.attachEvent('WeixinJSBridgeReady'   , onBridgeReady);
		   document.attachEvent('onWeixinJSBridgeReady' , onBridgeReady);
		}
	  });

	  var dataForWeixin={
	   MsgImg:"http://mobile.ningpai.com/app/h5/images/intro.jpg",
	   url:"<#if map.nowcate?? >http://shop.ningpai.com/mobile/getwxcode3.htm?toUrl=list/${map.nowcate.catId}.html<#else>http://shop.ningpai.com/mobile/getwxcode3.htm?toUrl=list/allproduct.html</#if>",
	   title:"发现一些好商品",
	   desc:"<#if map.nowcate?? >${map.nowcate.catName}<#else>所有商品</#if>",
	   callback:function(
		//这里是分享成功后的回调功能
	   ){}
	};
	</script>
  </body>
</html>
