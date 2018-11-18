<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
  <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/style.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<input type="hidden" name="CSRFToken" value="${token}" class="token_val"/>
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
  <div class="row">
    <jsp:include page="../page/left.jsp"></jsp:include>
    <input type="hidden" value="goodsisthirdList" id="formId"/>
    <input type="hidden" value="findAllGoodsisthird.htm" id="formName"/>
    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

        <h2 class="main_title">${pageNameChild} <small>(共${pb.rows }条)</small></h2>


        <div class="common_data p20">
          <div class="filter_area">
            <form role="form" class="form-inline" method="post" action="queryByParamisthird.htm?CSRFToken=${token }" id="adv_form">
              <input type="hidden" id="searchPageSize" name="pageSize" value="${pb.pageSize }"/>
              <input type="hidden" id="searchPageNo" name="pageNo" value="${pb.pageNo }"/>
              <input type="hidden" id="showFlag" name="showFlag" value="0"/>
              <input type="hidden" name="isThird" value="1" />

              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">商品标题</span>
                  <input type="text" name="goodsName" class="form-control"  value="${searchBean.goodsName}">
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">商品编号</span>
                  <input type="text" name="goodsNo"  value="${searchBean.goodsNo}" class="form-control">
                </div>
              </div>


             
                              <div class="form-group">
                                <div class="input-group">
                                     <span class="input-group-addon">商品品牌</span>
                                     <select   class="cate_selector w150 " name="goodsBrandId" data-live-search="true">
				                        <option value="">选择品牌</option>
				                        <c:forEach items="${brandList }" var="brand">
				                         <option value="${brand.brandId }"
				                            <c:if test="${searchBean.goodsBrandId==brand.brandId }">
				                                selected="selected"
				                            </c:if>
				                           >${brand.brandName }</option>
				                        </c:forEach>
				                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                     <span class="input-group-addon">是否上架</span>
                                     <select class="form-control cate_selector"  name="status">
				                         <option value="-1">是否上架</option>
				                        <option value="0">否</option>
				                        <option value="1">是</option>
				                    </select>
                                </div>
                            </div>
                            
                                 <div class="form-group">
                                <div class="input-group">
                                     <span class="input-group-addon">一级分类</span>
                                     <select class="cate_selector w150"  data-live-search="true" name="oneCateId" onchange="chooseCateOne(this);">
				                        <option value="">选择分类</option>
				                        <c:forEach items="${oneCateList }" var="cate">
				                               <option value="${cate.catId}"  <c:if test="${oneCateId==cate.catId }">selected="selected" </c:if> >${cate.catName}</option>
				                        </c:forEach>
				                    </select>
                                </div>
                            </div>
                            
                             
                                 <div class="form-group">
                                <div class="input-group">
                                     <span class="input-group-addon">二级分类</span>
                                     <select class="cate_selector w150"  data-live-search="true"  id="twoCateId" name="twoCateId" onchange="chooseCateTwo(this);">
				                        <option value="">选择分类</option>
				                        <c:forEach items="${twoCateList }" var="cate">
				                               <option value="${cate.catId}"  <c:if test="${twoCateId==cate.catId }">selected="selected" </c:if> >${cate.catName}</option>
				                        </c:forEach>
				                    </select>
                                </div>
                            </div>
                             
                                 <div class="form-group">
                                <div class="input-group">
                                     <span class="input-group-addon">三级分类</span>
                                     <select class="cate_selector w150"  data-live-search="true" name="goodsCateId" id="threeCateId">
				                        <option value="">选择分类</option>
				                        <c:forEach items="${threeCateList }" var="cate">
				                               <option value="${cate.catId}"  <c:if test="${searchBean.goodsCateId==cate.catId }">selected="selected" </c:if> >${cate.catName}</option>
				                        </c:forEach>
				                    </select>
                                </div>
                            </div>
                        

              <div class="form-group">
                <button onclick="subForm('adv_form','1');" class="btn btn-primary">搜索</button>
                <!--<button type="button" class="btn btn-primary advanced_search">高级搜索</button>-->
              </div>
            </form>
          </div>
          <div class="data_ctrl_area mb20">
            <div class="data_ctrl_search pull-right"></div>
            <div class="data_ctrl_brns pull-left">
              <%--<button type="button" class="btn btn-info" onclick="delallcustomer();">--%>
                <%--<i class="glyphicon glyphicon-trash"></i> 删除--%>
              <%--</button>--%>
              <button type="button" class="btn btn-info" onclick="createIndex()">
                <i class="glyphicon glyphicon-th-large"></i> 生成索引
              </button>
              <div class="btn-group">
                <button type="button" class="btn btn-info" onclick="exportList();">导出所有</button>
                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                  <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="javascript:;" onclick="exportPage();">导出列表</a></li>
                  <li><a href="javascript:;" onclick="exportCheck();">导出选中</a></li>
                </ul>
              </div>
            </div>
            <div class="clr"></div>
          </div>
          <form action="batchDelGoodsisthird.htm?CSRFToken=${token }" method="post" id="goodsisthirdList">
          <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="25"><input type="checkbox" onclick="allunchecked(this,'goodsIds');"/></th>
              <th width="250">商品信息</th>
              <th width="150">商品编号</th>
              <!-- <th>库存</th> -->
              <th>是否上架</th>
              <th>分类</th>
              <th>品牌</th>
              <th>所属商家</th>
              <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pb.list}" var="goods" varStatus="sta">
              <tr>
                <!--<td class="tc">${sta.count }</td>  -->
                <td>
                  <input type="checkbox" name=goodsIds value='${goods.goodsId }'/>
                </td>
                <td width="250" style="text-align:left;">
                    <a href="${bset}/item/${goods.goodsInfoId}.html" target="_blank">
                   <div class="data_item">
			             			 <img  height="60" src='${goods.goodsImg }'/>
			                      <p title="${goods.goodsName}">${goods.goodsName}</p>
			                      <p class="text-muted">${goods.goodsPrice }</p>
			                    </div>
                        </a>
                </td>
                <td>
                  ${goods.goodsNo}
                </td>
               <%--  <td>
                    ${goods.stock }
                </td> --%>
                <td>
                  <c:if test="${goods.goodsAdded==0}"><a href="updateGoodsStaisthird.htm?CSRFToken=${token }&goodsId=${goods.goodsId }&goodsAdded=1&pageNo=${pb.pageNo }" class="label label-default">否</a></c:if>
                  <c:if test="${goods.goodsAdded==1}"><a href="updateGoodsStaisthird.htm?CSRFToken=${token }&goodsId=${goods.goodsId }&goodsAdded=0&pageNo=${pb.pageNo }" class="label label-success">是</a></c:if>
                </td>
                <td>
                    ${goods.goodsCate.catName }
                </td>
                <td>
                    ${goods.goodsBrand.brandName }
                </td>
                <td>
                  <c:if test="${goods.thirdName==null}">BOSS</c:if>
                  <c:if test="${goods.thirdName!=null}">${goods.thirdName }</c:if>
                </td>
                <td>
                  <div class="btn-group">
                    <button type="button" class="btn btn-default" onclick="location.href='queryAllByGoodsId.htm?CSRFToken=${token }&goodsId=${goods.goodsId}'">查看货品</button>
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                      <span class="caret"></span>
                      <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <%--<ul class="dropdown-menu" role="menu">--%>
                      <%--<li><a href="toModifyGoods.htm?isThird=1&CSRFToken=${token }&goodsId=${goods.goodsId}&pageNo=${pb.pageNo }&pageSize=${pb.pageSize }">修改</a></li>--%>
                    <%--</ul>--%>
                  </div>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          </form>

          <div class="table_foot">
            <c:import url="../page/searchPag.jsp">
              <c:param name="pageBean" value="${pb }"/>
            </c:import>
          </div>

        </div>

      </div>

    </div>
  </div>



<!-- Modal -->
<div class="modal fade" id="addSEO"  role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">添加SEO设置</h4>
      </div>
      <div class="modal-body">
        <form role="form" class="form-horizontal">
          <div class="form-group">
            <label class="control-label col-sm-5">SEO标题：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <input type="text" class="form-control"/>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">SEO关键字：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <input type="text" class="form-control"/>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">是否启用：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <label class="radio-inline">
                <input type="radio" name="open" id="open1" value="option1" checked/> 是
              </label>
              <label class="radio-inline">
                <input type="radio" name="open" id="open2" value="option2"/> 否
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">SEO描述：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <textarea class="form-control" rows="5"></textarea>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="multiEdit"  role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">编辑版权信息</h4>
      </div>
      <div class="modal-body">
        <form role="form" class="form-horizontal">
          <div class="summernote"></div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>
</div>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script charset="utf-8" src="<%=basePath%>js/goods/goods_list_isthird.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
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

    /* 富文本编辑框 */
    $('.summernote').summernote({
      height: 300,
      tabsize: 2,
      lang: 'zh-CN'
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.kucunyujing').popover({
      content : '当商品库存低于该值则进行预警',
      trigger : 'hover'
    });
    /* 高级搜索 */
    $('.advanced_search').popover({
      html : true,
      title : '高级搜索',
      content : $('.advanced_search_cont').html(),
      trigger : 'click',
      placement : 'bottom'
    }).click(function(){
    	 $('select[data-live-search="true"]').select2();
    });
    $('select[data-live-search="true"]').select2();
  });

  //删除商品列表信息
  function delallcustomer(goodsIds){
    showDeleteBatchConfirmAlert('goodsisthirdList','goodsIds','确定要删除所选中的商品吗？');
  }

  
  function chooseCateOne(obj){
  	if($(obj).val()!=''){
  	 $.ajax({
  	        url:'querySonCateByCatIdAndName.htm?CSRFToken=${token}&catId='+$(obj).val(),
  	        success: function (data) {
  	        	
  	            var html = '<option  value="">选择分类</option>';
  	            for(var i=0;i<data.length;i++) {
  	                var cate = data[i];
  	                
  	                html += '<option  value="'+cate.catId+'"';
  	                if(i==0){
  	                	html+=' selected="selected"';
  	                }
  	                html+='>'+cate.catName+'</option>';
  	                
  	            }
  	            $("#twoCateId").html(html);
  	            $('select[data-live-search="true"]').select2();
  	            chooseCateTwo($("#twoCateId"));
  	        }
  	 });
  	}else{
  		 $("#twoCateId").html('<option value="">选择分类</option>');
  		 $("#threeCateId").html('<option value="">选择分类</option>');
  		 $('select[data-live-search="true"]').select2();
  	}
  }
  
  function chooseCateTwo(obj){
  	if($(obj).val()!=''){
  	 $.ajax({
  	        url:'querySonCateByCatIdAndName.htm?CSRFToken=${token}&catId='+$(obj).val(),
  	        success: function (data) {
  	            var html = '<option  value="">选择分类</option>';
  	            for(var i=0;i<data.length;i++) {
  	                var cate = data[i];
  	                
  	                html += '<option  value="'+cate.catId+'"';
  	                if(i==0){
  	                	html+=' selected="selected"';
  	                }
  	                html+='>'+cate.catName+'</option>';
  	                
  	            }
  	            $("#threeCateId").html(html);
  	            $('select[data-live-search="true"]').select2();
  	        }
  	 });
  	}else{
  		 $("#threeCateId").html('<option value="">选择分类</option>');
  		 $('select[data-live-search="true"]').select2();
  	}
  }
  
</script>
</body>
</html>
