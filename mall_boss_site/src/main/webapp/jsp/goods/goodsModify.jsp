<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .cate_set_list{
            height:350px;
        }
    </style>
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <div class="panel" id="catepanel">
                    <div class="common_info">
                        <div class="common_info_cont">
                            <div>
                                <h2 class="main_title">选择商品类目</h2>

                                <div class="goods_cate_choose">

                                    <div class="cate_choose_area">

                                        <div class="cate_set container-fluid">

                                            <div class="row">

                                                <div class="col-xs-8 cate_set_column">
                                                    <div class="cate_set_item">
                                                        <input type="hidden" id="CSRFToken" name="CSRFToken" value="${token}"/>
                                                        <div class="cate_set_cont">
                                                            <div class="cate_search">
                                                                <input type="hidden" id="parentId1" >
                                                                <input type="text" class="cate_search_box" placeholder="输入名称查找" id="search_name1">
                                                                <a href="javascript:;" onclick="findFirstGoodsCate();"><span class="glyphicon glyphicon-search"></span></a>
                                                            </div>
                                                            <div class="cate_set_list" id="cate_list1" >

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-xs-8 cate_set_column">
                                                    <div class="cate_set_item">

                                                        <div class="cate_set_cont">
                                                            <div class="cate_search">
                                                                <input type="hidden" id="parentId2">
                                                                <input type="text" class="cate_search_box" placeholder="输入名称查找" id="search_name2">
                                                                <a href="javascript:;" onclick="findSecondGoodsCate();"><span class="glyphicon glyphicon-search"></span></a>
                                                            </div>
                                                            <div class="cate_set_list" id="cate_list2">

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-xs-8 cate_set_column">
                                                    <div class="cate_set_item">

                                                        <div class="cate_set_cont">
                                                            <div class="cate_search">

                                                                <input type="text" class="cate_search_box" placeholder="输入名称查找" id="search_name3">
                                                                <a href="javascript:;" onclick="findThirdGoodsCate()"><span class="glyphicon glyphicon-search"></span></a>
                                                            </div>
                                                            <div class="cate_set_list" id="cate_list3">

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>

                                        </div>

                                        <div class="cate_choosed">
                                            <p>当前选择的是：
                                                <strong id="productName1"></strong>
                                                <strong id="productName2"></strong>
                                                <strong id="productName3"></strong>
                                            </p>
                                        </div>
                                    </div>
                                    <input type="hidden" id="cateGrade" value="${goodsMoifiedVo.goodsCate.catGrade}"/>
                                    <input type="hidden" id="cateId" value="${goodsMoifiedVo.goodsCate.catId}"/>
                                    <input type="hidden" id="cateParentId" value="${goodsMoifiedVo.goodsCate.catParentId}"/>
                                </div>
                            </div>
                        </div>


                        <div class="common_info_cont" id="spec_form" style="display:none;">
                            <div>
                                <h2 class="main_title">规格参数</h2>
                                <div class="goods_cate_choose">

                                    <div class="cate_choose_area">

                                        <div class="cate_set container-fluid">
                                            <span class="text-danger">注：修改分类后，会导致原有货品规格失效，故需要重新设置货品规格，请选择规格。</span>
                                            <div class="row form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">规格：</label>
                                                    <div class="col-sm-1"></div>
                                                    <div class="col-sm-21" style="padding:6px;">
                                                        <input type="hidden" id="productSize" value="${fn:length(products)}"/>
                                                        <div class="w600">当前商品下共有${fn:length(products)}个货品，所以选择的规格组合后个数需要大于${fn:length(products)}</div>
                                                        <div class="inline_block type_spec">



                                                        </div>
                                                    </div>
                                                    <div class="text-center mt20">
                                                        <a href="javascript:;" class="btn btn-info" onclick="showModifyProduct()">生成规格组合</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <form class="form-horizontal" id="goodsParamForm" method="post" action="updateGoodsParamSpec.htm" target="hidden_frame">
                            <input type="hidden" name="CSRFToken" class="token_val" value="${token }" id="token_val"/>
                            <input type="hidden" name="catId" id="parentId3" class="ch_goods_cate">
                            <div class="common_info_cont" id="product_form" style="display: none;">
                                <div>
                                    <h2 class="main_title">重置货品规格</h2>
                                    <div class="goods_cate_choose">
                                        <div class="cate_choose_area">
                                            <div class="cate_set container-fluid">
                                                <div class="row form-horizontal">
                                                    <span class="text-danger">注：修改分类后，会导致原有货品规格失效，故需要重新设置货品规格</span>
                                                    <table class="table table-bordered table-hover" id="goods_info_table">
                                                        <thead>
                                                        <tr>
                                                            <th width="200px">货品编号</th>
                                                            <th width="500px">货品名称</th>
                                                            <th width="300px">货品规格</th>
                                                            <th width="200px">操作</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${products}" var="product">
                                                            <tr>
                                                                <td>${product.goodsInfoItemNo}</td>
                                                                <td>${product.goodsInfoName}</td>
                                                                <td id="spec_info${product.goodsInfoId}"  class="spec_info text-danger">
                                                                    <c:forEach items="${product.specVo}" var="specVo">
                                                                        ${specVo.spec.specName}:${specVo.goodsSpecDetail.specDetailName}<br/>
                                                                    </c:forEach>
                                                                </td>
                                                                <td>
                                                                    <a class="show_select_spec_btn" href="javascript:;" onclick="showSelectSpec(this)" goods_info_id="${product.goodsInfoId}">修改规格</a>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>



                            <div class="common_info_cont">
                                <div>
                                    <h2 class="main_title">商品参数</h2>
                                    <c:forEach items="${goodsMoifiedVo.expandParamVoList}" var="expandParamVo">
                                        <input type="hidden" class="expand_param_values" expand_param_id="${expandParamVo.expandParamVo.expandparamId}" value="${expandParamVo.expangparamValue.expandparamValueId}"/>
                                    </c:forEach>
                                    <c:forEach items="${goodsMoifiedVo.paramVoList}" var="paramVo">
                                        <input type="hidden" class="param_values" param_id="${paramVo.param.paramId}" value="${paramVo.paramValue}"/>
                                    </c:forEach>
                                    <div class="goods_cate_choose">

                                        <div class="cate_choose_area">

                                            <div class="cate_set container-fluid">

                                                <div class="row form-horizontal">
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
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" class="new_goods_id" name="goodsId" value="${goodsMoifiedVo.goodsId}">
                        </form>

                        <div class="add_good_sep"><div></div></div>

                        <div class="text-center mt20">
                            <button type="button" class="btn btn-primary btn-lg" onclick="panelNext('#catepanel','#panel1');">进入下一步</button>
                        </div>
                    </div>
                </div>

                <form class="form-horizontal" id="goodsBaseForm" action="updateGoodsNew.htm?CSRFToken=${token}&pageNo=${pageNo}&pageSize=${pageSize}&goodsId=${goodsMoifiedVo.goodsId}&isThird=0" method="post" enctype="multipart/form-data">
                    <div class="panel" id="panel1" style="display: none;">
                        <div class="common_info">
                            <ul class="common_info_tabs">
                                <li class="active"><a href="javascript:void(0);">基本信息</a></li>
                                <li><a href="javascript:void(0);" onclick="panelNext('#panel1','#panel2')">商品介绍</a></li>
                                <li><a href="javascript:void(0);" onclick="panelNext('#panel1','#panel3')">关联商品</a></li>
                            </ul>
                            <div class="common_info_cont ">

                                <div class="add_good_item">
                                    <div class="common_form common_form_lg">
                                        <h4>基本信息</h4>

                                        <div class="form-group">
                                            <label class="control-label col-sm-5"><span class="text-danger">*</span>商品编号：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control required numandletter" name="goodsNo" value="${goodsMoifiedVo.goodsNo}" minlength="10" maxlength="32">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-5"><span class="text-danger">*</span>商品名称：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control required" name="goodsName" maxlength="120" minlength="3" value="${goodsMoifiedVo.goodsName}" >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-5">商品副标题：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-12">
                                                <textarea class="form-control" rows="5" name="goodsSubtitle">${goodsMoifiedVo.goodsSubtitle}</textarea>
                                            </div>
                                        </div>
                                        <%--<div class="form-group">
                                            <label class="control-label col-sm-5">商品图片：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-8">
                                                <p class="pt5"><input type="file"></p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-5">商品关键词：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" value="${goodsMoifiedVo.goodsKeywords}">
                                            </div>
                                        </div>--%>
                                        <div class="form-group">
                                            <label class="control-label col-sm-5"><span class="text-danger">*</span>商品品牌：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-8">
                                                <select class="form-control" data-live-search="true" name="brandId" id="goods_brand">
                                                    <c:forEach items="${brandList }" var="brand">
                                                        <option value="${brand.brandId }" <c:if test="${goodsMoifiedVo.goodsBrand.brandId==brand.brandId}">selected="selected" </c:if>>${brand.brandName }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-5"><span class="text-danger">*</span>销售价格：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-8">
                                                <div class="input-group">
                                                    <input type="text" class="form-control required number" name="goodsPrice" value="${goodsMoifiedVo.goodsPrice}">
                                                </div>
                                            </div>
                                        </div>
                                        <!--2015.10.22 wuyanbo 添加会员价格-->
                                        <div class="form-group">
                                            <label class="control-label col-sm-5"><span class="text-danger">*</span>会员价格：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-8">
                                                <div class="input-group">
                                                    <input type="text" class="form-control required number" name="goodsVipPrice" value="${goodsMoifiedVo.goodsVipPrice}">
                                                </div>
                                            </div>
                                        </div>
                                        <%--<div class="form-group">--%>
                                            <%--<label class="control-label col-sm-5"><span class="text-danger">*</span>计价单位：</label>--%>
                                            <%--<div class="col-sm-1"></div>--%>
                                            <%--<div class="col-sm-3">--%>
                                                <%--<input type="text" class="form-control required" name="goodsDeno" value="${goodsMoifiedVo.goodsDeno}">--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label class="control-label col-sm-5">商品标签：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-17">
                                                <c:forEach items="${tagList }" var="tag">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" name="update_goods_tags" class="goods_tag" value="${tag.tagId }"
                                                        <c:forEach items="${goodsMoifiedVo.tags }" var="goodsTag">
                                                               <c:if test="${tag.tagId==goodsTag.goodsTag.tagId}">checked</c:if>
                                                        </c:forEach>
                                                                ><img src="${tag.tagImg}" height="30px;" style="vertical-align:middle" title=" ${tag.tagName }"/>
                                                    </label>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="add_good_sep"><div></div></div>

                                    <div class="text-center mt20">
                                        <button type="button" class="btn btn-lg btn-default" onclick="panelNext('#panel1','#catepanel');">上一步</button>
                                        <button type="button" class="btn btn-primary btn-lg" onclick="panelNext('#panel1','#panel2');">进入下一步</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- end -->

                    <div class="panel" id="panel2" style="display:none;">
                        <div class="common_info">
                            <ul class="common_info_tabs">
                                <li><a href="javascript:void(0);" onclick="showOnePanel('#panel1');">基本信息</a></li>
                                <li class="active"><a href="javascript:void(0);">商品介绍</a></li>
                                <li><a href="javascript:void(0);" onclick="showOnePanel('#panel3');">关联商品</a></li>
                            </ul>
                            <div class="common_info_cont">
                                <div class="add_good_item">

                                    <div class="common_form common_form_lg">
                                        <%--<div class="form-group">
                                            <label class="control-label col-sm-3">商品简介：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-18">
                                                <textarea class="form-control" rows="5">${goodsMoifiedVo.goodsBrief}</textarea>
                                            </div>
                                        </div>--%>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">PC版详情：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-18">
                                                <div class="summernotedesc">${goodsMoifiedVo.goodsDetailDesc}</div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">移动版详情：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-18">
                                                <div class="summernotemobile">${goodsMoifiedVo.mobileDesc}</div>
                                            </div>
                                        </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-5">seo标题：</label>
                                                <div class="col-sm-1"></div>
                                                <div class="col-sm-12">
                                                    <textarea class="form-control" rows="5" name="goodsSeoTitle">${goodsMoifiedVo.goodsSeoTitle}</textarea>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-5">seo关键字：</label>
                                                <div class="col-sm-1"></div>
                                                <div class="col-sm-12">
                                                    <textarea class="form-control" rows="5" name="goodsSeoKeyword">${goodsMoifiedVo.goodsSeoKeyword}</textarea>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-5">seo描述：</label>
                                                <div class="col-sm-1"></div>
                                                <div class="col-sm-12">
                                                    <textarea class="form-control" rows="5" name="goodsSeoDesc">${goodsMoifiedVo.goodsSeoDesc}</textarea>
                                                </div>
                                            </div>

                                    </div>

                                </div>
                                <input type="hidden" class="new_goods_id" name="goodsId" value="${goodsMoifiedVo.goodsId}">
                                <input type="hidden" class="goods_desc" name="goodsDetailDesc" value="">
                                <input type="hidden" class="goods_mobile_desc" name="mobileDesc" value="">
                                <div class="text-center mt20">
                                    <button type="button" class="btn btn-lg btn-default" onclick="panelNext('#panel2','#panel1');">上一步</button>
                                    <button type="button" class="btn btn-primary btn-lg" onclick="panelNext('#panel2','#panel3');">进入下一步</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end -->

                    <!-- end -->

                    <div class="panel" id="panel3" style="display:none;">
                        <div class="common_info">
                            <ul class="common_info_tabs">
                                <li><a href="javascript:void(0);" onclick="showOnePanel('#panel1');">基本信息</a></li>
                                <li><a href="javascript:void(0);" onclick="showOnePanel('#panel2');">商品介绍</a></li>
                                <li class="active"><a href="javascript:void(0);">关联商品</a></li>
                            </ul>
                            <div class="common_info_cont">

                                <div class="mb20">
                                    <button type="button" class="btn btn-info" onclick="showAddGoodsRelModal()">添加关联商品</button>
                                    <%--<button type="button" class="btn btn-info">批量取消关联</button>--%>
                                </div>

                                <table class="table table-striped table-hover" id="oldAboutGoodsList">
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
                                    <c:forEach items="${goodsMoifiedVo.relaGoodsVo}" var="relGoodsVo">
                                        <c:forEach items="${relGoodsVo.releatedGoods}" var="relGoods">
                                            <tr id="rel_goods_tr${relGoods.goodsId}">
                                                <td><img src="${relGoods.goodsImg}" width="50px"/></td>
                                                <td>${relGoods.goodsName}<input type="hidden" name="aboutGoodsId" value="${relGoods.goodsId}"/> </td>
                                                <td>${relGoods.goodsNo}</td>
                                                <td>${relGoods.goodsCate.catName}&nbsp;</td>
                                                <td>${relGoods.goodsBrand.brandName}&nbsp;</td>
                                                <td><a href="javascript:;" onclick="deleteReGoods('${relGoods.goodsId}')">删除</a></td>
                                            </tr>
                                        </c:forEach>
                                    </c:forEach>
                                    </tbody>
                                </table>

                                <div class="add_good_sep">
                                    <div></div>
                                </div>

                                <div class="text-center mt20">
                                    <button type="button" class="btn btn-lg btn-default" onclick="panelNext('#panel3','#panel2');">上一步</button>
                                    <button type="button" class="btn btn-primary btn-lg" onclick="updateGoods();">发布商品</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="otherForm" style="display: none;"></div>
                </form>
                <!-- end -->
            </div>
        </div>
    </div>
</div>


<!-- 保存商品的表单 -->
<form class="save_goods_info" action="newUploadGood.htm?CSRFToken=${token }" method="post" target="hidden_frame" enctype="multipart/form-data">

</form>


<!-- 模拟无刷新上传图片用到的 -->
<iframe name="hidden_frame" style="display:none"></iframe>


<input type="hidden" class="flag_saved" value="0">

<!-- 选择规格 -->
<div class="modal fade" id="selectSpecModal"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <input type="hidden" id="goodsInfoId" />
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">选择规格</h4>
            </div>
            <div class="modal-body">
                <div class="modal_form">
                    <table id="select_spec_talbe" class="table table-bordered table-hover">
                        <thead></thead>
                        <tbody></tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="selectSpecGroup()">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addGoodsRelModal"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加关联商品</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-inline" >
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
                                <input type="text" class="form-control" placeholder="商品名称" id="goods_name" maxlength="120" minlength="3">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">商品品牌</span>
                                <select   class="form-control w150 " name="goodsBrandId" data-live-search="true" id="goodsBrandId">
                                    <option value="">选择品牌</option>
                                    <c:forEach items="${brandList }" var="brand">
                                        <option value="${brand.brandId }"
                                                <c:if test="${searchBean.goodsBrandId==brand.brandId }">
                                                    selected="selected"
                                                </c:if>
                                                >${brand.brandName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group"  style="padding:10px 0px;">
                            <button type="button" class="btn btn-info" onclick="queryGoodsByParam(1)">搜索</button>
                        </div>
                        <table class="table table-striped table-hover" id="aboutGoodsList">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="allunchecked(this,'aboutGoodsIdSelect')"></th>
                                <th width="75px">商品图片</th>
                                <th width="300px">商品名称</th>
                                <th>商品编号</th>
                                <th>商品分类</th>
                                <th>商品品牌</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                        <div class="table_foot">
                            <div class="table_pagenav pull-right">
                                <div class="meneame" id="pageFoot">

                                </div>
                            </div>

                            <div class="clr"></div>
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

<%--删除关联商品--%>
<div class="modal fade" id="deleteReGoods"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <input type="hidden" name="relatedId" value="">
            <div class="form-group" style="text-align:center;">
                <label class="control-label" style="padding-top:20px;">确定删除关联商商品吗？</label>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="deleteRelatedProduct()" >确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-suggest.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/goods/goods_update.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
<script>
    $(function(){
        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        //这是普通的带搜索框下拉菜单，只可用于纯英文或纯中文选项
    });

    /*删除关联商品弹出框*/
    function deleteReGoods(id){
        $("input[name='relatedId']").val(id);
        $("#deleteReGoods").modal("show");
    }
    /*删除关联商品*/
    function deleteRelatedProduct(){
        //关联的商品ID
        var relatedId =  $("input[name='relatedId']").val();
        //商品ID
        var new_goods_id = $(".new_goods_id").val();
        $.ajax({
            url:"deleteRelatedProduct.htm?relatedProductId="+relatedId+"&relatedId="+new_goods_id,
            type:"post",
            success:function(data){
                if(data==1){
                    //关闭提示窗
                    $("#deleteReGoods").modal("hide");
                    $("#rel_goods_tr"+relatedId).remove();
                    //重新加载页面
//                    location.reload();
                }
            }
        });
    }
</script>
</body>
</html>
