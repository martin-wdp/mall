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

            <h2 class="main_title">添加${pageNameChild}&nbsp;<small><a href="javascript:void(0)"  onclick="$('#mjp').modal('show')" style="diaplay:block; float:right; padding-right: 12px">查看帮助<i class="icon iconfont">&#xe611;</i></a></small></h2>

            <div class="common_info common_tabs mt20">
            <ul class="nav nav-tabs" role="tablist">
              <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">单品促销</a></li>
              <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">类目促销</a></li>
              <li role="presentation"><a href="#tab3" aria-controls="tab2" role="tab" data-toggle="tab">品牌促销</a></li>
            </ul>
            <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="tab1">
              <div class="common_form common_form_lg p20">
              <!-- 单品促销选择 货品 -->
                <form class="form-horizontal" action="newdoaddmarketing.htm?CSRFToken=${token}"  id="addFormOne" method="post">
                  <input type="hidden" id="isAlls" value="0" name="isAll" >
                  <input type="hidden" name="status" class="f_status" value="0">
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>促销名称：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                      <input type="text" class="form-control required" name="marketingName">
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
                   	 
	                    <c:forEach items="${codexList }" var="codex">
		                     <c:if test="${codex.codexType=='13' || codex.codexType=='14'}">
		                     <label class="radio-inline"> <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType=='13'}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
	                    	  <c:if test="${codex.codexType=='13'}">
	                    	  		<input type="hidden" name="codexType" value="${codex.codexType }" />
                      				<input type="hidden" name="codexId" value="${codex.codexId }" />
	                    	  </c:if>  
	                    	 </c:if>
	                    </c:forEach>
                    </div>
                  </div>
                 
                  
                  <c:forEach items="${codexList }" var="codex">
                  
                  <c:if test="${codex.codexType=='13' }">
	                   <div class="form-group codexDiv codexDiv${codex.codexType}">
		                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
		                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
		                    <div class="col-sm-1"></div>
		                    <div class="col-sm-2"> 
		                        <input type="text" name="packagesNo"  class="form-control required digits">
		                    </div>
		                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
		                     <div class="col-sm-1"><label class="radio-inline">打</label></div>
		                      <div class="col-sm-1"></div>
		                     <div class="col-sm-2">
		                        <input type="text" name="packagebuyDiscount" id="packagebuyDiscount" class="form-control required zeroOne">
		                    </div>
		                     <div class="col-sm-1"><label class="radio-inline">折</label></div>
	                  </div>
	                   <div class="form-group addChoose addChoose${codex.codexType}" >
	                         <div class="col-sm-4"></div>
		                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
		                </div>
                  </c:if>
                
                    <c:if test="${codex.codexType=='14' }">
	                   <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
		                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
		                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
		                    <div class="col-sm-1"></div>
		                    <div class="col-sm-2"> 
		                        <input type="text" name="countNo" class="form-control">
		                    </div>
		                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
		                     <div class="col-sm-1"><label class="radio-inline">共</label></div>
		                      <div class="col-sm-1"></div>
		                     <div class="col-sm-2">
		                        <input type="text" name="countMoney" class="form-control">
		                    </div>
		                     <div class="col-sm-1"><label class="radio-inline">元</label></div>
	                  </div>
	                   <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
	                         <div class="col-sm-4"></div>
		                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
		                </div>
                  </c:if>
                  
               </c:forEach>
               
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>选择货品：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-4">
                      <button type="button" class="btn btn-info" onclick="chooseProduct();">选择参加促销的货品</button>
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
						                  <th width="100">操作</th>
						              </tr>
					            </table>
		                   <div style="max-height:300px;overflow-y:auto;position:relative;">
		                  
		                     <table class="table table-striped table-hover table-bordered" id="readproduct">
						              <tbody style="">
						             
						              </tbody>
					            </table>
					            
					        </div> 
                    </div>
                  </div>
		                   
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>开始时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="startpicker">
                            <input class="form-control required" type="text" id="startTime" value="" readonly
                                   name="sTime">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>结束时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="endpicker">
                            <input class="form-control required" type="text" id="endTime" value="" readonly
                                   name="eTime">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>
              


                  <div class="form-group">
                       <label class="control-label col-sm-4">活动站点：</label>
                       <div class="col-sm-1"></div>
                      <input type="hidden" name="activeSiteType" value="2"/>
                      <div class="col-sm-4">
                          <label class="radio-inline">全部</label>
                      </div>
                  </div>
                  <div class="form-group">
	                   <label class="control-label col-sm-4"><span class="text-danger"></span>促销LOGO：</label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-19">
	                        <c:forEach items="${logolist}" var="logo">
		                        <label class="checkbox-inline"> <input type="checkbox"  name="promotionLogoId" value="${logo.promotionLogoId }"/>
		                        <img  alt="${logo.promotionLogoName}" title="${logo.promotionLogoName}" src="${logo.promotionLogoUrl }" height="20" />
		                        </label>
	                        </c:forEach>
	                   </div>
                  </div>
               
                  <div class="form-group">
                    <label class="control-label col-sm-4">促销描述：<input type="hidden" name="marketingDes" id="marketingDes"/></label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-19">
                      <div class="summernote"></div>
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
            <div role="tabpanel" class="tab-pane" id="tab2">
              <div class="common_form common_form_lg p20">
               
                 <!-- 单品促销选择 类目 -->
             <form class="form-horizontal" action="newdoaddmarketing.htm?CSRFToken=${token}"  id="addFormTwo" method="post">
                   <input type="hidden" name="status" class="f_status" value="1"><!-- 分类促销 -->
                  <div class="form-group">
                     <label class="control-label col-sm-4"><span class="text-danger">*</span>促销名称：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                      <input type="text" class="form-control required" name="marketingName">
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
                   	 
	                    <c:forEach items="${codexList }" var="codex">
		                     <c:if test="${codex.codexType=='13' || codex.codexType=='14'}">
		                     <label class="radio-inline"> <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType=='13'}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
	                    	  <c:if test="${codex.codexType=='13'}">
	                    	  		<input type="hidden" name="codexType" value="${codex.codexType }" />
                      				<input type="hidden" name="codexId" value="${codex.codexId }" />
	                    	  </c:if>  
	                    	 </c:if>
	                    </c:forEach>
                    </div>
                  </div>
                 
                  
                  <c:forEach items="${codexList }" var="codex">
                  
                  <c:if test="${codex.codexType=='13' }">
	                   <div class="form-group codexDiv codexDiv${codex.codexType}">
		                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
		                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
		                    <div class="col-sm-1"></div>
		                    <div class="col-sm-2"> 
		                        <input type="text" name="packagesNo" class="form-control required digits">
		                    </div>
		                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
		                     <div class="col-sm-1"><label class="radio-inline">打</label></div>
		                      <div class="col-sm-1"></div>
		                     <div class="col-sm-2">
		                        <input type="text" name="packagebuyDiscount" id="packagebuyDiscount" class="form-control required zeroOne">
		                    </div>
		                     <div class="col-sm-1"><label class="radio-inline">折</label></div>
	                  </div>
	                   <div class="form-group addChoose addChoose${codex.codexType}" >
	                         <div class="col-sm-4"></div>
		                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
		                </div>
                  </c:if>
                
                    <c:if test="${codex.codexType=='14' }">
	                   <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
		                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
		                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
		                    <div class="col-sm-1"></div>
		                    <div class="col-sm-2"> 
		                        <input type="text" name="countNo" class="form-control">
		                    </div>
		                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
		                     <div class="col-sm-1"><label class="radio-inline">共</label></div>
		                      <div class="col-sm-1"></div>
		                     <div class="col-sm-2">
		                        <input type="text" name="countMoney" class="form-control">
		                    </div>
		                     <div class="col-sm-1"><label class="radio-inline">元</label></div>
	                  </div>
	                   <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
	                         <div class="col-sm-4"></div>
		                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
		                </div>
                  </c:if>
                  
               </c:forEach>
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>选择类目：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-4">
                      <button type="button" class="btn btn-info" onclick="chooseCate();">选择参加促销的类目</button>
                    </div>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-12">
                    	 <label class="radio-inline">
                    	 	<input type="checkbox" id="isallCate" value="0" name="isallCate" onchange="checkIsAll(this);">全场促销（全场促销可不指定类目）
                    	 	<input type="hidden" id="isAlls" value="0" name="isAll" >
                    	 	<span id="pc" class="spanweight"></span>
                    	 </label>
                    </div> 
                  </div>
                  
                  <div class="form-group" id="towCate">
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
                            <input class="form-control required" type="text" id="startTime2" value="" readonly
                                   name="sTime">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>结束时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="endpicker2">
                            <input class="form-control required" type="text" id="endTime2" value="" readonly
                                   name="eTime">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>


                  <div class="form-group">
                       <label class="control-label col-sm-4">活动站点：</label>
                       <div class="col-sm-1"></div>
                      <input type="hidden" name="activeSiteType" value="2"/>
                      <div class="col-sm-4">
                          <label class="radio-inline">全部</label>
                      </div>
                  </div>
                  <div class="form-group">
	                   <label class="control-label col-sm-4"><span class="text-danger"></span>促销LOGO：</label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-19">
	                        <c:forEach items="${logolist}" var="logo">
		                        <label class="checkbox-inline"> <input type="checkbox"  name="promotionLogoId" value="${logo.promotionLogoId }"/>
		                        <img  alt="${logo.promotionLogoName}" title="${logo.promotionLogoName}" src="${logo.promotionLogoUrl }" height="20" />
		                        </label>
	                        </c:forEach>
	                   </div>
                  </div>
               
                 
                  <div class="form-group">
                    <label class="control-label col-sm-4">促销描述：<input type="hidden" name="marketingDes" id="marketingDesTwo"/></label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-19">
                      <div class="summernoteTwo"></div>
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
            <div role="tabpanel" class="tab-pane" id="tab3">
              <div class="common_form common_form_lg p20">
                <!-- 单品促销选择 品牌-->
               <form class="form-horizontal" action="newdoaddmarketing.htm?CSRFToken=${token}"  id="addFormThree" method="post">
                   <input type="hidden" name="status" class="f_status" value="2"><!-- 品牌促销 -->
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>促销名称：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                       <input type="text" class="form-control required" name="marketingName">
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
                   	 
	                    <c:forEach items="${codexList }" var="codex">
		                     <c:if test="${codex.codexType=='13' || codex.codexType=='14'}">
		                     <label class="radio-inline"> <input type="radio" class="codexchoose" name="codexchoose"  <c:if test="${codex.codexType=='13'}">checked="checked"</c:if> value="${codex.codexId }-${codex.codexType }" />${codex.codexName }</label>
	                    	  <c:if test="${codex.codexType=='13'}">
	                    	  		<input type="hidden" name="codexType" value="${codex.codexType }" />
                      				<input type="hidden" name="codexId" value="${codex.codexId }" />
	                    	  </c:if>  
	                    	 </c:if>
	                    </c:forEach>
                    </div>
                  </div>
                 
                  
                  <c:forEach items="${codexList }" var="codex">
                  
                  <c:if test="${codex.codexType=='13' }">
	                   <div class="form-group codexDiv codexDiv${codex.codexType}">
		                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
		                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
		                    <div class="col-sm-1"></div>
		                    <div class="col-sm-2"> 
		                        <input type="text" name="packagesNo"  class="form-control required digits">
		                    </div>
		                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
		                     <div class="col-sm-1"><label class="radio-inline">打</label></div>
		                      <div class="col-sm-1"></div>
		                     <div class="col-sm-2">
		                        <input type="text" name="packagebuyDiscount"  class="form-control required zeroOne">
		                    </div>
		                     <div class="col-sm-1"><label class="radio-inline">折</label></div>
	                  </div>
	                   <div class="form-group addChoose addChoose${codex.codexType}" >
	                         <div class="col-sm-4"></div>
		                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
		                </div>
                  </c:if>
                
                    <c:if test="${codex.codexType=='14' }">
	                   <div class="form-group codexDiv codexDiv${codex.codexType}" style="display:none;">
		                    <label class="control-label col-sm-4"><span class="text-danger">*</span></label>
		                    <div class="col-sm-1"><label class="radio-inline">满</label></div>
		                    <div class="col-sm-1"></div>
		                    <div class="col-sm-2"> 
		                        <input type="text" name="countNo"  class="form-control">
		                    </div>
		                    <div class="col-sm-1"><label class="radio-inline">件</label></div>
		                     <div class="col-sm-1"><label class="radio-inline">共</label></div>
		                      <div class="col-sm-1"></div>
		                     <div class="col-sm-2">
		                        <input type="text" name="countMoney" class="form-control">
		                    </div>
		                     <div class="col-sm-1"><label class="radio-inline">元</label></div>
	                  </div>
	                   <div class="form-group addChoose addChoose${codex.codexType}" style="display:none;">
	                         <div class="col-sm-4"></div>
		                     <div class="col-sm-6"><label class="radio-inline" style="color:blue" onclick="addmore(this,${codex.codexType})">+添加多级促销</label></div>
		                </div>
                  </c:if>
                  
               </c:forEach>
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>选择品牌：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-4">
                      <button type="button" class="btn btn-info" onclick="chooseBrandsView();">选择参加促销的品牌</button>
                    </div>
                     <div class="col-sm-1"></div>
                     <div class="col-sm-12">
                    	 <label class="radio-inline">
                    	 	<input type="checkbox" value="0" name="isAllsThreeChecked" id="isAllsThreeChecked" onchange="isAllThree(this);">全场促销（全场促销可不指定品牌）
                    	 	<input type="hidden" id="isAllsThree" value="0" name="isAll" >
                    	 	<span id="pb" class="spanweight"></span>
                    	 </label>
                    </div>
                  </div>
                    <div class="form-group" id="threeBrand">
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
				             
				              </tbody>
				            </table>
				        </div>
                    </div>
                    <c:forEach items="${brandlist}" var="brand" varStatus="index">
						<input name="brand_allids"  value="${brand.brandId}"  type="hidden"/>
					</c:forEach>
                  </div>
                  
                  <div class="form-group">
                      <label class="control-label col-sm-4"><span class="text-danger">*</span>开始时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="startpicker3">
                            <input class="form-control required" type="text" id="startTime3" value="" readonly
                                   name="sTime">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-4"><span class="text-danger">*</span>结束时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="endpicker3">
                            <input class="form-control required" id="endtime3" type="text" value="" readonly
                                   name="eTime">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>


                  <div class="form-group">
                       <label class="control-label col-sm-4">活动站点：</label>
                       <div class="col-sm-1"></div>
                      <input type="hidden" name="activeSiteType" value="2"/>
                      <div class="col-sm-4">
                          <label class="radio-inline">全部</label>
                      </div>
                  </div>
                  <div class="form-group">
	                   <label class="control-label col-sm-4"><span class="text-danger"></span>促销LOGO：</label>
	                   <div class="col-sm-1"></div>
	                   <div class="col-sm-19">
	                        <c:forEach items="${logolist}" var="logo">
		                        <label class="checkbox-inline"> <input type="checkbox"  name="promotionLogoId" value="${logo.promotionLogoId }"/>
		                        <img  alt="${logo.promotionLogoName}" title="${logo.promotionLogoName}" src="${logo.promotionLogoUrl }" height="20" />
		                        </label>
	                        </c:forEach>
	                   </div>
                  </div>
               
                
                  <div class="form-group">
                    <label class="control-label col-sm-4">促销描述：<input type="hidden" name="marketingDes" id="marketingDesThree"/></label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-19">
                      <div class="summernoteThree"></div>
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
                <th width="50"><input type="checkbox" onchange="chooseAllPro(this);" id="chooseAllPro"></th>
                <th width="100">货品图片</th>  
                <th width="100">货品规格</th>
                <th width="150">货品编号</th>
                <th>货品名称</th>
                <th>价格</th>
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
    
     <div class="modal fade" id="mjp" tabindex="-1" role="dialog">
       <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加满件促销</h4>
          </div>
          <div class="modal-body">
            <div class="modal-article">
              <p><em>1.</em>添加满件促销，满件促销包含买够件数打折和买够件数多少钱，添加信息如下图</p>
              <img src="<%=basePath %>/images/syshelp/img_c04.png" alt="">
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
    <script>
      $(function(){
          $("#addFormOne").validate();
          $("#addFormTwo").validate();
          $("#addFormThree").validate();
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
        			$(this).removeClass('digits');
        			$(this).removeClass('zeroOne');
        			$(this).removeClass('isNumber');
        		});
        		
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
      
      
      function chooseProduct(){
          doAjax(1,8);
          $('#chooseGoods').modal('show');
      }
      
  	/*点击添加货品的时候*/ 
  	
		function doAjax(pageNo, pageSize)
		{
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
	  			            productListHtml = productListHtml + "</td>" + "<td class='goodsNo'>" + list[i].goodsInfoItemNo+ "</td>" + "<td  class='goodsName' title='"+list[i].goodsInfoName+"' >" + list[i].goodsInfoName + "</td>"+"<td class='goodsInfoPreferPrice'>"+list[i].goodsInfoPreferPrice  + "</tr>";
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
		    var productId=$(obj).val();
		    var goodsImg=$(obj).parents("tr").find(".goodsImg").attr("src");
		    var goodsNo=$(obj).parents("tr").find(".goodsNo").text();
		    var goodsName=$(obj).parents("tr").find(".goodsName").text();
		    var goodsTag=$(obj).parents("tr").find(".goodsTag").html();
		    if(obj.checked==true){
		        var htm = "<tr id='goods_tr_"+productId+"'>";
		    	htm+='<td width="92"><img src="'+goodsImg+'" width="50" height="50"/><input type="hidden" name="goodsIdP" id="goods_Id_'+productId+'" value="'+productId+'"/></td>';
		    	htm+='<td width="98">'+goodsTag+'</td>';
		    	htm+='<td width="120">'+goodsNo+'</td>';
		    	htm+='<td  width="300">'+goodsName+'</td>';
		    	htm+='<td width="70"><button onclick="removeTr(this);">移除</button></td>';
		  	  	htm+="</tr>";
		    $("#readproduct tbody").append(htm);
		    }else{
		      $("#goods_tr_"+productId).remove();
		        
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
		var num=0;
			//保存货品促销 
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


		          
		          if($("#addFormOne").valid()&&f&&num==0){
                      num+=1;
		              $("#marketingDes").val($(".summernote").code());
		              $("#addFormOne").submit();
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
	    	            htm+='<td width="110">'+nodes[i].id+'<input type="hidden" name="cateIdp" id="cate_Id_p'+nodes[i].id+'" value="'+nodes[i].id+'"></td>'; 
	    	            htm+='<td width="285">'+nodes[i].name+'</td>';
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
				var cus =  $(obj).parents(".tab-pane").find("input[name='lelvelId']:checked");
			    var f = true;
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


		          if($("#addFormTwo").valid()&&f){
		              $("#marketingDesTwo").val($(".summernoteTwo").code());
		              $("#addFormTwo").submit();
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





			    
		          if($("#addFormThree").valid()&&f){
		              $("#marketingDesThree").val($(".summernoteThree").code());
		              $("#addFormThree").submit();
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
	    	            htm+='<td width="110">'+$arr[i]+'<input type="hidden" name="brandIdP" id="brandIdP'+$arr[i]+'" value="'+$arr[i]+'"></td>'; 
	    	            htm+='<td width="285">'+$("#brand option[value='"+$arr[i]+"']").text()+'</td>';
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
	
	function addmore(obj,codetype){
		var length =$(obj).parents(".tab-pane").find(".codexDiv"+codetype).size();
		if(codetype==13 && length <3){
			var html='<div class="form-group codexDiv codexDiv'+codetype+'">';
			html+='<label class="control-label col-sm-4"><span class="text-danger">*</span></label>';
			html+='<div class="col-sm-1"><label class="radio-inline">满</label></div>';
			html+='<div class="col-sm-1"></div>';
			html+='<div class="col-sm-2">'; 
			html+= '     <input type="text" name="packagesNo" class="form-control required digits">';
			html+= '</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">件</label></div>';
			html+= '<div class="col-sm-1"><label class="radio-inline">打</label></div>';
			html+=  '   <div class="col-sm-1"></div>';
			html+= '<div class="col-sm-2">';
			html+= '    <input type="text" name="packagebuyDiscount"  class="form-control required zeroOne">';
			html+='</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">折</label></div>';
			html+='<div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></div>';
			$(obj).parents(".tab-pane").find(".codexDiv"+codetype).last().after(html);
		}
		
		if(codetype==14 && length <3){
			var html='<div class="form-group codexDiv codexDiv'+codetype+'">';
			html+='<label class="control-label col-sm-4"><span class="text-danger">*</span></label>';
			html+='<div class="col-sm-1"><label class="radio-inline">满</label></div>';
			html+='<div class="col-sm-1"></div>';
			html+='<div class="col-sm-2">'; 
			html+= '     <input type="text" name="countNo"  class="form-control required digits">';
			html+= '</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">件</label></div>';
			html+= '<div class="col-sm-1"><label class="radio-inline">共</label></div>';
			html+=  '   <div class="col-sm-1"></div>';
			html+= '<div class="col-sm-2">';
			html+= '    <input type="text" name="countMoney" class="form-control required isNumber">';
			html+='</div>';
			html+='<div class="col-sm-1"><label class="radio-inline">元</label></div>';
			html+='<div class="col-sm-4" onclick="dellevel(this)"><label class="radio-inline" style="color:blue">删除本级促销</label></div></div>';
			$(obj).parents(".tab-pane").find(".codexDiv"+codetype).last().after(html);
		}
	}
	
	function dellevel(obj){
		$(obj).parent().remove();
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
    </script>
  </body>
</html>
