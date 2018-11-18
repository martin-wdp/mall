<!DOCTYPE html>
<#assign basePath=request.contextPath>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <#if (sys.bsetName)??>
    	<title>${(sys.bsetName)!''}</title>
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
    
    <div class="login_title">
      <h4>新闻中心</h4>
    </div><!-- /login_title -->
    
    <div class="articles_list">
      <ul>
      <#if (pb.list)?? && (pb.list?size>0)>
      	<#list pb.list as info>
			<li><a href="${basePath}/infordetail/${(info.infoId)!''}.html">
	          <p>${(info.title)!''}</p>
	        </a></li>
      	</#list>
      </#if>
      </ul>
    </div>
    
    <div class="pages container-fluid">
      <div class="col-xs-6">
      	<#if (pb.pageNo)?? && (pb.pageNo<=1)>
      		<a class="disabled" href="#">&lt;&nbsp;上一页</a>
      	<#else>
      		<a href="${basePath}/inforlist/${(pb.prePageNo)!''}.html">&lt;&nbsp;上一页</a>
      	</#if>
      </div>
      <div class="col-xs-6">
      	<#if (pb.pageNo)?? && (pb.pageNo>=pb.totalPages)>
      		<a class="disabled" href="#">下一页&nbsp;&gt;</a>
      	<#else>
      		<a href="${basePath}/inforlist/${(pb.nextPageNo)!''}.html">下一页&nbsp;&gt;</a>
      	</#if>
      </div>
    </div><!-- /pages -->
    
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    
    <#include "../common/smart_menu.ftl"/>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script>
	  $(function(){
		FastClick.attach(document.body);
		$('.fill_item input').focus(function(){
		  $(this).parent().next().css('borderColor','#EB6122');
		});
		$('.fill_item input').blur(function(){
		  $(this).parent().next().css('borderColor','#EEEEEE');
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
