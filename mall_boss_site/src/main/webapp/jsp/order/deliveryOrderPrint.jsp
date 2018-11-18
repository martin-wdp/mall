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
    <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>/css/font-awesome.min.css">
    <link href="<%=basePath %>/iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>/css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/style.css" rel="stylesheet">
    <link href="<%=basePath %>/css/print.css" rel="stylesheet">
    <style media=print>
        .Noprint{display:none;}
        .PageNext{page-break-after:   always;}
    </style>
    <style type="text/css">
    	.express_board{position:relative;border:0;height:600px;margin:10px;padding:20px;}
    </style>
    <title>打印</title>
</head>
<body style="background-color: #fff;width: 100%;">
<!--startprint1-->
<div class="printBox">
    <div class="top">
        <div class="logo" style="background-color: transparent;">
            <ul>
                <li style="text-align:left;"><img src="images/p_logo.png"><p><label>订单编号：</label><em>${order.orderCode}</em></p></li>
                <li style="text-align:center;"><h3>24小时服务热线</h3><br/><h2>96008</h2><p><label>下单日期：</label><em><fmt:formatDate value='${order.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/></em></p></li>
                <li style="text-indent:4em;"><img src="images/p_ewm.png"></li>
            </ul>
        </div>
        <div class="mes">
            <ul>
                <li><label>客户姓名：</label><span>${order.shippingPerson}</span></li>
                <li><label>联系电话：</label><span>${order.shippingMobile}</span></li>
                <li><label>客户类型：</label><span><c:choose><c:when test="${order.isEnterprise == 1}">企业用户</c:when><c:otherwise>个人</c:otherwise></c:choose></span></li>
                <li><label>支付方式：</label><span><c:choose><c:when test="${order.orderLinePay == 1}">在线支付</c:when><c:otherwise>货到付款</c:otherwise></c:choose></span></li>
                <li style="width:50%;"><label>客户地址：</label><span>${order.shippingProvince}${order.shippingCity}${order.shippingCounty}${order.shippingAddress}</span></li>
                <li style="width:50%;"><label>发票抬头：</label><span>${order.invoiceTitle}</span></li>
            </ul>
        </div>
    </div>
    <c:if test="${not empty relations}">
    <div class="table">
    <c:forEach var="relation" varStatus="status" items="${relations}">
        <c:if test="${fn:length(relations) >1}">
            <br/>
            装箱单${status.index+1}
        </c:if>
        <table width="100%" border="0" cellpadding="1" cellspacing="0">
            <tr style="height:30px;"><th width="200">商品编号</th><th>商品名称(商品SKU)</th><th width="114">规格</th><th width="74">单价</th><th>数量</th><th width="65">小计</th></tr>
            <c:forEach var="containers" varStatus="status1" items="${relation.containers}">
                <tr>
                    <td>${containers.goodsProductDetailViewVo.goodsInfoItemNo}</td>
                    <td class="tdcont">${containers.goodsProductDetailViewVo.goodsInfoName}</td>
                    <c:forEach items="${order.orderGoodsList}" var="orderGoods" varStatus="status2">
                        <c:if test="${orderGoods.goodsInfoId eq containers.goodsProductDetailViewVo.goodsInfoId}">
                            <c:if test="${not empty orderGoods.goodsProductReleSpecVoList}">
                                <td>
                                    <c:forEach items="${orderGoods.goodsProductReleSpecVoList}" var="spec" varStatus="status3">
                                        <c:choose>
                                            <c:when test="${not empty spec.specValueRemark}">
                                                ${spec.spec.specName}:${spec.specValueRemark}
                                            </c:when>
                                            <c:otherwise>
                                                ${spec.spec.specName}:${spec.goodsSpecDetail.specDetailName}
                                            </c:otherwise>
                                        </c:choose>
                                        <c:if test="${!status3.last}">
                                            </br>
                                        </c:if>
                                    </c:forEach>

                                </td>
                            </c:if>
                            <td><fmt:formatNumber value="${orderGoods.goodsInfoPrice}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> </td>
                            <td>${containers.goodsNum}</td>
                            <td><fmt:formatNumber value="${containers.goodsNum * orderGoods.goodsInfoPrice}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> </td>

                        </c:if>

                    </c:forEach>
                </tr>
            </c:forEach>
            <tr><td colspan="6" style="color:red;text-align:left;text-indent:1em;">备注：${order.customerRemark}</td></tr>
        </table>
    </c:forEach>
    </div>
    </c:if>
    <div class="bottom">
        <div class="left">
            <ul>
                <li><label>温馨提示：</label>如需要退换货，请先登录京华亿家网站申请，再退到如下地址</li>
                <li><label>退货地址：</label>京华亿家</li>
                <li><label>收件人：</label>京华亿家售后部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>电话：</label>96008</li>
                <li>非常感谢您在京华亿家商城app购物，我们期待您的再次光临！</li>
            </ul>
        </div>
        <div class="right">
            <ul>
                <li><label>订单总额：</label><em>${order.orderOldPrice}</em></li>
                <li><label>运费：</label><em>${order.expressPrice}</em></li>
                <li><label>小计：</label><em>${order.orderOldPrice+order.expressPrice}</em></li>
                <li><label>促销折扣：</label><em>-${order.orderPrePriceOrder}</em></li>
                <li><label>优惠券抵扣金额：</label><em>-${order.orderPrePrice}</em></li>
                <li><label>积分抵扣金额：</label><em>-<c:if test="${empty order.orderIntegral}">0</c:if><c:if test="${not empty order.orderIntegral}"><fmt:formatNumber value="${order.orderIntegral/100}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if></em></li>
                <li><label>应付款金额：</label><em>${order.orderPrice}</em></li>
            </ul>
        </div>
    </div>
</div>
	<!--endprint1-->

</body>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/jquery.min.js"></script>
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script type="text/javascript">
    $(function(){
        doPrint();
    });
    function doPrintSetup(){
        //打印设置
        document.all.WebBrowser.ExecWB(8,1)
    }
    function doPrintPreview(){
        //打印预览
        document.all.WebBrowser.ExecWB(7,1)
    }
    function doPrint() {
        pagesetup_null();
        window.print();

    }

    //设置网页打印的页眉页脚为空
    function pagesetup_null(){
        var hkey_root,hkey_path,hkey_key;
        hkey_root="HKEY_CURRENT_USER";
        hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
        try{
            var RegWsh = new ActiveXObject("WScript.Shell");
            hkey_key="header";
            RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
            hkey_key="footer";
            RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
        }catch(e){}
    }
</script>
</html>