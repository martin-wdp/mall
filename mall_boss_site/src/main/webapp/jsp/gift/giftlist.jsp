<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
    <title>积分商品列表</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>js/uploadify/uploadify.css" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
    <div class="row">
        <!-- 引用左边 -->
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <form role="form" class="form-inline" id="search_from" method="post" action="searchgiftlist.htm">
                            <input type="hidden" value="search_from" id="formId">
                            <input type="hidden" value="searchgiftlist.htm" id="formName">

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">赠品名称</span>
                                    <input type="text" class="form-control"  name="giftName" value="${pageBean.objectBean.giftName }">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="$('#addPointsGood').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="delBatch()">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="50"><input name="giftOrderId"  onclick="allunchecked(this,'giftId')" type="checkbox"></th>
                            <th>赠品信息</th>
                            <th>赠品编号</th>
                            <th>所属分类</th>
                            <th>所需积分</th>
                            <th>是否发布</th>
                            <th>是否推荐</th>
                            <th>库存</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.list}" var="gift" varStatus="i">
                        <tr>
                            <td><input type="checkbox" name="giftId"  value="${gift.giftId }"></td>
                            <td>
                                <div class="data_item">
                                    <img width="50" height="50" src='${gift.giftPicList[0].picUrl}'/>
                                    <p title="${gift.giftName }"><c:if test="${fn:length(gift.giftName )>25}">${fn:substring(gift.giftName  , 0, 25)}</c:if>
                                        <c:if test="${fn:length(gift.giftName )<=25}">${gift.giftName }</c:if>
                                    </p>
                                    <p class="text-muted">${gift.giftPrice }</p>
                                </div>

                            </td>
                            <td>${gift.giftCode }</td>
                            <td>${gift.giftCateName }</td>
                            <td>${gift.giftIntegral}</td>
                            <td><a href="javascript:;">
                               <c:if test="${gift.giftIssue eq '0'}"> <span class="label label-default">否    </span></c:if>
                                <c:if test="${gift.giftIssue eq '1'}"><span class="label label-success">是    </span></c:if>
                            </a></td>
                            <td><a href="javascript:;">

                                <c:if test="${gift.giftRecommend eq '0'}"> <span class="label label-default">否    </span></c:if>
                                <c:if test="${gift.giftRecommend eq '1'}"><span class="label label-success">是    </span></c:if>
                            </a></td>
                            <td>${gift.giftStock }</td>
                            <td>
                                <div class="btn-group">
                                <button type="button" class="btn btn-default" onclick="updateGift(${gift.giftId })" >编辑</button>
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                    <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#" onclick="modifiedImage(${gift.giftId})">编辑图片</a></li>
                                </ul>
                                    </div>
                            </td>
                        </tr>
                     </c:forEach>
                        </tbody>
                    </table>

                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${map.pb}"/>
                    </c:import>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- 添加Modal -->
<div class="modal fade" id="addPointsGood"  role="dialog">
    <div class="modal-dialog modal-lg">
        <form id="couponForm" action="doaddgift.htm" method="post" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加积分商品</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>商品名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-15">
                            <input type="text" class="form-control required" name="giftName" id="giftName" maxlength="60">
                            <label id="expressNo_tips"></label>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>商品编号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control required" name="giftCode" id="giftCode" maxlength="30">
                            <label id="giftCode_tips"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>选择分类：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <input type="hidden" name="giftCateId" id="giftCateId" value="0"/>
                            <input type="text" class="form-control required" id="cateChooseInput" data-toggle="dropdown" readonly>
                            <label id="cateChooseInput_tips"></label>
                            <div class="dropdown-menu" role="menu" id="cateChoose">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>兑换所需积分：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control required digits"  name="giftIntegral" id="giftIntegral" maxlength="10" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>每单限购数量：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control required digits"  name="giftLimitBuy" id="giftLimitBuy" maxlength="10" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否发布：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" name="giftIssue" checked="checked" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="giftIssue"  value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否推荐：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" name="giftRecommend" checked="checked" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="giftRecommend" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>售价：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control required isNumber" name="giftPrice" id="giftPrice" data-toggle="dropdown" >
                            <label id="giftPrice_tips"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>货号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control required"  name="giftArtNo" id="giftArtNo" data-toggle="dropdown" >
                            <label id="giftArtNo_tips"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>库存：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control required digits"  name="giftStock" id="giftStock" maxlength="10" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>重量：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control required isNumber"  name="giftWeight" id="giftWeight" data-toggle="dropdown" >
                            <label id="giftWeight_tips"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">商品简介：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <textarea class="form-control" rows="5" name="giftDesc" id="giftDesc"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">详细介绍：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <div class="summernote" id="contentstr"></div>
                            <input type="hidden" id="giftText"  name="giftText">
                        </div>
                    </div>


                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="addsubmit()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
        </form>
    </div>
</div>

<!-- 编辑Modal -->
<div class="modal fade" id="updatePointsGood"  role="dialog">
    <div class="modal-dialog modal-lg">
        <form id="doupdategift" action="doupdategift.htm" method="post" >
            <input type="hidden" name="giftId" id="giftId2">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加积分商品</h4>
                </div>
                <div class="modal-body">
                    <div class="form-horizontal">

                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>商品名称：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-15">
                                <input type="text" class="form-control required" name="giftName" id="giftName2" maxlength="60">
                                <label id="expressNo2_tips"></label>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>商品编号：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control required" name="giftCode" id="giftCode2" maxlength="30">
                                <label id="giftCode2_tips"></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>选择分类：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="hidden" name="giftCateId" id="giftCateId2" value="0"/>
                                <input type="text" class="form-control required" id="cateChooseInput2" data-toggle="dropdown" readonly>
                                <label id="cateChooseInput2_tips"></label>
                                <div class="dropdown-menu" role="menu" id="cateChoose2">
                                    <ul id="treeDemo2" class="ztree"></ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>兑换所需积分：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control required digits"  name="giftIntegral" id="giftIntegral2" maxlength="10" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>每单限购数量：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control required digits"  name="giftLimitBuy" id="giftLimitBuy2" maxlength="10" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">是否发布：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <label class="radio-inline">
                                    <input type="radio" name="giftIssue" id="giftIssue2"  checked="checked"  value="1"> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="giftIssue" id="giftIssue22"  value="0"> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">是否推荐：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <label class="radio-inline">
                                    <input type="radio" name="giftRecommend" id="giftRecommend2" checked="checked" value="1"> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="giftRecommend" id="giftRecommend22" value="0"> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>售价：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control required isNumber" name="giftPrice" id="giftPrice2" data-toggle="dropdown" >
                                <label id="giftPrice2_tips"></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>货号：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control required"  name="giftArtNo" id="giftArtNo2" data-toggle="dropdown" >
                                <label id="giftArtNo2_tips"></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>库存：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control required digits"  name="giftStock" id="giftStock2" maxlength="10" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>重量：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control required isNumber"  name="giftWeight" id="giftWeight2" data-toggle="dropdown" >
                                <label id="giftWeight2_tips"></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">商品简介：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <textarea class="form-control" rows="5" name="giftDesc" id="giftDesc2"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">详细介绍：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <div class="summernote" id="contentstr2"></div>
                                <input type="hidden" id="giftText2"  name="giftText">
                            </div>
                        </div>


                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="updategift()">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 编辑图片Modal -->
<div class="modal fade" id="picEdit">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑图片</h4>
            </div>
            <div class="modal-body">
                <div class="mb20">
                    <input style="margin-left:5px;" type="button" id="choose" value="选择"/>
                </div>
                <form id="updateProductImage" enctype="multipart/form-data" action="updateProductImage.htm" method="post">
                    <input id="giftIds" name="giftIds" type="hidden"/>

                    <div class="container-fluid">
                    <div class="row" id="row">

                    </div>
                </div>
                    </form>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 删除Modal -->
<div class="modal fade" id="dialog-confirm2"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal"  method="post" id="delallgift">
                <input type="hidden" name="CSRFToken" value="${token}">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    你确定要删除选中的记录吗?
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" >确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 删除图片Modal -->
<div class="modal fade" id="dialog-confirm3"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">

            <input id="picId" type="hidden"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    你确定要删除该图片吗?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="deletePhoto()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath%>js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="<%=basePath%>js/jqtreetable.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script type="text/javascript" src="<%=basePath%>js/uploadify/jquery.uploadify.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script>

    /* 下面是关于树形菜单 */
    var setting = {
        data : {
            key : { }, simpleData : {
                enable : true
            }
        },
        callback: {
            onClick: zTreeOnClick
        }
    };
    var  checkedList ='';
    var num=0;
    function addsubmit(){
        if($("#couponForm").valid()&&num==0){
            num+=1;
          var text=  $("#contentstr").code();
            $("#giftText").val(text);
            $("#couponForm").submit();
        }
    }
    function updategift(){
        if($("#doupdategift").valid()){
            var text=  $("#contentstr2").code();
            $("#giftText2").val(text);
            $("#doupdategift").submit();
        }
    }

    // 删除报表
    function delBatch(){
        if(checkSelected('giftId',0)==false){
            showTipAlert("最少选择一行!");
            return false;
        }else{
            $('#dialog-confirm2').modal('show');
            $("#delallgift").attr("action","delallgift.htm?giftId="+checkedList);
        }
    }


    //检查是否选中一行
    function checkSelected(objId,modifyFlag){
        checkedList= new Array();
        $("input[name='"+objId+"']:checked").each(function() {
            checkedList.push($(this).val());
        });
        if(modifyFlag!=0){
            if(checkedList.length ==1){
                return true;
            }else{
                return false;
            }
        }

        if(checkedList.length > 0){
            return true;
        }else{
            return false;
        }
    }

    //编辑图片
    function modifiedImage(giftId){


        $("#giftIds").val(giftId);
        $.get("selectgiftpicbyId.htm?giftId=" + giftId, function (data)
        {
            var imageListHtml = "";
            for (var i = 0; i < data.length; i++)
            {
                imageListHtml = imageListHtml +  "<div class='col-sm-6' id='imagediv"+data[i].picId +"'> <div class='pic_item'> <div class='img'><img  id='image'"+ i+" src='"+data[i].picUrl +"'></div> <p class='text-right'>"+
                " <button type='button' class='btn btn-default btn-sm ' onclick="+"openDelImage("+data[i].picId+")>删除</button></p> </div> </div>" }
            $("#row").html(imageListHtml);

        });

        $('#picEdit').modal('show')

    }
    function openDelImage(picId){
        $('#dialog-confirm3').modal('show');
        $("#picId").val(picId);

    }

    function deletePhoto(){
        var picId=$("#picId").val();
        $.get("deletepicbypicid.htm?picId=" + picId, function (data)
        {
            if(data==1){
                $('#dialog-confirm3').modal('hide');
                $("#imagediv"+picId).remove();
            }

        });
    }
    //编辑积分商品
    function updateGift(giftId){

        var flaggiftid='';
        $('#updatePointsGood').modal('show');
        $.ajax({
            url:'ajaxupdategift.htm?giftId='+giftId,
            success:function(data) {
                if(data == null){
                    return;
                }
                flaggiftid=data.giftCateId;

                $('#giftId2').val(data.giftId);
                $('#giftName2').val(data.giftName);
                $('#giftCode2').val(data.giftCode);
                $('#giftCateId2').val(data.giftCateId);
                $('#giftIntegral2').val(data.giftIntegral);
                $('#giftLimitBuy2').val(data.giftLimitBuy);
                if(data.giftIssue ==1){
                    $("#giftIssue2").attr("checked","checked");
                }else{
                    $("#giftIssue22").attr("checked","checked");
                }
                if(data.giftRecommend ==1){
                    $("#giftRecommend2").attr("checked","checked");
                }else{
                    $("#giftRecommend22").attr("checked","checked");
                }
                $('#giftPrice2').val(data.giftPrice);
                $('#giftArtNo2').val(data.giftArtNo);
                $('#giftStock2').val(data.giftStock);
                $('#giftWeight2').val(data.giftWeight);
                $('#giftDesc2').val(data.giftDesc);
                $("#content2").val(data.giftText);
                $("#contentstr2").code(data.giftText);
                /*编辑查询商品分类放在树形控件中*/
                $.get("querygiftcate.htm", function (data)
                {
                    var zNodes = new Array();
                    for (var i = 0; i < data.length; i++)
                    {

                        if(flaggiftid==data[i].giftCateId){

                            $("#cateChooseInput2").val(data[i].giftCateName);
                        }
                        var node = {
                            id : data[i].giftCateId, pId : data[i].giftParentId, name : data[i].giftCateName, open : true
                        };
                        zNodes.push(node);
                    }
                    $.fn.zTree.init($("#treeDemo2"), setting, zNodes);
                });

            }

        });


    }


    function zTreeOnClick(event, treeId, treeNode) {

        $("#giftCateId").val(treeNode.id);
        $("#cateChooseInput").val(treeNode.name);
        $("#giftCateId2").val(treeNode.id);
        $("#cateChooseInput2").val(treeNode.name);



    };

    //保存选择的图片信息
    function saveChoooseImage(path) {
        var giftIds = $("#giftIds").val();
        var url = new Array();
        if (path.toString().indexOf(",") > -1) {
            var paths = path.toString().split(",");

            for (var i = 0; i < paths.length; i++) {
                url.push(paths[i]);
            }

        } else {
            url.push(path)
        }
        //ajax调用
        $.ajax({
            type: "POST",
            url: "newbatchSaveGiftImage.htm",
            data: {giftIds: giftIds, url: url},
            dataType: "json",
            traditional: true,
            async: false,
            success: function (data) {

            }

        });
        modifiedImage(giftIds);
    }
    $(function(){

        $("#choose").click(function () {

            art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=10000', {
                lock: true,
                opacity: 0.3,
                width: '900px',
                height: '400px',
                title: '选择图片'
            });
        });


        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 富文本编辑框 */
        $('.summernote').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN'
        });




        //点击新增出现下拉内容,光标移开下拉内容消失
        $('#cateChooseInput').click(function(){
            $('#cateChoose').show().css({
                'left' : $(this).prev('.input-group-addon').width() + 'px',
                'minWidth' : '200px'
            });
            $('#cateChoose').mouseleave(function(){
                $(this).hide();
            });
        });

         //点击编辑出现下拉内容,光标移开下拉内容消失
        $('#cateChooseInput2').click(function(){
            $('#cateChoose2').show().css({
                'left' : $(this).prev('.input-group-addon').width() + 'px',
                'minWidth' : '200px'
            });
            $('#cateChoose2').mouseleave(function(){
                $(this).hide();
            });
        });


        $(document).ready(function(){

            /*查询商品分类放在树形控件中*/
            $.get("querygiftcate.htm", function (data)
            {
                var zNodes = new Array();
                for (var i = 0; i < data.length; i++)
                {
                    var node = {
                        id : data[i].giftCateId, pId : data[i].giftParentId, name : data[i].giftCateName, open : true
                    };
                    zNodes.push(node);
                }
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            });



        });




    });
</script>
</body>
</html>
