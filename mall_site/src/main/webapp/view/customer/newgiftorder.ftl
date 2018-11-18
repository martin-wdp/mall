<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!''}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的订单-${topmap.systembase.bsetName}</title>
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
            <#include "newleftmenu.ftl" />
                <div class="new_member-right">
                    <div class="new_order_list">
                        <div class="n-title">积分兑换订单</div>
                        <div class="tagMenu order-menu">
                            <ul class="gift_tabs menu clearfix">
                                <li <#if (!type?? || type == '0') >class="current"</#if> data-val="0"><a href="javascript:;" >所有订单</a></li>
                                <li <#if (type?? && type == '1')>class="current"</#if> data-val="1"><a href="javascript:;" >待发货订单</a></li>
                                <li <#if (type?? && type == '2')>class="current"</#if> data-val="2"><a href="javascript:;" >待收货订单</a></li>
                                <li <#if (type?? && type == '3')>class="current"</#if> data-val="3"><a href="javascript:;" >已完成订单</a></li>
                            </ul>
                        </div>
                        <div class="simple mt10 clearfix">
                        <#if (!type?? || type != '6')>
                            <div class="filter_l fl">
                                <select class="select s1 gifts">
                                    <option value="0">近一个月订单</option>
                                    <option value="1">一个月前订单</option>
                                </select>
                                <input type="hidden" id="hi_date" value="${date!'0'}" />
                                <input type="hidden" id="hi_type" value="${type!'5'}" />
                            <#--<select class="select s2 ss">
                                <option value="5">所有订单</option>
                                <option value="0">待处理订单</option>
                                <option value="3">已完成订单</option>
                                <option value="4">已取消订单</option>
                            </select>-->
                            </div>
                        <#else>
                            <input class="select s1 ss" type="hidden" id="hi_date" value="0" />
                        </#if>
                        <#--<div class="fr">-->
                        <#--<input type="text" class="auction" placeholder="商品名称、商品编号"/>-->
                        <#--<button>查询</button>-->
                        <#--</div>-->
                        </div>
                        <div class="content ">
                            <div class="layout">
                                <table class="bought-table mt10">
                                    <thead>
                                    <tr>
                                        <th width="420">订单信息</th>
                                        <th>收货人</th>
                                        <th>兑换积分</th>
                                        <th>订单状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <#if pb.list??&&(pb.list?size!=0)>
                                        <#list pb.list as order>
                                            <tbody>
                                            <tr class="sep-row">
                                                <td colspan="5"></td>
                                            </tr>
                                            <tr class="order-hd">
                                                <td class="first">
                                                    <span>订单编号: ${order.giftOrderCode!''}</span>
                                                    <span>下单时间：<#if order.createTime??>
                                                    ${order.createTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}
                                                    </#if></span>
                                                </td>
                                                <td colspan="4">
                                                    <div class="fr mr20">

                                                            <#--<a href="javascript:;"  onclick="showDialogs('${order.backOrderId }');"><img src="${basePath}/images/delete1.png" width="11" height="15"/></a>-->
                                                       <#---->

                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="order-bd">
                                                <td class="baobei">

                                                        <div style="height:110px;">
                                                            <img class="pic" title="${(order.giftName)!''}" alt="${(good.goodsName)!''}" src="<#if order.giftPicList?? &&  order.giftPicList?size &gt; 0 >${(order.giftPicList[0].picBig)!''}</#if>"  width="100" height="100" />
                                                            <div class="desc ml20">
                                                                <a href="#" class="name">${order.giftName!''}</a>
                                                                <p class="col9">
                                                                    <#--<#list good.specVo as specVo>-->
                                                                    <#--${specVo.spec.specName}:${specVo.goodsSpecDetail.specDetailName!''} &nbsp;-->
                                                                    <#--</#list>-->
                                                                </p>
                                                            </div>

                                                        </div>
                                                </td>
                                                <td>
                                                    <#if order.shoppingPerson??>
													${order.shoppingPerson}
												</#if>
                                                </td>
                                                <td>
                                                    <#if order.orderIntegral??>
														${(order.orderIntegral)!'0'}
													</#if>
                                                </td>
                                                <td>
                                                    <a href="#" class="red">
                                                         <#if order.giftOrderStatus??>
                                                            <#if order.giftOrderStatus=="0">
                                                                未发货
                                                            <#elseif order.giftOrderStatus=="1">
                                                                已发货
                                                            <#elseif order.giftOrderStatus=="2">
                                                                已完成
                                                            </#if>
                                                        </#if>
                                                     </a>
                                                    <br/>
                                                    <a  style="display:initial;" target="_blank" href="${basePath}/customer/giftorderdetail-${(order.giftOrderId)!''}.html">查看订单</a>

                                                </td>
                                                <td>
                                                    <#if order.giftOrderStatus=="1">
                                                        <a class="buy-again" href="javascript:void(0)" onclick="comfirmgift('${basePath}/customer/comfirmofgiftorder-giftorder-${(order.giftOrderId)!''}.html')"  >确认收货</a>
                                                    </#if>
                                                </td>
                                            </tr>
                                            </tbody>

                                        </#list>
                                    <#else>
                                        <tr>
                                            <td colspan="5" style="font-size:18px; height:60px;text-align: center;">暂无订单！</td>
                                        </tr>
                                    </#if>

                                </table>
                            </div>

                        </div>
                    <#if pb.list?? && (pb.list?size!=0)>
                    <#-- 分页 -->
                        <#import "../pagination/pageBean.ftl" as page>
                        <@page.pagination pb />
                    </#if>
                    </div>

                </div>
            </div>
        </div>

        <div class="mask"></div>
        <div class="member-dialog dia3">
            <div class="member-dialog-body">
                <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
                <div class="tc">
                    <div class="que-delete clearfix">
                        <img src="${basePath}/images/images_l6.png"/>
                        <div class="fl tl">
                            <p class="f16 red">确定确认收货吗？</p>
                            <p >小心钱货两空哦！</p>
                            <div class="m-btn mt20">
                                <a  id="go_pay_01" href="javascript:;">确定</a>
                                <a  href="javascript:cls();">取消</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>



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
                $(".guess_goods_list").jCarouselLite({
                    btnNext: ".next",
                    btnPrev: ".prev",
                    visible : 6,
                    auto : 2000,
                    speed : 800
                });
                $(".new_member_left div:eq(1) ul li:eq(1)").addClass("cur");
                $(".pro_sort").addClass("pro_sort_close");

                jQuery.fn.isChildAndSelfOf = function(b){ return (this.closest(b).length > 0); };
                $(".mn_sel").click(function(){
                    $(this).next(".selCont").show();
                    $(".sel_txa").hide();
                    $(".err_tip").hide();
                    $(".input_tip").hide();
                    $(document).click(function(event){
                        if(!$(event.target).isChildAndSelfOf(".mn_sel, .selCont")) {
                            $(".selCont").hide();
                            if( $("#other_yy").prop("checked")){
                                $(".sel_txa").show();
                            }
                        };
                    });
                });
                $(".selCont input").click(function(){
                    $(".mn_sel").html($(this).val());
                    $("#rea_hid").val($(this).val());
                    $(".selCont").hide();
                    $(".sel_txa").hide();
                });
                $("#other_yy").click(function(){
                    $(".sel_txa").show();
                });
            });
        </script>
</@htmlBody>
