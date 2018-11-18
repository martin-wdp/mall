<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/17
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<jsp:include page="../../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
              <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}<small>(共${pageBean.rows}条)</small></h2>

                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 异常页面设置，若页面配置不当，会造成用户体验方便的问题，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>

                    <div class="box_data container-fluid p20">
                        <div class="row">
                            <input type="hidden" id="CSRFToken" name="CSRFToken" value="${token}"/>
                            <c:forEach items="${pageBean.list}" var="tempObject" varStatus="i">
                                <fmt:formatDate value="${tempObject.modifyDate }" var="cdate" type="date"/>
                                <div class="col-sm-6">
                                    <div class="box_item" style="padding:10px;">
                                        <h2>${tempObject.pageName }(${tempObject.pageTitle})</h2>
                                        <c:if test="${tempObject.usedStatus == 1 }">
                                            <span class="label label-success">已启用</span>
                                        </c:if>
                                        <c:if test="${tempObject.usedStatus == 0 }">
                                            <span class="label label-default">已停用</span>
                                        </c:if>
                                        <p><img style="width:100%;height:188px;" alt="${tempObject.pageTitle }" title="${tempObject.pageTitle }" src="<%=basePath %>${tempObject.page }"></p>
                                        <p>最新修改时间：${cdate}</p>
                                        <div class="box_edit">
                                            <a href="javascript:;" onclick="showEditPage(${tempObject.errorPageId})">编辑</a>
                                                <%--    <a href="<%=basePath %>${tempObject.page }" target="_blank">预览</a> --%>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--<div class="col-sm-6">
                                <div class="box_add">
                                    <a href="javascript:;"  onclick="$('#editPayment').modal('show')">
                                        <i class="glyphicon glyphicon-plus"></i> 添加支付方式
                                    </a>
                                </div>
                            </div>--%>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="pageEdit"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑异常页面</h4>
            </div>
            <form role="form" class="form-horizontal" action="updateSysErrorPage.htm" id="pageEditForm" method="post">
            <div class="modal-body">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="errorPageId" id="errorPageId" />
                    <div class="form-group">
                        <label class="control-label col-sm-5">页面名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="control-label" id="pageNameLabel">404</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">页面标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control" id="pageTitle" name="pageTitle">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus" id="open1" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus" id="open2" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">页面描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <div class="summernote" id="pageDesDiv"></div>
                        </div>
                    </div>
                    <textarea style="display: none;" id="pageDes" name="pageDes"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditForm()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
            </form>
        </div>
    </div>
</div>


<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/system/syserrorpage.js"></script>
</body>
</html>
