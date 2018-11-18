<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-装箱</title>
<#assign basePath=request.contextPath>
<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>

<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/third.css" />
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/order/printoutstock.js"></script>
<script type="text/javascript" src="${basePath}/js/print_common.js"></script>
<style media=print>
.Noprint{display:none;} 
.PageNext{page-break-after:always;}
</style>  



<style>
		* {margin:0; padding:0;}
		body {font:12px tahoma,arial,\5b8b\4f53; color:#333;}
		.print_wp {width:700px; margin:0 auto; font-size:14px;}
		table {width:100%; border-collapse:collapse; border-spacing:0;}
		.print_info {margin-top:50px;}
		.print_info td {text-align:left; line-height:30px;}
		.print_tb {margin:10px 0;}
		.print_tb th, .print_tb td {border:1px solid #ddd; padding:5px 15px; text-align:center;}
		.settle_tb td {line-height:30px;}
	</style>
</head>
<script type="text/javascript">

	 function clsWindow(){
	 	window.opener.location.href="querythirdoutstock.html?orderCargoStatus=1";
	 	window.close();
	 }
</script>
<body>
	<div class="dialog s_dia dia5" style="width: 300px; height: 150px; display: none;">
	   
		    <input type="hidden" id="thirdOrderId" name="orderId" value=""/>
		    <div class="sd_tit clearfix"><h3 class="fl">操作提示</h3>
		    	<a class="sd_close fr" href="javascript:;" onclick="cls()"></a>
		    </div>
		    <div class="pmt_wp" style="height: 50px;">
		    	<p class="tc f14" style="line-height:70px;">装箱成功。</p>
		    </div>
		    <div class="tc mt20">
		    	<a class="sop_btn" href="javascript:;" onclick="clsWindow()" >确定</a>
		    </div>
		
    </div>
	
	<div class="print_wp " >
        <!--startprint1-->
        <div class="modal-header">
            <h4 class="modal-title">装箱管理</h4>
        </div>
        <div class="modal-body">
            <div class="picking-info">
                <span><b>收货人：</b>${order.shippingPerson}</span>
                <span><b>订购日期：</b><#if order.createTime??>${order.createTime?date}</#if></span>
                <span>
                    <b>付款方式：</b>
                    <#if order.orderLinePay=="0">
                        货到付款
                    <#elseif order.orderLinePay=="1">
                        在线支付
                    </#if>
                </span>
                <span><b>订单号：</b>${order.orderCode}</span>
                <span><b>收货地址：</b>${order.shippingProvince} ${order.shippingCity} ${order.shippingCounty} ${order.shippingAddress}</span>
                <span><b>邮编：</b><#if order.shippingPostcode??>${order.shippingPostcode}</#if></span>
            </div>
           <#list relations as relation>
            <h4 class="picking-box-tit">装箱单${relation_index+1 }</h4>
                <#if relation.isGoodsInfos ??>
               <table class="table table-bordered">
                   <thead>
                   <tr>
                       <th>商品编号</th>
                       <th>商品名称</th>
                       <th>商品规格</th>
                       <th>价格</th>
                       <th>商品数量</th>
                       <th>小计</th>
                       <th>操作</th>
                   </tr>
                   </thead>
                   <#list relation.containers as containers>
                       <tbody>
                           <#if containers.containerStatus == '0'>
                           <tr>
                               <td>${containers.goodsProductDetailViewVo.goodsInfoItemNo}</td>
                               <td>${containers.goodsProductDetailViewVo.goodsInfoName}</td>
                               <td>
                                   <#list containers.goodsProductDetailViewVo.specVo as specVo>
                                   ${specVo.spec.specName}: ${specVo.goodsSpecDetail.specDetailName}<br/>
                                   </#list>
                               </td>
                               <td>${containers.goodsProductDetailViewVo.goodsInfoPreferPrice}</td>
                               <td>${containers.goodsNum}</td>
                               <td>${containers.goodsNum*containers.goodsProductDetailViewVo.goodsInfoPreferPrice}</td>
                               <td>
                                   <button class="btn btn-primary btn-sm" onclick="changeContainers(${containers.containerId},${containers.goodsNum})" type="button" data-toggle="modal" data-target="#changeBox">更换包裹</button>
                                   <#--<a class="btn dropdown-toggle"  href="javascript:;" onclick="menu_btn(this)"><span class="caret"></span></a>
                                   <ul class="dropdown-menu">
                                   </ul>-->
                               </td>
                           </tr>
                           </#if>
                       </tbody>
                   </#list>
               </table>
           </#if>

           <#if relation.isGifts ??>
               <table class="table table-bordered">
                   <thead>
                   <tr>
                       <th>赠品编号</th>
                       <th>赠品名称</th>
                       <th>赠品数量</th>
                       <th>操作</th>
                   </tr>
                   </thead>
                   <#list relation.containers as containers>
                       <tbody>
                           <#if containers.containerStatus == '1'>
                           <tr>
                               <td>${containers.gift.giftCode}</td>
                               <td>${containers.gift.giftName}</td>
                               <td>${containers.goodsNum}</td>
                               <td>
                                   <button class="btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#changeBox">更换包裹</button>
                                   <a class="btn dropdown-toggle"  href="javascript:;" onclick="menu_btn(this)"><span class="caret"></span></a>
                                   <ul class="dropdown-menu">
                                   </ul>
                               </td>
                           </tr>
                           </#if>
                       </tbody>
                   </#list>
               </table>
           </#if>
           <button class="btn btn-primary" onclick="delContainers(${relation.relationId})" type="button">删除此装箱单</button>
           </#list>
            <!--endprint1-->
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" onclick="preview(1)" type="button">打印</button>
            <button class="btn btn-primary" onclick="doPrint(${order.orderId})" type="button">装箱</button>
            <button class="btn btn-primary" onclick="addContainers(${order.orderId});" type="button">新增装箱单</button>
            <button class="btn btn-primary" onclick="javascript:window.close();" type="button">关闭</button>
        </div>

    </div><!--/print_wp-->

    <#--更换包裹弹出框-->
    <div class="modal fade" id="changeBox" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">操作提示</h4>
                </div>
                <div class="modal-body">
                    <form  action="thirdupdatecontainer.htm" class="changeContainer" method="post">
                        <input name="containerId"  value=""  type="hidden" id="container_id"/>
                        <input name="orderId" type="hidden" value="${order.orderId}">
                        <input  type="hidden" name="goodsNums" id="goodsNums" />
                        <div class="modal-body">
                            请选择装箱单：
                            <select name="relationId" class="re_id">
                                <option value="">请选择-</option>
                                   <#list relations as relation>
                                        <option value="${relation.relationId }">装箱单${relation_index+1}</option>
                                    </#list>
                            </select><br/><br/>
                            请输入数量：<input name="goodsNum"
                                         onkeyup="value=value.replace(/[^\d]/g,'') "
                                         onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
                                         ID="Text2" NAME="Text2"

                                          style="width:100px;"/><br/><br/>
                            <span id="errorInfo" style="color:#ff0000"></span>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" onclick="changeContainerRe();">确定</button>
                </div>
            </div>
        </div>
    </div>


    <!--删除装箱单 有商品禁止删除 弹出框-->
    <div class="modal fade" id="deleteBox" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">操作提示</h4>
                </div>
                <div class="modal-body">
                    包裹内存在商品，不可删除！
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>

    </div>

    <div class="modal fade" id="ZXout_success" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">操作提示</h4>
                </div>
                <div class="modal-body zhuang">
                    请先打印！
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="window.close();"  data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>

	<form action="thirddelrelationbyid.htm" id="del_relation" method="post">
		<input name="relationId"  value=""  type="hidden" id="del_relation_id"/>
		<input name="orderId" type="hidden" value="${order.orderId}">
	</form>
    <input type="hidden" id="printNo" value="0" />
    <input type="hidden" id="printNo2" value="0" />
    <input type="hidden" id="printNo3" value="0" />
	<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
 	<#import "../common/selectindex.ftl" as selectindex>
	<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
</html>
