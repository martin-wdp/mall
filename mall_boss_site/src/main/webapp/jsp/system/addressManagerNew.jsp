<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/24
  Time: 11:19
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
    <title>地区管理</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
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

                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_data p20">

                    <div class="filter_area">
                        <form role="form" class="form-inline" action="findAllProvinceNew.htm">
                            <input type="hidden" name="CSRFToken" value="${token}"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">地区名称</span>
                                    <input type="text" class="form-control" name="searchText">
                                </div>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">搜索</button>
                            </div>
                        </form>
                    </div>

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onClick="showAddProvince();">
                                <span class="glyphicon glyphicon-plus-sign"></span> 添加省份
                            </button>
                            <button type="button" class="btn btn-info" onClick="showAddCity()">
                                <span class="glyphicon glyphicon-plus-sign"></span> 添加城市
                            </button>
                            <button type="button" class="btn btn-info" onClick="showAddCounty()">
                                <span class="glyphicon glyphicon-plus-sign"></span> 添加区县
                            </button>
                            <button type="button" class="btn btn-info" onClick="showAddStreet()">
                                <span class="glyphicon glyphicon-plus-sign"></span> 添加街道
                            </button>
                            <button type="button" class="btn btn-info" onclick="batchDeleteArea()">
                                <span class="glyphicon glyphicon-remove-sign"></span> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <!-- 这是另一种表格，带伸缩，不带分页 -->
                    <table class="treetable table table-striped table-hover" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th width="50">序号</th>
                            <th width="20"></th>
                            <th>地区名称</th>
                            <th>排序</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody id="treetable">
                        <c:set var="index" value="0"/>
                        <c:set var="parent_index" value="0"/>
                    <c:forEach items="${pb.list}" var="province" varStatus="sta">
                        <c:set var="index" value="${index+1}"/>
                        <tr parent_index="0" class="tr">
                            <td>${index}</td>
                            <td><input type="checkbox" name="areaId" value="${province.provinceId}" area_type="1"></td>
                            <td> ${province.provinceName}</td>
                            <td>${province.provinceSort }</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="updateAddress('${province.provinceId}','1')">编辑</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick="delAddress('${province.provinceId}','1')">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        <c:set var="parent_index" value="${index}"/>
                        <c:forEach items="${province.cityvos}" var="city" varStatus="staCity">
                            <c:set var="index" value="${index+1}"/>
                            <tr parent_index="${parent_index}" class="tr">
                                <td>${index}</td>
                                <td><input type="checkbox" name="areaId" value="${city.cityId}" area_type="2"></td>
                                <td> ${city.cityName}</td>
                                <td>${city.citySort }</td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" onclick="updateAddress('${city.cityId}','2')">编辑</button>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:;" onclick="delAddress('${city.cityId}','2')">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>


                            <c:set var="city_parent_index" value="${index}"/>
                            <c:forEach items="${city.districtvos}" var="district">
                                <c:set var="index" value="${index+1}"/>
                                <tr parent_index="${city_parent_index}" class="tr">
                                    <td>${index}</td>
                                    <td><input type="checkbox" name="areaId" value="${district.districtId}" area_type="3"></td>
                                    <td> ${district.districtName}</td>
                                    <td>${district.districtSort }</td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="updateAddress('${district.districtId}','3')">编辑</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="javascript:;" onclick="delAddress('${district.districtId}','3')">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>

                                <c:set var="street_parent_index" value="${index}"/>
                                <c:forEach items="${district.streets}" var="street">
                                    <c:set var="index" value="${index+1}"/>
                                    <tr parent_index="${street_parent_index}" class="tr">
                                        <td>${index}</td>
                                        <td><input type="checkbox" name="areaId" value="${street.streetId}" area_type="4"></td>
                                        <td> ${street.streetName}</td>
                                        <td>${street.streetSort }</td>
                                        <td>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default" onclick="updateAddress('${street.streetId}','4')">编辑</button>
                                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="javascript:;" onclick="delAddress('${street.streetId}','4')">删除</a></li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </c:forEach>
                    </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addProvince"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加省份</h4>
            </div>
            <div class="modal-body">
                <div class="modal_form">
                    <form role="form" class="form-horizontal" method="post" action="saveProvince.htm" id="saveProvinceForm">
                        <input type="hidden" name="CSRFToken" value="${token}"/>
                        <input type="hidden" name="isNew" value="1"/><!--新后台标记-->
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>省份名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required specstr" name="addressName">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>排序：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control w100 required number" name="addressSort">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAreaAddForm('saveProvinceForm')">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addCity"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加城市</h4>
            </div>
            <div class="modal-body">
                <div class="modal_form">
                    <form role="form" class="form-horizontal" action="saveCity.htm" method="post" id="saveCityForm">
                        <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
                        <input type="hidden" name="isNew" value="1"/><!--新后台标记-->
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>城市名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required specstr" name="addressName">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>排序：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control w100 required number" name="addressSort">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>所属省份：
                            </label>
                            <div class="col-sm-13">
                                <select class="form-control required" data-live-search="true" id="provinces_city" name="parentId">

                                </select>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAreaAddForm('saveCityForm')">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addCounty"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加区县</h4>
            </div>
            <div class="modal-body">
                <div class="modal_form">
                    <form role="form" class="form-horizontal" action="saveDistrict.htm" id="saveDistrictForm" method="post">
                        <input type="hidden" name="CSRFToken" value="${token}">
                        <input type="hidden" name="isNew" value="1"/><!--新后台标记-->
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>区县名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required specstr" name="addressName">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>排序：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control w100 required number" name="addressSort">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>所属省份：
                            </label>
                            <div class="col-sm-13">
                                <select class="form-control" data-live-search="true" onChange="queryCityByProvinceId(this,'cities_county');" id="provinces_county">
                                    <option>--请选择--</option>
                                </select>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>所属城市：
                            </label>
                            <div class="col-sm-13">
                                <select class="form-control required" data-live-search="true" id="cities_county" name="parentId">
                                    <option value="">--请选择--</option>
                                </select>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAreaAddForm('saveDistrictForm')">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addStreet"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加街道</h4>
            </div>
            <div class="modal-body">
                <div class="modal_form">
                    <form role="form" class="form-horizontal" method="post" id="saveStreetForm" action="saveStreet.htm">
                        <input type="hidden" name="isNew" value="1"/><!--新后台标记-->
                        <input type="hidden" name="CSRFToken" value="${token}">
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>街道名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required specstr" name="addressName">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>排序：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control w100 required number" name="addressSort">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>所属省份：
                            </label>
                            <div class="col-sm-13">
                                <select class="form-control" data-live-search="true" id="provinces_street" onChange="queryCityByProvinceId(this,'cities_street');">
                                    <option>--请选择--</option>
                                </select>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>所属城市：
                            </label>
                            <div class="col-sm-13">
                                <select class="form-control" data-live-search="true" id="cities_street" onChange='queryDistrictByCityId(this);'>
                                    <option>--请选择--</option>
                                </select>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>所属区县：
                            </label>
                            <div class="col-sm-13">
                                <select class="form-control required" data-live-search="true"  id="districts" name="parentId">
                                    <option value="">--请选择--</option>
                                </select>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAreaAddForm('saveStreetForm')">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- 修改地区弹框 -->
<div class="modal fade" id="editAddressModal"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改地区</h4>
            </div>
            <div class="modal-body">
                <div class="modal_form">
                    <form role="form" class="form-horizontal" method="post" action="" id="updateAddressForm">
                        <input type="hidden" name="CSRFToken" value="${token}"/>
                        <input type="hidden" id="updateAddressId" name="addressId"/>
                        <input type="hidden" name="isNew" value="1"/><!--新后台标记-->
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>地区名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required specstr"  name="addressName" id="update_address_name">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>排序：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control w100 required number" name="addressSort" id="update_addressSort">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                所属：
                            </label>
                            <div class="col-sm-13">
                                <span id="parentAddress"></span>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAreaAddForm('updateAddressForm')">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/jqtreetable.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/system/addressmanager.js"></script>
</body>
</html>

