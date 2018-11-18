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
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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
                      <span class="input-group-addon">订单号</span>
                      <input type="text" class="form-control" id="orderCode" value="${order.orderCode }">
                    </div>
                      <div class="input-group">
                          <span class="input-group-addon">收货人</span>
                          <input type="text" class="form-control" value="${order.shippingPerson}" id="shippingPerson">
                      </div>
                      <div class="input-group">
                          <span class="input-group-addon">联系电话</span>
                          <input type="text" class="form-control" id="shippingMobile" value="${order.shippingMobile}" onkeyup="value=value.replace(/[^\d]/g,'') "
                                 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
                      </div>
                      <div class="input-group">
                          <span class="input-group-addon">订单状态</span>
                          <div class="col-sm-15">
                              <select id="backCheck" class="form-control">
                                  <option value="">--请选择--</option>
                                  <option value="1" <c:if test="${order.orderStatus eq '1'}">selected ="selected"</c:if>>已付款</option>
                                  <option value="3" <c:if test="${order.orderStatus eq '2'}">selected="selected"</c:if>>
                                      已发货
                                  </option>
                                  <option value="3" <c:if test="${order.orderStatus eq '3'}">selected="selected"</c:if>>
                                      已完成
                                  </option>
                                  <option value="4" <c:if test="${order.orderStatus eq '4'}">selected ="selected"</c:if>>已取消</option>
                              </select>
                          </div>
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
                  <th class="text-center">总价/数量</th>
                  <th class="text-center" width="140px;">收货人</th>
                  <th class="text-center w100">下单时间</th>
                  <th class="text-center">订单状态</th>
                  <th class="text-center">实付金额</th>
                  <th class="text-center">商家</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageBean.list}" var="order" varStatus="i">
                <tr class="table_blank">
                  <td colspan="7"></td>
                </tr>
                <tr class="order_head">
                  <td colspan="7">
                    <p>订单号：${order.orderCode } <span>在线支付</span> </p>
                    <!-- <p>
                      外部订单号：<span>1009880256201504270092921846</span>
                      支付流水号：<span>201504221525242579244497</span>
                    </p> -->
                    <div class="btns">
                      <a href="javascript:;" onclick="fnOpen(${order.orderId })">查看详情</a>
                      
                       <c:if test="${order.orderStatus eq '1' }">
                       		<b> - </b>
                        	<a href="javascript:;" onclick="delivergoodsModal(${order.orderId})">发货</a>
				    	</c:if>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="mini_goods">
                    
                      <a href="${bsetaddress }/item/${order.goodsInfoId}.html" target="_blank"><img alt="" src="${order.goodsProductVo.goodsInfoImgId}" height="50"></a>
                    </div>
                  </td>
                  <td align="center">
                    <p>${order.goodsProductVo.goodsInfoPreferPrice}</p>
                    <p>(1件)</p>
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
                      <c:if test="${order.orderStatus eq 0}"><a href="javascript:;"><span class="label label-danger">未付款</span></a></c:if>
                      <c:if test="${order.orderStatus eq 1}"><a href="javascript:;"><span class="label label-danger">已付款未发货</span></a></c:if>
                      <c:if test="${order.orderStatus eq 2}"><a href="javascript:;"><span class="label label-info">已发货</span></a></c:if>
                      <c:if test="${order.orderStatus eq 3}"><a href="javascript:;"><span class="label label-info">已完成</span></a></c:if>
                      <c:if test="${order.orderStatus eq 4}"><a href="javascript:;"><span class="label label-default">已取消</span></a></c:if>
                      <c:if test="${order.orderStatus eq 7}"><a href="javascript:;"><span class="label label-danger">退单审核中</span></a></c:if>
                      <c:if test="${order.orderStatus eq 8}"><a href="javascript:;"><span class="label label-info">同意退货</span></a></c:if>
                      <c:if test="${order.orderStatus eq 9}"><a href="javascript:;"><span class="label label-info">拒绝退货</span></a></c:if>
                      <c:if test="${order.orderStatus eq 10}"><a href="javascript:;"><span class="label label-info">待商家收货</span></a></c:if>
                      <c:if test="${order.orderStatus eq 11}"><a href="javascript:;"><span class="label label-info">退单结束</span></a></c:if>
                      <c:if test="${order.orderStatus eq 12}"><a href="javascript:;"><span class="label label-danger">退款审核中</span></a></c:if>
                      <c:if test="${order.orderStatus eq 13}"><a href="javascript:;"><span class="label label-info">拒绝退款</span></a></c:if>
                      <c:if test="${order.orderStatus eq 14}"><a href="javascript:;"><span class="label label-info">已提交退货审核 </span></a></c:if>
                      <c:if test="${order.orderStatus eq 15}"><a href="javascript:;"><span class="label label-info">已提交退款审核</span></a></c:if>
                      <c:if test="${order.orderStatus eq 16}"><a href="javascript:;"><span class="label label-info">收货失败</span></a></c:if>
                      <c:if test="${order.orderStatus eq 17}"><a href="javascript:;"><span class="label label-info">已退款</span></a></c:if>
                  </td>
                  <td align="center">${order.orderPrice }</td>
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
    <div class="modal fade" id="udpateExpressmodal"  role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">发货</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="udpateExpress" action="sendgrouporder.htm?CSRFToken=${token}" method="post">
                        <div class="form-group">
                            <label class="control-label col-sm-5">物流公司：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <input type="hidden" id="deliverorderId" name="orderId"/>
                                <input type="hidden" name="url" value="${pageBean.url }">
                                <select class="form-control" name="expressId" id="expressId">
                                    <c:forEach items="${expressList}" var="express" >
                                        <option  value="${express.logComId }">${express.name }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">物流单号：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <input type="text" name="expressNo" id="expressNo" class="form-control required"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="deliverorder()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

    <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
    <input type="hidden" value="ordercxform" id="formId"/>
    <input type="hidden" value="${pageBean.url }" id="formName"/>
    <form role="form" action="${pageBean.url }" id="ordercxform" method="post">
        <input type="hidden" name="shippingPerson" id="shippingPersonstr"  value="${order.shippingPerson}" />
        <input type="hidden" name="orderCode" value="${order.orderCode}" id="orderCodestr" />
        <input type="hidden" name="shippingMobile" value="${order.shippingMobile}" id="shippingMobilestr" />
        <input type="hidden" name="orderStatus" value="${order.orderStatus}" id="orderStatusstr" />
    </form>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/summernote.min.js"></script>
    <script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath%>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath%>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath%>js/common.js"></script>
    <script src="<%=basePath%>js/order/ordervicelist.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
  </body>
</html>
