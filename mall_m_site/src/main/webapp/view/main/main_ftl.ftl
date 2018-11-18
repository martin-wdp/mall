<#assign basePath=request.contextPath>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <#if (sys.bsetName)??>
    	<title>${sys.bsetName}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>
    <input type="hidden" id="basePath" value="${(basePath)!''}">

    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.min.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
    <style>
    <#if (mobSiteBasic.isShowBuffer)?? && (mobSiteBasic.isShowBuffer=='1')>
		.curtain_wp {background:url(${(mobSiteBasic.backgroudImage)!''}) ${(mobSiteBasic.backgroudColor)!''} no-repeat center 30%; background-size:50% auto;}
  	<#else>
		.curtain_wp {background:url(images/curtain_word.png) #2589c9 no-repeat center 30%; background-size:50% auto;}
  	</#if>
		.curtain_wp p {position:absolute; width:100%; left:0; bottom:10%; text-align:center; color:#fff; font-family:Arial; font-size:16px;}
	</style>
    <script type="text/javascript">
    	//basePath值为''时，重新静态化
    	//if($("#basePath").val()==""){
    	//	location = "toStaticizeIndex.htm";
    	//}
    
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
                	//location = "${(siteProjectUrl)!''}";
                </#if>
            }
        }
		//根据设备跳转
        browserRedirect();
        //显示主体
        function showmain(){
        	$("#main").fadeIn(function(){
        		$('.roll_banner,.roll_banner img').css('height',parseInt(($(window).width() * 35) / 72) + 'px');
	   				var mySwiper = new Swiper('.swiper-container',{
		  			pagination: '.swiper-pagination',
		 		 	loop:true,
		  			grabCursor: true,
		 			autoplay : 3000
				});
        	});
        }
        
        	//站点设置信息不显示缓冲页
        	<#if (mobSiteBasic.isShowBuffer)?? && (mobSiteBasic.isShowBuffer=='0')>
        	//不显示缓冲页
        		$(function(){
        			showmain();
        		});
        	<#else>
        	//没有站点设置，或显示缓冲页
	        	$(function(){
		        	var count;
		        	$.get("getCount.htm",function(data){
		        		count = data + 1;
				        $.post("setCount.htm",{count:count},function(){
				        });
		        		if(data>=1){
				        	showmain();
		        		}else{
				        	$(".curtain_wp").width($(window).width()).height($(window).height()).delay(3000).fadeOut(500,function(){
					        	showmain();
				        	});
		        		}
		        	});
		        });
        	</#if>
        
        
    </script>
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
  <body>
  	<#--
  	<div id="index" style="background:url(${basePath}/images/roll_1.jpg) #e16434 no-repeat center center;background-size:100% auto; height: inherit;"></div>
  	<div id="index" style="background:url(${basePath}/images/roll_1_2.png) #e16434 no-repeat center center;background-size:100% 100%;"></div>
  	-->
  	<div class="curtain_wp">
  		<#if (mobSiteBasic)??>
			<p>© ${(mobSiteBasic.copyright)!''}</p>
  		<#else>
			<p>© NINGPAI</p>
  		</#if>
	</div><!--/curtain_wp-->
    <div id="main" style="display: none;">
    <div class="roll_banner">
      <div class="swiper-container">
      	<div class="swiper-wrapper">
      	<#if (mainInfo.bigMobAdvers)?? && (mainInfo.bigMobAdvers?size>0)>
      	<#list mainInfo.bigMobAdvers as bigAdver>
          <div class="swiper-slide"><a href="${(bigAdver.adverHref)!''}">
          	<img alt="" src="${(bigAdver.adverImgSrc)!''}"></a></div>
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
      	<a href="${basePath}/customer/coupon.html" class="link2">
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
      	<a href="${basePath}/inforlist/1.html" class="link4">
          <i></i>
          <span>新闻中心</span>
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
	          	<#--
	          	<img class="lazy" alt="" data-original="${(storeyAdver.adverImgSrc)!''}">
	          	<noscript><img alt="" src="${(storeyAdver.adverImgSrc)!''}" ></noscript></a>
	          	-->
	          	<img alt="" src="${(storeyAdver.adverImgSrc)!''}" ></a>
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
			
			<div class="imgs row">
				<#if (storeyInfo.storeyAdvers[0])??>
			      	<#assign storeyAdver1=storeyInfo.storeyAdvers[0] />
			        <div class="col-xs-12">
			          <a href="${(storeyAdver1.adverHref)!''}">
			          	<img alt="" src="${(storeyAdver1.adverImgSrc)!''}"></a>
			        </div>
				</#if>
		      </div>
	          <div class="imgs row">
	          	<#if (storeyInfo.storeyAdvers[1])??>
			      	<#assign storeyAdver2=storeyInfo.storeyAdvers[1] />
				    <div class="col-xs-6 img2">
				      <a href="${(storeyAdver2.adverHref)!''}">
				      <img alt="" src="${(storeyAdver2.adverImgSrc)!''}"></a>
				    </div>
				</#if>
			    <div class="col-xs-6 img_s">
					<#if (storeyInfo.storeyAdvers[2])??>
		      		<#assign storeyAdver3=storeyInfo.storeyAdvers[2] />
				     <div class="row">
			          	<div class="col-sm-12"  style="height:50%;">
			              <a class="border_l" href="${(storeyAdver3.adverHref)!''}">
			              <img alt="" src="${(storeyAdver3.adverImgSrc)!''}"></a>
			            </div>
			          </div>
					</#if>
					<#if (storeyInfo.storeyAdvers[3])??>
			      	<#assign storeyAdver4=storeyInfo.storeyAdvers[3] />
			          <div class="row">
			          	<div class="col-sm-12"  style="height:50%;">
			              <a class="border_l" href="${(storeyAdver4.adverHref)!''}">
			              <img alt="" src="${(storeyAdver4.adverImgSrc)!''}"></a>
			            </div>
			          </div>
					</#if>
			        </div>
		      </div>
		  </#if>
	          
	    </div><!-- /index_goods -->
    </#if>
    </#list>
    </#if>
    
    
    <div class="more_goods">
      <a href="${basePath}/list/allproduct.html">查看更多商品&gt;&gt;</a>
    </div><!-- /more_goods -->
    
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
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
    <script>
	  $(function(){
		$('img.lazy').lazyload({
		  palceholder : 'images/loading.gif',
		  effect : 'fadeIn'
		});
		
		<#--
			$(".col-sm-12").load(function(){
			alert(3);
			  	alert($(".col-sm-12").parent().parent().prev().css("height"));
			  	$(".col-sm-12").css("height",$(".col-sm-12").parent().parent().prev().css("height")/2);
			
			});
			var obj = new Image();
		    obj.src = $(".img2 img").attr("src");
		    obj.onload = function(){
		      $(".img_s img").css("height",obj.height/2);
		    };
		-->
					
	  });
	  
	</script>