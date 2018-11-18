<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!''}">
<link rel="stylesheet" href="${basePath}/index_seven/css/style.css"/>
<style>
    .dropdown-menu {display: block !important;}
    .customer_service:hover, .feedback:hover {/*background: #c33;*/}
    .customer_service:hover em, .feedback:hover em {font-size: 12px;position: relative;top: 3px;font-style: normal;}
    .customer_service b {/* background: url(${basePath}/index_seven/images/-01.png) #fff no-repeat center center !important;*/}
    .feedback b {/* background: url(${basePath}/index_seven/images/-02.png) #fff no-repeat center center !important;*/}
    .customer-box {position: absolute;background: #efefef;width: 146px;min-height: 150px;border: 1px solid #ccc;padding: 9px;right: 50px;display: none;}
    .sideBar .customer-box a {width: auto;height: auto;background-color: transparent;border: none;line-height: normal;}
    .close-cs {position: absolute !important;top: 10px;right: 10px;background: url(${basePath}/index_seven/images/agree_close1.gif) no-repeat;width: 10px !important;height: 10px !important;display: block;}
    .customer-box p {overflow: hidden;margin-top: 10px;}
    .customer-box p .qq_name {float: right;}
    .customer-box p .qq_img {float: left;}
    .sideBar a span {font-size: 12px !important;padding: 2px 5px;width: 28px !important;line-height: normal !important;}
    #wrapper{margin-bottom: 20px;}
</style>
</@htmlHead>
<@htmlBody>
<#--<#include "index7Tip.ftl">-->
<#include "newheader7.ftl">
<#include "index7.html"/>

<#--- 2015.10.12 wuyanbo 屏蔽晒家分享、热门商品 -->
<div class="sj_share clearfix" style="display: none;">
    <div class="sj_share_left">
        <div class="title">晒家分享</div>
        <div class="details">
        <#if shares?? && (shares?size>0)>
            <ul>
                <#list shares as share>
                    <#if (share_index>=0) &&(share_index<6)>
                        <li>
                            <div class="left s-img">
                                <a href="${basePath}/share/detail-${share.shareId?c}.html"><img src="${(share.goodsImg)!''}" width="120" height="120"></a>
                            </div>
                            <div class="right r-comment">
                                <a href="${basePath}/share/detail-${share.shareId?c}.html" class="title">
                                    <#if (share.shareTitle?length>14)>
                                		${share.shareTitle?substring(0,14)}
                                <#else>
                                    ${share.shareTitle!''}
                                    </#if>
                                </a>
                                <div class="com-detail">
                                    <span class="z_dot"></span>
                                    <#if (share.shareContent?length>28)>
                                    ${share.shareContent?substring(0,28)}
                                    <#else>
                                    ${share.shareContent!''}
                                    </#if>
                                    <span class="r_dot"></span>
                                </div>
                                <a href="${basePath}/share/detail-${share.shareId?c}.html" class="right">详细&gt;&gt;</a>
                            </div>
                        </li>
                    </#if>
                </#list>
            </ul>
        </#if>
        </div>
    </div><!--sj_share_left-->
    <div class="sj_share_rig">
        <div class="title">${temp.standby5!'热门活动'}</div>
        <div class="details">
            <div class="ac-con">
            <#if onepages?? && (onepages?size>0)>
                <ul>
                    <#list onepages as op>
                        <#if (op_index>=0) && (op_index<6)>
                            <li>
                                <a href="${basePath}/onepage/${op.infoOPId?c}.html"><img src="${op.imgSrc!''}" width="110" height="110"/></a>
                                <p><a href="${basePath}/onepage/${op.infoOPId?c}.html">${op.title!''}</a></p>
                                <div>${op.description!''}</div>
                            </li>
                        </#if>
                    </#list>
                </ul>
            </#if>
            </div>
        </div>
    </div><!--sj_share_rig-->
</div><!--sj_share-->
    </div><!--content-->
</div><!--wrapper-->

<#include "newbottom.ftl">
<#--2015-12-27 wuyanbo closed christmas-->
<#--<#include "christmas.ftl">-->
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?9f1197ed3343c16c00736b8aa6123f7a";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</@htmlBody>