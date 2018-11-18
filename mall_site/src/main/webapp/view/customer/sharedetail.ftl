<#include "../include/common.ftl">
<@htmlHead title="晒单详情-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/share.base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/share.style.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>晒单详情-${topmap.systembase.bsetName}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="Keywords" content="${topmap.seo.meteKey}">
<meta name="description" content="${topmap.seo.meteDes}">
<#assign basePath=request.contextPath>
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/share.base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/share.style.css" />
</head>-->
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
	<div class="page_locate w1200 mt10 mb10">
        	<input type="hidden" class="bread_crumb_cat_id" value="${map.share.good.cateId}" data-role="detail" />
        	<input type="hidden" class="first_catId" value="" />
        	<input type="hidden" class="index_url">
            &nbsp;&gt;&nbsp;
            <span>${map.share.good.goodsName}</span>
        </div><!--/page_locate-->
<div class="w1200">
	<div class="page_right fr">
    	<div class="share_pic gray_border mb10">
        	<h2>[晒单]${map.share.shareTitle!"--"}</h2>
            <div class="body">
            	<div class="share_pic_box fr">
                	<div class="conner"></div>
                    <div class="word">
                    	<#assign contentLength=map.share.shareContent?length>
                    	<#list 1..contentLength as c><#if ((c_index+1)%55==0) ><br/></#if>${map.share.shareContent[c_index]}</#list>
                    	<#--<p>买了这个国产，我觉得太棒了！这个价格，能表现到这种配置，并且有国母代言。倍有面子！<br />拍照对于细节表现中肯，色彩还原度较高，锐度稍差！<br />机身发热比较厉害，不过还算过得去！<br />系统优化不足，玩节奏大师掉帧比较严重！<br />液晶可视角度很好。<br />整体来说是比较满意的一次购物！</p>-->
                    </div>
                    <div class="pic mb10">
                    	<div class="pic_thumb fr">
                        	<a class="pic_thumb_l" href="javascript:void(0);"></a>
                            <a class="pic_thumb_r" href="javascript:void(0);"></a>
                            <div class="pic_thumb_list">
                            	<ul>
                                	<#list map.share.imageList as img>
                                		<li><a href="javascript:void(0);"><img alt="" width="140px" height="90px" src="${img.imageName}" /></a></li>
                                	</#list>
                                </ul>
                            </div>
                        </div>
                        <div class="pic_show fl"><!-- 这个里面大图和上面的小图是一一对应的 -->
                        	<ul>
                            	<#list map.share.imageList as img>
	                            	<li style="background-image:url(${img.imageName});<#if img_index==0>display:block;</#if>">
	                                	<span class="pic_roll_l"></span>
	                                    <span class="pic_roll_r"></span>
	                                </li>
                            	</#list>
                            </ul>
                        </div>
                        <div class="cb"></div>
                    </div>
                    <div>
                    	<a class="reply" href="${basePath}/share/detail-${map.share.shareId}.html#anchor">回复</a>
                    	<span class="ml10">发表于${map.share.createTime?string('yyyy-MM-dd HH:mm:ss')}</span>
                    </div>
                </div><!-- /share_pic_box -->
                <div class="share_pic_user fl">
                	<p class="tc mb5"><img alt="" width="60px" height="60px" src="<#if map.share.customerHead??&&map.share.customerHead!=''>${map.share.customerHead!'#'}<#else>../images/user_img.gif</#if>" /></p>
                    <p class="tc">
                    	<#if map.share.customerName??>
                    		<#if map.share.customerName?length gt 6>
                    			${map.share.customerName?substring(0,6)}..
                    		<#else>
                    			${map.share.customerName}
                    		</#if>
                    	<#else>
                    		--
                    	</#if>
                    </p>
                </div><!-- /share_pic_user -->
                <div class="cb"></div>
            </div>
        </div><!-- /share_pic -->
        <#list map.share.replies as reply>
            <#if reply.isDisplay=='1'>
	        <div class="share_pic gray_border mb10">
	            <div class="body">
	            	<div class="share_pic_box fr">
	                	<div class="conner"></div>
	                    <div class="word">
	                    	<p>${reply.replyContent}</p>
	                    </div>
	                    <div>
	                    	<!--<a class="reply" href="#">回复</a>-->
	                        <span class="ml10" style="float:right;">发表于<#if reply.createTime??>${reply.createTime?string('yyyy-MM-dd HH:mm:ss')}</#if></span>
	                    </div>
	                </div><!-- /share_pic_box -->
	                <div class="share_pic_user fl">
                        <#if reply.customer??>
	                	<p class="tc mb5"><img alt="${reply.customer.customerUsername!''}" width="60" height="60" src="<#if reply.customer.customerImg??&&reply.customer.customerImg!=''>${reply.customer.customerImg!'#'}<#else>../images/user_img.gif</#if>" /></p>
	                    <p class="tc">
	                    	<#if reply.customer.customerNickname??>
	                    		<#if reply.customer.customerNickname?length gt 6>
	                    			${reply.customer.customerNickname?substring(0,6)}..
	                    		<#else>
	                    			${reply.customer.customerNickname}
	                    		</#if>
	                    	<#else>
	                    		--
	                    	</#if>
	                    </p>
                        <#else>
                            <p class="tc mb5"><img alt="${map.bs.bsetName!''}" width="60" height="60" src="${basePath}/images/admin.png" /></p>
                            <p class="tc">
                                <#if map.bs.bsetName??>
                                    <#if map.bs.bsetName?length gt 6>
                                    ${map.bs.bsetName?substring(0,6)}..
                                    <#else>
                                    ${map.bs.bsetName}
                                    </#if>
                                <#else>
                                    --
                                </#if>
                            </p>
                        </#if>
	                </div><!-- /share_pic_user -->
	                <div class="cb"></div>
	            </div>
	        </div><!-- /share_pic -->
            </#if>
        </#list>
        <input type="hidden" class="uf" value="<#if cust??>0<#else>1</#if>" />
        <#if cust??>
          <div class="reply_form gray_border">
	        	<h2>发表回复</h2>
	            <div class="body">
	            <a name="anchor"></a>
	            	<table width="100%">
	            	<input type="hidden" id="shareId" value="${map.share.shareId}"/>
	                	<tr>
	                    	<td width="20%" align="right" valign="top">内容</td>
	                        <td><textarea id="replyContent" class="text_area"></textarea></td>
	                    </tr>
	                    <tr>
	                    	<td></td>
	                        <td><input type="button" class="common_btn_red share_reply_btn" value="提交" /></td>
	                    </tr>
	                </table>
	            </div>
	        </div><!-- /reply_form -->
	    <#else>
	    	<a href="${basePath}/login.html">登录</a>后可回复!
        </#if>
     
    </div><!-- /page_right -->
    
    <div class="mask"></div>
    <div class="page_left fl">
    	<div class="good_side gray_border mb10">
        	<h2>商品详情</h2>
            <div class="body">
            	<a class="img" href="${basePath}/item/${map.share.goodsId}.html"><img alt="<#if map.share.good.goodsName??>${map.share.good.goodsName}</#if>" title="<#if map.share.good.goodsName??>${map.share.good.goodsName}</#if>"  src="<#if map.share.good.goodsImg??>${map.share.good.goodsImg}</#if>" width="180" height="180" /></a>
                <p class="name"><span>商品名称：</span><a class="" href="${basePath}/item/${map.share.goodsId}.html" style="color:#eb6122">${(map.share.good.goodsName)!'--'}</a></p>
                <p class="price fl">￥${(map.share.good.goodsPrice?string('0.00'))!'0.00'}</p>
                <div class="cb"></div>
                <div class="rate" style="width:auto;">
                	<span class="">评价得分：</span>
                    <div class="star_wp"><span class="star fl star_${(map.share.good.goodsScore)!'0'}"></span></div>
                    <span class="">(${(map.share.good.goodsScore)!'0'}分)</span>
                </div>
                <p>评论数：${(map.share.good.commentCount)!'0'}条</p>
                <a class="side_cart_btn" href="javascript:void(0)" onclick="buy(this)"></a>
                <input type="hidden" value="${map.share.goodsId}">
            </div>
        </div>
        <div class="side_topic gray_border mb10">
        	<h2>热门晒单</h2>
            <div class="body">
            	<ul>
                	<#list map.topShare as share>
                		<li><a href="${basePath}/share/detail-${share.shareId}.html">${share.shareTitle!'--'}</a></li>
                	</#list>
                </ul>
            </div>
        </div><!-- /side_topic -->
    </div><!-- /page_left -->
    <div class="cb"></div>
</div>
 <div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_err.png" />
            	<em id="con_00">回复信息中包含特殊字符！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    <div class="dialog s_dia dia3">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_war.png" />
            	<em id="con_00">系统检测到你未登录,请先登录！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
   <#--引入底部 <#include "/index/bottom.ftl" /> -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>

<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/sharedeatil.js"></script>
</@htmlBody>
