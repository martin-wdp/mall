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
  <title>分销商品列表</title>

  <!-- Bootstrap -->
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <link rel="<%=basePath%>stylesheet" href="css/font-awesome.min.css">
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

    <jsp:include page="../page/left.jsp"></jsp:include>
    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

        <h2 class="main_title">商品列表 <small>(共${pageBean.rows}条)</small></h2>

        <div class="common_data p20">
          <%--<div class="filter_area mb10">
            <form role="form" class="form-inline">
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">商品名称</span>
                  <input type="text" class="form-control" >
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">商品编号</span>
                  <input type="text" class="form-control" >
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">商品分类</span>
                  <select class="form-control w200" data-live-search="true">
                    <option>请选择分类</option>
                    <optgroup label="女装">
                      <option>上衣</option>
                      <option>裙装</option>
                      <option>风衣</option>
                      <option>休闲裤</option>
                      <option>打底裤</option>
                      <option>毛针织衫</option>
                    </optgroup>
                    <optgroup label="女装">
                      <option>上衣</option>
                      <option>裙装</option>
                      <option>风衣</option>
                      <option>休闲裤</option>
                      <option>打底裤</option>
                      <option>毛针织衫</option>
                    </optgroup>
                    <optgroup label="女装">
                      <option>上衣</option>
                      <option>裙装</option>
                      <option>风衣</option>
                      <option>休闲裤</option>
                      <option>打底裤</option>
                      <option>毛针织衫</option>
                    </optgroup>
                    <optgroup label="女装">
                      <option>上衣</option>
                      <option>裙装</option>
                      <option>风衣</option>
                      <option>休闲裤</option>
                      <option>打底裤</option>
                      <option>毛针织衫</option>
                    </optgroup>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">商品品牌</span>
                  <select class="form-control w200" data-live-search="true">
                    <option>STEVEMADDEN</option>
                    <option>翰墨生香</option>
                    <option>玺玥</option>
                    <option>娘子写</option>
                    <option>E.Beauty/逸·红颜</option>
                    <option>逸红颜</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">是否上架</span>
                  <select class="form-control">
                    <option>全部</option>
                    <option>已上架</option>
                    <option>已下架</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">商品来源</span>
                  <select class="form-control">
                    <option>全部</option>
                    <option>自营</option>
                    <option>第三方</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">商家名称</span>
                  <input type="text" class="form-control" >
                </div>
              </div>
              <div class="text-center">
                <button type="submit" class="btn btn-primary">确认筛选</button>
              </div>
            </form>
          </div>
          <div class="data_ctrl_area mb20">
            <div class="data_ctrl_search pull-right"></div>
            <div class="data_ctrl_brns pull-left">
              <button type="button" class="btn btn-info" onclick="window.location.href='商品_商品管理_添加商品01_选择类目.html'">
                <i class="glyphicon glyphicon-plus"></i> 添加
              </button>
              <button type="button" class="btn btn-info">
                <i class="glyphicon glyphicon-trash"></i> 批量删除
              </button>
              <button type="button" class="btn btn-info">
                <i class="glyphicon glyphicon-th-large"></i> 生成索引
              </button>
              <div class="btn-group">
                <button type="button" class="btn btn-info">导出所有</button>
                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                  <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">导出列表</a></li>
                  <li><a href="#">导出选中</a></li>
                </ul>
              </div>
            </div>
            <div class="clr"></div>
          </div>--%>

          <div role="tabpanel">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs mb20" role="tablist">
              <li role="presentation" <c:if test="${itemtype=='onsale'}"> class="active"</c:if> ><a href="<%=basePath%>getOnsaleItems.htm" >已上架商品</a></li>
              <li role="presentation" <c:if test="${itemtype=='inventory'}"> class="active"</c:if>><a href="<%=basePath%>getInventoryItems.htm" >已下架商品</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
              <div role="tabpanel" class="tab-pane active" id="tab1">
                <input type="hidden" value="itemList" id="formId">
                <input type="hidden" value="${pageBean.url}" id="formName">
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
                          <button type="button" class="btn btn-default" onclick="location.href='<%=basePath%>getItemDetail.htm?numIid=${item.numIid}'">查看详情</button>
                          <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                          </button>
                          <ul class="dropdown-menu" role="menu">
                            <li><a href="javascript:;" item-key="${item.numIid}" class="importItem-btn">导入至qpmall</a></li>
                          </ul>
                        </div>
                      </td>
                    </tr>

                  </c:forEach>
                  </tbody>
                </table>

                <div class="table_foot" id="onsalepagefoot">
                  <c:import url="../page/searchPag.jsp">
                  </c:import>
                </div>

              </div>
              <%--div role="tabpanel" class="tab-pane" id="tab2">

                <table class="table table-striped table-hover">
                  <thead>
                  <tr>
                    <th width="50"><input type="checkbox"></th>
                    <th>图片</th>
                    <th>商品名称</th>
                    <th>商品编号</th>
                    <th>库存</th>
                    <th>销售价</th>
                    <th>是否上架</th>
                    <th>分类</th>
                    <th>品牌</th>
                    <th>所属商家</th>
                    <th width="150">操作</th>
                  </tr>
                  </thead>
                  <tbody id="inventoryitems">
                  </tbody>
                </table>

                <div class="table_foot" id="inventorypagefoot">
                </div>

              </div>--%>
            </div>

          </div>

        </div>

      </div>

    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal" id="importTips" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body">
        <p class="import-tip-p">导入成功！请在商品-->商品导入列表中进行发布商品等剩下操作.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=basePath%>js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script>
  $(function(){
    /**
    * 导入商品
     */
    $(".importItem-btn").click(
      function(){
        $.ajax({
          url:"importItem.htm",
          data:{numIid:$(this).attr("item-key")},
          type:'POST',
          dataType:'json',
          success:function(data){
            if(data.result){
              $("#importTips").modal('show');
            }else{
              $(".import-tip-p").html("导入失败。"+data.msg);
              $("#importTips").modal('show');
            }
          }
        });
      }
    );
  });
</script>
</body>
</html>
