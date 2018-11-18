
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
    <title>店铺详细</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
      body{background:none}
    </style>
  </head>
  <body>
           <div class="common_info common_tabs mt20">
              <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#shop1" aria-controls="tab1" role="tab" data-toggle="tab">详细信息</a></li>
              </ul>
              <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="shop1">
                  <div class="rec_example">
                    <div class="container-fluid">
                      <div class="row">
                        <h4>商家信息</h4>
                        <div class="col-sm-8">
                          <p>商家编号：${info.storeId}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>店铺名称：${info.storeName }</p>
                        </div>
                      </div>
                      <div class="row">
                        <h4>公司营业执照信息（副本）</h4>
                        <div class="col-sm-8">
                          <p>公司名称：${info.companyName}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>注册号(营业执照号)：${info.bussId}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>成立日期：${(info.companyCreTime)}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>营业期限：${fn:split(info.bussDate,',')[0] } 至 ${fn:split(info.bussDate,',')[1] }</p>
                        </div>
                        <div class="col-sm-8">
                          <p>法定代表人姓名：${info.bussLegalName}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>法人身份证电子版：
                                <c:if test="${fn:length(info.cardUrl)>0 }"><a href="${info.cardUrl}" target="_blank">[查看]</a></c:if>
                            	<c:if test="${fn:length(info.cardUrl)<=0 }">[无]</c:if>
                          </p>
                        </div>
                        <div class="col-sm-8">
                          <p>注册资本：${info.companyCapital}万元</p>
                        </div>
                        <div class="col-sm-8">
                          <p>营业执照副本电子版：<c:if test="${fn:length(info.bussUrl)>0 }"><a href="${info.bussUrl}" target="_blank" >[查看]</a></c:if>
                            	          <c:if test="${fn:length(info.bussUrl)<=0 }">[无]</c:if>	</p>
                        </div>
                        <div class="col-sm-8">
                          <p>经营范围：${info.bussRange}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>公司电话：${info.companyTel}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>公司地址：${fn:split(info.companyAddr,',')[0] }
                            	${fn:split(info.companyAddr,',')[1] }
                            	${fn:split(info.companyAddr,',')[2] }
                            	${info.companyAddrDel}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>公司联系人：${info.companyContactName}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>详细地址：${fn:split(info.companyAddr,',')[0] }
                            	${fn:split(info.companyAddr,',')[1] }
                            	${fn:split(info.companyAddr,',')[2] }
                            	${info.companyAddrDel}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>联系人电话：${info.companyContactTel}</p>
                        </div>
                          <div class="col-sm-8">
                              <p>电子邮箱：${info.companyEmail}</p>
                          </div>
                          <div class="col-sm-8">
                              <p>身份证号：${info.bussLegalCardId}</p>
                          </div>
                          <div class="col-sm-8">
                              <p>营业执照所在地：${address}</p>
                          </div>

                          <div class="col-sm-8">
                              <p>营业执照详细地址：${info.bussAddr}</p>
                          </div>
                          <div class="col-sm-8">
                              <p>员工总数：${info.companyEmpNum}</p>
                          </div>
                        </div>
                      <div class="row">
                        <h4>公司组织机构代码证</h4>
                        <div class="col-sm-8">
                          <p>组织机构代码：${info.bussDeptNo}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>组织机构代码证电子版：
                          		<c:if test="${fn:length(info.companyResearchUrl)>0 }"><a href="${info.companyResearchUrl}" target="_blank">[查看]</a></c:if>
                            	<c:if test="${fn:length(info.companyResearchUrl)<=0 }">[无]</c:if>
                          </p>
                        </div>
                      </div>
                      <div class="row">
                        <h4>公司税务登记证</h4>
                        <div class="col-sm-8">
                          <p>税务人识别号：${info.bussTaxPayerId}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>税务登记证号： ${info.bussTaxRegistId}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>税务登记证电子版：
                          		<c:if test="${fn:length(info.bussTaxRegistUrl)>0 }"><a href="${info.bussTaxRegistUrl}" target="_blank">[查看]</a></c:if>
                            	<c:if test="${fn:length(info.bussTaxRegistUrl)<=0 }">[无]</c:if>
                           </p>
                        </div>
                        <div class="col-sm-8">
                          <p>一般纳税人资格证电子版：
                          		<c:if test="${fn:length(info.bussTaxCredUrl)>0 }"><a href="${info.bussTaxCredUrl}" target="_blank">[查看]</a></c:if>
                            	<c:if test="${fn:length(info.bussTaxCredUrl)<=0 }">[无]</c:if>
                          </p>
                        </div>
                      </div>
                      <div class="row">
                        <h4>结算银行信息</h4>
                        <div class="col-sm-8">
                          <p>银行开户名：${info.bankUsername}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>公司银行账号：${info.bankCardId}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>开户行支行名称：${info.bankName}</p>
                        </div>
                        <div class="col-sm-8">
                          <p>开户行支行联行号：${info.bankId}</p>
                        </div>
                          <div class="col-sm-8">
                          <p>开户行支行所在地：${fn:replace(info.bankAddr, ',', '-')}</p>
                      </div>
                        <div class="col-sm-8">
                          <p>银行开户许可证电子版：
                          		<c:if test="${fn:length(info.bankUrl)>0 }"><a href="${info.bankUrl}" target="_blank">[查看]</a></c:if>
                            	<c:if test="${fn:length(info.bankUrl)<=0 }">[无]</c:if>
                          </p>
                        </div>
                      </div>
                      <div class="row">
                        <h4>签约分类</h4>
                        <c:if test="${thirdcate!=null}">
                   		  <c:forEach items="${thirdcate}" var="cate" varStatus="i">
	                            <div class="col-sm-4">
                                	<p>${cate.catName}</p>
                                </div>
	                    </c:forEach>
                    	</c:if>
                      </div>
                      <div class="row">
                        <h4>签约品牌</h4>
                        <c:if test="${thirdbrand!=null}">
                   		   <c:forEach items="${thirdbrand}" var="brand" varStatus="i">
	                             <div class="col-sm-4">
                               		<p>${brand.brandName}</p>
                                 </div>
	                    	</c:forEach>
                    	</c:if>
                      </div>
                      <div class="row">
                        <h4>申请自定义品牌</h4>
                        <c:if test="${appbrand!=null}">
	                    	<c:forEach items="${appbrand}" var="brand" varStatus="i">
	                    	<div class="col-sm-6">
	                    	  	 <dd style="width:210px;">&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;称:${brand.applyBrandName}</dd>
			                     <%--<dd>网址：${brand.applyUrl}</dd> --%>
			                     <dd style="width:210px;">品牌图片:<img src="${baseUrl}${brand.applyBrandPic}" width="150" height="50"/></dd>
			                     <dd style="width:210px;">&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;&nbsp;书:<img src="${baseUrl}${brand.applyCertificate}" width="150" height="50"/></dd>
	                        </div>
	                       </c:forEach>
	                    </c:if>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
    <script src="<%=basePath %>js/jquery.min.js"></script>
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
  </body>
  </html>