<!DOCTYPE html>
<html>
<head>
	<#assign basePath=request.contextPath>
	<meta charset="UTF-8">
	<!--<title>${topmap.systembase.bsetName!''}</title>-->
	<title>${topmap.seo.mete!''}</title>
	<!--DEMO QQ LOGIN <meta property="qc:admins" content="27416764457661670116375" />-->
	
	<!-- SHOP QQ LOGIN -->
	<meta property="qc:admins" content="27416763170661670116375" />
	<!-- SINA LOGIN -->
	<meta property="wb:webmaster" content="0c64ebf2c46c59c1" />
	
	<meta name="Keywords" content="${(topmap.seo.meteKey)!''}">
	<meta name="description" content="${(topmap.seo.meteDes)!''}">
	<#if (topmap.systembase.bsetHotline)??>
		<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
	<#else>
		<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
	</#if>
	<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/index.css" />
</head>
<body>

<#include "newheader.ftl"/>
<#include "index2_ftl.ftl"/>

		<div class="store_club clearfix">
            <div class="club_box fl">
                <h2>热门晒单</h2>
                <div class="club_cont mt5">
                    <ul class="club_list clearfix">
                    <#if shares?? && (shares?size>0)>
                    <#list shares as share>
                        <li>
                            <a class="p_img fl" href="${basePath}/share/detail-${share.shareId?c}.html"><img class="lazy" alt="" data-original="${(share.imageList[0].imageName)!''}"  width="75" height="60" /></a>
                            <div class="p_info fl ml20">
                                <div class="p_name"><a href="${basePath}/share/detail-${share.shareId?c}.html">
                                	<#if (share.shareTitle?length>14)>
                                		${share.shareTitle?substring(0,14)}
                                	<#else>
                                		${share.shareTitle!''}
                                	</#if>
                                </a></div>
                                <p>
                                	<#if (share.shareContent?length>28)>
                                		${share.shareContent?substring(0,28)}
                                	<#else>
                                		${share.shareContent!''}
                                	</#if>
                                	</p>
                            </div><!--/p_info-->
                        </li>
                    </#list>
                    </#if>
                        
                    </ul><!--/club_list-->
                </div><!--/club_cont-->
            </div><!--/club_box-->

            <div class="club_box fr">
                <h2>${temp.standby5!'热门活动'}</h2>
                <div class="club_cont mt5">
                    <ul class="club_list clearfix">
                    	<#if onepages?? && (onepages?size>0)>
                    	<#list onepages as op>
                    		<#if (op_index>=0) && (op_index<8)>
                        <li>
                        	<#--
                            <a class="p_img fl" href="showOnePage.htm?infoOPId=${op.infoOPId?c}"><img class="lazy" alt="" data-original="${op.imgSrc!''}"  width="75" height="60" /></a>
                            <div class="p_info fl ml20">
                                <div class="p_name"><a href="showOnePage.htm?infoOPId=${op.infoOPId?c}">${op.title!''}</a></div>
                                <p>${op.description!''}</p>
                            </div>
                        	-->
                        	<a class="p_img fl" href="${basePath}/onepage/${op.infoOPId?c}.html"><img class="lazy" alt="" data-original="${op.imgSrc!''}"  width="75" height="60" /></a>
                            <div class="p_info fl ml20">
                                <div class="p_name"><a href="${basePath}/onepage/${op.infoOPId?c}.html">${op.title!''}</a></div>
                                <p>${op.description!''}</p>
                            </div><!--/p_info-->
                        </li>
                    		</#if>
                    	</#list>
                    	</#if>
                    </ul><!--/club_list-->
                </div><!--/club_cont-->
            </div><!--/club_box-->
        </div><!--/store_club-->
    </div><!--/container-->
    <#include "newbottom.ftl"/>
</body>
</html>