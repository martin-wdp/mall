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
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
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
    .popover-content{
    	color:#000000;
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

            <h2 class="main_title">添加${pageNameChild}&nbsp;<small><a href="javascript:void(0)"  onclick="$('#zk').modal('show')" style="diaplay:block; float:right; padding-right: 12px">查看帮助<i class="icon iconfont">&#xe611;</i></a></small></h2>

            <div class="common_info common_tabs mt20">
            <ul class="nav nav-tabs" role="tablist">
              <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">单品促销</a></li>
            </ul>
            <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="tab1">
              <div class="common_form common_form_max p20">
              <!-- 单品促销选择 货品 -->
                <form class="form-horizontal" action="newdoaddmarketing.htm?CSRFToken=${token}"  id="addFormOne" method="post">
                  <input type="hidden" name="status" class="f_status" value="0">
                  <input type="hidden" id="isAlls" value="0" name="isAll" >
                  <div class="form-group">
                    <label class="control-label col-sm-3"><span class="text-danger">*</span>促销名称：</label>
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
                    <label class="control-label col-sm-3">促销类型：<input type="hidden" name="marketingType" value="0"/></label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-8">
                   	 
	                    <c:forEach items="${codexList }" var="codex">
		                     <c:if test="${codex.codexType=='15'}">
		                     		<label class="control-label">${codex.codexName }</label>
	                    	  		<input type="hidden" name="codexType" value="${codex.codexType }" />
                      				<input type="hidden" name="codexId" value="${codex.codexId }" />
	                    	 </c:if>
	                    </c:forEach>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-3"><span class="text-danger">*</span>选择货品：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-4">
                      <button type="button" class="btn btn-info" onclick="chooseProduct();">选择参加促销的货品</button>
                      <span id="ps" class="spanweight"></span>
                    </div>
                  </div>
				<div class="form-group">
                    <label class="control-label col-sm-3">已选择货品：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-19">
                    	<table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;">
						              <thead style="top:0;"> 
						              <tr>
						               <th width="760"></th>
						                 <th width="120">批量折扣</th>
						                <th width="80"><input type="text" class="form-control" style="width:60px;" onblur="plzhekou(this);"/>
                                          <th width="120">会员批量折扣</th>
                                          <th width="80"><input type="text" class="form-control" style="width:60px;" onblur="hyplzhekou(this);"/>
                                          </th>
						                <th> <a href="javascript:;" class="plzk help_tips">
					                        <i class="icon iconfont">&#xe611;</i>
					                      </a></th>
						                <th width="110"><a class="btn btn-info" href="javascript:void(0);" onclick="yichus(1);">批量抹分</a></th>
						                <th width="110"><a class="btn btn-info" href="javascript:void(0);" onclick="yichus(0);">抹分角</a></th>
						                <th width="80"></th> 
						              </tr>   
					            </table>
                     		<table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;"> 
						              <thead style="top:0;"> 
						              <tr>
						                <th width="8%">货品图片</th>
						                <th width="12%">货品规格</th>
						                <th width="17%">货品编号</th>
						                <th width="7%">货品名称</th>
						                <th width="6%">价格</th>
						                <th width="10%">折扣</th>
						                <th width="6%">折后价</th>
                                          <th width="6%">会员价格</th>
                                          <th width="10%">会员折扣</th>
                                          <th width="6%">会员折后价</th>
						                <th width="12%">操作</th>
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
                    <label class="control-label col-sm-3"><span class="text-danger">*</span>开始时间：</label>
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
                    <label class="control-label col-sm-3"><span class="text-danger">*</span>结束时间：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-9">
                        <div class="input-group date form_datetime w200" id="endpicker">
                            <input class="form-control required" id="endTime" type="text" value="" readonly
                                   name="eTime">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                  </div>
              	 <%--<div class="form-group">--%>
	                   <%--<label class="control-label col-sm-3">附加赠送：</label>--%>
	                   <%--<div class="col-sm-1"></div>--%>
	                   <%--<div class="col-sm-3">--%>
	                        <%--<span class="radio-inline"> <input type="radio" class="addGiveType" name="addGiveType"  value="0"/>赠送积分</span>--%>
	                   <%--</div>--%>
	                    <%--<div class="col-sm-2">--%>
	                     <%--<input type="text" name="giveIntegral"  class="form-control required digits giveIntegral" disabled="disabled" />--%>
	                    <%--</div>--%>
	                    <%--<div class="col-sm-1">--%>
	                      <%--<label class="radio-inline">个</label>--%>
	                    <%--</div>--%>
                  <%--</div>--%>
                   <%--<div class="form-group">--%>
	                   <%--<label class="control-label col-sm-3"><span class="text-danger"></span></label>--%>
	                   <%--<div class="col-sm-1"></div>--%>
	                   <%--<div class="col-sm-3">--%>
	                      <%--<label class="radio-inline"> <input type="radio" class="addGiveType" name="addGiveType" value="1"/>赠送优惠券</label>--%>
	                   <%--</div>--%>
	                    <%--<div class="col-sm-4">--%>
                           <%--<select class="form-control valid couponId" name="couponId"  disabled="disabled">--%>
	                           <%--<c:forEach items="${couponlist}" var="coupon">--%>
	                             <%--<option value="${coupon.couponId }">${coupon.couponName }</option>--%>
	                           <%--</c:forEach>--%>
						   <%--</select>--%>
	                   <%--</div>--%>
                  <%--</div>--%>

                  <div class="form-group">
                       <label class="control-label col-sm-3">活动站点：</label>
                       <div class="col-sm-1"></div>
                      <input type="hidden" name="activeSiteType" value="2"/>
                      <div class="col-sm-4">
                          <label class="radio-inline">全部</label>
                      </div>
                  </div>
                  <div class="form-group">
	                   <label class="control-label col-sm-3"><span class="text-danger"></span>促销LOGO：</label>
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
                    <label class="control-label col-sm-3">促销描述：<input type="hidden" name="marketingDes" id="marketingDes"/></label>
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

   <div class="modal fade" id="zk" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加折扣促销</h4>
          </div>
          <div class="modal-body">
            <div class="modal-article">
              <p><em>1.</em>添加折扣促销，折扣促销只包含货品折扣促销，添加信息如下图</p>
              <img src="<%=basePath %>/images/syshelp/img_c03.png" alt="">
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
    <script src="<%=basePath %>/js/select2.min.js"></script>
      <script src="<%=basePath %>js/common/common_alert.js"></script>
      <script src="<%=basePath %>js/common/common_checked.js"></script>
    <script>
      $(function(){
          $("#addFormOne").validate();
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
        $('select[data-live-search="true"]').select2();
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

      	  $('.plzk').popover({
      	        content : '请输入0~1之间的小数',
      	        trigger : 'hover'
      	    });
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
	  			            productListHtml = productListHtml + "</td>" + "<td class='goodsNo'>" + list[i].goodsInfoItemNo+ "</td>" + "<td  class='goodsName' title='"+list[i].goodsInfoName+"' >" + list[i].goodsInfoName + "</td>"+"<td class='goodsInfoPreferPrice'>"+list[i].goodsInfoPreferPrice  + "</td>"+"<td class='goodsInfoVipPrice'>"+list[i].goodsInfoVipPrice  + "</td>"+"</tr>";
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
		    var goodsPrice=$(obj).parents("tr").find(".goodsInfoPreferPrice").text();
            var vipDiscountPrice=$(obj).parents("tr").find(".goodsInfoVipPrice").text();

		    if(obj.checked==true){
		        var htm = "<tr id='goods_tr_"+productId+"'>";
		    	htm+='<td width="8%"><img src="'+goodsImg+'" width="50" height="50"/><input type="hidden" name="goodsIdP" id="goods_Id_'+productId+'" value="'+productId+'"/></td>';
		    	htm+='<td width="12%">'+goodsTag+'</td>';
		    	htm+='<td width="17%">'+goodsNo+'</td>';
		    	htm+='<td  width="7%">'+goodsName+'</td>';
		    	htm+='<td  width="6%" class="goodsPrices">'+goodsPrice+'</td>';
		    	htm+='<td  width="10%"><input type="text" class="form-control required zeroOne discountInfos" name="discountInfo" id="discount_info_'+productId+'" style="width:55px;" onblur="zkchange('+goodsPrice+',this);"/></td>';
		    	htm+='<td  width="6%"><span class="zhj">'+goodsPrice+'</span><input type="hidden" class="form-control" name="discountPrice" /><input type="hidden" class="form-control" name="discountFlag" id="discount_prices_'+productId+'" value="0" readonly  ></td>';

                htm+='<td  width="6%" class="vipGoodsPrices">'+vipDiscountPrice+'</td>';
		    	htm+='<td  width="10%"><input type="text" class="form-control required zeroOne vipDiscountInfos" name="vipDiscountInfo" id="vip_discount_info_'+productId+'" style="width:55px;" onblur="hyzkchange('+vipDiscountPrice+',this);"/></td>';
		    	htm+='<td  width="6%"><span class="hyzhj">'+vipDiscountPrice+'</span><input type="hidden" class="form-control" name="vipDiscountPrice" /><input type="hidden" class="form-control" name="vipDiscountFlag" id="vipdiscount_prices_'+productId+'" value="0" readonly  ></td>';

                htm+='<td width="12%"><input type="button" onclick="mofensingle('+goodsPrice+','+vipDiscountPrice+',this,1);" value="移除分" class="btn btn-sm btn-default"/>';
		    	htm+='<input type="button" onclick="mofensingle('+goodsPrice+','+vipDiscountPrice+',this,0);" value="移除分角" class="btn btn-sm btn-default"/>';
		    	htm+='<button onclick="removeTr(this);" class="btn btn-sm btn-default">移除</button></td>';
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


		          var info =  document.getElementsByName("discountInfo");
		          var f = true;
		          if(info.length==0){
		              $("#ps").html('请选择货品');
		              $("#ps").addClass("error");
		              f=false&&f;
		          }else{
		             for(var j=0;j<info.length;j++){
		                 if(info[j].value==''){
		                     f=false&&f;
		                     $("#addFormOne").valid();
		                     return ;
		                 }
		             }
		          }
		        
		          
		          
		          if($("#addFormOne").valid()&&f&&num==0){
                      num+=1;
		              $("#marketingDes").val($(".summernote").code());
		              $("#addFormOne").submit();
		          }   
		        
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

      function hyzkchange(price,obj){
          var b = (price*$(obj).val()).toFixed(2);
          $(obj).parents("tr").find("input[name='vipDiscountFlag']").val(0);
          $(obj).parents("tr").find(".hyzhj").text(b);
          $(obj).parents("tr").find("input[name='vipDiscountPrice']").val(b);
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
				 	  $(this).parents("tr").find("input[name='discountPrice']").val(vb);
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
</script>
  </body>
</html>
