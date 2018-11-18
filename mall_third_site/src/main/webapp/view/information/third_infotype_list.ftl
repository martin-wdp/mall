<!doctype html>
<html lang="zh-CN">
<#assign basePath=request.contextPath/>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>资讯管理</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/ztree/zTreeStyle.css">
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
                <li class="active" style="color: #07d;">文章栏目管理</li>
            </ol>

            <div class="app-content">
                <div>
                    <div class="search-block">
                        <form action="">
                            <div class="filter-groups">
                                <div class="control-group">
                                    <label class="control-label">栏目名称：</label>
                                    <div class="controls">
                                        <input id="searchText" class="form-control" type="text"/>
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
                            <button class="btn btn-primary btn-sm create-infotype-btn" type="button" data-toggle="modal" data-target="#addColumn"><i class="glyphicon glyphicon-plus"></i>添加栏目</button>
                        </div>
                        <table class="table treetable">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="selectAll(this,'infoTypeIds')"/></th>
                                <th width="30%">栏目名称</th>
                                <th>描述</th>
                                <th>排序</th>
                                <th>是否发布</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="treetable">
                            <#--<tr>
                                <td><input type="checkbox"/></td>
                                <td>最近商品</td>
                                <td>暂无描述</td>
                                <td>1</td>
                                <td><i class="glyphicon glyphicon-ok"></i></td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary btn-sm">修改</button>
                                        <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:;">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="checkbox"/></td>
                                <td>最近商品</td>
                                <td>暂无描述</td>
                                <td>1</td>
                                <td><i class="glyphicon glyphicon-ok"></i></td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary btn-sm">修改</button>
                                        <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:;">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="checkbox"/></td>
                                <td>最近商品</td>
                                <td>暂无描述</td>
                                <td>1</td>
                                <td><i class="glyphicon glyphicon-ok"></i></td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary btn-sm">修改</button>
                                        <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:;">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>-->

                            </tbody>
                        </table>
                        <div class="footer-operation">
                            <#--<div class="ops-left">
                                <button class="btn btn-primary btn-xs" type="button">批量删除</button>
                            </div>
                            <div class="ops-right">
                                <nav>
                                    <ul class="pagination">
                                        <li class="disabled">
                                            <a href="javascript:;" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <li class="active"><a href="javascript:;">1</a></li>
                                        <li><a href="javascript:;">2</a></li>
                                        <li><a href="javascript:;">3</a></li>
                                        <li><a href="javascript:;">4</a></li>
                                        <li><a href="javascript:;">5</a></li>
                                        <li>
                                            <a href="javascript:;" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--左部与底部-->
<#include "../common/leftfooter.ftl">

<#--提示框-->

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


<div class="modal fade" id="select-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="result-tip-title">请至少选择一行！</label>
                </div>
            </div>
            <div class="modal-footer">
            <#--<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>-->
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<#--删除-->
<form id="deleteInfoTypeForm" action="" method="post">

</form>

<div class="modal fade" id="addColumn" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title infotype-info-title">添加栏目</h4>
            </div>
            <form action="" method="post" id="infoTypeForm">
                <input type="hidden" name="infoTypeId" id="infoTypeId"  value=""/>
                <div class="modal-body">
                <div class="form-item">
                    <label class="control-label"><b>*</b>栏目名称：</label>
                    <div class="controls">
                        <input type="text" class="form-control" name="name" id="up_name"/>
                        <label></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label" >上级栏目：</label>
                    <div class="controls">
                        <input class="st_text parentName form-control input-xs" value="所有" readonly=readonly type="text"/>
                        <label></label>
                        <input type="hidden" value="0" name="parentId" id="parentId" />
                        <a class="st_choose" onclick="javascript:showMenu();" href="javascript:;">选择</a>
                        <div id="menuContent" style=" border:1px solid #ccc;width:160px;height:300px;overflow-y:auto;display:none;position: absolute; background-color:#fcfcfc; box-shadow:0 0 5px #ddd inset;z-index:99;">
                            <ul id="treeDemo" class="ztree">
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">是否显示：</label>
                    <div class="controls checkWp radio radio-primary">
                        <label class="choose-label"><input name="isShow" type="radio" value="1" checked="checked"/>是</label>
                        <label class="choose-label"><input name="isShow" type="radio" value="0"/>否</label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>排序：</label>
                    <div class="controls">
                        <input type="text" name="sort" id="up_sort" class="form-control"/>
                        <label></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">栏目描述：</label>
                    <div class="controls">
                        <textarea class="form-control" id="up_seoDesc" name="seoDesc"></textarea>
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

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/ztree/jquery.ztree.core-3.5.js"></script>
<script src="${basePath}/js/jqtreetable.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<!--模板相关END-->
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script src="${basePath}/js/information/third_infotype_list.js"></script>
<script>
	$(function () {
    	$.material.init();
    });
    /*显示树形控件*/
	function showMenu() 
	{
	    var selObj = $(".parentName");
	    var businessOffset = $(".parentName").offset();
	    $("#menuContent").css( 
	    {
	        left : "145px", top : "115px" 
	    }).slideDown("fast");
	    onBodyDownForArea();
	}
	/*隐藏树形控件*/
	function onBodyDownForArea()
	{
	    jQuery.fn.isChildAndSelfOf = function (b)
	    {
	        return (this.closest(b).length > 0);
	    };
	    $(document).click(function (event)
	    {
	        if (!($(event.target).isChildAndSelfOf("#menuContent")) && !($(event.target).hasClass("st_choose"))) {
	            $("#menuContent").fadeOut("slow");
	        };
	    });
	}
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
</body>
</html>