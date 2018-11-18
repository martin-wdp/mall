<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/16
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
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

                <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows}条)</small></h2>
                <input type="hidden" id="CSRFToken" value="${token}"/>

                <div class="box_data container-fluid p20">
                    <div class="row">
                        <c:forEach items="${pageBean.list}" var="tempObject" varStatus="i">
                            <div class="col-sm-6">
                                <div class="box_item" style="height:124px;">
                                    <h2 style="width: 230px">${tempObject.mete}</h2>

                                    <p>最新修改时间： <fmt:formatDate value="${tempObject.modifyDate}"
                                                               pattern="yyyy-MM-dd HH:mm:ss"/></p>

                                    <p class="text-muted">
                                        <c:choose>
                                        <c:when  test="${fn:length(tempObject.meteDes)>40}">

                                            ${fn:substring(tempObject.meteDes, 0,40 )}</c:when>
                                            <c:otherwise>
                                                ${tempObject.meteDes}
                                            </c:otherwise>
                                        </c:choose>
                                    </p>

                                    <div class="box_edit">
                                        <a href="javascript:;"
                                           onclick="openSeoUpdate(${tempObject.seoId},${tempObject.type})">编辑</a>
                                </div>
                            </div>
                        </div>
                        </c:forEach>

                    </div>
                </div>

            </div>

        </div>

    </div>

</div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addSEO"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加SEO设置</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="addSeoForm" action="addSeoConf.htm"  method="post">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>SEO标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="seoTitle">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>SEO关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="meteKey" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>SEO描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <textarea class="form-control required specstr" rows="5" name="meteDes" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus"  value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus"  value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitForm();">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="editSEO"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑SEO设置</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="editSeoForm" method="post">
                    <input type="hidden" id="seoId" name="seoId" />
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>SEO标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" id="mete" name="mete">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>SEO关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" id="meteKey" name="meteKey" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>SEO描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <textarea class="form-control required specstr" rows="5" id="meteDes" name="meteDes" ></textarea>
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updateSeo();">保存</button>
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
    function openSeoUpdate(id, obj) {

        if (obj == 1) {
            window.location.href = "openUpdateSeoConfPage.htm?id=" + id + "&CSRFToken=${token}"
        }
        else if (obj == 2) {
            window.location.href = "openSeoWebsitemapPage.htm?id=" + id + "&CSRFToken=${token}"
        } else {
            window.location.href = "openSeoRobotsPage.htm?id=" + id + "&CSRFToken=${token}"
        }

    }

</script>
</body>
</html>

