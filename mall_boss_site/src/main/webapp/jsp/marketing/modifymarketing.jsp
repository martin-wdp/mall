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
    <title></title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    .spanweight{
    	display: inline-block;
		max-width: 100%;
		margin-bottom: 5px;
		font-weight: bold;
		color:#a94442;
    }
    
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

            <h2 class="main_title">修改促销</h2>
             <input type="hidden" id="marketingFlag" value="${marketingFlag }">
            <div class="common_info common_tabs mt20">
            <ul class="nav nav-tabs" role="tablist">
              <li role="presentation" <c:if test="${!empty kulist|| !empty (marketing.preDiscountMarketings) }">class="active"</c:if>><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">
            <c:choose>
                <c:when test="${marketingFlag =='GRP' }">
                    团购促销
                </c:when>
                <c:when test="${marketingFlag =='PCG'}">
                抢购促销
                </c:when>
                <c:otherwise>
                    单品促销
                </c:otherwise>
            </c:choose>
              </a></li>
              <c:if test="${marketingFlag !='ZK' }">
              <%--<li role="presentation" <c:if test="${!empty catelist }">class="active"</c:if>><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">类目促销</a></li>
              <li role="presentation" <c:if test="${!empty brandlist }">class="active"</c:if>><a href="#tab3" aria-controls="tab2" role="tab" data-toggle="tab">品牌促销</a></li>--%>
              </c:if>
            </ul>
            <div class="tab-content">
             <div role="tabpanel" class="tab-pane <c:if test="${!empty kulist }">active</c:if>" id="tab1">
              <div class="common_form common_form_max p20">
              <!-- 单品促销选择 商品 -->
                <form class="form-horizontal" action="newdomodifymarketing.htm?CSRFToken=${token}"  id="updateFormOne" method="post">
                  <input type="hidden" name="status" class="f_status" value="0">
                  <input type="hidden" id="isAllOne" value="0" name="isAll" >
                  <input type="hidden"  value="${marketing.marketingId }" name="marketingId" >
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>促销名称：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                      <input type="text" class="form-control required" name="marketingName" value="${marketing.marketingName }">
                    </div>
                    <div class="col-sm-3">
                      <a href="javascript:;" class="cuxiaomingchen help_tips">
                        <i class="icon iconfont">&#xe611;</i>
                      </a>
                    </div>
                  </div>
                   <div class="form-group">
                    <label class="control-label col-sm-4">促销类型： <input type="hidden" name="marketingType" value="0"/></label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-8">
	                    <c:forEach items="${codexlist }" var="codex">
	                        <c:if test="${marketingFlag=='DP'}">
			                     <c:if test="${codex.codexType == 1}">
				                     <label class="radio-inline"> <%-- <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType==marketing.codexType}"> checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" />--%>${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag =='BY'}">
			                     <c:if test="${codex.codexType=='12'}">
				                     <label class="radio-inline"> ${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
                            <%--团购促销--%>
                            <c:if test="${marketingFlag =='GRP'}">
			                     <c:if test="${codex.codexType=='10'}">
				                     <label class="radio-inline"> ${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
                            <%--抢购促销--%>
                            <c:if test="${marketingFlag =='PCG'}">
			                     <c:if test="${codex.codexType=='11'}">
				                     <label class="radio-inline"> ${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>

	                        <c:if test="${marketingFlag == 'ZK'}">
			                     <c:if test="${codex.codexType=='15'}">
				                     <label class="radio-inline"> ${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag =='MJO'}">
			                     <c:if test="${codex.codexType=='5' || codex.codexType=='8'}">
				                     <label class="radio-inline"> <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType==marketing.codexType}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag =='MJP'}">
			                     <c:if test="${codex.codexType=='13' || codex.codexType=='14'}">
				                     <label class="radio-inline"> <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType==marketing.codexType}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                    </c:forEach>
                 	   <input type="hidden" name="codexType" value="${marketing.codexType }" />
                  	   <input type="hidden" name="codexId" value="${marketing.codexId}" />
                    </div>
                  </div>
                  
                  <c:forEach items="${codexlist }" var="codex">
	                  <c:if test="${marketingFlag == 'DP'}">
	                     <c:if test="${codex.codexType=='1' }">
		                   <div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==9}" >style="display: none;"</c:if>>
			                    <label class="control-label col-sm-4"><span class="text-danger">*</span>普通直降金额：</label>
			                    <div class="col-sm-1"></div>
			                    <div class="col-sm-6">
			                        	<input type="text" name="offValue"
									    onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
									    onblur="this.v();"
										id="offValue" class="form-control <c:if test="${marketing.codexType ==1}">requires</c:if>" value="${marketing.priceOffMarketing.offValue }">
									<span id="offValue_error" class="spanweight"></span>
			                    </div>
		                  </div>
		                   <div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==9}" >style="display: none;"</c:if>>
			                    <label class="control-label col-sm-4"><span class="text-danger">*</span>会员直降金额：</label>
			                    <div class="col-sm-1"></div>
			                    <div class="col-sm-6">
			                        	<input type="text" name="offVipValue"
									    onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
									    onblur="this.v();"
										id="offVipValue" class="form-control <c:if test="${marketing.codexType ==1}">requires</c:if>" value="${marketing.priceOffMarketing.offVipValue }">
									<span id="offVipValue_error" class="spanweight"></span>
			                    </div>
		                  </div>
		                  </c:if>
		                  <%-- <c:if test="${codex.codexType=='9' }">
		                   <div class="form-group codexDiv codexDiv${codex.codexType}"<c:if test="${marketing.codexType ==1}" >style="display: none;"</c:if>>
			                    <label class="control-label col-sm-4"><span class="text-danger">*</span>限购数量：</label>
			                    <div class="col-sm-1"></div>
			                    <div class="col-sm-6">
			                        <input type="text" name="limitCount" id="limitCount" class="form-control digits <c:if test="${marketing.codexType ==9}">requires</c:if>"  value="${marketing.limitBuyMarketing.limitCount}">
			                    </div>
		                  </div>
		                  </c:if> --%>
	                  </c:if>

	                  <c:if test="${marketingFlag == 'MJO'}">
		                    <c:if test="${codex.codexType=='5' }">
				                <c:forEach items="${marketing.fullbuyReduceMarketings}" var="reduce" varStatus="status">
			                   		<div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==8}" >style="display: none;"</c:if>>
                                        <div class="form-group">
				                        <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-1" style="width:150px;"><label class="radio-inline">普通会员满
                                            </label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="fullPrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if> isNumber" value="${reduce.fullPrice }" >
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                     <div class="col-sm-1"><label class="radio-inline">减</label></div>
					                     <div class="col-sm-2">
					                        <input type="text" name="reducePrice" id="reducePrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if>isNumber" value="${reduce.reducePrice }">
					                    </div>
					                     <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                                    </div>
                                        <div class="form-group">
                                        <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
                                        <div class="col-sm-1" style="width:150px;"><label class="radio-inline">企业会员满</label></div>
                                        <div class="col-sm-2">
                                            <input type="text" name="vipFullPrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if> isNumber" value="${reduce.vipFullPrice }" >
                                        </div>
                                        <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                        <div class="col-sm-1"><label class="radio-inline">减</label></div>
                                        <div class="col-sm-2">
                                            <input type="text" name="vipReducePrice" id="vipReducePrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if>isNumber" value="${reduce.vipReducePrice }">
                                        </div>
                                        <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                            <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
                                            </div>

			                  		</div>
				             </c:forEach>
                                <c:if test="${marketing.fullbuyReduceMarketings== null || fn:length(marketing.fullbuyReduceMarketings) == 0}">
                                    <div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==8}" >style="display: none;"</c:if>>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
                                            <div class="col-sm-1" style="width:150px;"><label class="radio-inline">普通会员满
                                            </label></div>
                                            <div class="col-sm-2">
                                                <input type="text" name="fullPrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if> isNumber" value="${reduce.fullPrice }" >
                                            </div>
                                            <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                            <div class="col-sm-1"><label class="radio-inline">减</label></div>
                                            <div class="col-sm-2">
                                                <input type="text" name="reducePrice" id="reducePrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if>isNumber" value="${reduce.reducePrice }">
                                            </div>
                                            <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
                                            <div class="col-sm-1" style="width:150px;"><label class="radio-inline">企业会员满</label></div>
                                            <div class="col-sm-2">
                                                <input type="text" name="vipFullPrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if> isNumber" value="${reduce.vipFullPrice }" >
                                            </div>
                                            <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                            <div class="col-sm-1"><label class="radio-inline">减</label></div>
                                            <div class="col-sm-2">
                                                <input type="text" name="vipReducePrice" id="vipReducePrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if>isNumber" value="${reduce.vipReducePrice }">
                                            </div>
                                            <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                        </div>

                                    </div>
                                </c:if>
				             <%--<c:if test="${marketing.codexType ==8}" >
			                     <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="fullPrice" class="form-control isNumber">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
				                     <div class="col-sm-1"><label class="radio-inline">减</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" &lt;%&ndash;name="reducePrice"&ndash;%&gt; id="reducePrice" class="form-control isNumber">
				                    </div>
				                     <div class="col-sm-1"><label class="radio-inline">元</label></div>
		                       </div>
		                       &lt;%&ndash; <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
				                         <div class="col-sm-4"></div>
					                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
					            </div> &ndash;%&gt;
	                        </c:if>--%>
	                          <%-- <c:if test="${marketing.codexType ==5}" > --%>
				                  <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==5 && marketing.codexType ==5}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
				                         <div class="col-sm-4"></div>
					                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
					               </div>
				               <%-- </c:if> --%>
		                    </c:if>

		                    <c:if test="${codex.codexType=='8' }">
				                <c:forEach items="${marketing.fullbuyDiscountMarketings }" var="discount" varStatus="status">
			                   		<div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==5}" >style="display: none;"</c:if>>
                                        <div class="form-group">
					                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-3"><label class="radio-inline">普通会员满</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="fullPrice" class="form-control isNumber<c:if test="${marketing.codexType ==8}">required</c:if>" value="${discount.fullPrice }">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                    <div class="col-sm-1"><label class="radio-inline">打</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="fullbuyDiscount" class="form-control zeroOne<c:if test="${marketing.codexType ==8}">required</c:if>" value="${discount.fullbuyDiscount}">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">折</label></div>
                                            </div>
                                        <div class="form-group">
					                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-3"><label class="radio-inline">企业会员满</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="vipFullPrice" class="form-control isNumber<c:if test="${marketing.codexType ==8}">required</c:if>" value="${discount.vipFullPrice }">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                    <div class="col-sm-1"><label class="radio-inline">打</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="vipFullbuyDiscount" class="form-control zeroOne<c:if test="${marketing.codexType ==8}">required</c:if>" value="${discount.vipFullbuyDiscount}">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">折</label></div>
                                            <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
                                            </div>

			                  		</div>
				               </c:forEach>
                                <c:if test="${marketing.fullbuyDiscountMarketings== null || fn:length(marketing.fullbuyDiscountMarketings) == 0}">
                                    <div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==5}" >style="display: none;"</c:if>>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
                                            <div class="col-sm-3"><label class="radio-inline">普通会员满</label></div>
                                            <div class="col-sm-2">
                                                <input type="text" name="fullPrice" class="form-control isNumber<c:if test="${marketing.codexType ==8}">required</c:if>" >
                                            </div>
                                            <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                            <div class="col-sm-1"><label class="radio-inline">打</label></div>
                                            <div class="col-sm-2">
                                                <input type="text" name="fullbuyDiscount" class="form-control zeroOne<c:if test="${marketing.codexType ==8}">required</c:if>" >
                                            </div>
                                            <div class="col-sm-1"><label class="radio-inline">折</label></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
                                            <div class="col-sm-3"><label class="radio-inline">企业会员满</label></div>
                                            <div class="col-sm-2">
                                                <input type="text" name="vipFullPrice" class="form-control isNumber<c:if test="${marketing.codexType ==8}">required</c:if>" >
                                            </div>
                                            <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                            <div class="col-sm-1"><label class="radio-inline">打</label></div>
                                            <div class="col-sm-2">
                                                <input type="text" name="vipFullbuyDiscount" class="form-control zeroOne<c:if test="${marketing.codexType ==8}">required</c:if>">
                                            </div>
                                            <div class="col-sm-1"><label class="radio-inline">折</label></div>

                                        </div>

                                    </div>
                                </c:if>
				              <%-- <c:if test="${marketing.codexType ==5}" >
					               <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
					                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="fullPrice"  class="form-control isNumber">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                    <div class="col-sm-1"><label class="radio-inline">打</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="fullbuyDiscount" class="form-control zeroOne">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">折</label></div>
				                  </div>
				                  &lt;%&ndash; <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
		                            <div class="col-sm-4"></div>
			                        <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                      </div> &ndash;%&gt;
			                  </c:if>--%>
			                  <%-- <c:if test="${marketing.codexType ==8}" > --%>
			                  <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==8 && marketing.codexType ==8}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
	                            <div class="col-sm-4"></div>
		                        <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
		                      </div>
		                    <%-- </c:if> --%>
	                    </c:if>
	                  </c:if>

	                  <c:if test="${marketingFlag == 'MJP'}">
	                    <c:if test="${codex.codexType=='13' }">
	                      <c:forEach items="${marketing.fullbuyNoDiscountMarketings}" var="discount" varStatus="status">
		                        <div class="form-group codexDiv codexDiv${codex.codexType}">
			                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
			                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
			                    <div class="col-sm-2">
			                        <input type="text" name="packagesNo"  class="form-control <c:if test="${marketing.codexType ==13}">required</c:if> digits" value="${discount.packagesNo}">
			                    </div>
			                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
			                     <div class="col-sm-1"><label class="radio-inline">打</label></div>
			                     <div class="col-sm-2">
			                        <input type="text" name="packagebuyDiscount" class="form-control <c:if test="${marketing.codexType ==13}">required</c:if> zeroOne" value="${discount.packagebuyDiscount }">
			                    </div>
			                     <div class="col-sm-1"><label class="radio-inline">折</label></div>
			                     <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
		                        </div>
	                      </c:forEach>
	                      <c:if test="${marketing.codexType ==14}">
	                      		<div class="form-group codexDiv codexDiv${codex.codexType}">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="packagesNo"  class="form-control  digits">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
				                     <div class="col-sm-1"><label class="radio-inline">打</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" name="packagebuyDiscount" class="form-control">
				                    </div>
				                     <div class="col-sm-1"><label class="radio-inline">折</label></div>
		                        </div>
		                        <%-- <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                   </div> --%>
	                      </c:if>
	                      <%-- <c:if test="${marketing.codexType ==13}" > --%>
		                      <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==13 && marketing.codexType ==13}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                   </div>
	                      <%-- </c:if> --%>
	                    </c:if>
	                    <c:if test="${codex.codexType=='14' }">
	                      <c:forEach items="${marketing.fullbuyNoCountMarketings}" var="buycount" varStatus="status">
	                           <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="countNo"  class="form-control digits <c:if test="${marketing.codexType ==14}">required</c:if>" value="${buycount.countNo }">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
				                    <div class="col-sm-1"><label class="radio-inline">共</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" name="countMoney" class="form-control isNumber <c:if test="${marketing.codexType ==14}">required</c:if>" value="${buycount.countMoney }">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
				                    <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
			                   </div>
	                      </c:forEach>
	                      <c:if test="${marketing.codexType ==13}">
		                      <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="countNo"  class="form-control digits">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
				                    <div class="col-sm-1"><label class="radio-inline">共</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" name="countMoney" class="form-control isNumber">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
				              </div>
				              <%-- <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                 </div> --%>
	                      </c:if>
	                     <%--  <c:if test="${marketing.codexType ==14}" > --%>
		                      <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==14 && marketing.codexType ==14}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                   </div>
	                      <%-- </c:if> --%>
	                    </c:if>
	                  </c:if>
                  </c:forEach>

                  <c:if test="${marketingFlag == 'BY'}">
	                   <div class="form-group">
	                    <label class="control-label col-sm-4"><span class="text-danger">*</span>促销内容：</label>
	                    <div class="col-sm-1"></div>
	                    <div class="col-sm-2" style="width:150px;"> <label class="radio-inline">普通会员购物满</label></div>
	                    <div class="col-sm-2">
	                     	<input type="text" class="form-control required isNumber" name="shippingMoney" value="${marketing.shippingMoney }">
	                    </div>
	                    <div class="col-sm-2"><label class="radio-inline">元包邮</label></div>
	                    <div class="col-sm-2" style="width:150px;"> <label class="radio-inline">企业会员购物满</label></div>
	                    <div class="col-sm-2" >
	                     	<input type="text" class="form-control required isNumber" name="vipShippingMoney" value="${marketing.vipShippingMoney }">
	                    </div>
	                    <div class="col-sm-2"><label class="radio-inline">元包邮</label></div>
	                  </div>
                  </c:if>




                  <c:if test="${marketingFlag !='ZK'}">
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>选择货品：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-4">
                      <button type="button" class="btn btn-info"
                              <c:choose>
                                  <c:when test="${marketingFlag =='GRP'}">onclick="chooseProduct(10);"</c:when>
                                  <c:otherwise>
                                      onclick="chooseProduct();"
                                  </c:otherwise>
                              </c:choose>
                              >选择参加促销的货品</button>
                      <span id="ps" class="spanweight"></span>
                    </div>
                  </div>
				<div class="form-group">
                    <label class="control-label col-sm-4">已选择货品：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-19">
                     		<table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;">
						              <thead style="top:0;">
						              <tr>
						                <th width="100">货品图片</th>
						                <th width="100">货品规格</th>
						                <th width="150">货品编号</th>
						                <th width="300">货品名称</th>
                                          <th width="100">商品价格</th>
                                          <th width="100">会员价格</th>
						                  <th width="100">操作</th>
						              </tr>
					            </table>
		                   <div style="max-height:300px;overflow-y:auto;position:relative;">

		                     <table class="table table-striped table-hover table-bordered" id="readproduct">
						              <tbody style="">
						                 <c:if test="${!empty kulist}">
						                   <c:forEach items="${kulist }" var="pro">
						                     <tr id="goods_tr_${pro.goodsInfoId }">
							                     <td width="100"><img src="${pro.goodsInfoImgId}" width="50" height="50">
							                     <input type="hidden" name="goodsIdP" id="goods_Id_3886" value="${pro.goodsInfoId }">
							                     </td>
							                     <td width="100">
							                     	<c:forEach items="${pro.specVo }" var="spec" varStatus="sta2">
								                        ${spec.spec.specName }:${spec.specValueRemark } <br/>
								                    </c:forEach></td>
							                     <td width="150">${pro.goodsInfoItemNo }</td>
							                     <td width="300">${pro.goodsInfoName }</td>
							                     <td width="100" name="goodsPrices">${pro.goodsInfoPreferPrice}</td>
							                     <td width="100" name="goodsQpvipPrices">${pro.goodsInfoVipPrice}</td>
							                     <td width="100">
							                     <button onclick="removeTr(this);">移除</button>
							                     </td>
						                     </tr>
                                               <c:choose>
                                                   <c:when test="${marketingFlag =='GRP'}">
                                                       <input type="hidden" name="TGgoodsInfoVipPrice" onchange="initTgprice()" value="${pro.goodsInfoVipPrice}">
                                                       <input type="hidden" name="TGgoodsInfoPreferPrice" onchange="initTgprice()" value="${pro.goodsInfoPreferPrice}">
                                                   </c:when>
                                                   <c:when test="${marketingFlag =='PCG'}">
                                                       <input type="hidden" name="QGgoodsInfoVipPrice" onchange="initQgprice()" value="${pro.goodsInfoVipPrice}">
                                                       <input type="hidden" name="QGgoodsInfoPreferPrice" onchange="initQgprice()" value="${pro.goodsInfoPreferPrice}">
                                                   </c:when>
                                                   <c:otherwise>

                                                   </c:otherwise>
                                               </c:choose>
						                   </c:forEach>
						                 </c:if>
						              </tbody>
					            </table>

					        </div>
                    </div>
                  </div>
		         </c:if>

		         <c:if test="${marketingFlag =='ZK' }">
		             <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>选择货品：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-4">
                      <button type="button" class="btn btn-info" onclick="chooseProduct(15);">选择参加促销的货品</button>
                      <span id="ps" class="spanweight"></span>
                    </div>
                  </div>
				<div class="form-group">
                    <label class="control-label col-sm-4">已选择货品：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-19">
                    	<table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;">
						              <tr>
						              <td style="text-align:left">
						                <div class="form-inline">
						                  <label class="control-label">批量折扣：</label>
						                  <input type="text" class="form-control zeroOne" style="width:60px;" onblur="plzhekou(this);"/>
						                  <label class="control-label">批量折扣：</label>
						                  <input type="text" class="form-control zeroOne" style="width:60px;" onblur="hyplzhekou(this);"/>

						                  <a href="javascript:;" class="plzk help_tips">
					                        <i class="icon iconfont">&#xe611;</i>
					                      </a>
						                </div>
						              </td>
						              <td style="text-align:left">
						                <a class="btn btn-info" href="javascript:void(0);" onclick="yichus(1);">批量抹分</a>
						                <a class="btn btn-info" href="javascript:void(0);" onclick="yichus(0);">批量抹分角</a>
						              </td>
						              </tr>
					            </table>
                     		<table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;">
						              <thead style="top:0;">
						              <tr>
						                <%--<th width="8%">货品图片</th>
						                <th width="12%">货品规格</th>
						                <th width="15%">货品编号</th>
						                <th width="20%">货品名称</th>
						                <th width="6.5%">价格</th>
						                <th width="10%">折扣</th>
						                <th width="6.5%">折后价</th>
						                <th width="22%">操作</th>--%>
                                            <th width="8%">货品图片</th>
                                            <th width="12%">货品规格</th>
                                            <th width="15%">货品编号</th>
                                            <th width="9%">货品名称</th>
                                            <th width="6%">价格</th>
                                            <th width="10%">折扣</th>
                                            <th width="6%">折后价</th>
                                            <th width="6%">会员价格</th>
                                            <th width="10%">会员折扣</th>
                                            <th width="6%">会员折后价</th>
                                            <th width="12%">操作</th>
						              </tr>
						              </thead>

					            </table>
		                   <div style="max-height:300px;overflow-y:auto;position:relative;">

		                     <table class="table table-striped table-hover table-bordered" id="readproduct">
						              <tbody style="">
						                <c:forEach items="${marketing.preDiscountMarketings }" var="sku">
						                 <tr id="goods_tr_${sku.goodsProduct.goodsInfoId }">
						                 <td width="8%"><img src="${sku.goodsProduct.goodsInfoImgId }" width="30" height="30">
						                 <input type="hidden" name="goodsIdP" id="goods_Id_${sku.goodsProduct.goodsInfoId }" value="${sku.goodsProduct.goodsInfoId }"></td>
						                 <td width="12%">
						                   <c:forEach items="${sku.goodsProduct.specVo }" var="spec" varStatus="sta2">
								                        <p>${spec.spec.specName }:${spec.specValueRemark }</p>
								           </c:forEach>
						                 </td>
						                 <td width="15%">${sku.goodsProduct.goodsInfoItemNo}</td>
						                 <td width="9%">${sku.goodsProduct.goodsInfoName}</td>
						                 <td width="6%" class="goodsPrices">${sku.goodsProduct.goodsInfoPreferPrice }</td>
						                 <td width="10%"><input type="text" class="form-control required zeroOne discountInfos" name="discountInfo" id="discount_info_${sku.goodsProduct.goodsInfoId }" style="width:60px;" value="${sku.discountInfo }" onblur="zkchange('${sku.goodsProduct.goodsInfoPreferPrice }',this);"></td>
						                 <td width="6%"><span class="zhj">${sku.discountPrice }</span><input type="hidden" class="form-control" name="discountPrice" value="${sku.discountPrice}"/><input type="hidden" class="form-control" name="discountFlag" id="discount_prices_${sku.goodsProduct.goodsInfoId }" value="${sku.discountFlag }" readonly=""></td>

						                  <td width="6%" class="vipGoodsPrices">${sku.goodsProduct.goodsInfoVipPrice }</td>
						                 <td width="10%"><input type="text" class="form-control required zeroOne vipDiscountInfos" name="vipDiscountInfo" id="discount_info_${sku.goodsProduct.goodsInfoId }" style="width:60px;" value="${sku.vipdiscountInfo }" onblur="hyzkchange('${sku.goodsProduct.goodsInfoVipPrice }',this);"></td>
						                 <td width="6%"><span class="hyzhj">${sku.vipdiscountPrice }</span><input type="hidden" class="form-control" name="vipDiscountPrice" value="${sku.vipdiscountPrice}"/><input type="hidden" class="form-control" name="discountFlag" id="discount_prices_${sku.goodsProduct.goodsInfoId }" value="${sku.vipdiscountPrice }" readonly=""></td>
						                 <td width="12%">
						                   <input type="button" onclick="mofensingle('${sku.goodsProduct.goodsInfoPreferPrice }','${sku.goodsProduct.goodsInfoVipPrice }',this,1);" value="抹分" class="btn btn-sm btn-default" />
						                   <input type="button" onclick="mofensingle('${sku.goodsProduct.goodsInfoPreferPrice }','${sku.goodsProduct.goodsInfoVipPrice }',this,0);" value="抹分角" class="btn btn-sm btn-default" />
						                   <button onclick="removeTr(this);" class="btn btn-sm btn-default">移除</button>
						                 </td>
						                 </tr>
						                </c:forEach>
						              </tbody>
					            </table>

					        </div>
                    </div>
                  </div>
		         </c:if>

                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>开始时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="startpicker">
                            <input class="form-control required" type="text" id="startTime" readonly
                                   name="sTime" value="<fmt:formatDate value="${marketing.marketingBegin }" pattern="yyyy-MM-dd HH:mm:ss" />" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>结束时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="endpicker">
                            <input class="form-control required" type="text" id="endTime" readonly
                                   name="eTime" value="<fmt:formatDate value="${marketing.marketingEnd }" pattern="yyyy-MM-dd HH:mm:ss" />" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>

                    <c:if test="${marketingFlag == 'GRP'}">

                        <div class="form-group">
                            <label class="control-label col-sm-4"><span
                                    class="text-danger">*</span>普通团购折扣：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" name="grouponDiscount" id="grouponDiscount"
                                       class="form-control required zeroOne" onchange="initTgprice()" value="${marketing.groupon.grouponDiscount}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4"><span
                                    class="text-danger">*</span>会员团购折扣：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" name="grouponVipDiscount" id="grouponVipDiscount"
                                       class="form-control required zeroOne" onchange="initTgprice()" value="${marketing.groupon.grouponVipDiscount}">
                            </div>
                        </div>
                        <div class="form-group" >
                            <label class="control-label col-sm-4"><span
                                    class="text-danger">*</span>普通团购价格：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" name="goodsInfoPreferTgPrice" id="goodsInfoPreferTgPrice"
                                       class="form-control required " onblur="initTgDiscount()" value="${marketing.groupon.grouponPrice}">
                            </div>
                        </div>
                        <div class="form-group" >
                            <label class="control-label col-sm-4"><span
                                    class="text-danger">*</span>会员团购价格：</label>

                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="text" name="goodsInfoVipTgPrice" id="goodsInfoVipTgPrice"
                                       class="form-control required " onblur="initTgVipDiscount()" value="${marketing.groupon.grouponVipPrice}">
                            </div>
                        </div>

                    </c:if>

                    <c:if test="${marketingFlag == 'PCG'}">


                        <div class="form-group">
                            <label class="control-label col-sm-4"><span class="text-danger">*</span>抢购图片：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-default chooseProimg">选择</button>
                            </div>
                            <div class="col-sm-5"><label class="control-label" style="font-size: 14px">建议尺寸:588*338</label></div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4"><span class="text-danger">*</span>已选择图片：</label>
                            <div class="col-sm-1"></div>
                            <input type="hidden" id="rushImage" name="rushImage" value="${marketing.rushs[0].rushImage }">
                            <div class="col-sm-8" id="rushImagestr">
                                <img height='90' width='150' src='${marketing.rushs[0].rushImage }'/>
                            </div>
                        </div>
                            <div class="form-group ">
                                <label class="control-label col-sm-4"><span class="text-danger">*</span>普通抢购折扣：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-6">
                                    <input type="text" name="rushDiscount" onchange="initQgprice()" id="rushDiscount" value="${marketing.rushs[0].rushDiscount }" class="form-control required zeroOne">
                                </div>
                            </div>

                            <div class="form-group ">
                                <label class="control-label col-sm-4"><span class="text-danger">*</span>会员抢购折扣：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-6">
                                    <input type="text" name="rushVipDiscount" onchange="initQgprice()" id="rushVipDiscount" value="${marketing.rushs[0].rushVipDiscount }" class="form-control required zeroOne">
                                </div>
                            </div>

                            <div class="form-group ">
                                <label class="control-label col-sm-4"><span class="text-danger">*</span>普通抢购价格：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-6">
                                    <input type="text" name="rushPrice" id="rushPrice" onblur="initQgDiscount()" value="${marketing.rushs[0].rushPrice }" class="form-control required ">
                                </div>
                            </div>

                            <div class="form-group ">
                                <label class="control-label col-sm-4"><span class="text-danger">*</span>会员抢购价格：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-6">
                                    <input type="text" name="rushVipPrice" onblur="initQgVipDiscount()" id="rushVipPrice" value="${marketing.rushs[0].rushVipPrice }" class="form-control required ">
                                </div>
                            </div>

                        <div class="form-group ">
                                <label class="control-label col-sm-4"><span class="text-danger">*</span>ID限购：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-6">
                                    <input type="text" name="rushLimitation" id="rushLimitation" value="${marketing.rushs[0].rushLimitation }" class="form-control required integer">
                                </div>
                            </div>

                    </c:if>
                 <div class="form-group" style="display: none;">
	                   <label class="control-label col-sm-4">附加赠送：</label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-3">
	                        <span class="radio-inline">
	                        <input type="radio" class="addGiveType" name="addGiveType" <c:if test="${marketing.addGiveType==0 || (marketing.addGiveType==0 && marketing.giveIntegral !=null)}">checked="checked"</c:if> value="0"/>赠送积分</span>
	                   </div>
	                    <div class="col-sm-2">
	                     <input type="text" name="giveIntegral"  class="form-control <c:if test="${marketing.addGiveType==0}">required</c:if> digits giveIntegral" value="${marketing.giveIntegral}" <c:if test="${marketing.addGiveType==1}">disabled="disabled"</c:if>/>
	                    </div>
	                    <div class="col-sm-1">
	                      <label class="radio-inline">个</label>
	                    </div>
                  </div>

                    <div class="form-group" style="display: none;">
	                   <label class="control-label col-sm-4"><span class="text-danger"></span></label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-3">
	                      <label class="radio-inline">
	                      <input type="radio" class="addGiveType" name="addGiveType" value="1" <c:if test="${marketing.addGiveType==1}">checked="checked"</c:if> />赠送优惠券</label>
	                   </div>
	                    <div class="col-sm-4">
                           <select class="form-control valid couponId" name="couponId"  <c:if test="${marketing.addGiveType==0}">disabled="disabled"</c:if>>
	                           <c:forEach items="${couponlist}" var="coupon">
	                             <option value="${coupon.couponId }" <c:if test="${marketing.couponId==coupon.couponId }">selected </c:if>>${coupon.couponName }</option>
	                           </c:forEach>
						   </select>
	                   </div>
                  </div>
                  <c:if test="${marketing.addGiveType==1}">
	                  <div class="form-group">
		                    <label class="control-label col-sm-4">已选优惠券：</label>
		                    <div class="col-sm-1"></div>
		                    <div class="col-sm-10">
		                     <span class="radio-inline">${marketing. couponName}</span>
		                     <span class="radio-inline timeflag" style="color:red"></span>

		                     <fmt:formatDate value="${marketing.coupon.couponEndTime  }" pattern="yyyy-MM-dd HH:mm:ss" var="couponendtime"/>

		                     <input type="hidden" value="${couponendtime }" class="couponendtime">
		                     <input type="hidden" value="${nowtime}" class="nowtime">
		                     <script type="text/javascript">
		                      if($("#tab1").find(".couponendtime").val()!=''){
		                    	  if($("#tab1").find(".couponendtime").val()<$("#tab1").find(".nowtime").val()){
		                    		  $("#tab1").find(".timeflag").html("该优惠券已过期请重新选择");
		                    		  $("#tab1").find('input:radio[name=addGiveType]')[1].checked = false;
		                    		  $(".couponId").attr("disabled","disabled");
		                    	  }
		                      }
		                     </script>
		                   </div>
		              </div>
	              </c:if>
                  <div class="form-group" style="display: none;">
                       <label class="control-label col-sm-4"><span class="text-danger">*</span>会员等级：</label>
                       <div class="col-sm-1"></div>
                       <div class="col-sm-19">
                           <label class="checkbox-inline">
                               <input type="checkbox" name="onCheck" onclick="allunchecked(this,'lelvelId')"> 全部会员
                           </label>
                           <c:forEach items="${customerLevel}" var="level">

                               <label class="checkbox-inline">
                                   <input type="checkbox" id="lelvelId" value="${level.pointLelvelId }" name="lelvelId" onclick="checkLelvel(this,'lelvelId')"
                                    <c:forEach items="${marketing.marketLelvels }" var="mlevel">
                                      <c:if test="${level.pointLelvelId==mlevel.lelvelId }">checked="checked"</c:if>
                                    </c:forEach>
                                   > ${level.pointLevelName }
                               </label>
                           </c:forEach>
                       </div>
                      <div class="col-sm-offset-5 col-sm-10" style="margin-top:10px;"><span id="cs" class="spanweight"></span></div>
                  </div>
<c:if test="${marketingFlag != 'GRP' && marketingFlag != 'PCG'}">
                  <div class="form-group">
                       <label class="control-label col-sm-4">活动站点：</label>
                       <input type="hidden" value="0"  name="activeSiteType" />
                       <div class="col-sm-1"></div>
                       <div class="col-sm-4" >
                           <label class="control-label col-sm-4" style="text-align: left;">全部</label>
                          <%-- <select class="form-control valid"  id="activeSiteType">
	                           <option value="0" <c:if test="${marketing.activeSiteType==0 }">selected="selected"</c:if>>平台</option>
	                           <option value="1" <c:if test="${marketing.activeSiteType==1 }">selected="selected"</c:if>>移动端</option>
							   <option value="2" <c:if test="${marketing.activeSiteType==2 }">selected="selected"</c:if>>全部</option>
						   </select>--%>
                       </div>
                  </div>
                  <div class="form-group">
	                   <label class="control-label col-sm-4"><span class="text-danger"></span>促销LOGO：</label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-19">
	                        <c:forEach items="${logolist}" var="logo">
		                        <label class="checkbox-inline">
		                        <input type="checkbox"  name="promotionLogoId" value="${logo.promotionLogoId }"
		                        <c:forEach items="${marketing.marketLogos}" var="mlogo">
		                          <c:if test="${mlogo.promotionLogoId==logo.promotionLogoId}">
		                          checked="checked"
		                          </c:if>
		                        </c:forEach>
		                        />
		                        <img  alt="${logo.promotionLogoName}" title="${logo.promotionLogoName}" src="${logo.promotionLogoUrl }" height="20" />
		                        </label>
	                        </c:forEach>
	                   </div>
                  </div>
</c:if>
                  <div class="form-group">
                    <label class="control-label col-sm-4">促销描述：<input type="hidden" name="marketingDes" id="marketingDes"/></label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-19">
                      <div class="summernote">${marketing.marketingDes}</div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-9">
                      <button type="button" class="btn btn-primary" onclick="subFormOne(this);">保存</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>

            <div role="tabpanel" class="tab-pane <c:if test="${!empty catelist }">active</c:if>" id="tab2">
              <div class="common_form common_form_max p20">

                 <!-- 单品促销选择 类目 -->
             <form class="form-horizontal" action="newdomodifymarketing.htm?CSRFToken=${token}"  id="updateFormTwo" method="post">
                  <input type="hidden" name="status" class="f_status" value="1">
                  <input type="hidden"  value="${marketing.marketingId }" name="marketingId" >
                  <div class="form-group">
                     <label class="control-label col-sm-4"><span class="text-danger">*</span>促销名称：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                      <input type="text" class="form-control required" name="marketingName" value="${marketing.marketingName }">
                    </div>
                    <div class="col-sm-3">
                      <a href="javascript:;" class="cuxiaomingchen help_tips">
                        <i class="icon iconfont">&#xe611;</i>
                      </a>
                    </div>
                  </div>
                    <div class="form-group">
                    <label class="control-label col-sm-4">促销类型：<input type="hidden" name="marketingType" value="0"/></label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-8">
                   	    <c:forEach items="${codexlist }" var="codex">
	                        <c:if test="${marketingFlag=='DP'}">
			                     <c:if test="${codex.codexType == 1 }">
				                     <label class="radio-inline"> <%-- <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType==marketing.codexType}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" /> --%>${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag =='BY'}">
			                     <c:if test="${codex.codexType=='12'}">
				                     <label class="radio-inline"> ${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag == 'ZK'}">
			                     <c:if test="${codex.codexType=='15'}">
				                     <label class="radio-inline"> ${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag =='MJO'}">
			                     <c:if test="${codex.codexType=='5' || codex.codexType=='8'}">
				                     <label class="radio-inline"> <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType==marketing.codexType}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag =='MJP'}">
			                     <c:if test="${codex.codexType=='13' || codex.codexType=='14'}">
				                     <label class="radio-inline"> <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType==marketing.codexType}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                    </c:forEach>
                 	   <input type="hidden" name="codexType" value="${marketing.codexType }" />
                  	   <input type="hidden" name="codexId" value="${marketing.codexId}" />
                    </div>
                  </div>

                    <c:forEach items="${codexlist }" var="codex">
	                  <c:if test="${marketingFlag == 'DP'}">
	                     <c:if test="${codex.codexType=='1' }">
							 <input type="text" value="${codex.codexType}" id="codex_codexType"/>
		                   <div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==9}" >style="display: none;"</c:if>>
			                    <label class="control-label col-sm-4"><span class="text-danger">*</span>直降金额：</label>
			                    <div class="col-sm-1"></div>
			                    <div class="col-sm-6">
			                        <input type="text" name="offValue" id="offValue" class="form-control <c:if test="${marketing.codexType ==1}">requires</c:if>" value="${marketing.priceOffMarketing.offValue }">
			                    </div>
		                  </c:if>
		                  <%-- <c:if test="${codex.codexType=='9' }">
		                   <div class="form-group codexDiv codexDiv${codex.codexType}"<c:if test="${marketing.codexType ==1}" >style="display: none;"</c:if>>
			                    <label class="control-label col-sm-4"><span class="text-danger">*</span>限购数量：</label>
			                    <div class="col-sm-1"></div>
			                    <div class="col-sm-6">
			                        <input type="text" name="limitCount" id="limitCount" class="form-control digits <c:if test="${marketing.codexType ==9}">requires</c:if>"  value="${marketing.limitBuyMarketing.limitCount}">
			                    </div>
		                  </div>
		                  </c:if> --%>
	                  </c:if>

	                  <c:if test="${marketingFlag == 'MJO'}">
		                    <c:if test="${codex.codexType=='5' }">
				                <c:forEach items="${marketing.fullbuyReduceMarketings}" var="reduce" varStatus="status">
			                   		<div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==8}" >style="display: none;"</c:if>>
				                        <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-1"><label class="radio-inline">普通会员满</label></div>

					                    <div class="col-sm-2">
					                        <input type="text" name="fullPrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if> isNumber" value="${reduce.fullPrice }" >
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                     <div class="col-sm-1"><label class="radio-inline">减</label></div>

					                     <div class="col-sm-2">
					                        <input type="text" <%--name="reducePrice"--%> id="reducePrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if>isNumber" value="${reduce.reducePrice }">
					                    </div>
					                     <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                     <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
			                  		</div>

			                   		<div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==8}" >style="display: none;"</c:if>>
				                        <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-1"><label class="radio-inline">企业会员满</label></div>

					                    <div class="col-sm-2">
					                        <input type="text" name="vipFullPrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if> isNumber" value="${reduce.vipFullPrice }" >
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                     <div class="col-sm-1"><label class="radio-inline">减</label></div>

					                     <div class="col-sm-2">
					                        <input type="text" <%--name="reducePrice"--%> id="vipReducePrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if>isNumber" value="${reduce.vipReducePrice }">
					                    </div>
					                     <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                     <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
			                  		</div>

				             </c:forEach>
				             <%--<c:if test="${marketing.codexType ==8}" >
			                     <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>

				                    <div class="col-sm-2">
				                        <input type="text" name="fullPrice" class="form-control isNumber">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
				                     <div class="col-sm-1"><label class="radio-inline">减</label></div>

				                     <div class="col-sm-2">
				                        <input type="text" &lt;%&ndash;name="reducePrice"&ndash;%&gt; id="reducePrice" class="form-control isNumber">
				                    </div>
				                     <div class="col-sm-1"><label class="radio-inline">元</label></div>
		                       </div>
		                       &lt;%&ndash; <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
				                         <div class="col-sm-4"></div>
					                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
					            </div> &ndash;%&gt;
	                        </c:if>--%>
	                          <%-- <c:if test="${marketing.codexType ==5}" > --%>
				                  <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==5 && marketing.codexType ==5}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
				                         <div class="col-sm-4"></div>
					                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
					               </div>
				               <%-- </c:if> --%>
		                    </c:if>

		                    <c:if test="${codex.codexType=='8' }">
				                <c:forEach items="${marketing.fullbuyDiscountMarketings }" var="discount" varStatus="status">
			                   		<div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==5}" >style="display: none;"</c:if>>
					                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-1"><label class="radio-inline">普通会员满</label></div>

					                    <div class="col-sm-2">
					                        <input type="text" name="fullPrice" class="form-control isNumber<c:if test="${marketing.codexType ==8}">required</c:if>" value="${discount.fullPrice }">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                    <div class="col-sm-1"><label class="radio-inline">打</label></div>

					                    <div class="col-sm-2">
					                        <input type="text" name="fullbuyDiscount" class="form-control zeroOne<c:if test="${marketing.codexType ==8}">required</c:if>" value="${discount.fullbuyDiscount}">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">折</label></div>
					                    <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
			                  		</div>
			                   		<div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==5}" >style="display: none;"</c:if>>
					                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-1"><label class="radio-inline">企业会员满</label></div>

					                    <div class="col-sm-2">
					                        <input type="text" name="vipFullPrice" class="form-control isNumber<c:if test="${marketing.codexType ==8}">required</c:if>" value="${discount.vipFullPrice }">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                    <div class="col-sm-1"><label class="radio-inline">打</label></div>

					                    <div class="col-sm-2">
					                        <input type="text" name="vipFullbuyDiscount" class="form-control zeroOne<c:if test="${marketing.codexType ==8}">required</c:if>" value="${discount.vipFullbuyDiscount}">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">折</label></div>
					                    <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
			                  		</div>
				               </c:forEach>
                                   <c:if test="${marketing.fullbuyDiscountMarketings== null || fn:length(marketing.fullbuyDiscountMarketings) == 0}">
                               <div class="form-group codexDiv codexDiv${codex.codexType}">
                                   <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
                                   <div class="col-sm-1"><label class="radio-inline">普通会员满</label></div>

                                   <div class="col-sm-2">
                                       <input type="text" name="fullPrice" class="form-control isNumber<c:if test="${marketing.codexType ==8}">required</c:if>">
                                   </div>
                                   <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                   <div class="col-sm-1"><label class="radio-inline">打</label></div>

                                   <div class="col-sm-2">
                                       <input type="text" name="fullbuyDiscount" class="form-control zeroOne<c:if test="${marketing.codexType ==8}">required</c:if>">
                                   </div>
                                   <div class="col-sm-1"><label class="radio-inline">折</label></div>
                               </div>
                               <div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==5}" >style="display: none;"</c:if>>
                                   <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
                                   <div class="col-sm-1"><label class="radio-inline">企业会员满</label></div>

                                   <div class="col-sm-2">
                                       <input type="text" name="vipFullPrice" class="form-control isNumber<c:if test="${marketing.codexType ==8}">required</c:if>">
                                   </div>
                                   <div class="col-sm-1"><label class="radio-inline">元</label></div>
                                   <div class="col-sm-1"><label class="radio-inline">打</label></div>

                                   <div class="col-sm-2">
                                       <input type="text" name="vipFullbuyDiscount" class="form-control zeroOne<c:if test="${marketing.codexType ==8}">required</c:if>">
                                   </div>
                                   <div class="col-sm-1"><label class="radio-inline">折</label></div>
                               </div>
                               </c:if>
				               <%--<c:if test="${marketing.codexType ==8}" >
					               <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
					                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-1"><label class="radio-inline">满</label></div>

					                    <div class="col-sm-2">
					                        <input type="text" name="fullPrice"  class="form-control isNumber">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                    <div class="col-sm-1"><label class="radio-inline">打</label></div>

					                    <div class="col-sm-2">
					                        <input type="text" name="fullbuyDiscount" class="form-control zeroOne">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">折</label></div>
				                  </div>
				                  &lt;%&ndash; <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
		                            <div class="col-sm-4"></div>
			                        <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                      </div> &ndash;%&gt;
			                  </c:if>--%>
			                  <%-- <c:if test="${marketing.codexType ==8}" > --%>
			                  <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==8 && marketing.codexType ==8}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
	                            <div class="col-sm-4"></div>
		                        <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
		                      </div>
		                    <%-- </c:if> --%>
	                    </c:if>
	                  </c:if>

	                  <c:if test="${marketingFlag == 'MJP'}">
	                    <c:if test="${codex.codexType=='13' }">
	                      <c:forEach items="${marketing.fullbuyNoDiscountMarketings}" var="discount" varStatus="">
		                        <div class="form-group codexDiv codexDiv${codex.codexType}">
			                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
			                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
			                    <div class="col-sm-2">
			                        <input type="text" name="packagesNo"  class="form-control <c:if test="${marketing.codexType ==13}">required</c:if> digits" value="${discount.packagesNo}">
			                    </div>
			                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
			                     <div class="col-sm-1"><label class="radio-inline">打</label></div>
			                     <div class="col-sm-2">
			                        <input type="text" name="packagebuyDiscount" class="form-control <c:if test="${marketing.codexType ==13}">required</c:if> zeroOne" value="${discount.packagebuyDiscount }">
			                    </div>
			                     <div class="col-sm-1"><label class="radio-inline">折</label></div>
			                     <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
		                        </div>
	                      </c:forEach>
	                      <c:if test="${marketing.codexType ==14}">
	                      		<div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="packagesNo"  class="form-control  digits">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
				                     <div class="col-sm-1"><label class="radio-inline">打</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" name="packagebuyDiscount" class="form-control">
				                    </div>
				                     <div class="col-sm-1"><label class="radio-inline">折</label></div>
		                        </div>
		                        <%-- <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                   </div> --%>
	                      </c:if>
	                      <%-- <c:if test="${marketing.codexType ==13}" > --%>
		                      <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==13 && marketing.codexType ==13}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                   </div>
	                      <%-- </c:if> --%>
	                    </c:if>
	                    <c:if test="${codex.codexType=='14' }">
	                      <c:forEach items="${marketing.fullbuyNoCountMarketings}" var="buycount" varStatus="status">
	                           <div class="form-group codexDiv codexDiv${codex.codexType}">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="countNo"  class="form-control digits <c:if test="${marketing.codexType ==14}">required</c:if>" value="${buycount.countNo }">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
				                    <div class="col-sm-1"><label class="radio-inline">共</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" name="countMoney" class="form-control isNumber <c:if test="${marketing.codexType ==14}">required</c:if>" value="${buycount.countMoney }">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
				                    <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
			                   </div>
	                      </c:forEach>
	                      <c:if test="${marketing.codexType ==13}">
		                      <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="countNo"  class="form-control digits">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
				                    <div class="col-sm-1"><label class="radio-inline">共</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" name="countMoney" class="form-control isNumber">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
				              </div>
				              <%-- <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                 </div> --%>
	                      </c:if>
	                     <%--  <c:if test="${marketing.codexType ==14}" > --%>
		                      <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==14 && marketing.codexType ==14}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                   </div>
	                      <%-- </c:if> --%>
	                    </c:if>
	                  </c:if>
                  </c:forEach>
                   <c:if test="${marketingFlag == 'BY'}">
	                   <div class="form-group">
	                    <label class="control-label col-sm-4"><span class="text-danger">*</span>促销内容：</label>
	                    <div class="col-sm-1"></div>
	                    <div class="col-sm-2"> <label class="radio-inline">购物满</label></div>
	                    <div class="col-sm-2">
	                     	<input type="text" class="form-control required isNumber" name="shippingMoney" value="${marketing.shippingMoney }">
	                    </div>
	                    <div class="col-sm-2"><label class="radio-inline">元包邮</label></div>
	                  </div>
                  </c:if>

                  <c:if test="${marketingFlag == 'ZK'}">
	                   <div class="form-group">
	                    <label class="control-label col-sm-4"><span class="text-danger">*</span>促销内容：</label>
	                    <div class="col-sm-3"> <label class="radio-inline">&nbsp;购物满</label></div>
	                    <div class="col-sm-3">
	                     	<input type="text" class="form-control required isNumber" name="shippingMoney" value="${marketing.shippingMoney }">
	                    </div>
	                    <div class="col-sm-3"><label class="radio-inline">元包邮</label></div>
	                  </div>
                  </c:if>

                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>选择类目：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-4">
                      <button type="button" class="btn btn-info" onclick="chooseCate();">选择参加促销的类目</button>
                    </div>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-12">
                    	 <label class="radio-inline">
                    	 	<input type="checkbox" id="isallCate" value="0" name="isallCate" onchange="checkIsAll(this);" <c:if test="${!empty catelist && marketing.isAll==1 }">checked="checked"</c:if> />全场促销（全场促销可不指定类目）
                    	 	<input type="hidden" id="isAlls" value="0" name="isAll" >
                    	 	<span id="pc" class="spanweight"></span>
                    	 </label>
                    </div>
                  </div>
                  <div class="form-group" id="towCate" <c:if test="${!empty catelist && marketing.isAll==1 }">style="display:none"</c:if>>
                    <label class="control-label col-sm-4">已选择类目：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-14">
                     <table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;">
				              <thead>
				              <tr>
				                <th width="100">分类编号</th>
				                <th width="250">分类名称</th>
				                <th width="100">操作</th>
				              </tr>
				              </thead>
				     </table>
				       <div style="max-height:300px;overflow-y:auto;position:relative;">
                    	   <table class="table table-striped table-hover table-bordered" id="list">
				              <tbody style="">
				               <c:if test="${!empty catelist}">
				                 <c:forEach items="${catelist}" var="cate">
				                     <tr>
				                        <td width="100">${cate.catId }<input type="hidden" name="cateIdp" id="cate_Id_p${cate.catId }" value="${cate.catId }"></td>
				                        <td width="250">${cate.catName }</td>
				                        <td width="100"><button onclick="removeTr(this)">移除</button></td></tr>
				                 </c:forEach>
				               </c:if>
				              </tbody>
				            </table>
				        </div>
                    </div>
                    <c:forEach items="${thirdCateList}" var="thirdcate" varStatus="index">
			 	 	  <input name="thirdcate_ids" value=${thirdcate.catId}    type="hidden" />
			        </c:forEach>
                  </div>

                  <div class="form-group">
                      <label class="control-label col-sm-4"><span class="text-danger">*</span>开始时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="startpicker2">
                            <input class="form-control required" type="text" id="startTime2" value="<fmt:formatDate value="${marketing.marketingBegin }" pattern="yyyy-MM-dd HH:mm:ss" />"  readonly
                                   name="sTime" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>结束时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="endpicker2">
                            <input class="form-control required" type="text" id="endTime2" value="<fmt:formatDate value="${marketing.marketingEnd }" pattern="yyyy-MM-dd HH:mm:ss" />" readonly
                                   name="eTime" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>

                 <div class="form-group" style="display: none;">
	                   <label class="control-label col-sm-4">附加赠送：</label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-3">
	                        <span class="radio-inline"> <input type="radio" class="addGiveType" name="addGiveType"  <c:if test="${marketing.addGiveType==0 || (marketing.addGiveType==0 && marketing.giveIntegral !=null)}">checked="checked"</c:if> value="0"/>赠送积分</span>
	                   </div>
	                    <div class="col-sm-2">
	                     <input type="text" name="giveIntegral"  class="form-control <c:if test="${marketing.addGiveType==0}">required</c:if> digits giveIntegral" value="${marketing.giveIntegral}" <c:if test="${marketing.addGiveType==1}">disabled="disabled"</c:if>>
	                    </div>
	                    <div class="col-sm-1">
	                      <label class="radio-inline">个</label>
	                    </div>
                  </div>

                    <div class="form-group" style="display: none;">
	                   <label class="control-label col-sm-4"><span class="text-danger"></span></label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-3">
	                      <label class="radio-inline"> <input type="radio" class="addGiveType" name="addGiveType" value="1" <c:if test="${marketing.addGiveType==1}">checked="checked"</c:if>/>赠送优惠券</label>
	                   </div>
	                    <div class="col-sm-4">
                           <select class="form-control valid couponId" name="couponId"  <c:if test="${marketing.addGiveType==0}">disabled="disabled"</c:if>>
	                           <c:forEach items="${couponlist}" var="coupon">
	                             <option value="${coupon.couponId }" <c:if test="${marketing.couponId==coupon.couponId }">selected </c:if>>${coupon.couponName }</option>
	                           </c:forEach>
						   </select>
	                   </div>
                  </div>
                  <c:if test="${marketing.addGiveType==1}">
	                  <div class="form-group">
		                    <label class="control-label col-sm-4">已选优惠券：</label>
		                    <div class="col-sm-1"></div>
		                    <div class="col-sm-10">
		                     <span class="radio-inline">${marketing. couponName}</span>
		                     <span class="radio-inline timeflag"  style="color:red"></span>

		                     <fmt:formatDate value="${marketing.coupon.couponEndTime  }" pattern="yyyy-MM-dd HH:mm:ss" var="couponendtime"/>

		                     <input type="hidden" value="${couponendtime }" class="couponendtime">
		                     <input type="hidden" value="${nowtime}" class="nowtime">
		                     <script type="text/javascript">
		                     if($("#tab2").find(".couponendtime").val()!=''){
		                    	  if($("#tab2").find(".couponendtime").val()<$("#tab1").find(".nowtime").val()){
		                    		  $("#tab2").find(".timeflag").html("该优惠券已过期请重新选择");
		                    		  $("#tab2").find('input:radio[name=addGiveType]')[1].checked = false;
		                    		 $("#tab2").find(".couponId").attr("disabled","disabled");
		                    	  }
		                      }
		                     </script>
		                   </div>
		              </div>
                  </c:if>
                  <div class="form-group" style="display: none;">
                       <label class="control-label col-sm-4"><span class="text-danger">*</span>会员等级：</label>
                       <div class="col-sm-1"></div>
                       <div class="col-sm-19">
                           <label class="checkbox-inline">
                               <input type="checkbox" name="onCheck" onclick="allunchecked(this,'lelvelId')"> 全部会员
                           </label>
                           <c:forEach items="${customerLevel}" var="level">

                               <label class="checkbox-inline">
                                   <input type="checkbox" id="lelvelId" value="${level.pointLelvelId }" name="lelvelId" onclick="checkLelvel(this,'lelvelId')"
                                    <c:forEach items="${marketing.marketLelvels }" var="mlevel">
                                      <c:if test="${level.pointLelvelId==mlevel.lelvelId }">checked="checked"</c:if>
                                    </c:forEach>
                                   > ${level.pointLevelName }
                               </label>
                           </c:forEach>
                       </div>
                       <div class="col-sm-offset-5 col-sm-10" style="margin-top:10px;"><span id="cs" class="spanweight"></span></div>
                  </div>

<c:if test="${marketingFlag != 'GRP' && marketingFlag != 'PCG'}">
                  <div class="form-group">
                       <label class="control-label col-sm-4">活动站点：</label>
                      <input type="hidden" value="0"  name="activeSiteType" />
                       <div class="col-sm-1"></div>
                       <div class="col-sm-4" >
                           <label class="control-label col-sm-4">全部</label>
                           <%--<select class="form-control valid" name="" id="activeSiteType">
	                            <option value="0" <c:if test="${marketing.activeSiteType==0 }">selected="selected"</c:if>>平台</option>
	                            <option value="1" <c:if test="${marketing.activeSiteType==1 }">selected="selected"</c:if>>移动端</option>
							    <option value="2" <c:if test="${marketing.activeSiteType==2 }">selected="selected"</c:if>>全部</option>
						   </select>--%>
                       </div>
                  </div>
                  <div class="form-group">
	                   <label class="control-label col-sm-4"><span class="text-danger"></span>促销LOGO：</label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-19">
	                        <c:forEach items="${logolist}" var="logo">
		                        <label class="checkbox-inline">
		                        <input type="checkbox"  name="promotionLogoId" value="${logo.promotionLogoId }"
		                        <c:forEach items="${marketing.marketLogos}" var="mlogo">
		                          <c:if test="${mlogo.promotionLogoId==logo.promotionLogoId}">
		                          checked="checked"
		                          </c:if>
		                        </c:forEach>
		                        />
		                        <img  alt="${logo.promotionLogoName}" title="${logo.promotionLogoName}" src="${logo.promotionLogoUrl }" height="20" />
		                        </label>
	                        </c:forEach>
	                   </div>
                  </div>
    </c:if>
                  <div class="form-group">
                    <label class="control-label col-sm-4">促销描述：<input type="hidden" name="marketingDes" id="marketingDesTwo"/></label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-19">
                      <div class="summernoteTwo">${marketing.marketingDes}</div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-9">
                      <button type="button" class="btn btn-primary" onclick="subFormTwo(this);">保存</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <div role="tabpanel" class="tab-pane <c:if test="${!empty brandlist }">active</c:if>" id="tab3">
              <div class="common_form common_form_max p20">
                <!-- 单品促销选择 品牌-->
              <form class="form-horizontal" action="newdomodifymarketing.htm?CSRFToken=${token}"  id="updateFormThree" method="post">
                  <input type="hidden" name="status" class="f_status" value="2">
                  <input type="hidden"  value="${marketing.marketingId }" name="marketingId" >
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>促销名称：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                       <input type="text" class="form-control required" name="marketingName" value="${marketing.marketingName }">
                    </div>
                    <div class="col-sm-3">
                      <a href="javascript:;" class="cuxiaomingchen help_tips">
                        <i class="icon iconfont">&#xe611;</i>
                      </a>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-4">促销类型：<input type="hidden" name="marketingType" value="0"/></label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-8">
 						<input type="hidden" value="${marketingFlag}" id="marketingFlags"/>
	                    <c:forEach items="${codexlist }" var="codex">
	                        <c:if test="${marketingFlag=='DP'}">
			                     <c:if test="${codex.codexType == 1 }">
				                     <label class="radio-inline"> <%-- <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType==marketing.codexType}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" /> --%>${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag =='BY'}">
			                     <c:if test="${codex.codexType=='12'}">
				                     <label class="radio-inline"> ${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag == 'ZK'}">
			                     <c:if test="${codex.codexType=='15'}">
				                     <label class="radio-inline"> ${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag =='MJO'}">
			                     <c:if test="${codex.codexType=='5' || codex.codexType=='8'}">
				                     <label class="radio-inline"> <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType==marketing.codexType}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                        <c:if test="${marketingFlag =='MJP'}">
			                     <c:if test="${codex.codexType=='13' || codex.codexType=='14'}">
				                     <label class="radio-inline"> <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType==marketing.codexType}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
		                    	 </c:if>
	                        </c:if>
	                    </c:forEach>
                 	   <input type="hidden" name="codexType" value="${marketing.codexType }" />
                  	   <input type="hidden" name="codexId" value="${marketing.codexId}" />
                    </div>
                  </div>

                  <c:forEach items="${codexlist }" var="codex">
	                  <c:if test="${marketingFlag == 'DP'}">
	                     <c:if test="${codex.codexType=='1' }">
		                   <div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==9}" >style="display: none;"</c:if>>
			                    <label class="control-label col-sm-4"><span class="text-danger">*</span>直降金额：</label>
			                    <div class="col-sm-1"></div>
			                    <div class="col-sm-6">
			                        <input type="text" name="offValue" id="offValue" class="form-control <c:if test="${marketing.codexType ==1}">requires</c:if>" value="${marketing.priceOffMarketing.offValue }">
			                    </div>
		                  </div>
		                  </c:if>
		                 <%--  <c:if test="${codex.codexType=='9' }">
		                   <div class="form-group codexDiv codexDiv${codex.codexType}"<c:if test="${marketing.codexType ==1}" >style="display: none;"</c:if>>
			                    <label class="control-label col-sm-4"><span class="text-danger">*</span>限购数量：</label>
			                    <div class="col-sm-1"></div>
			                    <div class="col-sm-6">
			                        <input type="text" name="limitCount" id="limitCount" class="form-control digits <c:if test="${marketing.codexType ==9}">requires</c:if>"  value="${marketing.limitBuyMarketing.limitCount}">
			                    </div>
		                  </div>
		                  </c:if> --%>
	                  </c:if>

	                  <c:if test="${marketingFlag == 'MJO'}">
		                    <c:if test="${codex.codexType=='5' }">
				                <c:forEach items="${marketing.fullbuyReduceMarketings}" var="reduce" varStatus="status">
			                   		<div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==8}" >style="display: none;"</c:if>>
				                        <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="fullPrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if> isNumber" value="${reduce.fullPrice }" >
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                     <div class="col-sm-1"><label class="radio-inline">减</label></div>
					                     <div class="col-sm-2">
					                        <input type="text" <%--name="reducePrice"--%> id="reducePrice" class="form-control <c:if test="${marketing.codexType ==5}">required</c:if>isNumber" value="${reduce.reducePrice }">
					                    </div>
					                     <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                     <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
			                  		</div>
				             </c:forEach>
				             <c:if test="${marketing.codexType ==8}" >
			                     <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="fullPrice" class="form-control isNumber">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
				                     <div class="col-sm-1"><label class="radio-inline">减</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" <%--name="reducePrice"--%> id="reducePrice" class="form-control isNumber">
				                    </div>
				                     <div class="col-sm-1"><label class="radio-inline">元</label></div>
		                       </div>
		                       <%-- <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
				                         <div class="col-sm-4"></div>
					                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
					            </div> --%>
	                        </c:if>
	                          <%-- <c:if test="${marketing.codexType ==5}" > --%>
				                  <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==5 && marketing.codexType ==5}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
				                         <div class="col-sm-4"></div>
					                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
					               </div>
				               <%-- </c:if> --%>
		                    </c:if>

		                    <c:if test="${codex.codexType=='8' }">
				                <c:forEach items="${marketing.fullbuyDiscountMarketings }" var="discount" varStatus="status">
			                   		<div class="form-group codexDiv codexDiv${codex.codexType}" <c:if test="${marketing.codexType ==5}" >style="display: none;"</c:if>>
					                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="fullPrice" class="form-control isNumber<c:if test="${marketing.codexType ==8}">required</c:if>" value="${discount.fullPrice }">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                    <div class="col-sm-1"><label class="radio-inline">打</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="fullbuyDiscount" class="form-control zeroOne<c:if test="${marketing.codexType ==8}">required</c:if>" value="${discount.fullbuyDiscount}">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">折</label></div>
					                    <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
			                  		</div>
				               </c:forEach>
				               <c:if test="${marketing.codexType ==5}" >
					               <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
					                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
					                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="fullPrice"  class="form-control isNumber">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
					                    <div class="col-sm-1"><label class="radio-inline">打</label></div>
					                    <div class="col-sm-2">
					                        <input type="text" name="fullbuyDiscount" class="form-control zeroOne">
					                    </div>
					                    <div class="col-sm-1"><label class="radio-inline">折</label></div>
				                  </div>
				                  <%-- <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
		                            <div class="col-sm-4"></div>
			                        <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                      </div> --%>
			                  </c:if>
			                  <%-- <c:if test="${marketing.codexType ==8}" > --%>
			                  <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==8 && marketing.codexType ==8}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
	                            <div class="col-sm-4"></div>
		                        <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
		                      </div>
		                    <%-- </c:if> --%>
	                    </c:if>
	                  </c:if>

	                  <c:if test="${marketingFlag == 'MJP'}">
	                    <c:if test="${codex.codexType=='13' }">
	                      <c:forEach items="${marketing.fullbuyNoDiscountMarketings}" var="discount" varStatus="">
		                        <div class="form-group codexDiv codexDiv${codex.codexType}">
			                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
			                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
			                    <div class="col-sm-2">
			                        <input type="text" name="packagesNo"  class="form-control <c:if test="${marketing.codexType ==13}">required</c:if> digits" value="${discount.packagesNo}">
			                    </div>
			                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
			                     <div class="col-sm-1"><label class="radio-inline">打</label></div>
			                     <div class="col-sm-2">
			                        <input type="text" name="packagebuyDiscount" class="form-control <c:if test="${marketing.codexType ==13}">required</c:if> zeroOne" value="${discount.packagebuyDiscount }">
			                    </div>
			                     <div class="col-sm-1"><label class="radio-inline">折</label></div>
			                     <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
		                        </div>
	                      </c:forEach>
	                      <c:if test="${marketing.codexType ==14}">
	                      		<div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="packagesNo"  class="form-control  digits">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
				                     <div class="col-sm-1"><label class="radio-inline">打</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" name="packagebuyDiscount" class="form-control">
				                    </div>
				                     <div class="col-sm-1"><label class="radio-inline">折</label></div>
		                        </div>
		                        <%-- <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                   </div> --%>
	                      </c:if>
	                      <%-- <c:if test="${marketing.codexType ==13}" > --%>
		                      <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==13 && marketing.codexType ==13}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                   </div>
	                      <%-- </c:if> --%>
	                    </c:if>
	                    <c:if test="${codex.codexType=='14' }">
	                      <c:forEach items="${marketing.fullbuyNoCountMarketings}" var="buycount" varStatus="status">
	                           <div class="form-group codexDiv codexDiv${codex.codexType}" >
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="countNo"  class="form-control digits <c:if test="${marketing.codexType ==14}">required</c:if>" value="${buycount.countNo }">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
				                    <div class="col-sm-1"><label class="radio-inline">共</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" name="countMoney" class="form-control isNumber <c:if test="${marketing.codexType ==14}">required</c:if>" value="${buycount.countMoney }">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
				                    <c:if test="${status.index >0 }"><div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></c:if>
			                   </div>
	                      </c:forEach>
	                      <c:if test="${marketing.codexType ==13}">
		                      <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
				                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
				                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
				                    <div class="col-sm-2">
				                        <input type="text" name="countNo"  class="form-control digits">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
				                    <div class="col-sm-1"><label class="radio-inline">共</label></div>
				                     <div class="col-sm-2">
				                        <input type="text" name="countMoney" class="form-control isNumber">
				                    </div>
				                    <div class="col-sm-1"><label class="radio-inline">元</label></div>
				              </div>
				              <%-- <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                 </div> --%>
	                      </c:if>
	                     <%--  <c:if test="${marketing.codexType ==14}" > --%>
		                      <div class="form-group addChoose addChoose${codex.codexType}" <c:choose><c:when test="${codex.codexType==14 && marketing.codexType ==14}">style="display:block"</c:when><c:otherwise>style="display:none"</c:otherwise></c:choose>>
		                         <div class="col-sm-4"></div>
			                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
			                   </div>
	                      <%-- </c:if> --%>
	                    </c:if>
	                  </c:if>
                  </c:forEach>

                   <c:if test="${marketingFlag == 'BY'}">
	                   <div class="form-group">
	                    <label class="control-label col-sm-4"><span class="text-danger">*</span>促销内容：</label>
	                    <div class="col-sm-1"></div>
	                    <div class="col-sm-2"> <label class="radio-inline">购物满</label></div>
	                    <div class="col-sm-2">
	                     	<input type="text" class="form-control required isNumber" name="shippingMoney" value="${marketing.shippingMoney }">
	                    </div>
	                    <div class="col-sm-2"><label class="radio-inline">元包邮</label></div>
	                  </div>
                  </c:if>

                  <c:if test="${marketingFlag == 'ZK'}">
	                   <div class="form-group">
	                    <label class="control-label col-sm-4"><span class="text-danger">*</span>促销内容：</label>
	                    <div class="col-sm-3"> <label class="radio-inline">&nbsp;购物满</label></div>
	                    <div class="col-sm-3">
	                     	<input type="text" class="form-control required isNumber" name="shippingMoney" value="${marketing.shippingMoney }">
	                    </div>
	                    <div class="col-sm-3"><label class="radio-inline">元包邮</label></div>
	                  </div>
                  </c:if>

                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>选择品牌：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-4">
                      <button type="button" class="btn btn-info" onclick="chooseBrandsView();">选择参加促销的品牌</button>
                    </div>
                     <div class="col-sm-1"></div>
                     <div class="col-sm-12">
                    	 <label class="radio-inline">
                    	 	<input type="checkbox" value="0" name="isAllsThreeChecked" id="isAllsThreeChecked" onchange="isAllThree(this);" <c:if test="${!empty brandlist && marketing.isAll==1 }">checked="checked"</c:if>>全场促销（全场促销可不指定品牌）
                    	 	<input type="hidden" id="isAllsThree" value="0" name="isAll" >
                    	 	<span id="pb" class="spanweight"></span>
                    	 </label>
                    </div>
                  </div>
                    <div class="form-group" id="threeBrand" <c:if test="${!empty brandlist && marketing.isAll==1 }">style="display:none"</c:if>>
                    <label class="control-label col-sm-4">已选择品牌：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-14">
                     <table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;">
				              <thead>
				              <tr>
				                <th width="100">品牌编号</th>
				                <th width="250">品牌名称</th>
				                <th width="100">操作</th>
				              </tr>
				              </thead>
				     </table>
				       <div style="max-height:300px;overflow-y:auto;position:relative;">
                    	   <table class="table table-striped table-hover table-bordered" id="brandlist">
				              <tbody style="">
				                <c:if test="${!empty brandlist}">
				                  <c:forEach items="${brandlist}" var="brand">
				                   <tr>
					                   <td width="100">${brand.brandId }<input type="hidden" name="brandIdP" id="brandIdP${brand.brandId }" value="${brand.brandId }"></td>
					                   <td width="250">${brand.brandName }</td>
					                   <td width="100"><button onclick="removeTr(this);">移除</button></td>
				                   </tr>
				                  </c:forEach>
				                </c:if>
				              </tbody>
				            </table>
				        </div>
                    </div>
                    <c:forEach items="${allbrandlist}" var="brand" varStatus="index">
						<input name="brand_allids"  value="${brand.brandId}"  type="hidden"/>
					</c:forEach>
                  </div>

                  <div class="form-group">
                      <label class="control-label col-sm-4"><span class="text-danger">*</span>开始时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="startpicker3">
                            <input class="form-control required" type="text" id="startTime3" value="<fmt:formatDate value="${marketing.marketingBegin }" pattern="yyyy-MM-dd HH:mm:ss" />" readonly
                                   name="sTime" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>结束时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="endpicker3">
                            <input class="form-control required" id="endtime3" type="text" value="<fmt:formatDate value="${marketing.marketingEnd }" pattern="yyyy-MM-dd HH:mm:ss" />" readonly
                                   name="eTime">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>

                  <div class="form-group"  style="display:none;">
	                   <label class="control-label col-sm-4">附加赠送：</label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-3">
	                        <span class="radio-inline"> <input type="radio" class="addGiveType" name="addGiveType" <c:if test="${marketing.addGiveType==0 || (marketing.addGiveType==0 && marketing.giveIntegral !=null)}">checked="checked"</c:if> value="0"/>赠送积分</span>
	                   </div>
	                    <div class="col-sm-2">
	                     <input type="text" name="giveIntegral"  class="form-control <c:if test="${marketing.addGiveType==0}">required</c:if> digits giveIntegral" value="${marketing.giveIntegral}" <c:if test="${marketing.addGiveType==1}">disabled="disabled"</c:if> />
	                    </div>
	                    <div class="col-sm-1">
	                      <label class="radio-inline">个</label>
	                    </div>
                  </div>

                    <div class="form-group" style="display:none;">
	                   <label class="control-label col-sm-4"><span class="text-danger"></span></label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-3">
	                      <label class="radio-inline"> <input type="radio" class="addGiveType" name="addGiveType" value="1" <c:if test="${marketing.addGiveType==1}">checked="checked"</c:if>/>赠送优惠券</label>
	                   </div>
	                    <div class="col-sm-4">
                           <select class="form-control valid couponId" name="couponId"   <c:if test="${marketing.addGiveType==0}">disabled="disabled"</c:if>>
	                           <<c:forEach items="${couponlist}" var="coupon">
	                             <option value="${coupon.couponId }" <c:if test="${marketing.couponId==coupon.couponId }">selected </c:if>>${coupon.couponName }</option>
	                           </c:forEach>
						   </select>
	                   </div>
                  </div>
                  <c:if test="${marketing.addGiveType==1}">
	                  <div class="form-group">
		                    <label class="control-label col-sm-4">已选优惠券：</label>
		                    <div class="col-sm-1"></div>
		                    <div class="col-sm-10">
		                     <span class="radio-inline">${marketing. couponName}</span>
		                     <span class="radio-inline timeflag"  style="color:red"></span>

		                     <fmt:formatDate value="${marketing.coupon.couponEndTime  }" pattern="yyyy-MM-dd HH:mm:ss" var="couponendtime"/>

		                     <input type="hidden" value="${couponendtime }" class="couponendtime">
		                     <input type="hidden" value="${nowtime}" class="nowtime">
		                     <script type="text/javascript">
		                     if($("#tab3").find(".couponendtime").val()!=''){
		                    	  if($("#tab3").find(".couponendtime").val()<$("#tab1").find(".nowtime").val()){
		                    		  $("#tab3").find(".timeflag").html("该优惠券已过期请重新选择");
		                    		 $("#tab3").find('input:radio[name=addGiveType]')[1].checked = false;
		                    		 $("#tab3").find(".couponId").attr("disabled","disabled");
		                    	  }
		                      }
		                     </script>
		                   </div>
		              </div>
                  </c:if>
                  <div class="form-group" style="display: none;">
                       <label class="control-label col-sm-4"><span class="text-danger">*</span>会员等级：</label>
                       <div class="col-sm-1"></div>
                       <div class="col-sm-19">
                           <label class="checkbox-inline">
                               <input type="checkbox" name="onCheck" onclick="allunchecked(this,'lelvelId')"> 全部会员
                           </label>
                           <c:forEach items="${customerLevel}" var="level">

                              <label class="checkbox-inline">
                                   <input type="checkbox" id="lelvelId" value="${level.pointLelvelId }" name="lelvelId" onclick="checkLelvel(this,'lelvelId')"
                                    <c:forEach items="${marketing.marketLelvels }" var="mlevel">
                                      <c:if test="${level.pointLelvelId==mlevel.lelvelId }">checked="checked"</c:if>
                                    </c:forEach>
                                   > ${level.pointLevelName }
                               </label>
                           </c:forEach>
                       </div>
                       <div class="col-sm-offset-5 col-sm-10" style="margin-top:10px;"><span id="cs" class="spanweight"></span></div>
                  </div>

                  <c:if test="${marketingFlag != 'GRP' && marketingFlag != 'PCG'}">
                  <div class="form-group">
                       <label class="control-label col-sm-4">活动站点：</label>
                      <input type="hidden" value="0"  name="activeSiteType" />
                       <div class="col-sm-1"></div>
                       <div class="col-sm-4">
                           <label class="control-label col-sm-4">全部</label>
                           <%--<select class="form-control valid" name="" id="activeSiteType">
	                           <option value="0" <c:if test="${marketing.activeSiteType==0 }">selected="selected"</c:if>>平台</option>
	                           <option value="1" <c:if test="${marketing.activeSiteType==1 }">selected="selected"</c:if>>移动端</option>
							   <option value="2" <c:if test="${marketing.activeSiteType==2 }">selected="selected"</c:if>>全部</option>
						   </select>--%>
                       </div>
                  </div>
                  <div class="form-group">
	                   <label class="control-label col-sm-4"><span class="text-danger"></span>促销LOGO：</label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-19">
	                        <c:forEach items="${logolist}" var="logo">
		                       <label class="checkbox-inline">
		                        <input type="checkbox"  name="promotionLogoId" value="${logo.promotionLogoId }"
		                        <c:forEach items="${marketing.marketLogos}" var="mlogo">
		                          <c:if test="${mlogo.promotionLogoId==logo.promotionLogoId}">
		                          checked="checked"
		                          </c:if>
		                        </c:forEach>
		                        />
		                        <img  alt="${logo.promotionLogoName}" title="${logo.promotionLogoName}" src="${logo.promotionLogoUrl }" height="20" />
		                        </label>
	                        </c:forEach>
	                   </div>
                  </div>
                  </c:if>
                  <div class="form-group">
                    <label class="control-label col-sm-4">促销描述：<input type="hidden" name="marketingDes" id="marketingDesThree"/></label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-19">
                      <div class="summernoteThree">${marketing.marketingDes}</div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-9">
                      <button type="button" class="btn btn-primary" onclick="subFormThree(this);">保存</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            </div>
            </div>

          </div>
        </div>
      </div>
    </div>

 <!-- Modal -->
    <div class="modal fade" id="chooseGoods"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">选择货品</h4>
          </div>
          <div class="modal-body">
            <div class="mb10">
              <form class="form-inline" id="searchGoodsInfo" action="" method="post" >
			         <input type="hidden" name="pageNo" id="pageNo" value=""/>
					 <input type="hidden" name="pageSize" id="pageSize" value=""/>
                        <input type="hidden" name="marketType" id="marketType"/>
                <div class="form-group">
                                                         商品名称：<input type="text" class="form-control" placeholder="商品名称"  name="goodsName">
                </div>
               <div class="form-group">
                                                         商品编号：<input type="text" class="form-control" placeholder="商品编号"  name="goodsNo">
                </div>
                <div class="form-group">
                                                         货品编号：<input type="text" class="form-control" placeholder="货品编号" name="goodsInfoItemNo">
                </div>
                <div class="form-group">
                                                         货品售价：<input type="text" class="form-control isNumber" id="lowgoodsInfoPrice" name="lowGoodsInfoPrice">
                     -<input type="text" class="form-control isNumber"  id="highgoodsInfoPrice" name="highGoodsInfoPrice">
                </div>
                <div class="form-group">
                  <button type="button" class="btn btn-info" onclick="chooseProduct();">搜索</button>
                </div>
              </form>
            </div>
            <table class="table table-striped table-hover table-bordered" id="productAddList">
              <thead>
              <tr>
                <th width="50"><input <c:if test="${marketingFlag=='GRP' || marketingFlag=='PCG'}">style="display: none"</c:if> type="checkbox" onchange="chooseAllPro(this);" id="chooseAllPro"></th>
                <th width="100">货品图片</th>
                <th width="100">货品规格</th>
                <th width="150">货品编号</th>
                <th>货品名称</th>
                 <th>价格</th>
                 <th>会员价格</th>

              </tr>
              </thead>
              <tbody>

              </tbody>
            </table>
            <div class="table_foot" id="productAddList_table_foot">
              <div class="table_pagenav pull-right">
                <div class="meneame">

                </div>
              </div>
              <div class="clr"></div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>



    <!-- Modal -->
    <div class="modal fade" id="chooseCates"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">选择类目</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-6">选择类目：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <div class="area_choose">
                    <ul id="treeDemo" class="ztree"></ul>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="trueCate();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
          <!-- Modal -->
          <div class="modal fade" id="errorT"  role="dialog">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title">操作提示</h4>
                      </div>
                      <div class="modal-body">
                          <form class="form-horizontal">
                              <div class="form-group" style="text-align:center;">
                                 减值应当小于满值！
                              </div>
                          </form>
                      </div>
                      <div class="modal-footer">
                          <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                      </div>
                  </div>
              </div>
          </div>
    <!-- Modal -->
    <div class="modal fade" id="chooseBrands"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">选择品牌</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-6"><span class="text-danger">*</span>选择品牌：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">

                  <select data-live-search="true" multiple id="brand">
	                   <c:forEach items="${brandlist }" var="brand" varStatus="index">
	                  	 	<option value="${brand.brandId}" >${brand.brandName}</option>
	                   	</c:forEach>
                  </select>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="chooseBrand();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>


	<!-- 优惠券DIV -->


 <!-- Modal -->
    <div class="modal fade" id="chooseCoupon"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">选择优惠券</h4>
          </div>
          <div class="modal-body">
            <div class="mb10">
              <form class="form-inline">
                <div class="form-group">
                  <input type="text" class="form-control" placeholder="优惠券关键字" id="searchCouponName">
                </div>
                <div class="form-group">
                  <button type="button" class="btn btn-info" onclick="choosecoupon('');">搜索</button>
                </div>
              </form>
            </div>
            <table class="table table-striped table-hover table-bordered" id="couponAddList">
              <thead>
              <tr>
                <th width="50"><input type="checkbox" onchange="chooseAllCoupon(this);" id="chooseAllCoupon"></th>
                <th width="150">优惠券名称</th>
                <th width="200">开始时间</th>
                <th width="200">结束时间</th>
                <th width="100">是否发布</th>
              </tr>
              </thead>
              <tbody>

              </tbody>
            </table>
            <div class="table_foot" id="couponAddList_table_foot">
              <div class="table_pagenav pull-right">
                <div class="meneame">

                </div>
              </div>
              <div class="clr"></div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
    <div class="modal fade" id="by" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加包邮促销</h4>
          </div>
          <div class="modal-body">
            <div class="modal-article">
              <p><em>1.</em>添加包邮促销，添加信息如下图</p>
              <img src="<%=basePath %>/images/syshelp/img_c05.png" alt="">
              <p><em>2.</em>保存成功后，该促销添加成功</p>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>/js/common/common_alert.js"></script>
    <script>

        //图片回调
        function saveChoooseImage(url,id) {
            if(typeof (url) != 'string') {
                url = url[0];
            }
            if(url.indexOf(',')!=-1){
                url=url.substring(0,url.indexOf(','));
            }
            $("#rushImagestr").html("<img height='90' width='150' src='"+url+"'/>");
            $("#rushImage").val(url);

        }
      $(function(){



          $(".chooseProimg").click(function(){
              art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=1', {
                  lock: true,
                  opacity:0.3,
                  width: '900px',
                  height: '620px',
                  title: '选择图片'
              });
          });
          $("#updateFormOne").validate();
          $("#updateFormTwo").validate();
          $("#updateFormThree").validate();
        /* 表单项的值点击后转换为可编辑状态 */
        $('.form_value').click(function(){
          var formItem = $(this);
          if(!$('.form_btns').is(':visible')) {
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

        $(".codexchoose").change(function(){
        	var $arr = $(this).val().split('-');
        		$(this).parents(".tab-pane").find('.codexDiv').hide();
        		$(this).parents(".tab-pane").find('.codexDiv'+$arr[1]).show();
        		$(this).parents(".tab-pane").find('.addChoose').hide();
        		$(this).parents(".tab-pane").find('.addChoose'+$arr[1]).show();
        		$(this).parents(".tab-pane").find("input[name='codexId']").val($arr[0]);
        		$(this).parents(".tab-pane").find("input[name='codexType']").val($arr[1]);
        		$(this).parents(".tab-pane").find("input[name='codexType']").val($arr[1]);
        		$(this).parents(".tab-pane").find('.codexDiv input[type="text"]').each(function(){
        			$(this).removeClass('required');
        		});
        		$(this).parents(".tab-pane").find('.codexDiv'+$arr[1]+' input[type="text"]').each(function(){
        			$(this).addClass('required');

        		});
        		if($arr[1]=='5'){
        			$(this).parents(".tab-pane").find(".codexDiv5").find("input[name='fullPrice']").addClass("required");
        			$(this).parents(".tab-pane").find(".codexDiv5").find("input[name='fullPrice']").addClass("isNumber");
        			$(this).parents(".tab-pane").find("input[name='reducePrice']").addClass("required");
        			$(this).parents(".tab-pane").find("input[name='reducePrice']").addClass("isNumber");
        		}
        		if($arr[1]=='8'){
        			$(this).parents(".tab-pane").find(".codexDiv8").find("input[name='fullPrice']").addClass("required");
        			$(this).parents(".tab-pane").find(".codexDiv8").find("input[name='fullPrice']").addClass("isNumber");
        			$(this).parents(".tab-pane").find("input[name='fullbuyDiscount']").addClass("required");
        			$(this).parents(".tab-pane").find("input[name='fullbuyDiscount']").addClass("zeroOne");
        		}
        		if($arr[1]=='13'){
        			$(this).parents(".tab-pane").find("input[name='packagesNo']").addClass("required");
        			$(this).parents(".tab-pane").find("input[name='packagesNo']").addClass("digits");
        			$(this).parents(".tab-pane").find("input[name='packagebuyDiscount']").addClass("required");
        			$(this).parents(".tab-pane").find("input[name='packagebuyDiscount']").addClass("zeroOne");
        		}
        		if($arr[1]=='14'){
        			$(this).parents(".tab-pane").find("input[name='countNo']").addClass("required");
        			$(this).parents(".tab-pane").find("input[name='countNo']").addClass("digits");
        			$(this).parents(".tab-pane").find("input[name='countMoney']").addClass("required");
        			$(this).parents(".tab-pane").find("input[name='countMoney']").addClass("isNumber");
        		}
        });

        /*切换附加赠送*/
        $(".addGiveType").change(function(){
       	 if($(this).val()==0){
       		 $(this).parents(".tab-pane").find(".giveIntegral").removeAttr("disabled");
       		 $(this).parents(".tab-pane").find(".giveIntegral").addClass("required");
       		 $(this).parents(".tab-pane").find(".couponId").attr("disabled","disabled");
       	 }else if($(this).val()==1){
       		 $(this).parents(".tab-pane").find(".couponId").removeAttr("disabled");
       		 $(this).parents(".tab-pane").find(".giveIntegral").removeClass("required error");
       		 $(this).parents(".tab-pane").find(".giveIntegral").next("label").hide();
       		 $(this).parents(".tab-pane").find(".giveIntegral").attr("disabled","disabled");
       	 }
      });
        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').selectpicker();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 下面是表单里面的日期时间选择相关的 */
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
          weekStart : 1,
          autoclose : true,
          language : 'zh-CN',
          pickerPosition : 'bottom-left',
          todayBtn : true,
          viewSelect : 'hour'
        });
        $('.form_date').datetimepicker({
          format : 'yyyy-mm-dd',
          weekStart : 1,
          autoclose : true,
          language : 'zh-CN',
          pickerPosition : 'bottom-left',
          minView : 2,
          todayBtn : true
        });
        /* 下面是表单里面的日期时间选择相关的 END */


          /*下面是时间选择器开始时间不能大于结束时间设置  START*/
          //单品促销开始结束时间配置
          var startTime = $("#startTime").val();
          var endTime = $("#endTime").val();
          $('#endpicker').datetimepicker('setStartDate', startTime);
          $('#startpicker').datetimepicker('setEndDate', endTime);
          $('#endpicker')
                  .datetimepicker()
                  .on('show', function (ev) {
                      startTime = $("#startTime").val();
                      endTime = $("#endTime").val();
                      $('#endpicker').datetimepicker('setStartDate', startTime);
                      $('#startpicker').datetimepicker('setEndDate', endTime);
                  });
          $('#startpicker')
                  .datetimepicker()
                  .on('show', function (ev) {
                      endTime = $("#endTime").val();
                      startTime = $("#startTime").val();
                      $('#startpicker').datetimepicker('setEndDate', endTime);
                      $('#endpicker').datetimepicker('setStartDate', startTime);
                  });
          /*下面是时间选择器开始时间不能大于结束时间设置  END*/


          /*下面是时间选择器开始时间不能大于结束时间设置  START*/
          // 类目促销开始结束时间配置
          var startTime2 = $("#startTime2").val();
          var endTime2 = $("#endTime2").val();
          $('#endpicker2').datetimepicker('setStartDate', startTime2);
          $('#startpicker2').datetimepicker('setEndDate', endTime2);
          $('#endpicker2')
                  .datetimepicker()
                  .on('show', function (ev) {
                      startTime2 = $("#startTime2").val();
                      endTime2 = $("#endTime2").val();
                      $('#endpicker2').datetimepicker('setStartDate', startTime2);
                      $('#startpicker2').datetimepicker('setEndDate', endTime2);
                  });
          $('#startpicker2')
                  .datetimepicker()
                  .on('show', function (ev) {
                      endTime2 = $("#endTime2").val();
                      startTime2 = $("#startTime2").val();
                      $('#startpicker2').datetimepicker('setEndDate', endTime2);
                      $('#endpicker2').datetimepicker('setStartDate', startTime2);
                  });
          /*下面是时间选择器开始时间不能大于结束时间设置  END*/


          /*下面是时间选择器开始时间不能大于结束时间设置  START*/
          //品牌开始结束时间配置
          var startTime3 = $("#startTime3").val();
          var endTime3 = $("#endTime3").val();
          $('#endpicker3').datetimepicker('setStartDate', startTime3);
          $('#startpicker3').datetimepicker('setEndDate', endTime3);
          $('#endpicker3')
                  .datetimepicker()
                  .on('show', function (ev) {
                      startTime3 = $("#startTime3").val();
                      endTime3 = $("#endTime3").val();
                      $('#endpicker3').datetimepicker('setStartDate', startTime3);
                      $('#startpicker3').datetimepicker('setEndDate', endTime3);
                  });
          $('#startpicker3')
                  .datetimepicker()
                  .on('show', function (ev) {
                      endTime3 = $("#endTime3").val();
                      startTime3 = $("#startTime3").val();
                      $('#startpicker3').datetimepicker('setEndDate', endTime3);
                      $('#endpicker3').datetimepicker('setStartDate', startTime3);
                  });
          /*下面是时间选择器开始时间不能大于结束时间设置  END*/

        /* 富文本编辑框 */
        $('.summernote').summernote({
          height: 300,
          tabsize: 2,
          lang: 'zh-CN',
          onImageUpload: function(files, editor, $editable) {
              sendFile(files,editor,$editable);
          }
        });

        /* 富文本编辑框 */
        $('.summernoteTwo').summernote({
          height: 300,
          tabsize: 2,
          lang: 'zh-CN',
          onImageUpload: function(files, editor, $editable) {
              sendFile(files,editor,$editable);
          }
        });

        /* 富文本编辑框 */
        $('.summernoteThree').summernote({
          height: 300,
          tabsize: 2,
          lang: 'zh-CN',
          onImageUpload: function(files, editor, $editable) {
              sendFile(files,editor,$editable);
          }
        });

        /* 下面是关于树形菜单 END */

        /* 下面是表单里面的填写项提示相关的 */
        $('.cuxiaomingchen').popover({
          content : '促销名称',
          trigger : 'hover'
        });


      });


      function chooseProduct(obj){
          doAjax(1,8,obj);
          $('#chooseGoods').modal('show');
      }


  	/*点击添加货品的时候*/

		function doAjax(pageNo, pageSize,obj)
		{
            if(obj==10){
                $("#marketType").val(10);
            }else if(obj===15){
                $("#marketType").val(15);
            }
			 $("#pageNo").val(pageNo),
	  		 $("#pageSize").val(pageSize),
		     $("#chooseAllPro").attr("checked",false);
	  		    $.ajax({
	  		    	url:"newqueryProductForCoupon.htm",
	  		    	data:$("#searchGoodsInfo").serialize(),
	  		    	async:true,
	  		    	success:function(data){
	  		    		 var list = data.list;
	  			        var productListHtml = "";
	  			        for (var i = 0; i < list.length; i++)
	  			        {
	  			            productListHtml = productListHtml + "<tr>" +"<td class='tc'><input type='checkbox' class='productId' name='productId' onclick='addpro(this);'";

	  			            	var pro =  document.getElementsByName("goodsIdP");
	  			            	for(var j=0;j<pro.length;j++){
	  			            		if(pro[j].value==list[i].goodsInfoId){
	  			            			 productListHtml = productListHtml +' checked="checked" ';
	  			            		}
	  			            	}
	  			            productListHtml = productListHtml+" value='" + list[i].goodsInfoId + "'/></td>";
	  			            productListHtml+='<td><img src="'+list[i].goodsInfoImgId+'" class="goodsImg"  width="50" height="50"/></td>';
	  			            productListHtml+= "<td  class='goodsTag' >" ;
	  			            if (list[i].specVo.length > 0)
	  			            {
	  			                for (var k = 0; k < list[i].specVo.length; k++)
	  			                {
	  			                    productListHtml = productListHtml + list[i].specVo[k].spec.specName;
	  			                    productListHtml = productListHtml + ":" + list[i].specVo[k].goodsSpecDetail.specDetailName + "<br/>";
	  			                }
	  			            }
	  			            productListHtml = productListHtml + "</td>" + "<td class='goodsNo'>" + list[i].goodsInfoItemNo+ "</td>" + "<td  class='goodsName' title='"+list[i].goodsInfoName+"' >" + list[i].goodsInfoName + "</td>"+"<td name='goodsPrices' class='goodsInfoPreferPrice'>"+list[i].goodsInfoPreferPrice  +"</td>"+"<td class='goodsInfoVipPrice'>"+list[i].goodsInfoVipPrice  + "</td>"+ "</tr>";
	  			        }
	  			        $("#productAddList tbody").html(productListHtml);
	  			        $("input[type=button]").button();
	  			        /*添加页脚*/
	  			        $("#productAddList .meneame").html("");
	  			        var foot = "";
	  			        var i = 1;
	  			        for (var l = data.startNo; l <= data.endNo; l++)
	  			        {
	  			            if ((i - 1 + data.startNo) == data.pageNo)
	  			            {
	  			                foot = foot + "<span class='current'> " + (i - 1 + data.startNo) + "</span>";
	  			            }
	  			            else
	  			            {
	  			                foot = foot + "<a onclick='doAjax(" + (i - 1 + data.startNo) + "," + (data.pageSize) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
	  			            }
	  			            i++;
	  			        }
	  			        foot = foot + "";
	  			        /*添加tfoot分页信息*/
	  			        $("#productAddList_table_foot .meneame").html(foot);
	  		    	}
	  		    });
		}
		/*添加货品的时候 分页*/
		/*改变每页显示的行数*/
		function changePageShow()
		{
		    doAjax( 1, $("#list_size").val());
		}
		/*跳转到某一页*/
		function changeShowPage( pageSize)
		{
		    doAjax( $("#list_pageno").val(), pageSize);
		}


		function addpro(obj){
			var flag =$("#marketingFlag").val();
			if(flag=="ZK"){
				var productId=$(obj).val();
			    var goodsImg=$(obj).parents("tr").find(".goodsImg").attr("src");
			    var goodsNo=$(obj).parents("tr").find(".goodsNo").text();
			    var goodsName=$(obj).parents("tr").find(".goodsName").text();
			    var goodsTag=$(obj).parents("tr").find(".goodsTag").html();
			    var goodsPrice=$(obj).parents("tr").find(".goodsInfoPreferPrice").text();
                var vipDiscountPrice=$(obj).parents("tr").find(".goodsInfoVipPrice").text();
			    if(obj.checked==true){
			        var htm = "<tr id='goods_tr_"+productId+"'>";
			    	htm+='<td width="8%"><img src="'+goodsImg+'" width="30" height="30"/><input type="hidden" name="goodsIdP" id="goods_Id_'+productId+'" value="'+productId+'"/></td>';
			    	htm+='<td width="12%">'+goodsTag+'</td>';
			    	htm+='<td width="15%">'+goodsNo+'</td>';
			    	htm+='<td  width="9%">'+goodsName+'</td>';
			    	htm+='<td  width="5%" class="goodsPrice">'+goodsPrice+'</td>';
			    	htm+='<td  width="10%"><input type="text" class="form-control required zeroOne discountInfos" name="discountInfo" id="discount_info_'+productId+'" style="width:50px;" onblur="hyzkchange('+goodsPrice+',this);"/></td>';
			    	htm+='<td  width="5%"><span class="zhj">'+goodsPrice+'</span><input type="hidden" class="form-control" name="discountPrice" /><input type="hidden" class="form-control" name="discountFlag" id="discount_prices_'+productId+'" value="0" readonly  ></td>';

                    htm+='<td  width="5%" class="vipGoodsPrices">'+vipDiscountPrice+'</td>';
                    htm+='<td  width="10%"><input type="text" class="form-control required zeroOne vipDiscountInfos" name="vipDiscountInfo" id="vip_discount_info_'+productId+'" style="width:50px;" onblur="hyzkchange('+vipDiscountPrice+',this);"/></td>';
                    htm+='<td  width="6%"><span class="hyzhj">'+vipDiscountPrice+'</span><input type="hidden" class="form-control" name="vipDiscountPrice" /><input type="hidden" class="form-control" name="vipDiscountFlag" id="vipdiscount_prices_'+productId+'" value="0" readonly  ></td>';
                    htm+='<td width="12%"><input type="button" onclick="mofensingle('+goodsPrice+','+vipDiscountPrice+',this,1);" value="移除分" class="btn btn-sm btn-default"/>';
			    	htm+='<input type="button" onclick="mofensingle('+goodsPrice+','+vipDiscountPrice+',this,0);" value="移除分角" class="btn btn-sm btn-default"/>';
			    	htm+='<button onclick="removeTr(this);" class="btn btn-sm btn-default">移除</button></td>';
			  	  	htm+="</tr>";
			    $("#readproduct tbody").append(htm);
			    }else{
			      $("#goods_tr_"+productId).remove();

			    }
			}else{

                if(flag=='GRP'||flag=='PCG'){

                    var productId=$(obj).val();
                    var goodsImg=$(obj).parents("tr").find(".goodsImg").attr("src");
                    var goodsNo=$(obj).parents("tr").find(".goodsNo").text();
                    var goodsName=$(obj).parents("tr").find(".goodsName").text();
                    var goodsTag=$(obj).parents("tr").find(".goodsTag").html();
                    var goodsPrice=$(obj).parents("tr").find(".goodsInfoPreferPrice").html();
                    if(obj.checked==true){
                        $("input[name='goodsIdP']").each(function(){
                            $("#goods_tr_"+$(this).val()).remove();
                        });

                        var htm = "<tr id='goods_tr_"+productId+"'>";
                        htm+='<td width="100"><img src="'+goodsImg+'" width="50" height="50"/><input type="hidden" name="goodsIdP" id="goods_Id_'+productId+'" value="'+productId+'"/></td>';
                        htm+='<td width="100">'+goodsTag+'</td>';
                        htm+='<td width="150">'+goodsNo+'</td>';
                        htm+='<td  width="300">'+goodsName+'</td>';
                        htm+='<td  width="100">'+goodsPrice+'</td>';
                        htm+='<td width="100"><button onclick="removeTr(this);">移除</button></td>';
                        htm+="</tr>";
                        $("#readproduct tbody").append(htm);
                        $("input[name='productId']").each(function(){
                            if($(this).val()!=$(obj).val()){
                                this.checked=false;
                            }
                        });
                    }else{
                        $("#goods_tr_"+productId).remove();

                    }
                }else{

			    var productId=$(obj).val();
			    var goodsImg=$(obj).parents("tr").find(".goodsImg").attr("src");
			    var goodsNo=$(obj).parents("tr").find(".goodsNo").text();
			    var goodsName=$(obj).parents("tr").find(".goodsName").text();
			    var goodsTag=$(obj).parents("tr").find(".goodsTag").html();
			    var goodsPrice=$(obj).parents("tr").find(".goodsInfoPreferPrice").html();
			    var vipDiscountPrice=$(obj).parents("tr").find(".goodsInfoVipPrice").text();
			    if(obj.checked==true){
			        var htm = "<tr id='goods_tr_"+productId+"'>";
			    	htm+='<td width="100"><img src="'+goodsImg+'" width="50" height="50"/><input type="hidden" name="goodsIdP" id="goods_Id_'+productId+'" value="'+productId+'"/></td>';
			    	htm+='<td width="100">'+goodsTag+'</td>';
			    	htm+='<td width="150">'+goodsNo+'</td>';
			    	htm+='<td  width="300">'+goodsName+'</td>';
			    	htm+='<td  width="100">'+goodsPrice+'</td>';
			    	htm+='<td  width="100">'+vipDiscountPrice+'</td>';
			    	htm+='<td width="100"><button onclick="removeTr(this);">移除</button></td>';
			  	  	htm+="</tr>";
			    $("#readproduct tbody").append(htm);
			    }else{
			      $("#goods_tr_"+productId).remove();

                }
                }
			}
		}

		function removeTr(obj){
		    $(obj).parents("tr").remove();
		}


		function chooseAllPro(obj){


		    if(obj.checked){
	              $("input[name='productId']").each(function(){
	                 this.checked=true;
	                 $("#goods_tr_"+$(this).val()).remove();
	                 addpro(this);
	              });
		      }else{
		          $("input[name='productId']").each(function(){
		                this.checked=false;
		                addpro(this);
		           });
		      }
		}

			//保存商品促销
		   function subFormOne(obj){
		          var pro =  document.getElementsByName("goodsIdP");
		          var cus =  $(obj).parents(".tab-pane").find("input[name='lelvelId']:checked");
		          var f = true;
		          if(pro.length==0){
		              $("#ps").html('请选择货品');
		              $("#ps").addClass("error");
		              f=false&&f;
		          }else{
		              f=true&&f;
		              $("#ps").html('');
		          }
		          /*if(cus.length==0){
		        	  $(obj).parents(".tab-pane").find("#cs").html('请选择会员等级');
		        	  $(obj).parents(".tab-pane").find("#cs").addClass("error");
		              f=false&&f;
		          }else{
		              f=true&&f;
		              $(obj).parents(".tab-pane").find("#cs").html('');
		          }*/
		          var codexType=$("#updateFormOne").find("input[name='codexType']").val();
		          if(codexType=='3'){
		              var coup = $("#updateFormOne").find("input[name='couponIds']");
		              if(coup.length==0){
		                  $(".cp1").html('请选择优惠券');
			              $(".cp1").addClass("error");
			              f=false&&f;
		              }else{
		                  $(".cp1").html('');
			              $(".cp1").removeClass("error");
		              }
		          }

			      //判断是哪种促销类型
                  var marketingFlags = $("#marketingFlags").val();
				  //获取直降的价格
				  var offValue = $("#offValue").val();
				  var offVipValue = $("#offVipValue").val();
                  //直降促销
			      if(marketingFlags=="DP"){
					  //获取促销货品的价格
					  var goodsPrices = document.getElementsByName("goodsPrices");
					  //验证直降的金额不能小于所选货品的金额
					  for(var i=0;i<goodsPrices.length;i++){
						  //获取第i个价格与直降的金额做比较
						  var pff_price = $(goodsPrices[i]).html();
						  if(parseInt(offValue) > parseInt(pff_price)){
							  $("#offValue_error").html("直降的金额不能大于促销商品的价格！");
							  $("#offValue_error").addClass("error");
							  f=false&&f;
							  return;
						  }
						  if(parseInt(offVipValue) > parseInt(pff_price)){
							  $("#offVipValue_error").html("直降的金额不能大于促销商品的价格！");
							  $("#offVipValue_error").addClass("error");
							  f=false&&f;
							  return;
						  }
					  }
				  }

		          if($("#updateFormOne").valid()&&f){
                      var man=$("input[name='fullPrice']");
                      var jian=$("input[name='reducePrice']");
					  var codexchoose = $("input[name='codexchoose']:checked").val();
					  //如果是满折就不验证满减的价格
					  if(codexchoose == "5-5"){
						  for(var i=0;i<jian.length;i++){
							  if(Number($(jian[i]).val())>=Number($(man[i]).val())){
								  $(jian[i]).addClass("error");
								  $(man[i]).addClass("error");
								  $('#errorT').modal('show');
								  return;
							  }
						  }
					  }
		              $("#marketingDes").val($(".summernote").code());
                      if( $("#marketingDes").val().length>1000){
                          showTipAlert("输入字符过长!")
                          return ;
                      }
                      if($("#marketingFlag").val()==11){
                      if($("#rushImage").val()==''||$("#rushImage").val()==null ){
                          showTipAlert("请选择抢购图片")
                          return;
                      }
                      }
		              $("#updateFormOne").submit();
		          }

		      }



			/*******************类目促销**************************/

			function chooseCate(){
			    if(!$("#isallCate").prop("checked")){

			        $.get("queryallgoodcate.htm?catId=0&CSRFToken=${token}",function(data){
						if(null != data && data.length>0){
						    var zNodes = new Array();
						    /* 下面是关于树形菜单 */
						       var setting = {
							          check: {
							            enable: true,
							            chkboxType : {"Y":"ps","N":"ps"}
							          },
							          data: {
							            simpleData: {
							              enable: true
							            }
							          },
							          view:{
							          	showIcon:false
							  			}
							        };


							for(var i =0;i<data.length;i++){
								  for (var i = 0; i < data.length; i++)
							        {

								      if(data[i].catParentId!=null){
								          var node = {
									                id : data[i].catId, pId : data[i].catParentId, name : data[i].catName, open : false
									            };
									            zNodes.push(node);
								      }

							        }
							}
							  var zTree;
					          $.fn.zTree.init($("#treeDemo"), setting, zNodes);

					          var pro =  document.getElementsByName("cateIdp");
					          var treeObject = $.fn.zTree.getZTreeObj("treeDemo");
					          for(var i=0;i<pro.length;i++){
					          	var treeNode= treeObject.getNodeByParam("id", pro[i].value);
					          	treeObject.checkNode(treeNode,true,true);
					           }

					          $('#chooseCates').modal('show');
						}
					});

			    }

			}

			function trueCate(){
			    var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
	            nodes=treeObj.getCheckedNodes(true);
			    var htm="";
	            for(var i=0;i<nodes.length;i++){

	                if(!nodes[i].isParent){
	                    htm+="<tr>";
	    	            htm+='<td width="100">'+nodes[i].id+'<input type="hidden" name="cateIdp" id="cate_Id_p'+nodes[i].id+'" value="'+nodes[i].id+'"></td>';
	    	            htm+='<td width="250">'+nodes[i].name+'</td>';
	    	            htm+='<td width="100"><button onclick="removeTr(this);">移除</button></td>';
	    	            htm+="</tr>";
			        }
	            }
	           $("#list tbody").html(htm);
	           $('#chooseCates').modal('hide');
			}


			function checkIsAll(obj){
			    if(obj.checked==true){
			       	$("#isAlls").val(1);
			       	$("#towCate").hide();
			    }else{
			    	$("#isAlls").val(0);
			     	$("#towCate").show();
			    }
			}

			function subFormTwo(obj){
			    var f = true;
			    var cus =  $(obj).parents(".tab-pane").find("input[name='lelvelId']:checked");
			     if(!$("#isallCate").prop('checked')){
			         var pro =  document.getElementsByName("cateIdp");
			         if(pro.length==0){
			              $("#pc").html('请选择范围');
			              $("#pc").addClass("error");
			              f=false&&f;
			          }else{
			              f=true&&f;
			              $("#pc").html('');
			          } 
			     }
			     /*if(cus.length==0){
		        	  $(obj).parents(".tab-pane").find("#cs").html('请选择会员等级');
		        	  $(obj).parents(".tab-pane").find("#cs").addClass("error");
		              f=false&&f;
		          }else{
		              f=true&&f;
		              $(obj).parents(".tab-pane").find("#cs").html('');
		          }*/
			     var codexType=$("#updateFormTwo").find("input[name='codexType']").val();
		          if(codexType=='3'){
		              var coup = $("#updateFormTwo").find("input[name='couponIds']");
		              if(coup.length==0){
		                  $(".cp2").html('请选择优惠券'); 
			              $(".cp2").addClass("error");
			              f=false&&f;
		              }else{
		                  $(".cp2").html(''); 
			              $(".cp2").removeClass("error");
		              }
		          }
		          
		          if($("#updateFormTwo").valid()&&f){
		              $("#marketingDesTwo").val($(".summernoteTwo").code());
                      if( $("#marketingDesTwo").val().length>1000){
                          showTipAlert("输入字符过长!")
                          return ;
                      }
		              $("#updateFormTwo").submit();
		          }   
		        
			}
			
			/***********************************品牌促销***********************************/
			
			function isAllThree(obj){
			    if(obj.checked==true){
			       	$("#isAllsThree").val(1);
			       	$("#threeBrand").hide();
			    }else{
			    	$("#isAllsThree").val(0);
			    	$("#threeBrand").show();
			    }
			}
			function subFormThree(obj){
			    var f = true;
			    var cus =  $(obj).parents(".tab-pane").find("input[name='lelvelId']:checked");
			     if(!$("#isAllsThreeChecked").prop('checked')){
			         var pro =  document.getElementsByName("brandIdP");
			         if(pro.length==0){
			              $("#pb").html('请选择范围');
			              $("#pb").addClass("error");
			              f=false&&f;
			          }else{
			              f=true&&f;
			              $("#pb").html('');
			          }
			     }
			     /*if(cus.length==0){
		        	  $(obj).parents(".tab-pane").find("#cs").html('请选择会员等级');
		        	  $(obj).parents(".tab-pane").find("#cs").addClass("error");
		              f=false&&f;
		          }else{
		              f=true&&f;
		              $(obj).parents(".tab-pane").find("#cs").html('');
		          }*/

			     var codexType=$("#updateFormThree").find("input[name='codexType']").val();
		          if(codexType=='3'){
		              var coup = $("#updateFormThree").find("input[name='couponIds']");
		              if(coup.length==0){
		                  $(".cp3").html('请选择优惠券'); 
			              $(".cp3").addClass("error");
			              f=false&&f;
		              }else{
		                  $(".cp3").html(''); 
			              $(".cp3").removeClass("error");
		              }
		          }
		          
		          
		          if($("#updateFormThree").valid()&&f){
		              $("#marketingDesThree").val($(".summernoteThree").code());
                      if( $("#marketingDesThree").val().length>1000){
                          showTipAlert("输入字符过长!")
                          return ;
                      }
		              $("#updateFormThree").submit();
		          }   
		        
			}
			 
			function chooseBrandsView(){
			    if(!$("#isAllsThreeChecked").prop('checked')){
			        var pro =  document.getElementsByName("brandIdP");
			        if(pro!=null){
			            var ids="";
			            for(var i=0;i<pro.length;i++){
			                ids+=pro[i].value;
			                if(i<pro.length-1){
			                    ids+=",";
			                }
			            }
			            $("#brand").val(ids);
			            /* 为选定的select下拉菜单开启搜索提示 */
			            $('select[data-live-search="true"]').selectpicker('refresh');
			        }else{
			            $("#brand").val('');   
			            /* 为选定的select下拉菜单开启搜索提示 */
			            $('select[data-live-search="true"]').selectpicker('refresh');
			        }
			        $('#chooseBrands').modal('show');
			    }
			}  
			
			function chooseBrand(){
			    var $arr = new Array();
			    var brandstr=$("#brand").val();
			    if(brandstr!=null){
			        brandstr=brandstr.toString();
			        $arr= brandstr.split(",");
			        var htm="";  
			        for(var i=0;i<$arr.length;i++){  
			            htm+="<tr>";
	    	            htm+='<td width="100">'+$arr[i]+'<input type="hidden" name="brandIdP" id="brandIdP'+$arr[i]+'" value="'+$arr[i]+'"></td>'; 
	    	            htm+='<td width="250">'+$("#brand option[value='"+$arr[i]+"']").text()+'</td>';
	    	            htm+='<td width="100"><button onclick="removeTr(this);">移除</button></td>';
	    	            htm+="</tr>";
			        }  
			    }
			   $("#brandlist tbody").html('');
			   $("#brandlist tbody").html(htm);
			   $('#chooseBrands').modal('hide');
			}
			
			
			
			
			
			
			/********************************优惠券促销 选择优惠券*******************************/
			var couponDivId = "";
			function choosecoupon(divId){
			    couponDivId = divId;
			    searchCoupon(1,8);
			    $('#chooseCoupon').modal('show');
			}
			
	function searchCoupon(pageNo,pageSize){
			var couponName=$("#searchCouponName").val();
			var html="";
			if(couponName!=null&&couponName!=""){ 
				html="&couponSearchName="+encodeURI(encodeURI(couponName));     
			}
			$.post("querycouponlistall.htm?pageSize="+pageSize+"&CSRFToken=${token}&pageNo="+pageNo+html,function(data){
				if(data!=null){
					 var list = data.list;
				       var productListHtml = "";
				        for (var i = 0; i < list.length; i++){
				        	productListHtml +='<tr class="tableListTr">';	           
				            productListHtml += '<td class="tc">';
				            productListHtml += '<input type="checkbox" name="couponId"';
				            if($("#gl").html()!=''){
				    			 var couponIds = document.getElementsByName("couponIds");
				    			 for(var j=0;j<couponIds.length;j++){  
				    				 if(couponIds[j].value==list[i].couponId){ 
				    					 productListHtml += ' checked="checked" ';  
				    				 } 
				    			 } 
				    		} 
				            productListHtml += ' value="'+list[i].couponId +'" onclick="addcoupon(this);"/>'; 
				            
				            productListHtml += '</td><td class="couponName">'+list[i].couponName + "</td>";
				             
				            productListHtml = productListHtml +"<td class='couponStartTime'>" + timeStamp2String(list[i].couponStartTime) + "</td>";
				            productListHtml = productListHtml +"<td class='couponEndTime'>" + timeStamp2String(list[i].couponEndTime) + "</td>";
				            productListHtml = productListHtml +"<td class='tc'>";
				            if(list[i].couponIsShow==1){
				            	 productListHtml = productListHtml +"发布";
				            }else{
				            	 productListHtml = productListHtml +"未发布"; 
				            } 
				            productListHtml = productListHtml + "</td>";
				            productListHtml = productListHtml +"</tr>";
				      
				        } 
				        $("#couponAddList tbody").html(productListHtml);
				        $("#couponList tbody input:checked").each(function(){
				        	var $this = $(this),
				        		$id = $this.val();
				        	if($("#couponInfos tr#coupon"+$id).length <= 0) {
				        		$this.prop("checked",false);
				        	};
				        });
				        /*添加页脚 */
				        $("#couponAddList_table_foot .meneame").html('');
				        var foot = '';
				        var i = 1;
				        for (var l = data.startNo; l <= data.endNo; l++)
				        {
				            if ((i - 1 + data.startNo) == data.pageNo)
				            {
				                foot = foot + "<span class='current'> " + (i - 1 + data.startNo) + "</span>";
				            }
				            else
				            {
				                foot = foot + "<a onclick='searchCoupon(" + (i - 1 + data.startNo) + "," + (data.pageSize) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
				            }
				            i++;
				        }
				        foot = foot + "";
				        /*添加tfoot分页信息 */
				        $("#couponAddList_table_foot .meneame").html(foot);
				   
					}
		});

	}	
	
	
	function chooseAllCoupon(obj){
	    
	    if(obj.checked){
              $("input[name='couponId']").each(function(){
                 this.checked=true;
                 $("#"+couponDivId).find(".goods_tr_"+$(this).val()).remove();
                 addcoupon(this);
              });  
	      }else{
	          $("input[name='couponId']").each(function(){
	                 this.checked=false;
	                 addcoupon(this);
	              });   
	      }
	}

      function hyzkchange(price,obj){
          var b = (price*$(obj).val()).toFixed(2);
          $(obj).parents("tr").find("input[name='vipDiscountFlag']").val(0);
          $(obj).parents("tr").find(".hyzhj").text(b);
          $(obj).parents("tr").find("input[name='vipDiscountPrice']").val(b);
      }
	function addcoupon(obj){
	    var couponId=$(obj).val();
	    var couponName=$(obj).parents("tr").find(".couponName").text();
	    var couponStartTime=$(obj).parents("tr").find(".couponStartTime").text();
	    var couponEndTime=$(obj).parents("tr").find(".couponEndTime").text();
	    if(obj.checked==true){
	        var htm = "<tr class='goods_tr_"+couponId+"'>";
	    	htm+='<td width="150">'+couponName+'<input type="hidden" name="couponIds" id="coupon_id'+couponId+'" value="'+couponId+'"/></td>';
	    	htm+='<td  width="150">'+couponStartTime+'</td>';
	    	htm+='<td  width="150">'+couponEndTime+'</td>';
	    	htm+='<td width="70"><button onclick="removeTr(this);">移除</button></td>';
	  	  	htm+="</tr>";
	     $("#"+couponDivId+" tbody").append(htm);
	    }else{
	      $("#"+couponDivId).find(".goods_tr_"+couponId).remove();
	    }  
	}
	
	
	/*处理时间格式化*/
	function timeStamp2String(time)
	{
	    var date = new Date(parseFloat(time));
	    var datetime = new Date();
	    datetime.setTime(date);
	    var year = datetime.getFullYear();
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
	    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	}
	
	/**
	 * 全选反选
	 * @param obj
	 * @param name
	 */
	function allunchecked(obj,name){
	      if(obj.checked){
	    	  $(obj).parents(".tab-pane").find($("input[name='"+name+"']")).each(function(){
	                 this.checked=true;  
	              });  
	      }else{
	    	  $(obj).parents(".tab-pane").find($("input[name='"+name+"']")).each(function(){
	                this.checked=false;  
	           });  
	      }
	 }  

	var number=0;
	function addmore(obj,codetype){
        number+=1;
		var length =$(obj).parents(".tab-pane").find(".codexDiv"+codetype).size();
		if(codetype==5 && length <3){
			var html='<div class="form-group codexDiv codexDiv'+codetype+'">';
            html+='<div class="form-group">';
			html+='<label class="control-label col-sm-4"><span class="text-danger">*</span></label>';
			html+='<div class="col-sm-3" ><label class="radio-inline">普通会员满</label></div>';
			html+='<div class="col-sm-2">'; 
			html+= '     <input type="text" name="fullPrice" id="fullPrice'+number+'" class="form-control required isNumber">';
			html+= '</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">元</label></div>';
			html+= '<div class="col-sm-1"><label class="radio-inline">减</label></div>';
			html+= '<div class="col-sm-2">';
			html+= '    <input type="text" name="reducePrice" id="reducePrice'+number+'" class="form-control required isNumber">';
			html+='</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">元</label></div>';
            html+='</div>';
            html+='<div class="form-group">';
			html+='<label class="control-label col-sm-4"><span class="text-danger">*</span></label>';
			html+='<div class="col-sm-3"><label class="radio-inline">企业会员满</label></div>';
			html+='<div class="col-sm-2">';
			html+= '     <input type="text" name="vipFullPrice" id="vipFullPrice'+number+'" class="form-control required isNumber">';
			html+= '</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">元</label></div>';
			html+= '<div class="col-sm-1"><label class="radio-inline">减</label></div>';
			html+= '<div class="col-sm-2">';
			html+= '    <input type="text" name="vipReducePrice" id="vipReducePrice'+number+'" class="form-control required isNumber">';
			html+='</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">元</label></div>';
			html+='<div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></div></div>';
			$(obj).parents(".tab-pane").find(".codexDiv"+codetype).last().after(html);
		}
		
		if(codetype==8 && length <3){
			var html='<div class="form-group codexDiv codexDiv'+codetype+'">';
            html+='<div class="form-group">';
			html+='<label class="control-label col-sm-4"><span class="text-danger">*</span></label>';
			html+='<div class="col-sm-3"><label class="radio-inline">普通会员满</label></div>';
			html+='<div class="col-sm-2">'; 
			html+= '     <input type="text" name="fullPrice" id="fullPrice'+number+'" class="form-control required isNumber">';
			html+= '</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">元</label></div>';
			html+= '<div class="col-sm-1"><label class="radio-inline">打</label></div>';
			html+= '<div class="col-sm-2">';
			html+= '    <input type="text" name="fullbuyDiscount" id="fullbuyDiscount'+number+'" class="form-control required zeroOne">';
			html+='</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">折</label></div>';
            html+='</div>';
            html+='<div class="form-group">';
			html+='<label class="control-label col-sm-4"><span class="text-danger">*</span></label>';
			html+='<div class="col-sm-3"><label class="radio-inline">企业会员满</label></div>';
			html+='<div class="col-sm-2">';
			html+= '     <input type="text" name="vipFullPrice" id="vipFullPrice'+number+'" class="form-control required isNumber">';
			html+= '</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">元</label></div>';
			html+= '<div class="col-sm-1"><label class="radio-inline">打</label></div>';
			html+= '<div class="col-sm-2">';
			html+= '    <input type="text" name="vipFullbuyDiscount" id="vipFullbuyDiscount'+number+'" class="form-control required zeroOne">';
			html+='</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">折</label></div>';
			html+='<div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></div></div>';
			$(obj).parents(".tab-pane").find(".codexDiv"+codetype).last().after(html);
		}
		
		if(codetype==13 && length <3){
			var html='<div class="form-group codexDiv codexDiv'+codetype+'">';
			html+='<label class="control-label col-sm-4"><span class="text-danger">*</span></label>';
			html+='<div class="col-sm-1"><label class="radio-inline">满</label></div>';
			html+='<div class="col-sm-2">'; 
			html+= '     <input type="text" name="packagesNo" id="packagesNo'+number+'" class="form-control required digits">';
			html+= '</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">件</label></div>';
			html+= '<div class="col-sm-1"><label class="radio-inline">打</label></div>';
			html+= '<div class="col-sm-2">';
			html+= '    <input type="text" name="packagebuyDiscount" id="packagebuyDiscount'+number+'"  class="form-control required zeroOne">';
			html+='</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">折</label></div>';
			html+='<div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></div>';
			$(obj).parents(".tab-pane").find(".codexDiv"+codetype).last().after(html);
		}
		
		if(codetype==14 && length <3){
			var html='<div class="form-group codexDiv codexDiv'+codetype+'">';
			html+='<label class="control-label col-sm-4"><span class="text-danger">*</span></label>';
			html+='<div class="col-sm-1"><label class="radio-inline">满</label></div>';
			html+='<div class="col-sm-2">'; 
			html+= '     <input type="text" name="countNo" id="countNo'+number+'" class="form-control required digits">';
			html+= '</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">件</label></div>';
			html+= '<div class="col-sm-1"><label class="radio-inline">共</label></div>';
			html+= '<div class="col-sm-2">';
			html+= '    <input type="text" name="countMoney"  id="countMoney'+number+'"  class="form-control required isNumber">';
			html+='</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">元</label></div>';
			html+='<div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></div>';
			$(obj).parents(".tab-pane").find(".codexDiv"+codetype).last().after(html);
		}
	}
	
	function dellevel(obj){
		$(obj).parent().parent().remove();
	}
      function hyzkchange(price,obj){
          var b = (price*$(obj).val()).toFixed(2);
          $(obj).parents("tr").find("input[name='vipDiscountFlag']").val(0);
          $(obj).parents("tr").find(".hyzhj").text(b);
          $(obj).parents("tr").find("input[name='vipDiscountPrice']").val(b);
      }
	function mofensingle(price,vipPrice,obj,fixed){
		  var obp = $(obj).parents("tr").find("input[name='discountInfo']").val();
		  var b = (price*obp).toFixed(fixed);
        var vobp = $(obj).parents("tr").find("input[name='vipDiscountInfo']").val();
        var vb = (vipPrice*vobp).toFixed(fixed);
		  if(fixed==1){
	 	 	 	$(obj).parents("tr").find("input[name='discountFlag']").val(1); 
		  }else if(fixed==0){
	 	  		$(obj).parents("tr").find("input[name='discountFlag']").val(2); 
		  }
	 	  $(obj).parents("tr").find(".zhj").text(b);
	 	  $(obj).parents("tr").find("input[name='discountPrice']").val(b);
        $(obj).parents("tr").find(".hyzhj").text(vb);
        $(obj).parents("tr").find("input[name='vipDiscountPrice']").val(vb);
		}
		
		function zkchange(price,obj){ 
			  var b = (price*$(obj).val()).toFixed(2);
		 	  $(obj).parents("tr").find("input[name='discountFlag']").val(0); 
		 	  $(obj).parents("tr").find(".zhj").text(b);
		 	  $(obj).parents("tr").find("input[name='discountPrice']").val(b); 
			}
		
		function yichus(fixed){
			if($(".goodsPrices").length!=0){
				$(".goodsPrices").each(function(){
					  var obp = $(this).parents("tr").find("input[name='discountInfo']").val();
					  var b = ($(this).text()*obp).toFixed(fixed);
					  var vobp = $(this).parents("tr").find("input[name='vipDiscountInfo']").val();
					  var vb = ($(this).text()*vobp).toFixed(fixed);
					  if(fixed==1){
				 	     $(this).parents("tr").find("input[name='discountFlag']").val(1); 
					  }else if(fixed==0){
				 	     $(this).parents("tr").find("input[name='discountFlag']").val(2); 
					  }
				 	  $(this).parents("tr").find(".zhj").text(b);
				 	  $(this).parents("tr").find("input[name='discountPrice']").val(b);
				 	  $(this).parents("tr").find(".hyzhj").text(vb);
				 	  $(this).parents("tr").find("input[name='vipDiscountPrice']").val(vb);
				});
			}
		}
		function plzhekou(obj){
			var s = new RegExp(/^0\.[0-9]*[0-9]$/);
			if($(obj).val()!=''&&s.test($(obj).val())){
				if($(".discountInfos").length!=0){
					$(".discountInfos").each(function(){
						$(this).val($(obj).val());
						zkchange($(this).parents("tr").find(".goodsPrices").text(),this);
					});
				}
			}else{
				showNoDeleteConfirmAlert('请输入0~1之间的数字');   
			}
		}

      function hyplzhekou(obj){
          var s = new RegExp(/^0\.[0-9]*[0-9]$/);
          if($(obj).val()!=''&&s.test($(obj).val())){
              if($(".vipDiscountInfos").length!=0){
                  $(".vipDiscountInfos").each(function(){
                      $(this).val($(obj).val());
                      hyzkchange($(this).parents("tr").find(".vipGoodsPrices").text(),this);
                  });
              }
          }else{
              showNoDeleteConfirmAlert('请输入0~1之间的数字');
          }
      }

		function checkLelvel(obj,name){
			var count =0;
			var clength = $(obj).parents(".tab-pane").find($("input[name='"+name+"']")).length;
			$(obj).parents(".tab-pane").find($("input[name='"+name+"']")).each(function(){
				if($(this).prop("checked")){
					count++;
				}
				if(!$(this).prop("checked")){
					$(obj).parents(".tab-pane").find($("input[name='onCheck']")).prop("checked",false);
				}
			});
				if(count == clength){
					$(obj).parents(".tab-pane").find($("input[name='onCheck']")).prop("checked",true);
				}
		}



        function initTgVipDiscount(){
            var goodsInfoPreferPrice=$("input[name='TGgoodsInfoPreferPrice']").val();
            var goodsInfoVipPrice=$("input[name='TGgoodsInfoVipPrice']").val();
            if(goodsInfoVipPrice!=""){

                var goodsInfoVipTgPrice = $("#goodsInfoVipTgPrice").val();
                //$("#grouponVipDiscount").val();
                if(goodsInfoVipTgPrice==""){
                    $("#grouponVipDiscount").val("");
                }else{
                    if(Number(goodsInfoVipTgPrice)>Number(goodsInfoVipPrice)){
                        goodsInfoVipTgPrice = goodsInfoPreferPrice;
                    }
                    $("#goodsInfoVipTgPrice").val((goodsInfoVipTgPrice*1).toFixed(2));
                    $("#grouponVipDiscount").val((goodsInfoVipTgPrice/goodsInfoVipPrice).toFixed(2));
                }
            }else{
                $("#grouponVipDiscount").val("");
            }
        }

        function initTgDiscount(){

            var goodsInfoPreferPrice=$("input[name='TGgoodsInfoPreferPrice']").val();
            var goodsInfoVipPrice=$("input[name='TGgoodsInfoVipPrice']").val();
            if(goodsInfoVipPrice!=""){

                var goodsInfoPreferTgPrice = $("#goodsInfoPreferTgPrice").val();
                //$("#grouponVipDiscount").val();
                if(goodsInfoPreferTgPrice==""){
                    $("#grouponDiscount").val("");
                }else{
                    if(Number(goodsInfoPreferTgPrice)>Number(goodsInfoPreferPrice)){
                        goodsInfoPreferTgPrice = goodsInfoPreferPrice;
                    }
                    $("#goodsInfoPreferTgPrice").val((goodsInfoPreferTgPrice*1).toFixed(2));
                    $("#grouponDiscount").val((goodsInfoPreferTgPrice/goodsInfoPreferPrice).toFixed(2));
                }
            }else{
                $("#grouponDiscount").val("");
            }
        }

        function initTgprice(){
            var goodsInfoPreferPrice=$("input[name='TGgoodsInfoPreferPrice']").val();
            var goodsInfoVipPrice=$("input[name='TGgoodsInfoVipPrice']").val();
            if(goodsInfoPreferPrice!=""){
                var zk = $("#grouponDiscount").val();
                if(zk==""){
                    $("#goodsInfoPreferTgPrice").val("");
                }else{
                    if(zk>1){
                        zk=1;
                    }
                    var zknew = (zk*1).toFixed(2);
                    $("#grouponDiscount").val(zknew);
                    $("#goodsInfoPreferTgPrice").val((zknew*goodsInfoPreferPrice).toFixed(2));
                }
            }else{
                $("#goodsInfoPreferTgPrice").val("");
            }
            if(goodsInfoVipPrice!=""){

                var yhzk = $("#grouponVipDiscount").val();
                if(yhzk==""){
                    $("#goodsInfoVipTgPrice").val("");
                }else{
                    if(yhzk>1){
                        yhzk=1;
                    }
                    var yhzknew = (yhzk*1).toFixed(2)
                    $("#grouponVipDiscount").val(yhzknew);
                    $("#goodsInfoVipTgPrice").val((yhzknew*goodsInfoVipPrice).toFixed(2));
                }
            }else{
                $("#goodsInfoVipTgPrice").val("");
            }
        }

        function round2(number,fractionDigits){
            with(Math){
                return round(number*pow(10,fractionDigits))/pow(10,fractionDigits);
            }
        }

        function initQgprice() {
            var goodsInfoPreferPrice = $("input[name='QGgoodsInfoPreferPrice']").val();
            var goodsInfoVipPrice = $("input[name='QGgoodsInfoVipPrice']").val();
            if (goodsInfoPreferPrice != "") {
                var zk = $("#rushDiscount").val();
                if (zk == "") {
                    $("#rushPrice").val("");
                } else {
                    if (zk > 1) {
                        zk = 1;
                    }
                    //var zknew = (zk * 1).toFixed(2);
                    var zknew = round2(zk,2);
                    $("#rushDiscount").val(zknew);
                    //$("#rushPrice").val((zknew * goodsInfoPreferPrice).toFixed(2));
                    $("#rushPrice").val(round2((zknew * goodsInfoPreferPrice),2));
                }
            } else {
                $("#rushPrice").val("");
            }
            if (goodsInfoVipPrice != "") {

                var yhzk = $("#rushVipDiscount").val();
                if (yhzk == "") {
                    $("#rushVipPrice").val("");
                } else {
                    if (yhzk > 1) {
                        yhzk = 1;
                    }
                    //var yhzknew = (yhzk * 1).toFixed(2) ;
                    var yhzknew = round2(yhzk , 2);
                    $("#rushVipDiscount").val(yhzknew);
                   // $("#rushVipPrice").val((yhzknew * goodsInfoVipPrice).toFixed(2));
                    $("#rushVipPrice").val(round2((yhzknew * goodsInfoVipPrice),2));
                }
            } else {
                $("#rushVipPrice").val("");
            }
        }


        function initQgVipDiscount() {
            var goodsInfoPreferPrice = $("input[name='QGgoodsInfoPreferPrice']").val();
            var goodsInfoVipPrice = $("input[name='QGgoodsInfoVipPrice']").val();
            if (goodsInfoVipPrice != "") {

                var goodsInfoVipTgPrice = $("#rushVipPrice").val();
                //$("#grouponVipDiscount").val();
                if (goodsInfoVipTgPrice == "") {
                    $("#rushVipDiscount").val("");
                } else {
                    if (Number(goodsInfoVipTgPrice) > Number(goodsInfoVipPrice)) {
                        goodsInfoVipTgPrice = goodsInfoPreferPrice;
                    }
                    /*$("#rushVipPrice").val((goodsInfoVipTgPrice * 1).toFixed(2));
                    $("#rushVipDiscount").val((goodsInfoVipTgPrice / goodsInfoVipPrice).toFixed(2));*/
                    $("#rushVipPrice").val(round2(goodsInfoVipTgPrice , 2));
                    $("#rushVipDiscount").val(round2((goodsInfoVipTgPrice / goodsInfoVipPrice),2));
                }
            } else {
                $("#rushVipDiscount").val("");
            }
        }

        function initQgDiscount() {

            var goodsInfoPreferPrice = $("input[name='QGgoodsInfoPreferPrice']").val();
            var goodsInfoVipPrice = $("input[name='QGgoodsInfoVipPrice']").val();
            if (goodsInfoVipPrice != "") {

                var goodsInfoPreferTgPrice = $("#rushPrice").val();
                //$("#grouponVipDiscount").val();
                if (goodsInfoPreferTgPrice == "") {
                    $("#rushDiscount").val("");
                } else {
                    if (Number(goodsInfoPreferTgPrice) > Number(goodsInfoPreferPrice)) {
                        goodsInfoPreferTgPrice = goodsInfoPreferPrice;
                    }
                    /*$("#rushPrice").val((goodsInfoPreferTgPrice * 1).toFixed(2));
                    $("#rushDiscount").val((goodsInfoPreferTgPrice / goodsInfoPreferPrice).toFixed(2));*/
                    $("#rushPrice").val(round2(goodsInfoPreferTgPrice , 2));
                    $("#rushDiscount").val(round2((goodsInfoPreferTgPrice / goodsInfoPreferPrice),2));
                }
            } else {
                $("#rushDiscount").val("");
            }
        }


    </script>
  </body>
</html>
