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
    	<title><#if map.nowcate?? >${map.nowcate.catName}<#else>所有商品</#if>列表页--${sys.bsetName}</title>
    <#else>
	    <title><#if map.nowcate?? >${map.nowcate.catName}<#else>所有商品</#if>列表页--${seo.mete}</title>
    </#if>
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css?v=201606091615" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
  <body>
  <#--新加头部－加搜索-->
  <#include "../publicHeader3_ftl.ftl"/>
  <input class="storeId" type="hidden" value="${storeId!''}">
    <div class="goods_filter container-fluid">
      <div class="col-xs-3">
      	<a val="5D" attr="${map.searchBean.sort}" class="change_sort <#if map.searchBean.sort='' || map.searchBean.sort='5D'>cur</#if>" href="javascript:;">默认</a>
      </div>
      <div class="col-xs-3">
      	<a val="2D" attr="${map.searchBean.sort}" class="change_sort <#if map.searchBean.sort='22D' || map.searchBean.sort='2D'>cur</#if> <#if map.searchBean.sort='22D'> s_up<#elseif map.searchBean.sort='2D'> s_down</#if>" href="javascript:;">销量</a>
      </div>
      <div class="col-xs-3">
      	<a val="1D" attr="${map.searchBean.sort}" class="change_sort <#if map.searchBean.sort='11D' || map.searchBean.sort='1D'>cur</#if> <#if map.searchBean.sort='11D'> s_up<#elseif map.searchBean.sort='1D'> s_down</#if>" href="javascript:;">价格</a>
      </div>
      <div class="col-xs-3">
      	<a val="4D" attr="${map.searchBean.sort}" class="change_sort <#if map.searchBean.sort='44D' || map.searchBean.sort='4D'>cur</#if> <#if map.searchBean.sort='44D'>s_up<#elseif map.searchBean.sort='4D'>s_down</#if>" href="javascript:;">人气</a>
      </div>
    </div><!-- /goods_filter -->
    <div class="goods_list container-fluid">
    <#if map.pb.list?? && map.pb.list?size &gt; 0>
        <ul>
      <#list map.pb.list as product>
      	  <#--<div class="col-xs-12">
	        <a href="${basePath}/item/${product.goodsInfoId}.html">
	          <img alt="" src="<#if  product.imageList?? && product.imageList?size &gt; 0 >${product.imageList[0].imageBigName!''}</#if>">
	          <span style="text-align:left;height:2.6em;">${product.productName!''}</span>
	          <span>￥<#if vip?? && vip=="1">
              ${product.wareVipPrice?string('0.00')}
              <#else>
               ${product.warePrice?string('0.00')}
              </#if></span>
	        </a> 
	      </div>-->
        <li>
            <a href="${basePath}/item/${product.goodsInfoId}.html">
                <img src="<#if  product.imageList?? && product.imageList?size &gt; 0 >${product.imageList[0].imageBigName!''}</#if>">
                <p>${product.productName!''}</p>
	          <span>￥<strong><#if vip?? && vip=="1">${product.wareVipPrice?string('0.00')}<#else>${product.warePrice?string('0.00')}</#if></strong></span>
            </a>
            <div class="goodsBtn"><i data-productId="${product.goodsInfoId}" data-url="${basePath}/saveAtte.htm"
                                     <#if goodsIds?? && goodsIds?seq_contains('${product.goodsInfoId?string}')>
                                     style="background-image: url('${basePath}/images/qp_sca.png')"
                                     <#else >style="background-image: url('${basePath}/images/qp_scb.png')"
                                     </#if>></i><button
                    data-productId="${product.goodsInfoId}" data-count="0" data-url="${basePath}/addproducttoshopcarnewAJAX.htm"><span class="dis">+1</span>加入购物车</button></div>
        </li>
      </#list>
        </ul>
        <#else>
            <div style="width:100%;text-align:center;margin-top:20px;font-size:16px;">
                <img src="${basePath}/images/no-pros.png" style="width:50%;"/>
                <br>
                商品不存在！
            </div>
        </#if>
    </div><!-- /goods_list -->
<#if map.pb.data?? && map.pb.data?size &gt; 0>
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
    </div><!-- /pages -->
</#if>
   <div class="hide">
		<#--<form action="${basePath}/<#if storeId??&&storeId!=0>allGoodsListByStoreId.htm?storeId=${storeId}<#else><#if map.nowcate?? >list/${map.nowcate.catId}<#else>specialPer</#if>.html</#if>" id="searchForm" method="post">-->
            <form action="${basePath}/<#if storeId??&&storeId!=0>allGoodsListByStoreId.htm?storeId=${storeId}<#else>list/<#if map.nowcate?? >${map.nowcate.catId}<#else>allproduct</#if>.html</#if>" id="searchForm" method="post">
			<div class="filterList">
				<ul class="clearfix">
					<input type="hidden" name="pageNo" class="pageNo" value="${map.pb.pageNo}">
					<input type="hidden" name="sort" class="list_sort" value="${map.searchBean.sort!''}">
					<input type="hidden" name="showStock" class="show_stock" value="${map.searchBean.showStock!''}">
					<input type="hidden" name="type" value="${type!''}">
				</ul>
			</div>
			<!--/filterList-->
		</form>
	</div>
    

    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/jquery.lazyload.js"></script>
    <script src="${basePath}/js/goods/goods_list.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
   <!-- <script src="${basePath}/js/customer/wxforward.js"></script>-->
    <script>
	  $(function(){

         /* var w = $(".goods_list img").width();
          $(".goods_list img").height(w);*/
		$('.cate').click(function(){
		  if($(this).attr('class').indexOf('hover')>=0){
			$(this).removeClass('cate_hover');
		  }
		  else{
			$(this).addClass('cate_hover');
		  }
		});

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
