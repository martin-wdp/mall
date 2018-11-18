<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/12
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
  <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
  <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
  <link href="<%=basePath%>css/style.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

  <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
  <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
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

        <h2 class="main_title">授权账户信息</h2>
        <div class="common_data p20">
          <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <%--<strong>注意!</strong>--%> 您的云销账户信息已设置。
          </div>
          <div class="common_form p20 form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-6"><span class="text-danger">*</span>云霄账户编号：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-14 form_item">
                  <span class="form_value form_value_parameter" attr_id="bsetName">${authorId }&nbsp;</span>
                </div>
                <div class="col-sm-3">
                    <button class="btn btn-primary" id="resetInfo" onclick="$('#connectTips').modal('show')">重置</button>
                </div>
              </div>
              <%--<div class="form-group" style="margin-top: 20px;">
                  <div class="col-sm-4"></div>
                  <div class="col-sm-3">
                      <button class="btn btn-primary" id="resetInfo">重置</button>
                  </div>
              </div>--%>
              <%--<div class="form-group">
                  <label class="control-label col-sm-6">网站描述：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-14 form_item">
                      <span class="form_value form_value_parameter" attr_id="bsetDesc">${parameter.bsetDesc}&nbsp; </span>
                      <div class="form_fill">
                          <textarea id="bsetDesc" class="form-control w300" rows="4" clazz="required specstr">${parameter.bsetDesc}</textarea>
                      </div>
                  </div>
                  <div class="col-sm-3"></div>
              </div>--%>

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
                <p>将会清空您的授权云销账户信息，确认这样做吗？</p>
            </div>
            <form action="<%=basePath%>clearAuthorInfo.htm" method="post" id="clearAuthorInfoForm"></form>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:$('#clearAuthorInfoForm').submit();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/common.js"></script>
</body>
</html>

