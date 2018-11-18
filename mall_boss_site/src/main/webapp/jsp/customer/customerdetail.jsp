<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员详细</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">body {
        background: none;
    }</style>

    <style>
        .couponScope{display:none;}
        .couponNoList{display:none;}
        .couponUnGot{display:none;}
        .couponGot{display:none;}
        .couponUsed{display:none;}
    </style>
</head>
<body>

<div class="common_info common_tabs mt20">
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#tab1" class="pointTab" aria-controls="tab1" role="tab"
                                                  data-toggle="tab">基本信息</a></li>
        <li role="presentation"><a href="#tab2" aria-controls="tab2" class="pointTab" role="tab"
                                   data-toggle="tab">购买信息</a></li>
        <li role="presentation"><a href="#tab3" aria-controls="tab3" class="pointTab" role="tab" data-toggle="tab">未使用的优惠券</a>
        </li>
        <li role="presentation"><a href="#tab4" aria-controls="tab4" class="pointTab" role="tab" data-toggle="tab">已使用的优惠券</a>
        </li>
        <li role="presentation"><a href="#tab5" aria-controls="tab5" class="pointTab" role="tab"
                                   data-toggle="tab">商品关注</a></li>
        <li role="presentation"><a href="#tab6" aria-controls="tab6" class="pointTab" role="tab"
                                   data-toggle="tab">积分</a></li>
    </ul>


    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="tab1">
            <div class="rec_example">
                <div class="recommend_edit">
                    <img class="good_img" alt=""
                         src="<c:if test="${customer.customerImg!='' }">${customer.customerImg}</c:if><c:if test="${customer.customerImg==null||customer.customerImg==''}"><%=basePath %>images/default_head.jpg</c:if>"
                         width="200">

                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-sm-12">
                                <fmt:formatDate value="${customer.createTime }" pattern="yyyy-MM-dd HH:mm:ss"
                                                var="createTime"/>
                                <fmt:formatDate value="${customer.modifiedTime }" pattern="yyyy-MM-dd HH:mm:ss"
                                                var="modifiedTime"/>
                                <p>用户名： ${customer.customerUsername }</p>
                            </div>
                            <div class="col-sm-12">
                                <p>真实姓名： ${customer.infoRealname}</p>
                            </div>
                            <div class="col-sm-12">
                                <p>兴趣爱好： <c:if test="${fn:length(customer.infoInterest)>20 }">
                                    ${fn:substring(customer.infoInterest,0,19) }...
                                </c:if>
                                    <c:if test="${fn:length(customer.infoInterest)<=20 }">
                                        ${customer.infoInterest}
                                    </c:if>
                                    </td></p>
                            </div>
                            <div class="col-sm-12">
                                <p>昵称： ${customer.customerNickname }</p>
                            </div>
                            <div class="col-sm-12">
                                <p>身份证： ${customer.infoCardid}</p>
                            </div>
                            <div class="col-sm-12">
                                <p>收货地址： ${ customer.customerAddress.province.provinceName}-
                                    ${customer.customerAddress.city.cityName}-
                                    ${customer.customerAddress.district.districtName}-
                                    <c:if test="${fn:length(customer.customerAddress.addressDetail)>6 }">
                                        ${fn:substring(customer.customerAddress.addressDetail,0,5) }...
                                    </c:if>
                                    <c:if test="${fn:length(customer.customerAddress.addressDetail)<=6 }">
                                        ${customer.customerAddress.addressDetail}
                                    </c:if>
                                </p>
                            </div>
                            <fmt:formatNumber value="${customer.balanceSum}" var="baSum" pattern="##.##"
                                              minFractionDigits="2"></fmt:formatNumber>
                            <!--<div class="col-sm-12">
                                <p>账户余额： ￥${baSum}</p>
                            </div>-->
                            <div class="col-sm-12">
                                <p>性别：
                                    <c:if test="${customer.infoGender==0}"> 保密 </c:if>
                                    <c:if test="${customer.infoGender==1}"> 男 </c:if>
                                    <c:if test="${customer.infoGender==2}"> 女 </c:if>
                                </p>
                            </div>
                            <div class="col-sm-12">
                                <p>手机：${customer.infoMobile}</p>
                            </div>
                            <div class="col-sm-12">
                                <p>月收入：
                                    <c:if test="${customer.infoSalary==-1}"> 无收入 </c:if>
                                    <c:if test="${customer.infoSalary==0}"> 2000元以下 </c:if>
                                    <c:if test="${customer.infoSalary==1}"> 2000-3999元 </c:if>
                                    <c:if test="${customer.infoSalary==2}"> 4000-5999元 </c:if>
                                    <c:if test="${customer.infoSalary==3}"> 6000-7999元 </c:if>
                                    <c:if test="${customer.infoSalary==4}"> 8000元以上 </c:if>
                                </p>
                            </div>
                            <div class="col-sm-12">
                                <p>邮箱： ${customer.infoEmail}</p>
                            </div>
                            <div class="col-sm-12">
                                <p>注册时间： ${createTime}</p>
                            </div>
                            <%--<div class="col-sm-12">--%>
                                <%--<p>登 录 IP： ${customer.loginIp}</p>--%>
                            <%--</div>--%>
                            <div class="col-sm-12">
                                <p>积分总额： ${customer.infoPointSum}</p>
                            </div>
                            <div class="col-sm-12">
                                <p>修改时间： ${modifiedTime}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="tab2">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>订单编号</th>
                    <th>商品图片</th>
                    <th width="250">商品名称</th>
                    <th>订单总价</th>
                    <th width="100">购买时间</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${customer.orders }" var="order" varStatus="i">
                    <fmt:formatDate value="${order.payTime }" pattern="yyyy-MM-dd HH:mm:ss" var="payTime"/>
                    <tr>
                        <td>${order.orderNo }</td>
                        <td><img alt="" src="${ order.goods[0].goodsImg}" height="50"></td>
                        <td title="${order.goods[0].goodsName}">
                            <c:if test="${fn:length(order.goods[0].goodsName)>10 }">
                                ${fn:substring(order.goods[0].goodsName,0,9) }...
                            </c:if>
                            <c:if test="${fn:length(order.goods[0].goodsName)<=10 }">
                                ${order.goods[0].goodsName}
                            </c:if>
                        </td>
                        <td>${order.moneyPaid }</td>
                        <td>${payTime }</td>
                        <td>
                            <button type="button" class="btn btn-default"
                                    onclick="jumpOrder('${order.businessId}','${order.orderNo }');">查看订单
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!--       <div class="table_foot">
                    <div class="table_pagenav pull-right">
                      <div class="meneame">
                        <span class="disabled"> 上一页 </span>
                        <span class="current"> 1 </span>
                        <a href="#?page=2"> 2 </a>
                        <a href="#?page=3"> 3 </a>
                        <a href="#?page=4"> 4 </a>
                        <a href="#?page=5"> 5 </a>
                        ...
                        <a href="#?page=199"> 199 </a>
                        <a href="#?page=200"> 200 </a>
                        <a href="#?page=2"> 下一页 </a>
                      </div>
                    </div>
                    <div class="clr"></div>
                  </div> -->
        </div>
        <div role="tabpanel" class="tab-pane" id="tab3">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>优惠券号码</th>
                    <th>类别</th>
                    <th>优惠金额</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody>


                <c:forEach items="${ customer.coupons}" var="coupon" varStatus="i">
                    <fmt:formatDate value="${coupon.createTime }" pattern="yyyy-MM-dd HH:mm:ss" var="createTime"/>
                    <c:if test="${coupon.codeStatus == 1 }">
                        <tr>
                            <td>${coupon.couponNo }</td>
                            <td>
                                <c:if test="${coupon.ruleType==1}">
                                    直降
                                </c:if>
                                <c:if test="${coupon.ruleType==2}">
                                    满减
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${coupon.ruleType==1}">
                                    直降${coupon.downPrice }
                                </c:if>
                                <c:if test="${coupon.ruleType==2}">
                                    满${coupon.fullPrice }减${coupon.reductionPrice }
                                </c:if>
                            </td>
                            <td>
                                <button type="button" class="btn btn-default" onclick="getScanCoupons('${coupon.couponId }');">查看</button>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
            <!--   <div class="table_foot">
                <div class="table_pagenav pull-right">
                  <div class="meneame">
                    <span class="disabled"> 上一页 </span>
                    <span class="current"> 1 </span>
                    <a href="#?page=2"> 2 </a>
                    <a href="#?page=3"> 3 </a>
                    <a href="#?page=4"> 4 </a>
                    <a href="#?page=5"> 5 </a>
                    ...
                    <a href="#?page=199"> 199 </a>
                    <a href="#?page=200"> 200 </a>
                    <a href="#?page=2"> 下一页 </a>
                  </div>
                </div>
                <div class="clr"></div>
              </div> -->
        </div>
        <div role="tabpanel" class="tab-pane" id="tab4">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>优惠券号码</th>
                    <th>订单号</th>
                    <th>优惠券类型</th>
                    <th>优惠金额</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ customer.coupons}" var="coupon" varStatus="i">
                    <fmt:formatDate value="${coupon.createTime }" pattern="yyyy-MM-dd HH:mm:ss" var="createTime"/>
                    <c:if test="${coupon.codeStatus == 2 }">
                        <tr>
                            <td>${coupon.couponNo }</td>
                            <td>
                                <c:if test="${coupon.ruleType==1}">
                                    直降
                                </c:if>
                                <c:if test="${coupon.ruleType==2}">
                                    满减
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${coupon.ruleType==1}">
                                    直降${coupon.downPrice }
                                </c:if>
                                <c:if test="${coupon.ruleType==2}">
                                    满${coupon.fullPrice }减${coupon.reductionPrice }
                                </c:if>
                            </td>
                            <td>
                                <button type="button" class="btn btn-default" onclick="getScanCoupons('${coupon.couponId }');">查看</button>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div role="tabpanel" class="tab-pane" id="tab5">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>商品编号</th>
                    <th>商品图片</th>
                    <th width="250">商品名称</th>
                    <th>商品价格</th>
                    <th>关注时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${follows }" var="follow" varStatus="i">
                    <fmt:formatDate value="${follow.createTime }" pattern="yyyy-MM-dd HH:mm:ss" var="payTime"/>
                    <tr>
                        <td>${follow.goodsId }</td>
                        <td><img alt="" src="${follow.good.goodsImg }" height="50"></td>
                        <td title="${follow.good.goodsName }">
                            <c:if test="${fn:length(follow.good.goodsName)>15 }">
                                ${fn:substring(follow.good.goodsName,0,14) }...
                            </c:if>
                            <c:if test="${fn:length(follow.good.goodsName)<=15 }">
                                ${follow.good.goodsName}
                            </c:if>
                        </td>
                        <td>${follow.good.goodsPrice }</td>
                        <td>${payTime }</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!--  <div class="table_foot">
               <div class="table_pagenav pull-right">
                 <div class="meneame">
                   <span class="disabled"> 上一页 </span>
                   <span class="current"> 1 </span>
                   <a href="#?page=2"> 2 </a>
                   <a href="#?page=3"> 3 </a>
                   <a href="#?page=4"> 4 </a>
                   <a href="#?page=5"> 5 </a>
                   ...
                   <a href="#?page=199"> 199 </a>
                   <a href="#?page=200"> 200 </a>
                   <a href="#?page=2"> 下一页 </a>
                 </div>
               </div>
               <div class="clr"></div>
             </div> -->
        </div>
        <div role="tabpanel" class="tab-pane" id="tab6">

            <form id="searchGoodsInfo" action="" method="post">
                <input type="hidden" name="pageNo" id="pageNo" value=""/>
                <input type="hidden" name="pageSize" id="pageSize" value=""/>
                <input type="hidden" name="status" id="status" value=""/>
                <input type="hidden" name="customerId" id="customerId" value="${ customer.customerId}"/>

                <div class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-xs-4">可用积分：</label>

                        <div class="col-xs-1"></div>
                        <div class="col-xs-10">
                            <label class="control-label" id="totalpoint">${customer.infoPointSum}</label></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-4"><span class="text-danger">*</span>调整积分：</label>

                        <div class="col-xs-1"></div>
                        <div class="col-xs-5"><input type="text" class="form-control" name="point" id="point" >

                        </div>

                        <div class="col-xs-1"></div>
                        <div class="col-xs-4"><a href="javascript:;" class="help_tips"  id="tiaozhengjifen"><i
                                class="iconfont">&#xe611;</i></a></div>
                    </div>
                    <div class="form-group"><label class="control-label col-xs-4"><span class="text-danger">*</span>备注说明：</label>

                        <div class="col-xs-1"></div>
                        <div class="col-xs-12"><input type="text" class="form-control" name="pointDetail" id="pointDetail"></div>
                    </div>
                    <div class="form-group" style="padding-left:90px">
                        <div class="col-xs-1">
                            <div class="sf-btns">
                                <input type="button" value="保存" onclick="openSave()">
                            </div>
                        </div>
                    </div>

                </div>

                <table class="table table-striped table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>日期时间</th>
                        <th>积分</th>
                        <th width="300">说明</th>
                        <th>操作人</th>
                    </tr>
                    </thead>
                    <tbody id="pointTable">
                    </tbody>
                </table>

                <div class="table_foot">
                    <div class="table_pagenav pull-right">
                        <div class="meneame pointfoot">
                        </div>
                    </div>
                    <div class="clr"></div>
                </div>
            </form>
        </div>
    </div>

</div>


<!-- 查看优惠券-->
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
                    <div class="row">
                        <div class="col-sm-24">
                            <p>使用等级：<span id="couponLelvel"></span> </p>
                        </div>
                    </div>
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


<!-- 添加积分Modal -->
<div class="modal fade" id="dialog3"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal"  >

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    您确定要修改该会员的积分记录吗?
                </div>
                <div class="modal-footer">
                    <a  class="btn btn-primary" onclick="saveorder();">确定</a>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/couponlist/couponlist.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.zclip.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.zclip.js"></script>
<script type="text/javascript">
    function jumpOrder(thirdId, codeNo) {
        if (thirdId == '0') {
            window.parent.location.href = "orderlist.htm?menuId=89&menuParentId=1165&myselfId=947&orderCode=" + codeNo;
        } else {
            window.parent.location.href = "orderlististhird.htm?menuId=1995&menuParentId=2001&myselfId=2002&orderCode=" + codeNo;
        }

    }

    function checkPoint(){
        var flag=true;
        var point=   $("#point").val();
        var pointDetail=$("#pointDetail").val();

            if(pointDetail==null||pointDetail==''){
            flag=flag&false;
            $(".flagerror2").remove();
            $("#pointDetail").after('<label for="point"  generated="true" class="error flagerror2" style="display: block">不能为空</label>');
            $("#pointDetail").addClass("error");
        }else{
                $(".flagerror2").remove();
                $("#pointDetail").removeClass("error");
                flag=flag&true;
            }
        if(point==null||point==''){
            flag=flag&false;
            $(".flagerror").remove();
            $("#point").after('<label for="point"  generated="true" class="error flagerror" style="display: block">不能为空</label>');
            $("#point").addClass("error");
        }
        else{
            $(".flagerror").remove();
            var reg=/^[-\+]?\d+$/;
            if(!reg.test(point)){
                $("#point").after('<label  for="point" generated="true" class="error flagerror" >请输入整数</label>');
                $("#point").addClass("error");
                flag=flag&false;
            }
            else{
                flag=flag&true;
                $("#point").removeClass("error");
            }
        }
        if(flag){
            $(".flagerror").remove();
            $(".flagerror2").remove();
            $("#point").removeClass("error");
            $("#pointDetail").removeClass("error");
        }
        return flag;
    }

    function openSave(){

            if(checkPoint()){
                var total=parseInt($("#totalpoint").text());
                var point=parseInt($("#point").val());
                if(point<0){
                    if(total+point<0 ){
                        showTipAlert("调整后的积分不能使可用积分小于0!")
                        $('#dialog3').modal('hide')
                        return;
                    }
                }
                $("#point").removeClass("error");
                $('#dialog3').modal('show')
            }

    }
    //    日期格式化
    var format = function (time, format) {
        var t = new Date(time);
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
            ;
        });
    }

    function saveorder(){

        $.ajax({
            url: "saveCustomerPoint.htm?CSRFToken=${token}",
            data: $("#searchGoodsInfo").serialize(),
            async: false,
            success: function (data) {
                if(data>=0){
                    showTipAlert("修改会员积分成功!")
                    $('#dialog3').modal('hide')
                    doAjax(1,8);
                    $("#totalpoint").html(data);
                }
                else{
                    showTipAlert("修改会员积分失败!")
                    $('#dialog3').modal('hide');
                }
            }
        });
    }
    /*点打开会员积分的时候*/
    function doAjax(pageNo, pageSize) {
        $("#pageNo").val(pageNo),
                $("#pageSize").val(pageSize),
                $.ajax({
                    url: "ajaxqueryCustomerDetail.htm?CSRFToken=${token}",
                    data: $("#searchGoodsInfo").serialize(),
                    async: true,
                    success: function (data) {
                        if(data!=null&&data.list!=null&&data.list.length>0){
                            var list = data.list;
                            var productListHtml = "";
                            for (var i = 0; i < list.length; i++) {
                                var point="";
                                if(list[i].pointType==1){
                                    point=list[i].point;
                                }else{
                                    point=-list[i].point;
                                }
                                productListHtml = productListHtml + "<tr><td>" + format(list[i].createTime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
                                productListHtml += "<td>" + point+ "</td>";
                                productListHtml += "<td >" + list[i].pointDetail + "</td><td>${name}</td></tr>";
                            }
                            $("#pointTable").html(productListHtml);
                            /*添加页脚*/
                            $(".pointfoot").html("");
                            var foot = "";
                            var i = 1;
                            for (var l = data.startNo; l <= data.endNo; l++) {
                                if ((i - 1 + data.startNo) == data.pageNo) {
                                    foot = foot + "<span class='current'> " + (i - 1 + data.startNo) + "</span>";
                                }
                                else {
                                    foot = foot + "<a onclick='doAjax(" + (i - 1 + data.startNo) + "," + (data.pageSize) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
                                }
                                i++;
                            }
                            foot = foot + "";
                            /*添加tfoot分页信息*/
                            $(".pointfoot").html(foot);
                        }
                    }
                });

    }

    $(function () {

        $('#tiaozhengjifen').popover({
            content : '可填写正负数，正数增加积分，负数减少积分。',
            trigger : 'hover'
        });
        $(".pointTab").click(function () {
            var status = $(this).attr("aria-controls").substring(3, 4);
            $("#status").val(status);
            //打开会员积分时
            if (status == '6') {
                doAjax(1, 8);
            }
        });
    });

</script>
</body>
</html>



   