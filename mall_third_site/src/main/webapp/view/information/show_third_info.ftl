<!doctype html>
<html lang="zh-CN">
<#assign basePath=request.contextPath>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>商品管理</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/bootstrap-chosen.css"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/summernote.css"/>
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>
<body>
<#--引入头部-->
<#include "../index/indextop.ftl">
<input type="hidden" value="${basePath}" id="basePath"/>

<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#-- 引入左侧菜单 -->
        	<#import "../index/indexleft.ftl" as leftmenu>
			<@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li><a href="goods01.html">商品管理</a></li>
                <li class="active">添加商品</li>
            </ol>

            <form class="form-horizontal" id="infoForm" action="" method="post">
                <input name="infoId" id="up_infoId" type="hidden" value="${(info.infoId)?default('')}"/>
                <div class="app-content">
                <div class="form-container">
                    <form action="">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="javascript:;">添加文章</a>
                            </li>
                        </ul>
                            <div class="form-group error">
                                <label class="control-label col-xs-3"><b>*</b>文章标题：</label>
                                <div class="controls col-xs-7">
                                    <input name="title" id="up_title" type="text" class="form-control" value="${(info.title)?default('')}"/>
                                    <label class="tips error-tip"></label>
                                </div>
                            </div>
                            <div class="form-group error">
                                <label class="control-label col-xs-3">简略标题：</label>
                                <div class="controls col-xs-7">
                                    <input type="text"  name="shortTitle" id="up_sortTitle" class="form-control" value="${(info.shortTitle)?default('')}"/>
                                    <label class="tips error-tip"></label>
                                </div>
                            </div>
                            <div class="form-group error">
                                <label class="control-label col-xs-3">作者：</label>
                                <div class="controls col-xs-7">
                                    <input name="author" id="up_author" type="text" class="form-control" value="${(info.author)?default('')}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3">所属栏目：</label>
                                <div class="controls col-xs-7">
                                    <select class="form-control" name="infoTypeId">
                                    <#if infoTypes??>
                                        <#list infoTypes as infoType>
                                            <option value="${infoType.infoTypeId}"
                                                    <#if (info.infoTypeId)?? && info.infoTypeId==infoType.infoTypeId>selected="selected"</#if>
                                                    >${infoType.name}</option>
                                        </#list>
                                    </#if>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3">是否发布：</label>
                                <div class="controls col-xs-7 radio radio-primary">
                                    <label class="choose-label"><input type="radio" name="isShow" value="1"
                                                                       <#if !(info.isShow)??>checked="checked"</#if>
                                                                       <#if (info.isShow)?? && info.isShow=='1'>checked="checked"</#if>/>是</label>
                                    <label class="choose-label"><input type="radio" name="isShow" value="0"
                                                                       <#if (info.isShow)?? && info.isShow=='0'>checked="checked"</#if>/>否</label>
                                </div>
                            </div>
                            <div class="form-group error">
                                <label class="control-label col-xs-3"><b>*</b>排序：</label>
                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control sm-input" name="sort" id="sort" value="${(info.sort)?default('')}"/>
                                    <label class="tips error-tip"></label>
                                </div>
                            </div>
                            <div class="form-group error">
                                <label class="control-label col-xs-3">关键字：</label>
                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control" placeholder="默认为文章名称" name="keyword" id="keyword" value="${(info.keyword)?default('')}"/>
                                    <label class="tips error-tip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3 error">内容摘要：</label>
                                <div class="controls col-xs-7">
                                    <textarea class="form-control" placeholder="默认为文章名称" name="description" id="description">${(info.description)!''}</textarea>
                                    <label class="tips error-tip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3">文章内容：</label>
                                <div class="controls col-xs-9">
                                    <div id="articleCont">${(info.content)?default('')}</div>
                                    <textarea id="up_content" name="content" style="display: none">${(info.content)?default('')}</textarea>
                                </div>
                            </div>
                        <div class="add-sorts-operation">
                            <button class="btn btn-default" type="button" id="back">关闭</button>
                            <button class="btn btn-primary" type="button" id="save">保存</button>
                        </div>
                    </form>
                </div>
            </div>
            </form>
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

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/summernote.min.js"></script>
<script src="${basePath}/js/summernote-zh-CN.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<!--模板相关END-->
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script>
    $(function(){
    	$.material.init();
        $("#articleCont").summernote({
            lang: 'zh-CN',
            height: 200,
            onImageUpload: function(files, editor, $editable) {
                sendFile(files,editor,$editable);
                }
        });
    });

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

</script>
<script src="${basePath}/js/information/show_third_info.js"></script>
</body>
</html>