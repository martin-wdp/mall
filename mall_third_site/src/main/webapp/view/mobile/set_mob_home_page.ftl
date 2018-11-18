<!doctype html>
<html lang="zh-CN" ng-app>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>商品管理</title>
<#assign basePath=request.contextPath>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/iconfont/iconfont.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <link href="${basePath}/mobile_home_page/css/newstyle.css" rel="stylesheet">
    <!-- toastr通知插件 -->
    <link rel="stylesheet" href="${basePath}/css/toastr.css">

    <link rel="stylesheet" href="${basePath}/js/jquery-ui-css/jquery-ui-1.10.3.custom.css">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="${basePath}/css/idangerous.swiper.css">
    <style>
        .disp_none{display:none;}
        .ke-container{width: 357px!important;}
        .aui_state_lock{top:20%!important;position: fixed!important;}
        .choose-goods {display:block!important;}
        .edit_box {margin-bottom:80px;}
        .bottom_bar{width:350px;position:fixed;left:470;bottom:10px;padding:5px 20px;text-align:left;background:rgba(0,0,0,0.4);}
    </style>
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
                <li>移动版</li>
                <li class="active" style="color: #07d;">首页模板</li>
            </ol>

            <div class="app-content">
                <div>
                    <div class="cfg-content">
                        <div class="sorts-wp main_cont">

                            <div class="common_data" style="padding-left:10px;">
                                <input type="hidden" id="merchantId" name="merchantId" value="${merchantId}">
                                <input type="hidden" id="homePageId" name="homePageId" value="${homePage.homepageId}">
                                <input type="hidden" id="rsv" value="${rsv}">
                                <!-- 首页内容 -->
                                <div class="col-sm-10 col-md-11 main" style="float:none;">
                                    <div class="edit_box">
                                        <div class="ip_pre">
                                            <div class="ip_head"></div>
                                            <div class="ip_body">
                                                <div class="wx_head">
                                                    <h1>
                                                        <span onclick="showBasicEdit()">${mobSiteBasic.name}</span>
                                                    </h1>
                                                </div>
                                                <div id="ip_cont" class="ip_cont">
                                                    <div class="app_item app_cube ui-state-default">
                                                        <div class="app_cont"></div>
                                                        <div class="app_edit">
                                                            <div class="app_btns">
                                                                <a href="javascript:;" class="edit">编辑</a> <a
                                                                    href="javascript:;" class="add">添加内容</a> <a
                                                                    href="javascript:;" class="delete">删除</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="plugin_add">
                                                <h4>添加模块</h4>
                                                <div class="plugin_list container-fluid">
                                                    <div class="row">
                                                        <div class="col-xs-3">
                                                            <a href="javascript:;" class="plugin_cube" onclick="showMF()">魔方</a>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <a href="javascript:;" onclick="showTextEdit()">文字</a>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <a href="javascript:;" onclick="showGoods()">商品</a>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <a href="javascript:;" onclick="showAdv()">图片广告</a>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <a href="javascript:;" onclick="showRollAdv()">轮播广告</a>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <a href="javascript:;" onclick="showFullRoll()">全屏画报</a>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <a href="javascript:;" onclick="showBlankbox()">空白占位</a>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <a href="javascript:;" onclick="addLine()">分隔线</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="bottom_bar">
                                            <button type="button" class="btn btn-primary btn-sm"  onclick="saveAllMod();">保存模板</button>
                                        </div>



                                        <!-- 魔方 -->
                                        <div id="cubeEdit" class="edit_area cube_edit"
                                             style="display: none;">
                                            <div class="arrow"></div>
                                            <div class="edit_cont">
                                                <form id="mfForm" role="form" class="form_cube">

                                                    <input type="hidden" name="rsv" value="${rsv}">
                                                    <input type="hidden" id="appCountId" name="appCountId">
                                                    <input type="hidden" name="merchantId" value="${merchantId}">
                                                    <div class="form_group cube_box">
                                                        <div class="form_cont">
                                                            <div class="cube container-fluid">
                                                                <div class="row">
                                                                    <div class="col-xs-3 1-1">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 1-2">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 1-3">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 1-4">
                                                                        <span></span>
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="col-xs-3 2-1">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 2-2">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 2-3">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 2-4">
                                                                        <span></span>
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="col-xs-3 3-1">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 3-2">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 3-3">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 3-4">
                                                                        <span></span>
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="col-xs-3 4-1">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 4-2">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 4-3">
                                                                        <span></span>
                                                                    </div>
                                                                    <div class="col-xs-3 4-4">
                                                                        <span></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <p class="text-muted">请点击选择起点，滑动光标选择区域，再次点击确定所选区域，选取过程中可右击取消</p>
                                                        </div>
                                                    </div>
                                                    <div class="app-submit" align="center" style="margin-top: 15px;">
                                                        <input id="submitBtn" type="button" onclick="addMF()" value=" 确 定 ">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <!-- 广告 -->
                                        <div id="advEdit" class="edit_area cube_edit" style="display: none;">
                                            <div class="arrow"></div>
                                            <div class="edit_cont">
                                                <form id="advForm" role="form" class="form_cube">

                                                    <input type="hidden" name="rsv" value="${rsv}">
                                                    <input type="hidden" id="imgAdvId" name="imgAdvId">
                                                    <input type="hidden" name="merchantId" value="${merchantId}">
                                                    <img id="advImg" alt="" src="" width="100%">
                                                    <div class="imgEdit imgAdvEdit" style="display: block;">
                                                        <a href="javascript:;" class="img_close" onclick="closeAdvEdit()">
                                                            <span class="glyphicon glyphicon-remove"></span>
                                                        </a>
                                                        <div class="form_group">
                                                            <div class="fh">选择图片：</div>
                                                            <div class="form_cont fg_box">
                                                                <input class="form_cont advChoose" type="button" value="选择图片"
                                                                       style="margin-bottom: 10px;">
                                                                <input class="form-control" type="hidden" id="imgAdvSrc"
                                                                       name="imgAdvSrc" >
                                                                <span id="imgAdvSrcTip"></span>
                                                            </div>
                                                        </div>
                                                        <div class="form_group fg_box nlink-wp clearfix">
                                                            <div class="fh">链接地址：</div>
                                                            <div class="form_cont dd-box">
                                                                <!-- <a href="#ctModal" role="button" onclick="md('imgAdvHref')">链接地址</a> -->
                                                                <div class="lk-sel lk-chs"><span class="sel-word"></span><ul><li class="s-selected"><a data-href="lk-01">功能链接</a></li><li><a data-href="lk-02">自定义链接</a></li></ul></div>
                                                                <div class="lk-sel gn-chs"><span class="sel-word"></span><ul>
                                                                    <li class="s-selected"><a>--请选择--</a></li>
                                                                    <li><a data-href="choose">商品</a></li>
                                                                    <li><a data-href="thirdStoreIndex.htm?storeId=${thirdId}">微店首页</a></li>
                                                                    <li><a data-href="allGoodsListByStoreId.htm?storeId=${thirdId}">所有商品</a></li>
                                                                </ul></div>

                                                                <input class="form-control custom-input" type="text" id="imgAdvHref"
                                                                       name="imgAdvHref" style="margin-top: 10px;">
                                                                <span id="imgAdvHrefTip"></span>
                                                            </div>
                                                        </div>
                                                        <div class="form_group fg_box nlink-wp clearfix none" style="display: none">
                                                            <div class="fh">已选择：</div>
                                                            <div class="form_cont dd-box"><span class="sel-tags"></span><a class="del-tags" href="javascript:;">删除</a></div>
                                                        </div>
                                                    </div>
                                                    <div class="app-submit" align="center" style="margin-top: 15px;">
                                                        <input type="button" onclick="saveAdv()" value=" 确 定 ">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                        <!-- 轮播广告 -->
                                        <div id="rollAdvEdit" class="edit_area cube_edit" style="display: none;">
                                            <div class="arrow"></div>
                                            <div class="edit_cont">
                                                <form id="rollAdvForm" action="saveRollAdv.html" role="form" class="form_cube">
                                                    <input type="hidden" name="rsv" value="${rsv}">
                                                    <input type="hidden" id="rollImgAdvId" name="rollImgAdvId">
                                                    <input type="hidden" name="merchantId" value="${merchantId}">

                                                    <div id="slides" style="margin-bottom:10px;">
                                                        <div class="slides_wp clearfix">
                                                        </div><!--/slides_wp-->
                                                        <a id="sd_prev" href="javascript:;"></a>
                                                        <a id="sd_next" href="javascript:;"></a>
                                                    </div><!--/slides-->

                                                    <input class="form_cont" id="rollAdvChoose" type="button" value="添加图片">
                                                    <label class="lb-word">点击图片可编辑</label>

                                                    <div class="app-submit" align="center" style="margin-top: 15px;">
                                                        <input type="button" onclick="saveRollAdv()" value=" 确 定 ">
                                                    </div>
                                                </form>

                                            </div>
                                        </div>
                                        <!-- 全屏轮播 -->
                                        <div id="fullRollEdit" class="edit_area cube_edit" style="display:none;width:480px;">
                                            <div class="arrow"></div>
                                            <div class="edit_cont">
                                                <form id="fullRollForm" action="saveFullRoll.html" method="post" role="form" class="form_cube">
                                                    <input type="hidden" name="rsv" value="${rsv}">
                                                    <input type="hidden" id="fullRollId" name="fullRollId" value="">
                                                    <input type="hidden" name="merchantId" value="${merchantId}">

                                                    <p class="notice">注意：添加全屏幻灯将清空已添加的全部模块</p>
                                                    <dl class="sd_infos clearfix">
                                                        <dt>轮播方式：</dt>
                                                        <dd>
                                                            <label class="mr10"><input class="s-mode vm mr5" type="radio" name="sd" value="sh" checked="checked">水平</label>
                                                            <!--   <label class="mr10"><input class="s-mode vm mr5" type="radio" name="sd" value="sv">垂直</label> -->
                                                        </dd>
                                                        <dt style="display:none;">背景音乐：</dt>
                                                        <dd style="display:none;">
                                                            <label><input id="enable_music" class="vm mr5" type="checkbox">启用背景音乐</label>
                                                            <span class="music-name ml20"></span>
                                                            <input type="hidden" id="music" name="music">
                                                            <input type="hidden" id="musicname" name="musicname">
                                                            <a class="choose_music ml20 none" href="#music-modal" data-toggle="modal">选择音乐</a>
                                                        </dd>
                                                    </dl><!--/sd_infos-->

                                                    <div class="imgAdd">
                                                        <input id="fullRollChoose" class="form_cont advSlide" type="button" value="添加图片" >
                                                        <span class="sd_intro">您还可以添加<em class="sd_num">5</em>张图片，图片建议大小640*1010像素</span>
                                                    </div>

                                                    <div class="sd_ok app-submit" align="center" style="margin-top: 15px;">
                                                        <input type="button" onclick="saveFullRoll()" value=" 确 定 ">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <!-- 文字 -->
                                        <div id="textEdit" class="edit_area cube_edit" style="display: none;">
                                            <div class="arrow"></div>
                                            <div class="edit_cont">
                                                <form id="textForm" action="saveText.html" method="post" role="form" class="form_cube">
                                                    <input type="hidden" name="rsv" value="${rsv}">
                                                    <input type="hidden" id="textId" name="textId" value="">
                                                    <input type="hidden" name="merchantId" value="${merchantId}">

                                                    <textarea id="content" name="content" rows="" cols="" ></textarea>

                                                    <div class="app-submit" align="center" style="margin-top: 15px;">
                                                        <!--
                                                        <input type="submit" value=" 确 定 ">
                                                         -->
                                                        <input type="button" onclick="saveText()" value=" 确 定 ">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <!-- 微信站点设置 -->
                                        <div id="basicEdit" class="edit_area cube_edit basicEdit" style="display:none;width:480px;">
                                            <div class="arrow"></div>
                                            <div class="edit_cont">
                                                <form id="basicForm" action="updateThirdMobHomePageSet.html" role="form" class="form_cube">
                                                    <input type="hidden" name="rsv" value="${rsv}">
                                                    <input type="hidden" name="homepageId" value="${homePage.homepageId}">
                                                    <dl class="cp_infos clearfix">
                                                        <dt>店铺标题：</dt>
                                                        <dd><input name="title" class="form-control caption-input"
                                                                   type="text" value="<#if homePage.title??>${homePage.title}<#else></#if>" placeholder="××的小店-店铺主页"></dd>

                                                        <dt>作者：</dt>
                                                        <dd><input name="author" class="form-control caption-input"
                                                                   type="text" value="<#if homePage.author ??>${homePage.author}<#else></#if>" placeholder="××的小店-店铺主页"></dd>
                                                        <dt>页面简介：</dt>
                                                        <dd><input name="homeDesc" class="form-control caption-input"
                                                                   type="text" value="<#if homePage.homeDesc ??>${homePage.homeDesc }<#else></#if>" placeholder="××的小店-店铺主页"></dd>

                                                        <dt>页面预览图：</dt>
                                                        <dd>
                                                            <input class="homeImg" type="button" value="选择图片">
                                                            <img id="homeImg" alt="" src=" <#if homePage.homeImg ??>${homePage.homeImg }<#else></#if>" width="180px" height="240px"
                                                                 style="background-color: gray;margin-left: 10px;">
                                                            <input id="homeImgSrc" name="homeImg" type="hidden" value="<#if homePage.homeImg ??>${homePage.homeImg }<#else></#if>">
                                                        </dd>

                                                        <dt>店铺LOGO：</dt>
                                                        <dd>
                                                            <input class="shareLogo" type="button" value="选择图片">
                                                            <img id="basicTemp1Img" alt="" src="<#if homePage.logo ??>${homePage.logo }<#else></#if>" width="65px" height="65px"
                                                                 style="background-color: gray;margin-left: 10px;">
                                                            <input id="basicTemp1" name="logo" type="hidden" value="<#if homePage.logo ??>${homePage.logo }<#else></#if>">
                                                        </dd>

                                                        <dt style="text-align:left; width:150px;">转发到朋友圈的内容：</dt>
                                                        <dd class="report-dd">
                                                            <div class="report-box clearfix">
                                                                <textarea name="friendsDesc" class="form-txa fl" maxlength="100" placeholder="这是转发到朋友圈的信息"><#if homePage.friendsDesc ??>${homePage.friendsDesc }<#else></#if></textarea>
                                                                <div class="sl_box fr">
                                                                    <p>示例：</p>
                                                                    <img alt="" src="${basePath}/mobile_home_page/images/wx_01.jpg">
                                                                </div>
                                                            </div>
                                                        </dd>
                                                        <dt style="text-align:left; width:150px;">转发给朋友的内容：</dt>
                                                        <dd class="report-dd">
                                                            <div class="report-box clearfix">
                                                                <textarea name="friendDesc" class="form-txa fl" maxlength="100" placeholder="这是转发给朋友时的信息"><#if homePage.friendDesc ??>${homePage.friendDesc }<#else></#if></textarea>
                                                                <div class="sl_box fr">
                                                                    <p>示例：</p>
                                                                    <img class="fr" alt="" src="${basePath}/mobile_home_page/images/wx_02.jpg">
                                                                </div>
                                                            </div>
                                                        </dd>
                                                    </dl>

                                                    <div class="app-submit" align="center" style="margin-top: 15px;">
                                                        <input type="submit" value=" 确 定 ">  <span style="color:red;">(注意：请确定模板已经保存，以防模板数据丢失。)</span>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                        <!-- 商品 -->
                                        <div id="goodsEdit" class="edit_area cube_edit" style="width: 480px; display:none;">
                                            <div class="arrow"></div>
                                            <div class="edit_cont">
                                                <div class="choose-style">
                                                    <form id="goodsForm" action="saveGoodsMob.html" method="post">
                                                        <input type="hidden" name="rsv" value="${rsv}">
                                                        <input type="hidden" name="merchantId" value="${merchantId}">
                                                        <input type="hidden" id="goodsmodId" name="goodsmodId" value="">
                                                        <div class="style-wp">
                                                            <label class="mr20"><input type="radio" name="style" value="gd-01" checked="checked">样式一</label>
                                                            <img alt="" src="${basePath}/mobile_home_page/images/style_01.jpg" style="width: 50%;">
                                                        </div>
                                                        <div class="style-wp">
                                                            <label class="mr20"><input type="radio" name="style" value="gd-02">样式二</label>
                                                            <img alt="" src="${basePath}/mobile_home_page/images/style_02.jpg" style="width: 50%;">
                                                        </div>
                                                        <div class="style-wp">
                                                            <label class="mr20"><input type="radio" name="style" value="gd-03">样式三</label>
                                                            <img alt="" src="${basePath}/mobile_home_page/images/style_03.jpg" style="width: 50%;">
                                                        </div>

                                                        <dl class="choose-goods clearfix">
                                                            <dt>选择商品：</dt>
                                                            <dd>
                                                                <div class="gds-show"></div>
                                                                <a id="choooseProduct" class="ch-gd" href="javascript:;"><i></i></a>
                                                            </dd>
                                                        </dl>

                                                        <div class="sd_ok app-submit" align="center" style="margin-top: 15px;">
                                                            <input type="button" onclick="saveGoods()" value=" 确 定 ">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div><!--/edit_cont-->
                                        </div><!--/advEdit-->

                                        <!-- 空白占位 -->
                                        <div id="blankBoxEdit" class="edit_area cube_edit" style="display: none;">
                                            <div class="arrow"></div>
                                            <div class="edit_cont">
                                                <form id="blankBoxForm" action="saveBlankbox.html" method="post">
                                                    <input type="hidden" name="rsv" value="${rsv}">
                                                    <input type="hidden" name="merchantId" value="${merchantId}">
                                                    <input type="hidden" id="blankBoxId" name="blankBoxId" value="">
                                                    <label for="amount">空白高度：</label>
                                                    <div id="slider-range-min"></div>
                                                    <span class="hg-val"></span>px
                                                    <input type="hidden" id="blankBoxHeight" name="height">
                                                    <div class="app-submit" align="center" style="margin-top: 15px;">
                                                        <input type="button" onclick="saveBlankBox()" value=" 确 定 ">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>


                                    </div>
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







<link rel="stylesheet" href="${basePath}/js/artDialog4.1.7/skins/default.css?4.1.7">
<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/angular.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/mobile_home_page/js/jsUnitCore.js"></script>
<script type="text/javascript" src="${basePath}/mobile_home_page/js/xslt.js"></script>

<script src="${basePath}/mobile_home_page/js/docs.min.js"></script>
<script type="text/javascript" src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
<!-- 全屏轮播 -->
<script src="${basePath}/mobile_home_page/js/slide-full.js"></script>
<script type="text/javascript" src="${basePath}/js/toastr.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-ui/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/plugins/iframeTools.js"></script>
<script src="${basePath}/mobile_home_page/js/ie10-viewport-bug-workaround.js"></script>
<!-- 轮播 -->
<script src="${basePath}/mobile_home_page/js/slide.js"></script>
<!-- 富文本 -->
<script src="${basePath}/js/kindeditor/kindeditor.js"></script>
<!-- 内连接 -->
<script src="${basePath}/mobile_home_page/js/inner_join.js"></script>

<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<#include "../mobile_html/inner_join.html">
<#include "../mobile_html/music_join.html">
<script type="text/javascript">
    $(function(){
        //初始化xml
        initXml();
    });

</script>
<!-- 加载xml和xsl结束 -->
<script>
    //更新错误提示框的状态
    function updateTips( t, tip){
        tip .text( t ) .addClass( "ui-state-highlight" );
    }
    //验证特殊字符，将调试显示到页面中
    function checkSpecSymb(inputobj,Tipobj){
        var regexp=new RegExp("[''\\[\\]<>\\\\!]");
        if (regexp.test( $("#"+inputobj).val() ) ) {
            $("#"+inputobj).addClass( "ui-state-error" );
            updateTips( "输入的内容包含特殊字符!", $("#"+Tipobj));
            $("#"+inputobj).focus();
            return false;
        }
        else {
            $("#"+Tipobj).text("").removeClass( "ui-state-highlight");
            $("#"+inputobj).removeClass( "ui-state-error" );
            return true;
        }
    }
    //验证特殊字符
    function checkSpecSymb(inputobj){
        var regexp=new RegExp("[''\\[\\]<>\\\\!]");
        if (regexp.test( $("#"+inputobj).val() ) ) {
            return false;
        }else {
            return true;
        }
    }

    //重新绑定内连接
    function rbundLink(id){
        $("#"+id).parents(".dd-box").find(".lk-chs a").click(function(){
            var _sel = $(this).parents(".lk-chs");
            $(this).parent("li").siblings(".s-selected").removeClass("s-selected");
            $(this).parent("li").addClass("s-selected");
            if($(this).attr("data-href") == "lk-02") {
                _sel.next().hide();
                //_sel.next().next(".custom-input").show();
                $("#"+id).show().val("");
                $("#"+id).parents(".nlink-wp").next(".nlink-wp").find(".sel-tags").html("");
                $("#"+id).parents(".nlink-wp").next(".nlink-wp").hide();
            } else {
                _sel.next().show().find("li:first a").click();
                //_sel.next().next(".custom-input").hide();
                $("#"+id).hide();
            };
        });

        $("#"+id).parents(".dd-box").find(".gn-chs a").click(function(){
            var _sel = $(this).parents(".gn-chs"),
                    _txt = $(this).text();
            $(this).parent("li").siblings(".s-selected").removeClass("s-selected");
            $(this).parent("li").addClass("s-selected");
            if($(this).attr("data-href") == "choose") {
                md(id);
            } else if(!$(this).attr("data-href")) {
                $("#"+id).parents(".nlink-wp").next(".nlink-wp").find(".sel-tags").html("");
                $("#"+id).parents(".nlink-wp").next(".nlink-wp").hide();
            } else {

                //_sel.next(".custom-input").val($(this).attr("data-href"));
                $("#"+id).val($(this).attr("data-href"));
                $("#"+id).parents(".nlink-wp").next(".nlink-wp").show().find(".sel-tags").html("<em>"+_txt+"</em>");
            };

        });

       sel(id);

    }

    $(function(){
        //设置通知插件
        toastr.options = {"positionClass": "toast-top-full-width"};

        var choice_flag = 0;
        showEdit('.app_cube');
        $('.cube .row div').click(function(){

            if((choice_flag == 0)&&($(this).attr('class').indexOf('selected') < 0)){
                $(this).find('span').css('backgroundColor','#C3D9FF');
                choice_flag = 1;
                $(this).addClass('start');
                var s_row = $(this).attr('class').substr(9,1);
                var s_col = $(this).attr('class').substr(11,1);
                var dis_row = 5;
                var dis_col = 5;
                $('.cube .row div').addClass('free');
                $('div.disabled').removeClass('disabled');
                for(var i = s_row;i <= 4;i++){
                    for(var j = s_col;j <= 4;j++){
                        if($('.'+i+'-'+j).attr('class').indexOf('selected') > 0){
                            dis_row = i;
                            dis_col = j;
                            break;
                        }
                    }
                    if(dis_row != 5){
                        break;
                    }
                }
                if(dis_row <= 4 && dis_col <= 4){
                    for(var i = dis_row;i <= 4;i++){
                        for(var j = dis_col;j <= 4;j++){
                            $('.'+i+'-'+j).removeClass('free').addClass('disabled');
                        }
                    }
                    $('.cube .row .disabled').unbind('mouseenter');
                }

                $('.cube .row .free').bind('mouseenter',function(){
                    if(choice_flag == 1){
                        var s_row = $('div.start').attr('class').substr(9,1);
                        var s_col = $('div.start').attr('class').substr(11,1);
                        //alert(n_s_row+','+n_s_col);
                        var row = $(this).attr('class').substr(9,1);
                        var col = $(this).attr('class').substr(11,1);
                        for(var i = 1;i <= 4;i++){
                            for(var j = 1;j <= 4;j++){
                                if($('.'+i+'-'+j).attr('class').indexOf('selected') < 0){
                                    $('.'+i+'-'+j).find('span').css('backgroundColor','#CCCCCC');
                                }
                            }
                        }

                        for(var i = s_row;i <= row;i++){
                            for(var j = s_col;j <= col;j++){
                                if($('.'+i+'-'+j).attr('class').indexOf('selected') < 0){
                                    $('.'+i+'-'+j).find('span').css('backgroundColor','#C3D9FF');
                                }
                            }
                        }
                    }
                });
            }
            else if((choice_flag == 1)&&($(this).attr('class').indexOf('disabled') < 0)){
                var s_row = $('.start').attr('class').substr(9,1);
                var s_col = $('.start').attr('class').substr(11,1);
                var e_row = $(this).attr('class').substr(9,1);
                var e_col = $(this).attr('class').substr(11,1);
                if((parseInt(e_row)>=parseInt(s_row))&&(parseInt(e_col)>=parseInt(s_col))){
                    var rows = e_row - s_row + 1;
                    var cols = e_col - s_col + 1;
                    var colorValue = getRandomColor();
                    for(var i = s_row;i <= e_row;i++){
                        for(var j = s_col;j <= e_col;j++){
                            $('.'+i+'-'+j).addClass('selected');
                            $('.'+i+'-'+j).css('backgroundColor',colorValue);
                            $('.'+i+'-'+j+' span').css('backgroundColor','transparent');
                        }
                    }
                    createImg(s_row,s_col,rows,cols);
                    $('.cube .row .free').removeClass('free');
                    $('.cube .row .start').removeClass('start');
                    $('.cube .row .disabled').removeClass('disabled');
                    choice_flag = 0;
                }
            }

        });

        //设置图片选择框
        $("#fullRollChoose").click(function(){
            var num =  5 - $(".add_slides").length;
            if(num>0){
                art.dialog.open('${basePath}/queryImageManageByChoose.htm?size='+num, {
                    id: 'fullRoll',
                    lock: true,
                    width: '800px',
                    height: '400px',
                    title: '选择图片'
                });
            }
        });
        $('.cube').rightclick(function(){
            /*这也是修改的地方，防止右键后选取也还原本色*/
            $('.cube .row .free').each(function() {
                if($(this).attr('class').indexOf('selected') < 0){
                    $(this).find('span').css('backgroundColor','#CCCCCC');
                }
            });
            /*这也是修改的地方，防止右键后选取也还原本色*/
            $('div.start').removeClass('start');
            $('div.disabled').removeClass('disabled');
            choice_flag = 0;
        });

        //设置广告图片选择框
        $('.advChoose').button().click(function (){
            art.dialog.open('${basePath}/queryImageManageByChoose.htm', {
                id: 'adv',
                lock: true,
                width: '800px',
                height: '400px',
                title: '选择图片'
            });
        });
        //设置轮播广告图片选择框
        $("#rollAdvChoose").click(function(){
            var num =  5 - $(".slide").length;
            if(num>0){
                art.dialog.open('${basePath}/queryImageManageByChoose.htm?size='+num, {
                    id: 'rollAdv',
                    lock: true,
                    width: '800px',
                    height: '400px',
                    title: '选择图片'
                });
            }else{
                toastr.warning('轮播广告已满5张!');
            }
        });
        //设置转发logo图片选择框
        $(".shareLogo").click(function(){
            art.dialog.open('${basePath}/queryImageManageByChoose.htm', {
                id: 'shareLogo',
                lock: true,
                width: '800px',
                height: '400px',
                title: '选择图片'
            });
        });
        //设置页面预览图片选择框
        $(".homeImg").click(function(){
            art.dialog.open('${basePath}/queryImageManageByChoose.htm', {
                id: 'homeImg',
                lock: true,
                width: '800px',
                height: '400px',
                title: '选择图片'
            });
        });
        //绑定空白占位搞定控制条
        $( "#slider-range-min" ).slider({
            range: "min",
            value: 5,
            min: 30,
            max: 50,
            slide: function( event, ui ) {
                $(".hg-val").text(ui.value);
                $("#blankBoxHeight").val(ui.value);
            }
        });
        $(".hg-val").text($("#slider-range-min").slider("value"));
        $("#blankBoxHeight").val($("#slider-range-min").slider("value"));
        //绑定空白占位搞定控制条结束

    });
    function showEdit(pid){
        var edit_box = $(pid).find('.app_edit');
        $(pid).show();
        edit_box.css({
            width: $(pid).width() + 'px',
            height: $(pid).height() + 'px'
        });
    }
    function createImg(s_row,s_col,rows,cols){
        var imgWidth = cols * 160;
        var imgHeight = rows * 160;
        var imgWidth_s = cols * 90;
        var imgHeight_s = rows * 90;

        var imgEditId = 'img'+s_row+'_'+s_col+'_'+rows+'_'+cols;
        $('.cube_box').after('<div class=\"imgEdit\" id=\"imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+'\">'
        +'<a href=\"javascript:;\" class=\"img_close\" id=\"imgClose'+s_row+'_'+s_col+'_'+rows+'_'+cols+'\">'
        +'<span class=\"glyphicon glyphicon-remove\"></span>'
        +'</a>'
        +'<div class=\"form_group fg_box\"><div class=\"fh\">选择图片：</div><div class=\"form_cont\">'
        +'<input class=\"form_cont choose\" type=\"button\" value=\"选择图片\">'
        +'<input id=\"'+imgEditId+'_src\" name=\"'+imgEditId+'_src\" class=\"form-control imgsrc\" type=\"hidden\">'
        +'<span id=\"'+imgEditId+'_srcTip\"></span>'
            //+'<input class="form-control imgsrctip" type="text" readonly="readonly"/>'
        +'<p class=\"text-muted\">建议尺寸：'
        +'<span class=\"img_size\">'+imgWidth + '×' + imgHeight+'</span>像素</p>'
        +'</div></div>'
        +'<div class=\"form_group fg_box clearfix\"><div class=\"fh\">链接地址：</div><div class=\"form_cont dd-box\">'
        +'<div class="lk-sel lk-chs"><span class="sel-word"></span><ul><li class="s-selected"><a data-href="lk-01">功能链接</a></li><li><a data-href="lk-02">自定义链接</a></li></ul></div>'
        +'<div class="lk-sel gn-chs"><span class="sel-word"></span><ul>'
        +'<li class="s-selected"><a>--请选择--</a></li>'
        +'<li><a data-href="choose">商品</a></li>'
        +'<li><a data-href="thirdStoreIndex.htm?storeId=${thirdId}">微店首页</a></li>'
        +'<li><a data-href="allGoodsListByStoreId.htm?storeId=${thirdId}">所有商品</a></li>'
        +'</ul></div>'
            //+'<a href="#ctModal" role="button" onclick="md(\''+imgEditId+'_href\')">链接地址</a>'
        +'<input id=\"'+imgEditId+'_href\" name=\"'+imgEditId+'_href\" class=\"form-control custom-input\" type=\"text\">'
        +'<span id=\"'+imgEditId+'_hrefTip\"></span>'
        +'</div></div><div class="form_group fg_box nlink-wp clearfix none" style="display:none;"><div class="fh">已选择：</div><div class="form_cont dd-box"><span class="sel-tags"></span><a class="del-tags" href="javascript:;">删除</a></div></div>'
        +'</div>');
        rbundLink(imgEditId+'_href');

        //设置图片选择框
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+' .choose').button().click(function (){
            art.dialog.open('${basePath}/queryImageManageByChoose.htm', {
                id: '#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols,
                lock: true,
                width: '800px',
                height: '400px',
                title: '选择图片'
            });

        });

        //创建占位图片
        var img = new Image();
        img.src = "${basePath}/images/blank.gif";
        img.width = imgWidth_s;
        img.height = imgHeight_s;
        img.id = 'img' + s_row + '_' +s_col+ '_' +rows+ '_' +cols;
        $('.cube').append(img);
        $('#img' + s_row + '_' +s_col + '_' + rows + '_' + cols).css({
            position : 'absolute',
            left : (s_col - 1) * 90 - 1 + 'px',
            top : (s_row - 1) * 90 + 'px'
        }).attr('class','img_s');
        $('.img_s').click(function(){

            $('.imgEdit').hide();
            $('#imgEdit'+$(this).attr('id').substr(3,7)).slideDown('fast');
        });


        $('.imgEdit').hide();
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).slideDown('fast');
        //alert($('#imgEdit'+s_row+'_'+s_col).html());
        //选择图片
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+' .imgsrc').change(function(){
            var objUrl = getObjectURL($(this).val()) ;
            $('#img' + s_row + '_' +s_col + '_' + rows + '_' + cols).attr('src',objUrl);
            //设置图片的隐藏参数
            //设置imgEdit的Id
            $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).append(
                    '<input class="disp_none" name="imgEdit_id" type="text" value="'+img.id+'">');
            $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).append(
                    '<input class="disp_none" name="'+img.id+'_width" id="'+img.id+'_width" type="text" value="'+imgWidth+'">');
            $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).append(
                    '<input class="disp_none" name="'+img.id+'_height" id="'+img.id+'_height" type="text" value="'+imgHeight+'">');
            $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).append(
                    '<input class="disp_none" name="'+img.id+'_style" id="'+img.id+'_style" type="text" value="'+
                    $('#' + img.id ).attr("style")+'">');

        });
        $('#imgClose' + s_row + '_' +s_col + '_' + rows + '_' + cols).click(function(){
            /* var s_row = $(this).attr('id').substr(8,1);
            var s_col = $(this).attr('id').substr(10,1);
            var rows = $(this).attr('id').substr(12,1);
            var cols = $(this).attr('id').substr(14,1); */
            for(var i = s_row;i <= parseInt(s_row)+parseInt(rows)-1;i++){
                for(var j = s_col;j <= parseInt(s_col)+parseInt(cols)-1;j++){
                    $('.'+i+'-'+j).removeClass('selected');
                    $('.'+i+'-'+j).css('backgroundColor','transparent');
                    $('.'+i+'-'+j+' span').css('backgroundColor','#ccc');
                }
            }
            $('#img' + s_row + '_' +s_col + '_' + rows + '_' + cols).remove();
            $(".app_selected .app_cont a").each(function(){
                if($(this).find("img").attr("imgid") == 'img' + s_row + '_' +s_col + '_' + rows + '_' + cols) {
                    $(this).remove();
                    mfHeight();
                };
            });
            $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).remove();
            $('.cube .row .free').removeClass('free');
            $('.cube .row .start').removeClass('start');
            $('.cube .row .disabled').removeClass('disabled');

        });

    }
    //建立一個可存取到該file的url
    function getObjectURL(url) {
        return url ;
    }
    var getRandomColor = function(){
        return (function(m,s,c){
            return (c ? arguments.callee(m,s,c-1) : '#') +
                    s[m.floor(m.random() * 16)];
        })(Math,'0123456789abcdef',5);
    };
    $.fn.extend({
        //定义鼠标右键方法，接收一个函数参数
        "rightclick":function(fn){
            //调用这个方法后将禁止系统的右键菜单
            $(this).bind('contextmenu',function(e){
                return false;
            });
            //为这个对象绑定鼠标释放事件
            $(this).mouseup(function(e){
                //如果按下的是右键，则执行函数
                if(3 == e.which){
                    fn(e);
                }
            });
        }
    });
</script>
<script type="text/javascript">
    /**
     * 警告
     * @param	{String}	消息内容
     */
    artDialog.alert = function (content) {
        return artDialog({
            id: 'Alert',
            icon: 'warning',
            fixed: true,
            lock: true,
            content: content,
            ok: true
        });
    };

    /**
     * 确认
     * @param	{String}	消息内容
     * @param	{Function}	确定按钮回调函数
     * @param	{Function}	取消按钮回调函数
     */
    var artDialogConfirm;
    artDialog.confirm = function (content, yes, no) {
        artDialogConfirm = artDialog({
            id: 'Confirm',
            icon: 'question',
            fixed: true,
            lock: true,
            opacity: .1,
            top: '20%!important',
            content: content,
            ok: function (here) {
                return yes.call(this, here);
            },
            cancel: function (here) {
                return no && no.call(this, here);
            }
        });
        return artDialogConfirm;
    };

    //显示内连接弹框
    function md(id){
        $("#linkId").val(id);
        $("#ctModal").modal("show");
        //cc(t);
    }

    function cc(t){
        var _cont = $(t).parents("tr").find(".link").text(),
                _tit = $(t).parents("tr").find("td:first").text();
        //alert(_cont+"=="+_tit);
        //alert($("#linkId").val());
        $("#"+$("#linkId").val()).val(_cont);
        $("#"+$("#linkId").val()).parents(".form_group").next(".nlink-wp").show().find(".sel-tags").html("<em>"+_tit+"</em>");
        $(".close").click();
    };

    //检查重复提交
    function checkRepeatSubmit(){
        var repeatSubmitFlag = true;
        $.ajax({
            url : "ajaxGetRSV.html",
            async : false,
            success : function(data){
                if(data!=$("#rsv").val()){
                    repeatSubmitFlag = false;
                }
            }
        });
        return repeatSubmitFlag;
    }

    //打开设置魔方模块
    function showMF(){
        //清空
        $("#appCountId").val("");
        $("#mfForm .cube_box .form_cont .cube img").remove();
        $("#mfForm .imgEdit").remove();

        $('.cube .row .selected span').css('backgroundColor','#CCCCCC');
        $('.cube .row .selected').removeClass('selected').css('backgroundColor','transparent');

        $('.cube .row .free').removeClass('free');
        $('.cube .row .start').removeClass('start');
        $('.cube .row .disabled').removeClass('disabled');

        $("#advEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#blankBoxEdit").hide();
        $("#goodsEdit").hide();

        $("#cubeEdit").show();

        var id,
                arrApp = new Array();
        if($(".app_cont").length > 0) {
            $("#ip_cont .app_cont").each(function(){
                arrApp.push($(this).attr("id").substring(8));
            });
            id = Math.max.apply(null, arrApp) + 1;
        } else {
            id = 1;
        };
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        var _item = '<div class="app_item app_cube app_selected"><div class="app_cont item" id="app_cont'+id+'"></div>'+
                '<div class="app_edit"><div class="app_btns">'+
                '<a href="javascript:;" class="edit" onclick="updatemf('+id+')">编辑</a>'+
                '<a href="javascript:;" class="delete" onclick="delmf('+id+')">删除</a></div></div></div>';
        $("#ip_cont").append(_item);

        $('.main_cont').css('minHeight',$('.common_data').height() +400 + 'px');
    }
    //打开设置广告模块
    function showAdv(){
        //清空
        closeAdvEdit();

        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#blankBoxEdit").hide();
        $("#goodsEdit").hide();
        $("#advEdit").find(".imgAdvEdit").show();
        $("#advEdit").show();

        //绑定图片广告的选择内连接事件
        rbundLink("imgAdvHref");

        var id,
                arrApp = new Array();
        if($(".img_adv").length > 0) {
            $("#ip_cont .img_adv").each(function(){
                arrApp.push($(this).attr("id").substring(8));
            });
            id = Math.max.apply(null, arrApp) + 1;
        } else {
            id = 1;
        };
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        var _item = '<div class="app_item app_cube app_selected"><div class="img_adv item" id="img_adv_'+id+'">'+
                '<a href="javascript:;"><img alt="" src=""></a></div>'+
                '<div class="app_edit"><div class="app_btns">'+
                '<a href="javascript:;" class="edit" onclick="updateAdv('+id+')">编辑</a>'+
                '<a href="javascript:;" class="delete" onclick="delAdv('+id+')">删除</a></div></div></div>';
        $("#ip_cont").append(_item);
    }
    //打开设置轮播广告模块
    function showRollAdv(){
        //清空
        $("#rollAdvForm #slides .slide").remove();

        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#blankBoxEdit").hide();
        $("#goodsEdit").hide();

        $("#rollAdvEdit").show();

        var id,
                arrApp = new Array();
        if($(".roll_adv").length > 0) {
            $("#ip_cont .roll_adv").each(function(){
                arrApp.push($(this).attr("id").substring(8));
            });
            id = Math.max.apply(null, arrApp) + 1;
        } else {
            id = 1;
        };
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        var _item = '<div class="app_item app_cube app_selected"><div class="roll_adv item" id="roll_adv'+id+'">'+
                '<div name="roll_banner" class="roll_banner"><div class="swiper-container">'+
                '<div class="swiper-wrapper"></div></div></div></div>'+
                '<div class="app_edit"><div class="app_btns">'+
                '<a href="javascript:;" class="edit" onclick="updateRollAdv('+id+')">编辑</a>'+
                '<a href="javascript:;" class="delete" onclick="delRollAdv('+id+')">删除</a></div></div></div>';
        $("#ip_cont").append(_item);
        rollSwiper = $('.app_selected .swiper-container').swiper({
            autoplay: 3000,
            mode:'horizontal',
            loop: true
        });
    }
    //打开设置文字模块
    function showTextEdit(){
        //清空
        clearHtml();

        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#blankBoxEdit").hide();
        $("#goodsEdit").hide();

        $("#textEdit").show();

        var id,
                arrApp = new Array();
        if($(".text").length > 0) {
            $("#ip_cont .text").each(function(){
                arrApp.push($(this).attr("id").substring(8));
            });
            id = Math.max.apply(null, arrApp) + 1;
        } else {
            id = 1;
        };
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        var _item = '<div class="app_item app_cube app_selected"><div class="text item" id="text_app'+id+'"></div>'+
                '<div class="app_edit"><div class="app_btns">'+
                '<a href="javascript:;" class="edit" onclick="updateTextEdit('+id+')">编辑</a>'+
                '<a href="javascript:;" class="delete" onclick="delText('+id+')">删除</a></div></div></div>';
        $("#ip_cont").append(_item);
    }

    //清空全屏轮播
    function clearFullRoll(){
        $("#fullRollId").val("");
        var sdtag = $("#fullRollForm").find("[name='sd']");
        for(var i=0;i<sdtag.length;i++){
            if($(sdtag[i]).val()=="sh"){
                $(sdtag[i]).prop("checked","checked");
                break;
            }
        }

        $("#fullRollForm").find("#enable_music").prop("checked",false);
        $(".choose_music").hide();
        $(".choose_music").text("选择音乐");
        $(".music-ico").hide();
        $("#music").val("");
        $("#fullRollForm").find("#musicname").val("");
        $(".music-name").hide().text("");

        $(".add_slides").remove();
    }

    //打开全屏轮播模块设置
    function showFullRoll(){
        //清空
        clearFullRoll();
        //确认
        art.dialog.confirm('设置全屏轮播会清除已添加的全部模块，你确定要清除吗？',
                function () {
                    //清除
                    var merchantId = $("#merchantId").val();
                    /* $.ajax({
                        url : "clearAll.htm",
                        data : {
                            merchantId : merchantId
                        },
                        async : false
                    }); */
                    $("#ip_cont .app_item").remove();

                    //隐藏
                    $("#advEdit").hide();
                    $("#cubeEdit").hide();
                    $("#rollAdvEdit").hide();
                    $("#textEdit").hide();
                    $("#basicEdit").hide();
                    $("#blankBoxEdit").hide();
                    $("#goodsEdit").hide();
                    //显示
                    $("#fullRollEdit").show();

                    var id,
                            arrApp = new Array();
                    if($(".roll_adv").length > 0) {
                        $("#ip_cont .fullRoll").each(function(){
                            arrApp.push($(this).attr("id").substring(8));
                        });
                        id = Math.max.apply(null, arrApp) + 1;
                    } else {
                        id = 1;
                    };
                    $("#ip_cont").find(".app_selected").removeClass("app_selected");
                    var _item = '<div class="app_item app_cube app_selected"><div class="fullRoll item" id="fullRoll'+id+'">'+
                            '<input type="hidden" class="fullRollSD" value="sh">'+
                            '<div class="phone_wp pr"><div class="full-swiper-container">'+
                            '<div class="swiper-wrapper fc_slides"></div><div class="full-pagination"></div></div><b class="music-ico"></b><input type="hidden" class="musicname" value=""><b class="vtc-img" style="display: none;"></b></div></div>'+
                            '<div class="app_edit"><div class="app_btns">'+
                            '<a href="javascript:;" class="edit" onclick="updateFullRoll('+id+')">编辑</a>'+
                            '<a href="javascript:;" class="delete" onclick="delFullRoll('+id+')">删除</a></div></div></div>';
                    $("#ip_cont").append(_item);
                    $(".plugin_box").css("top",$(".app_selected").offset().top - 150 + "px");
                    fullSwiper = $('.app_selected .full-swiper-container').swiper({
                        autoplay: 3000,
                        loop: true
                        //pagination: '.full-pagination',
                        //paginationClickable: true
                    });
                }, function () {
                    return;
                }
        );
    }
    //打开店铺标题、转发信息设置
    function showBasicEdit(){
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#blankBoxEdit").hide();
        $("#goodsEdit").hide();

        if($("#basicEdit").css("display")=="none"){
            //显示
            $("#basicEdit").show().removeClass("plugin_box").css("top","90px");
        }else{
            $("#basicEdit").hide();
        }
    }

    //显示空白占位
    function showBlankbox(){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        //显示
        $("#blankBoxEdit").show();

        var id,
                arrApp = new Array();
        if($(".blank-box").length > 0) {
            $("#ip_cont .blank-box").each(function(){
                arrApp.push($(this).attr("id").substring(8));
            });
            id = Math.max.apply(null, arrApp) + 1;
        } else {
            id = 1;
        };
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        var _item = '<div class="app_item app_selected"><div class="blank-box item" id="blankbox'+id+'"></div>'+
                '<div class="app_edit"><div class="app_btns">'+
                '<a href="javascript:;" class="edit" onclick="updateBlankbox('+id+')">编辑</a>'+
                '<a href="javascript:;" class="delete" onclick="delBlankbox('+id+')">删除</a></div></div></div>';
        $("#ip_cont").append(_item);
        $("#slider-range-min").slider({
            range: "min",
            value: $("#blankbox"+id).height(),
            min: 30,
            max: 50,
            slide: function( event, ui ) {
                $(".hg-val").text(ui.value);
                $("#blankbox"+id).height(ui.value);
                $("#blankBoxHeight").val(ui.value);
            }
        });
        $(".hg-val").text($("#slider-range-min").slider("value"));
        $("#blankBoxHeight").val($("#slider-range-min").slider("value"));
    }
    //显示商品模块
    function showGoods(){
        //清除模块内容
        $(".gds-show").find(".img-sw").remove();
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#blankBoxEdit").hide();
        //显示
        $("#goodsEdit").show();

        $("input[name='style']").change(function(){
            $(".app_selected .goodsmod").attr("styleclass",$("input[name='style']:checked").val());
            $(".app_selected .app-goods").attr("class","app-goods clearfix").addClass($("input[name='style']:checked").val());
        });

        var id,
                arrApp = new Array();
        if($(".goodsmod").length > 0) {
            $("#ip_cont .goodsmod").each(function(){
                arrApp.push($(this).attr("id").substring(8));
            });
            id = Math.max.apply(null, arrApp) + 1;
        } else {
            id = 1;
        };
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        var _item = '<div class="app_item app_selected"><div class="goodsmod item" id="goodsmod'+id+'" styleclass="">'+
                '<ul class="app-goods clearfix"></ul></div>'+
                '<div class="app_edit"><div class="app_btns">'+
                '<a href="javascript:;" class="edit" onclick="updateGoods('+id+')">编辑</a>'+
                '<a href="javascript:;" class="delete" onclick="delGoods('+id+')">删除</a></div></div></div>';
        $("#ip_cont").append(_item);
        $(".app_selected .goodsmod").attr("styleclass",$("input[name='style']:checked").val());
        $(".app_selected .app-goods").attr("class","app-goods clearfix").addClass($("input[name='style']:checked").val());
        $('.main_cont').css('minHeight',$('.common_data').height() +400 + 'px');
    }

    //添加魔方
    function addMF(){
        var flag = true;
        var imgEdit = $("#mfForm .imgEdit");
        if(imgEdit.length>0){
            for(var i=0;i<imgEdit.length;i++){
                var imgEditId = $(imgEdit[i]).attr("id");
                var imgId = imgEditId.substring(7);

                var imghref = "img"+imgId+"_href";
                var imgsrc = "img"+imgId+"_src";

                if($("#"+imghref).val()!=""){
                    if(!checkSpecSymb(imghref)){

                        toastr.error(imgId+"号方块图片链接地址有特殊字符！");
                        flag = flag && false;
                    }
                }

                if($("#"+imgsrc).val()==""){
                    toastr.warning(imgId+"号方块请先选择图片！");
                    flag = flag && false;
                }
            }

        }else{
            toastr.warning("请先配置魔方！");
            flag = flag && false;
        }

        if(flag){
            $(".app_selected .app_cont img").each(function(){
                var _id = $(this).attr("imgid");
                $(this).parent("a").attr("href",$("#"+_id+"_href").val());
            });
            $("#cubeEdit").hide();
        }
    }
    //提交修改魔方
    function updateMF(){
        var appContId = $("#appCountId").val();
        var appCount = $("#app_cont"+appContId);
        var flag = true;
        var imgEdit = $("#mfForm .imgEdit");
        if(imgEdit.length>0){
            for(var i=0;i<imgEdit.length;i++){
                var imgEditId = $(imgEdit[i]).attr("id");
                var imgId = imgEditId.substring(7);

                var imghref = "img"+imgId+"_href";
                var imgsrc = "img"+imgId+"_src";

                if($("#"+imghref).val()!=""){

                    if(!checkSpecSymb(imghref)){

                        toastr.error(imgId+"号方块图片链接地址有特殊字符！");
                        flag = flag && false;
                    }
                }

                if($("#"+imgsrc).val()==""){
                    toastr.warning(imgId+"号方块请先选择图片！");
                    flag = flag && false;
                }

                if(flag){
                    $(appCount).find("img[id=saimg"+imgId+"]").parent("a").attr("href",$("#"+imghref).val());
                }
            }
        }
        else{
            toastr.warning("请先配置魔方！");
            flag = flag && false;
        }

        if(flag){
            mfHeight();
            $("#cubeEdit").hide();
        }

    }
    //删除魔方
    function delmf(id){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        $("#blankBoxEdit").hide();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#app_cont"+id).parents(".app_item").remove();
    }
    //修改魔方
    function updatemf(id){
        //显示魔方模块
        //清空
        $("#appCountId").val("");
        $("#mfForm .cube_box .form_cont .cube img").remove();
        $("#mfForm .imgEdit").remove();

        $('.cube .row .selected span').css('backgroundColor','#CCCCCC');
        $('.cube .row .selected').removeClass('selected').css('backgroundColor','transparent');

        $('.cube .row .free').removeClass('free');
        $('.cube .row .start').removeClass('start');
        $('.cube .row .disabled').removeClass('disabled');

        $("#advEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#blankBoxEdit").hide();
        $("#goodsEdit").hide();

        $("#cubeEdit").show();
        //修改魔方的id
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#app_cont"+id).parents(".app_item").addClass("app_selected");
        var _h = $("#app_cont"+id).parents(".app_item").offset().top - 150;
        $(".edit_area").removeClass("plugin_box").css("top",_h);

        var iImg="";
        var imgs = $("#app_cont"+id+" .imgapp");

         for(var i = 0;i<imgs.length;i++){
            var imgid = $(imgs[i]).attr("id").substring(2);
            var src = $(imgs[i]).attr("src");
            var href = $(imgs[i]).parent().attr("href");
            var s_row = imgid.substring(3,4);
            var s_col = imgid.substring(5,6);
            var rows = imgid.substring(7,8);
            var cols = imgid.substring(9);
         if(src!=""&&iImg!=imgid){
             iImg=imgid;
                var width = cols * 90;
                var height = rows * 90;
                //var imgWidth = cols * 160;
                //var imgHeight = rows * 160;
                //把图片放到魔方里
                var img = new Image();
                img.src = src;
                img.width = width;
                img.height = height;
                img.id = imgid;
                $('.cube').append(img);
                $(img).css({
                    position: 'absolute',
                    left: (s_col - 1) * 90 - 1 + 'px',
                    top: (s_row - 1) * 90 + 'px'
                }).attr('class', 'img_s');
                //创建修改时的imgEdit
                updateImgEdit(s_row, s_col, rows, cols, src, href);
            }
        }
        $("#appCountId").val(id);
        $("#submitBtn").attr("onclick","updateMF()");

    }

    //修改图片广告
    function updateAdv(advid){
        //隐藏魔方布局编辑div
        //清空
        closeAdvEdit();

        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#blankBoxEdit").hide();
        $("#goodsEdit").hide();
        $("#advEdit").find(".imgAdvEdit").show();
        $("#advEdit").show();

        //绑定图片广告的选择内连接事件
        rbundLink("imgAdvHref");

        var href = $("#img_adv_"+advid).find("a").attr("href");
        var isflag=true;
        //获取所有选项
        var lis = $('#advEdit .gn-chs').find("li");
        //遍历比对，找到对应href的选项，添加选中class
        for(var j=0;j<lis.length;j++){
            $(lis[j]).removeClass("s-selected");
            if(href == $(lis[j]).find("a").attr("data-href")){
                $(lis[j]).addClass("s-selected");
                $(lis[j]).find("a").click();
                sel();
                isflag=false;
            }
        }


        var reg = /^(list\/\d)|(item\/\d)/;
        var n =href.search(reg);
        if(n>-1){
            for(var j=0;j<lis.length;j++){
                if("choose" == $(lis[j]).find("a").attr("data-href")){
                    $(lis[j]).addClass("s-selected");
                    sel();
                    isflag=false;
                }
            }
        }
        //自定义链接
        if(href){
            if(isflag){

                var liss = $('#advEdit .lk-chs').find("li");
                var fl= $(liss).find("a");
                //   $(".s-selected").removeClass("s-selected");
                $(fl).click()
                //   sel();
            }
        }
        //创建图片广告编辑div
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#img_adv_"+advid).parents(".app_item").addClass("app_selected");
        var _h = $("#img_adv_"+advid).parents(".app_item").offset().top - 150;
        $(".edit_area").removeClass("plugin_box").css("top",_h);

        $("#imgAdvId").val(advid);
        $("#imgAdvSrc").val($("#img_adv_"+advid).find("img").attr("src"));
        $("#advImg").attr("src",$("#img_adv_"+advid).find("img").attr("src"));
        $("#imgAdvHref").val(href);
    }
    //保存广告图片
    function saveAdv(){
        var flag = true;
        if($("#imgAdvSrc").val()==""){
            toastr.warning("请先选择图片！");
            flag = flag && false;
        }

        if($("#imgAdvHref").val()!=""){
            if(!checkSpecSymb("imgAdvHref")){
                toastr.error("图片链接地址有特殊字符！");
                flag = flag && false;
            }
        }

        if(flag){
            $(".app_selected .img_adv img").attr("src",$("#imgAdvSrc").val());
            $(".app_selected .img_adv a").attr("href",$("#imgAdvHref").val());
            $("#advEdit").hide();
        }
    }
    //删除图片广告
    function delAdv(advid){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        $("#blankBoxEdit").hide();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#img_adv_"+advid).parent(".app_item").remove();
    }
    //关闭广告图片编辑div
    function closeAdvEdit(){
        $("#advEdit").hide();
        $("#imgAdvId").val("");
        $("#imgAdvSrc").val("");
        $("#advImg").attr("src","");
        $("#imgAdvHref").val("");
    }


    //修改轮播广告
    function updateRollAdv(rollAdvid){
        //隐藏其他模块编辑，显示轮播广告编辑
        $("#rollAdvForm #slides .slide").remove();

        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#blankBoxEdit").hide();
        $("#goodsEdit").hide();

        $("#rollAdvEdit").show();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#roll_adv"+rollAdvid).parents(".app_item").addClass("app_selected");
        var _h = $("#roll_adv"+rollAdvid).parents(".app_item").offset().top - 150;
        $(".edit_area").css("top",_h);


        //重新赋值
        $("#rollImgAdvId").val(rollAdvid);
        var slides = $("#roll_adv"+rollAdvid).find(".swiper-slide");
        var rolls = new Array();
        for(var i=1;i<slides.length-1;i++){
            rolls[(i-1)] = slides[i];
        }
        for(var i=0;i<rolls.length;i++){
            var href = $(rolls[i]).find("a").attr("href");
            var src = $(rolls[i]).find("img").attr("src");
            updateRollAdvEdit(src,href);
        }

    }
    //提交轮播广告
    function saveRollAdv(){
        var flag = true;
        var slides = $("#rollAdvForm #slides .slide");
        if(slides.length<1){
            toastr.warning('请先至少添加一张图片!');
            flag = flag && false;
        }
        for(var i=0;i<slides.length;i++){
            var href = $(slides[i]).find(".form-control");
            if(href.val()!=""){
                if(!checkSpecSymb(href.prop("id"))){
                    toastr.error('第'+(i+1)+'张图片链接地址有特殊字符');
                    flag = flag && false;
                }
            }
        }

        if(flag){
            $("#rollAdvEdit .slide").each(function(){
                var n = $(this).find(".slide_img").attr("class").substring(15),
                        src = $("#rollImgAdvHref"+n).val(),
                        _img = $(this).find(".slide_img").attr("src");
                $(".app_selected .swiper-slide").each(function(){
                    if($(this).find("img").attr("src") == _img) {
                        $(this).find("a").attr("href",src);
                    };
                });
            });
            $("#rollAdvEdit").hide();
        }
    }

    //删除轮播广告
    function delRollAdv(rollAdvid){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        $("#blankBoxEdit").hide();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#roll_adv"+rollAdvid).parent(".app_item").remove();
    }
    //修改文字
    function updateTextEdit(id){
        //showTextEdit();
        //清空
        clearHtml();

        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#blankBoxEdit").hide();
        $("#goodsEdit").hide();

        $("#textEdit").show();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#text_app"+id).parents(".app_item").addClass("app_selected");
        var _h = $("#text_app"+id).parents(".app_item").offset().top - 150;
        $(".edit_area").css("top",_h);

        $("#textId").val(id);
        $("#content").html($("#text_app"+id).html());
        refreshHtml($("#text_app"+id).html());
    }

    //保存文字
    function saveText(){
        var flag = true;
        if($("#content").val()==""){
            toastr.warning("文字内容不能为空！");
            flag = flag && false;
        }
        if(flag){
            $(".app_selected .text").html($("#content").val());
            $("#textEdit").hide();
        }
    }

    //删除文字
    function delText(id){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        $("#blankBoxEdit").hide();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#text_app"+id).parent(".app_item").remove();
    }

    //提交全屏轮播
    function saveFullRoll(){
        var flag = true;
        var slides = $("#fullRollForm .add_slides");
        if(slides.length<1){
            toastr.warning('请先至少添加一张图片!');
            flag = flag && false;
        }
        for(var i=0;i<slides.length;i++){
            var href = $(slides[i]).find("input[name='fullRollHref']");

            if(href.val()!=""){
                if(!checkSpecSymb(href.prop("id"))){
                    toastr.error('第'+(i+1)+'张图片链接地址有特殊字符');
                    flag = flag && false;
                }
            }
        }

        if(flag){
            $("#fullRollEdit .add_slides").each(function(){
                var n = $(this).find(".fullRollAgain").attr("id").substring(9),
                        src = $("#fullRollHref_"+n).val(),
                        _img = $(this).find(".view_img img").attr("src");
                $(".app_selected .swiper-slide").each(function(){
                    if($(this).find("img").attr("src") == _img) {
                        $(this).find("a").attr("href",src);
                    };
                });
            });
            $("#fullRollEdit").hide();
        }
    }

    //修改全屏轮播
    function updateFullRoll(id){
        //清空
        clearFullRoll();
        //隐藏
        $("#advEdit, #cubeEdit, #rollAdvEdit, #textEdit, #basicEdit").hide();
        //显示
        $("#fullRollEdit").show().removeClass("plugin_box");
        //编辑框定位
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#fullRoll"+id).parents(".app_item").addClass("app_selected");
        var _h = $("#fullRoll"+id).parents(".app_item").offset().top - 150;
        $(".edit_area").css("top",_h);

        //重新赋值
        $("#fullRollId").val(id);
        var sd = $("#fullRoll"+id).find(".fullRollSD").val();
        var sdtag = $("#fullRollForm").find("[name='sd']");
        for(var i=0;i<sdtag.length;i++){
            if($(sdtag[i]).val()==sd){
                $(sdtag[i]).prop("checked","checked");
                break;
            }
        }

        var music = $("#fullRoll"+id).find("audio").prop("src");
        if(music){
            $("#fullRollForm").find("#enable_music").prop("checked","checked");
            $(".choose_music").show();
            $(".music-ico").show();
            $("#music").val(music);
            $(".choose_music").text("修改");
        }
        var musicname = $("#fullRoll"+id).find(".musicname").val();
        if(musicname){
            $("#fullRollForm").find("#musicname").val(musicname);
            $(".music-name").show().text(musicname);
        }

        var slides = $("#fullRoll"+id).find(".swiper-slide");
        var newSlides = new Array();
        for(var i=1;i<slides.length-1;i++){
            newSlides.push($(slides[i]));
        }
        for(var i=0;i<newSlides.length;i++){
            addFun($(newSlides[i]).find("img").prop("src"));
        }
        var frhref = $("input[name='fullRollHref']");
        var isflag=true;
        for(var i=0;i<newSlides.length;i++){
            var href = $(newSlides[i]).find("a").attr("href");
            $(frhref[i]).val(href);
            //获取所有选项
            var lis = $(frhref[i]).prev(".gn-chs").find("li");
            //遍历比对，找到对应href的选项，添加选中class
            for(var j=0;j<lis.length;j++){
                $(lis[j]).removeClass("s-selected");
                if(href == $(lis[j]).find("a").attr("data-href")){
                    $(lis[j]).addClass("s-selected");
                    $(lis[j]).find("a").click();
                    sel();
                    isflag=false;
                }
            }

            //$(frhref[i]).prev(".gn-chs").val(href);
            var reg = /^(list\/\d)|(item\/\d)/;
            var n =href.search(reg);
            if(n>-1){
                for(var j=0;j<lis.length;j++){
                    if("choose" == $(lis[j]).find("a").attr("data-href")){
                        $(lis[j]).addClass("s-selected");
                        sel();
                        isflag=false;
                    }
                }
                //$(frhref[i]).prev(".gn-chs").val("choose");
            }
            if(href){
                if(isflag)
                {
                    var liss = $(frhref[i]).prev(".gn-chs").prev(".lk-chs").find("li").find("a");
                    $(liss).click();
                }
                $("#fullRollHref_"+i).val(href)
            }
        }
    }
    //删除全屏轮播
    function delFullRoll(id){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        $("#blankBoxEdit").hide();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#fullRoll"+id).parent(".app_item").remove();
    }
    //保存空白占位
    function saveBlankBox(){
        $("#blankBoxEdit").hide();
    }
    //修改空白占位
    function updateBlankbox(id){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        //显示
        $("#blankBoxEdit").show();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#blankbox"+id).parents(".app_item").addClass("app_selected");
        var _h = $("#blankbox"+id).parents(".app_item").offset().top - 150;
        $(".edit_area").css("top",_h);

        //设置空白占位模块的id
        $("#blankBoxId").val(id);
        //设置空白占位模块的高度
        var height = $(".blank-box").height();
        $( "#slider-range-min" ).slider({
            range: "min",
            value: $("#blankbox"+id).height(),
            min: 30,
            max: 50,
            slide: function( event, ui ) {
                $(".hg-val").text(ui.value);
                $("#blankbox"+id).height(ui.value);
                $("#blankBoxHeight").val(ui.value);
            }
        });
        $(".hg-val").text($("#slider-range-min").slider("value"));
        $("#blankBoxHeight").val($("#slider-range-min").slider("value"));
    }
    //删除空白占位
    function delBlankbox(id){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        $("#blankBoxEdit").hide();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#blankbox"+id).parent(".app_item").remove();
    }

    //保存商品模块
    function saveGoods(){
        var imgs = $("#choooseProduct").parent("dd").find("img");
        if(imgs.length<1){
            toastr.warning("请最少选择一个商品");
            return;
        }
        $("#goodsEdit").hide();
    }

    //修改商品模块
    function updateGoods(id){
        //清除模块内容
        $(".gds-show").find(".img-sw").remove();
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#blankBoxEdit").hide();
        //显示
        $("#goodsEdit").show();
        //定位
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#goodsmod"+id).parents(".app_item").addClass("app_selected");
        var _h = $("#goodsmod"+id).parents(".app_item").offset().top - 150;
        $(".edit_area").css("top",_h);

        //获取模块的商品信息
        var styleClass = $("#goodsmod"+id).attr("styleClass");
        var href = new Array();
        var src = new Array();
        var name = new Array();
        var price = new Array();
        var as =  $("#goodsmod"+id).find("a");
        for(var i=0;i<as.length;i++){
            var a = $(as[i]);
            href.push(a.attr("href"));
            src.push(a.find("img").attr("src"));
            name.push(a.find("h3").html());
            price.push(a.find("span").html());
        }
        //重新赋值
        $("#goodsmodId").val(id);
        var styles =  $("#goodsForm").find("input[name='style']");
        for(var i=0;i<styles.length;i++){
            if($(styles[i]).val()==styleClass){
                $(styles[i]).attr("checked",true);
            }
        }
        for(var i=0;i<href.length;i++){
            var html = '<div class="img-sw"><img src="'+src[i]+'" width="50px;" height="50px;"/>';
            html = html + '<div style="display:none;">'
            +'<input type="hidden" name="href" value="'+href[i]+'">'
            +'<input type="hidden" name="src" value="'+src[i]+'">'
            +'<input type="hidden" name="name" value="'+name[i]+'">'
            +'<input type="hidden" name="price" value="'+price[i]+'">'
            +'</div><a class="rmv-gd" onclick="rmv(this)">X</a></div>';
            $(".gds-show").append(html);
        }
    }

    //删除商品模块
    function delGoods(id){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        $("#blankBoxEdit").hide();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#goodsmod"+id).parent(".app_item").remove();
    }

    //添加分隔线
    function addLine(){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        $("#blankBoxEdit").hide();
        var id,
                arrApp = new Array();
        if($(".dividing").length > 0) {
            $("#ip_cont .dividing").each(function(){
                arrApp.push($(this).attr("id").substring(8));
            });
            id = Math.max.apply(null, arrApp) + 1;
        } else {
            id = 1;
        };
        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        var _item = '<div class="app_item app_selected"><div class="dividing item" id="dividing'+id+'"><div class="line-wp"><hr class="line-app"></div></div>'+
                '<div class="app_edit"><div class="app_btns">'+
                '<a href="javascript:;" class="delete" onclick="delLine('+id+')">删除</a></div></div></div>';
        $("#ip_cont").append(_item);

    }
    //删除分隔线
    function delLine(id){
        //隐藏
        $("#advEdit").hide();
        $("#cubeEdit").hide();
        $("#rollAdvEdit").hide();
        $("#textEdit").hide();
        $("#fullRollEdit").hide();
        $("#basicEdit").hide();
        $("#goodsEdit").hide();
        $("#blankBoxEdit").hide();

        $("#ip_cont").find(".app_selected").removeClass("app_selected");
        $("#dividing"+id).parent(".app_item").remove();
    }


    /*
     * 保存选择的图片信息
     * 选择图片控件回调函数
     */
    function saveChoooseTrademark(path,id){
        var s = id.split("#imgEdit");
//		if(s.length==2){
//				$("#saimg"+s[1]).parent("a").remove();
//				$("#saimg"+s[1]).parent("a").remove();
//		}
        if(id=="adv"){//广告
            $("#imgAdvSrc").val(path);
            $("#advImg").attr("src",path);
            $(".app_selected .img_adv img").attr("src",path);
        }else if(id=="rollAdv"){//轮播

            var paths = String(path).split(",");
            var rSlide;
            for(var i=0;i<paths.length;i++){
                chooseRollAdv(paths[i]);
                rSlide = rollSwiper.createSlide('<a href=""><img alt="" src="'+paths[i]+'"></a>');
                rollSwiper.appendSlide(rSlide);
            };

        }else if(id.indexOf("rollImgAdvSrc")>-1){//重新上传轮播
            var _index = id.substring(id.length-1),
                    _src = $("."+id.replace(/rollImgAdvSrc/,"rolls")).attr("src");
            $("#"+id).val(path);
            $("."+id.replace(/rollImgAdvSrc/,"rolls")).attr("src",path);
            $(".app_selected .swiper-slide").each(function(){
                if($(this).find("img").attr("src") == _src) {
                    $(this).find("img").attr("src",path);
                };
            });
            rollSwiper.reInit();
        }else if(id=="fullRoll"){//全屏轮播
            var paths = String(path).split(",");
            var fSlide;
            for(var i=0;i<paths.length;i++){
                addFun(paths[i]);
                //fSlide = fullSwiper.createSlide('<a href=""><img alt="" src="'+path[i]+'"></a>');
                //fullSwiper.appendSlide(fSlide);
            };
            fullSwiper.destroy();
            $('.app_selected .swiper-wrapper, .app_selected .full-pagination').html("");
            $(".edit_cont .add_slides").each(function(){
                var imgSrc = $(this).find(".view_img img").attr("src");
                $('.app_selected .swiper-wrapper').append('<div class="swiper-slide"><a href="javascript:;"><img alt="" src="'+imgSrc+'"></a></div>');
            });
            if($("input[value='sh']").prop("checked") == true) {
                fullSwiper = $('.app_selected .full-swiper-container').swiper({
                    autoplay: 3000,
                    loop: true,
                    mode: 'horizontal',
                    pagination: '.full-pagination',
                    paginationClickable: true
                });
            } else if ($("input[value='sv']").prop("checked") == true) {
                fullSwiper = $('.app_selected .full-swiper-container').swiper({
                    autoplay: 3000,
                    loop: true,
                    mode: 'vertical'
                });
            } else {
                fullSwiper = $('.app_selected .full-swiper-container').swiper({
                    autoplay: 3000,
                    loop: true,
                    mode: 'horizontal',
                    pagination: '.full-pagination',
                    paginationClickable: true
                });
            };
            $("#enable_music").change(function(){
                if($(this).prop("checked") == true) {
                    $(".music-ico").show();
                } else {
                    $(".music-ico").hide();
                };
            });
        }else if(id.indexOf("fullRoll_")>-1){//重新上传全屏轮播
            var _fSrc = $("#"+id).prev("img").prop("src");
            $("#"+id).prev().prop("src",path);
            $("#"+id.replace("fullRoll_","fullRollImg_")).prop("src",path);
            $("#"+id.replace("fullRoll_","fullRollImgSrc_")).val(path);
            $(".app_selected .swiper-slide").each(function(){
                if($(this).find("img").attr("src") == _fSrc) {
                    $(this).find("img").attr("src",path);
                };
            });
            fullSwiper.reInit();
        }else if(id=="shareLogo"){//转发logo
            $("#basicTemp1").val(path);
            $("#basicTemp1Img").prop("src",path);
        }else if(id=="homeImg"){//页面预览图
            $("#homeImgSrc").val(path);
            $("#homeImg").prop("src",path);
        }else{//魔方
            $(id+" .imgsrc").val(path);
            $(id+" .imgsrctip").val(path);
            $(id+" .imgsrc").change();
            var _id = "img"+id.substring(8),
                    _width = $("#"+_id+"_width").val()/2,
                    _height = $("#"+_id+"_height").val()/2,
                    _top = parseInt($("#"+_id).css("top"))/90*80,
                    _left = (parseInt($("#"+_id).css("left"))+1)/90*80-1,
                    _href = $("#"+_id+"_href").val();
            $(".app_selected .app_cont").append('<a href="'+_href+'"><img class="imgapp" id="sa'+_id+'" imgid="'+_id+'" alt="" src="'+path+'" width="'+_width+'" height="'+_height+'" style="position:absolute; left:'+_left+'px; top:'+_top+'px;"></a>');
            mfHeight();
        }

    }
    //魔方高度
    function mfHeight(){
        $(".app_selected .app_cont img").each(function(){
            var $t = (parseInt($(this).attr("id").substring(5,6))-1)*80,
                    $l = (parseInt($(this).attr("id").substring(7,8))-1)*80-1,
                    $w = parseInt($(this).attr("id").substring(9,10))*80,
                    $h = parseInt($(this).attr("id").substring(11,12))*80;
            $(this).css("top",$t+"px");
        });

        Array.ExistsSameValues = function(a1, a2) {
            var exists = false;
            if(a1 instanceof Array && a2 instanceof Array){
                for (var i=0,iLen=a1.length; i<iLen; i++){
                    for (var j=0,jLen=a2.length; j<jLen; j++){
                        if (a1[i]===a2[j]){
                            return true;
                        };
                    };
                };
            };
            return exists;
        };
        $(".app_selected .app_cont").each(function(){
            var _this = $(this),
                    _hg = "",
                    arr1 = new Array(),
                    arr2 = new Array();
            _this.find("img").each(function(){
                _hg = parseFloat($(this).css("top")) + parseFloat($(this).height());
                arr1.push(parseFloat(_hg));
                arr2.push(parseFloat($(this).css("top")));
            });
            //if(Array.ExistsSameValues(arr1, arr2) == true) {
            //	var _top = Math.max.apply(null, arr1);
            //	_this.height(_top);
            //} else {
            _this.find("img").each(function(){
                var _t = $(this),
                        _tr = parseInt(_t.css("top"))/80+1,
                        _rowspan = parseInt(_t.height()/80);
                for(var r=_tr;r<_rowspan+_tr;r++) {
                    _t.addClass("tr"+r+" ");
                };
            });
            var arr3 = new Array();
            for(var n=1;n<5;n++) {
                if(_this.find(".tr"+n).length == 0) {
                    arr3.push(n);
                };
            };
            _this.height(320-80*arr3.length);
            for(x in arr3) {
                _this.find("img").each(function(){
                    var _t = $(this),
                            _n = _t.prop("class").substring(9,10),
                            _top = parseInt(_t.css("top"));
                    if(_n > arr3[x]) {
                        _t.css("top",_top-80+"px");
                    };
                });
            };
            //};
        });
    };

    //创建修改时的imgEdit
    function updateImgEdit(s_row,s_col,rows,cols,src,href){

        var imgid = 'img' + s_row + '_' +s_col+ '_' +rows+ '_' +cols;
        var imgWidth = cols * 160;
        var imgHeight = rows * 160;

        $('.cube_box').after('<div class=\"imgEdit\" id=\"imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+'\">'
        +'<a href=\"javascript:;\" class=\"img_close\" id=\"imgClose'+s_row+'_'+s_col+'_'+rows+'_'+cols+'\">'
        +'<span class=\"glyphicon glyphicon-remove\"></span></a><div class=\"form_group fg_box\"><div class=\"fh\">选择图片：</div>'
        +'<div class=\"form_cont\">'
        +'<input class=\"form_cont choose\" type=\"button\" value=\"选择图片\">'
        +'<input id=\"'+imgid+'_src\" name=\"'+imgid+'_src\" class=\"form-control imgsrc\" type=\"hidden\" value=\"'+src+'\">'
        +'<span id=\"'+imgid+'_srcTip\"></span>'
            //+'<input class="form-control imgsrctip" type="text" value=\"'+src+'\" readonly="readonly"/>'
        +'<p class=\"text-muted\">建议尺寸：<span class=\"img_size\">'+imgWidth + '×' + imgHeight+'</span>像素</p></div></div>'
        +'<div class=\"form_group fg_box nlink-wp clearfix\"><div class=\"fh\">链接地址：</div><div class=\"form_cont dd-box\">'
        +'<div class="lk-sel lk-chs"><span class="sel-word"></span><ul><li class="s-selected"><a data-href="lk-01">功能链接</a></li><li><a data-href="lk-02">自定义链接</a></li></ul></div>'
        +'<div class="lk-sel gn-chs"><span class="sel-word"></span><ul>'
        +'<li class="s-selected"><a>--请选择--</a></li>'
        +'<li><a data-href="choose">商品</a></li>'
        +'<li><a data-href="thirdStoreIndex.htm?storeId=${thirdId}">微店首页</a></li>'
        +'<li><a data-href="allGoodsListByStoreId.htm?storeId=${thirdId}">所有商品</a></li>'
        +'</ul></div>'
        +'<input id="'+imgid+'_href" name="'+imgid+'_href" class=\"form-control custom-input\" type=\"text\" value=\"\">'
        +'<span id=\"'+imgid+'_hrefTip\"></span>'
        +'</div></div><div class="form_group fg_box nlink-wp clearfix none" style="display:none"><div class="fh">已选择：</div><div class="form_cont dd-box"><span class="sel-tags" ></span><a class="del-tags" attr-value="'+imgid+'_href" href="javascript:;">删除</a></div></div>'
        +'</div>');

        /* $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+' .gn-chs').val(href);
        var reg = /^(list\/\d)|(item\/\d)/;
        var n =href.search(reg);
        if(n>-1){
            $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+' .gn-chs').val("choose");
        } */
        rbundLink(imgid+'_href');
        var isflag=true;
        //获取所有选项
        var lis = $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+' .gn-chs').find("li");
        //遍历比对，找到对应href的选项，添加选中class
        for(var j=0;j<lis.length;j++){
            $(lis[j]).removeClass("s-selected");
            if(href == $(lis[j]).find("a").attr("data-href")){
               // alert(href)
                $(lis[j]).addClass("s-selected");
                $(lis[j]).find("a").click();
                sel();
                isflag=false;
            }
        }

        var reg = /^(list\/\d)|(item\/\d)/;
        var n =href.search(reg);
        if(n>-1){
            for(var j=0;j<lis.length;j++){
                if("choose" == $(lis[j]).find("a").attr("data-href")){
                    $(lis[j]).addClass("s-selected");
                   sel();
                    isflag=false;
                }
            }
        }
        //自定义链接
        if(href){
            if(isflag){

                var liss = $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+' .lk-chs').find("li");
                var fl= $(liss).find("a");
                //   $(".s-selected").removeClass("s-selected");
                $(fl).click();
                $("#"+imgid+'_href').val(href);
                isflag=false;
            }
        }
        //设置图片选择框
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+' .choose').button().click(function (){
            art.dialog.open('${basePath}/queryImageManageByChoose.htm', {
                id: '#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols,
                lock: true,
                width: '800px',
                height: '400px',
                title: '选择图片'
            });
        });
        //设置图片的隐藏参数
        //设置imgEdit的Id
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).append(
                '<input class="disp_none" name="imgEdit_id" id="'+imgid+'_id" type="text" value="'+imgid+'">');
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).append(
                '<input class="disp_none" name="'+imgid+'_width" id="'+imgid+'_width" type="text" value="'+imgWidth+'">');
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).append(
                '<input class="disp_none" name="'+imgid+'_height" id="'+imgid+'_height" type="text" value="'+imgHeight+'">');
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).append(
                '<input class="disp_none" name="'+imgid+'_style" id="'+imgid+'_style" type="text" value="'+
                $('#' + imgid ).attr("style")+'">');
        $('.imgEdit').hide();
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).hide();
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).slideDown('fast');

        //设置图片信息框改变事件
        $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+' .imgsrc').change(function(){

            var objUrl = getObjectURL($(this).val()) ;
            if($('#img' + s_row + '_' +s_col + '_' + rows + '_' + cols).length == 1){
                $('#img' + s_row + '_' +s_col + '_' + rows + '_' + cols).attr('src',objUrl);
            }else{
                var img = new Image();
                img.src = objUrl;
                img.width = imgWidth_s;
                img.height = imgHeight_s;
                img.id = 'img' + s_row + '_' +s_col+ '_' +rows+ '_' +cols;
                $('.cube').append(img);
                $('#img' + s_row + '_' +s_col + '_' + rows + '_' + cols).css({
                    position : 'absolute',
                    left : (s_col - 1) * 90 - 1 + 'px',
                    top : (s_row - 1) * 90 + 'px'
                }).attr('class','img_s');
                //设置图片的隐藏参数
                //设置imgEdit的Id
                $('#'+img.id+'_id').val(img.id);
                $('#'+img.id+'_width').val(imgWidth);
                $('#'+img.id+'_height').val(imgHeight);
                $('#'+img.id+'_style').val($('#' + img.id ).attr("style"));
            }

        });
        //魔方点击图片触发事件
        $('.img_s').click(function(){
            //重新刷出已经选择的功能连接内容
//            rbundLink(imgid+'_href');
//            var isflag=true;
//            //获取所有选项
//            var lis = $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+' .gn-chs').find("li");
//            //遍历比对，找到对应href的选项，添加选中class
//            for(var j=0;j<lis.length;j++){
//                $(lis[j]).removeClass("s-selected");
//                if(href == $(lis[j]).find("a").attr("data-href")){
//                    $(lis[j]).addClass("s-selected");
//                    $(lis[j]).find("a").click();
//                    sel();
//                    isflag=false;
//                }
//            }
//
//            var reg = /^(list\/\d)|(item\/\d)/;
//            var n =href.search(reg);
//            if(n>-1){
//                for(var j=0;j<lis.length;j++){
//                    if("choose" == $(lis[j]).find("a").attr("data-href")){
//                        $(lis[j]).addClass("s-selected");
//                        sel();
//                        isflag=false;
//                    }
//                }
//            }
//            //自定义链接
//            if(href){
//                if(isflag){
//
//                    var liss = $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols+' .lk-chs').find("li");
//                    var fl= $(liss).find("a");
//                    //   $(".s-selected").removeClass("s-selected");
//                    $(fl).click();
//                    $("#"+imgid+'_href').val(href);
//                    isflag=false;
//                }
//            }

            $('.imgEdit').hide();
            var stt=$('#imgEdit'+$(this).attr('id').substr(3,7)).find(".dd-box .gn-chs .sel-word");
            if(!$(stt).text()){
                $(stt).text("请选择");
            }
            $('#imgEdit'+$(this).attr('id').substr(3,7)).slideDown('fast');

        });
        $('#imgClose' + s_row + '_' +s_col + '_' + rows + '_' + cols).click(function(){
            var s_row = $(this).attr('id').substr(8,1);
            var s_col = $(this).attr('id').substr(10,1);
            var rows = $(this).attr('id').substr(12,1);
            var cols = $(this).attr('id').substr(14,1);
            for(var i = s_row;i <= s_row+rows;i++){
                for(var j = s_col;j <= s_col+cols;j++){
                    $('.'+i+'-'+j).removeClass('selected');
                    $('.'+i+'-'+j).css('backgroundColor','transparent');
                    $('.'+i+'-'+j+' span').css('backgroundColor','#ccc');
                }
            }
            $('#img' + s_row + '_' +s_col + '_' + rows + '_' + cols).remove();
            $(".app_selected .app_cont a").each(function(){
                $(".app_selected").find('#saimg' + s_row + '_' +s_col + '_' + rows + '_' + cols).remove();
                if($(this).find("img").attr("imgid") == 'img' + s_row + '_' +s_col + '_' + rows + '_' + cols) {
                    $(this).remove();
                    mfHeight();
                };
            });
            $('#imgEdit'+s_row+'_'+s_col+'_'+rows+'_'+cols).remove();
            $('.cube .row .free').removeClass('free');
            $('.cube .row .start').removeClass('start');
            $('.cube .row .disabled').removeClass('disabled');

        });

    }
    //弹出商品选择框
    $("#choooseProduct").button().click(function (){
        var imgs = $("#choooseProduct").parent("dd").find("img");
        var size = 4-imgs.length;
        if(size<1){
            toastr.warning("最多选择4个商品");
            return;
        }
        //计算可选择的商品
        art.dialog.open('${basePath}/queryThirdMobProductForGoods.htm?size='+size, {
            lock: true,
            width: '800px',
            height: '400px',
            title: '选择图片'
        });

        $("input[name='style']").change(function(){
            $(".app_selected .goodsmod").attr("styleclass",$("input[name='style']:checked").val());
            $(".app_selected .app-goods").attr("class","app-goods clearfix").addClass($("input[name='style']:checked").val());
        });
    });
    //保存选择的商品信息
    function saveChoooseProduct(id, img,no,name,title,price){
        for(var i=0;i<id.length;i++){
            var _li = '<li><a href="item/'+id[i]+'.html"><img id="" width="100%" src="'+img[i]+'">'+
                    '<div class="goods-info"><h3>'+name[i]+'</h3>'+
                    '<span class="good-price">￥'+price[i]+'</span><em class="good-buy-btn">立即购买</em></div></a></li>';
            var html = '<div class="img-sw"><img src="'+img[i]+'" width="50px;" height="50px;"/>';
            html = html + '<div style="display:none;">'
            +'<input type="hidden" name="href" value="item/'+id[i]+'.html">'
            +'<input type="hidden" name="src" value="'+img[i]+'">'
            +'<input type="hidden" name="name" value="'+name[i]+'">'
            +'<input type="hidden" name="price" value="￥'+price[i]+'">'
            +'</div><a class="rmv-gd" onclick="rmv(this)">X</a></div>';
            $(".gds-show").append(html);
            $(".app_selected .app-goods").append(_li);
        }

    }
    //删除选择的商品
    function rmv(t){
        var n = $(t).parents(".img-sw").index();
        $(t).parents(".img-sw").remove();
        $(".app_selected .app-goods li:eq("+n+")").remove();
    };
    //初始化xml
    function initXml(){
        $("#ip_cont").html("");
        new Transformation().setXml("${basePath}/${xmlFilePath}").setXslt("${basePath}/mobile_home_page/xsl/9gdemo.xsl")
                .setCallback(
                function (){
                    //拖拽
                    $("#ip_cont").sortable({
                        placeholder: "ui-state-highlight",
                        axis: "y",
                        //当用户停止排序和DOM的位置发生了变化
                        update:function(event,ui){
                            /* var sort = 0;
                            var itemArray = new Array();
                            $(".item").each(function(){
                                var _this = $(this);
                                itemArray[sort] = _this.prop("id");
                                sort++;
                            });
                            $.ajax({
                                url : "updateSort.htm",
                                data : {
                                    itemArray : itemArray
                                    },
                                async : false
                            }); */
                            if($(".app_selected").length > 0) {
                                $(".edit_area").css("top",$(".app_selected").offset().top-70);
                            };
                        }
                    });
                    $("#ip_cont").disableSelection();
                    //处理魔方高度
                    Array.ExistsSameValues = function(a1, a2) {
                        var exists = false;
                        if(a1 instanceof Array && a2 instanceof Array){
                            for (var i=0,iLen=a1.length; i<iLen; i++){
                                for (var j=0,jLen=a2.length; j<jLen; j++){
                                    if (a1[i]===a2[j]){
                                        return true;
                                    };
                                };
                            };
                        };
                        return exists;
                    };
                    $(".app_cont").each(function(){
                        var _this = $(this),
                                _hg = "",
                                arr1 = new Array(),
                                arr2 = new Array();
                        _this.find("img").each(function(){
                            _hg = parseFloat($(this).css("top")) + parseFloat($(this).height());
                            arr1.push(parseFloat(_hg));
                            arr2.push(parseFloat($(this).css("top")));
                        });
                        if(Array.ExistsSameValues(arr1, arr2) == true) {
                            var _top = Math.max.apply(null, arr1);
                            _this.height(_top);
                        } else {
                            _this.find("img").each(function(){
                                var _t = $(this),
                                        _tr = parseInt(_t.css("top"))/80+1,
                                        _rowspan = parseInt(_t.height()/80);
                                for(var r=_tr;r<_rowspan+_tr;r++) {
                                    _t.addClass("tr"+r+" ");
                                };
                            });
                            var arr3 = new Array();
                            for(var n=1;n<5;n++) {
                                if(_this.find(".tr"+n).length == 0) {
                                    arr3.push(n);
                                };
                            };
                            _this.height(320-80*arr3.length);
                            for(x in arr3) {
                                _this.find("img").each(function(){
                                    var _t = $(this),
                                            _n = _t.prop("class").substring(9,10),
                                            _top = parseInt(_t.css("top"));
                                    if(_n > arr3[x]) {
                                        _t.css("top",_top-80+"px");
                                    };
                                });
                            };
                        };
                    });

                    $(".plugin_list a").click(function(){
                        $(".edit_area").addClass("plugin_box");
                        $(".plugin_box").css("top",$(".app_selected").offset().top - 150 + "px");
                    });
                    //设置轮播效果
                    var rolls = $(".roll_adv");
                    for(var i=0;i<rolls.length;i++){
                        var rollId = "#"+$(rolls[i]).prop("id");
                        $(rollId +' .roll_banner,'+rollId+' .roll_banner img').css({'width':'320px','height':parseInt((320 * 35) / 72) + 'px'});
                        rollSwiper = new Swiper(rollId + ' .swiper-container',{
                            pagination: '.swiper-pagination',
                            loop:true,
                            grabCursor: true,
                            autoplay : 3000
                        });
                    }

                    //设置全屏轮播
                    var fullRolls = $(".fullRoll");
                    if(fullRolls.length>0){
                        //灰化添加模块按钮
                        /* var as = $(".plugin_list").find("a");
                        for(var i=0;i<as.length;i++){
                            $(as[i]).addClass("disabled-edit").removeAttr("onclick");
                        } */
                    }
                    for(var i=0;i<fullRolls.length;i++){
                        var sd = $(fullRolls[i]).find(".fullRollSD").val();
                        if(sd == "sh"){
                            fullSwiper = new Swiper('.full-swiper-container',{
                                mode:'horizontal',
                                pagination: '.full-pagination',
                                paginationClickable: true,
                                loop: true,
                                autoplay : 3000
                            });
                            $(fullRolls[i]).find(".full-pagination").show();
                            $(fullRolls[i]).find(".vtc-img").hide();
                            //$(".swiper-wrapper").css({"-webkit-transform":"translate3d(0px, 0px, 0px)","transform":"translate3d(0px, 0px, 0px)"});
                        } else {
                            fullSwiper = new Swiper('.full-swiper-container',{
                                mode:'vertical',
                                pagination: '.full-pagination',
                                paginationClickable: true,
                                loop: true,
                                autoplay : 3000
                            });
                            $(fullRolls[i]).find(".full-pagination").hide();
                            $(fullRolls[i]).find(".vtc-img").show();
                            //$(".swiper-wrapper").css({"-webkit-transform":"translate3d(0px, 0px, 0px)","transform":"translate3d(0px, 0px, 0px)"});
                        };

                    }

                    //设置text展示
                    var texts = $(".text");
                    for(var i=0;i<texts.length;i++){
                        var text = $(texts[i]);
                        var textid = text.prop("id");
                        var textCont = $("#textCont"+textid.substring(8)).text();
                        text.html(textCont);
                    }
                }
        ).transform('ip_cont');
    };

    function remove_sd(t) {
        var _src = $(t).parents(".add_slides").find(".view_img img").attr("src");
        if($(t).parents(".edit_area").attr("id") == "fullRollEdit") {
            $(t).parents(".add_slides").remove();
            fullSwiper.destroy();
            $('.app_selected .swiper-wrapper, .app_selected .full-pagination').html("");
            $(".edit_cont .add_slides").each(function(){
                var imgSrc = $(this).find(".view_img img").attr("src");
                $('.app_selected .swiper-wrapper').append('<div class="swiper-slide"><a href="javascript:;"><img alt="" src="'+imgSrc+'"></a></div>');
            });
            if($("input[value='sh']").prop("checked") == true) {
                fullSwiper = $('.app_selected .full-swiper-container').swiper({
                    autoplay: 3000,
                    loop: true,
                    mode: 'horizontal',
                    pagination: '.full-pagination',
                    paginationClickable: true
                });
            } else if ($("input[value='sv']").prop("checked") == true) {
                fullSwiper = $('.app_selected .full-swiper-container').swiper({
                    autoplay: 3000,
                    loop: true,
                    mode: 'vertical'
                });
            } else {
                fullSwiper = $('.app_selected .full-swiper-container').swiper({
                    autoplay: 3000,
                    loop: true,
                    mode: 'horizontal',
                    pagination: '.full-pagination',
                    paginationClickable: true
                });
            };
        } else {
            $(t).parents(".slide").remove();
            rollSwiper.destroy();
            $('.app_selected .swiper-wrapper').html("");
            $("#slides .slide").each(function(){
                var imgSrc = $(this).find(".view_img img").attr("src");
                $('.app_selected .swiper-wrapper').append('<div class="swiper-slide"><a href="javascript:;"><img alt="" src="'+imgSrc+'"></a></div>');
            });
            rollSwiper = $('.app_selected .swiper-container').swiper({
                autoplay: 3000,
                loop: true,
                mode: 'horizontal'
            });
        };
    };
    //保存所有模块
    function saveAllMod(){
        //var ip_cont = $("#ip_cont").html();
        $.ajax({
            url : "${basePath}/saveAllThirdMod.html",
            type: 'POST',
            data : {
                ipCont : $("#ip_cont").html(),
                homePageId : $("#homePageId").val()
            },
            async : false,
            success : function(){
              location.reload();
            }
        });
    }



    //富文本编辑器
    var editor;
    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="content"]', {
            cssPath : '${basePath}/js/kindeditor/plugins/code/prettify.css',
            uploadJson : '${basePath}/js/kindeditor/jsp/upload_json.jsp',
            fileManagerJson : '${basePath}/js/kindeditor/jsp/file_manager_json.jsp',
            allowFileManager : false,
            allowImageRemote : false,
            afterBlur : function() {
                this.sync();
            },
            items : [
                'source', '|', 'preview', '|', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'clearhtml', 'quickformat', '|',
                'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',
                'hr', 'emoticons', 'anchor', 'link', 'unlink'
            ]
        });
    });

    function refreshHtml(html){
        editor.clickToolbar('source');
        editor.html(html);
        editor.clickToolbar('source');

    }

    function clearHtml(){
        editor.html("");
    }

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

</script>
</body>
</html>