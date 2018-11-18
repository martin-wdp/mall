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
  <title>商品详情</title>

  <!-- Bootstrap -->
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
  <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
  <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
  <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
  <link href="<%=basePath%>/css/style.css" rel="stylesheet">

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

        <h2 class="main_title"><small>商品详情</small></h2>

        <div class="common_data p20">
          <div class="data_ctrl_area mb20">
            <div class="data_ctrl_search pull-right"></div>
           <%-- <div class="data_ctrl_brns pull-left">
              <button type="button" class="btn btn-info" onclick="$('#addSku1').modal('show')">
                <i class="glyphicon glyphicon-plus"></i> 添加
              </button>
              <div class="btn-group">
                <button type="button" class="btn btn-info">批量上架</button>
                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                  <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">批量删除</a></li>
                  <li><a href="#">批量显示到移动版</a></li>
                </ul>
              </div>
              <div class="btn-group">
                <button type="button" class="btn btn-info">导出所有</button>
                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                  <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">导出当前页</a></li>
                  <li><a href="#">导出选中</a></li>
                </ul>
              </div>
            </div>--%>
            <div class="clr"></div>
          </div>

          <div role="tabpanel">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs mb20" role="tablist">
              <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">商品详情</a></li>
              <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">单品列表</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
              <div role="tabpanel" class="tab-pane active" id="tab1">

                <div class="good_details_info">
                  <div class="good_pics pull-left">
                    <div class="good_pics_big">
                      <img id="imgBig" alt="" src="${item.picUrl}">
                    </div>
                    <div class="good_pics_thumb">
                      <ul>
                        <c:forEach items="${item.itemImgs}" var="img">
                          <li><a href="javascript:;" data-img="${img.url}"><img alt="" src="${img.url}"></a></li>
                        </c:forEach>
                      </ul>
                    </div>
                  </div>
                  <div class="good_words pull-left">
                    <h2>${item.title}<small></small></h2>
                    <table>
                      <tbody>
                      <tr>
                        <td width="200">商品关键字：</td>
                        <td>无</td>
                      </tr>
                      <tr>
                        <td>商品编号：</td>
                        <td>${item.numIid}</td>
                      </tr>
                      <tr>
                        <td>商品类型：</td>
                        <td>${item.typeName}</td>
                      </tr>
                      <tr>
                        <td>商品品牌：</td>
                        <td>${item.brandName}</td>
                      </tr>
                      <%--<tr>
                        <td>商品分类：</td>
                        <td>休闲食品</td>
                      </tr>--%>
                      <tr>
                        <td>商品状态：</td>
                        <td>
                          <c:choose>
                            <c:when test="${item.approveStatus=='onsale'}">在售</c:when>
                            <c:otherwise>在库中</c:otherwise>
                          </c:choose>
                        </td>
                      </tr>
                      <tr>
                        <td>销售价格：</td>
                        <td><strong class="text-danger">￥${item.price}</strong> </td>
                      </tr>
                      <tr>
                        <td>计件单位：</td>
                        <td>${item.unit}</td>
                      </tr>
                      <%--<tr>
                        <td>是否促销：</td>
                        <td>否</td>
                      </tr>--%>
                      <tr>
                        <td>是否为自定义商品：</td>
                        <td>
                          <c:choose>
                            <c:when test="${isCustom=='0'}">标准商品</c:when>
                            <c:otherwise>自定义商品</c:otherwise>
                          </c:choose>
                        </td>
                      </tr>
                      <tr>
                        <td>商品描述：</td>
                        <td>${item.desc}</td>
                      </tr>
                      <tr>
                        <td>属性：</td>
                        <td>${item.propsName}</td>
                      </tr>
                      <%--
                      <tr>
                        <td>商品推荐点：</td>
                        <td>无</td>
                      </tr>--%>
                      </tbody>
                    </table>
                  </div>
                </div>

               <%-- <div class="good_details_tab common_tabs mt20">
                  <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#tab2_1" aria-controls="tab2_1" role="tab" data-toggle="tab">商品介绍</a></li>
                    <li role="presentation"><a href="#tab2_2" aria-controls="tab2_2" role="tab" data-toggle="tab">参数信息</a></li>
                    <li role="presentation"><a href="#tab2_3" aria-controls="tab2_3" role="tab" data-toggle="tab">搜索优化信息</a></li>
                  </ul>

                  <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tab2_1">
                      <div class="panel-group" id="spxq" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-default">
                          <div class="panel-heading" role="tab" id="spjjt">
                            <h4 class="panel-title">
                              <a role="button" data-toggle="collapse" data-parent="#spxq" href="#spjj" aria-expanded="true" aria-controls="spjj">
                                商品简介
                              </a>
                            </h4>
                          </div>
                          <div id="spjj" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="spjjt">
                            <div class="panel-body">
                              <p>乐事无限薯片104g*3组合装<br>
                                配料：马铃薯雪花粉,植物油,预糊化淀粉,嗞嗞烤肉味调味料/忠于原味调味料/鲜浓番茄调味料，请以包装信息为准。<br>
                                净含量：104g*3罐<br>
                                保质期：12个月<br>
                                生产许可证：QS3117 1201 0019 具体生产地以包装为准<br>
                                产品标准号：GB/T 22699<br>
                                储存方法：请置于干燥凉爽处，避免阳光直射，开口后请即食，以免受潮。</p>
                            </div>
                          </div>
                        </div>
                        <div class="panel panel-default">
                          <div class="panel-heading" role="tab" id="xxjst">
                            <h4 class="panel-title">
                              <a class="collapsed" role="button" data-toggle="collapse" data-parent="#spxq" href="#xxjs" aria-expanded="false" aria-controls="xxjs">
                                详细介绍
                              </a>
                            </h4>
                          </div>
                          <div id="xxjs" class="panel-collapse collapse" role="tabpanel" aria-labelledby="xxjst">
                            <div class="panel-body">
                              <div class="formwork">
                                <div class="formwork_img"><img data-lazyload="done" src="http://img20.360buyimg.com/vc/jfs/t658/301/1497030475/138830/69ec51e5/54f6f2b9N8a204910.jpg" class=""></div>
                              </div>
                              <div class="formwork">
                                <div class="formwork_img"><img data-lazyload="done" src="http://img20.360buyimg.com/vc/jfs/t472/150/1496214333/81571/bd353bf2/54f6f2cdNc06fc948.jpg" class=""></div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tab2_2">
                      <table class="table table-bordered table-striped table-hover w500 mb10">
                        <thead>
                        <tr>
                          <th colspan="2">扩展属性</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                          <td>可可含量：</td>
                          <td>40%</td>
                        </tr>
                        </tbody>
                      </table>
                      <table class="table table-bordered table-striped table-hover w500">
                        <thead>
                        <tr>
                          <th colspan="2">详细参数</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                          <td>生产厂家：</td>
                          <td>德芙南京分公司</td>
                        </tr>
                        <tr>
                          <td>保质期：</td>
                          <td>12个月</td>
                        </tr>
                        <tr>
                          <td>储存方式：</td>
                          <td>阴凉避光处</td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tab2_3">
                      <table class="table table-bordered table-striped table-hover w500">
                        <thead>
                        <tr>
                          <th colspan="2">详细参数</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                          <td>优化标题：</td>
                          <td></td>
                        </tr>
                        <tr>
                          <td>优化关键字：</td>
                          <td></td>
                        </tr>
                        <tr>
                          <td>优化详细信息：</td>
                          <td></td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>

                </div>--%>

              </div>
              <div role="tabpanel" class="tab-pane" id="tab2">

                <table class="table table-striped table-hover">
                  <thead>
                  <tr>
                    <th width="50"><input type="checkbox"></th>
                    <%--<th>图片</th>--%>
                    <%--<th>单品副标题</th>--%>
                    <th>单品编号</th>
                    <th>库存</th>
                    <th>销售价</th>
                    <th>状态</th>
                    <th>属性</th>
                    <%--<th width="150">操作</th>--%>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${item.skus}" var="sku">
                    <tr>
                      <td><input type="checkbox"></td>
                      <%--<td><img alt="" height="50" src="images/good_2_small.jpg"></td>--%>
                      <%--<td>${sku.sellPoint}</td>--%>
                      <td>${sku.skuId}</td>
                      <td>${sku.quantity}</td>
                      <td>${sku.price}</td>
                      <td>
                        <c:choose>
                          <c:when test="${sku.status=='normal'}">正常</c:when>
                          <c:otherwise>已删除</c:otherwise>
                        </c:choose>
                      </td>
                      <td>${sku.propertiesName}</td>
                      <td>
                        <div class="btn-group">
                          <%--<button type="button" class="btn btn-primary">采购至qpmall</button>--%>
                          <%--<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                          </button>
                          <ul class="dropdown-menu" role="menu">
                            <li><a href="#">修改</a></li>
                          </ul>--%>
                        </div>
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>

                <%--<div class="table_foot">
                  <div class="table_pagenav pull-right">
                    <div class="meneame">
                      <span class="disabled"> 上一页 </span>
                      <span class="current"> 1 </span>
                      <a href="#?page=2"> 2 </a>
                      <a href="#?page=3"> 3 </a>
                      <a href="#?page=4"> 4 </a>
                      <a href="#?page=5"> 5 </a>
                      <a href="#?page=6"> 6 </a>
                      <a href="#?page=7"> 7 </a>
                      ...
                      <a href="#?page=199"> 199 </a>
                      <a href="#?page=200"> 200 </a>
                      <a href="#?page=2"> 下一页 </a>
                    </div>
                  </div>
                  <div class="table_ctrl pull-left">
                    <form role="form" class="form-inline">
                      <label class="control-label">每页显示：</label>
                      <select class="form-control">
                        <option>10</option>
                        <option>20</option>
                        <option>50</option>
                        <option>100</option>
                      </select>
                    </form>
                  </div>
                  <div class="clr"></div>
                </div>--%>

              </div>
            </div>

          </div>

        </div>

      </div>

    </div>
  </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=basePath%>js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
</body>
</html>
