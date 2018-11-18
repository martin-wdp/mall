<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <!-- 引用左边 -->
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                 <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 审核开关开启后，可对第三方商家上传商品进行审核上架，请根据实际情况进行调整，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="common_form">
                        <form class="form-horizontal" action="changeAuditAction.htm" id="changeAuditAction">
                            <div class="form-group">
                                <label class="control-label col-sm-4">是否启用：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-10">
                                    <label class="radio-inline">
                                        <input  type="radio" name="isThirdAuditUsed" id="actionFlag1" value="0" <c:if test="${isThirdAuditUsed eq '0' }">checked="checked"</c:if>> 关闭
                                    </label>
                                    <label class="radio-inline">
                                        <input class="vm mr5" type="radio" name="isThirdAuditUsed" id="actionFlag2" value="1" <c:if test="${isThirdAuditUsed eq '1' }">checked="checked"</c:if>> 开启
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-5 col-sm-10">
                                    <button type="button" class="btn btn-primary" onclick=" $('#dialog-confirm2').modal('show');">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

<!-- 保存弹出窗口 -->
<div class="modal fade" id="dialog-confirm2"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    你确定要修改吗?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="examine()">确定</button>
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
<script>

    function examine(){
        $("#changeAuditAction").submit();
    }
</script>
</body>
</html>
