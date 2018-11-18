<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!''}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .inputSearch {box-sizing: content-box;}
    .mn_sel {display:inline-block;zoom:1;*display:inline;border:1px solid #ddd;width:205px;height:25px;line-height:25px;padding:0 5px;vertical-align:middle;background:url(${basePath}/images/area_arrow.gif) no-repeat 195px center;}
    .selCont {width:330px;min-height:50px;border:1px solid #ddd;padding:10px;margin-top:-4px;display:none;}
    .selCont label {display:inline-block;zoom:1;*display:inline;width:145px;margin:0 10px 10px 0;}
    .sel_txa {width:340px;height:100px;padding:5px;border:1px solid #ddd;display:none;margin-top:5px;}
    #backtable tr {
        padding-top:5px;
    }
    .red{color:red;}
    .black{color:black;}
    .dialog {position:fixed; background:#fff; z-index:9999; width:300px; min-height:100px!important; padding:0 0 10px!important; border:5px solid #f7f7f7; display:none;}
    .dia_tit {height:30px; line-height:30px; padding:0 20px; font-family:microsoft YaHei; font-size:14px; color:#fff;background: #eb6122;}
    .dia_close {width:15px; height:15px; background:url(../images/dia_close.png) no-repeat; margin-top:7px;}
    .dia_intro{height:auto!important; text-align:left; padding-left:50px; padding-top:20px!important;}
    .info_ok {display:inline-block!important; zoom:1; *display:inline; width:100px; height:28px; text-align:center; line-height:27px; font-family:microsoft YaHei; font-size:14px; color:#fff!important; margin:0 5px;}
    .info_ok {background:url(../images/org_btn.gif) no-repeat;}
    .goods {  width: 150px;}
    .genz{ position: relative;cursor: pointer; display: inline-block;}
    .genz_con{ background: #fff; position: absolute; width: 445px; padding: 10px; top:0; right:35px; padding-top: 1px; z-index: 98; display: none;}
    .genz_con table {border:none;}
    .genz_con table tr th{ text-align: left; font-weight: bold;}
    .genz_con table tr td{border:none; text-align: left; font-weight: 400;}
    .genz_con .close{display: block; font-size: 18px;text-align: right; padding-bottom: 5px;}
    .genz_bg{ position: absolute; top: 5px; right: 28px; z-index: 99; width: 8px; height: 10px; display: none;}
</style>
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
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .inputSearch {box-sizing: content-box;}
	.mn_sel {display:inline-block;zoom:1;*display:inline;border:1px solid #ddd;width:205px;height:25px;line-height:25px;padding:0 5px;vertical-align:middle;background:url(${basePath}/images/area_arrow.gif) no-repeat 195px center;}
	.selCont {width:330px;min-height:50px;border:1px solid #ddd;padding:10px;margin-top:-4px;display:none;}
	.selCont label {display:inline-block;zoom:1;*display:inline;width:145px;margin:0 10px 10px 0;}
	.sel_txa {width:340px;height:100px;padding:5px;border:1px solid #ddd;display:none;margin-top:5px;}
	#backtable tr {   
		padding-top:5px;
	}
	.red{color:red;}
	.black{color:black;}
    .dialog {position:fixed; background:#fff; z-index:9999; width:300px; min-height:100px!important; padding:0 0 10px!important; border:5px solid #f7f7f7; display:none;}
    .dia_tit {height:30px; line-height:30px; padding:0 20px; font-family:microsoft YaHei; font-size:14px; color:#fff;background: #eb6122;}
    .dia_close {width:15px; height:15px; background:url(../images/dia_close.png) no-repeat; margin-top:7px;}
    .dia_intro{height:auto!important; text-align:left; padding-left:50px; padding-top:20px!important;}
    .info_ok {display:inline-block!important; zoom:1; *display:inline; width:100px; height:28px; text-align:center; line-height:27px; font-family:microsoft YaHei; font-size:14px; color:#fff!important; margin:0 5px;}
    .info_ok {background:url(../images/org_btn.gif) no-repeat;}
    .goods {  width: 150px;}
    .genz{ position: relative;cursor: pointer; display: inline-block;}
    .genz_con{ background: #fff; position: absolute; width: 445px; padding: 10px; top:0; right:35px; padding-top: 1px; z-index: 98; display: none;}
    .genz_con table {border:none;}
    .genz_con table tr th{ text-align: left; font-weight: bold;}
    .genz_con table tr td{border:none; text-align: left; font-weight: 400;}
    .genz_con .close{display: block; font-size: 18px;text-align: right; padding-bottom: 5px;}
    .genz_bg{ position: absolute; top: 5px; right: 28px; z-index: 99; width: 8px; height: 10px; display: none;}
</style>
</head>-->
<@htmlBody>
    	<#--一引入头部 <#include "/index/topnew.ftl" />  -->	
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
        <div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <a href="${basePath}/customer/myorder.html">订单中心</a><span>&gt;</span>
            <span>我的订单</span>
        </div>
        <div class="member_box mb20">
            <#include "leftmenu.ftl" >
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">订单中心</h2>
                </div>
                <div class="order_list switch_border">
                	<div class="tagMenu tagMenu1">
                        <ul class="ods_tabs clearfix">
                           <li <#if (!type?? || type == '5') >class="cur"</#if> data-val="5"><a href="javascript:;" >所有订单</a></li>
                           <li <#if (type?? && type == '0')>class="cur"</#if> data-val="0"><a href="javascript:;" >待处理订单</a></li>
                           <li <#if (type?? && type == '3')>class="cur"</#if> data-val="3"><a href="javascript:;" >已完成订单</a></li>
                           <li <#if (type?? && type == '4')>class="cur"</#if> data-val="4"><a href="javascript:;" >已取消订单</a></li>
                           <li <#if (type?? && type == '6')>class="cur"</#if> data-val="6"><a href="javascript:;" >已退货订单</a></li>
                        </ul>
                    </div>
                    <div class="list_filter">
                        <div class="filter_r fr">
                            <#--<input class="text t_param" type="text" value="${paramString!'商品名称或订单编号'}" onfocus="javascript:this.value = '';" />
                            <input class="sub_btn" type="submit" value="查询" /> -->
                        </div>
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
                        <div class="cb"></div>
                    </div>
                    <div class="content">
                        <div class="layout">
                          <#if ( type?? && type == '6')>
                            <table class="orders common_table" style="font-size:12px;">
                                <tr>
                                    <th>退单编号</th>
                                    <th>退单商品</th>
                                    <th>订单单号</th>
                                    <th>退单金额</th>
                                    <th>实退金额</th>
                                    <th>退单时间</th>
                                    <th>退单状态</th>
                                    <th>操作</th>
                                    <th></th>
                                </tr>
                                <#if (pb.list??) && (pb.list?size!=0)>
	                                <#list pb.list as backorder>
		                                <tr>
                                                <td>
                                                    <#if backorder.backOrderCode??>
                                                        ${backorder.backOrderCode}
                                                    </#if>
                                                </td>
                                                <td class="goods">
                                                    <#if backorder.orderGoodslistVo?? && backorder.orderGoodslistVo?size gt 0>
                                                        <#list backorder.orderGoodslistVo as backgoods>
                                                            <a target="black" title="${backgoods.goodsInfoName!''}" href="${basePath}/item/${backgoods.goodsInfoId}.html"><img width="50" height="50" title="${(backgoods.goodsInfoName)!''}" alt="${(backgoods.goodsInfoName)!''}" src="${(backgoods.goodsInfoImgId)!''}" /></a>
                                                        </#list>
                                                    <#else>
                                                        <span style="padding-left:65px;">无</span>
                                                    </#if>

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
                                                    <font color="red" style="font-family: '微软雅黑'">
                                                        <#if backorder.businessId?? && backorder.businessId == 0>
                                                            <#if backorder.order?? && backorder.order.backPrice??>
                                                                ￥${backorder.order.backPrice?string('0.00')}
                                                            </#if>
                                                        <#else>
                                                            ￥${backorder.backPrice?string('0.00')}
                                                        </#if>
                                                    </font>
                                                </td>
                                                <td>
                                                    <font color="grey">
                                                        <#if backorder.backTime??>
                                                            ${backorder.backTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}
                                                            <br/>
                                                            ${backorder.backTime?string("yyyy-MM-dd HH:mm:ss")?substring(11)}
                                                        </#if>
                                                    </font>
                                                </td>
                                                <td>
                                                    <b>
                                                        <font color="#FF6600">
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
                                                        </font>
                                                    </b>
                                                </td>
                                                <td>
                                                    <#if backorder.backCheck=="2" || backorder.backCheck=="4" || backorder.backCheck=="7" ||backorder.backCheck=="10"  >
                                                        <img  src="${basePath}/images/delete_01.png" style="width:20px;height:20px;cursor: pointer;" onclick="showDialogs_back('${backorder.backOrderId}');">
                                                    </#if>
                                                </td>
                                             <tr>
                                        </#list>
                                    <#else>
                                        <tr>
                                            <td colspan="8" style="border-right:2px solid #E6E6E6 ;width:1025px;">暂无订单！</td>
                                        </tr>
                                    </#if>
                                </table>
                            <#else>
                                <table class="orders common_table" style="font-size:12px;">
                                    <tr>
                                        <th>订单编号</th>
                                        <th>订单商品</th>
                                        <th>收货人</th>
                                        <th>订单金额</th>
                                        <th>下单时间</th>
                                        <th>订单状态</th>
                                        <th>支付状态</th>
                                        <th>操作</th>
                                        <th></th>
                                    </tr>
                                    <#if (pb.list?size!=0)>
                                        <#list pb.list as order>
                                           <#if order.goods?size gt 0>
                                            <tr>
                                                <td>
                                                    <#if order.orderNo??>
                                                        ${order.orderNo}
                                                     </#if>
                                                </td>
                                                <td class="goods">
                                                    <#assign cFlag=0 />
                                                    <#list order.goods as good>
                                                        <#if good.evaluateFlag== '0'>
                                                            <#assign cFlag=cFlag+1 />
                                                        </#if>
                                                        <a target="black" title="${good.goodsName!''}" href="${basePath}/item/${good.goodsId}.html"><img width="50" height="50" title="${(good.goodsName)!''}" alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}" /></a>
                                                    </#list>
                                                </td>
                                                <td>
                                                    <#if order.shippingPerson??>
                                                        ${order.shippingPerson}
                                                    </#if>
                                                </td>
                                                <td>
                                                    <font color="red" style="font-family: '微软雅黑'">￥
                                                        <#if order.moneyPaid??>
                                                            ${order.moneyPaid?string('0.00')}
                                                        </#if>
                                                    </font>
                                                </td>
                                                <td>
                                                    <font color="grey">
                                                        <#if order.addTime??>
                                                                ${order.addTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}
                                                            <br/>
                                                            ${order.addTime?string("yyyy-MM-dd HH:mm:ss")?substring(11)}
                                                        </#if>
                                                    </font>
                                                </td>
                                                <td>
                                                    <b>
                                                        <font color="#FF6600">
                                                            <#if order.orderStatus??>
                                                                <#if order.orderStatus=="0">
                                                                    <#if order.orderLinePay=="0">
                                                                        待商家发货
                                                                    <#else>
                                                                        待付款
                                                                    </#if>
                                                                <#elseif (order.orderStatus=="1" || order.orderStatus=="5" || order.orderStatus=="6") >
                                                                    已付款未发货
                                                                <#elseif order.orderStatus=="2">
                                                                    已发货</br>
                                                                    <a onmouseover="ShowUmsMessage(${order.orderId});" onmouseout="CloseUmsMessage(${order.orderId});">跟踪</a>
                                                                    <b class="genz">
                                                                        <div class="genz_bg"><img src="${basePath}/images/rig.jpg"/></div>
                                                                        <div class="genz_con" id="track_box_${order.orderId}">
                                                                            <iframe width="534px" height="340" style="border:0px;" id="track_${order.orderId}"></iframe>
                                                                        </div>
                                                                    </b>
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
                                                                                   退单结束
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
                                                        </font>

                                                        <p  style="color: red; font-family: '微软雅黑' ">
                                                            <#if order.orderStatus=="17"&&order.backPrice??>
                                                                -￥${order.backPrice}
                                                            </#if>
                                                        </p>
                                                    </b>
                                                   </td>
                                                    <td>
                                                        <#if order.orderLinePay??>
                                                                <#if order.orderLinePay=="1">
                                                                    <font color="#FF6600">
                                                                        在线支付
                                                                    </font>
                                                                <#elseif order.orderLinePay=="0">
                                                                    <font color="red">
                                                                        货到付款
                                                                    </font>
                                                                </#if>
                                                        </#if>
                                                    </td>
                                                <td>
                                                    <!--商品id-->
                                                    <a  style="display:initial;" target="_blank" href="${basePath}/customer/detail-${order.orderId}.html">查看</a><br/>
                                                    <#if ( order.orderStatus=="5" || order.orderStatus=="6") >
                                                        <a  style="display:block;"  href="${basePath}/customer/applybackmoney.htm?orderId=${order.orderId}">申请退货</a><br/>
                                                    </#if>

                                                    <#if (order.orderStatus=="1") >
                                                        <a  style="display:block;"  href="${basePath}/customer/applybackmoneyprice.htm?orderId=${order.orderId}">申请退款</a><br/>
                                                    </#if>

                                                    <#if order.orderStatus=="0">
                                                        <#if order.orderLinePay='1'>
                                                            <a class="order_btn1" style="display:block;"  target="_blank" href="${basePath}/gopayorder-${order.orderId}.html">去付款</a>
                                                            <a style="display:block;"  href="javascript:void(0)" onclick="cancelOrder('${basePath}/customer/cancelorder-myorder-${order.orderId}.html')">取消订单</a>
                                                        </#if>
                                                     <#elseif order.orderStatus=="2">
                                                            <a style="display:block;" class="order_btn1"  href="javascript:void(0)" onclick="comfirmgoods('${basePath}/customer/comfirmofgoods-myorder-${order.orderId}.html')"  >确认收货</a>
                                                     </#if>
                                                    <#if isBackOrder==1>
                                                         <#if (order.orderStatus=="3" && cFlag>0) >
                                                             <a  style="display:initial;" target="_blank" href="${basePath}/comment-${order.orderId}.html">评论</a>
                                                             <a  style="display:initial;" target="_blank" href="${basePath}/share-${order.orderId}.html">晒单</a>
                                                             <a  style="display:block;"  href="${basePath}/customer/applybackmoney.htm?orderId=${order.orderId}">申请退货</a><br/>
                                                         </#if>
                                                        <#if (order.orderStatus=="3" && cFlag==0) >
                                                            <#if (order.orderStatus=="3" && order.shareFlag >0)>
                                                                <a style="display:initial;" target="_blank" href="${basePath}/share-${order.orderId}.html">晒单</a>
                                                            </#if>
                                                            <a  style="display:block;"  href="${basePath}/customer/applybackmoney.htm?orderId=${order.orderId}">申请退货</a><br/>
                                                        </#if>
                                                         <#if (order.orderStatus=="8") >
                                                            <a  style="display:initial;"  href="javascript:void(0)" onclick="setwuliu(${order.orderNo})">填写物流信息</a><br/>
                                                         </#if>
                                                         <#if (order.orderStatus=="14" || order.orderStatus=="17" || order.orderStatus=="10" || order.orderStatus=="9" ||  order.orderStatus=="16")>
                                                            <a style="display:initial;" href="${basePath}/customer/backdetail.htm?orderId=${order.orderId}">退货详情</a><br/>
                                                         </#if>

                                                         <#if (order.orderStatus=="13" || order.orderStatus=="18")>
                                                            <a style="display:initial;" href="${basePath}/customer/backdetailprice.htm?orderId=${order.orderId}">退款详情</a><br/>
                                                         </#if>
                                                    <#elseif isBackOrder==2>
                                                        <#if (order.orderStatus=="3" && cFlag>0) >
                                                            <a  style="display:initial;" target="_blank" href="${basePath}/comment-${order.orderId}.html">评论</a>
                                                         </#if>
                                                        <#if (order.orderStatus=="3" && order.shareFlag >0)>
                                                            <a style="display:initial;" target="_blank" href="${basePath}/share-${order.orderId}.html">晒单</a>
                                                        </#if>
                                                    </#if>
                                                </td>
                                                <td>
                                                    <#if order.orderStatus=='3' || order.orderStatus=='4' || order.orderStatus=='13'|| order.orderStatus=='17'||order.orderStatus=='18' >
                                                        <img src="${basePath}/images/delete_01.png" style="width:20px;height:20px;cursor: pointer;" onclick="showDialogs('${order.orderId}');">
                                                    </#if>
                                                </td>
                                            </tr>
                                           </#if>
                                        </#list>
                                    <#else>
                                        <tr>
                                            <td colspan="8" style="border-right:2px solid #E6E6E6 ;width:1025px;">暂无订单！</td>
                                        </tr>
                                     </#if>
                                </table>
                            </#if>
                            <#if pb.list?? && (pb.list?size!=0)>
	                            <#-- 分页 -->
	                            <#import "../pagination/pageBean.ftl" as page>
	                            <@page.pagination pb />
                            </#if>
                        </div>
                       
                    </div>
                </div>
            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
    </div>
     
    <div class="dialog s_dia dia3">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/q_mark.png" />
            	<em>确定确认收货吗？小心钱货两空哦！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay mysure" id="go_pay_01" href="javascript:;">确定</a>
                <a class="go_shopping" href="javascript:cls();">取消</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    
    <!--退货-->
    <div id="dialog-form" class="dialog s_dia dia5" style=" height:240px; width:350px;">
    	<div class="dia_tit clearfix">
            <h4 class="fl">信息提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<form id="returnGoods_from" method="post">
        		<div style=" padding-top: 10px; padding-left:20px; font-size: 12px; font-weight:bold;">亲！请填写您要退货的原因！</div>
        		<table style="height:100px;margin-top:20px; margin-left:10px;" id="backtable">
        			<tr>
        				<td>
        					<textarea  rows="4" cols="45" onBlur="jiaodianyuanyin()" style=" padding-left:10px; border-radius:8px;" name="returnyuanyin" maxlength="100" id="returnyuanyin" ></textarea>
        				</td>
        			<tr/>
        			<tr>
        				<td style="font-size: 12px; margin-left: 180px;" class="err_yuanyin pl10"></td>
        			<tr/>
        		</table>
	            <div class="dia_ops mt20 tr" style="text-align: center;" >      
	            	<input type="button" class="go_pay" id="go_pay_01"  onclick="returngoods_queding();" value="确定"/> 
	            	<input type="button" class="go_pay" id="go_pay_00" onclick="quxiao()"  value="取消"/> 
	            </div>
            </form>
        </div><!--/dia_cont-->
    </div>
    
    
    
     <!--退货物流-->
    <div id="dialog-form" class="dialog s_dia dia15" style=" height:240px; width:380px;">
    	<div class="dia_tit clearfix">
            <h4 class="fl">信息提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
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
    				<td class="yanzhengno">&nbsp;&nbsp;单号必须正确有效的数字！</td>
    			<tr/>
    			<tr>
    				<td colspan="3"><img src='${basePath}/images/gantanhao_1.gif'/>注：物流公司信息必须真实有效！</td>
    			</tr>
    		</table>
            <div class="dia_ops mt20 tr" style="text-align: center;" >      
            	<input type="button" class="go_pay" id="go_pay_01"  onclick="quedingwl('00');" value="确定"/> 
            	<input type="button" class="go_pay" id="go_pay_00" onclick="quxiaowuliu()"  value="取消"/>
            </div>
        </div><!--/dia_cont-->
    </div>
    
    
    <!--申请退款-->
   <div id="dialog-form" class="dialog s_dia dia16" style=" height:240px; width:350px;">
    	<div class="dia_tit clearfix">
            <h4 class="fl">信息提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="clsc()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<form id="returnGoods_from" method="post">
        		<div style=" padding-top: 10px; padding-left:20px; font-size: 12px; font-weight:bold;">亲！请填写您要退款的原因！</div>
        		<table style="height:100px;margin-top:20px; margin-left:10px;" id="backtable">
        			<tr>
        				<td>
        					<textarea  rows="4" cols="44" onBlur="returnmoney1()" style="border-radius:8px;width: 327px;" name="tuikuanyuanyin" maxlength="100" id="tuikuanyuanyin" ></textarea>
        				</td>
        			<tr/>
        			<tr>
        				<td style="font-size: 12px; margin-left: 180px;" class="err_tuikuan pl10"></td>
        			<tr/>
        		</table>
	            <div class="dia_ops mt20 tr" style="text-align: center;" >      
	            	<input type="button" class="go_pay" id="go_pay_01"  onclick="returnmoney_queding('00');" value="确定"/> 
	            	<input type="button" class="go_pay" id="go_pay_00" onclick="quxiaokuan()"  value="取消"/>
	            </div>
            </form>
        </div><!--/dia_cont-->
    </div>
    
    
    
    
    
    <div class="dialog s_dia dia2" style="width:400px;">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30" style="height:200px;margin-left:20px;">
        		<img class="vm mr10" alt="" src="${basePath}/images/q_mark.png" />
            	<em>取消订单原因:</em>
            	<div class="mn_sel"></div>
            	<div class="selCont">
            		<label><input class="vm mr5" name="res" type="radio" value="现在不想买" />现在不想买</label>
            		<label><input class="vm mr5" name="res" type="radio" value="商品价格较贵" />商品价格较贵</label>
            		<label><input class="vm mr5" name="res" type="radio" value="价格波动"/>价格波动</label>
            		<label><input class="vm mr5" name="res" type="radio" value="商品缺货"/>商品缺货</label>
            		<label><input class="vm mr5" name="res" type="radio" value="重复下单"/>重复下单</label>
            		<label><input class="vm mr5" name="res" type="radio" value="添加或删除商品"/>添加或删除商品</label>
            		<label><input class="vm mr5" name="res" type="radio" value="收货人信息有误"/>收货人信息有误</label>
            		<label><input class="vm mr5" name="res" type="radio" value="发票信息有误/发票未开"/>发票信息有误/发票未开</label>
            		<label><input class="vm mr5" name="res" type="radio" value="送货时间过长"/>送货时间过长</label>
            		<label><input class="vm mr5" name="res" id="other_yy" type="radio" value="其他原因" />其他原因</label>
            	</div>
            	<div class="err_tip" style="color:red;width: 350px;text-align:right;margin-top: 10px;display:none;">请选择取消原因!</div>
            	<textarea class="sel_txa"></textarea>
            	<div class="input_tip" style="color:red;width: 350px;text-align:right;margin-top: 10px;display:none;">请输入10个以上字符!</div>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" id="go_pay_00" href="javascript:;" onclick="changeUrl();">确定</a>
                <a class="go_shopping" href="javascript:cls();">取消</a>
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

    <div class="dialog promotion_dialog_2">
        <div class="dia_tit clearfix">
            <h3 class="fl info_title" style="line-height:30px;">温馨提示</h3>
            <a class="dia_close fr" href="javascript:;" onclick="clsc()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="dia_intro pt30" id="dialog_tip_back" style="text-align: center;margin:0 auto;margin-top: 20px; padding-left:0; font-size: 14px;min-height:50px;">
            </div>
            <div class="dia_ops mt20 tc dia_btn_ok">
                <a class="info_ok info_tip_cancel" href="javascript:;" onclick="deleteBackOrder()">确定</a>
                <a class="info_ok info_tip_cancel" href="javascript:;" onclick="clsc()">关闭</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div>


    <!-- 删除框确认 -->
    <div class="dialog promotion_dialog_1">
        <div class="dia_tit clearfix">
            <h3 class="fl info_title" style="line-height:30px;">温馨提示</h3>
            <a class="dia_close fr" href="javascript:;" onclick="clsc()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="dia_intro pt30" id="dialog_tip" style="text-align: center;margin:0 auto;margin-top: 20px; padding-left:0; font-size: 14px;min-height:50px;">

            </div>
            <div class="dia_ops mt20 tc dia_btn_ok">
                <a class="info_ok info_tip_cancel" href="javascript:;" onclick="clsd()">确定</a>
                <a class="info_ok info_tip_cancel" href="javascript:;" onclick="clsc()">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
        <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>


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
    $(".memeber_left div:eq(0) ul li:eq(0)").addClass("cur");
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
    $("#dialog_tip").html("确定要永久删除？");
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
