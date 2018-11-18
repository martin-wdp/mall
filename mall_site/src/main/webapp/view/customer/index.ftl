<#include "../include/common.ftl">
<@htmlHead title="会员中心-${topmap.systembase.bsetName}">
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
        <div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <span>首页</span>
        </div>
        <div class="member_box mb20">
        	<#include "leftmenu.ftl" />
            <div class="member_right fl ml10">
            <#if customer??>
                <div class="user_info mb20">
                   	<form id="upload_form${customer.customerId}" name="upload_form" method="post" enctype="multipart/form-data" action="${basePath}/uploadimg.htm?customerId=${customer.customerId}" target="hidden_frame">
                    <a class="my_image fl" href="javascript:void(0);">
                        <img width="100" height="100" alt="" id="customer_imgs" src="
                    <#if customer.customerImg??>
							<#if customer.customerImg=='0'>
							    ../images/register.jpg
							<#else>
								${(customer.customerImg)!''}
							</#if>
						<#else>
							 ../images/register.jpg
						</#if>
                        " /><span></span>
                        <font class="upload_file">修改头像</font>
                        <input type="file" class="upload_file" id="imageFile" name="shareFile" customer_id="${customer.customerId}" style="width:100px;height:100px;margin-top:-100px;filter:alpha(opacity=0);-moz-opacity:0.5;-khtml-opacity: 0;opacity: 0;position:relative;z-index:999;">
                        
                    </a>
                    </form>
                    <iframe name="hidden_frame" style="display:none"></iframe>
                    <div class="user_info_content fl">
                        <div class="wlcome mb10"><b>
                        <#if customer.customerNickname??>
							<#if customer.customerNickname=='0'>
							<#else>
								${customer.customerNickname}
							</#if>
						</#if>
                        </b>，欢迎您！</div>
                        <a class="user_lv" href="#">
                            <#--<em class="fr"></em>-->
                            <span class="lv3">
								<#if customer.pointLevelName??>
									<#if customer.pointLevelName=='0'>
									<#else>
										${customer.pointLevelName}
									</#if>
								</#if>
							</span>
                        </a>
                        <div class="safe">
                            <span class="safe_lv lv<#if  cust.isEmail == '0' &&  cust.isMobile == '0'>2<#elseif (cust.isEmail == '1' &&  cust.isMobile == '0') || (cust.isEmail == '0' &&  cust.isMobile == '1')>3<#elseif cust.isEmail == '1' &&  cust.isMobile == '1'>4</#if> ">账户安全：</span>
                            <a href="${basePath}/customer/securitycenter.html" class="phone_varifi <#if customer.isMobile=='1'>active</#if> >" href="#"><em></em>手机已验证</a>
                            <a href="${basePath}/customer/securitycenter.html" class="mail_varifi <#if customer.isEmail=='1'>active</#if>" href="#"><em></em>邮箱已验证</a>
                            <#--<a href="${basePath}/customer/securitycenter.html" class="password_varifi" href="#"><em></em>已设置支付密码</a>-->
                        </div>
                        <div class="message_area">
                            <div class="order_info fl">
                                <dl>
                                    <dt>订单提醒：</dt>
                                    <dd>
                                    	<a
                                    	<#if notice.pendingNum != 0>
                                    		class="active" target="_blank" href="${basePath}/customer/myorder-0-0-1.html"
                                    	<#else>
                                    		href="javascript:void(0)"
                                    	</#if>
                                    	>待处理订单(${notice.pendingNum})</a>
                                    </dd>
                                    <dd>
                                    	<a 
                                    	<#if notice.commentNum != 0>
                                    		class="active" target="_blank" href="${basePath}/customer/myorder-0-5-1.html"
                                    	<#else>
                                    		href="javascript:void(0)"
                                    	</#if>
                                    	>待评价订单(${notice.commentNum})</a></dd>
                                </dl>
                                <dl>
                                    <dt>我的关注：</dt>
                                    <dd>
                                    	<a 
                                    	<#if notice.reduceNum != 0>
                                    		class="active" target="_blank" href="${basePath}/customer/myfollw.html"
                                    	<#else>
                                    		href="javascript:void(0)"
                                    	</#if>
                                    	>降价商品(${notice.reduceNum})</a>
                                    </dd>
                                    <dd>
                                    	<a 
                                    	<#if notice.goodsArriveNum != 0>
                                    		class="active" target="_blank" href="${basePath}/customer/myfollw.html"
                                    	<#else>
                                    		href="javascript:void(0)"
                                    	</#if>
                                    	>新到货商品(${notice.goodsArriveNum})</a></dd>
                                    <dd>
                                    	<a 
                                    	<#if notice.activityGoodsNum != 0>
                                    		class="active" target="_blank" href="${basePath}/customer/myfollw.html"
                                    	<#else>
                                    		href="javascript:void(0)"
                                    	</#if>
										>收藏商品(${notice.activityGoodsNum})</a></dd>
                                </dl>
                                <dl>
                                    <dt>消息中心：</dt>
                                    <dd>
                                    	<a 
                                    	<#--<#if notice.reduceNum != 0>
                                    		class="active" href="#"
                                    	<#else>-->
                                    		href="javascript:void(0)"
                                    	<#--</#if>-->
                                    	>提醒通知(0)</a></dd>
                                    <dd>
                                    	<a
                                    	<#if notice.noReadNum != 0>
                                    		class="active" href="#"
                                    	<#else>
                                    		href="javascript:void(0)"
                                    	</#if>
                                    	>咨询回复(${notice.noReadNum})</a></dd>
                                </dl>
                            </div>
                            <div class="accout_info fl">
                              <!--  <dl>
                                    <dt>账户余额：</dt>
                                   <dd><a class="active" href="#"><b>￥${customer.balanceSum?string('0.00')}</b>余额充值</a></dd>
                                 </dl>-->
                                <dl>
                                    <dt>我的积分：</dt>
                                    <dd><a class="active" target="_blank" href="${basePath}/customer/myintegral.html"><b>${customer.infoPointSum!'0'}</b>个积分</a></dd>
                                </dl>
                                <dl>
                                    <dt>优惠券：</dt>
                                    <dd>
                                    	<a 
                                    	<#if couponNum != 0>
                                    		class="active" target="_blank" href="${basePath}/mycoupon/1.html"
                                    	<#else>
                                    		href="javascript:void(0)"
                                    	</#if>
                                    	>${couponNum}张</a>
                                    </dd>
                                </dl>
                            </div>
                            <div class="cb"></div>
                        </div>
                    </div>
                    <div class="cb"></div>
                </div><!-- END OF user_info -->
                <div class="order_list switch_border">
                    <div class="tagMenu">
                        <ul class="menu">
                           <li><em></em><span></span>近一个月订单</li>
                           <#--<li><em></em><span></span>待评价商品</li>-->
                        </ul>
                    </div>
                    <div class="content">
                        <div class="layout">
                            <table class="orders common_table">
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
                                <#if (customer.orders?size!=0)>
	                                <#list customer.orders as order>
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
			                                    	<a target="_blank" title="${good.goodsName!''}" href="${basePath}/item/${good.goodsId}.html"><img width="50" height="50" title="${(good.goodsName)!''}" alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}" /></a>
			                                    </#list>
		                                    </td>
		                                    <td>
		                                    <#if order.address??>
												${order.address.addressName}
											</#if>
		                                    </td>
		                                    <td>
			                                    <font color="red" style="font-family: '微软雅黑'" ><span >￥</span>
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
																待付款
															<#elseif (order.orderStatus=="1" || order.orderStatus=="5" || order.orderStatus=="6") >
																已付款未发货
															<#elseif order.orderStatus=="2">
																已发货</br>
                                                                <#--<a onmouseover="ShowUmsMessage(${order.orderId});" onmouseout="CloseUmsMessage(${order.orderId});">跟踪</a>
                                                                <b class="genz">
                                                                    <div class="genz_bg"><img src="../images/rig.jpg"/></div>
                                                                    <div class="genz_con" id="track_box_${order.orderId}">
                                                                        <iframe width="534px" height="340" style="border:0px;" id="track_${order.orderId}"></iframe>
                                                                    </div>
                                                                </b>-->
															<#elseif order.orderStatus=="3">
																已完成
															<#elseif order.orderStatus=="4">
																已取消
														    <#elseif order.orderStatus=="7">
														    	退单审核中
														    <#elseif order.orderStatus=="8">
														    	同意退货
														    <#elseif order.orderStatus=="9">
														    	拒绝退货
														    <#elseif order.orderStatus=="10">
														    	待商家收货
														    <#elseif order.orderStatus=="11">
														                       退单结束
														    <#elseif order.orderStatus=="12">
														                       退款审核中
														    <#elseif order.orderStatus=="13">
														                       拒绝退款
                                                            <#elseif order.orderStatus=="14">
                                                                                已提交退货审核
                                                            <#elseif order.orderStatus=="16">
                                                                                商家收货失败
                                                            <#elseif order.orderStatus=="17">
                                                                                已退款
															</#if>
														</#if>
			                                    	</font>
		                                    	</b></td>
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
		                                        <#if order.orderStatus=="0">
                                                    <#if order.orderLinePay='1'>
                                                        <a class="order_btn1" style="display:block;"  target="_blank" href="${basePath}/gopayorder-${order.orderId}.html">去付款</a>
                                                        <a style="display:block;"  href="javascript:void(0)" onclick="cancelOrder('${basePath}/customer/cancelorder-myorder-${order.orderId}.html')">取消订单</a>
                                                    </#if>
                                                 <#elseif order.orderStatus=="2">
                                                        <a style="display:block;" class="order_btn1"  href="javascript:void(0)" onclick="comfirmgoods('${basePath}/customer/comfirmofgoods-myorder-${order.orderId}.html')"  >确认收货</a>
                                                 </#if>
                                                <!--isBackOrder：后台设置是否允许退单  1：允许  2：不允许-->
		                                        <#if isBackOrder==1>
		                                    		 <#if (order.orderStatus=="3" && cFlag>0) >
                                                         <a  style="display:initial;" target="_blank" href="${basePath}/comment-${order.orderId}.html">评论</a>
                                                         <a  style="display:initial;" target="_blank" href="${basePath}/share-${order.orderId}.html">晒单</a>
                                                         <a  style="display:block;"  href="${basePath}/customer/applybackmoney.htm?orderId=${order.orderId}">申请退单</a><br/>
		                                       		 </#if>
                                                    <#if (order.orderStatus=="3" && cFlag==0) >
                                                        <a  style="display:block;"  href="${basePath}/customer/applybackmoney.htm?orderId=${order.orderId}">申请退单</a><br/>
                                                    </#if>
		                                       		 <#if (order.orderStatus=="8" && cFlag>0) >
		                                       		 	<a  style="display:initial;"  href="javascript:void(0)" onclick="setwuliu(${order.orderNo})">填写物流信息</a><br/>
		                                       		 </#if>
		                                       		  <#--<#if (order.orderStatus=="1" ||order.orderStatus=="5"||order.orderStatus=="6" ) >-->
		                                       		 	<#--<a  style="display:initial;"  href="javascript:void(0)" onclick="returnmoney('${basePath}/returns/saveReturnGoodsTake-${order.orderId}.html')">申请退款</a><br/>-->
		                                       		 <#--</#if>-->
                                                    <#if (order.orderStatus=="14" || order.orderStatus=="17" || order.orderStatus=="10" || order.orderStatus=="9" || order.orderStatus=="13" || order.orderStatus=="16")>
                                                        <a style="display:initial;" href="${basePath}/customer/backdetail.htm?orderId=${order.orderId}">退单详情</a><br/>
                                                    </#if>
	                                    		<#elseif isBackOrder==2>
                                                    <#if (order.orderStatus=="3" && cFlag>0) >
                                                        <a  style="display:initial;" target="_blank" href="${basePath}/comment-${order.orderId}.html">评论</a>
                                                        <a style="display:initial;" target="_blank" href="${basePath}/share-${order.orderId}.html">晒单</a>
                                                    </#if>
	                                    		</#if>
		                                        
		                                        <#--<#if (order.orderStatus=="3" && order.shareFlag>0) >-->
		                                        	<#--<a style="display:initial;" target="_blank" href="${basePath}/share-${order.orderId}.html">晒单</a>-->
		                                        <#--</#if>-->
		                                    </td>
		                                    <td>
                                                <#if order.orderStatus=='3' || order.orderStatus=='4'>
                                                    <img src="${basePath}/images/delete_01.png" style="width:20px;height:20px" onclick="showDialogs('${order.orderId}');">
                                                </#if>
                                            </td>
		                                </tr>
	                                </#list>
                                <#else>
	                                <tr>
	                                    <td colspan="7">暂无订单！</td>
	                                </tr>
                                 </#if>
                            </table>
                        </div>
                        <#--<div class="layout">-->
                            <#--<table class="evaluate_goods common_table">-->
                                <#--<tr>-->
                                    <#--<th width="400">商品名称</th>-->
                                    <#--<th width="150">购买时间</th>-->
                                    <#--<th width="100">操作</th>-->
                                <#--</tr>-->
                                <#--<tr>-->
                                    <#--<td class="goods">-->
                                        <#--<a href="#"><img width="50" alt="" src="../images/images_34.jpg" /></a>-->
                                        <#--<a href="#">三星（SAMSUNG）I9100G 3G手机（黑色）WCDMA/GSM</a>-->
                                    <#--</td>-->
                                    <#--<td><font color="grey">2012-12-03 14:22:00</font></td>-->
                                    <#--<td><a class="member_common_btn" href="#">我要评价</a></td>-->
                                <#--</tr>-->
                            <#--</table>-->
                        <#--</div>-->
                    </div>
                    </#if>
                </div>
            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
        
        <div class="guess_goods mb20">
            <h2 class="f14 fb">浏览记录</h2>
            <div class="body">
                <a class="prev" href="javascript:void(0)">&lt;&lt;</a>
                <a class="next" href="javascript:void(0)">&gt;&gt;</a>
                <div class="guess_goods_list">
                    <ul>
                    	<#if browses??>
	                    	<#list browses as browse>
	                    		<li>
			                         <div class="pic"><a target="_blank" title="${(browse.goods.goodsName)!''}" href="${basePath}/item/${browse.goodsId}.html"><img src="<#if browse.goods??><#if browse.goods.goodsImg??> ${(browse.goods.goodsImg)!''}</#if></#if>" alt="${(browse.goods.goodsName)!''}" title="${(browse.goods.goodsName)!''}"  width="130" height="130" ></a></div>
			                         <div class="name"><a target="_blank" title="${(browse.goods.goodsName)!''}" href="${basePath}/item/${browse.goodsId}.html"><#if browse.goods??><#if browse.goods.goodsName??> ${(browse.goods.goodsName)!''}</#if></#if></a></div>
			                         <div class="score"><span class="star star_${(browse.goods.goodsScore)!'0'}"></span></div>
			                         <div class="reviews"><a href="${basePath}/item/${browse.goodsId}.html#comment">(共有${(browse.goods.commentCount)!'0'}个评价)</a></div>
			                         <div class="price"><span class="fb">￥<#if browse.goods??><#if browse.goods.goodsPrice??> ${browse.goods.goodsPrice?string('0.00')}</#if></#if></span></div>
		                        </li>
	                    	</#list>
                    	</#if>
                    </ul>
                </div><!-- END OF guess_goods_list -->
            </div>
        </div><!-- END OF guess_goods -->
    </div>
    
    <input type="hidden" value="${token!''}" id="hi_token" />
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
            	<input type="hidden" value=""  id="rea_hid"/>
            	<textarea class="sel_txa"></textarea>
            	<div class="input_tip" style="color:red;width: 350px;text-align:right;margin-top: 10px;display:none;">请输入10个以上字符!</div>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" id="go_pay_00" href="javascript:;" onclick="changeUrl();">确定</a>
                <a class="go_shopping" href="javascript:cls();" onclick="quxiao()" >取消</a>
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
        		<img class="vm mr10" alt="" src="${basePath}/images/q_mark.png" />
            	<em>确定确认收货吗？小心钱货两空哦！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" id="go_pay_01" href="javascript:;">确定</a>
                <a class="go_shopping" href="javascript:cls();">取消</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    <div class="dialog s_dia dia4">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/q_mark.png" />
            	<em id="titleerr">头像上传成功！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    <!-- 删除框确认 -->
    <div class="dialog promotion_dialog_1 s_dia">
        <div class="dia_tit clearfix">
            <h3 class="fl info_title">温馨提示</h3>
            <a class="dia_close fr" href="javascript:;" onclick="clsc()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="dia_intro pt30" id="dialog_tip" style="text-align: center;margin-top: 19px; font-size: 14px;">

            </div>
            <div class="dia_ops mt20 tc dia_btn_ok">
                <a class="info_ok info_tip_cancel" href="javascript:;" onclick="clsd()">确定</a>
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
        		<div style=" padding-top: 10px; padding-left:20px; font-size: 12px; font-weight:bold;">亲！请填写您要退单的原因！</div>
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
            	<input type="button" class="go_pay" id="go_pay_01"  onclick="quedingwl('01');" value="确定"/> 
            	<input type="button" class="go_pay" id="go_pay_00" onclick="quxiaowuliu()"  value="取消"/>
            </div>
        </div><!--/dia_cont-->
    </div>
    
    <!--申请退款-->
   <div id="dialog-form" class="dialog s_dia dia16 s_dia" style=" height:240px; width:350px;">
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
	            	<input type="button" class="go_pay" id="go_pay_01"  onclick="returnmoney_queding('01');" value="确定"/> 
	            	<input type="button" class="go_pay" id="go_pay_00" onclick="quxiaokuan()"  value="取消"/>
	            </div>
            </form>
        </div><!--/dia_cont-->
    </div>
    
    <#--<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>-->
    <#--引入底部 <#include "/index/bottom.ftl" />  -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<#--
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script> 
-->
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/uploadImg.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>

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

var id = '';
function showDialogs(orderId) {
    id = orderId;
    $(".mask").fadeIn();
    $(".promotion_dialog_1").fadeIn();
    $("#dialog_tip").html("确定要永久删除？");
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
