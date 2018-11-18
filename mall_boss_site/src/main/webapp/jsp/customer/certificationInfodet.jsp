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
                        <h1>会员认证信息详情和认证操作:</h1>
                    </div>

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-center">

                        </div>
                        <div class="clr"></div>
                    </div>

                    <input type="hidden" value="delForm" id="formId">
                    <input type="hidden" value="deleteAllNewCustomer.htm" id="formName">
                    <table class="table table-striped table-hover">


                        <tr>

                            <td width="50%" align="right">编号:</td>
                            <td width="50%" width="60%" align="left">${qpEnterpriseCertificationInfo.enterpriseId }</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right"> 会员账号:</td>
                            <td width="50%" width="60%" align="left">${qpEnterpriseCertificationInfo.customerUsername }</td>
                        </tr>

                        <tr>
                            <td width="50%" align="right">公司名称:</td>
                            <td width="50%" width="60%" align="left">${qpEnterpriseCertificationInfo.companyName }</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">公司详细地址:</td>
                            <td width="50%" width="60%"
                                align="left">(${qpEnterpriseCertificationInfo.companyAddress })${qpEnterpriseCertificationInfo.companyContactAddr }</td>
                        </tr>

                        <tr>
                            <td width="50%" align="right">注册资金:</td>
                            <td width="50%" width="60%"
                                align="left">
                                <c:if test="${qpEnterpriseCertificationInfo.companyCapital!=null&&(fn:trim(qpEnterpriseCertificationInfo.companyCapital))!='' }">
                                    ${qpEnterpriseCertificationInfo.companyCapital }万元
                                </c:if>
                            </td>
                        </tr>

                        <tr>
                            <td width="50%" align="right">电子邮箱:</td>
                            <td width="50%" width="60%" align="left">${qpEnterpriseCertificationInfo.companyEmail }</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">企业类型:</td>
                            <td width="50%" align="left">
                                <c:choose>
                                    <c:when test="${qpEnterpriseCertificationInfo.companyType=='1' }">
                                        维修厂
                                    </c:when>
                                    <c:when test="${qpEnterpriseCertificationInfo.companyType=='2' }">
                                        4s店
                                    </c:when>
                                    <c:when test="${qpEnterpriseCertificationInfo.companyType=='3' }">
                                        快修链锁
                                    </c:when>
                                    <c:when test="${qpEnterpriseCertificationInfo.companyType=='4' }">
                                        经销商
                                    </c:when>
                                    <c:otherwise>
                                        不详
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td width="50%">联系人姓名:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.companyContactName }</td>
                        </tr>
                       <%-- <tr>
                            <td width="50%">法人身份证URL:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.cardUrl }</td>
                        </tr>--%>

                        <tr>
                            <td width="50%">联系人手机:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.company_contact_moble }</td>
                        </tr>
                        <tr>
                            <td width="50%">固定电话:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.companyContactTel }</td>
                        </tr>

                        <tr>
                            <td width="50%">法人代表:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.bussLegalName }</td>
                        </tr>
                        <tr>
                            <td width="50%">法人身份证编号:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.bussLegalCardId }
                            </td>
                        </tr>

                        <tr>
                            <td width="50%">经营范围:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.bussRange }</td>
                        </tr>
                        <tr>
                            <td width="50%">审核状态:</td>
                            <td width="50%" align="left">
                                <c:choose>
                                    <c:when test="${qpEnterpriseCertificationInfo.checkStatus=='0' }">
                                        待审核
                                    </c:when>
                                    <c:when test="${qpEnterpriseCertificationInfo.checkStatus=='1' }">
                                        已通过
                                    </c:when>
                                    <c:when test="${qpEnterpriseCertificationInfo.checkStatus=='2' }">
                                        已驳回
                                    </c:when>
                                    <c:otherwise>
                                        其他
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <c:choose>
                            <c:when test="${qpEnterpriseCertificationInfo.checkStatus=='1' }">
                                <tr>
                                    <td width="50%">审核人:</td>
                                    <td width="50%" align="left">${qpEnterpriseCertificationInfo.auditManager}</td>
                                </tr>
                                <tr>
                                    <td width="50%">审核时间:</td>
                                    <td width="50%" align="left"><fmt:formatDate value="${qpEnterpriseCertificationInfo.auditTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="50%">反馈意见:</td>
                                    <td width="50%" align="left">${qpEnterpriseCertificationInfo.auditFeedback}</td>
                                </tr>
                            </c:when>
                            <c:when test="${qpEnterpriseCertificationInfo.checkStatus=='2' }">
                                <tr>
                                    <td width="50%">审核人:</td>
                                    <td width="50%" align="left">${qpEnterpriseCertificationInfo.auditManager}</td>
                                </tr>
                                <tr>
                                    <td width="50%">审核时间:</td>
                                    <td width="50%" align="left"><fmt:formatDate value="${qpEnterpriseCertificationInfo.auditTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="50%">反馈意见:</td>
                                    <td width="50%" align="left">${qpEnterpriseCertificationInfo.auditFeedback}</td>
                                </tr>
                            </c:when>
                        </c:choose>
                       <%--<c:if test="${QpEnterpriseCertificationInfo.checkStatus=='2' }">
                           <tr>
                               <td width="50%">审核人:</td>
                               <td width="50%" align="left">${QpEnterpriseCertificationInfo.auditManager}</td>
                           </tr>
                           <tr>
                               <td width="50%">审核时间:</td>
                               <td width="50%" align="left"><fmt:formatDate value="${QpEnterpriseCertificationInfo.auditTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                               </td>
                           </tr>
                           <tr>
                               <td width="50%">反馈意见:</td>
                               <td width="50%" align="left">${QpEnterpriseCertificationInfo.auditFeedback}</td>
                           </tr>
                       </c:if>
                        <c:if test="${QpEnterpriseCertificationInfo.checkStatus=='1'}">
                            <tr>
                                <td width="50%">审核人:</td>
                                <td width="50%" align="left">${QpEnterpriseCertificationInfo.auditManager}</td>
                            </tr>
                            <tr>
                                <td width="50%">审核时间:</td>
                                <td width="50%" align="left"><fmt:formatDate value="${QpEnterpriseCertificationInfo.auditTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="50%">反馈意见:</td>
                                <td width="50%" align="left">${QpEnterpriseCertificationInfo.auditFeedback}</td>
                            </tr>
                        </c:if>--%>

                        <tr>
                            <td width="50%">企业资质:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.enterpriseAptitude }</td>
                        </tr>
                        <tr>
                            <td width="50%">创建时间:</td>
                            <td width="50%" align="left"><fmt:formatDate value="${qpEnterpriseCertificationInfo.createTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                        <tr>
                            <td width="50%">修改时间:</td>
                            <td width="50%" align="left"><fmt:formatDate value="${qpEnterpriseCertificationInfo.modTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                        <c:set value="${ fn:split(qpEnterpriseCertificationInfo.companyAddress,',') }"
                               var="companyAddressdel"/>
                        <tr>
                        <tr>
                            <td width="50%">企业所在省份:</td>
                            <td width="50%" align="left">${companyAddressdel[0]}
                            </td>
                        </tr>

                            <td width="50%">企业所在城市:</td>
                            <td width="50%" align="left">${companyAddressdel[1]}</td>
                        </tr>
                        <tr>
                            <td width="50%">企业所在区:</td>
                            <td width="50%" align="left">${companyAddressdel[2]}</td>
                        <tr>
                            <td width="50%">经营品牌:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.bussBrand }</td>
                        </tr>

                        <%--<tr>
                            <td width="50%">联系人通讯地址:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.companyContactAddr }</td>
                        </tr>--%>
                        <tr>
                            <td width="50%">传真:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.companyContactCz }</td>
                        </tr>
                        <tr>
                            <td width="50%">邮编:</td>
                            <td width="50%" align="left">${qpEnterpriseCertificationInfo.companyContactYb }</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">门头照片:</td>

                            <td width="50%" align="left">

                                <c:set value="${ fn:split(qpEnterpriseCertificationInfo.companyPicUrl,'##') }"
                                       var="companyPicUrluls"/>

                                <ul id="companyPicUrlul">

                                    <c:forEach items="${ companyPicUrluls }" var="companyPicUrlul">
                                        <li style="display: inline;float: left">
                                            <div align="center">
                                                <a href="${companyPicUrlul }" title="点击查看门头照片详图"
                                                   target="_blank"><img src=" ${ companyPicUrlul }  "
                                                     width="140px" height="96px"/></a>
                                            </div>
                                        </li>

                                    </c:forEach>

                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">证件照:</td>
                            <td width="50%" align="left">
                                <ul>
                                    <li style="display: inline;float: left">
                                        <div class="qp-papers" id="buss_tax_registdiv" align="center">
                                            <a href="${qpEnterpriseCertificationInfo.busscardUrl }" title="点击查看营业执照详图"
                                               target="_blank"><img src="${qpEnterpriseCertificationInfo.busscardUrl }"
                                                                    width="140px" height="96px" alt=""/></a>
                                        </div>
                                        <p>营业执照</p>
                                    </li>
                                    <li style="display: inline;float: left">

                                        <div class="qp-papers" align="center">
                                            <a href="${qpEnterpriseCertificationInfo.bussDeptNoUrl }" title="点击查看组织机构代码详图"
                                               target="_blank"><img
                                                    src="${qpEnterpriseCertificationInfo.bussDeptNoUrl }"
                                                    width="140px" height="96px" alt="组织机构代码"/></a>
                                        </div>
                                        <p>组织机构代码</p>
                                    </li>
                                    <li style="display: inline;float: left">

                                        <div class="qp-papers" align="center">
                                            <a href="${qpEnterpriseCertificationInfo.bussTaxRegistUrl }" title="点击查看税务登记证详图"
                                               target="_blank"><img
                                                    src="${qpEnterpriseCertificationInfo.bussTaxRegistUrl }"
                                                    width="140px" height="96px" alt="税务登记证"/></a>
                                        </div>
                                        <p>税务登记证</p>
                                    </li>
                                </ul>
                            </td>
                            <%--<tr>
                                <td width="50%">组织机构代码Url:</td>
                                <td width="50%" align="left">
                                    <div class="qp-papers" align="center">
                                        <img src="${qpEnterpriseCertificationInfo.bussDeptNoUrl }"
                                             width="140" height="100" alt="税务登记证"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="50%">税务登记证Url:</td>
                                <td width="50%" align="left">
                                    <div class="qp-papers" align="center">
                                        <img src="${qpEnterpriseCertificationInfo.bussTaxRegistUrl }"
                                             width="140" height="100" alt="税务登记证"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="50%">营业执照电子版:</td>
                                <td width="50%" align="left">
                                    <div class="qp-papers" id="buss_tax_registdiv" align="center">
                                        <img src="${qpEnterpriseCertificationInfo.busscardUrl }"
                                             width="140" height="100" alt="税务登记证"/>
                                    </div>
                                </td>
                            </tr>--%>
                         <c:if test="${qpEnterpriseCertificationInfo.checkStatus=='0' }">
                        <tr>
                            <td width="50%">反馈意见:</td>
                            <td width="50%" align="left">
                                <form id="enterpriseExamine" action="<%=basePath%>/enterpriseExamine.htm">
                                    <textarea  id="auditFeedback"  rows="5" cols="50" name="auditFeedback">

                                    </textarea>
                                    <input type="hidden" id="setstatus" name="checkStatus"  />
                                    <input type="hidden" id="enterpriseId" name="enterpriseId"  value="${qpEnterpriseCertificationInfo.enterpriseId}"/>

                                </form>
                            </td>
                        </tr>
                            </c:if>
                        <tr>
                            <td width="50%" align="left" colspan="2">
                                <c:if test="${qpEnterpriseCertificationInfo.checkStatus=='0'}">

                                    <a href="#"
                                      onclick="enterpriseExamine(1)" class="btn btn-info">认证通过</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="#"
                                       onclick="enterpriseExamine(2)"  class="btn btn-info">认证驳回</a>
                                    &nbsp;&nbsp;&nbsp;<input type="button" class="btn btn-info" value="返回"
                                    onclick="javascript:history.back();"/>
                                </c:if>
                                <c:if test="${qpEnterpriseCertificationInfo.checkStatus!='0'}">
                                    <input type="button" class="btn btn-info" value="返回"
                                           onclick="javascript:history.back();"/>
                                </c:if>
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
function enterpriseExamine(status){
   if($("#enterpriseExamine")!=null&&$("#enterpriseExamine")!=""){
       $("#setstatus").val(status);
       $("#enterpriseExamine").submit();
   }

}


</script>
</body>
</html>
