<%--
  Created by IntelliJ IDEA.
  User: Zhoux
  Date: 2015/3/26
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>统计代码</title>
    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">

   
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../../page/header.jsp"></jsp:include>

<div class="container-fluid page_body">
    <div class="row">
        <jsp:include page="../../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

                <div class="common_data p20">

                    <div class="filter_area">
                        <form id="search_from" role="form" class="form-inline" action="queryStatisticsCode.htm" method="post">
                            <input type="hidden" name="CSRFToken" value="${token}">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">统计方案标题</span>
                                    <input type="text" class="form-control required" name="staTitle" id="staTitle" value="${queryParam.searchValue}">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary" id="searchButton">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="$('#addStatisticalCode').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="deleteBatch('bodyForm','staCodeId');">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th><input type="checkbox"></th>
                            <th>记录ID</th>
                            <th>统计方案标题</th>
                            <th>统计方案代码</th>
                            <th>模块名</th>
                            <th>是否启用</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <form id="bodyForm" action="deleteStatisticsCode.htm?CSRFToken=${token}&deleteStatus=${deleteStatus}" method="post">
                        <c:forEach items="${pageBean.list}" var="tempObject" varStatus="i">
                        <tr>
                            <td><input type="checkbox" name="staCodeId" id="staCodeId"  value="${tempObject.staCodeId }"></td>
                            <td>${tempObject.staCodeId }</td>
                            <td>${tempObject.staTitle }</td>
                            <td>${tempObject.staStyle }</td>
                            <td>${tempObject.module }</td>
                            <td>
                                <c:if test="${tempObject.usedStatus == 0 }">
								<span class="label label-default" style="cursor:pointer; "
                                      onclick="changeUserdStatus(${tempObject.staCodeId})">否</span>
                                </c:if>
                                <c:if test="${tempObject.usedStatus == 1 }">
								<span class="label label-success" style="cursor:pointer; "
                                      onclick="changeUserdStatus(${tempObject.staCodeId})">是</span>
                                </c:if>
                            </td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="updateButton('${tempObject.staCodeId }');">编辑</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:void(0);" onclick="deleteSingle(${tempObject.staCodeId });">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                        </form>
                        </tbody>
                    </table>

                    <c:import url="../../page/paging.jsp">
                        <c:param name="pageBean" value="${pageBean }"/>
                        <c:param name="path" value="../../"></c:param>
                    </c:import>

                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal 添加 -->
<div class="modal fade" id="addStatisticalCode"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加统计代码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addForm"  action="addStatisticsCode.htm?CSRFToken=${token}"  method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="staTitle" id="staTitleAdd">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>代码key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="staStyle" id="staStyleAdd">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>模块名：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="module" id="moduleAdd">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>统计代码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea rows="5" class="form-control required" name="code" id="codeAdd"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <c:if test="${statisticsCode.usedStatus == 0 }">checked="checked"</c:if>
                            <label class="radio-inline">
                                <input type="radio" value="0"  name="usedStatus" class="ui-widget-content ui-corner-all vm mr5"
                                <c:if test="${statisticsCode.usedStatus == 0 }">checked="checked"</c:if>
                                <c:if test="${empty statisticsCode.usedStatus}">checked="checked"</c:if>>否
                            </label>
                            <label class="radio-inline">
                                <input type="radio" value="1" name="usedStatus" class="ui-widget-content ui-corner-all vm mr5"
                                 <c:if test="${statisticsCode.usedStatus == 1 }">checked="checked"</c:if>>是
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal 编辑 -->
<div class="modal fade" id="addSpecialTopic"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑统计代码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateForm"  action="updateStatisticsCode.htm?CSRFToken=${token}"  method="post" enctype="multipart/form-data">
                    <input type="hidden" name="CSRFToken" value="${token}">
                    <input type="hidden" name="staCodeId" id="staCodeIdUpdate" value="${statisticsCode.staCodeId }"/>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="staTitle" id="staTitleUpdate" value="${statisticsCode.staTitle}"/><label class="staTitleUpdateTip" id="staTitleUpdateTip"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>代码key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="staStyle" id="staStyleUpdate" value="${statisticsCode.staStyle}"/><label class="staStyleUpdateTip" id="staStyleUpdateTip"></label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>模块名：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="module" id="moduleUpdate" value="${statisticsCode.module}"/><label class="moduleUpdateTip" id="moduleUpdateTip"></label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>统计代码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea rows="5" class="form-control required" name="code" id="code">${statisticsCode.code }</textarea>
                            <br/><label class="codeUpdateTip" id="codeUpdateTip"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline"><input type="radio" id="radioN"  value="0" <c:if test="${statisticsCode.usedStatus == 0 }">checked="true"</c:if> name="usedStatus" class="ui-widget-content ui-corner-all vm mr5"/>否</label>
                            <label class="radio-inline"><input type="radio" id="radioY"  value="1" <c:if test="${statisticsCode.usedStatus == 1 }">checked="true"</c:if> name="usedStatus" class="ui-widget-content ui-corner-all vm mr5"/>是</label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="update">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="CSRFToken" value="${token}">
<input type="hidden" id="deleteStatus" value="${deleteStatus}">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common.js"></script>
 <script src="<%=basePath %>js/system/statisticscode_list.js"></script>
 <script src="<%=basePath %>js/select2.min.js"></script>
<script>

</script>
</body>
</html>
