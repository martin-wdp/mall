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
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
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
  
                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows}条) <a  href="javascript:void(0);" onclick="$('#helpGuige').modal('show')" style="diaplay:block; float:right; padding-right: 12px">查看帮助<i class="icon iconfont">&#xe611;</i></a></small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <input type="hidden" id="formId" value="searchForm"/> 
                        <input type="hidden" id="formName" value="findAllSpec.htm"/>
                        <form role="form" class="form-inline" id="searchForm" action="findAllSpec.htm">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">规格名称</span>
                                    <input type="text" class="form-control" name="searchText" value="${selectBean.searchText}">
                                    <input type="hidden" name="condition" value="1"/>
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
                            <button type="button" class="btn btn-info" onclick="$('#addSpecification').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlert('batchDeleteSpecForm','specIds')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="batchDeleteSpecForm" action="batchDelSpec.htm" method="post">
                        <input type="hidden" name="CSRFToken" value="${token}"/>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" onclick="allunchecked(this,'specIds')"></th>
                            <th>规格名称</th>
                            <th>规格备注</th>
                         <!--    <th>规格类型</th>
                            <th>显示方式</th> -->
                            <th width="200">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pb.list }" var="spec" varStatus="sta">
                        <tr>
                            <td><input type="checkbox" name="specIds" value="${spec.specId }"></td>
                            <td>${spec.specName }</td>
                            <td>${spec.specRemark }</td>
                          <%--   <td>
                                <c:if test="${spec.specShowtype==0}">文字 </c:if>
                                <c:if test="${spec.specShowtype==1}">图片</c:if>
                            </td> --%>
                         <%--    <td>
                                <c:if test="${spec.specShowmode==0}">
                                下拉 </c:if>
                                <c:if test="${spec.specShowmode==1}">
                                    平铺 </c:if>
                            </td> --%>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="querySpecDetail(${spec.specId})">管理规格值</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick='showEditSpec(${spec.specId})'>编辑</a></li>
                                        <li><a href="javascript:;" onclick="showDeleteOneConfirmAlert('delSpec.htm?CSRFToken=${token}&specId=${spec.specId}')">删除</a></li>
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

<!-- Modal -->
<div class="modal fade" id="addSpecification"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加规格</h4>
            </div>
            <form role="form" class="form-horizontal" action="saveSpecNew.htm?CSRFToken=${token}" enctype="multipart/form-data" method="post" id="saveSpecForm">
            <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken"/>
            <input type="hidden" name="specShowtype" value="0"/>
            <div class="modal-body">
                <div class="modal_form">
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                <span class="text-danger">*</span>规格名称：
                            </label>
                            <div class="col-sm-12">
                                <input type="text" class="form-control required specstr" name="specName" maxlength="4" minlength="2">
                            </div>
                            <div class="col-sm-5">
                                <a role="button" class="guigemingchen help_tips" href="javascript:;">
                                    <i class="icon iconfont">&#xe611;</i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                <span class="text-danger">*</span>规格备注：
                            </label>
                            <div class="col-sm-12">
                                <input type="text" class="form-control required specstr" name="specRemark" maxlength="16" minlength="2">
                            </div>
                            <div class="col-sm-5">
                                <a role="button" class="guigebeizhu help_tips" href="javascript:;">
                                    <i class="icon iconfont">&#xe611;</i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                规格别名：
                            </label>
                            <div class="col-sm-12">
                                <input type="text" class="form-control" name="specNickname">
                            </div>
                            <div class="col-sm-5">

                            </div>
                             <input type="hidden" name="specShowmode" value="1">
                        </div>
                        <!-- <div class="form-group">
                            <label class="col-sm-7 control-label">
                                <span class="text-danger">*</span>显示方式：
                            </label>
                            <div class="col-sm-12">
                                <label class="radio-inline">
                                    <input type="radio" name="specShowmode" value="1" checked> 平铺
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="specShowmode" value="0"> 下拉
                                </label>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div> -->
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="saveSpecForm()" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="editSpecification"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑规格</h4>
            </div>
            <form role="form" class="form-horizontal" action="updateSpecNew.htm?CSRFToken=${token }" method="post" id="updateSpecForm">
                <input type="hidden" name="specId" id="update_specId"/>
                <input type="hidden" name="specShowtype" value="0"/>
                <div class="modal-body">
                    <div class="modal_form">
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                <span class="text-danger">*</span>规格名称：
                            </label>
                            <div class="col-sm-12">
                                <input type="text" class="form-control required specstr" name="specName" id="update_spec_name" maxlength="4">
                            </div>
                            <div class="col-sm-5">
                                <a role="button" class="guigemingchen help_tips" href="javascript:;">
                                    <i class="icon iconfont">&#xe611;</i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                <span class="text-danger">*</span>规格备注：
                            </label>
                            <div class="col-sm-12">
                                <input type="text" class="form-control required specstr" name="specRemark" id="update_remark">
                            </div>
                            <div class="col-sm-5">
                                <a role="button" class="guigebeizhu help_tips" href="javascript:;">
                                    <i class="icon iconfont">&#xe611;</i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-7 control-label">
                                规格别名：
                            </label>
                            <div class="col-sm-12">
                                <input type="text" class="form-control" name="specNickname" id="update_nickname">
                            </div>
                            <div class="col-sm-5">

                            </div>
                            <input type="hidden" name="specShowmode" value="1">
                        </div>
                        <!-- <div class="form-group">
                            <label class="col-sm-7 control-label">
                                <span class="text-danger">*</span>显示方式：
                            </label>
                            <div class="col-sm-12">
                                <label class="radio-inline">
                                    <input type="radio" name="specShowmode" id="specShowmode1" value="1" checked>平铺
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="specShowmode" id="specShowmode0" value="0"> 下拉
                                </label>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div> -->
                    </div>
                </div>

            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="bcspec();">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="manageSpecification"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">管理规格值</h4>
            </div>
            <form id="updateSpecDetailForm" action="updateSpecDetail.htm?CSRFToken=${token }" method="post" enctype="multipart/form-data">
                <input type="hidden" name="specId" id="specId" />
                <div class="modal-body">
                <p>
                    <button type="button" class="btn btn-info" onclick="addOneSpecDetail()">
                        <span class="glyphicon glyphicon-plus-sign"></span> 添加规格值
                    </button>
                    <button type="button" class="btn btn-info" onclick="batchDeleteSpecDetail()">
                        <span class="glyphicon glyphicon-remove-sign"></span> 删除全部
                    </button>
                </p>

                <table class="table table-striped table-hover" id="spec_detail_table">
                    <thead>
                    <tr>
                        <th width="10"><input type="checkbox" onclick="allunchecked(this,'specDetail')" name="specDetailId"></th>
                        <th>规格值名称</th>
                        <th width="150">排序</th>
                        <th width="100">操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>

            </div>
            </form>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveSpec();">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


   <div class="modal fade" id="helpGuige"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加规格</h4>
          </div>
          <div class="modal-body">
            <div class="modal-article">
              <p><em>1.</em>添加商品规格，点击“添加规格”</p>
              <img src="./images/syshelp/img_g01.png" alt="">
              <p><em>2.</em>点击保存，规格添加成功，然后管理规格值，点击“管理规格值”</p>
              <img src="./images/syshelp/img_g02.png" alt="">
              <p>保存成功后，规格添加完成</p>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
<script src="<%=basePath%>js/common/common_checked.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script src="<%=basePath%>js/goods/goods_spec.js"></script>
<script>
    var num=0;
    /**
    *添加规格
     */
    function saveSpecForm(){
        if($("#saveSpecForm").valid()&&num==0){
            num+=1;
            $("#saveSpecForm").submit();
        }
    }

    function saveSpec(){
        if($("#updateSpecDetailForm").valid()&&num==0){
            if(!$("#spec_detail_table").find('tbody').html()==""){
                num+=1;
                $("#updateSpecDetailForm").submit();
            }else{
                showTipAlert("至少要有一个规格值存在！");
            }

        }
    }
    /**
     * 修改规格值提交按钮事件
     * */
    function bcspec(){
        if($("#updateSpecForm").valid()){
            $("#updateSpecForm").submit();
        }
    }
</script>
</body>
</html>
