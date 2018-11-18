<#include "../include/common.ftl">
<@htmlHead title='消息中心-${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<link type="text/css" rel="stylesheet" href="${basePath}/css/jsuggest.css" />
<style type="text/css">
    .d_lk .ok_btn, .d_lk .cancel_btn {display:inline-block; zoom:1; *display:inline; background:url(images/g_search_btn.gif) no-repeat; width:62px; height:24px; color:#fff; text-align:center; line-height:24px; font-weight:700;}
    .create_albums dt {float:left; width:70px; line-height:30px;}
    .create_albums dd {margin-bottom:15px; margin-left:65px; line-height:30px;}
    .ca_text {width:228px; height:28px; border:1px solid #e1e1e1; padding:0 5px;line-height:28px;}
    .ca_txa {width:228px; height:58px; border:1px solid #e1e1e1; padding:5px;}
    .create_albums label {display:block; line-height:20px;}
    .comment_list .user_detail, .mood_list .user_detail {top:17px; left:0;}
    .create_albums .LV_validation_message {position:static;}
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

  <div class="container pt20">
    	<div class="msg_wp clearfix">
        	<#include "../common/msgleft.ftl">
              <div class="msg_right fr">
            	<h4 class="f14 fb">已发私信</h4>
                <div class="operate_wp mt20 clearfix">
                	<div class="rowElem mt5 clearfix fl">
                    	<input type="checkbox" onclick="switchAll(this,'messageId')"/>
                        <label>全选</label>
                    </div><!--/rowElem-->
                    <a class="fl ml5 opBt" href="javascript:delbatchsendmmsg();">删除</a>
                    <a class="fr opBt open_window" href="javascript:sendmessage();">发私信</a>
                </div><!--/operate_wp-->
                <form id="msgForm" method="post" action="">
	                <ul class="sys_msg_list msg_list">
	                    <#if pb.list??>
	                      <#list pb.list as message>
	                        <li class="clearfix">
	                    	<div class="rowElem clearfix fl"><input type="checkbox" name="messageId"  value="${message.messageId}"/></div>
	                        <a class="fl ml5" href="${basePath}/customerhomepage/${message.messageRecCustomerId}.html" target="_blank">
	                        <#if (message.customerHead)?? && (message.customerHead)!="">
		                        <img width="50" height="50" alt="${message.customerName}" src="${message.customerHead}" /></a>
	                         <#else>
	                      		 <img width="50" height="50" alt="${message.customerName}" src="${basePath}/images/default_head3.jpg" /></a>
	                        </#if>
	                        <div class="smsg_cont fl ml10">
	                        	<div class="clearfix"><a class="b_blue fl" href="${basePath}/customerhomepage/${message.messageRecCustomerId}.html" target="_blank">${(message.customerName)!''}</a><span class="fr b_grey">${(message.messageCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span></div>
	                            <div class="clearfix mt15 lh150">
	                            	<p class="fl smsg_word">${(message.messageContent)!''}</p>
	                                <a class="fr b_blue" href="javascript:delsendmmsg(${message.messageId})">删除</a>
	                            </div>
	                        </div><!--/smsg_cont-->
	                    </li>
	                    </#list>
	                  </#if>
	                </ul><!--/sys_msg_list-->
                </form>
                <#if (pb.list?size>0)>
                <form action="sendlettermsg.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
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
            </div><!--/msg_right-->
        </div><!--/msg_wp-->
    </div>
    
 <div class="mask1"></div>
	<div class="dialog1 dia1" style="width: 450px; height: 250px;">
		<a class="close" href="javascript:cls1();"></a>
		<h4>私信</h4>
		<form id="sendForm" method="post" action="">
			<dl class="create_albums mt20 ml50 clearfix">
				<dt>发给：</dt>
				<input type="hidden" name="messageAuthorId" id="messageAuthorId" value="${customer.customerId}" />
				<input type="hidden" name="messageRecCustomerId" id="messageRecCustomerId"  />
				<dd>
					<input class="ca_text" type="text" id="customerUsername" name="customerUsername"/>
				</dd>
				<dt>内容：</dt>
				<dd>
					<textarea class="ca_txa" maxlength="300" onkeyup="length300();" id="messageContent" name="messageContent"></textarea>
				</dd></br>
				<span id="ab">还可以输300字</span>
			</dl>
			<!--/create_albums-->  
		</form>
		<div class="tc mt10 d_lk">
			<a class="ok_btn" href="javascript:dosendmessage();">确定</a><a
				class="cancel_btn" href="javascript:cls1();">取消</a>
		</div>
	</div>
	<!--/dialog-->

	<div class="dialog1 dia2" style="width: 300px; height: 120px;">
		<a class="close" href="javascript:cls1();" onclick="page('1')"></a>
		<h4>提示</h4>
		<div class="create_albums mt20 ml50 clearfix">
			<span id="message"></span>
		</div>
		<!--/create_albums-->
	</div>
	
      <div class="dialog1 dia3" style="width:500px; padding:30px;">
    	<a class="r_close" href="javascript:;" onclick="cls1()"></a>
        <h3 class="f14 fb mt10">回复内容</h3>
	        <dl class="address_info clearfix mt20" id="met">
	       </dl>
    </div><!--/dia2-->
	<!--/dialog-->   
    
	<#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/message/message_comm.js"></script>
<script type="text/javascript" src="${basePath}/js/jSuggest.js"></script>
<script type="text/javascript">
			$(function() {
				 $(".rowElem").jqTransform();
			});
			$(function(){
				  $('#customerUsername').jSuggest({
				    autoChange : true,
					url : "findcustomername.htm",
					type : "POST",
					data:'customerUsername'
				  });
			 });
		 $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
        });
</script>
</@htmlBody>