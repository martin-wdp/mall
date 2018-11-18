<#include "../include/common.ftl">
<@htmlHead title='消息中心-${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<style type="text/css">
    .msg_content1 .s1, .msg_content1 .s2 {float: right;margin-left: 10px;margin-top: 20px;color: #336699;}
    .group_btn {background: url(${basePath}/images/group_btn.gif) no-repeat;width: 97px;height: 34px;border: none;cursor: pointer;color: #fff;margin: 10px 0 0 110px;font-family: "微软雅黑";font-size: 14px;}
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
            	<h4 class="f14 fb">系统消息</h4>
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
                     <#list pb.list as sysmsg>
                       <li class="clearfix <#if sysmsg.messageReadFlag=='0'>noreadmsg</#if>">
                    	<div class="rowElem clearfix fl"><input type="checkbox" name="messageCustomerId" value="${sysmsg.messageCustomerId}"/></div>
                        <a class="fl ml5" href="javascript:void(0);"><img alt="" src="${basePath}/images/sys1_img.jpg" /></a>
                        <div class="smsg_cont fl ml10">
                        	<div class="clearfix"><a class="b_blue fl" href="javascript:void(0);">XX小秘书：</a><span class="fr b_grey">${(sysmsg.messageCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span></div>
                            <div class="clearfix mt15 lh150">
                            <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '0'>
                             <#assign msgtitle=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign title=(msgtitle)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${title}</p>
                        	</#if>
                        	<#if (sysmsg.refuseGroupReason)??>
		                          <#if (sysmsg.refuseGroupReason)?length gt 20>
		                    	    <#assign reason=(sysmsg.refuseGroupReason)?substring(0,20)>
		                    	  <#else><#assign reason=(sysmsg.refuseGroupReason)!''>
		                    	 </#if>
		                     </#if>
                            <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '1'>
                             <#assign msgtitle=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html'  target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign title=(msgtitle)?replace("$$","<s class='b_blue'>"+sysmsg.groupName+"</s>")>
                             <#if reason??>
                               <#assign rea = reason>
                              <#else><#assign rea = "">
                             </#if>
                             <#assign title1=(title)?replace("##","<s class='b_blue'>"+rea+"</s>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${title1}</p>
                        	</#if>
                            <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '2' && (sysmsg.messageContent)??>
                             <#assign msgContent=(sysmsg.messageContent)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign content=(msgContent)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign content1=(content)?replace("&&","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.customerId}.html' target='_blank'>"+sysmsg.addGroupCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${sysmsg.messageTitle}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '3'>
                             <#assign msgtitle=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign title=(msgtitle)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign title1=(title)?replace("&&","<a class='b_blue' href='${basePath}/customerhomepage/${(sysmsg.messageAuthorId)!''}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${title1}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '4'>
                             <#assign msgtitle=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign title=(msgtitle)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign title1=(title)?replace("&&","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageAuthorId}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${title1}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '1' && sysmsg.messageTempOperation == '5'>
                             <#assign msgtitle=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign title=(msgtitle)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign title1=(title)?replace("**","<a class='b_blue' href='${basePath}/topicdetail/${sysmsg.groupId}-${sysmsg.topicId}.html' target='_blank'>"+sysmsg.topicTitle+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${title1}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '1' && sysmsg.messageTempOperation == '6'>
                             <#assign msgtitle=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign title=(msgtitle)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign title1=(title)?replace("**","<a class='b_blue' href='${basePath}/topicdetail/${sysmsg.groupId}-${sysmsg.topicId}.html' target='_blank'>"+sysmsg.topicTitle+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${title1}</p>
                        	</#if>
                        	<#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '7' && (sysmsg.messageContent)??>
                             <#assign msgContent=(sysmsg.messageContent)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign content=(msgContent)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign content2=(content)?replace("&&","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageAuthorId}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${sysmsg.messageTitle}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '8' || sysmsg.messageTempOperation == '9'>
                             <#assign msgtitle=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign title=(msgtitle)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign title1=(title)?replace("##","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageAuthorId}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${title1}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '10'>
                             <#assign msgContent=(sysmsg.messageContent)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign content=(msgContent)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign content5=(content)?replace("&&","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageAuthorId}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${sysmsg.messageTitle}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '11' && (sysmsg.messageContent)??>
                             <#assign msgContent=(sysmsg.messageContent)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign content=(msgContent)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign content3=(content)?replace("&&","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageAuthorId}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${sysmsg.messageTitle}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '12' || sysmsg.messageTempOperation == '13'>
                             <#assign msgtitle=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign title=(msgtitle)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign title1=(title)?replace("##","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageAuthorId}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${title1}</p>
                        	</#if>
                        	<#if sysmsg.messageTempType == '4' && sysmsg.messageTempOperation == '21'>
                             <#assign msgtitle=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign title=(msgtitle)?replace("##","<a class='b_blue' href='${basePath}/customerhomepage/${(sysmsg.messageAuthorId)!''}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${title}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '1' && sysmsg.messageTempOperation == '22'>
                             <#assign msgContent=(sysmsg.messageContent)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign content=(msgContent)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign content1=(content)?replace("**","<a class='b_blue' href='${basePath}/topicdetail/${sysmsg.groupId}-${sysmsg.topicId}.html' target='_blank'>"+sysmsg.topicTitle+"</a>")>
                        	 <#assign content4=(content1)?replace("##","<a class='b_blue' href='${basePath}/customerhomepage/${(sysmsg.messageAuthorId)!''}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${sysmsg.messageTitle}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '23'>
                             <#assign msgContent=(sysmsg.messageContent)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign content=(msgContent)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                        	 <#assign content6=(content)?replace("##","<a class='b_blue' href='${basePath}/customerhomepage/${(sysmsg.messageAuthorId)!''}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${sysmsg.messageTitle}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '24' || sysmsg.messageTempOperation == '25'>
                             <#assign msgtitle=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign title=(msgtitle)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                             <#assign title5=(title)?replace("##","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageAuthorId}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${title5}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '1' && sysmsg.messageTempOperation == '26'>
                             <#assign msgContent=(sysmsg.messageContent)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                             <#assign content=(msgContent)?replace("$$","<a class='b_blue' href='${basePath}/groupdetail/${sysmsg.groupId}.html' target='_blank'>"+sysmsg.groupName+"</a>")>
                        	 <#assign content1=(content)?replace("##","<a class='b_blue' href='${basePath}/customerhomepage/${(sysmsg.messageAuthorId)!''}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <#assign content2=(content1)?replace("**","<a class='b_blue' href='${basePath}/topicdetail/${sysmsg.groupId}-${sysmsg.topicId}.html' target='_blank'>"+sysmsg.topicTitle+"</a>")>
                        	 <#assign content7=(content2)?replace("&&",(sysmsg.messageCreateTime)?string("yyyy-MM-dd HH:mm:ss"))>
                        	 <#assign customerId=sysmsg.messageRecCustomerId>
                        	 <#assign delcustomerId=sysmsg.messageAuthorId>
                        	 <#assign topicId=sysmsg.topicId>
                        	 <#assign groupId=sysmsg.groupId>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${sysmsg.messageTitle}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '1' && sysmsg.messageTempOperation == '27'>
                             <#assign msgContent=(sysmsg.messageContent)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                        	 <#assign content1=(msgContent)?replace("##","<a class='b_blue' href='${basePath}/customerhomepage/${(sysmsg.messageAuthorId)!''}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <#assign content8=(content1)?replace("**","<a class='b_blue' href='${basePath}/topicdetail/${sysmsg.groupId}-${sysmsg.topicId}.html' target='_blank'>"+sysmsg.topicTitle+"</a>")>
                        	 <#assign customerId2=sysmsg.messageRecCustomerId>
                        	 <#assign delcustomerId2=sysmsg.messageAuthorId>
                        	 <#assign topicId2=sysmsg.topicId>
                        	 <#assign groupI2=sysmsg.groupId>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${sysmsg.messageTitle}</p>
                        	</#if>
                        	 <#if sysmsg.messageTempType == '1' && sysmsg.messageTempOperation == '28' || sysmsg.messageTempOperation == '29'>
                             <#assign msgContent=(sysmsg.messageTitle)?replace("@@","<a class='b_blue' href='${basePath}/customerhomepage/${sysmsg.messageRecCustomerId}.html' target='_blank'>"+sysmsg.customerName+"</a>")>
                        	 <#assign content1=(msgContent)?replace("##","<a class='b_blue' href='${basePath}/customerhomepage/${(sysmsg.messageAuthorId)!''}.html' target='_blank'>"+sysmsg.createMsgCustomerName+"</a>")>
                        	 <#assign content9=(content1)?replace("**","<a class='b_blue' href='${basePath}/topicdetail/${sysmsg.groupId}-${sysmsg.topicId}.html' target='_blank'>"+sysmsg.topicTitle+"</a>")>
                        	 <p class="fl smsg_word" id="${sysmsg.messageCustomerId}">${content9}</p>
                        	</#if>
                             <a class="fr b_blue" href="javascript:delsystemmsg(${sysmsg.messageCustomerId})">删除</a>
	                         <a class="fr msg_view1 mr10 b_blue" href="javascript:void(0);" onclick="read('${sysmsg.messageCustomerId}')" >查看</a>
                            </div>
                             <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation == '2'>
	                             <div class="msg_content1 mt10 lh150 none">${content1} 
	                             <a href ="javascript:void(0);" class="s2"  onclick="applyjoin(${sysmsg.groupId},${sysmsg.customerId},${sysmsg.messageRecCustomerId},'2',this)"> 拒绝</a>
			    	             <a href ="javascript:void(0);" class="s1"  onclick="applyjoin(${sysmsg.groupId},${sysmsg.customerId},${sysmsg.messageRecCustomerId},'1',this)"> 同意</a>
	                             <span class="none">${sysmsg.messageRecCustomerId}</span><span class="none">${sysmsg.messageId}</span></div> 
		    	             </#if>
                             <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation =='7'>
	                             <div class="msg_content1 mt10 lh150 none">${content2} 
	                             <a href ="javascript:void(0);" class="s2"  onclick="managergroup(${sysmsg.groupId},${sysmsg.messageAuthorId},${sysmsg.messageRecCustomerId},'2',this)"> 拒绝</a>
			    	             <a href ="javascript:void(0);" class="s1"  onclick="managergroup(${sysmsg.groupId},${sysmsg.messageAuthorId},${sysmsg.messageRecCustomerId},'1',this)"> 同意</a>
	                             <span class="none">${sysmsg.messageRecCustomerId}</span><span class="none">${sysmsg.messageId}</span></div> 
		    	             </#if>
                             <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation =='11'>
	                             <div class="msg_content1 mt10 lh150 none">${content3} 
	                             <a href ="javascript:void(0);" class="s2"  onclick="transfergroup(${sysmsg.groupId},${sysmsg.messageAuthorId},${sysmsg.messageRecCustomerId},'2',this)"> 拒绝</a>
			    	             <a href ="javascript:void(0);" class="s1"  onclick="transfergroup(${sysmsg.groupId},${sysmsg.messageAuthorId},${sysmsg.messageRecCustomerId},'1',this)"> 同意</a>
	                             <span class="none">${sysmsg.messageRecCustomerId}</span><span class="none">${sysmsg.messageId}</span></div> 
		    	             </#if>
                             <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation =='23'>
	                             <div class="msg_content1 mt10 lh150 none">${content6} 
	                             <a href ="javascript:void(0);" class="s2"  onclick="invitefriends(${sysmsg.groupId},${sysmsg.messageAuthorId},${sysmsg.messageRecCustomerId},'2',this)"> 拒绝</a>
			    	             <a href ="javascript:void(0);" class="s1"  onclick="invitefriends(${sysmsg.groupId},${sysmsg.messageAuthorId},${sysmsg.messageRecCustomerId},'1',this)"> 同意</a>
	                             <span class="none">${sysmsg.messageRecCustomerId}</span><span class="none">${sysmsg.messageId}</span></div> 
		    	             </#if>
                             <#if sysmsg.messageTempType == '1' && sysmsg.messageTempOperation =='26'>
	                             <div class="msg_content1 mt10 lh150 none">${content7}
	                             <#if sysmsg.topicDelFlag == '1'>
	                             <a href ="javascript:void(0);" class="s2"  onclick="dia(2)">申请恢复话题</a>
	                             </#if>
	                             <#if sysmsg.topicDelFlag == '0'>
	                             <a href ="javascript:void(0);" class="s2" >已恢复话题</a>
	                             </#if>
	                             <span class="none">${sysmsg.messageRecCustomerId}</span><span class="none">${sysmsg.messageId}</span></div> 
		    	             </#if>
                             <#if sysmsg.messageTempType == '1' && sysmsg.messageTempOperation =='27'>
	                             <div class="msg_content1 mt10 lh150 none">${content8}
	                             <#if sysmsg.topicApplyFlag == '0'>
	                             <a href ="${basePath}/applytopicdetail-${topicId2}.html" class="s2" target="_blank">处理话题恢复申诉</a>
	                             </#if>
	                             <#if sysmsg.topicApplyFlag == '1'>
	                             <span>已处理话题恢复申诉</span>
	                             </#if>
	                             <span class="none">${sysmsg.messageRecCustomerId}</span><span class="none">${sysmsg.messageId}</span></div> 
		    	             </#if>
                             <#if sysmsg.messageTempType == '0' && sysmsg.messageTempOperation =='10'>
	                             <div class="msg_content1 mt10 lh150 none">${content5} 
	                             <span class="none">${sysmsg.messageRecCustomerId}</span><span class="none">${sysmsg.messageId}</span></div> 
		    	             </#if>
                             <#if sysmsg.messageTempType == '1' && sysmsg.messageTempOperation =='22'>
	                             <div class="msg_content1 mt10 lh150 none">${content4} 
	                             <span class="none">${sysmsg.messageRecCustomerId}</span><span class="none">${sysmsg.messageId}</span></div> 
		    	             </#if>
		    	             <#if !(sysmsg.messageContent)??>
		    	             <div class="msg_content1 mt10 lh150 none">${(sysmsg.messageContent)!''}</div>
		    	             </#if>
		    	             
                        </div><!--/smsg_cont-->
                    </li>
                     </#list>
                   </#if>
                </ul><!--/sys_msg_list-->
               <#if (pb.list?size>0)>
                <form action="systemmsg.html" method="post" name="group_page_form" id="group_page_form">
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
    
     <div class="dialog1 dia1"  style="width: 300px; height: 120px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20" style="padding:20px;" id ="message"></p>   
    </div><!--/dialog-->
    
    <form id="applytopic" action="">
	 <input type="hidden" value="${(delcustomerId)!''}" name="topicDelCustomerId">
	 <input type="hidden" value="${(topicId)!''}" name="topicId">
	 <input type="hidden" value="${(groupId)!''}" name="groupId">
	 <input type="hidden" value="${(customerId)!''}" name="customerId">
     <div class="dialog1 dia2"  style="width: 300px; height: 180px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>申诉理由</h4>
        <textarea name="topicApplyReason" style="width:295px;height:100px"></textarea>
        <a onclick="apply()"><input type="button" class="group_btn" value="提交"></a>
        <p class="tc f14 mt20" style="padding:20px;" id ="message1"></p>   
    </div><!--/dialog-->
    </form>
    
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
			
			
		$(".msg_view1").click(function(){
			$(this).toggleClass("sq_text");
			$(this).parent().next(".msg_content1").slideToggle('slow');
			if($(this).hasClass("sq_text")) {
				$(this).text("收起");
				} else {
					$(this).text("查看");
					};
			});
			
	 $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
        });
</script>
</@htmlBody>