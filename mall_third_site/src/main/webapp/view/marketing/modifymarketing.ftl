<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>促销管理</title>
    <link href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="${basePath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet">    
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">  
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/summernote.css"/>
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>
<body>

<#--引入头部-->
<#include "../index/indextop.ftl">

<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
            <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>促销管理</li>
                <li class="active" style="color: #07d;">优惠劵促销</li>
            </ol>

            <div class="app-content">
                <div class="form-container">
                        <ul class="nav nav-tabs">
                            <li class="active">
                               <#if marketingFlag=="DP">
                                <a href="javascript:void(0);">修改单品促销</a>
                               </#if>
                               <#if marketingFlag=="ZK">
                                <a href="javascript:void(0);">修改折扣促销</a>
                               </#if>
                               <#if marketingFlag=="BY">
                                <a href="javascript:void(0);">修改包邮促销</a>
                               </#if>
                               <#if marketingFlag=="MJO">
                                <a href="javascript:void(0);">修改满减促销</a>
                               </#if>
                               <#if marketingFlag=="MJP">
                                <a href="javascript:void(0);">修改满件促销</a>
                               </#if>
                            </li>
                        </ul>
                       <form class="form-horizontal" action="newsavemarketing.htm" id="addForm" method="post">
                         <#if catelist?? && catelist?size gt 0>
                          <input type="hidden" name="status" class="f_status" id="status" value="1">
                         </#if>
                         <#if brandlist?? && brandlist?size gt 0>
                          <input type="hidden" name="status" class="f_status" id="status" value="2">
                         </#if>
                         <#if kulist?? && kulist?size gt 0>
                          <input type="hidden" name="status" class="f_status" id="status" value="0">
                         </#if>
                         <input type="hidden" name="marketingFlag"  value="${(marketingFlag)!''}">
                         <input type="hidden" name="marketingId"  value="${(marketing.marketingId)!''}">
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>促销名称：</label>
                                <div class="controls col-xs-8">
                                    <input type="text" class="form-control required" placeholder="限制1~100字" name="marketingName" maxlength="100" value="${(marketing.marketingName)!'' }"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>促销时间：</label>
                                <div class="controls col-xs-8">
                                	<div class="row">
                                		<div class="col-xs-5">
                                			<div class="input-group date form_datetime" id="startpicker">
					                            <input class="form-control required" placeholder="开始时间" type="text" id="startTime" value="${(marketing.marketingBegin)?string("yyyy-MM-dd HH:mm:ss") }" readonly
					                                   name="sTime">
					                          	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
					                       	</div>
                                		</div>
                                		<div class="col-xs-1 col-line">-</div>
                                		<div class="col-xs-5">
                                			<div class="input-group date form_datetime" id="endpicker">
					                            <input class="form-control required" placeholder="结束时间" type="text" id="endTime" value="${(marketing.marketingEnd)?string("yyyy-MM-dd HH:mm:ss") }" readonly
					                                   name="eTime">
					                          	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
					                       	</div>
                                		</div>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group switch-wp">
                                <label class="control-label col-xs-2"><b>*</b>促销类型：<input type="hidden" name="marketingType" value="0"/></label>
                                <div class="controls col-xs-8">
                                    <#if codexList?? && codexList?size gt 0>
                                    <#list codexList as codex>
                                    <#if marketingFlag=='DP'>
                                      <#if codex.codexType=='1'>
                                      	<div style="line-height:34px;">
		                                    <label class="choose-label">
		                                    <#--<input name="codexchoose" type="radio" class="codexchoose"
		                                    <#if marketing.codexType==codex.codexType> checked="checked"</#if>
		                                     value="${codex.codexId }-${codex.codexType }" />-->
		                                     ${codex.codexName }</label>
	                                     </div>
                                      </#if>
                                    </#if>
                                    <#if marketingFlag=='BY'>
                                      <#if codex.codexType=='12'>
                                      	<div style="line-height:34px;">
	                                    	<label class="choose-label">${codex.codexName }</label>
	                                    </div>
                                      </#if>
                                    </#if>
                                    <#if marketingFlag=='ZK'>
                                      <#if codex.codexType=='15'>
                                      	<div style="line-height:34px;">
	                                    	<label class="choose-label">${codex.codexName }</label>
	                                    </div>
                                      </#if>
                                    </#if>
                                    <#if marketingFlag=='MJO'>
                                      <#if codex.codexType=='5' || codex.codexType=='8'>
                                      	<div class="radio radio-primary">
		                                    <label class="choose-label"><input name="codexchoose" type="radio" class="codexchoose"
		                                    <#if marketing.codexType==codex.codexType> checked="checked"</#if>
		                                     value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
	                                     </div>
                                      </#if>
                                    </#if>
                                    <#if marketingFlag=='MJP'>
                                      <#if codex.codexType=='13' || codex.codexType=='14'>
                                      	<div class="radio radio-primary">
		                                    <label class="choose-label"><input name="codexchoose" type="radio" class="codexchoose"
		                                    <#if marketing.codexType==codex.codexType> checked="checked"</#if>
		                                     value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
	                                     </div>
                                      </#if>
                                    </#if>
                                    </#list>
                                    </#if>
                                 	<input type="hidden" name="codexType" value="${marketing.codexType }" />
              						<input type="hidden" name="codexId" value="${marketing.codexId }" />
                                    <#if marketingFlag=='DP'>
	                                <div class="switch-box mold-switch">
	                                    <p class="switch-item" id="mold01" <#if marketing.codexType=='1'>style="display: block;"</#if>>单品直减<input class="form-control input-xs text-center <#if marketing.codexType=='1'>required</#if> isNumber" type="text" name="offValue" value="${(marketing.priceOffMarketing.offValue)!''}"/>元</p>
	                                    <p class="switch-item" id="mold09" <#if marketing.codexType=='9'>style="display: block;"</#if>>每人限购数量<input class="form-control input-xs text-center digits <#if marketing.codexType=='9'>required</#if>" type="text" name="limitCount" value="${(marketing.limitBuyMarketing.limitCount)!''}"/>件</p>
	                                </div>
	                                </#if>
	                                <#if marketingFlag=='MJO'>
	                                <div class="switch-box mold-switch">
	                                  <div class="switch-item" id="mold05" <#if marketing.codexType=='5'>style="display: block;"</#if>>
		                                   <ul class="mold-list">
	                                    	   <#if (marketing.fullbuyReduceMarketings)?? &&  (marketing.fullbuyReduceMarketings)?size gt 0>
			                                      <#list marketing.fullbuyReduceMarketings as reduce>
			                                        <li>
	                                                   	 满<input class="form-control input-xs text-center isNumber" name="fullPrice" type="text" value="${(reduce.fullPrice)!''}"/>元，减<input class="form-control input-xs text-center isNumber" name="reducePrice" type="text" value="${(reduce.reducePrice)!''}"/>元
		                                                <#if reduce_index gt 0>
		                                                <button class="btn btn-default btn-sm del-mold" type="button">删除本级促销</button>
		                                                </#if>
	                                                </li>
			                                      </#list>
		                                      </#if> 
		                                      <#if marketing.codexType=='8'>
		                                       <li>
                                                   	 满<input class="form-control input-xs text-center isNumber" name="fullPrice" type="text" />元，减<input class="form-control input-xs text-center isNumber" name="reducePrice" type="text" />元
                                                </li>
		                                      </#if>
		                                  </ul>
                                           <button class="btn btn-primary btn-sm add-mold02" type="button"><i class="glyphicon glyphicon-plus"></i>添加多级促销</button>
	                                  </div>
	                                  <div class="switch-item" id="mold08" <#if marketing.codexType=='8'>style="display: block;"</#if>>
	                                      <ul class="mold-list">
		                                     <#if (marketing.fullbuyDiscountMarketings)?? && (marketing.fullbuyDiscountMarketings)? size gt 0>
		                                         <#list marketing.fullbuyDiscountMarketings as discount>
		                                            <li>
	                                                   	 满<input class="form-control input-xs text-center isNumber required" type="text" name="fullPrice" value="${(discount.fullPrice)!''}">元，打<input class="form-control input-xs text-center zeroOne required" name="fullbuyDiscount" type="text" value="${(discount.fullbuyDiscount)!''}">折
	                                                    <#if discount_index == 0><label class="form-tips">折扣有效值0~1</label>
	                                                    <#else>
	                                                    <button class="btn btn-default btn-sm del-mold" type="button">删除本级促销</button>
	                                                    </#if>
	                                                </li>
		                                         </#list>
	                                        </#if>
	                                        <#if  marketing.codexType=='5'>
	                                           <li>
                                                   	 满<input class="form-control input-xs text-center isNumber " type="text" name="fullPrice" >元，打<input class="form-control input-xs text-center zeroOne " name="fullbuyDiscount" type="text">折
                                                    <label class="form-tips">折扣有效值0~1</label></li>
	                                        </#if>
	                                      </ul>
	                                     	<button class="btn btn-primary btn-sm add-mold01" type="button"><i class="glyphicon glyphicon-plus"></i>添加多级促销</button>
	                                  </div>
	                              </div>
	                              </#if>
	                              
	                              <#if marketingFlag=='MJP'>
	                              <div class="switch-box mold-switch">
	                                  <div class="switch-item" id="mold013" <#if marketing.codexType=='13'>style="display: block;"</#if>>
		                                   <ul class="mold-list">
	                                    	   <#if (marketing.fullbuyNoDiscountMarketings)?? &&  (marketing.fullbuyNoDiscountMarketings)?size gt 0>
			                                      <#list marketing.fullbuyNoDiscountMarketings as discount>
			                                        <li>
	                                                   	 满<input class="form-control input-xs text-center digits" type="text" name="packagesNo" value="${(discount.packagesNo)!''}"/>件，打<input class="form-control input-xs text-center zeroOne" name="packagebuyDiscount" type="text" value="${(discount.packagebuyDiscount)!''}"/>折
		                                                 <#if discount_index == 0><label class="form-tips">折扣有效值0~1</label>
	                                                    <#else>
		                                                <button class="btn btn-default btn-sm del-mold" type="button">删除本级促销</button>
		                                                </#if>
	                                                </li>
			                                      </#list>
		                                      </#if> 
		                                      <#if marketing.codexType=='14'>
		                                       <li>
                                                   	 满<input class="form-control input-xs text-center digits" name="countNo" type="text"/>件，共<input class="form-control input-xs text-center isNumber" name="countMoney" type="text"/>元
                                                </li>
		                                      </#if>
		                                  </ul>
                                           <button class="btn btn-primary btn-sm add-mold03" type="button"><i class="glyphicon glyphicon-plus"></i>添加多级促销</button>
	                                  </div>
	                                  <div class="switch-item" id="mold014" <#if marketing.codexType=='14'>style="display: block;"</#if>>
	                                      <ul class="mold-list">
		                                     <#if (marketing.fullbuyNoCountMarketings)?? && (marketing.fullbuyNoCountMarketings)? size gt 0>
		                                         <#list marketing.fullbuyNoCountMarketings as count>
		                                            <li>
	                                                   	 满<input class="form-control input-xs text-center digits" name="countNo"  type="text" value="${(count.countNo)!''}"/>件，共<input class="form-control input-xs text-center isNumber" name="countMoney" type="text" value="${(count.countMoney)!''}"/>元
	                                                    <#if count_index gt 0>
	                                                    <button class="btn btn-default btn-sm del-mold" type="button">删除本级促销</button>
	                                                    </#if>
	                                                </li>
		                                         </#list>
	                                        </#if>
	                                        <#if  marketing.codexType=='13'>
	                                           <li>
                                                   	 满<input class="form-control input-xs text-center digits" type="text"  name="packagesNo"/>件，打<input class="form-control input-xs text-center zeroOne" name="packagebuyDiscount" type="text"/>折
                                                    <label class="form-tips">折扣有效值0~1</label>
                                                </li>
	                                        </#if>
	                                      </ul>
	                                     	<button class="btn btn-primary btn-sm add-mold04" type="button"><i class="glyphicon glyphicon-plus"></i>添加多级促销</button>
	                                  </div>
	                              </div>
	                              </#if>
                               </div>
                            </div>
                             <#if marketingFlag=='BY'>
                             <div class="form-group">
                           		 <label class="control-label col-xs-2"><b>*</b>促销内容：</label>
			                      <div class="controls col-xs-8">
			                       		 购物满
			                        <input type="text" class="form-control input-xs text-center required isNumber" name="shippingMoney" value="${(marketing.shippingMoney)!''}"/>
			                        	元包邮
			                     </div>
                          	</div>
		                    </#if> 
                            <div class="form-group switch-wp">
                                <label class="control-label col-xs-2"><b>*</b>选择商品：</label>
                                <div class="controls col-xs-10">
                                	<div class="radio radio-primary">
                                		<label><input name="goods" type="radio" data-switch="goods01" <#if (marketing.isAll)?? && marketing.isAll=='1'>checked="checked"</#if> id="allgoods"/>全部商品</label>
	                                    <#--<label><input name="goods" type="radio" data-switch="goods02" <#if catelist??>checked="checked"</#if>/>分类</label>-->
	                                    <#--<label><input name="goods" type="radio" data-switch="goods03" <#if brandlist??>checked="checked"</#if>/>品牌</label>-->
	                                    <label><input name="goods" type="radio" data-switch="goods04" <#if (marketing.isAll)?? && marketing.isAll=='0' && kulist??>checked="checked"</#if>/>部分参与商品</label>
	                                    <input type="hidden" id="isAlls" value="${marketing.isAll}" name="isAll" >
	                                    <span id="pc" class="spanweight"></span>
	                                    <#if prolist?? && prolist?size gt 0>
	                                      <#list prolist as pro>
	                                       <input type="hidden" name="product_allids" value="${pro.goodsInfoId}">
	                                      </#list>
	                                    </#if>
                                	</div>
                                    <div class="switch-box goods-switch" >
                                        <div class="switch-item" id="goods01"></div>
                                        <div class="switch-item" id="goods02" <#if catelist??>style="display:block"</#if>>
                                            <table class="table table-bordered" id="catelist">
                                                <thead>
                                                <tr>
                                                    <th>分类编号</th>
                                                    <th>分类名称</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                  <#if catelist?? && catelist?size gt 0>
                                                   <#list catelist as cate>
                                                     <tr>
                                                        <td>${(cate.catId)!'' }<input type="hidden" name="cateIdp" id="cate_Id_p${(cate.catId)!'' }" value="${(cate.catId)!'' }"></td>
                                                        <td>${(cate.catName)!''}</td>
                                                        <td><button class="btn btn-primary btn-sm" type="button" onclick="del(this)">移除</button></td>
                                                     </tr>
                                                   </#list>
                                                  </#if>
                                                </tbody>
                                                <tfoot>
                                                <tr>
                                                    <td class="text-center" colspan="3">
                                                        <button class="btn btn-primary" type="button" data-toggle="modal" onclick="addCate()">添加</button>
                                                    </td>
                                                </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                        <div class="switch-item" id="goods03" <#if brandlist??>style="display:block"</#if>>
                                            <table class="table table-bordered" id="brandslist">
                                                <thead>
                                                <tr>
                                                    <th>品牌编号</th>
                                                    <th>品牌名称</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                 <#if brandlist?? && brandlist?size gt 0>
                                                   <#list brandlist as brand>
                                                     <tr>
                                                       <td>${brand.brandId}<input type="hidden" name="brandIdP" id="brandIdP${brand.brandId}" value="${brand.brandId}"></td>
                                                       <td>${brand.brandName}</td>
                                                       <td><button class="btn btn-primary btn-sm" type="button" onclick="del(this)">移除</button></td>
                                                     </tr>
                                                   </#list>
                                                 </#if>
                                                </tbody>
                                                <tfoot>
                                                <tr>
                                                    <td class="text-center" colspan="3">
                                                        <button class="btn btn-primary" type="button" data-toggle="modal" onclick="addBrands()">添加</button>
                                                    </td>
                                                </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                       <#if marketingFlag=='ZK'>
                                          <div class="switch-item" id="goods04" <#if marketing.isAll=='0' && kulist??>style="display:block"</#if>>
                                            <table class="table table-bordered" id="prolist">
                                                <thead>
                                                <tr>
                                                    <td class="discount-td" colspan="7">
                                                        <div class="discount-wp">
                                                            	批量折扣<input class="xs-input zeroOne" type="text" onblur="plzhekou(this)"/>
                                                            <div class="helpDot">
                                                                <a href="javascript:;" class="help-notes">?</a>
                                                            </div>
                                                        </div>
                                                        <button class="btn btn-primary btn-sm" type="button" onclick="yichus(1)">批量移除分</button>
                                                        <button class="btn btn-primary btn-sm" type="button" onclick="yichus(0)">批量移除角</button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>货品编号</th>
                                                    <th width="20%">货品名称</th>
                                                    <th>价格</th>
                                                    <th>折扣</th>
                                                    <th>折后价</th>
                                                    <th>折扣后金额操作</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <#if (marketing.preDiscountMarketings)?? && (marketing.preDiscountMarketings)?size gt 0>
                                                  <#list marketing.preDiscountMarketings as sku>
                                                    <tr><input type='hidden' name='goodsIdP' value="${(sku.goodsProduct.goodsInfoId)!''}" />
	                                                    <td>${(sku.goodsProduct.goodsInfoItemNo)!''}</td>
	                                                    <td>${(sku.goodsProduct.goodsInfoName)!''}</td>
	                                                    <td>&yen;${(sku.goodsProduct.goodsInfoPreferPrice)!''}<input type='hidden' class='goodsPrices' value='${(sku.goodsProduct.goodsInfoPreferPrice)!''}'></td>
	                                                    <td><input class="xs-input required zeroOne discountInfos" type="text" value="${(sku.discountInfo)!''}"  name="discountInfo" id="discount_info_${(sku.goodsProduct.goodsInfoId)!''}"  onblur="zkchange('${(sku.goodsProduct.goodsInfoPreferPrice)!''}',this)"/></td>
	                                                    <td>&yen;<span class='zhj'>${(sku.discountPrice)!''}</span><input type="hidden" name="discountPrice" value="${(sku.discountPrice)!''}"/><input type='hidden' class='form-control' name='discountFlag' id='discount_prices_${(sku.goodsProduct.goodsInfoId)!''}' value="0" readonly  ></td>
                                                        <td width="40%">
	                                                        <button class="btn btn-primary btn-sm" type="button" onclick="mofensingle('${(sku.goodsProduct.goodsInfoPreferPrice)!''}',this,1)">移除分</button>
	                                                        <button class="btn btn-primary btn-sm" type="button" onclick="mofensingle('${(sku.goodsProduct.goodsInfoPreferPrice)!''}',this,0)">移除角</button>
                                                        </td>
                                                        <td><button class="btn btn-primary btn-sm" type="button" onclick="del(this)">移除</button></td>
                                                    </tr>
                                                  </#list>
                                                </#if>
                                                </tbody>
                                                <tfoot>
                                                <tr>
                                                    <td class="text-center" colspan="7">
                                                        <button class="btn btn-primary" type="button" data-toggle="modal" onclick="addProducts(1,5,0)">添加</button>
                                                    </td>
                                                </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                         <#else>
                                           <div class="switch-item" id="goods04" <#if marketing.isAll=='0' && kulist??>style="display:block"</#if>>
                                            <table class="table table-bordered" id="prolist">
                                                <thead>
                                                <tr>
                                                    <th>货品编号</th>
                                                    <th>货品名称</th>
                                                    <th>价格</th>
                                                    <th>库存</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <#if marketing.isAll=='0' && kulist?? && kulist?size gt 0>
                                                  <#list kulist as sku>
                                                    <tr><input type='hidden' name='goodsIdP' value="${(sku.goodsInfoId)!''}" />
	                                                    <td>${(sku.goodsInfoItemNo)!''}</td>
	                                                    <td>${(sku.goodsInfoName)!''}</td>
	                                                    <td>&yen;${(sku.goodsInfoPreferPrice)!''}</td>
	                                                    <td>${(sku.goodsInfoStock)!''}</td>
	                                                    <td><button class="btn btn-primary btn-sm" type="button" onclick="del(this)">移除</button></td>
                                                    </tr>
                                                  </#list>
                                                </#if>
                                                </tbody>
                                                <tfoot>
                                                <tr>
                                                    <td class="text-center" colspan="5">
                                                        <button class="btn btn-primary" type="button" data-toggle="modal" onclick="addProducts(1,5,0)">添加</button>
                                                    </td>
                                                </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                       </#if>
                                    </div>
                                </div>
                            </div>
                            <#--<div class="form-group">
                                <label class="control-label col-xs-2">附加赠送：</label>
                                <div class="controls col-xs-8" style="margin-top:-7px;">
                                    <div class="radio radio-primary choose-bar">
                                        <label><input name="addGiveType" class="addGiveType" type="radio" value="0" <#if ((marketing.addGiveType)?? && marketing.addGiveType=='0') || (marketing.giveIntegral)??> checked="checked"</#if>/>赠送积分</label>
                                        <input type="text" class="form-control input-xs text-center digits giveIntegral" name="giveIntegral" value="${(marketing.giveIntegral)!''}"/>个
                                    </div>
                                    <div class="radio radio-primary choose-bar">
                                        <label><input name="addGiveType" class="addGiveType" type="radio" value="1" <#if (marketing.addGiveType)?? && marketing.addGiveType=='1'> checked="checked"</#if>/>赠送优惠券</label>
                                        <#if couponlist?? && couponlist?size gt 0>
                                        <select class="form-control couponId" name="couponId" <#if !(marketing.couponId)??> disabled="disabled"</#if>>
                                            <#list couponlist as coupon>
	                                            <option value="${(coupon.couponId)!'' }" <#if (marketing.couponId)??&& (marketing.couponId==coupon.couponId)>selected </#if>>${(coupon.couponName)!'' }</option>
                                            </#list>
                                        </select>
                                        </#if>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">会员等级：</label>
                                <#if customerLevel?? && customerLevel?size gt 0>
	                                <div class="controls col-xs-8">
	                                	<div class="checkbox checkbox-primary">
	                                		<label><input type="checkbox" onclick="allunchecked(this,'lelvelId')" name="onCheck"/>全部会员</label>
		                                    <#list customerLevel as level>
			                                    <label><input type="checkbox" name="lelvelId" onclick="checkLelvel('lelvelId')" value="${level.pointLelvelId }"
			                                     <#if marketing.marketLelvels??>
				                                     <#list marketing.marketLelvels as mlevel>
				                                       <#if level.pointLelvelId==mlevel.lelvelId>checked="checked"</#if>
				                                     </#list>
			                                     </#if>
			                                    />${level.pointLevelName }</label>
		                                    </#list>
	                                	</div>       
	                                </div>
                                </#if>
                            </div>-->
                            <div class="form-group">
                                <label class="control-label col-xs-2">活动站点：</label>
                                <div class="controls col-xs-8">
                                    <label class="control-label" style="text-align: left;">全部</label>
                                    <#--<select class="form-control"  name="activeSiteType" id="activeSiteType">
                                         <option value="0" <#if (marketing.activeSiteType)?? && marketing.activeSiteType=='0'>selected="selected"</#if>>平台</option>
				                         <option value="1" <#if (marketing.activeSiteType)?? && marketing.activeSiteType=='1'>selected="selected"</#if>>移动端</option>
										 <option value="2" <#if (marketing.activeSiteType)?? && marketing.activeSiteType=='2'>selected="selected"</#if>>全部</option>
                                    </select>-->
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">促销LOGO：</label>
                                <div class="controls col-xs-8" id="logolabel">
                                	<div class="checkbox checkbox-primary">
                                		<#if logolist?? && logolist?size gt 0>
	                                      <#list logolist as logo>
		                                      <label>
		                                        <input type="checkbox" name="promotionLogoId" value="${logo.promotionLogoId }"
		                                         <#if (marketing.marketLogos)??>
		                                         	<#list marketing.marketLogos as mlogo>
		                                         	 <#if mlogo.promotionLogoId==logo.promotionLogoId >checked="checked"</#if>
		                                         	</#list>
		                                         </#if>/>
		                                         <img alt="${logo.promotionLogoName}" title="${logo.promotionLogoName}" src="${logo.promotionLogoUrl }" height="20" >
		                                   	   </label>
	                                      </#list>
	                                    </#if>
	                                    <label>
	                                        <#--<button class="btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#addPromotions">添加</button>-->
	                                    </label>
                                	</div>
                                    
                                </div>
                            </div>
                            <input type="hidden" name="marketingDes" id="marketingDes"/>
                           <div class="form-group">
                                <label class="control-label col-xs-2">促销描述：</label>
                                <div class="controls col-xs-10">
                                    <div id="promotionDes">${(marketing.marketingDes)!''}</div>
                                </div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-xs-2"></label>
                                <div class="controls operation-control col-xs-8">
                                    <button class="btn btn-primary" type="button" onclick="subForm()">提交</button>
                                </div>
                            </div>
                   		</form>
                </div>
            </div>
        </div>
    </div>
</div>

<#--<div class="service-wrap">-->
    <#--<span class="service-close">×</span>-->
    <#--<a href="javascript:;" class="service-box">联系客服</a>-->
<#--</div>-->

<div class="back-to-top">
    <a href="javascript:;"><i></i>返回顶部</a>
</div>

<#--<div class="notice-center">-->
    <#--<div class="notice-center-ring"><i></i></div>-->
    <#--<div class="notice-center-wrapper">-->
        <#--<div class="nt-header">-->
            <#--<h3>系统通知（<span>1</span>）</h3>-->
            <#--<a href="javascript:;" class="nt-close">收起》</a>-->
        <#--</div>-->
        <#--<ul class="nt-content">-->
            <#--<li>-->
                <#--<div class="nt-item unread">-->
                    <#--<p>刘仙升于2015-04-08 15:41:23申请提现1.00元，已提现成功，请注意查询您的银行账户。</p>-->
                    <#--<a href="javascript:;">查看提现记录 》</a>-->
                <#--</div>-->
            <#--</li>-->
        <#--</ul>-->
        <#--<div class="nt-footer">-->
            <#--<a href="javascript:;" class="mark-read">全部标记已读</a>-->
            <#--<a href="javascript:;" class="nt-all">查看全部信息</a>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--<div class="page-help-btn">帮助</div>-->
<div class="page-help-container">
    <div class="page-help-content">
        <p style="color:#f00;">不知道从哪里开始？</p>
        <p>完成掌柜任务，简简单单开店铺！</p>
        <p>点击开始》》<a href="javascript:;">掌柜成长之旅</a></p>
    </div>
    <div class="page-help-operation">
        <a href="javascript:;" class="btn btn-primary btn-sm">进入帮助中心</a>
        <a href="javascript:;" class="btn btn-default btn-sm">建议反馈</a>
    </div>
</div>

<div class="modal fade" id="addSorts" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加分类</h4>
            </div>
            <div class="modal-body">
                <div class="add-sorts-wp">
                    <div class="choose-sorts-box optional-box">
                        <h4>添加分类</h4>
                        <div class="choose-sorts-cont">
                            <ul class="main-list" id="allcates">
                               <li>
                                   <div class="optional-item"><i class="glyphicon glyphicon-plus-sign item-switch"></i>服装鞋帽</div>
                                   <ul class="sec-list">
                                       <li>
                                           <div class="optional-item"><i class="glyphicon glyphicon-plus-sign item-switch"></i>女鞋</div>
                                           <ul class="third-list">
                                               <li>
                                                   <div class="optional-item">高跟鞋</div>
                                               </li>
                                               <li>
                                                   <div class="optional-item">凉鞋</div>
                                               </li>
                                               <li>
                                                   <div class="optional-item">休闲鞋</div>
                                               </li>
                                           </ul>
                                       </li>
                                       <li>
                                           <div class="optional-item"><i class="glyphicon glyphicon-plus-sign item-switch"></i>女装</div>
                                           <ul class="third-list">
                                               <li>
                                                   <div class="optional-item">连衣裙</div>
                                               </li>
                                               <li>
                                                   <div class="optional-item">背心</div>
                                               </li>
                                               <li>
                                                   <div class="optional-item">长裙</div>
                                               </li>
                                           </ul>
                                       </li>
                                   </ul>
                               </li>
                                <li>
                                    <div class="optional-item"><i class="glyphicon glyphicon-plus-sign item-switch"></i>箱包、珠宝饰品、钟表</div>
                                    <ul class="sec-list">
                                        <li>
                                            <div class="optional-item"><i class="glyphicon glyphicon-plus-sign item-switch"></i>箱包</div>
                                            <ul class="third-list">
                                                <li>
                                                    <div class="optional-item">单肩包</div>
                                                </li>
                                                <li>
                                                    <div class="optional-item">背包</div>
                                                </li>
                                                <li>
                                                    <div class="optional-item">电脑包</div>
                                                </li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div class="optional-item"><i class="glyphicon glyphicon-plus-sign item-switch"></i>珠宝饰品</div>
                                            <ul class="third-list">
                                                <li>
                                                    <div class="optional-item">项链</div>
                                                </li>
                                                <li>
                                                    <div class="optional-item">戒指</div>
                                                </li>
                                                <li>
                                                    <div class="optional-item">手镯</div>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="choose-sorts-box selected-box">
                        <h4>已选分类</h4>
                        <div class="choose-sorts-cont">
                        </div>
                    </div>

                    <div class="btn btn-default btn-sm add-choose" type="button">添加</div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                <button class="btn btn-primary" type="button" onclick="chooseCate()">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="addBrands" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加品牌</h4>
            </div>
            <div class="modal-body">
                <div class="add-sorts-wp">
                    <div class="choose-sorts-box optional-box">
                        <h4>添加品牌</h4>
                        <div class="choose-sorts-cont">
                            <ul class="main-list" id="addAllBrands">
                                <li>
                                    <div class="optional-item">品牌1</div>
                                </li>
                                <li>
                                    <div class="optional-item">品牌2</div>
                                </li>
                                <li>
                                    <div class="optional-item">品牌3</div>
                                </li>
                                <li>
                                    <div class="optional-item">品牌4</div>
                                </li>
                                <li>
                                    <div class="optional-item">品牌5</div>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="choose-sorts-box selected-box">
                        <h4>已选品牌</h4>
                        <div class="choose-sorts-cont">
                        </div>
                    </div>

                    <div class="btn btn-default btn-sm add-choose" type="button">添加</div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                <button class="btn btn-primary" type="button" onclick="chooseBrands()">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="addGoods" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加商品</h4>
            </div>
            <div class="modal-body">
              <form id="searchPro" action="" method="post">
                <input type="hidden" name="pageNo" id="pageNo" value=""/>
                <input type="hidden" name="pageSize" id="pageSize" value=""/>
                <div class="promotion-goods-item">
                    <h4>搜索条件</h4>
                    <div class="search-box">
                        <form action="">
                            <div class="form-group">
                                <div class="form-item">
                                    <label class="control-label">商品名称：</label>
                                    <div class="controls">
                                        <input type="text" class="form-control" id="goodsName" name="goodsName"/>
                                    </div>
                                </div>
                                <div class="form-item">
                                    <label class="control-label">商品编号：</label>
                                    <div class="controls">
                                        <input type="text" class="form-control" id="goodsNo" name="goodsNo"/>
                                    </div>
                                </div>
                                <div class="form-item">
                                    <label class="control-label">货品编号：</label>
                                    <div class="controls">
                                        <input type="text" class="form-control" id="productNo" name="goodsInfoItemNo"/>
                                    </div>
                                </div>
                                <#--<div class="form-item">
                                    <label class="control-label">货品状态：</label>
                                    <div class="controls">
                                        <select class="form-control">
                                            <option value="">上架</option>
                                            <option value="">下架</option>
                                        </select>
                                    </div>
                                </div>-->
                                <div class="form-item lg-item">
                                    <label class="control-label">货品售价：</label>
                                    <div class="controls">
                                        <input type="text" class="form-control sm-input" id="lowgoodsInfoPrice" name="lowGoodsInfoPrice"/>
                                        ~
                                        <input type="text" class="form-control sm-input" id="highgoodsInfoPrice" name="highGoodsInfoPrice"/>
                                    </div>
                                </div>
                            </div>
                            <div class="search-operation">
                                <button class="btn btn-primary btn-sm" type="button" onclick="addProducts(1,5)">搜索<i class="glyphicon glyphicon-search"></i></button>
                                <button class="btn btn-default btn-sm" type="button" onclick="clearInfo()">重置<i class="glyphicon glyphicon-refresh"></i></button>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="promotion-goods-item">
                    <h4>查询结果</h4>
                    <table class="table table-bordered query-table" id="allpro">
                        <thead>
                        <tr>
                            <th>货品编号</th>
                            <th>货品名称</th>
                            <th>销售价</th>
                            <th>库存</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="footer-operation" id="profoot">
                    </div>
                </div>

                <div class="promotion-goods-item">
                    <h4>已选货品<span>（促销设置完成后，该商品编码下新增的sku货品不会参与该促销）</span></h4>
                    <table class="table table-bordered result-table">
                        <thead>
                        <tr>
                            <th>货品编号</th>
                            <th>货品名称</th>
                            <th>销售价</th>
                            <th>库存</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                <#if marketingFlag=='ZK'>
    	            <button class="btn btn-primary" type="button" onclick="chooseZKPro()">确定</button>
				<#else>
	                <button class="btn btn-primary" type="button" onclick="choosePro()">确定</button>
                </#if>
            </div>
        </div>
    </div>
</div>

<#--<div class="modal fade" id="addPromotions" role="dialog" aria-hidden="true">-->
  <#--<form role="form" class="form-horizontal" enctype="multipart/form-data" action="addthirdlogo.htm" method="post" id="saveLogoForm">-->
    <#--<div class="modal-dialog">-->
        <#--<div class="modal-content">-->
            <#--<div class="modal-header">-->
                <#--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
                <#--<h4 class="modal-title">添加促销LOGO</h4>-->
            <#--</div>-->
            <#--<div class="modal-body">-->
               <#--<div class="form-item">-->
                     <#--<label class="control-label"><b>*</b>logo名称：</label>-->
                      <#--<div class="controls">-->
                        <#--<input type="text" class="form-control required" name="promotionLogoName" onblur="checkLogoNameExists(this)"/>-->
                     <#--</div>-->
                <#--</div>-->
                <#--<div class="form-item">-->
                    <#--<label class="control-label"><b>*</b>上传logo：</label>-->
                    <#--<div class="controls">-->
                        <#--<input type="file" class="required" name="picFile" id="logoImage"/>-->
                        <#--<iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="form-item">-->
                    <#--<label class="control-label">预览图片：</label>-->
                    <#--<div class="controls">-->
                        <#--<img alt="" src="" id="preview_image" width="90px" />-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
            <#--<div class="modal-footer">-->
                <#--<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>-->
                <#--<button class="btn btn-primary" type="button" onclick="submitSaveLogoForm()">确定</button>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
   <#--</form>-->
<#--</div>-->

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script src="${basePath}/js/bootstrap.min.js"></script>
<script src="${basePath}/js/bootstrap-select.min.js"></script>
<script src="${basePath}/js/bootstrap-datetimepicker.min.js"></script>
<script src="${basePath}/js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${basePath}/js/summernote.min.js"></script>
<script src="${basePath}/js/summernote-zh-CN.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/marketing/createmarketing.js"></script>
<script src="${basePath}/js/common/common_checked.js"></script>
<script src="${basePath}/js/marketing/promotion_logo.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script>
   $(function(){
   		$.material.init();
       $("#addForm").validate();
   
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN',
            todayBtn : true,
            pickerPosition : 'bottom-left',
            viewSelect : 'hour'
        });
        
         /* 富文本编辑框 */
        $("#promotionDes").summernote({
            lang: 'zh-CN',
            height: 200,
            tabsize: 2,
            onImageUpload: function(files, editor, $editable) {
                sendFile(files,editor,$editable);
            }
        });
        
        $('.summernotemobile').summernote({
            height: 300,
            lang: 'zh-CN',
            onImageUpload: function(files, editor, $editable) {
                sendFile(files,editor,$editable);
            }
        });
        
	  /*下面是时间选择器开始时间不能大于结束时间设置  START*/
	  //单品促销开始结束时间配置
	  var startTime = $("#startTime").val();
	  var endTime = $("#endTime").val();
	  $('#endpicker').datetimepicker('setStartDate', startTime);
	  $('#startpicker').datetimepicker('setEndDate', endTime);
	  $('#endpicker')
	          .datetimepicker()
	          .on('show', function (ev) {
	              startTime = $("#startTime").val();
	              endTime = $("#endTime").val();
	              $('#endpicker').datetimepicker('setStartDate', startTime);
	              $('#startpicker').datetimepicker('setEndDate', endTime);
	          });
	  $('#startpicker')
	          .datetimepicker()
	          .on('show', function (ev) {
	              endTime = $("#endTime").val();
	              startTime = $("#startTime").val();
	              $('#startpicker').datetimepicker('setEndDate', endTime);
	              $('#endpicker').datetimepicker('setStartDate', startTime);
	          });
	  /*下面是时间选择器开始时间不能大于结束时间设置  END*/
	   /* 下面是表单里面的日期时间选择相关的 END */
   
   
     $(".codexchoose").change(function(){
        	var $arr = $(this).val().split('-'); 
        		$(".mold-switch").find('.switch-item').hide();
        		$('#mold0'+$arr[1]).show();
        		$("input[name='codexId']").val($arr[0]);
        		$("input[name='codexType']").val($arr[1]);
        		$('.switch-item input[type="text"]').each(function(){
        			$(this).removeClass('required');
        		});
        		$('#mold0'+$arr[1]+' input[type="text"]').each(function(){
        			$(this).addClass('required');
        	  });
        });
        
        /*切换附加赠送*/
         $(".addGiveType").change(function(){
        	 if($(this).val()==0){
        		$(".giveIntegral").removeAttr("disabled");
        		 $(".giveIntegral").addClass("required");
        		 $(".couponId").attr("disabled","disabled");
        		 $(".couponId").removeClass("required");
        	 }else if($(this).val()==1){
        	     $(".couponId").addClass("required");
        		 $(".couponId").removeAttr("disabled");
        		 $(".giveIntegral").removeClass("required error");
        		 $(".giveIntegral").next("label").hide();
        		 $(".giveIntegral").attr("disabled","disabled");
        	 }
         });
    });

//保存商品促销 
  function subForm(){
          var pro =  document.getElementsByName("goodsIdP");
          var cro =  document.getElementsByName("cateIdp");
          var bro =  document.getElementsByName("brandIdP");
          var f = true;
          if(!$("#allgoods").prop('checked')){
	          if(($("#goods04").css("display")=='block' && pro.length ==0 )
                        ||($("#goods02").css("display")=='block' && cro.length==0)
                          ||($("#goods03").css("display")=='block' && bro.length==0)){
	              $("#pc").html('请选择商品');
	              $("#pc").addClass("error");
	              f=false&&f;
	          }else{
	             $("#isAlls").val(0);
	          }
          }else{
             $("#isAlls").val(1);
             $("#status").val(0);
          }
          if($("#goods04").css("display")=='block' && pro.length > 0){
            $("#status").val(0);
          }else if($("#goods02").css("display")=='block' && cro.length > 0){
            $("#status").val(1);
          }else if($("#goods03").css("display")=='block' && bro.length > 0){
            $("#status").val(2);
          }
          if($("#addForm").valid()&&f){
              $("#marketingDes").val($("#promotionDes").code());
              $("#addForm").submit();
          }   
     }
    
    function checkLelvel(name){
			var count =0;
			var clength = $("input[name='"+name+"']").length;
			$("input[name='"+name+"']").each(function(){
				if($(this).prop("checked")){
					count++;
				}
				if(!$(this).prop("checked")){
					$("input[name='onCheck']").prop("checked",false);
				}
			});
			if(count == clength){
				$("input[name='onCheck']").prop("checked",true);
			}
		}
       /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
       function show(){
           $(".show_text").fadeOut(1000).fadeIn(1000);
       }
       setTimeout("show()",1000);
</script>
</body>
</html>