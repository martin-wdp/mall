<#include "../include/common.ftl">
<@htmlHead title="站内信息-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/letter.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>站内信息-${topmap.systembase.bsetName}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="Keywords" content="${topmap.seo.meteKey}">
<meta name="description" content="${topmap.seo.meteDes}">
<meta name="renderer" content="webkit">
<#assign basePath=request.contextPath>
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/letter.css" />
</head>-->
<@htmlBody>
	<input type="hidden" name="CSRFToken" value="${token}">
    	<#--一引入头部 <#include "/index/topnew.ftl" /> -->	
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
		<div class="container">
        <div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <a href="${basePath}/customer/consult.html">消息中心</a><span>&gt;</span>
            <span>站内信息</span>
        </div>
        <div class="member_box mb20 clearfix">
            <#include "leftmenu.ftl" >
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">我的站内信息</h2>
                </div>
   				<div class="msg_right fr">
            	<h4 class="f14 fb">收到的站内信</h4>
                <div class="operate_wp mt20 clearfix">
                	 <div class="rowElem mt5 clearfix fl">
                    	<a href="javascript:"><input type="checkbox" class="check_all" onclick="selectAll(this);"/></a>
                        <label>全选</label>
                    </div>
                    <a class="fl ml5 opBt" href="javascript:void(0);" onclick="deleteAll()">删除</a>
                    <a class="fl ml10 opBt" href="javascript:void(0);" onclick="readAll()">标记为已读</a>
                </div>
               
                <ul class="sys_msg_list msg_list">
	                    <#if (pb.list?size!=0)>
			                    	<#list pb.list as letter>
		                	<#if letter.relaId??>
			                	<li class="clearfix">
			                    	<div class="rowElem clearfix fl">
			                    	<input type="checkbox" name="selectletter"  value="${letter.letterId}" onclick="oncheck()"/></div>
			                        <div class="smsg_cont fl ml10" style="color:black;">
				                                	<div class="clearfix">
				                                	<p class="fl smsg_word"><img src="${basePath}/images/read.jpg" />&nbsp;&nbsp;${letter.letterContent}</p>
				                                	<input type="hidden" value="${customerId}" name="customerId" id="custId">
				                                	<span class="fr b_grey">
				                                	<#if letter.createTime??>
															${letter.createTime?string("yyyy-MM-dd HH:mm:ss")}
													</#if>
				                                	</span>
				                                	</div>
					                            	<div class="clearfix lh150">
						                            	&nbsp;
						                                		<a class="fr msg_det" href="javascript:;" onclick="deleteLetter(${letter.relaId},${letter.letterId})">删除</a>
						                                
						                            </div>
			                        </div><!--/smsg_cont-->
			                    </li>
		                    <#else>
			                    <li class="clearfix">
			                    	<div class="rowElem clearfix fl">
			                    	<input type="checkbox" name="selectletter"  value="${letter.letterId}" onclick="oncheck()"/></div>
			                        <div class="smsg_cont fl ml10">
				                                	<div class="clearfix">
				                                	<p class="fl smsg_word"><img src="${basePath}/images/readno.jpg" />&nbsp;&nbsp;${letter.letterContent}</p>
				                                	<input type="hidden" value="${customerId}" name="customerId" id="custId">
				                                	<span class="fr b_grey">
				                                	<#if letter.createTime??>
															${letter.createTime?string("yyyy-MM-dd HH:mm:ss")}
													</#if>
				                                	</span>
				                                	</div>
					                            	<div class="clearfix lh150">
						                            	&nbsp;
						                                		<a class="fr msg_det" href="javascript:;" onclick="deleteNoLetter(${letter.letterId})">删除</a>
						                            </div>
			                        </div><!--/smsg_cont-->
			                    </li>
		                    </#if>
		                    
                    	</#list>
                         <#else>
	                     <li style="text-align:center;">
	                     	暂无站内信！
	                     </li>
                      </#if>
                </ul><!--/sys_msg_list-->
                <#if (pb.list?size!=0)>
	                            <#-- 分页 -->
	                            <#import "../pagination/pageBean.ftl" as page>
	                            <@page.pagination pb />
                </#if>
            </div><!--/msg_right-->
            </div>
            </div>
            <div class="dialog s_dia dia2">
		    	<div class="dia_tit clearfix">
		            <h4 class="fl">提示</h4>
		            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
		        </div><!--/dia_tit-->
		        <div class="dia_cont">
		        	<div class="dia_intro no_tc pt30">
		        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/q_mark.png" />
		            	<em id="con_00">操作失败！</em>
		            </div>
		            <div class="dia_ops mt20 tr">       
		                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
		            </div><!--/dia_ops-->
		        </div><!--/dia_cont-->
		    </div><!--/dialog-->
            <div class="dialog s_dia dia3">
		    	<div class="dia_tit clearfix">
		            <h4 class="fl">提示</h4>
		            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
		        </div><!--/dia_tit-->
		        <div class="dia_cont">
		        	<div class="dia_intro no_tc pt30">
		        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/q_mark.png" />
		            	<em id="con_00">已读成功！</em>
		            </div>
		            <div class="dia_ops mt20 tr">       
		                 <a class="go_pay" id="go_pay_00" href="javascript:clas();">确定</a>
		            </div><!--/dia_ops-->
		        </div><!--/dia_cont-->
		    </div><!--/dialog-->
		    <div class="dialog s_dia dia4">
		    	<div class="dia_tit clearfix">
		            <h4 class="fl">提示</h4>
		            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
		        </div><!--/dia_tit-->
		        <div class="dia_cont">
		        	<div class="dia_intro no_tc pt30">
		        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/q_mark.png" />
		            	<em id="con_00">已经全部为已读状态！</em>
		            </div>
		            <div class="dia_ops mt20 tr">       
		                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
		            </div><!--/dia_ops-->
		        </div><!--/dia_cont-->
		    </div><!--/dialog-->
		    <div class="dialog s_dia dia5">
		    	<div class="dia_tit clearfix">
		            <h4 class="fl">提示</h4>
		            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
		        </div><!--/dia_tit-->
		        <div class="dia_cont">
		        	<div class="dia_intro no_tc pt30">
		        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/q_mark.png" />
		            	<em id="con_00">请选择一行！</em>
		            </div>
		            <div class="dia_ops mt20 tr">       
		                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
		            </div><!--/dia_ops-->
		        </div><!--/dia_cont-->
		    </div><!--/dialog-->
 <script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/letter.js"></script>
 <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
   <#--引入底部 <#include "/index/bottom.ftl" /> -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script>
	$(document).ready(function(){
		$('.item_title').each(function(){
			$(this).click(function(){
				$(this).next().toggle('fast',function(){
					if($(this).is(':visible')){
						$(this).prev().removeClass('up');
						$(this).prev().addClass('down');
					}
					else{
						$(this).prev().removeClass('down');
						$(this).prev().addClass('up');
					}
				});
			});
		});
		$(".memeber_left div:eq(3) ul li:eq(2)").addClass("cur");
	    $(".pro_sort").addClass("pro_sort_close");
	    jQuery.fn.isChildAndSelfOf = function(b){ return (this.closest(b).length > 0); };
	});
</script>
</@htmlBody>
