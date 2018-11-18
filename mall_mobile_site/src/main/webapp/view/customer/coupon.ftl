<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
      <!-- Bootstrap -->
  <#assign basePath=request.contextPath>
      <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
      <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
      <link href="${basePath}/css/style.css" rel="stylesheet">
      <script src="${basePath}/js/jquery.js"></script>
      <script src="${basePath}/js/jquery.keleyi.js"></script>
      <script>
          $(function(){
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
          });
      </script>
      <style>
          #keleyi-menu {display:none;}
      </style>
    <#if (sys.bsetName)??>
    	<title>我的优惠券-${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>我的优惠券-${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="order_filter">
      <ul>
        <li <#if !type??> class="current"</#if> ><a href="${basePath}/customer/coupon.html">全部</a></li>
        <li <#if type?? && type=='1'> class="current"</#if> ><a href="${basePath}/customer/coupon.html?type=1">未使用</a></li>
        <li <#if type?? && type=='2'> class="current"</#if> ><a href="${basePath}/customer/coupon.html?type=2">已使用</a></li>
        <li <#if type?? && type=='3'> class="current"</#if> ><a href="${basePath}/customer/coupon.html?type=3">已作废</a></li>
      </ul>
    </div>
    <div class="ocoupon_list">
    <#if pb.list?size!=0>
    	<#list pb.list as coupon >
    		 <div class="coupon_item <#if (coupon.codeStatus=="2")>coupon_zf</#if><#if (coupon.codeStatus=="3")>coupon_eq</#if>">
		        <h4>
				<#if (coupon.couponRulesType=="1")>
					直减	${((coupon.couponStraightDown.downPrice)!'0.00')?string.currency}
				<#else>
					满${((coupon.couponFullReduction.fullPrice)!'0.00')?string.currency}减${((coupon.couponFullReduction.reductionPrice)!'0.00')?string.currency}
				</#if>
				<#--${coupon.couponName}${coupon.couponId}-->
				<small>
					范围:
					<#if (coupon.gplist??) >
						<#list coupon.gplist as gp>
								<#if (gp.goodsInfoName?length>16)>
									${gp.goodsInfoName?substring(0,16)}...&nbsp;
								<#else>
									${gp.goodsInfoName}&nbsp;
								</#if>
							</a>
						</#list>
					</#if>
					
					<#if (coupon.gclist??) >
						<#list coupon.gclist as gc>
								<#if (gc.catName)?? && (gc.catName?length>16)>
									${gc.catName?substring(0,16)}...&nbsp;
								<#else>
									${gc.catName}&nbsp;
								</#if>
							</a>
						</#list>
					</#if>
					
				</small></h4>
		        <div class="time">
		          <p>${coupon.couponStartTime?string("yyyy/MM/dd")}</p>
		          <p>${coupon.couponEndTime?string("yyyy/MM/dd")}</p>
		        </div>
		      </div>
    	</#list>
    <#else>
    <div style="text-align:center; font-size:14px;">  暂无数据！</div>

    </#if>
    </div><!-- /order_list -->
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    <#include '/common/smart_menu.ftl' />
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script>
	  $(function(){
		FastClick.attach(document.body);
		$('.more_order_goods').click(function(){
			$(this).prevAll().show();
			$(this).remove();
		});
	  });
	</script>
  </body>
</html>
