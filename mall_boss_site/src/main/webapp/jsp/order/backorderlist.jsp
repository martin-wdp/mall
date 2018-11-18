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
    <div class="container-fluid page_body">
      <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <div class="main_cont">
            <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

            <div class="common_data p20">

              <div class="filter_area">
                <form role="form" class="form-inline">
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">退单号</span>
                      <input type="text" class="form-control" id="backOrderCode" value="${pageBean.objectBean.backOrderCode}">
                    </div>
                  </div>
                   <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">收货人</span>
                      <input type="text" class="form-control" id="shippingPerson" value="${pageBean.objectBean.shippingPerson}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">联系电话</span>
                     <input type="text" class="form-control" id="shippingMobile" value="${pageBean.objectBean.shippingMobile}">
                    </div>
                  </div>
                
		         <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">订单状态</span>
                      <select class="form-control" id="up_backCheck" value="${pageBean.objectBean.backCheck}">
		                <option value="">--请选择--</option>
						<option value="0" <c:if test="${pageBean.objectBean.backCheck eq '0'}">selected ="selected"</c:if>>退货申请</option>
						<option value="1" <c:if test="${pageBean.objectBean.backCheck eq '1'}">selected ="selected"</c:if>>同意退单</option>
						<option value="2" <c:if test="${pageBean.objectBean.backCheck eq '2'}">selected ="selected"</c:if>>拒绝退单</option>
						<option value="3" <c:if test="${pageBean.objectBean.backCheck eq '3'}">selected ="selected"</c:if>>待收货</option>
						<option value="4" <c:if test="${pageBean.objectBean.backCheck eq '4'}">selected ="selected"</c:if>>退单结束</option>
						<option value="6" <c:if test="${pageBean.objectBean.backCheck eq '6'}">selected ="selected"</c:if>>审核退款</option>
						<option value="7" <c:if test="${pageBean.objectBean.backCheck eq '7'}">selected ="selected"</c:if>>拒绝退款</option>
                        <option value="10" <c:if test="${pageBean.objectBean.backCheck eq '10'}">selected ="selected"</c:if>>已退款</option>
		              </select>
                    </div>
                  </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">业务员</span>
                            <select  class="form-control cate_selector" name="salesmanId" id="salesmanId" >
                                <option value="0">全部</option>
                                <c:forEach items="${enableSalaList}" var="salesman">
                                    <option value="${salesman.salesmanId}"
                                            <c:if test="${pageBean.objectBean.salesmanId==salesman.salesmanId}">selected ="selected"</c:if>>${salesman.salesmanName}</option>
                                </c:forEach>
                            </select >
                        </div>
                    </div>
		        
                  <div class="form-group">
                    <button type="button" class="btn btn-primary" onclick="submitOrder();">搜索</button>
                  </div>
                </form>
              </div>

              <table class="table order_table">
                <thead>
                <tr>
                  <th width="45%">商品</th>
                  <th class="text-center">金额/数量</th>
                  <th class="text-center" width="140px;">收货人</th>
                  <th class="text-center w100">退单时间</th>
                  <th class="text-center">退单状态</th>
                  <th class="text-center">退单金额</th>
                  <th class="text-center">业务员信息</th>
                  <th class="text-center">商家</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageBean.list}" var="order" varStatus="i">
                <tr class="table_blank">
                  <td colspan="8" style="padding:0px;"></td>
                </tr>
                <tr class="order_head">
                  <td colspan="8">
                    <p>单号：${order.backOrderCode }
                        <span style="padding-left: 10px;  font-weight:bold;  color: #002a80;">
                            <c:if test="${order.order.orderLinePay==1}">线上支付</c:if>
                            <c:if test="${order.order.orderLinePay==0}">货到付款</c:if>
                        </span>
                    </p>
                    <!-- <p>
                      外部订单号：<span>1009880256201504270092921846</span>
                      支付流水号：<span>201504221525242579244497</span>
                    </p> -->
                    <div class="btns">
                      <%--<a href="javascript:;" onclick="viewdetail('${order.backOrderId}');">查看详情</a>
                      <b> - </b>
                      --%>
                      <c:if test="${order.isBack==1}">
                          <a href="javascript:;" onclick="backdetail('${order.backOrderId}','${order.order.orderId}');">退货审核</a>
                          <b> - </b>
                      </c:if>
                      <c:if test="${order.isBack==2 && order.backCheck == 6}">
                          <a href="javascript:;" onclick="backprice('${order.backOrderId}','${order.order.orderId}');">退款审核</a>
                          <b> - </b>
                      </c:if>

                      	<%--<c:if test="${order.backCheck eq 0}">--%>
	    					<%--<a href="javascript:;" onclick="fnModifyBackOrder(${order.backOrderId })">退货操作</a>--%>
                      		<%--<b> - </b>--%>
	    				<%--</c:if>--%>
	    				<%--<c:if test="${order.backCheck eq 3}">--%>
	    					<%--<a href="javascript:;" onclick="getGoods(${order.backOrderId })">确认收货</a>--%>
	    					<%--<b> - </b>--%>
	    				<%--</c:if>--%>
	    				<%--<c:if test="${order.backCheck eq 2}">--%>
	    					<%--<a href="javascript:;" onclick="delOrder(${order.backOrderId })">取消订单</a>--%>
	    					<%--<b> - </b>--%>
	    				<%--</c:if>--%>
	    				<%--<c:if test="${order.backCheck eq 4}">--%>
	    					<%--<a href="javascript:;" onclick="delOrder(${order.backOrderId })">取消订单</a>--%>
	    					<%--<b> - </b>--%>
	    				<%--</c:if>--%>
	    				<%--<c:if test="${order.backCheck eq 1}">--%>
	    					<%--<a href="javascript:;" onclick="delOrder(${order.backOrderId })">取消订单</a>--%>
	    					<%--<b> - </b>--%>
	    				<%--</c:if>--%>
	    				<%--<c:if test="${order.backCheck eq 7}">--%>
	    					<%--<a href="javascript:;" onclick="delOrder(${order.backOrderId })">取消订单</a>--%>
	    					<%--<b> - </b>--%>
	    				<%--</c:if>--%>
	    				<%--<c:if test="${order.backCheck eq 6}">--%>
	    					<%--<a href="javascript:;" onclick="fnModifyBackmoney(${order.backOrderId })">退款审核</a>--%>
	    					<%--<b> - </b>--%>
	    				<%--</c:if>--%>
	    				<a href="javascript:;" onclick="orderlog(${order.order.orderId },${order.backOrderId })">操作日志</a>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="mini_goods" style="text-align:left;">
                     <c:if test="${ empty  order.orderGoodslistVo}">
                        <span style="font-family: '微软雅黑'; padding-left:50px; font-size: 14px; color: #002a80; font-weight:bold; "> 无 </span>
                     </c:if>
                     <c:forEach items="${order.orderGoodslistVo}" var="ordergoods" varStatus="i">
                         <a href="${bsetaddress }/item/${ordergoods.goodsInfoId}.html" target="_blank"><img alt="" title="${ordergoods.goodsInfoName}" src="${ordergoods.goodsInfoImgId}" height="50"></a>
                     </c:forEach>
                    </div>
                  </td>
                  <td align="center">
                    <p>${order.backPrice}</p>
                    <p>(${fn:length(order.orderGoodslistVo)}件)</p>
                  </td>
                  <td align="center">
                    <%--<p>${order.order.pointLevelName }</p>--%>
                    <p>${order.order.shippingPerson }</p>
                    <p>${order.order.shippingMobile }</p>
                  </td>
                  <td>
                   	<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${order.backTime }" type="both"/>
                  </td>
                  <td align="center">
                  	<c:if test="${order.backCheck eq 0}"><a href="javascript:;"><span class="label label-success">退货申请</span></a></c:if>
					<c:if test="${order.backCheck eq 1}"><a href="javascript:;"><span class="label label-success">同意退货</span></a></c:if> 
					<c:if test="${order.backCheck eq 2}"><a href="javascript:;"><span class="label label-danger">拒绝退货</span></a></c:if>
					<c:if test="${order.backCheck eq 3}"><a href="javascript:;"><span class="label label-success">待退款</span></a></c:if>
					<c:if test="${order.backCheck eq 4}"><a href="javascript:;"><span class="label label-default">退单结束</span></a></c:if>
					<c:if test="${order.backCheck eq 6}"><a href="javascript:;"><span class="label label-success">审核退款</span></a></c:if>
					<c:if test="${order.backCheck eq 7}"><a href="javascript:;"><span class="label label-danger">拒绝退款</span></a></c:if>
					<c:if test="${order.backCheck eq 8}"><a href="javascript:;"><span class="label label-danger">拒绝收货</span></a></c:if>
                    <c:if test="${order.backCheck eq 10}"><a href="javascript:;"><span class="label label-danger">已退款</span></a></c:if>
					<c:if test="${order.backCheck eq 9}"><a href="javascript:;"><span class="label label-success">待客户填写物流地址</span></a></c:if>
                  </td>
                  <td align="center">${order.backPrice }</td>
                    <td align="center">${order.salesmanName }</td>
                  <td align="center"><c:if test="${orderstoreInfo.storeName !=''}">${order.storeInfo.storeName }</c:if><c:if test="${order.storeInfo.storeName ==''||order.storeInfo.storeName ==null}">BOSS</c:if></td>
                </tr>
                </c:forEach>
                </tbody>
              </table>

				<c:import url="../page/searchPag.jsp">
					<c:param name="pageBean" value="${pageBean }"/>
					<c:param name="path" value="../"></c:param>
				</c:import>
              

            </div>

          </div>
        </div>
      </div>
    </div>
    
    
    <!-- 订单取消 -->
    <form id="delOrder" action="" method="post">
	<input type="hidden" value="1" name="backDelFlag" id="backDelFlag"/>
	</form>
	<!-- 确认收货 -->
	<form id="querenBackOrder" action="" method="post">
		<input type="hidden" value="4" name="backCheck" id="backCheck"/>
		<input type="hidden" value="" name="backOrderId" id="backOrderId"/>
	</form>
    <!-- Modal -->
    <div class="modal fade" id="orderDetails"  role="dialog">
      <div class="modal-dialog modal-lg">
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
                  <p id="detailorderIntegral">积分：</p>
                </div>
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
              <th width="40%">退货原因</th>
              <th class="text-center">申请凭据</th>
              <th class="text-center">退款金额</th>
              <th class="text-center">问题说明</th>
              <th class="text-center">上传凭证</th>
              <th class="text-center">商品返回方式</th>
            </tr>
            </thead>
            <tbody id="tui">
            
            </tbody>
          </table>
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
            
            </tbody>
          </table>

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
            <form class="form-horizontal" id="updateOrder">
              <div class="form-group">
                <label class="control-label col-sm-5">订单状态：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <select class="form-control" id="oStatus" name="orderStatus">
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
      <li role="presentation"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">退单日志</a></li>
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
          <th>操作详情</th>
            <th>操作人</th>
          <th>操作时间</th>
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
    <div class="modal fade" id="changeOrderMoney"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改订单金额</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="sub_modify_order_price" >
              <div class="form-group">
              	<input type="hidden" name="CSRFToken" value="${sx}" />
				<input type="hidden" value="" class="modify_order_id" name="orderId"> 
				<input type="hidden" value="" class="modify_order_codex" name="orderCodex"> 
				<input type="hidden" id="modify_order_price"  name="price" class="ui-widget-content ui-corner-all j_text ">
				<input type="hidden" id="o_order_price"  class="ui-widget-content ui-corner-all j_text">
                <label class="control-label col-sm-5">优惠的金额：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <input type="text" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">修改金额原因：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <textarea class="form-control" rows="5"></textarea>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary">确定</button>
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
            <form class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-5">订单状态：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <select class="form-control">
                    <option>未付款</option>
                    <option>已付款</option>
                  </select>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

   <!-- Modal -->
   <div class="modal fade" id="dialog-Back-Money"  role="dialog">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title">审核修改</h4>
               </div>
               <div class="modal-body">
                   <form class="form-horizontal" id="updateOrderMoeny" method="post">
                       <div class="form-group">
                           <label class="control-label col-sm-5">退单状态：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-10">
                               <select class="form-control" name="backCheck" id="modifyOrderMoney">
                                   <option value="4">同意退款</option>
                                   <option value="7">拒绝退款</option>
                               </select>
                           </div>
                       </div>
                   </form>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-primary" onclick="fnModifyBackmoneyup();">确定</button>
                   <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
               </div>
           </div>
       </div>
   </div>

   <!-- Modal -->
   <div class="modal fade" id="dialog-Back-backCheck"   role="dialog">
       <div class="modal-dialog">
           <div class="modal-content" style="width:800px;">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title">退单详情</h4>
               </div>
               <div class="modal-body">
                       <iframe id="mainFrame" name="mainFrame" frameborder="0" style="width:100%;" src=""></iframe>
               </div>
           </div>
       </div>
   </div>


   <!-- Modal -->
   <div class="modal fade" id="dialog-Back-price"  role="dialog">
       <div class="modal-dialog">
           <div class="modal-content" style="width:800px;">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title">退款详情</h4>
               </div>
               <div class="modal-body">
                   <iframe id="mainFrames" name="mainFrames" frameborder="0" style="width:100%;" src=""></iframe>
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
            <form class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-5">优惠的金额：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <input type="text" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">修改金额原因：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <textarea class="form-control" rows="5"></textarea>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

    <input type="hidden" value="ordercxform" id="formId"/>
    <input type="hidden" value=<c:if test="${ businessId ==0}">"backorderlist.htm"</c:if><c:if test="${ businessId !=0}">"backorderlististhird.htm"</c:if> id="formName"/>
    <form role="form" method="post" action=<c:if test="${ businessId ==0}">"backorderlist.htm"</c:if><c:if test="${ businessId !=0}">"backorderlististhird.htm"</c:if> id="ordercxform">
         <input type="hidden" id="backOrderCodestr" name="backOrderCode" value="${pageBean.objectBean.backOrderCode }" />
         <input type="hidden" name="shippingPerson" id="shippingPersonstr"  value="${pageBean.objectBean.shippingPerson}" />
         <input type="hidden" name="shippingMobile" value="${pageBean.objectBean.shippingMobile}" id="shippingMobilestr" />
         <input type="hidden" name="backCheck" value="${pageBean.objectBean.backCheck}" id="backCheckstr" />
   <input type="hidden" name="salesmanId" value="${pageBean.objectBean.salesmanId}" id="salesmanIdstr" />
    </form>
	<input type="hidden" name="businessId" id="businessId" value="${ businessId}">
   <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
	<script src="<%=basePath %>js/order/backorderlist.js"></script>
   <script>
       $(function(){
           $('#mainFrame').load(function(){
               var theHeight = $('#mainFrame').contents().find('body').height();
               $('#mainFrame').height(theHeight);
           });
       });
       $("input[name=backRemark]").keydown(function (event){
           if(event.keyCode==13)
               return false;
       })
   </script>
  </body>
</html>
