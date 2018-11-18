<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/16
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>SEO设置</title>

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
                <h2 class="main_title">${seoConf.seoTitle}</h2>
                <div class="common_form">
                    <form role="form" class="form-horizontal" id="editSeoForm" method="post">
                        <input type="hidden" name="seoId" value="${seoConf.seoId}">
                        <input type="hidden" name="type" value="${seoConf.type}">
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span style="color:red;">*</span>SEO标题：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <input type="text" class="form-control required" id="mete" name="mete"
                                       value="${seoConf.mete}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span style="color:red;">*</span>SEO关键字：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <input type="text" class="form-control required specstr" id="meteKey" name="meteKey"
                                       value="${seoConf.meteKey}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span style="color:red;">*</span>SEO描述：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <textarea class="form-control required specstr" rows="5" id="meteDes"
                                          name="meteDes">${seoConf.meteDes}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">是否启用：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <label class="radio-inline">
                                    <input type="radio" name="usedStatus" id="open1" value="1"
                                    <c:if test="${seoConf.usedStatus==1}"> checked </c:if> > 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="usedStatus" id="open2" value="0"  <c:if
                                            test="${seoConf.usedStatus==0}"> checked </c:if>> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-6">
                                <button type="button" onclick="history.go(-1)" class="btn btn-primary">返回</button>
                                &nbsp;&nbsp;
                                <button type="button" onclick="editSeoForm()" class="btn btn-primary">保存</button>
                            </div>
                        </div>
                    </form>
                </div>

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
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/system/system_common.js"></script>
<script>

    function editSeoForm() {
        if ($("#editSeoForm").valid()) {
            $("#editSeoForm").attr("action", "updateSeoConf.htm?CSRFToken=${token}")
            $("#editSeoForm").submit();
        }
    }

</script>
</body>
</html>

