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
    <title>添加移动端分类导航</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">

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

            <div class="common_form common_form_lg">
              <form class="form-horizontal" id="addForm"  method="post" enctype="multipart/form-data"
             		 <c:if test="${empty mobcatebar }">
						action="createMobCateBar.htm?CSRFToken=${token }"
					</c:if>
					<c:if test="${not empty mobcatebar }">
						action="updateMobCateBar.htm?CSRFToken=${token }"
					</c:if>
              >
              	<input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
				<input type="hidden" name="cateBarId" id="up_cateBarId" value="${mobcatebar.cateBarId }"/>
				<input type="hidden" id="grade" value="${grade }"/>
				<input type="hidden" id="parentTemp2" value="${parent.temp2 }"/>
					<c:if test="${empty mobcatebar }">
						<input type="hidden" name="parentId" id="up_parentId" value="${parent.cateBarId }"/>
					</c:if>
					<c:if test="${not empty mobcatebar }">
						<input type="hidden" name="parentId" id="up_parentId" value="${mobcatebar.parentId }"/>
					</c:if>
		
                <div class="form-group">
                  <label class="control-label col-sm-5"><span class="text-danger">*</span>分类导航名称：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-8">
                    <input type="text" class="form-control required" name="name"  id="up_name" value="${mobcatebar.name }" >
                  </div>
                </div>
               <c:if test="${grade==1}">
                <div class="form-group">
                  <label class="control-label col-sm-5">分类导航图片：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-8">
                    <input type="hidden" name="imageSrc" id="up_imgSrc"  value="">
                    <img src="${mobcatebar.imgSrc }"  id="img" width="30" height="30"/>
                    <button type="button" class="btn btn-default" id="choose">上传图片</button>
                    <input type="hidden" name="imgSrc" id="imageSrc" value="${mobcatebar.imgSrc }"/>
                  </div>
                </div>
                </c:if>
               <c:if test="${grade==1}">
                <div class="form-group">
                  <label class="control-label col-sm-5">叶类目：</label>
                  <input type="hidden" id="temp3" value="${mobcatebar.temp3 }"/>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-13">
                    <div class="checkbox">
                      <label>
                        <input type="checkbox" id="isChild" name="temp3" value="1" class="third_chs"> 是否作为子类目显示
                      </label>
                    </div>
                  </div>
                </div>
                </c:if>
                <c:if test="${grade==2}">
					<input type="hidden" name="temp3" value="1"/>
				</c:if>  
                <div class="form-group" id="childChoose" <c:if test="${grade==1}">style="display: none;"</c:if>>
                  <label class="control-label col-sm-5"><span class="text-danger">*</span>选择类目链接：</label>
                  <input type="hidden" name="cateId" id="cateId" value="${mobcatebar.cateId }">
                  <div class="col-sm-1"></div>
                  <div class="col-sm-5">
                       <input type="text" class="form-control" id="cateChooseInput"  readonly data-toggle="dropdown">
                       <input type="text" class="form-control" name="temp1" id="temp1" value="${mobcatebar.temp1 }" style="display:none;"> 
	                   <div class="dropdown-menu" role="menu" id="cateChoose">
	                      <ul id="treeDemo" class="ztree"></ul>
	                    </div>
	                   <input type="hidden" id="temp2" value="${mobcatebar.temp2 }"/>
                  		
                  		
                  </div>
                   <div class="col-sm-1"></div>
                  <div class="col-sm-12">
                  <input type="checkbox" name="temp2" id="chooseTemp2" value="1" class="custom_chs"> 添加自定义链接（如要关联商品分类，请按“list/n”的格式填写，n代表商品分类编号）
                  </div>
               
             	
             	
                </div>
                <div class="form-group">
                  <label class="control-label col-sm-5">是否显示：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16">
                    <label class="radio-inline">
                      <input type="radio" name="isUsing" value="1" <c:if test="${empty mobcatebar.isUsing}">checked="checked"</c:if>
							<c:if test="${mobcatebar.isUsing==1 }">checked="checked"</c:if>> 是
                    </label>
                    <label class="radio-inline">
                      <input type="radio" name="isUsing" value="0" <c:if test="${mobcatebar.isUsing==0 }">checked="checked"</c:if>> 否
                    </label>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-sm-5"><span class="text-danger">*</span>排序：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-3">
                    <input type="text" class="form-control required digits"  name="sort"  value="${mobcatebar.sort }">
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-6 col-sm-5">
                    <button type="button" class="btn btn-primary" onclick="saveMob();">保存</button>
                  </div>
                </div>
              </form>
            </div>

          </div>
        </div>
      </div>
    </div>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/jquery.ztree.core-3.5.min.js"></script>
    <script src="<%=basePath%>js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="<%=basePath%>js/common.js"></script>
    
    <script>
      $(function(){
      	//修改时判断是否是叶类目，显示相关div
  		if($("#temp3").val()==1){
  			$(".third_chs").prop("checked","checked");
  		   $('#childChoose').show();
  		}
  		//修改时判断是否自定义类目
  		if($("#temp2").val()==1){
  			$(".custom_chs").prop("checked","checked");
  			$("#cateChooseInput").hide();
  			$("#temp1").show();
  		}
  		
			$("#addForm").validate();
          
          $("#choose").click(function(){
              i=1;
              art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=10000', {
                  lock: true,
                  opacity:0.3,
                  width: '900px',
                  height: '400px',
                  title: '选择图片'
              });
          });
          
          
          
          
          
          
          
          
          
        //点击出现下拉内容,光标移开下拉内容消失
        $('#cateChooseInput').click(function(){
          $('#cateChoose').show();
          $('#cateChoose').mouseleave(function(){
            $(this).hide();
          });
        });

        $('#isChild').change(function(){
          if($(this).is(':checked')){
            $('#childChoose').show();
          }
          else{
            $('#childChoose').hide();
          }
        });

        $('#chooseTemp2').change(function(){
            if($(this).is(':checked')){
                $('#cateChooseInput').hide();
                $('#temp1').show();
            }
            else{
                $('#cateChooseInput').show();
                $('#temp1').hide();
            }
          });


        
      
        
        
        $.get("queryallgoodcate.htm?catId=0&CSRFToken=${token}",function(data){
			if(null != data && data.length>0){
			    var zNodes = new Array();
			    /* 下面是关于树形菜单 */
		        var setting = {
		          data: {
		            simpleData: {
		              enable: true
		            }
		          },
		          callback : {
			  	        onClick : onClick 
			  	    }
		        };
				for(var i =0;i<data.length;i++){
					  for (var i = 0; i < data.length; i++)
				        {
					      if(data[i].catParentId!=null){
					          if($("#cateId").val()!=''){
					              if($("#cateId").val()==data[i].catId){
					                  $("#cateChooseInput").val(data[i].catName);
					              }
					          }
					          var node = {
						                id : data[i].catId, pId : data[i].catParentId, name : data[i].catName, open : false 
						            };
						            zNodes.push(node);
					      }
				               
				        }
				}
				  var zTree; 
		          $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			}
		});  
        

        /* 下面是关于树形菜单 END */
      });
      
      
   	function onClick(event, treeId, treeNode, clickFlag) 
	{
   	    if(!treeNode.isParent){
	   	     $("#cateId").val(treeNode.id);
	 	    $("#cateChooseInput").val(treeNode.name);
	 	    $("#up_name").val(treeNode.name);
   	    }
	   
	    
	}
      
      //图片回调
        function saveChoooseImage(url) {
          if(typeof (url) != 'string') {
              url = url[0];
          }
          if(url.indexOf(',')!=-1){
              url=url.substring(0,url.indexOf(','));  
          }
          $("#img").attr("src",url);
          $("#imageSrc").val(url);

      } 
      var num=0;
 		function saveMob(){
 		   if($('#isChild').is(':checked')){
 		       	$("#cateChooseInput").addClass("required");
		           if($("#chooseTemp2").is(':checked')){
		               $("#temp1").addClass('required');
		            	$("#cateChooseInput").removeClass("required");
		           }else{
		               $("#temp1").removeClass('required');
		           }
		           
		       }
 		  $("#addForm").validate();
 		   if($("#addForm").valid()&&num==0){
               num+=1;
 		      $("#addForm").submit();
			}
 		    
 		}
        
    </script>
  </body>
</html>
