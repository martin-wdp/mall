<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
  <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

	<style type="text/css">
	body{background:none}
	div.meneame a{
	border: #ddd 1px solid;
		padding: 10px 10px 10px 15px; 
		background-position: 50% bottom;
		color: #555;
		margin-right: 3px;
		text-decoration: none;
	}
	</style>

</head>
<body>
	
            <div class="common_data p20">
               <div class="filter_area">
              <form role="form" class="form-inline" action="queryMobProductForGoods.htm" method="post" id="searchForm">
              
                  <input type="hidden" value="searchForm" id="formId">
                  <input type="hidden" value="queryMobProductForGoods.htm" id="formName">
              	<input id="size" name="size" type="hidden" value="${map.size}" />
              <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">选择分类</span>
                    <input type="text" class="form-control" id="cateChooseInput" data-toggle="dropdown">
                    <div class="dropdown-menu" role="menu" id="cateChoose">
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                     <input type="hidden" name="cateId"
						id="searchCatId" value="${map.cateId }" /> 
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">商品关键字</span>
                    <input type="text" class="form-control" name="name" value="${map.name }" >
                  </div>
                </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary">搜索</button>
                </div>
              </form>
            </div>
     <div>
      <form id="batchdel_ware" action="batchDelWare.htm?CSRFToken=${token }" method="post">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th>序号</th>
              <th>选择</th>
              <th>图片</th>
              <th>货品编号</th>
              <th>货品名称</th>
              <th>货品副标题</th>
              <th>优惠价</th>
            </tr>
            </thead>
            <tbody>
           	<c:forEach items="${map.pb.list}" var="product" varStatus="sta">
	            <tr>
	              <td>${sta.count }</td>
	              <td><input type="checkbox" name="productId" value="${product.goodsInfoId }" /></td>
	              <td><input type="hidden" value="${product.goodsInfoImgId}" id="productImg_${product.goodsInfoId }" />
	               <img src="${product.goodsInfoImgId}" width="131px" height="60px" /></td>
	              <td><span id="productNo_${product.goodsInfoId }">${product.goodsInfoItemNo}</span></td>
	              <td><span
								id="productName_${product.goodsInfoId }"
								title="${product.goodsInfoName}"> <c:choose>
										<c:when test="${fn:length(product.goodsInfoName) > 10}">
											<c:out
												value="${fn:substring(product.goodsInfoName, 0, 10)}..." />
										</c:when>
										<c:otherwise>
											<c:out value="${product.goodsInfoName}" />
										</c:otherwise>
									</c:choose>
							</span></td>
	               <td>
	               <span
						id="productTitle_${product.goodsInfoId }"
						title="${product.goodsInfoSubtitle}"> <c:choose>
								<c:when test="${fn:length(product.goodsInfoSubtitle) > 10}">
									<c:out
										value="${fn:substring(product.goodsInfoSubtitle, 0, 10)}..." />
								</c:when>
								<c:otherwise>
									<c:out value="${product.goodsInfoSubtitle}" />
								</c:otherwise>
							</c:choose>
					</span>
	              </td>
	              <td>
	              <span
								id="productPrice_${product.goodsInfoId }">${product.goodsInfoPreferPrice}</span>
	              </td>
	            </tr>
             </c:forEach>
            </tbody>
            </table>
		</form>
            <div class="table_foot">
               	<c:import url="../jsp/page/searchPag.jsp">
				     <c:param name="pageBean" value="${map.pb }"/>
				     <c:param name="path" value="../"></c:param>
				</c:import>
            </div>

            </div>
     
    <script src="<%=basePath %>js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    	<script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
     <script>
      $(function(){
        //点击出现下拉内容,光标移开下拉内容消失
        $('#cateChooseInput').click(function(){
          $('#cateChoose').show().css({
            'left' : $(this).prev('.input-group-addon').width() + 'px',
            'minWidth' : '200px'
          });
          $('#cateChoose').mouseleave(function(){
            $(this).hide();
          });
        });

        /* 下面是关于树形菜单 */
        var setting = {
          data: {
            simpleData: {
              enable: true
            }
          },
		    callback : {
		        onClick : onClick3 
		    }
        };

        function onClick3(event, treeId, treeNode, clickFlag) 
        {
            $("#cateChooseInput").val(treeNode.name);
            $("#searchCatId").val(treeNode.cateId);
        }
        
        /*获取所有移动版分类，填充高级搜索的Tree*/
        $.get("ajaxQueryMobCateBarForChoose.htm?CSRFToken=${token}", function (data)
        {
            var zNodes = new Array();
            var node = {
                    id : 0, cateId : -1,
                    pId : 0, name : "所有", open : true 
                };
            zNodes.push(node);
            for (var i = 0; i < data.length; i++)
            {
                if (data[i].cateId == $("#searchCatId").val()) {
                    $("#cateChooseInput").val(data[i].name);
                }
                node = {
                    id : data[i].cateBarId, cateId : data[i].cateId,
                    pId : data[i].parentId, name : data[i].name, open : true 
                };
                zNodes.push(node);
            }
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
       

        /* 下面是关于树形菜单 END */
        
        
        var parent = art.dialog.parent,				// 父页面window对象
		  api = art.dialog.open.api;	// 			art.dialog.open扩展方法
	if (!api) return;
	// 操作对话框
	api.title('选择商品')
		// 自定义按钮
		.button(
			{
				name: '保存',
				callback: function () {
					var win = art.dialog.open.origin;//来源页面
					//获取可选择的长度
					var size = $("#size").val()>0?$("#size").val():1;
					var flag = checkSelected('productId',size);
					
					if(flag==1){
						//获取productId集合
						var products = $("input[name='productId']:checked");
						//获取图片集合
						var ids = new Array();
						var srcs = new Array();
						var names = new Array();
						var prices = new Array();
						for(var i=0;i<products.length;i++){
							var id = $(products[i]).val();
							ids.push(id);
							srcs.push($("#productImg_" + id).val());
							names.push($("#productName_" + id).attr("title"));
							prices.push($("#productPrice_" + id).text());
						}
						win.window.saveChoooseProduct(ids, srcs, "",
								names, "", prices);
						art.dialog.close();
					}else if(flag==0){
						art.dialog.alert('选择的商品大于'+size+'个！');
						return false;
					}else{
						art.dialog.alert('请选择商品！');
						return false;
					}
				},
				focus: true
			},
			{
				name: '取消',
				callback: function () {
					art.dialog.close();
				}
			}
		);
      });
      
      
      
      
      /**
      * 检查是否选中一行
      * @param _objId      checkbox节点name属性名
      * @param _modifyFlag 标识符值
      * NINGPAI_xiaomm
      * 2014-03-04 14:22
      * */
      function checkSelected(_objId,size){
      	checkedList = new Array();
        	$("input[name='"+_objId+"']:checked").each(function() {
        		checkedList.push($(this).val());
        	});
        	if(checkedList.length > 0){
      		if(checkedList.length <= size){
      			return 1;
      		}else{
      			return 0;
      		}
      	}else{
        	  	return -1;
        	}
      }; 
      
    </script>
    
</body>
</html>