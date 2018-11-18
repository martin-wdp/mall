<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>审核商品列表</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <link rel="stylesheet" href="${basePath}/css/ztree/zTreeStyle.css">
    <script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/ztree/jquery.ztree.core-3.5.js"></script>
    <script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
    <script src="${basePath}/js/ripples.min.js"></script>
	<script src="${basePath}/js/material.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/third.js"></script>
    <script type="text/javascript" src="${basePath}/js/common.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/thirdGoodsList.js"></script>
    <script src="${basePath}/js/app.js"></script>
    <style>
        .st_choose {
            color: #1a81c1;
            white-space:nowrap;
        }
    </style>
</head>

<body>
<#--引入头部-->
<#include "../index/indextop.ftl">

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
                <li>商品管理</li>
            <#if map.goodsAddedSta == 0>
                <li class="active" style="color: #07d;">待售商品管理</li>
            <#elseif map.goodsAddedSta == 1>
                <li class="active" style="color: #07d;">在售商品管理</li>
            <#elseif map.goodsAddedSta == 2>
                <li class="active" style="color: #07d;">审核商品列表</li>
            </#if>
            </ol>
            <form class="simple_search" action="<#if map.pb??>${map.pb.url}</#if>" method="post">
            </form>
            <div class="app-content">
                <div>
                    <form class="high_search" action="<#if map.pb??>${map.pb.url}</#if>" method="post">
                        <input type="hidden" name="showFlag" value="2"/>

                        <div class="search-block">
                            <div class="filter-groups">
                                <div class="control-group">
                                    <label class="control-label">商品名称：</label>

                                    <div class="controls">
                                        <input class="form-control sm-input" type="text"
                                               value="<#if map.searchBean?? && map.searchBean.goodsName??>${map.searchBean.goodsName}</#if>"
                                               name="goodsName"/>
                                    </div>
                                </div>
                                <div class="control-group lg-group">
                                    <label class="control-label">平台价格：</label>

                                    <div class="controls">
                                        <input type="text" class="form-control sm-input iqy_text"
                                               value="<#if map.searchBean?? && map.searchBean.lowPrice??>${map.searchBean.lowPrice}</#if>"
                                               type="text" name="lowPrice"/>
                                        ~
                                        <input type="text" class="form-control sm-input iqy_text"
                                               value="<#if map.searchBean?? && map.searchBean.highPrice??>${map.searchBean.highPrice}</#if>"
                                               type="text" name="highPrice"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">商品编码：</label>

                                    <div class="controls">
                                        <input class="form-control sm-input" type="text"
                                               value="<#if map.searchBean?? && map.searchBean.goodsNo??>${map.searchBean.goodsNo}</#if>"
                                               name="goodsNo"/>
                                    </div>
                                </div>
                                <div class="control-group lg-group">
                                    <label class="control-label">上架时间：</label>

                                    <div class="controls">
                                        <input type="text" class="form-control sm-input datepicker"
                                               data-provide="datepicker"
                                               value="<#if map.searchBean?? && map.searchBean.lowCreateTime??>${map.searchBean.lowCreateTime}</#if>"
                                               name="lowCreateTime" id="sTime"/>
                                        ~
                                        <input type="text" class="form-control sm-input datepicker"
                                               data-provide="datepicker"
                                               value="<#if map.searchBean?? && map.searchBean.highCreateTime??>${map.searchBean.highCreateTime}</#if>"
                                               name="highCreateTime" id="sTime"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">平台分类：</label>

                                    <div class="controls">
                                        <input class="form-control np_cate_name" type="text" readonly="readonly"/>
                                        <input type="hidden" name="npCateId" class="np_cate_id"
                                               value="<#if map.searchBean?? && map.searchBean.npCateId??>${map.searchBean.npCateId}</#if>"/>
                                        <a class="st_choose" onclick="javascript:showChNpCate(this);"
                                           href="javascript:;">选择</a>

                                        <div id="menuContent" class="npmenuContent"
                                             style=" border:1px solid #ccc;width:160px;height:300px;overflow-y:auto;display:none;position: absolute; background-color:#fcfcfc; box-shadow:0 0 5px #ddd inset;">
                                            <ul id="nptreeDemo" class="ztree">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">店内分类：</label>

                                    <div class="controls">
                                        <input class="form-control third_cate_name" readonly=readonly type="text"/>
                                        <input type="hidden" name="thirdCateId" class="third_cate_id"
                                               value="<#if map.searchBean?? && map.searchBean.thirdCateId??>${map.searchBean.thirdCateId}</#if>"/>
                                        <a class="st_choose" onclick="javascript:showChThirdCate(this);"
                                           href="javascript:;">选择</a>

                                        <div id="thirdmenuContent" class="thirdmenuContent"
                                             style=" border:1px solid #ccc;width:160px;height:300px;overflow-y:auto;display:none;position: absolute; background-color:#fcfcfc; box-shadow:0 0 5px #ddd inset;">
                                            <ul id="thirdtreeDemo" class="ztree">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="search-operation">
                                <button class="btn btn-primary btn-sm query_btn" type="submit">查询<i
                                        class="glyphicon glyphicon-search"></i></button>
                                <button class="btn btn-default btn-sm rst_btn" type="button">重置<i
                                        class="glyphicon glyphicon-refresh"></i></button>
                            </div>
                        </div>
                    </form>

                    <div class="cfg-content">
                        <div class="ops-bar"></div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="selectAll(this,'thirdGoodsId')"/></th>
                                <th>序号</th>
                                <th width="300">商品</th>
                                <th>商品编码</th>
                                <th>品牌</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                        <#if map.pb??>
                            <#if (map.pb.list?size) &gt; 0 >
                                <form class="list_form" method="post">
                                    <tbody>
                                        <#if map.pb??>
                                            <#list map.pb.list as goods>
                                                <#if goods??>
                                                <tr>
                                                    <td><input type="checkbox" value="${goods.goodsId}"
                                                               name="thirdGoodsId" class="ch_goods"/></td>
                                                    <td>${goods_index+1}</td>
                                                    <td>
                                                        <div class="goods-intro">
                                                            <#if map.goodsAddedSta==1>
                                                                <a target="_blank"
                                                                   href="${map.bset}/item/${goods.goodsInfoId!''}.html">
                                                                    <#if goods.goodsImg??>
                                                                        <#list goods.goodsImg?split(",") as img>
                                                                            <img class="gds-img" alt=""
                                                                                 src="<#if img??>${img}</#if>"/>
                                                                            <#break>
                                                                        </#list>
                                                                    </#if>
                                                                </a>
                                                            <#elseif map.goodsAddedSta==0 || map.goodsAddedSta ==2>
                                                                <#if goods.goodsImg??>
                                                                    <#list goods.goodsImg?split(",") as img>
                                                                        <img class="gds-img" alt=""
                                                                             src="<#if img??>${img}</#if>"/>
                                                                        <#break>
                                                                    </#list>
                                                                </#if>
                                                            </#if>

                                                            <div class="gds-info">
                                                                <#if map.goodsAddedSta==1>
                                                                    <p class="gds-name"><a target="_blank"
                                                                                           href="${map.bset}<#if goods.goodsInfoId??>/item/${goods.goodsInfoId!''}.html</#if>">${goods.goodsName}</a>
                                                                    </p>

                                                                    <p class="gds-price">&yen;<#if goods.goodsPrice??>#{goods.goodsPrice;m2M2}</#if></p>
                                                                <#elseif map.goodsAddedSta==0 || map.goodsAddedSta ==2 >
                                                                    <p class="gds-name">${goods.goodsName}</p>

                                                                    <p class="gds-price">&yen;<#if goods.goodsPrice??>#{goods.goodsPrice;m2M2}</#if></p>
                                                                </#if>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td title="${goods.goodsNo}">
                                                        <#if goods.goodsNo?length gt 18>
                                                            ${goods.goodsNo?substring(0,17)}....
                                                        <#else>
                                                            ${goods.goodsNo}
                                                        </#if>
                                                    </td>
                                                    <td><#if goods.goodsBrand??>${goods.goodsBrand.brandName!''}</#if></td>
                                                    <td>
                                                        <div class="btn-group">
                                                            <#if map.isThirdAuditUsed == '1'>
                                                                <#if goods.auditStatus == '1'>
                                                                    <a type="button"
                                                                       class="btn btn-primary btn-sm">审核中</a>
                                                                    <a type="button"
                                                                       class="btn btn-primary btn-sm dropdown-toggle"
                                                                       data-toggle="dropdown"
                                                                       aria-expanded="false"
                                                                       onclick="menu_btn(this)">
                                                                        <span class="caret"></span>
                                                                    </a>
                                                                    <ul class="dropdown-menu" role="menu">
                                                                        <li>
                                                                            <a href="thirdProductManager.html?goodsId=${goods.goodsId}&goodsAddedSta=${map.goodsAddedSta}&flag=0">查看货品</a>
                                                                        </li>
                                                                    </ul>
                                                                <#elseif goods.auditStatus == '2'>
                                                                    <a type="button"
                                                                       class="btn btn-primary btn-sm"
                                                                       onclick="refuseReason('${goods.refuseReason!''}');">
                                                                        拒绝原因
                                                                    </a>
                                                                    <a type="button"
                                                                       class="btn btn-primary btn-sm dropdown-toggle"
                                                                       data-toggle="dropdown"
                                                                       aria-expanded="false"
                                                                       onclick="menu_btn(this)">
                                                                        <span class="caret"></span>
                                                                    </a>
                                                                    <ul class="dropdown-menu" role="menu">
                                                                        <li>
                                                                            <a href="thirdProductManager.html?goodsId=${goods.goodsId}&goodsAddedSta=${map.goodsAddedSta}&flag=1">编辑货品</a>
                                                                        </li>
                                                                        <li>
                                                                            <a href="toModifiedThirdGoods.html?goodsId=${goods.goodsId}&goodsAddedSta=${map.goodsAddedSta}">编辑商品</a>
                                                                        </li>
                                                                        <li><a href="javascript:;"
                                                                               onclick="del(${goods.goodsId});">删除</a>
                                                                        </li>
                                                                    </ul>
                                                                <#elseif goods.auditStatus == '0'>
                                                                    <a type="button" class="btn btn-primary btn-sm"
                                                                       href="toModifiedThirdGoods.html?goodsId=${goods.goodsId}&goodsAddedSta=${map.goodsAddedSta}">编辑商品</a>
                                                                    <a type="button"
                                                                       class="btn btn-primary btn-sm dropdown-toggle"
                                                                       data-toggle="dropdown"
                                                                       aria-expanded="false"
                                                                       onclick="menu_btn(this)">
                                                                        <span class="caret"></span>
                                                                    </a>
                                                                    <ul class="dropdown-menu" role="menu">
                                                                        <li>
                                                                            <a href="thirdProductManager.html?goodsId=${goods.goodsId}&goodsAddedSta=${map.goodsAddedSta}&flag=1">编辑货品</a>
                                                                        </li>
                                                                        <li><a href="javascript:;"
                                                                               onclick="del(${goods.goodsId});">删除</a>
                                                                        </li>
                                                                    </ul>
                                                                <#else>
                                                                    <a type="button" class="btn btn-primary btn-sm"
                                                                       href="toModifiedThirdGoods.html?goodsId=${goods.goodsId}&goodsAddedSta=${map.goodsAddedSta}">编辑商品</a>
                                                                    <a type="button"
                                                                       class="btn btn-primary btn-sm dropdown-toggle"
                                                                       data-toggle="dropdown"
                                                                       aria-expanded="false"
                                                                       onclick="menu_btn(this)">
                                                                        <span class="caret"></span>
                                                                    </a>
                                                                    <ul class="dropdown-menu" role="menu">
                                                                        <li>
                                                                            <a href="thirdProductManager.html?goodsId=${goods.goodsId}&goodsAddedSta=${map.goodsAddedSta}&flag=1">编辑货品</a>
                                                                        </li>
                                                                        <li><a href="javascript:;"
                                                                               onclick="del(${goods.goodsId});">删除</a>
                                                                        </li>
                                                                    </ul>
                                                                </#if>
                                                            <#else>
                                                                <a type="button" class="btn btn-primary btn-sm"
                                                                   href="toModifiedThirdGoods.html?goodsId=${goods.goodsId}&goodsAddedSta=${map.goodsAddedSta}">编辑商品</a>
                                                                <a type="button"
                                                                   class="btn btn-primary btn-sm dropdown-toggle"
                                                                   data-toggle="dropdown" aria-expanded="false"
                                                                   onclick="menu_btn(this)">
                                                                    <span class="caret"></span>
                                                                </a>
                                                                <ul class="dropdown-menu" role="menu">
                                                                    <li>
                                                                        <a href="thirdProductManager.html?goodsId=${goods.goodsId}&goodsAddedSta=${map.goodsAddedSta}&flag=1">编辑货品</a>
                                                                    </li>
                                                                    <li><a href="javascript:;"
                                                                           onclick="del(${goods.goodsId});">删除</a>
                                                                    </li>
                                                                </ul>
                                                            </#if>
                                                        </div>
                                                    </td>
                                                </tr>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </tbody>
                                </form>
                            <#else>
                                <td colspan="8">
                                    <center>暂无相关商品信息</center>
                                </td>
                            </#if>
                        </#if>
                        </table>
                        <div class="footer-operation">
                        <#if map.goodsAddedSta == 1>
                            <div class="ops-left">
                                <a class="btn btn-primary btn-xs batch_off" href="javascript:;">批量下架</a>
                            </div>
                        <#else>
                            <#if map.flag == 1><a class="btn btn-primary btn-xs batch_upload"
                                                  href="javascript:;">批量<#if map.isThirdAuditUsed == '1'>审核</#if>
                                上架</a></#if>
                        </#if>
                        <#if map.pb??>
                            <#if (map.pb.list?size) &gt; 0 >
                                <div class="ops-right">
                                    <nav>
                                        <ul class="pagination">
                                            <li>
                                                <a href="javascript:;" aria-label="Previous"
                                                   onclick="changePageNo(this);" data-role="${map.pb.prePageNo}">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                            <#if (map.pb.startNo?number>1)>
                                                <li><a href="javascript:;">1</a></li>
                                            </#if>
                                            <#list map.pb.startNo?number .. map.pb.endNo as item>
                                                <li <#if item == map.pb.pageNo>class="active"</#if>><a
                                                        href="javascript:;" onclick="changePageNo(this);"
                                                        data-role="${item}">${item}</a></li>
                                            </#list>
                                            <#if (map.pb.totalPages?number>map.pb.endNo)>
                                                <li><a href="javascript:;" onclick="changePageNo(this);"
                                                       data-role="${map.pb.totalPages}">${pageBean.totalPages}</a>
                                                </li>
                                            </#if>
                                            <li>
                                                <a href="javascript:;" aria-label="Next"
                                                   onclick="changePageNo(this);" data-role="${map.pb.nextPageNo}">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </#if>
                        </#if>

                        </div>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>

<#include "../common/leftfooter.ftl">
<#--确认删除提示框-->
<div class="modal fade" id="delete-tip" role="dialog" aria-hidden="true">
    <form action="delThirdGoods.html" method="post" id="mform" name="mform">
        <input type="hidden" id="thirdGoodsId" name="thirdGoodsId" value=""/>
        <input type="hidden" name="goodsAddedSta" value="${map.goodsAddedSta}"/>

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div class="form-item error">
                        <label class="control-label">确认删除吗？</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button" data-dismiss="modal" id="tip-submit-btn"
                            onclick="delmar();">确定
                    </button>
                    <button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </form>
</div>
<#--没有选中行提示框-->
<div class="modal fade" id="select-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="show_title">请至少选择一行！</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
<!-- 保存查询方式  -->
<#if map.searchBean?? && map.searchBean.showFlag??>
<input type="hidden" class="show_flag" value="${map.searchBean.showFlag}"/>
</#if>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script>
    $(function () {
    	$.material.init();
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        })
    });

    //商品删除提示
    function del(id) {
        $("#thirdGoodsId").val(id);
        $(".modal-title").html("操作提示");
        $("#delete-tip").modal('show');
    }

    //商品删除
    function delmar() {
        $("#mform").submit();
    }

    function refuseReason(refuseRen) {
        $(".modal-title").html("拒绝原因");
        $(".show_title").html(refuseRen);
        $("#select-tip").modal('show');
    }

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
</html>