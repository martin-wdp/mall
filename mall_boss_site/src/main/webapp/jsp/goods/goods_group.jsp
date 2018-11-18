<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/31
  Time: 10:12
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
    <title>商品组合</title>

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

                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows}条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <input type="hidden" id="formId" value="searchForm"/>
                        <input type="hidden" id="formName" value="findAllGroup.htm"/>
                        <form role="form" class="form-inline" id="searcForm" action="findAllGroup.htm">
                            <input type="hidden" name="CSRFToken" value="${token}"/>
                            <input type="hidden" name="condition" value="1"/><!--这个参数是为了调用老后台的方法-->
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">组合名称</span>
                                    <input type="text" class="form-control" name="searchText" value="${pb.objectBean.searchText}">
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
                            <button type="button" class="btn btn-info" onclick="$('#addCombination').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlert('deleGroupForm','groupIds')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="deleGroupForm" action="batchDelGroup.htm?CSRFToken=${token }" method="post">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" onclick="selectAll('groupIds',this)"></th>
                            <th>组合名称</th>
                            <th>优惠类型</th>
                            <th>优惠额度</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pb.list }" var="group" varStatus="sta">
                        <tr>
                            <td><input type="checkbox" name="groupIds" value="${group.groupId }"></td>
                            <td> ${group.groupName }</td>
                            <td><c:if test="${group.groupPrefertype==0 }">特惠套装</c:if>
                                <c:if test="${group.groupPrefertype==1 }">人气组合 </c:if></td>
                            <td>${group.groupPreferamount }</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="location.href='queryGroupByPrimaryKey.htm?CSRFToken=${token }&groupId=${group.groupId}';">管理货品</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick="updateGroup('${group.groupId}');">编辑</a></li>
                                        <li><a href="javascript:;" onclick="showDeleteOneConfirmAlert('delGroup.htm?CSRFToken=${token}&groupId=${group.groupId}');">删除</a></li>
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
<div class="modal fade" id="addCombination"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加商品组合</h4>
            </div>
            <form role="form" class="form-horizontal" id="saveGroup" action="saveGroup.htm" method="post">
            <div class="modal-body">
                <div class="modal_form">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <div class="form-group">
                        <label class="col-sm-6 control-label">
                            <span class="text-danger">*</span>组合名称：
                        </label>
                        <div class="col-sm-13">
                            <input type="text" class="form-control required specstr" name="groupName" id="group_name" minlength="2" maxlength="16">
                        </div>
                        <div class="col-sm-5">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-6 control-label">
                            <span class="text-danger">*</span>组合类型：
                        </label>
                        <div class="col-sm-13">
                            <label class="radio-inline">
                                <input type="radio" name="groupPrefertype"  id="groupPrefertype_00" onclick="changGroupType(0)" checked="checked" value="0"> 特惠套装
                            </label>
                            <label class="radio-inline">
                                <input type="radio"name="groupPrefertype"  id="groupPrefertype_01" onclick="changGroupType(1)" value="1"> 人气组合
                            </label>
                        </div>
                        <div class="col-sm-5">
                        </div>
                    </div>
                    <div class="form-group group_preferamountType">
                        <label class="col-sm-6 control-label">
                            <span class="text-danger">*</span>优惠额度：
                        </label>
                        <div class="col-sm-13">
                            <input type="text" class="form-control required number" name="groupPreferamount" id="group_preferamount">
                        </div>
                        <div class="col-sm-5">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="saveGroup()" class="btn btn-primary">保存组合</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
            </form>
        </div>
    </div>
</div>

<!-- 编辑组合 -->
<div class="modal fade" id="editCombination"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑商品组合</h4>
            </div>
            <form role="form" class="form-horizontal" id="editGroup" action="updateGroup.htm" method="post">
                <div class="modal-body">
                    <div class="modal_form">
                        <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken"/>
                        <input type="hidden" name="groupId" value="${token}" id="update_groupId"/>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>组合名称：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required specstr" name="groupName" id="update_group_name" minlength="2" maxlength="16">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>组合类型：
                            </label>
                            <div class="col-sm-13">
                                <label class="radio-inline">
                                    <input type="radio" name="groupPrefertype"  id="prefertype0" onclick="changGroupType(0)" checked="checked" value="0"> 特惠套装
                                </label>
                                <label class="radio-inline">
                                    <input type="radio"name="groupPrefertype"  id="prefertype1" onclick="changGroupType(1)" value="1"> 人气组合
                                </label>
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                        <div class="form-group group_preferamountType">
                            <label class="col-sm-6 control-label">
                                <span class="text-danger">*</span>优惠额度：
                            </label>
                            <div class="col-sm-13">
                                <input type="text" class="form-control required number" name="groupPreferamount" id="update_group_preferamount">
                            </div>
                            <div class="col-sm-5">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">保存组合</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
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
<script src="<%=basePath%>js/system/system_common.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/goods/goods_group.js"></script>
<script>
    var num=0;
    /**
    *添加商品组合
     */
    function saveGroup(){
        if($("#saveGroup").valid()&&num==0){
            num+=1;
            $("#saveGroup").submit();
        }
    }
</script>
</body>
</html>

