<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title><#if map.detailBean.productVo.goods.goodsSeoTitle?length &gt; 0> ${map.detailBean.productVo.goods.goodsSeoTitle!''}<#else>${map.detailBean.productVo.productName!''}</#if> 所有咨询-${topmap.systembase.bsetName}</title>
<meta name="description" content="${map.detailBean.productVo.goods.goodsSeoDesc!''}-${topmap.seo.meteDes}">
<meta name="Keywords" content="${map.detailBean.productVo.goods.goodsSeoKeyword}-${topmap.seo.meteKey}">
<#assign basePath=request.contextPath>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
</head>
<body>

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
        	<input type="hidden" class="bread_crumb_cat_id" value="${map.detailBean.productVo.goods.catId}" data-role="detail" />
        	<input type="hidden" class="first_catId" value="" />
        	<input type="hidden" class="index_url">
            &nbsp;&gt;&nbsp;
            <span>${map.detailBean.productVo.productName}</span>
        </div><!--/page_locate-->
    
       <div class="content clearfix mt10">
            <div class="right_wp fr">
                <div class="det_title clearfix">
                    <ul class="consult_tabs fl clearfix">
                        <li class="cur" data-role="0"><a href="javascript:;">全部购买咨询</a></li>
                        <li data-role="1"><a href="javascript:;">商品咨询</a></li>
                        <li data-role="2"><a href="javascript:;">库存配送</a></li>
                        <li data-role="3"><a href="javascript:;">支付</a></li>
                        <li data-role="4"><a href="javascript:;">发票及配送</a></li>
                        <li data-role="5"><a href="javascript:;">促销及赠品</a></li>
                    </ul><!--/consult_tabs-->
                </div><!--/det_title-->
                <div class="consult_box clearfix">
                    <div class="consult_search fl">
                        咨询前请先搜索，方便又快捷：
                        <div class="consult_input mt10 clearfix">
                            <input class="consult_text fl" type="text" placeholder="请输入咨询关键词" />
                            <input class="cs_search_btn fl" type="button" onclick="loadCommentAsk(1,null);" value="搜索" />
                        </div><!--/consult_input-->
                    </div><!--/consult_search-->
                    <div class="consult_prompt fl">
                        <b>温馨提示：</b>因厂家更改产品包装、产地或者更换随机附件等没有任何提前通知，且每位咨询者购买情况、提问时间等不同，
为此以下回复仅对提问者3天内有效，其他网友仅供参考！若由此给您带来不便请多多谅解，谢谢！
                    </div><!--/consult_prompt-->
                </div><!--/consult_box-->
                <div class="consult_wp">
                    <div class="consult_cont" style="display:block"> 
                         <div class="consult_list">
                         </div>
                        <div class="pages tr cosult_pages">
                        </div><!--/pages-->
                    </div><!--/consult_cont-->
                </div><!--/consult_wp-->
                
                <div class="issued_problem mt10">
                    <div class="is_tit clearfix">
						<a href="javascript:void(0)" name="pub"></a>
                        <h3 class="fl">发表咨询</h3>
                    </div><!--/is_tit-->
                    <div class="is_cont">
                        <p class="is_statement">声明：您可在购买前对产品包装、颜色、运输、库存等方面进行咨询，我们有专人进行回复！因厂家随时会更改一些产品的包装、颜色、产地等参数，所以该回复仅在当时对提问者有效，其他网友仅供参考！咨询回复的工作时间为：周一至周五，9:00至18:00，请耐心等待工作人员回复。</p>
                        <dl class="problem_input clearfix mt10">
                            <dt>咨询类型：</dt>
                            <dd>
                                <label class="mr10"><input class="vm mr10 ask_type" name="type" type="radio" checked=checked value="1" />商品咨询</label>
                                <label class="mr10"><input class="vm mr10 ask_type" name="type" type="radio" value="2" />库存配送</label>
                                <label class="mr10"><input class="vm mr10 ask_type" name="type" type="radio" value="3" />支付</label>
                                <label class="mr10"><input class="vm mr10 ask_type" name="type" type="radio" value="4" />发票及保修</label>
                                <label class="mr10"><input class="vm mr10 ask_type" name="type" type="radio" value="5" />促销及赠品</label>
                            </dd>
                            <dt>福气承诺：</dt>
                            <dd>商品均为原装正品行货，自带机打发票，严格执行国家三包政策，享受全国联保服务。</dd>
                            <dt>功能咨询：</dt>
                            <dd>咨询商品功能建议您拨打各品牌的官方客服电话，以便获得更准确的信息。</dd>
                            <dt>咨询内容：</dt>
                            <dd><textarea class="pb_txa askComment"></textarea></dd>
                            <dt>&nbsp;</dt>
                            <dd>
                                <input class="sub_pb ask_commit_btn" type="button" value="提交" />
                                <label class="ml10 none"><input class="vm mr10" type="checkbox" />将客服的回复发到我的邮箱</label>
                            </dd>
                        </dl><!--/problem_input-->
                    </div><!--/is_cont-->
                </div><!--/issued_problem-->
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
                    <a class="add_cart"  product_id="${map.detailBean.productVo.goodsInfoId}" product_stock="${map.detailBean.productVo.goodsInfoStock}" distinct_id="${map.distinctId}" href="javascript:;"><i></i>加入购物车</a>
                    <input  type="hidden" class="product_buy_num" value="1" />
                    <input type="hidden" id="productId" value="${map.detailBean.productVo.goodsInfoId}" />
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
    <!-- 对比页面 -->
	<#include "../goods/compare_box.ftl">
	<!-- 提示框-->
	<#include "../infotips.ftl">
    <div class="side_tools">
        <a class="backtotop" href="javascript:;"><em>返回顶部</em><b></b></a>
    </div><!--/side_tools-->
    <script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/cloud-zoom.1.0.2.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/index.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/goods_comm.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/new_goods_detail.js"></script>
	<script type="text/javascript" src="${basePath}/js/comment/cosult.js"></script>
</body>
</html>