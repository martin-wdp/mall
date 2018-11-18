<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商城第三方后台-订单管理</title>
<#assign basePath=request.contextPath>

<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>

<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/angular.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/order/thirdoutstocks.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/order/thirdorderlists.js"></script>

<script>
    $(function(){
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
        <div class="ui-footer"><a href="javascript:;">© qianmi.com</a></div>

    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li><a href="javascript:;">订单管理</a></li>
                <li class="active">装箱列表</li>
            </ol>

            <div class="app-content">
                <div>
                    <div class="search-block">
                        <form action="querythirdoutstock.html" class="searchThirdOrderList" method="post">
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
                                        <input type="text" class="form-control sm-input"/>
                                        ~
                                        <input type="text" class="form-control sm-input"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">订单编号：</label>
                                    <div class="controls">
                                        <input class="form-control" name="orderCode"
                                        <#if map.order.orderCode??>
                                               value="${map.order.orderCode}"
                                        <#else>
                                               value=""
                                        </#if>
                                               type="text" />
                                    </div>
                                </div>
                                <div class="control-group lg-group">
                                    <label class="control-label">上架时间：</label>
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

                        <table class="table table-bordered orders-table">
                            <thead>
                            <tr>
                                <th width="30%">商品</th>
                                <th>单价/数量</th>
                                <th>售后</th>
                                <th>买家</th>
                                <th width="10%">下单时间</th>
                                <th>订单状态</th>
                                <th>出库状态</th>
                                <th>实付金额</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="separation-row">
                                <td colspan="9"></td>
                            </tr>
                            <tr class="header-row">
                                <td colspan="7">
                                    <p>订单号：E20150427141513921635451</p>
                                </td>
                                <td class="row-ops" colspan="2">
                                    <a href="order-detail.html">查看详情</a>
                                </td>
                            </tr>
                            <tr class="content-row">
                                <td class="order-pros">
                                    <a href="javascript:;" title="商品1"><img src="../images/images_06.jpg" alt="商品1" width="60" height="60"/></a>
                                    <a href="javascript:;" title="商品2"><img src="../images/images_03.jpg" alt="商品2" width="60" height="60"/></a>
                                </td>
                                <td>
                                    &yen;76.00
                                    <p>（2件）</p>
                                </td>
                                <td></td>
                                <td>test</td>
                                <td>2015-04-27 14:15:13</td>
                                <td><span class="label label-primary">已付款</span></td>
                                <td><span class="label label-default">未拣货</span></td>
                                <td>0.10</td>
                                <td>
                                    <button class="btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#gdPicking">装箱</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div class="footer-operation">
                            <div class="ops-left">
                            </div>
                            <div class="ops-right">
                                <nav>
                                    <ul class="pagination">
                                        <li class="disabled">
                                            <a href="javascript:;" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <li class="active"><a href="javascript:;">1</a></li>
                                        <li><a href="javascript:;">2</a></li>
                                        <li><a href="javascript:;">3</a></li>
                                        <li><a href="javascript:;">4</a></li>
                                        <li><a href="javascript:;">5</a></li>
                                        <li>
                                            <a href="javascript:;" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
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

<div class="modal fade" id="gdPicking" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">拣货管理</h4>
            </div>
            <div class="modal-body">
                <div class="picking-info">
                    <span><b>收货人：</b>qianmi</span>
                    <span><b>订购日期：</b>2015-05-14</span>
                    <span><b>付款方式：</b>在线支付</span>
                    <span><b>订单号：</b>2015051214295244</span>
                    <span><b>收货地址：</b>安徽 安庆 安庆市 23</span>
                </div>
                <h4 class="picking-box-tit">装箱单1</h4>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>商品编号</th>
                        <th>商品名称</th>
                        <th>价格</th>
                        <th>商品数量</th>
                        <th>小计</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>2015011910001</td>
                        <td>360儿童卫士2 智能手表</td>
                        <td>289</td>
                        <td>1</td>
                        <td>289</td>
                        <td>
                            <button class="btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#changeBox">更换包裹</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <button class="btn btn-primary btn-xs" type="button">删除此装箱单</button>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button">打印</button>
                <button class="btn btn-primary" type="button">装箱</button>
                <button class="btn btn-primary" type="button">新增装箱单</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="changeBox" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">请选择装箱单：</label>
                    <div class="controls">
                        <select class="form-control">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">请输入数量：</label>
                    <div class="controls">
                        <input type="text" class="form-control sm-input"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal" type="button">取消</button>
                <button class="btn btn-primary" type="button">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</html>
