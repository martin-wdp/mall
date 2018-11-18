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
                            <button class="btn btn-primary btn-sm create-floor-btn" type="button" data-toggle="modal" data-target="#addFloor"><i class="glyphicon glyphicon-plus"></i>添加楼层</button>
                        </div>
                        <form action="" id="muilt-delete-form" method="POST">
                            <input type="hidden" id="tempId" name="tempId" value="${(map.temp.tempId)!''}"/>
                            <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="selectAll(this,'channelStoreyId')"/></th>
                                <th>序号</th>
                                <th>楼层名称</th>
                                <th>楼层图片</th>
                                <th>SEO优化</th>
                                <th>楼层数</th>
                                <th>启用</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list map.pb.list as storey>
                            <tr>
                                <td><input name="channelStoreyId" type="checkbox" value="${storey.channelStoreyId}"/></td>
                                <td>${storey_index+1}</td>
                                <td>${storey.storeyName}</td>
                                <td><img src="${storey.storeyImg!''}" alt="" width="120" height="60"/></td>
                                <td>${storey.storeySeo}</td>
                                <td>${storey.floorId!''}F</td>
                                <td>
                                    <#if storey.userStatus=='1'>
                                        <i class="glyphicon glyphicon-ok"></i>
                                    <#else>
                                        <i class="glyphicon glyphicon-remove"></i>
                                    </#if>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" onclick="javascript:window.location.href='tempstoreygoodslist.html?storeyId=${storey.channelStoreyId}';" class="btn btn-primary btn-sm">设置楼层货品</button>
                                        <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:javascript:window.location.href='tempstoreyadvertlist.html?storeyId=${storey.channelStoreyId}';">设置楼层广告</a></li>
                                            <li><a href="javascript:window.location.href='queryThirdTempStoreyTagByPageBean.htm?storeyId=${storey.channelStoreyId}';;">设置楼层标签</a></li>
                                            <li><a href="javascript:;" class="modify-story-btn" data-toggle="modal" data-target="#addFloor" data-key="${storey.channelStoreyId}">编辑</a></li>
                                            <li><a href="javascript:;" class="delete-btn" data-toggle="modal" data-target="#delete-tip" data-key="${storey.channelStoreyId}">删除</a></li>

                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            </#list>
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

<#--分頁form-->
<form id="infoForm" action="tempstoreylist.html" method="post">
    <input type="hidden" id="tempId" name="tempId" value="${(map.temp.tempId)!''}"/>
    <input type="hidden" id="pageNo" name="pageNo" value="${(map.pb.pageNo)!''}"/>
</form>

<#--单个删除form-->
<form action="" method="POST" id="singleDeleteForm">
    <input name="tempId" type="hidden" value="${(map.temp.tempId)!''}"/>
    <input name="channelStoreyId" value="" type="hidden" id="deleteKey"/>
</form>

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<div class="modal fade" id="addFloor" role="dialog" aria-hidden="true">
    <form id="storyInfoForm" action="" method="post" enctype="multipart/form-data">
        <input name="channelStoreyId" id="up_barId" type="hidden" value=""/>
        <input name="tempId" id="tempId" type="hidden" value="${(map.temp.tempId)!''}"/>
        <input name="temp1" id="thirdId" type="hidden" value="${(map.thirdId)!''}"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title story-info-title">添加广告</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">楼层分类：</label>
                    <div class="controls">
                        <select name="goodsCatId" id="catId" class="form-control">
                            <#if map.goodsCates??>
                            <#list map.goodsCates as cate>
                                <option value="${cate.catId }">${cate.catName }</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>楼层名称：</label>
                    <div class="controls">
                        <input name="storeyName" id="up_storeyName" type="text" class="form-control"/>
                        <label class="tips error-tip"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">楼层图片：</label>
                    <div class="controls">
                        <input type="file" name="netLogo" id="up_imgSrc" onchange="changeFile()"/>
                        <label class="tips error-tip"></label>
                        <input type="hidden" name="storeyImg" id="imageSrc" value="">
                        <img alt="" id="stotyPath" src="" width="131px;" height="60px;" style="display:none ">
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">图片链接：</label>
                    <div class="controls">
                        <input type="text" class="form-control" name="storeyImgHref" id="up_storeyImgHref"/>
                        <label class="tips error-tip"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">选择楼层：</label>
                    <div class="controls">
                        <select name="floorId" id="floorId" class="form-control">
                            <#if map.goodsCates??>
                            <#list map.goodsCates as cate>
                                <option value="${cate_index+1}">${cate_index+1}F</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">SEO优化：</label>
                    <div class="controls">
                        <input name="storeySeo" id="up_storeySeo" type="text" class="form-control"/>
                        <label class="tips error-tip"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">启用：</label>
                    <div class="controls checkWp radio radio-primary">
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

<#--引入提示框-->
<#include "infotip.ftl">

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/temp/temp_story_list.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
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