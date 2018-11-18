<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .contErr p{width: 800px;height: 60px;line-height: 60px;padding-left: 70px;margin: 200px auto;background: url(/images/x_error.png) no-repeat left center;background-size: 60px 60px;font-size: 20px;color: red;}
</style>
</@htmlHead>
<@htmlBody>
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
<#if avs?? && (avs?size>0)>
    <#list avs as bigAdvert>
        <div style="text-align:center;"><a href="${bigAdvert.adverHref}"><img alt="" src="${bigAdvert.adverPath}" /></a></div>
    </#list>
</#if>
<div class="contErr">
    <p>支付失败！</p>
</div>
<!--这是底部-->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
            <#else>
                <#include "../index/newbottom.ftl" />
    </#if>
</#if>
<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${basePath}/js/cloud-zoom.1.0.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/group.js"></script>
</@htmlBody>