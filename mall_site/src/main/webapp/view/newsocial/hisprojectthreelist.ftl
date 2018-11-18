<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<link rel="shortcut icon" href="${basePath}/favicon.ico" /><title>他的3D样板房-摆摆网</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/> 

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
                    <li class="cur"><a href="${basePath}/hisprojectthreelist/${fanscustomerId}.html">下载的3D样板房</a></li>
                    <li><a href="${basePath}/hisgallerylist/${fanscustomerId}.html">TA的图库</a></li>
                    <li><a href="${basePath}/hiscommunity/${fanscustomerId}.html">TA的社区</a></li>
                </ul>
            </div>
            <div class="m_design_content">
            	<div class="m_design_layout">
                	<div class="m_design_ctrl">
                    </div>
                      <div class="house_chart mt20">
                      <#if pb.list??>
                   
                   	  <#list pb.list as pro>
                   	   <#assign pindex=0>
                    	<div class="m_chart_item">
                        	<div class="img fl">
                        		<#if pro.piclist??> 
                        			<#list pro.piclist as pic>
                        				<#if pic.isImprotant=='1'>
                        				<#assign pindex=pic_index>
                            			 <a href="javascript:void(0)"><img  title="<#if pic.galleryName??>${pic.galleryName}</#if>" alt="<#if pic.galleryName??>${pic.galleryName}</#if>" src="<#if pic.galleryUrl??>${baseUrl}${pic.galleryUrl}</#if>" width="210" height="210" /></a>
                            		 	</#if>
                            		</#list> 
                            	</#if> 
                            </div>
                            <div class="word2 fl ml10">
                            	<h2>${pro.myProjectName}<span>已装修</span></h2>
                                <p>修改：<#if pro.modifyTime??>${pro.modifyTime?string("yyyy-MM-dd HH:mm:ss")}</#if></p>
                                <div class="imgs">
                                <#if pro.piclist??> 
                        			<#list pro.piclist as pic>
                        				<#if pic.isImprotant=='0' && pindex gt 5 && pic_index lt 5>
		                                	<a href="#"><img title="<#if pic.galleryName??>${pic.galleryName}</#if>" alt="<#if pic.galleryName??>${pic.galleryName}</#if>" src="<#if pic.galleryUrl??>${baseUrl}${pic.galleryUrl}</#if>" width="120" height="100"/></a>
                                    	</#if>
                        				<#if pic.isImprotant=='0' && pindex lt 5 && pic_index lt 6>
		                                	<a href="#"><img title="<#if pic.galleryName??>${pic.galleryName}</#if>" alt="<#if pic.galleryName??>${pic.galleryName}</#if>" src="<#if pic.galleryUrl??>${baseUrl}${pic.galleryUrl}</#if>" width="120" height="100"/></a>
                                    	</#if>
                            		</#list>  
                            	</#if> 
                                    <div class="cb"></div>
                                </div>
                            </div>
                            <div class="btns2 fl">
                            <a class="common_btn_red" href="javascript:void(0)" onclick="saveprojectthree(${pro.myProjectId})"><span>保存到我的3D样板房</span></a>
                            </div>
                            <div class="tips">
                            	<ul>
                                <li>创建者：<#if pro.oldNickName??&&pro.oldNickName!=''><a href="${basePath}/customerhomepage/${pro.oldCustomerId}.html">${pro.oldNickName}</a><#else>自己</#if></li>
                                    <li>发布时间：<#if pro.createTime??>${pro.createTime?string("yyyy-MM-dd HH:mm:ss")}</#if></li> 
                                </ul>
                            </div>
                            <div class="cb"></div>
                        </div>
                       </#list>
                    </#if>    
                    </div><!-- /house_chart -->
                       <#if (pb.list?size!=0)>
	                            <#import "../pagination/pageBean.ftl" as page>
	                            <@page.pagination pb />
                     </#if>
                </div>
            </div>
        </div><!-- /m_map_design -->
    </div>
    <div class="cb"></div>
</div>
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
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/message/fans_comm.js"></script>
<script type="text/javascript" src="${basePath}/js/login_dialog.js"></script>

<script type="text/javascript" >

function saveprojectthree(id){
     if(showLoginDialog(2)){
			$.ajax({
				url:"${basePath}/copytomyproject.htm",
				data:{myProjectId:id,isProject:1},
				success:function(data){
					if(data == 1){
						$("#message").html('保存成功！！！');
						dia(2);
					}else {
						$("#message").html('已保存过此样板房！！！');
						dia(2);
					}
				}
			});
		}
 }
 
  $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });
</script>
</body>
</html>

