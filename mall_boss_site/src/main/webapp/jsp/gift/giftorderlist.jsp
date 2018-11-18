<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>积分商品订单</title>

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
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
    <div class="row">
        <!-- 引用左边 -->
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}
                    <small>(共${pageBean.rows }条)
                    </small>
                </h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <form class="form-inline" id="search_from" method="post" action="${pageBean.url }">
                            <input type="hidden" value="search_from" id="formId">
                            <input type="hidden" value="${pageBean.url }" id="formName">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">订单号</span>
                                    <input name="giftOrderCode" value="${order.giftOrderCode }" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">收货人</span>
                                <div class="col-sm-15">
                                    <input type="text" class="form-control" name="shoppingPerson" id="shippingPerson"  value="${order.shoppingPerson}">
                                </div>
                            </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                <span class="input-group-addon">联系电话</span>

                                    <input type="text" class="form-control" name="shoppingMobile" value="${order.shoppingMobile}" id="shippingMobile">
                                </div>
                            </div>
                            <div class="form-group">

                                <div class="input-group">

                                    <span class="input-group-addon">订单状态</span>
                                    <select class="cate_selector w150" name="giftOrderStatus" id="orderStatus"
                                            >
                                        <option value="">-请选择-</option>
                                        <option value="0" <c:if test="${order.giftOrderStatus eq '0'}">selected ="selected"</c:if>>未发货</option>
                                        <option value="1" <c:if test="${order.giftOrderStatus eq '1'}">selected ="selected"</c:if>>已发货</option>
                                        <option value="2" <c:if test="${order.giftOrderStatus eq '2'}">selected ="selected"</c:if>>已完成</option>
                                    </select>
                                </div>

                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">搜索</button>
                            </div>



                        </form>
                            </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="50"><input name="giftOrderId"  onclick="allunchecked(this,'giftOrderId')" type="checkbox"></th>
                            <th >订单号</th>
                            <th >收货人</th>
                            <th >联系电话</th>
                            <th >兑换积分</th>
                            <th >订单状态</th>
                            <th class="w100">下单时间</th>
                            <th >商家</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>



                        <c:forEach items="${pageBean.list}" var="order" varStatus="i">
                            <tr>
                                <td><input type="checkbox" name="giftOrderId"  value="${order.giftOrderId }"></td>
                                <td>
                                    <a href="javascript:;" onclick="openGiftOrderDetail(${order.giftOrderId })">${order.giftOrderCode }</a>
                                </td>
                                <td>${order.shoppingPerson }</td>
                                <td>${order.shoppingMobile}</td>

                                <td>${order.orderIntegral}</td>
                                <td>


                                    <c:if test="${order.giftOrderStatus eq 0}"><span
                                            class="label label-danger">未发货</span></c:if>
                                    <c:if test="${order.giftOrderStatus eq 1}"><span
                                            class="label label-primary">已发货</span></c:if>
                                    <c:if test="${gorder.giftOrderStatus eq 2}"><span
                                            class="label label-success">已完成</span></c:if>
                                </td>
                                <td> <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.createTime }" type="both"/></td>
                                <td>平台自营</td>

                                <td>
                                    <div class="btn-group">

                                        <button type="button" class="btn btn-default" onclick="openGiftOrderDetail(${order.giftOrderId })">查看详情</button>
                                        <button type="button" class="btn btn-default dropdown-toggle"
                                                data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <c:if test="${order.giftOrderStatus eq '0' }">
                                            <ul class="dropdown-menu" role="menu">
                                                <li>

                                                    <a href="javascript:;"
                                                       onclick="opendialog3(${order.giftOrderId })">
                                                        发货
                                                    </a>
                                                </li>
                                            </ul>
                                        </c:if>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${map.pb}"/>
                    </c:import>
                    </div>



                </div>

            </div>

        </div>

</div>

<%--订单详情--%>
<div class="modal fade" id="orderDetails"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">订单详情</h4>
            </div>
            <div class="modal-body">
                <div class="common_info common_tabs mt20">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">订单详情</a></li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab1">
                            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                                <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="headingOne">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                会员信息
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                        <div class="panel-body">
                                            <div class="container-fluid">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <p >会员ID：<span id="p1"></span></p>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <p >用户名：<span id="p2"></span></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="headingTwo">
                                        <h4 class="panel-title">
                                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                                订单详细信息
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                        <div class="panel-body">
                                            <div class="container-fluid">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <p>订单编号：<span id="p3"></span></p>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <p >下单时间：<span id="p4"></span></p>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <p >兑换所需积分：<span id="p5"></span></p>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <p >订单状态：
                                                            <span class="text-danger" id="p6"></span>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="headingThree">
                                        <h4 class="panel-title">
                                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                物流信息
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                        <div class="panel-body">
                                            <div class="container-fluid">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <p>运单号：<span id="p7"></span></p>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <p>物流公司：<span id="p8"></span></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="headingFour">
                                        <h4 class="panel-title">
                                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseThree">
                                                收货信息
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                                        <div class="panel-body">
                                            <div class="container-fluid">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <p>商品名称：<span id="p9"></span></p>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <p>商品市场价：<span id="p10"></span></p>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <p>详细地址：<span id="p11"></span></p>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <p>收货人：<span id="p12"></span></p>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <p>手机：	<span id="p13"></span></p>
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
            </div>
        </div>
    </div>
</div>





<!-- 发货Modal -->
<div class="modal fade" id="dialog-confirm3"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" method="post" id="updategiftorder">
                <input type="hidden" name="CSRFToken" value="${token}">


                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">发货</h4>
                </div>
                <div class="modal-body">
                    <div>

                        <div class="form-group">
                            <label class="control-label col-sm-5">物流公司:</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-15">
                                <select name="temp2" id="expressId" style="width: 150px"
                                        class="form-control cate_selector">
                                    <c:forEach items="${expressList}" var="express" >
                                        <option  value="${express.logComId }">${express.name }</option>
                                    </c:forEach>
                                    <option></option>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="url" value="${pageBean.url }">
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>物流单号:</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <input class="form-control required" name="temp3" id="expressNo" >
                                <label id="expressNo_tips"></label>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="dosubmit();">确定</button>
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
<script src="<%=basePath %>/js/select2.min.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script>


    function openDialog(gId) {
        $('#auditByGoodsId').attr("action","auditByGoodsId.htm?goodsId="+gId);
        $('#dialog-confirm2').modal('show');
    }
    function openRefuseDialog(gId) {
        $('#refuseAuditByGoodsId').attr("action","refuseAuditByGoodsId.htm?goodsId="+gId);
        $('#dialog-confirm3').modal('show');
    }


    //更新错误提示框的状态
    function updateTips( t, tip)
    {
        tip .text( t ) .addClass( "error" );

    }


    function dosubmit() {
        if($("#updategiftorder").valid()){
            //验证特殊字符
            if(!checkSpecSymb("expressNo","expressNo_tips")){
                return;
            }
            else{
                $("#expressNo").removeClass( "error" );
                $("#expressNo_tips").text("").removeClass( "error" );
            }
            $("#updategiftorder").submit();
        }
    }
    function opendialog3(obj) {
        $('#dialog-confirm3').modal('show');

        $("#updategiftorder").attr("action", "updategiftorder.htm?giftOrderId=" + obj);


    }
    //验证特殊字符，将调试显示到页面中
    function checkSpecSymb(inputobj,Tipobj){
        var regexp=new RegExp("[''\\[\\]<>?\\\\!]");
        if (regexp.test( $("#"+inputobj).val() ) ) {
            $("#"+inputobj).addClass( "error" );
            updateTips( "输入的内容包含特殊字符!", $("#"+Tipobj));
            $("#"+inputobj).focus();
            return false;
        }else {
            $("#"+Tipobj).text("").removeClass( "ui-state-highlight");
            $("#"+inputobj).removeClass( "error" );
            return true;
        }
    }
    function openGiftOrderDetail(giftOrderId) {

        $('#orderDetails').modal('show');
        $.ajax({
            url:'ajaxSelectGiftDetail.htm?giftOrderId='+giftOrderId,
            success:function(data) {

                $("#p1").html(data.customerId);
                $("#p2").html(data.customerUsername);
                $("#p3").html(data.giftOrderCode);
                var statTime=new Date(data.createTime);
                var month=statTime.getMonth();
                month=month+1;
                if(parseInt(month) < 10) {
                    month = "0" + month;
                }
                var hour=statTime.getHours();
                var minu=statTime.getMinutes();
                var sec=statTime.getSeconds();
                if(parseInt(hour)<10){
                    hour="0"+hour;
                }  if(parseInt(minu)<10){
                    minu="0"+minu;
                }  if(parseInt(sec)<10){
                    sec="0"+sec;
                }
                statTime=statTime.getFullYear()+"-"+month+"-"+statTime.getDate()+" "+hour+":"+minu+":"+sec;
                $("#p4").html(statTime);
                $("#p5").html(data.orderIntegral);
                var text="";
                if(data.giftOrderStatus==0){
                    text="未发货";
                } else if (data.giftOrderStatus == 1) {
                    text="已发货";
                }
                else{
                    text="订单完成";
                }

                $("#p6").html(text);
                $("#p7").html(data.temp3);
                $("#p8").html(data.temp4);
                $("#p9").html(data.giftName);
                $("#p10").html(data.giftPrice);
                $("#p11").html(data.shoppingAddress);
                $("#p12").html(data.shoppingPerson);
                $("#p13").html(data.shoppingMobile);

            }

        });
    }

    $(function () {


        $('select').select2();

    });
</script>
</body>
</html>
