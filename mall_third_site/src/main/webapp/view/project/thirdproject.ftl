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
                <li>资讯管理</li>
                <li class="active" style="color: #07d;">专题管理</li>
            </ol>

            <div class="app-content">
                <div>
                    <div class="search-block">
                        <form action="queryProjectList.htm" method="post" id="selectfrom">
                            <input type="hidden" id="pageNo" name="pageNo" value="${pageBean.pageNo}"/>
                            <div class="filter-groups">
                                <div class="control-group">
                                    <label class="control-label">专题名称：</label>
                                    <div class="controls">
                                        <input name="thirdProjectName" id="thirdProjectName" class="form-control" type="text" value="<#if thirdProject?? && thirdProject.thirdProjectName??>${thirdProject.thirdProjectName}</#if>"/>
                                    </div>
                                </div>
                            </div>
                            <div class="search-operation">
                                <button class="btn btn-primary btn-sm" id="searchBtn" type="button">查询<i class="glyphicon glyphicon-search"></i></button>
                                <button class="btn btn-default btn-sm" id="reset-search-btn" type="button">重置<i class="glyphicon glyphicon-refresh"></i></button>
                            </div>
                        </form>
                    </div>

                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm create-project-btn" type="button" ><i class="glyphicon glyphicon-plus"></i>添加专题</button>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="selectAll(this,'thirdProjectId')"/></th>
                                <th>序号</th>
                                <th>专题名称</th>
                                <th>URL</th>
                                <th>修改时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                        <#if pageBean.list??>
                            <#list pageBean.list as project>
                            <tr>
                                <td><input type="checkbox" name="thirdProjectId"  value="${project.thirdProjectId }"></td>
                                <td>${project_index+1}</td>
                                <td><a href="javascript:;" style="color:#2177d4;">${(project.thirdProjectName)!'' }</a></td>
                                <td>third_project/${project.thirdProjectId }</td>
                                <td>${project.thirdProjectModifyTime?string("yyyy-MM-dd HH:mm:ss") }</td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary btn-sm modify-project-btn" data-key="${project.thirdProjectId}">编辑</button>
                                        <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:;" class="delete-btn" data-key="${project.thirdProjectId }">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </#list>
                        </#if>
                        </tbody>
                        </table>
                        <div class="footer-operation">
                        <#if (pageBean.list)??&&(pageBean.list?size gt 0)>
                            <div class="ops-left">
                                <#--<button class="btn btn-primary btn-xs" type="button" onclick="batchDelInfo('infoId')">批量删除</button>-->
                            </div>
                            <div class="ops-right">
                                <nav>
                                    <ul class="pagination">
                                        <li <#if pageBean.pageNo<=1>class="disabled"</#if>>
                                            <a <#if pageBean.pageNo<=1><#else>onclick="changePageNo(this);"</#if> href="javascript:;" aria-label="Previous" data-role="${pageBean.prePageNo}" data-role="${pageBean.prePageNo}">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <#if (pageBean.startNo?number>1)>
                                            <li><a href="javascript:void(0);" onclick="changePageNo(this);" data-role="1">1</a></li>...
                                        </#if>
                                        <#list pageBean.startNo?number .. pageBean.endNo as item>
                                            <li <#if item == pageBean.pageNo>class="active"</#if>><a href="javascript:;" <#if item == pageBean.pageNo><#else>onclick="changePageNo(this);"</#if> data-role="${item}">${item}</a></li>
                                        </#list>
                                        <#if (pageBean.totalPages?number>pageBean.endNo)>
                                            ...<li><a href="javascript:void(0);" onclick="changePageNo(this);" data-role="${pageBean.totalPages}">${pageBean.totalPages}</a></li>
                                        </#if>
                                        <li <#if pageBean.pageNo gte pageBean.endNo> class="disabled"</#if>>
                                            <a <#if pageBean.pageNo gte pageBean.endNo><#else>onclick="changePageNo(this);"</#if> data-role="${pageBean.nextPageNo}" href="javascript:;" aria-label="Next">
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

<div class="modal fade" id="addTopic" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="project_sub">添加专题</h4>
            </div>
            <form action="" method="post" id="thirdprojectinfoForm">
                <input name="thirdProjectId" id="up_infoId" class="pg_text" type="hidden" value=""/>
                <div class="modal-body">
                <div class="form-item">
                    <label class="control-label"><b>*</b>专题名称：</label>
                    <div class="controls">
                        <input type="text" class="form-control" name="thirdProjectName" id="up_name"/>
                        <label style="color: red"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">专题内容：</label>
                    <div class="controls">
                        <div id="topicCont"></div>
                        <textarea id="thirdProjectContext" name="thirdProjectContext" style="display: none"></textarea>
                    </div>
                </div>
            </div>
            </form>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary" type="button" id="save">保存</button>
            </div>
        </div>
    </div>
</div>

<form action="" id="single-delete-form" method="post">
    <input type="hidden" name="thirdProjectId" id="deleteKey"/>
</form>
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
    
      /*重置表单*/
    $("#reset-search-btn").click(function(){
        $("#thirdProjectName").val("");
    });
    	$.material.init();
        $("#topicCont").summernote({
            lang: 'zh-CN',
            height: 200
        });
    });

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
<script src="${basePath}/js/project/thirdproject.js"></script>
</body>
</html>