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
    String qpmallbasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
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

                <div class="common_form common_form_lg">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 网站地图设置，搜索引擎收录<%=qpmallbasePath%>的链接,生成后可在地址栏输入<%=qpmallbasePath%>/sitemap.xml查看.
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns col-sm-offset-2">
                            <button type="button" class="btn btn-info" onclick="$('#sitemap').modal('show')">
                                <i></i> 生成网站地图
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form role="form" class="form-horizontal" id="editSeoForm" method="post">
                        <input type="hidden" name="seoId" value="${seoConf.seoId}">
                        <input type="hidden" name="type" value="${seoConf.type}">

                        <div class="form-group">
                            <label class="control-label col-sm-5"><span style="color:red;">*</span>标题：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <input type="text" class="form-control required" id="mete" name="mete"
                                       value="${seoConf.mete}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">所属平台：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-4">
                                <select class="form-control" name="belongPlate">
                                    <option value="0" <c:if test="${seoConf.belongPlate==0}">selected</c:if>>PC版
                                    </option>
                                    <option value="1" <c:if test="${seoConf.belongPlate==1}">selected </c:if>>移动版
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span style="color:red;">*</span>Sitemap内容：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <textarea class="form-control required" rows="8"
                                          name="meteDes">${seoConf.meteDes}</textarea>
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
                    <div class="alert alert-warning alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                        <strong>XML Sitemap</strong> 通常称为Sitemap。 类似：www.qianmi.com/sitemap.xml，简单来讲，Sitemap
                        就是网站内部所有链接的列表。
                        制作Sitemap，并提交给搜索引擎可以使网站的内容完全被收录，包括那些隐藏比较深的页面。这是一种网站与搜索引擎对话的好方式。
                    </div>
                </div>

            </div>

        </div>

    </div>

</div>

<!-- Modal -->
<div class="modal fade" id="sitemap" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">生成网站地图</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="sitemapForm" method="post">
                    <div class="form-group" id="user">
                        <label class="control-label col-sm-6"> <span class="text-danger">*</span>生成网站地图URL：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="url" id="url" value="<c:choose><c:when test='${seoConf.url!=null}'>${seoConf.url}</c:when><c:otherwise><%=qpmallbasePath%></c:otherwise></c:choose>">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createsubsitemap()">确定</button>
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

    function createsubsitemap() {
        if ($("#sitemapForm").valid()) {
            var url = $("#url").val();
            var token = "${token}";
            //ajax调用
            $.ajax({
                type: "POST",
                url: "createSitemap.htm",
                data: {'seoId': ${seoConf.seoId}, 'CSRFToken': token, 'url': url},
                async: false,
                success: function (data) {
                    if (data == 1) {
                        $("#sitemap").modal("hide");
                        showTipAlert("生成网站地图成功")

                    }
                    else {
                        $("#sitemap").modal("hide");
                        showTipAlert("生成网站地图失败");

                    }
                }

            });
        }
    }

</script>
</body>
</html>

