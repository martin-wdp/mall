<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/21
  Time: 11:01
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
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                 <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
                <h2 class="main_title">${pageNameChild}<small>(共${pageBean.rows }条)</small></h2>

                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong>当在应用市场中下载安装应用时，会查询此处的网站列表，安装模块到相应的环境中，所以这里要设置完成后，才可在每个环境中安装应用。
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="$('#addAppServerModal').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form action="deleteAppServerBatch.htm" method="post" id="deleteAppServerForm">
                    <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken"/>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="50"><input type="checkbox" onclick="selectAll('appServerIds',this)"></th>
                            <th width="100">序号</th>
                            <th>网站地址</th>
                            <th>位置</th>
                            <th>主机</th>
                            <th width="200">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.list}" var="tempObject" varStatus="i">
                        <tr>
                            <td width="50"><input type="checkbox" name="appServerIds"  value="${tempObject.appServerId }"></td>
                            <td>${i.count }</td>
                            <td>${tempObject.appServerRoot }</td>
                            <td>
                                <c:if test="${tempObject.appServerType == '1' }">Boss</c:if>
                                <c:if test="${tempObject.appServerType == '2' }">商家</c:if>
                                <c:if test="${tempObject.appServerType == '3' }">前台</c:if>
                            </td>
                            <td>
                                <c:if test="${tempObject.isMain == '1' }"><span class="label label-success">是</span></c:if>
                                <c:if test="${tempObject.isMain == '0' }"><span class="label label-default">否</span></c:if>
                            </td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="showEditAppServerForm(${tempObject.appServerId })">编辑</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick="showAjaxDeleteConfirmAlert('deleteAppServer.htm?CSRFToken=${token}&appServerId=${tempObject.appServerId }')">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </form>
                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pageBean }"/>
                    </c:import>

                </div>

            </div>

        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addAppServerModal"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加网站地址</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" action="addAppServer.htm" id="addAppServerForm" method="post">
                    <input type="hidden" name="CSRFToken" value="${token}">
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>网站地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required specstr" name="appServerRoot" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>位置：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <label class="radio-inline"><input type="radio" name="appServerType" value="1" checked>Boss</label>
                            <label class="radio-inline"><input type="radio" name="appServerType" value="2">商家</label>
                            <label class="radio-inline"><input type="radio" name="appServerType" value="3">前台</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>是否主机：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <label class="radio-inline"><input type="radio" name="isMain" value="0" checked>否</label>
                            <label class="radio-inline"><input type="radio" name="isMain" value="1">是</label>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAddAppServerForm()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="editAppServerModal"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑网站地址</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" action="updateAppServer.htm" id="editAppServerForm" method="post">
                    <input type="hidden" id="appServerId" name="appServerId"/>
                    <input type="hidden" name="CSRFToken" value="${token}">
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>网站地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required specstr" name="appServerRoot" id="appServerRoot">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">位置：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <label class="radio-inline">
                                <input type="radio" name="appServerType" id="open1" value="1" checked> Boss
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="appServerType" id="open2" value="2"> 商家
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="appServerType" id="open3" value="3"> 前台
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>是否主机：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <label class="radio-inline"><input type="radio" name="isMain" id="isMain0" value="0" checked>否</label>
                            <label class="radio-inline"><input type="radio" name="isMain" id="isMain1" value="1">是</label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditAppServerForm()">保存</button>
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
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/system/system_common.js"></script>
<script src="<%=basePath%>js/app/appserver.js"></script>
</body>
</html>

