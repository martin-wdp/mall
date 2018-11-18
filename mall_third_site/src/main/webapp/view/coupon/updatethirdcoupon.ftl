<!doctype html>
<html lang="zh-CN">
<head>
    <#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>促销管理</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
    <link href="${basePath}/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="${basePath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet">    
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
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
                <li class="active">促销管理</li>
            </ol>

            <div class="app-content">
                <div class="form-container">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="javascript:;">修改优惠券</a>
                            </li>
                        </ul>
                    <form class="form-horizontal" action="addcouponthird.htm" id="addForm" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="status" class="f_status" id="status" value="0">
                            <input type="hidden" name="couponId" id="couponId" value="${coupon.couponId}">
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>优惠券名称：</label>
                                <div class="controls col-xs-8">
                                    <input type="text" class="form-control required" placeholder="限制1~100字" name="couponName" value="${(coupon.couponName)!''}" maxlength="100"/>
                                </div>
                            </div>
                            <div class="form-group">
                              <label class="control-label col-xs-2"><b>*</b>优惠券图片：</label>
                               <div class="controls col-xs-8">
                                  <img alt="" src="${(coupon.couponPic)!''}" id="preview_image" width="90px" />
                                  <input  type="file"  name="picFile" id="couponImg" />
                                  <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                               </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>优惠时间：</label>
                                <div class="controls col-xs-8">
                                	<div class="row">
                                		<div class="col-xs-5">
                                			<div class="input-group date form_datetime" style="width:200px" id="startpicker">
					                            <input class="form-control sm-input required" placeholder="开始时间" type="text" id="startTime"  readonly
					                                   name="sTime" value="${coupon.couponStartTime?string("yyyy-MM-dd HH:mm:ss") }" >
					                          	<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
					                       	</div>
                                		</div>
                                		<div class="col-xs-1 col-line">-</div>
                                		<div class="col-xs-5">
                                			<div class="input-group date form_datetime" style="width:200px" id="endpicker">
					                            <input class="form-control sm-input required" placeholder="结束时间" type="text" id="endTime"  readonly
					                                   name="eTime" value="${coupon.couponEndTime?string("yyyy-MM-dd HH:mm:ss")}">
					                          	<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
					                       	</div>
                                		</div>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>优惠券面额：</label>
                                <div class="controls col-xs-8">
                                    <input type="text" class="form-control input-xs text-center required isNumber" name="reductionPrice" value="${(coupon.reductionPrice)!''}"/>
                                  	  元
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>使用规则：</label>
                                <div class="controls col-xs-8">
                                	<div class="radio radio-primary">
                                		<label><input name="couponRulesType" type="radio"  value='3' <#if (coupon.couponRulesType)?? && coupon.couponRulesType=='3'> checked="checked" </#if>/>不限制（任何金额可用）</label>
                                	</div>
                                    <div class="radio radio-primary">
                                    	<label><input name="couponRulesType" type="radio" class="isNumber" value="2"<#if (coupon.couponRulesType)?? && coupon.couponRulesType=='2'> checked="checked"</#if>/>满</label>
                                    	<input type="text" class="form-control input-xs text-center couponRulesType-input <#if (coupon.couponRulesType)?? && coupon.couponRulesType=='2'> required </#if>" name="fullPrice" value="${(coupon.couponFullReduction.fullPrice)!''}" <#if (coupon.couponRulesType)?? && coupon.couponRulesType=='3'> disabled </#if>/>元可用
                                    </div>
                                </div>
                            </div>
                            <div class="form-group switch-wp">
                                <label class="control-label col-xs-2"><b>*</b>适用范围：</label>
                                <div class="controls col-xs-10">
                                	<div class="radio radio-primary">
                                		<label class="choose-label"><input name="goods" type="radio" data-switch="goods01" id="allgoods"  <#if (coupon.isAll)?? && coupon.isAll=='1'>checked="checked"</#if>/>全部商品</label>
                                    	<label class="choose-label"><input name="goods" type="radio" data-switch="goods02" <#if catelist??>checked="checked"</#if>/>按分类</label>
                                    	<label class="choose-label"><input name="goods" type="radio" data-switch="goods03" <#if brandlist??>checked="checked"</#if>/>按品牌</label>
                                    	<label class="choose-label"><input name="goods" type="radio" data-switch="goods04"<#if (coupon.isAll)?? && coupon.isAll=='0' && skulist??>checked="checked"</#if>/>指定商品</label>
                                	</div>
                                    <input type="hidden" id="isAlls" value="0" name="isAll" >
                                    <span id="pc" class="spanweight"></span>
                                    <#if prolist?? && prolist?size gt 0>
                                      <#list prolist as pro>
                                       <input type="hidden" name="product_allids" value="${pro.goodsInfoId}">
                                      </#list>
                                    </#if>
                                    <div class="switch-box goods-switch">
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
                                        <div class="switch-item" id="goods03"  <#if brandlist??>style="display:block"</#if>>
                                            <table class="table table-bordered" id="brandslist" <#if brandlist??>style="display:block"</#if>>
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
                                        <div class="switch-item" id="goods04" <#if (coupon.isAll)?? && coupon.isAll=='0' && skulist??>style="display:block"</#if>>
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
                                                <#if coupon.isAll=='0' && skulist?? && skulist?size gt 0>
                                                  <#list skulist as sku>
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
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>发行总数：</label>
                                <div class="controls col-xs-8">
                                    <input type="text" class="form-control input-xs text-center required digits"  name="couponCount" value="${(coupon.couponCount)!''}"/>张
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>每人限领：</label>
                                <div class="controls col-xs-8">
                                    <input type="text" class="input-xs form-control text-center required digits"  name="couponGetNo" value="${(coupon.couponGetNo)!''}"/>张
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">会员等级：</label>
                                <div class="controls col-xs-8">
                                	<div class="checkbox checkbox-primary">
                                		<label class="choose-label"><input type="checkbox" name="all" id="all" onclick="levelall(this)"/>全部会员</label>
	                                    <#list customerLevel as level> 
	                                     <label class="choose-label">
		  									<input type="checkbox" value="${level.pointLelvelId }" name="lelvelId" 
		  									<#list listLevel as lel> 
		  								      <#if lel.lelvelId==level.pointLelvelId> checked ="checked" </#if>
		  							        </#list>
		  									 onclick="levelonly();"/>${level.pointLevelName } &nbsp;
		  								 </label>
		  								</#list>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">活动站点：</label>
                                <div class="controls col-xs-8">
                                    <select class="form-control"  name="activeSiteType" id="activeSiteType">
                                         <option value="0" <#if (coupon.activeSiteType)?? && coupon.activeSiteType=='0'>selected="selected"</#if>>平台</option>
				                         <option value="1" <#if (coupon.activeSiteType)?? && coupon.activeSiteType=='1'>selected="selected"</#if>>移动端</option>
										 <option value="2" <#if (coupon.activeSiteType)?? && coupon.activeSiteType=='2'>selected="selected"</#if>>全部</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">领用方式：</label>
                                <div class="controls col-xs-8">
                                	<div class="radio radio-primary">
                                		<label class="choose-label"><input name="couponGetType" type="radio" value="0" <#if (coupon.couponGetType)?? && coupon.couponGetType=='0'>checked="checked"</#if>/>领取券（通过链接领取优惠券）</label>
                                    	<label class="choose-label"><input name="couponGetType" type="radio" value="1" <#if (coupon.couponGetType)?? && coupon.couponGetType=='1'>checked="checked"</#if>/>发放（注册或购买商品后直接发放到用户）</label>
                                	</div>
                                </div>
                            </div><input type="hidden" name="couponRemark" id="couponRemark"/>   
                            <div class="form-group">
                                <label class="control-label col-xs-2">优惠券描述：</label>
                                <div class="controls col-xs-10">
                                    <div id="promotionDes">${(coupon.couponRemark)!''}</div>
                                </div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-xs-2"></label>
                                <div class="controls col-xs-8">
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
                                            <ul class="third-list" >
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

                    <div class="choose-sorts-box selected-box brand-box">
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
                <div class="promotion-goods-item">
                    <h4>搜索条件</h4>
                    <div class="search-box">
                        <form action="" id="searchPro" method="post">
                           <input type="hidden" name="pageNo" id="pageNo" value=""/>
			                <input type="hidden" name="pageSize" id="pageSize" value=""/>
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
                <button class="btn btn-primary" type="button" onclick="choosePro()">确定</button>
            </div>
        </div>
    </div>
</div>



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
<script type="text/javascript" src="${basePath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script>
    $(function(){
    	 $.material.init();
         $("#couponImg").change(function() {
	        $("#addForm").attr("action","uploadImg.htm");
	        $("#addForm").attr("target","uploadFrame");
	        $("#addForm").submit();
       });

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
        
        
        $("input[name='couponRulesType']").change(function(){
            if($(".couponRulesType-input").prev("input[name='couponRulesType']").prop("checked") == true) {
                $(".couponRulesType-input").removeAttr("disabled");
            } else {
                $(".couponRulesType-input").attr("disabled","disabled");
                $(".couponRulesType-input").removeClass("required");
            };
        });
    });
    
    function levelall(obj){ 
		var le = document.getElementsByName("lelvelId");
		if(obj.checked){
			for(var i=0;i<le.length;i++){
				if(le[i].checked==false){
					le[i].checked=true;
				}
			} 
		}else{
			for(var i=0;i<le.length;i++){
				if(le[i].checked==true){
					le[i].checked=false;
				}
			}
		} 
	}
	
	function levelonly(){
		var temp=0;
		var le = document.getElementsByName("lelvelId");
		for(var i=0;i<le.length;i++){
			if(le[i].checked==true){ 
			   temp+=1; 
			}
		}    
		if(temp==le.length){
			var all=document.getElementsByName("all");
			all[0].checked=true;  
		}else{
			var all=document.getElementsByName("all");
			all[0].checked=false;
		}
	}
	
	//保存商品促销 
  function subForm(){
          var pro =  document.getElementsByName("goodsIdP");
          var cro =  document.getElementsByName("cateIdp");
          var bro =  document.getElementsByName("brandIdP");
          var f = true;
          if(!$("#allgoods").prop('checked')){
	          if(pro.length ==0 && cro.length==0 && bro.length==0){
	              $("#pc").html('请选择商品');
	              $("#pc").addClass("error");
	              f=false&&f;
	          }else{
	             $("#isAlls").val(0); 
		          if(pro.length > 0){
		            $("#status").val(0);
		          }else if(cro.length > 0){
		            $("#status").val(1);
		          }else if(bro.length > 0){
		            $("#status").val(2);
		          }
	          }
          }else{
             $("#isAlls").val(1);
             $("#status").val(0);
          }
          if($("#addForm").valid()&&f){
              $("#couponRemark").val($("#promotionDes").code());
              $("#addForm").attr("action","doupdatethirdcouponbyid.htm");
               $("#addForm").attr("target","_self");
              $("#addForm").submit();
          }   
     }
     
     /**
	 * 图片上传回调方法
	 * @param data 图片链接或者信息
	 */
	function callback(data) {
	    $("#preview_image").attr("src",data);
	}
	$(function(){
		$("input[name='couponRulesType']").change(function(){
			if($("input[name='couponRulesType']:checked").val() == 2) {
				$(".couponRulesType-input").removeAttr("disabled");	
			} else {
				$(".couponRulesType-input").attr("disabled","disabled");	
			};
		});
	});

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
</body>
</html>