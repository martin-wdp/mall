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
    <link rel="<%=basePath %>stylesheet" href="css/font-awesome.min.css">
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
            <h2 class="main_title">${pageNameChild} <small>(共${map.pb.rows}条)</small></h2>

            <div class="common_data p20">
            <div class="filter_area">
              <form role="form" class="form-inline" action="findallgoodsatte.htm" method="post" id="searchForm">
              	  		<input type="hidden" value="searchForm" id="formId">
                        <input type="hidden" value="findallgoodsatte.htm" id="formName">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">商品标题</span>
                    <input type="hidden" name="condition" value="1"/>
                    <input type="text" class="form-control" name="searchText" value="${map.searchBean.searchText }">
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
              <th>商品信息</th>
              <th>货号</th>
              <th width="100">成本价格</th>
              <th width="100">市场价格</th>
              <th width="100">关注度</th>
              <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${map.pb.list }" var="goodsAtte" varStatus="sta">
                <c:if test="${goodsAtte.product.goodsInfoItemNo gt 0}">
                    <tr>
                        <td width="440" style="text-align:left;">
                          <div class="data_item">
                            <img height="60" src="${goodsAtte.product.goodsInfoImgId }"/>
                                <p title="${goodsAtte.product.goodsInfoName}">${goodsAtte.product.goodsInfoName}</p>
                                <p class="text-muted"> ${goodsAtte.product.goodsInfoPreferPrice}</p>
                          </div>
                        </td>
                        <td>${goodsAtte.product.goodsInfoItemNo}</td>
                        <td> ${goodsAtte.product.goodsInfoCostPrice}</td>
                        <td>${goodsAtte.product.goodsInfoMarketPrice}</td>
                        <td>${goodsAtte.atteCount }</td>
                        <td>
                            <button type="button" class="btn btn-default" onclick="viewAtte(${goodsAtte.product.goodsInfoId },'${goodsAtte.product.goodsInfoName}')">查看</button>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
            </table>

            <div class="table_foot">
                 <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${map.pb}"/>
                    </c:import>
            </div>

            </div>

          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="scanFocus"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="nameTitle"></h4>
          </div>
          <div class="modal-body">
          	<iframe src="" id="goodsAtteDetail" frameborder="0" style="width:100%;max-height:450px;min-height:350px;" ></iframe>
        
          </div>
        </div>
      </div>
    </div>



    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
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
      
      function viewAtte(goodsInfoId,name){
          $("#nameTitle").text(name);
          $("#goodsAtteDetail").attr('src','findGoodsAtteByProductId.htm?CSRFToken=${token}&productId='+goodsInfoId);
          $('#scanFocus').modal('show');
      }
    </script>
  </body>
</html>
