<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-订单管理</title>
<#assign basePath=request.contextPath>

<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" >
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/order/thirdorderlists.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script>
    $(function(){
        //默认按钮为禁止状态
        $(".btn_css").attr({"disabled":"disabled"});
    	$.material.init();
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        })
    });
</script>
</head>


<body>

<#--引入头部-->
<#include "../index/indextop.ftl">

<div class="wp">
    <div class="ui-sidebar">

        <div class="sidebar-nav">
        <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
        </div>

    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li><a href="javascript:;">订单管理</a></li>
                <li class="active">订单列表</li>
            </ol>

            <div class="app-content">
                <div>
                    <div class="search-block">
                        <form action="queryThirdOrderList.html" class="searchThirdOrderList" method="post">
                            <input type="hidden" value="<#if map.order.orderStatus??>${map.order.orderStatus}</#if>" name="orderStatus" class="tabStatus">
                            <div class="filter-groups">
                                <div class="control-group">
                                    <label class="control-label">商品名称：</label>
                                    <div class="controls">
                                        <input class="form-control" type="text"/>
                                    </div>
                                </div>
                                <div class="control-group lg-group">
                                    <label class="control-label">平台价格：</label>
                                    <div class="controls">
                                        <input  class="form-control sm-input" name="startprice"
                                            onkeyup="value=value.replace(/[^\d]/g,'') "
                                            onbeforepaste="clipboardData.setData('text',
								            clipboardData.getData('text').replace(/[^\d]/g,''))"
                                            <#if map.order.startprice??>
                                                    value="${map.order.startprice}"
                                            <#else>
                                                    value=""
                                            </#if> type="text"/>
                                        ~
                                        <input  class="form-control sm-input" name="endprice"
                                            onkeyup="value=value.replace(/[^\d]/g,'') "
                                            onbeforepaste="clipboardData.setData('text',
								            clipboardData.getData('text').replace(/[^\d]/g,''))"
                                            <#if map.order.endprice??>
                                                    value="${map.order.endprice}"
                                            <#else>
                                                    value=""
                                            </#if> type="text"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">订单编号：</label>
                                    <div class="controls">
                                        <input class="form-control" name="orderCode"
                                        onkeyup="value=value.replace(/[^\d]/g,'') "
                                        onbeforepaste="clipboardData.setData('text',
								        clipboardData.getData('text').replace(/[^\d]/g,''))"
                                        <#if map.order.orderCode??>
                                               value="${map.order.orderCode}"
                                        <#else>
                                               value=""
                                        </#if> type="text"/>
                                    </div>
                                </div>
                                <div class="control-group lg-group">
                                    <label class="control-label">下单时间：</label>
                                    <div class="controls">
                                        <input type="text" name="startTime" value="<#if map.order.startTime??>${map.order.startTime}</#if>" class="form-control sm-input datepicker" data-provide="datepicker"/>
                                        ~
                                        <input type="text" name="endTime" value="<#if map.order.endTime??>${map.order.endTime}</#if>" class="form-control sm-input datepicker" data-provide="datepicker"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">平台分类：</label>
                                    <div class="controls">
                                        <input class="form-control" type="text"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">店内分类：</label>
                                    <div class="controls">
                                        <input class="form-control" type="text"/>
                                    </div>
                                </div>
                            </div>
                            <div class="search-operation">
                                <button class="btn btn-primary btn-sm"  onclick="queryOrderList()" type="button">查询<i class="glyphicon glyphicon-search"></i></button>
                                <button class="btn btn-default btn-sm"  onclick="resetThirdOrder()"  type="button">重置<i class="glyphicon glyphicon-refresh"></i></button>
                            </div>
                        </form>
                    </div>

                    <ul class="nav nav-tabs">
                        <li class="tb_barterOrder " onclick="changTbl('')"><a href="javascript:;">全部订单</a></li>
                        <li class="tb_backOrderSH" onclick="changTbl(0)"><a href="javascript:;">待付款</a></li>
                        <li class="tb_backOrderTg" onclick="changTbl(1)"><a href="javascript:;">待发货</a></li>
                        <li class="tb_backOrderJj" onclick="changTbl(2)"><a href="javascript:;">已发货</a></li>
                        <li class="tb_backOrderYt" onclick="changTbl(3)"><a href="javascript:;">已收货</a></li>
                        <li class="tb_backOrderover " onclick="changTbl(4)"><a href="javascript:;">已取消</a></li>
                    </ul>

                    <div class="cfg-content">
                        <form class="simple_search" action="<#if map.pb.url??&&map.pb??>${map.pb.url}<#else> </#if>" method="post">
                        </form>
                        <form class="high_search" action="<#if map.pb.url??&&map.pb??>${map.pb.url}<#else> </#if>" method="post">
                        <div class="ops-bar"></div>
                            <table class="table table-bordered orders-table">
                                <thead>
                                <tr>
                                    <th width="30%">商品</th>
                                    <th>商品种类</th>
                                    <th>买家</th>
                                    <th width="10%">下单时间</th>
                                    <th>订单状态</th>
                                    <th>实付金额</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                 <#if map.pb??&&map.pb.list??&&map.pb.list?size!=0&&map.pb.list[0]??>
                                    <#list map.pb.list as order>
                                        <tbody>
                                        <tr class="separation-row">
                                            <td colspan="8"></td>
                                        </tr>
                                        <tr class="header-row">
                                            <td colspan="6">
                                                <p>订单号：${order.orderCode}</p>
                                            </td>
                                            <td class="row-ops" colspan="2">
                                                <a href="tothirdorderdetail.html?orderId=<#if order.orderId??>${order.orderId}</#if>">查看详情</a>
                                            </td>
                                        </tr>
                                        <tr class="content-row">
                                            <td class="order-pros">
                                                <#--遍历该订单下面的所有商品的信息-->
                                                <#if order.orderGoodsList??>
                                                 <#list order.orderGoodsList as map>
                                                     <#if map.goodsProductVo??>
                                                         <a href="javascript:;" title="${map.goodsProductVo.goodsInfoName!'' }"><img src="${map.goodsProductVo.goodsInfoImgId!'' }" alt="商品1" width="60" height="60"/></a>
                                                     </#if>
                                                 </#list>
                                                </#if>
                                            </td>
                                            <td>
                                            	<#--<#if order.orderGoodsList??>
                                                	<#assign goodsTotalPrice=0>
                                                	<#assign goodsNum=0>
                                                    <#list order.orderGoodsList as map>
                                                        <#if map.goodsProductVo??>
                                                        	<#assign goodsNum=goodsNum+map.goodsInfoNum>
                                                        	<#assign goodsTotalPrice=map.goodsInfoNum*order.orderPrice>
                                                        </#if>
                                                    </#list>
                                                     &yen;${goodsTotalPrice?string("0.00")}
                                                </#if>
                                                <p>（<#if order.orderGoodsList??>${order.orderGoodsList?size}<#else></#if>件）</p>
                                                -->
                                                （<#if order.orderGoodsList??>${order.orderGoodsList?size}<#else></#if>件）
                                            </td>
                                            <td>${order.shippingPerson!''}</td>
                                            <td><#if order.createTime??>${order.createTime?string("yyyy-MM-dd HH:mm:ss") }</#if></td>
                                            <td>
                                                <#if order.orderStatus??>
                                                    <#if order.orderStatus=="0">
                                                        待付款
                                                    <#elseif (order.orderStatus=="1" || order.orderStatus=="5" || order.orderStatus=="6") >
                                                        未发货
                                                    <#elseif order.orderStatus=="2">
                                                        已发货
                                                    <#elseif order.orderStatus=="3">
                                                        已完成
                                                    <#elseif order.orderStatus=="4">
                                                        已取消
                                                    <#elseif order.orderStatus=="14">
                                                        退货审核
                                                    <#elseif order.orderStatus=="8">
                                                        同意退单
                                                    <#elseif order.orderStatus=="10">
                                                        待商家收货
                                                    <#elseif order.orderStatus=="11">
                                                        退单结束
                                                    <#elseif order.orderStatus=="9">
                                                        拒绝退货
                                                    <#elseif order.orderStatus=="15">
                                                        退款审核
                                                    <#elseif order.orderStatus=="18">
                                                        退款完成
                                                    <#elseif order.orderStatus=="13">
                                                        拒绝退款
                                                    <#else>
                                                        退货中
                                                    </#if>
                                                </#if>
                                            </td>
                                            <td>¥${(order.orderPrice)?string("0.00")}</td>
                                            <td>
                                                <div class="btn-group">
                                                     <#if order.orderStatus=="0"&&order.orderLinePay=="1">
                                                         <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modifyOrder" onclick="modifyThirdOrderbyId(<#if order.orderId??>${order.orderId}</#if>)">修改金额</button>
                                                         <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                             <span class="caret"></span>
                                                         </button>
                                                     <#elseif order.orderStatus=="1"&&order.orderLinePay=="1">
                                                     <#if map.isPage =="0">
                                                        <button type="button" class="btn btn-primary btn-sm" style="width: 112px;" onclick="javascript:window.location.href='querythirdoutstock.html?n=9&l=59'">去拣货</button>
                                                     <#else >
                                                         <button  class="btn btn-primary btn-sm btn_css"  style="width: 112px;">暂无拣货权限</button>
                                                     </#if>
                                                     <#elseif order.orderStatus=="11">
                                                         <button type="button" class="btn btn-primary btn-sm" style="width: 112px;" data-toggle="modal" data-target="#cancleOrder" onclick="del(<#if order.orderId??>${order.orderId}</#if>)">取消订单</button>
                                                     <#else>
                                                         <button  class="btn btn-primary btn-sm btn_css"  style="width: 112px;">暂无操作</button>
                                                     </#if>

                                                    <#if order.orderLinePay=="1">
                                                        <ul class="dropdown-menu" role="menu" <#if order.orderStatus=="5" || order.orderStatus=="1"||order.orderStatus=="6"||order.orderStatus!="0">style="display:none;"</#if>>
                                                            <#if order.orderStatus!="5" && order.orderStatus!="1"&&order.orderStatus!="6">
                                                                <li><a href="" data-toggle="modal" data-target="#modifyState" onclick="upOrderSta(<#if order.orderId??>${order.orderId}</#if>,<#if order.orderStatus??>${order.orderStatus}</#if>)">修改状态</a></li>
                                                            </#if>
                                                            <#if order.orderStatus=="0">
                                                                <li><a href="javascript:;" data-toggle="modal" data-target="#cancleOrder" onclick="del(<#if order.orderId??>${order.orderId}</#if>)">订单取消</a></li>
                                                            </#if>
                                                        </ul>
                                                    <#elseif order.orderLinePay=='0'>
                                                        <#if order.orderStatus=="2">
                                                            <ul class="dropdown-menu" role="menu">
                                                                <li><a href="" data-toggle="modal" data-target="#modifyState" onclick="upOrderSta(<#if order.orderId??>${order.orderId}</#if>,<#if order.orderStatus??>${order.orderStatus}</#if>)">修改状态</a></li>
                                                            </ul>
                                                        </#if>
                                                    </#if>

                                                </div>
                                            </td>
                                        </tr>
                                      </tbody>
                                    </#list>
                            </table>
                        <div class="footer-operation">
                            <div class="ops-left">

                            </div>
                            <div class="ops-right">
                                <nav>
                                    <ul class="pagination">
                                        <li>
                                            <a href="javascript:;" aria-label="Previous" onclick="changePageNo(this);" data-role="${map.pb.prePageNo}">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <#if (map.pb.startNo?number>1)>
                                            <li><a href="javascript:;">1</a></li>
                                        </#if>
                                        <#list map.pb.startNo?number .. map.pb.endNo as item>
                                            <li <#if item == map.pb.pageNo>class="active"</#if>><a href="javascript:;" onclick="changePageNo(this);" data-role="${item}">${item}</a></li>
                                        </#list>
                                        <#if (map.pb.totalPages?number>map.pb.endNo)>
                                            <li><a href="javascript:;" onclick="changePageNo(this);" data-role="${map.pb.totalPages}">${pageBean.totalPages}</a></li>
                                        </#if>
                                        <li>
                                            <a href="javascript:;" aria-label="Next" onclick="changePageNo(this);" data-role="${map.pb.nextPageNo}">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <#else>
                            <tfoot>
                            <tr>
                                <td colspan="12">暂无信息</td>
                            </tr>
                            </tfoot>
                        </#if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--<div class="service-wrap">-->
    <#--<span class="service-close">×</span>-->
    <#--<a href="javascript:;" class="service-box">联系客服</a>-->
<#--</div>-->

<div class="back-to-top">
    <a href="javascript:;"><i></i>返回顶部</a>
</div>

<#--<div class="notice-center">-->
    <#--<div class="notice-center-ring"><i></i></div>-->
    <#--<div class="notice-center-wrapper">-->
        <#--<div class="nt-header">-->
            <#--<h3>系统通知（<span>1</span>）</h3>-->
            <#--<a href="javascript:;" class="nt-close">收起》</a>-->
        <#--</div>-->
        <#--<ul class="nt-content">-->
            <#--<li>-->
                <#--<div class="nt-item unread">-->
                    <#--<p>刘仙升于2015-04-08 15:41:23申请提现1.00元，已提现成功，请注意查询您的银行账户。</p>-->
                    <#--<a href="javascript:;">查看提现记录 》</a>-->
                <#--</div>-->
            <#--</li>-->
        <#--</ul>-->
        <#--<div class="nt-footer">-->
            <#--<a href="javascript:;" class="mark-read">全部标记已读</a>-->
            <#--<a href="javascript:;" class="nt-all">查看全部信息</a>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--<div class="page-help-btn">帮助</div>-->
<div class="page-help-container">
    <div class="page-help-content">
        <p style="color:#f00;">不知道从哪里开始？</p>
        <p>完成掌柜任务，简简单单开店铺！</p>
        <p>点击开始》》<a href="javascript:;">掌柜成长之旅</a></p>
    </div>
    <div class="page-help-operation">
        <a href="javascript:;" class="btn btn-primary btn-sm">进入帮助中心</a>
        <a href="javascript:;" class="btn btn-default btn-sm">建议反馈</a>
    </div>
</div>

<div class="modal fade" id="modifyOrder" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改订单</h4>
            </div>
            <div class="modal-body">
                <form class="update_thirdorder" action="updateThirdOrder.htm" method="post">
                    <input type="hidden" name="orderId" class="order_id" value="" />
                    <div class="form-item">
                        <label class="control-label">订单编号：</label>
                        <div class="controls checkWp upt_orderCode" ></div>
                    </div>
                    <div class="form-item">
                        <label class="control-label">收货人：</label>
                        <div class="controls checkWp upt_shippingPerson"></div>
                    </div>
                    <div class="form-item">
                        <label class="control-label">联系电话：</label>
                        <div class="controls checkWp upt_shippingPhone"></div>
                    </div>
                    <div class="form-item">
                        <label class="control-label upt_orderPrice"><b>*</b>交易金额：</label>
                        <div class="controls">
                            <input type="text" id="orderPrice" value="" name="orderPrice" class="form-control"/>
                            <label class="upt_orderInfo"></label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary" onclick="uptThirdOrder()" type="button">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modifyState" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <form action="updateThirdOrderSta.htm" method="post" class="up_order_sta ">
                <input type="hidden" class="up_order_sta" name="orderId" value="" />
                <div class="modal-body">
                    <div class="form-item">
                        <label class="control-label">选择状态：</label>
                        <div class="controls">
                            <select class="form-control"  name="orderStatus" id="orderStatus">
                                <option value="-1">-请选择-</option>
                                <option class="ordersta option0" value="0">未付款</option>
                                <option class="ordersta option6" value="1">已付款未发货</option>
                                <option class="ordersta option2" value="2">已发货未确认收货</option>
                                <option class="ordersta option3" value="3">已经收货</option>
                                <#--<option class="ordersta option4" value="4">取消</option>-->
                            </select>
                            <span style="color: #ff0000" class="select_tip"></span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                    <button class="btn btn-primary" onclick="subThirdOrderSta();" type="button">确定</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--取消订单-->
<div class="modal fade" id="cancleOrder" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <form id="mform" action="delThirdOrderByparam.htm" method="post">
                <input type="hidden" id="thirdOrderId" name="orderId" value=""/>
                <div class="modal-body">
                    <div class="form-item">
                            您确定要取消订单吗？
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                    <button class="btn btn-primary" onclick="delOrderById();" type="button">确定</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script>
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</html>
