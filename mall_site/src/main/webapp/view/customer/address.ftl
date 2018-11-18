<#include "../include/common.ftl">
<@htmlHead title="收货地址-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .tipserror{color:red;}
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>收货地址-${topmap.systembase.bsetName}</title>
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
.tipserror{
	color:red;
}
</style>
</head>-->

<@htmlBody>
    
    	<input type=hidden name="basePath" id="basePath" value="${basePath}"/>
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
            <a href="${basePath}/customer/myinfo.html">账户中心</a><span>&gt;</span>
            <span>收货地址</span>
        </div>
        <div class="member_box mb20">
        <#--引入左侧导航-->
        <#include "leftmenu.ftl" />
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">收货地址</h2>
                </div>
                <div class="new_address mb10">
                	<a class="new_addrss_btn" id="addBtn" href="#fill_address" onclick="toaddAddress()">新增收货地址</a>
                    <p class="light">您已经创建<span class="orange" id="addressesNum">${customer.addresses?size}</span>个收货地址，最多可创建<span class="orange">10</span>个</p>
                </div>
                <div class="address_list">
                <#list customer.addresses as address>
                    <div class="address_item mb20" >
                    	<div class="title">
                        	<div class="extra_links fr">
                                <#if address??>
									<#if address.isDefault=="0">
										<a href="javascript:;" onclick="modifyDefault('${address.addressId}','${customer.customerId}')">设为默认地址</a>
									</#if>
	                                <a href="#fill_address" onclick="updateAddress(${address.addressId})">修改地址信息</a>
	                                <a href="${basePath}/customer/address/delete/${address.addressId}" >删除</a>
								</#if>
                            </div>
                            <h4 class="f16 fb">
                            	<#if address.addressAlias?? && address.addressAlias != "">
	                            	${address.addressAlias?default("收货地址")}
                            	<#else>
                            		收货地址
                            	</#if>
                            	<#if address.isDefault=="1">
                            		<span class="orange">(默认)</span>
                            	</#if>
                            </h4>
                        </div>
                        <div class="body" style="height:120px;">
                        	<div class="item_info">
                                <div class="item">
                                    <span class="label">收货人：</span>
                                    <div>
		                                <#if address.addressName??>
											${address.addressName}
										</#if>
                                    </div>
                                </div>
                                <div class="item">
                                    <span class="label">地址：</span>
                                    <div>
                                       	<#if address.province??>
												${address.province.provinceName}
										</#if>-
										<#if address.city??>
												${address.city.cityName}
										</#if>-
										<#if address.district??>
												${address.district.districtName}
										</#if>-
										<#if address.street??>
												${address.street.streetName}
										</#if>-
										<#if address.addressDetail??>
												${address.addressDetail}
										</#if>
                                    </div>
                                </div>
                                <div class="item">
                                    <span class="label">手机：</span>
                                    <div>
                                        <#if address.addressMoblie??>
											<#if address.addressMoblie?length!=0>
												${address.addressMoblie}<br>
											</#if>
										</#if>
                                    </div>
                                </div>
                                <div class="item">
                                    <span class="label">固定电话：</span>
                                    <div>
                                    	<#if address.addressPhone?? && (address.addressPhone?length>0)>
											${address.addressPhone}
										<#else>
											&nbsp;&nbsp;
										</#if>
                                    </div>
                                </div>
                                <div class="item">
                                    <span class="label">邮编：</span>
                                    <div>
                                    	<#if address.addressZip?? && (address.addressZip?length>0)>
											${address.addressZip}
										<#else>
											&nbsp;&nbsp;
										</#if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><!-- /address_item -->
                </#list>
                    <div class="switch_border mb20" id="fill_address">
                    	<form method="post"  id="addressFrom" onsubmit="return updateqqq()">
                    	<input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
                    	<!--隐藏会员编号
                    	-->
						<input type=hidden name="customerId" id=hiden_customerId value=${(customer.customerId)!''} />
						<!--隐藏地址编号-->
						<input type=hidden name=addressId id=hiden_addressId />
                        <div class="ex_info member_info mb20">
                            <h4 class="f16 fb">地址信息</h4>
                            <dl>
                                <dt><em>*</em>收货人：</dt>
                                <dd>
                                	<input class="text" name="addressName" id="addressName" type="text">
                                	<span id="addressName_tips"></span>
                                	${(bingingResult.addressName)!''}
                                </dd>
                            </dl>
                            <dl>
                                <dt><em>*</em>所在地：</dt>
	                            <dd>
	                            	<select class="select" name="infoProvince" id=infoProvince >
									</select>
									<select class="select" name="infoCity" id=infoCity>
										<option selected value="">请选择</option>
									</select>
									<select class="select" name="infoCounty" id=infoCounty>
										<option selected value="">请选择</option>
									</select>
									<select class="select" name="infoStreet" id=infoStreet>
										<option selected value="">请选择</option>
									</select>
	                             </dd>
                            </dl>
                            <dl>
                                <dt><em>*</em>详细地址：</dt>
                                <dd>
                                	<input class="text address" name="addressDetail" id="addressDetail" type="text" />
                                	<span id="addressDetail_tips"></span>
                                </dd>
                            </dl>
                            <dl>
                                <dt><em>*</em>手机号码：</dt>
                                <dd>
                                	<input class="text" name="addressMoblie" id="addressMoblie" type="text" />
                                	<span id="addressMoblie_tips"></span>
                                </dd>
                            </dl>
                            <dl>
                                <dt>固定电话：</dt>
                                <dd>
                                	<input class="text" name="addressPhone" id="addressPhone" type="text" />
                                	<span id="addressPhone_tips"></span>
                                	<span class="light">例如“025-8816157”、“(025)8816157”，可加分机号如“025-8816157-4001”</span>
                                </dd>
                            </dl>
                            <dl>
                                <dt>邮政编码：</dt>
                                <dd>
	                                <input class="text" name="addressZip" id="addressZip" type="text" />
	                                <span class="light" id="addressZip_tips"></span>
                                </dd>
                            </dl>
                            <dl>
                                <dt>地址别名：</dt>
                                <dd>
	                                <input class="text" name="addressAlias" id="addressAlias" type="text" />
	                                <span class="light" id="addressAlias_tips">例如“自己家”“公司地址”等</span>
                                </dd>
                            </dl>
                            <dl>
                                <dt></dt>
                                <dd><input type="submit" id="submitBtn" class="sub_btn" value="提交" /></dd>
                            </dl>
                        </div>
                    	</form>
                    </div>
                </div><!-- /address_list -->
            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
        
    </div>
	<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
    <#--引入底部 <#include "/index/bottom.ftl" /> -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
	
	<div class="dialog dia1">
        <div class="dia_tit clearfix">
            <h4 class="fl info_tip_title">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="dia_intro tc pt30">
            	<img class="vm info_tip_img" alt="" src="${basePath}/images/q_mark.png" />
	            <em>设置此地址为默认地址的同时</em>
	            <em>会将之前的默认地址清除</em>
	            <em>确定设置为默认吗?</em>
        	</div>
            <div class="dia_ops mt20 tc">
                <a class="go_pay info_tip_cancel" href="javascript:;">确定</a>
                <a class="go_shopping info_tip_submit" href="javascript:;" onclick="cls()">取消</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
	
    <div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div>
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/mod_war.png" />
            	<em>您最多可创建10个收货地址！</em>
            </div>
            <div class="dia_ops mt20 tr">
                <a class="go_pay" href="javascript:;" onclick="cls()">确定</a>
            </div>
        </div>
    </div>
    
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script> 
<script type="text/javascript" src="${basePath}/js/customer/customaddress.js"></script> 
<script type="text/javascript">
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
	$(".pro_sort").addClass("pro_sort_close");
	$(".memeber_left div:eq(2) ul li:eq(5)").addClass("cur");
	
	//弹窗
		win();
		$(window).resize(function(){
			win();
		});
});
//添加收货地址，文本框清空
function toaddAddress(){
	$("#hiden_addressId").val("")
	$("#addressName").val("")
	$("#addressDetail").val("")
	$("#addressMoblie").val("")
	$("#addressPhone").val("")
	$("#addressAlias").val("")
	
	$("#addressName_tips").text("").removeClass("tipserror");
	$("#addressDetail_tips").text("").removeClass("tipserror");
	$("#addressMoblie_tips").text("").removeClass("tipserror");
	$("#addressPhone_tips").text("").removeClass("tipserror");
	$("#addressAlias_tips").text("例如“自己家”“公司地址”等").removeClass("tipserror").addClass("light");
}

//判断是否达到数量上限
function checkAddressesNum(){
	var num = $("#addressesNum").text();
	if(num>=10){
		dia(2);
		return false;
	}else{
		return true;
	}
}
//验证特殊字符，将调试显示到页面中
function checkSpecSymb(inputobj,Tipobj){
	 var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
	 if (regexp.test( $("#"+inputobj).val() ) ) {
		 $("#"+inputobj).addClass( "ui-state-error" );
         updateTips( "输入的内容包含特殊字符!", $("#"+Tipobj));
         $("#"+inputobj).focus();
         return false;
     }
     else {
    	 $("#"+Tipobj).text("").removeClass( "ui-state-highlight");
    	 $("#"+inputobj).removeClass( "ui-state-error" );
         return true;
     }
}
//特殊字符
var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
//
//var regexpPhone = new RegExp("[\d{3}-\d{8}|\d{4}-\d{7,8}]");
//var regexpMoblie = new RegExp("[1\d{10}]");
function checkField(){
		var flag = true;
		if($("#addressName").val()==""){
			$("#addressName_tips").text("请填写收货人").addClass("tipserror");
			flag = flag && false;
		}else{
			if (regexp.test( $("#addressName").val() ) ) {
				$("#addressName_tips").text("输入的内容包含特殊字符!").addClass("tipserror");
				flag = flag && false;
			}else{
				$("#addressName_tips").text("").removeClass("tipserror");
				flag = flag && true;
			}
		}
		if($("#addressDetail").val()==""){
			$("#addressDetail_tips").text("请填写详细地址").addClass("tipserror");
			flag = flag && false;
		}else{
			if (regexp.test( $("#addressDetail").val() ) ) {
				$("#addressDetail_tips").text("输入的内容包含特殊字符!").addClass("tipserror");
				flag = flag && false;
			}else{
				$("#addressDetail_tips").text("").removeClass("tipserror");
				flag = flag && true;
			}
		}
		if($("#addressMoblie").val()==""){
			$("#addressMoblie_tips").text("请填写手机号码").addClass("tipserror");
			flag = flag && false;
		}else{
			if (/^1[3|4|5|7|8][0-9]\d{4,8}$/.test( $("#addressMoblie").val() ) ) {
				$("#addressMoblie_tips").text("").removeClass("tipserror");
				flag = flag && true;
			}else{
				$("#addressMoblie_tips").text("手机号码格式不对!").addClass("tipserror");
				flag = flag && false;
			}
		}
		if($("#addressPhone").val()!=""){
			if (/^((0[0-9]{2,3}\-)|(\(0[0-9]{2,3}\)))?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test( $("#addressPhone").val() ) ) {
				$("#addressPhone_tips").text("").removeClass("tipserror");
				flag = flag && true;
			}else{
				$("#addressPhone_tips").text("固定电话格式不对!").addClass("tipserror");
				flag = flag && false;
			}
		}
		if($("#addressZip").val()!=""){
			if(/^[1-9]\d{5}(?!\d)$/.test($("#addressZip").val())){
				$("#addressZip_tips").text("").removeClass("tipserror");
				flag = flag && true;
			}else{
				$("#addressZip_tips").text("邮政编码格式不对!").addClass("tipserror");
				flag = flag && false;
			}
		}
		if($("#addressAlias").val()!=""){
			if (regexp.test( $("#addressAlias").val() ) ) {
				$("#addressAlias_tips").text("输入的内容包含特殊字符!").removeClass("light").addClass("tipserror");
				flag = flag && false;
			}else{
				$("#addressAlias_tips").text("例如“自己家”“公司地址”等").removeClass("tipserror").addClass("light");
				flag = flag && true;
			}
		}
		<#--
		if($("#addressPhone").val()==""){
			$("#addressPhone_tips").text("请填写固定电话").addClass("tipserror");
			flag = flag && false;
		}else{
			$("#addressPhone_tips").text("").removeClass("tipserror");
			flag = flag && true;
		}
		if($("#addressAlias").val()==""){
			$("#addressAlias_tips").text("请填写地址别名").removeClass("light").addClass("tipserror");
			flag = flag && false;
		}else{
			$("#addressAlias_tips").text("例如“自己家”“公司地址”等").removeClass("tipserror").addClass("light");
			flag = flag && true;
		}
		-->
		return flag;
}

//判断是修改还是添加,设置提交的控制器方法
function updateqqq(){
	if($("#hiden_addressId").val()!=""){
		$("#addressFrom").prop("action",$("#basePath").val()+"/customer/address/update");
		return checkField();
	}else{
		$("#addressFrom").prop("action",$("#basePath").val()+"/customer/address/add");
		if(checkAddressesNum()){
			return checkField();
		}else{
			return false;
		}
	}
	
}
function win(){
	var _wd = $(window).width();
	var _hd = $(window).height();
	$(".dialog").css("top",(_hd - $(".dialog").height())/2).css("left",(_wd - $(".dialog").width())/2);
};

function dia(n){
	$(".mask").fadeIn();
	$(".dia"+n).fadeIn();
	};
	
function cls(){
	$(".dialog").fadeOut();
	$(".mask").fadeOut();
	};
</script>
</@htmlBody>

