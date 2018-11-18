<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/18
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                 <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows}条)</small></h2>
                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 短信接口，若修改不当，会影响用户短信的接收，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="box_data container-fluid p20">
                        <div class="row">
                        <c:forEach  var="sms" items="${pb.list }" varStatus="status">
                            <fmt:formatDate value="${sms.modifyTime }" var="cdate" type="both"/>
                            <div class="col-sm-6">
                                <div class="box_item">
                                    <h2>${sms.smsProvider }</h2>
                                    <c:if test="${sms.isOpen==1 }"> 
                                        <span class="label label-success">已启用</span>
                                    </c:if>
                                    <c:if test="${sms.isOpen==0 }">  
                                        <span class="label label-default">已停用</span> 
                                    </c:if>
                                    <span><a href="${sms.smsAddress }" target="_blank">我要申请</a></span>
                                    <p>最新修改时间：${cdate}</p>
                                    
                                    <div class="box_edit">
                                        <a href="javascript:;" onclick="updateSms(${sms.smsId },this);"
                                           smsAddress="${sms.smsAddress}" smsProvider="${sms.smsProvider}"
                                           smsName="${sms.smsName}" smsUrl="${sms.smsUrl }" smsPass="${sms.smsPass }"
                                           smsGateway="${sms.smsGateway }" isOpen="${sms.isOpen}">编辑</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="editMessage"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑短信接口</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" action="updatmess.htm" method="post" id="editMessageForm">
                    <input type="hidden" id="smsId" name="smsId"/>
                    <input type="hidden" name="CSRFToken" value="${token}">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>是否开启：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="option1" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="option2" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>接口名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" id="smsName" name="smsName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>提供商：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" id="smsProvider"
                                   name="smsProvider">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>申请地址：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" id="smsAddress" name="smsAddress">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>接口密码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="password" class="form-control required specstr" id="smsPass" name="smsPass">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>短信接口地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required url" id="smsUrl" name="smsUrl">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>报备和签名：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-6">
                            <input type="text" class="form-control w200 required" id="smsGateway" name="smsGateway">
                        </div>
                        <div class="col-sm-8 control-label">(格式：报备+|+签名)</div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitMessageForm()">保存</button>
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
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/system/messageserver.js"></script>
</body>
</html>
