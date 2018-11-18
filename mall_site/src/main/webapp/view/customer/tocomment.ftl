<#include "../include/common.ftl">
<@htmlHead title="商品评价-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<style>
    .text_area{width:600px;height:100px;border-top:1px solid #ABADB3;border-left:1px solid #ABADB3;border-bottom:1px solid #E3E9EF;border-right:1px solid #E3E9EF;padding:5px;}
    .star {float:none!important; display:inline-block!important; zoom:1; *display:inline;}
    .err {color:red;}
    .common_form dl{width:710px;}
    body,div,ul,li,p{margin:0;padding:0;}
    body{color:#666;font:12px/1.5 Arial;}
    ul{list-style-type:none;}
    .stars{position:relative;margin:10px auto; padding-left:120px; height:25px; width:100%;}
    .stars ul,.stars span{float:left;display:inline;height:25px;line-height:25px;}
    .stars span.left{width:130px; text-align:right;}
    .stars ul{margin:0 10px;}
    .stars li{float:left;width:24px;cursor:pointer;text-indent:-9999px;background:url(${basePath}/images/commentstar.png) no-repeat;}
    .stars strong{color:#f60;padding-left:10px;}
    .stars li.on{background-position:0 -28px;}
    .stars p{position:absolute; z-index:999;top:20px;width:159px;height:60px;display:none;background:url(${basePath}/images/icon.gif) no-repeat;padding:7px 10px 0;}
    .stars p em{color:#f60;display:block;font-style:normal;}
</style>

<script type="text/javascript">
    var oStaraLi,oUl,oSpan,oP;
    var i = iScore = iStar = 0;
    function commentstar(obj){
        oStar = document.getElementById(obj);
        aLi = oStar.getElementsByTagName("li");
        oUl = oStar.getElementsByTagName("ul")[0];
        oSpan = oStar.getElementsByTagName("span")[1];
        oP = oStar.getElementsByTagName("p")[0];

        if(obj == 'star1'){
            var aMsg = [
                "很不满意|差得太离谱，与卖家描述的严重不符，非常不满",
                "不满意|部分有破损，与卖家描述的不符，不满意",
                "一般|质量一般，没有卖家描述的那么好",
                "满意|质量不错，与卖家描述的基本一致，还是挺满意的",
                "非常满意|质量非常好，与卖家描述的完全一致，非常满意"
            ]
        }else if(obj=='star2'){
            var aMsg = [
                "很不满意|卖家态度很差，还骂人、说脏话，简直不把顾客当回事",
                "不满意|卖家有点不耐烦，承诺的服务也兑现不了",
                "一般|卖家回复问题很慢，态度一般，谈不上沟通顺畅",
                "满意|卖家服务挺好的，沟通挺顺畅的，总体满意",
                "非常满意|卖家的服务太棒了，考虑非常周到，完全超出期望值"
            ]
        }else{
            var aMsg = [
                "很不满意|再三提醒下，卖家超过一天才发货，耽误我的时间",
                "不满意|卖家发货有点慢的，催了几次终于发货了",
                "一般|卖家发货速度一般，提醒后才发货的",
                "满意|卖家发货还算及时",
                "非常满意|卖家发货速度非常快"
            ]
        }

        for (i = 1; i <= aLi.length; i++)
        {
            aLi[i - 1].index = i;
            //鼠标移过显示分数
            aLi[i - 1].onmouseover = function ()
            {
                oStar = document.getElementById(obj);
                aLi = oStar.getElementsByTagName("li");
                oUl = oStar.getElementsByTagName("ul")[0];
                oSpan = oStar.getElementsByTagName("span")[1];
                oP = oStar.getElementsByTagName("p")[0];
                fnPoint(this.index,aLi);
                //浮动层显示
                oP.style.display = "block";
                //计算浮动层位置
                oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 104 + "px";
                //匹配浮动层文字内容
                oP.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1]
            };
            //鼠标离开后恢复上次评分
            aLi[i - 1].onmouseout = function ()
            {
                oStar = document.getElementById(obj);
                aLi = oStar.getElementsByTagName("li");
                oUl = oStar.getElementsByTagName("ul")[0];
                oSpan = oStar.getElementsByTagName("span")[1];
                oP = oStar.getElementsByTagName("p")[0];
                fnPoint();
                //关闭浮动层
                oP.style.display = "none"
            };
            //点击后进行评分处理
            aLi[i - 1].onclick = function ()
            {
                oStar = document.getElementById(obj);
                aLi = oStar.getElementsByTagName("li");
                oUl = oStar.getElementsByTagName("ul")[0];
                oSpan = oStar.getElementsByTagName("span")[1];
                oP = oStar.getElementsByTagName("p")[0];
                sinput =  oStar.getElementsByTagName("input")[0];
                iStar = this.index;
                oP.style.display = "none";
                oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")"
                sinput.value=this.index;
            }
        }

    }
    //评分处理
    function fnPoint(iArg)
    {
        //分数赋值
        iScore = iArg || iStar;
        for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";
    }
</script>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>
<base href="${basePath}/">
<title>商品评价-${topmap.systembase.bsetName}</title>
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
<style>
	.text_area{width:600px;height:100px;border-top:1px solid #ABADB3;border-left:1px solid #ABADB3;border-bottom:1px solid #E3E9EF;border-right:1px solid #E3E9EF;padding:5px;}
	.star {float:none!important; display:inline-block!important; zoom:1; *display:inline;}
	.err {color:red;}
	.common_form dl{width:710px;}
	body,div,ul,li,p{margin:0;padding:0;}
	body{color:#666;font:12px/1.5 Arial;}
	ul{list-style-type:none;}
	.stars{position:relative;margin:10px auto; padding-left:120px; height:25px; width:100%;}
	.stars ul,.stars span{float:left;display:inline;height:25px;line-height:25px;}
	.stars span.left{width:130px; text-align:right;}
	.stars ul{margin:0 10px;}
	.stars li{float:left;width:24px;cursor:pointer;text-indent:-9999px;background:url(${basePath}/images/commentstar.png) no-repeat;}
	.stars strong{color:#f60;padding-left:10px;}
	.stars li.on{background-position:0 -28px;}
	.stars p{position:absolute; z-index:999;top:20px;width:159px;height:60px;display:none;background:url(${basePath}/images/icon.gif) no-repeat;padding:7px 10px 0;}
	.stars p em{color:#f60;display:block;font-style:normal;}
</style>

<script type="text/javascript"> 
var oStaraLi,oUl,oSpan,oP;
var i = iScore = iStar = 0;
function commentstar(obj){
     oStar = document.getElementById(obj);
	 aLi = oStar.getElementsByTagName("li");
	 oUl = oStar.getElementsByTagName("ul")[0];
	 oSpan = oStar.getElementsByTagName("span")[1];
	 oP = oStar.getElementsByTagName("p")[0];
	
	if(obj == 'star1'){
	var aMsg = [
				"很不满意|差得太离谱，与卖家描述的严重不符，非常不满",
				"不满意|部分有破损，与卖家描述的不符，不满意",
				"一般|质量一般，没有卖家描述的那么好",
				"满意|质量不错，与卖家描述的基本一致，还是挺满意的",
				"非常满意|质量非常好，与卖家描述的完全一致，非常满意"
				]
	}else if(obj=='star2'){
	   var aMsg = [
				"很不满意|卖家态度很差，还骂人、说脏话，简直不把顾客当回事",
				"不满意|卖家有点不耐烦，承诺的服务也兑现不了",
				"一般|卖家回复问题很慢，态度一般，谈不上沟通顺畅",
				"满意|卖家服务挺好的，沟通挺顺畅的，总体满意",
				"非常满意|卖家的服务太棒了，考虑非常周到，完全超出期望值"
				]
	}else{
	var aMsg = [
				"很不满意|再三提醒下，卖家超过一天才发货，耽误我的时间",
				"不满意|卖家发货有点慢的，催了几次终于发货了",
				"一般|卖家发货速度一般，提醒后才发货的",
				"满意|卖家发货还算及时",
				"非常满意|卖家发货速度非常快"
				]
	}
	
	for (i = 1; i <= aLi.length; i++)
	{
		aLi[i - 1].index = i;
		//鼠标移过显示分数
		aLi[i - 1].onmouseover = function ()
		{
	     oStar = document.getElementById(obj);
		 aLi = oStar.getElementsByTagName("li");
		 oUl = oStar.getElementsByTagName("ul")[0];
		 oSpan = oStar.getElementsByTagName("span")[1];
		 oP = oStar.getElementsByTagName("p")[0];
			fnPoint(this.index,aLi);
			//浮动层显示
			oP.style.display = "block";
			//计算浮动层位置
			oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 104 + "px";
			//匹配浮动层文字内容
			oP.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1]
		};
		//鼠标离开后恢复上次评分
		aLi[i - 1].onmouseout = function ()
		{
		 oStar = document.getElementById(obj);
		 aLi = oStar.getElementsByTagName("li");
		 oUl = oStar.getElementsByTagName("ul")[0];
		 oSpan = oStar.getElementsByTagName("span")[1];
		 oP = oStar.getElementsByTagName("p")[0];
			fnPoint();
			//关闭浮动层
			oP.style.display = "none"
		};
		//点击后进行评分处理
		aLi[i - 1].onclick = function ()
		{
		 oStar = document.getElementById(obj);
		 aLi = oStar.getElementsByTagName("li");
		 oUl = oStar.getElementsByTagName("ul")[0];
		 oSpan = oStar.getElementsByTagName("span")[1];
		 oP = oStar.getElementsByTagName("p")[0];
		 sinput =  oStar.getElementsByTagName("input")[0];
			iStar = this.index;
			oP.style.display = "none";
			oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")"
		   sinput.value=this.index;
		}
	}

}
	//评分处理
	function fnPoint(iArg)
	{
		//分数赋值
		iScore = iArg || iStar;
		for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";	
	}
	
	
</script>
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
		<div class="container">
		<input type="hidden" value="${token!''}" id="hi_token" />
	<div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <a href="${basePath}/customer/myorder.html">订单中心</a><span>&gt;</span>
            <span>商品评价</span>
    </div>
	<#include "leftmenu.ftl" />
    <div class="member_right fl ml10">
    	<div class="memebr_title mb20">
                    <h2 class="f14 fb">商品评价</h2>
        </div>
        <div class="evaluate_goods">
        <#if order??>
        	<table class="common_table">
            	<thead>
                	<tr>
                    	<th width="50">商品信息</th>
                        <th width="10%">购买时间</th>
                        <th width="10%">评价</th>
                    </tr>
                </thead>
                <tbody>
                <#if order.goods?size!=0>
	                <#list order.goods as good>
	                	<tr>
	                    	<td>
	                        	<a class="img fl" target="_blank" href="${basePath}/item/${good.goodsId}.html"><img alt="${good.goodsName}" src="${good.goodsImg}" width="60" height="60" /></a>
	                            <p class="name fl ml10"><a target="_blank" href="${basePath}/item/${good.goodsId}.html">
	                            <#if good.goodsName?length gt 18>
	                        				${good.goodsName?substring(0,18)}
	                            <#else>
	                        				${good.goodsName} 
	                        	</#if>
	                            </a></p>
	                        </td>
	                        <td>
	                        	<p><#if order.addTime??>${order.addTime?string("yyyy-MM-dd")}</#if></p>
	                        	<p><#if order.addTime??>${order.addTime?string("HH:mm:ss")}</#if></p>
	                        </td>
	                        <td>
	                        	<div class="evaluate_box">
	                        		<#if good.evaluateFlag?? && good.evaluateFlag=='1'>
	                            		<a target="_blank" href="${basePath}/item/${good.goodsId}.html#comment" >已评价 </a>
	                        		</#if>
	                        		<#if !good.evaluateFlag?? || good.evaluateFlag=='0'>
	                            	<a class="evaluate_link" href="javascript:void(0);" data-gid="${(good.goodsId)!''}" data-oid="${(order.orderId)!''}" >评价</a>
	                                <div class="evalate_cont">
	                                	<div class="coner"></div>
	                                	<div class="common_form">
	                                	<form id="commForm${(good.goodsId)!''}" action="${basePath}/addgoodscomment.htm?CSRFToken=${token}" method="post">
					                       
					                        <dl>
					                            <dt><#--<em>*</em>-->标&nbsp; &nbsp; &nbsp; 签：</dt>
					                            <dd id="g_tag${(good.goodsId)!''}">
					                            <#--<#if tagList?? >
					                            	<#if tagList?size != 0>
						                            	<#list tagList as tag>
						                            		<label><input type="checkbox" name="commentTag" value="${tag.goodsTag.tagName}"  class="vm mr5"/><strong>${tag.goodsTag.tagName}</strong></label>
						                            	</#list>
						                            <#else>
						                            	&nbsp;
					                            	</#if>
					                            <#else>
					                            	&nbsp;
					                            </#if>-->
					                            </dd>
					                        </dl>
					                        <div id="star1" class="stars des" >
					                           <input type="hidden" id="commentScore" name="commentScore" value="" />
											    <span class="left"><em style="color: red;font-size: 14px">* </em>商品描述评分：</span>
											    <ul>
											        <li onmouseover="commentstar('star1')"><a href="javascript:;">1</a></li>
											        <li onmouseover="commentstar('star1')"><a href="javascript:;">2</a></li>
											        <li onmouseover="commentstar('star1')"><a href="javascript:;">3</a></li>
											        <li onmouseover="commentstar('star1')"><a href="javascript:;">4</a></li>
											        <li onmouseover="commentstar('star1')"><a href="javascript:;">5</a></li>
											    </ul>
											    <span></span>
											    <p></p>
											</div>
											<#--<div id="star2" class="stars" >-->
											   <#--<input type="hidden" id="sattitudeScore" name="sattitudeScore" value="" />-->
											    <#--<span class="left">服务态度评分：</span>-->
											    <#--<ul>-->
											       <#--<li onmouseover="commentstar('star2')"><a href="javascript:;">1</a></li>-->
											       <#--<li onmouseover="commentstar('star2')"><a href="javascript:;">2</a></li>-->
											       <#--<li onmouseover="commentstar('star2')"><a href="javascript:;">3</a></li>-->
											       <#--<li onmouseover="commentstar('star2')"><a href="javascript:;">4</a></li>-->
											       <#--<li onmouseover="commentstar('star2')"><a href="javascript:;">5</a></li>-->
											    <#--</ul>-->
											    <#--<span></span>-->
											    <#--<p></p>-->
											<#--</div>-->
											<#--<div id="star3" class="stars" >-->
											  <#--<input type="hidden" id="dgoodsScore" name="dgoodsScore" value="" />-->
											    <#--<span class="left">发货速度评分：</span>-->
											    <#--<ul>-->
											       <#--<li onmouseover="commentstar('star3')"><a href="javascript:;">1</a></li>-->
											       <#--<li onmouseover="commentstar('star3')"><a href="javascript:;">2</a></li>-->
											       <#--<li onmouseover="commentstar('star3')"><a href="javascript:;">3</a></li>-->
											       <#--<li onmouseover="commentstar('star3')"><a href="javascript:;">4</a></li>-->
											       <#--<li onmouseover="commentstar('star3')"><a href="javascript:;">5</a></li>-->
											    <#--</ul>-->
											    <#--<span></span>-->
											    <#--<p></p>-->
											<#--</div>-->
					                        <dl>
					                           <dt><em style="color: red;font-size: 14px;">* </em>评论内容：</dt>
					                            <dd>
					                                <textarea id="complaincon${(good.goodsId)!''}" name="commentContent" class="w500 h80"></textarea>
					                                <label id="commTip${(good.goodsId)!''}">&nbsp;</label>
					                            </dd>
					                        </dl>
					                        <dl>
	                                        	<dt></dt>
	                                            <dd>
	                                            	<input type="button" class="common_btn_red "  onclick="checkComment(${(good.goodsId)!''});"  value="评价" order_goods_id="${good.goodsId}" />
	                                            </dd>
	                                            <input name="goodsId" value="${(good.goodsId)!''}" type="hidden" />
							                    <input name="customerId" value="${customerId}" type="hidden" />
							                    <input name="orderId" value="${(order.orderId)!''}" type="hidden" />
	                                        </dl>
	                                        </form>
	                                    </div>
	                                </div>
	                                </#if>
	                            </div>
	                        </td>
	                    </tr>
	                    </#list>
	                    <input type="hidden" id="orderId" value="${order.orderId}"/>
                    <#else>
                    	 <tr>
	                         <td colspan="3" style="text-align:center;">暂无可晒单商品！</td>
	                     </tr>
                    </#if>
                </tbody>
            </table>
            <#else>
            	<div class="reg_success">
			    	<div class="notice2">
			        	<img alt="" src="${basePath}/images/failed.png">参数异常！<span>
			        </div>
			        <div class="notice3">
			            <strong><span id="time">5</span>秒自动进入<a href="${basePath}/index.html">“首页”</a></strong></span> 
			        </div>
			    </div>
            </#if>
        </div><!-- /evaluate_goods -->  <!-- /common_form --><!-- /evaluate_box -->
        <div class="prompt mt20">
        	<h2>评价</h2>
             <div class="body">
            	<h4>评价说明：</h4>
            		<#if explain??>
			            <#list "${explain.content}"?split("|") as con>
						<p>${con_index + 1}.${con}</p>
						</#list>
			       	</#if>
               <#-- <p>1.服务评分用来对本次购物过程中的商城服务进行评价</p>
                <p>2.商品评价资格有效期为订单完成后半年内，服务评分资格有效期为订单完成后三个月内。</p>-->
            </div>
        </div><!-- /prompt -->
    </div>
    <div class="cb"></div>
</div>
<div class="mask"></div>
    <div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/mod_war.png" />
            	<em>该商品已经评价!</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" href="javascript:location.reload();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
<#--引入底部 <#include "/index/bottom.ftl" /> -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/member.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/goodcomm.js"></script>
<script type="text/javascript">
$(".s2 option[value='"+$("#hi_type").val()+"']").prop("selected","selected"); 
$(".s1 option[value='"+$("#hi_date").val()+"']").prop("selected","selected"); 
$(document).ready(function(){
	$('.item_title').each(function(){
		$(this).click(function(){
			$(this).next().toggle('fast',function(){
				if($(this).is(':visible')){
					$(this).prev().removeClass('up');
					$(this).prev().addClass('down');
				}
				else{
					$(this).prev().removeClass('down');
					$(this).prev().addClass('up');
				}
			});
		});
	});
    $(".memeber_left div:eq(0) ul li:eq(0)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");
});
setTimeout(countDown, 1000);
	function countDown(){
		var time = $("#time").text();
		$("#time").text(time - 1);
		if (time == 1) {
			location.href='${basePath}/index.html';
		} else {
			setTimeout(countDown, 1000);
		}
	}
</script>
</@htmlBody>
