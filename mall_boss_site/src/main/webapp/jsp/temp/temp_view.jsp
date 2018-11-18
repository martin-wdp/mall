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
    <link href="<%=basePath %>/css/style.css" rel="stylesheet">

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

            <h2 class="main_title">页面配置</h2>

            <div class="common_data p20">
              <div class="alert alert-warning alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                <strong>注意!</strong> 以下仅为图示，真实效果请点此<a href="${topmap.systembase.bsetAddress}">浏览商城</a>
              </div>

              <div class="temp_choose">
                <div class="temp_bg"><img alt="" src="images/template_bg.png"></div>
                <div class="temp_item temp_nav" data-position="240_140" data-size="1000_50">
                  <h4>页面导航</h4>
                  <button type="button" class="btn btn-danger btn-sm edit_btn" onclick="window.location.href='showTempInfo.htm?tempId=${tempId}&status=9'">编辑</button>
                </div>
                <div class="temp_item temp_cate" data-position="26_140" data-size="210_520">
                  <h4>分类导航</h4>
                  <button type="button" class="btn btn-danger btn-sm edit_btn" onclick="window.location.href='showTempInfo.htm?tempId=${tempId}&status=1'">编辑</button>
                </div>
                <div class="temp_item slide_ad" data-position="240_190" data-size="720_470">
                  <h4>轮播大广告</h4>
                  <button type="button" class="btn btn-danger btn-sm edit_btn" onclick="window.location.href='showTempInfo.htm?tempId=${tempId}&status=2'">编辑</button>
                </div>
                <!--<div class="temp_item roll_ad" data-position="90_680" data-size="280_280">
                  <h4>轮播小广告</h4>
                  <button type="button" class="btn btn-danger btn-sm edit_btn" onclick="window.location.href='showTempInfo.htm?tempId=${tempId}&status=3'">编辑</button>
                </div>-->
                <div class="temp_item news_list" data-position="975_230" data-size="250_205">
                  <h4>新闻公告</h4>
                  <button type="button" class="btn btn-danger btn-sm edit_btn" onclick="window.location.href='showTempInfo.htm?tempId=${tempId}&status=4'">编辑</button>
                </div>
                <div class="temp_item page_ad" data-position="90_680" data-size="1130_280">
                  <h4>轮播小广告</h4>
                  <button type="button" class="btn btn-danger btn-sm edit_btn" onclick="window.location.href='showTempInfo.htm?tempId=${tempId}&status=3'">编辑</button>
                </div>
                <div class="temp_item floor_set" data-position="27_1300" data-size="1200_610">
                  <h4>楼层设置</h4>
                  <button type="button" class="btn btn-danger btn-sm edit_btn" onclick="window.location.href='showTempInfo.htm?tempId=${tempId}&status=6'">编辑</button>
                </div>
                <div class="temp_item hot_key" data-position="420_85" data-size="530_40">
                  <h4>热门搜索</h4>
                  <button type="button" class="btn btn-danger btn-sm edit_btn" onclick="window.location.href='showTempInfo.htm?tempId=${tempId}&status=7'">编辑</button>
                </div>
                <div class="temp_item hot_sell" data-position="27_985" data-size="1200_285">
                  <h4>热销推荐</h4>
                  <button type="button" class="btn btn-danger btn-sm edit_btn" onclick="window.location.href='showTempInfo.htm?tempId=${tempId}&status=8'">编辑</button>
                </div>

              </div>
            </div>

          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="addPageNav" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">添加页面导航</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-4">导航名称：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <input type="text" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">导航地址：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <input type="text" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">描述信息：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <input type="text" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">排序：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-3">
                  <input type="text" class="form-control w100">
                </div>
                <div class="col-sm-3">
                  <a href="javascript:;" class="paixu help_tips">
                    <i class="icon iconfont">&#xe611;</i>
                  </a>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">是否启用：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <label class="radio-inline">
                    <input type="radio" name="inlineRadioOptions" value="option1"> 是
                  </label>
                  <label class="radio-inline">
                    <input type="radio" name="inlineRadioOptions" value="option2"> 否
                  </label>
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
    <script>
      $(function(){

        /* 下面是表单里面的填写项提示相关的 */
        $('.paixu').popover({
          content : '数字越小越靠前',
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
          format : 'yyyy-mm-dd hh:ii:00',
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

        /* 模板位置设置 END */
        $('.temp_item').each(function(){
          var p_position = $(this).attr('data-position').split('_'),p_size = $(this).attr('data-size').split('_');
          var p_left = parseInt(p_position[0]),p_top = parseInt(p_position[1]);
          var p_width = parseInt(p_size[0]),p_height = parseInt(p_size[1]);
          $(this).css({
            'width' : p_width + 'px',
            'height' : p_height + 'px',
            'left' : p_left + 'px',
            'top' : p_top + 'px'
          });
          $(this).mouseenter(function(){
            $(this).animate({'opacity':'1'},100)
          });
          $(this).mouseleave(function(){
            $(this).animate({'opacity':'0'},100)
          });
        });


      });
    </script>
  </body>
</html>
