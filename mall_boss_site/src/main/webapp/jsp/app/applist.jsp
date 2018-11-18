<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/12
  Time: 15:20
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

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/jquery-ui.min.css" rel="stylesheet">
<link href="<%=basePath%>css/reset.min.css" rel="stylesheet">
<link href="<%=basePath%>css/style_app.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 引用头 -->
<div class="container-fluid page_body">
    <div class="row">
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">

                <div class="common_data p20">
                    <div class="common_form p20">
                        <div class="app-content">
                            <div class="filter_area">
                                <form action="" class="form-inline">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon">应用名称</span>
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <button class="btn btn-primary" type="button">搜索</button>
                                    </div>
                                </form>
                                <%--<button class="btn btn-primary add-app" type="button" data-toggle="modal" data-target="#addApp"><i class="glyphicon glyphicon-plus"></i>添加新应用</button>--%>
                            </div>
                            <div class="app-wrap">
                                <ul class="app-list clearfix">
                                    <c:forEach items="${pb.list}" var="app">
                                    <li>
                                        <div class="app-icon"><img alt="" src="${app.appLogo}"/></div>
                                        <p class="app-name">${app.appName}</p>
                                        <div class="app-cont">
                                            <p class="full-name"><span>Name:</span>${app.appName}</p>
                                            <p class="app-version"><span>Version:</span>1.0.1</p>
                                            <p class="app-status"><span>Status:</span>Active</p>
                                            <p class="app-detail"><button class="btn btn-default btn-xs" type="button" data-toggle="modal" data-target="#appDetail">查看详情</button></p>
                                            <div class="app-operation">
                                                <a href="javascript:;" class="ui-state-default ui-corner-all" onclick="installApp(${app.id})"><span class="ui-icon ui-icon-arrowthickstop-1-s"></span></a>
                                                <a href="javascript:;" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-stop"></span></a>
                                                <a href="javascript:;" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-refresh"></span></a>
                                                <a href="javascript:;" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-transferthick-e-w"></span></a>
                                                <a href="javascript:;" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-trash"></span></a>
                                            </div>
                                        </div>
                                    </li>
                                    </c:forEach>
                                </ul>
                            </div>

                            <c:import url="../page/searchPag.jsp">
                                <c:param name="pageBean" value="${pageBean }"/>
                                <c:param name="path" value="../"></c:param>
                            </c:import>
                        </div>

                        <div class="modal fade" id="appDetail" role="dialog" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">应用详情</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="md-content">
                                            <div class="form-item">
                                                <label class="control-label">应用名称：</label>
                                                <div class="controls">Adobe Photoshop CC</div>
                                            </div>
                                            <div class="form-item">
                                                <label class="control-label">介绍：</label>
                                                <div class="controls">
                                                    <p>这里是应用介绍这里是应用介绍这里是应用介绍这里是应用介绍这里是应用介绍这里是应用介绍</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="addApp" role="dialog" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">添加应用</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form action="" class="form-horizontal">
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Start Bundle：</label>
                                                <div class="col-sm-8">
                                                    <div class="checkbox">
                                                        <input type="checkbox">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Refresh Packages：</label>
                                                <div class="col-sm-8">
                                                    <div class="checkbox">
                                                        <input type="checkbox">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group form-group-sm">
                                                <label class="col-sm-4 control-label">Start Level：</label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control ">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label"></label>
                                                <div class="col-sm-8">
                                                    <input type="file" class="form-control">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-primary" type="button" data-dismiss="modal">安装</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/jquery.min.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script>
    function installApp(id) {
        $.ajax({
            url:'<%=basePath%>installapp.htm?CSRFToken=${token}&id='+id,
            success:function(data) {
                alert(data);
            }
        });
    }

</script>
</body>
</html>

