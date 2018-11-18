<#include "../include/common.ftl">
<@htmlHead title='消息中心-${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link  rel="stylesheet" type="text/css" href="${basePath}/face/qqFace.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<style type="text/css">
    .msg_view {
        color: #999;
        background: url(${basePath}/images/zkqb.gif) no-repeat scroll right center transparent;
        padding-right: 15px;
    }
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
            	<h4 class="f14 fb">收到的私信</h4>
                <div class="operate_wp mt20 clearfix">
                	<div class="rowElem mt5 clearfix fl">
                    	<input type="checkbox" onclick="switchAll(this,'messageCustomerId')"/>
                        <label>全选</label>
                    </div><!--/rowElem-->
                    <a class="fl ml5 opBt" href="javascript:delbatchsystemmsg();;">删除</a>
                    <a class="fl ml10 opBt" href="javascript:readbatchsystemmsg();">全部标记为已读</a>
                </div><!--/operate_wp-->
                <ul class="sys_msg_list msg_list">
                    <#if pb.list??>
                      <#list pb.list as message>
                      
	                      <li class="clearfix <#if message.messageReadFlag=='0'>noreadmsg</#if>">
	                    	<div class="rowElem clearfix fl"><input type="checkbox" name="messageCustomerId" value="${message.messageCustomerId}"/></div>
	                        <a class="fl ml5" href="${basePath}/customerhomepage/${message.messageAuthorId}.html" target="_blank">
	                        <#if (message.createCustomerHead)?? && (message.createCustomerHead)!="">
		                        <img width="50" height="50" alt="${(message.createMsgCustomerName)!''}" src="${baseUrl}${message.createCustomerHead}" /></a>
	                        <#else>
		                        <img width="50" height="50" alt="${(message.createMsgCustomerName)!''}" src="${basePath}/images/default_head3.jpg" /></a>
	                        </#if>
	                        <div class="smsg_cont fl ml10">
	                        	<div class="clearfix">
	                        	    <a class="b_blue fl" href="${basePath}/customerhomepage/${message.messageAuthorId}.html" target="_blank"">${(message.createMsgCustomerName)!''}：</a>
	                        		<span class="fr"> <a class="msg_view mr10 " href="javascript:void(0);"  onclick="read('${message.messageCustomerId}')">展开全部</a></span>
                               </div>
	                            <div class="clearfix mt15 lh150">
	                            	<p class="fl smsg_word" id="${message.messageCustomerId}">
	                            	<#if (message.messageContent)??>
	                            	 <#assign content=(message.messageContent)?replace("<.*?>","","r")>
	                                  <#if content?length gt 20>
	                            	  <a class="show" href="javascript:void(0);"  onclick="read('${message.messageCustomerId}')"> ${(content)?substring(0,20)}</a>
	                            	 <#else> <a class="show" href="javascript:void(0);"  onclick="read('${message.messageCustomerId}')">${(message.messageContent)!''}</a>
	                            	  </#if>
	                            	  </#if>
	                            	</p>
	                            </div>
                            	<div class="msg_content mt10 lh150 clearfix none">${(message.messageContent)!''}</div>
	                            <div class="clearfix">
	                            <a class="fr ml10  mt10 b_blue" href="javascript:delsystemmsg(${message.messageCustomerId})">删除</a>
	                                 <span class="fr ml10 mt10">|</span>
		                             <a class="fr msg_rep mt10 b_blue" href="javascript:void(0);">回复</a>
		                       	     <span class="mt10" style="float:left;">${(message.messageCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
	                            </div>
	                            
	                             <div class="msg_replycontent mt10 lh150 none">
		                            <form id="form${message.messageId}" action="">
		                             <input type="hidden" name="customerUsername" value="${(message.createMsgCustomerName)!''}" />
						             <div class="clearfix cb">   
						            	<div class="face_box fl"><img alt="" id="face${message.messageId}" src="${basePath}/images/face_img.gif"></div>
						             </div><!--/rls_mood-->
						            <div class="rowElem mood_wp mt5 clearfix">
						            	<textarea class="mood_txa" style="width:600px;" maxlength="300" onkeyup="length300(this);" id="content${message.messageId}" name="messageContent"></textarea>
						            	<span class="sv">可输入300字</span>
						            </div><!--/row_elem--> 
						            <div class=" clearfix mt10" style="width:610px;">    
						                <div class="rowElem clearfix fr"><a href="javascript:void();" onclick="doreplymessage('${message.messageId}');"><input type="button" value="回复" /></a></div>
						            </div><!--/rls_mood-->
							      </form>
								</div>
	                        </div><!--/smsg_cont-->
	                    </li>
	                     <script type="text/javascript">
									$(function(){
										$('#face${message.messageId}').qqFace({
											id : 'facebox1', //表情盒子的ID
											assign:'content${message.messageId}', //给那个控件赋值
											path:'face/face/', //表情存放的路径
											tip : 'em_'
										});
									});
						</script>
                     </#list>
                   </#if>
                </ul><!--/sys_msg_list-->
               <#if (pb.list?size>0)>
                <form action="lettermsg.html" method="post" name="group_page_form" id="group_page_form">
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
    
    <div class="dialog1 dia2" style="width: 300px; height: 120px;">
		<a class="close" href="javascript:cls1();" onclick="page('1')"></a>
		<h4>提示</h4>
		<div class="create_albums mt20 ml50 clearfix">
			<span id="message"></span>
		</div>
		<!--/create_albums-->
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
<script type="text/javascript" src="${basePath}/face/jquery.qqFace.min.js"></script>

<script type="text/javascript">
			$(function() {
				$(".rowElem").jqTransform();
			});
			
			$(".msg_view").click(function(){
			    if($(this).parents().next().next(".msg_content").is(":hidden")){
			         $(this).parents().next().next(".msg_content").slideToggle('slow');
			  	 	$(this).text("收起全部"); 
			   		$(this).css("background","url(${basePath}/images/sqqb.gif) no-repeat right center");
			   }else {
			        $(this).parents().next().next(".msg_content").slideToggle('slow');
			  	 	$(this).text("展开全部"); 
			   		$(this).css("background","url(${basePath}/images/zkqb.gif) no-repeat right center");
			   }
			  });
				
			$(".show").click(function(){
			   if($(this).parents().next(".msg_content").is(":hidden")){
			         $(this).parents().next(".msg_content").slideToggle('slow');
			  	 	$(this).parents().prev().find(".fr a").text("收起全部"); 
			   		$(this).parents().prev().find(".fr a").css("background","url(${basePath}/images/sqqb.gif) no-repeat right center");
			   }else {
			        $(this).parents().next(".msg_content").slideToggle('slow');
			  	 	$(this).parents().prev().find(".fr a").text("展开全部"); 
			   		$(this).parents().prev().find(".fr a").css("background","url(${basePath}/images/zkqb.gif) no-repeat right center");
			   }
			   $(".msg_view").removeClass("")
			});
				
			$(".msg_rep").click(function(){
				$(this).toggleClass("sq_text");
				$(this).parent().next(".msg_replycontent").slideToggle('slow');
				if($(this).hasClass("sq_text")) {
					$(this).text("回复");
				}else{
					$(this).text("回复"); 
					};
			 });
			 
			 
       $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
        });
</script>
</@htmlBody>