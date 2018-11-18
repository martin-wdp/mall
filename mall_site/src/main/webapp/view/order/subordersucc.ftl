<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
<style>
    .agreement_dia {
        width: 910px!important;
        height: auto;
        border: 5px solid rgba(238,238,238,.5);
        padding: 0;
    }
    .agreement_wp {
        height: 360px;
        overflow-y: scroll;
        padding: 0 20px;
    }
    .agreement_dia .dia_tit {
        background: #eee;
        text-align: center;
        font-size: 14px;
        font-weight: 700;
        color: #666;
    }
    .agreement_dia .dia_close {
        position: absolute;
        top: 8px;
        right: 20px;
        margin-top: 0;
        background: url(${basePath}/images/agree_close.gif) no-repeat;
    }
    .order_success{
        padding:15px 0px;
    }
</style>
</@htmlHead>
<@htmlBody>
<#include "../index/newtop7.ftl">
    <input type="hidden" id="currentProvince" value="${chProvince!''}" />
    <input type="hidden" id="basePath" value="${basePath}" />
    <div class="container" id="suborderContainer">
        <#if (topmap.temp.tempId!=10)>
        <div class = "logo fl head2">
            <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt="" style="width:165px;height:40px;"/></a>
        </div>
        </#if>
        <div class="head_s mb20">
            <div class="fr w700 pt10">
                <div class="flow_progress3">
                    <ul>
                        <li class="step1">1.查看购物车</li>
                        <li class="step2">2.填写核对订单信息</li>
                        <li class="step3">3.提交订单成功</li>
                    </ul>
                </div>
            </div>
       
            <div class="cb"></div>
        </div><!-- /head_s -->
        <form action="payorder.html"  class="payOrder" target="_blank" method="post">
		<input type="hidden" name="orderCount" value="${map.orders?size}"/>
        <div class="order_success">
        	<div class="success_notice">
            	<span class="success_icon"></span>
            	
                
                <#list map.orders as order>
                	<#if order.orderLinePay=='1'>
                		<h3 class="f20 mb10">订单提交成功，请您尽快付款！</h3>
                	</#if>
                	<#if order.orderLinePay=='0'>
                		<h3 class="f20 mb10">订单提交成功，耐心等待收货！</h3>
                	</#if>
                	<ul>
                        <#if order.businessId==0>
                        <#assign pid="${order.payId}">
                        <#else >
                            <#assign thirdpid="${order.payId}">
                        </#if>
                    <#if pid?? && pid=='1'>
                	    <input type="hidden" value="${order.orderCode}" name="orderCode"/>
                		<input type="hidden" value="${order.orderId}" name="orderId"/>
                		<input type="hidden" value="${order.orderOldCode}" name="orderOldCode"/>
                	</#if>
                        <#if thirdpid?? && thirdpid=='1'>
                	    <input type="hidden" value="${order.orderCode}" name="orderCode"/>
                		<input type="hidden" value="${order.orderId}" name="orderId"/>
                		<input type="hidden" value="${order.orderOldCode}" name="orderOldCode"/>
                	</#if>
                        <li>订单号：${order.orderCode}</li>
                    <li>应付金额：<b><font color="red"><span class='ct_price'><b>¥</b></span>${order.orderPrice?string("0.00")}</font></b></li>
                </ul>
                    <script type="text/javascript">
                        //支付成功页 2015.10.13 wuyanbo add
                        NTKF_PARAM.orderid="${order.orderId}";
                        NTKF_PARAM.orderprice="${order.orderPrice}";
                    </script>
                </#list>
            </div>
            <#assign thirdpid="${thirdpid!'-1'}">
            <#assign pid="${pid!'-1'}">
            <#if pid=='1'  ||thirdpid=='1'>
            <div class="pay_it">
            	<h2><span class="f14 fb">立即支付，即可完成订单</span></h2>
                <div class="pay_content">
                	<h4 class="f13 fb">您选择的支付方式是：</h4>
                    <div class="pay_icon">
                    		<#if map.payList??>
                    		<#list map.payList as pay>
                    			 <#if pay.payDefault=="1">
                    				<input type="hidden" value="${pay.payId}" name="payId" id="payId"/>
                    			 </#if> 	
                    			<#if (pay.payType='1' && pay.isOpen='1')>
                    				 <img 
                    				 <#if pay.payDefault=="1">
                    				 	class="selected"
                    				</#if> 
                    				  onclick="changethis(this,${pay.payId});"
                    				 alt="" src="${pay.payImage!''}" />
                    			</#if>

                                <#if (pay.payType='7' && pay.isOpen='1')><#--微信支付-->
                                    <img alt="" onclick="changethis(this,${pay.payId});"
                                        <#if pay.payDefault=="1">
                                         class="selected"
                                        </#if>
                                         src="${pay.payImage!''}" />
                                </#if>
                                <#if (pay.payType='5' && pay.isOpen='1')>
                                    <img alt="" onclick="changethis(this,${pay.payId});"
                                        <#if pay.payDefault=="1">
                                         class="selected"
                                        </#if>
                                         src="${pay.payImage!''}" />
                                </#if>
                                <#if (pay.payType='6' && pay.isOpen='1')>
                                    <img alt="" onclick="changethis(this,${pay.payId});"
                                        <#if pay.payDefault=="1">
                                         class="selected"
                                        </#if>
                                         src="${pay.payImage!''}" />
                                </#if>
                                <#if (pay.payType='8' && pay.isOpen='1')>
                                    <img alt="" onclick="changethis(this,${pay.payId});"
                                        <#if pay.payDefault=="1">
                                         class="selected"
                                        </#if>
                                         src="${pay.payImage!''}" />
                                </#if>
                                <#if (pay.payType='2' && pay.isOpen='1')>
                                    <img alt="" onclick="changethis(this,${pay.payId});"
                                        <#if pay.payDefault=="1">
                                         class="selected"
                                        </#if>
                                         src="${pay.payImage!''}" />
                                </#if>
                    		</#list>
                    		</#if>
                        <input type="button" onclick="payOrder()" class="pay_now" value="确定支付" />
                    </div>
                </div>
            </div>
            </#if>

            <div class="other_do">
            	<p class="f14"><#if pid?? && pid=='1'>完成支付后，</#if>您可以：<a href="${basePath}/customer/myorder.html">查看订单详情</a><a href="${basePath}/index.html">继续购物</a></p>
            </div>
        </div><!-- /order_success -->
        </form>
    </div><!-- /container -->

	<div class="mask"></div>
    <div class="dialog dia1">
        <div class="dia_tit clearfix">
            <h4 class="fl">支付提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="dia_intro no_tc pt30">
            	<em>付款进行中...</em>
            	<div class="mt20">
            		<p class="lh180">支付完成前，请不要关闭此支付验证窗口。</p>
            		<p class="lh180">支付完成后，请根据您支付的情况点击下面按钮。</p>
            	</div>
            </div>
            <div class="dia_ops mt20 tc">       
                <a class="go_shopping" href="${basePath}/customer/myorder.html">完成支付</a>
                 <a class="go_shopping" href="javaScript:void(0)" onclick="showHelp(this)" date-value="" id="payhelp">付款遇到问题</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
     <div class="mask"></div>
     <div class="dialog dia2 agreement_dia">
        <div class="dia_tit clearfix">
            	支付问题描述
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="agreement_wp no_tc pt30" id="payHelpDesc">
            	
            </div>
            <div class="dia_ops mt20 tc">       
               
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    <script type="text/javascript" src="${basePath}/js/default.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/goods_comm.js"></script>
    <script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>
    <script type="text/javascript">
         $(function(){
              <#if  map.payList??>
                <#list  map.payList as pay>
                      <#if pay.payDefault=="1">
                        $("#payhelp").attr("date-value",${pay.payId});
                      </#if>
               </#list>
              </#if>
             setSuborderContainerMinHeight();
         });

         /* *
         * 计算结算订单主体的最小高度，避免网站底部出现空白区域
         * 2015.11.04 wuyanbo add
         * */
         function setSuborderContainerMinHeight(){
             var bodyHeight = $(window).height();//当前屏幕的可视区域高度
             var topHeight = 30;
             var bottomHeight = 450;
             var suborderContainer = (bodyHeight - topHeight- bottomHeight);
             if(suborderContainer <= 350){
                 suborderContainer = 350;
             }
             $("div[id='suborderContainer']").css({"min-height":suborderContainer+"px"});
         }
        function changethis(obj,id){
            $("#payId").val(id);
            $(".pay_icon").find(".selected").removeClass("selected");
            $(obj).addClass("selected");
            $("#payhelp").attr("date-value",id);

        }
        function payOrder(){
            if($("#payId").val()==undefined){
            return ;
            }
            dia(1);
            if($("#payId").val()=='42'){
                $(".payOrder").attr("action","/payorderwx.htm");
                //window.open("${basePath}/payorderwx.htm");
            }else{
                $(".payOrder").attr("action","payorder.html");
            }
            $(".payOrder").submit();
        }
        function showHelp(obj){
           $(".dia1").hide();
          $("#payhelp").attr("date-value");
          var payId = ( $("#payhelp").attr("date-value"));
            $.ajax({
            type:"POST",
            url:"${basePath}/findpayone.htm",
            data:"payid="+payId,
            success:function(data){
                $("#payHelpDesc").html(data.payHelp);
                var _wd = $(window).width();
                var _hd = $(window).height();
                $(".dia2").css("top",(_hd - $(".dia2").height())/2).css("left",(_wd - $(".dia2").width())/2);
                $(".dia2").show();
                }
            });
        }
    </script>
    <#include "../index/newbottom.ftl" />
</@htmlBody>