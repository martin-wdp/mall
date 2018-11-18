<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
</@htmlHead>
<@htmlBody>
	<div>
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
	</div>
	<div class="container">
		<!-- 面包屑 -->
        <div class="location">
       		<#if (map.cHelpCate??)>
				<a href="${basePath}/helpfirstpage.html"><strong>帮助中心</strong></a>
				<span>&gt;</span><a href="${basePath}/help/list/${map.cHelpCate.helpcateId}">${map.cHelpCate.helpcateName}</a>
			<#else>
				<a href="${basePath}/help/list/"><strong>帮助中心</strong></a>
			</#if>
        </div>
        
        <div class="member_box mb20">
            <div class="memeber_left fl">
                <h2><span class="f14 fb">帮助中心</span></h2>
                <#list map.helpCates as helpcate>
                	<div class="menu_item">
	                    <ul class="item_list">
						<li <#if (helpcate.helpcateId==map.cHelpCate.helpcateId)>class="cur"</#if>>
							<a href="${basePath}/help/list/${helpcate.helpcateId}">
									${helpcate.helpcateName!''}</a>
						</li>
	                    </ul>
                	</div>
				</#list>
            </div>
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">帮助中心</h2>
                </div>
                <div class="order_list switch_border">
                	<div class="tagMenu" style="height:0px;">
                    </div>
                        	<!-- 保存分页参数 -->
							<form id="searchForm" method="post" action="${basePath}/help/list/">
								<input type="hidden" id="pageBeanShowSize" name="pageSize" value="${map.pb.pageSize}" />
								<input type="hidden" id="pageBeanShowPage" name="pageNo" value="${map.pb.pageNo}" />
								<input type="hidden" id="rows" name="rows" value="${map.pb.rows}" />
								<input type="hidden" name="typeId" value="<#if (map.cHelpCate??)>${map.cHelpCate.helpcateId}</#if>" />
							</form>
                    <div class="content">
                        <div class="layout">
                            	<table class="orders common_table">
	                                <tr>
	                                    <th width="50">序号</th>
	                                    <th width="200">帮助标题</th>
	                                    <th width="50">帮助分类</th>
	                                    <th width="50">作者</th>
	                                    <th width="80">创建时间</th>
	                                </tr>
                           		 	<#if (map.pb.list?size>0) >
		                                <#list map.pb.list as help>
									    <tr>
									    	<td><strong>${help_index+1}</strong></td>
									    	<td>
									    	<a class="info_tit" href="${basePath}/help/${help.helpId}">${help.helpTitle }</a>
									    	</td>
									    	<td>${help.helpcateName}</td>
									    	<td>${help.helpAuthor}</td>
									    	<td>${help.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
										</tr>
										</#list>
									<#else>
	                                <tr>
								    	<td colspan="6">暂无帮助</td>
									</tr>
                            		</#if>
	                            </table>
	                            
	                            <!--分页结束-->
                                <#if (map.pb.list?size!=0)>
	                            <#import "/pagination/pageBean.ftl" as page>
	                            <@page.pagination map.pb />
                            	</#if>
                        </div>
                    </div>
                </div>
            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
    </div>

<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/information/information_comm.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//头部分类导航显示和隐藏
    $(".pro_sort").addClass("pro_sort_close");
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
	$(".guess_goods_list").jCarouselLite({
        btnNext: ".next",
        btnPrev: ".prev",
		visible : 6,
		auto : 2000,
		speed : 800
    });
    
});
</script>
<#--引入底部-->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
</@htmlBody>