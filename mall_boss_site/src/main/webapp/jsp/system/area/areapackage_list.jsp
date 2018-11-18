<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/24
  Time: 10:05
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

                <div class="common_data p20">
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="$('#addArea').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlert('deleteAreaPackageForm','areaPackageIds')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            <button type="button" class="btn btn-info" onclick="location.reload();">
                                <i class="glyphicon glyphicon-refresh"></i> 刷新
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form action="deleteAreaPackageBatch.htm" id="deleteAreaPackageForm" method="post">
                        <input type="hidden" name="CSRFToken" value="${token}" />
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th width="50"><input type="checkbox" onclick="selectAll('areaPackageIds',this)"></th>
                                <th>记录ID</th>
                                <th>地区包</th>
                                <th>地区设置</th>
                                <th>是否默认</th>
                                <th>是否启用</th>
                                <th width="150">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageBean.list}" var="tempObject" varStatus="i">
                            <tr>
                                <td><input type="checkbox" name="areaPackageIds"  value="${tempObject.areaPackageId }"></td>
                                <td>${tempObject.areaPackageId }</td>
                                <td>${tempObject.areaPackage }</td>
                                <td>${tempObject.area }</td>
                                <td>
                                    <c:if test="${tempObject.defaultPackage == 0 }">
                                        <a href="javascript:;" class="label label-default">否</a>
                                    </c:if>
                                    <c:if test="${tempObject.defaultPackage == 1 }">
                                        <a href="javascript:;" class="label label-success">是</a>
                                    </c:if>

                                </td>
                                <td>
                                    <c:if test="${tempObject.usedStatus == 0 }">
                                        <a href="javascript:;" class="label label-default">否</a>
                                    </c:if>
                                    <c:if test="${tempObject.usedStatus == 1 }">
                                        <a href="javascript:;" class="label label-success">是</a>
                                    </c:if>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" onclick="showEditAreaPackageForm(${tempObject.areaPackageId })">编辑</button>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:;" onclick="showDeleteOneConfirmAlert('deleteAreaPackageOne.htm?CSRFToken=${token}&areaPackageId=${tempObject.areaPackageId }')">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                            </tbody>
                        </table>
                    </form>

                    <c:import url="../../page/paging.jsp">
                        <c:param name="pageBean" value="${pageBean }"/>
                        <c:param name="path" value="../../"></c:param>
                    </c:import>

                </div>

            </div>

        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addArea"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加地区设置</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="addAreaPackageForm"  action="addAreaPackage.htm"  method="post">
                    <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken"/>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>地区包：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="areaPackage" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>地区设置：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="area">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>是否默认：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="defaultPackage" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="defaultPackage" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>是否开启：</label>
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
                    <div class="form-group">
                        <label class="control-label col-sm-5">描述信息：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <textarea class="form-control specstr" rows="5" name="des"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAreaPackageAddForm();">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 编辑弹框 -->
<div class="modal fade" id="editArea"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑地区设置</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="editAreaPackageForm"  action="updateAreaPackage.htm"  method="post">
                    <input type="hidden" name="areaPackageId" id="areaPackageId"/>
                    <input type="hidden" name="CSRFToken" value="${token}" />
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>地区包：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="areaPackage" id="areaPackage"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>地区设置：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="area" id="area">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>是否默认：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="defaultPackage" id="option1" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="defaultPackage" id="option2" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>是否开启：</label>
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
                    <div class="form-group">
                        <label class="control-label col-sm-5">描述信息：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <textarea class="form-control specstr" rows="5" name="des" id="des"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAreaPackageEditForm();">保存</button>
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
<script src="<%=basePath%>js/system/adress.js"></script>
</body>
</html>

