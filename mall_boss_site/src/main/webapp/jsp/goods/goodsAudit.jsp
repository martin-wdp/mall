<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品审核</title>

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
        <!-- 引用左边 -->
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}
                    <small>(共<c:choose><c:when
                            test="${empty map.pb.rows}">0</c:when><c:otherwise>${map.pb.rows }</c:otherwise></c:choose>条)
                    </small>
                </h2>

                <div class="common_data p20">
                    <div class="filter_area">

                        <form class="form-inline" id="goodsAuditList" method="post" action="goodsAudit.htm">

                            <input type="hidden" value="goodsAuditList" id="formId">
                            <input type="hidden" value="goodsAudit.htm" id="formName">
                            <input type="hidden" id="showFlag" name="showFlag" value="1"/>
                            <input type="hidden" name="isThird" value="-1"/>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">商品名称</span>
                                    <input name="goodsName" value="${searchBean.goodsName }" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">商家名称</span>
                                    <input type="text" class="form-control" name="thirdName" value="${searchBean.thirdName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">商品编号</span>
                                    <input type="text" class="form-control" name="goodsNo" value="${searchBean.goodsNo }">
                                </div>
                            </div>



                            <div class="form-group">

                            <div class="input-group">


                                    <span class="input-group-addon">商品品牌</span>
                                    <select class="cate_selector "  data-live-search="true" name="goodsBrandId">
                                        <option value="">-请选择-</option>
                                        <c:forEach items="${brandList }" var="brand">
                                            <c:if test="${searchBean.goodsBrandId==brand.brandId }">
                                                <option value="${brand.brandId }" selected="selected">${brand.brandName }</option>
                                            </c:if>
                                            <c:if test="${searchBean.goodsBrandId!=brand.brandId }">
                                                <option value="${brand.brandId }">${brand.brandName }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">一级分类</span>
                                    <select class="cate_selector w150" data-live-search="true" name="oneCateId"
                                            onchange="chooseCateOne(this);">
                                        <option value="">选择分类</option>
                                        <c:forEach items="${oneCateList }" var="cate">
                                            <option value="${cate.catId}"
                                                    <c:if test="${oneCateId==cate.catId }">selected="selected" </c:if> >${cate.catName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">二级分类</span>
                                    <select class="cate_selector w150" data-live-search="true" id="twoCateId"
                                            name="twoCateId" onchange="chooseCateTwo(this);">
                                        <option value="">选择分类</option>
                                        <c:forEach items="${twoCateList }" var="cate">
                                            <option value="${cate.catId}"
                                                    <c:if test="${twoCateId==cate.catId }">selected="selected" </c:if> >${cate.catName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">三级分类</span>
                                    <select class="cate_selector w150" data-live-search="true" name="goodsCateId"
                                            id="threeCateId">
                                        <option value="">选择分类</option>
                                        <c:forEach items="${threeCateList }" var="cate">
                                            <option value="${cate.catId}"
                                                    <c:if test="${searchBean.goodsCateId==cate.catId }">selected="selected" </c:if> >${cate.catName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <a onclick="doSubmit()" class="btn btn-primary">搜索</a>

                            </div>
                        </form>

                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="50"><input name="goodsIds"  onclick="allunchecked(this,'goodsIds')" type="checkbox"></th>
                            <th>商品信息</th>
                            <th width="150">商品编号</th>
                            <th>是否上架</th>
                            <th>分类</th>
                            <th>品牌</th>
                            <th>所属商家</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>


                        <c:if test="${map.isThirdAuditUsed==1}">
                            <c:forEach items="${map.pb.list}" var="goods" varStatus="sta">
                                <tr>
                                    <td><input type="checkbox" name="goodsIds" value="${goods.goodsId  }"></td>
                                    <td width="250" style="text-align:left;">
                                         <div class="data_item">
					             			  <img width="50" height="50" src='${goods.goodsImg }'/>
					                      <p title="${goods.goodsName}">${goods.goodsName}</p>
					                      <p class="text-muted">${goods.goodsPrice }</p>
					                    </div>
                                    </td>
                                    <td>${goods.goodsNo}</td>
                                    <td>
                                        <c:if test="${goods.goodsAdded==0}"><span
                                                class="label label-default">否</span></c:if>
                                        <c:if test="${goods.goodsAdded==1||goods.goodsAdded==''}"><span
                                                class="label label-success">是</span></c:if>
                                    </td>
                                    <td> ${goods.catName }</td>
                                    <td>${goods.brandName }</td>
                                    <td><c:if test="${goods.goodsBeloName==null}">BOSS</c:if>
                                        <c:if test="${goods.goodsBeloName!=null}">${goods.goodsBeloName }</c:if></td>
                                    <td>
                                        <div class="btn-group">
                                            <c:if test="${goods.auditStatus==1}">
                                                <button type="button" class="btn btn-default"
                                                        onclick="openDialog(${goods.goodsId})">

                                                    审核
                                                </button>
                                            </c:if>
                                            <c:if test="${goods.auditStatus!=1}">
                                                <button type="button" class="btn btn-default"
                                                        onclick="location.href='queryAuditByGoodsId.htm?goodsId=${goods.goodsId}'">
                                                    查看货品
                                                </button>
                                            </c:if>
                                            <button type="button" class="btn btn-default dropdown-toggle"
                                                    data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                                <c:if test="${goods.auditStatus==1}">
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="javascript:;"
                                                       onclick="openRefuseDialog('${goods.goodsId}');">拒绝</a></li>
                                                <li><a href="javascript:;"
                                                       onclick="location.href='queryAuditByGoodsId.htm?goodsId=${goods.goodsId}'">查看货品</a>
                                                </li>
                                            </ul>
                                                </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach></c:if>
                        </tbody>
                    </table>

                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${map.pb}"/>
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
            <form class="form-horizontal" action="auditByGoodsId.htm" method="post" id="auditByGoodsId">
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
            <form class="form-horizontal" action="refuseAuditByGoodsId.htm" method="post" id="refuseAuditByGoodsId">
                <input type="hidden" name="CSRFToken" value="${token}">


                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div style="text-align:center;">

                        <div class="form-group">
                            <label class="control-label col-sm-5">拒绝原因:</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <textarea class="form-control required" rows="3"  maxlength="200" name="refuseReason"></textarea>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="subGoods()">确定</button>
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
<script src="<%=basePath %>/js/select2.min.js"></script>
<script src="<%=basePath %>/js/jquery.validate.js"></script>

<script>
    function subGoods(){
        if($("#refuseAuditByGoodsId").valid()){
            $("#refuseAuditByGoodsId").submit();
        }
    }
    function doSubmit() {
        $("#goodsAuditList").submit();
    }
    function openDialog(gId) {
        $('#auditByGoodsId').attr("action","auditByGoodsId.htm?goodsId="+gId);
        $('#dialog-confirm2').modal('show');
    }
    function openRefuseDialog(gId) {
        $('#refuseAuditByGoodsId').attr("action","refuseAuditByGoodsId.htm?goodsId="+gId);
        $('#dialog-confirm3').modal('show');
    }

    function chooseCateOne(obj) {
        if ($(obj).val() != '') {
            $.ajax({
                url: 'querySonCateByCatIdAndName.htm?CSRFToken=${token}&catId=' + $(obj).val(),
                success: function (data) {

                    var html = '<option  value="">选择分类</option>';
                    for (var i = 0; i < data.length; i++) {
                        var cate = data[i];

                        html += '<option  value="' + cate.catId + '"';
                        if (i == 0) {
                            html += ' selected="selected"';
                        }
                        html += '>' + cate.catName + '</option>';

                    }
                    $("#twoCateId").html(html);
                    $('select[data-live-search="true"]').select2();
                    chooseCateTwo($("#twoCateId"));
                }
            });
        } else {
            $("#twoCateId").html('<option value="">选择分类</option>');
            $("#threeCateId").html('<option value="">选择分类</option>');
            $('select[data-live-search="true"]').select2();
        }
    }

    function chooseCateTwo(obj) {
        if ($(obj).val() != '') {
            $.ajax({
                url: 'querySonCateByCatIdAndName.htm?CSRFToken=${token}&catId=' + $(obj).val(),
                success: function (data) {
                    var html = '<option  value="">选择分类</option>';
                    for (var i = 0; i < data.length; i++) {
                        var cate = data[i];

                        html += '<option  value="' + cate.catId + '"';
                        if (i == 0) {
                            html += ' selected="selected"';
                        }
                        html += '>' + cate.catName + '</option>';

                    }
                    $("#threeCateId").html(html);
                    $('select[data-live-search="true"]').select2();
                }
            });
        } else {
            $("#threeCateId").html('<option value="">选择分类</option>');
            $('select[data-live-search="true"]').select2();
        }
    }

    $(function () {

        $('select[data-live-search="true"]').select2();

    });
</script>
</body>
</html>
