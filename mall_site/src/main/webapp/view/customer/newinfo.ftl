<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!''}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>账户信息-${topmap.systembase.bsetName}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="Keywords" content="${topmap.seo.meteKey}">
    <meta name="description" content="${topmap.seo.meteDes}">

<#assign basePath=request.contextPath>

<#if (topmap.systembase.bsetHotline)??>
    <link rel="Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
    <link rel="Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery-1.11.1.min.js"></script>
</head>-->
<@htmlBody>
<input type="hidden" name="CSRFToken" value="${token}">
<#--一引入头部 <#include "/index/topnew.ftl" />  -->
<#include "../index/newtop7.ftl">
<#include "newtop.ftl"/>
<div style="background: #f5f5f5;">
    <div class="container clearfix pt20 pb10">
        <!--new_member_left-->
    <#include "newleftmenu.ftl"/>
        <div class="new_member-right">
            <div class="new_order_list">
                <div class="n-title">账户信息</div>
                <div class="tagMenu order-menu pr">
                    <ul class="menu clearfix">
                        <li class="current">基本信息</li>
                    </ul>
                    <div class="aco-link">
                        注：修改邮箱、手机请到<a href="${basePath}/customer/securitycenter.html">账户安全</a>
                    </div>
                </div>
                <div class="content">
                    <div class="layout">
                        <form method="post" action="${basePath}/modifyInfo.html">
                            <table class="account-table">
                                <tr>
                                    <td class="account-td-title">用户名：</td>
                                        <input type="hidden" id="hi_uid" value="${customer.customerId}"/>
                                    <td class="account-td-content">
                                        <#if customer.customerUsername??>
	                                    	${customer.customerUsername}
	                                    </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="account-td-title">会员等级：</td>
                                    <td class="account-td-content">
                                        <#if customer.pointLevelName??>
                                            ${customer.pointLevelName}
                                        <#else>
                                            &nbsp;&nbsp;
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="account-td-title"><span>*</span>昵称：</td>
                                    <td class="account-td-content">
                                        <input class="text" id="nickName" type="text" name="customerNickname"
                                               value="<#if customer.customerNickname??>${customer.customerNickname!''}</#if>"/>
                                        <img id="nickName_img" src="${basePath}/images/succ-ico.png"/>
                                        <label id="nickName_msg"></label>
                                        <input class="hidden" id="nickName_" value="${customer.customerNickname!''}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="account-td-title" valign="top"><span>*</span>性别：</td>
                                    <td class="account-td-content">
                                        <input type="radio" name="infoGender" value="0"
                                               <#if customer.infoGender=="0" >checked=checked</#if> /><label>保密</label>
                                        <input type="radio" name="infoGender" value="1"
                                               <#if customer.infoGender=="1" >checked=checked</#if> /><label>男</label>
                                        <input type="radio" name="infoGender" value="2"
                                               <#if customer.infoGender=="2" >checked=checked</#if> /><label>女</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="account-td-title">真实姓名：</td>
                                    <td class="account-td-content">
                                        <input class="text" id="realName" type="text" name="infoRealname"
                                               value="<#if customer.infoRealname??>${customer.infoRealname}</#if>"/>
                                        <img id="realName_img" src="${basePath}/images/succ-ico.png"/>
                                        <label id="realName_msg"></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="account-td-title">身份证号码：</td>
                                    <td class="account-td-content">
                                    <#if customer.infoCardid??>
                                        <#if (customer.infoCardid?trim)?length==0>
                                            <input class="text" id="idnum" name="infoCardid" style="width: 178px;"/>
                                            <img id="idnum_img" src="${basePath}/images/succ-ico.png"/>
                                            <label id="idnum_msg"></label>
                                        <#else>
                                            <#assign cd="${customer.infoCardid?substring(3,customer.infoCardid?length-3)}" />
                                            <#assign cid="${customer.infoCardid?replace(cd,'******')}" />
                                            <input class="text" id="idnum" name="infoCardid" style="width: 178px;"
                                                   value="${cid}"/>
                                            <img id="idnum_img" src="${basePath}/images/succ-ico.png"/>
                                            <label id="idnum_msg"></label>
                                        <#--<a href="#">修改</a>-->
                                        </#if>
                                    <#else>
                                        <input class="text" id="idnum" name="infoCardid" style="width:178px;"/>
                                        <img id="idnum_img" src="${basePath}/images/succ-ico.png"/>
                                        <label id="idnum_msg"></label>
                                    </#if>
                                        <input class="hidden" id="idnum_" value="${customer.infoCardid!''}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="account-td-title">所在地：</td>
                                    <td class="account-td-content">
                                        <select class="select" name="infoProvince" id=infoProvince>
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
                                    <td class="account-td-title">详细地址：</td>
                                    <td class="account-td-content">
                                        <input class="text address" style="width: 178px;" id="address"
                                               name="infoAddress" type="text"
                                               value="<#if customer.infoAddress??>${customer.infoAddress?default("")}</#if>"/>
                                        <img id="address_img" src="${basePath}/images/succ-ico.png"/>
                                        <label id="address_msg"></label>
                                    </td>
                                </tr>
                            </table>
                            <div class="cho-fill-title">
                                选填信息
                            </div>
                            <table class="account-table">
                                <tr>
                                    <td class="account-td-title">生日：</td>
                                    <td class="account-td-content">
                                        <select class="select" id=year name="year"> </select> 年
                                        <select class="select" id=month name="month"> </select>月
                                        <select class="select" id=day name="day"> </select> 日
                                    </td>
                                </tr>
                                <tr>
                                    <td class="account-td-title">月收入：</td>
                                    <td class="account-td-content">
                                        <select class="select" id="infoSalary" name="income">
                                            <option selected value="-1">无收入</option>
                                            <option value="0">2000元以下</option>
                                            <option value="1">2000-3999元</option>
                                            <option value="2">4000-5999元</option>
                                            <option value="3">6000-7999元</option>
                                            <option value="4">8000元以上</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="account-td-title">婚姻状况：</td>
                                    <td class="account-td-content">
                                        <input type="radio" name="infoMarital" value="0"
                                               <#if customer.infoMarital=="0" >checked=checked</#if> /><label>保密</label>
                                        <input type="radio" name="infoMarital" value="1"
                                               <#if customer.infoMarital=="1" >checked=checked</#if> /><label>未婚</label>
                                        <input type="radio" name="infoMarital" value="2"
                                               <#if customer.infoMarital=="2" >checked=checked</#if> /><label>已婚</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="account-td-title" valign="top">兴趣爱好：</td>
                                    <td class="account-td-content">
                                        <textarea cols="80" rows="5" id="infoInterest" name="infoInterest"
                                                  class="text_area"><#if customer.infoInterest??>${customer.infoInterest} </#if></textarea>
                                        <img id="infos_img" src="${basePath}/images/succ-ico.png"/>
                                        <label id="infos_msg"></label>
                                        <br/>
                                        <a onclick="updateUserInfo()" class="complain-submit">提交</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    <#if vip?? && vip !="1" >
                        <a href="${basePath}/toValidateProtocol.htm" class="mt30" style="display:block;">
                            <img src="${basePath}/images/qpmalenterprise/qp_images/df.jpg"/></a>
                    </#if>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="mask"></div>
<div class="member-dialog dia2">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:;" onclick="closeDialog('2')">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>

                <div class="fl tl">
                    <p class="f16 red" id="con_00">修改成功！</p>

                    <div class="m-btn mt20">
                        <a href="javascript:closeDialog('2');">确定</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<#--引入底部 <#include "/index/bottom.ftl" /> -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
    <#else>
        <#include "../index/newbottom.ftl" />
    </#if>
</#if>

<input type=hidden id=Province value=
<#if customer.infoProvince??>
${customer.infoProvince}
</#if>/>
<input type=hidden id=City value=
<#if customer.infoCity??>
${customer.infoCity}
</#if>/>
<input type=hidden id=County value=
<#if customer.infoCounty??>
${customer.infoCounty}
</#if>/>
<input type=hidden id=Street value=
<#if customer.infoStreet??>
${customer.infoStreet}
</#if>/>
<input type=hidden id=birth value=
<#if customer.infoBirthday??>
${customer.infoBirthday}
</#if>/>
<input type=hidden id=salary value=
<#if customer.infoSalary??>
${customer.infoSalary}
</#if>/>

<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/scroller.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/userInfo.js"></script>

<script type="text/javascript" src="${basePath}/js/customer/dateclass.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/yearclass.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/education.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        loadProvice();
        selectLocationOption($("#Province").val(), $("#City").val(), $("#County").val(), $("#Street").val(), 'infoProvince', 'infoCity', 'infoCounty', 'infoStreet');
        selectBirthday($("#birth").val());
        selectSalary($("#salary").val());

    });

    var selYear = window.document.getElementById("year");
    var selMonth = window.document.getElementById("month");
    var selDay = window.document.getElementById("day");
    new DateSelector(selYear, selMonth, selDay);


    function win() {
        var _wd = $(window).width();
        var _hd = $(window).height();
        $(".dialog").css("top", (_hd - $(".dialog").height()) / 2).css("left", (_wd - $(".dialog").width()) / 2);
    }
    ;
</script>
</@htmlBody>
