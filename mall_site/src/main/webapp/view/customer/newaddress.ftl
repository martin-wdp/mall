<#include "../include/common.ftl">
<@htmlHead title="收货地址-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
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
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
</head>-->
<@htmlBody>
    	<input type=hidden name="basePath" id="basePath" value="${basePath}"/>
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
                <!--new_member_left-->
                <#include "newleftmenu.ftl"/>
                <div class="new_member-right">
                    <div class="air-account-address">
                        <div class="n-title">收货地址</div>
                        <div class="simple mt20 clearfix">
                            <div class="fl">
                                <button  onclick="toaddAddress()">新增收货地址</button>
                                <span class="addressNum-tips ml10">您已创建<span class="red">${pb.rows!'0'}</span>个收货地址，最多可创建<span class="red">20</span>个</span>
                            </div>
                        </div>
                        <div class="content">
                            <div class="layout">
                                <div class="air-addressList">
                                    <#if pb.list?size!=0>
                                        <#list pb.list as address>
                                            <div class="air-addressInfo">
                                                <ul>
                                                    <li>
                                                        <label for=""> 收货人：</label><span><#if address.addressName??>${address.addressName}</#if></span>
                                                    </li>
                                                    <li>
                                                        <label for=""> 所在地区：</label>
                                                        <span>
                                                            <#if address.province??>
                                                            ${address.province.provinceName}
                                                            </#if>
                                                            <#if address.city??>
                                                                -${address.city.cityName}
                                                            </#if>
                                                            <#if address.district??>
                                                                -${address.district.districtName}
                                                            </#if>
                                                            <#if address.street??>
                                                            -${address.street.streetName}
                                                            </#if>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <label for=""> 地址：</label>
                                                        <span>
                                                            <#if address.addressDetail??>
                                                            ${address.addressDetail}
                                                            </#if>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <label for="">手机：</label>
                                                        <span>
                                                                <#if address.addressMoblie??>
                                                                <#if address.addressMoblie?length!=0>
                                                                ${address.addressMoblie}<br>
                                                                </#if>
                                                                </#if>
                                                        </span>
                                                    </li>
                                                </ul>
                                                <span class="delete-address"><a onclick="deladdress(${address.addressId})"><img src="${basePath}/images/delete-address.png"/></a></span>
                                                <div class="address-toolbar">
                                                    <#if address.isDefault=='0'>
                                                    <button  onclick="modifyDefault('${address.addressId}','${customer.customerId}')">设为默认</button>
                                                    <#else>
                                                        <button style="border: none; color: red; background: none; cursor: default;">默认地址</button>
                                                    </#if>
                                                    <button onclick="updateAddress(${address.addressId})">编辑</button>
                                                </div>
                                            </div>
                                        </#list>
                                    <#else>
                                        <div  style="margin-top:10px;border:1px #e8e8e8 solid; height:40px; text-align: center; font-size: 18px;line-height:40px;;">
                                            暂无收货地址！
                                        </div>
                                    </#if>

                                </div>
                                <div class="paging_area">
                                    <#if (pb.list?size!=0)>
                                    <#-- 分页 -->
                                    <#import "../pagination/pageBean.ftl" as page>
                                    <@page.pagination pb />
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="mask"></div>
        <div class="dialog member-dialog dia2 big-dialog">
            <div class="member-dialog-body">
                <div class="title"><a href="javascript:;" onclick="cls()">×</a><span id="diaTitle">添加收货地址</span></div>
                <div class="add-arr pb10">
                    <form method="post"  id="addressFrom" onsubmit="return updateqqq()">
                        <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
                        <!--隐藏会员编号
                    -->
                        <input type=hidden name="customerId" id=hiden_customerId value=${(customer.customerId)!''} />
                    <table>
                        <tr><!--隐藏地址编号-->
                            <input type="hidden" name="addressId" id="hiden_addressId" />
                            <td width="95" align="right"><em>*</em>收货人：</td>
                            <td>
                                <input class="text" name="addressName" id="addressName" type="text">
                                <span id="addressName_tips" class="red"></span>
                                 ${(bingingResult.addressName)!''}
                            </td>
                        </tr>
                        <tr>
                            <td align="right"><em>*</em>所在地：</td>
                            <td>
                                <select class="select" name="infoProvince" id=infoProvince >
                                </select>
                                <select class="select" name="infoCity" id=infoCity>
                                    <option selected value="">请选择</option>
                                </select>
                                <select class="select" name="infoCounty" id=infoCounty>
                                    <option selected value="">请选择</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right"><em>*</em>详细地址：</td>
                            <td>
                                <input class="text address"style="width: 210px;" name="addressDetail" id="addressDetail" type="text" />
                                <span id="addressDetail_tips" class="red"></span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right"><em>*</em>手机号：</td>
                            <td>
                            <input class="text" name="addressMoblie" id="addressMoblie" type="text" />
                            <span id="addressMoblie_tips" class="red"></span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">固定电话：</td>
                            <td>
                                <input class="text" name="addressPhone" id="addressPhone" type="text" />
                                <span id="addressPhone_tips"></span>
                                <span class="light" >例如“025-8816157”</span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">邮政编码：</td>
                            <td>
                                <input class="text" name="addressZip" id="addressZip" type="text" />
                                <span class="light red" id="addressZip_tips" ></span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">地址别名：</td>
                            <td>
                                <input class="text" name="addressAlias" id="addressAlias" type="text" />
                                <span class="light" id="addressAlias_tips" >例如“自己家”“公司地址”等</span>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><button id="submitBtn">保存收货地址</button></td>
                        </tr>

                    </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="member-dialog dia1">
            <div class="member-dialog-body">
                <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
                <div class="tc">
                    <div class="que-delete clearfix">
                        <img src="${basePath}/images/images_l6.png"/>
                        <div class="fl tl">
                            <p class="f16 red">确定设置为默认吗?</p>
                            <div class="m-btn mt20">
                                <a class="info_tip_cancel" href="javascript:;">确定</a>
                                <a class="info_tip_submit" href="javascript:;" onclick="cls()">取消</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="member-dialog dia4">
            <div class="member-dialog-body">
                <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
                <div class="tc">
                    <div class="que-delete clearfix">
                        <img src="${basePath}/images/images_l6.png"/>
                        <div class="fl tl">
                            <p class="f16 red">您确定要删除此收货地址！</p>
                            <div class="m-btn mt20">
                                <a  id="delUrl" href="javascript:;">确定</a>
                                <a  href="javascript:;" onclick="cls()">取消</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="member-dialog dia3">
            <div class="member-dialog-body">
                <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
                <div class="tc">
                    <div class="que-delete clearfix">
                        <img src="${basePath}/images/images_l6.png"/>
                        <div class="fl tl">
                            <p class="f16 red">您最多可创建10个收货地址！</p>
                            <div class="m-btn mt20">
                                <a class="go_pay" href="javascript:;" onclick="cls()">确定</a>
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
<script type="text/javascript" src="${basePath}/js/customer/customaddress.js"></script>
<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
        <script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
        <script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
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
    $(".new_member_left div:eq(3) ul li:eq(5)").addClass("cur");
	//弹窗
		win();
		$(window).resize(function(){
			win();
		});
});
//删除收货地址
function deladdress(addressId){
    $("#delUrl").attr("href","${basePath}/customer/address/delete/"+addressId);
    dia(4);
}
//添加收货地址，文本框清空
function toaddAddress(){
    $("#diaTitle").html("添加收货地址");
	$("#hiden_addressId").val("")
	$("#addressName").val("")
	$("#addressDetail").val("")
	$("#addressMoblie").val("")
	$("#addressPhone").val("")
	$("#addressAlias").val("")
    $("#addressZip").val("")
	$("#addressName_tips").text("").removeClass("tipserror");
	$("#addressDetail_tips").text("").removeClass("tipserror");
	$("#addressMoblie_tips").text("").removeClass("tipserror");
	$("#addressPhone_tips").text("").removeClass("tipserror");
	$("#addressAlias_tips").text("例如“自己家”“公司地址”等").removeClass("tipserror").addClass("light");
    dia(2);
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
			if (/^1[3|4|5|7|8][0-9]\d{8}$/.test( $("#addressMoblie").val() ) ) {
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

		return flag;
}

//判断是修改还是添加,设置提交的控制器方法
function updateqqq(){
	if($("#hiden_addressId").val()!=""){
		$("#addressFrom").prop("action",$("#basePath").val()+"/customer/address/update");
		return checkField();
	}else{
		if(checkAddressesNum()){
            checkField();
            if(checkField()){
                $("#submitBtn").attr("disabled","disabled");
                $("#addressFrom").prop("action",$("#basePath").val()+"/customer/address/add");
               // return false;
            }
            return checkField();
        }else{
			return false;
		}
	}
	
}
</script>
</@htmlBody>
