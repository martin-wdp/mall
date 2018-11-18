<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-订单管理</title>
<#assign basePath=request.contextPath>

<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>

<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/order/thirdbackorderlists.js"></script>
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
                <li>订单管理</li>
                <li class="active" style="color: #07d;">退单列表</li>
            </ol>

            <div class="app-content">
                <!--初始值设为1 ：给退单审批增加蓝色底层-->
                <input type="hidden" value="1" name="backCheck" class="tabStatus">
                <div>
                    <div class="search-block">
                        <form action="queryThirdBackOrderList.html" class="searchThirdOrderList" method="post">
                            <input type="hidden" value="<#if map.bkorder.orderStatus??>${map.bkorder.orderStatus}</#if>" name="orderStatus" class="tabStatus">
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
                                        <#if map.bkorder.startprice??>
                                                value="${map.bkorder.startprice}"
                                        <#else>
                                                value=""
                                        </#if> type="text"/>
                                        ~
                                        <input  class="form-control sm-input" name="endprice"
                                                onkeyup="value=value.replace(/[^\d]/g,'') "
                                                onbeforepaste="clipboardData.setData('text',
								            clipboardData.getData('text').replace(/[^\d]/g,''))"
                                        <#if map.bkorder.endprice??>
                                                value="${map.bkorder.endprice}"
                                        <#else>
                                                value=""
                                        </#if> type="text"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">退单单号：</label>
                                    <div class="controls">
                                        <input class="form-control"  name="backOrderCode"
                                               onkeyup="value=value.replace(/[^\d]/g,'') "
                                               onbeforepaste="clipboardData.setData('text',
								               clipboardData.getData('text').replace(/[^\d]/g,''))"
                                               type="text"
                                        <#if map.bkorder.backOrderCode??>
                                               value="${map.bkorder.backOrderCode}"
                                        <#else>
                                               value=""
                                        </#if>/>
                                    </div>
                                </div>
                                <div class="control-group lg-group">
                                    <label class="control-label">退单时间：</label>
                                    <div class="controls">
                                        <input type="text" name="startTime" value="<#if map.bkorder.startTime??>${map.bkorder.startTime}</#if>" class="form-control sm-input datepicker" data-provide="datepicker"/>
                                        ~
                                        <input type="text" name="endTime" value="<#if map.bkorder.endTime??>${map.bkorder.endTime}</#if>" class="form-control sm-input datepicker" data-provide="datepicker"/>
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
                        <li class="active">
                            <a href="javascript:;"  onclick="changTbl(1)">退单审批</a>
                        </li>
                    </ul>

                    <div class="cfg-content">
                        <div class="ops-bar"></div>
                        <form class="simple_search" action="<#if map.pb.url??>${map.pb.url}<#else> </#if>" method="post">
                        </form>
                        <form class="high_search" action="<#if map.pb.url??>${map.pb.url}<#else> </#if>" method="post">
                            <table class="table table-bordered orders-table">
                                <thead>
                                    <tr>
                                        <th width="30%">商品</th>
                                        <th>退货数量</th>
                                        <#--<th>售后</th>-->
                                        <#--<th>买家</th>-->
                                        <th width="10%">退单时间</th>
                                        <th>订单状态</th>
                                        <th>退单金额</th>
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
                                                <td colspan="5">
                                                    <p>退单号：${order.backOrderCode}</p>
                                                </td>
                                                <td class="row-ops" colspan="2">
                                                    <a href="toexambackorder.html?backOrderId=<#if order.backOrderId??>${order.backOrderId}</#if>">查看详情</a>
                                                </td>
                                            </tr>
                                            <tr class="content-row">
                                                <td class="order-pros">
                                                <#--遍历该订单下面的所有商品的信息-->

                                                    <#if order.orderGoodslistVo?? && order.orderGoodslistVo?size gt 0>
                                                        <#list order.orderGoodslistVo as backGoods>
                                                            <a href="javascript:;" title="${backGoods.goodsInfoName!''}"><img src="${backGoods.goodsInfoImgId!'' }" alt="${backGoods.goodsInfoName!''}" width="60" height="60"/></a>
                                                        </#list>
                                                    <#else>
                                                      <span style="font-family: '微软雅黑'; padding-left:10px; font-size: 14px; color: #002a80; font-weight:bold; ">无</span>
                                                    </#if>
                                                </td>
                                                <td>
                                                    （<#if order.orderGoodslistVo??>${order.orderGoodslistVo?size}<#else></#if>件）
                                                </td>
                                                <#--<td>${order.shippingPerson!''}</td>-->
                                                <td><#if order.backTime??>${order.backTime?string("yyyy-MM-dd HH:mm:ss") }</#if></td>
                                                <td>


                                                    <#--0:退货申请 1:同意退货 2:拒绝退货 3:待收货 4:退单结束 6:审核退款 7:拒绝退款 8:收货失败 9:待客户填写物流地址-->

                                                    <#if order.backCheck??>
                                                        <#if order.backCheck=="0">
                                                            退货审核
                                                        <#elseif (order.backCheck=="1") >
                                                            同意退货
                                                        <#elseif order.backCheck=="2">
                                                            拒绝退货
                                                        <#elseif order.backCheck=="3">
                                                            待收货
                                                        <#elseif order.backCheck=="4">
                                                            退单结束
                                                        <#elseif order.backCheck=="6">
                                                            退款审核
                                                        <#elseif order.backCheck=="7">
                                                             拒绝退款
                                                        <#elseif order.backCheck=="9">
                                                            待客户填写物流地址
                                                        <#elseif order.backCheck=="10">
                                                            退款完成
                                                        </#if>
                                                    </#if>
                                                </td>
                                                <td>¥${order.backPrice!''}</td>
                                                <td>
                                                    <div class="btn-group">
                                                        <#if order.backCheck=="0">
                                                            <button type="button" class="btn btn-primary btn-sm"  data-toggle="modal" onclick="fnModifyBackOrder(<#if order.backOrderId??>${order.backOrderId}</#if>)" data-target="#modifyState"  style="width: 112px;">退单审核</button>
                                                        <#elseif  order.backCheck=="2">
                                                            <button type="button" class="btn btn-primary btn-sm"  data-toggle="modal" onclick="delBackOrder(<#if order.backOrderId??>${order.backOrderId}</#if>)" data-target="#delete_order"  style="width: 112px;">取消订单</button>
                                                        <#elseif order.backCheck=="6" || order.backCheck=="2">
                                                            <button type="button" class="btn btn-primary btn-sm"  data-toggle="modal" onclick="fnModifyBackOrderMoney(<#if order.backOrderId??>${order.backOrderId}</#if>)" data-target="#Order_Money"  style="width: 112px;">退款审核</button>
                                                        <#elseif order.backCheck=="10" || order.backCheck=="1">
                                                            <button type="button" class="btn btn-primary btn-sm btn_css"  style="width: 112px;">暂无操作</button>
                                                        <#elseif order.backCheck=="3">
                                                            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" onclick="getGoods(${order.backOrderId})" data-target="#get_Goods"  style="width: 112px;">确认收货</button>
                                                        <#else>
                                                            <button type="button" class="btn btn-primary btn-sm"  onclick="javascript:window.location.href='toexambackorder.html?backOrderId=<#if order.backOrderId??>${order.backOrderId}</#if>'">查看详情</button>
                                                            <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                                <span class="caret"></span>
                                                            </button>
                                                        </#if>

                                                        <ul class="dropdown-menu" role="menu">


                                                            <#if order.backCheck??&&order.backCheck=="0">
                                                                <li><a href="" data-toggle="modal" onclick="fnModifyBackOrder(<#if order.backOrderId??>${order.backOrderId}</#if>)" data-target="#modifyState">订单审核</a></li>
                                                            </#if>
                                                            <#--<#if order.backCheck??&&order.backCheck=="3">
                                                                <li><a href="" data-toggle="modal" onclick="getGoods(${order.backOrderId})" data-target="#get_Goods">确认收货</a></li>
                                                            </#if>-->
                                                            <#if order.backCheck??&&order.backCheck=="6">
                                                                <li><a href="" data-toggle="modal" onclick="fnModifyBackOrderMoney(${order.backOrderId})" data-target="#Order_Money">退款审核</a></li>
                                                            </#if>

                                                            <#if order.backCheck??&&order.backCheck=="1"||order.backCheck??&&order.backCheck=="4"||order.backCheck??&&order.backCheck=="7" >
                                                                <li><a href="" data-toggle="modal" onclick="delBackOrder(<#if order.backOrderId??>${order.backOrderId}</#if>)" data-target="#delete_order">订单取消</a></li>
                                                            </#if>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </#list>
                            </table>
                        </form>
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
                                        <script>
                                            /**
                                             * 分页
                                             * author IT_kang
                                             */
                                            function changePageNo(obj){
                                                /*获取查询的方式标记*/
                                                var show_flag=$(".show_flag").val();
                                                if(show_flag==1){
                                                    $(".simple_search").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
                                                }else{
                                                    $(".high_search").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
                                                }
                                            }
                                        </script>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <#else>
                            <tfoot>
                            <tr>
                                <td colspan="12">暂无信息~</td>
                            </tr>
                            </tfoot>
                        </#if>
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



<#--退款审核-->
<div class="modal fade" id="Order_Money" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="updateBackOrderStatus.htm" method="post" class="up_third_backOrder_money">
                <input type="hidden" id="returnMoneyId"  name="backOrderId" value="" />
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div class="form-item">
                        <label class="control-label">选择状态:</label>
                        <div class="controls">
                            <select class="form-control" name="backCheck" id="backCheck">
                                <option value="10">同意退款</option>
                                <option value="7">拒绝退款</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="returnMoneySub();" type="button">确定</button>
                    <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>



<#--取消订单弹出框-->
<div class="modal fade" id="delete_order" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="updateBackOrderStatus.htm" method="post" class="up_delete_order_sta">
                <input type="hidden" id="quedingbackOrderId" name="backOrderId" value=""/>
                <input type="hidden" id="backDelFlag" name="backDelFlag" value="1"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div class="form-item">
                        <label class="control-label">是否将此订单取消？</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="deleteOrder();" type="button">确定</button>
                    <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>

<#--确认收货弹出框-->
<div class="modal fade" id="get_Goods" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="updateBackOrderStatus.htm" method="post" class="up_queren_order_sta">
                <input type="hidden" id="backOrderId" name="backOrderId" value=""/>
                <input type="hidden" id="backCheck" name="backCheck" value="4"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div class="form-item">
                        <label class="control-label">是否确认收货？</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="querenshouhuo();" type="button">确定</button>
                    <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>

<#--订单审核弹出框-->
<div class="modal fade" id="modifyState" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="updateBackOrderStatus.htm" method="post" class="up_third_order_sta">
                <input type="hidden" id="updateOrderCheck"  name="backOrderId" value="" />
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div class="form-item">
                        <label class="control-label">选择状态：</label>
                        <div class="controls">
                            <select class="form-control"  name="backCheck" id="backCheck">
                                <option value="1">同意退单</option>
                                <option value="2">拒绝退单</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="subBackOrder();" type="button">确定</button>
                    <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
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
