<#include "../../include/common.ftl">
<@htmlHead title='企业认证-${topmap.systembase.bsetName}'>
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/base.min.css"/>
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/pages.css"/>
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/qp_style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
</@htmlHead>
<@htmlBody>
<input type="hidden" name="CSRFToken" value="${token}">
<#--一引入头部 <#include "/index/topnew.ftl" />  -->
<#include "../../index/newtop7.ftl">
<#include "../../customer/newtop.ftl"/>
<div style="background: #f5f5f5;">
    <div class="container clearfix pt20 pb10">
        <!--new_member_left-->
    <#include "../../customer/newleftmenu.ftl"/>
        <div class="new_member-right">
            <div class="new_order_list">
                <div class="n-title">企业认证结果</div>
            <#--<div class="tagMenu order-menu pr">
                <ul class="menu clearfix">
                    <li class="current">企业认证结果</li>
                </ul>
                <div class="aco-link">
                    注：修改邮箱、手机请到<a href="${basePath}/customer/securitycenter.html">账户安全</a>
                </div>
            </div>-->
                <div class="content">
                    <div class="layout">
                        <div class="qp-fill-infor">
                        <#--<div style="float: left">填写验证信息</div>
                        <div class="title" style="float: right;font-size: 20px;"><input type="button" value="返回上一页"
                                                                       onclick="javascript:history.back();"/></div>-->
                            <p class="tit-top mb20">企业认证结果</p>
                            <table class="auther-list">
                                <tr>
                                    <td colspan="2" align="center"><h2>${enInfo}</h2>
                                    <br/> <br/>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2" align="center"><a href="${basePath}/${nextURL}" class="qpButton">${urlname}</a></td>
                                </tr>
                            </table>
                        </div>
                    </div>
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
<#include "../../index/newbottom.ftl" />
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/scroller.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/userInfo.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/dateclass.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/yearclass.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/education.js"></script>
</@htmlBody>