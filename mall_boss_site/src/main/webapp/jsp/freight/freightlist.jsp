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
    <title>物流模板列表</title>

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

            <h2 class="main_title">${pageNameChild} <small><!-- (共条) --></small></h2>

            <div class="common_data p20">
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>注意!</strong> 物流模板，若修改不当，会影响订单运费的结算，在不了解的情况下请联系我们的工作人员进行修改。
                </div>
              <div class="data_ctrl_area mb20">
                <div class="data_ctrl_search pull-right"></div>
                <div class="data_ctrl_brns pull-left">
                  <button type="button" class="btn btn-info" onclick="toaddfreight();">
                    <i class="glyphicon glyphicon-plus"></i> 添加物流模板
                  </button>
                </div>
                <div class="clr"></div>
              </div>
			<input type="hidden" value="${token }" id="token"/>
              <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		             <c:forEach items="${list}" var="template" varStatus="i">
		                <div class="panel panel-default">
		                  <div class="panel-heading" role="tab" id="headingOne_${i.count }">
		                    <h4 class="panel-title">
		                      <div class="pull-right">
		                        <a href="javascript:void(0);" onclick="copytemp(${template.freightTemplateId});">复制</a>
		                        <a href="javascript:void(0);" onclick="toupdate(${template.freightTemplateId})" >编辑</a>
		                     <a href="javascript:void(0);" onclick="setDelete(${template.freightTemplateId});">删除</a>
		                        <c:if test="${template.freightIsDefault=='0' }"><a href="javascript:void(0);"  onclick="setDefault(${template.freightTemplateId});">设为默认</a></c:if>
		                      </div>
		                      <span class="pull-right mr20">最后编辑时间：<fmt:formatDate value="${template.freightModifyTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
		                      <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne_${i.count }" aria-expanded="true" aria-controls="collapseOne_${i.count }">
		                      	${template.freightTemplateName } 
		                      	<c:if test="${template.freightIsDefault=='1' }">(默认模板)</c:if>
		                      </a>
		                    </h4>
		                  </div>
		                  <div id="collapseOne_${i.count }" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne_${i.count }">
		                    <div class="panel-body p20">
		                     <c:forEach items="${template.freightExpressList }" var="freightExpresslist"> 
			                      <h5>运送方式：
			                      		<c:if test="${freightExpresslist.sysLogisticsCompany!=null}">
											${freightExpresslist.sysLogisticsCompany.name }
										</c:if>  
								  </h5>
			                      <table class="table table-hover table-bordered">
			                        <thead>
			                        <tr>
			                          <th width="50%">运送至</th>
			                          <th>首<c:if test="${template.freightMethods=='0'}">件(个)</c:if><c:if test="${template.freightMethods=='1'}">重(g)</c:if></th>
			                          <th>运费(元)</th>
			                          <th>续件<c:if test="${template.freightMethods=='0'}">件(个)</c:if><c:if test="${template.freightMethods=='1'}">重(g)</c:if></th>
			                          <th>运费(元)</th>
			                        </tr>
			                        </thead>
			                        <tbody>
			                        	<c:if test="${freightExpresslist!=null }">
											<tr>
												<td>全国</td>
												<td>${freightExpresslist.expressStart}</td>
												<td>${freightExpresslist.expressPostage}</td>
												<td>${freightExpresslist.expressPlusN1}</td>
												<td>${freightExpresslist.expressPostageplus}</td>
											</tr>	
										</c:if>
										<c:if test="${freightExpresslist!=null }">
											<c:forEach items="${freightExpresslist.freightExpressAll }" var="templateall">
												<tr>
													<td>${templateall.allCityName }</td>
													<td>${templateall.expressStart}</td>
													<td>${templateall.expressPostage}</td>
													<td>${templateall.expressPlusN1}</td>
													<td>${templateall.expressPostageplus}</td>
												</tr>	
											</c:forEach>
										</c:if>
			                        </tbody>
			                      </table>
			                  </c:forEach>
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

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/freight/freight.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    <script>
      $(function(){
        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */
      });
      //物流模板设为默认
      function setDefault(freightTemplateId){
          showDeleteOneConfirmAlert('defaultfreighttemplate.htm?CSRFToken=${token}&freightTemplateId='+freightTemplateId,'确定要设置此模板为默认吗？');    
      }
      
      //删除物流模板
      function setDelete(freightTemplateId){
          showDeleteOneConfirmAlert('deletefreighttemplate.htm?CSRFToken=${token}&freightTemplateId='+freightTemplateId+'&freightThirdId=0','确定要要删除此模板吗？');
      }
      
      //编辑物流模板
      function toupdate(freightTemplateId){
          window.location.href="toupdatefreighttemplate.htm?freightTemplateId="+freightTemplateId+"&CSRFToken=${token}";
      }
    </script>
  </body>
</html>
