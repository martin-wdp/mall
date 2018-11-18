<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/4/13
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link href="iconfont/iconfont.css" rel="stylesheet">
    <link href="css/summernote.css" rel="stylesheet">
    <link href="css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .btn{padding:6px 4px;}

    </style>
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


                <div class="cate_set container-fluid mt20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        地区修改和删除可能会对仓库产生影响，如修改或删除，请及时修改仓库管理中的分仓地区。
                    </div>

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick=" $('#setdefault').modal('show')">
                                <i class="glyphicon glyphicon"></i> &nbsp;&nbsp;&nbsp;设为默认地址&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                            <span>(默认地区：${defaultProvince.provinceName}-${defaultCity.cityName}-${defaultDistrict.districtName})</span>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <div class="row">
                        <input type="hidden" value="${token}"/>
                        <div class="col-xs-6 cate_set_column">
                            <div class="cate_set_item">
                                <%--<div class="cate_set_ctrl">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showAddProvince()"><span class="glyphicon glyphicon-plus-sign"></span> 新增省份</button>
                                        </div>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-11">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showEditProvince()"><span class="glyphicon glyphicon-edit"></span> 修改省份</button>
                                        </div>
                                    </div>
                                </div>--%>
                                <div class="cate_set_cont">
                                    <div class="cate_search">
                                        <input type="hidden" id="provinceId"/>
                                        <input type="text" id="provinceName" class="cate_search_box" placeholder="输入名称查找">
                                        <a href="javascript:;" onclick="findProvince()"><span class="glyphicon glyphicon-search"></span></a>
                                    </div>
                                    <div class="cate_set_list" id="province_list">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-6 cate_set_column">
                            <div class="cate_set_item">
                                <%--<div class="cate_set_ctrl">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showAddCity()"><span class="glyphicon glyphicon-plus-sign"></span> 新增城市</button>
                                        </div>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-11">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showEditCity()" id="modifyCityBtn"><span class="glyphicon glyphicon-edit"></span> 修改城市</button>
                                        </div>
                                    </div>
                                </div>--%>
                                <div class="cate_set_cont">
                                    <div class="cate_search">
                                        <input type="hidden" id="cityId"/>
                                        <input type="text" id="cityName" class="cate_search_box" placeholder="输入名称查找">
                                        <a href="javascript:;" onclick="findCity()"><span class="glyphicon glyphicon-search"></span></a>
                                    </div>
                                    <div class="cate_set_list" id="city_list">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-6 cate_set_column">
                            <div class="cate_set_item">
                                <%--<div class="cate_set_ctrl">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showAddCounty()"><span class="glyphicon glyphicon-plus-sign"></span> 新增县区</button>
                                        </div>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-11">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showEditDistrict()" id="modifyDistrictBtn"><span class="glyphicon glyphicon-edit"></span> 修改县区</button>
                                        </div>
                                    </div>
                                </div>--%>
                                <div class="cate_set_cont">
                                    <div class="cate_search">
                                        <input type="hidden" id="districtId"/>
                                        <input type="text" id="districtName" class="cate_search_box" placeholder="输入名称查找">
                                        <a href="javascript:;" onclick="findDistrict()"><span class="glyphicon glyphicon-search"></span></a>
                                    </div>
                                    <div class="cate_set_list" id="district_list">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <%--街道用不到 暂时隐藏掉--%>
                        <div class="col-xs-6 cate_set_column" style="display: none">
                            <div class="cate_set_item">
                                <%--<div class="cate_set_ctrl">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showAddStreet()"><span class="glyphicon glyphicon-plus-sign"></span> 新增街道</button>
                                        </div>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-11">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showEditStreet()" id="modifyStreetBtn"><span class="glyphicon glyphicon-edit"></span> 修改街道</button>
                                        </div>
                                    </div>
                                </div>--%>
                                <div class="cate_set_cont">
                                    <div class="cate_search">
                                        <input type="hidden" id="streetId"/>
                                        <input type="text" id="streetName" class="cate_search_box" placeholder="输入名称查找">
                                        <a href="javascript:;" onclick="findStreet()"><span class="glyphicon glyphicon-search"></span></a>
                                    </div>
                                    <div class="cate_set_list" id="street_list">

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
                <button type="button" class="btn btn-primary" onclick="submitAreaAddForm('saveProvinceForm',this)">保存</button>
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
                                <select class="required w150" data-live-search="true" id="provinces_city" name="parentId">

                                </select>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAreaAddForm('saveCityForm',this)">保存</button>
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
                                <select class=" w150" data-live-search="true" onChange="queryCityByProvinceId(this,'cities_county');" id="provinces_county">
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
                                <select class="required  w150"  data-live-search="true" id="cities_county" name="parentId">
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
                <button type="button" class="btn btn-primary" onclick="submitAreaAddForm('saveDistrictForm',this)">保存</button>
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
                                <select class=" w150" data-live-search="true" id="provinces_street" onChange="queryCityByProvinceId(this,'cities_street');">
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
                                <select class=" w150" data-live-search="true" id="cities_street" onChange='queryDistrictByCityId(this);'>
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
                                <select class="required  w150" data-live-search="true"  id="districts" name="parentId">
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
                <button type="button" class="btn btn-primary" onclick="submitAreaAddForm('saveStreetForm',this)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 设为默认地址Modal -->
<div class="modal fade" id="setdefault" role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">


            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                你确定要把选中的地址设为默认地址吗?
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" onclick="batdefault()"> &nbsp;&nbsp;&nbsp;确定 &nbsp;&nbsp;&nbsp;</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">&nbsp;&nbsp;&nbsp;取消 &nbsp;&nbsp;&nbsp;</button>
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
                        <input type="hidden" id="parentLocation"/>
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
                            <div class="col-sm-13" style="line-height:34px;">
                                <label id="parentAddress"></label>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitAreaAddForm('updateAddressForm',this)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="deleteLocation">


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/system/addressNew.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
<script>
    $(function(){

        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 下面是表单里面的填写项提示相关的 */
        $('.xiaoshoujia').popover({
            content : '此价格只用于显示，以商品定价为商品销售价',
            trigger : 'hover'
        });

        /* 双击编辑分类 */
        $('.cate_item').dblclick(function(){
            $('#cateEdit').modal('show');
        });
        $('.cate_item').click(function(){
            $(this).parent().find("div.cate_item").each(function () {
                $(this).removeClass("active");
            });
            $(this).addClass("active");
        });

    });

    /*设为默认*/
    function batdefault() {
        var curDistrictId = $("#districtId").val();
        var streetId = $("#streetId").val();
        $.ajax({
            url: "setthedefault.htm?CSRFToken=${token}&districtId=" + curDistrictId,
            async: false,
            success: function (data) {
                $('#setdefault').modal('hide');
                location.reload();
            }


        });


    }
</script>
</body>
</html>

