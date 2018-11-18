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
                <@ultag.ultag '${map.temp.tempId}'/>

                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm" type="button" id="createAdertBtn" data-toggle="modal" data-target="#addAdv"><i class="glyphicon glyphicon-plus"></i>添加广告</button>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="selectAll(this,'channelAdverId')"/></th>
                                <th>序号</th>
                                <th>广告图片</th>
                                <th>链接地址</th>
                                <th>排序</th>
                                <th>启用</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <form action="" method="POST" id="muilt-delete-form">
                                <input name="tempId" type="hidden" value="${(map.temp.tempId)!''}"/>
                                <input name="atId" type="hidden" value="${(map.atId)!''}"/>
                            <tbody>
                            <#list map.pb.list as advert>
                            <tr>
                                <td><input type="checkbox" name="channelAdverId" value="${advert.channelAdverId}"/></td>
                                <td>1</td>
                                <td><img src="<#if advert??>${advert.adverPath!''}</#if>" alt="" width="120" height="60"/></td>
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
                        <#--<div class="footer-operation">-->
                            <#--<div class="ops-left">-->
                                <#--<button class="btn btn-primary btn-xs" type="button" id="muilty-delete-btn">批量删除</button>-->
                            <#--</div>-->
                            <#--<div class="ops-right">-->
                                <#--<nav>-->
                                    <#--<ul class="pagination">-->
                                        <#--<li <#if map.pb.pageNo<=1>class="disabled"</#if>>-->
                                            <#--<a <#if map.pb.pageNo<=1><#else>onclick="changePageNo(this);"</#if> href="javascript:;" aria-label="Previous" data-role="${map.pb.prePageNo}" data-role="${map.pb.prePageNo}">-->
                                                <#--<span aria-hidden="true">&laquo;</span>-->
                                            <#--</a>-->
                                        <#--</li>-->
                                        <#--<#if (map.pb.startNo?number>1)>-->
                                            <#--<li><a href="javascript:void(0);" onclick="changePageNo(this);" data-role="1">1</a></li>...-->
                                        <#--</#if>-->
                                        <#--<#list map.pb.startNo?number .. map.pb.endNo as item>-->
                                            <#--<li <#if item == map.pb.pageNo>class="active"</#if>><a href="javascript:;" onclick="changePageNo(this);" data-role="${item}">${item}</a></li>-->
                                        <#--</#list>-->
                                        <#--<#if (map.pb.totalPages?number>map.pb.endNo)>-->
                                            <#--...<li><a href="javascript:void(0);" onclick="changePageNo(this);" data-role="${map.pb.totalPages}">${map.pb.totalPages}</a></li>-->
                                        <#--</#if>-->
                                        <#--<li <#if map.pb.pageNo gte map.pb.endNo> class="disabled"</#if>>-->
                                            <#--<a <#if map.pb.pageNo gte map.pb.endNo><#else>onclick="changePageNo(this);"</#if> data-role="${map.pb.nextPageNo}" href="javascript:;" aria-label="Next">-->
                                                <#--<span aria-hidden="true">&raquo;</span>-->
                                            <#--</a>-->
                                        <#--</li>-->
                                    <#--</ul>-->
                                <#--</nav>-->
                            <#--</div>-->
                        <#--</div>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#--单个删除form-->
<form action="" method="POST" id="singleDeleteForm">
    <input name="tempId" type="hidden" value="${(map.temp.tempId)!''}"/>
    <input name="atId" type="hidden" value="${(map.atId)!''}"/>
    <input name="channelAdverId" value="" type="hidden" id="deleteKey"/>
</form>

<#--分页form-->
<form id="infoForm" action="tempadvertlist.html" method="post">
    <input type="hidden" id="tempId" name="tempId" value="${(map.temp.tempId)!''}"/>
    <input type="hidden" id="pageNo" name="pageNo" value="${(map.pb.pageNo)!''}"/>
    <input type="hidden" id="atId" name="atId" value="${(map.atId)!''}"/>
</form>

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<div class="modal fade" id="addAdv" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
    <form action="" method="POST" enctype="multipart/form-data" id="adverInfoForm">
        <input name="channelAdverId" id="up_barId" type="hidden" value=""/>
        <input name="tempId" id="tempId" type="hidden" value="${(map.temp.tempId)!''}"/>
        <input name="temp4" id="temp4" type="hidden" value="${(map.thirdId)!''}"/>
        <input name="atId" id="atId" type="hidden" value="${(map.atId)!''}"/>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title advet-info-title">添加广告</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label"><b>*</b>广告名称：</label>
                    <div class="controls">
                        <input type="text" name="adverName" maxlength="20" id="up_adverName" class="form-control"/>
                        <label class="tips error-tip"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b></b>广告副标题：</label>
                    <div class="controls">
                        <input type="text" name="temp2" maxlength="20" id="up_temp2" class="form-control"/>
                        <label class="tips error-tip"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>广告图片：</label>
                    <div class="controls">
                        <input type="file" name="imgSrc" id="up_imgSrc" onchange="changeFile()"/>
                        <label class="tips error-tip"></label>
                        <img alt="" id="adverPath" src="" width="131px;" height="60px;"/>
                        <input type="hidden" name="adverPath" id="imageSrc" value="" class="cfg_text"/>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">广告链接地址：</label>
                    <div class="controls">
                        <input type="text" name="adverHref" id="up_adverHref" class="form-control"/>
                        <label class="tips error-tip"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>排序：</label>
                    <div class="controls">
                        <input type="text" maxlength="10" class="form-control"
                               onKeyUp="this.value=this.value.replace(/\D/g,'')"
                               onafterpaste="this.value=this.value.replace(/\D/g,'')"
                               name="adverSort" id="adverSort"/>
                        <label class="tips error-tip"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">启用：</label>
                    <div class="controls checkWp radio radio-primary">
                        <label class="choose-label"><input name="userStatus" type="radio" <#if !(map.channelAdver.userStatus)??>checked="checked"</#if>
                                                           <#if (map.channelAdver.userStatus)?? && map.channelAdver.userStatus=='1'>checked="checked"</#if> value="1"/>是</label>
                        <label class="choose-label"><input name="userStatus" type="radio"
                                                           <#if (map.channelAdver.userStatus)?? && map.channelAdver.userStatus=='0'>checked="checked"</#if> value="0"/>否</label>
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
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/temp/temp_advert_list.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script>
    $(function(){
    	$.material.init();
        $("#ultag li").removeClass("active");
        if(${(map.atId)!''}=='157'){
            $("#ultag #li3").addClass("active");
        }else if(${(map.atId)!''}=='159'){
            $("#ultag #li4").addClass("active");
        }else if(${(map.atId)!''}=='161'){
            $("#ultag #li5").addClass("active");
        }
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