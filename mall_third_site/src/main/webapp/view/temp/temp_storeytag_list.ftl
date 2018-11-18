<!doctype html>
<html lang="zh-CN" ng-app>
<#assign basePath=request.contextPath>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>我的店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <link href="${basePath}/css/material.css" rel="stylesheet">
</head>
<body>

<#--引入头部-->
<#include "../index/indextop.ftl">
<input type="hidden" value="${basePath}" id="basePath"/>

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
                <li><a href="javascript:;">我的店铺</a></li>
                <li><a href="javascript:;">店铺管理</a></li>
                <li class="active">模板内容配置</li>
            </ol>

            <div class="app-content">
                <div>
                <#--引入標籤-->
                <#import "ultag.ftl" as ultag>
                <@ultag.ultag '${map.channelStorey.tempId}'/>
                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm create-tag-btn" type="button" data-toggle="modal" data-target="#addFloor"><i class="glyphicon glyphicon-plus"></i>添加标签</button>
                        </div>
                        <form action="" method="post" id="muilt-delete-form">
                            <input name="storeyId" type="hidden" value="${(map.channelStorey.channelStoreyId)!''}"/>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="selectAll(this,'tagIds')"/></th>
                                <th>序号</th>
                                <th>标签名称</th>
                                <th>排序</th>
                                <th>启用</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if (map.pb.list?size) &gt; 0 >
                            <#list map.pb.list as storeyTag>
                            <tr>
                                <td><input type="checkbox" name="tagIds" value="${storeyTag.channelStoreyTagId}"/></td>
                                <td>${storeyTag_index+1}</td>
                                <td>${storeyTag.name}</td>
                                <td>${storeyTag.sort!''}</td>
                                <td>
                                    <#if storeyTag.userStatus=='1'>
                                        <i class="glyphicon glyphicon-ok"></i>
                                    <#else>
                                        <i class="glyphicon glyphicon-remove"></i>
                                    </#if>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" onclick="javascript:window.location.href='queryThirdTemplStoreyTagGoodsByPageBean.htm?storeyTagId=${storeyTag.channelStoreyTagId}';" class="btn btn-primary btn-sm">设置标签商品</button>
                                        <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:window.location.href='queryThirdTempStoreyTagAdverByPageBean.htm?storeyTagId=${storeyTag.channelStoreyTagId}';">设置标签广告</a></li>
                                            <li><a href="javascript:;" class="modify-tag-btn" data-key="${storeyTag.channelStoreyTagId}">编辑</a></li>
                                            <li><a href="javascript:;" class="delete-btn" data-key="${storeyTag.channelStoreyTagId}">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            </#list>
                            </#if>
                            </tbody>
                        </table>
                        </form>
                    <#--引入分页-->
                    <#include "pageable.ftl">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--分页form-->
<form id="infoForm" action="queryThirdTempStoreyTagByPageBean.htm" method="post">
    <input type="hidden" id="storeyId" name="storeyId" value="${(map.channelStorey.channelStoreyId)!''}"/>
    <input type="hidden" id="pageNo" name="pageNo" value="${(map.pb.pageNo)!''}"/>
</form>

<#--单个删除form-->
<form action="" method="POST" id="singleDeleteForm">
    <input name="storeyId" type="hidden" value="${(map.channelStorey.channelStoreyId)!''}"/>
    <input name="tagIds" value="" type="hidden" id="deleteKey"/>
</form>

<#--引入提示框-->
<#include "infotip.ftl">

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<div class="modal fade" id="addFloor" role="dialog" aria-hidden="true">
    <form id="addStoreyTagForm" action="" method="POST">
        <input name="channelStoreyTagId" id="up_barId" type="hidden" value=""/>
        <input name="storeyId" id="storeyId" type="hidden" value="${(map.channelStorey.channelStoreyId)!''}"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title tag-info-title">添加标签</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label"><b>*</b>标签名称：</label>
                    <div class="controls">
                        <input type="text" class="form-control" name="name" id="up_name"/>
                        <label class="tips error-tip"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>排序：</label>
                    <div class="controls">
                        <input type="text" class="form-control" maxlength="10"
                               onKeyUp="this.value=this.value.replace(/\D/g,'')"
                               onafterpaste="this.value=this.value.replace(/\D/g,'')"
                               name="sort" id="up_sort"/>
                        <label class="tips error-tip"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">启用：</label>
                    <div class="controls checkWp">
                        <label class="choose-label"><input name="userStatus" value="1" type="radio" checked="checked"/>是</label>
                        <label class="choose-label"><input name="userStatus" value="0" type="radio"/>否</label>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary" type="button" id="save">保存</button>
            </div>
        </div>
    </div>
    </form>
</div>

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/angular.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script type="text/javascript" src="${basePath}/js/temp/temp_storeytag_list.js"></script>
<script  type="text/javascript"src="${basePath}/js/material.min.js"></script>
<script>
    $(function(){
        $.material.init();
        $("#ultag li").removeClass("active");
        $("#ultag #li8").addClass("active");
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