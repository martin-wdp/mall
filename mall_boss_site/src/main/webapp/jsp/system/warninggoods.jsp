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
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <jsp:include page="../page/header.jsp"></jsp:include>

<div class="container-fluid page_body">
    <div class="row">

        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">

            <!-- 需要替换的位置 -->
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

          <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

            <div class="common_data p20">
            <div class="filter_area">
              <form role="form" class="form-inline" action="warninggoods.htm" method="post" id="searchForm" >
                  <input type="hidden" value="searchForm" id="formId">
                  <input type="hidden" value="warninggoods.htm" id="formName">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">货品名称</span>
                    <input type="text" class="form-control" name="goodsName" value="${warn.goodsName }">
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">货品编号</span>
                    <input type="text" class="form-control" name="goodsNo" value="${warn.goodsNo }">
                  </div>
                </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary">搜索</button>
                </div>
              </form>
            </div>

            <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>货品信息</th>
              <th>规格值</th>
              <th>货品编号</th>
              <th>所属商家</th>
              <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
           <c:forEach items="${pageBean.list }" var="lis" varStatus="i">
            <tr>
              <td width="300" style="text-align:left;">

                  <div class="data_item">
                      <img  height="60" src="${lis.imgId }"/>

                      <p title="${lis.goodname}">${lis.goodname}</p>

                      <p class="text-muted"> ${lis.preferPrice}</p>
                  </div>
              </td>
                <td style="text-align:left;">
               <c:forEach items="${lis.stockWarnSpec }" var="spc" varStatus="s">
                   <span class="label label-info">${spc.specname }:</span>&nbsp;<span
                       class="label label-success">${spc.specvalue }</span>
				</c:forEach>
              </td>
              <td>${lis.number }</td>
              <td>${lis.storename }</td>
              <td>
                <button type="button" class="btn btn-default" onclick="showStock(${lis.id });">查看</button>
              </td>
            </tr>
           </c:forEach>
            </tbody>
            </table>

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
    <div class="modal fade" id="warehouseInfo"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">仓库库存信息</h4>
          </div>
          <div class="modal-body">
          <form role="form" class="form-inline" action="updateproductwarn.htm?CSRFToken=${token }" method="post" id="warnForm">
            <table class="table table-striped table-hover" id="warnlist">
              <thead>
              <tr>
                <th>仓库名</th>
                <th>库存</th>
              </tr>
              </thead>
              <tbody>
             
              </tbody>
            </table>
			</form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="saveWare();">保存</button>
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
    <script src="<%=basePath %>js/select2.min.js"></script>
    <script>
      $(function(){
          $("#warnForm").validate();
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
      
      function showStock(id){
          var url="showwarninnewggoods.htm?id="+id+"&CSRFToken=${token}";
			$.ajax({
		         type: 'post',
		         url:url,
		         async:false,
		         success: function(data) {
		             var htm = "";
		             if (data!=null&&data.list!=null&&data.list.length > 0){
		                 for(var i=0;i<data.list.length;i++){
		                     htm+='<tr>';
			        	     htm+='<td>'+data.list[i].warename+'<input type="hidden" name="stockid" value="'+data.list[i].stockid+'"/> </td>';
			        	     htm+='<td><input type="text" class="form-control w100 required digits" maxlength="10" name="stock" value="'+data.list[i].stock+'"></td>';
			        	     htm+=' </tr>';
			             
		                 }
		        	    $("#warnlist tbody").html(htm);
		                 
		                 $('#warehouseInfo').modal('show');
		        	     
				      } else {
				          $("#warnlist tbody").html('');
				          $('#warehouseInfo').modal('show');
			          }
		         }
			 });
          
      }
      
      function saveWare(){
          if($("#warnForm").valid()){
              
              $("#warnForm").submit();
          }
          
      }
    </script>
  </body>
</html>

