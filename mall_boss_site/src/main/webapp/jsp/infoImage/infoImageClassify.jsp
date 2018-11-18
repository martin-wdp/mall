<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/31
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
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
                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 基本信息设置，若修改不当，会影响相关网站地址和logo，会造成页面显示和跳转出错，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="showAddImageCate()">
                                <i class="glyphicon glyphicon-plus"></i> 添加分类
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}"/>
                    <!-- 这是另一种表格，带伸缩，不带分页 -->
                    <table class="treetable table table-striped table-hover" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>分类名称</th>
                            <th>是否包含图片</th>
                            <%--<th>是否系统分类</th>--%>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody id="treetable">
                        </tbody>
                    </table>

                </div>

            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="addImgCate">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加图片分类</h4>
            </div>
            <form id="saveImageCateForm" action="saveImageClassifyAction.htm" method="post">
            <input type="hidden" name="CSRFToken" value="${token}"/>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-5">父分类：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <div class="table-bordered">
                                <ul id="treeDemo" class="ztree"></ul>
                                <input type="hidden" name="parentId" id="parentId" value="-1"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>分类名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required specstr" name="classifyName" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">包含图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isHasImg" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isHasImg" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-5">系统分类：</label>--%>
                        <%--<div class="col-sm-1"></div>--%>
                        <%--<div class="col-sm-10">--%>
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" name="isSys" value="1" checked> 是--%>
                            <%--</label>--%>
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" name="isSys" value="0"> 否--%>
                            <%--</label>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitImageCateForm()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--编辑图片分类-->
<div class="modal fade" id="editImgCate">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑图片分类</h4>
            </div>
            <form id="editImageCateForm" action="updateImageClassifyAction.htm" method="post">
                <input type="hidden" name="CSRFToken" value="${token}"/>
                <input type="hidden" name="classifyId" id="classifyId"/>
                <div class="modal-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-5">父分类：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <div class="table-bordered">
                                    <ul id="treeDemo_update" class="ztree"></ul>
                                    <input type="hidden" name="parentId" id="parentId_update" value="-1"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>分类名称：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <input type="text" class="form-control required specstr" name="classifyName" id="classifyName" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">包含图片：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" id="isHasImg1" name="isHasImg" value="1" checked> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" id="isHasImg0" name="isHasImg" value="0"> 否
                                </label>
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-5">系统分类：</label>--%>
                            <%--<div class="col-sm-1"></div>--%>
                            <%--<div class="col-sm-10">--%>
                                <%--<label class="radio-inline">--%>
                                    <%--<input type="radio" id="isSys1" name="isSys" value="1" checked> 是--%>
                                <%--</label>--%>
                                <%--<label class="radio-inline">--%>
                                    <%--<input type="radio" id="isSys0" name="isSys" value="0"> 否--%>
                                <%--</label>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/jqtreetable.js"></script>
<script src="<%=basePath%>js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath%>js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/system/image_cate.js"></script>
</body>
</html>

