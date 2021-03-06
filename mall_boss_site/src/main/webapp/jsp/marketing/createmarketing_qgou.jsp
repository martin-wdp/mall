<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
    <%--<link href="<%=basePath %>css/style_new.css" rel="stylesheet">--%>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .spanweight {
            display: inline-block;
            max-width: 100%;
            margin-bottom: 5px;
            font-weight: bold;
            color: #a94442;
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


                <h2 class="main_title">添加${pageNameChild}&nbsp;
                    <small><a href="javascript:void(0)" onclick="$('#qgou').modal('show')"
                              style="diaplay:block; float:right; padding-right: 12px">查看帮助<i class="icon iconfont">
                        &#xe611;</i></a></small>
                </h2>

                <div class="common_info common_tabs mt20">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab"
                                                                  data-toggle="tab">抢购促销</a></li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab1">
                            <div class="common_form common_form_lg p20">
                                <!-- 单品促销选择 商品 -->
                                <form class="form-horizontal" action="doaddmarketing.htm?CSRFToken=${token}"
                                      id="addFormOne" method="post">
                                    <input type="hidden" name="status" class="f_status" value="0">
                                    <input type="hidden" name="businessId" value="0">
                                    <input type="hidden" name="goodsInfoVipPrice" onchange="initQgprice()" value="0">
                                    <input type="hidden" name="goodsInfoPreferPrice" onchange="initQgprice()" value="0">

                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>促销名称：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control required" name="marketingName">
                                        </div>
                                        <div class="col-sm-3">
                                            <a href="javascript:;" class="cuxiaomingchen help_tips">
                                                <i class="icon iconfont">&#xe611;</i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">促销类型：<input type="hidden"
                                                                                          name="marketingType"
                                                                                          value="0"/></label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-8">

                                            <c:forEach items="${codexList }" var="codex">
                                                <c:if test="${codex.codexType=='11'}">
                                                    <label class="control-label"> ${codex.codexName }</label>
                                                    <input type="hidden" name="codexType" value="${codex.codexType }"/>
                                                    <input type="hidden" name="codexId" value="${codex.codexId }"/>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>选择商品：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-6">
                                            <button type="button" class="btn btn-info" onclick="chooseProduct();">
                                                选择参加促销的商品
                                            </button>
                                            <span id="ps" class="spanweight"></span>
                                        </div>
                                        <div class="col-sm-8"><label class="control-label" style="font-size: 14px">抢购促销的货品不允许参加其他促销</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">已选择商品：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-19">
                                            <table class="table table-striped table-hover table-bordered"
                                                   style="margin-bottom:0px;">
                                                <thead style="top:0;">
                                                <tr>
                                                    <th width="100">商品图片</th>
                                                    <th width="100">商品规格</th>
                                                    <th width="150">商品编号</th>
                                                    <th width="300">商品名称</th>
                                                    <th width="100">商品价格</th>
                                                    <th width="100">会员价格</th>
                                                    <th width="100">操作</th>
                                                </tr>
                                            </table>
                                            <div style="max-height:300px;overflow-y:auto;position:relative;">

                                                <table class="table table-striped table-hover table-bordered"
                                                       id="readproduct">
                                                    <tbody style="">

                                                    </tbody>
                                                </table>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>开始时间：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-9">
                                            <div class="input-group date form_datetime w200" id="startpicker">
                                                <input class="form-control required" type="text" id="startTime" value=""
                                                       readonly
                                                       name="sTime">
                                                <span class="input-group-addon"><span
                                                        class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>结束时间：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-9">
                                            <div class="input-group date form_datetime w200" id="endpicker">
                                                <input class="form-control required" type="text" value="" id="endTime"
                                                       readonly
                                                       name="eTime">
                                                <span class="input-group-addon"><span
                                                        class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>抢购图片：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-3">
                                            <button type="button" class="btn btn-default chooseProimg">选择</button>
                                        </div>
                                        <div class="col-sm-5"><label class="control-label" style="font-size: 14px">建议尺寸:588*338</label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>已选择图片：</label>

                                        <div class="col-sm-1"></div>
                                        <input type="hidden" id="rushImage" name="rushImage">

                                        <div class="col-sm-8" id="rushImagestr">

                                        </div>
                                    </div>

                                    <%-- <div class="form-group">
                                           <label class="control-label col-sm-4"><span class="text-danger">*</span>抢购折扣：</label>
                                           <div class="col-sm-1"></div>
                                           <div class="col-sm-8">
                                               <input type="text" name="rushDiscount" id="rushDiscount" class="form-control required zeroOne">
                                           </div>
                                     </div>--%>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>普通抢购折扣：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-8">
                                            <input type="text" name="rushDiscount" id="rushDiscount"
                                                   class="form-control required zeroOne" onchange="initQgprice()">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>会员抢购折扣：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-8">
                                            <input type="text" name="rushVipDiscount" id="rushVipDiscount"
                                                   class="form-control required zeroOne" onchange="initQgprice()">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>普通抢购价格：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-8">
                                            <input type="text" name="rushPrice" id="rushPrice"
                                                   class="form-control required " onblur="initQgDiscount()">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>会员抢购价格：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-8">
                                            <input type="text" name="rushVipPrice" id="rushVipPrice"
                                                   class="form-control required " onblur="initQgVipDiscount()">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-4"><span
                                                class="text-danger">*</span>ID限购：</label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-8">
                                            <input type="text" name="rushLimitation" id="rushLimitation"
                                                   class="form-control required integer">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-4">促销描述：<input type="hidden"
                                                                                          name="marketingDes"
                                                                                          id="marketingDes"/></label>

                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-19">
                                            <div class="summernote"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-5 col-sm-9">
                                            <button type="button" class="btn btn-primary" onclick="subFormOne();">保存
                                            </button>
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
<div class="modal fade" id="chooseGoods" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">选择商品</h4>
            </div>
            <div class="modal-body">
                <div class="mb10">
                    <form class="form-inline" id="searchGoodsInfo" action="" method="post">
                        <input type="hidden" name="CSRFToken" value="${token}"/>
                        <input type="hidden" name="marketType" value="11"/>

                        <input type="hidden" name="pageNo" id="pageNo" value=""/>
                        <input type="hidden" name="pageSize" id="pageSize" value=""/>

                        <div class="form-group">
                            商品名称：<input type="text" class="form-control" placeholder="商品名称" name="goodsName">
                        </div>
                        <div class="form-group">
                            货品编号：<input type="text" class="form-control" placeholder="货品编号" name="goodsInfoItemNo">
                        </div>
                        <div class="form-group">
                            货品售价：<input type="text" class="form-control isNumber" id="lowgoodsInfoPrice"
                                        name="lowGoodsInfoPrice">
                            -<input type="text" class="form-control isNumber" id="highgoodsInfoPrice"
                                    name="highGoodsInfoPrice">
                        </div>


                        <div class="form-group">
                            <button type="button" class="btn btn-info" onclick="chooseProduct();">搜索</button>
                        </div>
                    </form>
                </div>
                <table class="table table-striped table-hover table-bordered" id="productAddList">
                    <thead>
                    <tr>
                        <th width="50"><input style="display: none" type="checkbox" onchange="chooseAllPro(this);"
                                              id="chooseAllPro"></th>
                        <th width="100">商品图片</th>
                        <th width="100">商品规格</th>
                        <th width="150">商品编号</th>
                        <th>商品名称</th>
                        <th>金额</th>
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


<div class="modal fade" id="qgou" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加抢购购促销</h4>
            </div>
            <div class="modal-body">
                <div class="modal-article">
                    <p><em>1.</em>添加抢购促销，添加信息如下图</p>
                    <img src="<%=basePath %>/images/syshelp/img_c07.png" alt="">

                    <p><em>2.</em>保存成功后，该促销添加成功</p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
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
<script src="<%=basePath %>/js/select2.min.js"></script>
<script src="<%=basePath %>/js/common/common_alert.js"></script>
<script>

    //图片回调
    function saveChoooseImage(url, id) {
        if (typeof (url) != 'string') {
            url = url[0];
        }
        if (url.indexOf(',') != -1) {
            url = url.substring(0, url.indexOf(','));
        }
        $("#rushImagestr").html("<img height='90' width='150' src='" + url + "'/>");
        $("#rushImage").val(url);

    }
    $(function () {
        $("#addFormOne").validate();


        $(".chooseProimg").click(function () {
            art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=1', {
                lock: true,
                opacity: 0.3,
                width: '900px',
                height: '620px',
                title: '选择图片'
            });
        });
        /* 表单项的值点击后转换为可编辑状态 */
        $('.form_value').click(function () {
            var formItem = $(this);
            if (!$('.form_btns').is(':visible')) {
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
        $(".codexchoose").change(function () {
            var $arr = $(this).val().split('-');
            $(this).parents(".tab-pane").find('.codexDiv').hide();
            $(this).parents(".tab-pane").find('.codexDiv' + $arr[1]).show();
            $(this).parents(".tab-pane").find("input[name='codexId']").val($arr[0]);
            $(this).parents(".tab-pane").find("input[name='codexType']").val($arr[1]);
            $(this).parents(".tab-pane").find("input[name='codexType']").val($arr[1]);

        });
        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 下面是表单里面的日期时间选择相关的 */
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN',
            pickerPosition: 'bottom-left',
            todayBtn: true,
            viewSelect: 'hour'
        });
        $('.form_date').datetimepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN',
            pickerPosition: 'bottom-left',
            minView: 2,
            todayBtn: true
        });
        /* 下面是表单里面的日期时间选择相关的 END */

        /*下面是时间选择器开始时间不能大于结束时间设置  START*/
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

        /* 富文本编辑框 */
        $('.summernote').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN',
            onImageUpload: function (files, editor, $editable) {
                sendFile(files, editor, $editable);
            }
        });

        /* 富文本编辑框 */
        $('.summernoteTwo').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN',
            onImageUpload: function (files, editor, $editable) {
                sendFile(files, editor, $editable);
            }
        });

        /* 富文本编辑框 */
        $('.summernoteThree').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN',
            onImageUpload: function (files, editor, $editable) {
                sendFile(files, editor, $editable);
            }
        });

        /* 下面是关于树形菜单 END */

        /* 下面是表单里面的填写项提示相关的 */
        $('.cuxiaomingchen').popover({
            content: '促销名称',
            trigger: 'hover'
        });


    });


    function chooseProduct() {
        doAjaxNew(1, 8);
        $('#chooseGoods').modal('show');
    }


    /*点击添加货品的时候*/

    function doAjaxNew(pageNo, pageSize) {
        $("#pageNo").val(pageNo),
                $("#pageSize").val(pageSize),
                $("#chooseAllPro").attr("checked", false);
        /*AJAX查询所有的货品列表*/
        $.ajax({
            url: "newqueryProductForCoupon.htm",
            data: $("#searchGoodsInfo").serialize(),
            async: true,
            success: function (data) {
                var list = data.list;
                var productListHtml = "";
                for (var i = 0; i < list.length; i++) {
                    productListHtml = productListHtml + "<tr>" + "<td class='tc'><input type='checkbox' class='productId' name='productId' onclick='addpro(this);'";

                    var pro = document.getElementsByName("goodsIdP");
                    for (var j = 0; j < pro.length; j++) {
                        if (pro[j].value == list[i].goodsInfoId) {
                            productListHtml = productListHtml + ' checked="checked" ';
                        }
                    }
                    productListHtml = productListHtml + " value='" + list[i].goodsInfoId + "'/></td>";
                    productListHtml += '<td><img src="' + list[i].goodsInfoImgId + '" class="goodsImg"  width="50" height="50"/></td>';
                    productListHtml += "<td  class='goodsTag' >";
                    if (list[i].specVo.length > 0) {
                        for (var k = 0; k < list[i].specVo.length; k++) {
                            productListHtml = productListHtml + list[i].specVo[k].spec.specName;
                            productListHtml = productListHtml + ":" + list[i].specVo[k].goodsSpecDetail.specDetailName + "<br/>";
                        }
                    }
                    productListHtml = productListHtml + "</td>" + "<td class='goodsNo'>" + list[i].goodsInfoItemNo + "</td>" + "<td  class='goodsName' title='" + list[i].goodsInfoName + "' >" + list[i].goodsInfoName + "</td>";
                    productListHtml += "<td class='goodsInfoPreferPrice'>" + list[i].goodsInfoPreferPrice + "</td>" + "<td class='goodsInfoVipPrice'>" + list[i].goodsInfoVipPrice + "</td>" + "</tr>";
                }
                $("#productAddList tbody").html(productListHtml);
                $("input[type=button]").button();
                /*添加页脚*/
                $("#productAddList .meneame").html("");
                var foot = "";
                var i = 1;
                for (var l = data.startNo; l <= data.endNo; l++) {
                    if ((i - 1 + data.startNo) == data.pageNo) {
                        foot = foot + "<span class='current'> " + (i - 1 + data.startNo) + "</span>";
                    }
                    else {
                        foot = foot + "<a onclick='doAjaxNew(" + (i - 1 + data.startNo) + "," + (data.pageSize) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
                    }
                    i++;
                }
                foot = foot + "";
                /*添加tfoot分页信息*/
                $("#productAddList_table_foot .meneame").html(foot);
            }
        });
    }


    /*点击添加货品的时候*/

    function doAjax(pageNo, pageSize) {
        var productName = $("#searchGoodsName").val();
        $("#chooseAllPro").attr("checked", false);
        /*AJAX查询所有的货品列表*/
        $.get("queryProductForCoupon.htm?CSRFToken=${token}&pageNo=" + pageNo + "&pageSize=" + pageSize + "&productName=" + productName,
                function (data) {
                    var list = data.list;
                    var productListHtml = "";
                    for (var i = 0; i < list.length; i++) {
                        productListHtml = productListHtml + "<tr>" + "<td class='tc'><input type='checkbox' class='productId' name='productId' onclick='addpro(this);'";

                        var pro = document.getElementsByName("goodsIdP");
                        for (var j = 0; j < pro.length; j++) {
                            if (pro[j].value == list[i].goodsInfoId) {
                                productListHtml = productListHtml + ' checked="checked" ';
                            }
                        }
                        productListHtml = productListHtml + " value='" + list[i].goodsInfoId + "'/></td>";
                        productListHtml += '<td><img src="' + list[i].goodsInfoImgId + '" class="goodsImg"  width="50" height="50"/></td>';
                        productListHtml += "<td  class='goodsTag' >";
                        if (list[i].specVo.length > 0) {
                            for (var k = 0; k < list[i].specVo.length; k++) {
                                productListHtml = productListHtml + list[i].specVo[k].spec.specName;
                                productListHtml = productListHtml + ":" + list[i].specVo[k].goodsSpecDetail.specDetailName + "<br/>";
                            }
                        }
                        productListHtml = productListHtml + "</td>" + "<td class='goodsNo'>" + list[i].goodsInfoItemNo + "</td>" + "<td  class='goodsName' title='" + list[i].goodsInfoName + "' >" + list[i].goodsInfoName + "</td><td>" + list[i].goodsInfoPreferPrice + "</td></tr>";
                    }
                    $("#productAddList tbody").html(productListHtml);
                    $("input[type=button]").button();
                    /*添加页脚*/
                    $("#productAddList .meneame").html("");
                    var foot = "";
                    var i = 1;
                    for (var l = data.startNo; l <= data.endNo; l++) {
                        if ((i - 1 + data.startNo) == data.pageNo) {
                            foot = foot + "<span class='current'> " + (i - 1 + data.startNo) + "</span>";
                        }
                        else {
                            foot = foot + "<a onclick='doAjax(" + (i - 1 + data.startNo) + "," + (data.pageSize) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
                        }
                        i++;
                    }
                    foot = foot + "";
                    /*添加tfoot分页信息*/
                    $("#productAddList_table_foot .meneame").html(foot);
                });
    }
    /*添加货品的时候 分页*/
    /*改变每页显示的行数*/
    function changePageShow() {
        doAjax(1, $("#list_size").val());
    }
    /*跳转到某一页*/
    function changeShowPage(pageSize) {
        doAjax($("#list_pageno").val(), pageSize);
    }


    function addpro(obj) {
        var productId = $(obj).val();
        var goodsImg = $(obj).parents("tr").find(".goodsImg").attr("src");
        var goodsNo = $(obj).parents("tr").find(".goodsNo").text();
        var goodsName = $(obj).parents("tr").find(".goodsName").text();
        var goodsTag = $(obj).parents("tr").find(".goodsTag").html();
        var goodsInfoPreferPrice = $(obj).parents("tr").find(".goodsInfoPreferPrice").html();
        var goodsInfoVipPrice = $(obj).parents("tr").find(".goodsInfoVipPrice").html();
        if (obj.checked == true) {

            $("input[name='goodsIdP']").each(function () {
                $("#goods_tr_" + $(this).val()).remove();
            });
            var htm = "<tr id='goods_tr_" + productId + "'>";
            htm += '<td width="92"><img src="' + goodsImg + '" width="50" height="50"/><input type="hidden" name="goodsIdP" id="goods_Id_' + productId + '" value="' + productId + '"/></td>';
            htm += '<td width="98">' + goodsTag + '</td>';
            htm += '<td width="120">' + goodsNo + '</td>';
            htm += '<td  width="300">' + goodsName + '</td>';
            htm += '<td  width="100">' + goodsInfoPreferPrice + '</td>';
            htm += '<td  width="100">' + goodsInfoVipPrice + '</td>';
            htm += '<td width="70"><button onclick="removeTr(this);">移除</button></td>';
            htm += "</tr>";
            $("#readproduct tbody").append(htm);
            // ADD BY LY  添加价格和会员价格
            $("input[name='goodsInfoPreferPrice']").val(goodsInfoPreferPrice);
            $("input[name='goodsInfoVipPrice']").val(goodsInfoVipPrice);
            $("input[name='productId']").each(function () {
                if ($(this).val() != $(obj).val()) {
                    this.checked = false;
                }
            });

        } else {
            $("#goods_tr_" + productId).remove();

        }
    }

    function removeTr(obj) {
        $(obj).parents("tr").remove();
    }


    function chooseAllPro(obj) {


        if (obj.checked) {
            $("input[name='productId']").each(function () {
                this.checked = true;
                $("#goods_tr_" + $(this).val()).remove();
                addpro(this);
            });
        } else {
            $("input[name='productId']").each(function () {
                this.checked = false;
                addpro(this);
            });
        }
    }
    var num = 0;
    //保存商品促销
    function subFormOne() {
        var pro = document.getElementsByName("goodsIdP");
        var f = true;
        if (pro.length == 0) {
            $("#ps").html('请选择商品');
            $("#ps").addClass("error");
            f = false && f;
        } else {
            f = true && f;
            $("#ps").html('');
        }

        if ($("#addFormOne").valid() && f && num == 0) {
            num += 1;
            $("#marketingDes").val($(".summernote").code());
            if ($("#rushImage").val() == '' || $("#rushImage").val() == null) {
                showTipAlert("请选择抢购图片")
                return;
            }
            $("#addFormOne").submit();
        }

    }


    function initQgprice() {
        var goodsInfoPreferPrice = $("input[name='goodsInfoPreferPrice']").val();
        var goodsInfoVipPrice = $("input[name='goodsInfoVipPrice']").val();
        if (goodsInfoPreferPrice != "") {
            var zk = $("#rushDiscount").val();
            if (zk == "") {
                $("#rushPrice").val("");
            } else {
                if (zk > 1) {
                    zk = 1;
                }
                //var zknew = (zk * 1).toFixed(2);
                var zknew = round2(zk,2);
                $("#rushDiscount").val(zknew);
                //$("#rushPrice").val((zknew * goodsInfoPreferPrice).toFixed(2));
                $("#rushPrice").val(round2((zknew * goodsInfoPreferPrice),2));
            }
        } else {
            $("#rushPrice").val("");
        }
        if (goodsInfoVipPrice != "") {

            var yhzk = $("#rushVipDiscount").val();
            if (yhzk == "") {
                $("#rushVipPrice").val("");
            } else {
                if (yhzk > 1) {
                    yhzk = 1;
                }
                //var yhzknew = (yhzk * 1).toFixed(2);
                var yhzknew = round2(yhzk,2);
                $("#rushVipDiscount").val(yhzknew);
                //$("#rushVipPrice").val((yhzknew * goodsInfoVipPrice).toFixed(2));
                $("#rushVipPrice").val(round2((yhzknew * goodsInfoVipPrice),2));
            }
        } else {
            $("#rushVipPrice").val("");
        }
    }
    function round2(number,fractionDigits){
        with(Math){
            return round(number*pow(10,fractionDigits))/pow(10,fractionDigits);
        }
    }

    function initQgVipDiscount() {
        var goodsInfoPreferPrice = $("input[name='goodsInfoPreferPrice']").val();
        var goodsInfoVipPrice = $("input[name='goodsInfoVipPrice']").val();
        if (goodsInfoVipPrice != "") {

            var goodsInfoVipTgPrice = $("#rushVipPrice").val();
            //$("#grouponVipDiscount").val();
            if (goodsInfoVipTgPrice == "") {
                $("#rushVipDiscount").val("");
            } else {
                if (Number(goodsInfoVipTgPrice) > Number(goodsInfoVipPrice)) {
                    goodsInfoVipTgPrice = goodsInfoPreferPrice;
                }
                /*$("#rushVipPrice").val((goodsInfoVipTgPrice * 1).toFixed(2));
                $("#rushVipDiscount").val((goodsInfoVipTgPrice / goodsInfoVipPrice).toFixed(2));*/
                $("#rushVipPrice").val(round2(goodsInfoVipTgPrice,2));
                $("#rushVipDiscount").val(round2((goodsInfoVipTgPrice / goodsInfoVipPrice),2));
            }
        } else {
            $("#rushVipDiscount").val("");
        }
    }

    function initQgDiscount() {

        var goodsInfoPreferPrice = $("input[name='goodsInfoPreferPrice']").val();
        var goodsInfoVipPrice = $("input[name='goodsInfoVipPrice']").val();
        if (goodsInfoVipPrice != "") {

            var goodsInfoPreferTgPrice = $("#rushPrice").val();
            //$("#grouponVipDiscount").val();
            if (goodsInfoPreferTgPrice == "") {
                $("#rushDiscount").val("");
            } else {
                if (Number(goodsInfoPreferTgPrice) > Number(goodsInfoPreferPrice)) {
                    goodsInfoPreferTgPrice = goodsInfoPreferPrice;
                }
                /*$("#rushPrice").val((goodsInfoPreferTgPrice * 1).toFixed(2));
                $("#rushDiscount").val((goodsInfoPreferTgPrice / goodsInfoPreferPrice).toFixed(2));*/
                $("#rushPrice").val(round2(goodsInfoPreferTgPrice,2));
                $("#rushDiscount").val(round2((goodsInfoPreferTgPrice / goodsInfoPreferPrice),2));
            }
        } else {
            $("#rushDiscount").val("");
        }
    }

</script>
</body>
</html>
