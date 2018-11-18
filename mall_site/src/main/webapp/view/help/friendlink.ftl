<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <!--<title>福气商城</title>-->
    <title>福气商城 - 友情链接</title>
    <!--DEMO QQ LOGIN <meta property="qc:admins" content="27416764457661670116375" />-->

    <!-- SHOP QQ LOGIN -->
    <meta property="qc:admins" content="27416763170661670116375" />
    <!-- SINA LOGIN -->
    <meta property="wb:webmaster" content="0c64ebf2c46c59c1" />

    <meta name="Keywords" content="${topmap.seo.meteDes}">
    <meta name="description" content="${topmap.seo.meteKey}">
    <link rel="stylesheet" type="text/css" href="http://qpmall.qianmi.com/index_two/css/base.min.css" />
    <link rel="stylesheet" type="text/css" href="http://qpmall.qianmi.com/index_two/css/index.css" />
    <#if (topmap.systembase.bsetHotline)??>
        <link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
    <#else>
        <link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
    </#if>

    <style type="text/css">
        .link_tit{height:30px;line-height:30px;background:#f7f7f7;padding-left:10px; font-family:\5fae\8f6f\96c5\9ed1;font-weight:normal;}

        .link_content{border:1px solid #ddd;overflow:hidden;zoom:1}
        .link_content a{color:#3265cb}
        .link_list{padding:10px 30px 0;}
        .link_list:after {content:".";display:block;height:0;clear:both;visibility:hidden;}
        .link_list_img{padding:10px;}
        .link_list_img:after {content:".";display:block;height:0;clear:both;visibility:hidden;}
        .link_list{display:inline-table;}
        .link_list_img{display:inline-table;}
        /* Hides from IE-mac \*/*html ..link_list {
                                    height:1%;
                                }
        ..link_list {
            display:block;
        }
        /* End hide from IE-mac */*+html ..link_list {
                                      min-height:1%;
                                  }

        .link_list li{padding-left:28px;float:left;width:157px;height:32px;line-height:32px; background:url(http://qpmall.qianmi.com/index_two/images/sort_arrow.gif) no-repeat 8px 50%;border-bottom:1px dotted #ccc; white-space:nowrap; overflow:hidden}

        .link_list li a{color:#005ea7;}

        .link_list_img li{float:left;position:relative;width:166px;height:99px;white-space:nowrap; overflow:hidden;border:1px solid #fff;}
        .link_list_img li:hover{border:1px solid #EB6122;}
        .link_list_img li img{width:157px;}
        .link_list_img li span{position:absolute;bottom:0;left:0;display:block;width:100%;height:30px;line-height:30px;text-align:center;color:#555;}
        .link_list_img li:hover span{background:#EB6122;color:#fff;}
    </style>
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/index.js"></script>
</head>


<body>
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
<br/>
<div style="width:1200px;margin:0 auto;">
    <div class="link_box">
        <div class="link_content">
            <h3 class="link_tit">
                友情链接
            </h3>
            <ul class="link_list">
            <#if map??>
                <#list map.pb.list as friendLink>
                    <#if friendLink.linkLogo??>
                    <#else>
                        <li>
                            <a href="${friendLink.linkUrl}" target="_blank">
                            ${friendLink.linkName}
                            </a>
                        </li>
                    </#if>
                </#list>
            </#if>
            </ul>
            <ul class="link_list_img">
            <#if map??>
                <#list map.fls as fl>
                    <#if fl.linkLogo?? && fl.isHidden=="1">
                        <li>
                            <a href="${fl.linkUrl}">
                                <img alt="" src="<#if fl.linkLogo??>${fl.linkLogo}</#if>">
                                <span>${fl.linkName}</span>
                            </a>
                        </li>
                    </#if>
                </#list>
            </#if>
            </ul>
        </div>
    </div>

    <#--<div class="link_box mt10">-->
        <#--<div class="link_content">-->

        <#--</div>-->
    <#--</div>-->

</div>
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
    <#else>
        <#include "../index/newbottom.ftl" />
    </#if>
</#if>

</body>
</html>
