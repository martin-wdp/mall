<%--
  新增云销功能授权页
  User: liangck
  Date: 15/6/23
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
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
  <title>账户授权</title>

  <!-- Bootstrap -->
  <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath %>/iconfont/iconfont.css" rel="stylesheet">
  <link href="<%=basePath %>/css/style.css" rel="stylesheet">
</head>
<body>

<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
  <div class="row">
    <jsp:include page="../page/left.jsp"></jsp:include>

    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
        <h2 class="main_title">开放平台对接</h2>

        <div class="common_data p20">

          <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>温馨提示：</strong> 一个云销账号只能和一个私有云账号绑定，绑定后不支持解除绑定，如果本店只是您用来测试的，绑定时请慎重！
          </div>

          <div class="connect_box" style="padding:0 10px">
            <h4 style="font-size:14px;font-weight:bold;">绑定云销账号，把私有云和云销打通</h4>
            <p class="text-muted">绑定后即可在后台体验更强大的功能！</p>
            <a href="http://oauth.qianmi.com/authorize?client_id=10000358&response_type=code&redirect_uri=${siteUrl}/authorSuccess.htm&state=${state}&view=web" target="_blank" onclick="$('#connectTips').modal('show')" style="display:inline-block;height:40px;line-height:40px;padding:0 20px 0 70px;background:#458BCA url(images/yunxiao_icon.png) no-repeat 10px center;border:1px solid #387EBD;border-radius:3px;color:#fff;">我有云销账号，立即设置</a>
          </div>

        </div>
      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal" id="connectTips" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body">
        <p>请在新窗口中完成云销账号和私有云对接</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:window.location.reload();">已成功设置</button>
        <button type="button" class="btn btn-default" onclick="window.location.href='http://oauth.qianmi.com/authorize?client_id=10000358&response_type=code&redirect_uri=${siteUrl}/authorSuccess.htm&state=${state}&view=web'">设置失败，重试</button>
      </div>
    </div>
  </div>
</div>
<script src="<%=basePath %>/js/bootstrap.min.js"></script>
<script src="<%=basePath %>/js/summernote.min.js"></script>
<script src="<%=basePath %>/js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>/js/common.js"></script>
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

    /* 富文本编辑框 */
    $('.summernote').summernote({
      height: 300,
      tabsize: 2,
      lang: 'zh-CN'
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.kucunyujing').popover({
      content : '当商品库存低于该值则进行预警',
      trigger : 'hover'
    });
  });
</script>
</body>
</html>
