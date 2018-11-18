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
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .spanweight{
            display: inline-block;
            max-width: 100%;
            margin-bottom: 5px;
            font-weight: bold;
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

                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_info common_tabs mt20">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" onclick="changeStatus(0)" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">单品促销</a></li>
                        <li role="presentation" onclick="changeStatus(1)"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">类目促销</a></li>
                        <li role="presentation" onclick="changeStatus(2)"><a href="#tab3" aria-controls="tab2" role="tab" data-toggle="tab">品牌促销</a></li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab1">
                            <div class="common_form common_form_lg p20">
                                <!-- 单品促销选择 商品 -->
                                <form class="form-horizontal" action="doaddcouponnew.htm?CSRFToken=${sx}"    id="subFormOne" method="post">
                                    <input type="hidden"  id="c_statu" name="status" class="f_status" value="0">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span style="color: red;">*</span>优惠券名称：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control required specstr"  name="couponName">
                                        </div>
                                        <div class="col-sm-3">
                                            <a href="javascript:;" class="cuxiaomingchen help_tips">
                                                <i class="icon iconfont">&#xe611;</i>
                                            </a>
                                        </div>
                                    </div>
                                    <div id="goodsShow">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span class="text-danger">*</span>选择商品：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-4">
                                            <button type="button" class="btn btn-info" onclick="chooseProduct();">选择参加促销的商品</button>
                                            <span id="ps" class="spanweight"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">已选择商品：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-19">
                                            <table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;">
                                                <thead style="top:0;">
                                                <tr>
                                                    <th width="100">商品图片</th>
                                                    <th width="100">商品规格</th>
                                                    <th width="150">商品编号</th>
                                                    <th width="300">商品名称</th>
                                                    <th width="100">操作</th>
                                                </tr>
                                            </table>
                                            <div style="max-height:300px;overflow-y:auto;position:relative;">

                                                <table class="table table-striped table-hover table-bordered" id="readproduct">
                                                    <tbody style="">

                                                    </tbody>
                                                </table>

                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                    <div id="cateShow" style="display:none;">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span class="text-danger">*</span>选择类目：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-4">
                                            <button type="button" class="btn btn-info" onclick="chooseCate();">选择参加促销的类目</button>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>

                                    <div class="form-group" id="towCate">
                                        <label class="control-label col-sm-4">已选择类目：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-14">
                                            <table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;">
                                                <thead>
                                                <tr>
                                                    <th width="100">分类编号</th>
                                                    <th width="250">分类名称</th>
                                                    <th width="100">操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                            <div style="max-height:300px;overflow-y:auto;position:relative;">
                                                <table class="table table-striped table-hover table-bordered" id="list">
                                                    <tbody style="">

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                    <div id="brandShow"  style="display:none;">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span class="text-danger">*</span>选择品牌：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-4">
                                            <button type="button" class="btn btn-info" onclick="chooseBrandsView();">选择参加促销的品牌</button>
                                        </div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                    <div class="form-group" id="threeBrand">
                                        <label class="control-label col-sm-4">已选择品牌：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-14">
                                            <table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;">
                                                <thead>
                                                <tr>
                                                    <th width="100">品牌编号</th>
                                                    <th width="250">品牌名称</th>
                                                    <th width="100">操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                            <div style="max-height:300px;overflow-y:auto;position:relative;">
                                                <table class="table table-striped table-hover table-bordered" id="brandlist">
                                                    <tbody style="">

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span style="color: red;"></span>预览图片：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-8">
                                            <input name="couponPic"  id="couponImg"  type="hidden" value="">
                                            <img alt="" id="img" src="<%=basePath %>images/default_head.jpg" height="50">
                                            <input type="button" id="choose" value="选择"/>
                                        </div>
                                        <div class="col-sm-3">
                                            <a href="javascript:;" class="couponImg help_tips">
                                                <i class="icon iconfont">&#xe611;</i>
                                            </a>
                                        </div>
                                    </div>

                                    <%--<div class="form-group">--%>
                                        <%--<label class="control-label col-sm-4">是否发布：</label>--%>
                                        <%--<div class="col-sm-1"></div>--%>
                                        <%--<div class="col-sm-12">--%>
                                            <%--<label class="radio-inline">--%>
                                                <%--<input type="radio" checked name="couponIsShow" id="Radio3" value="1"> 是--%>
                                            <%--</label>--%>
                                            <%--<label class="radio-inline">--%>
                                                <%--<input type="radio" name="couponIsShow" id="Radio4" value="0"> 否--%>
                                            <%--</label>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span style="color: red;">*</span>开始时间：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-9">
                                            <div class="input-group date form_datetime " id="startpicker">
                                                <input name="couponSTime" id="startTime" class="form-control required "
                                                       style="width: 200px" type="text" value="" readonly>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span style="color: red;">*</span>结束时间：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-9">
                                            <div class="input-group date form_datetime " id="endpicker">
                                                <input name="couponETime" id="endTime" class="form-control required"
                                                       style="width:200px" type="text required" value="" readonly>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span style="color: red;">*</span>生成张数：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-12">
                                                <input  name="couponCount" style="width: 164px" type="text" class="form-control required digits" id="couponCount">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span style="color: red;">*</span>领取张数：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-12">
                                                <input  name="couponGetNo" style="width: 164px"type="text" class="form-control required digits" onblur="checkNum(this);" id="couponGetNo">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span style="color: red;">*</span>选择规则：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-5">
                                            <select class="form-control" name="couponRulesType" id="couponRulesType" onchange="selectChange(this);">
                                                <option value="1">直降</option>
                                                <option value="2">满减</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div id="hiddenPrice">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span style="color: red;">*</span>减：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-5">
                                            <input name="downPrice" class="form-control required number">
                                        </div>
                                    </div>
                                    </div>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="control-label col-sm-4">使用等级：</label>--%>
                                        <%--<div class="col-sm-1"></div>--%>
                                        <%--<div class="col-sm-19">--%>
                                            <%--<label class="checkbox-inline">--%>
                                                <%--<input type="checkbox" name="onCheck" onclick="allunchecked(this,'lelvelId')"> 全部--%>
                                            <%--</label>--%>

                                            <%--<c:forEach items="${customerLevel}" var="level">--%>

                                                <%--<label class="checkbox-inline">--%>
                                                    <%--<input type="checkbox" id="lelvelId" value="${level.pointLelvelId }" name="lelvelId"> ${level.pointLevelName }--%>
                                                <%--</label>--%>
                                            <%--</c:forEach>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">优惠券描述：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-19">
                                            <div class="summernote" id="summernote"></div>
                                            <input type="hidden" value="" name="couponRemark" id="countId">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-5 col-sm-9">
                                            <button type="button" onclick="subFormOne()" class="btn btn-primary">保存</button>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="chooseGoods"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">选择货品</h4>
            </div>
            <div class="modal-body">
                <div class="mb10">
                    <form class="form-inline" id="searchGoodsInfo" action="" method="post" >
                        <input type="hidden" name="pageNo" id="pageNo" value=""/>
                        <input type="hidden" name="pageSize" id="pageSize" value=""/>
                        <div class="form-group">
                            商品名称：<input type="text" class="form-control" placeholder="商品名称"  name="goodsName">
                        </div>
                        <div class="form-group">
                            商品编号：<input type="text" class="form-control" placeholder="商品编号"  name="goodsNo">
                        </div>
                        <div class="form-group">
                            货品编号：<input type="text" class="form-control" placeholder="货品编号" name="goodsInfoItemNo">
                        </div>
                        <div class="form-group">
                            货品售价：<input type="text" class="form-control isNumber" id="lowgoodsInfoPrice" name="lowGoodsInfoPrice">
                            -<input type="text" class="form-control isNumber"  id="highgoodsInfoPrice" name="highGoodsInfoPrice">
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-info" onclick="chooseProduct();">搜索</button>
                        </div>
                    </form>
                </div>
                <table class="table table-striped table-hover table-bordered" id="productAddList">
                    <thead>
                    <tr>
                        <th width="50"><input type="checkbox" onchange="chooseAllPro(this);" id="chooseAllPro"></th>
                        <th width="100">货品图片</th>
                        <th width="100">货品规格</th>
                        <th width="150">货品编号</th>
                        <th>货品名称</th>
                        <th>价格</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <div class="table_foot" id="productAddList_table_foot">
                    <div class="table_pagenav pull-right">
                        <div class="meneame">

                        </div>
                    </div>
                    <div class="clr"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="chooseCates"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">选择类目</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-6">选择类目：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <div class="area_choose">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="trueCate();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="chooseBrands"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">选择品牌</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span class="text-danger">*</span>选择品牌：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">

                            <select class="form-control w150 " data-live-search="true" multiple id="brand">
                                <c:forEach items="${brandlist }" var="brand" varStatus="index">
                                    <option value="${brand.brandId}" >${brand.brandName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="chooseBrand();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<input  type="hidden" value="${token}" id="CSRFToken">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/couponlist/addcoupon.js"></script>
<script src="<%=basePath %>/js/common/common_checked.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
<script>
    $(function(){
        /* 表单项的值点击后转换为可编辑状态 */
        $('.form_value').click(function(){
            var formItem = $(this);
            if(!$('.form_btns').is(':visible')) {
                formItem.parent().addClass('form_open');
                $('.form_btns').show();
                $('.form_btns').css({
                    'left': formItem.next().offset().left + 70 + 'px',
                    'top': formItem.next().offset().top - 30 + 'px'
                });
                $('.form_sure,.form_cancel').click(function () {
                    $('.form_btns').hide();
                    formItem.parent().removeClass('form_open');
                });
            }
        });


        /* 为选定的select下拉菜单开启搜索提示 */

        $('select[data-live-search="true"]').select2();
        //这是普通的带搜索框下拉菜单，只可用于纯英文或纯中文选项


        /* 为选定的select下拉菜单开启搜索提示 END */

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

        $('.spec_set input').change(function(){
            if($(this).is(':checked')){
                $(this).parent().parent().next().slideDown('fast');
            }
            else {
                $(this).parent().parent().next().slideUp('fast');
            }
        });


    });


    function chooseProduct(){
        doAjax(1,8);
        $('#chooseGoods').modal('show');
    }

    /**
     * 判断领取张数是否大于生成张数
     * */
    function checkNum(obj){
        var couponCount = $("#couponCount").val();
        if(parseInt($(obj).val()) > parseInt(couponCount)){
            showTipAlert("领取张数不能大于生成张数！");
            $("#couponCount").focus();
        }
    }


    /*点击添加货品的时候*/

    function doAjax(pageNo, pageSize)
    {
        $("#pageNo").val(pageNo),
                $("#pageSize").val(pageSize),
                $("#chooseAllPro").attr("checked",false);
        $.ajax({
            url:"newqueryProductForCoupon.htm",
            data:$("#searchGoodsInfo").serialize(),
            async:true,
            success:function(data){
                var list = data.list;
                var productListHtml = "";
                for (var i = 0; i < list.length; i++)
                {
                    productListHtml = productListHtml + "<tr>" +"<td class='tc'><input type='checkbox' class='productId' name='productId' onclick='addpro(this);'";

                    var pro =  document.getElementsByName("goodsIdP");
                    for(var j=0;j<pro.length;j++){
                        if(pro[j].value==list[i].goodsInfoId){
                            productListHtml = productListHtml +' checked="checked" ';
                        }
                    }
                    productListHtml = productListHtml+" value='" + list[i].goodsInfoId + "'/></td>";
                    productListHtml+='<td><img src="'+list[i].goodsInfoImgId+'" class="goodsImg"  width="50" height="50"/></td>';
                    productListHtml+= "<td  class='goodsTag' >" ;
                    if (list[i].specVo.length > 0)
                    {
                        for (var k = 0; k < list[i].specVo.length; k++)
                        {
                            productListHtml = productListHtml + list[i].specVo[k].spec.specName;
                            productListHtml = productListHtml + ":" + list[i].specVo[k].goodsSpecDetail.specDetailName + "<br/>";
                        }
                    }
                    productListHtml = productListHtml + "</td>" + "<td class='goodsNo'>" + list[i].goodsInfoItemNo+ "</td>" + "<td  class='goodsName' title='"+list[i].goodsInfoName+"' >" + list[i].goodsInfoName + "</td>"+"<td class='goodsInfoPreferPrice'>"+list[i].goodsInfoPreferPrice  + "</tr>";
                }
                $("#productAddList tbody").html(productListHtml);
                $("input[type=button]").button();
                /*添加页脚*/
                $("#productAddList .meneame").html("");
                var foot = "";
                var i = 1;
                for (var l = data.startNo; l <= data.endNo; l++)
                {
                    if ((i - 1 + data.startNo) == data.pageNo)
                    {
                        foot = foot + "<span class='current'> " + (i - 1 + data.startNo) + "</span>";
                    }
                    else
                    {
                        foot = foot + "<a onclick='doAjax(" + (i - 1 + data.startNo) + "," + (data.pageSize) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
                    }
                    i++;
                }
                foot = foot + "";
                /*添加tfoot分页信息*/
                $("#productAddList_table_foot .meneame").html(foot);
            }
        });

    }
</script>
</body>
</html>
