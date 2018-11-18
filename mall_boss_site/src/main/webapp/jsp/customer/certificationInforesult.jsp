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
    <title>企业审核</title>

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
                    <small></small>
                </h2>
                <div class="common_data p20">
                    <div class="filter_area">
                        <h1>企业认证结果:</h1>
                    </div>

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">

                        </div>
                        <div class="clr"></div>
                    </div>

                    <input type="hidden" value="delForm" id="formId">
                    <input type="hidden" value="deleteAllNewCustomer.htm" id="formName">
                    <table class="table table-striped table-hover">
                        <tr>
                            <td align="center">
                        <h2>认证结果:</h2>
                        <h3>
                        ${resultInfos.resultInfo}</h3>
                        <c:choose>
                        <c:when test="${resultInfos.checkStatus=='1'}">
                        <a class="btn btn-info" href="<%=path %>/queryEnterpriseAllInfoIsEn.htm?checkStatus=1" >
                            返回已审核列表
                        </a>
                        </c:when>
                        <c:when test="${resultInfos.checkStatus=='2'}">
                        <a class="btn btn-info" href="<%=path %>/queryEnterpriseAllInfoIsEn.htm?checkStatus=2" >
                            返回已驳回列表
                        </a>
                        </c:when>
                        <c:otherwise>
                        <a class="btn btn-info" href="<%=path %>/queryEnterpriseAllInfos.htm" >
                            返回企业认证信息列表
                        </a>
                        </c:otherwise>
                        </c:choose>
                        </td>
                        </tr>
                            </table>

                    <div class="table_foot">
                        <%--<c:import url="../page/searchPag.jsp">
                            <c:param name="pageBean" value="${pageBean }"/>
                            <c:param name="path" value="../"></c:param>
                       </c:import>--%>

                    </div>

                </div>

            </div>
        </div>
    </div>
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
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script type="text/javascript" src="<%=basePath%>js/customer/customer.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script>

</script>
</body>
</html>
