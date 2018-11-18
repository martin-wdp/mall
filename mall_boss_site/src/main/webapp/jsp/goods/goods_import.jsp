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
    <input type="hidden" value="${map.flag}" id="flag">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild}<small>(共${map.pageBean.rows}条)</small></h2>

            <div class="common_data p20">
            <div class="filter_area">
              <form role="form" class="form-inline" action="queryAllImport.htm?CSRFToken=${token }" method="post" id="searchForm">
              	 <input type="hidden" value="searchForm" id="formId">
                  <input type="hidden" value="queryAllImport.htm?CSRFToken=${token }" id="formName">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">商品标题</span>
                    <input type="hidden" name="condition" value="1"/>
                    <input type="text" class="form-control" name="searchText" value="${map.selectBean.searchText }"> 
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
                <button type="button" class="btn btn-info" onclick="delallimportgoods();">
                  <i class="glyphicon glyphicon-trash"></i> 删除
                </button>
                <button type="button" class="btn btn-info" onclick="downImportExcel();">
                  <i class="glyphicon glyphicon-th-large"></i> 下载模板
                </button>
                <button type="button" class="btn btn-info" onclick="$('#inportGoods').modal('show')">
                  <i class="glyphicon glyphicon-download-alt"></i> 导入商品
                </button>
              </div>
              <div class="clr"></div>
            </div>
           <form role="form" class="form-inline" action="batchDelImport.htm?CSRFToken=${token }" method="post" id="delForm">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="25"><input type="checkbox" onclick="allunchecked(this,'importCheck');"></th>
              <th width="200">商品标题</th>
              <th width="100">成本价格</th>
              <th width="100">市场价格</th>
              <th width="80">所属商家</th>
              <th width="80">是否发布</th>
              <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
              
          <c:forEach items="${map.pageBean.list }" var="goods" varStatus="sta">
            <tr>
              <td><input type="checkbox" name="importCheck" value="${goods.id}" /></td>
              <td  width="200" style="text-align:left;">
              	<div class="data_item" style="padding-left:0px;">
                     <p title="${goods.goodsName}">${goods.goodsName}</p>
                     <p class="text-muted">${goods.goodsPrice }</p>
                   </div>
              </td>
              <td>${goods.goodsCostPrice}</td>
              <td>${goods.goodsMarketPrice}</td>
              <td>${goods.thirdName}</td>
              <td>	
              <c:if test="${goods.addFlag==1}"><a href="javascript:;" class="label label-success">是</a></c:if>
              <c:if test="${goods.addFlag==0}"><a href="javascript:;" class="label label-default">否</a></c:if>
              </td>
              <td>
                  <c:if test="${goods.addFlag==0}"> <button type="button" class="btn btn-default" onclick="sendGoods(${goods.id});">发布</button></c:if>
                   <c:if test="${goods.addFlag==1}"><a href="javascript:;" class="label label-success">已发布</a></c:if>
              </td>
            </tr>
         </c:forEach>
            </tbody>
            </table>
		</form>
            <div class="table_foot">
              <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${map.pageBean}"/>
                    </c:import>
            </div>

            </div>

          </div>
        </div>
      </div>
    </div>
   <!-- Modal -->
   <div class="modal fade" id="error"  role="dialog">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title">操作提示</h4>
               </div>
               <div class="modal-body">
                       <div class="form-group">
                           <label class="control-label col-sm-13">商品导入失败，请查看导入数据是否有问题！</label>
                       </div>

               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
               </div>
           </div>
       </div>
   </div>
    <!-- Modal -->
    <div class="modal fade" id="inportGoods"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">导入商品</h4>
          </div>
          <div class="modal-body">
            <form role="form" class="form-inline" action="importGoods.htm?CSRFToken=${token }" id="importForm" method="post"  enctype="multipart/form-data" >
              <div class="form-group">
                <label class="control-label col-sm-8">上传excel文件：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <input type="file" name="importExcel" class="required">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="subImpot();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

	<!-- 下载导入模板 -->
	<form action="downImportExcel.htm?CSRFToken=${token }" method="post" class="downImportExcel">
	</form>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    
    <script>
      $(function(){
          $("#importForm").validate();
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
        //导入失败提示
          if($("#flag").val()==1){
             $("#error").modal('show');
          }

      });
      
      //导出模板
      function downImportExcel(){
      	$(".downImportExcel").submit();
      }
      //删除
      function delallimportgoods(){
          showDeleteBatchConfirmAlert('delForm','importCheck','确定要删除所选中的数据吗？');
      }
      
      function subImpot(){
          if($("#importForm").valid()){
              $("#importForm").submit();
          }
         
      }
      
      function sendGoods(goodsId){
    	  
    	  window.location.href='toPubImportGoods.htm?CSRFToken=${token }&type=1&id='+goodsId;
      }
    </script>
  </body>
</html>
