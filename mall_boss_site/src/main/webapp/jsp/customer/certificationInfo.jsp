<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>企业认证信息列表</title>

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

                    </div>

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <!-- <button type="button" class="btn btn-info" onclick="$('#setMemberType').modal('show')">
                              设置会员类型
                            </button> -->
                            <a class="btn btn-info" href="<%=path %>/queryEnterpriseAllInfos.htm?menuId=4&menuParentId=5&myselfId=3126">
                                全部会员
                            </a>
                            <a class="btn btn-info" href="<%=path %>/queryEnterpriseAllInfoIsEn.htm?checkStatus=1">
                                已审核列表
                            </a>
                            <a class="btn btn-info" href="<%=path %>/queryEnterpriseAllInfoIsEn.htm?checkStatus=0">
                                待审核列表
                            </a>
                            <a class="btn btn-info" href="<%=path %>/queryEnterpriseAllInfoIsEn.htm?checkStatus=2">
                                已驳回列表
                            </a>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form action="deleteAllNewCustomer.htm?CSRFToken=${token}" id="delForm" method="post">
                        <input type="hidden" value="delForm" id="formId">
                        <input type="hidden" value="deleteAllNewCustomer.htm" id="formName">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <%--Edit By 付陈林 Time 2015年11月27日，--%>
                                <%--Note:修改了列显示的宽度。--%>
                                <%--<th width="50">编号</th>--%>
                                <th width="10%">会员账号</th>
                                <th width="20%">公司名称</th>
                                <th width="30%">公司详细地址</th>
                                <th width="10%">注册资金</th>
                                <%--<th>电子邮箱</th>--%>
                               <%-- <th>固定电话</th>--%>
                               <%-- <th width="64">法人代表</th>--%>
                               <%-- <th>法人身份证编号</th>--%>
                                <th width="10%">审核状态</th>
                                <th width="10%">审核操作</th>
                                <%--<th width="10%">删除</th>--%>
                                <%--End edit by付陈林--%>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${qpEnterpriseCertificationInfos}" var="QpEnterpriseCertificationInfo"
                                       varStatus="i">
                                <tr>
                                    <%--<td>${QpEnterpriseCertificationInfo.enterpriseId }</td>--%>
                                    <td>${QpEnterpriseCertificationInfo.customerUsername }</td>
                                    <td>${QpEnterpriseCertificationInfo.companyName }</td>
                                    <td>(${QpEnterpriseCertificationInfo.companyAddress })${QpEnterpriseCertificationInfo.companyContactAddr}</td>
                                    <td>
                                        <c:if test="${QpEnterpriseCertificationInfo.companyCapital!=null&&QpEnterpriseCertificationInfo.companyCapital!='' }">
                                            ${QpEnterpriseCertificationInfo.companyCapital }
                                            万元
                                        </c:if>

                                    </td>
                                    <%--<td>${QpEnterpriseCertificationInfo.companyEmail }</td>--%>
                                    <%--<td>${QpEnterpriseCertificationInfo.companyContactTel }</td>--%>
                                    <%--<td>${QpEnterpriseCertificationInfo.bussLegalName }</td>--%>
                                    <%--<td>${QpEnterpriseCertificationInfo.bussLegalCardId }</td>--%>
                                    <td>
                                        <c:choose>
                                            <c:when test="${QpEnterpriseCertificationInfo.checkStatus=='0' }">
                                                待审核
                                            </c:when>
                                            <c:when test="${QpEnterpriseCertificationInfo.checkStatus=='1' }">
                                                已通过
                                            </c:when>
                                            <c:when test="${QpEnterpriseCertificationInfo.checkStatus=='2' }">
                                                已驳回
                                            </c:when>
                                            <c:otherwise>
                                                其他
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${QpEnterpriseCertificationInfo.checkStatus=='0' }">
                                            <div class="btn-group">
                                                <a type="button" class="btn btn-default"
                                                   href="<%=path %>/queryEnterpriseAllInfoById.htm?enterpriseId=${QpEnterpriseCertificationInfo.enterpriseId }">
                                                    查看审核
                                                </a>
                                            </div>
                                        </c:if>

                                        <c:if test="${QpEnterpriseCertificationInfo.checkStatus!='0' }">
                                            <div class="btn-group">
                                                <a type="button" class="btn btn-default"
                                                   href="<%=path %>/queryEnterpriseAllInfoById.htm?enterpriseId=${QpEnterpriseCertificationInfo.enterpriseId }">
                                                    查看详情
                                                </a>
                                            </div>
                                        </c:if>

                                    </td>
                                    <%--<td>
                                        <div class="btn-group">
                                            <a type="button" class="btn btn-default"
                                               href="<%=path %>/deleteEnterpriseAllInfoById.htm?enterpriseId=${QpEnterpriseCertificationInfo.enterpriseId }">
                                                删除
                                            </a>
                                        </div>
                                    </td>--%>
                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>
                    </form>
                    <div class="table_foot">
                        <c:import url="../page/paging.jsp">
                            <c:param name="pageBean" value="${pageBean }"/>
                            <c:param name="path" value="../"></c:param>
                        </c:import>

                    </div>

                </div>

            </div>
        </div>
    </div>
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

</script>
</body>
</html>
