<!doctype html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>商品管理</title>
    <#assign basePath=request.contextPath>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>
<body>


<#include "../index/indextop.ftl">

<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
            <div class="sidebar-nav">
            <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
            </div>
        </div>
    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li><a href="goods01.html">商品管理</a></li>
                <li class="active">商家分类管理</li>
            </ol>

            <div class="app-content">
                <div>
                    <div class="cfg-content">
                        <div class="sorts-wp">
                            <div class="sorts-item">
                                <div class="cate-search">
                                	<input type="hidden" id="parentId0" value="0"/>
                                    <input type="hidden" id="parentId1"/>
                                    <input type="text" id="search_name1" placeholder="输入名称查找"/>
                                    <button type="button" onclick="findFirstGoodsCate();"><i class="glyphicon glyphicon-search"></i></button>
                                </div>
                                <div class="cate-list">
                                    <a class="add-cate" data-toggle="modal" data-target="#addCate" onclick="showAddCate();"><i class="glyphicon glyphicon-plus"></i>添加一级分类</a>
                                    <ul class="cate-items" id="third_cate_list1">
                                       
                                    </ul>
                                </div>
                            </div>

                            <div class="sorts-item">
                                <div class="cate-search">
                                	<input type="hidden" id="parentId2"/>
                                    <input type="text" id="search_name2" placeholder="输入名称查找"/>
                                    <button type="button" onclick="findSecondGoodsCate()"><i class="glyphicon glyphicon-search"></i></button>
                                </div>
                                <div class="cate-list">
                                    <a class="add-cate" data-toggle="modal" data-target="#addCate" onclick="showAddCate(0)"><i class="glyphicon glyphicon-plus"></i>添加二级分类</a>
                                    <ul class="cate-items" id="third_cate_list2">
                                    
                                    </ul>
                                </div>
                            </div>

                            <div class="sorts-item">
                                <div class="cate-search">
                                    <input type="text" id="search_name3" placeholder="输入名称查找"/>
                                    <button type="button" onclick="findThirdGoodsCate()"><i class="glyphicon glyphicon-search"></i></button>
                                </div>
                                <div class="cate-list">
                                    <a class="add-cate" data-toggle="modal" data-target="#addCate" onclick="showAddCate(1)"><i class="glyphicon glyphicon-plus"></i>添加三级分类</a>
                                    <ul class="cate-items" id="third_cate_list3">

                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../common/leftfooter.ftl">

<div class="modal fade" id="addCate" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="boxTitle">添加分类</h4>
            </div>
            <form class="addThirdCate" enctype="multipart/form-data" method="post" id="saveGoodsCateForm">
            	<input type="hidden" id="cur_level" />
	            <div class="modal-body">
	                <div class="form-item">
	                    <label class="control-label"><b>*</b>分类名称：</label>
	                    <div class="controls">
	                        <input type="text" class="form-control catName" name="catName"/>
	                        <label class="catNameTip"></label>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label"><b>*</b>上级分类：</label>
	                    <div class="controls">
	                        <select class="form-control" id="parentCate_add" name="catParentId">
	                        
	                        </select>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label">分类图片：</label>
	                    <div class="controls">
	                        <input type="file" name="upCatImg"/>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label"><b>*</b>排序：</label>
	                    <div class="controls">
	                        <input type="text" class="form-control catSort" name="catSort"/>
	                        <label class="catSortTip"></label>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label">页面标题：</label>
	                    <div class="controls">
	                        <input type="text" class="form-control catSeoTitle" name="catSeoTitle"/>
	                        <label class="catSeoTitleTip"></label>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label">SEO优化关键字：</label>
	                    <div class="controls">
	                        <input type="text" class="form-control catSeoKeyword" name="catSeoKeyword"/>
	                        <label class="catSeoKeywordTip"></label>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label">SEO详细优化：</label>
	                    <div class="controls">
	                        <textarea class="form-control catSeoDesc" name="catSeoDesc"></textarea>
	                        <label class="catSeoDescTip"></label>
	                    </div>
	                </div>
	            </div>
            </form>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary" type="button" onclick="chkThirdCateForm();">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="cateEdit" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="boxTitle">修改分类</h4>
            </div>
            <form class="editThirdCate" enctype="multipart/form-data" method="post" id="updateCateForm">
	            <div class="modal-body">
	            	<input type='hidden' name='catId' class='upCatId' />
	            	<input type='hidden' class='oldCatName'/>
	                <div class="form-item">
	                    <label class="control-label"><b>*</b>分类名称：</label>
	                    <div class="controls">
	                        <input type="text" class="form-control catName" name="catName" id="update_cate_name"/>
	                        <label class="catNameTipEdit"></label>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label"><b>*</b>上级分类：</label>
	                    <div class="controls">
	                        <select class="form-control" id="update_parent_cate" name="catParentId" id="update_parent_cate">
	                        
	                        </select>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label">分类图片：</label>
	                    <div class="controls">
	                        <input type="file" name="upCatImg"/>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label"><b>*</b>排序：</label>
	                    <div class="controls">
	                        <input type="text" class="form-control catSort" name= "catSort" id="update_catSort"/>
	                        <label class="catSortTipEdit"></label>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label">页面标题：</label>
	                    <div class="controls">
	                        <input type="text" class="form-control catSeoTitle" name="catSeoTitle" id="update_catSeoTitle"/>
	                        <label class="catSeoTitleTipEdit"></label>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label">SEO优化关键字：</label>
	                    <div class="controls">
	                        <input type="text" class="form-control catSeoKeyword" name="catSeoKeyword" id="update_cateSeoKeyword"/>
	                        <label class="catSeoKeywordTipEdit"></label>
	                    </div>
	                </div>
	                <div class="form-item">
	                    <label class="control-label">SEO详细优化：</label>
	                    <div class="controls">
	                        <textarea class="form-control catSeoDesc" name="catSeoDesc" id="update_catSeoDesc"></textarea>
	                        <label class="catSeoDescTipEdit"></label>
	                    </div>
	                </div>
	            </div>
            </form>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary" type="button" onclick="chkEditThirdCateForm();">保存</button>
            </div>
        </div>
    </div>
</div>

<#--没有选中行提示框-->
<div class="modal fade" id="operate-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="control-label">分类下存在子分类 ,不可删除！</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<#--确认删除提示框-->
<div class="modal fade" id="delete-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
    <form type="get" id="delCate">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
                <input type="hidden" id="thirdCateId" name="thirdCateId"/>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="control-label">确认删除吗？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal" onclick="cls()">取消</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal" id="tip-submit-btn" onclick="delThirdGIds();">确定</button>
            </div>
        </div>
    </form>
    </div>
</div>

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/goods/third_goods_cate.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
<script>
    $(function(){
    	$.material.init();
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        })
    });
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
	<#import "../common/selectindex.ftl" as selectindex>
	<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
</html>