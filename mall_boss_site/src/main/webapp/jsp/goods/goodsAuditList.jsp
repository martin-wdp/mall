<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>

<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
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
        <!-- 引用左边 -->
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                 <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows}条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <form role="form" class="form-inline" id="search_form">
                            <input type="hidden" value="search_form" id="formId">
                            <input type="hidden" value="queryAuditByGoodsId.htm" id="formName">
                            <input  name="goodsId" type="hidden" value="${goodsId}"/>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">货品名称</span>
                                    <input type="text" class="form-control" name="goodsName" value="${selectBean.goodsName}" >
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">货品编号</span>
                                    <input type="text" class="form-control" name="goodsNo" value="${selectBean.goodsNo}">
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
                            <button type="button" class="btn btn-info" onclick="history.go(-1)">
                                返回列表
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="50"><input name="goodsInfoId"  onclick="allunchecked(this,'goodsInfoId')" type="checkbox"></th>
                            <th>图片</th>
                            <th>货品名称</th>
                            <th>规格值</th>
                            <th>货号</th>
                            <th>销售价</th>
                            <th>是否上架</th>
                            <th>列表显示</th>
                            <th>移动版显示</th>
                            <th>所属商家</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pb.list }" var="product" varStatus="sta">
                        <tr>
                            <td><input type="checkbox" name="goodsInfoId" value="${product.goodsInfoId }"/></td>
                            <td><img  height="50px" src="${product.goodsInfoImgId }"/></td>
                            <td>${product.goodsInfoName}</td>
                            <td><p title="
					<c:forEach items="${product.specVo }" var="spec" varStatus="sta2">
							${spec.spec.specName }:${spec.specValueRemark }　|　
					</c:forEach>
				">
                                <c:forEach items="${product.specVo }" var="spec" varStatus="sta2">
                                    <c:if test="${sta2.count<3 }">
                                        ${spec.spec.specName }:${spec.specValueRemark } <br/>
                                    </c:if>
                                </c:forEach>
                            </p> </td>
                            <td>${product.goodsInfoItemNo }</td>
                            <td> ${product.goodsInfoPreferPrice}</td>
                            <td>
                                <c:if test="${product.goodsInfoAdded==1}">
                                <a href="javascript:;" class="label label-success">是</a>
                                </c:if>
                                <c:if test="${product.goodsInfoAdded==0}">
                                    <a href="javascript:;" class="label label-default">否</a>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${product.showList==1}">
                                <a href="javascript:;" class="label label-success">是</a>
                                </c:if>
                                <c:if test="${product.showList==0}">
                                    <a href="javascript:;" class="label label-default">否</a>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${product.showMobile==0}">
                                <a href="javascript:;" class="label label-default">否</a>
                                </c:if>
                                <c:if test="${product.showMobile==1}">
                                    <a href="javascript:;" class="label label-success">是</a>

                                </c:if>
                            </td>
                            <td>${product.thirdName}</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="openDialog('${product.goodsInfoId }');">审核</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;"
                                               onclick="openRefuseDialog('${product.goodsInfoId}');">拒绝</a></li>

                                    </ul>
                                </div>
                            </td>
                        </tr>
                   </c:forEach>
                        </tbody>
                    </table>

                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pb}"/>
                    </c:import>

                </div>

            </div>

        </div>
    </div>
</div>


<!-- 审核Modal -->
<div class="modal fade" id="dialog-confirm2"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" method="post" id="auditProductAction">
                <input type="hidden" name="CSRFToken" value="${token}">


                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    你确定审核通过吗?
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" >确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 拒绝Modal -->
<div class="modal fade" id="dialog-confirm3"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal"  method="post" id="refuseAuditProductAction">
                <input type="hidden" name="CSRFToken" value="${token}">


                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div style="text-align:center;">

                        <div class="form-horizenal">
                            <div class="form-group">
                                <label class="control-label col-sm-5">拒绝原因:</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3"  maxlength="200" name="refuseReason"></textarea>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" >确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
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
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script>
    function openDialog(goodsInfoId) {

        $('#auditProductAction').attr("action","auditProductAction.htm?goodsInfoId="+goodsInfoId);
        $('#dialog-confirm2').modal('show');
    }
    function openRefuseDialog(goodsInfoId) {

        $('#refuseAuditProductAction').attr("action","refuseAuditProductAction.htm?goodsInfoId="+goodsInfoId)
        $('#dialog-confirm3').modal('show');
    }
</script>
</body>
</html>

