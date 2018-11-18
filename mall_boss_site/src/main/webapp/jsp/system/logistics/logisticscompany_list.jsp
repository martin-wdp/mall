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
<jsp:include page="../../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                 <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>
                <h2 class="main_title">${pageNameChild}<small>(共${pageBean.rows }条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <input type="hidden" id="formId" value="searchCompanyForm"/>
                        <input type="hidden" id="formName" value="queryLogisticsCompanyNew.htm"/>
                        <form role="form" class="form-inline" action="queryLogisticsCompanyNew.htm" method="post" id="searchCompanyForm">
                            <input type="hidden" name="CSRFToken" value="${token}">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">物流公司名称</span>
                                    <input type="text" class="form-control specstr" name="companyName" value="${companyName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">物流公司代码</span>
                                    <input type="text" class="form-control specstr"  name="companyCode" value="${companyCode}">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="searchCompany()">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="$('#addCompany').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlert('deleteCompanyForm','companyIds')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            <button type="button" class="btn btn-info" onclick="location.reload();">
                                <i class="glyphicon glyphicon-refresh"></i> 刷新
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form action="deleteLogisticsCompanyBatch.htm" method="post" id="deleteCompanyForm">
                        <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken"/>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="50"><input type="checkbox" onclick="selectAll('companyIds',this)"></th>
                            <th width="100">记录ID</th>
                            <th>物流公司名称</th>
                            <th>物流公司代码</th>
                            <th>快递100物流公司代码</th>
                            <th>排序</th>
                            <th>是否启用</th>
                            <th width="200">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.list}" var="tempObject" varStatus="i">
                        <tr>
                            <td width="50"><input type="checkbox" name="companyIds"  value="${tempObject.logComId }"></td>
                            <td>${tempObject.logComId }</td>
                            <td>${tempObject.name }</td>
                            <td>${tempObject.code}</td>
                            <td>${tempObject.kuaidi100Code}</td>
                            <td>${tempObject.sort }</td>
                            <td>
                                <c:if test="${tempObject.usedStatus == 0 }">
                                    <a href="javascript:;" class="label label-default" title="点击开启" onclick="changeUserdStatus(${tempObject.logComId })">否</a>
                                </c:if>
                                <c:if test="${tempObject.usedStatus == 1 }">
                                    <a href="javascript:;" class="label label-success"  title="点击关闭" onclick="changeUserdStatus(${tempObject.logComId })">是</a>
                                </c:if>
                            </td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="showEditCompanyForm(${tempObject.logComId })">编辑</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick="showAjaxDeleteConfirmAlert('deleteLogisticsCompanyOne.htm?CSRFToken=${token}&companyId=${tempObject.logComId }')">删除</a></li>
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
<div class="modal fade" id="addCompany"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加物流公司</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" action="addLogisticsCompany.htm" id="addCompanyForm" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="CSRFToken" value="${token}">
                    <!--
                    <div class="form-group">
                        <label class="control-label col-sm-7">承运公司：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <select class="form-control w200" data-live-search="true">
                                <option>中国邮政EMS</option>
                                <option>申通快递</option>
                                <option>圆通速递</option>
                            </select>
                        </div>
                    </div>-->
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>物流公司名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required specstr" name="name" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>物流公司代码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required" name="code">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>快递100物流公司代码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control required" name="kuaidi100Code">
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10"><label class="control-label"><a href="ap.kuaidi100公司代码.doc">点击下载快递100物流公司代码文档</a></label></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">公司网址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control url" name="comUrl" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">询件网址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea class="form-control url" rows="5" name="queryUrl" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w50  integer" name="sort" id="addSort">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus"value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAddCompanyForm()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="editCompany"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑物流公司</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" action="updateLogisticsCompany.htm" id="editCompanyForm" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="logComId" name="logComId"/>
                    <input type="hidden" name="CSRFToken" value="${token}">
                    <!--
                    <div class="form-group">
                        <label class="control-label col-sm-7">承运公司：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <select class="form-control w200" data-live-search="true">
                                <option>中国邮政EMS</option>
                                <option>申通快递</option>
                                <option>圆通速递</option>
                            </select>
                        </div>
                    </div>-->
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>物流公司名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required specstr" name="name" id="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>物流公司代码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required" name="code" id="code">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>快递100物流公司代码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control required" name="kuaidi100Code" id="kuaidi100Code">
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10"><label class="control-label"><a href="ap.kuaidi100公司代码.doc">点击下载快递100物流公司代码文档</a></label></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">公司网址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control url" name="comUrl" id="comUrl">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">询件网址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea class="form-control url" rows="5" name="queryUrl" id="queryUrl"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w50 digits" name="sort" id="sort">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
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
                <button type="button" class="btn btn-primary" onclick="submitEditCompanyForm()">保存</button>
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
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath%>js/system/system_common.js"></script>
<script src="<%=basePath%>js/system/logisticscompany.js"></script>
</body>
</html>

