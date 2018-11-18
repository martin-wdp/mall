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
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
      <style media=print>
          .Noprint{display:none;}
          .PageNext{page-break-after:   always;}
      </style>
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
          
          <div class="main_cont Noprint">
             <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

            <div class="common_data p20">
			
              <div class="filter_area">
                <form role="form" class="form-inline">
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">订单号</span>
                      <input type="text" class="form-control"  name="orderCode" id="orderCode" value="${order.orderCode }">
                    </div>
                      <div class="input-group">
                          <span class="input-group-addon">收货人</span>
                          <input type="text" class="form-control"  id="shippingPerson" value="${order.shippingPerson }">
                      </div>
                      <div class="input-group">
                          <span class="input-group-addon">联系电话</span>
                          <input type="text" class="form-control"  id="shippingMobile" value="${order.shippingMobile }">
                      </div>
                  </div>
                  <div class="form-group">
                    <button type="button" class="btn btn-primary" onclick="ordersssubmit();">搜索</button>
                  </div>
                </form>
              </div>
              <div class="data_ctrl_area mb20">
                <div class="data_ctrl_search pull-right"></div>
                  <c:if test="${order.orderCargoStatus eq '0'}">
                  <div class="data_ctrl_brns pull-left">
                  <button type="button" class="btn btn-info" onclick="pickingOrderIds()">
                    <span class="glyphicon glyphicon-print"></span> 打印并拣货
                  </button>
                </div>
                  </c:if>
                <div class="clr"></div>
              </div>


                <div class="table_tabs" id="good_tabs">
                    <ul>
                        <li class="<c:if test="${order.orderCargoStatus eq '0'}">active</c:if>">
                            <a href="<%=basePath%>orderpickinglist.htm?myselfId=1441">待拣货</a>
                        </li>
                        <li class="<c:if test="${order.orderCargoStatus eq '1'}">active</c:if>">
                            <a href="<%=basePath%>orderdeliverylist.htm?myselfId=1467">待装箱</a>
                        </li>
                        <li class="<c:if test="${order.orderCargoStatus eq '2'}">active</c:if>">
                            <a href="<%=basePath%>ordersendgoods.htm?myselfId=1471">待出库</a>
                        </li>
                    </ul>
                </div>
              <table class="table order_table">
                <thead>
                <tr>
                  <c:if test="${order.orderCargoStatus eq '0'}">
               	    <th class="text-center"><input type="checkbox" onclick="allunchecked(this,'order_id');"></th>
                  </c:if>
                  <th width="45%">商品</th>
                  <th class="text-center">总价/数量</th>
                  <th class="text-center" width="140px;">收货人</th>
                  <th class="text-center w100">下单时间</th>
                  <th class="text-center">订单状态</th>
                  <th class="text-center">出库状态</th>
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
                    <p>订单号：${order.orderCode } <span style="padding-left: 10px;  font-weight:bold;  color: #002a80;"><c:if test="${order.orderLinePay eq 1}">在线支付</c:if>
				  <c:if test="${order.orderLinePay eq 0}">货到付款</c:if></span> </p>
                    <!-- <p>
                      外部订单号：<span>1009880256201504270092921846</span>
                      支付流水号：<span>201504221525242579244497</span>
                    </p> -->
                    <div class="btns">
                      <a href="javascript:;" onclick="fnOpen(${order.orderId })">查看详情</a>
                      <b> - </b>
                      <c:if test="${order.orderCargoStatus ne 2}">
                        <c:if test="${order.orderCargoStatus eq '1'}">
                        	<a href="javascript:;" onclick="goOrderPincking('${order.orderId }')">装箱操作</a>
                      		<b> - </b>
                      		<a href="javascript:;" onclick="fnModifyOrder(${order.orderId},4,${order.orderCode })">中断订单</a>
                      		<b> - </b>
                        </c:if>
                        <c:if test="${order.orderCargoStatus eq '0'}">
                        	<a href="javascript:;" onclick="fnModifyOrder(${order.orderId},4,${order.orderCode })">中断订单</a>
                      		<b> - </b>
                        </c:if>
                      </c:if>
                      <c:if test="${order.orderCargoStatus eq '2'}">
                       		<a href="javascript:;" onclick="goSendGoods('${order.orderId }')">出库</a>
                      		<b> - </b>
            		  </c:if>
                      <a href="javascript:;" onclick="orderlog(${order.orderId })">操作日志</a>
                    </div>
                  </td>
                </tr>
                <tr>
                    <c:if test="${order.orderCargoStatus eq '0'}">
                      <td>
                        <input type="checkbox" name="order_id" value="${order.orderId }" class="input_orderId">
                      </td>
                    </c:if>
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
                    <p>${order.shippingPerson }</p>
                    <p>${order.shippingMobile }</p>
                  </td>
                  <td>
                   	<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${order.createTime }" type="both"/>
                  </td>
                  <td align="center">
                  	<c:if test="${order.orderStatus eq 0}"><a href="javascript:;"><span class="label label-danger">
                      <c:if test="${order.orderLinePay eq 0}">
                        待发货
                      </c:if>
                        <c:if test="${order.orderLinePay eq 1}">
                          待付款
                        </c:if>

                    </span></a></c:if>
					<c:if test="${order.orderStatus eq 1}"><a href="javascript:;"><span class="label label-danger">已付款未发货</span></a></c:if>
					<c:if test="${order.orderStatus eq 2}"><a href="javascript:;"><span class="label label-info">已发货</span></a></c:if>
					<c:if test="${order.orderStatus eq 3}"><a href="javascript:;"><span class="label label-info">已完成</span></a></c:if>
					<c:if test="${order.orderStatus eq 4}"><a href="javascript:;"><span class="label label-default">已取消</span></a></c:if>
                      <c:if test="${order.orderStatus eq 13}"><a href="javascript:;"><span class="label label-default">拒绝退款</span></a></c:if>
                  </td>
                  <td align="center">
                  	<c:if test="${order.orderCargoStatus eq 0}"><a href="javascript:;"><span class="label label-danger">未拣货</span></a></c:if>
					<c:if test="${order.orderCargoStatus eq 1}"><a href="javascript:;"><span class="label label-danger">未装箱</span></a></c:if>  
					<c:if test="${order.orderCargoStatus eq 2}"><a href="javascript:;"><span class="label label-danger">未出库</span></a></c:if>
					<c:if test="${order.orderCargoStatus eq 3}"><a href="javascript:;"><span class="label label-info">已出库</span></a></c:if>
                  </td>
                  <td align="center"><c:if test="${order.storeName!=''}">${order.storeName}</c:if><c:if test="${order.storeName==''||order.storeName==null}">BOSS</c:if></td>
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


    <!-- 无权限提示信息 -->
    <div class="modal fade" id="noPermit"  role="dialog">
      <div class="modal-dialog modal-lg">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title">系统提示</h4>
              </div>
              <div class="modal-body">
                  <div class="common_info common_tabs mt20">
                      <div class="tab-content">
                          对不起您暂时没有权限，请与管理员联系!
                      </div>
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
      <li role="presentation"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">退款日志</a></li>
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

          </div>
        </div>
      </div>
    </div>
	
    <!-- Modal -->
    <div class="modal fade" id="pickUp"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">

          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">订单拣货</h4>
          </div>
            <!--startprint1-->
          <div class="modal-body" id="orderGoodsInfolist">
            <div class="mb20">
              <div class="border_bottom mb10 row">
                <div class="col-sm-3">
                <p>序号</p>
                </div>
                <div class="col-sm-5">
                <p>商品编号</p>
                </div>
                <div class="col-sm-13">
                <p class="text-center">商品名称</p>
                </div>
                <div class="col-sm-3">
                <p class="text-right">数量</p>
                </div>
                <div class="col-sm-5">
                  <p>商品属性</p>
                </div>
                  <div class="col-sm-5">
                      <p>价格</p>
                  </div>
              </div>
              <div class="border_bottom mb10 row">
                <div class="col-sm-8">
                  <p>OF1012931301341924</p>
                </div>
                <div class="col-sm-13">
                  <p>忆江南正品 茶叶 红茶 正宗武夷 金骏眉 正山小种一级 50g/罐</p>
                </div>
                <div class="col-sm-3">
                  <p class="text-right">1</p>
                </div>
              </div>
              <div class="border_bottom mb10 row">
                <div class="col-sm-8">
                  <p>OF1012931301341924</p>
                </div>
                <div class="col-sm-13">
                  <p>张飞葱香牛肉干散装500g 香味独特 美味十足 四川特产零食品</p>
                </div>
                <div class="col-sm-3">
                  <p class="text-right">1</p>
                </div>
              </div>
            </div>
            
          </div>
          <!--endprint1-->
          <div class="modal-footer">
            <button type="button" class="btn btn-primary Noprint" onclick="return doPrint();">打印</button>
            <button type="button" class="btn btn-primary Noprint" onclick="subOrder();">拣货</button>
            <button type="button" class="btn btn-default Noprint" data-dismiss="modal">取消</button>
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
              	<input  type="hidden" name="CSRFToken" id="CSRFToken" value="${sx}" />
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
                	<input  type="password" class="form-control required" name="userkey" id="pwd" />
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
  <div class="modal fade" id="outWarehouse"  role="dialog">
      <div class="modal-dialog" style="width:1000px">
          <div class="modal-content">
              
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                  <h4 class="modal-title">订单出库</h4>
              </div>
              <div class="modal-body">
              <form action="subsendgoodsorder.htm?"   id="addForm" method="post" class="sub_sendGoods">
              </form>
              </div>
              
              <div class="modal-footer">
                  <button type="button" class="btn btn-primary" onclick="doPrint3()">打印</button>
                  <button type="button" class="btn btn-primary" onclick="subSendGoods()">出库</button>
                  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
              </div>
          </div>
      </div>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="packing"  role="dialog">
      <div class="modal-dialog modal-lg">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                  <h4 class="modal-title">订单装箱</h4>
              </div>
              <!--startprint2-->
              <div class="modal-body">
                  <div class="mb20" id="ordermod">

                  </div>
                  <div class="packages">
                      <div class="mb20">
                      </div>
                  </div>
                  <!--endprint2-->
                  <div class="modal-footer">
                      <input type="hidden" id="changeOrderId" value="" />
                      <button type="button" class="btn btn-primary" onclick="doPrint2();">打印</button>
                      <button type="button" class="btn btn-primary" onclick="changeOrder()">装箱</button>
                      <input type="hidden" id="addContainer" />
                      <button type="button" class="btn btn-primary" onclick="addContainers()">新增装箱单</button>
                      <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
  <div class="modal fade" id="changePackage"  role="dialog">
      <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                  <h4 class="modal-title">更换包裹</h4>
              </div>
              <div class="modal-body">
                  <form role="form" id ="updatecontainer" class="form-horizontal">
                      <div class="form-group">
                          <label class="control-label col-sm-6">选择装箱单:</label>
                          <div class="col-sm-1"></div>
                          <div class="col-sm-11">
                              <input name="containerId" value="1"  type="hidden"/>
                              <input name="orderId" type="hidden" value="">
                              <select class="form-control" name="relationId">

                              </select>
                          </div>
                          <div class="col-sm-6"></div>
                      </div>
                      <%--<div class="form-group">
                          <label class="control-label col-sm-6">请输入数量:</label>
                          <div class="col-sm-1"></div>
                          <div class="col-sm-11">
                              <input name="goodsNum" id="goodsNum" style="width:100px;"/>
                          </div>
                      </div>
                      <span style="padding-left: 70px; color:#ff0000" id="errorInfo"></span>--%>
                  </form>
              </div>
              <div class="modal-footer">
                  <button type="button" onclick="changeContainerRe();" class="btn btn-primary">确定</button>
                  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

              </div>
          </div>
      </div>
  </div>

    <form action="" method="post" class="thirdorder_picking">
    	<input type="hidden" name="orderId" class="order_p_id"/>
    </form>
    <input type="hidden" value="ordercxform" id="formId"/>
    <input type="hidden" value="${pageBean.url}" id="formName"/>
    <form role="form"  method="post" action="<%=basePath %>${pageBean.url}" id="ordercxform">
         <input type="hidden" name="orderCode" id="orderCodestr" value="${order.orderCode }" />
         <input type="hidden" name="shippingPerson" id="shippingPersonstr"  value="${order.shippingPerson}" />
         <input type="hidden" name="shippingMobile" value="${order.shippingMobile}" id="shippingMobilestr" />
    </form>
  <form id="sub_orders" method="post" action="changeorderbyfif.htm">
  </form>

  <form action="delrelationbyid.htm?CSRFToken=${sx}" id="del_relation" method="post">
      <input name="relationId"  value=""  type="hidden" id="del_relation_id"/>

      <input name="orderId" type="hidden" id="orderIdu">
  </form>
  <form action="printView.htm?CSRFToken=${sx}" id="printView" method="post" target="_blank" >
      <input name="str"  value=""  type="hidden" id="str"/>
  </form>
  <%--出库单打印--%>
  <form action="deliveryPrintView.htm?CSRFToken=${sx}" id="deliveryPrintView" method="post" target="_blank" >
    <input name="orderId"  value=""  type="hidden" id="printOrderId"/>
  </form>
    <input type="hidden" value="${sessionScope.name}" id="scopeName">
    <input type="hidden" value="0" class="status_print">
	<input id="csrf_token" type="hidden" name="CSRFToken" value="${sx}" />
	<input id="basePath" value="<%=basePath %>" type="hidden" />
    <input type="hidden" id="printNo" value="0" />
    <input type="hidden" id="printNo2" value="0" />
  <input type="hidden" id="printNo3" value="0" />
  <input type="hidden"   class="order_m_type" value="">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/order/orderpickinglist.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
  </body>
</html>
