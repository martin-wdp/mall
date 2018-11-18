<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-品牌管理</title>
<#assign basePath=request.contextPath>

<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css">
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>


<script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/grandbrand/grandbrandlists.js"></script>


</head>

<body>
<#-- 引入头部 -->
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
                <li class="active" style="color: #07d;">品牌授权管理</li>
            </ol>

            <div class="app-content">
                <div>
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active" onclick="changTbl(1)">
                            <a href="${basePath}/queryThirdGrandBrandLists.htm">品牌列表</a>
                        </li>
                        <li role="presentation">
                            <a href="${basePath}/queryThirdGrandBrandLists.htm?tabStatus=2">申请品牌</a>
                        </li>
                        <li role="presentation">
                            <a href="${basePath}/queryapplybrand.html">申请自定义</a>
                        </li>
                    </ul>
                    <form action="queryThirdGrandBrandLists.htm" method="post" class="queryThirdGrandBrandLists">
                        <input type="hidden" value="<#if map.tab_status??>${map.tab_status}<#else>1</#if>" name="tabStatus" class="tab_status">
                        <input type="hidden" class="brandId" name="brandId" />
                        <div class="query-wrap">
                            <div class="control-group">
                                <label class="control-label">品牌名：</label>
                                <div class="controls">
                                    <input type="text"  name="brandName"
                                    <#if map.goodsBrand.brandName??>
                                           value="${map.goodsBrand.brandName}"
                                    </#if>
                                    class="form-control"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">品牌别名：</label>
                                <div class="controls">
                                    <input type="text"  name="brandNickname"
                                    <#if map.goodsBrand.brandNickname??>
                                           value="${map.goodsBrand.brandNickname}"
                                    </#if>
                                           class="form-control"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">排序：</label>
                                <div class="controls">
                                    <input type="text"  name="brandSort"
                                        <#if map.goodsBrand.brandSort??>
                                           value="${map.goodsBrand.brandSort}"
                                    </#if>
                                           class="form-control"/>
                                </div>
                            </div>
                            <div class="query-operation">
                                <button class="btn btn-primary" onclick="submitBrand()" type="button">查询<i class="glyphicon glyphicon-search"></i></button>
                                <button class="btn btn-default" onclick="resetThirdOrder()" type="button">重置<i class="glyphicon glyphicon-refresh"></i></button>
                            </div>
                        </div>
                    </form>
                    <div class="cfg-content">
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="check_boxs"/></th>
                                <#--<th>序号</th>-->
                                <th>品牌LOGO</th>
                                <th>品牌名称</th>
                                <th>品牌别名</th>
                                <#--<th>品牌网址</th>-->
                                <th>品牌排序</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <form class="simple_search" action="<#if map.pb??>${map.pb.url}<#else></#if>" method="post">
                            </form>
                            <#if map.pb.list[0]??>
                                <form action="${basePath}/updateGrandBrands.htm" class="upd_GrandBrand" method="post">
                                    <input type="hidden" value="<#if map.tab_status??>${map.tab_status}<#else>1</#if>" name="tabStatus" class="tab_status">
                                    <input type="hidden" value="" name="brandIds" class="brandIds">
                                </form>
                                <form class="high_search" action="<#if map.pb??>${map.pb.url}<#else></#if>" method="post">
                                <#list map.pb.list as goodsBrand>
                                    <tbody>
                                        <tr>
                                            <td><input type="checkbox" class="brandIds" value="${goodsBrand.brandId}" name="brandIds" /></td>
                                            <#--<td>${goodsBrand_index+1}</td>-->
                                            <td><img src="<#if goodsBrand.brandLogo??>${goodsBrand.brandLogo}</#if>" alt="" width="119" height="60" /></td>
                                            <td>${goodsBrand.brandName}</td>
                                            <td>${goodsBrand.brandNickname}</td>
                                            <#--<td>${goodsBrand.brandUrl!''}</td>-->
                                            <td>${goodsBrand.brandSort!''}</td>
                                            <td>
                                                <#if goodsBrand.rateStatus??>
                                                     <#if goodsBrand.rateStatus == '2'>
                                                         <a href="javascript:;" onclick="refusalreason('${goodsBrand.reason!''}')">拒绝</a>
                                                     <#else>
                                                         <#if goodsBrand.rateStatus == '1'>
                                                             申请通过
                                                         <#else>
                                                             申请中
                                                         </#if>
                                                     </#if>
                                                <#else>申请中</#if>
                                            </td>
                                            <td><button class="btn btn-primary btn-sm"  onclick="removeGrandBrand(${goodsBrand.brandId})" type="button">移除</button></td>
                                        </tr>
                                    </tbody>
                                </#list>
                            </form>
                        </table>
                        <div class="footer-operation">
                            <div class="ops-left">
                                <button class="btn btn-primary btn-xs"  onclick="delGrandBrands()" type="button">批量删除</button>
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
                            <#else>
                                <tr>
                                    <td colspan="8">暂无信息~</td>
                                </tr>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<#--批量删除弹出框-->
<div class="modal fade" id="delete_brand_all" role="dialog" aria-hidden="true" >
    <input type="hidden" value="" class="exp_id_hide" />
    <input type="hidden" value="" class="flag_id_hide" />
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label" id="lableText">确认删除吗？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary"  onclick="delThirdGoodsBrandIds()"  type="button">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>



<#--选择一行弹出框-->
<div class="modal fade" id="delete_brand_check" role="dialog" aria-hidden="true" >
    <input type="hidden" value="" class="exp_id_hide" />
    <input type="hidden" value="" class="flag_id_hide" />
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">至少选中一行</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<#--删除弹出框-->
<div class="modal fade" id="delete_brand" role="dialog" aria-hidden="true" >
    <input type="hidden" value="" class="exp_id_hide" />
    <input type="hidden" value="" class="flag_id_hide" />
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label" id="lableText">确认删除吗？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary"  onclick="sub_update()"  type="button">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="cant_delete_brand" role="dialog" aria-hidden="true" >
    <input type="hidden" value="" class="exp_id_hide" />
    <input type="hidden" value="" class="flag_id_hide" />
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label>该品牌有商品无法删除</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="brand_reason" role="dialog" aria-hidden="true" >
    <input type="hidden" value="" class="exp_id_hide" />
    <input type="hidden" value="" class="flag_id_hide" />
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label  id="applyRefusalReason">该品牌有商品无法删除</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
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
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script>
    $(function(){
    	$.material.init();
    });
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
</body>
</html>
