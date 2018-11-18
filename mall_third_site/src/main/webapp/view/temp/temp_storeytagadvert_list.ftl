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
                            <button class="btn btn-primary btn-sm" type="button" id="createAdertBtn" data-toggle="modal" data-target="#addAdv"><i class="glyphicon glyphicon-plus"></i>添加广告</button>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="selectAll(this,'adverIds')"/></th>
                                <th>序号</th>
                                <th>广告图片</th>
                                <th>广告名称</th>
                                <th>链接地址</th>
                                <th>排序</th>
                                <th>启用</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <form action="" method="POST" id="muilt-delete-form">
                                <input type="hidden" id="storeyTagId" name="storeyTagId" value="${(map.storeyTag.channelStoreyTagId)!''}"/>
                                <tbody>
                                <#list map.pb.list as advert>
                                <tr>
                                    <td><input type="checkbox" name="adverIds" value="${advert.channelAdverId}"/></td>
                                    <td>${advert_index+1}</td>
                                    <td><img src="${advert.adverPath}" alt="" width="120" height="60"/></td>
                                    <td>${advert.adverName}</td>
                                    <td>${advert.adverHref!''}</td>
                                    <td>${advert.adverSort}</td>
                                    <td>
                                        <#if advert.userStatus=='1'>
                                            <i class="glyphicon glyphicon-ok"></i>
                                        <#else>
                                            <i class="glyphicon glyphicon-remove"></i>
                                        </#if>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn modify-btn btn-primary btn-sm" data-key="${advert.channelAdverId}" data-toggle="modal" data-target="#addAdv" <#--onclick="updateInfo(${advert.channelAdverId})"-->>编辑</button>
                                            <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="javascript:;" <#--onclick="delInfo(${advert.channelAdverId})"-->data-toggle="modal" data-target="#delete-tip" class="delete-btn" data-key="${advert.channelAdverId}">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </form>
                        </table>
                    <#--引入分页-->
                    <#include "pageable.ftl">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#--单个删除form-->
<form action="" method="POST" id="singleDeleteForm">
    <input type="hidden" id="storeyTagId" name="storeyTagId" value="${(map.storeyTag.channelStoreyTagId)!''}"/>
    <input name="adverIds" value="" type="hidden" id="deleteKey"/>
</form>

<#--分页form-->
<form id="infoForm" action="tempstoreyadvertlist.html" method="post">
    <input type="hidden" id="storeyTagId" name="storeyTagId" value="${(map.storeyTag.channelStoreyTagId)!''}"/>
    <input type="hidden" id="pageNo" name="pageNo" value="${(map.pb.pageNo)!''}"/>
</form>

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<div class="modal fade" id="addAdv" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <form action="" method="POST" enctype="multipart/form-data" id="adverInfoForm">
            <input name="channelAdverId" id="up_barId" type="hidden" value=""/>
            <input name="floorTagId" id="storeyTagId" type="hidden" value="${(map.storeyTag.channelStoreyTagId)!''}"/>
            <input name="atId" id="" type="hidden" value="161"/>
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title advet-info-title">添加广告</h4>
                </div>
                <div class="modal-body">
                    <div class="form-item error">
                        <label class="control-label"><b>*</b>广告名称：</label>
                        <div class="controls">
                            <input type="text" name="adverName" id="up_adverName" class="form-control"/>
                            <label class="tips error-tip"></label>
                        </div>
                    </div>
                    <div class="form-item error">
                        <label class="control-label"><b>*</b>广告副标题：</label>
                        <div class="controls">
                            <input type="text" name="temp2" id="up_temp2" class="form-control"/>
                            <label class="tips error-tip"></label>
                        </div>
                    </div>
                    <div class="form-item error">
                        <label class="control-label"><b>*</b>广告图片：</label>
                        <div class="controls">
                            <input type="file" name="imgSrc" id="up_imgSrc" onchange="changeFile()"/>
                            <label class="tips error-tip"></label>
                            <img alt="" id="adverPath" src="" width="131px;" height="60px;"/>
                            <input type="hidden" name="adverPath" id="imageSrc" value="" class="cfg_text"/>
                        </div>
                    </div>
                    <div class="form-item error">
                        <label class="control-label">广告链接地址：</label>
                        <div class="controls">
                            <input type="text" name="adverHref" id="up_adverHref" class="form-control"/>
                            <label class="tips error-tip"></label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label"><b>*</b>排序：</label>
                        <div class="controls">
                            <input type="text" class="form-control" name="adverSort" id="adverSort"/>
                            <label class="tips error-tip"></label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label">启用：</label>
                        <div class="controls checkWp">
                            <label class="choose-label"><input name="userStatus" type="radio" checked="checked" value="1"/>是</label>
                            <label class="choose-label"><input name="userStatus" type="radio" value="0"/>否</label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label">广告描述：</label>
                        <div class="controls">
                            <textarea class="form-control" name="des" id="up_des"></textarea>
                        </div>
                    </div>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
            <button class="btn btn-primary" type="button" id="save">保存</button>
        </div>
</div>
</form>
</div>
</div>

<#--引入提示框-->
<#include "infotip.ftl">

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/angular.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/temp/temp_storeytagadvert_list.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script>
    $(function(){
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