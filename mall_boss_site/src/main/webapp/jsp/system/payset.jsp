<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/23
  Time: 10:21
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
    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
       <style>
      .modal-article {padding: 0 10px; height: 400px; overflow-y: scroll;}
      .modal-article p {line-height: 180%; font-size: 16px; position: relative; padding-left: 25px;}
      .modal-article p em {font-style: normal; position: absolute; top: 0; left: 0;}
      .modal-article img {display: block; width: 100%; margin: 10px auto 20px;}
      .payment .panel-heading {padding:0;}
      .payment .panel-heading .panel-title > a {padding:18px 15px;}
    </style>
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
                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows}条)</small></h2>
                <div class="payment p20">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
                        <c:forEach  var="pay" items="${pb.list }" varStatus="status">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="heading${pay.payId}">
                                <h4 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse${pay.payId}" aria-expanded="false" aria-controls="collapseFour">
                                        ${pay.payName }
                                            <c:if test="${pay.isOpen==0 }">
                                                <small>(已禁用)</small>
                                            </c:if>  
                                        <i class="icon iconfont">&#xe61a;</i>  
                                    </a>
                                </h4>
                                
                                 <div class="btn-group">
                                        <button type="button" class="btn btn-default" onclick="showEditForm(${pay.payType},${pay.payId})">编辑</button>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                          <c:if test="${pay.isOpen == 1 }">   <li><a href="javascript:void(0);" onclick="changeUserdStatus(${pay.payId })">禁用</a></li></c:if>
                                           <c:if test="${pay.isOpen == 0 }">   <li><a href="javascript:void(0);" onclick="changeUserdStatus(${pay.payId })">启用</a></li></c:if>
                                        </ul>
                                    </div>
                                
                               
                            </div>
                            <div id="collapse${pay.payId}" class="panel-collapse collapse <c:if test="${status.index==0}">in</c:if> " role="tabpanel" aria-labelledby="heading${pay.payId}">
                                <div class="panel-body">
                                    <img class="pay_icon" alt="" src="${pay.payImage }">
                                    
                                    <c:if test="${pay.payType==1}">
                                    <h4>启用支付宝支付，买家就可使用支付宝付款购买您的商品。货款将直接进入配置好的支付宝账号。</h4>

                                    <p class="ui-message-warning">
                                      因为支付宝接口问题，暂时无法支持支付宝付款，需求客户到支付宝网站（https://b.alipay.com）申请商家服务。
                                       <a href="javascript:;" onclick="$('#multiArticle${status.count}').modal('show')">查看帮助</a>
                                    </p>

                                 
                                    </c:if>
                                    <c:if test="${pay.payType==2}">
                                        <h4>启用银行卡支付，买家就可使用银行卡付款购买您的商品。货款将先进入配置好的账号，您可以随时申请提现。</h4>
                                        <a href="javascript:;" onclick="$('#multiArticle${status.count}').modal('show')">查看帮助</a>
                                    </c:if>
                                    <c:if test="${pay.payType==3}">
                                        <h4>启用微信支付，买家就可在微信商城中使用微信支付来购买商品了，货款将直接进入相应的财付通账号。</h4>
                                        <a href="javascript:;" onclick="$('#multiArticle${status.count}').modal('show')">查看帮助</a>
                                    </c:if>

                                    <c:if test="${pay.payType==4}">
                                        <h4>启用千米收银台，买家就可使用千米收银台购买您的商品。货款将直接进入配置好的千米账号中。</h4>
                                        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2853573863&site=qq&menu=yes" >点击咨询</a>

                                    </c:if>

                                    <c:if test="${pay.payType==5}">
                                        <h4>启用联付通企业支付，买家就可使用联付通企业支付购买您的商品。货款将直接进入配置好的联付通企业支付账号中。</h4>
                                        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2853573863&site=qq&menu=yes" >点击咨询</a>

                                    </c:if>
                                    <c:if test="${pay.payType==6}">
                                        <h4>启用联付通个人支付，买家就可使用联付通个人支付购买您的商品。货款将直接进入配置好的联付通个人支付。</h4>
                                        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2853573863&site=qq&menu=yes" >点击咨询</a>

                                    </c:if>
                                    <c:if test="${pay.payType==7}">
                                        <h4>启用微信支付，买家就可在微信商城中使用微信支付来购买商品了。</h4>
                                        <a href="javascript:;" onclick="$('#multiArticle${status.count}').modal('show')">查看帮助</a>

                                    </c:if>
                                    <c:if test="${pay.payType==8}">
                                        <h4>启用银行卡支付，买家就可使用银行卡付款购买您的商品。货款将先进入配置好的账号，您可以随时申请提现。</h4>
                                        <a href="javascript:;" onclick="$('#multiArticle${status.count}').modal('show')">查看帮助</a>
                                    </c:if>
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

<!-- 支付宝 editModal1中的1表示支付类型是支付宝-->
<div class="modal fade" id="editModal1"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑支付接口</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" method="post" action="updatepay.htm?CSRFToken=${token}" enctype="multipart/form-data" id="editForm1">
                    <input type="hidden" name="payId" id="payId1" />
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>支付名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payName" id="up_payname1" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">选择图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="netLogo"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img height="50" alt="" src="" id="preview_pic1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付问题描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                           <button type="button" class="btn btn-default" onclick="editpayhelp(1)">查看并修改</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>Api-Key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="apiKey" id="up_apikey1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>Secret-Key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="secretKey" id="up_secrect1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>收款账号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payAccount" id="up_account1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>后台回调地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="payUrl" id="up_payurl1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>前台回调地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="backUrl" id="up_backurl1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付类型：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="form-control" data-live-search="true" name="payType">
                                <option value="1">支付宝</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">手机支付回调：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control specstr" name="payComment" id="up_comment1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否设为默认：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open11" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open12" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open13" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open14" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditForm(1)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 银联 editModal2中的2表示支付类型是银联-->
<div class="modal fade" id="editModal2"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑支付接口</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" method="post" action="updatepay.htm?CSRFToken=${token}" enctype="multipart/form-data" id="editForm2">
                    <input type="hidden" name="payId" id="payId2" />
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>支付名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payName" id="up_payname2" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">选择图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="netLogo"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img height="50" alt="" src="" id="preview_pic2">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付问题描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                           <button type="button" class="btn btn-default" onclick="editpayhelp(2)">查看并修改</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>Api-Key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="apiKey" id="up_apikey2">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>收款账号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payAccount" id="up_account2">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>后台回调地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="payUrl" id="up_payurl2">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>前台回调地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="backUrl" id="up_backurl2">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付类型：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="form-control" data-live-search="true">
                                <option>银联在线</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">备注：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control specstr" name="payComment" id="up_comment2">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否设为默认：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open21" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open22" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open23" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open24" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditForm(2)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 微信 editModal3中的3表示支付类型是微信-->
<div class="modal fade" id="editModal3"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑支付接口</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" method="post" action="updatepay.htm?CSRFToken=${token}" enctype="multipart/form-data" id="editForm3">
                    <input type="hidden" name="payId" id="payId3" />
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>支付名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payName" id="up_payname3" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">选择图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="netLogo"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img height="50" alt="" src="" id="preview_pic3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付问题描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                           <button type="button" class="btn btn-default" onclick="editpayhelp(3)">查看并修改</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>公众号ID：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="apiKey" id="up_apikey3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>app_key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="secretKey" id="up_secrect3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>商户号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="partner" id="up_partner3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>商户标识：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="partnerKey" id="up_partnerKey3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>通知URL：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="backUrl" id="up_backurl3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付类型：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="form-control" data-live-search="true">
                                <option value="3">微信支付</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">备注：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control specstr" name="payComment" id="up_comment3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否设为默认：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open31" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open32" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open33" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open34" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditForm(3)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 微信 editModal4中的3表示支付类型是千米收银台-->
<div class="modal fade" id="editModal4"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑支付接口</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" method="post" action="updatepay.htm?CSRFToken=${token}" enctype="multipart/form-data" id="editForm4">
                    <input type="hidden" name="payId" id="payId4" />
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>支付名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payName" id="up_payname4" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">选择图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="netLogo"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img height="50" alt="" src="" id="preview_pic4">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付问题描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                           <button type="button" class="btn btn-default" onclick="editpayhelp(4)">查看并修改</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>购买方编号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payAccount" id="up_account4">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>上级编号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="secretKey" id="up_secrect4">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="apiKey" id="up_apikey4">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>域名：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="payUrl" id="up_payurl4">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>通知URL：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="backUrl" id="up_backurl4">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付类型：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="form-control" data-live-search="true">
                                <option value="4">千米收银台</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">备注：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control specstr" name="payComment" id="up_comment4">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否设为默认：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open41" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open42" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open43" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open44" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditForm(4)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--ADD BY LUYONG--%>
<!-- 联付通企业支付-->
<div class="modal fade" id="editModal5"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑支付接口</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" method="post" action="updatepay.htm?CSRFToken=${token}" enctype="multipart/form-data" id="editForm5">
                    <input type="hidden" name="payId" id="payId5" />
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>支付名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payName" id="up_payname5" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">选择图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="netLogo"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img height="50" alt="" src="" id="preview_pic5">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付问题描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-default" onclick="editpayhelp(5)">查看并修改</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>购买方编号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payAccount" id="up_account5">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>上级编号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="secretKey" id="up_secrect5">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="apiKey" id="up_apikey5">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>域名：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="payUrl" id="up_payurl5">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>通知URL：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="backUrl" id="up_backurl5">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付类型：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="form-control" data-live-search="true">
                                <option value="5">联付通企业支付</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">备注：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control specstr" name="payComment" id="up_comment5">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否设为默认：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open51" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open52" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open53" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open54" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditForm(5)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--ADD BY LUYONG--%>

<%--ADD BY LUYONG--%>
<!-- 联付通个人支付-->
<div class="modal fade" id="editModal6"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑支付接口</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" method="post" action="updatepay.htm?CSRFToken=${token}" enctype="multipart/form-data" id="editForm6">
                    <input type="hidden" name="payId" id="payId6" />
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>支付名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payName" id="up_payname6" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">选择图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="netLogo"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img height="50" alt="" src="" id="preview_pic6">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付问题描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-default" onclick="editpayhelp(6)">查看并修改</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>购买方编号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payAccount" id="up_account6">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>上级编号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="secretKey" id="up_secrect6">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="apiKey" id="up_apikey6">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>域名：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="payUrl" id="up_payurl6">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>通知URL：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="backUrl" id="up_backurl6">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付类型：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="form-control" data-live-search="true">
                                <option value="6">联付通个人支付</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">备注：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control specstr" name="payComment" id="up_comment6">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否设为默认：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open61" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open62" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open63" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open64" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditForm(6)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--ADD BY LUYONG--%>

<!-- 微信 editModal3中的3表示支付类型是微信-->
<div class="modal fade" id="editModal7"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑支付接口</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" method="post" action="updatepay.htm?CSRFToken=${token}" enctype="multipart/form-data" id="editForm7">
                    <input type="hidden" name="payId" id="payId7" />
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>支付名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payName" id="up_payname7" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">选择图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="netLogo"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img height="50" alt="" src="" id="preview_pic7">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付问题描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-default" onclick="editpayhelp(7)">查看并修改</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>公众号ID：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="apiKey" id="up_apikey7">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>app_key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="secretKey" id="up_secrect7">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>商户号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="partner" id="up_partner7">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>商户标识：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="partnerKey" id="up_partnerKey7">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>通知URL：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="backUrl" id="up_backurl7">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付类型：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="form-control" data-live-search="true">
                                <option value="3">微信支付</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">备注：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control specstr" name="payComment" id="up_comment7">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否设为默认：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open71" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open72" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open73" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open74" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditForm(7)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 银联 editModal2中的2表示支付类型是银联-->
<div class="modal fade" id="editModal8"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑支付接口</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" method="post" action="updatepay.htm?CSRFToken=${token}" enctype="multipart/form-data" id="editForm8">
                    <input type="hidden" name="payId" id="payId8" />
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>支付名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payName" id="up_payname8" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">选择图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="netLogo"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img height="50" alt="" src="" id="preview_pic8">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付问题描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-default" onclick="editpayhelp(2)">查看并修改</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>Api-Key：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="apiKey" id="up_apikey8">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>收款账号：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required specstr" name="payAccount" id="up_account8">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>后台回调地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="payUrl" id="up_payurl8">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>前台回调地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control required" name="backUrl" id="up_backurl8">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">支付类型：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select class="form-control" data-live-search="true">
                                <option>银联在线</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">备注：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control specstr" name="payComment" id="up_comment8">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否设为默认：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open81" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="payDefault" id="open82" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open83" value="1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isOpen" id="open84" value="0"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitEditForm(8)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
                    
 <div class="modal fade" id="multiArticle1"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">支付宝支付</h4>
          </div>
          <div class="modal-body">
            <div class="modal-article">
              <p><em>1.</em>首先申请<b>支付宝企业支付</b>（<a href="https://app.alipay.com" target="_blank">https://app.alipay.com</a>），申请成功后，如下图添加服务</p>
              <img src="./images/syshelp/img_01.png" alt="">
              <p><em>2.</em>“即时到账”成功添加后，进入商户服务</p>
              <img src="./images/syshelp/img_02.png" alt="">
              <p><em>3.</em>根据上图获取PID和KEY，然后到后台修改支付宝信息，如下图</p>
              <img src="./images/syshelp/img_03.png" alt="">
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>
 
 <div class="modal fade" id="multiArticle2"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">微信支付</h4>
          </div>
          <div class="modal-body">
            <div class="modal-article">
              <p><em>1.</em>首先要申请<b>微信服务号</b>，申请成功后申请微信支付，微信支付申请成功后，用户会收到一份邮件，内容如下：</p>
              <img src="./images/syshelp/img_04.png" alt="">
              <p><em>2.</em> 然后用户通过上图提供的商户平台登录账号和商户平台登录密码登录商户平台（<a href="https://mch.weixin.qq.com">https://mch.weixin.qq.com</a>），登录后到账户设置中设置秘钥，密码必须是32位。</p>
              <p><em>3.</em> 最后到后台修改微信支付内容，根据下面提示内容就行相应修改</p>
              <img src="./images/syshelp/img_05.png" alt="">
              <p><em>4.</em>最后到微信公众号后台进行对应修改</p>
              <img src="./images/syshelp/img_06.png" alt="">
              <p>添加如图所示的两个目录，最后保存</p>
              <img src="./images/syshelp/img_07.png" alt="">
              <p>最后点击保存，微信支付配置成功了</p>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>


<div class="modal fade" id="multiArticle4"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">银联支付</h4>
            </div>
            <div class="modal-body">
                <div class="modal-article">
                    <p>申请<b>企业网银支付</b>（<a href="https://static.95516.com/static/merchant/service/index.html" target="_blank">https://static.95516.com/static/merchant/service/index.html</a>）</p>
                    <img src="./images/syshelp/img_yl_001.jpg" alt="">
                    <img src="./images/syshelp/img_yl_002.jpg" alt="">
                </div>
			</div>
			<div class="modal-footer">
			    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
	 	</div>
	</div>
</div>

<div class="modal fade" id="rightinfo"  role="dialog">
  <input type="hidden" id="helpPayId">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑支付问题描述</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote" id="payHelp"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="savePayHelp('payHelp',this)">保存</button>
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
<script src="<%=basePath%>js/system/system_common.js"></script>
<script src="<%=basePath%>js/system/payset.js"></script>
</body>
</html>
