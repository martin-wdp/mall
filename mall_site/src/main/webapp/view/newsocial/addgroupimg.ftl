<#include "../include/common.ftl">
<@htmlHead title='上传图片-${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/plugins/uploadify/uploadify.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/plupload/queue/css/jquery.plupload.queue.css" />
<style type="text/css">
    .u_btn {display:inline-block; zoom:1; *display:inline; background:url(${basePath}/images/btn_bg.gif) no-repeat; width:75px; height:24px; line-height:24px; text-align:center; color:#fff;
        font-weight:700; margin:0 10px;}
    .u_btn:hover {color:#fff;}
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
<input type="hidden" value="${basePath}" id="basePath"/>
<div class="container pt20" style="min-height:450px;"> 
    <div class="love_car">
        <h2>小组：${(group.groupName)!''}</h2>
   		<br/><span style="color:#df242a">注：上传的图片大小在1M以内 </sapn>
	    <div class="uploadBox mt20">
			<div id="fileupload" class="uploadify" style="height: 30px; width: 120px;">
			</div>
		</div>
    </div><!--love_car-->
</div>
<div class="container">
	<div class="love_bt">
        <a href="javascript:$('#fileupload').uploadify('upload','*')">上传</a>
        <a href="${basePath}/groupdetail/${group.groupId}.html">返回</a>
    </div>
</div>
<#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/masonry.pkgd.min.js"></script>
<script type="text/javascript" src="${basePath}/plugins/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript">     
	$(function() {
	    $("#fileupload").uploadify({
	    	'removeCompleted' : false,
	        'height'        : 30,
	        'swf'           : '${basePath}/plugins/uploadify/uploadify.swf',
	        'uploader'      : '${basePath}/uploadgroupimg/${group.groupId}-${cusId!''}.html',
	        'width'         : 120,
	        'fileObjName'   : 'filedata',
	        'buttonText'    : '选择图片',
	        'auto'          : false,
	        'multi'         : true,
	        'onQueueComplete' : function(queueData) { 
				 window.location.href= '${basePath}/editgroupnewupload/${group.groupId}-${cusId!''}-'+queueData.uploadsSuccessful+'.html';
	        } 
	    });
	});
	 $(function(){
			//选中首页社区
			$(".sort_list li").removeClass("cur");
			$(".sort_list li:eq(1)").addClass("cur");
        });	
</script>
</@htmlBody>