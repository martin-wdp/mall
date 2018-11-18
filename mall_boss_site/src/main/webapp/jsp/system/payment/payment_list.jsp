<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/25
  Time: 17:46
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
    <title>支付方式设置</title>

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
                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows }条)</small></h2>

                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 这两种支付方式是系统配置好的，请根据网站实际情况选择是否启用，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="box_data container-fluid p20">
                        <div class="row">
                            <c:forEach  var="payment" items="${pb.list }" varStatus="status">
                                <fmt:formatDate value="${payment.updateDate }" var="cdate" type="both"/>
                                <div class="col-sm-6">
                                    <div class="box_item">
                                        <h2>${payment.name }</h2>
                                        <c:if test="${payment.isOpen == 1 }">
                                            <span class="label label-success">已启用</span>
                                        </c:if>
                                        <c:if test="${payment.isOpen == 0}">
                                            <span class="label label-default">已停用</span>
                                        </c:if>

                                        <p>最新修改时间：${cdate }</p>
                                        <a class="box_edit" href="javascript:;" onclick="showEditPayment(this)" payment_id="${payment.paymentId}" payment_name="${payment.name}" is_open="${payment.isOpen}">编辑</a>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--<div class="col-sm-6">
                                <div class="box_add" style="height: 130px; line-height: 130px;">
                                    <a href="javascript:;"  onclick="$('#editPayment').modal('show')">
                                        <i class="glyphicon glyphicon-plus"></i> 添加支付方式
                                    </a>
                                </div>
                            </div>--%>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addPayment"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加支付方式</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="addPaymentForm" action="createPayment.htm">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>是否开启：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>支付方式名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="name">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitPaymentForm('addPaymentForm')">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- 编辑支付方式 -->
<div class="modal fade" id="editPayment"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑支付方式</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="editPaymentForm" action="updatePayment.htm">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="paymentId" id="paymentId"/>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>是否开启：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open1" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open2" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>支付方式名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="name" id="payname">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitPaymentForm('editPaymentForm')">保存</button>
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
<script src="<%=basePath%>js/system/payment.js"></script>
</body>
</html>


