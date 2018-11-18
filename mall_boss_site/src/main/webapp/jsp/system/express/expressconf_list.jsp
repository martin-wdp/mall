<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/26
  Time: 17:55
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
    <title>配送方式设置</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
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
                <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken"/>
               <div class="common_data p20">
                     <div class="filter_area">
                        <input type="hidden" id="formId" value="searchForm"/>
                        <input type="hidden" id="formName" value="queryExpressConf.htm?deleteStatus=${deleteStatus}"/>
                       <!-- <form role="form" class="form-inline" action="queryExpressConf.htm?deleteStatus=${deleteStatus}" method="post" id="searchForm">
                            <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">上门自提名称</span>
                                    <input type="text" class="form-control" name="expressName" value="${expressName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">运费</span>
                                    <input type="text" class="form-control" name="price" id="searchPrice" value="${price}">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="searchExpressConf()">搜索</button>
                            </div>
                        </form>-->
                    </div>
                    <form id="deleteExpressConfForm" method="post" action="deleteBatchExpressConf.htm">
                        <input type="hidden" name="CSRFToken" value="${token}"/>
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th width="50"><input type="checkbox" onclick="selectAll('expressIds',this)"></th>
                                <th width="100">记录ID</th>
                                <th>配送方式名称</th>
                                <!-- <th>物流公司</th>
                                <th>承运公司</th> -->
                                <th>是否启用</th>
                                <th width="200">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageBean.list}" var="tempObject" varStatus="i">
                                <tr>
                                    <td width="50"><input type="checkbox" value="${tempObject.expressId }" name="expressIds"></td>
                                    <td>${tempObject.expressId }</td>
                                    <td>${tempObject.name }</td>
                                    <%-- <td>${tempObject.expFleid1}</td>
                                    <td>${tempObject.expFleid2}</td> --%>
                                    <td>
                                        <c:if test="${tempObject.usedStatus == 1 }">
                                            <a href="javascript:;" class="label label-success" title="点击开启" onclick="changeUserdStatus(${tempObject.expressId })">是</a>
                                        </c:if>
                                        <c:if test="${tempObject.usedStatus == 0 }">
                                            <a href="javascript:;" class="label label-default" title="点击关闭" onclick="changeUserdStatus(${tempObject.expressId })">否</a>
                                        </c:if>
                                    </td>

                                    <td>
                                        <div class="btn-group">

                                            <c:if test="${tempObject.pickupFlag == 0 }">
                                                <button type="button" class="btn btn-default" onclick="window.location.href='initLogisticsCompany.htm'">查看物流公司</button>
                                            </c:if>
                                            <c:if test="${tempObject.pickupFlag == 1 }">
                                                <button type="button" class="btn btn-default" onclick="window.location.href='queryDeliveryPointByPb.htm'">查看自提点</button>
                                            </c:if>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="javascript:;" onclick="showEditExpressConfForm(${tempObject.expressId})">编辑</a></li>
                                                <!--<li><a href="javascript:;" onclick="showAjaxDeleteConfirmAlert('deleteOneExpressConf.htm?CSRFToken=${token}&expressId=${tempObject.expressId}')">删除</a></li>-->
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>

                    <c:import url="../../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pageBean }"/>
                    </c:import>

                </div>

            </div>

        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addShip"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加上门自提</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="addExpressConfForm" action="addExpressConf.htm" method="post">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><font color="red">*</font>上门自提名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="name" id="expressName">
                        </div>
                    </div>
                   <!--  <div class="form-group">
                        <label class="control-label col-sm-5"><font color="red">*</font>物流公司：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="w200" data-live-search="true" id="expressAdd" name="express">

                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="display:none;">
                        <label class="control-label col-sm-5"><font color="red">*</font>承运公司：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="w200" data-live-search="true" id="sendExpressAdd" name="sendExpress">

                            </select>
                        </div>
                    </div> -->
                    <div class="form-group">
                        <label class="control-label col-sm-5">备注：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <textarea class="form-control specstr" rows="5" name="des"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="addExpressConf();">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="editShip"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改上门自提</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="editExpressConfForm" action="updateExpressConf.htm" method="post">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="expressId" id="expressId"/>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><font color="red">*</font>配送方式名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="name" id="expressNameEdit">
                        </div>
                    </div>

                   <!--  <div class="form-group">
                        <label class="control-label col-sm-5"><font color="red">*</font>物流公司：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="w200" data-live-search="true" id="expressEdit" name="express">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><font color="red">*</font>承运公司：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="w200" data-live-search="true" id="sendExpressEdit" name="sendExpress">

                            </select>
                        </div>
                    </div> -->
                    <div class="form-group">
                        <label class="control-label col-sm-5">备注：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <textarea class="form-control specstr" rows="5" name="des" id="desEdit"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus" id="open3" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus" id="open4" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updateExpressConf();">保存</button>
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
<script src="<%=basePath%>js/system/expressconf.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
<script>
  /*  $(function(){
        /* 为选定的select下拉菜单开启搜索提示 
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END 
    }); */
</script>
</body>
</html>
