<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#assign basePath=request.contextPath> 
<title>${sys.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script> 
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/index.js"></script>
<style>
.rmb{
	font-family: "微软雅黑";
}
.inforBox{
	font-family: "microsoft yahei";
	font-weight: bold;
}
.inforBox span{
	float: right;
	font-size: 12px ;
	font-weight: normal;
}
.infor{
	width:100%;
	font-size: 12px ;
	background: none;
}


</style>
</head>

<body>
<div>
  <#include "topnew.ftl">

</div>
<div class="container">
<div class="slide_wp clearfix">
        	<div class="slide_box fl">
            	<div id="slides">
            		<#if (avc??)>
            		<#list avc as bigAdvert>
            			<#if (bigAdvert_index >= 0 )>
			            <#if (bigAdvert_index < 4 )>
                			<a href="${bigAdvert.adverHref}"><img alt="" src="${bigAdvert.adverPath}" width="790px" height="350px"/></a>
			            </#if>                   	
			            </#if>
                    </#list>
                    </#if>
                </div><!--/slides-->
                <div class="jscroll pr">
                	<div class="jscroll_wp">
                    	<div class="jscroll_box">
                            <ul class="jscroll_list clearfix">
                            	<#if (avs??)>
                            	<#list avs as smallAdvert>
	                            	<#if (smallAdvert_index >= 0 )>
			                    	<#if (smallAdvert_index < 8 )>
	                                	<li><a href="${smallAdvert.adverHref}"><img alt="" src="${smallAdvert.adverPath}" width="250" height="160" /></a></li>
			                    	</#if>                   	
			                    	</#if>
                                </#list>
                                </#if>
                            </ul><!--/jscroll_list-->
                        </div><!--/jscroll_box-->
                    </div><!--/jscroll_wp-->
                    <a class="j_prev" href="javascript:;"></a>
                    <a class="j_next" href="javascript:;"></a>
                </div><!--/jscroll-->
            </div><!--/slide_box-->
            
            <div class="live_wp fr">
            	
                <div class="hao_post">
                	<div>
	                	<h4 class="inforBox"  style="border-bottom: 2px solid #AAA;">
	                		${temp.expFleid2}
	                		<span style="float: right;"><a href="information/list/">查看全部&gt;&gt;</a><span>
	                	</h4>
	                	
                	</div>
                    <div class="post_cont" style="height:122px">
                    	<ul class="h_post">
                    		<#list infoList as info>
                    			<#if (info_index >= 0 )>
		                    	<#if (info_index < 5 )>
	                        		<li><a href="information/${info.infoId}" class="infor" 
	                        			style="background:none;padding-left:5px;" title="${info.title}">
	                        			<#if info.title?length gt 15>
	                        				<span style="float: left;">${info.title?substring(0,15)}</span>
	                        			<#else>
	                        				<span style="float: left;">${info.title}</span>
	                        			</#if>
	                        			</a>
	                        		</li>
		                    	</#if>                   	
		                    	</#if>
		                    </#list>
                        </ul><!--/h_post-->
                    </div><!--/post_cont-->
                </div><!--/hao_post-->
                <#list pageAdvs as adv>
				        <#if adv.adverSort==1>
				           <div class="ad mt10">
			        		<a  href="<#if adv.adverHref??>${adv.adverHref}</#if>" title="${adv.adverName}"><img alt="${adv.adverName}" src="${adv.adverPath}" width="200px" height="155px"/></a>
			        		</div>
						</#if>
				</#list>
                <#list pageAdvs as adv>
				        <#if adv.adverSort==2>
				           <div class="ad mt10">
			        		<a  href="<#if adv.adverHref??>${adv.adverHref}</#if>" title="${adv.adverName}"><img alt="${adv.adverName}" src="${adv.adverPath}" width="200px" height="155px"/></a>
			        		</div>
						</#if>
				</#list>
            </div><!--/live_wp-->
        </div><!--/slide_wp-->
        <div class="mt20 clearfix">
	        <!--左侧页面标签-->
        	<div class="deals fl pr">
        		<#if (tags?? && (tags?size>0))>
	        		<#list tags as tag>
	        		<#if (tag_index >= 0)>
	        		<#if (tag_index < 1)>
	            	<div class="deals_tit clearfix">
	                	<h4 class="fl"><span>${tag.name}</span></h4>
	                </div><!--/deals_tit-->
	                <div class="deals_cont">
	                	<div id="deals_slides" class="deals_slides">
	                		<#list tag.indexAdvertList as tagAdvert>
			        		<#if (tagAdvert_index >= 0)>
			        		<#if (tagAdvert_index < 3)>
	                    	<div class="deals_slide pr">
	                        	<a href="${tagAdvert.adverHref}"><img alt="" src="${tagAdvert.adverPath}" width="193" height="223" /></a>
	                        </div><!--/deals_slide-->
	                        </#if>
			        		</#if>
			        		</#list>
	                    </div><!--/deals_slides-->
	                </div><!--/deals_cont-->
	        		</#if>
	        		</#if>
	        		</#list>
        		<#else>
	            	<div class="deals_tit clearfix">
	                	<h4 class="fl"><span style="text-decoration:line-through;">推荐特惠</span></h4>
	                </div><!--/deals_tit-->
	                <div class="deals_cont">
	                	<div id="deals_slides" class="deals_slides">
	                    	<div class="deals_slide pr">
	                        	<a href="javascript:;"><img alt="" src="images/images_10.jpg" width="193" height="223" /></a>
	                        </div><!--/deals_slide-->
	                        <div class="deals_slide pr">
	                        	<a href="javascript:;"><img alt="" src="images/images_10.jpg" width="193" height="223" /></a>
	                        </div><!--/deals_slide-->
	                        <div class="deals_slide pr">
	                        	<a href="javascript:;"><img alt="" src="images/images_10.jpg" width="193" height="223" /></a>
	                        </div><!--/deals_slide-->
	                    </div><!--/deals_slides-->
	                </div><!--/deals_cont-->
        		</#if>
            </div><!--/deals--><!--/左侧页面标签-->
            <!--右侧页面标签-->
            <div class="pro_show fr">
            <#if (tags?? && tags?size>1)>
            	<div class="pro_tit">
                	<ul class="p_tit clearfix">
		        	<#list tags as tag>
		        	<#if (tag_index >= 1)>
		        	<#if (tag_index < 5)>
                    	<li class="cur"><a href="javascript:;"><span>${tag.name}</span></a></li>
	                </#if>
		        	</#if>
		        	</#list>
                    </ul><!--/p_tit-->
                </div><!--/pro_tit-->
                <div class="pro_content pr">
                	<a class="pro_prev" href="javascript:;"></a>
                    <a class="pro_next" href="javascript:;"></a>
		        	<#list tags as tag>
		        	<#if (tag_index >= 1)>
		        	<#if (tag_index < 5)>
                    <div class="pro_cont pc_0${tag_index} mt5">
                    	<ul class="pc_list clearfix">
                    		<#list tag.indexAdvertList as tagAdvert>
			        		<#if (tagAdvert_index >= 0)>
			        		<#if (tagAdvert_index < 4)>
                        	<li>
                            	<a href="${tagAdvert.adverHref}"><img alt="" src="${tagAdvert.adverPath}" width="225" height="210" /></a>
                            	<p class="ps_info clearfix" style="background:rgba(218,142,63,.5);">
                                	<span class="fl ml5" title="">
                                		<#if (tagAdvert.adverName?length>10)>
	                                		${tagAdvert.adverName?substring(0,10)}...
                                		<#else>
	                                		${tagAdvert.adverName}
                                		</#if>
                                	</span>
                                    <span class="fr mr5 ps_price">${tagAdvert.des}</span>
                                </p><!--/ps_info-->
                            </li>
                            </#if>
                            </#if>
                            </#list>
                        </ul><!--/pc_list-->
                    </div><!--/pc_01-->
                    </#if>
		        	</#if>
		        	</#list>
                </div><!--/pro_content-->
			<#else>
				<div class="pro_tit">
                	<ul class="p_tit clearfix">
                    	<li class="cur"><a href="javascript:;"><span style="text-decoration:line-through;">享划算</span></a></li>
                        <li><a href="javascript:;"><span style="text-decoration:line-through;">热销排行</span></a></li>
                        <li><a href="javascript:;"><span style="text-decoration:line-through;">最新上架</span></a></li>
                        <li><a href="javascript:;"><span style="text-decoration:line-through;">品牌热卖</span></a></li>
                    </ul><!--/p_tit-->
                </div><!--/pro_tit-->
                <div class="pro_content pr">
                	<a class="pro_prev" href="javascript:;"></a>
                    <a class="pro_next" href="javascript:;"></a>
                    <div class="pro_cont pc_01 mt5">
                    	<ul class="pc_list clearfix">
                        	<li>
                            	<a href="javascript:;"><img alt="" src="images/images_11.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(255,98,47,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_12.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(110,190,30,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_13.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(218,142,63,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_14.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(138,108,99,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                        </ul><!--/pc_list-->
                    </div><!--/pc_01-->
                    
                    <div class="pro_cont pc_02 mt5">
                    	<ul class="pc_list clearfix">
                        	<li>
                            	<a href="javascript:;"><img alt="" src="images/images_11.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(255,98,47,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_12.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(110,190,30,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_13.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(218,142,63,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_14.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(138,108,99,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                        </ul><!--/pc_list-->
                    </div><!--/pc_02-->
                    
                    <div class="pro_cont pc_03 mt5">
                    	<ul class="pc_list clearfix">
                        	<li>
                            	<a href="javascript:;"><img alt="" src="images/images_11.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(255,98,47,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_12.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(110,190,30,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_13.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(218,142,63,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_14.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(138,108,99,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                        </ul><!--/pc_list-->
                    </div><!--/pc_03-->
                    
                    <div class="pro_cont pc_04 mt5">
                    	<ul class="pc_list clearfix">
                        	<li>
                            	<a href="javascript:;"><img alt="" src="images/images_11.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(255,98,47,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_12.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(110,190,30,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_13.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>4.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(218,142,63,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                            <li>
                            	<a href="javascript:;"><img alt="" src="images/images_14.jpg" width="225" height="210" /></a>
                                <div class="discount"><span>1.5</span>折</div>
                                <p class="ps_info clearfix" style="background:rgba(138,108,99,.5);">
                                	<span class="fl ml5">还剩：3天11小时33分40秒</span>
                                    <span class="fr mr5 ps_price">￥998</span>
                                </p><!--/ps_info-->
                            </li>
                        </ul><!--/pc_list-->
                    </div><!--/pc_04-->
                </div><!--/pro_content-->
			</#if>
            </div><!--/pro_show--><!--/右侧页面标签-->          
        </div>
        <#list floor.floorList as floors>  
        <a class="g_box mt10" href="javascript:;">
        <#if floors.storeyImg?exists>
       		<a href="${floors.storeyImgHref}"><img alt="" src="${floors.storeyImg}" width="1200px" height="90px"/></a>
       	</#if>
        <div class="floor clearfix mt20 floor_01">
        	<div class="flr_left fl">
            	<h3>${floors_index+1}F ${floors.storeyName}</h3>
                <div class="hot_recommend mt5">
                	<h4 class="hide">热销推荐</h4>
                    <ul class="ranking_list mt10">
                    	<#list floors.indexGoodsList as recommend>
                    	<li class="clearfix">
                        	<span class="ranking_num fl">${recommend_index+1}</span>
                            <a class="ranking_img fl" href="item/${recommend.id}.html"><img alt="item/${recommend.id}.html"" src="${recommend.urlpic}" width="56" height="60"/></a>
                            <a class="ranking_name fl" href="item/${recommend.id}.html"><#if recommend.name?length gt 12>${recommend.name?substring(0,12)}<#else>${recommend.name}</#if></a>
                            <#--
                            <span class="pros_price">￥${recommend.price}</span>
                            -->
                            <span class="ranking_price rmb">￥${recommend.price}</span>
                        </li>
                        </#list>
                       
                    </ul><!--/ranking_list-->
                    
                    <ul class="brand_list clearfix mt10">
                    	<#list floors.indexAdvertList as ab>
                    	<#if (ab_index >= 0 )>
                    	<#if (ab_index < 4 )>
                        	<li><a href="${ab.adverHref}"><img alt="" src="${ab.adverPath}" width="70" height="50" /></a></li>
                    	</#if>                   	
                    	</#if>
                    	</#list>
                    </ul><!--/brand_list-->
                    <p class="into_brand mt10 tr"><a href="list/${floors.goodsCatId}-${floors.goodsCatId}.html">进入&gt;&gt;</a></p>
                </div><!--/hot_recommend-->
            </div><!--/flr_left-->
            <#list floors.indexStoreyTagList as storeyTag>
            <div class="flr_right fl">
            	<div class="rmd_title clearfix">
                	<h4 class="fl">${storeyTag.name}</h4>
                    <div class="tit_cont fl clearfix">
                    	<a class="more_rmd fr" href="list/${floors.goodsCatId}-${floors.goodsCatId}.html">进入更多推荐&gt;</a>
                    	<ul class="rmd_list clearfix fr">
                    		<#list floors.indexCateList as cates>  
                        		<li><a href="list/${cates.id}-${floors.goodsCatId}.html">${cates.name}</a></li>
                         	</#list>
                        </ul><!--/rmd_list-->                 
                    </div><!--/tit_cont-->
                </div><!--/rmd_title-->
                <div class="recommend_cont pr">
                	<div class="rmd_slides">
                		<#list storeyTag.indexAdvertList as ab>
                    	<#if (ab_index >= 0 )>
                    	<#if (ab_index < 3 )>
                    	<a href="${ab.adverHref}"><img alt="${ab.adverHref}" src="${ab.adverPath}" width="470" height="228" /></a>
                    	</#if>                   	
                    	</#if>
                    	</#list>
                      
                    </div><!--/rmd_slides-->
                    <ul class="rmd_pros clearfix">
                    	<#list storeyTag.indexGoodsList as goods>
                    	<li>
                        	<a class="pros_img" href="item/${goods.id}.html"><img alt="item/${goods.id}.html" src=<#if goods.urlpic?exists>${goods.urlpic}</#if> width="160" height="160" /></a>
                            <a class="pros_name" href="item/${goods.id}.html" title="${goods.name}"><#if goods.name?length gt 16>${goods.name?substring(0,16)}...<#else>${goods.name}</#if></a>
                            <p class="clearfix">
                            	<span class="pros_code fl" title="${goods.number}">编号：
                            	<#if goods.number?length gt 10>${goods.number?substring(0,10)}...<#else>${goods.number}</#if>
                            	</span>
                                <span class="pros_price fr">￥${goods.price}</span>
                            </p>
                        </li>
                        </#list>
                    </ul><!--/rmd_pros-->
                </div><!--/recommend_cont-->
            </div><!--/flr_right-->
           	</#list>
        </div><!--/floor_01-->
      	</#list>
        <div class="ggs_box mt15 clearfix">
        	<#if pageAdvs?? && (pageAdvs?size>3)>
        	<#list pageAdvs as adv>
				<#if (adv.adverSort>=3) && (adv.adverSort<6)>
			        <a class="fl" href="<#if adv.adverHref??>${adv.adverHref}</#if>" title="${adv.adverName}"><img alt="${adv.adverName}" src="${adv.adverPath}" width="400px" height="101px"/></a>
				</#if>
			</#list>
        	</#if>
        </div><!--/ggs_box-->
        <#include "bottom.ftl">


</div>
</body>
</html>