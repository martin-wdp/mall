<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>未审核列表</title>

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
            <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows}条)</small></h2>

            <div class="common_data p20">
            <div class="filter_area">
              <form role="form" class="form-inline" action="auditlist.htm" method="post" id="searchForm">
                 <input type="hidden" value="searchForm" id="formId">
                 <input type="hidden" value="auditlist.htm" id="formName">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">公司名称</span>
                    <input type="text" class="form-control" name="companyName" value="${pageBean.objectBean.companyName }">
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">店铺名称</span>
                    <input type="text" class="form-control" name="storeName" value="${pageBean.objectBean.storeName }">
                  </div>
                </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary">搜索</button>
                </div>
              </form>
            </div>
            <div class="data_ctrl_area mb20">
              <div class="data_ctrl_search pull-right"></div>
              <div class="data_ctrl_brns pull-left">
                <button type="button" class="btn btn-info" onclick="checkauditlist()">
                  <i class="glyphicon glyphicon-ok"></i> 审核
                </button>
                <button type="button" class="btn btn-info" onclick="refuseauditlist()">
                  <i class="glyphicon glyphicon-remove"></i> 打回
                </button>
                <%--<button type="button" class="btn btn-info" onclick="deleteauditlist()">--%>
                  <%--<i class="glyphicon glyphicon-trash"></i> 删除--%>
                <%--</button>--%>
              </div>
              <div class="clr"></div>
            </div>

          <form id="delallform" method="post" action="delnewallstore.htm" method="post" >
             <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken">

           <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="10"><input type="checkbox" onclick="allunchecked(this,'storeId')"></th>
              <th>公司名称</th>
              <th>店铺名称</th>
              <th>法人姓名</th>
              <th>公司电话</th>
              <th>注册资本(万元)</th>
              <th class="w100s">申请时间</th>
              <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
             <c:forEach items="${pageBean.list}" var="store" varStatus="i">
             <fmt:formatDate value="${store.createTime }" pattern="yyyy-MM-dd HH:mm:ss" var="auditTime" />
	            <tr>
	              <td><input type="checkbox" name="storeId" value="${store.storeId }"></td>
	              <td>${store.companyName}</td>
	              <td>${store.storeName}</td>
	              <td>${store.bussLegalName}</td>
	              <td>${store.companyTel}</td>
	              <td>${store.companyCapital}</td>
	              <td>${auditTime}</td>
	              <td>
	                <div class="btn-group">
	                  <button type="button" class="btn btn-default" onclick="queryStore(${store.storeId})">查看详情</button>
	                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
	                    <span class="caret"></span>
	                    <span class="sr-only">Toggle Dropdown</span>
	                  </button>
	                  <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript:void(0);" onclick="checkonestore(${store.storeId})">审核</a></li>
	                    <li><a href="javascript:void(0);" onclick="refuseonestore(${store.storeId})">打回</a></li>
	                    <li><a href="javascript:void(0);" onclick="delonestore(${store.storeId})">删除</a></li>
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
    <div class="modal fade" id="shopDetails"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">店铺详细信息</h4>
          </div>
          <div class="modal-body">
            <iframe id="mainFrame" frameborder="0" style="width:100%;" src=""></iframe>
          </div>
        </div>
      </div>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="settleInfo"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">审核信息</h4>
          </div>
          <div class="modal-body">
            <form id="addStoreForm" method="post" action="" >
             <input type="hidden" name="storeId" id="storeId">
            <div class="form-inline mb20">
              <div class="form-group">
                <label class="control-label">添加支付方式</label>
                <select class="form-control" id="payway">
                </select>
              </div>
              <div class="form-group">
                <button type="button" class="btn btn-default" id="addPay">添加</button>
              </div>
            </div>
            <table class="table table-striped table-hover table-bordered">
              <thead>
              <tr>
                <th>支付方式</th>
                <th>
                    平台扣点
                    <a href="javascript:;" id="kou_dian" class=" help_tips">
                        <i class="icon iconfont">&#xe611;</i>
                    </a>

                </th>
                <th>
                    佣金
                    <a href="javascript:;" id="yong_jin" class=" help_tips">
                        <i class="icon iconfont">&#xe611;</i>
                    </a>
                </th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody id="pay_tb">
             
              </tbody>
            </table>
            <div class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-4">添加结算周期：</label>
                <div class="col-sm-1"><label class="control-label">每月</label></div>
                <div class="col-sm-15">
                  <div class="pt5">
                    <div class="tags"></div>
                  </div>
                </div>
              </div>
              <%--<div class="form-group">
                <label class="control-label col-sm-4">店铺类型：</label>
                <div class="col-sm-4"> 
                    <select class="form-control" name="storeQi" >
                     <option value="2">专卖店</option>
				     <option value="0">专营店</option>
				    <option value="1">旗舰店</option>
                 </select></div>
                <div class="col-sm-3"></div>
              </div>--%>
            </div>
           </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="savecheck">保存</button>
            <button type="button" class="btn btn-default hycCancle" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
    
     <!-- Modal -->
    <div class="modal fade" id="punishment"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">商家打回</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="refuseForm" action="" method="post">
               <input type="hidden" name="storeId" id="storeInfoId">
              <div class="form-group">
                <label class="control-label col-sm-5">打回原因：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-14">
                  <textarea class="form-control required specstr" rows="5" name="refuseContent"></textarea>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="saveRefuse">保存</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
    <script src="<%=basePath %>js/tabControl.js"></script> 
    <script src="<%=basePath %>js/customer/auditlist.js"></script>
    <script>

      $(function(){
        /* 表单项的值点击后转换为可编辑状态 */
       /*  $('.form_value').click(function(){
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
        }); */

          $('#kou_dian').popover({
              html: true,
              content : '<p style="color:#555;margin:0;">第三方支付对商家收取的点数</p>',
              trigger : 'hover'
          });

          $('#yong_jin').popover({
              html: true,
              content : '<p style="color:#555;margin:0;">平台对商家收取的点数</p>',
              trigger : 'hover'
          });



        $('#punishmentChoose').change(function(){
            if($(this).val() == 0){
              $('.punishment_1,.punishment_2').hide();
            }
            else if($(this).val() == 1){
              $('.punishment_1,.punishment_2').hide();
              $('.punishment_1').show();
            }
            else if($(this).val() == 2){
              $('.punishment_1,.punishment_2').hide();
              $('.punishment_2').show();
            }
          });
    	  /* 下面是表单里面的日期时间选择相关的 END */

          /**************  下面是关键字标签选择  ********************/
          /**************  获取所有标签值的方法 var v = $("#tag").getTabVals(); **************/
          $('.tags').tabControl({maxTabCount:5/*最大标签数*/,tabW:80/*标签最大长度*/,tabH : 25,errorcontent:'请输入1-31之间的正整数,且结算周期不能重复！',regularEx:/^([1-9]|[1-2]\d|3[0-1])$/ /*匹配正则*/}/*预置标签值*/);

      });
      //删除店铺
      function delonestore(storeId){
    	  showDeleteOneConfirmAlert('delnewallstore.htm?CSRFToken=${token}&storeId='+storeId+'');
      }
      //删除店铺
      function deleteauditlist(){
    	  showAjaxDeleteBatchConfirmAlert("delallform","storeId");
      }
      
      //审核
      function checkauditlist(){
    	  $("#storeId").val("");
    	  if(checkselect("storeId",0)){
    		  //初始化支付信息
    		   $.ajax({
 		         type: 'post',
 		         url:'findpayAll.htm?CSRFToken='+$("#CSRFToken").val(),
 		         async:false,
 		         success: function(data) {
 		        	 var ss = "";
 		        	 for(var i = 0 ;i< data.length;i++){
 		        		 ss+='<option value="'+data[i].payId+'">'+data[i].payName+'</option>';
 		        	 }
 		        	 $("#payway").html(ss);
 		         }
 			 }); 
    		  $('#settleInfo').modal('show');
    	  }else{
    		  return ;
    	  }
      }
      
      //打回
      function refuseauditlist(){
    	  $("#storeInfoId").val("");
    	  if(checkselect("storeId",0)){
    		  $('#punishment').modal('show');
    	  }else{
    		  return ;
    	  }
      }

      //查看店铺信息
      function queryStore(storeId){
    	  $("#mainFrame").attr("src",'sellerinfo.htm?CSRFToken=${token}&storeId='+storeId);
    	  $('#shopDetails').modal('show');
    	  $('#mainFrame').css('height','950px');
      }
      
      //单个拒绝店铺
      function refuseonestore(storeId){
    	  $('#punishment').modal('show');
    	  //$("#storeInfoId").val("");
    	  $("#storeInfoId").val(storeId);
      }
      
      //单个审核店铺
      function checkonestore(storeId){
    	  $.ajax({
		         type: 'post',
		         url:'findpayAll.htm?CSRFToken='+$("#CSRFToken").val(),
		         async:false,
		         success: function(data) {
		        	 var ss = "";
		        	 for(var i = 0 ;i< data.length;i++){
		        		 ss+='<option value="'+data[i].payId+'">'+data[i].payName+'</option>';
		        	 }
		        	 $("#payway").html(ss);
		         }
			 }); 
		  $('#settleInfo').modal('show');
		//  $("#storeId").val("");
		  $("#storeId").val(storeId);
      }
    </script>
  </body>
</html>
