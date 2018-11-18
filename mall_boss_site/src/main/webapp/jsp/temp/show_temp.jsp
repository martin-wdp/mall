<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="<%=basePath %>stylesheet" href="css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
    <link href="<%=basePath %>css/vsStyle/jquery.treeTable.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<style>
    .main_cont{
        background: #fff;
        <%--overflow-y: visible;--%>
    }
    .hideElement{
        display: none;
    }
</style>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="container-fluid page_body">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">

            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">模板配置</h2>

                <div class="common_info order_details mt20">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" id="_tabl12" onclick="showTempBar()" class="active"><a  href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">页面导航</a></li>
                        <li role="presentation" id="_tab1" onclick="showTempCategory()"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">分类导航</a></li>
                        <li role="presentation" id="_tab2" onclick="showAdverPage(157)"><a href="#tab4" aria-controls="tab3" role="tab" data-toggle="tab">轮播大广告</a></li>
                        <%--2016-01-09 wuyanbo 屏蔽Boss后台轮播小广告的显示--%>
                        <li role="presentation" id="_tab3" onclick="showAdverPage(159)" class="hideElement"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">轮播小广告</a></li>
                        <%--2016-01-09 wuyanbo 屏蔽Boss后台页面广告的显示--%>
                        <li role="presentation" id="_tab4" onclick="showAdverPage(161)" class="hideElement"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">页面广告</a></li>
                        <li role="presentation" id="_tab5" onclick="showTempInfoType()"><a href="#tab6" aria-controls="tab6" role="tab" data-toggle="tab">新闻公告</a></li>
                        <li role="presentation" id="_tab6" onclick="showTempStorey()"><a href="#tab7" aria-controls="tab7" role="tab" data-toggle="tab">楼层设置</a></li>
                        <li role="presentation" id="_tab7" onclick="showHotSearch()"><a href="#tab8" aria-controls="tab8" role="tab" data-toggle="tab">热门搜索</a></li>
                        <li role="presentation" id="_tab8" onclick="showStoreyGoods()"><a href="#tab9" aria-controls="tab9" role="tab" data-toggle="tab">热销推荐</a></li>
                        <%--2016-01-09 wuyanbo 屏蔽Boss后台页面标签的显示--%>
                        <li role="presentation" id="_tab9" onclick="showTagList()" class="hideElement"><a href="#tab10" aria-controls="tab10" role="tab" data-toggle="tab">页面标签</a></li>
                        <%--2016-01-09 wuyanbo 屏蔽Boss后台活动的显示--%>
                        <li role="presentation" id="_tab10" onclick="showTempInfoOPTag()" class="hideElement"><a href="#tab11" aria-controls="tab11" role="tab" data-toggle="tab">活动</a></li>
                        <%--<li role="presentation" id="_tab11" onclick="shoMegawizard()"><a href="#tab12" aria-controls="tab12" role="tab" data-toggle="tab">页面说明</a></li>--%>
                    </ul>

                    <div class="tab-content">
                        <c:if test="${status!=null}">
                        <div class="data_ctrl_area mb20">
                            <button type="button" class="btn btn-info" onclick="window.location.href='selectTempView.htm?tempId=${temp.tempId}'">
                                返回页面配置
                            </button>
                        </div>
                        </c:if>
                        <div role="tabpanel" class="tab-pane active" id="tab1">
                            <div class="data_ctrl_area mb20">
                                <div class="data_ctrl_search pull-right"></div>
                                <div class="data_ctrl_brns pull-left">
                                    <button type="button" class="btn btn-info" onclick="$('#addPageNav').modal('show')">
                                        <i class="glyphicon glyphicon-plus"></i> 添加
                                    </button>
                                    <button id="deleteAllBar" type="button" class="btn btn-info">
                                        <i class="glyphicon glyphicon-trash"></i> 批量删除
                                    </button>
                                </div>
                                <div class="clr"></div>
                            </div>
                            <form id="delBarHtml" action="deleteTempBarAjxs.htm" method="post" enctype="multipart/form-data">
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" onclick="allunchecked(this,'barId')"></th>
                                        <th>导航名称</th>
                                        <th>导航地址</th>
                                        <th>排序</th>
                                        <th>是否启用</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>
                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <tbody id="barHtml">
                                    </tbody>
                                </table>
                            </form>

                            <div class="table_foot" id="barFootHtml">

                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab2">
                            <div class="common_form fldh p20">
                                <form id="setAdvertForm" class="form-horizontal">
                                    <input type="hidden" name="CSRFToken" value="${token}">
                                    <input type="hidden" name="tempId" value="${temp.tempId }"/>
                                    <input type="hidden" name="tempType" value="${temp.tempType }"/>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5">显示分类导航：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-5">
                                            <label class="radio-inline">
                                                <input type="radio" name="expFleid5" id="setShowTemp1" value="1" checked> 是
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="expFleid5" id="setShowTemp2" value="0"> 否
                                            </label>
                                        </div>
                                        <div class="col-sm-3">
                                            <a href="javascript:" class="xsfldh help_tips">
                                                <i class="icon iconfont">&#xe611;</i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5">分类级数：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-4">
                                            <select class="form-control" id="setTempLevel" name="goodClassifyLevel">
                                                <option value="3">3级</option>
                                            </select>
                                        </div>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-3">
                                            <button type="button" class="btn btn-default" onclick="doAjaxForCate(1,100)">查看分类导航</button>
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </div>
                                    <!--<div class="form-group">

                                        <label class="control-label col-sm-5">是否显示广告：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-5">
                                            <label class="radio-inline">
                                                <input type="radio" name="expFleid3" onclick="$('#set_Advert').show();" id="setAdvert1" value="1" checked> 是
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="expFleid3" onclick="$('#set_Advert').hide();" id="setAdvert2" value="0"> 否
                                            </label>
                                        </div>
                                        <div class="col-sm-3">
                                            <button type="button" id="set_Advert" class="btn btn-default" onclick="showClassfybarCate()">设置分类导航框广告</button>
                                        </div>
                                        <div class="col-sm-3"></div>

                                    </div>-->
                                    <div class="form-group">
                                        <label class="control-label col-sm-5">是否显示品牌：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-5">
                                            <label class="radio-inline">
                                                <input type="radio" name="expFleid4"
                                                       onclick="$('#set_TempCate').show();" id="setTempBrand1" value="1"
                                                       checked> 是
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="expFleid4" onclick="$('#set_TempCate').hide();" id="setTempBrand2" value="0"> 否
                                            </label>
                                        </div>
                                        <div class="col-sm-3">
                                            <button type="button" id="set_TempCate" class="btn btn-default" onclick="onclickShowClassifyBrand()">设置分类导航框品牌</button>
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-6 col-sm-14">
                                            <button type="button" onclick="updSetAdvertForm()" class="btn btn-primary">保存设置</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="set_box1" style="display: none">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addSetbox1').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="$('.set_box1').hide();$('.fldh').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回模板设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <table class="table table-striped table-hover" id="treeTable">
                                    <thead>
                                    <tr>
                                        <th width="10"><input type="checkbox" onclick="allunchecked(this,'classifyBarIds')"></th>
                                        <th>分类名称</th>
                                        <th>分类图片</th>
                                        <th></th>
                                        <th>是否启用</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="goodsCateHtml">
                                    <tr>
                                        <td colspan="6"><p class="text-center">加载中</p> </td>
                                    </tr>
                                    </tbody>
                                </table>

                            </div>
                            <div class="set_box2" style="display: none">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <div id="clasifycz1">
                                        <button type="button" class="btn btn-info" onclick="$('#addSlideAd').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('delAdverBatchForm','adverId','deletetempadverajax.htm',11)">
                                        <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="$('.set_box2').hide();$('.fldh').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回模板设置
                                        </button>
                                        </div>
                                        <div id="clasifycz2">
                                            <button type="button" class="btn btn-info" onclick="$('#addSlideAd').modal('show')">
                                                <i class="glyphicon glyphicon-plus"></i> 添加
                                            </button>
                                            <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('delAdverBatchForm','adverId','deletetempadverajax.htm',13)">
                                                <i class="glyphicon glyphicon-trash"></i> 批量删除
                                            </button>
                                            <button type="button" class="btn btn-info" onclick="  $('.set_box2').hide();$('.set_box1').show();">
                                                <i class="icon iconfont">&#xe614;</i> 返回分类导航
                                            </button>
                                        </div>
                                    </div>
                                    <div class="clr"></div>
                                </div>

                                <form id="delAdverBatchForm">
                                  <input type="hidden" name="CSRFToken" value="${token}"/>
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width="10"><input type="checkbox"  onclick="allunchecked(this,'adverId')"> </th>
                                        <th>图片</th>
                                        <th>链接地址</th>
                                        <th>排序</th>
                                        <th>是否启用</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="showClassifyBarCateHtml">
                                    </tbody>
                                </table>
                                </form>
                                <div class="table_foot" id="showClassifyBarCateHtmlFoot">

                                </div>
                            </div>
                            <div class="set_box3" style="display: none">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addSetbox3').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info"  onclick="doAjaxShowDeleteBatchConfirmAlert('delBrandBatchForm','trademarkId','deletechannelstoreytrademarkajax.htm',12)">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" id="classifybtn1"  class="btn btn-info" onclick="$('.set_box3').hide();$('.fldh').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回模板设置
                                        </button>
                                        <button type="button" id="classifybtn2"  class="btn btn-info" onclick="$('.set_box1').show();$('.set_box3').hide();">
                                            <i class="icon iconfont">&#xe614;</i> 返回分类导航
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <form id="delBrandBatchForm">
                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width="10"><input type="checkbox"  onclick="allunchecked(this,'trademarkId')"></th>
                                        <th>品牌</th>
                                        <th>显示类型</th>
                                        <th>排序</th>
                                        <th>是否启用</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="classifyBarBrandHtml">
                                    <tr>
                                        <td colspan="6"><p class="text-center">暂无可用数据</p> </td>
                                    </tr>
                                    </tbody>
                                </table>
                                </form>
                                <div class="table_foot" id="classifyBarBrandHtmlFot">

                                </div>
                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab3">

                            <div class="data_ctrl_area mb20">
                                <div class="data_ctrl_search pull-right"></div>
                                <div class="data_ctrl_brns pull-left">
                                    <button type="button" class="btn btn-info" onclick="$('#addSlideAd').modal('show')">
                                        <i class="glyphicon glyphicon-plus"></i> 添加
                                    </button>
                                    <button type="button" class="btn btn-info">
                                        <i class="glyphicon glyphicon-trash"></i> 批量删除
                                    </button>
                                </div>
                                <div class="clr"></div>
                            </div>
                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="allunchecked(this,'adverId')" ></th>
                                    <th>广告显示位置</th>
                                    <th>图片</th>
                                    <th>链接地址</th>
                                    <th>排序</th>
                                    <th>是否启用</th>
                                    <th width="150">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>首页广告</td>
                                    <td><img alt="" src="images/slide_ad.jpg" height="50"></td>
                                    <td>1</td>
                                    <td>1</td>
                                    <td><a href="javascript:"><span class="label label-success">是</span></a></td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="$('#addSlideAd').modal('show')">编辑</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>首页广告</td>
                                    <td><img alt="" src="images/slide_ad.jpg" height="50"></td>
                                    <td>item/3208</td>
                                    <td>1</td>
                                    <td><a href="javascript:"><span class="label label-success">是</span></a></td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="$('#addSlideAd').modal('show')">编辑</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>首页广告</td>
                                    <td><img alt="" src="images/slide_ad.jpg" height="50"></td>
                                    <td>item/3208</td>
                                    <td>1</td>
                                    <td><a href="javascript:"><span class="label label-success">是</span></a></td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="$('#addSlideAd').modal('show')">编辑</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>首页广告</td>
                                    <td><img alt="" src="images/slide_ad.jpg" height="50"></td>
                                    <td>item/3208</td>
                                    <td>1</td>
                                    <td><a href="javascript:"><span class="label label-success">是</span></a></td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="$('#addSlideAd').modal('show')">编辑</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="table_foot">
                                <div class="table_pagenav pull-right">
                                    <div class="meneame">
                                        <span class="disabled"> 上一页 </span>
                                        <span class="current"> 1 </span>
                                        <a href="#?page=2"> 2 </a>
                                        <a href="#?page=3"> 3 </a>
                                        <a href="#?page=4"> 4 </a>
                                        <a href="#?page=5"> 5 </a>
                                        ...
                                        <a href="#?page=199"> 199 </a>
                                        <a href="#?page=200"> 200 </a>
                                        <a href="#?page=2"> 下一页 </a>
                                    </div>
                                </div>
                                <div class="table_ctrl pull-left">
                                    <form role="form" class="form-horizontal">
                                        <label class="control-label col-sm-12">每页显示：</label>
                                        <div class="col-sm-12">
                                            <select class="form-control">
                                                <option>10</option>
                                                <option>20</option>
                                                <option>50</option>
                                                <option>100</option>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                                <div class="clr"></div>
                            </div>

                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab4">

                            <div class="data_ctrl_area mb20">
                                <div class="data_ctrl_search pull-right"></div>
                                <div class="data_ctrl_brns pull-left">
                                    <button type="button" class="btn btn-info" onclick="$('#addSlideAd').modal('show')">
                                        <i class="glyphicon glyphicon-plus"></i> 添加
                                    </button>
                                    <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('delAdverForm','adverId','deletetempadverajax.htm',2)">
                                        <i class="glyphicon glyphicon-trash"></i> 批量删除
                                    </button>
                                </div>
                                <div class="clr"></div>
                            </div>
                            <form id="delAdverForm">
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" onclick="allunchecked(this,'adverId')" ></th>
                                        <th>广告显示位置</th>
                                        <th>图片</th>
                                        <th>链接地址</th>
                                        <th>排序</th>
                                        <th>是否启用</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>

                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <tbody id="adverHtml">

                                    </tbody>

                                </table>
                            </form>
                            <div class="table_foot" id="adverFootHtml">
                                <div class="table_pagenav pull-right">
                                    <div class="meneame">
                                        <span class="disabled"> 上一页 </span>
                                        <span class="current"> 1 </span>
                                        <a href="#?page=2"> 2 </a>
                                        <a href="#?page=3"> 3 </a>
                                        <a href="#?page=4"> 4 </a>
                                        <a href="#?page=5"> 5 </a>
                                        ...
                                        <a href="#?page=199"> 199 </a>
                                        <a href="#?page=200"> 200 </a>
                                        <a href="#?page=2"> 下一页 </a>
                                    </div>
                                </div>
                                <div class="table_ctrl pull-left">
                                    <form role="form" class="form-horizontal">
                                        <label class="control-label col-sm-12">每页显示：</label>
                                        <div class="col-sm-12">
                                            <select class="form-control">
                                                <option>10</option>
                                                <option>20</option>
                                                <option>50</option>
                                                <option>100</option>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                                <div class="clr"></div>
                            </div>

                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab5">

                            <div class="data_ctrl_area mb20">
                                <div class="data_ctrl_search pull-right"></div>
                                <div class="data_ctrl_brns pull-left">
                                    <button type="button" class="btn btn-info" onclick="$('#addSlideAd').modal('show')">
                                        <i class="glyphicon glyphicon-plus"></i> 添加
                                    </button>
                                    <button type="button" class="btn btn-info">
                                        <i class="glyphicon glyphicon-trash"></i> 批量删除
                                    </button>
                                </div>
                                <div class="clr"></div>
                            </div>
                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox"></th>
                                    <th>广告显示位置</th>
                                    <th>图片</th>
                                    <th>链接地址</th>
                                    <th>排序</th>
                                    <th>是否启用</th>
                                    <th width="150">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>首页广告</td>
                                    <td><img alt="" src="images/slide_ad.jpg" height="50"></td>
                                    <td>1</td>
                                    <td>1</td>
                                    <td><a href="javascript:"><span class="label label-success">是</span></a></td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="$('#addSlideAd').modal('show')">编辑</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>首页广告</td>
                                    <td><img alt="" src="images/slide_ad.jpg" height="50"></td>
                                    <td>item/3208</td>
                                    <td>1</td>
                                    <td><a href="javascript:"><span class="label label-success">是</span></a></td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="$('#addSlideAd').modal('show')">编辑</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>首页广告</td>
                                    <td><img alt="" src="images/slide_ad.jpg" height="50"></td>
                                    <td>item/3208</td>
                                    <td>1</td>
                                    <td><a href="javascript:"><span class="label label-success">是</span></a></td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="$('#addSlideAd').modal('show')">编辑</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>首页广告</td>
                                    <td><img alt="" src="images/slide_ad.jpg" height="50"></td>
                                    <td>item/3208</td>
                                    <td>1</td>
                                    <td><a href="javascript:"><span class="label label-success">是</span></a></td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="$('#addSlideAd').modal('show')">编辑</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="table_foot">
                                <div class="table_pagenav pull-right">
                                    <div class="meneame">
                                        <span class="disabled"> 上一页 </span>
                                        <span class="current"> 1 </span>
                                        <a href="#?page=2"> 2 </a>
                                        <a href="#?page=3"> 3 </a>
                                        <a href="#?page=4"> 4 </a>
                                        <a href="#?page=5"> 5 </a>
                                        ...
                                        <a href="#?page=199"> 199 </a>
                                        <a href="#?page=200"> 200 </a>
                                        <a href="#?page=2"> 下一页 </a>
                                    </div>
                                </div>
                                <div class="table_ctrl pull-left">
                                    <form role="form" class="form-horizontal">
                                        <label class="control-label col-sm-12">每页显示：</label>
                                        <div class="col-sm-12">
                                            <select class="form-control">
                                                <option>10</option>
                                                <option>20</option>
                                                <option>50</option>
                                                <option>100</option>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                                <div class="clr"></div>
                            </div>

                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab6">

                            <div class="alert alert-warning alert-dismissible fade in" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                                <strong>注意！</strong> 选择栏目后，将会自动展示该栏目下最新的文章。
                            </div>

                            <div class="common_form">
                                <form class="form-horizontal" id="updateInfoType">
                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <input type="hidden" name="tempId" value="${temp.tempId}">
                                    <input type="hidden" name="tempType" value="${temp.tempType}">
                                    <div class="form-group">
                                        <label class="control-label col-sm-5">文章栏目：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-5">
                                            <select class="form-control" id="infoTypeId" name="expFleid1" >
                                                <c:forEach var="infoType" items="${infoTypeList }">
                                                    <option value="${infoType.infoTypeId }">${infoType.name }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5">模块名称：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <input type="text" id="infoTypeName" name="expFleid2" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-6 col-sm-10">
                                            <button type="button" onclick="updateInfoType()" class="btn btn-primary">保存设置</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab7">

                            <div class="floor_set">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addFloor').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('lcForm','storeyId','deletetempstoreyajax.htm',6)">
                                            <i class="glyphicon glyphicon-trash" ></i> 批量删除
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <form id="lcForm">
                                    <table class="table table-striped table-hover">

                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" onclick="allunchecked(this,'storeyId')"> </th>
                                            <th>楼层名称</th>
                                            <th>楼层图片</th>
                                            <th>楼层数</th>
                                            <th>是否启用</th>
                                            <th width="250">操作</th>
                                        </tr>
                                        </thead>


                                        <input type="hidden" name="CSRFToken" value="${token}"/>
                                        <tbody id="showTempStoreyHtml">

                                        </tbody>

                                    </table>
                                </form>
                                <div class="table_foot" id="showTempStoreyFootHtml">

                                </div>
                            </div>

                            <div class="floor_good_set" style="display:none;">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="goAddFloorGoods()">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('floorGoodsForm','storeyGoodsId','deletechannelstoreygoodsajax.htm',7)">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="$('.floor_good_set').hide();$('.floor_set').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回楼层设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <form id="floorGoodsForm">
                                    <table class="table table-striped table-hover">
                                        <input type="hidden" name="CSRFToken" value="${token}"/>
                                        <thead>
                                        <tr>
                                            <th width="120"><input type="checkbox"  onclick="allunchecked(this,'storeyGoodsId')"></th>
                                            <%--<th>商品类型</th>--%>
                                            <th>货品图片</th>
                                            <th>货品编号</th>
                                            <th>排序</th>
                                            <th width="150">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="channelStoreyGoodsHtml">

                                        </tbody>
                                    </table>
                                </form>
                                <div class="table_foot" id="channelStoreyGoodsFootHtml">

                                </div>
                            </div>

                            <div class="floor_ad_set" style="display:none;">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addSlideAd').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('delFloorAdverForm','adverId','deletetempadverajax.htm',8)">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="$('.floor_ad_set').hide();$('.floor_set').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回楼层设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <form id="delFloorAdverForm">
                                    <input type="hidden" name="CSRFToken" value="${token}">
                                    <table class="table table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th width="10"><input type="checkbox" onclick="allunchecked(this,'adverId')"></th>
                                            <th>图片</th>
                                            <th>标题</th>
                                            <th>副标题</th>
                                            <th>链接地址</th>
                                            <th>排序</th>
                                            <th>是否启用</th>
                                            <th width="150">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="floorAdverHtml">

                                        </tbody>
                                    </table>
                                </form>
                                <div class="table_foot" id="floorAdverFootHtml">

                                </div>
                            </div>

                            <div class="floor_brand_set" style="display:none;">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addFloorBrands').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('delTrademark','trademarkId','deletechannelstoreytrademarkajax.htm',9)">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="$('.floor_brand_set').hide();$('.floor_set').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回楼层设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <form id="delTrademark">
                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <table class="table table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th width="10"><input type="checkbox" onclick="allunchecked(this,'trademarkId')"></th>
                                            <th>品牌名称</th>
                                            <th>LOGO</th>
                                            <th>排序</th>
                                            <th>是否启用</th>
                                            <th width="150">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="storeyTrademarkHtml">
                                        <tr>
                                            <td colspan="6"><p class="text-center">暂无可用数据</p> </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class="table_foot"  id="storeyTrademarkFootHtml">

                                </div>
                            </div>

                            <div class="floor_label_set" style="display:none;">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addFloorLabel').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="$('.floor_label_set').hide();$('.floor_set').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回楼层设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width="10"><input type="checkbox"></th>
                                        <th>标签名称</th>
                                        <th>排序</th>
                                        <th>是否启用</th>
                                        <th width="250">操作1</th>
                                    </tr>
                                    </thead>
                                    <tbody >
                                    <tr>
                                        <td><input type="checkbox"></td>
                                        <td>促销活动</td>
                                        <td>1</td>
                                        <td><a href="javascript:"><span class="label label-success">是</span></a></td>
                                        <td>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default" onclick="$('.floor_label_good_set').show();$('.floor_label_set').hide()">设置标签商品</button>
                                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="javascript:" onclick="$('.floor_label_ad_set').show();$('.floor_label_set').hide()">设置标签广告</a></li>
                                                    <li><a href="javascript:" onclick="$('.floor_label_brand_set').show();$('.floor_label_set').hide()">设置标签品牌</a></li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="table_foot">

                                </div>
                            </div>

                            <div class="floor_label_ad_set" style="display:none;">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addSlideAd').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="$('.floor_label_ad_set').hide();$('.floor_label_set').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回标签设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width="10"><input type="checkbox"></th>
                                        <th>图片</th>
                                        <th>标题</th>
                                        <th>副标题</th>
                                        <th>链接地址</th>
                                        <th>排序</th>
                                        <th>是否启用</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody >
                                    <tr>
                                        <td colspan="8"><p class="text-center">暂无可用数据</p> </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="table_foot" >
                                    <div class="table_pagenav pull-right">
                                        <div class="meneame">
                                            <span class="disabled"> 上一页 </span>
                                            <span class="current"> 1 </span>
                                            <a href="#?page=2"> 2 </a>
                                            <a href="#?page=3"> 3 </a>
                                            <a href="#?page=4"> 4 </a>
                                            <a href="#?page=5"> 5 </a>
                                            ...
                                            <a href="#?page=199"> 199 </a>
                                            <a href="#?page=200"> 200 </a>
                                            <a href="#?page=2"> 下一页 </a>
                                        </div>
                                    </div>
                                    <div class="table_ctrl pull-left">
                                        <form role="form" class="form-horizontal">
                                            <label class="control-label col-sm-12">每页显示：</label>
                                            <div class="col-sm-12">
                                                <select class="form-control">
                                                    <option>10</option>
                                                    <option>20</option>
                                                    <option>50</option>
                                                    <option>100</option>
                                                </select>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                            </div>

                            <div class="floor_label_brand_set" style="display:none;">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addSlideAd').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="$('.floor_label_brand_set').hide();$('.floor_label_set').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回标签设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width="10"><input type="checkbox"></th>
                                        <th>品牌名称</th>
                                        <th>LOGO</th>
                                        <th>排序</th>
                                        <th>是否启用</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td colspan="6"><p class="text-center">暂无可用数据</p> </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="table_foot">
                                    <div class="table_pagenav pull-right">
                                        <div class="meneame">
                                            <span class="disabled"> 上一页 </span>
                                            <span class="current"> 1 </span>
                                            <a href="#?page=2"> 2 </a>
                                            <a href="#?page=3"> 3 </a>
                                            <a href="#?page=4"> 4 </a>
                                            <a href="#?page=5"> 5 </a>
                                            ...
                                            <a href="#?page=199"> 199 </a>
                                            <a href="#?page=200"> 200 </a>
                                            <a href="#?page=2"> 下一页 </a>
                                        </div>
                                    </div>
                                    <div class="table_ctrl pull-left">
                                        <form role="form" class="form-horizontal">
                                            <label class="control-label col-sm-12">每页显示：</label>
                                            <div class="col-sm-12">
                                                <select class="form-control">
                                                    <option>10</option>
                                                    <option>20</option>
                                                    <option>50</option>
                                                    <option>100</option>
                                                </select>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab8">

                            <div class="data_ctrl_area mb20">
                                <div class="data_ctrl_search pull-right"></div>
                                <div class="data_ctrl_brns pull-left">
                                    <button type="button" class="btn btn-info" onclick="$('#addHotSearch').modal('show')">
                                        <i class="glyphicon glyphicon-plus"></i> 添加
                                    </button>
                                    <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('deleteBatchHot','hots','batchdelhotajax.htm',3)">
                                        <i class="glyphicon glyphicon-trash"></i> 批量删除
                                    </button>
                                </div>
                                <div class="clr"></div>
                            </div>
                            <form id="deleteBatchHot">
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" onclick="allunchecked(this,'hots')"></th>
                                        <th>热门搜索</th>
                                        <th>排序</th>
                                        <th>修改时间</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>

                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <tbody id="hotHtml" >

                                    </tbody>

                                </table>
                            </form>
                            <div class="table_foot" id="hotFootHtml">
                                <div class="table_pagenav pull-right">
                                    <div class="meneame">
                                        <span class="disabled"> 上一页 </span>
                                        <span class="current"> 1 </span>
                                        <a href="#?page=2"> 2 </a>
                                        <a href="#?page=3"> 3 </a>
                                        <a href="#?page=4"> 4 </a>
                                        <a href="#?page=5"> 5 </a>
                                        ...
                                        <a href="#?page=199"> 199 </a>
                                        <a href="#?page=200"> 200 </a>
                                        <a href="#?page=2"> 下一页 </a>
                                    </div>
                                </div>
                                <div class="table_ctrl pull-left">
                                    <form role="form" class="form-horizontal">
                                        <label class="control-label col-sm-12">每页显示：</label>
                                        <div class="col-sm-12">
                                            <select class="form-control">
                                                <option>10</option>
                                                <option>20</option>
                                                <option>50</option>
                                                <option>100</option>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                                <div class="clr"></div>
                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab9">

                            <div class="data_ctrl_area mb20">
                                <div class="data_ctrl_search pull-right"></div>
                                <div class="data_ctrl_brns pull-left">
                                    <button type="button" class="btn btn-info" onclick="$('#addHotGoods').modal('show')">
                                        <i class="glyphicon glyphicon-plus"></i> 添加
                                    </button>
                                    <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('delStoreyByFormId','storeyGoodsIds','deleteChannelGoodsAjax.htm?CSRFToken=${token}',5)">
                                        <i class="glyphicon glyphicon-trash"></i> 批量删除
                                    </button>
                                </div>
                                <div class="clr"></div>
                            </div>
                            <form id="delStoreyByFormId">
                                <table class="table table-striped table-hover">

                                    <thead>
                                    <tr>
                                        <th width="120"><input type="checkbox" onclick="allunchecked(this,'storeyGoodsIds')"></th>
                                        <th>货品图片</th>
                                        <th>货品编号</th>
                                        <th>排序</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="storeyGoodsHtml">
                                    <tr>
                                        <td colspan="8"><p class="text-center">暂无可用数据</p> </td>
                                    </tr>
                                    </tbody>

                                </table>
                            </form>

                            <div class="table_foot" id="storeyGoodsFootHtml">

                            </div>

                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab10">

                            <div class="floor_label_set">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addFloorLabel').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info"  onclick="doAjaxShowDeleteBatchConfirmAlert('delTagForm','tagId','deletepagetagajax.htm',14)">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info" id="showTagBtn" onclick="returnFoolrHtml()">
                                            <i class="icon iconfont">&#xe614;</i> 返回楼层设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <form id="delTagForm">
                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width="10"><input type="checkbox"  onclick="allunchecked(this,'tagId')"></th>
                                        <th>标签名称</th>
                                        <th>排序</th>
                                        <th>是否启用</th>
                                        <th width="250">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tagHtml">

                                    </tbody>
                                </table>
                                </form>
                                <div class="table_foot"  id="tagFootHtml">

                                </div>
                            </div>

                            <div class="floor_label_good_set" style="display:none;">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addHotGoods').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('tagGoodsList','storeyGoodsIds','deleteChannelGoodsAjax.htm?CSRFToken=${token}',5)">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info"
                                                onclick=" $('.floor_label_good_set').hide();$('.floor_label_set').show()">
                                            <i class="icon iconfont">&#xe614;</i> 返回标签设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <form id="tagGoodsList">
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width="120"><input type="checkbox" onclick="allunchecked(this,'storeyGoodsIds')"></th>
                                        <th>货品图片</th>
                                        <th>货品编号</th>
                                        <th>排序</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>

                                        <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <tbody id="tagGoodsHtml">
                                    <tr>
                                        <td colspan="8"><p class="text-center">暂无可用数据</p> </td>
                                    </tr>
                                    </tbody>

                                </table>
                                </form>
                                <div class="table_foot" id="tagGoodsFootHtml">

                                </div>
                            </div>

                            <div class="floor_label_ad_set" style="display:none;">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addSlideAd').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('delTagAdverBatch','adverId','deletetempadverajax.htm',11)">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="$('.floor_label_ad_set').hide();$('.floor_label_set').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回标签设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <form id="delTagAdverBatch">
                                <input type="hidden" name="CSRFToken" value="${token}"/>
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width="10"><input type="checkbox" onclick="allunchecked(this,'adverId')"></th>
                                        <th>图片</th>
                                        <th>标题</th>
                                        <th>副标题</th>
                                        <th>链接地址</th>
                                        <th>排序</th>
                                        <th>是否启用</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tagAdverHtml">
                                    <tr>
                                        <td colspan="8"><p class="text-center">暂无可用数据</p> </td>
                                    </tr>
                                    </tbody>
                                </table>
                                </form>
                                <div class="table_foot" id="tagAdverFootHtml">
                                    <div class="table_pagenav pull-right">
                                        <div class="meneame">
                                            <span class="disabled"> 上一页 </span>
                                            <span class="current"> 1 </span>
                                            <a href="#?page=2"> 2 </a>
                                            <a href="#?page=3"> 3 </a>
                                            <a href="#?page=4"> 4 </a>
                                            <a href="#?page=5"> 5 </a>
                                            ...
                                            <a href="#?page=199"> 199 </a>
                                            <a href="#?page=200"> 200 </a>
                                            <a href="#?page=2"> 下一页 </a>
                                        </div>
                                    </div>
                                    <div class="table_ctrl pull-left">
                                        <form role="form" class="form-horizontal">
                                            <label class="control-label col-sm-12">每页显示：</label>
                                            <div class="col-sm-12">
                                                <select class="form-control">
                                                    <option>10</option>
                                                    <option>20</option>
                                                    <option>50</option>
                                                    <option>100</option>
                                                </select>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                            </div>

                            <div class="floor_label_brand_set" style="display:none;">
                                <div class="data_ctrl_area mb20">
                                    <div class="data_ctrl_search pull-right"></div>
                                    <div class="data_ctrl_brns pull-left">
                                        <button type="button" class="btn btn-info" onclick="$('#addFloorBrands').modal('show')">
                                            <i class="glyphicon glyphicon-plus"></i> 添加
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('delTagTrademark','trademarkId','deletechannelstoreytrademarkajax.htm',9)">
                                            <i class="glyphicon glyphicon-trash"></i> 批量删除
                                        </button>
                                        <button type="button" class="btn btn-info" onclick="$('.floor_label_brand_set').hide();$('.floor_label_set').show();">
                                            <i class="icon iconfont">&#xe614;</i> 返回标签设置
                                        </button>
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <form id="delTagTrademark">
                               <input type="hidden" name="CSRFToken" value="${token}"/>
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width="10"><input type="checkbox"  onclick="allunchecked(this,'trademarkId')"></th>
                                        <th>品牌名称</th>
                                        <th>LOGO</th>
                                        <th>排序</th>
                                        <th>是否启用</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tagBrandHtml">
                                    <tr>
                                        <td colspan="6"><p class="text-center">暂无可用数据</p> </td>
                                    </tr>
                                    </tbody>
                                </table>
                                </form>
                                <div class="table_foot" id="tagBrandFootHtml">
                                </div>
                            </div>

                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab11">
                            <div class="alert alert-warning alert-dismissible fade in" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                                <strong>注意！</strong> 选择单页标签后，将会自动展示该标签下最新的单页。
                            </div>

                            <div class="common_form">
                                <form class="form-horizontal" id="tempInfoOPTagForm">
                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <input type="hidden" name="tempId" value="${temp.tempId}">
                                    <input type="hidden" name="tempType" value="${temp.tempType}">
                                    <div class="form-group">
                                        <label class="control-label col-sm-5">单页标签：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-5">
                                            <select class="form-control" id="infoOPTagId" name="standby4" >
                                                <c:forEach var="opTag" items="${opTagList }">
                                                    <option value="${opTag.infoopTagId }" >${opTag.name }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5">活动名称：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <input type="text" id="infoOPTagName" name="standby5"  class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-6 col-sm-10">
                                            <button onclick="updateTempInfoOPTag()" type="button" class="btn btn-primary">保存设置</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane" id="tab12">

                            <div class="data_ctrl_area mb20">
                                <div class="data_ctrl_search pull-right"></div>
                                <div class="data_ctrl_brns pull-left">
                                    <button type="button" class="btn btn-info" onclick="$('#addPageIntro').modal('show')">
                                        <i class="glyphicon glyphicon-plus"></i> 添加
                                    </button>
                                    <button type="button" class="btn btn-info"  onclick="doAjaxShowDeleteBatchConfirmAlert('delMegForm','id','delectmegawizardajax.htm',4)">
                                        <i class="glyphicon glyphicon-trash"></i> 批量删除
                                    </button>
                                </div>
                                <div class="clr"></div>
                            </div>
                            <form id="delMegForm">
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" onclick="allunchecked(this,'id')"></th>
                                        <th>页面类型</th>
                                        <th>页面说明名称</th>
                                        <th>创建时间</th>
                                        <th width="150">操作</th>
                                    </tr>
                                    </thead>

                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <tbody id="megawizardHtml">


                                    </tbody>

                                </table>
                            </form>
                            <div class="table_foot" id="msgFootHtml">

                            </div>

                        </div>

                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addPageNav"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span onclick="cls()" aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 id="up_bar_title" class="modal-title">添加页面导航</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal"  id="subForm_Bar">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="tempId" value="${temp.tempId}"/>
                    <input type="hidden" name="barId" id="up_barId" value=""/>
                    <input type="hidden" name="openChannel"  value="0"/>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span style="color: red;">*</span>导航名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" id="up_barName" name="barName" class="form-control  required specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">导航地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" id="up_barUrl" name="barUrl"  class="form-control   specstr">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="dhurl help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">描述信息：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text"  id="up_des_bar" name="des" class="form-control   specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span style="color: red;">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="text"  id="up_barSort" name="barSort" class="form-control w100  digits required">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus" checked="checked" value="1" id="up_userStatus1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="usedStatus" value="0" id="up_userStatus0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="subBarButton"   onclick="submitBar()" class="btn btn-primary">确定</button>
                <button type="button" id="barRest" onclick="cls()" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" tabindex="99" id="addSetbox1"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" onclick="clsClassifyBar()" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="addClasifyBarTitle">添加分类导航</h4>
            </div>
            <div class="modal-body">
                <form id="goodsCateForm" method="post">
                <div class="form-horizontal" >
                    <input type="hidden" name="CSRFToken" value="${token}">
                    <input type="hidden" name="classifyBarId" id="up_classifyBarId" value=""/>
                    <input type="hidden" name="tempId" id="up_tempId" value="${temp.tempId }"/>
                    <input type="hidden" name="goodsCatId" id="isdefi" value="0" />
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>分类导航名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" id="up_classifyName" name="name" class="form-control required specstr" >
                        </div>
                    </div>
                    <div class="form-group">
                    <label class="control-label col-sm-6"><span style="color: red;">*</span>广告图片：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-12">
                        <img alt="" id="goodsCatePath_y" src="<%=basePath %>images/default_head.jpg" height="50"> <input type="button"   onclick="saveImg(4)" value="选择"/>
                        <input name="temp2" id="goodsCatePath" type="hidden" value="" class="required">
                    </div>
                    </div>
                   <input type="hidden" name="zdyspfl" id="zdyspfl1" value="1" checked>
                    <div class="form-group zdyfl_yes">
                        <label class="control-label col-sm-6"> <span style="color: red;">*</span>商品分类1：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-6">
                            <input type="hidden" value="" id="goodsCateName1" name="barCateNames">
                            <select class="form-control" name="barCateIds"  onchange="changeGoodsCateName(1)" id="goodsCate1">
                                <c:forEach var="cate" items="${cate }">
                                    <option value="${cate.catId }" >${cate.catName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group zdyfl_yes">
                        <label class="control-label col-sm-6">商品分类2：</label>
                        <input type="hidden" value="" id="goodsCateName2"   name="barCateNames">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-6">
                            <select class="form-control" id="goodsCate2" name="barCateIds" onchange="changeGoodsCateName(2)">
                                <option value="-1">请选择</option>
                                <c:forEach var="cate" items="${cate }">
                                    <option value="${cate.catId }" >${cate.catName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group zdyfl_yes">
                        <label class="control-label col-sm-6">商品分类3：</label>
                        <input type="hidden" value="" id="goodsCateName3"  name="barCateNames">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-6">

                            <select class="form-control"  name="barCateIds" id="goodsCate3"    onchange="changeGoodsCateName(3)">
                                <option value="-1">请选择</option>
                                <c:forEach var="cate" items="${cate }">
                                    <option value="${cate.catId }" >${cate.catName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group zdyfl_yes">
                        <label class="control-label col-sm-6">商品分类4：</label>
                        <input type="hidden" value="" name="barCateNames"  >
                        <div class="col-sm-1"></div>
                        <div class="col-sm-6">
                            <select class="form-control"  name="barCateIds" onchange="changeGoodsCateName(4)"  id="goodsCate4">
                                <option value="-1">请选择</option>
                                <c:forEach var="cate" items="${cate }">
                                    <option value="${cate.catId }" >${cate.catName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6" >快捷分类1：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input id="barQuickName1" readonly="readonly" class="form-control" name="barQuickNames" value="" type="text">
                        </div>
                    </div>
                    <div class="form-group zdyfl_yes">
                        <label class="control-label col-sm-6" ></label>
                        <input id="barQuickIds1" name="barQuickIds" value="0" type="hidden">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <div class="area_choose">
                                <ul id="kjfl1" class="ztree"></ul>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6" >快捷分类2：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input id="barQuickName2" readonly="readonly" class="form-control" name="barQuickNames" value="" type="text">
                        </div>
                    </div>
                    <div class="form-group zdyfl_yes">
                        <label class="control-label col-sm-6"></label>
                        <input id="barQuickIds2" name="barQuickIds" value="0" type="hidden">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <div class="area_choose">
                                <ul id="kjfl2" class="ztree"></ul>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6" >快捷分类3：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input id="barQuickName3" readonly="readonly" class="form-control" name="barQuickNames" value="" type="text">
                        </div>
                    </div>
                    <div class="form-group zdyfl_yes">
                        <label class="control-label col-sm-6">快捷分类3：</label>
                        <input id="barQuickIds3" name="barQuickIds" value="0" type="hidden">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <div class="area_choose">
                                <ul id="kjfl3" class="ztree"></ul>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6" >快捷分类4：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input id="barQuickName4" readonly="readonly" class="form-control" name="barQuickNames" value="" type="text">
                        </div>
                    </div>
                    <div class="form-group zdyfl_yes">
                        <label class="control-label col-sm-6">快捷分类4：</label>
                        <input id="barQuickIds4" name="barQuickIds" value="0" type="hidden">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <div class="area_choose">
                                <ul id="kjfl4" class="ztree"></ul>
                            </div>
                        </div>
                    </div>

                    <div class="form-group zdyfl_no" style="display: none;">
                        <label class="control-label col-sm-6">自定义导航名称1：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="hidden"  name="barCIds" id="barCIds1" value="-1">
                            <input type="text" id="barCNames1" name="barCNames" class="form-control">
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3"><label class="control-label">自定义URL：</label></div>
                        <div class="col-sm-6">
                            <input type="text"  id="bartemps1" name="bartemps"  class="form-control">
                        </div>
                    </div>
                    <div class="form-group zdyfl_no" style="display: none;">
                        <label class="control-label col-sm-6">自定义导航名称2：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="hidden" name="barCIds" id="barCIds2" value="-1">
                            <input type="text"  id="barCNames2" name="barCNames" class="form-control">
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3"><label class="control-label">自定义URL：</label></div>
                        <div class="col-sm-6">
                            <input type="text"   id="bartemps2" name="bartemps" class="form-control">
                        </div>
                    </div>
                    <div class="form-group zdyfl_no" style="display: none;">
                        <label class="control-label col-sm-6">自定义导航名称3：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="hidden" name="barCIds" id="barCIds3" value="-1">
                            <input type="text"  id="barCNames3" name="barCNames" class="form-control">
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3"><label class="control-label">自定义URL：</label></div>
                        <div class="col-sm-6">
                            <input type="text"   id="bartemps3" name="bartemps" class="form-control">
                        </div>
                    </div>
                    <div class="form-group zdyfl_no" style="display: none;">
                        <label class="control-label col-sm-6">自定义导航名称4：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="hidden"  name="barCIds" id="barCIds4" value="-1">
                            <input type="text"  id="barCNames4" name="barCNames" class="form-control">
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3"><label class="control-label">自定义URL：</label></div>
                        <div class="col-sm-6">
                            <input type="text"   id="bartemps4" name="bartemps" class="form-control">
                        </div>
                    </div>

                    <div class="form-group zdyfl_no" style="display: none;">
                        <label class="control-label col-sm-6">自定义快捷分类1：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="hidden"  name="barQIds" id="barQId1" value="0">
                            <input type="text" id="barQNames1" name="barQNames"  class="form-control">
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3"><label class="control-label">自定义URL：</label></div>
                        <div class="col-sm-6">

                            <input type="text" id="barQTemps1" name="barQTemps" class="form-control">
                        </div>
                    </div>

                    <div class="form-group zdyfl_no" style="display: none;">
                        <label class="control-label col-sm-6">自定义快捷分类2：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="hidden" name="barQIds" id="barQId2" value="0">
                            <input type="text" id="barQNames2" name="barQNames"  class="form-control">
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3"><label class="control-label">自定义URL：</label></div>
                        <div class="col-sm-6">
                            <input type="text"   id="barQTemps2" name="barQTemps" class="form-control">
                        </div>
                    </div>
                    <div class="form-group zdyfl_no" style="display: none;">
                        <label class="control-label col-sm-6">自定义快捷分类3：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="hidden"  name="barQIds" id="barQId3" value="0">
                            <input type="text" id="barQNames3" name="barQNames" class="form-control">
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3"><label class="control-label">自定义URL：</label></div>
                        <div class="col-sm-6">
                            <input type="text"   id="barQTemps3" name="barQTemps" class="form-control">
                        </div>
                    </div>
                    <div class="form-group zdyfl_no" style="display: none;">
                        <label class="control-label col-sm-6">自定义快捷分类4：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="hidden" name="barQIds" id="barQId4" value="0">
                            <input type="text" id="barQNames4" name="barQNames" class="form-control">
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3"><label class="control-label">自定义URL：</label></div>
                        <div class="col-sm-6">
                            <input type="text"  id="barQTemps4"  name="barQTemps" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="text" id="classifySort" name="sort" class="form-control w100 required digits">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否显示：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isUsing" checked="checked" id="inlineRadio1" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isUsing"  id="inlineRadio2" value="0"> 否
                            </label>
                        </div>
                    </div>
                </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="subClasifybarBtn" onclick="submitgoodsCate()" class="btn btn-primary">确定</button>
                <button type="button" id="clsClassifyBarBtn" onclick="clsClassifyBar()" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="addSetbox_paternity"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" onclick="clsClassifyBar()" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="paternity_addClasifyBarTitle">添加分类导航</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="paternity_goodsCateForm">
                    <input type="hidden" name="CSRFToken" value="${token}">
                    <input type="hidden" name="classifyBarId" id="paternity_classifyBarId" value=""/>
                    <input type="hidden" name="tempId" id="paternity_tempId" value="${temp.tempId }"/>
                    <input type="hidden" name="parentId" id="up_parentId">
                    <input type="hidden" name="goodsCatId" id="up_parGoodsCatId">
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>分类导航名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" id="up2_classifyName" name="name" class="form-control required specstr" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="text" id="paternity_classifySort" name="sort" class="form-control w100 required digits">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>

                    <div class="form-group zdyfl_yes" id="parentGoodsCate">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>商品分类：</label>
                        <input type="hidden" value="" name="barCateNames"  >
                        <div class="col-sm-1"></div>
                        <div class="col-sm-6">
                            <select class="form-control"  onchange="changePaternGoodsCateName()"  id="up_goodsCatId">
                                <option value="0" >请选择</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6">是否显示：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isUsing" checked="checked" id="paternity_inlineRadio1" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isUsing"  id="paternity_inlineRadio2" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="paternity_subClasifybarBtn" onclick="suboodsCatePater()" class="btn btn-primary">确定</button>
                <button type="button" id="paternity_clsClassifyBarBtn" onclick="clsClassifyBar()" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addSetbox2"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加分类导航广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="">
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告副标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" width="160px;" height="160px" src="images/qpmall_logo.jpg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告链接地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control w100">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions" value="option1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions" value="option2"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <textarea rows="5" class="form-control"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addSetbox3"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  onclick="clsClassifyFuntion()" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="classfiyBrandTitle">添加分类导航品牌</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="classfiyBrandAddForm">
                    <input type="hidden" id="classifyTrademarkId"  name="channelTrademarkId">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="tempId" value="${temp.tempId}">
                    <input type="hidden" name="temp2" id="up_temp2" value="1">
                    <input type="hidden" name="temp1" id="up_temp5" value="3128">
                    <div class="form-group">
                        <label class="control-label col-sm-6">展示类型：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <!--<label class="radio-inline">
                                <input type="radio" name="showType" id="zslx1" value="0" checked> 文字
                            </label>-->
                            <label class="radio-inline">
                                <input type="radio" name="showType" id="zslx2" value="1"> 图片
                            </label>
                        </div>
                    </div>

                    <div class="form-group zslx_yes">
                        <label class="control-label col-sm-6">  <span style="color: red;">*</span>品牌标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                          <input type="text" name="title" id="up_classifyCate" class="form-control">
                        </div>
                    </div>
                    <div class="form-group zslx_no">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>选择品牌：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                                <select class="form-control" data-live-search="true"  name="trademarkName" id="classifyTrademarkBrand" onchange="changeClassifyTrademarkBrand(this)">
                                    <c:forEach items="${brand}" var="b" varStatus="sta">
                                        <option  value="${b.brandName}">${b.brandName} </option>
                                    </c:forEach>
                                </select>
                        </div>
                    </div>
                    <div class="form-group zslx_no">
                        <label class="control-label col-sm-6">预览品牌图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" id="classifyYLSrc" src="images/qpmall_logo.jpg">
                            <input type="hidden" name="logoSrc" id="trademarkClassify" value="">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>链接地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text"  id="up_url" name="url"  class="form-control required url">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" name="sort" id="up_trademarkSort" class="form-control w100 required integer">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" name="userStatus" checked id="inlineRadio7" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="userStatus" id="inlineRadio8" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="subClassifyTradermark()" class="btn btn-primary">确定</button>
                <button type="button" id="clsClassifyBrandbtn" onclick="clsClassifyFuntion()" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addSlideAd"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="showAdverTitle">添加模板广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="subForm_Adver">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="storeyId" id="storeyAdverId">
                    <input type="hidden" name="tempId"  id="up_storeyAdverTempId" value="${temp.tempId}"/>
                    <input type="hidden" name="floorId" value="" id="up_floorId">
                    <input type="hidden" id="upChannelAdverId" name="channelAdverId" value=""/>
                    <input type="hidden" name="atId" value="" id="upAtId"/>
                    <input type="hidden" name="temp1" id="up_temp1" value="">
                    <input type="hidden" name="temp3" id="up_temp3" value="">
                    <input type="hidden" name="adverType" id="up_adverType" >
                    <input type="hidden" name="floorTagId" id="storeyTagAdverId" >
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>广告名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" id="up_adverName" name="adverName" class="form-control required specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告副标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" id="up_adver_temp2" name="temp2" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>广告图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">

                            <img alt="" id="adverPath_y" src="<%=basePath %>images/default_head.jpg" height="50"> <input type="button"   onclick="saveImg(3)" value="选择"/>
                            <input name="adverPath" id="adverPath" type="hidden" value="" class="required">
                        </div>
                        <!--<div class="col-sm-3">
                            <a href="javascript:;" class="bigAdv help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>-->

                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告链接地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" id="up_adverHerf" name="adverHref" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" id="up_adverSort" name="adverSort" class="form-control w100 digits  isNumber  required specstr">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" id="up_adverStatus1" name="userStatus" checked="checked" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="up_adverStatus0" name="userStatus" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group" id="showFloorAdver">
                        <label class="control-label col-sm-6">广告显示位置：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-6">

                            <select name="temp5" id="advFlag"  class="form-control">
                                <option value="0">首页广告</option>
                                <!--<option value="1">商品列表页广告</option>
                                <option value="2">商品搜索页广告</option>
                                <option value="3">资讯页广告</option>-->
                                <%--<option value="5">团购页广告</option>--%>
                                <option value="6">抢购页广告</option>
                                <!--<option value="4">其他。。。</option>-->
                                <!--
                                <option value="4">类型5</option>
                                <option value="5">类型6</option>
                                <option value="6">类型7</option>
                                <option value="7">类型8</option>
                                <option value="8">类型9</option>
                                <option value="9">类型10</option> -->
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <textarea rows="5" id="up_adverDes" name="des" class="form-control"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="subAdverButton" onclick="submitAdver()" class="btn btn-primary">确定</button>
                <button type="button" id="cls_adver" onclick="clsAdver()" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addFloor"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="clsStoreForm()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="store_model_title">添加模板楼层</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" value="createtempstoreyajax.htm" id="storeyFormUrl">
                <form class="form-horizontal" id="up_storeyForm">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="tempId" value="${temp.tempId}"/>
                    <input type="hidden" id="upChannelStoreyId" name="channelStoreyId" value=""/>
                    <div class="form-group">
                        <label class="control-label col-sm-6">楼层分类：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <select class="form-control" id="goodsCate" name="goodsCatId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>楼层名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" name="storeyName"  id="storeyName" class="form-control  required specstr" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>楼层图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">

                            <img alt="" id="upStoreyImg_y" width="200" src="<%=basePath %>images/default_head.jpg" height="50"> <input type="button" onclick="saveImg(1)" value="选择"/>
                            <input name="storeyImg" id="upStoreyImg" type="hidden" value="" class="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">楼层右侧导航图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input name="temp2" id="temp2Img" class="imgage_path" type="hidden" value="">
                            <img alt="" class="img_y" id="temp2Img_y" src="<%=basePath %>images/default_head.jpg" height="50"> <input type="button" onclick="saveImg(2)" value="选择"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>图片链接：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" name="storeyImgHref" id="storeyHref" class="form-control  required specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6" >选择楼层：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <select class="form-control" name="floorId" id="floorId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否显示楼层广告：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" id="up_showImg1" checked="checked" name="showAdver" value="1"> 显示
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="up_showImg0" name="showAdver" value="0"> 不显示
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否显示楼层标签：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" id="showTag1" checked="checked" name="showTag" value="1"> 显示
                            </label>
                            <label class="radio-inline">
                                <input type="radio"  id="showTag0" name="showTag" value="0"> 不显示
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否显示楼层品牌：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" id="showTrademark1" checked="checked" name="showTrademark" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="showTrademark0" name="showTrademark" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" id="userStatus1" checked="checked" name="userStatus" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="userStatus0" name="userStatus" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="subStoreyForm()" class="btn btn-primary">确定</button>
                <button type="button" id="clsStoreyBtn" onclick="clsStoreForm()" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addFloorGoods"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="clsFloorStorey()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="floorGoods">添加楼层商品</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="FloorStoreyGoodsForm">

                    <div class="form-group">
                        <label class="control-label col-sm-6">选择商品：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-default" onclick="showChanneelStoreyGoodsList()">选择商品</button>
                        </div>
                    </div>
                    <%--楼层ID--%>
                    <input type="hidden" id="storeyId"  name="storeyId">

                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="tempId" value="${temp.tempId}"/>
                    <%--单个楼层商品主键--%>
                    <input type="hidden" name="channelStoreyGoodsproductId" id="up_channelStoreyGoodsproductId" />
                    <input type="hidden" name="goodsproductId" id="up_channelStoreyGoodsproductId1" />
                    <div class="form-group">
                        <label class="control-label col-sm-6">商品编号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input readonly="readonly" name="goodsproductNo" id="up_goodsproductNo1" type="text" class="form-control specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>商品名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" name="goodsproductName" id="up_goodsproductName1" class="form-control required specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">预览商品图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img id="up_goodsproductImgsrc1" width="160px;" height="160px">
                            <input type="hidden"  name="goodsproductImgsrc" id="up_goodsproductImgsrcValue1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">优惠价：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <div class="input-group">
                                <span class="input-group-addon">￥</span>
                                <input name="goodsproductPrice" id="up_goodsproductPrice1" readonly="readonly" type="text" class="form-control specstr">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" name="sort"  id="up_goodsSort1" class="form-control w100 digits required">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-6">商品类型</label>--%>
                        <%--<div class="col-sm-1"></div>--%>
                        <%--<div class="col-sm-5">--%>
                            <%--<select class="form-control" name="temp3" id="floorGoodsType">--%>
                                <%--<option value="0">排行榜</option>--%>
                                <%--<option value="1">最新降价</option>--%>
                                <%--<option value="2">新品</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="subFloorStorey()" class="btn btn-primary" >确定</button>
                <button type="button" id="floorGoodsBtn" onclick="clsFloorStorey()" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addFloorAds"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加楼层广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告副标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" width="160px;" height="160px" src="images/qpmall_logo.jpg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告链接地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control w100">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions" value="option1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions" value="option2"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <textarea rows="5" class="form-control"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addFloorBrands"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"  onclick="clsTradermark()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="trademarkTitle">添加楼层品牌</h4>
                <input type="hidden" id="tradermarkUrl" value="createchannelstoreytrademarkajax.htm">
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="trademarkForm">
                    <input type="hidden" id="channelTrademarkId"  name="channelTrademarkId">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" id="trademarkAdverId" value="" name="storeyId">
                    <input type="hidden" id="up_brand_storeyTagId" value="" name="storeyTagId">

                    <div class="form-group">
                        <label class="control-label col-sm-6">选择品牌：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">

                            <select   class="form-control w150 " name="trademarkName" data-live-search="true" id="trademarkBrand" onchange="changeTrademarkBrand(this)">
                                <c:forEach items="${brand}" var="b" varStatus="sta">
                                    <option  value="${b.brandName}">${b.brandName} </option>
                                </c:forEach>
                            </select>
                            <!--<select data-live-search="true"  name="trademarkName" id="trademarkBrand" onchange="changeTrademarkBrand(this)">
                                <c:forEach items="${brand}" var="b" varStatus="sta">
                                    <option  value="${b.brandName}">${b.brandName} </option>
                                </c:forEach>
                            </select>-->
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">品牌LOGO：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" src="images/logo1.png" id="trademarkBrandImg">
                            <input type="hidden" value="" id="trademarBrandValue" name="logoSrc">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">   <span style="color: red;">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" name="sort" id="trademarkLogoSrc" class="form-control w100 required number digits">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" name="userStatus" id="trademarkStatus1" checked="checked" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="userStatus" id="trademarkStatus0" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="subTradermark()" class="btn btn-primary">确定</button>
                <button type="button" onclick="clsTradermark()" id="clsTrademark" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addFloorLabel"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="pageTagTitle">添加页面标签</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" value="createpagetagajax.htm" id="pagTagFormUrl" >

                <form class="form-horizontal" id="pageTagForm">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="temp1" id="up_tempO" value="${temp.tempId}"/>
                    <input type="hidden" name="storeyId" id="tag_storeyId"/>
                    <input type="hidden"  id="up_channelStoreyTagId" name="channelStoreyTagId" value=""/>
                    <div class="form-group">
                        <label name="" class="control-label  col-sm-6"> <span style="color: red;">*</span>标签名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-7">
                            <input type="text" name="name" id="pagTagName" class="form-control required specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"> <span style="color: red;">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" name="sort" id="pagTagSort" class="form-control w100 required digits">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否显示标签广告：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" checked="checked" id="pageTagShowAdver1" name="showAdver" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="showAdver"  id="pageTagShowAdver0 "  value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否显示标签品牌：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" checked="checked" name="showTrademark" id="pageShowTrademark1" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="showTrademark" id="pageShowTrademark0" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" checked="checked" id="pageTageUserStatus1"  name="userStatus" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="userStatus" id="pageTageUserStatus0" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="subPagTagFrom()" class="btn btn-primary">确定</button>
                <button type="button" id="pagTagCls" onclick="clsPagTag()" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addHotSearch"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" onclick="clsHot()">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="hotTitle">添加热门搜索</h4>
            </div>
            <input type="hidden" value="createhotselectiveajax.htm" id="subHotId">
            <div class="modal-body">
                <form class="form-horizontal" id="upHotForm">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="tempid" value="${temp.tempId}"/>
                    <input type="hidden" id="upHotSearchId" name="hotSearchId" value=""/>
                    <div class="form-group">
                        <label class="control-label col-sm-6">热门搜索：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-7">
                            <input type="text" id="upHotKeyword" name="keyword" class="form-control required specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" id="upHotSort" name="sort" max="999"  class="form-control w1h00 required digits isNumber">
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="submitHotSearch()" class="btn btn-primary">确定</button>
                <button type="button" id="upHotClsBtn" class="btn btn-default" onclick="clsHot()" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addHotGoods"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="clsStorey()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 id="storeyTitle" class="modal-title">添加热销推荐商品</h4>
            </div>
            <input type="hidden" value="createchannelgoodsajax.htm" id="upStoreyForm">
            <div class="modal-body">
                <form class="form-horizontal" id="storeyGoodsForm">

                    <div class="form-group">
                        <label class="control-label col-sm-6">选择商品：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-default" onclick="showStoreyGoodsList()">选择商品</button>
                        </div>
                    </div>
                    <input type="hidden" id="storeyTagId"  name="storeyTagId">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="tempId" id="upStoreGoodsTempId" value="${temp.tempId}"/>
                    <input type="hidden" id="isHot"  name="isHot" value="1">
                    <input type="hidden" name="channelStoreyGoodsproductId" id="up_channelStoreyGoodsproductId" />
                    <input type="hidden" name="goodsproductId" id="up_channelStoreyGoodsProductId1" />
                   <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>商品编号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input readonly="readonly" name="goodsproductNo" id="up_goodsproductNo" type="text" class="form-control required specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>商品名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" name="goodsproductName" id="up_goodsproductName" class="form-control required specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">预览商品图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img id="up_goodsproductImgsrc" width="160px;" height="160px">
                            <input type="hidden" name="goodsproductImgsrc" id="up_goodsproductImgsrcValue">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">优惠价：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <div class="input-group">
                                <span class="input-group-addon">￥</span>
                                <input name="goodsproductPrice" id="up_goodsproductPrice" readonly="readonly" type="text" class="form-control  specstr">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" name="sort" id="up_goodsSort" class="form-control w100 digits required isNumber" >
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="subStorey()" class="btn btn-primary">确定</button>
                <button type="button" onclick="clsStorey()" id="clsStoreButton" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addPageIntro"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="clsMsg()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 id="msgTitle" class="modal-title">添加页面说明</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" value="newmegawizardajax.htm" id="msgFormId">
                <form class="form-horizontal" id="upMsgForm" >
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="tempId" value="${temp.tempId}"/>
                    <input type="hidden" id="msgId" name="id" value=""/>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span style="color: red;">*</span>页面说明名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" id="upMegawizardName" name="megawizardName" class="form-control required specstr ">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">页面类型：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <select class="form-control" id="msgSelect" name="sort">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span style="color: red;">*</span>页面说明内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <textarea rows="5" name="content" id="upMsgContent" class="form-control required specstr "></textarea>
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:" class="ymsmnr help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="subMsgForm()" class="btn btn-primary">确定</button>
                <button type="button" id="msgButtonCls" onclick="clsMsg()" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addGoodsChoose"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 id="goodsListTitle" class="modal-title">添加楼层商品</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-inline">
                    <div class="mb20">
                        <div class="form-group">
                            <input type="text" id="searchText" class="form-control" placeholder="商品关键字">
                        </div>
                        <div class="form-group">
                            <button type="button" onclick="doByAjax()" class="btn btn-info">搜索</button>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>选择</th>
                            <th>商品图片</th>
                            <th>商品名称</th>
                            <th>商家</th>
                            <th>商品分类</th>
                            <th>商品品牌</th>
                        </tr>
                        </thead>
                        <tbody id="singlHtml">

                        </tbody>
                    </table>
                    <div class="table_foot" id="singlFootHtml">



                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="singlButton" class="btn btn-primary">保存</button>
                <button type="button" id="singlQ" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<c:forEach items="${brand}" var="b" varStatus="sta">
    <input type="hidden" value="${b.brandLogo}" brandName="${b.brandName}" id="trademark${b.brandName}">
</c:forEach>
<input type="hidden" id="tempId" value="${temp.tempId}"/>
<input type="hidden" id="CSRFToken" value="${token}"/>

<input type="hidden" id="imgPath" value="">

<input type="hidden" id="imgPathValue" value="">
<input type="hidden" id="isAdver" value="">
<input type="hidden" id="isBrand" value="">

<input type="hidden" id="status" value="${status}">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="<%=basePath %>js/jqtreetable.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>/js/temp/show_temp.js"></script>
<script src="<%=basePath %>/js/temp/temp_classifyBar_list.js"></script>
<script src="<%=basePath %>/js/common/common_alert.js"></script>
<script src="<%=basePath %>/js/common/common_temp_alert.js"></script>
<script src="<%=basePath %>/js/common/common_checked.js"></script>
<script src="<%=basePath %>/js/temp/tag.js"></script>
<script src="<%=basePath %>/js/jquery.treeTable.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script>

    $(function(){
        $("#searchText").keypress(function (event){
            if(event.keyCode==13) {
                event.preventDefault();
            }
        });
        if($("#status").val()==1){
            $(".tab-pane").removeClass("active");
            $("#tab2").addClass("active");
            $("#_tab1").addClass("active");
            $("#_tabl12").removeClass("active");
            showTempCategory();
        }else if($("#status").val()==2){
            $(".tab-pane").removeClass("active");
            $("#tab4").addClass("active");
            $("#_tab2").addClass("active");
            $("#_tabl12").removeClass("active");
            showAdverPage(157);
        }else if($("#status").val()==3){
            $(".tab-pane").removeClass("active");
            $("#tab4").addClass("active");
            $("#_tab3").addClass("active");
            $("#_tabl12").removeClass("active");
            showAdverPage(159);
        }else if($("#status").val()==4){
            //新闻公告
            $(".tab-pane").removeClass("active");
            $("#tab6").addClass("active");
            $("#_tab5").addClass("active");
            $("#_tabl12").removeClass("active");
            showTempInfoType();
        }else if($("#status").val()==5){
            //页面广告
            showAdverPage(161);
            $(".tab-pane").removeClass("active");
            $("#tab4").addClass("active");
            $("#_tab4").addClass("active");
            $("#_tabl12").removeClass("active");
        }else if($("#status").val()==6){
            //页面广告
            $(".tab-pane").removeClass("active");
            $("#tab7").addClass("active");
            $("#_tab6").addClass("active");
            $("#_tabl12").removeClass("active");
            showTempStorey();
        }else if($("#status").val()==7){
            //页面广告
            showAdverPage(161);
            $(".tab-pane").removeClass("active");
            $("#tab8").addClass("active");
            $("#_tab7").addClass("active");
            $("#_tabl12").removeClass("active");
            showHotSearch();
        }else if($("#status").val()==8){
            //页面广告
            showAdverPage(161);
            $(".tab-pane").removeClass("active");
            $("#tab9").addClass("active");
            $("#_tab8").addClass("active");
            $("#_tabl12").removeClass("active");
            showStoreyGoods();
        }
        $("#goodsCateName1").val($("#goodsCate1").find("option:selected").text());
        /* 表单项的值点击后转换为可编辑状态 */
        $('.form_value').click(function(){
            var formItem = $(this);
            if(!$('.form_btns').is(':visible')) {
                formItem.parent().addClass('form_open');
                $('.form_btns').show();
                $('.form_btns').css({
                    'left': formItem.next().offset().left + 70 + 'px',
                    'top': formItem.next().offset().top - 30 + 'px'
                });
                $('.form_sure,.form_cancel').click(function () {
                    $('.form_btns').hide();
                    formItem.parent().removeClass('form_open');
                });
            }
        });

        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 富文本编辑框 */
        $('.summernote').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN'
        });

        /* 下面是表单里面的填写项提示相关的 */
        $('.paixu').popover({
            content : '数字越小越靠前',
            trigger : 'hover'
        });
        $('.dhurl').popover({
            content : '外部地址需要加：http://',
            trigger : 'hover'
        });
        $('.xsfldh').popover({
            content : '此设置作用于除首页以外的页面',
            trigger : 'hover'
        });
        $('.ymsmnr').popover({
            content : '多条以“|”隔开',
            trigger : 'hover'
        });

        /* 高级搜索 */
        $('.advanced_search').popover({
            html : true,
            title : '高级搜索',
            content : $('.advanced_search_cont').html(),
            trigger : 'click',
            placement : 'bottom'
        }).click(function(){
            $('.cate_selector').selectpicker('refresh');
        });

        /* 下面是表单里面的日期时间选择相关的 */
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00:00',
            weekStart : 1,
            autoclose : true,
            language : 'zh-CN',
            pickerPosition : 'bottom-left',
            todayBtn : true,
            viewSelect : 'hour'
        });
        $('.form_date').datetimepicker({
            format : 'yyyy-mm-dd',
            weekStart : 1,
            autoclose : true,
            language : 'zh-CN',
            pickerPosition : 'bottom-left',
            minView : 2,
            todayBtn : true
        });
        /* 下面是表单里面的日期时间选择相关的 END */

        /* 单选按钮点击切换 */
        $('input[name="zdyspfl"]').change(function(){
            if($(this).val() == 0){
                $('.zdyfl_yes').hide();
                $('.zdyfl_no').show();
                $(isdefi).val(-1);
            }
            else{
                $('.zdyfl_yes').show();
                $('.zdyfl_no').hide();
                $(isdefi).val(0);

            }
        });
        $('input[name="showType"]').change(function(){
            if($(this).val() == 1){
                $("#up_classifyCate").removeClass("required");
                $("#up_classifyCate").removeClass("specstr");
                $('.zslx_yes').hide();
                $('.zslx_no').show();
            }
            else{
                $("#up_classifyCate").addClass("required");
                $("#up_classifyCate").addClass("specstr");
                $('.zslx_yes').show();
                $('.zslx_no').hide();
            }
        });

        /* 下面是关于树形菜单 */
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: onCheck
            }
        };
        $.post("queryallgoodcate.htm?CSRFToken=${token}",function(data) {
            var zNodes = [];
            for(var i=0;i<data.length;i++){
                if(data[i].catParentId==null || data[i].catParentId==0){
                    var node = {
                        id: data[i].catId , pId: 0, name:  data[i].catName , open: false
                    };
                    zNodes.push(node);
                }else{
                    var node = {
                        id: data[i].catId, pId:data[i].catParentId, name:data[i].catName
                    };
                    zNodes.push(node);
                }
            }
            var zTree;
            $.fn.zTree.init($("#kjfl1"), setting, zNodes);
            $.fn.zTree.init($("#kjfl2"), setting, zNodes);
            $.fn.zTree.init($("#kjfl3"), setting, zNodes);
            $.fn.zTree.init($("#kjfl4"), setting, zNodes);
        });
    });

    function onCheck(e, treeId, treeNode){
        if(treeId=="kjfl1"){
            $("#barQuickName1").val("");
            $("#barQuickIds1").val(0);
            var zTree = $.fn.zTree.getZTreeObj("kjfl1");
            nodes = zTree.getSelectedNodes(true);
            var s = '';
            if(nodes != null && nodes.length>0){
                for(var i = 0; i< nodes.length;i++){
                    if(nodes[i].level==3){
                        $("#barQuickName1").val(nodes[i].name);
                        $("#barQuickIds1").val(nodes[i].id);
                    }
                }
            }
        }
        if(treeId=="kjfl2"){
            $("#barQuickName2").val("");
            $("#barQuickIds2").val(0);
            var zTree = $.fn.zTree.getZTreeObj("kjfl2");
            nodes = zTree.getSelectedNodes(true);
            var s = '';
            if(nodes != null && nodes.length>0){
                for(var i = 0; i< nodes.length;i++){
                    if(nodes[i].level==3){
                        $("#barQuickName2").val(nodes[i].name);
                        $("#barQuickIds2").val(nodes[i].id);
                    }
                }
            }
        }
        if(treeId=="kjfl3"){
            $("#barQuickName3").val("");
            $("#barQuickIds3").val(0);
            var zTree = $.fn.zTree.getZTreeObj("kjfl3");
            nodes = zTree.getSelectedNodes(true);
            var s = '';
            if(nodes != null && nodes.length>0){
                for(var i = 0; i< nodes.length;i++){
                    if(nodes[i].level==3){
                        $("#barQuickName3").val(nodes[i].name);
                        $("#barQuickIds3").val(nodes[i].id);

                    }
                }
            }
        }
        if(treeId=="kjfl4"){
            $("#barQuickName4").val("");
            $("#barQuickIds4").val(0);
            var zTree = $.fn.zTree.getZTreeObj("kjfl4");
            nodes = zTree.getSelectedNodes(true);
            var s = '';
            if(nodes != null && nodes.length>0){
                for(var i = 0; i< nodes.length;i++){
                    if(nodes[i].level==3){
                        $("#barQuickName4").val(nodes[i].name);
                        $("#barQuickIds4").val(nodes[i].id);
                    }
                }
            }
        }

    }
</script>
</body>
</html>

