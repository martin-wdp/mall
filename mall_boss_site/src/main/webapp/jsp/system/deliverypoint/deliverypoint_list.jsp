<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/21
  Time: 17:13
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

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

                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows}条)</small></h2>

                <div class="common_data p20">

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="showAddPoint()">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlert('deleteDeliveryPointForm','dpid')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            <button type="button" class="btn btn-info" onclick="window.location.href='initExpressConf.htm'">
                                <i class="icon iconfont">&#xe614;</i> 返回上门自提设置
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form action="batchDelDeliveryPoint.htm" method="post" id="deleteDeliveryPointForm">
                        <input type="hidden" name="CSRFToken" value="${token}" />
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th width="50"><input type="checkbox" onclick="selectAll('dpid',this)"></th>

                                <th>自提点名称</th>
                                <th>联系人</th>
                                <th>联系电话</th>
                                <th>自提点地址</th>
                                <th>所属地区</th>
                                <th>是否启用</th>
                                <th width="200">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="dp" items="${pb.list }" varStatus="sta">
                            <tr>
                                <td width="50"><input type="checkbox" value="${dp.deliveryPointId }" name="dpid"></td>

                                <td>${dp.name}</td>
                                <td>${dp.linkman}</td>
                                <td>${dp.telephone}</td>
                                <td>${dp.address}</td>
                                <td>${dp.temp1}-${dp.temp2}-${dp.temp3}</td>
                                <td>
                                    <c:if test="${dp.isUserd=='0'}">
                                        <a href="javascript:;" title="点击启用" class="label label-default" onclick="changeUserdStatus(${dp.deliveryPointId })">否</a>
                                    </c:if>
                                    <c:if test="${dp.isUserd=='1'}">
                                        <a href="javascript:;" title="点击禁用" class="label label-success" onclick="changeUserdStatus(${dp.deliveryPointId })">是</a>
                                    </c:if>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" onclick="showEditDeliveryPointForm(${dp.deliveryPointId })">编辑</button>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:;" onclick="showAjaxDeleteConfirmAlert('deleteDeliveryPoint.htm?CSRFToken=${token}&deliveryPointId=${dp.deliveryPointId  }')">删除</a></li>
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
<div class="modal fade" id="addPickPoint"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加自提点</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" action="createDeliveryPoint.htm" id="addDeliveryPointForm">
                    <input type="hidden" id="CSRFToken" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="temp1" id="temp1" value="${deliveryPoint.temp1 }">
                    <input type="hidden" name="temp2" id="temp2" value="${deliveryPoint.temp2 }">
                    <input type="hidden" name="temp3" id="temp3" value="${deliveryPoint.temp3 }">
                    <input type="hidden" name="temp4" id="temp4" value="${deliveryPoint.temp4 }">
                    <input type="hidden" name="temp5" id="temp5" value="${deliveryPoint.temp5 }">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><b style="color: red;">*</b>自提点名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required specstr" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><b style="color: red;">*</b>自提点地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control w200 required" name="address" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><b style="color: red;">*</b>联系人：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control w200 required " name="linkman" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><b style="color: red;">*</b>联系电话：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control w200 required isPhone" name="telephone" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><b style="color: red;">*</b>选择地区：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-18">
                            <select class="inline" data-live-search="true" id="provinces" onchange="queryCityByProvinceId(this)">
                                <option>选择省份</option>
                                <option>江苏省</option>
                            </select>
                            <select class="inline" data-live-search="true" id="cities" onchange="queryDistrictByCityId(this)">
                                <option>选择城市</option>
                                <option>南京市</option>
                            </select>
                            <select class="inline" data-live-search="true" id="districts" name="districtId" onchange="selectDistrict(this)">
                                <option>选择区县</option>
                                <option>建邺区</option>
                            </select>
                            <label for="districts" generated="true" class="districts_valid"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <label class="radio-inline">
                                <input type="radio" name="isUserd" id="open3" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isUserd" id="open4" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitPointForm();">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editDeliveryPoint"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑自提点</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" action="updateDeliveryPoint.htm" id="editDeliveryPointForm" method="post">
                    <input type="hidden" name="temp1" id="editTemp1" value="${deliveryPoint.temp1 }">
                    <input type="hidden" name="temp2" id="editTemp2" value="${deliveryPoint.temp2 }">
                    <input type="hidden" name="temp3" id="editTemp3" value="${deliveryPoint.temp3 }">
                    <input type="hidden" name="temp4" id="editTemp4" value="${deliveryPoint.temp4 }">
                    <input type="hidden" name="temp5" id="editTemp5" value="${deliveryPoint.temp5 }">
                    <input type="hidden" id="deliveryPointId" name="deliveryPointId"/>
                    <input type="hidden" id="districtId" name="districtId"/>
                    <input type="hidden" name="CSRFToken" value="${token}">
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>自提点名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required specstr" name="name" id="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>自提点地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required" name="address" id="address">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>联系人：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required" name="linkman" id="linkman">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>联系电话：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200 required isPhone" name="telephone" id="telephone">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-7"><font color="red">*</font>选择地区：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-15">
                            <select class="inline" data-live-search="true" id="province" onchange="editQueryCityByProvinceId(this)">
                                <option>选择省份</option>
                            </select>
                            <select class="inline" data-live-search="true" id="city" onchange="editQueryDistrictByCityId(this)">
                                <option>选择城市</option>
                            </select>
                            <select class="inline required" data-live-search="true" id="district" onchange="selectDistrict(this)">
                                <option>选择区县</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <label class="radio-inline">
                                <input type="radio" name="isUserd" id="open5" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isUserd" id="open6" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditDeliveryPointForm()">保存</button>
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
<script src="<%=basePath%>js/system/deliverypoint.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/system/system_common.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
</body>
</html>

