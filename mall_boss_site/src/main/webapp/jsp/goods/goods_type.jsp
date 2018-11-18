<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/4/3
  Time: 16:41
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
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
    <style type="text/css">
        .select2-search--inline input{
            width: 10em!important;
        }
    </style>
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

                <h2 class="main_title">${pageNameChild}<small>(共${pb.rows }条)<a  href="javascript:void(0);" onclick="$('#helpTypeTips').modal('show')" style="diaplay:block; float:right; padding-right: 12px">查看帮助<i class="icon iconfont">&#xe611;</i></a></small></small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <input type="hidden" id="formId" value="searchForm"/>
                        <input type="hidden" id="formName" value="findAllType.htm"/>
                        <form role="form" class="form-inline" id="searchForm" action="findAllType.htm" method="post">
                            <input type="hidden" name="CSRFToken" value="${token}"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">类型名称</span>
                                    <input type="text" class="form-control" name="searchText" value="${pb.objectBean.searchText}">
                                    <input type="hidden" name="condition" value="1">
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
                            <button type="button" class="btn btn-info" onclick="showAddType()">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlert('typeList','typeIds')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form action="batchDelType.htm?CSRFToken=${token }" method="post" id="typeList">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" onchange="allunchecked(this,'typeIds')"></th>
                            <th>类型名称</th>
                            <%--<th>是否实体商品</th>--%>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="type" items="${pb.list }" varStatus="sta">
                        <tr>
                            <td><input type="checkbox" name="typeIds" value="${type.typeId }"></td>
                            <td>${type.typeName }</td>
                            <%--<td>
                                <c:if test="${type.typeIsreal==0}">
                                    <span class="label label-default">否</span>
                                </c:if>
                                <c:if test="${type.typeIsreal==1}">
                                    <span class="label label-success">是</span>
                                </c:if>

                            </td>--%>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="showUpdateType(${type.typeId})">编辑</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick="showDeleteOneConfirmAlert('delTypeByTypeId.htm?CSRFToken=${token}&typeId=${type.typeId}')">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </form>

                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pb }"/>
                    </c:import>

                </div>

            </div>
        </div>
    </div>
</div>

<!-- 添加类型 -->
<div class="modal fade" id="addType"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加类型</h4>
            </div>
            <form action="saveType.htm?CSRFToken=${token }" enctype="multipart/form-data" method="post" id="saveTypeForm">
            <input type="hidden" id="CSRFToken" name="CSRFToken" value="${token}"/>
            <div class="modal-body">
                <div class="add_type1">
                    <div class="modal_form form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                <span class="text-danger">*</span>类型名称：
                            </label>
                            <div class="col-sm-12">
                                <input type="text" class="form-control required specstr" name="typeName" maxlength="20">
                            </div>
                            <div class="col-sm-5"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                类型别名：
                            </label>
                            <div class="col-sm-12">
                                <input type="text" class="form-control specstr" name="typeNickname" maxlength="30">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <%--<div class="form-group">
                            <label class="col-sm-7 control-label">
                                <span class="text-danger">*</span>实体商品：
                            </label>
                            <div class="col-sm-12">
                                <label class="radio-inline">
                                    <input type="radio" name="typeIsreal"  value="1" checked> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="typeIsreal" value="0"> 否
                                </label>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>--%>
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                关联品牌：
                            </label>
                            <div class="col-sm-12">
                                <select class="cate_selector w150" data-live-search="true" name="brandIds"
                                        id="relBrand" multiple>
                                </select>
                            </div>
                            <div class="col-sm-5"></div>
                        </div>
                    </div>
                    <div class="mt20 mb20 text-center">
                        <button type="button" class="btn btn-primary" onclick="toStep2()">下一步</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
                <div class="add_type2" style="display:none;">
                    <div class="alert alert-warning alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <strong>注意!</strong> 修改扩展属性会影响已经存在的商品的属性，请谨慎修改。
                    </div>
                      <div class="alert alert-warning alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <strong>京东列表地址：</strong>  <input type="text" class="form-control" id="jiazaiinput"><input type="button" class="btn btn-info" onclick="jiazaiClick();" value="加载"/>
                    </div>
                    <div class="ctrl_btns mb20">
                        <button type="button" class="btn btn-info" onclick="addOneExpand()">添加一个扩展属性</button>
                        <a role="button" class="extendtip help_tips" href="javascript:;">
                            <i class="icon iconfont">&#xe611;</i>
                        </a>
                    </div>
                    <table class="table" id="step2_table">
                        <thead>
                        <tr>
                            <th>属性名</th>
                            <th>属性别名</th>
                            <th class="w200">选择项可选值</th>
                            <th>显示</th>
                            <th>排序</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="expandTbody">
                        <tr id="expand_tr0">
                            <td>
                                <span class="text-danger">*</span>
                                <input type="text" name="expandnames" class="w100" maxlength="20">
                            </td>
                            <td>
                                <input type="text" name="expandnicknames" class="w100" maxlength="20">
                            </td>
                            <td style="text-align:left;">
                                <span class="text-danger">*</span>
                                <div class="tags w300"><input type="hidden" name="expandvalues"/></div>

                            </td>
                            <td>
                                <a href="javascript:;" onclick="changeIsShow(this)"><input type="hidden" name="expandparamisshow" value="1"> <span class="label label-success">是</span> </a>
                            </td>
                            <td>
                                <span class="text-danger">*</span>
                                <input type="text" name="expandparamsort" class="w50" >
                            </td>
                            <td>
                               <%-- <a href="javascript:;" onclick="removeExpand(0)">删除</a>--%>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="mt20 mb20 text-center">
                        <button type="button" class="btn btn-primary" onclick="backToStep1()">上一步</button>
                        <button type="button" class="btn btn-primary" onclick="toStep3()">下一步</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
                <div class="add_type3" style="display:none;">
                    <div class="alert alert-warning alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <strong>注意!</strong> 修改规格会影响已经存在的商品的规格，请谨慎修改。
                    </div>
                    <div class="ctrl_btns mb20">
                        <span class="text-danger">*</span>
                    选择规格：
                        <select class="cate_selector w150" data-live-search="true" id="specs" multiple
                                onchange="addSpec()">

                        </select>
                        <span class="text-danger" id="specsTips"></span>
                    </div>
                    <table class="table" id="step3_table">
                        <thead>
                        <tr>
                            <th>规格名称</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="mt20 mb20 text-center">
                        <button type="button" class="btn btn-primary" onclick="$('.add_type3').hide();$('.add_type2').show()">上一步</button>
                        <button type="button" class="btn btn-primary" onclick="toStep4();">下一步</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
                <div class="add_type4" style="display:none;">
                    <div class="alert alert-warning alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <strong>注意!</strong> 修改参数会影响已经存在的商品的参数，请谨慎修改。
                    </div>
                    
                       <div class="alert alert-warning alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <strong>京东相关详细页地址：</strong>  <input type="text" class="form-control" id="jiazaicanshu"><input type="button" class="btn btn-info" onclick="canshuClick();" value="加载"/>
                    </div>
                    <div class="ctrl_btns mb20">
                        <button type="button" class="btn btn-info" onclick="addParam()">添加一个参数</button>
                    </div>
                    <table class="table" id="step4_table">
                        <thead>
                        <tr>
                            <th>参数名</th>
                            <th>别名</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr id="param_tr0">
                            <td><span class="text-danger">*</span><input type="text" name="paramname" class="w200" maxlength="20"></td>
                            <td><input type="text" name="paramnickname" class="w200" maxlength="20"></td>
                            <td>
                                <a href="javascript:;" onclick="deleteParam(0)">删除</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="mt20 mb20 text-center">
                        <button type="button" class="btn btn-primary" onclick="$('.add_type4').hide();$('.add_type3').show()">上一步</button>
                        <button type="button" class="btn btn-primary" onclick="submitSaveTypeForm()">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>


<!-- 编辑类型 -->
<div class="modal fade" id="updateType"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑类型</h4>
            </div>
            <form action="updateType.htm?CSRFToken=${token }" enctype="multipart/form-data" method="post" id="updateTypeForm">
                <input type="hidden" name="CSRFToken" value="${token}"/>
                <input type="hidden" name="typeId" id="typeId" />
                <input type="hidden" name="searchText" id="searchText" />
                <div class="modal-body">
                    <div class="add_type1_update">
                        <div class="modal_form form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-7 control-label">
                                    <span class="text-danger">*</span>类型名称：
                                </label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control required specstr" name="typeName" minLength="2" id="typeName">
                                </div>
                                <div class="col-sm-5"></div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-7 control-label">
                                    类型别名：
                                </label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control specstr" name="typeNickname" id="typeNickname">
                                </div>
                                <div class="col-sm-5">
                                </div>
                            </div>
                            <%--<div class="form-group">
                                <label class="col-sm-7 control-label">
                                    <span class="text-danger">*</span>实体商品：
                                </label>
                                <div class="col-sm-12">
                                    <label class="radio-inline">
                                        <input type="radio" name="typeIsreal" id="inlineRadio1" value="1" checked> 是
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="typeIsreal" id="inlineRadio2" value="0"> 否
                                    </label>
                                </div>
                                <div class="col-sm-5">
                                </div>
                            </div>--%>
                            <div class="form-group">
                                <label class="col-sm-7 control-label">
                                    关联品牌：
                                </label>
                                <div class="col-sm-12">
                                    <select class="cate_selector w150" data-live-search="true" name="brandIds"
                                            id="relBrand_update" multiple>
                                    </select>
                                </div>
                                <div class="col-sm-5"></div>
                            </div>
                        </div>
                        <div class="mt20 mb20 text-center">
                            <button type="button" class="btn btn-primary" onclick="toUpdateStep2()">下一步</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                    <div class="add_type2_update" style="display:none;">
                        <div class="alert alert-warning alert-dismissible fade in" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <strong>注意!</strong> 修改扩展属性会影响已经存在的商品的属性，请谨慎修改。
                        </div>
                        <div class="ctrl_btns mb20">
                            <button type="button" class="btn btn-info" onclick="addOneUpdateExpand()">添加一个扩展属性</button>
                        </div>
                        <table class="table" id="step2_table_update">
                            <thead>
                            <tr>
                                <th>属性名</th>
                                <th>属性别名</th>
                                <th>选择项可选值</th>
                                <th>显示</th>
                                <th>排序</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr id="expand_update_tr0">
                                <td>
                                    <span class="text-danger">*</span>
                                    <input type="text" name="expandnames" class="w100">
                                </td>
                                <td>
                                    <span class="text-danger">*</span>
                                    <input type="text" name="expandnicknames" class="w100">
                                </td>
                                <td style="text-align:left">
                                    <span class="text-danger">*</span>
                                    <div class="tags w300"><input type="hidden" name="expandvalues"/></div>

                                </td>
                                <td>
                                    <a href="javascript:;" onclick="changeIsShow(this)"><input type="hidden" name="expandparamisshow" value="1"> <span class="label label-success">是</span> </a>
                                </td>
                                <td>
                                    <span class="text-danger">*</span>
                                    <input type="text" name="expandparamsort" class="w50" >
                                </td>
                                <td>
                                    <a href="javascript:;" onclick="removeUpdateExpand(0)">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="mt20 mb20 text-center">
                            <button type="button" class="btn btn-primary" onclick="backToUpdateStep1()">上一步</button>
                            <button type="button" class="btn btn-primary" onclick="toUpdateStep3()">下一步</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                    <div class="add_type3_update" style="display:none;">
                        <div class="alert alert-warning alert-dismissible fade in" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <strong>注意!</strong> 修改规格会影响已经存在的商品的规格，请谨慎修改。
                        </div>
                        <div class="ctrl_btns mb20">
                            <span class="text-danger">*</span>
                            选择规格：
                            <select class="cate_selector w150" data-live-search="true" id="specs_update" multiple
                                    onchange="addUpdateSpec()">

                            </select>
                            <span class="text-danger" id="specsTipsUpdate"></span>
                        </div>
                        <table class="table" id="step3_table_update">
                            <thead>
                            <tr>
                                <th>规格名称</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <div class="mt20 mb20 text-center">
                            <button type="button" class="btn btn-primary" onclick="$('.add_type3_update').hide();$('.add_type2_update').show()">上一步</button>
                            <button type="button" class="btn btn-primary" onclick="toUpdateStep4();">下一步</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                    <div class="add_type4_update" style="display:none;">
                        <div class="alert alert-warning alert-dismissible fade in" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <strong>注意!</strong> 修改参数会影响已经存在的商品的参数，请谨慎修改。
                        </div>
                        <div class="ctrl_btns mb20">
                            <button type="button" class="btn btn-info" onclick="addUpdateParam()">添加一个参数</button>
                        </div>
                        <table class="table" id="step4_table_update">
                            <thead>
                            <tr>
                                <th>参数名</th>
                                <th>别名</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr id="param_update_tr0">
                                <td><span class="text-danger">*</span><input type="text" name="paramname" class="w200"></td>
                                <td><input type="text" name="paramnickname" class="w200"></td>
                                <td>
                                    <a href="javascript:;" onclick="deleteUpdateParam(0)">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="mt20 mb20 text-center">
                            <button type="button" class="btn btn-primary" onclick="$('.add_type4_update').hide();$('.add_type3_update').show()">上一步</button>
                            <button type="button" class="btn btn-primary" onclick="submitUpdateTypeForm()">保存</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="helpTypeTips"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加商品类型</h4>
          </div>
          <div class="modal-body">
            <div class="modal-article">
              <p><em>1.</em>商品分类添加好后，针对已添加好的第三级分类设置第三级分类类型，如第三级分类是T恤，那么就要添加T恤类型。在商品类型中添加类型，如下</p>
              <img src="./images/syshelp/img_L01.png" alt="">
              <p><em>2.</em>点击下一步，添加扩展属性，扩展属性用于商品列表页的搜索属性，添加相应的属性名和属性值，并排序，添加完后点击</p>
              <img src="./images/syshelp/img_L02.png" alt="">  
			  <p><em>3.</em>添加相应的属性名和属性值，并排序，添加完后点击下一步</p>
              <img src="./images/syshelp/img_L03.png" alt="">  
			  <p><em>4.</em>这里对应商品类型选择添加相应的规格，选择完后点击下一步</p>
              <img src="./images/syshelp/img_L04.png" alt="">  
			  <p>这里的参数名为商品详情页的详细参数名，根据商品类型进行相应添加，最后点击保存，商品添加添加成功</p>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>
    
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/goods/goods_type_tab.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/common/common_checked.js"></script>
<script src="<%=basePath%>js/goods/goods_type.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script>
    $(function(){


        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 富文本编辑框 */
        $('.summernote').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN'
        });

        /* 选择规格值 */
        $('.spec_set input').change(function(){
            if($(this).is(':checked')){
                $(this).parent().parent().next().slideDown('fast');
            }
            else {
                $(this).parent().parent().next().slideUp('fast');
            }
        });

        /* 下面是表单里面的填写项提示相关的 */
        $('.guigemingchen').popover({
            content : '展示商品详情页规格名称',
            trigger : 'hover'
        });
        $('.guigebieming').popover({
            content : '用“|”分割',
            trigger : 'hover'
        });

        /* 双击编辑分类 */
        $('.cate_item').dblclick(function(){
            $('#cateEdit').modal('show');
        });

        /**************  下面是关键字标签选择  ********************/
        /**************  获取所有标签值的方法 var v = $("#tag").getTabVals(); **************/
        $('.tags').tabControl({maxTabCount:15/*最大标签数*/,tabW:80/*标签最大长度*/,tabH : 25},""/*预置标签值*/);

    });
    
    
    function jiazaiClick(){
    	 $.ajax({
				url: "jdcatelist.htm", 
				context: document.body, 
				 type: 'POST',
				 data:{httpUrl:$("#jiazaiinput").val()},
				success: function(data){
					
					if(data!=""&&data!=null){
						$("#expandTbody").html('');
						for(var i=0;i<data.length;i++){
							
							var htm = '';
							
							htm+='<tr id="expand_tr'+(i+999)+'">';
							htm+='<td>';
								htm+='<span class="text-danger">*</span>';
								htm+='<input type="text" name="expandnames" class="w100" value="'+data[i].outKey+'">';
							htm+='</td>';
							htm+='<td>';
								htm+='  <input type="text" name="expandnicknames" class="w100"  value="'+data[i].outKey+'">';
							htm+=' </td>';
							htm+='  <td>';
								htm+=' <span class="text-danger">*</span>';
                                htm+='    <div class="tags w300" id="tagsss_'+i+'">';
                                var expandValue = '';
                                for (var k = 0; k < data[i].values.length; k++) {
                                	htm +=
                                        '<input class="tabinput valid tabinput_defautl" name="tabinput" style="width: 80px; height: 25px; display: none;" type="text" value="'+data[i].values[k].outValue+'">' +
                                        '<span name="tab" id="radius">' +
                                        '   <input type="hidden" name="expandParamId" value=""/>' +
                                        '   <input type="hidden" name="expandParamValueDelFlag" value="0"/>' +
                                        '   <input type="hidden" name="expandParamValueId" value="-1"/> ' +
                                        '   <input type="hidden" name="expandvaluesa" value="'+data[i].values[k].outValue+'"/> '+
                                        '   <input type="hidden" name="expandvaluesort" value=""/> '+
                                        '   <b>'+ data[i].values[k].outValue +'</b>' +
                                        '<a id="deltab">×</a></span>';
                                	  expandValue = expandValue + data[i].values[k].outValue + "|";
                                }
                                
                                htm+='<input type="hidden" name="expandvalues" value="'+expandValue.substring(0,(expandValue.length-1))+'"></div>';

                           	htm+='  </td>';
                          	htm+=' <td>';
                             	htm+='    <a href="javascript:;" onclick="changeIsShow(this)"><input type="hidden" name="expandparamisshow" value="1"> <span class="label label-success">是</span> </a>';
                              htm+=' </td>';
                               	htm+='<td>';
                              	htm+='   <span class="text-danger">*</span>';
                                		htm+=' <input type="text" name="expandparamsort" class="w50"  value="'+(i+1)+'">';
                                htm+='</td>';
                                htm+=' <td>';
                                		htm+='  <a href="javascript:;" onclick="removeExpand('+(i+999)+')">删除</a>';  
                               htm+=' </td>';
                          htm+=' </tr>';
                          $("#expandTbody").append(htm);
                          
                          $('#tagsss_'+i).tabControl({maxTabCount:15/*最大标签数*/,tabW:80/*标签最大长度*/,tabH : 25},""/*预置标签值*/);
						}
						
					}
					
					
				}
    	 });
    }
    
    function canshuClick(){
    	 $.ajax({
				url: "jdtriggerlist.htm", 
				context: document.body, 
				 type: 'POST',
				 data:{httpUrl:$("#jiazaicanshu").val()},
				success: function(data){
						if(data!=""&&data!=null){
							$("#step4_table tbody").html('');
							for(var i=0;i<data.length;i++){
								var htm = '';
								 htm += '<tr id="param_tr'+i+'">';
								 htm +='<td><span class="text-danger">*</span><input type="text" name="paramname" value="'+data[i].outParam+'" class="w200 valid"></td>';
								 htm +='<td><input type="text" name="paramnickname" class="w200 valid" value="'+data[i].outParam+'"></td>';
								 htm +='<td> <a href="javascript:;" onclick="deleteParam('+i+')">删除</a>  </td>';
								 htm +='</tr>';
								 $("#step4_table tbody").append(htm);
							}
							
						}
						
					}
					
				
 	 });
 }
</script>
</body>
</html>
