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
                <@ultag.ultag '${temp.tempId}'/>

                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn add-classify-btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#sortNav" pdata-key=""><i class="glyphicon glyphicon-plus"></i>分类导航</button>
                        </div>
                        <form action="" method="POST" id="muilt-delete-form">
                        <input name="tempId" type="hidden" value="${(temp.tempId)!''}"/>
                        <table class="table treetable">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="selectAll(this,'classifyBarId')"/></th>
                                <th width="30%">分类名称</th>
                                <th>分类URL</th>
                                <th>启用</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="treetable">
                            </tbody>
                        </table>
                        </form>
                        <div class="footer-operation">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="tempId" name="tempId" value="${(temp.tempId)!''}"/>
<#--分页form-->
<#--<form id="infoForm" action="${basePath}/tempclassifybarlist.html" method="post">
    <input type="hidden" id="pageNo" name="pageNo" value="${(pb.pageNo)!''}"/>
</form>-->

<#--单个删除form-->
<form action="" method="POST" id="singleDeleteForm">
    <input name="tempId" type="hidden" value="${(temp.tempId)!''}"/>
    <input name="classifyBarId" value="" type="hidden" id="deleteKey"/>
</form>

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<div class="modal fade" id="sortNav" role="dialog" aria-hidden="true">
 <form action="" method="POST" id="classyInfoForm">
     <input type="hidden" name="classifyBarId" id="up_classifyBarId" value=""/>
     <input type="hidden" name="tempId" id="up_tempId" value="${temp.tempId!'' }"/>
     <input type="hidden" name="parentId" id="up_parentId" value=""/>
     <input type="hidden" name="temp1" id="temp1" value="${thirdId!'' }"/>

    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加分类导航</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label"><b>*</b>导航名称：</label>
                    <div class="controls">
                        <input type="text" class="form-control" name="name" id="up_name"/>
                        <label>要添加多条链接请用“、”分隔</label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>URL路径：</label>
                    <div class="controls">
                        <input type="text" class="form-control" name="url" id="up_url"/>
                        <label>要添加多条链接请用“、”分隔，请和分类导航名称数量相同</label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>排序：</label>
                    <div class="controls">
                        <input type="text"
                               onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "
                        class="form-control" maxlength="3" name="sort" id="up_sort"/>
                        <label>请填写数字</label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">是否显示：</label>
                    <div class="controls checkWp radio radio-primary">
                        <label class="choose-label"><input name="isUsing" type="radio" checked="checked" value="1"/>是</label>
                        <label class="choose-label"><input name="isUsing" type="radio" value="0"/>否</label>
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
<script src="${basePath}/js/jqtreetable.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/temp/temp_classifybar_list.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<#--<script>
    $(function(){
        var map = [0,1,0,3,3];

    });
</script>-->
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