<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/4/16
  Time: 10:33
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
    <title>促销LOGO</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">

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

                <h2 class="main_title">${pageNameChild}<small>(共${pb.rows}条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <input type="hidden" id="formName" value="findalllogo.htm"/>
                        <input type="hidden" id="formId" value="searchForm"/>
                        <form role="form" class="form-inline" id="searchForm"  action="findalllogo.htm" method="post">
                            <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">促销LOGO名称</span>
                                    <input type="text" class="form-control" name="promotionLogoName" value="${pageBean.objectBean.promotionLogoName}">
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
                            <button type="button" class="btn btn-info" onclick="showAddLogo()">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showAjaxDeleteBatchConfirmAlert('batchDeleteLogoForm','promotionLogoId')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="batchDeleteLogoForm" action="dellogo.htm" method="post">
                        <input type="hidden" name="CSRFToken" value="${token}"/>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" onclick="allunchecked(this,'promotionLogoId')"></th>
                            <th>LOGO图片</th>
                            <th>LOGO名称</th>
                            <th width="200">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                    <c:forEach items="${pb.list}" var="logo" varStatus="sta">
                        <tr>
                            <td><input type="checkbox" name="promotionLogoId" value="${logo.promotionLogoId}"></td>
                            <td><img width="" height="28"  src="${logo.promotionLogoUrl}" /></td>
                            <td>${logo.promotionLogoName}</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="showEditLogoForm('${logo.promotionLogoId}','${logo.promotionLogoName}','${logo.promotionLogoUrl }')">编辑</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick="showDeleteOneConfirmAlert('dellogo.htm?promotionLogoId=${logo.promotionLogoId}&CSRFToken=${token}')">删除</a></li>
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
                        <c:param name="path" value="../"></c:param>
                    </c:import>

                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="logoAdd"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加LOGO</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" enctype="multipart/form-data" action="addlogo.htm?CSRFToken=${token }" method="post" id="saveLogoForm">
                    <div class="form-group">
                        <label class="col-sm-6 control-label">
                            <span class="text-danger">*</span>LOGO名称：
                        </label>
                        <div class="col-sm-13">
                            <input type="text" class="form-control required" name="promotionLogoName" onblur="checkLogoNameExists(this)">
                        </div>
                        <div class="col-sm-5">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span class="text-danger">*</span>LOGO图片：</label>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" class="required" name="picFile" id="logoImage"></p>
                            <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:;" class="tagLogo help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">预览图片：</label>
                        <div class="col-sm-12">
                            <img alt="" src="" id="preview_image" width="90px">
                        </div>
                        <div class="col-sm-1"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitSaveLogoForm()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="logoEdit"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑LOGO</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" enctype="multipart/form-data" action="updateLogo.htm?CSRFToken=${token }" method="post" id="updateLogoForm">
                    <input type="hidden" id="oldLogoName"/>
                    <input type="hidden" id="promotionLogoId" name="promotionLogoId"/>
                    <input type="hidden" id="pageNo" name="pageNo" value="${pageBean.pageNo}"/>
                    <div class="form-group">
                        <label class="col-sm-6 control-label">
                            <span class="text-danger">*</span>LOGO名称：
                        </label>
                        <div class="col-sm-13">
                            <input type="text" class="form-control required" name="promotionLogoName" id="logoName">
                        </div>
                        <div class="col-sm-5">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">LOGO图片：</label>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="picFile" id="logoImage_update"></p>
                        </div>
                        <div class="col-sm-1"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">预览图片：</label>
                        <div class="col-sm-12">
                            <img alt="" src="" id="preview_image_update" width="90px">
                        </div>
                        <div class="col-sm-1"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitUpdateLogoForm()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
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
<script src="<%=basePath%>js/common/common_checked.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/marketing/promotion_logo.js"></script>
</body>
</html>

