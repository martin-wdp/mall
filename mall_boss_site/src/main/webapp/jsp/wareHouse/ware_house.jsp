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
    <title>仓库管理</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

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

            <h2 class="main_title">${pageNameChild} <small>(共${map.pb.rows}条)</small></h2>

            <div class="common_data p20">

                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>提示!</strong> 仓库管理中需要添加至少一个仓库，否则商品会显示无货并且无法添加商品，在不了解情况下请联系我们的工作人员。
                </div>
            <div class="filter_area">
              <form role="form" class="form-inline" id="searchForm" action="queryWareHouseByPageBean.htm?CSRFToken=${token}" method="post">
                  <input type="hidden" value="searchForm" id="formId">
                  <input type="hidden" value="queryWareHouseByPageBean.htm" id="formName">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">仓库名称</span>
                    <input type="hidden" name="condition" value="1"/> 
                    <input type="text" class="form-control" name="searchText" value="${selectBean.searchText }" >
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
                <button type="button" class="btn btn-info" onclick="addwarehouse();">  
                  <i class="glyphicon glyphicon-plus"></i> 添加
                </button>
                <button type="button" class="btn btn-info" onclick="delallwarehouse();">
                  <i class="glyphicon glyphicon-trash"></i> 删除
                </button>
              </div>
              <div class="clr"></div>
            </div>
          <form id="batchdel_ware" action="batchDelWare.htm?CSRFToken=${token }" method="post">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="10"><input type="checkbox" onclick="allunchecked(this,'tagCheck');"></th>
              <th>仓库名称</th>
              <th>仓库地址</th>
              <th>备注</th>
              <th>唯一标识</th>
              <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${map.pb.list}" var="ware" varStatus="sta">
	            <tr>
	              <td><input type="checkbox"  name="tagCheck"  value="${ware.wareId}"></td>
	              <td>${ware.wareName}</td>
	              <td>${ware.wareAddress}</td>
	              <td width="300" style="text-align:left;">${ware.wareRemark}</td>
	              <td>${ware.identifyId}</td>
	              <td>
	                <div class="btn-group">
	                  <button type="button" class="btn btn-default"  onclick="onedit(${ware.wareId});">编辑</button>
	                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
	                    <span class="caret"></span>
	                    <span class="sr-only">Toggle Dropdown</span> 
	                  </button>
	                  <ul class="dropdown-menu" role="menu">
	                    <li><a href="javascript:void(0);" onclick="delWare(${ware.wareId});">删除</a></li>
	                  </ul>
	                </div>
	              </td>
	            </tr>
             </c:forEach>
            </tbody>
            </table>
		</form>
            <div class="table_foot">
               	<c:import url="../page/searchPag.jsp">
				     <c:param name="pageBean" value="${map.pb }"/>
				     <c:param name="path" value="../"></c:param>
				</c:import>
            </div>

            </div>

          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="addWarehouse"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">添加仓库</h4>
          </div>
          <div class="modal-body">
            <div class="modal_form">
              <form role="form" class="form-horizontal"  id="add_ware" enctype="multipart/form-data" action="" method="post">
                	<div class="save_distinctIds" style="display:none"></div>
                <div class="form-group">
                  <label class="col-sm-6 control-label">
                    <span class="text-danger">*</span>仓库名称：
                  </label>
                  <div class="col-sm-13">
                  	<input type="hidden" id="updateWareId" name="wareId"/>
                    <input type="text" class="form-control required"  minlength="3" maxlength="15" name="wareName" id="ware_name" onBlur="checkExists('checkWareName','wareName',this)">
                  <span id="wtips"></span>
                  </div>
                  <div class="col-sm-5">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-6 control-label">
                    <span class="text-danger"></span>备注：
                  </label>
                  <div class="col-sm-13">
                    <input type="text" class="form-control"  name="wareRemark" id="ware_remark">
                  </div>
                  <div class="col-sm-5">
                  </div>
                </div>
               <!--  <div class="form-group">
                  <label class="col-sm-6 control-label">
                    <span class="text-danger">*</span>管理员：
                  </label>
                  <div class="col-sm-13">
                    <select class="required" data-live-search="true" name="wareAdmin" id="ware_admin" > 
                    </select>
                  </div>
                  <div class="col-sm-5">
                  </div>
                </div> -->
                <div class="form-group">
                  <label class="col-sm-6 control-label">
                    <span class="text-danger">*</span>仓库地址：
                  </label>
                  <div class="col-sm-13">
                    <input type="text" class="form-control required"  name="wareAddress" id="ware_address" >
                  </div>
                  <div class="col-sm-5">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-6 control-label">
                    <span class="text-danger"></span>唯一标识：
                  </label>
                  <div class="col-sm-13">
                    <input type="text" class="form-control" name="identifyId" id="wareidentifyId" >
                  </div>
                  <div class="col-sm-5">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-6 control-label">
                    <span class="text-danger">*</span>覆盖地区：
                  </label>
                  <div class="col-sm-13">
                    <div class="area_choose">
                      <ul id="treeDemo" class="ztree"></ul>
                    </div>
                  </div>
                  <div class="col-sm-5">
                    <input type="hidden" class="required" id="areaIds"/>
                  </div>
                </div>
              </form>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="saveWare();">保存仓库</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
    <script src="<%=basePath %>js/select2.min.js"></script>
    <script>
    $(function(){
        $("#add_ware").validate();
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

      /* 选择规格值 */
      $('.spec_set input').change(function(){
        if($(this).is(':checked')){
          $(this).parent().parent().next().slideDown('fast');
        }
        else {
          $(this).parent().parent().next().slideUp('fast');
        }
      });

      /* 下面是表单里面的填写项提示相关的 */
      $('.xiaoshoujia').popover({
        content : '此价格只用于显示，以商品定价为商品销售价',
        trigger : 'hover'
      });
      $('.pptpcc').popover({
        content : '建议图片尺寸120 × 40 px',
        trigger : 'hover'
      });

      /* 双击编辑分类 */
      $('.cate_item').dblclick(function(){
        $('#cateEdit').modal('show');
      });


    });
    
    /* 下面是关于树形菜单 */
    var setting = {
      check: {
        enable: true,
        chkboxType : {"Y":"ps","N":"ps"}
      },
      data: {
        simpleData: {
          enable: true
        }
      },
      callback: {
			onCheck: onCheck
		},
		view:{
			showIcon:false
			}
    };
    

	 
  	$.get("queryAllManagerForWareHouse.htm?CSRFToken=${token}",function(data){
		for(var i = 0; i < data.length;i++){
	      			$("#ware_admin").append("<option value='"+data[i].id+"'>"+data[i].username+"</option>");
	      			
	      		}  
	      		   /* 为选定的select下拉菜单开启搜索提示 */
	              $('select[data-live-search="true"]').select2();
	              /* 为选定的select下拉菜单开启搜索提示 END */
	 });
  	
	   
  function addwarehouse(){
	        $("#addWarehouse").find(".modal-title").text("添加仓库"); 
	        $("#add_ware").attr('action','saveWareHouse.htm?CSRFToken=${token }');
	        $("#updateWareId").val("");
		    $("#ware_name").val("");
		    $("#ware_remark").val("");
		    $("#wareidentifyId").val("");
		    $("#ware_admin").val("");
		    $('select[data-live-search="true"]').select2();
		    $("#ware_address").val("");
		    $(".save_distinctIds").html("");
		    $("#areaIds").val('');
  	$.get("queryAllManagerForWareHouse.htm?CSRFToken=${token}",function(data){
		for(var i = 0; i < data.length;i++){
	      			$("#ware_admin").append("<option value='"+data[i].id+"'>"+data[i].username+"</option>");
	      			
	      		}  
	      		   /* 为选定的select下拉菜单开启搜索提示 */
	              $('select[data-live-search="true"]').select2();
	              /* 为选定的select下拉菜单开启搜索提示 END */
	 });
	

	var zNodes = new Array();
  /*加载省份*/
	$.get("getAllProvince.htm?CSRFToken=${token}",function(data){
		for(var i = 0;i<data.length;i++){
			var node = {
                  id : "p"+data[i].provinceId, pId : 0 , name : data[i].provinceName, open : false 
              };
			
          zNodes.push(node);
		}
		/*加载城市*/
		$.get("selectallcitydistrict.htm?CSRFToken=${token}",function(data2){
			for(var i = 0;i<data2.length;i++){
				var node2 = {
	                    id : "c"+data2[i].cityId, pId : "p"+data2[i].provinceId , name : data2[i].cityName, open : false 
	                };
	            zNodes.push(node2);
			}
			/*加载区县*/
			$.get("getallwarehousedistrict.htm?CSRFToken=${token}",function(data3){
				for(var i = 0;i<data3.length;i++){
					var node3 = {
		                    id : "d"+data3[i].districtId, pId : "c"+data3[i].cityId , name : data3[i].districtName, open : false ,chkDisabled:data3[i].chkDisabled
		                };
		            zNodes.push(node3);
				}
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

			
			
			
			});  
		});
	
	});
  
      $('#addWarehouse').modal('show');
  }
  
	function onCheck(e, treeId, treeNode){
	 	$(".save_distinctIds").html("");
	 	$("#areaIds").val();
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		nodes = zTree.getCheckedNodes(true);
		var s = '';
		if(nodes != null && nodes.length>0){
			for(var i = 0; i< nodes.length;i++){
				if(nodes[i].id.indexOf("d") != -1){
					$(".save_distinctIds").append("<input type='hidden' name='districtIds' value='"+nodes[i].id.replace("d","")+"' />");
					s+=nodes[i].id.replace("d","")+",";
				}
			}
		} 
		$("#areaIds").val(s);
	}
    
    
    
    var flag = false;
    function checkExists(url,objName,obj){
        $.post(url+".htm?"+objName+"="+encodeURI(encodeURI($(obj).val()))+"&wareId="+$("#updateWareId").val(),function(data){
			if(data==false){
			  var htm='<label for="ware_name" generated="true" class="error">该名称已经存在</label>';
			  $("#wtips").html(htm);
			  flag = false;
			}else{
			  $("#wtips").html(''); 
			  flag = true;
			}
		});
    }
    var num=0;
    function saveWare(){
       
        
        if($("#add_ware").valid()&&num==0){
            $.post("checkWareName.htm?wareName="+encodeURI(encodeURI($("#ware_name").val()))+"&wareId="+$("#updateWareId").val(),function(data){
    			if(data==false){
    			  var htm='<label for="ware_name" generated="true" class="error">该名称已经存在</label>';
    			  $("#wtips").html(htm);
    			  flag = false;
    			}else{
                    num+=1;
    			  $("#wtips").html(''); 
    			  flag = true;
    			  $("#add_ware").submit();
    			}
    		});
           
        }
       
    }
        
    
    function delWare(wareId){
       showDeleteOneConfirmAlert('delWareHouse.htm?CSRFToken=${token}&wareId='+wareId,'确定要要删除此仓库吗？'); 
    }
    
    function onedit(wareId){
        $("#addWarehouse").find(".modal-title").text("修改仓库"); 
        $("#add_ware").attr('action','updateWareHouse.htm?CSRFToken=${token }');

    	var zNodes = new Array();
      /*加载省份*/
    	$.get("getAllProvince.htm?CSRFToken=${token}",function(data){
    		for(var i = 0;i<data.length;i++){
    			var node = {
                      id : "p"+data[i].provinceId, pId : 0 , name : data[i].provinceName, open : false 
                  };
    			
              zNodes.push(node);
    		}
    		/*加载城市*/
    		$.get("selectallcitydistrict.htm?CSRFToken=${token}",function(data2){
    			for(var i = 0;i<data2.length;i++){
    				var node2 = {
    	                    id : "c"+data2[i].cityId, pId : "p"+data2[i].provinceId , name : data2[i].cityName, open : false 
    	                };
    	            zNodes.push(node2);
    			}
    			/*加载区县*/
    			$.get("getallwarehousedistrict.htm?CSRFToken=${token}",function(data3){
    				for(var i = 0;i<data3.length;i++){
    					var node3 = {
    		                    id : "d"+data3[i].districtId, pId : "c"+data3[i].cityId , name : data3[i].districtName, open : false ,chkDisabled:data3[i].chkDisabled
    		                };
    		            zNodes.push(node3);
    				}
    			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

       	     $.get("queryWareHouseByWareId.htm?CSRFToken=${token}"+"&wareId="+wareId,function(data){
       	            $("#updateWareId").val(data.wareId);
       			    $("#ware_name").val(data.wareName);
       			    $("#ware_remark").val(data.wareRemark);
       			    $("#wareidentifyId").val(data.identifyId);
       			    $("#ware_admin").val(data.wareAdmin);
       			    $('select[data-live-search="true"]').select2();
       			    $("#ware_address").val(data.wareAddress);
       			    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
       			    var s = '';
       			      for(var i=0;i<data.distincts.length;i++){
       			    	if(data.distincts[i]!=null||data.distincts[i].cityId!=null){
       			    		var treeNode= zTree.getNodeByParam("id", "d"+data.distincts[i].cityId);
       			    		zTree.setChkDisabled(treeNode, false, false, true); 
       					    zTree.checkNode(treeNode,true,true);
       						$(".save_distinctIds").append("<input type='hidden' name='districtIds' value='"+data.distincts[i].cityId+"' />");
       						s+=data.distincts[i].cityId+",";
       			    	}
       			    	
       			    } 
       			   $("#areaIds").val(s);
       			    
       			  
       	        });
       		
    			
    			
    			});  
    		});
    	
    		
    		
    		
    		
    		
    		
    		
    	});
      
    	  $('#addWarehouse').modal('show');
   
    }
    
    
    function delallwarehouse(){
        showDeleteBatchConfirmAlert('batchdel_ware','tagCheck','确定要删除所选中的仓库吗？');
    }
    </script>
  </body>
</html>
