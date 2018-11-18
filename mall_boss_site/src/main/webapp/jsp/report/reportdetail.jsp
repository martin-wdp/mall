<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=basePath%>js/jquery-ui-css/jquery-ui-1.10.3.custom.css">
<link rel="stylesheet" href="<%=basePath%>css/j	query-ui.css">
<link rel="stylesheet" href="<%=basePath%>css/base.min.css">
<link rel="stylesheet" href="<%=basePath%>css/customer.css">
<link rel="stylesheet" href="<%=basePath%>css/jquery.ad-gallery.css">
<link rel="stylesheet" href="<%=basePath%>css/base.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-ui/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="<%=basePath%>js/customer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.ad-gallery.pack.js"></script>
<script charset="utf-8" src="<%=basePath%>js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单详细信息</title>
</head>
<body>

    
    <div class="pd_wp mt10" id="report_detail">
    	<div class="pd_box">
    		<table class="pd_tb w">
    			<thead>
    				<tr>
    					<th colspan="4">报表明细(<fmt:formatDate pattern="yyyy-MM-dd" value="${report.startTime}" type="both"/>~<fmt:formatDate pattern="yyyy-MM-dd" value="${report.endTime }" type="both"/>)</th>
    				</tr>
    			</thead>
    			<tbody>
    			    <tr>
                        <td width="20%">报表时间</td>
                        <td width="30%" colspan="3"><fmt:formatDate pattern="yyyy-MM-dd" value="${report.startTime}" type="both"/>~<fmt:formatDate pattern="yyyy-MM-dd" value="${report.endTime }" type="both"/></td>
                    </tr>
    				<tr>
    					<td width="20%">商户名</td>
    					<td width="30%">${report.storeName }</td>
    					<td width="20%">公司名称</td>
    					<td width="30%">${report.companyName }</td>
    				</tr>
    				<tr>
    					<td width="20%">分类名称:</td>
    					<td width="30%">${report.cateName }</td>
    					<td width="20%">类目扣率:</td>
                        <td width="30%">${report.cateRate }元</td>
    				</tr>
    				<tr>
                        <td width="20%">总流水:</td>
                        <td width="30%">${report.totalOrderMoney }元</td>
                        <td width="20%">结算状态:</td>
                        <td width="30%">
	                        <c:if test="${report.settleStatus=='0'}">
	                            <span class="tb_error" title="未结算"></span>
	                        </c:if>
	                        <c:if test="${report.settleStatus=='1'}">
	                            <span class="tb_right" title="已结算"></span>
	                        </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%">总订单优惠:</td>
                        <td width="30%">${report.totalOrderPrePrice }元</td>
                        <td width="20%">总商品优惠:</td>
                        <td width="30%">${report.totalGoodsPrePrice }元</td>
                    </tr>
    				<tr class="opr">
    					<td class="tr" colspan="4">
    					   <button onclick="doPrint()">打印</button>
<%--     					   <button style="margin-left:20px;" onclick="exportReportDetail(${report.reportId})">导出</button> --%>
<%-- 	    					<button style="margin-left:20px;" onclick="queryReportOrders(${report.reportId},${report.storeId },'<fmt:formatDate pattern="yyyy-MM-dd" value="${report.startTime}" type="both"/>','<fmt:formatDate pattern="yyyy-MM-dd" value="${report.endTime }" type="both"/>')">订单明细</button> --%>
<%-- 	    					<button style="margin-left:20px;" onclick="queryReportBackOrders(${report.reportId},${report.storeId },'<fmt:formatDate pattern="yyyy-MM-dd" value="${report.startTime}" type="both"/>','<fmt:formatDate pattern="yyyy-MM-dd" value="${report.endTime }" type="both"/>')">退单明细</button> --%>
	    					<button style="margin-left:20px;" onclick="window.close();">关闭</button>
    					</td>
    				</tr>
    		</table>
    	</div>
    </div>
</body>  

</html>  
