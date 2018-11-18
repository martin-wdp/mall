<!DOCTYPE html>
<html>
<head>
    <title>商城第三方后台-货品列表</title>
<#assign basePath=request.contextPath />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/third.css"/>
    <script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/third.js"></script>
    <script type="text/javascript" src="${basePath}/js/common.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/thirdProductManager.js"></script>
    <script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
    <script src="${basePath}/js/ripples.min.js"></script>
	<script src="${basePath}/js/material.min.js"></script>
    <script type="text/javascript" src="${basePath}/plugin/uploadify/jquery.uploadify.js"></script>
    <script type="text/javascript" src="${basePath}/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="${basePath}/js/artDialog4.1.7/plugins/iframeTools.js"></script>
    <link rel="stylesheet" href="${basePath}/plugin/uploadify/uploadify.css"/>
    <script src="${basePath}/js/app.js"></script>
    <style>
        .none {
            display: none;
        }
    </style>
</head>

<body>
<!-- 验证存在的标记变量  start-->
<input type="hidden" class="checkExistsFlag" value="1"/>
<!-- 验证规格参数是否可用的标记变量  start-->
<input type="hidden" class="checkProdcutExists" value="1"/>
<!-- end -->
<!-- end -->
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
                <li><a href="thirdgoodsmanager.htm?n=3&l=27">商品管理</a></li>
                <li class="active">货品管理</li>
            </ol>
            <div class="group_wp">
                <form class="search_product_form" action="thirdProductManager.html" method="post">
                    <input type="hidden" name="goodsId" value="${map.goodsId}">
                    <input type="hidden" name="flag" value="1">

                    <div class="search-block">
                        <div class="filter-groups">
                            <div class="control-group">
                                <label class="control-label">货品名称：</label>

                                <div class="controls">
                                    <input class="form-control" type="text"
                                           value="<#if map.selectBean?? && map.selectBean.goodsName??>${map.selectBean.goodsName}</#if>"
                                           name="goodsName"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">货品编码：</label>

                                <div class="controls">
                                    <input class="form-control" type="text"
                                           value="<#if map.selectBean?? && map.selectBean.goodsNo??>${map.selectBean.goodsNo}</#if>"
                                           name="goodsNo"/>
                                </div>
                            </div>
                        </div>
                        <div class="search-operation">
                            <button class="btn btn-primary btn-sm query_btn" type="submit">查询<i
                                    class="glyphicon glyphicon-search"></i></button>
                            <button class="btn btn-default btn-sm rst_btn" type="button">重置<i
                                    class="glyphicon glyphicon-refresh"></i></button>
                        </div>
                    </div>
                </form>


                <div class="mt20 clearfix">
                <#if map.flag??>
                    <#if map.flag=='1'>
                        <button class="btn btn-primary btn-sm add-goods">添加货品</button>
                    <#--<#if (map.pb.list?size) == 0>
                        <a class="btn btn-primary btn-sm" href="${basePath}/thirdcreateproduct.html?goodsId=${map.goodsId}" style="margin-left:10px;width: 94px;height: 34px;line-height: 34px;background: url(${basePath}/images/add_goods.gif) no-repeat;color: #fff;font-size: 14px;font-weight: 700;text-indent: 25px;">生成货品</a>
                    </#if>-->
                    </#if>
                <#else>
                    <button class="btn btn-primary btn-sm add-goods">添加货品</button>
                </#if>
                </div>

                <div class="cfg-content">
                    <div class="ops-bar"></div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th width="5%"><input type="checkbox" onclick="selectAll(this,'productId')"/></th>
                            <th>序号</th>
                            <th>图片</th>
                            <th width="12%">货品名称</th>
                            <th width="10%">规格值</th>
                            <th>货品编号</th>
                            <th>库存</th>
                            <th>销售价</th>
                            <th>成本价</th>
                            <th>市场价</th>
                            <th width="10%">状态</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                    <#if (map.pb.list?size) &gt; 0 >
                        <form class="list_form" method="post">
                            <input type="hidden" name="goodsId" value="${map.goodsId}">
                            <tbody>
                                <#list map.pb.list as product>
                                    <#if product??>
                                    <tr>
                                        <#if map.isThirdAuditUsed == '1'>
                                            <#if product.auditStatus == '1'>
                                                <td><input type="checkbox" onclick="return false;"/></td>
                                            <#else>
                                                <td><input type="checkbox" class="ch_product" name="productId"
                                                           value="${product.goodsInfoId}"/></td>
                                            </#if>
                                        <#else>
                                            <td><input type="checkbox" class="ch_product" name="productId"
                                                       value="${product.goodsInfoId}"/></td>
                                        </#if>
                                        <td>${product_index+1}</td>
                                        <td><a href="javascript:;"><img alt="" width="30px" height="30px"
                                                                        src="<#if product.goodsInfoImgId??>${product.goodsInfoImgId}</#if>"/></a>
                                        </td>
                                        <td style="text-align:left;"><a href="javascript:;"
                                                                        style="color:#2177d4; line-height:180%;">
                                            <#if product.goodsInfoName??>
                                                <#if product.goodsInfoName?length   gt   8  >
                                                    <p title="${product.goodsInfoName}">${product.goodsInfoName?substring(0,8)}
                                                        ...</p>
                                                <#else>
                                                ${product.goodsInfoName}
                                                </#if>
                                            </#if>
                                        </a></td>
                                        <td>
                                            <p title="<#list product.specVo  as spec>${spec.spec.specName }:${spec.goodsSpecDetail.specDetailName } &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</#list>">
                                                <#list product.specVo  as spec>
                                                    <#if spec_index < 3 >
                                                    ${spec.spec.specName }:${spec.goodsSpecDetail.specDetailName }<br/>
                                                    </#if>
                                                </#list>
                                            </p>
                                        </td>
                                        <td>
                                            <#if product.goodsInfoItemNo?length   gt   10   >
                                                <p title="${product.goodsInfoItemNo}">${product.goodsInfoItemNo?substring(0,10)}
                                                    ...</p>

                                            <#else>
                                            ${product.goodsInfoItemNo}
                                            </#if>
                                        </td>
                                        <td>${product.goodsInfoStock!''}</td>
                                        <td><#if product.goodsInfoPreferPrice??>
                                            ¥${product.goodsInfoPreferPrice?string("0.00")}</#if></td>
                                        <td><#if product.goodsInfoCostPrice??>
                                            ¥${product.goodsInfoCostPrice?string("0.00")}</#if></td>
                                        <td><#if product.goodsInfoMarketPrice??>
                                            ¥${product.goodsInfoMarketPrice?string("0.00")}</#if></td>
                                        <td>
                                            <#if product.goodsInfoAdded??>
                                                <#if product.goodsInfoAdded=="1">销售中</#if>
                                                <#if product.goodsInfoAdded=="0">下架</#if>
                                            </#if>
                                        </td>
                                        <td>
                                            <div class="btn-group">
                                                <#if map.isThirdAuditUsed == '1'>
                                                    <#if map.flag??>
                                                        <#if map.flag == '1'>
                                                            <#if product.auditStatus == '1'>
                                                                <a class="btn btn-primary btn-sm" href="javascript:;"><i
                                                                        class="icon"></i>审核中</a>
                                                            <#elseif product.auditStatus == '2'>
                                                                <a class="btn btn-primary btn-sm" href="javascript:;"
                                                                   onclick="refuseReason('${product.refuseReason}');"><i
                                                                        class="icon"></i>拒绝原因</a>
                                                                <a class="btn btn-primary btn-sm dropdown-toggle" href="javascript:;"
                                                                   onclick="menu_btn(this)"><span class="caret"></span></a>
                                                                <ul class="dropdown-menu">
                                                                    <li><a class="modi-goods"
                                                                           data-role="${product.goodsInfoId}"
                                                                           href="javascript:;">编辑货品</a>
                                                                    </li>
                                                                    <li><a href="javascript:;"
                                                                           onclick="modiImg(${product.goodsInfoId});">编辑图片1</a>
                                                                    </li>
                                                                    <li><a href="javascript:;"
                                                                           onclick="del(${product.goodsInfoId});">删除</a>
                                                                    </li>
                                                                </ul>
                                                            <#else>
                                                                <a class="btn modi-goods btn-primary btn-sm"
                                                                   data-role="${product.goodsInfoId}"
                                                                   href="javascript:;"><i
                                                                        class="icon"></i>编辑货品</a>
                                                                <button class="btn btn-primary btn-sm dropdown-toggle"
                                                                        data-toggle="dropdown" aria-expanded="false"
                                                                        onclick="menu_btn(this)"><span
                                                                        class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu">
                                                                    <li><a href="javascript:;"
                                                                           onclick="modiImg(${product.goodsInfoId});">编辑图片</a>
                                                                    </li>
                                                                    <li><a href="javascript:;"
                                                                           onclick="del(${product.goodsInfoId});">删除</a>
                                                                    </li>
                                                                </ul>
                                                            </#if>
                                                        <#else>
                                                            <#if product.auditStatus == '1'>
                                                                <a class="btn" href="javascript:;"><i
                                                                        class="icon"></i>审核中</a>
                                                            <#elseif product.auditStatus == '2'>
                                                                <a class="btn" href="javascript:;"
                                                                   onclick="refuseReason('${product.refuseReason}');"><i
                                                                        class="icon"></i>拒绝原因</a>
                                                            </#if>
                                                        </#if>
                                                    <#else>
                                                        <#if product.auditStatus == '1'>
                                                            <a class="btn btn-primary btn-sm" href="javascript:;"><i
                                                                    class="icon"></i>审核中</a>
                                                        <#elseif product.auditStatus == '2'>
                                                            <a class="btn btn-primary btn-sm" href="javascript:;"
                                                               onclick="refuseReason('${product.refuseReason}');"><i
                                                                    class="icon"></i>拒绝原因</a>
                                                            <a class="btn dropdown-toggle" href="javascript:;"
                                                               onclick="menu_btn(this)"><span class="caret"></span></a>
                                                            <ul class="dropdown-menu">
                                                                <li><a class="modi-goods btn btn-primary"
                                                                       data-role="${product.goodsInfoId}"
                                                                       href="javascript:;">编辑货品</a>
                                                                </li>
                                                                <li><a href="javascript:;"
                                                                       onclick="modiImg(${product.goodsInfoId});">编辑图片</a>
                                                                </li>
                                                                <li><a href="javascript:;"
                                                                       onclick="del(${product.goodsInfoId});">删除</a>
                                                                </li>
                                                            </ul>
                                                        <#else>
                                                            <a class="btn modi-goods btn-primary btn-sm"
                                                               data-role="${product.goodsInfoId}"
                                                               href="javascript:;"><i
                                                                    class="icon"></i>编辑货品</a>
                                                            <button class="btn btn-primary btn-sm dropdown-toggle"
                                                                    data-toggle="dropdown" aria-expanded="false"
                                                                    onclick="menu_btn(this)"><span
                                                                    class="caret"></span>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li><a href="javascript:;"
                                                                       onclick="modiImg(${product.goodsInfoId});">编辑图片</a>
                                                                </li>
                                                                <li><a href="javascript:;"
                                                                       onclick="del(${product.goodsInfoId});">删除</a>
                                                                </li>
                                                            </ul>
                                                        </#if>
                                                    </#if>
                                                <#else>
                                                    <a class="btn modi-goods btn-primary btn-sm"
                                                       data-role="${product.goodsInfoId}" href="javascript:;"><i
                                                            class="icon"></i>编辑货品</a>
                                                    <button type="button" class="btn btn-primary btn-sm dropdown-toggle"
                                                            data-toggle="dropdown" aria-expanded="false"
                                                            onclick="menu_btn(this)">
                                                        <span class="caret"></span>
                                                    </button>
                                                    <ul class="dropdown-menu" role="menu">
                                                        <li><a href="javascript:;"
                                                               onclick="modiImg(${product.goodsInfoId});">编辑图片</a>
                                                        </li>
                                                        <li><a href="javascript:;"
                                                               onclick="del(${product.goodsInfoId});">删除</a>
                                                        </li>
                                                    </ul>
                                                </#if>
                                            </div>
                                        </td>
                                    </tr>
                                    </#if>
                                </#list>
                            </tbody>
                        </form>
                    <#else>
                        <td colspan="12">
                            <center>暂无相关商品信息</center>
                        </td>
                    </#if>
                    </table>
                    <!--/pr_tb-->
                    <div class="footer-operation">
                        <div class="ops-left">
                            <button class="btn btn-primary btn-xs batch_down_product" type="button">批量下架</button>
                        </div>
                    <#if (map.pb.list?size) &gt; 0 >
                        <div class="ops-right">
                            <nav>
                                <ul class="pagination">
                                    <li>
                                        <a href="javascript:;" aria-label="Previous" onclick="changePageNo(this);"
                                           data-role="${map.pb.prePageNo}">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <#if (map.pb.startNo?number>1)>
                                        <li><a href="javascript:;">1</a></li>
                                    </#if>
                                    <#list map.pb.startNo?number .. map.pb.endNo as item>
                                        <li <#if item == map.pb.pageNo>class="active"</#if>><a href="javascript:;"
                                                                                               onclick="changePageNo(this);"
                                                                                               data-role="${item}">${item}</a>
                                        </li>
                                    </#list>
                                    <#if (map.pb.totalPages?number>map.pb.endNo)>
                                        <li><a href="javascript:;" onclick="changePageNo(this);"
                                               data-role="${map.pb.totalPages}">${pageBean.totalPages}</a></li>
                                    </#if>
                                    <li>
                                        <a href="javascript:;" aria-label="Next" onclick="changePageNo(this);"
                                           data-role="${map.pb.nextPageNo}">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </#if>
                    </div>
                </div>
            </div>
            <div class="app-content none">
                <div class="form-container">
                    <form class="sub_product form-horizontal" method="post">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="javascript:;" class="productTitle">添加货品</a>
                            </li>
                        </ul>
                        <div class="form-group">
                            <input type="hidden" name="goodsId" class="pro_goods_id" value="${map.goodsId}"/>
                            <input class="parem_len" type="hidden" value="${map.specs?size}"/>
                            <input class="exec_flag" type="hidden" value="0"/>

                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>货品编号：</label>
                                <input type="hidden" name="goodsInfoId" class="pg_text goodsInfoId">

                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control goodsInfoItemNo"
                                           name="goodsInfoItemNo"/>
                                    <label class="goodsInfoItemNo_tip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>货品名称：</label>

                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control goodsInfoName"
                                           name="goodsInfoName"/>
                                    <label class="goodsInfoName_tip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3">货品副标题：</label>

                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control goodsInfoSubtitle"
                                           name="goodsInfoSubtitle"/>
                                    <label class="goodsInfoSubtitle_tip"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-xs-3">服务支持：</label>

                                <div class="controls col-xs-7">
                                    <div class="checkbox checkbox-primary">
                                    <#if map.allProductSuppList ?? && map.allProductSuppList?size &gt; 0>
                                <#list map.allProductSuppList as allsupp>

                                    <label class="choose-label"><input class="pro_supp suppchecked"  type="checkbox" value="${allsupp.id }"
                                                                       name="support"/>${allsupp.serviceName }
                                    </label>
                                </#list>
                                    </#if>
                                        </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>库存：</label>

                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control goodsInfoStock"
                                           name="goodsInfoStock"/>
                                    <label class="goodsInfoStock_tip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>销售价：</label>

                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control goodsInfoPreferPrice"
                                           name="goodsInfoPreferPrice"/>
                                    <input type="hidden" class="pg_text goodsInfoPreferPrice" name="oldPrice"/>
                                    <label class="goodsInfoPreferPrice_tip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>成本价：</label>

                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control goodsInfoCostPrice"
                                           name="goodsInfoCostPrice"/>
                                    <label class="goodsInfoCostPrice_tip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>市场价：</label>

                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control goodsInfoMarketPrice"
                                           name="goodsInfoMarketPrice"/>
                                    <label class="goodsInfoMarketPrice_tip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>重量：</label>

                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control goodsInfoWeight"
                                           name="goodsInfoWeight"/>
                                    <label class="goodsInfoWeight_tip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>是否上架：</label>

                                <div class="controls col-xs-7 radio radio-primary">
                                    <label class="choose-label"><input class="added_sta" type="radio" value="1"
                                                                       name="goodsInfoAdded"/>是</label>
                                    <label class="choose-label"><input class="un_added_sta" type="radio" value="0"
                                                                       name="goodsInfoAdded"
                                                                       checked="checked"/>否</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>是否类目页推荐：</label>

                                <div class="controls col-xs-7 radio radio-primary">
                                    <label class="choose-label"><input class="show_list"  type="radio" value="1"
                                                                       name="showList"/>是</label>
                                    <label class="choose-label"><input class="unshow_list" type="radio" value="0" name="showList"
                                                                       checked="checked"/>否</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>是否手机端推荐：</label>

                                <div class="controls col-xs-7 radio radio-primary">
                                    <label class="choose-label"><input class="show_mobile" type="radio" value="1"
                                                                       name="showMobile"/>是</label>
                                    <label class="choose-label"><input class="unshow_mobile" type="radio" value="0"
                                                                       name="showMobile"
                                                                       checked="checked"/>否</label>
                                </div>
                            </div>
                            <div class="form-group" style="display:none;">
                                <label class="control-label col-xs-3"><b>*</b>是否参与会员折扣：</label>

                                <div class="controls col-xs-7 radio radio-primary">
                                    <label class="choose-label"><input type="radio" id="isCustomerAdded"
                                                                       value="1"
                                                                       name="isCustomerDiscount"/>是</label>
                                    <label class="choose-label"><input type="radio" value="0" id="isCustomer"
                                                                       name="isCustomerDiscount"
                                                                       checked="checked"/>否</label>
                                </div>
                            </div>
                            <div class="form-group">
                            <#list map.specs as spec>
                                <label class="control-label col-xs-3">${spec.spec.specName}：</label>

                                <div class="controls col-xs-7">
                                    <input type="hidden" name="specId" value="${spec.spec.specId }"/>
                                    <select onChange="selProductParam('spec_param_Tips${spec.spec.specId}');"
                                            name="specDetailId" class="tb_select sel${spec.spec.specId} form-control">
                                        <#list spec.specValList as detail>
                                            <option value="${detail.specDetail.specDetailId }">${detail.specDetail.specDetailName }</option>
                                        </#list>
                                    </select>
                                    <label class="spec_param_Tips${spec.spec.specId}"></label>
                                </div>
                            </#list>
                            </div>
                        </div>
                        <div class="add-sorts-operation">
                            <button class="btn btn-primary sub_product_form" type="button">保存</button>
                            <button class="btn btn-default close_sub_product cls_gp" type="button">关闭</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modi_img mt10 none">
                <form class="modi_third_product_img" enctype="multipart/form-data" action="updateThirdProductImage.htm"
                      method="post">
                    <div class="app-content" id="modi_image">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="javascript:;">编辑图片</a>
                            </li>
                        </ul>
                        <input type="hidden" name="goodsId" class="img_goodsId_hide" value="${map.goodsId}"/>
                        <input class="img_productId_hide" type="hidden" name="productId" value=""/>
                        <input class="default_image" name="defaultImage" type="hidden" value=""/>
                        <input class="goodsImgId" name="goodsImgId" type="hidden" value=""/>
                        <div class="del_image">
                        </div>
                        <button class="btn btn-primary choose_image_button" type="button">选择图片</button>

                        <ul class="edit-images-list">
                        </ul>

                        <div class="edit-operation">
                            <button class="btn btn-primary save_img" type="button">保存</button>
                            <button class="btn btn-default close_img" type="button">取消</button>
                        </div>
                </form>
            </div>
        </div>
    </div>
    <div class="old_param_div"></div>
    <!-- 修改货品图片信息的弹窗 -->
    <div class="mask"></div>

<#--图片确认删除提示框-->
    <div class="modal fade" id="delete-tip" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div class="form-item error">
                        <label class="control-label">确认删除吗？</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button" data-dismiss="modal" id="tip-submit-btn"
                            onclick="delProductImg();">确定
                    </button>
                    <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
<#--货品确认删除提示框-->
    <div class="modal fade" id="delete-tip1" role="dialog" aria-hidden="true">
        <form action="delThirdProduct.htm" method="post" id="mform" name="mform">
            <input type="hidden" id="productId" name="productId" value=""/>
            <input type="hidden" name="goodsId" value="${map.goodsId}"/>

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">操作提示</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-item error">
                            <label style="width:100%;" id="errorT">确认删除吗？</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                        <button class="btn btn-primary" type="button" data-dismiss="modal" id="tip-submit-btn"
                                onclick="delmar();">确定
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

<#--拒绝原因-->
    <div class="modal fade" id="refuse-tip" role="dialog" aria-hidden="true">
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
                    <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
    <script>
        $(function () {
        	$.material.init();
        
            /*初始化图片上传插件*/
            $("#file").uploadify({
                'swf': '${basePath}/plugin/uploadify/uploadify.swf',
                'cancelImg': '${basePath}/plugin/uploadify/uploadify-cancel.png',
                'queueID': 'fileList', // 和存放队列的DIV的id一致
                'fileDataName': 'imageFile', // 和以下input的name属性一致
                'fileObjName': 'filedata',
                'uploader': 'saveThirdProductImage.htm?productId=' + $(".img_productId_hide").val(),
                'auto': true, // 是否自动开始
                'multi': true, // 是否支持多文件上传
                //是否移除掉队列中已经完成上传的文件。false为不移除
                removeCompleted: true,
                'buttonText': '选择图片', // 按钮上的文字
                'fileTypeDesc': '支持格式:jpg/jpeg/png/gif', // 如果配置了以下的'fileExt'属性，那么这个属性
                'fileTypeExts': '*.jpg;*.jpeg;*.png;*.gif;',// 允许的格式
                onUploadStart: function (file) {
                    //当开始上传的时候  拼接参数
                    $("#file").uploadify('settings', 'uploader', '${basePath}/saveThirdProductImage.htm?productId=' + $(".img_productId_hide").val());
                },
                onUploadSuccess: function (file, data, response) {
                    //上传成功后执行回调
                    modiImg($(".img_productId_hide").val());
                },
                onError: function (event, queueID, fileObj) {
                    alert("文件:" + fileObj.name + "上传失败");
                }
            });

            $(".add-goods").click(function () {
                $(".sub_product").attr("action", "tNewUploadProduct.htm");
                $(".exec_flag").val("0");
                $(".productTitle").html("添加货品");
                $(".group_wp").hide();
                $(".app-content").show();
            });
            $(".modi-goods").click(function () {
                $(".productTitle").html("修改货品");
                $(".group_wp").hide();
                $(".app-content").show();
            });
            $(".cls_gp").click(function () {
                $(".group_wp").show();
                $(".app-content").hide();
            });

            $(".close_img").click(function () {
                $(".group_wp").show();
                $(".modi_img").hide();
                $(".img_productId_hide").val("");
                $(".defaule_image").val("");
                $(".del_image").html("");
            });

        });
        function del(id) {
            $("#productId").val(id);
            var str="确定要删除此货品吗？";
            $.ajax({
                type : 'post',
                url : 'queryGoodsMarket.htm?goodsInfoId='+id,
                async : false,
                success : function(data) {
                    if(data.length>0){
                      str="此货品已参加过促销活动，确定要删除此货品吗？";

                    }
                }
            })
            $("#errorT").html(str);
            $("#delete-tip1").modal('show');
        }
        function delmar() {
            $("#mform").submit();
        }

        function refuseReason(refuseRen) {
            $(".modal-title").html("拒绝原因");
            $(".show_title").html(refuseRen);
            $("#refuse-tip").modal('show');
        }

        //点击保存
        function saveChoooseTrademark(url) {
            var batch_image = '';
            if (Object.prototype.toString.call(url) === '[object Array]') {//如果是数组(图片库上传)
                batch_image = url.join(",");
                url = batch_image;
            } else {//如果是字符串(本地上传)
                url = url;
            }
            $.ajax({
                url: '${basePath}/batchSaveProductImage.htm?productId=' + $(".img_productId_hide").val() + '&image=' + url,
                success: function (data) {
                    if (data == "1") {
                        $.get("${basePath}/queryImageListByProductId.htm?productId=" + $(".img_productId_hide").val(), function (data) {
                            var imageListHtml = "";
                            for (var i = 0; i < data.length; i++) {
                                imageListHtml = imageListHtml + "<li id='imagediv" + i + "'><img alt='' id='image" + i + "' src='" + data[i].imageInName + "' />" + "<div class='img-ops'>" + "<button class='btn btn-primary btn-xs' type='button'onclick=" + "setDefaultImage(" + "'" + data[i].imageInName+ "'," + data[i].goodsImgId + ");" + ">设为默认</button>" + "<button class='btn btn-default btn-xs' type='button' onclick=" + "delImage(" + "'" + data[i].goodsImgId + "',this);" + ">删除</button></div></li>";
                            }
                            $(".edit-images-list").html(imageListHtml);
                        });
                    }
                }
            });
        }

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
