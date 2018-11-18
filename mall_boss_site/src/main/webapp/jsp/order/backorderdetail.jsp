<%--
  Created by IntelliJ IDEA.
  User: Zhoux
  Date: 2015/6/16
  Time: 10:10
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
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>退单详情</title>
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

    <style>
        li{list-style:none;}
        .sf-tit {margin:20px 0;}
        .sf-item {margin-bottom:20px;margin-top:20px;}
        .sf-item input[type="radio"] {vertical-align:middle;margin-right:5px;}
        .sf-item label {margin-right:20px;}
        .sf-item input[type="text"] {border:1px solid #ccc;width:150px;height:25px;}
        .sf-btns input {padding:5px 10px;}
        .sf-item textarea {vertical-align:top;border:1px solid #ccc;width:250px;height:50px;}
        .sf-btns {padding-left:75px;}
        .track-info{padding-left:300px;color:#afafaf}
        .track-info .track-date,.track-info i,.track-info .track-cont{display:inline-block;*display:inline;*zoom:1;vertical-align:middle;vertical-align:bottom}
        .track-info .track-date{width:160px;text-align:right}
        .track-info i{background:url(../images/track-bg.png) no-repeat left bottom;width:14px;height:40px;margin:0 20px}
        .track-info .current .track-date{color:#e64f25}
        .track-info .current .track-cont{color:#333}
        .track-info .current .track-cont em{color:#e64f25;font-style:normal}
        .track-info .latest-track i{background:url(../images/track-circle.png) no-repeat;width:14px;height:14px}
    </style>
</head>
<body>
<div class="mb20 container-fluid">
    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>退货原因</th>
            <th>申请凭据</th>
            <th>退款金额</th>
            <th>问题说明</th>
            <th>上传凭证</th>
            <th>商品返回方式</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <c:if test="${backorder.backReason eq '1'}">不想买了</c:if>
                    <c:if test="${backorder.backReason eq '2'}">收货人信息有误</c:if>
                    <c:if test="${backorder.backReason eq '3'}">商品损坏</c:if>
                    <c:if test="${backorder.backReason eq '4'}">其他</c:if>
                </td>
                <td>
                    <c:if test="${backorder.applyCredentials eq '1'}">有发票</c:if>
                    <c:if test="${backorder.applyCredentials eq '2'}">有质检报告</c:if>
                    <c:if test="${backorder.applyCredentials eq '3'}">无任何凭据</c:if>
                </td>
                <td>${backorder.backPrice}</td>
                <td>${backorder.backRemark}</td>
                <td>
                    <c:forEach items="${backorder.imgs }" var="img">
                        <c:if  test="${img != ''}">
                       <a href="${img}" target="_blank"><img src="${img}" alt="" style="width:30px;height:30px;"/></a>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:if test="${backorder.backWay eq '0'}">快递</c:if>
                    <c:if test="${backorder.backWay eq '1'}">无</c:if>
                </td>
            </tr>
        </tbody>
    </table>
    .
    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>退货商品</th>
            <th>商品货号</th>
            <th>销售单价</th>
            <th>数量</th>
            <th>实付金额</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${backorder.orderGoodslistVo }" var="backGoods">
            <tr>
                <td>
                    <li><img src="${backGoods.goodsInfoImgId}" alt="${backGoods.goodsInfoName}" style="width:30px;height:30px;"/></li>
                </td>
                <td>${backGoods.goodsInfoItemNo}</td>
                <td>${backGoods.goodsInfoPreferPrice}</td>
                <%--随意找的货品字段装的数据 只用于前台显示--%>
                <td>${backGoods.goodsCount}</td>
                <%--随意找的货品字段装的数据 只用于前台显示--%>
                <td>${backGoods.goodsInfoSumPrice}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="sf-tit"><b>操作日志</b></div>
    <div class="item-cont">
        <div class="track-info"  style="padding-left: 130px;">
            <c:if test="${backOrderLogs ne null}">
                <c:forEach items="${backOrderLogs}" var="backOrderLog">
                    <c:if test="${backOrderLog.backLogStatus eq '7'}">
                        <p>
                            <span class="track-date"><fmt:formatDate value="${backOrderLog.backLogTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            <i></i>
                            <span class="track-cont">退款成功 (操作：admin)<c:if test="${backOrderLog.backRemark != null}">留言：${backOrderLog.backRemark}</c:if></span>
                        </p>
                    </c:if>
                    <c:if test="${backOrderLog.backLogStatus eq '6'}">
                        <p>
                            <span class="track-date"><fmt:formatDate value="${backOrderLog.backLogTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            <i></i>
                            <span class="track-cont">拒绝收货 (操作：admin)<c:if test="${backOrderLog.backRemark != null}">留言：${backOrderLog.backRemark}</c:if></span>
                        </p>
                    </c:if>
                    <c:if test="${backOrderLog.backLogStatus eq '5'}">
                        <p>
                            <span class="track-date"><fmt:formatDate value="${backOrderLog.backLogTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            <i></i>
                            <span class="track-cont">确认收货(操作：admin)<c:if test="${backOrderLog.backRemark != null}">留言：${backOrderLog.backRemark}</c:if></span>
                        </p>
                    </c:if>
                    <c:if test="${backOrderLog.backLogStatus eq '4'}">
                        <p>
                            <span class="track-date"><fmt:formatDate value="${backOrderLog.backLogTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            <i></i>
                            <span class="track-cont">填写快递信息(操作：顾客)<c:if test="${backOrderLog.backRemark != null}">留言：${backOrderLog.backRemark}</c:if></span>
                        </p>
                    </c:if>
                    <c:if test="${backOrderLog.backLogStatus eq '3'}">
                        <p>
                            <span class="track-date"><fmt:formatDate value="${backOrderLog.backLogTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            <i></i>
                            <span class="track-cont">拒绝退单(操作：admin)<c:if test="${backOrderLog.backRemark != null}">留言：${backOrderLog.backRemark}</c:if></span>
                        </p>
                    </c:if>
                    <c:if test="${backOrderLog.backLogStatus eq '2'}">
                        <p>
                            <span class="track-date"><fmt:formatDate value="${backOrderLog.backLogTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            <i></i>
                            <span class="track-cont">同意退单(操作：admin)<c:if test="${backOrderLog.backRemark != null}">留言：${backOrderLog.backRemark}</c:if></span>
                        </p>
                    </c:if>
                    <c:if test="${backOrderLog.backLogStatus eq '1'}">
                        <p>
                            <span class="track-date"><fmt:formatDate value="${backOrderLog.backLogTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            <i></i>
                            <span class="track-cont">申请退单审核(操作：顾客)<c:if test="${backOrderLog.backRemark != null}">留言：${backOrderLog.backRemark}</c:if></span>
                        </p>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>
    </div>

    <c:if test="${backOrderGeneral != null}">
        <div class="sf-tit"><b>退单物流信息</b></div>
        <div class="sf-item">
            退货物流名称:<span>${backOrderGeneral.ogisticsName}</span><br/>
        </div>
        <div class="sf-item">
            退货物流单号:<span>${backOrderGeneral.ogisticsNo}</span>
        </div>
    </c:if>

    <form id="submitForm" action="newsavebackorderdetail.htm" type="post">
        <div class="sf-tit"><b>操作（重要）</b></div>
        <div class="sf-item">
            <c:if test="${backorder.isBack eq '1'}">
                <c:if test="${backorder.backCheck eq '0'}">
                    <label><input type="radio" name="backLogStatus" class="backLogStatus" value="2">同意退单</label>
                    <label><input type="radio" name="backLogStatus" class="backLogStatus" value="3">拒绝退单</label>
                    <span class="remark_content" style="color:red;"></span>
                    <div class="sf-item">
                        <span>给客户留言：</span>
                        <textarea class="customerRemark" name="backRemark"></textarea>
                    </div>
                    <input type="hidden" name="backOrderId" value="${backorder.backOrderId}">
                    <input type="hidden" name="orderId" value="${orderId}">
                    <div class="sf-btns">
                        <input type="button" value="保存" onclick="saveorder();">
                        <input type="button" value="取消" onclick="cancle();">
                    </div>
                </c:if>
                <c:if test="${backorder.backCheck eq '1'}">
                    <label><input type="radio" name="_backLogStatus" onchange="showPrice(0)" class="backLogStatus" value="5">确认收货</label>
                    <label><input type="radio" name="_backLogStatus" onchange="showPrice(1)" class="backLogStatus" value="6">拒绝收货</label>
                    <span class="remark_content" style="color:red;"></span>
                    <div class="sf-item" id="orderProceStatus">
                    <label><input type="radio" name="backLogStatus" checked="checked" class="backLogStatus" value="7">退款</label>
                    <span>退款金额：<input type="text" class="checkBackPrice" name="backPrice" style="width:40px;"
                                      onkeyup="if(isNaN(value))execCommand('undo')"
                                      onafterpaste="if(isNaN(value))execCommand('undo')"
                            ></span>
                    <span class="remark_contents" style="color:red;"></span>
                    </div>
                    <div class="sf-item">
                        <span>给客户留言：</span>
                        <textarea class="customerRemark" name="backRemark"></textarea>
                    </div>
                    <input type="hidden" name="backOrderId" value="${backorder.backOrderId}">
                    <input type="hidden" name="orderId" value="${orderId}">
                    <div class="sf-btns">
                        <input type="button" value="保存" onclick="saveorder();">
                        <input type="button" value="取消" onclick="cancle();">
                    </div>
                </c:if>
                <c:if test="${backorder.backCheck eq '9'}">
                    <span>待客户填写物流地址！</span>
                </c:if>
                <c:if test="${backorder.backCheck eq '3'}">
                    <input type="hidden" id="backCheck_check" value="${backorder.backCheck}"/>
                    <label><input type="radio" name="backLogStatus" checked="checked" class="backLogStatus" value="7">退款</label>
                    <span>退款金额：<input type="text" class="checkBackPrice" name="backPrice" style="width:40px;"
                                  onkeyup="if(isNaN(value))execCommand('undo')"
                                  onafterpaste="if(isNaN(value))execCommand('undo')"
                            ></span>
                    <span class="remark_contents" style="color:red;"></span>
                    <div class="sf-item">
                        <span>给客户留言：</span>
                        <textarea class="customerRemark" name="backRemark"></textarea>
                    </div>
                    <input type="hidden" name="backOrderId" value="${backorder.backOrderId}">
                    <input type="hidden" name="orderId" value="${orderId}">
                    <div class="sf-btns">
                        <input type="button" value="保存" onclick="saveorder();">
                        <input type="button" value="取消" onclick="cancle();">
                    </div>
                </c:if>
                <c:if test="${backorder.backCheck eq '2'}">
                    <div class="sf-item">
                        <span>给客户留言：</span>
                        ${backorder.backRemark}
                    </div>
                </c:if>
            </c:if>

            <c:if test="${backorder.isBack eq '2'}">
                <c:if test="${backorder.backCheck eq '0'}">
                    <label><input type="radio" name="backLogStatus" class="backLogStatus" value="2">同意退单</label>
                    <label><input type="radio" name="backLogStatus" class="backLogStatus" value="3">审核不通过</label>
                    <span class="remark_content" style="color:red;"></span>
                    <div class="sf-item">
                        <span>给客户留言：</span>
                        <textarea class="customerRemark" name="backRemark"></textarea>
                    </div>
                    <input type="hidden" name="backOrderId" value="${backorder.backOrderId}">
                    <input type="hidden" name="orderId" value="${orderId}">
                    <div class="sf-btns">
                        <input type="button" value="保存" onclick="saveorder();">
                        <input type="button" value="取消" onclick="cancle();">
                    </div>
                </c:if>
                <c:if test="${backorder.backCheck eq '3'}">
                    <label><input type="radio" name="backLogStatus" class="backLogStatus" value="7">退款</label>
                    <span class="remark_content" style="color:red;"></span>
                    <span>退款金额：<input type="text" name="backPrice" style="width:40px;"></span>
                    <div class="sf-item">
                        <span>给客户留言：</span>
                        <textarea class="customerRemark" name="backRemark"></textarea>
                    </div>
                    <input type="hidden" name="backOrderId" value="${backorder.backOrderId}">
                    <input type="hidden" name="orderId" value="${orderId}">
                    <div class="sf-btns">
                        <input type="button" value="保存" onclick="saveorder();">
                        <input type="button" value="取消" onclick="cancle();">
                    </div>
                </c:if>
            </c:if>
        </div>
    </form>
    <input type="hidden" class="flag_saved" value="0">
</div>
<script src="<%=basePath %>js/jquery.min.js"></script>
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/order/backorderlist.js"></script>
<script>
    $("#orderProceStatus").hide();
    $("input[name=backRemark]").keypress(function (event){
        if(event.keyCode==13) {
            event.preventDefault();
        }
    })

</script>
<script>
    $(function(){
        $('#mainFrame').load(function(){
            alert($('#mainFrame').contents());
        });
    });
</script>
</body>
</html>
