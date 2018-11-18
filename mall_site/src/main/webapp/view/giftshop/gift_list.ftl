<#include "../include/common.ftl">
<@htmlHead title="积分商城-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style type="text/css">
    .yk_jf{ text-align: right; padding-top: 10px; font-family: "微软雅黑"; font-size: 16px; color: #333; padding-right: 50px;}
    .yk_jf span{ color: #eb6122; font-size: 16px; font-weight: bold;}
    .text,.select,.text_area{height: 25px;*width:100px;min-width:100px;padding:0 4px;line-height:25px; display:inline-block; zoom:1;border-style:solid;border-width:1px;border-color:#aaa #ddd #ddd #aaa; vertical-align:middle;margin-right:5px;}
    .gift_list .nodui{ display: inline-block; zoom:1; border: 1px solid #e2e2e2; background: #ddd; width: 68px; height: 20px; text-align: center; line-height: 20px; margin-top: 10px; color: #fff;}
</style>
</@htmlHead>
<@htmlBody>
<input type="hidden" id="cusIntegral" value="${(cusInfo.infoPointSum)!'0'}">
<input type="hidden" id="basePath" value="${basePath}">
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
	<#include "gift_left.ftl">
		<div class="pss_rig">
		  <form action="${basePath}/giftshop<#if (sCate.giftCateId)?? && (pCate.giftCatId)??>/${sCate.giftCateId}-${pCate.giftCateId}</#if>.html" id="searchForm" method="post">
			<input type="hidden" name="pageNo" class="pageNo" value="${pb.pageNo}">
			<input type="hidden" name="integralFlag" id="giftIntegral" value="${(gift.integralFlag)!''}">
			<input type="hidden" name="startIntegral" id="startIntegral" value="${(gift.startIntegral)!''}">
			<input type="hidden" name="endIntegral" id="endIntegral" value="${(gift.endIntegral)!''}">
			<input type="hidden" name="giftCateId" id="giftCateId" value="${(sCate.giftCateId)!''}">
			<input type="hidden" name="giftParentId" id="giftParentId" value="${(pCate.giftCateId)!''}">
			<div class="shai_list clearfix">
				<div class="shai_left fl">
					按所需积分数量筛选：
				</div>
				<div class="shai_rig clearfix">
					<a href="javascript:void(0)" class="integralFlag <#if (gift.integralFlag)?? && gift.integralFlag == 1>cur</#if>" data-role="1">0-999</a>
					<a href="javascript:void(0)" class="integralFlag <#if (gift.integralFlag)?? && gift.integralFlag == 2>cur</#if>" data-role="2">1000-1999</a>
					<a href="javascript:void(0)" class="integralFlag <#if (gift.integralFlag)?? && gift.integralFlag == 3>cur</#if>" data-role="3">2000-2999</a>
					<a href="javascript:void(0)" class="integralFlag <#if (gift.integralFlag)?? && gift.integralFlag == 4>cur</#if>" data-role="4">3000-4999</a>
					<a href="javascript:void(0)" class="integralFlag <#if (gift.integralFlag)?? && gift.integralFlag == 5>cur</#if>" data-role="5">5000-9999</a>
					<a href="javascript:void(0)" class="integralFlag <#if (gift.integralFlag)?? && gift.integralFlag == 6>cur</#if>" data-role="6">10000-19999</a>
					<a href="javascript:void(0)" class="integralFlag <#if (gift.integralFlag)?? && gift.integralFlag == 7>cur</#if>" data-role="7">20000以上</a>
				</div>
			</div>
			<div class="gift_list">
				<ul class="clearfix" style="width:1020px;">
					<#if (pb.list)??>
					  <#list pb.list as gift>
						  <li>
							<a href="${basePath}/giftdetail/${gift.giftId}.html" target="_blank">
							<img alt="${gift.giftName}" title="${gift.giftName!''}" src="<#if gift.giftPicList?? &&  gift.giftPicList?size &gt; 0 >${ gift.giftPicList[0].picUrl!''}</#if>"  width="220" height="220" />
							<a href="${basePath}/giftdetail/${gift.giftId}.html" target="_blank" class="deli">${(gift.giftName)!''}</a>
							<p class="hx_bi">积分：<strong>${(gift.giftIntegral)!'0'}</strong></p>
							<#if gift.giftStock gt 0>
							 <a href="javascript:void(0)" class="dui" onclick="duihuan('${gift.giftId}','${(gift.giftIntegral)!'0'}','${gift.giftStock}')">立即兑换</a>
						    <#else>
							 <a href="javascript:void(0)" class="nodui" )">立即兑换</a>
						    </#if>
						 </li>
					  </#list>
					</#if>
				</ul>

			</div>
	 <#if (pb.list?size>0)>
    	<div class="pages mt10 tr">
    		<#if ((pb.pageNo-2)>0)>
					<#assign pageNo="${pb.pageNo-2}" />
				<#else>
					<#assign pageNo="${pb.firstPageNo}" />
				</#if>
				<#if ((pb.lastPageNo-1)>0)>
					<#assign endNo="${pb.lastPageNo-2}" />
				<#else>
					<#assign endNo="1" />
				</#if>
            	<#if (pb.pageNo==1)>
                    <a class="pg_prev no_pages" href="javascript:;">上一页</a>
                <#else>
                	<a class="pg_prev changePages" pages="${pb.prePageNo}" href="javascript:;">上一页</a>
                </#if>
                
                <#list pageNo?number .. pb.endNo as item>
						<#if (item_index<=4)>
			               <#if (item=pb.pageNo)>
			               		<a class="cur" href="javascript:;">${item}</a>
			               <#else>
			               		<a class="changePages" pages="${item}" href="javascript:;">${item}</a>
			               </#if>
	            		 </#if>
				</#list>
				<#if pb.pageNo!=pb.lastPageNo>
				     <#if ((pb.lastPageNo-pb.pageNo)>3) >
				     	<#if (pb.lastPageNo>5)>
				           ...
				        </#if>
				     </#if>
			    </#if>
			     <#if (pb.pageNo ==pb.lastPageNo ||pb.endNo <= 1)>
			    	 <#if (pb.lastPageNo >pb.pageNo)>
			    	 	<a class="cur" href="javascript:;">${pb.lastPageNo}</a>
			         </#if>
			         	<a class="pg_next no_pages" href="javascript:void(0);">下一页</a>
				<#else>
					 <#if ((pb.lastPageNo - pb.pageNo)>=3)>
					 	<#if (pb.lastPageNo>5)>
					 		<a class="pg_next changePages" pages="${pb.lastPageNo}" href="javascript:;">${pb.lastPageNo}</a>
					 	</#if>
					 </#if>
					 <a class="pg_next changePages"  pages="${pb.nextPageNo}"  href="javascript:void(0);">下一页</a>
				</#if>
		  	 <#else>
					<center>暂无商品</center>	                    	
		     </#if>
  	     </div><!--/pages-->
	    </form>
		</div>
	
     </div>
	
	<#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
   </#if>
   <div class="mask"></div><!--/mask-->
     <div class="dialog dia1">
        <div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="dia_intro tc pt30"><em style="line-height:100px;" id="message">您的积分不足，请重新选择礼品兑换！</em></div>
            <div class="dia_ops mt20 tc">
                    <a class="go_pay"  href="giftshop.htm">确定</a>
                </div>
            <div class="dia_ops mt20 tc" id="index">
                <a class="go_pay" href="${basePath}/giftshop.html" >返回积分商城</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
   <div class="mask"></div><!--/mask-->
  
   <div class="dialog dia2" style="width:600px;">
    <form id="giftorder" method="post" action="1212">
	       <input type="hidden" id="customerId" name="customerId" value="${cusId!''}">
	       <input type="hidden" id="giftId" name="temp1"  value="">
	       <input type="hidden" id="sumInteger" name="orderIntegral"  value="">
	       <input type="hidden" id="giftStock" name="giftStock"  value="">
	       <input type="hidden" id="orderNum" name="orderNum"  value="">
	        <div class="dia_tit clearfix">
	            <h4 class="fl">提示</h4>
	            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
	        </div><!--/dia_tit-->
	        <div class="dia_cont">
	            <div class="order_fill" style="border:none;">
	                <div class="order_fill_item"> 
	                    <table>
                            <tbody><tr>
                                <td width="85" align="right"><font color="red">*</font>收货人姓名：</td>
                                <td><input type="text" class="text save_add_name" size="10" onblur="checkAddressName();" name="shoppingPerson"/><label class="addressNameTip"></label></td>
                            </tr>
                            <tr>
                                <td align="right"><font color="red">*</font>省市地区：</td>
                                <td>
                                    <select  name="shoppingProvince" id="infoProvince">
                                        <option selected="selected" value="">请选择</option>
                                    </select>
                                    <select name="shoppingCity" id="infoCity">
                                        <option selected="selected" value="">请选择</option>
                                    </select>
                                    <select name="shoppingCounty" id="infoCounty">
                                        <option selected="selected" value="" >请选择</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right"><font color="red">*</font>详细地址：</td>
                                <td><input type="text" class="text save_add_detail" size="40" name="shoppingAddress" onblur="checkAddressDetail();" /><label class="addressDetailTip"></label></td>
                            </tr>
                            <tr>
                                <td align="right"><font color="red">*</font>手机号码：</td>
                                <td><input type="text" class="text save_add_mobile" size="30" name="shoppingMobile" onblur="checkAddressMobile();"/><label class="addressMobiTip">用于接收发货通知短信及送货前确认</label></td>
                            </tr>
                            <tr>
                                <td align="right">固定电话：</td>
                                <td><input type="text" class="text save_add_phone" size="30" name="shoppingPhone" onblur="checkAddressPhone();"/><label class="addressPhoneTip">格式:XXXX-XXXXXXXX</label></td>
                            </tr>
                            <tr>
                                <td align="right">邮政编码：</td>
                                <td><input type="text" class="text save_add_post" size="20" name="shoppingPostcode" onblur="checkAddressPost1();" onblur="checkAddressPost();" /><label class="addPostTips">有助于快速确定送货地址</label></td>
                            </tr>
                        </tbody>
	                </table>
	                    <div class="yk_jf">
	                    	    应扣积分：<span id="orderIntegral"></span>
	                    </div>
	                </div>
	            </div>
	            <div class="dia_ops mt20 tc">
	                <a class="go_pay" href="javascript:;" onclick="tijiao();">提交订单</a>
	            </div><!--/dia_ops-->
	        </div><!--/dia_cont-->
	     </form>
	    </div><!--/dialog-->
    
    <div class="side_tools">
        <a class="backtotop" href="javascript:;"><em>返回顶部</em><b></b></a>
    </div><!--/side_tools-->
    <script type="text/javascript" src="${basePath}/js/index.js"></script>
     <script type="text/javascript" src="${basePath}/js/order/loadAddress.js"></script>
    <script type="text/javascript" src="${basePath}/js/customer/customaddress.js"></script>
    <script type="text/javascript" src="${basePath}/js/order/order_common.js"></script>
    <script type="text/javascript" src="${basePath}/js/giftorder/giftorder.js"></script>
	<script type="text/javascript">
	
	
	 $(function(){
        $('.rank ul li').mouseover(function(){
		$('.rank ul li').removeClass('hover');
		$(this).addClass('hover');
	   });
	});
	
       $('.sub_cate2 dt').click(function(){
            if($(this).next().is(':visible')){
                $(this).parent().removeClass('hover');
            }
            else{
                $(this).parent().addClass('hover');
            }
          });
	
        $(function(){
            $(".shai_rig a").click(function(){
            	$(this).addClass('cur');
            	$(this).siblings().removeClass('cur');
            });
        });
        
        $(".integralFlag").click(function(){
           var flag=$(this).attr("data-role");
           if(flag==1){
             $("#startIntegral").val(0);
             $("#endIntegral").val(999);
           }else if(flag == 2){
             $("#startIntegral").val(1000);
             $("#endIntegral").val(1999);
           }else if(flag == 3){
             $("#startIntegral").val(2000);
             $("#endIntegral").val(2999);
           }else if(flag == 4){
             $("#startIntegral").val(3000);
             $("#endIntegral").val(4999);
           }else if(flag == 5){
             $("#startIntegral").val(5000);
             $("#endIntegral").val(9999);
           }else if(flag == 6){
             $("#startIntegral").val(10000);
             $("#endIntegral").val(19999);
           }else if(flag== 7){
             $("#startIntegral").val(20000);
           }
           $(".pageNo").val(1);
           $("#giftIntegral").val(flag);
           $("#searchForm").submit();
        });
       
       function duihuan(giftId,point,stock){
            var customerId = $("#customerId").val();
	        var integral =$("#cusIntegral").val();
	          if(customerId==''){
	           window.location.href="${basePath}/login.html";
	          }else{
	            if(integral<=0 || parseInt(integral)<parseInt(point)){
	              dia(1);
	            }else{
	              $("#orderIntegral").html(point);
	              $("#sumInteger").val(point);
	              $("#giftId").val(giftId);
	              $("#giftStock").val(parseInt(stock)-1);
	               $("#orderNum").val(1);
	              dia(2);
	            }
	          }
        }
	</script>
</@htmlBody>