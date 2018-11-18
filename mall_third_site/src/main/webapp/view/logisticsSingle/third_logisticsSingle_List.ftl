<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物流单列表</title>
<#assign basePath=request.contextPath />
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<title>我的店铺</title>
<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" >
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/third.css" />
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
                <li>我的店铺</li>
                <li class="active" style="color: #07d;">物流单管理</li>
            </ol>

            <div class="app-content">
                <div>
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active">
                            <a href="">物流单列表</a>
                        </li>
                    </ul>
                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm" id="save_btn-sm" type="button" onclick="javascript:window.location.href='toAddThirdLogisticsSingle.htm'"><i class="glyphicon glyphicon-plus"></i>添加</button>
                            <label class="add-tips">(物流单和物流公司一一对应)</label>
                        </div>
                        <table class="table">
                            <thead>
	                            <tr>
	                                <th>图片</th>
	                                <th>物流公司名称</th>
	                                <th>宽度</th>
	                                <th>高度</th>
	                                <th>创建时间</th>
	                                <th>操作</th>
	                            </tr>
                            </thead>
                            <#if (map.pb.list?size) &gt; 0 >
                            <tbody>
                                <#list map.pb.list as info>
                                <tr>
                                    <td><img alt="" src="${(info.logisticsSingleImg)!''}" width="30" height="30"/></td>
                                    <td>${(info.companyName)!''}</td>
                                    <td>${(info.logisticsSingleWidth)!''}</td>
                                    <td>${(info.logisticsSingleHeight)!''}</td>
                                    <td>${(info.createTime)?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>
                                        <div class="btn-group">
                                            <input id="logisticsSingleId" type="hidden" value=""/>
                                            <button type="button" onclick="updateInfo(${info.logisticsSingleId})" class="btn btn-primary btn-sm">编辑</button>
                                            <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="javascript:;"   data-toggle="modal" data-target="#addImg_express" onclick="delInfo(${info.logisticsSingleId})"  >删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                            </#list>
                        </table>

                        <div class="footer-operation">
                            <div class="ops-right">
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
                            </div>
                        </div>

                        <#else>
                            <tfoot>
                            <tr>
                                <td colspan="6">暂无数据</td>
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

<div class="modal fade" id="addImg_express" role="dialog" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">确认删除吗？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="deleteexpress()" type="button">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<div class="dialog s_dia dia2" style="width: 300px; height: 150px;display: none; ">
    <div class="sd_tit clearfix"><h3 class="fl">操作提示</h3>
        <a class="sd_close fr" href="javascript:;" onclick="cls();"></a>
    </div>
    <div class="pmt_wp" style="height: 50px;">
        <p class="tc f14" id="tcText" style="line-height:70px;">包裹内存在商品，不可删除！</p>
    </div>
    <div class="tc mt20">
        <a class="sop_btn" href="javascript:void(0);" onclick="cls();">确定</a>
    </div>
</div>

</body>
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/express.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-ui/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${basePath}/js/system/system.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/drag.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/customer.js"></script>
<script charset="utf-8" src="${basePath}/js/themes/themes.js"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/plugins/iframeTools.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${basePath}/js/drag.jquery.js"></script>



<script>
    function delInfo(logisticsSingleId){
        $("#logisticsSingleId").val(logisticsSingleId);
    }
    function updateInfo(logisticsSingleId){
        window.location="toUpdateThirdLogisticsSingle.htm?logisticsSingleId="+logisticsSingleId;
    }

    /*删除图片*/
    function deleteexpress(){
        $.ajax({
            type:'post',
            async:false,
            url:'dellogisticssingles.htm?logisticsSingleId='+$("#logisticsSingleId").val(),
            success:function(data){
                if(data==1){
                    location.href="logisticssingleThirdList.htm?n=2&l=98";
                }
            }
        });

    }
    $(function(){
    	$.material.init();
    });
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</html>
