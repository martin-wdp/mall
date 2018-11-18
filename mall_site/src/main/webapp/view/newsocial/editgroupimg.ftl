<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>图片编辑-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
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
<div class="wrapper clearfix">
	 	<form id="photoForm" method="post">
	    <input type="hidden" name="groupId" value="${groupId}" id="groupId">   
	    <input type="hidden" value="${basePath}" id="basePath"/>	
    	<ul class="imgs_list clearfix" id="container" style="width:1220px;">
           <#if imglist??>
             <#list imglist as img>
	        	<li class="item_a"> 
	        	<input type="hidden" name="groupImgId" value="${img.groupImgId}">   	
	            	<div class="img_box"><img alt="${img.groupImgTitle}" width="200px" height="150px" src="${img.groupImgUrl}"></div>
	                <div class="edit_wp mt15">标题：<input type="text" style="width:150px;" name="groupImgTitle" class="ca_text" maxlength="15" placeholder="最多15个字" 
	                value='<#if (img.groupImgTitle)?length gt 15> ${(img.groupImgTitle)?substring(0,15)}<#else>${img.groupImgTitle}</#if>'/></div> 
	                <div class="edit_wp mt15">描述：<textarea class="edit_txa" maxlength="70" placeholder="最多70个字" name="groupImgDes"></textarea></div>  
	            </li>
             </#list>
           </#if>   
        </ul><!--/img_list-->
        <a href="javascript:void(0);" onclick="savephoto();"><input class="save_pub" type="button" value="保存"/></a>
        </form>
    </div>

  <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/masonry.pkgd.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_img.js"></script>
<script type="text/javascript">
				$(function() {
					$(".rowElem").jqTransform();
				});
		       	$(function(){
					var $container = $('#container');
					$container.masonry({
					  columnWidth: 238,
					  gutter: 5,
					  itemSelector: '.item_a'
					});
					});	
					
			$(function(){
			//选中首页社区
			$(".sort_list li").removeClass("cur");
			$(".sort_list li:eq(1)").addClass("cur");
        });	
</script>
</body>
</html>


