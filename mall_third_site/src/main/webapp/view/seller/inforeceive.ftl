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
                <li><a href="javascript:;">我的店铺</a></li>
                <li class="active">信息接收设置</li>
            </ol>

            <div class="app-content">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="javascript:;">消息接收设置</a>
                    </li>
                </ul>
                <div class="cont-item">
                    <p class="bg-warning tips-word">提醒：不同模块可设置不同的接收邮件和手机；子账号的消息接收权限请在“账号管理”主菜单中分配</p>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>模块名称</th>
                            <th>接收方式</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list messes as mess>
                        <tr>
                            <td>${mess_index+1}</td>
                            <td>${mess.messModName}</td>
                            <td>
                                <#if mess.messRecType??>
                                    <#assign a=(mess.messRecType)?split(',') />
                                    <#if (a[0]??) >
                                        <#if (a[0]=='0') >
                                            IM接收
                                        <#elseif (a[0]=='1')>
                                            邮件接收
                                        <#elseif (a[0]=='2')>
                                            手机短信接收
                                        </#if>
                                    </#if>
                                    <#if (a[1]??) >
                                        <#if (a[1]=='0') >
                                            /IM接收
                                        <#elseif (a[1]=='1')>
                                            /邮件接收
                                        <#elseif (a[1]=='2')>
                                            /手机短信接收
                                        </#if>
                                    </#if>
                                    <#if (a[2]??) >
                                        <#if (a[2]=='0') >
                                            /IM接收
                                        <#elseif (a[2]=='1')>
                                            /邮件接收
                                        <#elseif (a[2]=='2')>
                                            /手机短信接收
                                        </#if>
                                    </#if>
                                </#if>
                            </td>
                            <td>${mess.messModCom}</td>
                            <td><a class="btn btn-primary btn-sm infoset-btn" data-toggle="modal" data-key="${mess.messModId}" r-type="${mess.messRecType!''}" data-target="#settingDia">设置</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<div class="modal fade" id="settingDia" role="dialog" aria-hidden="true">
    <form method="post" id="mess_set_from">
        <input type="hidden" value="" name="messModId" id="mid_hide" />
        <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">接收设置</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="choose-label"><input type="checkbox" value="0" name="messRecType" id="im_r" type="checkbox"/>IM接收</label>
                </div>
                <div class="form-item">
                    <label class="choose-label"><input type="checkbox" value="1" name="messRecType" id="email_r" />邮件接收</label>
                    （<a class="receive-setting text-primary" href="javascript:;">设置邮箱地址</a>）
                    <input type="text" class="form-control hide" id="email_t" name="relaEmail" placeholder="请在此输入邮箱地址${basePath}."/>
                    <label class="tips error-tip"></label>
                </div>
                <div class="form-item">
                    <label class="choose-label"><input type="checkbox" value="2" name="messRecType"id="mobile_r"/>短信接收</label>
                    （<a class="receive-setting text-primary" href="javascript:;">设置手机号码</a>）
                    <input type="text" class="form-control hide" id="mobile_t" name="relaMobile" placeholder="请在此输入手机号码${basePath}."/>
                    <label class="tips error-tip"></label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                <button class="btn btn-primary" type="button" id="confirm">确定</button>
            </div>
        </div>
    </div>
    </form>
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
<script src="${basePath}/js/sell/inforeceive.js"></script>
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