<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员列表</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
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

<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>

        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
                <input type="hidden" value="${token }" id="hi_token"/>

                <h2 class="main_title">${pageNameChild}
                    <small>(共${pageBean.rows}条)</small>
                </h2>
                <div class="common_data p20">
                    <div class="filter_area">
                        <form role="form" class="form-inline" action="initCustomer.htm" id="searchForm" method="post">
                            <input type="hidden" value="searchForm" id="formId">
                            <input type="hidden" value="initCustomer.htm" id="formName">

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">用户名</span>
                                    <input type="text" class="form-control" name="customerUsername"
                                           value="${pageBean.objectBean.customerUsername}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">真实姓名</span>
                                    <input type="text" class="form-control" name="infoRealname"
                                           value="${pageBean.objectBean.infoRealname }">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">手机号</span>
                                    <input type="text" class="form-control" name="infoMobile"
                                           value="${pageBean.objectBean.infoMobile }">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">邮箱</span>
                                    <input type="text" class="form-control" name="infoEmail"
                                           value="${pageBean.objectBean.infoEmail }">
                                </div>
                            </div>
                            <div class="form-group">
                                <%--  <label class="control-label col-sm-7">手机验证：</label>
                                 <div class="col-sm-15">
                                   <label class="radio-inline">
                                     <input type="radio" name="isMobile"  value="0" <c:if test="${pageBean.objectBean.isMobile==0 }">checked="checked"</c:if>> 未验证
                                   </label>
                                   <label class="radio-inline">
                                     <input type="radio" name="isMobile"  value="1"  <c:if test="${pageBean.objectBean.isMobile==1 }">checked="checked"</c:if>> 已验证
                                   </label>
                                 </div> --%>
                                <div class="input-group">
                                    <span class="input-group-addon">是否手机验证</span>
                                    <select class="form-control cate_selector" name="isMobile">
                                        <option value=""
                                                <c:if test="${empty pageBean.objectBean.isMobile }">selected="selected"</c:if>>
                                            是否手机验证
                                        </option>
                                        <option value="0"
                                                <c:if test="${not empty pageBean.objectBean.isMobile && pageBean.objectBean.isMobile==0 }">selected="selected"</c:if>>
                                            否
                                        </option>
                                        <option value="1"
                                                <c:if test="${not empty pageBean.objectBean.isMobile && pageBean.objectBean.isMobile==1 }">selected="selected"</c:if>>
                                            是
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <%-- <label class="control-label col-sm-7">邮箱验证：</label>
                                <div class="col-sm-15">
                                  <label class="radio-inline">
                                    <input type="radio" name="isEmail" value="0"  <c:if test="${pageBean.objectBean.isEmail==0 }">checked="checked"</c:if>> 未验证
                                  </label>
                                  <label class="radio-inline">
                                    <input type="radio" name="isEmail" value="1"  <c:if test="${pageBean.objectBean.isEmail==1 }">checked="checked"</c:if>> 已验证
                                  </label>
                                </div> --%>
                                <div class="input-group">
                                    <span class="input-group-addon">是否邮箱验证</span>
                                    <select class="form-control cate_selector" name="isEmail">
                                        <option value=""
                                                <c:if test="${empty pageBean.objectBean.isEmail }">selected="selected"</c:if>>
                                            是否邮箱验证
                                        </option>
                                        <option value="0"
                                                <c:if test="${not empty pageBean.objectBean.isEmail && pageBean.objectBean.isEmail==0 }">selected="selected"</c:if>>
                                            否
                                        </option>
                                        <option value="1"
                                                <c:if test="${not empty pageBean.objectBean.isEmail &&  pageBean.objectBean.isEmail==1 }">selected="selected"</c:if>>
                                            是
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <%-- <label class="control-label col-sm-7">账户状态</label>
                                <div class="col-sm-15">
                                  <label class="radio-inline">
                                    <input type="radio" name="isFlag" value="1"  <c:if test="${pageBean.objectBean.isFlag==1 }">checked="checked"</c:if>> 冻结
                                  </label>
                                  <label class="radio-inline">
                                    <input type="radio" name="isFlag" value="0" <c:if test="${pageBean.objectBean.isFlag==0 }">checked="checked"</c:if>> 正常
                                  </label>
                                </div> --%>
                                <div class="input-group">
                                    <span class="input-group-addon">账户状态</span>
                                    <select class="form-control cate_selector" name="isFlag">
                                        <option value=""
                                                <c:if test="${empty pageBean.objectBean.isFlag }">selected="selected"</c:if>>
                                            选择状态
                                        </option>
                                        <option value="0"
                                                <c:if test="${not empty pageBean.objectBean.isFlag && pageBean.objectBean.isFlag==0 }">selected="selected"</c:if>>
                                            正常
                                        </option>
                                        <option value="1"
                                                <c:if test="${not empty pageBean.objectBean.isFlag && pageBean.objectBean.isFlag==1 }">selected="selected"</c:if>>
                                            冻结
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <%--  <label class="control-label col-sm-7">账户类型：</label>
                                 <div class="col-sm-15">
                                   <label clasinitCustomers="radio-inline">
                                     <input type="radio" name="isSeller" value="0" <c:if test="${pageBean.objectBean.isSeller==0 }">checked="checked"</c:if>> 普通
                                   </label>
                                   <label class="radio-inline">
                                     <input type="radio" name="isSeller" value="1" <c:if test="${pageBean.objectBean.isSeller==1 }">checked="checked"</c:if>> 商家
                                   </label>
                                 </div> --%>
                                <div class="input-group">
                                    <span class="input-group-addon">账户类型</span>
                                    <select class="form-control cate_selector" name="isSeller">
                                        <option value=""
                                                <c:if test="${empty pageBean.objectBean.isSeller }">selected="selected"</c:if>>
                                            选择类型
                                        </option>
                                        <option value="0"
                                                <c:if test="${not empty pageBean.objectBean.isSeller && pageBean.objectBean.isSeller==0 }">selected="selected"</c:if>>
                                            普通
                                        </option>
                                        <option value="1"
                                                <c:if test="${not empty pageBean.objectBean.isSeller && pageBean.objectBean.isSeller==1 }">selected="selected"</c:if>>
                                            商家
                                        </option>
                                    </select>
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon">业务员</span>
                                    <select class="form-control cate_selector" name="salesmanId">
                                        <option value="0">全部</option>
                                        <c:forEach items="${enableSalaList}" var="salesman">
                                            <option value="${salesman.salesmanId}"
                                                    <c:if test="${pageBean.objectBean.salesmanId==salesman.salesmanId}">selected="selected"</c:if>>${salesman.salesmanName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">搜索</button>
                                <!-- <button type="button" class="btn btn-primary advanced_search">高级搜索</button> -->
                            </div>
                        </form>
                    </div>

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="toaddcustomer();">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="delallcustomer();">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            <!-- <button type="button" class="btn btn-info" onclick="$('#setMemberType').modal('show')">
                              设置会员类型
                            </button> -->
                            <button type="button" class="btn btn-info" onclick="openSendNote()">
                                发送通知
                            </button>
                            <button type="button" class="btn btn-info" onclick="exportList();">
                                导出会员信息
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form action="deleteAllNewCustomer.htm?CSRFToken=${token}" id="delForm" method="post">
                        <input type="hidden" value="delForm" id="formId">
                        <input type="hidden" value="deleteAllNewCustomer.htm" id="formName">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th width="10"><input type="checkbox" onclick="allunchecked(this,'customerId');"></th>
                                <th>头像</th>
                                <th>用户名</th>
                                <th>等级</th>
                                <th>手机号</th>
                                <th>邮箱</th>
                                <th>手机验证</th>
                                <th>邮箱验证</th>
                                <th>状态</th>
                                <th>类型</th>
                                <th class="w100">最后登录时间</th>
                                <th class="w50">业务员信息</th>
                                <th width="150">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${pageBean.list}" var="customer" varStatus="i">
                                <tr>
                                    <td><input type="checkbox" name="customerId" value="${customer.customerId }"></td>
                                    <td>
                                        <img class="img_circle"
                                             src="<c:if test="${customer.customerImg!='' }">${customer.customerImg}</c:if><c:if test="${customer.customerImg==null||customer.customerImg==''}"><%=basePath %>images/default_head.jpg</c:if>"
                                             height="50"/>
                                    </td>
                                    <td>
                                        <c:if test="${!empty customer.companyName}">
                                            ${customer.companyName }
                                        </c:if><c:if test="${empty customer.companyName}">
                                        ${customer.customerNickname }
                                    </c:if>
                                        <%--<c:if test="${fn:length(customer.customerUsername)>10 }">
                                            ${fn:substring(customer.customerUsername,0,9) }...
                                        </c:if>
                                        <c:if test="${fn:length(customer.customerUsername)<=10 }">
                                            ${customer.customerUsername}
                                        </c:if>--%>
                                    </td>
                                    <td>${fn:substring(customer.pointLevelName,0,2) }</td>
                                    <td>${customer.infoMobile}</td>
                                    <td>${customer.infoEmail}</td>
                                    <td>
                                        <a href="javascript:;">
                                            <c:if test="${customer.isMobile ==0}">
                                                <span class="label label-default">否</span>
                                            </c:if>
                                            <c:if test="${customer.isMobile ==1}">
                                                <span class="label label-success">是</span>
                                            </c:if>

                                        </a>
                                    </td>
                                    <td><a href="javascript:;">
                                        <c:if test="${customer.isEmail ==0}">
                                            <span class="label label-default">否</span>
                                        </c:if>
                                        <c:if test="${customer.isEmail ==1}">
                                            <span class="label label-success">是</span>
                                        </c:if>


                                    </a></td>
                                    <td>

                                        <c:if test="${customer.isFlag ==0}">
                                            <a title="点击冻结"
                                               href="updateCustomer.htm?CSRFToken=${token }&customerId=${customer.customerId }&isFlag=1&pageNo=${pageBean.pageNo }&delFlag=0"
                                               class="label label-success">正常</a>
                                        </c:if>
                                        <c:if test="${customer.isFlag ==1}">
                                            <a title="点击正常"
                                               href="updateCustomer.htm?CSRFToken=${token }&customerId=${customer.customerId }&isFlag=0&pageNo=${pageBean.pageNo }&delFlag=0"
                                               class="label label-default">冻结</a>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${customer.isSeller ==0}">
                                            普通
                                        </c:if>
                                        <c:if test="${customer.isSeller ==1}">
                                            <span class="label label-success">商家</span>
                                        </c:if>
                                    </td>
                                    <fmt:formatDate value="${customer.loginTime }" pattern="yyyy-MM-dd HH:mm:ss"
                                                    var="logindate"/>
                                    <td>${logindate }</td>
                                    <td>${customer.salesmanName }</td>

                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default"
                                                    onclick="onedit(${customer.customerId});">编辑
                                            </button>
                                            <button type="button" class="btn btn-default dropdown-toggle"
                                                    data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="javascript:;"
                                                       onclick="queryCustomer(${customer.customerId});">查看</a></li>
                                                <li><a href="javascript:void(0);"
                                                       onclick="delcustomer(${customer.customerId});">删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>
                    </form>
                    <div class="table_foot">
                        <c:import url="../page/searchPag.jsp">
                            <c:param name="pageBean" value="${pageBean }"/>
                            <c:param name="path" value="../"></c:param>
                        </c:import>

                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addMember" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加会员</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="cusForm" method="post" action="" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>用户名：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <P id="nameDiv" class="radio-inline"></P>
                            <input type="hidden" name="customerId" id="customerId"/>
                            <input type="text" class="form-control required numandletter" maxlength="20" minlength="6"
                                   name="customerUsername" id="customerUsername">
                            <label id="customerUsernametip" class="error" style="display: none">用户名已存在</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">邮箱：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <P id="emailDiv" class="radio-inline"></P>
                            <input type="text" class="form-control email" name="infoEmail" id="email">
                            <input type="hidden" id="oldemail">
                            <label id="emailtip" class="error" style="display: none">邮箱已使用</label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>密码：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <p id="passDiv" class="radio-inline"></p>
                            <input type="password" class="form-control required" maxlength="16" minlength="6"
                                   name="customerPassword" id="password">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>重复密码：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <p id="repassDiv" class="radio-inline"></p>
                            <input type="password" class="form-control required" equalTo="#password" name="repassword"
                                   id="repassword">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">会员等级：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <select class="form-control" name="pointLevelId" id="pointLevel">
                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5">预览头像：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <input name="customerImg" id="customerImg" type="hidden" value="">
                            <img alt="" id="img" src="<%=basePath %>images/default_head.jpg" height="50"> <input
                                type="button" id="choose" value="选择"/>
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:;" class="headPortrait help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">性别：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" id="gender1" value="0" name="infoGender" checked="checked"> 保密
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="gender2" value="1" name="infoGender"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="gender3" value="2" name="infoGender"> 女
                            </label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">真实姓名：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control isChineseandPonint" name="infoRealname"
                                   id="realname">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">手机号码：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <P id="mobileDiv" class="radio-inline"></P>
                            <input type="text" class="form-control mobile" name="infoMobile" id="mobile">
                            <input type="hidden" id="oldmobile">
                            <label id="mobiletip" class="error" style="display: none">手机号已使用</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">身份证号码：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control isIdCardNo" name="infoCardid" id="card_id">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">所在地区：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-15">
                            <select class="inline" data-live-search="true" name="infoProvince" id="province">
                                <option value="">选择省份</option>
                            </select>
                            <select class="inline" data-live-search="true" name="infoCity" id="city">
                                <option value="">选择城市</option>
                            </select>
                            <select class="inline" data-live-search="true" name="infoCounty" id="district">
                                <option value="">选择区县</option>
                            </select>
                            <select class="inline" data-live-search="true" name="infoStreet" id="street">
                                <option value="">选择街道</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">详细地址：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control" name="infoAddress" id="info_detail">
                        </div>
                    </div>
                    <div class="form-group" id="enableSalaList">
                        <label class="control-label col-sm-5">所属业务员：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <select class="form-control" name="salesmanId">
                                <option value="0">选择业务员</option>
                                <c:forEach items="${enableSalaList}" var="salesman">
                                    <option value="${salesman.salesmanId}">${salesman.salesmanName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="savecustomer();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="setMemberType" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">设置会员类型</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-5">设置会员类型：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions2" id="inlineRadio5" value="option1"> 普通会员
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions2" id="inlineRadio6" value="option2"> 前台管理员
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

<!-- Modal -->
<div class="modal fade" id="sendMessages" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">发送通知</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" enctype="multipart/form-data" action="" id="sendForm">
                    <div class="form-group">
                        <label class="control-label col-sm-5">标题：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="letterTitle" id="letterTitle">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">内容：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <textarea class="form-control required" rows="5" name="letterContent"
                                      id="letterContent"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="subSend();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="scanMember" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">查看会员详情</h4>
            </div>
            <div class="modal-body">
                <iframe id="mainFrame" frameborder="0" style="width:100%;" src=""></iframe>

            </div>


        </div>
    </div>
</div>


<div class="advanced_search_cont none">
    <div class="advanced_search_form">
        <form class="form-horizontal" action="initCustomer.htm" method="post">
            <div class="form-group">
                <label class="control-label col-sm-7">用户名：</label>

                <div class="col-sm-15">
                    <input type="text" class="form-control" name="customerUsername"
                           value="${pageBean.objectBean.customerUsername }">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-7">姓名：</label>

                <div class="col-sm-15">
                    <input type="text" class="form-control" name="infoRealname"
                           value="${pageBean.objectBean.infoRealname }">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-7">手机号：</label>

                <div class="col-sm-15">
                    <input type="text" class="form-control" name="infoMobile"
                           value="${pageBean.objectBean.infoMobile }">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-7">邮箱：</label>

                <div class="col-sm-15">
                    <input type="text" class="form-control" name="infoEmail" value="${pageBean.objectBean.infoEmail }">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-7">手机验证：</label>

                <div class="col-sm-15">
                    <label class="radio-inline">
                        <input type="radio" name="isMobile" value="0"
                               <c:if test="${pageBean.objectBean.isMobile==0 }">checked="checked"</c:if>> 未验证
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="isMobile" value="1"
                               <c:if test="${pageBean.objectBean.isMobile==1 }">checked="checked"</c:if>> 已验证
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-7">邮箱验证：</label>

                <div class="col-sm-15">
                    <label class="radio-inline">
                        <input type="radio" name="isEmail" value="0"
                               <c:if test="${pageBean.objectBean.isEmail==0 }">checked="checked"</c:if>> 未验证
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="isEmail" value="1"
                               <c:if test="${pageBean.objectBean.isEmail==1 }">checked="checked"</c:if>> 已验证
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-7">账户状态：</label>

                <div class="col-sm-15">
                    <label class="radio-inline">
                        <input type="radio" name="isFlag" value="1"
                               <c:if test="${pageBean.objectBean.isFlag==1 }">checked="checked"</c:if>> 冻结
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="isFlag" value="0"
                               <c:if test="${pageBean.objectBean.isFlag==0 }">checked="checked"</c:if>> 正常
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-7">账户类型：</label>

                <div class="col-sm-15">
                    <label class="radio-inline">
                        <input type="radio" name="isSeller" value="0"
                               <c:if test="${pageBean.objectBean.isSeller==0 }">checked="checked"</c:if>> 普通
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="isSeller" value="1"
                               <c:if test="${pageBean.objectBean.isSeller==1 }">checked="checked"</c:if>> 商家
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-7 col-sm-15">
                    <button type="submit" class="btn btn-primary btn-sm">确认搜索</button>
                </div>
            </div>
        </form>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script type="text/javascript" src="<%=basePath%>js/customer/customer.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script>
    var modified = 0;
    var bValid = true;
    var oldemail = $("#oldemail").val();
    var oldmobile = $("#oldmobile").val();
    $(function () {
        $("#cusForm").validate();
        $("#sendForm").validate();
        /* 表单项的值点击后转换为可编辑状态 */
        $('.form_value').click(function () {
            var formItem = $(this);
            if (!$('.form_btns').is(':visible')) {
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

        /* 选择规格值 */
        $('.spec_set input').change(function () {
            if ($(this).is(':checked')) {
                $(this).parent().parent().next().slideDown('fast');
            }
            else {
                $(this).parent().parent().next().slideUp('fast');
            }
        });

        /* 下面是表单里面的填写项提示相关的 */
        $('.zhekoulv').popover({
            content: '有效值0~1,如果输入0.85,表示该会员等级以销售价85折购买商品',
            trigger: 'hover'
        });
        $('.morendengji').popover({
            content: '如果选择"是",顾客注册会员时,初始等级为当前等级',
            trigger: 'hover'
        });
        $('.suoxujifen').popover({
            content: '按积分升级时,会员积分达到此标准后会自动升级为当前等级',
            trigger: 'hover'
        });


        $("#choose").click(function () {
            i = 1;
            art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=10000', {
                lock: true,
                opacity: 0.3,
                width: '900px',
                height: '400px',
                title: '选择图片'
            });
        });


        /* 高级搜索 */
        $('.advanced_search').popover({
            html: true,
            title: '高级搜索',
            content: $('.advanced_search_cont').html(),
            trigger: 'click',
            placement: 'bottom'
        });
    });

    function openSendNote() {
        if (checkSelected("customerId")) {
            $('#sendMessages').modal('show')
        }
        else {
            showTipAlert("请至少选择一条记录")
        }

    }

    //检查是否选中一行
    var checkedList;
    function checkSelected(objId) {
        checkedList = new Array();
        $("input[name='" + objId + "']:checked").each(function () {
            checkedList.push($(this).val());
        });
        if (checkedList.length > 0) {
            return true;
        } else {
            return false;
        }
    }
    ;

    //图片回调
    function saveChoooseImage(url) {
        if (typeof (url) != 'string') {
            url = url[0];
        }
        if (url.indexOf(',') != -1) {
            url = url.substring(0, url.indexOf(','));
        }
        $("#img").attr("src", url);
        $("#customerImg").val(url);

    }


    //弹出添加会员
    function toaddcustomer() {
        modified = 0;
        $("input[id^='customerUsername']").val('');
        $('#customerUsername').attr('readonly', 'readonly');
        $('#customerUsername').show();
        $('#password').show();
        $('#repassword').show();
        $("#nameDiv").hide();
        $("#passDiv").hide();
        $("#repassDiv").hide();
        $('#customerUsername').removeAttr('readonly');
        $('#customerUsername').removeClass('error');
        $("#customerUsernametip").hide();
        $('#email').removeClass('error');
        $("#emailtip").hide();
        $('#mobile').removeClass('error');
        $("#mobiletip").hide();
        $("#email").val('');
        $("#password").val("");
        $('#password').removeAttr('readonly');
        //$('#enableSalaList').attr("display","none");
        $('#enableSalaList').hide();
        $("#repassword").val("");
        $('#repassword').removeAttr('readonly');
        $("#mobile").val('');
        $("#img").attr("src", 'images/default_head.jpg');
        $("#customerImg").val('');
        $('#gender1').prop('checked', 'checked');
        //将收货地址 省份 城市 区县 选中
        $("#realname").val('');
        $("#mobile").val('');
        $("#card_id").val('');
        $("#info_detail").val('');
        $("#customerId").val('');

        $('#cusForm').attr('action', 'addCustomer.htm?CSRFToken=' + $("#hi_token").val());
        fillPandL();
        $('.modal-title').text("添加会员");
        $('#addMember').modal('show');
        c = 0;
    }

    //弹出修改会员框
    function onedit(customerId) {
        c = 0;
        modified = 1;
        $('#customerUsername').removeClass('error');
        $("#customerUsernametip").hide();
        $('#email').removeClass('error');
        $("#emailtip").hide();
        $('#mobile').removeClass('error');
        $("#mobiletip").hide();
        $('#cusForm').attr('action', 'updateCustomer.htm?CSRFToken=' + $("#hi_token").val());
        doSearchCustomer(customerId);
        $('.modal-title').text("修改会员");
        //$('#enableSalaList').show();
        $('#addMember').modal('show');
        // $("#cusForm").valid();
    }
    //.保存会员
    var c = 0;
    function savecustomer() {
        check();
        if (bValid && $("#cusForm").valid() && c == 0) {
            c = 1;
            $("#cusForm").submit();
        }

    }

    //删除会员信息
    function delcustomer(customerId) {
        $.post("queryByCustomerId.htm?CSRFToken=${token}&customerId=" + customerId, function (data) {
            if (data[0].orders != null && data[0].orders != "") {
                showTipAlert("删除的会员有订单,不允许删除");
                return;
            }
            showDeleteOneConfirmAlert('deleteNewCustomer.htm?CSRFToken=${token}&customerId=' + customerId, '确定要删除此用户吗？');
        });

    }


    //批量删除会员信息
    function delallcustomer() {
        var ids = new Array();
        var flag = true;
        $("input[name='customerId']:checked").each(function (i) {
            ids[i] = $(this).val();
        });
        jQuery.ajax({
            url: "queryByCustomerId.htm?CSRFToken=${token}&customerIds=" + ids,
            dataType: "json",
            type: "POST",
            success: function (data) {
                $.each(data, function (i, result) {
                    if (data[i].orders != null && data[i].orders != "") {
                        showTipAlert("批量删除的会员当中有订单,不允许删除!");
                        flag = false;
                        return;
                    }
                });
                if (flag) {
                    showDeleteBatchConfirmAlert('delForm', 'customerId', '确定要删除所选中的用户吗？');
                }

            }
        });

    }


    function subSend() {
        if ($("#sendForm").valid()) {
            var datas = "1=1";
            datas += "&letterTitle=" + $("#letterTitle").val();
            datas += "&letterContent=" + $("#letterContent").val();
            datas += "&customerIds=" + checkedList;
            jQuery.ajax({
                url: "insertNotices.htm?CSRFToken=${token}",
                data: datas,
                dataType: "json",
                type: "POST",
                success: function (html) {
                    if (html >= 1) {
                        $("#letterTitle").val('');
                        $("#letterContent").val('');
                        $('#sendMessages').modal('hide');
                        showTipAlert('发送通知成功！');
                    } else {
                        $('#sendMessages').modal('hide');
                        showTipAlert('发送通知失败！');
                    }
                }
            });
        }

    }


    function queryCustomer(customerId) {
        $("#mainFrame").attr("src", 'queryCustomer.htm?CSRFToken=${token}&customerId=' + customerId);
        $('#scanMember').modal('show');
        $('#mainFrame').css('minHeight', '450px');
    }

    function check() {
        if (modified != 1) {
            bValid = checkExist("customerUsername", $("#customerUsername").val(), "checkExistCustomerUsername.htm", "用户名") && bValid;
            if ($("#email").val() != "") {
                bValid = checkExist("email", $("#email").val(), "checkbossemailexist.htm", "邮箱") && bValid;
            }
            if ($("#mobile").val() != "") {
                bValid = checkExist("mobile", $("#mobile").val(), "checkbossmobileexist.htm", "手机号") && bValid;
            }
        } else {
            if ($("#email").val() != "") {
                if (oldemail != $("#email").val()) {
                    bValid = checkExist("email", $("#email").val(), "checkbossemailexist.htm", "邮箱") && bValid;
                }
            }
            if ($("#mobile").val() != "") {
                if (oldmobile != $("#mobile").val()) {
                    bValid = checkExist("mobile", $("#mobile").val(), "checkbossmobileexist.htm", "手机号") && bValid;
                }
            }
            if (oldemail == $("#email").val() && oldmobile == $("#mobile").val()) {
                bValid = true;
            }
        }
    }

    function exportList() {
        window.location.href = "exportallcustomer.htm?CSRFToken=${token }";
    }
</script>
</body>
</html>
