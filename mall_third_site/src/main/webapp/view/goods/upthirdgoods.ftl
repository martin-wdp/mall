<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商城第三方后台-商品管理</title>
<#assign basePath=request.contextPath />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/third.css"/>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet"> 
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/ripples.min.js"></script>
	<script src="${basePath}/js/material.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/third.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/thirdgoods.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/newupload.js"></script>
    <script type="text/javascript" src="${basePath}/js/common.js"></script>
    <script type="text/javascript" src="${basePath}/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="${basePath}/js/artDialog4.1.7/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.slimscroll.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/summernote.css">
    <script type="text/javascript" src="${basePath}/js/summernote.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/summernote-zh-CN.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/common_alert.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/common_date.js"></script>
    <script src="${basePath}/js/app.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/goodsAdd.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/third.css"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/bootstrap-chosen.css"/>
    <script src="${basePath}/js/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/angular.min.js"></script>
</head>
<body>
<#--引入头部-->
<#include "../index/indextop.ftl">

<!-- 保存商品的表单 -->
<form class="save_goods_info" action="sathirdgoods.htm" method="post" target="hidden_frame"
      enctype="multipart/form-data">
</form>

<!-- 模拟无刷新上传图片用到的 -->
<iframe name="hidden_frame" style="display:none"></iframe>

<!-- 保存商品详情的表单 -->
<form class="save_goods_desc" action="tNewUploadSaveGoodsDesc.htm" method="post" target="hidden_frame"
      enctype="multipart/form-data">
    <input type="hidden" class="new_goods_id" name="goodsId" value="">
    <input type="hidden" class="goods_desc" name="goodsDetailDesc" value="">
    <input type="hidden" class="goods_mobile_desc" name="goodsMobileDesc" value="">
</form>


<div class="wp">
    <input type="hidden" value="${isThirdAuditUsed!''}" id="isThirdAuditUsed"/>
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
                <li>商品管理</li>
                <li class="active" style="color: #07d;">上传商品</li>
            </ol>

            <div class="app-content step_wp step_01">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="javascript:;">添加商品</a>
                    </li>
                </ul>

                <div>
                    <div class="cfg-content">
                        <div class="sorts-wp">
                            <div class="sorts-item">
                                <div class="cate-search">
                                    <input type="hidden" id="parentId1">
                                    <input type="text" placeholder="输入名称查找" id="firstText"/>
                                    <button type="button"><i class="glyphicon glyphicon-search"
                                                             onclick="searfirst()"></i></button>
                                </div>
                                <div class="cate-list">
                                    <ul class="cate-items cat_first" id="cat_first">
                                    </ul>
                                </div>
                            </div>

                            <div class="sorts-item">
                                <div class="cate-search">
                                    <input type="hidden" id="parentId2">
                                    <input type="text" placeholder="输入名称查找" id="secondText"/>
                                    <button type="button"><i class="glyphicon glyphicon-search"
                                                             onclick="searSecond();"></i></button>
                                </div>
                                <div class="cate-list">
                                    <ul class="cate-items cat_second" id="cat_second">
                                    </ul>
                                </div>
                            </div>

                            <div class="sorts-item">
                                <div class="cate-search">
                                    <input type="hidden" name="catId" id="parentId3" class="ch_goods_cate">
                                    <input type="text" placeholder="输入名称查找" id="thirdText"/>
                                    <button type="button"><i class="glyphicon glyphicon-search"
                                                             onclick="searThird();"></i></button>
                                </div>
                                <div class="cate-list">
                                    <ul class="cate-items cat_third" id="cat_third">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <p class="selected-sorts">
                            当前选择的是：
                            <b><span class="firstCate">请选择商品所属分类</span></b>
                            <b><span class="secondCate"></span></b>
                            <b><span class="thirdCate"></span></b>
                           <span class="fouthCate"></span></

                        </p>
                        <p ><a  href="cateManager.html?n=3&l=47" style="color:blue;font-size: 15px;">添加商品分类</a></p>
                        <div class="add-sorts-operation">
                        <#if haveFreight=="1">
                            <a class="btn btn-primary step_next" data-role="step_first">下一步</a></#if>
                        <#if haveFreight=="0">
                            <a class="btn btn-primary " data-role="step_first">下一步</a>
                            你还没有添加一个默认模板，请先添加默认<a href="freighttemplatelistthird.htm?n=2&l=92">运费模板</a></#if>
                        </div>
                    </div>
                </div>
            </div>
            <!--/step_01-->

            <!-- --------------------------------------------------------------------------------------- -->

            <div class="app-content step_wp step_02 none">
                <input type="hidden" name="thirdCateId" class="ch_third_catid" value="-1"/>

                <div class="form-container">
                    <h3 class="fm-tit">基本信息</h3>

                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-xs-2"><b>*</b>商品类目：</label>
                            <input type="hidden" name="catId" class="ch_goods_cate" value="">

                            <div class="controls col-xs-8">
                                <div class="sorts-wp">
                                    <div class="sorts-item">
                                        <div class="cate-search">
                                            <input type="text" class="up_sch fir_search" placeholder="输入名称查找"/>
                                            <button type="button"><i class="glyphicon glyphicon-search"
                                                                     onclick="searchCate();"></i>
                                            </button>
                                        </div>
                                        <div class="cate-list">
                                            <ul class="cate-items fir_list"></ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <form class="form-horizontal" id="goods_info_form" action="" method="post">
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>商品标题：</label>

                                <div class="controls col-xs-8">
                                    <input type="text" name="goodsNameTemp"
                                           class="form-control name_input required"
                                           maxlength="50" minlength="3" value="<#--${importGoods.goodsName }-->"
                                           onblur="$('.name_input').val($(this).val())"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">商品副标题：</label>

                                <div class="controls col-xs-8">
                                        <textarea name="desInputTemp" class="form-control des_input" rows="2"
                                                  onblur="$('.des_input').text($(this).val())"><#--${importGoods.goodsSubtitle }--></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-xs-2">商品标签：</label>

                                <div class="controls col-xs-8">
                                	<div class="checkbox checkbox-primary">
                                		<#list tagList as tag>
	                                    <label class="choose-label">
	                                        <input class="goods_tag" type="checkbox" name="goods_tags" value="${tag.tagId }"/>
	                                        <img src="${tag.tagImg!!}" title=" ${tag.tagName!! }"/>
	                                    </label>
	                                    </#list>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">服务支持：</label>

                                <div class="controls col-xs-8">
                                	<div class="checkbox checkbox-primary">
                                		<#list support as supp>
		                                    <label class="choose-label"><input class="pro_supp" type="checkbox" value="${supp.id }"
		                                                                       name="ProSupTemp"/>${supp.serviceName }
		                                    </label>
		                                </#list>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>销售价格：</label>

                                <div class="controls col-xs-8">
                                    <input type="text" name="proPriceTemp"
                                           class="form-control sm-input pro_price required number"
                                           onblur="changeAllPrice(this);"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>成本价格：</label>

                                <div class="controls col-xs-8">
                                    <input name="proCostPriceTemp" type="text"
                                           class="form-control sm-input pro_cost_price required number"
                                           onblur="$('.pro_cost_price').val($(this).val())"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>市场价格：</label>

                                <div class="controls col-xs-8">
                                    <input type="text" name="proMarkPriceTemp"
                                           class="form-control sm-input pro_mark_price required number"
                                           onblur="$('.pro_mark_price').val($(this).val())"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>库存：</label>

                                <div class="controls col-xs-8">
                                    <input type="text" name="proStock"
                                           class="form-control sm-input pro_stock required number"
                                           onblur="$('.pro_stock').val($(this).val())"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>重量：</label>

                                <div class="controls col-xs-2">
                                    <div class="input-group">
                                        <input type="text" name="proWeightTemp"
                                               class="form-control sm-input pro_weight required digits"
                                               onblur="$('.pro_weight').val($(this).val())" style="width:100px;"/>
                                        <span class="input-group-addon" id="basic-addon2">克</span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">商品状态：</label>
                                <div class="controls col-xs-8">
                                	<div class="checkbox checkbox-primary">
                                		<label class="choose-label">
                                			<input type="checkbox" name="showListP" value=""
                                                                       class="show_list"
                                                                       onclick="$('.show_list_temp').click();"/>类目页推荐
                                        </label>
                                    	<label class="choose-label">
                                    		<input type="checkbox" name="showListP" value=""
                                                                       class="show_mobile"
                                                                       onclick="$('.show_mobile_temp').click();"/>手机版推荐
                                        </label>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group" style="display: none;">
                                <label class="control-label col-xs-2"><b>*</b>是否包邮：</label>
                                <div class="controls col-xs-8">
                                	<div class="radio radio-primary">
                                		<label class="choose-label"><input name="postFree" class="isMailBay"
                                                                       value="1"
                                                                       type="radio"
                                                                       onclick="$('.post_free_yes').click();"/>包邮</label>
                                    	<label class="choose-label"><input name="postFree" class="isMailBay"
                                                                       value="0"
                                                                       checked="checked"
                                                                       type="radio"
                                                                       onclick="$('.post_free_not').click();"/>买家承担运费</label>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2"><b>*</b>是否上架：</label>
                                <div class="controls col-xs-8">
                                	<div class="radio radio-primary">
                                		<label class="choose-label"><input name="shelves" type="radio"
                                                                       class="pro_status"
                                                                       value="1"
                                                                       onclick="$('.up_yes').click();"/>立即上架</label>
                                    	<label class="choose-label"><input name="shelves" type="radio"
                                                                       class="pro_status" value="0"
                                                                       checked="checked"
                                                                       onclick="$('.up_not').click();"/>下架</label>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group" style="display: none;">
                                <label class="control-label col-xs-2"><b>*</b>参与会员折扣：</label>

                                <div class="controls col-xs-8">
                                	<div class="radio radio-primary">
                                		<label class="choose-label"><input name="customerDisc"
                                                                       class="customer_discount"
                                                                       type="radio" value="1"
                                                                       onclick="$('.customer_discount_yes').click();"/>是</label>
                                    	<label class="choose-label"><input name="customerDisc"
                                                                       class="customer_discount"
                                                                       type="radio" value="0"
                                                                       checked="checked"
                                                                       onclick="$('.customer_discount_not').click();"/>否</label>
                                	</div>
                                </div>
                            </div>
                        </form>
                        <div class="form-group">
                            <label class="control-label col-xs-2"><b>*</b>商品品牌：

                            </label>

                            <div class="controls col-xs-8">
                                <select class="form-control" data-live-search="true"
                                        name="brandId"
                                        id="goods_brand">
                                </select>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-2"><b>*</b>商品属性：</label>

                            <div class="controls col-xs-10">
                                <table class="table table-bordered table-hover" id="attribute">
                                    <thead>
                                    <!-- <tr>
                                      <th>参数名</th>
                                      <th>参数值</th>
                                    </tr> -->
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-2"><b>*</b>规格：</label>

                            <div class="controls col-xs-10 type_spec">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-2">关联商品：</label>

                            <div class="controls col-xs-10">
                                <button class="btn btn-primary" type="button" data-toggle="modal"
                                        data-target="#addRelation" onclick="showAddGoodsRelModal()">添加关联商品
                                </button>
                                <table class="table table-bordered table-hover mt20" id="select_rel_goods">
                                    <thead style="display: none;">
                                    <tr>
                                        <td width="80px">商品图片</td>
                                        <td width="200px">商品编号</td>
                                        <td width="450px">商品名称</td>
                                        <td width="60px">操作</td>
                                    </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-2">PC版详情：</label>

                            <div class="controls col-xs-10">
                                <div class="summernotedesc"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-2">移动版详情：</label>

                            <div class="controls col-xs-10">
                                <div class="summernotemobile"></div>
                            </div>
                        </div>
                    </div>
                    <div class="add-sorts-operation">
                        <a class="btn btn-default prev_step" type="button"
                           href="javascript:;">上一步
                        </a>
                        <a class="btn btn-primary step_next" type="button"
                           data-role="step_second" id="create_gds" href="javascript:;">下一步
                        </a>
                    </div>
                </div>
            </div>

            <div class="app-content step_wp step_03 none">
                <div class="detail_info form-container">
                    <ul class="dinfo_tabs nav nav-tabs" role="tablist">

                    </ul>
                    <!--/dinfo_tabs-->
                    <div class="tab-content dinfo_wp">

                    </div>
                    <!--/dinfo_wp-->
                    <div class="dinfo_box demo_box">
                        <div class="tab-content">
                            <div class="demo_box" style="display:none;">
                                <div role="tabpanel" class="tab-pane" id="">
                                    <form class="form-horizontal" method="post">
                                        <div>
                                            <div class="form-group">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>商品编号：</label>

                                                <div class="controls col-xs-7">
                                                	<div class="input-group">
                                                		<input type="text" class="form-control p_code no_input"
                                                           value="<#--${importGoods.goodsNo }-->"
                                                           maxlength="32"
                                                           minlength="10" onblur="checkProNo(this);">
                                                           <a href="javascript:;" class="input-group-addon text-primary"
                                                       onclick="generateProNo(this)">生成编号</a>
                                                	</div>
                                                    
                                                    
                                                    <input type="hidden" class="exist_flag" value="0">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>商品标题：</label>

                                                <div class="controls col-xs-7">
                                                    <input type="text" class="form-control name_input required"
                                                           maxlength="50" minlength="3"
                                                           value="<#--${importGoods.goodsName }-->"
                                                           onblur="checkProductForm()">
                                                </div>
                                            </div>
                                            <div class="form-group" style="display: none;color:gray;">
                                                <label class="control-label col-xs-3">商品副标题：</label>

                                                <div class="controls col-sm-9">
                                                    <textarea class="form-control des_input"
                                                              rows="5"><#--${importGoods.goodsSubtitle }--></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3">服务支持：</label>

                                                <div class="col-xs-1"></div>
                                                <div class="controls col-xs-9">
                                                <#list support as supp>
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox"  alue="${supp.id }">${supp.serviceName }
                                                    </label>
                                                </#list>
                                                </div>
                                                <div class="col-xs-3"></div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>销售价格：</label>

                                                <div class="col-xs-1"></div>
                                                <div class="col-xs-7">
                                                    <div class="input-group w200">
                                                        <span class="input-group-addon">￥</span>
                                                        <input type="text"
                                                               class="form-control pro_price sml_input required number"
                                                               value="<#--${importGoods.goodsPrice }-->">
                                                    </div>
                                                </div>
                                                <div class="col-xs-3">

                                                </div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>商品定价：</label>

                                                <div class="controls col-xs-9">
                                                    <table class="table sm-table">
                                                        <thead>
                                                        <tr>
                                                            <th>仓库</th>
                                                            <th>库存</th>
                                                            <th>销售价</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <#if wareHouse?size==0>
                                                        <tr>
                                                            <td colspan="3">
                                                                <span style="color:red;">请先到<a
                                                                        href="queryWareHouseByPageBean.htm">仓库列
                                                                    表</a>，添加仓库 </span>
                                                            </td>
                                                        </tr>
                                                        </#if>
                                                        <#list wareHouse as ware>
                                                        <tr>
                                                            <td>
                                                                <input type="hidden" class="ware_id"
                                                                       name="wareId"
                                                                       value="${ware.wareId }"/>
                                                            ${ware.wareName }
                                                            </td>
                                                            <td style="text-align:left;">
                                                                <input class="form-control w100 ware_stock required digits"
                                                                       type="text" value="0"
                                                                       onblur="checkProductForm()"/>
                                                            </td>
                                                            <td style="text-align:left;">
                                                                <input type="text" name="productPrices"
                                                                       value="0.00"
                                                                       class="form-control w100 save_productPrice ware_price required number"
                                                                       onblur="checkProductForm()">
                                                            </td>
                                                        </tr>
                                                        </#list>
                                                        <!--此Form只为验证使用-->
                                                        <tr>
                                                            <td>
                                                                批量设置
                                                            </td>
                                                            <td>
                                                                <input class="form-control w100 batc_set_stock required number"
                                                                       type="text" value="0"
                                                                       style="display: none;"/>
                                                                <a class="batch_set_stock_ctrl text-primary"
                                                                   href="javascript:;"
                                                                   onclick="displayBatchSetStock(this)">设置库
                                                                    存</a>
                                                                <a class="do_batch_set_stock_ctrl"
                                                                   href="javascript:;"
                                                                   onclick="doBatchSetStock(this)"
                                                                   style="display: none;">确定</a>
                                                                <a class="cancel_batch_set_stock_ctrl"
                                                                   href="javascript:;"
                                                                   onclick="hideBatchSetStock(this)"
                                                                   style="display: none;">取消</a>
                                                            </td>
                                                            <td>
                                                                <input class="form-control w100 batch_set_price required number"
                                                                       type="text" value="0.00"
                                                                       style="display: none;">
                                                                <a class="batch_set_price_ctrl text-primary"
                                                                   href="javascript:;"
                                                                   onclick="displayBatchSetPrice(this)">设置价
                                                                    格</a>
                                                                <a class="do_batch_set_price_ctrl"
                                                                   href="javascript:;"
                                                                   onclick="doBatchSetPrice(this)"
                                                                   style="display: none;">确定</a>
                                                                <a class="cancel_batch_set_price_ctrl"
                                                                   href="javascript:;"
                                                                   onclick="hideBatchSetPrice(this)"
                                                                   style="display: none;">取消</a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3">
                                                                <a class="text-primary copy_ware_to_other_spec_ctrl"
                                                                   href="javascript:;"
                                                                   onclick="copyWareToOtherSpec(this)">将此设
                                                                    置应用到其他货品</a>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="col-xs-3"></div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>成本价格：</label>

                                                <div class="col-xs-1"></div>
                                                <div class="controls col-xs-7">
                                                    <div class="input-group w200">
                                                        <span class="input-group-addon">￥</span>
                                                        <input type="text"
                                                               class="form-control pro_cost_price required number"
                                                               value="<#--${importGoods.goodsCostPrice }-->">
                                                    </div>
                                                </div>
                                                <div class="col-xs-3"></div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>市场价格：</label>

                                                <div class="col-xs-1"></div>
                                                <div class="col-xs-7">
                                                    <div class="input-group w200">
                                                        <span class="input-group-addon">￥</span>
                                                        <input type="text"
                                                               class="form-control pro_mark_price required number"
                                                               value="<#--${importGoods.goodsMarketPrice }-->">
                                                    </div>
                                                </div>
                                                <div class="col-xs-3"></div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>重量：</label>

                                                <div class="col-xs-1"></div>
                                                <div class="controls col-xs-6">
                                                    <div class="input-group w200">
                                                        <input type="text"
                                                               class="form-control pro_weight required digits">
                                                    </div>
                                                </div>
                                                <div class="col-xs-3"></div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3"><b>*</b>库存：</label>

                                                <div class="controls col-xs-7">
                                                    <input type="text" name="proStock"
                                                           class="form-control sm-input pro_stock required number"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>商品图片：</label>

                                                <div class="controls col-xs-3">
                                                    <button type="button" class="btn btn-primary chooseProimg">
                                                        编辑商品图片
                                                    </button>
                                                </div>
                                                <div class="col-xs-3">
                                                    <a href="javascript:;" class="productsImg help_tips" style="  background: #09e;width: 16px;height: 16px;display: inline-block;border-radius: 50%;text-align: center;color: #fff;margin: 20px;">
                                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true" ></span>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>已选择图片：</label>
                                                <div class="controls col-xs-7">
                                                    <ul class="choose_imgs"></ul>
                                                </div>
                                                <div class="col-xs-3"></div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3">商品状态：</label>

                                                <div class="col-xs-1"></div>
                                                <div class="controls col-xs-7">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" class="show_list show_list_temp"
                                                               value=""> 类目页推荐
                                                    </label>
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" class="show_mobile show_mobile_temp"
                                                               value=""> 手机版推荐
                                                    </label>
                                                </div>
                                                <div class="col-sm-3"></div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>是否包邮：</label>

                                                <div class="col-xs-1"></div>
                                                <div class="controls col-xs-7">
                                                    <label class="radio-inline">
                                                        <input type="radio" name="isMailBay"
                                                               class="isMailBay post_free_yes" value="1"> 包邮
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="isMailBay"
                                                               class="isMailBay post_free_not" value="0"
                                                               checked="checked"> 买家承担运费
                                                    </label>
                                                </div>
                                                <div class="col-xs-3"></div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>是否上架：</label>

                                                <div class="col-xs-1"></div>
                                                <div class="controls col-xs-7">
                                                    <label class="radio-inline">
                                                        <input type="radio" name="pro_status"
                                                               class="pro_status up_yes"
                                                               value="1"> 立即上架
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="pro_status"
                                                               class="pro_status up_not"
                                                               value="0" checked="checked"> 下架
                                                    </label>
                                                </div>
                                                <div class="col-xs-3"></div>
                                            </div>
                                            <div class="form-group" style="display: none;">
                                                <label class="control-label col-xs-3"><span
                                                        class="text-danger">*</span>参与会员折扣：</label>

                                                <div class="col-xs-1"></div>
                                                <div class="controls col-xs-7">
                                                    <label class="radio-inline">
                                                        <input type="radio" name="inlineRadioOptions3"
                                                               class="customer_discount customer_discount_yes"
                                                               value="1"> 是
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="inlineRadioOptions3"
                                                               class="customer_discount customer_discount_not"
                                                               value="0"
                                                               checked="checked"> 否
                                                    </label>
                                                </div>
                                                <div class="col-xs-3"></div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="add_good_sep">
                        <div></div>
                    </div>

                    <div class="add-sorts-operation">
                        <a class="btn btn-default prev_step"
                           href="javascript:;">上一步
                        </a>
                        <a class="btn btn-primary info_sub" onclick="up_goods_info()" href="javascript:;">发布商品</a>
                    </div>
                </div>
                <!--/detail_info-->
            </div>
        </div>
    </div>
</div>
<#--</form>-->
<#include "../common/leftfooter.ftl">

<#--没有选中行提示框-->
<div class="modal fade" id="select-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="show_title">请至少选择一行！</label>
                </div>
            </div>
            <div class="modal-footer">
            <#--<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>-->
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addGoodsRelModal" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加关联商品</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-inline">
                    <div class="mb20">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">商品编号</span>
                                <input type="text" class="form-control" placeholder="商品编号" id="goods_no">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">商品名称</span>
                                <input type="text" class="form-control" placeholder="商品名称" id="goods_name">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">商品品牌</span>
                                <select class="form-control brandsChosen" name="goodsBrandId" <#--data-live-search="true"-->
                                        id="goodsBrandId">
                                    <option value="">选择品牌</option>
                                <#list brandList as brand>
                                    <option value="${brand.brandId }"
                                    <#--<#if (searchBean.goodsBrandId==brand.brandId)>
                                        selected="selected"
                                    </#if>-->
                                            >${brand.brandName }</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="padding:10px 0px;">
                            <button type="button" class="btn btn-primary" onclick="queryGoodsByParam(1)">搜索</button>
                        </div>
                        <div class="cfg-content">
                            <table class="table table-striped table-hover" id="aboutGoodsList">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="allunchecked(this,'aboutGoodsIdSelect')"></th>
                                    <th width="75px">商品图片</th>
                                    <th>商品编号</th>
                                    <th width="300px">商品名称</th>
                                    <th>商品分类</th>
                                    <th>商品品牌</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>

                            <div class="footer-operation">
                                <div class="ops-right">
                                    <nav>
                                        <ul class="pagination">

                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveRelGoods()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script>
    $(function(){
    	$.material.init();
        $(".chosen-select").chosen();
        $(".brandsChosen").chosen();
        /* 富文本编辑框 */
        $('.summernotedesc').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN',
            onImageUpload: function(files, editor, $editable) {
                sendFile(files,editor,$editable);
            }
        });


        /* 富文本编辑框 */
        $('.summernotemobile').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN',
            onImageUpload: function(files, editor, $editable) {
                sendFile(files,editor,$editable);
            }
        });
    });

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />

</body>
</html>
