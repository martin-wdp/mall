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


                <div class="common_form common_form_lg">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> roborts.txt文件设置，主要设置qpmall前台网站哪些可以收录,哪些不允许收录，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <form role="form" class="form-horizontal" id="editSeoForm" method="post">
                        <input type="hidden" name="seoId" value="${seoConf.seoId}">
                        <input type="hidden" name="type" value="${seoConf.type}">

                        <div class="form-group">
                            <label class="control-label col-sm-5"><span style="color:red;">*</span>所属平台：</label>

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
                            <label class="control-label col-sm-5">Robots内容：</label>

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
                        <p><strong>robots.txt是什么？</strong></p>

                        <p>如果您的商城里面某个页面不想让百度和google收录，该怎么办？<br>
                            其实搜索引擎已经和我们达成一个约定，如果我们按约定那样做了，它们就不会被收录。这个文件就是：robots.txt。<br>
                            robots.txt是一个最简单的.txt文件，用以告诉搜索引擎哪些网页可以收录，哪些不允许收录。</p>

                        <p><strong>关于robots.txt您需要注意以下几点：</strong></p>

                        <p>1.如果你的站点对所有搜索引擎公开，则不用做这个文件或者robots.txt为空就行。<br>
                            2.一般情况下，robots.txt里只写着两个函数：User-agent和 Disallow。<br>
                            3.有几个禁止，就得有几个Disallow函数，并分行描述。<br>
                            4.至少要有一个Disallow函数，如果都允许收录，则写: Disallow: 如果都不允许收录，则写:Disallow: / (注：只是差一个斜杠)。</p>

                        <p><strong>补充说明：</strong></p>

                        <p>User-agent: * 星号说明允许所有搜索引擎收录<br>
                            Disallow: /search.html 说明 http://www.qianmi.com/search.html 这个页面禁止搜索引擎抓取。<br>
                            Disallow: /index.php? 说明类似这样的页面都禁止搜索引擎抓取。</p>
                    </div>
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

