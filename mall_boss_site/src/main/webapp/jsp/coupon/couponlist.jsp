<%--
  Created by IntelliJ IDEA.
  User: Zhoux
  Date: 2015/3/18
  Time: 17:04
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
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
   

</head>
<style>
    .couponScope{display:none;}
    .couponNoList{display:none;}
    .couponUnGot{display:none;}
    .couponGot{display:none;}
    .couponUsed{display:none;}
</style>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>

<div class="container-fluid page_body">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows}条)</small></h2>
                <div class="common_data p20">
                    <div class="filter_area">
                        <form  id="search_form" method="post" action="couponlist.htm" role="form" class="form-inline">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">优惠券名称</span>
                                    <input type="text" class="form-control"name="couponName" value="${pageBean.objectBean.couponName }"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary" id="search-button">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="window.location.href='toaddcoupon.htm'">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" id="batch-button">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                <form id="delete_form" method="post" action="delallcoupon.htm">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" id="check_boxs"></th>
                            <th class="w200">优惠券名称</th>
                            <th class="w100">类型</th>
                            <th class="w100">图片</th>
                            <th>每人可领张数</th>
                            <%--<th>是否发布</th>--%>
                            <th class="w100">开始时间</th>
                            <th class="w100">结束时间</th>
                          
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.list}" var="coupon" varStatus="i">
                        <tr>
                            <td><input type="checkbox" class="check_box" name="couponId" value="${coupon.couponId}"></td>
                            <td>${coupon.couponName}</td>
                            <td><c:if test="${coupon.couponRulesType==1 }">直降优惠</c:if> <c:if test="${coupon.couponRulesType==2 }">满减优惠</c:if></td>
                            <td><c:if test="${coupon.couponPic!=''&&coupon.couponPic!=null}"><img src="${coupon.couponPic}"  height="50" /></c:if>
                                <c:if test="${coupon.couponPic==''||coupon.couponPic==null}"><img src="${basePath}images/default_head.jpg"  height="50" /></c:if>
                            </td>
                            <td>${coupon.couponGetNo}</td>
                            <%--<td>--%>
                                <%--<c:if test="${coupon.couponIsShow eq '1'}"><span class="label label-success">是</span></c:if>--%>
                                <%--<c:if test="${coupon.couponIsShow eq '0'}"><span class="label label-default">否</span></c:if>--%>
                            <%--</td>--%>
                               <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${coupon.couponStartTime }" type="both"/></td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${coupon.couponEndTime }" type="both"/></td>
                            <td>
                                <div class="btn-group">
                                    <a href="javascript:void(0);" class="btn btn-default copy"  attr-id="${coupon.couponId}"  target="_blank">复制链接</a>
                                    <a href="javascript:void(0);" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:void(0);" onclick="getScanCoupons('${coupon.couponId }');">查看详情</a></li>
                                        <li><a href="javascript:void(0);" onclick="getScanCouponNums('${coupon.couponId }');">查看券码</a></li>
                                        <li><a href="javascript:void(0);" onclick="deleteSingleCoupon('${coupon.couponId }');">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>

                    <c:import url="../page/paging.jsp">
                        <c:param name="pageBean" value="${pageBean }"/>
                        <c:param name="path" value="../../"></c:param>
                    </c:import>

                </div>


                <!-- Modal -->
                <div class="modal fade" id="scanCoupons"  role="dialog">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">优惠券详细信息</h4>
                            </div>
                            <div class="modal-body">
                                <div class="mb20 container-fluid">
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <p>优惠券名称：<span id="couponName"></span></p>
                                        </div>
                                        <div class="col-sm-8">
                                            <p>优惠券描述：<span id="couponRemark"></span></p>
                                        </div>
                                        <div class="col-sm-8">
                                            <p>类型：<span id="couponType"></span></p>
                                        </div>
                                    </div>
                                    <%--<div class="row">
                                        <div class="col-sm-24">
                                            <p>使用等级：<span id="couponLelvel"></span> </p>
                                        </div>
                                    </div>--%>
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <p>开始时间：<span id="couponStartTime"></span></p>
                                        </div>
                                        <div class="col-sm-8">
                                            <p>结束时间：<span id="couponEndTime"></span></p>
                                        </div>
                                        <div class="col-sm-8">
                                            <p>创建时间：<span id="createTime"></span></p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <p>修改时间：<span id="modifyTime"></span></p>
                                        </div>
                                        <div class="col-sm-8">
                                            <p><span id="couponRulesType"></span></p>
                                        </div>
                                        <div class="col-sm-8">
                                            <p>生成张数：<span id="couponCount"></span></p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <p>限领张数：<span id="couponGetNo"></span></p>
                                        </div>
                                    </div>
                                </div>
                                <h4>优惠券使用范围</h4>
                                <table class="table table-striped table-hover table-bordered">
                                    <thead>
                                    <tr id="couponTop">
                                        <th width="80">图片</th>
                                        <th width="150">货品编号</th>
                                        <th>货品名称</th>
                                    </tr>
                                    </thead>
                                    <tbody id="couponScope">

                                    </tbody>
                                </table>
                                <div class="table_foot">
                                    <div class="table_pagenav pull-right">
                                        <div class="meneame" id="couponPage">

                                        </div>
                                    </div>

                                    <div class="clr"></div>
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
<div class="modal fade" id="scanCouponNums"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">券码列表</h4>
            </div>
            <div class="modal-body">
                <div class="common_info common_tabs mt20">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">所有券码</a></li>
                        <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">未领取券码</a></li>
                        <li role="presentation"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">已领取券码</a></li>
                        <li role="presentation"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">已使用券码</a></li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab1">
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>券码序列号</th>
                                    <th width="100">状态</th>
                                    <th>领取时间</th>
                                    <th>领取人</th>
                                </tr>
                                </thead>
                                <tbody id="couponNoList">

                                </tbody>
                            </table>
                            <div class="table_foot">
                                <div class="table_pagenav pull-right">
                                    <div class="meneame" id="couponNoPage">

                                    </div>
                                </div>
                                <div class="clr"></div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tab2">
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>券码序列号</th>
                                    <th width="100">状态</th>
                                    <th>领取时间</th>
                                    <th>领取人</th>
                                </tr>
                                </thead>
                                <tbody id="couponUnGot">

                                </tbody>
                            </table>
                            <div class="table_foot">
                                <div class="table_pagenav pull-right">
                                    <div class="meneame" id="couponUnGotPage">

                                    </div>
                                </div>

                                <div class="clr"></div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tab3">
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>券码序列号</th>
                                    <th width="100">状态</th>
                                    <th>领取时间</th>
                                    <th>领取人</th>
                                </tr>
                                </thead>
                                <tbody id="couponGot">

                                </tbody>
                            </table>
                            <div class="table_foot">
                                <div class="table_pagenav pull-right">
                                    <div class="meneame" id="couponGotPage">

                                    </div>
                                </div>

                                <div class="clr"></div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tab4">
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>券码序列号</th>
                                    <th width="100">状态</th>
                                    <th>领取时间</th>
                                    <th>领取人</th>
                                </tr>
                                </thead>
                                <tbody id="couponUsed">

                                </tbody>
                            </table>
                            <div class="table_foot">
                                <div class="table_pagenav pull-right">
                                    <div class="meneame" id="couponUsedPage">

                                    </div>
                                </div>

                                <div class="clr"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<input id="csrf_token" type="hidden" id="sx" value="${sx}">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/jquery.validate.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
 <script src="<%=basePath %>js/couponlist/couponlist.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.zclip.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.zclip.js"></script>
<script>
    $(function(){
        $("#check_boxs").click(function(){
            var sc_id="";
            if($(this).prop("checked")){
                $(".check_box").each(function(){
                    if(! $(this).prop("checked")){
                        $(this).prop("checked",true);
                    }
                });
                $(".check_box").each(function(){
                    sc_id += $(this).val()+"|";
                });
            }else{
                $(".check_box").each(function(){
                    if($(this).prop("checked")){
                        $(this).prop("checked",false);
                    }
                });
            }
        });
    });


</script>
</body>
</html>

