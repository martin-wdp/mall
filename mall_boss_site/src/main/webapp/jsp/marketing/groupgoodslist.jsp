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
    <title>团购促销列表</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">
<%--
      <link href="<%=basePath %>css/style_new.css" rel="stylesheet">
--%>
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

                  <h2 class="main_title">${pageNameChild}
                      <small>(共${pageBean.rows }条)</small>
                  </h2>

                  <div class="common_data p20">
            <div class="filter_area">
              <form role="form" class="form-inline" action="groupgoodslist.htm" method="post" id="searchForm">
                	<input type="hidden" name="CSRFToken" value="${token}" />
                	<input type="hidden" value="searchForm" id="formId">
                 	<input type="hidden" value="groupgoodslist.htm" id="formName">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">促销名称</span>
                    <input type="text" class="form-control" name="marketingName" value="${pageBean.objectBean.marketingName }">
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
                        <button type="button" class="btn btn-info" onclick="addMarket();">
                            <i class="glyphicon glyphicon-plus"></i> 添加
                        </button>
                        <button type="button" class="btn btn-info" onclick="delallmarketing();">
                            <i class="glyphicon glyphicon-trash"></i> 删除
                        </button>
                    </div>
                    <div class="clr"></div>
                </div>
		<form action="delallmarketingbyids.htm?CSRFToken=${token}" method="post" id="delForm">
		<input type="hidden" name="codexType" value="10"/>
		
            <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th width="10"><input type="checkbox"  onclick="allunchecked(this,'marketingId');"></th>
            	<th>促销名称</th>
	      		<th>促销类型</th>
	      		<th class="w300">描述</th>
	      		<th >开始时间</th>
				<th >结束时间</th>
				<th>操作</th>
            </tr>
            </thead>
            <tbody>
              
               <c:forEach items="${pageBean.list}" var="market" varStatus="i">  
		    	<tr>
                    <td><input type="checkbox"  name="marketingId"  value="${market.marketingId }"></td>
					<td>${market.marketingName }</td>
					<td>${market.codexName }</td> 
					<td>${market.marketingDes }</td>
					<td> <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${market.marketingBegin }" type="both"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${market.marketingEnd }" type="both"/></td>
					<td>
		                 <div class="btn-group">
		                 <button type="button" class="btn btn-default" onclick="viewdetail(${market.marketingId });">查看</button>
		                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
		                    <span class="caret"></span>
		                    <span class="sr-only">Toggle Dropdown</span>
		                  </button>
		                  <ul class="dropdown-menu" role="menu">
                              <li><a href="javascript:void(0);" onclick="modifymarketing(${market.marketingId},${market.codexType });">修改</a></li>
		                    <li><a href="javascript:void(0);" onclick="delmarketing(${market.marketingId });">删除</a></li>
		                  </ul>
		                </div>
		              </td>
					
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
    <div class="modal fade" id="scanPromotion"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">促销详细信息</h4>
          </div>
          <div class="modal-body">
       		 <iframe id="mainFrame" frameborder="0" style="width:100%;" src=""></iframe> 
          </div>
        </div>
      </div>
    </div>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/summernote.min.js"></script>
    <script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath%>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>js/common.js"></script>
          <script src="<%=basePath %>js/common/common_alert.js"></script>
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
     function viewdetail(marketingId){
         $("#mainFrame").attr("src",'marketingdetail.htm?CSRFToken=${token}&marketingId='+marketingId);
         $('#scanPromotion').modal('show');
         $('#mainFrame').css('minHeight','450px');
     }

      function modifymarketing(marketingId,codexType){
          var marketingFlag;
          if(codexType==10 ){
              marketingFlag="GRP";
          }
          window.location.href='modifybossmarketing.htm?marketingId='+marketingId+'&marketingFlag='+marketingFlag+'&CSRFToken=${token}';
      }
     
     function delmarketing(marketingId){
         showDeleteOneConfirmAlert('delmarketingbyid.htm?marketingId='+marketingId+'&CSRFToken=${token}&codexType=10','确定要删除此促销吗？');
     }
     
     function delallmarketing(){
        	 showDeleteBatchConfirmAlert('delForm','marketingId','确定要删除所选中的促销吗？');
     }

      /**
       * 跳转到添加促销页
       * */
      function addMarket(){
          window.location.href="createproductmarket.htm?menuId=71&menuParentId=125&myselfId=2607";
      }

      /**
       *
       * 删除促销
       * */
      function delallmarketing(){
          showDeleteBatchConfirmAlert('delForm','marketingId','确定要删除所选中的促销吗？');
      }

    </script>
  </body>
</html>
