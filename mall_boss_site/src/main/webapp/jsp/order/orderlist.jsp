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
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

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

            <h2 class="main_title">订单列表 <small id="countrows">(共${map.pageBean.rows }条)</small></h2>

            <div class="common_data p20">

              <div class="filter_area">
                <form role="form" class="form-inline">
                <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">订单号</span>
                      <input type="text" class="form-control" id="orderCode" value="${order.orderCode }">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">收货人</span>
                      <input type="text" class="form-control" id="shippingPerson" value="${order.shippingPerson }">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">联系电话</span>
                      <input type="text" class="form-control" id="shippingMobile"onkeyup="value=value.replace(/[^\d]/g,'') "
                                   onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${order.shippingMobile }">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group date form_datetime w300">
                      <span class="input-group-addon">开始时间</span>
                      <input class="form-control" type="text" id="startTime" value="${order.startTime}" readonly>
                      <span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
                      <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group date form_datetime w300">
                      <span class="input-group-addon">结束时间</span>
                      <input class="form-control" type="text" value="${order.endTime}" id="endTime" readonly>
                      <span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span>
                      <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                  </div>
                  <%--<div class="form-group">--%>
                    <%--<div class="input-group">--%>
                      <%--<span class="input-group-addon">订单状态</span>--%>
                      <%--<select id="orderStatus" value="${order.orderStatus}" class="form-control">--%>
                          <%--<option value="">--请选择--</option>--%>
                          <%--<option value="0" <c:if test="${order.orderStatus eq '0'}">selected ="selected"</c:if>>未付款</option>--%>
                          <%--<option value="1" <c:if test="${order.orderStatus eq '1'}">selected ="selected"</c:if>>已付款</option>--%>
                          <%--<option value="3" <c:if test="${order.orderStatus eq '3'}">selected ="selected"</c:if>>订单完成</option>--%>
                          <%--<option value="4" <c:if test="${order.orderStatus eq '4'}">selected ="selected"</c:if>>已取消</option>--%>
                          <%--<option value="7" <c:if test="${order.orderStatus eq '7'}">selected ="selected"</c:if>>退单审核中</option>--%>
                          <%--<option value="8" <c:if test="${order.orderStatus eq '8'}">selected ="selected"</c:if>>同意退货</option>--%>
                          <%--<option value="9" <c:if test="${order.orderStatus eq '9'}">selected ="selected"</c:if>>拒绝退货</option>--%>
                          <%--<option value="10" <c:if test="${order.orderStatus eq '10'}">selected ="selected"</c:if>>待商家收货</option>--%>
                          <%--<option value="11" <c:if test="${order.orderStatus eq '11'}">selected ="selected"</c:if>>退单结束</option>--%>
                          <%--<option value="12" <c:if test="${order.orderStatus eq '15'}">selected ="selected"</c:if>>退款审核</option>--%>
                          <%--<option value="13" <c:if test="${order.orderStatus eq '13'}">selected ="selected"</c:if>>拒绝退款 </option>--%>
                          <%--<option value="18" <c:if test="${order.orderStatus eq '18'}">selected ="selected"</c:if>>退款成功 </option>--%>
                      <%--</select>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">用户名</span>
                      <input type="text" class="form-control" id="customerUsername" value="${order.customerUsername }">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">业务员</span>
                        <select  class="form-control cate_selector" name="salesmanId" id="salesmanId" >
                            <option value="0">全部</option>
                            <c:forEach items="${enableSalaList}" var="salesman">
                                <option value="${salesman.salesmanId}"
                                        <c:if test="${order.salesmanId==salesman.salesmanId}">selected ="selected"</c:if>>${salesman.salesmanName}</option>
                            </c:forEach>
                        </select >
                    </div>
                  </div>


                  <div class="form-group">
                    <button type="button" class="btn btn-primary" onclick="submitOrder();">搜索</button>
                  </div>
                    <div class="form-group">

                        <button type="button" class="btn btn-info" onclick="window.location.href='<%=basePath %>toaddorder.htm?CSRFToken=${token }'">
                            <i class="glyphicon glyphicon-plus"></i>
                            添加订单
                        </button>

                        <a type="button" class="btn btn-info" href="exportallorder.htm?CSRFToken=${token }">
                            导出订单信息
                        </a>
                    </div>
                </form>
              </div>


                <div class="table_tabs" id="order_tabs">
                    <ul class="flagli">
                        <li class="<c:if test="${status=='1'}">active</c:if><c:if test="${status==null||status==''}">active</c:if>">
                            <a href="javascript:;" data-type="1">全部订单</a>
                        </li>
                        <li class="<c:if test="${status=='2'}">active</c:if>">
                            <a href="javascript:;" data-type="2">待发货</a>
                        </li>
                        <li class="<c:if test="${status=='3'}">active</c:if>">
                            <a href="javascript:;" data-type="3">已发货</a>
                        </li>
                        <li class="<c:if test="${status=='4'}">active</c:if>">
                            <a href="javascript:;" data-type="4">待付款</a>
                        </li>
                        <li class="<c:if test="${status=='5'}">active</c:if>">
                            <a href="javascript:;" data-type="5">货到付款</a>
                        </li>
                        <li class="<c:if test="${status=='6'}">active</c:if>">
                            <a href="javascript:;" data-type="6">已完成</a>
                        </li>

                        <li class="<c:if test="${status=='7'}">active</c:if>">
                            <a href="javascript:;" data-type="7">已取消</a>
                        </li>
                        <li class="<c:if test="${status=='8'}">active</c:if>">
                            <a href="javascript:;" data-type="8">手机订单</a>
                        </li>
                    </ul>
                </div>
              <table class="table order_table">
                <thead>
                <tr>
                  <th width="45%">商品</th>
                  <th class="text-center">总价/数量</th>
                  <th class="text-center" width="140px;">买家信息</th>
                  <th class="text-center w100">下单时间</th>
                  <th class="text-center">订单状态</th>
                  <th class="text-center">实付金额</th>
                  <th class="text-center">业务员信息</th>
                  <th class="text-center">商家</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${map.pageBean.list}" var="order" varStatus="i">

                   <%--全部订单--%>
                        <tr class="table_blank data1 status">
                            <td colspan="8" style="padding:0px;"></td>
                        </tr>
                <tr class="order_head data1 status">
                  <td colspan="8">
                      <div class="checkbox">
                          <label>
                              <input type="checkbox" class="order_select" name="orderId" value="${order.orderId }">
                              订单号：${order.orderCode } <c:if test="${order.orderMType==2 }"><img style="vertical-align:middle; height: 15" alt="" src="<%=basePath%>/images/phone.png"></c:if><span style="padding-left: 10px;  font-weight:bold;  color: #002a80;"><c:if test="${order.orderLinePay eq 1}">在线支付</c:if>
				  <c:if test="${order.orderLinePay eq 0}">货到付款</c:if></span>
                          </label>
                      </div>

                    <div class="btns" style="top:20px;">
                      <a href="javascript:;" onclick="fnOpen(${order.orderId })">查看详情</a>
                      <b> - </b>
                       <c:if test="${order.payId eq 1}">
                           <c:if test="${(order.orderStatus eq '0')}">
                                <a href="javascript:;" onclick="fnModifyOrder(${order.orderId},
                                <c:if test="${order.orderStatus eq 0 && order.orderCargoStatus ne 4 && order.orderLinePay eq 1}">1</c:if>
                                <c:if test="${order.orderCargoStatus eq 4 && order.orderLinePay eq 1}">2</c:if>
                                <c:if test="${order.orderLinePay eq 0 && order.orderStatus eq 2}">3</c:if>,${order.orderLinePay });">修改状态</a>
                                <b> - </b>
                            </c:if>
                        </c:if>
				    	<c:if test="${order.orderStatus eq '0'}">
				    		<a href="javascript:;" onclick="modifyPrice(${order.orderId},${order.orderCode },${order.orderPrice})">修改金额</a>
                      		<b> - </b>
                      		<a href="javascript:;" onclick="fnModifyOrderCancel(${order.orderId},4,${order.orderCode })">取消订单</a>
                      		<b> - </b>
           				</c:if>
                      <a href="javascript:;" onclick="orderlog(${order.orderId })">操作日志</a>
                    </div>
                  </td>
                </tr>
                <tr class="data1 status">
                  <td>
                    <div class="mini_goods" style="text-align:left;">
                    <c:forEach items="${order.orderGoodsList}" var="ordergoods" varStatus="i">
                      <a href="${bsetaddress }/item/${ordergoods.goodsInfoId}.html" target="_blank"><img alt="" title="${ordergoods.goodsProductVo.goodsInfoName}" src="${ordergoods.goodsProductVo.goodsInfoImgId}" height="50"></a>
                    </c:forEach>
                    </div>
                  </td>
                  <td align="center">
                    <p>${order.orderOldPrice}</p>
                    <p>(${fn:length(order.orderGoodsList)}件)</p>
                  </td>
                  <td align="center">
                      <%--<p>${order.pointLevelName }</p>--%>
                   <c:if test="${ !empty order.customerNickname }">
                  <c:if test="${ order.companyName!=null}"> <p>企业名称:${ order.companyName }</c:if>
                       <c:if test="${ order.companyName == null}"><p>用户名:${ order.customerNickname }</c:if></p></c:if>
                      <c:if test="${ !empty order.shippingPerson }"><p>收件人: ${order.shippingPerson }</p></c:if>
                      <c:if test="${ !empty order.shippingMobile }"> <p>${order.shippingMobile }</p></c:if>
                  </td>
                  <td>
                   	<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${order.createTime }" type="both"/>
                  </td>
                  <td align="center">
                  	<c:if test="${order.orderStatus eq 0}">
                        <c:if test="${order.orderLinePay eq 1}">
                      <a href="javascript:;"><span class="label label-danger">

                          待付款

                      </span></a>
                        </c:if>
                        <c:if test="${order.orderLinePay eq 0}">
                            <a href="javascript:;"><span class="label label-danger">

                         待发货

                      </span></a>

                        </c:if>
                    </c:if>
					<c:if test="${order.orderStatus eq 1}"><a href="javascript:;"><span class="label label-danger">待发货</span></a></c:if>
					<c:if test="${order.orderStatus eq 2}"><a href="javascript:;"><span class="label label-info">已发货</span></a></c:if>
					<c:if test="${order.orderStatus eq 3}"><a href="javascript:;"><span class="label label-info">已完成</span></a></c:if>
					<c:if test="${order.orderStatus eq 4}"><a href="javascript:;"><span class="label label-default">已取消</span></a></c:if>
					<c:if test="${order.orderStatus eq 14}"><a href="javascript:;"><span class="label label-danger">退单审核中</span></a></c:if>
					<c:if test="${order.orderStatus eq 8}"><a href="javascript:;"><span class="label label-info">同意退货</span></a></c:if>
					<c:if test="${order.orderStatus eq 9}"><a href="javascript:;"><span class="label label-info">拒绝退货</span></a></c:if>
					<c:if test="${order.orderStatus eq 10}"><a href="javascript:;"><span class="label label-info">待商家收货</span></a></c:if>
					<c:if test="${order.orderStatus eq 11}"><a href="javascript:;"><span class="label label-info">退单结束</span></a></c:if>
					<c:if test="${order.orderStatus eq 15}"><a href="javascript:;"><span class="label label-danger">退款审核中</span></a></c:if>
					<c:if test="${order.orderStatus eq 13}"><a href="javascript:;"><span class="label label-info">拒绝退款</span></a></c:if>
                    <c:if test="${order.orderStatus eq 16}"><a href="javascript:;"><span class="label label-info">商家收货失败</span></a></c:if>
                    <c:if test="${order.orderStatus eq 17}"><a href="javascript:;"><span class="label label-info">已退款</span></a></c:if>
                    <c:if test="${order.orderStatus eq 18}"><a href="javascript:;"><span class="label label-info">退款成功</span></a></c:if>

                      <p style="color: red; font-family: '微软雅黑'">
                          <c:if test="${order.orderStatus eq 17}">
                                -${order.backPrice}
                          </c:if>
                      </p>
                  </td>
                  <td align="center">${order.orderPrice }</td>
                  <td align="center">${order.salesmanName }</td>
                  <td align="center"><c:if test="${order.storeName!=''}">${order.storeName}</c:if><c:if test="${order.storeName==''||order.storeName==null}">BOSS</c:if></td>
                </tr>


                </c:forEach>
                </tbody>
              </table>


                <c:import url="../page/searchPag.jsp">
                    <c:param name="pageBean" value="${map.pageBean }"/>
                    <c:param name="path" value="../"></c:param>
                </c:import>

                <%--<div id="pagefoot">--%>

                    <%--<div class="table_pagenav pull-right">--%>
                        <%--<div class="meneame">--%>

                            <%--<c:if test="${pageBean.pageNo!=1 }">--%>
                                <%--<a  href="javascript:void(0);" onclick="changeNextPage(${pageBean.pageSize },${pageBean.pageNo-1 })"> ${basePath}上一页 </a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${pageBean.pageNo==1 }">--%>
                                <%--<span class="disabled"> 上一页 </span>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${pageBean.startNo>2}">--%>
                                <%--<a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },1)" >1 </a>--%>
                                <%--<span class="current"> ...</span>--%>
                            <%--</c:if>--%>
                            <%--<c:forEach begin="${pageBean.startNo }" end="${pageBean.endNo}" varStatus="sta">--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${pageBean.pageNo==(pageBean.startNo+sta.count-1)}">--%>
                                        <%--<span class="current"> ${pageBean.startNo+sta.count-1}</span>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <%--<a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },${pageBean.startNo+sta.count-1})" >${pageBean.startNo+sta.count-1} </a>--%>
                                    <%--</c:otherwise>--%>
                                <%--</c:choose>--%>

                            <%--</c:forEach>--%>

                            <%--<c:if test="${pageBean.endNo<pageBean.totalPages}">--%>
                                <%--<span class="current"> ...</span>--%>
                                <%--<a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },${pageBean.totalPages })" >${pageBean.totalPages }</a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${pageBean.pageNo!=pageBean.totalPages}">--%>
                                <%--<a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },${pageBean.pageNo+1 })"> 下一页 </a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${pageBean.pageNo==pageBean.totalPages }">--%>
                                <%--<span class="disabled"> 下一页 </span>--%>
                            <%--</c:if>--%>

                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="table_ctrl pull-left">--%>
                        <%--<form role="form" class="form-inline">--%>
                            <%--<label class="control-label">每页显示：</label>--%>
                            <%--<!--    <input type="text"  class="form-control"> -->--%>
                            <%--<select class="form-control" onChange="changePage(this);">--%>
                                <%--<option value="10" <c:if test="${pageBean.pageSize==10 }">selected="selected"</c:if>>10</option>--%>
                                <%--<option value="15" <c:if test="${pageBean.pageSize==15 }">selected="selected"</c:if>>15</option>--%>
                                <%--<option value="20" <c:if test="${pageBean.pageSize==20 }">selected="selected"</c:if>>20</option>--%>
                                <%--<option value="30" <c:if test="${pageBean.pageSize==30 }">selected="selected"</c:if>>30</option>--%>
                                <%--<option value="50" <c:if test="${pageBean.pageSize==50 }">selected="selected"</c:if>>50</option>--%>
                                <%--<option value="100" <c:if test="${pageBean.pageSize==100 }">selected="selected"</c:if>>100</option>--%>
                            <%--</select>--%>
                        <%--</form>--%>
                    <%--</div>--%>
                    <%--<div class="clr"></div>--%>

                    <%--</div>--%>





            </div>

          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->

    <div class="modal fade" id="orderDetails"  role="dialog">
      <div class="modal-dialog modal-lg">
          <!--startprint1-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title"  id="detailorderCodeNo">订单号：E20150427141513921635451</h4>
          </div>

          <div class="modal-body">

          <div class="order_flow">
            <ul id="status">
              <li class="active">
                <p class="name">已下单</p>
                <p class="bar"><i>1</i></p>
                <p class="time">2015-04-27 14:15:13</p>
              </li>
              <li class="active">
                <p class="name">已付款</p>
                <p class="bar"><i>2</i></p>
                <p class="time">2015-04-27 14:15:13</p>
              </li>
              <li>
                <p class="name">已发货</p>
                <p class="bar"><i>3</i></p>
                <p class="time">2015-04-27 14:15:13</p>
              </li>
              <li>
                <p class="name">已完成</p>
                <p class="bar"><i>4</i></p>
                <p class="time">2015-04-27 14:15:13</p>
              </li>
            </ul>
          </div>

          <div class="order_info">
            <h4>订单概况</h4>
            <div class="container-fluid">
              <div class="row">
                <div class="col-sm-12">
                  <p  id="detailorderCode">订单编号：20150228143717</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailcreateTime">下单时间：2015-02-28 14:37:17</p>
                </div>
                <div class="col-sm-12">
                  <p>订单状态：<span class="text-danger" id="detailorderStatus">已付款未发货</span></p>
                </div>
                <div class="col-sm-12">
                  <p id="detailcouponNo">使用优惠券：</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailorderOldPrice">订单原始金额：172.01</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailorderPrePrice">订单优惠金额：0.00</p>
                </div>
                <div class="col-sm-12">
                  <p>订单交易金额：<span id="detailorderPrice" class="text-danger">172.01</span></p>
                </div>
                <div class="col-sm-12">
                  <p id="detailorderIntegral">订单使用积分：</p>
                </div>
                  <div class="col-sm-12">
                      <p id="detailorederRemark">取消订单原因：</p>
                  </div>
                  <div class="col-sm-12">
                      <p id="detailorederRetime">取消订单时间：</p>
                  </div>
              </div>
            </div>

            <h4>快递信息</h4>
            <div class="container-fluid" >
                <div class="row" id="relations">

                </div>
            </div>


            <h4>物流信息</h4>
            <div class="container-fluid">
              <div class="row">
                <div class="col-sm-12">
                  <p id="detailorderExpress">配送方式：快递配送</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailexpressPrice">运费：	12.00</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailshippingaddress">收货地址：安徽 安庆 安庆市</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailaddress">详细地址：dsdsd</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailshippingPerson">收货人：dsdsd</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailshippingPhone">联系电话：</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailshippingMobile">手机：	13913021285</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailshippingPostcode">邮编：</p>
                </div>
                <div class="col-sm-12">
                  <p id="detailcustomerRemark">客户留言：</p>
                </div>
              </div>
            </div>

            <h4>发票信息</h4>
            <div class="container-fluid">
              <div class="row" id="invoiceType">
                
              </div>
            </div>

          </div>

          <table class="table  table-bordered">
            <thead>
            <tr>
              <th width="40%">商品名称</th>
              <th class="text-center">原始价格</th>
              <th class="text-center">数量</th>
              <th class="text-center">商品规格</th>
              <th class="text-center">交易价格</th>
              <th class="text-center">商品总价</th>
            </tr>
            </thead>
            <tbody id="ordergoods">
            <tr>
              <td>
                <div class="data_item">
                  <img alt="" src="images/good_2_small.jpg">
                  <p>贝亲（Pigeon）婴儿柔湿巾80片装（3包）PL135</p>
                  <p class="text-muted">80片*3包</p>
                </div>
              </td>
              <td class="text-center">39.80</td>
              <td class="text-center">1</td>
              <td class="text-center"></td>
              <td class="text-center">39.80</td>
              <td rowspan="2" class="text-center">5.00</td>
            </tr>
            <tr>
              <td>
                <div class="data_item">
                  <img alt="" src="images/good_2_small.jpg">
                  <p>贝亲（Pigeon）婴儿柔湿巾80片装（3包）PL135</p>
                  <p class="text-muted">80片*3包</p>
                </div>
              </td>
              <td class="text-center">39.80</td>
              <td class="text-center">1</td>
              <td class="text-center"></td>
              <td class="text-center">39.80</td>
            </tr>
            </tbody>
          </table>
              <!--endprint1-->
              <div style="text-align:center;">
                <button type="button" class="btn btn-primary Noprint" onclick="return doPrint();">打印</button>
              </div>
          </div>
        </div>

      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="orderLogmodal"  role="dialog">
    <div class="modal-dialog modal-lg">
    <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <h4 class="modal-title">操作日志</h4>
    </div>
    <div class="modal-body">
    <div class="common_info common_tabs mt20">
    <ul class="nav nav-tabs" role="tablist">
      <li role="presentation" class="active"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">订单操作日志</a></li>
      <%--<li role="presentation"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">退款日志</a></li>--%>
    </ul>
    <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="tab3">
      <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
          <th>操作类型</th>
          <th>操作人</th>
          <th>操作时间</th>
          <th>操作原因</th>
        </tr>
        </thead>
        <tbody id="orderLog">
        <tr>
          <td colspan="4"><p class="text-center">暂无可用数据！</p></td>
        </tr>

        </tbody>
      </table>
      <div class="table_foot">
        <div class="table_pagenav pull-right">
          <div class="meneame" id="pageorder">
            
          </div>
        </div>
        <div class="clr"></div>
      </div>
    </div>
    <div role="tabpanel" class="tab-pane" id="tab4">
      <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
          <th>操作类型</th>
          <th>操作时间</th>
          <th>操作详情</th>
        </tr>
        </thead>
        <tbody id="pageLog">
        <tr>
          <td colspan="3"><p class="text-center">暂无可用数据！</p></td>
        </tr>
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
        <tr>
          <td>3170</td>
          <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
          <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
          <td>99.00</td>
          <td>2014-07-02 14:16:41</td>
        </tr>
        <tr>
          <td>3170</td>
          <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
          <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
          <td>99.00</td>
          <td>2014-07-02 14:16:41</td>
        </tr>
        <tr>
          <td>3170</td>
          <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
          <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
          <td>99.00</td>
          <td>2014-07-02 14:16:41</td>
        </tr>
        </tbody>
      </table>
      <div class="table_foot">
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
      </div>
    </div>
    </div>
    </div>
    </div>
    </div>
    </div>
    </div>
	
	<!-- Modal -->
    <div class="modal fade" id="updateOrderStatusYN"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">操作提示</h4>
          </div>
          <div class="modal-body">

                	<p>
                		订单不能随意中断，你确定要修改吗？
					</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="updateOrderoYN();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Modal -->
    <div class="modal fade" id="updateOrderStatus"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">订单状态修改</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="updateOrder">
              <div class="form-group">
              	<input  type="hidden" name="CSRFToken" value="${sx}" />
				<input type="hidden" name="orderStatus"  id="oStatus">
				<input type="hidden" name="orderCodex"  id="orderCodex">
				<input type="hidden" name="orderId"  id="orId">
                <label class="control-label col-sm-5">中断原因：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                	<textarea class="form-control required" rows="5" cols="60" id="orReason" name="reason"></textarea>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="updateOrderoStatus();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Modal -->
    <div class="modal fade" id="verifylogin"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">验证身份</h4>
          </div>
          <div class="modal-body">
          	<form class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-5">请输入登录密码：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                	<input type="password" class="form-control required" name="userkey" id="pwd" />
					<label class="pwdtip"></label>
                </div>
              </div>
              </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="verifylogin();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

     <!-- Modal -->
    <div class="modal fade" id="changeOrderMoney"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改订单金额</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="upmoneyform">
              <div class="form-group">
              	<input type="hidden" name="CSRFToken" value="${sx}" />
				<input type="hidden" value="" class="modify_order_id" name="orderId">
				<input type="hidden" value="" class="modify_order_codex" name="orderCodex">
				<input type="hidden" id="modify_order_price"  name="price" class="ui-widget-content ui-corner-all j_text ">
				<input type="hidden" id="o_order_price"  class="ui-widget-content ui-corner-all j_text">
                <label class="control-label col-sm-5"><span class="text-danger">*</span>优惠的金额：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10" id="moneytipd">
                  <input type="text" class="form-control" name="userkey" id="order_price">
                  <label id="moneytip" class="error"></label>
                </div>
              </div>
                <br>
              <div class="form-group">
                <label class="control-label col-sm-5 required"><span class="text-danger">*</span>修改金额原因：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <textarea class="form-control" rows="5" id="up_orReason" name="reason"></textarea>
                    <label id="reasontip" style="color: #a94442;"></label>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="upmoney()">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="changeOrderStatus"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">设置订单状态</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="updateOrderst" action="" method="post">
              <div class="form-group">
                <label class="control-label col-sm-5">订单状态：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <select class="form-control" id="oStatusst" name="orderStatus">
                    <option value="0">未付款</option>
                    <option value="1">已付款</option>
                  </select>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="updateOrdersub();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Modal -->
    <div class="modal fade" id="upmoneyYN"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">操作提示</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal"  action="" method="post">
              <div class="form-group">
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <p>
                		订单金额不能随意修改，你确定要修改吗？
					</p>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="upmoneyYN();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
    

    <!-- Modal -->
    <div class="modal fade" id="changeOrderMoneypwd"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">验证身份</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="">
              <div class="form-group">
                <label class="control-label col-sm-5">请输入登录密码：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <input type="password" class="form-control required" name="userkey" id="changepwd">
                  <label class="pwdtip"></label>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="upmoneypwd()">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

	<input type="hidden" value="ordercxform" id="formId"/>
    <input type="hidden" value=<c:if test="${ order.businessId ==0}">"<%=basePath %>orderlist.htm"</c:if><c:if test="${ order.businessId !=0}">"<%=basePath %>orderlististhird.htm"</c:if> id="formName"/>
    <form role="form"  method="post" action=<c:if test="${ order.businessId ==0}">"<%=basePath %>orderlist.htm"</c:if><c:if test="${ order.businessId !=0}">"<%=basePath %>orderlististhird.htm"</c:if> id="ordercxform">
         <input type="hidden" name="orderCode" id="orderCodestr" value="${order.orderCode }" />
         <input type="hidden" name="shippingPerson" id="shippingPersonstr"  value="${order.shippingPerson}" />
         <input type="hidden" name="storeName" value="${order.storeName}" id="storeNamestr" />
         <input type="hidden" name="shippingMobile" value="${order.shippingMobile}" id="shippingMobilestr" />
         <input type="hidden" name="startTime" value="${order.startTime}" id="startTimestr" />
         <input type="hidden" name="endTime" value="${order.endTime}" id="endTimestr" />
         <input type="hidden" name="orderStatus" value="${order.orderStatus}" id="orderStatusstr" />
         <input type="hidden" name="customerUsername" value="${order.customerUsername}" id="customerUsernamestr" />
         <input type="hidden" name="salesmanId" value="${order.salesmanId}" id="salesmanIdstr" />
          <input type="hidden" name="wareId" value="${getWareId}" id="wareIdstr" />
   <input type="hidden" name="status" value="${status}" id="statusflag"/>
   </form>
    <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">


   <form action="printView.htm?CSRFToken=${sx}" id="printView" method="post" target="_blank">
       <input name="str"  value=""  type="hidden" id="str"/>
   </form>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/order/orderlist.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>


   <script>

       var formId=$("#formId").val();
       function changePage(obj){
           $("#"+$("#formId").val()).attr("action",$("#formName").val()).append("<input type='hidden' name='pageSize' value='"+$(obj).val()+"''>").submit();
       }

       function changeNextPage(pageSize,pageNo){
           $("#"+$("#formId").val()).attr("action",$("#formName").val())
                   .append("<input type='hidden' name='pageSize' value='"+pageSize+"''>")
                   .append("<input type='hidden' name='pageNo' value='"+pageNo+"''>")
                   .submit();
       }

       //新订单列表分页
       function doajaxgetfoot(status){
           $.ajax({
               url:"newajaxgetpagefoot.htm",
               data:{"status":status},
               async:false,
               success:function(data){

                   $("#pagefoot").html("");
                var pagefoot="";
                pagefoot+=' <div class="table_pagenav pull-right">';
                   pagefoot +='<div class="meneame">';

                   if(data.pageNo!=1){
                       pagefoot +='<a  href="javascript:void(0);" onclick="changeNextPage('+data.pageSize +'","'+data.pageNo-1+')"> ${basePath}上一页 </a>';
                   }
                  if(data.pageNo==1){
                      pagefoot +=' <span class="disabled"> 上一页 </span>' ;
                  }
                  if(data.startNo>2){
                      pagefoot +='<a  href="javascript:void(0);"  onclick="changeNextPage('+data.pageSize+',1)" >1 </a><span class="current"> ...</span>';
                  }

                  for(var i=data.startNo;i<= data.endNo;i++){
                      var str=parseInt( data.startNo)+ i-1;

                      if(data.pageNo==str){

                          pagefoot +='<span class="current"> '+str+'</span>';
                      }else{

                          pagefoot +=' <a  href="javascript:void(0);"  onclick="changeNextPage('+data.pageSize+','+str+')" >'+str+ '</a>';
                      }
                  }
                   if(data.endNo<data.totalPages){
                       pagefoot +='<span class="current"> ...</span> ';
                       pagefoot+='<a  href="javascript:void(0);"  onclick="changeNextPage('+data.pageSize +','+data.totalPages+')" >'+data.totalPages +'</a>'
                   }
                if(data.pageNo!=data.totalPages){
                    pagefoot+=' <a  href="javascript:void(0);"  onclick="changeNextPage('+data.pageSize +','+data.pageNo+1+')"> 下一页 </a>';
                }
                   if(data.pageNo==data.totalPages){
                       pagefoot+='<span class="disabled"> 下一页 </span>'
                   }
pagefoot+=' </div></div>';

                   pagefoot+=' <div class="table_ctrl pull-left">';
                   pagefoot+='<form role="form" class="form-inline">';
                   pagefoot+= '<label class="control-label">每页显示：</label>';
                   pagefoot+='<select class="form-control" onChange="changePage(this);">';
                   pagefoot+='<option value="10" ';
                   if(data.pageSize==10){
                       pagefoot+='selected="selected"';
                   }
                   pagefoot+='>10</option>';
                   pagefoot+='<option value="15" ';
                   if(data.pageSize==15){
                       pagefoot+='selected="selected"';
                   }
                   pagefoot+='>15</option>';
                   pagefoot+='<option value="20" ';
                   if(data.pageSize==20){
                       pagefoot+='selected="selected"';
                   }
                   pagefoot+='>20</option>';
                   pagefoot+='<option value="30" ';
                   if(data.pageSize==30){
                       pagefoot+='selected="selected"';
                   }
                   pagefoot+='>30</option>';

                   pagefoot+='<option value="50" ';
                   if(data.pageSize==50){
                       pagefoot+='selected="selected"';
                   }
                   pagefoot+='>50</option>';

                   pagefoot+='<option value="100" ';
                   if(data.pageSize==100){
                       pagefoot+='selected="selected"';
                   }
                   pagefoot+='>100</option>';
                   pagefoot+=' </select> </form></div><div class="clr"></div>';
                $("#pagefoot").html(pagefoot);
               }
           });
       }
       $(function(){

           $("#statusflag").val($(".flagli .active").find("a").attr("data-type"));


           /* 订单选择以后整体高亮 */
           $('body').on('change','.order_select',function(){
               var $orderCheck = $(this);
               if($orderCheck.is(':checked')){
                   $orderCheck.parents('td').css({backgroundColor : '#FFFFEA'});
                   $orderCheck.parents('tr').next().find('td').css({backgroundColor : '#FFFFEA'});
               }
               else{
                   $orderCheck.parents('td').css({backgroundColor : '#FAFAFA'});
                   $orderCheck.parents('tr').next().find('td').css({backgroundColor : '#FFFFFF'});
               }
           });

           /* 订单筛选交互演示用*/
           $('#order_tabs a').click(function(){
               $that = $(this);
               $that.parent().addClass('active');
               $that.parent().siblings().removeClass('active');
               var _cla=$that.attr("data-type")

               $("#statusflag").val($(".flagli .active").find("a").attr("data-type"));
               submitOrder();

//              $('.order_table tbody').html('<tr class="table_blank"><td colspan="7"></td></tr><tr><td colspan="7"><p class="text-center"><img alt="" src="js/artDialog/skins/icons/loading.gif"> 数据载入中...</p></td></tr>');
//               setTimeout(function(){
//                   $(".status").hide();
//                   $(".data"+_cla).show();
//                   doajaxgetfoot($("#statusflag").val());
//               },1);



           });

       });
   </script>
  </body>
</html>
