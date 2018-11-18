<#include "../include/common.ftl">
<@htmlHead title="${map.detailBean.productVo.productName}所有评论-${topmap.systembase.bsetName}">
</@htmlHead>
<@htmlBody>
	<#--一引入头部 <#include "/index/topnew.ftl" /> -->	
		<#if (topmap.temp)??>
			<#if (topmap.temp.tempId==1)>
				<#include "../index/topnew.ftl">
			<#elseif (topmap.temp.tempId==3)>
				<#include "../index/newheader.ftl">
			<#elseif (topmap.temp.tempId==9)>
				<#include "../index/newheader4.ftl">
			<#elseif (topmap.temp.tempId==10)>
				<#include "../index/newheader5.ftl">
			<#elseif (topmap.temp.tempId==11)>
				<#include "../index/newheader6.ftl">
			<#elseif (topmap.temp.tempId==12)>
				<#include "../index/newheader7.ftl">
			<#elseif (topmap.temp.tempId==13)>
				<#include "../index/newheader8s.ftl">
			<#elseif (topmap.temp.tempId==14)>
				<#include "../index/newheader9.ftl">
			<#elseif (topmap.temp.tempId==15)>
				<#include "../index/newheader10.ftl">
			<#elseif (topmap.temp.tempId==16)>
				<#include "../index/newheader11.ftl">
			<#elseif (topmap.temp.tempId==17)>
				<#include "../index/newheader12.ftl">
			<#elseif (topmap.temp.tempId==18)>
				<#include "../index/newheader13.ftl">
			<#elseif (topmap.temp.tempId==19)>
				<#include "../index/newheader14.ftl">
            <#elseif (topmap.temp.tempId==20)>
                <#include "../index/newheader15.ftl">
			<#else>
				<#include "../index/newheader3.ftl">
			</#if>
		</#if>
    <div class="container">
        <div class="page_locate mt10">
       		 <input type="hidden" id="productId" value="${map.detailBean.productVo.goodsInfoId}" />
            <input type="hidden" class="bread_crumb_cat_id" value="${map.detailBean.productVo.goods.catId}" data-role="detail" />
        	<input type="hidden" class="first_catId" value="" />
        	<input type="hidden" class="index_url">
            &nbsp;&gt;&nbsp;
            <span>${map.detailBean.productVo.productName}</span>
        </div><!--/page_locate-->
        <div class="content clearfix mt10">
            <div class="right_wp fr">
                <div class="evaluation_info clearfix">
                    <div class="rate fl tc">
                        <strong  style="line-height: normal;"><span class="bigHaoPercent">95<span><b>%</b></strong>
                        <span>好评度</span>
                    </div><!--/rate-->
                    <div class="percent fl ml20">
                        <dl class="per_info clearfix">
                            <dt>好评<em class="per">(<b><span class="haoPercent"></span></b>)</em></dt>
                            <dd><div class="per_bar"><span class="haoPercentLine"></span></div></dd>
                        </dl>
                        <dl class="per_info clearfix">
                            <dt>中评<em class="per">(<b><span class="zhongPercent"></span></b>)</em></dt>
                            <dd><div class="per_bar"><span class="zhongPercentLine"></span></div></dd>
                        </dl>
                        <dl class="per_info clearfix">
                            <dt>差评<em class="per">(<b><span class="chaPercent"></span></b>)</em></dt>
                            <dd><div class="per_bar"><span class="chaPercentLine"></span></div></dd>
                        </dl>
                    </div><!--/percent-->
                    <div class="recommend_point fl">
                      	 <#if map.tags?size  &gt; 0>
	                              推荐点：
	                          <ul class="rec_points mt10 clearfix">
	                                <#list map.tags as tag>
	                                    <li>${tag.goodsTag.tagName}</li>
	                                </#list>
	                          </ul><!--/rec_points-->
	                     </#if>
                    </div><!--/recommend_point-->
                    <div class="publish_cmt fl">
                        <p>您可对已购的商品进行评价</p>
                        <a class="pb_cmt" target="_blank" href="${basePath}/customer/myorder.html">发表评论拿积分</a>
                        <#--<p>1积分=1元&nbsp;&nbsp;<a class="cmt_rule" href="javascript:;">评论规则</a></p>-->
                    </div><!--/publish_cmt-->
                </div><!--/evaluation_info-->
                <div class="det_title mt10 clearfix">
                    <ul class="cmt_tabs fl clearfix">
                        <li class="cur"><a href="javascript:;" class="commentTab" data-role="3">全部评论（<span class="allCount"></span>）</a></li>
                        <li><a href="javascript:;" class="commentTab" data-role="0">好评（<span class="haoCount"></span>）</a></li>
                        <li><a href="javascript:;" class="commentTab" data-role="1">中评（<span class="zhongCount"></span>）</a></li>
                        <li><a href="javascript:;" class="commentTab" data-role="2">差评（<span class="chaCount"></span>）</a></li>
                    </ul><!--/cmt_tabs-->
                </div><!--/det_title-->
                <div class="comment_wp">
                    <div class="comment_cont"  style="display:block">
                    	<div class="comment_list">
                            <!-- 评论主体 -->
                        </div>
                        <div class="clearfix mt10">
                            <div class="pages tr fr comment_pages">
                             </div><!--/pages-->
                        </div>
                    </div><!--/comment_cont-->
                </div><!--/comment_wp-->
            </div><!--/right_wp-->
            <div class="left_wp fl">
                <div class="left_box cmt_pro">
                    <h3>商品信息</h3>
                    <div class="gd_img tc">
                        <a href="${basePath}/item/${map.detailBean.productVo.goodsInfoId}.html" title="${map.detailBean.productVo.productName!''}" alt="${map.detailBean.productVo.productName!''}" target="_blank"><img class="lazy" title="${map.detailBean.productVo.productName!''}" alt="${map.detailBean.productVo.productName!''}" data-original="<#if map.detailBean.productVo.imageList?? && map.detailBean.productVo.imageList[0]??>${map.detailBean.productVo.imageList[0].imageBigName!''}</#if>" width="180" height="180" /></a>
                    </div><!--/gd_img-->
                    <div class="gd_name mt10">商品名称：<a  href="${basePath}/item/${map.detailBean.productVo.goodsInfoId}.html" title="${map.detailBean.productVo.productName!''}" alt="${map.detailBean.productVo.productName!''}" target="_blank">${map.detailBean.productVo.productName!''}</a></div>
                    <div class="gd_price">¥ ${map.detailBean.productVo.goodsInfoPreferPrice?string("0.00")}</div>
                    <div class="cmt_star">
                        评价得分：<div class="star_wp"><span class="star star_<#if map.detailBean.productVo.commentUtilBean??>${map.detailBean.productVo.commentUtilBean.score?substring(0,1)}<#else>0</#if>_0"></span></div> (<#if map.detailBean.productVo.commentUtilBean??>${map.detailBean.productVo.commentUtilBean.score?substring(0,1)}<#else>0</#if>分)
                    </div><!--/cmt_star-->
                    <div class="cmt_num">评论数：<#if map.detailBean.productVo.commentUtilBean??>${map.detailBean.productVo.commentUtilBean.count}<#else>0</#if>条</div>
                    <a class="add_cart"  product_id="${map.detailBean.productVo.goodsInfoId}" product_stock="${map.detailBean.productVo.goodsInfoStock}" distinct_id="${map.distinctId}" href="javascript:;">加入购物车</a>
                    <input  type="hidden" class="product_buy_num" value="1" />
                </div><!--/left_box-->
            </div><!--/left_wp-->
        </div><!--/content-->
    </div><!--/container-->
   		 <#if (topmap.temp)??>
			<#if (topmap.temp.tempId==1)>
				<#include "../index/bottom.ftl">
			<#else>
		   		 <#include "../index/newbottom.ftl" />
			</#if>
		</#if>
    <div class="side_tools">
        <a class="backtotop" href="javascript:;"><em>返回顶部</em><b></b></a>
    </div><!--/side_tools-->
    <!-- 提示框-->
	<#include "../infotips.ftl">
	<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/cloud-zoom.1.0.2.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/index.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/goods_comm.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/new_goods_detail.js"></script>
	<script type="text/javascript" src="${basePath}/js/comment/comment.js"></script>
</@htmlBody>
