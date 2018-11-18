<!doctype html>
<html lang="zh-CN">
<#assign basePath=request.contextPath>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>我的店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
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
                <li class="active" style="color: #07d;">功能设置</li>
            </ol>

            <div class="app-content">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="javascript:;">功能设置</a>
                    </li>
                </ul>
                <div class="cont-item">
                    <div class="title">
                        <h3>店铺首页</h3>
                    </div>
                    <div class="info-cont info-configure">
                     <form id="storeIndexForm" action="${basePath}/updateStoreIndexState.htm" mothed="post">
                         <div class="info-item">
                            <label class="control-label">是否启用店铺首页：</label>
                            <div class="controls radio radio-primary" style="margin-top:0;">
                                <label class="choose-label"><input name="isStoreIndex"
                                                                   <#if !(info.isStoreIndex)??>checked="checked"</#if>
                                                                   <#if info.isStoreIndex?? && info.isStoreIndex=='0'>checked="checked"</#if> value="0" type="radio"/>不启用</label>
                                <label class="choose-label"><input name="isStoreIndex"
                                                                   <#if info.isStoreIndex?? && info.isStoreIndex=='1'>checked="checked"</#if> value="1" type="radio"/>启用</label>
                            </div>
                        </div>
                        <#--<div class="info-item">
                            <div class="controls configure-box">
                                <a class="btn btn-primary edit-url" role="button" tabindex="0">设置店铺首页URL</a>
                                <!--<a class="btn btn-primary edit-domain-name">设置店铺域名</a>&ndash;&gt;
                            </div>
                        </div>-->
                        <div class="info-item">
                            <div class="controls">
                                <button class="btn btn-primary" id="submitUpdate" type="button">保存</button>
                            </div>
                        </div>
                         <#--<input type="hidden" name="domain" value="${domain.domain}" id="domainText"/>
                         <input type="hidden" name="storeUrl" value="${storeUrl}" id="storeUrl"/>-->
                     </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--修改首页URL表单-->
<form id="storeIndexUrlForm" action="${basePath}/updateThirdStoreURL.htm" mothed="post">
    <input type="hidden" name="url" id="up_url" value="${thirdStoreURL!''}"/>
</form>

<#--修改域名表单-->
<form id="storeIndexDomainForm" action="${basePath}/updatedomain.htm" mothed="post">
    <input type="hidden" name="customerId" id="custId" value="${thirdId}"/>
    <input type="hidden" name="domain" id="domainText" value="<#if domainCustom??>${(domainCustom.domain)!'' }</#if>"/>
    <input type="hidden" name="dmCuId" value="<#if domainCustom??>${(domainCustom.dmCuId)!'' }</#if>"/>

</form>

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

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script src="${basePath}/js/app.js"></script>
<script>
    /*设置店铺URL地址*/
    function setStoreUrl(){
        var url=$("#storeUrlVal").val()
        $("#up_url").val(url);
        $("#storeIndexUrlForm").submit();
    }
    /*设置二级域名地址*/
    function setSubDomain(){
        $("#domainText").val($("#domainVal").val());
        $("#storeIndexDomainForm").submit();
    }
    $(function(){
		$.material.init();
        var popoverTEmplate = ['<div class="timePickerWrapper popover">',
            '<div class="arrow"></div>',
            '<div class="popover-content">',
            '</div>',
            '</div>'].join('');
        var content = ['<div class="configure-popover"><input id="storeUrlVal" length="20" value="${storeUrl}" class="form-control" type="text" style="width:100%;"/>',
            '<label>(请按"http://前台商城首页地址/"的格式填写)</label>',
            '<div class="config-ops"><button class="btn btn-primary" onclick="setStoreUrl()" type="button">提交</button></div></div>', ].join('');
        var domainContent = ['<div class="configure-popover"><table class="table table-bordered"><thead><tr><th>域名</th><th>状态</th></tr></thead>',
            '<tbody><tr><td><input id="domainVal" value="<#if domainCustom??>${domainCustom.domain!''}</#if>" class="form-control" type="text"/>.www.qianmi.com</td><td>开启</td></tr><tr><td colspan="2"><label id="domainTip">(请填写正确的二级域名地址)</label></td></tr></tbody>',
            '</table><div class="config-ops"><button class="btn btn-primary" onclick="setSubDomain();" type="button">提交</button></div></div>', ].join('');

        $(".edit-url").popover({
            container: '.configure-box',
            template: popoverTEmplate,
            content: content,
            placement: 'bottom',
            trigger: 'click',
            html: true
        });
        $(".edit-domain-name").popover({
            container: '.configure-box',
            template: popoverTEmplate,
            content: domainContent,
            placement: 'bottom',
            trigger: 'click',
            html: true
        });
        $(document).click(function(){
            if(!$(event.target).isChildAndSelfOf(".popover, .edit-url")) {
                $(".edit-url").popover('hide');
            }
        });
        $(document).click(function(){
            if(!$(event.target).isChildAndSelfOf(".popover, .edit-domain-name")) {
                $(".edit-domain-name").popover('hide');
            }
        });

        /*提交表单*/
        $("#submitUpdate").click(function(){
            $("#storeIndexForm").submit();
        });
        /*$("#setStoreUrl").on("click",function(){
           alert("aaa");
        });*/
    });
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

</script>
<!--模板相关END-->
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
</html>