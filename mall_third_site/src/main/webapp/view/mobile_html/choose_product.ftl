<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
<title></title>
  <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css">
    <link href="${basePath}/iconfont/iconfont.css" rel="stylesheet">
    <link href="${basePath}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">

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
              <form role="form" class="form-inline" action="queryThirdMobProductForGoods.htm" method="post" id="searchForm">
              	<input id="size" name="size" type="hidden" value="<#if map.size??>${map.size}</#if>" />
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">商品名称</span>
                    <input type="text" class="form-control" name="goodsInfoName" value="<#if map.objectBean?? && map.searchBean.goodsInfoName??>${map.objectBean.goodsInfoName }</#if>" >
                  </div>
                </div>
                  <div class="form-group">
                      <div class="input-group">
                          <span class="input-group-addon">商品编号</span>
                          <input type="text" class="form-control" name="goodsInfoNo" value="<#if map.objectBean?? && map.searchBean.goodsInfoNo??>${map.objectBean.goodsInfoNo }</#if>" >
                      </div>
                  </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary">搜索</button>
                </div>
              </form>
            </div>
     <div>
         <br/>
      <form id="batchdel_ware"  method="post">
            <table class="table table-striped table-hover">
            <thead style="background-color:#bdc6cd">
            <tr>
              <th>序号</th>
              <th>选择</th>
              <th>图片</th>
              <th>货品编号</th>
              <th>货品名称</th>
              <#--<th>货品副标题</th>-->
              <th>优惠价</th>
            </tr>
            </thead>
            <tbody>
        <#list map.pb.list as product>
            <#if product??>
	            <tr>
	              <td>${product_index+1 }</td>
	              <td><input type="checkbox" name="productId" value="${product.goodsInfoId }" /></td>
	              <td><input type="hidden" value="${product.goodsInfoImgId}" id="productImg_${product.goodsInfoId }" />
	               <img src="${product.goodsInfoImgId}" width="131px" height="60px" /></td>
	              <td><span id="productNo_${product.goodsInfoId }">${product.goodsInfoItemNo}</span></td>
	              <td><span
								id="productName_${product.goodsInfoId }"
								title="${product.goodsInfoName}">
                      <#if product.goodsInfoName?length lt 15>
                       ${product.goodsInfoName}
                      <#else>
                      ${product.goodsInfoName?substring(0,15)}...
                      </#if>
							</span></td>
	               <#--<td>-->
	               <#--<span-->
						<#--id="productTitle_${product.goodsInfoId }"-->
						<#--title="${product.goodsInfoSubtitle}">-->
                        <#--<#if product.goodsInfoSubtitle?length lt 15>-->
                        <#--${product.goodsInfoSubtitle}-->
                        <#--<#else>-->
                        <#--${product.goodsInfoSubtitle?substring(0,15)}...-->
                        <#--</#if>-->

					<#--</span>-->
	              <#--</td>-->
	              <td>
	              <span
								id="productPrice_${product.goodsInfoId }">${product.goodsInfoPreferPrice}</span>
	              </td>
	            </tr>
            </#if>
            </#list>
            </tbody>
            </table>
      <#if map.pb??>
          <#if (map.pb.list?size) &gt; 0 >
              <div class="ops-right">
                  <nav>
                      <ul class="pagination">
                          <li>
                              <a href="javascript:;" aria-label="Previous"
                                 onclick="changePageNo(this);" data-role="${map.pb.prePageNo}">
                                  <span aria-hidden="true">&laquo;</span>
                              </a>
                          </li>
                          <#if (map.pb.startNo?number>1)>
                              <li><a href="javascript:;">1</a></li>
                          </#if>
                          <#list map.pb.startNo?number .. map.pb.endNo as item>
                              <li <#if item == map.pb.pageNo>class="active"</#if>><a
                                      href="javascript:;" onclick="changePageNo(this);"
                                      data-role="${item}">${item}</a></li>
                          </#list>
                          <#if (map.pb.totalPages?number>map.pb.endNo)>
                              <li><a href="javascript:;" onclick="changePageNo(this);"
                                     data-role="${map.pb.totalPages}">${pageBean.totalPages}</a>
                              </li>
                          </#if>
                          <li>
                              <a href="javascript:;" aria-label="Next"
                                 onclick="changePageNo(this);" data-role="${map.pb.nextPageNo}">
                                  <span aria-hidden="true">&raquo;</span>
                              </a>
                          </li>
                      </ul>
                  </nav>
              </div>
          </#if>
      </#if>
		</form>

            </div>
    <script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
    <#--Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/jquery.ztree.core-3.5.min.js"></script>
    <script src="${basePath}/js/jquery.ztree.excheck-3.5.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="${basePath}/js/artDialog4.1.7/plugins/iframeTools.js"></script>
     <script>


         function changePageNo(obj){

             /*获取查询的方式标记*/

             $(".form-inline").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();

         }
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