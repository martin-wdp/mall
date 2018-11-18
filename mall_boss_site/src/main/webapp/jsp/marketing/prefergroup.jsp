<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>促销分组</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">

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

            <h2 class="main_title">${pageNameChild} <small>(共${pb.rows }条)</small></h2>

            <div class="common_data p20">
            <div class="filter_area">
              <form role="form" class="form-inline" action="queryprelist.htm" method="post" id="searchForm">
                	<input type="hidden" name="CSRFToken" value="${token}" />
                	<input type="hidden" value="searchForm" id="formId">
                 	<input type="hidden" value="queryprelist.htm" id="formName">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">分组名称</span>
                    <input type="text" class="form-control" name="preferentialName" value="${preferentialName }">
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
           	  <th width="50">序号</th>
              <th>分组名称</th>
              <th width="150">操作</th>
            </tr> 
            </thead>
            <tbody>
              <c:forEach items="${pb.list}" var="pr" varStatus="i">  
		            <tr>
		              <td>${i.count}</td>
		              <td>${pr.preferentialName }</td>
		              <td>
		                <div class="btn-group">
		                  <button type="button" class="btn btn-default" onclick="edit(${pr.groupId },'${pr.preferentialName }');">编辑</button>
		                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
		                    <span class="caret"></span>
		                    <span class="sr-only">Toggle Dropdown</span>
		                  </button>
		                  <ul class="dropdown-menu" role="menu">
		                    <li><a href="javascript:void(0);" onclick="del(${pr.groupId });">删除</a></li>
		                  </ul>
		                </div>
		              </td>
		            </tr>
	           </c:forEach>
           
            </tbody>
            </table>

            <div class="table_foot">
             	<c:import url="../page/searchPag.jsp">
					     <c:param name="pageBean" value="${pb }"/>
					     <c:param name="path" value="../"></c:param>
					</c:import>
            </div>

            </div>

          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="editGroup"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改促销分组</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" action="insertmaketinggroup.htm" method="post" id="updateForm">
              <div class="form-group">
                <label class="control-label col-sm-5">分组名称：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-8">
                <input type="hidden" name="CSRFToken" value="${token }">
              	<input type="hidden" id="preferentialId" name="groupId" >
                  <input type="text" class="form-control" name="preferentialName" id="preferentialName">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="subForm();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
      
      function edit(id,name){
          $("#editGroup").find(".modal-title").text("修改促销分组");
          $("#updateForm").validate();
          $("#preferentialId").val(id);
          $("#preferentialName").val(name);
          $("#updateForm").attr("action","updatemarketing.htm");
          $('#editGroup').modal('show');
      }
      
      function subForm(){
          if($("#updateForm").valid()){
              $("#updateForm").submit();
          }
          
      }
      
      function del(id){
          showDeleteOneConfirmAlert('delmarketinggroupbyparam.htm?CSRFToken=${token}&groupId='+id,'确定要删除此分组吗？');   
      }
    </script>
  </body>
</html>
