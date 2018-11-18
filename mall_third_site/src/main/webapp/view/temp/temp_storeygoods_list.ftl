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
                            <button class="btn btn-primary btn-sm create-floorgoods-btn" type="button" data-toggle="modal" data-target="#addFloor"><i class="glyphicon glyphicon-plus"></i>楼层货品</button>
                        </div>
                        <form action="" id="muilt-delete-form" method="POST">
                            <input name="storeyId" type="hidden" value="${(map.channelStorey.channelStoreyId)!''}"/>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="selectAll(this,'storeyGoodsIds')"/></th>
                                    <th>序号</th>
                                    <th>货品编号</th>
                                    <th>货品图片</th>
                                    <th>货品名称</th>
                                    <th>优惠价格</th>
                                    <th>排序</th>
                                    <th style="width:110px;">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list map.pb.list as goods>
                                <tr>
                                    <td><input name="storeyGoodsIds" type="checkbox" value="${goods.channelStoreyGoodsproductId}"/></td>
                                    <td>${goods_index+1}</td>
                                    <td>${goods.goodsproductNo!''}</td>
                                    <td><img src="${goods.goodsproductImgsrc }" alt="" width="120" height="60"/></td>
                                    <td>${goods.goodsproductName}</td>
                                    <td>${goods.goodsproductPrice}</td>
                                    <td>${goods.sort}</td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary btn-sm modify-storeygoods-btn" data-key="${goods.channelStoreyGoodsproductId}" data-toggle="modal" data-target="#addFloor">编辑</button>
                                            <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="javascript:;" class="delete-btn" data-key="${goods.channelStoreyGoodsproductId}">删除</a></li>
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
<form id="infoForm" action="${basePath}/tempstoreygoodslist.html" method="post">
    <input type="hidden" id="storeyId" name="storeyId" value="${(map.channelStorey.channelStoreyId)!''}"/>
    <input type="hidden" id="pageNo" name="pageNo" value="${(map.pb.pageNo)!''}"/>
</form>

<#--单个删除form-->
<form action="" method="POST" id="singleDeleteForm">
    <input name="storeyId" type="hidden" value="${(map.channelStorey.channelStoreyId)!''}"/>
    <input name="storeyGoodsIds" value="" type="hidden" id="deleteKey"/>
</form>

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<div class="modal fade" id="addFloor" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title storey-goodsinfo-title">添加货品</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">选择货品：</label>
                    <div class="controls">
                        <button class="btn btn-primary btn-sm chooseGoods" type="button">选择货品</button>
                    </div>
                </div>
                <div class="choose-goods">
                    <div class="search-bar">
                        <div class="sch-item">
                            <label>编号：</label>
                            <input name="goodsproductNo" id="goodsInfoItemNo" type="text" class="form-control"/>
                        </div>
                        <div class="sch-item">
                            <label>品牌：</label>
                            <select class="form-control" name="brandId" id="brandId">
                                <option value="">全部</option>
                                <#list map.brandlist as brand>
                                    <option value="${brand.brandId}">${brand.brandName }</option>
                                </#list>
                            </select>
                        </div>
                        <div class="sch-item text-right">
                            <button class="btn btn-primary btn-sm" onclick="addProduct(1,5);" type="button">查询</button>
                        </div>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th></th>
                            <th>序号</th>
                            <th>货品编号</th>
                            <th>货品名称</th>
                            <th>图片</th>
                            <th>优惠价</th>
                        </tr>
                        </thead>
                        <tbody id="goods_list">
                       <#-- <tr>
                            <td><input type="checkbox"/></td>
                            <td>1</td>
                            <td>7878978974564</td>
                            <td>2015夏装韩版气质文艺短袖圆领宽松棉麻T恤女式纯色大码亚麻上衣</td>
                            <td><img alt="" src="${basePath}/images/images_03.jpg" width="120" height="60"></td>
                            <td>89</td>
                        </tr>-->
                        </tbody>
                    </table>
                    <div class="footer-operation" id="goodspages">
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
                        </div>
                    </div>
                </div>
            <form action="" method="POST" id="storyGoodsInfoForm">
                <input name="channelStoreyGoodsproductId" id="up_barId" type="hidden" value=""/>
                <input type="hidden" id="storeyId" name="storeyId" value="${(map.channelStorey.channelStoreyId)!''}"/>
                <input type="hidden" name="goodsproductId" id="up_goodsproductId" value=""/>
                <div class="form-item">
                <label class="control-label">货品编号：</label>
                <div class="controls">
                    <input type="text" name="goodsproductNo" id="up_goodsproductNo" class="form-control" readonly="readonly"/>
                    <label class="tips error-tip"></label>
                </div>
            </div>
                <div class="form-item">
                    <label class="control-label">货品名称：</label>
                    <div class="controls">
                        <input type="text" name="goodsproductName" id="up_goodsproductName" class="form-control" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">货品图片：</label>
                    <div class="controls">
                        <img id="image" alt="" src="" width="176px" height="176px"/>
                        <input type="hidden" name="goodsproductImgsrc" id="up_goodsproductImgsrc" value="" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">优惠价：</label>
                    <div class="controls">
                        <input type="text" name="goodsproductPrice" id="up_goodsproductPrice" value="" readonly="readonly" class="form-control"/>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>排序：</label>
                    <div class="controls">
                        <input type="text"
                               onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "
                        name="sort" id="up_sort" value="1" class="form-control"/>
                        <label class="tips error-tip"></label>
                    </div>
                </div>
            </form>
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
<script type="text/javascript" src="${basePath}/js/angular.min.js"></script>
<script  type="text/javascript"src="${basePath}/js/app.js"></script>
<script  type="text/javascript"src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/temp/temp_storygoods_list.js"></script>
<script  type="text/javascript"type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
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