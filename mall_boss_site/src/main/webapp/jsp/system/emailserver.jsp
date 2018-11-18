<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/18
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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

                <h2 class="main_title">${pageNameChild}</h2>
                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 邮箱接口，若修改不当，会影响邮件的接收和验证，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="common_form p20">
                        <form class="form-horizontal" id="editEmailServerForm">
                            <input type="hidden" value="${server.serverid}" id="serverid">
                            <input type="hidden" value="${token}" id="CSRFToken">
                            <div class="form-group">
                                <label class="control-label col-sm-6">是否开启：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="isOpen" attr_type="radio">
                                        <c:if test="${server.isOpen==1}"><span class="label label-success">是</span></c:if>
                                        <c:if test="${server.isOpen==0}"><span class="label label-default">否</span></c:if>
                                    </span>
                                    <div class="form_fill">
                                        <label class="radio-inline">
                                            <input type="radio" name="isOpen" id="open1" value="1" <c:if test="${server.isOpen==1}">checked</c:if>> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isOpen" id="open2" value="0" <c:if test="${server.isOpen==0}">checked</c:if>> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">发信邮箱：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="sendmail">${server.sendmail }&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w300" value="${server.sendmail }" id="sendmail" clazz="required email">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">发信人：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="sendname">${server.sendname }&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w200" value="${server.sendname }" id="sendname" clazz="required specstr">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">SMTP服务器：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="smtpserver">${server.smtpserver }&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w300" value="${server.smtpserver }" id="smtpserver" clazz="required">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">SMTP端口号：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="smtpport">${server.smtpport }&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${server.smtpport }" id="smtpport"  clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">SMTP账号：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="smtpaccount">${server.smtpaccount }&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w300" value="${server.smtpaccount }" id="smtpaccount" clazz="required specstr">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">SMTP密码：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="smtppass">********&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="password" class="form-control w200" value="******" id="smtppass" clazz="required specstr">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">是否验证：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="isCheck" attr_type="radio">
                                        <c:if test="${server.isCheck==1}"><span class="label label-success">是</span></c:if>
                                        <c:if test="${server.isCheck==0}"><span class="label label-default">否</span></c:if>
                                    </span>
                                    <div class="form_fill">
                                        <label class="radio-inline">
                                            <input type="radio" name="isCheck" id="open3" value="1" <c:if test="${server.isCheck==1}">checked</c:if>> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isCheck" id="open4" value="0" <c:if test="${server.isCheck==0}">checked</c:if>> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>

                            <div class="form_btns popover right">
                                <div class="arrow" style="top:50%"></div>
                                <h3 class="popover-title">确定修改？</h3>
                                <div class="popover-content">
                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary form_sure">确定</button>
                                        &nbsp;&nbsp;
                                        <button type="button" class="btn btn-default form_cancel">取消</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
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
<script src="<%=basePath%>js/system/emailserver.js"></script>
</body>
</html>

