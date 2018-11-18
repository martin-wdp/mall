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
  <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="<%=basePath %>/css/font-awesome.min.css">
  <link href="<%=basePath %>/iconfont/iconfont.css" rel="stylesheet">
  <link href="<%=basePath %>/css/summernote.css" rel="stylesheet">
  <link href="<%=basePath %>/css/bootstrap-select.min.css" rel="stylesheet">
  <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
  <link href="<%=basePath %>/css/style.css" rel="stylesheet">
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

<div class="container-fluid page_body">
  <div class="row">

    <jsp:include page="../page/left.jsp"></jsp:include>
    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <input type="hidden" value="itemList" id="formId">
      <input type="hidden" value="getInventoryItems.htm" id="formName">

      <!-- 需要替换的位置 -->
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

        <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

        <div class="common_data p20">
          <form action="" method="post" id="itemList">
          </form>
          <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="25"><input  onclick="selectAll('numIid')" type="checkbox"></th>
              <th width="250">商品信息</th>
              <th width="120">商品编号</th>
              <th width="50">商品标题</th>
              <th width="80">标准类目ID</th>
              <th width="80">商品类型</th>
              <th width="75">品牌</th>
              <!--   <th width="75">所属商家</th> -->
              <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageBean.items}" var="item" varStatus="sta">
              <tr>
                <td><input name="numIid" value='${item.numIid }'   type="checkbox"></td>
                <td>

                  <a href="${bset}/item/${item.numIid}.html" target="_blank">
                    <div class="data_item">
                      <img  height="60" src='${item.picUrl }'/>
                      <p title="${item.title}"><c:if test="${fn:length(item.title)>25}">${fn:substring(item.title , 0, 25)}</c:if>
                        <c:if test="${fn:length(item.title)<=25}">${item.title}</c:if>
                      </p>
                      <p class="text-muted">${item.price }</p>
                    </div>
                  </a>
                </td>
                <td width="150">${item.numIid}</td>
                <td> ${item.title }</td>
                <td>
                    ${item.cid }
                </td>
                <td> ${item.typeName}</td>
                <td> ${item.brandName }</td>
                  <%--  <td width="75"><c:if test="${goods.thirdName==null}">BOSS</c:if>
                       <c:if test="${goods.thirdName!=null}">${goods.thirdName }</c:if>
                       </td> --%>
                <td>
                  <div class="btn-group">
                    <button type="button" class="btn btn-default" onclick="location.href='javascript:;'">查看货品</button>
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                      <span class="caret"></span>
                      <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="javascript:;">修改</a></li>
                    </ul>
                  </div>
                </td>
              </tr>

            </c:forEach>
            </tbody>
          </table>
          <c:import url="../page/searchPag.jsp">
          </c:import>
        </div>

      </div>
    </div>
  </div>


</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>/js/bootstrap.min.js"></script>
<script src="<%=basePath %>/js/summernote.min.js"></script>
<script src="<%=basePath %>/js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>/js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>/js/common.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
</body>
</html>
