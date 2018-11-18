<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<style type="text/css">
.mutual_list li {
width: 250px;
height: 70px;
padding: 10px;
}
.fb span{
font-weight:700!important;
}
</style>
</head>
<body>
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
<input type="hidden" id="customerId" value="${cusId!''}">
<input type="hidden" id="fansFlag" value="${fansFlag!''}">
<div class="wrapper clearfix">
        <div class="mutual_op clearfix">
            <div class="mutual_opt fl clearfix">
                <div class="rowElem clearfix fl mr10 <#if fansFlag=='2'>fb</#if>" ><a href="javascript:void(0);"onclick="javascript:fantofan(); return false;"><input  type="button" value="互相关注"/></a></div>
                <div class="rowElem clearfix fl mr10 <#if fansFlag=='1'>fb</#if>"><a href="javascript:void(0);" onclick="javascript:tofan(); return false;"><input  type="button" value="<#if customerId?? && cusId==customerId>我的</#if><#if !customerId?? || cusId!=customerId>他的</#if>关注" /></a></div>
                <div class="rowElem clearfix fl mr10 <#if fansFlag=='0'>fb</#if>"><a href="javascript:void(0);" onclick="javascript:fanto(); return false;"><input  type="button" value="<#if customerId?? && cusId==customerId>我的</#if><#if !customerId?? || cusId!=customerId>他的</#if>粉丝" /></a></div>
            </div><!--/mutual_opt-->
            <#--<div class="mutual_search fr clearfix">
                <div class="rowElem clearfix fl"><input type="text" placeHolder="输入昵称" name="customerNickName"/></div>
                <div class="rowElem clearfix fl ml5" style="margin-top:-1px;"><input type="submit" value="查找" /></div>
            </div><!--/mutual_search-->
        </div><!--/mutual_op-->
       
       <form id="fansForm" method="post" action=""> 
        <#if pb.list?size gt 0>
         <ul class="mutual_list mt20 clearfix">
            <#list pb.list as fans>
              <li class="clearfix">
                <#if customerId?? && cusId==customerId><div class="rowElem clearfix fl"><input type="checkbox" name="customerIds" value="${fans.customerId},${fans.fansFlag}"/></div></#if>
                <a class="fl ml5" href="${basePath}/customerhomepage/${fans.customerId}.html" target="_blank">
                <#if (fans.infoHeadimg)?? && (fans.infoHeadimg)!="">
                <img alt="" src="${fans.infoHeadimg}" width="78px" height="78px"/>
				<#else>
                <img alt="" src="${basePath}/images/default_head3.jpg" width="50px" height="50px"/>
                </#if>
                </a>
                <div class="mta_info fl ml10">
                    <a class="b_blue" href="${basePath}/customerhomepage/${fans.customerId}.html" target="_blank">${fans.customerName}</a>
                    <p class="mt10">${(fans.proName)!''}&nbsp;${(fans.cityName)!''}</p>
                   <#if customerId??>
                     <#if fans.fansFlag??>
	                    <#if fans.fansFlag=='1'><div class="followed mt10">已关注<a href="javascript:cancelguanzhu('${fans.customerId }');">取消</a></div></#if>
	                    <#if fans.fansFlag=='2'><div class="mutual mt10">互相关注<a href="javascript:cancelguanz('${fans.customerId }');">取消</a></div></#if>
	                 </#if>  
	                    <#if !(fans.fansFlag)?? || fans.fansFlag=='0'><a class="attention mt10" href="javascript:guanzhu('${fans.customerId }');">加关注</a></#if>
                   </#if>
                    <#if !customerId??>
                    <a class="attention mt10" href="javascript:guanzhu('${fans.customerId }');">加关注</a>
                    </#if>
                </div><!--/mta_info-->
            </li>
          </#list>
        </ul><!--/mutual_list-->
        </#if>
      </form>  
        
           <div class="mutual_opt fl clearfix">
             <#if customerId?? && cusId==customerId>
              <div class="rowElem clearfix fl mr10">
                <a href="javascript:void(0);" onclick="javascript:outallfans(); return false;">
                <input type="button" value="取消关注" /></a></div>
             </#if>
            </div><!--/mutual_opt-->
            <#if (pb.list?size>0)>
                <form action="mymutual.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
                 <input name="flagFlag" id= "page_keyword" type="hidden"/>
                  <div class="pages tc mt30">
                   <!--判断上一页-->
                     <a class="pg_prev" 
                      <#if ((pb.pageNo)>1)>
                       href="javascript:page('${pb.pageNo-1}')"
                       <#else>href="javascript:void(0)" style="color:#999;visibility:hidden"</#if>
                       >&lt;上一页</a>
                     <#if ((pb.startNo)>1)> 
                      <a href="javascript:page('1');" >1</a>
									...
                     </#if>
                     <#list pb.startNo..pb.endNo as i>
                      <a <#if pb.pageNo==i> class="cur"</#if> href="javascript:page('${i}');">${i}</a>
                     </#list>
                     <#if (pb.totalPages > pb.endNo)>
                        ...
                        <a href="javascript:page('${pb.totalPages}')"${pb.totalPages}></a>
                     </#if>
                     <!--判断下一页-->
                     <a class="pg_next" 
                       <#if ((pb.pageNo+1) > pb.totalPages)>
                       href="javascript:void(0)" style="color:#999;visibility:hidden"
                       <#else>href="javascript:page('${pb.pageNo+1}')"
                       </#if>>下一页&gt;
                     </a>
                  </div>
                 </form>
                </#if>
      </div><!--/wrapper-->
    
   <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
	<div class="mask1"></div>
<div class="dialog1 dia2"  style="width:300px; height:120px;">
	<a class="close" href="javascript:cls1();"></a> 
    <h4>提示</h4>
    <div class="create_albums mt20 ml50 clearfix">
    	<span id="message"></span>
    </div><!--/create_albums-->
</div><!--/dialog-->
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/message/fans_comm.js"></script>

    <script type="text/javascript">
        $(function() {
            $(".rowElem").jqTransform();
        });
        
         $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });
    </script>
</body>
</html>
