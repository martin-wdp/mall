<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/26
  Time: 13:26
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
    <title>又拍云设置</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
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
<jsp:include page="../../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}<a style="float:right;font-size:17px;" href="javascript:void(0);" onclick="showTestUpyun()">测试又拍云</a></h2>

                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 又拍云接口，若修改不当，会影响图片的处理与显示，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="common_form">
                        <form class="form-horizontal" id="updateUpyunForm">
                            <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
                            <input type="hidden" name="upyunId" id="upyunId" value="${upyunConf.upyunId }">
                            <div class="form-group">
                                <label class="control-label col-sm-6">空间名：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="bucketName">${upyunConf.bucketName}</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w300" value="${upyunConf.bucketName}" name="bucketName" id="bucketName" clazz="required specstr">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">用户名：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="userName">${upyunConf.userName}</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w200" value="${upyunConf.userName}" name="userName" id="userName" clazz="required specstr">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">密码：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="passWord">${upyunConf.passWord}</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w300" value="${upyunConf.passWord}" name="userName" id="passWord" clazz="required specstr">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">空间地址：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="urlPath">${upyunConf.urlPath}</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w300" value="${upyunConf.urlPath}" name="userName" id="urlPath" clazz="required url">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">是否开启：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value " attr_id="usedStatus" attr_type="radio">
                                        <c:if test="${upyunConf.usedStatus=='0'}"><span class="label label-default">否</span></c:if>
                                        <c:if test="${upyunConf.usedStatus=='1'}"><span class="label label-success">是</span></c:if>
                                    </span>
                                    <div class="form_fill">
                                        <label class="radio-inline">
                                            <input type="radio" name="usedStatus" id="open1" value="1" <c:if test="${upyunConf.usedStatus=='1'}">checked</c:if>> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="usedStatus" id="open2" value="0" <c:if test="${upyunConf.usedStatus=='0'}">checked</c:if>> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form_btns popover right">
                                <div class="arrow" style="top:50%"></div>
                                <h3 class="popover-title">确定修改？</h3>
                                <div class="popover-content">
                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary form_sure">确定</button>
                                        &nbsp;&nbsp;
                                        <button type="button" class="btn btn-default form_cancel">取消</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>

            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="testUpyun"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">测试又拍云</h4>
            </div>
            <div class="modal-body">
                <iframe style="display: none;" name="upyun_frame"></iframe>
                <form class="form-horizontal" id="testUpyunForm" action="testUpyun.htm?CSRFToken=${token}" method="post" enctype="multipart/form-data" target="upyun_frame">
                    <div class="form-group">
                        <label class="control-label col-sm-6">选择文件：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14 form_item">
                                <input type="file" class="form-control w300" name="picFile" onchange="submitTestUpYunForm(this)"/>
                        </div>
                    </div>
                    <div id="testUpyunResult"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/system/upyunconf.js"></script>
</body>
</html>

