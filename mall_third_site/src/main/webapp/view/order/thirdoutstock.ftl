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
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_vali.js" ></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/print_common.js"></script>
<script type="text/javascript" src="${basePath}/js/order/thirdorderlists.js"></script>
    <script type="text/javascript" src="${basePath}/js/order/thirdoutstocks.js"></script>
<script type="text/javascript" src="${basePath}/js/order/printpicking.js"></script>

<script>
    //批量拣货
    function pickingOrderIds(){
        var bool = false;
        var checks = $(".check_box");
        var checkGroup = new Array();
        for (var i = 0; i < checks.length; i++) {
            var e = checks[i];
            if (e.checked == true) {
                bool = true;
                checkGroup.push(e.value);
            }
        }
        $(".order_p_id").attr("value",checkGroup);
        if(bool){
            viewdetail("thirdorderpicking.htm?orderId="+checkGroup+'&CSRFToken='+$("#csrf_token").val());
        }else {
            $('#check_row').modal('show')
            return null;
        }
    }


    //批量拣货 弹出框样式
    function viewdetail(url) {
        var winName="newWin";
        var awidth=screen.availWidth/3*2;       	//窗口宽度,需要设置
        var aheight=screen.availHeight/2.5*2;       //窗口高度,需要设置
        var atop=(window.screen.availHeight+30- aheight)/2;  //窗口顶部位置,一般不需要改
        var aleft=(window.screen.availWidth - awidth)/2;   //窗口放中央,一般不需要改
        var param0="scrollbars=0,status=0,menubar=0,resizable=no,location=no"; //新窗口的参数
        var params="top=" + atop + ",left=" + aleft + ",width=" + 900+ ",height=" + 500 + "," + param0 ;
        win=window.open(url,winName,params); //打开新窗口
        win.focus(); //新窗口获得焦点
    }

    $(function(){
    	$.material.init();
    
        /*全选*/
        $("#check_boxs").click(function(){
            var sc_id="";
            if($(this).prop("checked")){
                $(".check_box").each(function(){
                    if(! $(this).prop("checked")){
                        $(this).prop("checked",true);
                    }
                });
                $(".check_box").each(function(){
                    sc_id += $(this).val()+"|";
                });
            }else{
                $(".check_box").each(function(){
                    if($(this).prop("checked")){
                        $(this).prop("checked",false);
                    }
                });
            }
        });

        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        })
    });

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
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
                <li class="active" style="color: #07d;">出库列表</li>
            </ol>
            <input type="hidden" value="" class="sub_orderid" name="orderId">
            <div class="app-content">
                <div>
                    <div class="search-block">
                        <form action="querythirdoutstock.html" class="searchThirdOrderList" method="post">
                            <input type="hidden" value="<#if map.order.orderCargoStatus??>${map.order.orderCargoStatus}</#if>" name="orderCargoStatus" class="tabStatus">
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
                                        </#if>
                                               type="text" />
                                    </div>
                                </div>
                                <div class="control-group lg-group">
                                    <label class="control-label">订单时间：</label>
                                    <div class="controls">
                                        <input type="text" class="form-control sm-input datepicker" data-provide="datepicker"/>
                                        ~
                                        <input type="text" class="form-control sm-input datepicker" data-provide="datepicker"/>
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
                        <li class="tb_backOrderSH" onclick="changTbl('0')"><a href="javascript:;">拣货列表</a></li>
                        <li class="tb_backOrderTg" onclick="changTbl('1')"><a href="javascript:;">装箱列表</a></li>
                        <li class="tb_backOrderJj" onclick="changTbl('2')"><a href="javascript:;">出库列表</a></li>
                    </ul>

                    <div class="cfg-content">
                        <div class="ops-bar"></div>
                        <form class="simple_search" action="<#if map.pb.url??>${map.pb.url}<#else> </#if>" method="post"></form>
                        <form class="high_search" action="<#if map.pb.url??>${map.pb.url}<#else> </#if>" method="post">
                        <table class="table table-bordered orders-table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="check_boxs"/></th>
                                <th width="30%">商品</th>
                                <th>商品种类</th>
                                <th>买家</th>
                                <th width="10%">下单时间</th>
                                <th>订单状态</th>
                                <th>出库状态</th>
                                <th>实付金额</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                    <#--拣货-->
                    <#list map.pb.list as order>
                        <form action="" method="post" class="subFom" id="yourformid">
                            <input type="hidden" name="orderId" value="${order.orderId}">
                        </form>
                    </#list>
                            <#if map.pb.list[0]??>
                                <#list map.pb.list as order>
                            <tbody>
                            <tr class="separation-row">
                                <td colspan="9"></td>
                            </tr>
                            <tr class="header-row">
                                <td colspan="7">
                                    <p>订单号：${order.orderCode}</p>
                                </td>
                               <td class="row-ops" colspan="2">
                                   <#-- <a href="order-detail.html">查看详情</a>-->
                                </td>
                            </tr>



                            <tr class="content-row">
                                <td><input type="checkbox" class="check_box" value="${order.orderId}" id="orderId"  name="orderId"/></td>
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
                                    <p>（<#if order.orderGoodsList??>${order.orderGoodsList?size}<#else></#if>件）</p>
                                </td>
                                <td>${order.shippingPerson!''}</td>
                                <td><#if order.createTime??>${order.createTime?string("yyyy-MM-dd HH:mm:ss") }</#if></td>
                                <td>
                                    <#if order.orderStatus=="1">
                                        <span class="label label-primary">已付款</span>
                                    <#elseif order.orderStatus=="0">
                                        <font color="red">待付款</font>
                                    </#if>
                                </td>
                                <td>
                                    <span class="label label-default">
                                        <#if order.orderCargoStatus=='0'>未拣货<#elseif order.orderCargoStatus=='1'>未装箱<#elseif order.orderCargoStatus=='2'>未出库
                                        <#elseif order.orderCargoStatus=='3'>已拣货<#elseif order.orderCargoStatus=='4'>已装箱<#elseif order.orderCargoStatus=='5'>已出库</#if>
                                    </span>
                                </td>
                                <td>¥${(order.orderPrice)?string("0.00")}</td>
                                <td>
                                    <button class="btn btn-primary btn-sm" onclick="sub_Form(${order.orderCargoStatus},${order.orderId})" type="button" data-toggle="modal" <#--data-target="#gdPicking"-->>
                                        <#if order.orderCargoStatus=='0' >
                                            拣货
                                        <#elseif order.orderCargoStatus=='1'>
                                            装箱
                                        <#elseif order.orderCargoStatus=='2'>
                                            出库
                                        </#if>
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                            </#list>
                        </table>
                        </form>
                        <div class="footer-operation">
                            <div class="ops-left">
                                <#if order.orderCargoStatus??&&  order.orderCargoStatus=="0">
                                    <button class="btn btn-primary btn-xs"  onclick="pickingOrderIds()" type="button">批量拣货</button>
                                </#if>
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


<!--批量拣货 至少选中一行-->
<div class="modal fade" id="check_row" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">操作提示</h4>
            </div>
            <div class="modal-body">
                请至少选择一行数据
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="operate-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" onclick="javascript:window.location.reload();">×</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="operate-tip-title">请先打印</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary result-confirm-btn" onclick="javascript:window.location.reload();" type="button" data-dismiss="modal">确定</button>
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
</body>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</html>
