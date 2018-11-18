<#assign basePath=request.contextPath>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
    <#if (sys.bsetName)??>
    	<title>${sys.bsetName}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>${seo.mete}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>
    

    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.min.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
        function browserRedirect() {
            var sUserAgent = navigator.userAgent.toLowerCase();
            var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
            var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
            var bIsMidp = sUserAgent.match(/midp/i) == "midp";
            var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
            var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
            var bIsAndroid = sUserAgent.match(/android/i) == "android";
            var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
            var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
            if (!(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM)) {
                //跳转到pc版
                <#if siteProjectUrl?? && (siteProjectUrl?length>0)>
                	//location = "${siteProjectUrl}";
                </#if>
            }
        }

        browserRedirect();
    </script>
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
  <body>
    <div class="roll_banner">
      <div class="swiper-container">
      	<div class="swiper-wrapper">
      	<#if (mainInfo.bigMobAdvers)?? && (mainInfo.bigMobAdvers?size>0)>
      	<#list mainInfo.bigMobAdvers as bigAdver>
          <div class="swiper-slide"><a href="${(bigAdver.adverHref)!''}">
          	<img alt="" src="${(bigAdver.adverImgSrc)!''}" style="height:691px;"></a></div>
      	</#list>
      	</#if>
        </div>
      </div>
      <div class="swiper-pagination"></div>
    </div><!-- /roll_banner -->
    <div class="quick_links row">
      <div class="col-xs-3">
      	<a href="${basePath}/list/allproduct.html" class="link1">
          <i></i>
          <span>全部商品</span>
        </a>
      </div>
      <div class="col-xs-3">
      	<a href="#" class="link2">
          <i></i>
          <span>优惠券</span>
        </a>
      </div>
      <div class="col-xs-3">
      	<a href="${basePath}/list/allproduct.html" class="link3">
          <i></i>
          <span>热门推荐</span>
        </a>
      </div>
      <div class="col-xs-3">
      	<a href="#" class="link4">
          <i></i>
          <span>话费充值</span>
        </a>
      </div>
    </div><!-- /quick_links -->
    <#if (mainInfo.mobStoreyInfos)?? && (mainInfo.mobStoreyInfos?size>0)>
	    <div class="index_goods">
	      <h4>${(mainInfo.mobStoreyInfos[0].mobStorey.storeyName)!''}</h4>
	      <div class="imgs row">
	      	<#if (mainInfo.mobStoreyInfos[0].storeyAdvers)?? &&
	      		 (mainInfo.mobStoreyInfos[0].storeyAdvers?size>0)>
			<#list mainInfo.mobStoreyInfos[0].storeyAdvers as storeyAdver>
	        <div class="col-xs-6">
	          <a class="border_r" href="${(storeyAdver.adverHref)!''}">
	          	<img class="lazy" alt="" data-original="${(storeyAdver.adverImgSrc)!''}" style="width:711px; height:397px;">
	          	<noscript><img alt="" src="${(storeyAdver.adverImgSrc)!''}"  style="width:711px; height:397px;"></noscript></a>
	        </div>
			</#list>
			</#if>
	      </div>
	    </div><!-- /index_goods -->
    </#if>
    
    <#if (mainInfo.mobStoreyInfos)?? && (mainInfo.mobStoreyInfos?size>0)>
    <#list mainInfo.mobStoreyInfos as storeyInfo>
    <#if (storeyInfo_index>0)>
    	<div class="index_goods">
	      <h4>${(storeyInfo.mobStorey.storeyName)!''}</h4>
	      <#--设置4个广告变量-->
	      <#assign storeyAdver1=''/>
	      <#assign storeyAdver2=''/>
	      <#assign storeyAdver3=''/>
	      <#assign storeyAdver4=''/>
	      <#if (storeyInfo.storeyAdvers)?? &&
	      		 (storeyInfo.storeyAdvers?size>0)>
			<#if (storeyInfo.storeyAdvers[0])??>
	      	<#assign storeyAdver1=storeyInfo.storeyAdvers[0] />
			</#if>
			<#if (storeyInfo.storeyAdvers[1])??>
	      	<#assign storeyAdver2=storeyInfo.storeyAdvers[1] />
			</#if>
			<#if (storeyInfo.storeyAdvers[2])??>
	      	<#assign storeyAdver3=storeyInfo.storeyAdvers[2] />
			</#if>
			<#if (storeyInfo.storeyAdvers[3])??>
	      	<#assign storeyAdver4=storeyInfo.storeyAdvers[3] />
			</#if>
		  </#if>
	          <div class="imgs row">
		        <div class="col-xs-12">
		          <a href="${(storeyAdver1.adverHref)!''}">
		          	<img class="lazy" alt="" data-original="${(storeyAdver1.adverImgSrc)!''}" style="width:1423px; height:672px;">
		          	<noscript><img alt="" src="${(storeyAdver1.adverImgSrc)!''}" style="width:1423px; height:672px;"></noscript></a>
		        </div>
		      </div>
	          <div class="imgs row">
			    <div class="col-xs-6">
			      <a href="${(storeyAdver2.adverHref)!''}">
			      <img class="lazy" alt="" data-original="${(storeyAdver2.adverImgSrc)!''}" style="width:712px; height:676px;">
			      <noscript><img alt="" src="${(storeyAdver2.adverImgSrc)!''}" style="width:712px; height:676px;"></noscript></a>
			    </div>
		        <div class="col-xs-6">
		          <div class="row">
		          	<div class="col-sm-12">
		              <a class="border_l" href="${(storeyAdver3.adverHref)!''}">
		              <img class="lazy" alt="" data-original="${(storeyAdver3.adverImgSrc)!''}" style="width:711px; height:334px;">
		              <noscript><img alt="" src="${(storeyAdver3.adverImgSrc)!''}" style="width:711px; height:334px;"></noscript></a>
		            </div>
		          </div>
		          <div class="row">
		          	<div class="col-sm-12">
		              <a class="border_l" href="${(storeyAdver4.adverHref)!''}">
		              <img class="lazy" alt="" data-original="${(storeyAdver4.adverImgSrc)!''}" style="width:711px; height:334px;">
		              <noscript><img alt="" src="${(storeyAdver4.adverImgSrc)!''}" style="width:711px; height:334px;"></noscript></a>
		            </div>
		          </div>
		        </div>
		      </div>
	    </div><!-- /index_goods -->
    </#if>
    </#list>
    </#if>
    
    
    <div class="more_goods">
      <a href="${basePath}/list/allproduct.html">查看更多商品&gt;&gt;</a>
    </div><!-- /more_goods -->


    
    <#include "../common/smart_menu.ftl"/>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/jquery.lazyload.js"></script>
    <script>
	  $(function(){
		FastClick.attach(document.body);
		$('.roll_banner,.roll_banner img').css('height',parseInt(($(window).width() * 35) / 72) + 'px');
	    var mySwiper = new Swiper('.swiper-container',{
		  pagination: '.swiper-pagination',
		  loop:true,
		  grabCursor: true,
		  autoplay : 3000
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
	  });
	</script>
  </body>
</html>
