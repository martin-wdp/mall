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
                        <form id="tempInfoTypeForm" action="updatetempnews.html" class="form-horizontal" method="post">
                            <input type="hidden" name="tempId" value="${temp.tempId }"/>
                            <input type="hidden" name="tempType" value="${temp.tempType }"/>
                            <div class="form-wrap">
                                <div class="form-item">
                                    <label class="control-label">文章栏目：</label>
                                    <div class="controls">
                                        <select id="infoTypeId" name="expFleid1" onchange="changeSelect();" class="form-control">
                                        <#list infoTypes as infotype>
                                            <#if infotype?? && (infotype.infoTypeId)??>
                                            <option value="${infotype.infoTypeId!''}"
                                                    <#if (temp.expFleid1)?? && temp.expFleid1!='' && infotype.infoTypeId==(temp.expFleid1)?number>selected="selected"</#if>
                                                    >${infotype.name }</option>
                                            </#if>
                                        </#list>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-item">
                                    <label class="control-label">模块名称：</label>
                                    <div class="controls">
                                        <input type="text" class="form-control" id="infoTypeName" name="expFleid2" value="${temp.expFleid2}"/>
                                    </div>
                                </div>
                                <div class="form-item">
                                    <div class="controls">
                                        <button class="btn btn-primary" id="save" type="button">保存</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script>
    $(function(){
    	$.material.init();
        $("#ultag li").removeClass("active");
        $("#ultag #li6").addClass("active");
    });
    //修改模板的新闻公告
    $('#save').click(function() {
        if($("#infoTypeName").val()==""){
            $("#infoTypeName").val($("#infoTypeId").find("option:selected").text());
        }
        $('#tempInfoTypeForm').submit();
    });

    function changeSelect(){
        $("#infoTypeName").val($("#infoTypeId").find("option:selected").text());
    }
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