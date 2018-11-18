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
   <title>已审核列表</title>

   <!-- Bootstrap -->
   <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
   <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
   <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
   <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
   <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
   <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
   <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    body{background:none}
    </style>
    
</head>
<body>
<form role="form" class="form-inline" action="newupdatesellerinfo.htm" method="post" id="sellerinfoForm">
 	<input type="hidden" value="sellerinfoForm" id="formId">
 	<input type="hidden" value="newupdatesellerinfo.htm" id="formName">
 	<input type="hidden" value="${storeId}" name="storeId" id="storeId">
 </form>
 
 <form action="newupdatethridcate.htm" method="post" id="updateForm">
 	<input type="hidden" value="${storeId}" name="storeId" >
 </form>
   <div class="mb20">
      <button type="button" class="btn btn-info" id="cat_btn">添加签约分类</button>
       </div>
       <table class="table table-striped table-hover table-bordered catbo">
         <thead>
         <tr>
           <th width="10">序号</th>
           <th>所属分类</th>
           <th>分类名称</th>
           <th>操作</th>
         </tr>
         </thead>
         <tbody>
         <c:forEach items="${pageBean.list}" var="cate" varStatus="i">
	    	<tr class="tableListTr">
	    		<td>${i.count}</td>
	    		<td>${cate.parentName}</td>
	    		<td>${cate.catName}</td>
	    		<td>
	    		<button type="button" class="btn btn-default" onclick="delstorecate(${cate.catId})">删除</button>
	    		<!--  <a class="blu_bt" href="deletesellerinfocate.htm?thirdId=${storeId }&cateId=${cate.catId}">删除</a> -->
	    		</td>
	    	</tr>
	    </c:forEach>
         </tbody>
       </table>
       <input type="hidden" value="" id="pc_id" />
                            <input type="hidden" value="<c:if test="${thirdcate!=null}"><c:forEach items="${thirdcate}" var="cate">${cate.catId}|</c:forEach></c:if>" id="sc_id" />
                            <input type="hidden" value="<c:if test="${thirdcate!=null}"><c:forEach items="${thirdcate}" var="cate">${cate.catId}|</c:forEach></c:if>" name="cids" id="cids" class="hasa" />
       <div class="table_foot">
		<%-- <c:import url="../page/searchPag.jsp">
		     	<c:param name="pageBean" value="${pageBean }"/>
		    	<c:param name="path" value="../"></c:param>
		</c:import> --%>
				<div class="table_pagenav pull-right">
		    <div class="meneame">
		
		        <c:if test="${pageBean.pageNo!=1 }">
		            <a  href="javascript:void(0);" onclick="changeNextPage(${pageBean.pageSize },${pageBean.pageNo-1 })"> ${basePath}上一页 </a>
		        </c:if>
		        <c:if test="${pageBean.pageNo==1 }">
		            <span class="disabled"> 上一页 </span>
		        </c:if>
		
		        <c:forEach begin="${pageBean.startNo }" end="${pageBean.endNo}" varStatus="sta">
		            <c:choose>
		                <c:when test="${pageBean.pageNo==(pageBean.startNo+sta.count-1)}">
		                    <span class="current"> ${pageBean.startNo+sta.count-1}</span>
		                </c:when>
		                <c:otherwise>
		          			<a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },${pageBean.startNo+sta.count-1})" >${pageBean.startNo+sta.count-1} </a>
		                </c:otherwise>
		            </c:choose>
		
		        </c:forEach>
		
		        <c:if test="${pageBean.pageNo!=pageBean.totalPages}">
						 <a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },${pageBean.pageNo+1 })"> 下一页 </a>
		        </c:if>
		        <c:if test="${pageBean.pageNo==pageBean.totalPages }">
		            <span class="disabled"> 下一页 </span>
		        </c:if>
		
		    </div>
		</div>
       </div>
       
       <!-- Modal -->
    <div class="modal fade" id="addSignCate"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加签约分类</h4>
          </div>
          <div class="modal-body">
            <div class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-5">选择分类：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <select class="form-control" data-live-search="true" id="cate">
                   <!--  <optgroup label="女装">
                      <option selected>女士上衣</option>
                      <option>裙装</option>
                      <option>女裤</option>
                    </optgroup>
                    <optgroup label="图书音像">
                      <option>小说</option>
                      <option>文艺</option>
                      <option>儿童读物</option>
                    </optgroup> -->
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">从属类目：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-15">
                  <p><button type="button" class="btn btn-default" id="sel_all">全选</button></p>
                  <div id="belongcate">
                    <!-- <label class="checkbox-inline">
                      <input type="checkbox"> 女装
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 女士上衣
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> T恤
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 衬衫
                    </label> -->
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="addCate()">保存</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
    <script src="<%=basePath %>js/jquery.min.js"></script>
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/customer/catgra.js"></script>
  </body>
  <script type="text/javascript">
  function changeNextPage(pageSize,pageNo){
      $("#"+$("#formId").val()).attr("action",$("#formName").val())
              .append("<input type='hidden' name='pageSize' value='"+pageSize+"''>")
              .append("<input type='hidden' name='pageNo' value='"+pageNo+"''>")
              .submit();
  }
  </script>
  </html>