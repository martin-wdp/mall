<#include "../include/common.ftl">
<@htmlHead title="积分商品详情页-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style type="text/css">
    .main_price {font-family: microsoft YaHei;font-weight: 700;font-size: 18px;}
    .no-pay {background: #ddd;color: #fff;font-size: 24px;width: 180px;height: 60px;border-radius: 5px;display: block;line-height: 60px;text-align: center;font-family: "微软雅黑";margin: 20px 0px 0px 20px;}
    .no-pay:hover{color:#fff;}
    .yk_jf{ text-align: right; padding-top: 10px; font-family: "微软雅黑"; font-size: 16px; color: #333; padding-right: 50px;}
    .yk_jf span{ color: #eb6122; font-size: 16px; font-weight: bold;}
    .text,.select,.text_area{height: 25px;*width:100px;min-width:100px;padding:0 4px;line-height:25px; display:inline-block; zoom:1;border-style:solid;border-width:1px;border-color:#aaa #ddd #ddd #aaa; vertical-align:middle;margin-right:5px;}
</style>
</@htmlHead>

<@htmlBody>
<input type="hidden" id="cusIntegral" value="${(cusInfo.infoPointSum)!'0'}">
<input type="hidden" id="basePath" value="${basePath}">
<input type="hidden" id="limitnum" value="${gift.giftLimitBuy}">
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
			<div class="git_det_top clearfix">
                <div class="det-left fl">
                    <div class="img_preview">
                <div class="big_img">
                    <a class="cloud-zoom" id="zoom" rel="adjustX:10,adjustY:0" href="<#if gift.giftPicList?? &&  gift.giftPicList?size &gt; 0 >${(gift.giftPicList[0].picUrl)!''}</#if>">
                       <img alt="" src="<#if gift.giftPicList?? &&  gift.giftPicList?size &gt; 0 >${(gift.giftPicList[0].picBig)!''}</#if>"  width="350" height="350" />
                    </a>
                </div><!--/big_img-->
                <div class="thumb_img clearfix mt10 pr">
                    <div class="thumb_scroll_wp">
                            <#if gift.giftPicList?? && gift.giftPicList?size &gt; 0>
	                            <ul class="clearfix">
		                            <#list gift.giftPicList as pic>
			                            <li <#if pic_index==0> class="cur"</#if>>
			                                <a class="cloud-zoom-gallery" href="${(pic.picUrl)!''}" rel="useZoom:'zoom',smallImage:'${(pic.picBig)!''}'">
			                                    <img alt="" src="${(pic.picLittle)!''}" width="50" height="50" />
			                                </a>
			                            </li>
		                            </#list>
	                            </ul>
                            </#if>
                        </ul>
                    </div><!--/thumb_scroll_wp-->
                    <a class="thumb_scroll_prev disabled" href="javascript:;"></a>
                    <a class="thumb_scroll_next disabled" href="javascript:;"></a>
                </div><!--/thumb_img-->
            </div><!--/img_preview-->
                </div>
                <div class="det-rig fr">
                    <div class="title">${gift.giftName}</div>
                    <div class="det-detail">
                        <table>
                            <tr>
                                <td width="100" align="right">积分：</td>
                                <td><strong class="det-red">${gift.giftIntegral}</strong></td>
                            </tr>
                            <tr>
                                <td align="right">市场参考价：</td>
                                <td><span class="main_price"><b>¥</b> ${(gift.giftPrice)?string("0.00")}</span></td>
                            </tr>
                            <tr>
                                <td align="right">库存情况：</td>
                                <td><div><label><#if gift.giftStock gt 0>有货<#else>缺货</#if></label></div></td>
                            </tr>
                            <tr>
                                <td align="right">兑换数量：</td>
                                <td><a class="minus num_minus fl" href="javascript:;"></a>
			                        <input class="min_text num_text fl product_buy_num" style="width: 25px;text-align:center;" type="text" value="1" />
			                        <a class="plus num_plus fl" href="javascript:;"></a></td>
                            </tr>
                        </table>
                        <#if gift.giftStock gt 0><a href="javascript:void(0)" class="det-pay" >立即兑换</a>
                        <#else>
                        <a href="javascript:void(0)" class="no-pay">立即兑换</a>
                        </#if>
                    </div>
                </div><!--det-rig-->

            </div><!--git_det_top-->
			<div class="git-det-body">
                <div class="title">礼品详情</div>
                <div class="git-det">
                    <#if gift.giftText??>
                    ${gift.giftText}
                    </#if>
                </div>
            </div><!--git-det-body-->

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
                <a class="go_pay"  href="${basePath}/giftshop.html">确定</a>
            </div>
            <div class="dia_ops mt20 tc" id="index">
                <a class="go_pay" href="${basePath}/giftshop.html" >返回积分商城</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
     <div class="mask"></div><!--/mask-->
     <form id="giftorder" method="post" action="">
	    <div class="dialog dia2" style="width:600px;">
	       <input type="hidden" id="customerId" name="customerId" value="${cusId!''}">
	       <input type="hidden" id="giftId" name="temp1"  value="${gift.giftId}">
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
	                <a class="go_pay" href="javascript:void(0);" onclick="tijiao();">提交订单</a>
	            </div><!--/dia_ops-->
	        </div><!--/dia_cont-->
	    </div><!--/dialog-->
	   </form>
    <script type="text/javascript" src="${basePath}/js/cloud-zoom.1.0.2.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/default.js"></script>
    <script type="text/javascript" src="${basePath}/js/order/loadAddress.js"></script>
    <script type="text/javascript" src="${basePath}/js/customer/customaddress.js"></script>
    <script type="text/javascript" src="${basePath}/js/order/order_common.js"></script>
    <script type="text/javascript" src="${basePath}/js/giftorder/giftorder.js"></script>
	<script type="text/javascript">
	       //选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(2)").addClass("cur");
        $('.rank li').mouseover(function(){
		$('.rank li').removeClass('hover');
		$(this).addClass('hover');
       });
        $('.sub_cate2 dt').click(function(){
            if($(this).next().is(':visible')){
                $(this).parent().removeClass('hover');
            }
            else{
                $(this).parent().addClass('hover');
            }
        });
        //详情页商品图片展示
    $(".thumb_scroll_wp li:first").addClass("cur");
    var li_n = $(".thumb_scroll_wp li").length - 5;
    var li_step = 0;
    $(".thumb_scroll_wp li a").mouseover(function(){
        var _li = $(this).parent("li");
        _li.siblings(".cur").removeClass("cur");
        _li.addClass("cur");
    });
    $(".thumb_scroll_wp li a").click(function(){
        return false;
    });
    if(li_n > 0) {
        $(".thumb_scroll_wp ul").width($(".thumb_scroll_wp li").length*59);
        $(".thumb_scroll_next").removeClass("disabled");
        $(".thumb_scroll_next").click(function(){
            $(".thumb_scroll_prev").removeClass("disabled");
            if(li_step < li_n) {
                li_step++;
                $(".thumb_scroll_wp ul").animate({
                    left: -59*li_step
                },300);
                if(li_step == li_n) {
                    $(".thumb_scroll_next").addClass("disabled");
                }
            }
        });
        $(".thumb_scroll_prev").click(function(){
            $(".thumb_scroll_next").removeClass("disabled");
            if(li_step > 0) {
                li_step--;
                $(".thumb_scroll_wp ul").animate({
                    left: -59*li_step
                },300);
                if(li_step == 0) {
                    $(".thumb_scroll_prev").addClass("disabled");
                }
            }
        });
    }
      $(".det-pay").click(function(){
        var customerId = $("#customerId").val();
        var integral =$("#cusIntegral").val();
        var sum =$(".product_buy_num").val();
        var limitnum = $("#limitnum").val();
        var giftIntegral = ${gift.giftIntegral};
        var sumpoint = giftIntegral*sum;
        var stock = ${gift.giftStock}
        var restock = parseInt(stock)-parseInt(sum);
          if(customerId==''){
           window.location.href="${basePath}/login.html";
          }else{
            if(integral<=0 || parseInt(integral) < parseInt(sumpoint)){
              dia(1);
            }else if(parseInt(sum)>parseInt(limitnum)){
              $("#message").html("抱歉，本商品限购"+limitnum+"件");
              $("#index").hide();
               dia(1);
            }else if(parseInt(stock)<parseInt(sum)){
              $("#message").html("抱歉，本商品库存只有"+stock+"件");
              $("#index").hide();
               dia(1);
            }else{
              $("#orderIntegral").html(sumpoint);
              $("#sumInteger").val(sumpoint);
              $("#giftStock").val(restock);
              $("#orderNum").val(sum);
              dia(2);
            }
          }
        });
	</script>
</@htmlBody>