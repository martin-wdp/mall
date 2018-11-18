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
    <title></title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">

    <link href="<%=basePath%>css/jquery-ui.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/reset.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style_app.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="container-fluid page_body">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <ol class="breadcrumb Noprint">
                    <li>系统</li>
                    <li>应用管理</li>
                </ol>

                <h2 class="main_title">应用管理</h2>
                <div class="p20">
                    <div class="app-content">
                        <div class="filter_area">
                            <form action="" class="form-inline">
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">应用名称</span>
                                        <input type="text" class="form-control" id="appName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-primary" type="button">搜索</button>
                                </div>
                            </form>
                            <button class="btn btn-primary add-app" type="button" data-toggle="modal" data-target="#addApp" onclick="selectMenuId();"><i class="glyphicon glyphicon-plus"></i>添加新应用</button>
                        </div>
                        <div class="app-wrap">
                            <ul class="app-list clearfix" id="appListUL">
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
                        <div class="table_foot">
                            <div class="table_pagenav pull-right">
                                <div class="meneame" id="pageFoot">

                                </div>
                            </div>
                        </div>
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
                                            <div class="controls" id="appNameDiv"></div>
                                        </div>
                                        <div class="form-item">
                                            <label class="control-label">介绍：</label>
                                            <div class="controls" id="appDescDiv">

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

                    <%--添加App--%>
                    <div class="modal fade" id="addApp" role="dialog" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">添加应用</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="addAppBoss.htm" class="form-horizontal" id="addAppBoss" method="post" enctype="multipart/form-data">
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label"><span class="text-danger">*</span>App名称：</label>
                                            <div class="col-sm-8">
                                                <div class="checkbox">
                                                    <input type="text" name="appName" class="form-control" id="checkAppName"/>
                                                    <span class="help-block"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">App Logo：</label>
                                            <div class="col-sm-8">
                                                <div class="checkbox">
                                                    <input name="appLogo" id="appLogo" type="hidden" value=""/>
                                                    <img alt="" id="img" src="images/default_head.jpg" height="50"/>
                                                    <input type="button" id="choose" value="选择"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">App版本：</label>
                                            <div class="col-sm-8">
                                                <div class="checkbox">
                                                    <input type="text" name="appVersion" class="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label"><span class="text-danger">*</span>是否上线：</label>
                                            <div class="col-sm-8">
                                                <label class="radio-inline">
                                                    <input type="radio"  value="1" name="publishStatus" checked="checked"/>是
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio"  value="0" name="publishStatus">否
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">菜单ID范围：</label>
                                            <div class="col-sm-8">
                                                <div class="checkbox">
                                                    <input type="text" name="startMenuId" class="form-control" id="checkStartMenuId" style="width: 85px;float:left;" readonly/>
                                                    <span style="float:left;margin-left:8px;margin-right:8px">-</span>
                                                    <input type="text" name="endMenuId" class="form-control" id="checkEndMenuId" style="width: 85px;float:left;"/>
                                                    <span class="help-block"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label"><span class="text-danger">*</span>上传文件(后台)：</label>
                                            <div class="col-sm-8">
                                                <input type="file" class="form-control" name="appLocation1" id="checkAppLocation"/>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label"><span class="text-danger"></span>上传文件(前台)：</label>
                                            <div class="col-sm-8">
                                                <input type="file" class="form-control" name="appLocation2" />
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label"><span class="text-danger"></span>上传文件(第三方)：</label>
                                            <div class="col-sm-8">
                                                <input type="file" class="form-control" name="appLocation3"/>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">安装时执行的SQL：</label>
                                            <div class="col-sm-8">
                                                <textarea cols="90" rows="4" name="loadSql"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">启用时执行的SQL：</label>
                                            <div class="col-sm-8">
                                                <textarea cols="90" rows="4" name="startSql"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">停用时执行的SQL：</label>
                                            <div class="col-sm-8">
                                                <textarea cols="90" rows="4" name="stopSql"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">卸载时执行的SQL：</label>
                                            <div class="col-sm-8">
                                                <textarea cols="90" rows="4" name="unloadSql"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">App简介：</label>
                                            <div class="col-sm-8">
                                                <textarea cols="90" rows="5" name="appIntroduction"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">App描述：</label>
                                            <div class="col-sm-8">
                                                <div  class="summernote" id="addAppDescHtml"></div>
                                                <textarea name="appDesc" style="display: none;"id="addAppDesc"></textarea>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-primary" type="button" id="submitAddAppBoss" onclick="addAppBoss();">添加App</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--更新App--%>
                    <div class="modal fade" id="updateApp" role="dialog" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">更新应用</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="updateAppBoss.htm" class="form-horizontal" id="updateAppBoss" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="id" class="form-control" id="id">
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label"><span class="text-danger">*</span>App名称：</label>
                                            <div class="col-sm-8">
                                                <div class="checkbox">
                                                    <input type="text" name="appName" class="form-control" id="updateAppName"/>
                                                    <span class="help-block"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">App Logo：</label>
                                            <div class="col-sm-8">
                                                <div class="checkbox">
                                                    <input name="appLogo" id="updateAppLogo" type="hidden" value=""/>
                                                    <img alt="" id="updateImg" src="images/default_head.jpg" height="50"/>
                                                    <input type="button" id="updateChoose" value="选择"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">App版本：</label>
                                            <div class="col-sm-8">
                                                <div class="checkbox">
                                                    <input type="text" name="appVersion" class="form-control" id="updateAppVersion"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label"><span class="text-danger">*</span>是否上线：</label>
                                            <div class="col-sm-8">
                                                <label class="radio-inline">
                                                    <input type="radio"  value="1" id="publishStatus1" name="publishStatus" checked="checked"/>是
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio"  value="0" id="publishStatus2" name="publishStatus"/>否
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">上传文件(后台)：</label>
                                            <div class="col-sm-8">
                                                <input type="file" class="form-control" name="appLocation1" id="updateAppLocation"/>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label"><span class="text-danger"></span>上传文件(前台)：</label>
                                            <div class="col-sm-8">
                                                <input type="file" class="form-control" name="appLocation2" />
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label"><span class="text-danger"></span>上传文件(第三方)：</label>
                                            <div class="col-sm-8">
                                                <input type="file" class="form-control" name="appLocation3"/>
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">安装时执行的SQL：</label>
                                            <div class="col-sm-8">
                                                <textarea cols="90" rows="4" name="loadSql" id="updateLoadSql"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">启用时执行的SQL：</label>
                                            <div class="col-sm-8">
                                                <textarea cols="90" rows="4" name="startSql" id="updateStartSql"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">停用时执行的SQL：</label>
                                            <div class="col-sm-8">
                                                <textarea cols="90" rows="4" name="stopSql" id="updateStopSql"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">卸载时执行的SQL：</label>
                                            <div class="col-sm-8">
                                                <textarea cols="90" rows="4" name="unloadSql" id="updateUnloadSql"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">App简介：</label>
                                            <div class="col-sm-8">
                                                <textarea cols="90" rows="5" name="appIntroduction" id="updateAppIntroduction"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="col-sm-5 control-label">App描述：</label>
                                            <div class="col-sm-8">
                                                <div  class="summernote" id="updateAppDescHtml"></div>
                                                <textarea style="display: none;" name="appDesc" id="updateAppDesc"></textarea>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-primary" type="button" id="submitUpdateAppBoss" onclick="updateAppBoss();">更新App</button>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</div>
</div>
<input type="hidden" id="serverUrl" value="${serverUrl}"/>
<input type="hidden" id="CSRFToken" value="${token}"/>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/app/appboss.js"></script>
</body>
</html>

