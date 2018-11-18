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
    <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>/css/font-awesome.min.css">
    <link href="<%=basePath %>/iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>/css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>

<div class="container-fluid page_body">
    <div class="row">

        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                  <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${map.indexList.size()}条)<a href="javascript:void(0);"  onclick="$('#HelpTempTips').modal('show')" style="diaplay:block; float:right; padding-right: 12px">查看帮助<i class="icon iconfont">&#xe611;</i></a></small></h2>

                <div class="common_data p20">
                    <h4>正在使用的模板</h4>

                    <div class="template_list container-fluid"> 
                        <div class="row">
                            <c:forEach items="${map.indexList}" var="tempObject" varStatus="i">
                                <c:if test="${tempObject.usedStatus == 1}">
                                    <div class="col-xs-8">
                                        <div class="template_item">
                                            <div style="height:210px;overflow-y:hidden">
                                                <img alt=""src="${tempObject.tempImageUrl }" alt="${tempObject.tempName }" width="100%">
                                            </div>
                                            <p class="mt20">${tempObject.tempName }</p>
                                            <div class="pull-right">
                                                <div class="btn-group" role="group">
                                                    <c:if test="${tempObject.usedStatus == 1}">
                                                        <button type="button" class="btn btn-primary" disabled>当前模板</button>
                                                    </c:if>
                                                    <c:if test="${tempObject.usedStatus != 1}">
                                                        <button type="button" onclick="setUsedStatus('${token}',${tempObject.tempId})" class="btn btn-default">设置为当前模板</button>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="pull-left">
                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-default"  onclick="location.href='showTempInfo.htm?tempId=${tempObject.tempId}'">模板配置</button>
                                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                        <span class="caret"></span>
                                                        <span class="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul class="dropdown-menu" role="menu">
                                                        <c:if test="${tempObject.usedStatus == 1}">
                                                        <li><a  href="refurbish.htm?CSRFToken=${token}">生成页面</a>
                                                            </c:if>
                                                            <li><a href="javascript:;"  onclick="showTemp('<c:if test="${basicset!=null}">${basicset.bsetAddress}</c:if>/preview/${tempObject.tempId }')">预览</a></li>
                                                            <!-- <li><a href="selectTempView.htm?tempId=${tempObject.tempId}">模板编辑</a></li> -->
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <h4>首页模板</h4>

                    <div class="template_list container-fluid">
                        <div class="row">
                            <c:forEach items="${map.indexList}" var="tempObject" varStatus="i">
                                <c:if test="${tempObject.usedStatus != 1}">
                                  <div class="col-xs-8">
                                    <div class="template_item">
                                        <div style="height:210px;overflow-y:hidden">
                                            <img alt=""src="${tempObject.tempImageUrl }" alt="${tempObject.tempName }" width="100%">
                                        </div>
                                        <p class="mt20">${tempObject.tempName }</p>
                                        <div class="pull-right">
                                            <div class="btn-group" role="group">
                                                <c:if test="${tempObject.usedStatus == 1}">
                                                    <button type="button" class="btn btn-primary" disabled>当前模板</button>
                                                </c:if>
                                                <c:if test="${tempObject.usedStatus != 1}">
                                                    <button type="button" onclick="setUsedStatus('${token}',${tempObject.tempId})" class="btn btn-default">设置为当前模板</button>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="pull-left">
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default" onclick="location.href='showTempInfo.htm?tempId=${tempObject.tempId}'">模板配置</button>
                                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">

                                                    <li><a href="toUpdateTempInfo.htm?tempId=${tempObject.tempId}">编辑模板信息</a></li>
                                                    <li><a href="javascript:;"  onclick="showTemp('<c:if test="${basicset!=null}">${basicset.bsetAddress}</c:if>/preview/${tempObject.tempId }')">预览</a></li>
                                                    <!-- <li><a href="selectTempView.htm?tempId=${tempObject.tempId}">模板编辑</a></li> -->
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="clr"></div>
                                    </div>
                                </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <h4>频道页模板</h4>

                    <div class="template_list container-fluid">
                        <div class="row">
                            <c:forEach items="${map.channelList}" var="tempObject" varStatus="i">
                                <%--<c:if test="${tempObject.usedStatus != 1}">--%>
                                <div class="col-xs-8">
                                    <div class="template_item">
                                        <c:if test="${tempObject.tempImageUrl!=''|| tempObject.tempImageUrl!=null}">
                                            <img alt=""src="${tempObject.tempImageUrl }" alt="${tempObject.tempName }" width="100%">
                                            <p class="mt20">${tempObject.tempName }</p>
                                        </c:if>
                                        <div class="pull-right">
                                            <div class="btn-group" role="group">
                                                    <%--<c:if test="${tempObject.usedStatus == 1}">--%>
                                                    <%--<button type="button" class="btn btn-primary" disabled>当前模板</button>--%>
                                                    <%--</c:if>--%>
                                                    <%--<c:if test="${tempObject.usedStatus != 1}">--%>
                                                    <%--<button type="button" onclick="setUsedStatus('${token}',${tempObject.tempId})" class="btn btn-default">设置为当前模板</button>--%>
                                                    <%--</c:if>--%>
                                            </div>
                                        </div>
                                        <div class="pull-left">
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default">操作</button>
                                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">

                                                    <li><a href="toUpdateChannelInfo.htm?CSRFToken=${token}&tempId=${tempObject.tempId}">修改模板信息</a></li>
                                                    <li><a href="queryChannelByPageBean.htm?CSRFToken=${token}&tempId=${tempObject.tempId}">查看使用该模板的频道</a></li>
                                                        <%--<li><a href="javascriptshowChannelSet:;"  onclick="showTemp('<c:if test="${basicset!=null}">${basicset.bsetAddress}</c:if>/preview/${tempObject.tempId }')">预览</a></li>--%>
                                                    <!-- <li><a href="selectTempView.htm?tempId=${tempObject.tempId}">模板编辑</a></li> -->
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="clr"></div>
                                    </div>
                                </div>
                                <%--</c:if>--%>
                            </c:forEach>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<form target="_blank" id="showTempA" method="post">

</form>






 <div class="modal fade" id="HelpTempTips" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">模板配置</h4>
          </div>
          <div class="modal-body">
            <div class="modal-article">
                <p><em>1.</em>进入模板配置页面，进行“页面导航”设置，配置如下图</p>
                <img src="./images/syshelp/img_m02.png" alt="">
                <p><em>2.</em>“分类导航”设置</p>
                <img src="./images/syshelp/img_m03.png" alt="">
                <p><em>3.</em>“点击添加“一级分类”</p>
                <img src="./images/syshelp/img_m04.png" alt="">
                <p><em></em>点击添加“二级分类”和“三级分类”</p>
                <img src="./images/syshelp/img_m051.png" alt="">
                <img src="./images/syshelp/img_m052.png" alt="">
                <p><em>4.</em>“轮播大广告”设置</p>
                <img src="./images/syshelp/img_m06.png" alt="">
                <p><em>5.</em>“轮播小广告”设置</p>
                <img src="./images/syshelp/img_m07.png" alt="">
                <p><em>6.</em>“页面广告”设置</p>
                <img src="./images/syshelp/img_m08.png" alt="">
                <p><em>7.</em>“商城公告”设置：商城内容在“站点”里的“文章列表”里设置</p>
                <img src="./images/syshelp/img_m09.png" alt="">
                <p><em>8.</em>“楼层设置”设置，可根据相应一级分类设置楼层</p>
                <img src="./images/syshelp/img_m10.png" alt="">
                <p><em>9.</em>“热门搜索”设置</p>
                <img src="./images/syshelp/img_m11.png" alt="">
                <p><em>10.</em>“热销推荐”设置</p>
                <img src="./images/syshelp/img_m12.png" alt="">
                <p><em>11.</em>“活动”设置</p>
                <img src="./images/syshelp/img_m13.png" alt="">
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>
    
    
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>/js/bootstrap.min.js"></script>
<script src="<%=basePath %>/js/summernote.min.js"></script>
<script src="<%=basePath %>/js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>/js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>/js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>/js/common.js"></script>
<script src="<%=basePath %>/js/temp/temp.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>

<script>
    $(function(){
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
        $('.zhuantiseokw').popover({
            content : '默认为文章名称(最大字数75)',
            trigger : 'hover'
        });
        $('.zhuantiseodesc').popover({
            content : '默认为文章名称(最大字数255)',
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
        	 $('select[data-live-search="true"]').select2();
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
    });
</script>
</body>
</html>
