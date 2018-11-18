<#include "../include/common.ftl">
<@htmlHead title="我的订单-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
<style>
    .cart-top{top:5px!important;}
</style>
</@htmlHead>
<#--<html lang="en">
<head>
    <meta charset="UTF-8">
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
    <link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
    <style>
        .cart-top{top:5px!important;}

    </style>

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
                <div class="n-title">我的订单</div>
                <div class="tagMenu order-menu">
                    <ul class="ods_tabs menu clearfix">
                        <li<#if (!type?? || type == '5') > class="current" </#if> data-val="5"><a href="javascript:" >所有订单</a></li>
                        <li<#if (type?? && type == '0')> class="current"</#if> data-val="0"><a href="javascript:" >待处理订单</a></li>
                        <li<#if (type?? && type == '3')> class="current"</#if> data-val="3"><a href="javascript:" >已完成订单</a></li>
                        <li<#if (type?? && type == '4')> class="current"</#if> data-val="4"><a href="javascript:" >已取消订单</a></li>
                        <li <#if (type?? && type == '6')>class="current"</#if> data-val="6"><a href="javascript:" >已退货订单</a></li>
                    </ul>
                </div>
                <div class="simple mt10 clearfix">
                <#if (!type?? || type != '6')>
                    <div class="filter_l fl">
                        <select class="select s1 ss">
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
                <div class="content">
                    <div class="layout">
                        <#if ( type?? && type == '6')>
                        <table class="bought-table mt10">
                            <thead>
                            <tr>
                                <th width="410">退单信息</th>
                                <th width="120">订单单号</th>
                                <th>退单金额</th>
                                <th>实退金额</th>
                                <th>退单状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                        <#if pb.list??&&(pb.list?size!=0)>
                            <#list pb.list as backorder>
                                <tbody>
                                <tr class="sep-row">
                                    <td colspan="6"></td>
                                </tr>
                                <tr class="order-hd">
                                    <td class="first">
                                        <span>退单编号: ${backorder.backOrderCode!''}</span>
                                    <span>退单时间：<#if backorder.backTime??>
                                    ${backorder.backTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}
                                    </#if></span>
                                    </td>
                                    <td colspan="5">
                                        <div class="fr mr20">
                                            <#if backorder.backCheck=="2" || backorder.backCheck=="4" ||backorder.backCheck=="10">
                                                <a href="javascript:"  onclick="showDialogs_back('${backorder.backOrderId }');"><img src="${basePath}/images/delete1.png" width="11" height="15"/></a>
                                            </#if>

                                        </div>
                                    </td>
                                </tr>
                                <tr class="order-bd">
                                    <td class="baobei">
                                        <#list backorder.orderGoodslistVo as good>
                                            <div style="height:110px;">
                                                <a target="_blank" class="pic" title="${good.goodsInfoName!''}" href="${basePath}/item/${good.goodsInfoId}.html"><img width="100" height="100" title="${(good.goodsInfoName)!''}" alt="${(good.goodsInfoName)!''}" src="${(good.goodsInfoImgId)!''}" /></a>
                                                <div class="desc ml20">
                                                    <a target="_blank" href="${basePath}/item/${good.goodsInfoId}.html" class="name">${good.goodsInfoName!''}</a>
                                                    <p class="col9">
                                                        <#list good.specVo as specVo>
                                                            <#if specVo.spec.specName??&&specVo.spec.specName == '.'>
                                                            <#else>
                                                                ${specVo.spec.specName}:<#if specVo.specValueRemark??>${specVo.specValueRemark}<#else>${specVo.goodsSpecDetail.specDetailName!''} </#if>&nbsp;
                                                            </#if>
                                                        </#list>
                                                    </p>
                                                </div>
                                            </div>
                                        </#list>
                                    </td>
                                    <td>
                                         <#if backorder.orderCode??>
                                           ${backorder.orderCode}
                                         </#if>
                                    </td>
                                    <td>
                                        <font color="red" style="font-family: '微软雅黑'">￥
                                            <#if backorder.backPrice??>
                                            ${backorder.backPrice?string('0.00')}
                                            </#if>
                                        </font>
                                    </td>
                                    <td>
                                    <span class="red fb f14">
                                        <#if backorder.businessId?? && backorder.businessId == 0>
                                            <#if backorder.order?? &&backorder.order.backPrice??>
                                                ￥${backorder.order.backPrice?string('0.00')}
                                            </#if>
                                        <#else>
                                            ￥${backorder.backPrice?string('0.00')}
                                        </#if></span><br/>
                                    <#--<span class="col9"><#if order.orderLinePay??>-->
                                    <#--<#if order.orderLinePay=="1">-->

                                    <#--在线支付-->

                                    <#--<#elseif order.orderLinePay=="0">-->

                                    <#--货到付款-->

                                    <#--</#if>-->
                                    <#--</#if></span>-->
                                    </td>
                                    <td>
                                        <span  class="red">
                                            <#if backorder.backCheck??>
                                                <#if backorder.backCheck=="0">
                                                    退货审核
                                                <#elseif backorder.backCheck=="1">
                                                    同意退货
                                                <#elseif backorder.backCheck=="2">
                                                    拒绝退货
                                                <#elseif backorder.backCheck=="3">
                                                    待商家收货
                                                <#elseif backorder.backCheck=="4">
                                                    退单结束
                                                <#elseif backorder.backCheck=="6">
                                                    退款审核
                                                <#elseif backorder.backCheck=="7">
                                                    拒绝退款
                                                <#elseif backorder.backCheck=="8">
                                                    拒绝收货
                                                <#elseif backorder.backCheck=="9">
                                                    待填写物流地址
                                                <#elseif backorder.backCheck=="10">
                                                    退款成功
                                                </#if>
                                            </#if>
                                        </span><br/>
                                        <#--<a  target="_blank" href="${basePath}/customer/detail-${order.orderId}.html" class="blue">查看订单</a>-->
                                    </td>
                                    <td>
                                    <#if backorder.backCheck=="6" || backorder.backCheck=="10" >
                                        <a class="buy-again" href="${basePath}/customer/backdetailprice.htm?orderId=<#if backorder?? && backorder.order??>${backorder.order.orderId!''}</#if>">退款详情</a><br/>
                                    <#else>
                                         <a class="buy-again" href="${basePath}/customer/backdetail.htm?orderId=<#if backorder?? && backorder.order??>${backorder.order.orderId!''}</#if>">退货详情</a><br/>
                                    </#if>

                                    </td>
                                </tr>
                                </tbody>

                            </#list>
                            <#else>
                                <tr>
                                    <td colspan="6" style="font-size:18px; height:60px;text-align: center;">暂无订单！</td>
                                </tr>
                            </#if>
                        </table>

                    <#else>
                    <table class="bought-table mt10">
                        <thead>
                        <tr>
                            <th width="420">订单信息</th>
                            <th>收货人</th>
                            <th>订单状态</th>
                            <th>支付金额</th>
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
                                        <span>订单编号: ${order.orderNo!''}</span>
                                    <span>下单时间：<#if order.addTime??>
                                    ${order.addTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}
                                    </#if></span>
                                    </td>
                                    <td colspan="4">
                                        <div class="fr mr20">
                                            <#if order.orderStatus=='3' || order.orderStatus=='4' ||order.orderStatus=='18' >
                                                <a href="javascript:"  onclick="showDialogs('${order.orderId}');"><img src="${basePath}/images/delete1.png" width="11" height="15"/></a>
                                            </#if>

                                        </div>
                                    </td>
                                </tr>
                                <tr class="order-bd">
                                    <td class="baobei">
                                        <#assign cFlag=0 />
                                        <#list order.goods as good>
                                            <#if good.evaluateFlag== '0'>
                                                <#assign cFlag=cFlag+1 />
                                            </#if>
                                            <#if order.orderStatus??>
                                                <div style="height:110px;">
                                                    <a target="_blank" class="pic" title="${good.goodsName!''}" href="${basePath}/item/${good.goodsId}.html"><img width="100" height="100" title="${(good.goodsName)!''}" alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}" /></a>
                                                    <div class="desc ml20">
                                                        <a href="${basePath}/item/${good.goodsId}.html" class="name">${good.goodsName!''}<#if  good.backFlag??&&good.backFlag=='1'><span style="color:red;">(已退货)</span></#if></a>
                                                        <p class="col9">
                                                            <#list good.specVo as specVo>
                                                                <#if specVo.spec.specName??&&specVo.spec.specName == '.'>
                                                                <#else>
                                                                    ${specVo.spec.specName}:<#if specVo.specValueRemark??>${specVo.specValueRemark}<#else>${specVo.goodsSpecDetail.specDetailName!''} </#if>&nbsp;
                                                                </#if>
                                                            </#list>
                                                        </p>
                                                    </div>
                                                </div>
                                            </#if>

                                        </#list>
                                    </td>
                                    <td>
                                        <#if order.shippingPerson??>
                                        ${order.shippingPerson}
									</#if>
                                    </td>
                                    <td>
                                        <span href="#" class="red">
                                            <#if order.orderStatus??>
                                                <#if order.orderStatus=="0">
                                                    <#if order.orderLinePay=="0">
                                                        待发货
                                                    <#else>
                                                        待付款
                                                    </#if>
                                                <#elseif (order.orderStatus=="1" || order.orderStatus=="5" || order.orderStatus=="6") >
                                                    已付款未发货
                                                <#elseif order.orderStatus=="2">
                                                    <#if order.orderExpressType??&&order.orderExpressType=='1'>
                                                        待自提
                                                    <#else>
                                                        已发货
                                                    </#if>
                                                <#elseif order.orderStatus=="3">
                                                    已完成
                                                <#elseif order.orderStatus=="4">
                                                    已取消
                                                <#elseif order.orderStatus=="7">
                                                    退货审核中
                                                <#elseif order.orderStatus=="8">
                                                    同意退货
                                                <#elseif order.orderStatus=="9">
                                                    拒绝退货
                                                <#elseif order.orderStatus=="10">
                                                    待商家收货
                                                <#elseif order.orderStatus=="11">
                                                    退货结束
                                                <#elseif order.orderStatus=="15">
                                                    退款审核中
                                                <#elseif order.orderStatus=="13">
                                                    拒绝退款
                                                <#elseif order.orderStatus=="14">
                                                    已提交退货审核
                                                <#elseif order.orderStatus=="16">
                                                    商家收货失败
                                                <#elseif order.orderStatus=="17">
                                                    已退款
                                                <#elseif order.orderStatus=="18">
                                                    退款成功
                                                </#if>
                                            </#if>
                                        </span><br/>
                                        <a  target="_blank" href="${basePath}/customer/detail-${order.orderId}.html" class="blue">查看订单</a>
                                    </td>
                                    <td>
                                    <span class="red fb f14">￥ <#if order.moneyPaid??>
                                    ${order.moneyPaid?string('0.00')}
                                    </#if></span><br/>
                                    <span class="col9"><#if order.orderLinePay??>
                                        <#if order.orderLinePay=="1">

                                            在线支付

                                        <#elseif order.orderLinePay=="0">

                                            货到付款

                                        </#if>
                                    </#if></span>
                                    </td>
                                    <td>
                                        <#if (order.orderStatus=="11") >
                                            <a class="buy-again" href="${basePath}/customer/backdetail.htm?orderId=${order.orderId}">退货详情</a><br/>
                                        </#if>

                                        <#if ( order.orderStatus=="5" || order.orderStatus=="6") >
                                            <a  class="buy-again"  href="${basePath}/customer/applybackmoney.htm?orderId=${order.orderId}">申请退货</a><br/>
                                        </#if>

                                        <#if (order.orderStatus=="1") >
                                            <a  class="buy-again"  href="${basePath}/customer/applybackmoneyprice.htm?orderId=${order.orderId}">申请退款</a><br/>
                                        </#if>

                                        <#if order.orderStatus=="0">
                                            <#if order.orderLinePay='1'>
                                                <a class="buy-again"  target="_blank" href="${basePath}/gopayorder-${order.orderId}.html">去付款</a><br/>
                                            </#if>
                                             <a class="buy-again"  href="javascript:void(0)" onclick="cancelOrder('${basePath}/customer/cancelorder-myorder-${order.orderId}.html')">取消订单</a><br/>
                                        <#elseif order.orderStatus=="2">
                                            <a class="buy-again"  href="javascript:void(0)" onclick="comfirmgoods('${basePath}/customer/comfirmofgoods-myorder-${order.orderId}.html')"  >确认收货</a><br/>
                                        </#if>
                                        <#if isBackOrder==1>
                                            <#if (order.orderStatus=="3" && cFlag>0) >
                                                <a  style="display:initial;" target="_blank" href="${basePath}/comment-${order.orderId}.html">评论晒单</a><br/>
                                                <a  class="buy-again"  href="${basePath}/customer/applybackmoney.htm?orderId=${order.orderId}">申请退货</a><br/>
                                            </#if>
                                            <#if (order.orderStatus=="3" && cFlag==0) >
                                                <#if (order.orderStatus=="3" && order.shareFlag >0)>
                                                    <a  style="display:initial;" target="_blank" href="${basePath}/comment-${order.orderId}.html">评论晒单</a><br/>
                                                </#if>
                                                <a  class="buy-again"  href="${basePath}/customer/applybackmoney.htm?orderId=${order.orderId}">申请退货</a><br/>
                                            </#if>
                                            <#if (order.orderStatus=="8") >
                                                <a  class="buy-again"  href="javascript:void(0)" onclick="setwuliu(${order.orderNo})">填写物流信息</a><br/>
                                            </#if>
                                            <#if (

                                                order.orderStatus=="7" ||
                                                order.orderStatus=="8" ||
                                                order.orderStatus=="9" ||
                                                order.orderStatus=="10" ||
                                                order.orderStatus=="14" ||
                                                order.orderStatus=="16" ||
                                                order.orderStatus=="17" ||
                                                order.orderStatus=="12"
                                                )
                                            >
                                                <a class="buy-again" href="${basePath}/customer/backdetail.htm?orderId=${order.orderId}">退货详情</a><br/>
                                            </#if>

                                            <#if (order.orderStatus=="13" ||
                                                  order.orderStatus=="15" ||
                                                  order.orderStatus=="18")>
                                                <a class="buy-again" href="${basePath}/customer/backdetailprice.htm?orderId=${order.orderId}">退款详情</a><br/>
                                            </#if>
                                        <#elseif isBackOrder==2>
                                            <#if (order.orderStatus=="3" && cFlag>0) >
                                                <a  style="display:initial;" target="_blank" href="${basePath}/comment-${order.orderId}.html">评论晒单</a><br/>
                                            </#if>
                                            <#if (order.orderStatus=="3" && order.shareFlag >0)>
                                                <a  style="display:initial;" target="_blank" href="${basePath}/comment-${order.orderId}.html">评论晒单</a><br/>
                                            </#if>
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

<#--引入底部 <#include "/index/bottom.ftl" />  -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
    <#else>
        <#include "../index/newbottom.ftl" />
    </#if>
</#if>

</div>

<div class="mask"></div>
<div class="member-dialog dia3">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red">确定确认收货吗？</p>
                    <p >小心钱货两空哦！</p>
                    <div class="m-btn mt20">
                        <a  id="go_pay_01" href="javascript:">确定</a>
                        <a class="" href="javascript:cls();">取消</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="member-dialog promotion_dialog_1">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red">您确定要删除该订单吗？</p>
                    <p >删除后，您将无法恢复！</p>
                    <div class="m-btn mt20">
                        <a href="javascript:"onclick="clsd()">确定</a>
                        <a onclick="cls()">取消</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="member-dialog promotion_dialog_2">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red">您确定要删除该订单吗？</p>
                    <p >删除后，您将无法恢复！</p>
                    <div class="m-btn mt20">
                        <a href="javascript:" onclick="deleteBackOrder()">确定</a>
                        <a onclick="cls()">取消</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<!--退货物流-->
<div class="member-dialog big-dialog dia15">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <div style=" padding-top: 10px; padding-left:20px; font-size: 12px; font-weight:bold;">亲！请填写物流信息!</div>
                    <input type="hidden" id="orderNo" name="orderNo" value=""/>
                    <table style="height:100px;margin-top:20px; margin-left:10px;" id="backtable">
                        <tr>
                            <td>*物流公司：</td>
                            <td><input type="text" maxlength="20" onBlur="wuliuname()" style=" border-radius:3px;" name="wlname" id="wlname"/></td>
                            <td class="yanzhengname">&nbsp;&nbsp;请填写正确的物流公司！</td>
                        <tr/>
                        <tr>
                            <td>*物流单号：</td>
                            <td>
                                <input type="text" maxlength="20" onBlur="wuliudanhao()" style=" border-radius:3px;" name="wlno" id="wlno"/>
                            </td>
                            <td class="yanzhengno" width="172px">&nbsp;&nbsp;单号必须正确有效的数字！</td>
                        <tr/>
                        <tr>
                            <td colspan="3"><img src='${basePath}/images/gantanhao_1.gif'/>注：物流公司信息必须真实有效！</td>
                        </tr>
                    </table>
                    <div class="m-btn mt20">
                        <a   id="go_pay_01" href="javascript:" onclick="quedingwl('00');" style="margin-left:80px;" >确定</a>
                        <a href="javascript:cls();" id="go_pay_00" onclick="quxiaowuliu()">取消</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<div class="member-dialog dia2 big-dialog">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
        <div class="pl20 mt30 clearfix">
            <em class="fl">取消订单原因：</em>
            <div class="fl">
                <div class="s-mn_sel mn_sel">发票信息有误/发票未开</div>
                <div class="s-selCont selCont">
                    <label><input class="vm mr5" name="res" type="radio" value="现在不想买">现在不想买</label>
                    <label><input class="vm mr5" name="res" type="radio" value="商品价格较贵">商品价格较贵</label>
                    <label><input class="vm mr5" name="res" type="radio" value="价格波动">价格波动</label>
                    <label><input class="vm mr5" name="res" type="radio" value="商品缺货">商品缺货</label>
                    <label><input class="vm mr5" name="res" type="radio" value="重复下单">重复下单</label>
                    <label><input class="vm mr5" name="res" type="radio" value="添加或删除商品">添加或删除商品</label>
                    <label><input class="vm mr5" name="res" type="radio" value="收货人信息有误">收货人信息有误</label>
                    <label><input class="vm mr5" name="res" type="radio" value="发票信息有误/发票未开">发票信息有误/发票未开</label>
                    <label><input class="vm mr5" name="res" type="radio" value="送货时间过长">送货时间过长</label>
                    <label><input class="vm mr5" name="res" id="other_yy" type="radio" value="其他原因">其他原因</label>
                </div>
                <div class="s-err_tip">请选择取消原因!</div>
                <div class="s-input_tip">请输入10个以上字符!</div>
                <div class="m-btn mt20 pb10">
                    <a  id="go_pay_00" href="javascript:" onclick="changeUrl();">确定</a>
                    <a href="javascript:cls();">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<script>
    $(".s-mn_sel").click(function(){
        $(this).next(".s-selCont").show();
        $(".s-sel_txa").hide();
        $(".err_tip").hide();
        $(".input_tip").hide();
        $(document).click(function(event){
            if(!$(event.target).isChildAndSelfOf(".s-mn_sel, .s-selCont")) {
                $(".s-selCont").hide();
                if( $("#other_yy").prop("checked")){
                    $(".s-sel_txa").show();
                }
            }
        });
    });
    $(".s-selCont input").click(function(){
        $(".s-mn_sel").html($(this).val());
        $("#rea_hid").val($(this).val());
        $(".s-selCont").hide();
        $(".s-sel_txa").hide();
    });
    $("#other_yy").click(function(){
        $(".s-sel_txa").show();
    });
</script>
<script type="text/javascript">
    $(".s2 option[value='"+$("#hi_type").val()+"']").prop("selected","selected");
    $(".s1 option[value='"+$("#hi_date").val()+"']").prop("selected","selected");
    $(document).ready(function(){

        //防止多次点击确认收货而获得更多积分奖励
        $(".mysure").click(function(){
            var _this=$(this);
            setTimeout(function(){
                _this.attr("href","javascript:;");
            },0);
        });
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
        $(".new_member_left div:eq(1) ul li:eq(0)").addClass("cur");
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
                }
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

    //退货弹窗  取消
    function quxiaokuan(){
        $(".dialog").fadeOut();
        $(".mask").fadeOut();
        $(".err_yuanyin").addClass("black");
        $(".err_yuanyin").html("<img src='../images/gantanhao_1.gif'/>收件原因不能超过100个字！");

    }

    var id = '';
    var backOrderId = '';
    function showDialogs(orderId) {
        id = orderId;
        $(".mask").fadeIn();
        $(".promotion_dialog_1").fadeIn();
    }

    //删除退货弹出框
    function showDialogs_back(backId) {
        backOrderId = backId;
        $(".mask").fadeIn();
        $(".promotion_dialog_2").fadeIn();
        $("#dialog_tip_back").html("确定要永久删除？");
    }

    /*删除退货*/
    function deleteBackOrder(){
        jQuery.ajax({
            type: 'post',
            url: 'deleteBackOrderById.htm?backOrderId='+backOrderId,
            success:function(data) {
                clsc();
                if(data>0)
                    location.reload();
            }
        });
    }

    function clsd(){
        jQuery.ajax({
            type: 'post',
            url: 'deleteOrderById.htm?orderId='+id,
            success:function(data) {

                clsc();
                if(data>0)
                    location.reload();
            }
        });
    }

    function clsc() {
        $(".dialog").fadeOut();
        $(".mask").fadeOut();
    }

    function ShowUmsMessage(orderId) {
        $.ajax({
            url:'queryContainerRelations.htm?orderId='+orderId,
            success:function(data) {
                for(var i=0;i<data.length;i++) {
                    $("#track_"+orderId).attr("src",data[i].expressInfoUrl);
                }
            }
        });
        $("#track_box_"+orderId).show();
    }

    function CloseUmsMessage(orderId){
        $("#track_box_"+orderId).hide();
    }
</script>
</@htmlBody>