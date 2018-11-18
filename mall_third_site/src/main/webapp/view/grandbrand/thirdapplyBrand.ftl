<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<script type="text/javascript"  src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript"  src="${basePath}/js/grandbrand/grandbrandlists.js"></script>
<script type="text/javascript"  src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/ajaxMutiUploadFile.js"></script>
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
                        <li role="presentation"  onclick="changTbl(1)">
                            <a href="${basePath}/queryThirdGrandBrandLists.htm">品牌列表</a>
                        </li>
                        <li role="presentation" >
                            <a href="${basePath}/queryThirdGrandBrandLists.htm?tabStatus=2">申请品牌</a>
                        </li>
                        <li role="presentation" class="active">
                            <a href="${basePath}/queryapplybrand.html">申请自定义</a>
                        </li>
                    </ul>

                    <form action="queryThirdGrandBrandLists.htm" method="post" class="queryThirdGrand">
                        <input type="hidden" name="tabStatus" class="tab_status" value="2">
                    </form>

                    <form action="${basePath}/queryapplybrand.html" method="post" class="queryapplybrand">
                        <div class="query-wrap">
                            <div class="control-group">
                                <label class="control-label">品牌名：</label>
                                <div class="controls">
                                    <input type="text"
                                    <#if map.applyBrand.applyBrandName??>
                                           value="${map.applyBrand.applyBrandName}"
                                    </#if>
                                    class="form-control" name="applyBrandName"/>
                                </div>
                            </div>
                            <div class="query-operation">
                                <button class="btn btn-primary" onclick="submitApplyBrand()" type="button">查询<i class="glyphicon glyphicon-search"></i></button>
                                <button class="btn btn-default" onclick="resetThirdApply()" type="button">重置<i class="glyphicon glyphicon-refresh"></i></button>
                            </div>
                        </div>
                    </form>

                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm" type="button"  data-toggle="modal" data-target="#applyBrand"><i class="glyphicon glyphicon-plus"></i>申请自定义品牌</button>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="check_boxs"/></th>
                                <#--<th>序号</th>-->
                                <th>品牌LOGO</th>
                                <th>品牌证书</th>
                                <th>品牌名称</th>
                                <#--<th>品牌网址</th>-->
                                <th>状态</th>
                                <th>拒绝原因</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <form class="simple_search" action="<#if map.pb.url??>${map.pb.url}<#else></#if>" method="post">
                            </form>
                            <#if map.pb.list[0]??>
                                <form class="high_search" action="<#if map.pb.url??>${map.pb.url}<#else></#if>" method="post">
                                <#list map.pb.list as applyBrand>
                                    <tbody>
                                        <tr>
                                            <td><input type="checkbox" class="brandIds" value="${applyBrand.applyBrandId}" name="applyBrandId" /></td>
                                            <#--<td>${applyBrand_index+1}</td>-->
                                            <td><img src="<#if applyBrand.applyBrandPic??>${applyBrand.applyBrandPic}</#if>" alt="" width="90" height="40" /></td>
                                            <td><img src="<#if applyBrand.applyBrandPic??>${applyBrand.applyCertificate}</#if>" alt="" width="90" height="40" /></td>
                                            <td>${applyBrand.applyBrandName}</td>
                                            <#--<td>${applyBrand.applyUrl!''}</td>-->
                                            <td>
                                                <#if applyBrand.applyStatus??>
                                                    <#if applyBrand.applyStatus == '2'><a href="javascript:;" onclick="refusalreason('${applyBrand.applyRefusalReason}')">拒绝</a><#elseif applyBrand.applyStatus == '1'>审核通过<#else>申请中</#if>
                                                <#else>申请中</#if>
                                            </td>
                                            <#if applyBrand.applyRefusalReason??>
                                                <td title="${applyBrand.applyRefusalReason}">
                                                    <#if applyBrand.applyRefusalReason?length gt 15 >
                                                         ${applyBrand.applyRefusalReason?substring(0,14)}......
                                                    <#else>
                                                         ${applyBrand.applyRefusalReason}
                                                    </#if>
                                                </td>
                                            <#else>
                                                <td>
                                                    无
                                                </td>
                                            </#if>
                                            <td><button class="btn btn-primary btn-sm"  onclick="removeApplyBrand(${applyBrand.applyBrandId})" type="button">移除</button></td>
                                        </tr>
                                    </tbody>
                                </#list>
                        </table>
                        <div class="footer-operation">
                            <div class="ops-left">
                                <button class="btn btn-primary btn-xs"  onclick="delGrandBrands('0')" type="button">批量删除</button>
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
                <button class="btn btn-primary"  onclick="delThirdApplyBrandIds()"  type="button">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<#--选中一行提示-->
<div class="modal fade" id="delete_brand_check" role="dialog" aria-hidden="true" >
    <input type="hidden" id="applyBrandIddel" name="applyBrandId" value=""/>
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
<div class="modal fade" id="delete_apply_brand" role="dialog" aria-hidden="true" >
    <input type="hidden" id="applyBrandIddel" name="applyBrandId" value=""/>
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
                <button class="btn btn-primary" id="primary" onclick="sub_updateapply();" type="button">确定</button>
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

<#--申请自定义弹出窗-->
<div class="modal fade" id="applyBrand" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="third/addbrands.htm" method="post" enctype="multipart/form-data" class="addapplybrand">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">申请自定义品牌</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label"><b>*</b>品牌名称：</label>
                    <div class="controls">
                        <input type="text" name="applyBrandName" maxlength="30" id="applyBrandName" onblur="checkOtherName(this);" class="form-control" />
                        <span id="brandtip" style="color:red;"></span>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>请选择图片：</label>
                    <div class="controls">
                        <input type="file" id="applyPic" name="applyPic"/>
                        <span id="applyPictip" style="color:red;"></span>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>证书：</label>
                    <div class="controls">
                        <input type="file" id="certificate" name="certificate"/>
                        <span id="certificatetip"  style="color:red;"></span>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary" onclick="addApply()" type="button">添加</button>
            </div>
            </form>
        </div>
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
