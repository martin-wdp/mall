<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<link rel="shortcut icon" href="${basePath}/favicon.ico" /><title>他的图库-摆摆网</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 

<style type="text/css">
 .d_lk .ok_btn, .d_lk .cancel_btn {display:inline-block; zoom:1; *display:inline; background:url(../images/g_search_btn.gif) no-repeat; width:62px; height:24px; color:#fff; text-align:center; line-height:24px; font-weight:700;}
.create_albums dt {float:left; width:70px; line-height:30px;}
.create_albums dd {margin-bottom:15px; margin-left:65px; line-height:30px;}
.ca_text {width:228px; height:28px; border:1px solid #e1e1e1; padding:0 5px;line-height:28px;} 
.ca_txa {width:228px; height:58px; border:1px solid #e1e1e1; padding:5px;}
.create_albums label {display:block; line-height:20px;}
.comment_list .user_detail, .mood_list .user_detail {top:17px; left:0;}
.create_albums .LV_validation_message {position:static;}
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
<div class="w1200 mt10 mb10">
<#include "common/hiscushomepage.ftl">
    <div class="w160 fr">
    	  <#if advlist??>
			<#list advlist as adv>
	       	<div class="ad mb10"><a href="${adv.advUrl}"><img src="${baseUrl}${adv.advImg}" width="160" height="252" alt="${adv.advName}"/></a></div>
	        </#list>
	        <#else>
			    <div class="ad mb10">
					<a href="javascript:void(0)"><img alt="" src="${basePath}/images/m_images_04.jpg" /></a>
				</div>
				<div class="ad mb10">
					<a href="javascript:void(0)"><img alt="" src="${basePath}/images/m_images_04.jpg" /></a>
				</div>
	      </#if>
    </div>
    <div class="w1030 fl">
    	<div class="m_map_design mb10">
        	<div class="ta_shequ">
            	<ul>
                	<li><a href="${basePath}/customerhomepage/${fanscustomerId}.html">TA的户型图</a></li>
                    <li><a href="${basePath}/hisprojectlist/${fanscustomerId}.html">TA的设计</a></li>
                    <li><a href="${basePath}/hisprojectthreelist/${fanscustomerId}.html">下载的3D样板房</a></li>
                    <li class="cur"><a href="${basePath}/hisgallerylist/${fanscustomerId}.html">TA的图库</a></li>
                    <li><a href="${basePath}/hiscommunity/${fanscustomerId}.html">TA的社区</a></li>
                </ul>
            </div>
            <div class="m_design_content">
            <div class="m_design_layout">
                	<div class="m_gallery">
                        <div class="m_gallery_list pt20">
                              <#if pb.list??>
	                            	<#list pb.list as pic>
			                            <div class="m_gallery_pic_item">
			                                <div class="pic">
			                                    <a href="javascript:void(0)" onclick="showGalleryHome('.m_gallery_out','<#if pic.galleryUrl??>${baseUrl}${pic.galleryUrl}</#if>','<#if pic.galleryName??>${pic.galleryName}</#if>','${pic.galleryCreateTime?string("yyyy-MM-dd HH:mm:ss")}');"><img alt="" src="<#if pic.galleryUrl??>${baseUrl}${pic.galleryUrl}</#if>" width="230" height="200" /><i>&nbsp;</i></a>
			                                </div>
			                                <div class="btns">
			                                    <h4>${pic.galleryName}</h4>
			                                </div>
			                            </div>
			                        </#list>
	                          </#if>
                            <div class="cb"></div>
                            <#if (pb.list?size!=0)>
	                            <#import "../pagination/pageBean.ftl" as page>
	                            <@page.pagination pb />
		                     </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /m_map_design -->
    </div>
    <div class="cb"></div>
</div>

<div class="m_gallery_out" style="display:none;">
	<div class="title">
    	<div class="btns">
        	<!--<a href="#">编辑</a> 
            <a href="#">删除</a>-->
        </div>
        <a class="close" href="javascript:void(0);" onclick="$('.m_gallery_out').hide();$('.dialog_bg').hide();"></a>
    </div> 
    <div class="body">      
    	<div class="m_gallery_cont">
        	<div class="pic fl" id="c_div"><img class="large_pic" style="display:block;max-width:946px;" id="c_img" alt=""  src=""  /></div>
            <div class="words fl ml10">
            	<h4 class="v_title">Sandy_707</h4>
                <p>图片属性</p> 
                <ul>
                    <li>原图尺寸：<span class="v_size">800 × 600</span></li>
                    <li><!--原图位置：我的图库--></li>
                    <li>上传时间：<span  class="v_time">2014-06-18 12:23</span></li>
                </ul>
            </div> 
            <div class="cb"></div>
        </div>
        <div class="m_gallery_thumbs">
        	<a href="javascript:void(0);" class="m_gallery_thumb_l"></a>
            <a href="javascript:void(0);" class="m_gallery_thumb_r"></a>
            <div class="m_gallery_tbumb_list">
              
            	<ul> 
            	 <#if pb.list??> 
                    <#list pb.list as pic>
                	<li><a href="<#if pic.galleryUrl??>${baseUrl}${pic.galleryUrl}</#if>" target="_blank" data-myName="${pic.galleryName}" data-myTime="${pic.galleryCreateTime?string("yyyy-MM-dd HH:mm:ss")}">
                		<img alt="" src="<#if pic.galleryUrl??>${baseUrl}${pic.galleryUrl}</#if>" width="132" height="80" />
                	</a></li> 
              		</#list>
                </#if> 
                </ul>
            </div>
        </div>
    </div>
</div><!-- /m_gallery_out -->

  <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
 <div class="mask1"></div>
 <div class="dialog1 dia1" style="width:450px; height:220px;">
	<a class="close" href="javascript:cls1();"></a>
    <h4>私信</h4>
    <form id="sendForm" method="post">
    <dl class="create_albums mt20 ml20 clearfix">
    	<dt>发给：</dt>
    		<input type="hidden" name="messageAuthorId" value="${cusId!'' }"/>
    		<input type="hidden" name="messageRecCustomerId" value="${(fanscustomerId)!'' }"/>
    		
        <dd><input class="ca_text" type="text" id="" name="customerUsername" readonly value="${map.cc.customerNickname}"/></dd>
        <dt>内容：</dt>
        <dd><textarea class="ca_txa" id="messageContent" maxlength="300" onkeyup="length300();" name="messageContent"></textarea><span id="ab">可输入300字</span></dd>
       
    </dl><!--/create_albums--> 
    </form>
    <div class="tc mt10 d_lk"><a class="ok_btn" href="javascript:dosendmessage();">确定</a><a class="cancel_btn" href="javascript:cls1();">取消</a></div>
</div><!--/dialog-->

 <div class="dialog1 dia2"  style="width:300px; height:120px;">
	<a class="close" href="javascript:cls1();"></a> 
    <h4>提示</h4>
    <div class="create_albums mt20 ml50 clearfix">
    	<span id="message"></span>
    </div><!--/create_albums-->
</div><!--/dialog-->
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/gallery.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/bbw_common.js"></script>
<script type="text/javascript" src="${basePath}/js/message/fans_comm.js"></script>

<script type="text/javascript">  

$(function(){ 

	var mt = ($('#c_div').height() - $('#c_img').height())/2 + 'px';
	$('#c_img').css('marginTop',mt);
	$('.m_gallery_tbumb_list a').click(function(){
		$('#c_img').load(function(){
			if($('#c_img').height()>$('#c_div').height()){
				var mt = 0 + 'px';
				}
				else
				{
				var mt = ($('#c_div').height() - $('#c_img').height())/2 + 'px';
			    $('#c_img').css('marginTop',mt);	
				}
				
			
		});	
	});
	
	
	$(".m_gallery_out").hide();
	$("#delDiv").hide();  
	$("#usDiv").hide();
	$(".user_menu div:eq(2) ul li:eq(1)").addClass("cur");
	
	
	$('.m_gallery_tbumb_list a').click(function(){
		$('.large_pic').attr('src',$(this).attr('href'));
		$('.words .v_title').html($(this).attr('data-myName'));
		$('.words .v_time').html($(this).attr('data-myTime'));
		var i = new Image(); //新建一个图片对象 
		i.src = $(this).attr('href'); 
		$('.words .v_size').html(i.width+" x "+i.height);
		return false; 
	});
	
	 
});


function quedel(){
	$("#delForm").submit();
}

function showGalleryHome(obj,url,name,time){
var mt = ($('#c_div').height() - $('#c_img').height())/2 + 'px';
	$('#c_img').css('marginTop',mt);
	$('.m_gallery_tbumb_list a').click(function(){
		$('#c_img').load(function(){
			if($('#c_img').height()>$('#c_div').height()){
				var mt = 0 + 'px';
				}
				else
				{
				var mt = ($('#c_div').height() - $('#c_img').height())/2 + 'px';
			    $('#c_img').css('marginTop',mt);	
				}
				
			
		});	 
	});
	

		$('.words .v_title').html(name);
		$('.words .v_time').html(time);
		var i = new Image(); //新建一个图片对象 
		i.src = url; 
		$('.words .v_size').html(i.width+" x "+i.height);
		showGallery(obj,url); 
}

  
function queall(){
	$("#listForm").submit();
}

 $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });	
</script> 
</body>
</html>
