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
    <title>商品促销列表</title>

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

            <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

            <div class="common_data p20">
            <div class="filter_area">
              <form role="form" class="form-inline" action="marketgoodslist.htm" method="post" id="searchForm">
             		 <input type="hidden" value="searchForm" id="formId">
                 	 <input type="hidden" value="marketgoodslist.htm" id="formName">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">商品促销名称</span>
                    <input type="text" class="form-control"  name="marketingName" value="${pageBean.objectBean.marketingName }" >
                  </div>
                </div>

                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">促销类型</span>
                  	 <select class="cate_selector"  data-live-search="true" name="codexId">
	                        <option value="">选择类型</option>

	                        <c:forEach items="${codexs }" var="codex">
	                        	  <c:if test="${codex.codexType!= 10&&codex.codexType!= 11}">
	                        			 <option value="${codex.codexId }" <c:if test="${pageBean.objectBean.codexId==codex.codexId }"> selected="selected"   </c:if>>${codex.codexName }</option>
	                       			</c:if>
	                        </c:forEach>
	                    </select>
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
	  <form role="form" class="form-inline"  action="delallmarketingbyids.htm?CSRFToken=${token}" method="post" id="delForm">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="10"><input type="checkbox"  onclick="allunchecked(this,'marketingId');"></th>
              <th>促销名称</th>
              <th>营销规则</th>
              <th width="400">描述</th>
              <th width="100">优惠分组</th>
              <th>开始时间</th>
              <th>结束时间</th>
              <th width="130">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageBean.list}" var="market" varStatus="i">
            <tr>
              <td><input type="checkbox"  name="marketingId"  value="${market.marketingId }"></td>
              <td width="120" style="text-align:left;">${market.marketingName }</td>
              <td>${market.codexName } </td>
              <td>

                 <c:if test="${fn:length(market.marketingDes) gt 20}">
                    ${fn:substring(market.marketingDes,0,19)}...
                 </c:if>

                <c:if test="${fn:length(market.marketingDes)<=20 }">
                  ${market.marketingDes}
                </c:if>

              </td>
              <td>${market.preferentialName }</td>
           	  <td> <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${market.marketingBegin }" type="both"/></td>
			  <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${market.marketingEnd }" type="both"/></td>
              <td>
                <div class="btn-group">
                  <button type="button" class="btn btn-default" onclick="viewmarket(${market.marketingId});">查看</button>
                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="javascript:void(0);" onclick="modifymarketing(${market.marketingId},${market.codexType });">修改</a></li>
                    <li><a href="javascript:void(0);" onclick="delmarketing(${market.marketingId})">删除</a></li>
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
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
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

        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 富文本编辑框 */
        $('.summernote').summernote({
          height: 300,
          tabsize: 2,
          lang: 'zh-CN'
        });

        /* 选择规格值 */
        $('.spec_set input').change(function(){
          if($(this).is(':checked')){
            $(this).parent().parent().next().slideDown('fast');
          }
          else {
            $(this).parent().parent().next().slideUp('fast');
          }
        });

        /* 下面是表单里面的填写项提示相关的 */
        $('.xiaoshoujia').popover({
          content : '此价格只用于显示，以商品定价为商品销售价',
          trigger : 'hover'
        });


      });

      function viewmarket(marketingId){
          $("#mainFrame").attr("src",'marketingdetail.htm?CSRFToken=${token}&marketingId='+marketingId);
          $('#scanPromotion').modal('show');
          $('#mainFrame').css('minHeight','450px');

      }

      function delmarketing(marketingId){
          showDeleteOneConfirmAlert('delmarketingbyid.htm?marketingId='+marketingId+'&CSRFToken=${token}','确定要删除此促销吗？');
      }

      function delallmarketing(){
         	 showDeleteBatchConfirmAlert('delForm','marketingId','确定要删除所选中的促销吗？');
      }

      function addMarket(){
          window.location.href="createproductmarket.htm?menuId=71&menuParentId=125&myselfId=2607";
      }

      function modifymarketing(marketingId,codexType){
    	  var marketingFlag;
    	  if(codexType==1 || codexType==9){
    		  marketingFlag="DP";
    	  }else if(codexType==12){
    		  marketingFlag="BY";
    	  }else if(codexType==15){
    		  marketingFlag="ZK";
    	  }else  if(codexType==5 || codexType==8){
    		  marketingFlag="MJO";
    	  }else  if(codexType==13 || codexType==14){
    		  marketingFlag="MJP";
    	  }
    	  window.location.href='modifybossmarketing.htm?marketingId='+marketingId+'&marketingFlag='+marketingFlag+'&CSRFToken=${token}';
      }
    </script>
  </body>
</html>
