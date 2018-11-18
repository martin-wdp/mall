<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <title>移动版分类设置</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
 
 
    <jsp:include page="../page/header.jsp"></jsp:include>
   
    <div class="page_body container-fluid">
      <div class="row">
           	<jsp:include page="../page/left.jsp"></jsp:include>
           	
        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <div class="main_cont">
             <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild}</h2>
			
            <div class="common_data p20">

              <div class="data_ctrl_area mb20">
                <div class="data_ctrl_search pull-right"></div>
                <div class="data_ctrl_brns pull-left">
                  <button type="button" class="btn btn-info" onclick="createMobCateBar();">
                    <i class="glyphicon glyphicon-plus"></i> 添加分类
                  </button>
                </div>
                <div class="clr"></div>
              </div>

              <!-- 这是另一种表格，带伸缩，不带分页 -->
              <table class="treetable table table-striped table-hover" cellspacing="0" width="100%">
              <thead>
              <tr>
                <th>分类名称</th>
                <th>分类图片</th>
                <th>叶类目</th>
                <th>是否启用</th>
                <th width="150">操作</th>
              </tr>
              </thead>
              <tbody id="treetable">
               <c:set var="count" value="0"></c:set>
                <input type="hidden" id="sort_0" data-sort="0"/>
        <c:forEach var="mobcatebar" items="${pb.list }" varStatus="sta">
             <c:set var="count" value="${count+1 }"></c:set>
          
              <tr data-sort="${count}" id="sort_${mobcatebar.cateBarId }" data-parentId="0"  class="sortIndex" >
                <td style="text-align:left;">
                	
					${mobcatebar.name}
				</td>
				 <td>
                	<c:if test="${not empty mobcatebar.imgSrc}">
						<img class="vm" src="${mobcatebar.imgSrc}" width="30px" height="30px"/>
					</c:if>
					</td>
                <td>
					<c:if test="${mobcatebar.temp3=='0'}"><span class="label label-default">否</span></c:if>
					<c:if test="${mobcatebar.temp3=='1'}"><span class="label label-success">是</span></c:if>
				</td>
                <td>
                	<a href="javascript:void(0);" onclick="changeUserdStatus(${mobcatebar.cateBarId });">
                	<c:if test="${mobcatebar.isUsing=='0'}"><span class="label label-default">禁用</span></c:if>
					<c:if test="${mobcatebar.isUsing=='1'}"><span class="label label-success">启用</span></c:if>
					</a>
				</td>
                <td>
                  <div class="btn-group">
                    <button type="button" class="btn btn-default" onclick="updateMobCateBar(${mobcatebar.cateBarId });">编辑</button>
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                      <span class="caret"></span>
                      <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                       <c:if test="${mobcatebar.temp3!=1 }">
							  <li><a href="javascript:;" onclick="createChild(${mobcatebar.cateBarId })">添加子分类</a></li>
						</c:if>
                      <li><a href="javascript:void(0);" onclick="deleteMobCateBar(${mobcatebar.cateBarId });">删除</a></li>
                    	  
                    </ul>
                  </div>
                </td>
              </tr>
             
              <c:if test="${fn:length(mobcatebar.childs)>0}">
								<c:forEach var="childcatebar" items="${mobcatebar.childs }" varStatus="status">
								   <c:set var="count" value="${count+1 }"></c:set>
									  <tr class="sortIndex collapsed" data-parentId="${childcatebar.parentId}">
						                <td style="text-align:left;">
						                ${childcatebar.name}</td>
						                 <td>
						                	<c:if test="${not empty childcatebar.imgSrc}">
												<img class="vm" src="${childcatebar.imgSrc}" width="30px" height="30px"/>
											</c:if>
											</td>
						               <td>
											<c:if test="${childcatebar.temp3=='0'}"><span class="label label-default">否</span></c:if>
											<c:if test="${childcatebar.temp3=='1'}"><span class="label label-success">是</span></c:if>
										</td>
						                <td>
						                	<a href="javascript:void(0);" onclick="changeUserdStatus(${childcatebar.cateBarId });">
						                	<c:if test="${childcatebar.isUsing=='0'}"><span class="label label-default">禁用</span></c:if>
											<c:if test="${childcatebar.isUsing=='1'}"><span class="label label-success">启用</span></c:if>
											</a>
										</td>
						                <td>
						                  <div class="btn-group">
						                    <button type="button" class="btn btn-default" onclick="updateChildMobCateBar(${childcatebar.cateBarId },${mobcatebar.cateBarId });">编辑</button>
						                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						                      <span class="caret"></span>
						                      <span class="sr-only">Toggle Dropdown</span>
						                    </button>
						                    <ul class="dropdown-menu" role="menu">
						                       <li><a href="javascript:void(0);" onclick="deleteMobCateBar(${childcatebar.cateBarId });">删除</a></li>
						                    </ul>
						                  </div>
						                </td>
						              </tr>
								</c:forEach>
						
			  </c:if>
			
          </c:forEach>  
              
              
              </tbody>
              </table>

            </div>

          </div>
          
        </div>
      </div>
    </div>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/jqtreetable.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
        <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script>
      $(function(){

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

        /* 下面是另一种带折叠的表格，没有分页 */
        // 这里要说明一下
        // 比如现在有5行，其中第二行和第一行平级，第三行是第二行的下级行，第四行是第三行的下级
        // 第五行和第三行平级，也就是说是第二行的下级行，应该如下所示：
        //      1
        //      2
        //         3
        //            4
        //         5
        // 这样的话，map应该是这样的
        // 行号：1					 2				 3                 4				 5
        // map： 0    ,              0,              2,                3,                2
        // 说明：1行的上级的行为0    2行的上级也为0  3行的上级是第2行  4行的上级是第3行  第5行的上级是第2行
        //
          var map = new Array();  
           
          $(".sortIndex").each(function(){
              var s = $(this).attr("data-parentId"); 
              map.push($("#sort_"+s).attr("data-sort"));
         });   
        $("#treetable").jqTreeTable(map, {  
          openImg: "<%=basePath %>images/TreeTable/tv-collapsable.gif",
          shutImg: "<%=basePath %>images/TreeTable/tv-expandable.gif",
          leafImg: "<%=basePath %>images/TreeTable/tv-item.gif",
          lastOpenImg: "<%=basePath %>images/TreeTable/tv-collapsable-last.gif",
          lastShutImg: "<%=basePath %>images/TreeTable/tv-expandable-last.gif",
          lastLeafImg: "<%=basePath %>images/TreeTable/tv-item-last.gif",
          vertLineImg: "images/TreeTable/vertline.gif",
          blankImg: "<%=basePath %>images/TreeTable/blank.gif",
          collapse: false,
          column: 0,
          striped: true,
          highlight: true,
          state:false
        }); 
        /* 下面是另一种带折叠的表格，没有分页 END */

        /* 下面是关于树形菜单 */
        var setting = {
          data: {
            simpleData: {
              enable: true
            }
          }
        };

  
        var zNodes = new Array();
        
        var node = {
                id : 0, pId : 0, name : '全部', open : false
            };
        zNodes.push(node);
        
        <c:forEach var="mobcatebar" items="${pb.list }" varStatus="sta">
	        var node = {
	                id : '${mobcatebar.cateBarId}', pId : '${mobcatebar.parentId}', name : '${mobcatebar.name}', open : false
	            };
            zNodes.push(node);
        </c:forEach>
        
  
      
        $(document).ready(function(){
          var zTree;
          $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
        /* 下面是关于树形菜单 END */

      });
      
      //启用禁用
      function changeUserdStatus(id){
      	window.location.href = "changeMobCateBarUserdStatus.htm?CSRFToken=${token}&mobCateBarId="+id;
      }
      //删除
      function deleteMobCateBar(id){
          showDeleteOneConfirmAlert('deleteMobCateBar.htm?CSRFToken=${token}&mobCateBarId='+id,'确定要要删除此分类吗？');
      }
      
      //添加
		function createMobCateBar(){
			location="showMobCateBar.htm?CSRFToken=${token}";
		}
      
      function updateMobCateBar(id){
          location="showMobCateBar.htm?CSRFToken=${token}&mobCateBarId="+id;
          
      }
   
    //添加二级分类导航
      function createChild(parentId){
      	location = "showMobCateBarByParentId.htm?CSRFToken=${token}&parentId="+parentId;
      }
    
    
    //修改二级分类导航  
      function updateChildMobCateBar(id,parentId){
      	location="showMobCateBarByParentId.htm?CSRFToken=${token}&mobCateBarId="+id+"&parentId="+parentId;
      }
    </script>
  </body>
</html>
