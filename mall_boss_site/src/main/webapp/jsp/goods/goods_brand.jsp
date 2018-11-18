<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/4/1
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
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
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows}条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <input type="hidden" value="searchForm" id="formId">
                        <input type="hidden" value="findAllBrand.htm" id="formName">
                        <form role="form" class="form-inline" id="searchForm" action="findAllBrand.htm">
                            <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">品牌名称</span>
                                    <input type="text" class="form-control" name="brandName" value="${selectBean.brandName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">品牌别名</span>
                                    <input type="text" class="form-control" name="brandNickname" value="${selectBean.brandNickname}">
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
                            <button type="button" class="btn btn-info" onclick="$('#addBrand').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlert('deleBrandForm','brandIds')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            <button type="button" class="btn btn-info" onclick="window.open('exportGoodsBrandTemp.htm?CSRFToken=${token }')">
                                <i class="glyphicon glyphicon-save"></i> 下载品牌数据文件模板
                            </button>
                            <div class="btn-group">
                                <button type="button" class="btn btn-info" onclick="window.open('exportGoodsBrand.htm?CSRFToken=${token}');">
                                	<i class="glyphicon glyphicon-export"></i>导出品牌
                                </button>
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="javascript:;" onclick="showImport()">导入品牌</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="deleBrandForm" action="batchDelBrand.htm?CSRFToken=${token }" method="post">
                        <input type="hidden" value="${pb.pageNo}" name="pageNo"/>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" onclick="allunchecked(this,'brandIds')"></th>
                            <th>品牌LOGO</th>
                            <th>品牌名称</th>
                            <th>品牌别名</th>
                            <!-- <th>品牌网址</th> -->
                            <th>品牌排序</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pb.list}" var="brand">
                        <tr>
                            <td><input type="checkbox" name="brandIds" value="${brand.brandId }"/></td>
                            <td><img alt="" src="${brand.brandLogo}" height="36px"></td>
                            <td>${brand.brandName}</td>
                            <td>${brand.brandNickname}</td>
                        <%--     <td>${brand.brandUrl}</td> --%>
                            <td>${brand.brandSort}</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="showEditBrandForm(${brand.brandId})">编辑</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick="showDeleteOneConfirmAlert2('delBrand.htm?CSRFToken=${token}&brandId=${brand.brandId}&pageNo=${pb.pageNo}','确认要删除品牌${brand.brandName}吗')">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </form>

                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pb }"/>
                    </c:import>

                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addBrand"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加品牌</h4>
            </div>
            <form role="form" class="form-horizontal" id="saveBrandForm" enctype="multipart/form-data" action="saveBrand.htm?CSRFToken=${token}" method="post">
            <div class="modal-body">
                <div class="modal_form">
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>品牌名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required specstr" name="brandName" id="addBrandName" maxlength="25">
                                <span style='color:#a94442;font-weight: bold;display: none' id='helpTip' >品牌名称重复，请重新输入</span>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                品牌别名：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr" name="brandNickname" >
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6">LOGO图片：</label>
                            <div class="col-sm-12">
                                <p class="pt5"><input type="file" name="picFile" id="logoFile"></p>
                                <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                            </div>
                            <div class="col-sm-3">
                                <a href="javascript:;" class="brandLogo help_tips">
                                    <i class="icon iconfont">&#xe611;</i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6">预览LOGO：</label>
                            <div class="col-sm-12">
                                <img alt="" src="" id="preview_image" width="90px">
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <%--<div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>品牌网址：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required url" name="brandUrl">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>--%>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>品牌排序：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control w100 required number" name="brandSort">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <!--<div class="form-group">
                            <label class="control-label col-sm-6">是否推荐到首页：</label>
                            <div class="col-sm-16">
                                <label class="radio-inline">
                                    <input type="radio" name="promIndex"  value="1"> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="promIndex"  value="0" checked> 否
                                </label>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>-->
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitSaveBrandForm();">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
            </form>
        </div>
    </div>
</div>


<!-- 编辑品牌 -->
<div class="modal fade" id="editBrand"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑品牌</h4>
            </div>
            <form role="form" class="form-horizontal" id="editBrandForm" enctype="multipart/form-data" action="updateBrand.htm?CSRFToken=${token}" method="post">
                <input type="hidden" name="brandId" id="brandId"/>
                <input type="hidden" value="${pb.pageNo}" name="pageNo" id="pageNo"/>
                <div class="modal-body">
                    <div class="modal_form">
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>品牌名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required specstr" name="brandName" id="brandName" maxlength="25">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                品牌别名：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control specstr" name="brandNickname" id="brandNickname">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6">LOGO图片：</label>
                            <div class="col-sm-12">
                                <p class="pt5"><input type="file" name="picFile" id="logoFile_update"></p>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6">预览LOGO：</label>
                            <div class="col-sm-12">
                                <img alt="" src="" id="preview_image_update" width="90px">
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <%--<div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>品牌网址：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required url" name="brandUrl" id="brandUrl">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>--%>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>品牌排序：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control w100 required number" name="brandSort" id="brandSort">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <!--<div class="form-group">
                            <label class="control-label col-sm-6">是否推荐到首页：</label>
                            <div class="col-sm-16">
                                <label class="radio-inline">
                                    <input type="radio" name="promIndex" id="promIndex1"  value="1"> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="promIndex" id="promIndex0" value="0" checked> 否
                                </label>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>-->
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="submitUpdateBrandForm();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>



<!-- 编辑品牌 -->
<div class="modal fade" id="importBrand"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑品牌</h4>
            </div>
            <form role="form" class="form-horizontal" action="importGoodsBrand.htm?CSRFToken=${token }" enctype="multipart/form-data" method="post" id="importGoodsBrandForm" target="uploadFrame">
                <div class="modal-body">
                    <div class="modal_form">
                        <div class="form-group">
                            <label class="control-label col-sm-6">下载品牌模板：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <label class="control-label"><a href="exportGoodsBrandTemp.htm?CSRFToken=${token }">品牌模板文件.xls</a></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6">选择品牌数据文件：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <p class="pt5"><input type="file" class="required" name="importExcel"></p>
                            </div>
                        </div>
                        <div style="display:none;"><iframe id="importFrame" name="importFrame"></iframe></div>
                    </div>
                </div>
                </form>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="importBrand()">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            <input type="hidden" id="suffixArray" value="${ufs.suffixArray}"/>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/common/common_checked.js"></script>
<script src="<%=basePath%>js/goods/goods_brand.js"></script>


<script style="text/javascript">

    /**
     * 删除单个记录的确认框
     * @param deleteUrl 删除链接。
     */
    function showDeleteOneConfirmAlert2(deleteUrl,tips) {
        $("#modalDialog").remove();
        var confirmDialog =
                '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
                '    <div class="modal-dialog">'+
                '        <div class="modal-content">'+
                '            <div class="modal-header">'+
                '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
                '               <h4 class="modal-title">系统提示</h4>'+
                '           </div>'+
                '           <div class="modal-body">';
        if(tips!='' && tips!=undefined){
            confirmDialog +=tips;
        }else{
            confirmDialog +='确认要删除这条记录吗？';
        }
        confirmDialog += '           </div>'+
        '           <div class="modal-footer">'+
        '               <button type="button" class="btn btn-primary" onclick="doAjaxDeleteOne(\''+deleteUrl+'\');">确定</button>'+
        '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
        $(document.body).append(confirmDialog);
        $('#modalDialog').modal('show');
    }
    function doAjaxDeleteOne(deleteUrl) {
        if(deleteUrl!=null){
            deleteUrl = deleteUrl+"&searchText="+$('input[name="searchText"]').val();
        }
        $.ajax({
            url:deleteUrl,
            async:false,
            success: function (data) {

                window.location.href=data;
            }
        });
    }
</script>
</body>
</html>

