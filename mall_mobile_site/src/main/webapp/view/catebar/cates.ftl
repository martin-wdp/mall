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
  </head>
  <body>
    <div class="cates">
      <#list catebars as catebar>
      	<div class="cate">
      		<div class="cate_lv1">
      		<#if (catebar.temp2)?? && (catebar.temp2=='1')>
	          <a href="${(catebar.temp1)!''}">
      		<#elseif (catebar.temp3)?? && (catebar.temp3=='1')>
	          <a href="${basePath}/list/${(catebar.cateId)!''}">
      		<#else>
	          <a href="javascript:;">
      		</#if>
	          	<img alt="" src="${(catebar.imgSrc)!''}">
	            <span>${(catebar.name)!''}</span>
	            <i></i>
	          </a>
	        </div>
	        <#if (catebar.childs)?? && (catebar.childs?size>0)>
	        <div class="cate_lv2">
	          <ul>
	          	<#list catebar.childs as child>
	          		<#if (child.temp2)?? && (child.temp2=='1')>
		          		<li><a href="${(child.temp1)!''}">${(child.name)!''}</a></li>
		      		<#else>
		          		<li><a href="${basePath}/list/${(child.cateId)!''}">${(child.name)!''}</a></li>
		      		</#if>
	          	</#list>
	          </ul>
	          <div class="clr"></div>
	        </div>
	        </#if>
	    </div>
      </#list>
    </div><!-- /cates -->
    
    <div class="more_goods">
      <a href="${basePath}/list/allproduct.html">查看更多商品&gt;&gt;</a>
    </div><!-- /more_goods -->
    
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    
	<#include "../common/smart_menu.ftl"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script>
	  $(function(){
		FastClick.attach(document.body);
		$('.cate').click(function(){
		  if($(this).attr('class').indexOf('hover')>=0){
			$(this).removeClass('cate_hover');
		  }
		  else{
		  	$('.cate').removeClass('cate_hover');
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
		
	  });
	</script>
  </body>
</html>
