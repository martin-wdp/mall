<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!''}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
<style>
    .container{margin:0px auto !important;}
</style>
</@htmlHead>
<#--<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>会员中心-${topmap.systembase.bsetName}</title>
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
    <style>
        .container{margin:0px auto !important;}
    </style>
</head>
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>-->
<@htmlBody>
<#--一引入头部 <#include "/index/topnew.ftl" />  -->
<#include "../index/newtop7.ftl">
<#include "newtop.ftl"/>
<div style="background: #f5f5f5;">
    <div class="container clearfix pt20 pb20">
        <#include "newleftmenu.ftl" />
      <!--new_member_left-->
            <iframe name="hidden_frame" style="display:none"></iframe>
        <div class="new_member-right">
            <div class="mod-main clearfix">
                <div class="mod-main-left fl clearfix">
                    <div class="mod_img fl">
                        <form id="upload_form${customer.customerId}" name="upload_form" method="post" enctype="multipart/form-data" action="${basePath}/uploadimg.htm?customerId=${customer.customerId}" target="hidden_frame">
                        <img src="<#if customer.customerImg??>
							<#if customer.customerImg==''>
							    ${basePath}/images/miu.jpg
							<#else>
								${(customer.customerImg)!''}
							</#if>
						<#else>
							 ${basePath}/images/miu.jpg
						</#if>" width="106" height="106" id="customer_imgs"/>
                        <p><a class="upload_file">修改头像</a></p>
                        <input type="file" class="upload_file" id="imageFile" name="shareFile" customer_id="${customer.customerId}" style="width:100px;height:100px;margin-top:-100px;filter:alpha(opacity=0);-moz-opacity:0.5;-khtml-opacity: 0;opacity: 0;position:relative;z-index:999;">
                       </form>
                    </div>
                    <div class="mod-sugar fl ml10">
                        <div class="title"> <#if customer.customerNickname??>
                            <#if customer.customerNickname=='0'>
                            <#else>
                            ${customer.customerNickname}
                            </#if>
                        </#if>
                            </b>，欢迎您！</div>
                        <p class="mt10">${customer.pointLevelName!''}</p>
                        <div class="mod-safe mt10">
                            账户安全：<div class="n_per_bar"><span style="width:<#if  cust.isEmail == '0' &&  cust.isMobile == '0'>33%<#elseif (cust.isEmail == '1' &&  cust.isMobile == '0') || (cust.isEmail == '0' &&  cust.isMobile == '1')>66%<#elseif cust.isEmail == '1' &&  cust.isMobile == '1'>100%</#if> "></span></div><span style="color: #71b247" class="pl20"><#if  cust.isEmail == '0' &&  cust.isMobile == '0'>低<#elseif (cust.isEmail == '1' &&  cust.isMobile == '0') || (cust.isEmail == '0' &&  cust.isMobile == '1')>一般<#elseif cust.isEmail == '1' &&  cust.isMobile == '1'>高级</#if></span>
                        </div>
                    </div>
                </div>
                <div class="mod-main-mid fl">
                    <ul class="clearfix">
                        <li>
                            <a
                            <#if notice.onpayNum != 0>
                                    class="active" target="_blank" href="${basePath}/customer/myorder-0-0-1.html"
                            <#else>
                                    href="javascript:void(0)"
                            </#if>
                            ><i><img src="${basePath}/images/sc_01.png" width="50" height="50"/></i><br/>
                            待付款<span>${notice.onpayNum!'0'}</span></a>
                        </li>
                        <li>
                            <a
                            <#if notice.onReceiptNum != 0>
                                    class="active" target="_blank" href="${basePath}/customer/myorder-0-0-1.html"
                            <#else>
                                    href="javascript:void(0)"
                            </#if>
                                    >
                            <i><img src="${basePath}/images/sc_02.png" width="50" height="50"/></i><br/>
                            待收货<span>${notice.onReceiptNum!'0'}</span></a>
                        </li>
                        <li>
                            <a
                            <#if notice.onMyNum != 0>
                                    class="active" target="_blank" href="${basePath}/customer/myorder-0-0-1.html"
                            <#else>
                                    href="javascript:void(0)"
                            </#if>
                                    >
                            <i><img src="${basePath}/images/sc_03.png" width="50" height="50"/></i><br/>
                            待自提<span>${notice.onMyNum!'0'}</span></a>
                        </li>
                        <li>
                            <a
                            <#if notice.commentNum != 0>
                                    class="active" target="_blank" href="${basePath}/customer/myorder-0-3-1.html"
                            <#else>
                                    href="javascript:void(0)"
                            </#if>
                                    >
                            <i><img src="${basePath}/images/sc_04.png" width="50" height="50"/></i><br/>
                            待评价<span>${notice.commentNum!'0'}</span></a>
                        </li>
                    </ul>
                </div>
                <div class="mod-main-rig">
                    <dl class="clearfix">
                        <dt>我的关注：</dt>
                        <dd>
                            <a<#if notice.reduceNum != 0>
                                class="active" target="_blank" href="${basePath}/customer/myfollw.html"
                        <#else>
                                href="javascript:void(0)"
                        </#if>
                                >降价商品（${notice.reduceNum!'0'}）</a></dd>
                        <dd>
                            <a
                        <#if notice.activityGoodsNum != 0>
                                class="active" target="_blank" href="${basePath}/customer/myfollw.html"
                        <#else>
                                href="javascript:void(0)"
                        </#if>
                                >收藏商品（${notice.activityGoodsNum!'0'}）</a></dd>
                    </dl>
                    <dl class="clearfix">
                        <dt>消息中心：</dt>
                        <dd>
                            <a <#if notice.noReadInsideNum != 0>
                                    class="active" href="/customer/insideletter.html"
                            <#else>
                                    href="javascript:void(0)"
                            </#if>
                                    >提醒通知（${notice.noReadInsideNum!'0'}）</a></dd>
                        <dd>
                            <a
                            <#if notice.noReadNum != 0>
                                    class="active" href="/customer/consult.html"
                            <#else>
                                    href="javascript:void(0)"
                            </#if>
                                    >咨询回复（${notice.noReadNum!'0'}）</a></dd>
                    </dl>
                    <dl class="clearfix">
                        <dt>我的积分：</dt>
                        <dd><a class="active" target="_blank" href="${basePath}/customer/myintegral.html"><b>${customer.infoPointSum!'0'}</b>个积分</a></dd>
                    </dl>
                    <dl class="clearfix">
                        <dt>优惠券：</dt>
                        <dd><a
                        <#if couponNum != 0>
                                class="active" target="_blank" href="${basePath}/mycoupon/1.html"
                        <#else>
                                href="javascript:void(0)"
                        </#if>
                                >${couponNum}张</a></dd>
                    </dl>
                </div>
            </div>
            <div class="mod-order mt20">
                <div class="tc-title clearfix">
                    <a href="${basePath}/customer/myorder.html">查看全部订单</a>我的订单
                </div>
                <div class="content pl20 pr20 pb20">
                    <div class="layout">
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
                    <#if (customer.orders?size!=0)>
                        <#list customer.orders as order>
                        <#if (order_index<4)>
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
                                        <#if order.orderStatus=='3' || order.orderStatus=='4'>
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
                                    <div style="height:110px;">
                                        <a target="_blank" class="pic" title="${good.goodsName!''}" href="${basePath}/item/${good.goodsId}.html"><img width="100" height="100" title="${(good.goodsName)!''}" alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}" /></a>
                                        <div class="desc ml20">
                                            <a href="#" class="name">${good.goodsName!''}<#if  good.backFlag??&&good.backFlag=='1'><span style="color:red;">(已退货)</span></#if></a>
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
                                    <#if order.address??>
                                        ${order.address.addressName}
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
                                        <a class="buy-again"  href="javascript:void(0)" onclick="cancelOrder('${basePath}/customer/cancelorder-myorder-${order.orderId}.html')">取消订单</a><br/>
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
                                        <#if (order.orderStatus=="14" || order.orderStatus=="17" || order.orderStatus=="10" || order.orderStatus=="9" ||  order.orderStatus=="16" || order.orderStatus=="8")>
                                            <a class="buy-again" href="${basePath}/customer/backdetail.htm?orderId=${order.orderId}">退货详情</a><br/>
                                        </#if>

                                        <#if (order.orderStatus=="13" || order.orderStatus=="18")>
                                            <a class="buy-again"" href="${basePath}/customer/backdetailprice.htm?orderId=${order.orderId}">退款详情</a><br/>
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
                        </#if>
                     </#list>
                    <#else>
                        <tr>
                            <td colspan="5" style="font-size:18px; height:60px;text-align:center;">暂无订单！</td>
                        </tr>
                    </#if>
                    </table>
                </div>
            </div>
            </div>
            <div class="borwsing-history mt20">
                <div class="title">浏览记录</div>
                <div class="his-srcoll" id="proScroll_a">
                    <#if browses??&&browses?size!=0>
                            <div  class="new-proscoll">
                                <ul>
                                        <#list browses as browse>
                                                <li>
                                                    <a target="_blank" title="${(browse.goods.goodsName)!''}" href="${basePath}/item/${browse.goodsId}.html"><img src="<#if browse.goods??><#if browse.goods.goodsImg??> ${(browse.goods.goodsImg)!''}</#if></#if>" alt="${(browse.goods.goodsName)!''}" title="${(browse.goods.goodsName)!''}" width="215" height="215" >

                                                        <p>
                                                            <#if browse?? && browse.goods?? >
                                                                 <#if browse.goods.goodsName??&&((browse.goods.goodsName)?length>13)>
                                                                     ${(browse.goods.goodsName)?substring(0,13 )}
                                                                <#else>
                                                                    ${(browse.goods.goodsName)!''}
                                                                </#if>
                                                            </#if>

                                                        </p>
                                                        <p class="red">￥<#if browse?? && browse.goods??>${browse.goods.goodsPrice?string('0.00')}</#if></p>
                                                    </a>
                                                </li>
                                        </#list>

                                </ul>
                            </div>
                            <a class="j-prev" href="javascript:"></a>
                            <a class="j-next" href="javascript:"></a>
                    <#else>
                       <div  style="margin-top:10px;border:1px #e8e8e8 solid; height:60px; text-align: center; font-size: 18px;line-height:60px;;">
                            暂无浏览信息！
                       </div>
                     </#if>
                </div>
            </div><!--borwsing-history-->
        </div>
    </div>
</div>
<input type="hidden" value="${token!''}" id="hi_token" />
<div class="mask"></div>
<div class="member-dialog dia4">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red" id="titleerr">头像上传成功！</p>

                    <div class="m-btn mt20">
                        <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
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
                                <input type="text" maxlength="20" onBlur="wuliudanhao()" style=" border-radius:3px;" name="wlno" id="wlno"
                                       onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',
						  clipboardData.getData('text').replace(/[^\d]/g,''))"
                                        />
                            </td>
                            <td class="yanzhengno" width="172px">&nbsp;&nbsp;请填写正确的物流单号！</td>
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
<#--引入底部 <#include "/index/bottom.ftl" />  -->
<#include "../index/newbottom.ftl" />
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/uploadImg.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<script>
    if($("#proScroll_a").length > 0) {
        $(".new-proscoll").jCarouselLite({
            btnNext: ".j-next",
            btnPrev: ".j-prev",
            auto: 3600,
            speed: 500,
            visible: 4,
            onMouse: true,
            scroll: 4
        });
    }
</script>
<script type="text/javascript">
    $(".pro_sort").addClass("pro_sort_close");
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
            speed : 800,
            onMouse: true
        });

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

    var id = '';
    function showDialogs(orderId) {
        id = orderId;
        $(".mask").fadeIn();
        $(".promotion_dialog_1").fadeIn();
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


    //退货弹窗  取消
    function quxiaokuan(){
        $(".dialog").fadeOut();
        $(".mask").fadeOut();
        $(".err_yuanyin").html("<img src='../images/gantanhao_1.gif'/>收件原因不能超过100个字！");
        $(".err_yuanyin").addClass("black");
        $(".err_tuikuan").addClass("black");
        $(".err_tuikuan").removeClass("red");
    }
</script>
</@htmlBody>