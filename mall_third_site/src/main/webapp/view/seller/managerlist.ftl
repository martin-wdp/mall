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
                <li>我的店铺</li>
                <li class="active" style="color: #07d;">职位管理</li>
            </ol>

            <div class="app-content">
                <div class="role-wp">
                    <div class="role-box">
                        <div class="role-tit">
                            <h4>职位</h4>
                            <button class="btn btn-primary btn-xs" id="addRole" type="button"><i class="glyphicon glyphicon-plus"></i>新建</button>
                        </div>
                        <ul class="role-list">
                        <#if roles??>
                        <#list roles as role>
                            <li <#if role_index==0>class="active"</#if>>
                                <a class="role-des" href="javascript:;">${role.designation}</a>
                                <input type="hidden" value="${role.id}"/>
                                <div class="dropdown">
                                    <i class="glyphicon glyphicon-cog" data-toggle="dropdown" aria-expanded="false"></i>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick="updateRileName(this,${role.id})">重命名</a></li>
                                        <li><a href="javascript:;" onclick="deleteRile(${role.id})">删除</a></li>
                                    </ul>
                                </div>
                            </li>
                        </#list>
                        </#if>
                        </ul>
                    </div>

                    <div class="role-setting">
                        <div class="tit-box">
                            <h3>设置权限</h3>
                            <a class="text-primary tit-right add-power" <#--data-toggle="modal" data-target="#addPower"-->>添加权限</a>
                        </div>
                        <#--引入角色页面-->
                        <#import "authoritylist.ftl" as page>
                        <@page.authorityList roles/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<#--权限列表弹出框-->
<div class="modal fade" id="addPower" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">设置角色权限</h4>
            </div>
            <div class="modal-body">
                <div class="power-cont">
                    <#--<h4><label class="choose-label"><input type="checkbox"/>首页</label></h4>
                    <dl>
                        <dt><label class="choose-label"><input type="checkbox"/>商家首页</label></dt>
                    </dl>
                    <h4><label class="choose-label"><input type="checkbox"/>我的店铺</label></h4>
                    <dl>
                        <dt><label class="choose-label"><input type="checkbox"/>店铺管理</label></dt>
                        <dt><label class="choose-label"><input type="checkbox"/>银行账号验证</label></dt>
                        <dt><label class="choose-label"><input type="checkbox"/>信息接收设置</label></dt>
                    </dl>-->
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                <button class="btn btn-primary" type="button" onclick="updateAuth('${basePath}')">保存</button>
            </div>
        </div>
    </div>
</div>

<#--提示框ß-->
<div class="modal fade" id="operate-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" onclick="javascript:window.location.reload();">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="operate-tip-title">操作成功！</label>
                </div>
            </div>
            <div class="modal-footer">
            <#--<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>-->
                <button class="btn btn-primary result-confirm-btn" onclick="javascript:window.location.reload();" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="operate-tip-dupname" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" onclick="javascript:window.location.reload();">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="operate-tip-title">该名称已存在！</label>
                </div>
            </div>
            <div class="modal-footer">
            <#--<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>-->
                <button class="btn btn-primary result-confirm-btn" onclick="javascript:window.location.reload();" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<#--删除提示-->
<div class="modal fade" id="delete-tip" role="dialog" aria-hidden="true">
    <input type="hidden" value="" id="deleteKey"/>
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" onclick="javascript:window.location.reload();">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="operate-tip-title">确认删除吗！</label>
                </div>
            </div>
            <div class="modal-footer">
            <button class="btn btn-default delete-cancel-btn" type="button" data-dismiss="modal">取消</button>
                <button class="btn btn-primary delete-confirm-btn" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>


<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>

<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<!--模板相关END-->
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script src="${basePath}/js/sell/managerlist.js"></script>
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