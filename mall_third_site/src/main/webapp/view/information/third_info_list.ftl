<!doctype html>
<html lang="zh-CN">
<#assign basePath=request.contextPath>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>资讯管理</title>
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
                <li>资讯管理</li>
                <li class="active" style="color: #07d;">文章管理</li>
            </ol>

            <div class="app-content">
                <div>
                    <div class="search-block">
                        <form id="infoForm" action="queryThirdInfoVoList.htm" method="post">
                            <div class="filter-groups">
                                <div class="control-group">
                                    <label class="control-label">栏目名称：</label>
                                    <div class="controls">
                                        <select id="typeId" name="typeId" class="form-control">
                                            <option value="">全部</option>
                                            <#list infoTypes as infoType>
                                            <option value="${infoType.infoTypeId}"
                                                    <#if typeId?? && typeId==infoType.infoTypeId>selected="selected"</#if>
                                                    >${infoType.name}</option>
                                            </#list>
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">文章名称：</label>
                                    <div class="controls">
                                        <input class="form-control" id="infoName" name="infoName" type="text" value="${infoName?default('')}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="search-operation">
                                <button class="btn btn-primary btn-sm submit-search-btn" type="button">查询<i class="glyphicon glyphicon-search"></i></button>
                                <button class="btn btn-default btn-sm reset-search-btn" type="button">重置<i class="glyphicon glyphicon-refresh"></i></button>
                            </div>
                        </form>
                    </div>

                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm" type="button" onclick="createInfo()"><i class="glyphicon glyphicon-plus"></i>添加文章</button>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="selectAll(this,'infoId')" ／></th>
                                <th>序号</th>
                                <th>文章标题</th>
                                <th>文章栏目</th>
                                <th>更新时间</th>
                                <th>作者</th>
                                <th>点击数</th>
                                <th>排序</th>
                                <th>是否发布</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if (pb.list?size) &gt; 0>
                            <#list pb.list as info>
                            <tr>
                                <td><input name="infoId" type="checkbox" value="${info.infoId}"/></td>
                                <td>${info_index+1}</td>
                                <td>${(info.title)!''}</td>
                                <td>${(info.infoType.name)!''}</td>
                                <td>${(info.updateDate)?string("yyyy-MM-dd HH:mm:ss")}</td>
                                <td>${(info.author)!''}</td>
                                <td>${(info.hits)!''}</td>
                                <td>${(info.sort)!''}</td>
                                <td>
                                    <#if info.isShow=='1'>
                                        <i class="glyphicon glyphicon-ok"></i>
                                    <#else>
                                        <i class="glyphicon glyphicon-remove"></i>
                                    </#if>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" onclick="updateInfo(${info.infoId})" class="btn btn-primary btn-sm">编辑</button>
                                        <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:;" onclick="delInfo(${info.infoId})">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            </#list>
                            </#if>
                            </tbody>
                        </table>
                        <div class="footer-operation">
                        <#if (pb.list)??&&(pb.list?size gt 0)>
                            <div class="ops-left">
                                <button class="btn btn-primary btn-xs" type="button" onclick="batchDelInfo('infoId')">批量删除</button>
                            </div>
                            <div class="ops-right">
                                <nav>
                                    <ul class="pagination">
                                        <li <#if pb.pageNo<=1>class="disabled"</#if>>
                                            <a <#if pb.pageNo<=1><#else>onclick="changePageNo(this);"</#if> href="javascript:;" aria-label="Previous" data-role="${pb.prePageNo}" data-role="${pb.prePageNo}">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <#if (pb.startNo?number>1)>
                                            <li><a href="javascript:void(0);" onclick="changePageNo(this);" data-role="1">1</a></li>...
                                        </#if>
                                        <#list pb.startNo?number .. pb.endNo as item>
                                            <li <#if item == pb.pageNo>class="active"</#if>><a href="javascript:;" <#if item == pb.pageNo><#else>onclick="changePageNo(this);"</#if> data-role="${item}">${item}</a></li>
                                        </#list>
                                        <#if (pb.totalPages?number>pb.endNo)>
                                            ...<li><a href="javascript:void(0);" onclick="changePageNo(this);" data-role="${pb.totalPages}">${pb.totalPages}</a></li>
                                        </#if>
                                        <li <#if pb.pageNo gte pb.endNo> class="disabled"</#if>>
                                            <a <#if pb.pageNo gte pb.endNo><#else>onclick="changePageNo(this);"</#if> data-role="${pb.nextPageNo}" href="javascript:;" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        <#else>
                            <center>暂无数据</center>
                        </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--右部底部-->
<#include "../common/leftfooter.ftl">

<#--错误信息提示框-->
<div class="modal fade" id="error-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="error-tip-title">请至少选择一行！</label>
                </div>
            </div>
            <div class="modal-footer">
            <#--<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>-->
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<#--delete form-->
<form id="delete-from" action="" method="post"></form>

<#--确认删除提示框-->
<div class="modal fade" id="delete-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="operate-tip-title">确认删除吗？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal" id="tip-submit-btn">确定</button>
            </div>
        </div>
    </div>
</div>

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<!--模板相关END-->
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script src="${basePath}/js/information/third_info_list.js"></script>
<script>
	$(function () {
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