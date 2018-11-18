<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商城第三方后台-商品管理</title>
<#assign basePath=request.contextPath />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/third.css"/>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/third.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/thirdgoods.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/newupload.js"></script>
    <script type="text/javascript" src="${basePath}/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="${basePath}/js/artDialog4.1.7/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.slimscroll.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/summernote.css">
    <script type="text/javascript" src="${basePath}/js/summernote.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/summernote-zh-CN.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/common_alert.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/common_date.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/goodsAdd.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/third.css"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
    <script type="text/javascript" src="${basePath}/js/goods/modithirdgoods.js"></script>
    <link rel="stylesheet" href="${basePath}/css/ztree/zTreeStyle.css">
    <script type="text/javascript" src="${basePath}/js/ztree/jquery.ztree.core-3.5.js"></script>
    <link rel="stylesheet" href="${basePath}/css/bootstrap-chosen.css"/>
    <script src="http://cdn.bootcss.com/chosen/1.4.2/chosen.jquery.min.js"></script>
</head>

<body>
<#-- 引入头部 -->
<#include "../index/indextop.ftl">

<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>
    <div class="app show_text" style="display: none;"">
        <form class="sa_third_goods form-horizontal" action="modithirdgoods.htm" enctype="multipart/form-data" method="post">
            <div class="app-container">
                <input type="hidden" name="goodsAddedSta" value="${map.goodsAddedSta}"/>
                <input type="hidden" name="thirdCateId" class="ch_third_catid">
                <div class="app-content step_wp step_01">
                    <ul class="nav nav-tabs edit-tabs">
                        <li class="active">
                            <a href="javascript:;">1.选择商品分类</a>
                        </li>
                        <li>
                            <a href="javascript:;">2.编辑基本信息</a>
                        </li>
                        <li>
                            <a href="javascript:;">3.编辑商品详情</a>
                        </li>
                        <li>
                            <a href="javascript:;">4.关联商品</a>
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
                                <b><span class="firstCate">商品原有分类:<#if map.thirdCate??>${map.thirdCate.catName!''}</#if></span></b>
                                <b><span class="secondCate"></span></b>
                                <b><span class="thirdCate"></span></b>
                                <b><span class="fouthCate"></span></b>
                            </p>

                            <div class="add-sorts-operation">
                                <a class="btn btn-primary step_next" data-role="step_first">下一步</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/step_01-->

                <div class="app-content step_wp step_02 none">
                    <ul class="nav nav-tabs edit-tabs">
                        <li>
                            <a href="javascript:;">1.选择商品分类</a>
                        </li>
                        <li class="active">
                            <a href="javascript:;">2.编辑基本信息</a>
                        </li>
                        <li>
                            <a href="javascript:;">3.编辑商品详情</a>
                        </li>
                        <li>
                            <a href="javascript:;">4.关联商品</a>
                        </li>
                    </ul>

                    <div class="edit-goods-cont">
                        <div class="edit-item">
                            <div class="edit-tit" style="margin-bottom:20px;">
                                <h4>基本信息</h4>
                            </div>
                            <div class="edit-info-cont">
                                <div class="group-inner">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"><b>*</b>商品编号：</label>
                                        <input type="hidden" name="goodsId" class="now_goodsId"
                                               value="${map.goodsModifiedVo.goodsId!''}"/>
                                        <input type="hidden" class="nowGoodsNo"
                                               value="${map.goodsModifiedVo.goodsNo!''}">

                                        <div class="controls col-xs-7">
                                            <input class="up_text goods_no form-control"
                                                   value="${map.goodsModifiedVo.goodsNo!''}"
                                                   onBlur="checkExists('checkGoodsNo','goodsNo',this,'goods_no_tips',1,'nowGoodsNo')"
                                                   type="text" name="goodsNo" maxlength="32"  readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"><b>*</b>商品名称：</label>

                                        <div class="controls col-xs-7">
                                            <input type="text" name="goodsName"
                                                   value="${map.goodsModifiedVo.goodsName!''}"
                                                   class="up_text goods_name form-control" maxlength="50" minlength="3"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">商品副标题：</label>

                                        <div class="controls col-xs-7">
                                            <input class="up_text goods_sub_title form-control"
                                                   value="${map.goodsModifiedVo.goodsSubtitle!''}"
                                                   name="goodsSubtitle" type="text"/>
                                        </div>
                                    </div>
                                    <div class="form-group" style="display:none">
                                        <label class="control-label col-xs-3"><b>*</b>商品图片：</label>

                                        <div class="controls col-xs-7">
                                            <input type="button" class="choose_image_button" value="选择图片">(200*200) jpg
                                            <input type="hidden" name="goodsImg" class="goodsImage"
                                                   value="${map.goodsModifiedVo.goodsImg!''}"/>
                                            <img width="30px" height="30px" src="${map.goodsModifiedVo.goodsImg!'' }"
                                                 id="goodsImage"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"><b>*</b>分类：</label>

                                        <div class="controls col-xs-7">
                                            <input type="text" class="up_text ch_cat_name form-control"
                                                   value="${map.Cate.catName!''}"
                                                   readonly=readonly/>
                                            <input type="hidden" value="${map.Cate.catId!''}" name="catId"
                                                   class="goodsCatId"/>
                                            <a class="st_choose" onclick="javascript:showMenu();" href="javascript:;"
                                               style="display:none;">选择</a>
                                            <label class="goodsCate_tips"></label>

                                            <div id="menuContent" class="menuContent"
                                                 style=" border:1px solid #ccc;width:150px;height:300px;overflow:scroll;scroll-y;display:none;position: absolute; background-color:#fcfcfc;">
                                                <ul id="treeDemo" class="ztree">
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group" style="display:none;">
                                        <label class="control-label col-xs-3">商品关键词：</label>

                                        <div class="controls col-xs-7">
                                            <input class="up_text goods_keywords form-control"
                                                   value="${map.goodsModifiedVo.goodsKeywords!''}" name="goodsKeywords"
                                                   type="text"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"><b>*</b>商品品牌：</label>

                                        <div class="controls col-xs-7">
                                            <input class="old_brand_id " type="hidden"
                                                   value="${map.goodsModifiedVo.goodsBrand.brandId!''}"/>
                                            <select class="form-control grand_brand_sel" data-live-search="true"
                                                    name="brandId"
                                                    id="goods_brand">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"><b>*</b>销售价格：</label>

                                        <div class="controls col-xs-7">
                                            <input class="up_text goods_price form-control"
                                                   value="${map.goodsModifiedVo.goodsPrice!''}"
                                                   name="goodsPrice" type="text"/>
                                        </div>
                                    </div>
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3"><b>*</b>计价单位：</label>-->

                                        <#--<div class="controls col-xs-7">-->
                                            <#--<input class="up_text goods_deno form-control"-->
                                                   <#--value="${map.goodsModifiedVo.goodsDeno!''}"-->
                                                   <#--name="goodsDeno" type="text" value=""/>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">商品标签：</label>

                                        <div class="controls goods_tag_third col-xs-7 checkbox checkbox-primary">

                                        </div>
                                    <#list map.goodsModifiedVo.tags as tag>
                                        <input type="hidden" class="old_tag" value="${tag.goodsTag.tagId!''}"/>
                                    </#list>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"><b>*</b>立即上架：</label>

                                        <div class="controls col-xs-7 radio radio-primary">
                                            <label class="choose-label"><input type="radio" name="goodsAdded" value="1"
                                                                               <#if map.goodsModifiedVo.goodsAdded=="1">checked=checked</#if>>是</label>
                                            <label class="choose-label"><input type="radio" name="goodsAdded" value="0"
                                                                               <#if map.goodsModifiedVo.goodsAdded=="0">checked=checked</#if>>否</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="edit-item">
                            <div class="edit-tit">
                                <h4>商品参数</h4>
                            </div>
                            <div class="edit-info-cont">
                                <div class="group-inner">
                                    <h5 class="inner-tit">商品类型扩展参数</h5>
                                <#list map.goodsModifiedVo.expandParamVoList as param>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"><b>*</b>${param.expandParamVo.expandparamName!''}：</label>
                                        <input type='hidden' name='expandParamId'
                                               value='${param.expandParamVo.expandparamId!''}'>

                                        <div class="controls col-xs-7">
                                            <select class="form-control" name='expandparamValue'>
                                                <#list param.expandParamVo.valueList as value>
                                                    <option
                                                        <#if value.expandparamValueId == param.expangparamValue.expandparamValueId>selected=selected</#if>
                                                        value='${value.expandparamValueId!''}'>${value.expandparamValueName!''}</option>
                                                </#list>
                                            </select>
                                        </div>
                                    </div>
                                </#list>
                                    <h5 class="inner-tit">商品类型详细参数</h5>
                                <#list map.goodsModifiedVo.paramVoList as params>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">${params.param.paramName!''}：</label>

                                        <div class="controls col-xs-7">
                                            <input type='hidden' name='paramId' value='${params.param.paramId!''}'/>
                                            <input type="text" class="form-control" value='${params.paramValue!''}'
                                                   name='paramValue'/>
                                        </div>
                                    </div>
                                </#list>
                                    <h5 class="inner-tit">商品类型详细参数</h5>
                                <#list map.goodsModifiedVo.openSpecList as openSpec>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">${openSpec.spec.specName!''}：</label>

                                        <div class="controls col-xs-7" style="margin-top:5px;">
                                            <#list openSpec.specValList as specVal>
                                                <#if specVal.imgUrl??><img width='20px' height='20px'
                                                                           src="${specVal.imgUrl!''}"/></#if>${specVal.specDetail.specDetailName!''}
                                            </#list>
                                        </div>
                                    </div>
                                </#list>
                                </div>
                            </div>
                            <div class="edit-goods-operation">
                                <a class="btn btn-primary step_prev" href="javascript:;">上一步</a>
                                <a class="btn btn-primary step_next" data-role="step_second" href="javascript:;">下一步</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/step_02-->

                <div class="app-content step_wp step_03 none">
                    <ul class="nav nav-tabs edit-tabs">
                        <li>
                            <a href="javascript:;">1.选择商品分类</a>
                        </li>
                        <li>
                            <a href="javascript:;">2.编辑基本信息</a>
                        </li>
                        <li class="active">
                            <a href="javascript:;">3.编辑商品详情</a>
                        </li>
                        <li>
                            <a href="javascript:;">4.关联商品</a>
                        </li>
                    </ul>

                    <div class="edit-goods-cont">
                        <div class="edit-item">
                            <div class="form-item">
                                <label class="control-label">PC版详情：</label>

                                <div class="controls" style="margin:20px 0;">
                                    <div class="summernotedesc">${map.goodsModifiedVo.goodsDetailDesc!''}</div>
                                </div>
                            </div>
                            <div class="form-item">
                                <label class="control-label">移动版详情：</label>

                                <div class="controls" style="margin:20px 0;">
                                    <div class="summernotemobile">${map.goodsModifiedVo.mobileDesc!''}</div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" class="goods_desc" name="goodsDetailDesc" value="">
                        <input type="hidden" class="goods_mobile_desc" name="mobileDesc" value="">
                        <div class="edit-goods-operation">
                            <a class="btn btn-primary step_prev" href="javascript:;">上一步</a>
                            <a class="btn btn-primary step_next" data-role="step_four" href="javascript:;">下一步</a>
                        </div>
                    </div>
                </div>
                <!--/step_03-->

                <div class="app-content step_wp step_04 none">
                    <ul class="nav nav-tabs edit-tabs">
                        <li>
                            <a href="javascript:;">1.选择商品分类</a>
                        </li>
                        <li>
                            <a href="javascript:;">2.编辑基本信息</a>
                        </li>
                        <li>
                            <a href="javascript:;">3.编辑商品详情</a>
                        </li>
                        <li class="active">
                            <a href="javascript:;">4.关联商品</a>
                        </li>
                    </ul>

                    <div class="mb20">
                        <button type="button" class="btn btn-info" onclick="showAddGoodsRelModal()">添加关联商品</button>
                    </div>

                    <div class="edit-goods-cont">
                        <table class="table table-hover" id="oldAboutGoodsList">
                            <thead>
                            <tr>
                                <th>商品图片</th>
                                <th>商品编号</th>
                                <th>商品名称</th>
                                <th>商品分类</th>
                                <th>商品品牌</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if map.goodsModifiedVo.relaGoodsVo??>
                                <#list map.goodsModifiedVo.relaGoodsVo as relGoodsVo>
                                    <#if relGoodsVo.releatedGoods??>
                                        <#list relGoodsVo.releatedGoods as relGoods>
                                        <tr id="rel_goods_tr${relGoods.goodsId}">
                                            <td><img src="${relGoods.goodsImg}" width="50px"/></td>
                                            <td>${relGoods.goodsName}<input type="hidden" name="aboutGoodsId"
                                                                            value="${relGoods.goodsId}"/></td>
                                            <td>${relGoods.goodsNo}</td>
                                            <td>${relGoods.goodsCate.catName}&nbsp;</td>
                                            <td>${relGoods.goodsBrand.brandName}&nbsp;</td>
                                            <td><a href="javascript:;"
                                                   onclick="$('#rel_goods_tr${relGoods.goodsId}').remove();">删除</a></td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </#list>
                            </#if>
                            </tbody>
                        </table>

                        <div class="add_good_sep">
                            <div></div>
                        </div>
                        <div class="edit-goods-operation">
                            <a class="btn btn-primary step_prev" href="javascript:;">上一步</a>
                        <#if map.isThirdAuditUsed??>
                            <#if map.isThirdAuditUsed == '1'>
                                <#if map.goodsModifiedVo.auditStatus == '0' || map.goodsModifiedVo.auditStatus == '3' || map.goodsModifiedVo.auditStatus == '2'>
                                    <a class="btn btn-primary sa_goods" href="javascript:;">去审核</a>
                                </#if>
                            <#else>
                                <a class="btn btn-primary sa_goods" href="javascript:;">保存</a>
                            </#if>
                        <#else>
                            <a class="btn btn-primary sa_goods" href="javascript:;">保存</a>
                        </#if>
                            <input type="hidden" name="isThirdAuditUsed" value="${map.isThirdAuditUsed}"/>
                            <input type="hidden" name="auditStatus" value="${map.goodsModifiedVo.auditStatus}"/>
                        </div>
                    </div>
                </div>
                <!--/step_04-->
            </div>
        </form>
    </div>
</div>
<!--/container-->

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
                                <select class="form-control brandsChosen"
                                        name="goodsBrandId" <#--data-live-search="true"-->
                                        id="goodsBrandId">
                                    <option value="">选择品牌</option>
                                <#list map.brandList as brand>
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
                            <table class="table table-hover" id="aboutGoodsList">
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
<#include "../common/leftfooter.ftl">
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script>
    $(function () {
    	$.material.init();
    
        /* 富文本编辑框 */
        $('.summernotedesc').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN',
            onImageUpload: function (files, editor, $editable) {
                sendFile(files, editor, $editable);
            }
        });


        /* 富文本编辑框 */
        $('.summernotemobile').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN',
            onImageUpload: function (files, editor, $editable) {
                sendFile(files, editor, $editable);
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