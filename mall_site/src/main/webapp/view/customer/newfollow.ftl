<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!''}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的收藏-${topmap.systembase.bsetName}</title>
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
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
</head>-->
<@htmlBody>
<#--一引入头部 <#include "/index/topnew.ftl" />  -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==8)>
        <#include "../index/newtop3.ftl">
    <#elseif (topmap.temp.tempId==9)>
        <#include "../index/newtop4.ftl">
    <#elseif (topmap.temp.tempId==10)>
        <#include "../index/newtop7.ftl">
    <#elseif (topmap.temp.tempId==11)>
        <#include "../index/newtop6.ftl">
    <#elseif (topmap.temp.tempId==12)>
        <#include "../index/newtop7.ftl">
    <#elseif (topmap.temp.tempId==13)>
        <#include "../index/newtop8.ftl">
    <#elseif (topmap.temp.tempId==14)>
        <#include "../index/newtop9.ftl">
    <#elseif (topmap.temp.tempId==15)>
        <#include "../index/newtop8.ftl">
    <#elseif (topmap.temp.tempId==16)>
        <#include "../index/newtop11.ftl">
    <#elseif (topmap.temp.tempId==17)>
        <#include "../index/newtop12.ftl">
    <#elseif (topmap.temp.tempId==18)>
        <#include "../index/newtop13.ftl">
    <#elseif (topmap.temp.tempId==19)>
        <#include "../index/newtop14.ftl">
    <#elseif (topmap.temp.tempId==20)>
        <#include "../index/newtop15.ftl">
    <#else>
        <#include "../index/newtop.ftl"/>
    </#if>
</#if>
<#include "newtop.ftl"/>
        <div style="background: #f5f5f5;">
            <div class="container clearfix pt20 pb10">
        <#include "newleftmenu.ftl">
        <div class="new_member-right">
            <div class="new_order_list">
                <div class="n-title">我的收藏</div>
                <div class="tagMenu order-menu">
                    <ul class="menu clearfix">
                        <li  <#if pb??&&pb.url=="customer/myfollw">class="current"</#if> onclick="gohref('myfollw.html');"><em></em><span></span>我收藏的商品</li>
                        <!-- 2015.11.03 wuyanbo 屏蔽我收藏的商家 -->
                        <#--<li <#if pb??&&pb.url=="customer/sellermyfollw">class="current"</#if>onclick="gohref('sellermyfollw.html');"><em>&nbsp;</em><span>&nbsp;</span>我收藏的商家</li>-->
                    </ul>
                </div>
                <#--<div class="simple mt10 clearfix">-->
                    <#--<div class="fl">-->
                        <#--<select>-->
                            <#--<option>最近三个月</option>-->
                        <#--</select>-->
                    <#--</div>-->
                    <#--<div class="fr">-->
                        <#--<input type="text" class="auction" placeholder="商品名称、商品编号"/>-->
                        <#--<button>查询</button>-->
                    <#--</div>-->
                <#--</div>-->
                <div class="content">
                    <div class="layout">
                        <table class="bought-table mt10">
                            <thead>
                            <tr>
                                <th width="480">商品名称</th>
                                <th>网页反馈</th>
                                <th>价格</th>
                                <th>库存情况</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if pb.list??&&(pb.list?size!=0)>
                                <#list pb.list as follow>
                                    <#if follow.good??>
                                            <tr class="order-bd">
                                                <td class="baobei no-border-right">
                                                    <a href="#"onclick="checkgoods('${follow.goodsId}')" class="pic"><img width="100" height="100" alt="<#if follow.good.goodsName??>${follow.good.goodsName}</#if>" title="<#if follow.good.goodsName??>${follow.good.goodsName}</#if>"
                                                                                 src="<#if follow.good.goodsImg??>${follow.good.goodsImg}</#if>" /></a>
                                                    <div class="desc ml20">
                                                        <a class="name" target="_black" onclick="checkgoods('${follow.goodsId}')" title="<#if follow.good.goodsName??>${follow.good.goodsName}</#if>"> <#if follow.good.goodsName??>${follow.good.goodsName}</#if></a>
                                                        <p class="col9">
                                                            <#list follow.good.specVo as specVo>
                                                                <#if specVo.spec.specName??&&specVo.spec.specName == '.'>
                                                                <#else>
                                                                    ${specVo.spec.specName}:<#if specVo.specValueRemark??>${specVo.specValueRemark}<#else>${specVo.goodsSpecDetail.specDetailName!''} </#if>&nbsp;
                                                                </#if>
                                                            </#list>
                                                        </p>
                                                        <p>
                                                        <div class="put" style="margin-left: 0;">
                                                                <span class="commstar clearfix">

                                                                    <div class="star_wp" >
                                                                        <span class="star star_<#if  follow.utilBean??&&follow.utilBean.score??>${follow.utilBean.score?substring(0,1)}</#if>_0"></span>
                                                                    </div>
                                                                </span>
                                                        </div>
                                                        </p>
                                                    </div>

                                                </td>
                                                <td class="no-border">
                                                    <a onclick="checkComment('${follow.goodsId}')" target="_black">评论：<#if follow.good.commentCount??>${follow.good.commentCount}</#if>条</a>
                                                    <br/>
                                                    <a onclick="checkConsult('${follow.goodsId}')" target="_black">咨询：<#if follow.good.consultCount??>${follow.good.consultCount}</#if>条</a>
                                                </td>
                                                <td class="no-border">
                                                    <span class="red fb f14">￥<#if vip?? && vip == "1"><#if follow.good.goodsVipPrice??>${follow.good.goodsVipPrice?string('0.00')}</#if><#else><#if follow.good.goodsPrice??>${follow.good.goodsPrice?string('0.00')}</#if></#if></span>
                                                </td>
                                                <td class="no-border">
                                                    <#if follow.good.goodStock?? >
                                                        <#if (follow.good.goodStock>0) >
                                                            现货
                                                        <#else>
                                                            无货
                                                        </#if>
                                                    </#if>
                                                </td>
                                                <td class="no-border-left">
                                                    <a href="javascript:void(0)" class="buy-again" onclick="buy(this)">加入购物车</a>
                                                    <input type="hidden" value="${follow.goodsId}" /><br/>
                                                    <a href="javascript:void(0)" class="blue" onclick="cancelfollow2('${basePath}/customer/cancelfollow-${follow.followId}.html')">取消收藏</a>
                                                </td>
                                            </tr>
                                    </#if>
                                </#list>
                            <#else>
                            <tr>
                                <td colspan="5" style="font-size:18px; height:60px;text-align: center;">暂无收藏记录！</td>
                            </tr>
                            </#if>
                            </tbody>

                        </table>
                    <#if pb.list?? && (pb.list?size!=0)>
                    <#-- 分页 -->
                        <#import "../pagination/pageBean.ftl" as page>
                        <@page.pagination pb />
                    </#if>
                    </div>
                </div>
                <input type="hidden" value="${token!''}" id="hi_token" />
            </div>
        </div>
      </div>
</div>
        <div class="mask"></div>

        <div class="member-dialog dia2">
            <div class="member-dialog-body">
                <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
                <div class="tc">
                    <div class="que-delete clearfix">
                        <img src="${basePath}/images/images_l6.png"/>
                        <div class="fl tl">
                            <p class="f16 red">确定取消收藏该商品吗？</p>
                            <div class="m-btn mt20">
                                <a  href="javascript:" class="can-det">确定</a>
                                <a class="" href="javascript:cls();">取消</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="member-dialog dia3">
            <div class="member-dialog-body">
                <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
                <div class="tc">
                    <div class="que-delete clearfix">
                        <img src="${basePath}/images/images_l6.png"/>
                        <div class="fl tl">
                            <p class="f16 red">请重试...</p>
                            <div class="m-btn mt20">
                                <a  href="javascript:cls();">确定</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="member-dialog dia22">
            <div class="member-dialog-body">
                <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
                <div class="tc">
                    <div class="que-delete clearfix">
                        <img src="${basePath}/images/images_l6.png"/>
                        <div class="fl tl">
                            <p class="f16 red">系统错误,请重试!</p>
                            <div class="m-btn mt20">
                                <a  href="javascript:cls();">确定</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="member-dialog dia23">
            <div class="member-dialog-body">
                <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
                <div class="tc">
                    <div class="que-delete clearfix">
                        <img src="${basePath}/images/images_l6.png"/>
                        <div class="fl tl">
                            <p class="f14 red">该商品已下架，请选择其他商品</p>
                            <div class="m-btn mt20" style="text-align: center">
                                <a  href="javascript:cls();">确定</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

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
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<script type="text/javascript">
    function checkgoods(id){
        $.ajax({
            type:'post',
            url:'${basePath}/checkgoods.htm?goodsId='+id,
            async:false,
            error:function(request) {
                dia(22);
            },
            success:function(data){
                if(data == 1){
                    window.location.href="${basePath}/item/"+id+".html"
                }else{
                   dia(23);
                }
            }
        });
    }
    function checkbuy(id){
        $.ajax({
            type:'post',
            url:'${basePath}/checkgoods.htm?goodsId='+id,
            async:false,
            error:function(request) {
                dia(22);
            },
            success:function(data){
                if(data == 1){
                    $.get("../goods/addProductToShopCar.html?productId=" + $(obj).next().val()
                    + "&goodsNum=1", function(data) {
                        if (data) {
                            location.href = "../myshoppingcart.html";
                        } else {
                            dia(3);
                        }
                    });
                }else{
                    dia(23);
                }
            }
        });
    }
    function checkComment(id){
        $.ajax({
            type:'post',
            url:'${basePath}/checkgoods.htm?goodsId='+id,
            async:false,
            error:function(request) {
                dia(22);
            },
            success:function(data){
                if(data == 1){
                    window.location.href="${basePath}/item/"+id+".html#comment"
                }else{
                   dia(23);
                }
            }
        });
    }

    function checkConsult(id){
        $.ajax({
            type:'post',
            url:'${basePath}/checkgoods.htm?goodsId='+id,
            async:false,
            error:function(request) {
                dia(22);
            },
            success:function(data){
                if(data == 1){
                    window.location.href="${basePath}/item/"+id+".html#consult"
                }else{
                    dia(23);
                }
            }
        });
    }

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
	$(".guess_goods_list").jCarouselLite({
        btnNext: ".next",
        btnPrev: ".prev",
		visible : 6,
		auto : 2000,
		speed : 800
    });
    $(".pro_sort").addClass("pro_sort_close");
    $(".new_member_left div:eq(1) ul li:eq(2)").addClass("cur");
});

	function gohref(sd){
		window.location.href="${basePath}/customer/"+sd;
	}
    function cancelfollow2(url) {
        $(".can-det").prop("href", url+"?&CSRFToken="+$("#hi_token").val());
        dia(2);
    }
</script>
</@htmlBody>
