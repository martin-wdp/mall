<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-运费模板列表</title>
<#assign basePath=request.contextPath />

<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" >
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/freight/freight.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${basePath}/js/bootstrap.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
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
                <li class="active" style="color: #07d;">运费模板</li>
            </ol>

            <div class="app-content">
                <div>
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active">
                            <a href="shop10.html">运费模板</a>
                        </li>
                    </ul>
                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm"  onclick="javascript:window.location.href='toAddFreightTemplatethird.html'" type="button"><i class="glyphicon glyphicon-plus"></i>添加模板</button>
                        </div>
                        <div class="freight-template">
                            <#list list as template>
                            <div class="cont-item">
                                <div class="tit-box">
                                    <#if template.freightIsDefault=='1'>
                                        <h3> ${template.freightTemplateName }
                                            <span class="default-temp">(默认模板)</span>
                                        </h3>
                                    </#if>
                                    <#if template.freightIsDefault=='0'>
                                        <h3>${template.freightTemplateName }
                                            <a class="default-temp" href="javascript:void(0);" data-toggle="modal" data-target="#give_freight" onclick="setDefault(${template.freightTemplateId});">设为默认</a>
                                        </h3>
                                    </#if>
                                    <div class="tit-right temp-ops">
                                        <span class="temp-date">最后编辑时间：<#if template.freightModifyTime??>${template.freightModifyTime?string("yyyy-MM-dd HH:mm:ss")}</#if></span>
                                        <a href="javascript:;" onclick="copytemp(${template.freightTemplateId})" >复制</a>
                                        <a href="javascript:;" onclick="toupdate(${template.freightTemplateId})">修改</a>
                                        <a href="javascript:;" data-toggle="modal" data-target="#delet_freight" onclick="setDelete(${template.freightTemplateId})">删除</a>
                                    </div>
                                </div>
                                <#list template.freightExpressList  as  freightExpresslist>
                                <p class="delivery">运送方式：
                                    <#if freightExpresslist.express??>
                                         ${freightExpresslist.express.expName }
                                    </#if>
                                </p>
                                    <#list template.freightExpressList  as  freightExpresslist>
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>运送至</th>
                                            <th>首<#if template.freightMethods=='0'>件(个)</#if><#if template.freightMethods=='1'>重(g)</#if></th>
                                            <th>运费（元）</th>
                                            <th>续<#if template.freightMethods=='0'>件(个)</#if><#if template.freightMethods=='1'>重(g)</#if></th>
                                            <th>运费（元）</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <#if freightExpresslist??>
                                                <tr>
                                                    <td>全国</td>
                                                    <td>${freightExpresslist.expressStart}</td>
                                                    <td>${freightExpresslist.expressPostage}</td>
                                                    <td>${freightExpresslist.expressPlusN1}</td>
                                                    <td>${freightExpresslist.expressPostageplus}</td>
                                                </tr>
                                            </#if>
                                            <#if freightExpresslist??>
                                                <#list freightExpresslist.freightExpressAll as templateall>
                                                    <tr>
                                                        <td><#if templateall.allCityName??>${templateall.allCityName}<#else>无</#if></td>
                                                        <td>${templateall.expressStart}</td>
                                                        <td>${templateall.expressPostage}</td>
                                                        <td>${templateall.expressPlusN1}</td>
                                                        <td>${templateall.expressPostageplus}</td>
                                                    </tr>
                                                </#list>
                                            </#if>
                                        </tbody>
                                    </table>
                                    </#list>
                                    </#list>
                                </div>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--设为默认模板弹出框-->
<div class="modal fade" id="give_freight" role="dialog" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="">确定要设置此模板为默认吗？</label>
                </div>
            </div>
            <form id="defaultTemplate" action="defaultfreighttemplatethird.html" method="post">
                <input type="hidden" name="freightTemplateId" id="defaultFreightTemplateId" />
            </form>
            <div class="modal-footer">
                <button class="btn btn-primary" id="primary" onclick="defaulttemplate()"  type="button">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>



<!--提示框-->
<div class="modal fade" id="delet_freight" role="dialog" aria-hidden="true" >
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
            <form id="deleteTemplate" action="deletefreighttemplatethird.html" method="post">
                <input type="hidden" name="freightTemplateId" id="freightTemplateId" />
            </form>
            <div class="modal-footer">
                <button class="btn btn-primary" id="primary" onclick="deltemplate()"  type="button">确定</button>
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
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
</body>
</html>
