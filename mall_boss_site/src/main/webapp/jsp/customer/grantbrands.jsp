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
    <title>品牌审核列表</title>

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
            <h2 class="main_title">${pageNameChild}<small>(共${pageBean.rows }条)</small></h2>

            <div class="common_data p20">
            <div class="filter_area">
              <form role="form" class="form-inline" action="querygrandbrandlists.htm" method="post" id="searchForm">
                  <input type="hidden" value="searchForm" id="formId">
                  <input type="hidden" value="querygrandbrandlists.htm" id="formName">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">品牌名称</span>
                    <input type="text" class="form-control" name="brandName" value="${pageBean.objectBean.brandName }">
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
                <button type="button" class="btn btn-info" onclick="passbrands()">
                  <i class="glyphicon glyphicon-ok"></i> 通过
                </button>
                <button type="button" class="btn btn-info" onclick="refusebrands()">
                  <i class="glyphicon glyphicon-remove"></i> 拒绝
                </button>
              </div>
              <div class="clr"></div>
            </div>

		<form id="passallform" method="post" action="updateGrantBrands.htm" method="post" >
             <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken">
             <input type="hidden" value="passallform" id="formId">
             <input type="hidden" value="updateGrantBrands.htm" id="formName">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="10"><input type="checkbox" onclick="allunchecked(this,'brandIds')"></th>
              <th>申请店铺</th>
              <th>品牌logo</th>
              <th>品牌名称</th>
              <th>品牌别名</th>
              <th>品牌排序</th>
              <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
               <input name="rateStatuts" value="1"  type="hidden" id="rateStatuts"/>
			    <c:forEach items="${pageBean.list}" var="goodsBrand" varStatus="i">
			  		<tr class="tableListTr">
						<td><input type="checkbox" id="brand${i.index }"  class="brandIds" value="${goodsBrand.grandBrandId}" name="brandIds" /></td>
						<td>${goodsBrand.storeName }</td>
						<td><img src="${goodsBrand.brandLogo}" alt="" width="90" height="40" /></td>
						<td>${goodsBrand.brandName}</td>
						<td>${goodsBrand.brandNickname}</td>
						<td>${goodsBrand.brandSort}</td>
						<td>
			                <div class="btn-group">
			                  <button type="button" class="btn btn-default" onclick="passonebrand(${goodsBrand.grandBrandId})">通过审核</button>
			                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
			                    <span class="caret"></span>
			                    <span class="sr-only">Toggle Dropdown</span>
			                  </button>
			                  <ul class="dropdown-menu" role="menu">
			                    <li><a href="javascript:void(0);" onclick="refuseonebrand(${goodsBrand.grandBrandId})">拒绝</a></li>
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
    <div class="modal fade" id="refusebrand"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">品牌拒绝</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="refuseForm" action="" method="post">
             <input type="hidden" name="CSRFToken" value="${token}">
               	<input name="brandIds" type="hidden" id="rateBrandIds"/>
               <input name="rateStatuts" value="2"  type="hidden" id="rateStatuts"/>
               <input  value=""  type="hidden" id="refusetype"/>
              <div class="form-group">
                <label class="control-label col-sm-5">拒绝原因：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-14">
                  <textarea class="form-control required specstr" rows="5" name="reason"></textarea>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="saverefuse()">保存</button>
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
    <script>
      $(function(){
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
      });
      
      //通过
      function passbrands(){
    	 // showAjaxDeleteBatchConfirmAlert("passallform","brandIds");
		if(checkselect("brandIds",0)){
			$.ajax({
				url:"updategrantbrands.htm",
				data:$("#passallform").serialize(),
				success:function(data){
					location.reload();
				}
			});
    	  }else{
    		  return;
    	  }
      }
      
      //拒绝
      function refusebrands(){
        $("#rateBrandIds").val("");
        $("#refusetype").val("");
 		if(checkselect("brandIds",0)){
    		  $("#refusebrand").modal('show');
    	  }else{
    		  return;
    	  }
      }
      
      //单个品牌通过
      function passonebrand(brandId){
    	  $.ajax({
				url:"updategrantbrands.htm?CSRFToken=${token}&brandIds="+brandId+"&rateStatuts=1",
				success:function(data){
					location.reload();
				}
			});
      }
      
      //保存拒绝理由
      function saverefuse(){
    	  if($("#refuseForm").valid()){
    		  if($("#refusetype").val()==''){
	    		  var checks = $(".brandIds");
	    		  var checkGroup = new Array();
	   		  		 for (var i = 0; i < checks.length; i++) {
			  		        var e = checks[i];
			  		        if (e.checked == true) {
		  		        		//$("#rateBrandIds").prop("value",$("#brand"+i).val());
		  		        		 $("#refuseForm").append('<input value="'+$("#brand"+i).val()+'" type="hidden" name="brandIds" />');
		  		        	}
	    		  		checkGroup.push(e);
	    		  	 }
    		  }
	    	  $.ajax({
					url:"updategrantbrands.htm",
				    type:"post",
					data:$("#refuseForm").serialize(),
					success:function(data){
						location.reload();
					}
				});
    	    }
      }
      
      //单个拒绝
      function refuseonebrand(grandBrandId){
    	  $("#refusebrand").modal('show');
    	  $("#rateBrandIds").val(grandBrandId);
    	  $("#refusetype").val(1);
      }
    </script>
  </body>
</html>
