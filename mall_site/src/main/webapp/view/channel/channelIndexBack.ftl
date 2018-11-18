<#include "../include/common.ftl">
<@htmlHead title='好享购——频道'>
<link rel="stylesheet" type="text/css" href="css/base.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>好享购——频道</title>
<link rel="stylesheet" type="text/css" href="css/base.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>-->
<@htmlBody>
<body>
<div>

</div>
<div class="container">
<div class="slide_wp clearfix">
        	<div class="slide_box fl">
            	<div id="slides">
                	<a href="javascript:;"><img alt="" src="images/images_05.jpg" /></a>
                    <a href="javascript:;"><img alt="" src="images/images_06.jpg" /></a>
                    <a href="javascript:;"><img alt="" src="images/images_05.jpg" /></a>
                    <a href="javascript:;"><img alt="" src="images/images_06.jpg" /></a>
                    <a href="javascript:;"><img alt="" src="images/images_05.jpg" /></a>
                </div><!--/slides-->
                <div class="jscroll pr">
                	<div class="jscroll_wp">
                    	<div class="jscroll_box">
                            <ul class="jscroll_list clearfix">
                                <li><a href="javascript:;"><img alt="" src="images/images_07.jpg" width="250" height="160" /></a></li>
                                <li><a href="javascript:;"><img alt="" src="images/images_07.jpg" width="250" height="160" /></a></li>
                                <li><a href="javascript:;"><img alt="" src="images/images_07.jpg" width="250" height="160" /></a></li>
                                <li><a href="javascript:;"><img alt="" src="images/images_07.jpg" width="250" height="160" /></a></li>
                                <li><a href="javascript:;"><img alt="" src="images/images_07.jpg" width="250" height="160" /></a></li>
                                <li><a href="javascript:;"><img alt="" src="images/images_07.jpg" width="250" height="160" /></a></li>
                            </ul><!--/jscroll_list-->
                        </div><!--/jscroll_box-->
                    </div><!--/jscroll_wp-->
                    <a class="j_prev" href="javascript:;"></a>
                    <a class="j_next" href="javascript:;"></a>
                </div><!--/jscroll-->
            </div><!--/slide_box-->
            
            <div class="live_wp fr">
            	<div class="live_box">
                	<div class="live_in pr">
                    	<a href="javascript:;"><img alt="" src="images/images_08.jpg" width="198" height="177" /></a>
                        <h5 class="pa">正在直播</h5>
                        <div class="live_info pa">
                        	<a class="live_pro" href="javascript:;">斯丽佳法兰克福展全棉四件套</a>
                            <p class="clearfix mt5">
                            	<span class="pro_code fl mt5">商品编号：<a href="javascript:;">9151448</a></span>
                                <span class="pro_price fr">￥<em>1299</em></span>
                            </p>
                        </div><!--/live_info-->
                    </div><!--/live_in-->
                    <div class="live_upside">
                    	<h4>上档直播</h4>
                        <div class="upside_box">
                        	<a class="live_pro" href="javascript:;">五粮液十二兽首酒之龙首6斤</a>
                            <div class="clearfix mt5">
                            	<a class="fl" href="javascript:;"><img alt="" src="images/images_09.jpg" /></a>
                                <div class="upside_info fr">
                                	<p>时间段：10:30~11:15</p>
                                    <p class="pro_code">商品编号：<a href="javascript:;">9151560</a></p>
                                    <p class="pro_price">￥<em>1980</em></p>
                                </div><!--/upside_info-->
                            </div>
                        </div><!--/upside_box-->
                    </div><!--/live_upside-->
                </div><!--/live_box-->
                <div class="hao_post">
                	<h4><a href="information/list/${map.channel.infoTypeId}">${map.channel.infoName}&gt;&gt;</a></h4>
                    <div class="post_cont">
                    	<ul class="h_post">
                    	<#list map.infoList as info>  
                        	<li><a href="information/${info.infoId}">${info.title}</a></li>
                        </#list>	
                        <#--
                        	<li><a href="javascript:;">好享购物2013销售排行榜！</a></li>
                            <li><a href="javascript:;">好享购物2013销售排行榜！</a></li>
                            <li><a href="javascript:;">好享购物2013销售排行榜！</a></li>
                            <li><a href="javascript:;">好享购物2013销售排行榜！</a></li>
                        -->
                        </ul><!--/h_post-->
                    </div><!--/post_cont-->
                </div><!--/hao_post-->
            </div><!--/live_wp-->
        </div><!--/slide_wp-->
        <div class="mt20 clearfix">
        	<div class="deals fl pr">
            	<div class="deals_tit clearfix">
                	<h4 class="fl">限时特惠</h4>
                </div><!--/deals_tit-->
                <div class="deals_cont">
                	<div id="deals_slides" class="deals_slides">
                    	<div class="deals_slide pr">
                        	<a href="javascript:;"><img alt="" src="images/images_10.jpg" width="193" height="223" /></a>
                            <p class="act_end">活动结束：3天20小时22分19秒</p>
                        </div><!--/deals_slide-->
                        <div class="deals_slide pr">
                        	<a href="javascript:;"><img alt="" src="images/images_10.jpg" width="193" height="223" /></a>
                            <p class="act_end">活动结束：3天20小时22分19秒</p>
                        </div><!--/deals_slide-->
                        <div class="deals_slide pr">
                        	<a href="javascript:;"><img alt="" src="images/images_10.jpg" width="193" height="223" /></a>
                            <p class="act_end">活动结束：3天20小时22分19秒</p>
                        </div><!--/deals_slide-->
                    </div><!--/deals_slides-->
                </div><!--/deals_cont-->
            </div><!--/deals-->
            
            <div class="pro_show fr">
            	<div class="pro_tit">
                	<ul class="p_tit clearfix">
                    	<li class="cur"><a href="javascript:;">享划算</a></li>
                        <li><a href="javascript:;">热销排行</a></li>
                        <li><a href="javascript:;">最新上架</a></li>
                        <li><a href="javascript:;">品牌热卖</a></li>
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
            </div><!--/pro_show-->          
        </div>
        <#list map.channelStoreyList as channelStorey>  
        <a class="g_box mt10" href="javascript:;"><img alt="" src="images/images_18.jpg" /></a>
       
        <div class="floor clearfix mt20 floor_01">
        	<div class="flr_left fl">
            	<h3>${channelStorey.storeyConf.floorId}F ${channelStorey.storeyConf.storeyName}</h3>
                <div class="hot_recommend mt5">
                	<#--
                	<h4 class="hide">热销推荐</h4>
                    <ul class="ranking_list mt10">
                    	<#list floors.indexGoodListRecommend as recommend>
                    	<li class="clearfix">
                        	<span class="ranking_num fl">${recommend_index+1}</span>
                            <a class="ranking_img fl" href="item/${recommend.id}.html"><img alt="item/${recommend.id}.html"" src="${recommend.urlpic}" width="56" height="60"/></a>
                            <a class="ranking_name fl" href="item/${recommend.id}.html">${recommend.name?substring(0,12)}</a>
                            <span class="ranking_price">￥${recommend.price}</span>
                        </li>
                        </#list>
                       
                    </ul>/ranking_list
                	-->
                    <a class="g_box mt10" href="${channelStorey.storeyConf.storeyHref}"><img alt="" src="${channelStorey.storeyConf.storeyImg}" /></a>
                    <ul class="brand_list clearfix mt10">
                    	<li><a href="javascript:;"><img alt="" src="images/images_20.jpg" width="70" height="50" /></a></li>
                        <li><a href="javascript:;"><img alt="" src="images/images_21.jpg" width="70" height="50" /></a></li>
                        <li><a href="javascript:;"><img alt="" src="images/images_22.jpg" width="70" height="50" /></a></li>
                    </ul><!--/brand_list-->
                    <p class="into_brand mt10 tr"><a href="javascript:;">进入品牌馆&gt;&gt;</a></p>
                </div><!--/hot_recommend-->
            </div><!--/flr_left-->
            <div class="flr_right fl">
            	<div class="rmd_title clearfix">
                	<#--
                	<h4 class="fl">新品上架</h4>
                	-->
                    <div class="tit_cont fl clearfix">
                    	<a class="more_rmd fr" href="list/${channelStorey.storeyConf.catId}-${channelStorey.storeyConf.catId}.html">进入更多推荐&gt;</a>
                    	<ul class="rmd_list clearfix fr">
                    		<#list channelStorey.goodsCateList as cates>  
                        		<li><a href="list/${cates.catId}-${channelStorey.storeyConf.catId}.html">${cates.catName}</a></li>
                         	</#list>
                        </ul><!--/rmd_list-->                 
                    </div><!--/tit_cont-->
                </div><!--/rmd_title-->
                <div class="recommend_cont pr">
                	<div class="rmd_slides">
                    	<a href="javascript:;"><img alt="" src="images/images_23.jpg" width="470" height="228" /></a>
                        <a href="javascript:;"><img alt="" src="images/images_23.jpg" width="470" height="228" /></a>
                        <a href="javascript:;"><img alt="" src="images/images_23.jpg" width="470" height="228" /></a>
                    </div><!--/rmd_slides-->
                    <ul class="rmd_pros clearfix">
                    	<#list channelStorey.goodsProductList as goods>
                    	<li>
                        	<a class="pros_img" href="item/${goods.goodsInfoId}.html"><img alt="item/${goods.goodsInfoId}.html" src=${goods.goodsInfoImgId} width="160" height="160" /></a>
                            <a class="pros_name" href="item/${goods.goodsInfoId}.html">
                            	<#if goods.goodsInfoName?length gt 12>
	                            	${goods.goodsInfoName?substring(0,12)}
                            	<#else>
	                            	${goods.goodsInfoName}
                            	</#if>
                            </a>
                            <p class="clearfix">
                            	<span class="pros_code fl">编号：${goods.goodsInfoItemNo}</span>
                                <span class="pros_price fr">￥${goods.goodsInfoPreferPrice}</span>
                            </p>
                        </li>
                        </#list>
                        
                    </ul><!--/rmd_pros-->
                </div><!--/recommend_cont-->
            </div><!--/flr_right-->
        </div><!--/floor_01-->
      	</#list>
        <div class="ggs_box mt15 clearfix">
        	<a class="fl" href="javascript:;"><img alt="" src="images/images_25.jpg" /></a>
            <a class="fl" href="javascript:;"><img alt="" src="images/images_26.jpg" /></a>
            <a class="fl" href="javascript:;"><img alt="" src="images/images_27.jpg" /></a>
        </div><!--/ggs_box-->
        
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.slides.min.js"></script>
<script type="text/javascript" src="js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="js/default.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</div>
</body>
</@htmlBody>