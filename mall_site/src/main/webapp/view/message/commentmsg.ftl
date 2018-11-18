<#include "../include/common.ftl">
<@htmlHead title='消息中心-${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
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
            	<h4 class="f14 fb">评论</h4>
                <div class="operate_wp mt20 clearfix">
                	<div class="rowElem mt5 clearfix fl">
                    	<input type="checkbox" onclick="switchAll(this,'messageCustomerId')"/>
                        <label>全选</label>
                    </div><!--/rowElem-->
                    <a class="fl ml5 opBt" href="javascript:delbatchsystemmsg();;">删除</a>
                    <a class="fl ml10 opBt" href="javascript:readbatchsystemmsg();">全部标记为已读</a>
                </div><!--/operate_wp-->
                  <#if pb.list??>
	                <ul class="cmt_list msg_list">
                      <#list pb.list as message>
	                      <li class="clearfix <#if message.messageReadFlag=='0'>noreadmsg</#if>">
	                       <div class="rowElem clearfix fl"><input type="checkbox" name="messageCustomerId" value="${message.messageCustomerId}"/></div>
                              <div class="smsg_cont fl ml10">
                              <div class="clearfix lh150">
		                         <#if message.messageTempType == '1' && message.messageTempOperation == '14' || message.messageTempOperation == '15'>
		                            <#if (message.topicTitle)?? && (message.topicTitle)?length gt 20>
		                              <#assign topictitle = (message.topicTitle)?substring(0,20)>
									<#else><#assign topictitle = (message.topicTitle)!''>
		                            </#if>
	                             <#assign msgtitle=(message.messageTitle)?replace("**","<a class='b_blue' href='${basePath}/topicdetail/${(message.groupId)!''}-${(message.topicId)!''}.html' target='_blank'>"+topictitle+"</a>")>
	                        	 <p class="fl smsg_word" id="${message.messageCustomerId}">${msgtitle}</p>
	                        	  <span>${(message.messageCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
	                        	  <div class="cb"></div>
	                              <div class="fr">
	                               <a class="fr ml10 mt5 b_blue" href="javascript:delsystemmsg(${message.messageCustomerId})">删除</a>
		                           <a class="fr mr10 mt5 b_blue"  href="${basePath}/topicdetail/${(message.groupId)!''}-${(message.topicId)!''}.html" target="_blank" onclick="read('${message.messageCustomerId}')">查看</a>
	                        	  </div>
	                        	 </#if>
		                        <#if message.messageTempType == '2' && message.messageTempOperation == '16' || message.messageTempOperation == '17'>
		                            <#if (message.groupImgTitle)?? && (message.groupImgTitle)?length gt 20>
		                              <#assign imgtitle = (message.groupImgTitle)?substring(0,20)>
									<#else><#assign imgtitle = (message.groupImgTitle)!''>
		                            </#if>
	                             <#assign msgtitle=(message.messageTitle)?replace("**","<a class='b_blue' href='${basePath}/groupimgdetail/${(message.groupId)!''}-${(message.picId)!''}-${message.messageRecCustomerId}.html' target='_blank'>"+imgtitle+"</a>")>
	                        	 <p class="fl smsg_word" id="${message.messageCustomerId}">${msgtitle}</p>
	                        	  <span>${(message.messageCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
	                        	  <div class="cb"></div>
	                              <div class="fr">
	                               <a class="fr ml10 mt5 b_blue" href="javascript:delsystemmsg(${message.messageCustomerId})">删除</a>
		                           <a class="fr mr10 mt5 b_blue"  href="${basePath}/groupimgdetail/${(message.groupId)!''}-${(message.picId)!''}-${message.messageRecCustomerId}.html" target="_blank" onclick="read('${message.messageCustomerId}')">查看</a>
	                        	  </div>
	                        	</#if>
		                        <#if message.messageTempType == '5' && message.messageTempOperation == '30'>
		                            <#if (message.tenderTitle)?? && (message.tenderTitle)?length gt 20>
		                              <#assign imgtitle = (message.tenderTitle)?substring(0,20)>
									<#else><#assign imgtitle = (message.tenderTitle)!''>
		                            </#if>
	                             <#assign msgtitle=(message.messageTitle)?replace("**","<a class='b_blue' href='${basePath}/tenderdetail-${message.tenderId}.html' target='_blank'>"+imgtitle+"</a>")>
	                        	 <p class="fl smsg_word" id="${message.messageCustomerId}">${msgtitle}</p>
	                        	  <span>${(message.messageCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
	                        	  <div class="cb"></div>
	                              <div class="fr">
	                                <a class="fr ml10 mt5 b_blue" href="javascript:delsystemmsg(${message.messageCustomerId})">删除</a>
		                            <a class="fr mr10 mt5 b_blue"  href="${basePath}/tenderdetail-${message.tenderId}.html" target="_blank" onclick="read('${message.messageCustomerId}')">查看</a>
	                              </div>
 	                        	 </#if>
	                          </div>
                           </div><!--/smsg_cont-->
	                    </li>
                      </#list>
                   </ul><!--/sys_msg_list-->
                </#if>
                
                <#if (pb.list?size>0)>
                <form action="commentmsg.html" method="post" name="group_page_form" id="group_page_form">
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
</@htmlBody>