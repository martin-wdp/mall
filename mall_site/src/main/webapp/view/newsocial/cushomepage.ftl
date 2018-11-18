<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>他的主页-${topmap.systembase.bsetName}</title>
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
                	<li class="cur"><a href="${basePath}/customerhomepage/${fanscustomerId}.html">TA的户型图</a></li>
                    <li><a href="${basePath}/hisprojectlist/${fanscustomerId}.html">TA的设计</a></li>
                    <li><a href="${basePath}/hisprojectthreelist/${fanscustomerId}.html">下载的3D样板房</a></li>
                    <li><a href="${basePath}/hisgallerylist/${fanscustomerId}.html">TA的图库</a></li>
                    <li><a href="${basePath}/hiscommunity/${fanscustomerId}.html">TA的社区</a></li>
                </ul>
            </div>
            <div class="m_design_content">
            	<div class="m_design_layout">
                	<div class="m_design_ctrl">
                    </div>
                    <div class="house_chart">
                       <#if pb.list??> 
                    	<#list pb.list as model>
                    	<div class="m_chart_item"> 
                        	<div class="btns fr">
                            	<a class="common_btn_red" href="javascript:void(0)" onclick="saveToMyModel(${model.myModelId})"><span>保存到我的户型图</span></a>
                            </div>
                        	<div class="img fl"> 
                            	<a href="javascript:void(0)"><img alt="" src="<#if model.myModelUrl??> ${baseUrl}${model.myModelUrl}</#if> " width="210" height="210"/></a>
                            </div>  
                            <div class="word fl ml10">   
                            	<h2>${(model.myModelName)!''}<span>已装修</span></h2>   
                                <p><#if model.modifyTime??>${model.modifyTime?string("yyyy-MM-dd HH:mm:ss")}</#if></p>          
                                <ul>   
                                	<li>面积：${(model.myModelArea)!''}平方米</li> 
                                    <li>位置：${(model.myModelPart)!''}</li>  
                                </ul>
                            </div>
                            <div class="tips">
                            	<ul>
                                	<li>原创者：<#if model.oldNickName??&&model.oldNickName!=''><a href="${basePath}/customerhomepage/${model.oldCustomerId}.html">${model.oldNickName}</a><#else>自己</#if></li>
                                    <li>发布时间：<#if model.createTime??>${model.createTime?string("yyyy-MM-dd HH:mm:ss")}</#if></li>
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
    <div class="create_albums mt20  clearfix" style="text-align:center; line-height:60px;">
    	<span id="message" ></span>
    </div><!--/create_albums-->
</div><!--/dialog-->

<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/message/fans_comm.js"></script>
<script type="text/javascript" src="${basePath}/js/login_dialog.js"></script>
<script type="text/javascript">
function saveToMyModel(id){
	if(showLoginDialog(2)){
		$.ajax({
			url:"${basePath}/copytomymodel.htm",
			data:{modelId:id},
			success:function(data){
					if(data == 1){
						$("#message").html('保存成功！！！');
						dia(2);
					}else {
						$("#message").html('已保存过此户型！！！');
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
        	
  function closeDialog(){
	$(".dialog").fadeOut();
	$(".mask").fadeOut();
	};
</script>

</body>
</html>

