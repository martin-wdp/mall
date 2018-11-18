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
    <title></title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="<%=basePath %>stylesheet" href="css/font-awesome.min.css">
    <link href="<%=basePath %>/iconfont/iconfont.css" rel="stylesheet">
      <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>/css/style.css" rel="stylesheet">
    
    
    
    
  </head>
<body>
   <!-- 引用头 -->
   <jsp:include page="../../page/header.jsp"></jsp:include>
     
    <div class="container-fluid page_body">
      <div class="row">
      
        <jsp:include page="../../page/left.jsp"></jsp:include>
            
        <div class="col-lg-20 col-md-19 col-sm-18 main">
      
          
          <!-- 需要替换的位置 -->
            <div class="main_cont">
             <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

            <div class="common_data p20">
              <div class="filter_area">
                <form role="form" class="form-inline" action="initAuthority.htm" method="post" id="searchForm">
                  <input type="hidden" value="searchForm" id="formId">
                  <input type="hidden" value="initAuthority.htm" id="formName">
                  <div class="form-group">
                    <div class="input-group"> 
                      <span class="input-group-addon">角色名称</span>
                      <input type="text" class="form-control" name="designation" value="${pageBean.objectBean.designation}">
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
                  <button type="button" class="btn btn-info" onclick="addAuthority();">
                    <i class="glyphicon glyphicon-plus"></i> 添加
                  </button>
                  <button type="button" class="btn btn-info" onclick="batchAuthrity();">
                    <i class="glyphicon glyphicon-trash"></i> 删除
                  </button>
                  <button type="button" class="btn btn-info" onclick="refresh();">
                    <i class="glyphicon glyphicon-refresh"></i> 刷新
                  </button>
                </div>
                <div class="clr"></div>
              </div>
              <form action="deleteAllAuthority.htm" id="delForm" method="post">
              <input type="hidden" value="${token}" name="CSRFToken"/>
              <table class="table table-striped table-hover">
                <thead>
                <tr>
                  <th>序号</th>
                  <th width="50"><input type="checkbox" name="authorids" onclick="allunchecked(this,'authorid');"></th>
                  <th>角色名称</th>
                  <th width="150">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageBean.list}" var="author" varStatus="i">
                <tr>
                  <td width="50">${i.count}</td>
                  <td width="50"><input type="checkbox" name="authorid"  value="${author.id }"></td>
                  <td>${author.designation }</td>
                  <td>  
                    <div class="btn-group">
                      <button type="button" class="btn btn-default" onclick="openEdit(${author.id });">编辑</button>
                      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                      </button>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript:void(0);" onclick="delauth(${author.id });">删除</a></li>
                      </ul>
                    </div>
                  </td>
                </tr>
                </c:forEach>
               
                </tbody>
              </table>
		</form>
				<c:import url="../../page/searchPag.jsp">
				     <c:param name="pageBean" value="${pageBean }"/>
				     <c:param name="path" value="../../"></c:param>
				</c:import>
  
            </div>  

          </div> 
             <!-- 需要替换的位置 结束-->
          
          
          
        </div>
      </div>
    </div>

    <div id="addAuth" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加角色</h4>
          </div>
          <form id="addForm" method="post">
          	<input type="hidden" value="" name="id" id="id"/>
          <div class="modal-body">
              <div class="common_form">
              <div class="form-horizontal">
                <div class="form-group">
                  <label for="input1" class="control-label col-sm-6">
                    <span class="text-danger">*</span>角色名称：
                  </label>
                  <div class="col-sm-15">
                    <input type="text" id="desi" class="form-control required" name="designation">
                    <span id="desitip"></span>
                  </div>
                </div>
                <div class="form-group">
                  <label for="input1" class="control-label col-sm-6">
                    <span class="text-danger">*</span>所选菜单：
                  </label>
                  <div class="col-sm-15">
                    <input type="text" id="catName" class="form-control required" readonly="true">
                  </div>
                </div>
                 <div class="form-group"> 
                   <label for="input1" class="control-label col-sm-7">
                    <span class="text-danger">&nbsp;</span>
                  </label>
	                 <div class="power_choose container-fluid">
		              <div class="row">
		                <div class="col-xs-4">
		                  <div class="power_cate" style="height:300px;width:300px;">
		                    <ul id="treeDemo" class="ztree"></ul>
		                  </div>
		                  <input type="hidden" id="catId" name="catId" />
		                </div> 
		                 
		              </div>
		            </div>
                </div>
                
                <div class="form-group">
                  <label class="control-label col-sm-6">
                    <span class="text-danger"></span>备注：
                  </label>
                  <div class="col-sm-15">
                    <textarea class="form-control" name="characterization" id="characterization" rows="5" maxlength="255"></textarea>
                  </div>
                  <div class="col-sm-3">
                  </div>
                </div>
              </div>
            </div>
          
          </div>
          
          </form>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="doAddAuth();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->




 <div id="editAuth" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">编辑角色</h4>
          </div>
          <form id="editForm" method="post">
          	<input type="hidden" value="" name="id" id="editid"/>
          <div class="modal-body">
              <div class="common_form">
              <div class="form-horizontal">
                <div class="form-group">
                  <label for="input1" class="control-label col-sm-6">
                    <span class="text-danger">*</span>角色名称：
                  </label>
                  <div class="col-sm-15">
                    <input type="text" id="editdesi" class="form-control required" name="designation">
                    <span id="editdesitip"></span>
                  </div>
                </div>
                <div class="form-group">
                  <label for="input1" class="control-label col-sm-6">
                    <span class="text-danger">*</span>所选菜单：
                  </label>
                  <div class="col-sm-15">
                    <input type="text" id="editcatName" class="form-control required" readonly="true">
                  </div>
                </div>
                 <div class="form-group"> 
                   <label for="input1" class="control-label col-sm-7">
                    <span class="text-danger">&nbsp;</span>
                  </label>
	                 <div class="power_choose container-fluid">
		              <div class="row">
		                <div class="col-xs-4">
		                  <div class="power_cate" style="height:300px;width:300px;">
		                    <ul id="edittreeDemo" class="ztree"></ul>
		                  </div>
		                  <input type="hidden" id="editcatId" name="catId" />
		                </div> 
		                 
		              </div>
		            </div>
                </div>
                
                <div class="form-group">
                  <label class="control-label col-sm-6">
                    <span class="text-danger"></span>备注：
                  </label>
                  <div class="col-sm-15">
                    <textarea class="form-control" name="characterization" id="editcharacterization" rows="5"></textarea>
                  </div>
                  <div class="col-sm-3">
                  </div>
                </div>
              </div>
            </div>
          
          </div>
          
          </form>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="doEditAuth();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
<input type="hidden" value="${token }" id="hi_token"/>
<input type="hidden" value="${managerFlag}" id="hi_managerId"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
 <script type="text/javascript">

 var authFlag = false;
	$("#addForm").validate();
	$("#editForm").validate();
 /* 下面是关于树形菜单 */
	var setting = {
			 check: {
					enable: true,
					chkStyle: "checkbox",
			        chkboxType: {"Y" : "ps", "N" : "ps" }
				},
				callback: {
					onCheck: onCheckForBusiness
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				view:{
					showIcon:false
					}
			};
 
 
	/* 下面是关于树形菜单 */
	var setting1 = {
			 check: {
					enable: true,
					chkStyle: "checkbox",
			        chkboxType: {"Y" : "ps", "N" : "ps" }
				},
				callback: {
					onCheck: onCheckForBusiness1
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				view:{
					showIcon:false
					}
			};
 
	
	
	//获取数据，根据父id做级联
	function onCheckForBusiness(e, treeId, treeNode){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		nodes = zTree.getCheckedNodes(true);
		id = "";
		name = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
		    id += nodes[i].id + ",";
			name += nodes[i].name + ",";
		}
		if (name.length > 0 ) name = name.substring(0, name.length-1);
		if (id.length > 0 ) id = id.substring(0, id.length-1);
		$("#catName").val('');
		$("#catName").val(name);
		$("#catId").val(id); 
	}
	
	
	//获取数据，根据父id做级联
	function onCheckForBusiness1(e, treeId, treeNode){
		var zTree = $.fn.zTree.getZTreeObj("edittreeDemo");
		nodes = zTree.getCheckedNodes(true);
		id = "";
		name = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
		    id += nodes[i].id + ",";
			name += nodes[i].name + ",";
		}
		if (name.length > 0 ) name = name.substring(0, name.length-1);
		if (id.length > 0 ) id = id.substring(0, id.length-1);
		$("#editcatName").val('');
		$("#editcatName").val(name);
		$("#editcatId").val(id); 
	}
	
	
  function addAuthority(){
  	$("#desi").val('');
	$("#id").val('');
	$("#characterization").val('');
	$("#catName").val('');
	$("#catId").val('');
      $('#addForm').attr('action', 'addAuthority.htm?CSRFToken='+$("#hi_token").val());
      loadMenu(null);
      $("#addAuth").modal('show');
      $(".modal-title").text('添加角色');
      
  } 


  
  
  function openEdit(authId){
  	$("#editdesi").val('');
	$("#editid").val('');
	$("#editcharacterization").val('');
	$("#editcatName").val('');
	$("#editcatId").val('');
      $.post("queryAuthorityById.htm?id="+authId+"&CSRFToken="+$("#hi_token").val(),function(data){
			$("#editdesi").val(data.designation);
			$("#editid").val(data.id);  
			$("#editcharacterization").val(data.characterization);
		    $('#editForm').attr('action', 'updateAuthority.htm?CSRFToken='+$("#hi_token").val());
	        loadMenuedit(authId);
	        $("#editAuth").modal('show');
		});
     
  }
  
  function loadMenu(authId){
      var ids="";
      var names="";
		var url = "";
		if($("#hi_managerId").val()=='1'){
			
			url = "queryAllMenu.htm";
		}else{
			url = "queryAllMenuByLogin.htm";
			
		}
		
		$.post(url+"?CSRFToken="+$("#hi_token").val(),function(data){
		    
		    if(authId!=null){
		        $.post("queryAuthorityByAId.htm?id="+authId+"&CSRFToken="+$("#hi_token").val(),function(menuIds){
					var zNodes=new Array();
					for(var i=0;i<data.length;i++){
						var node=null; 
						  if(data[i].id==0){
					          node={
										id:data[i].id,pId:data[i].parentId, name:data[i].designation, open:true
							  };
					      }else{
					          node={
										id:data[i].id,pId:data[i].parentId, name:data[i].designation, open:false
							  };
					      }
						for(var j=0;j<menuIds.length;j++){
							if(menuIds[j].id==data[i].id){
								ids+=menuIds[j].id+",";
								node.checked=true;
								names+=data[i].designation+",";  
							}
						}
						zNodes.push(node);
					}
					$("#catId").val(ids.substring(0, ids.length-1));
					$("#catName").val(names.substring(0, names.length-1));
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				});
		    }else{
		        
		        var zNodes=new Array();
				  for(var i=0;i<data.length;i++){
				      var node=null;
				      if(data[i].id==0){
				          node={
									id:data[i].id,pId:data[i].parentId, name:data[i].designation, open:true
						  };
				      }else{
				          node={
									id:data[i].id,pId:data[i].parentId, name:data[i].designation, open:false
						  };
				      }
					  zNodes.push(node);
				  }
				  $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		    }
			
		});
		
	}
  
  
  
  
  function loadMenuedit(authId){
      var ids="";
      var names="";
		var url = "";
		if($("#hi_managerId").val()=='1'){
			
			url = "queryAllMenu.htm";
		}else{
			url = "queryAllMenuByLogin.htm";
			
		}
		
		$.post(url+"?CSRFToken="+$("#hi_token").val(),function(data){
		    
		    if(authId!=null){
		        $.post("queryAuthorityByAId.htm?id="+authId+"&CSRFToken="+$("#hi_token").val(),function(menuIds){
					var zNodes=new Array();
					for(var i=0;i<data.length;i++){
						var node=null;
						  if(data[i].id==0){
					          node={
										id:data[i].id,pId:data[i].parentId, name:data[i].designation, open:true
							  };
					      }else{
					          node={
										id:data[i].id,pId:data[i].parentId, name:data[i].designation, open:false
							  };
					      }
						for(var j=0;j<menuIds.length;j++){
							if(menuIds[j].id==data[i].id){
								ids+=menuIds[j].id+",";
								node.checked=true;
								names+=data[i].designation+",";  
							}
						}
						zNodes.push(node);
					}
					$("#editcatId").val(ids.substring(0, ids.length-1));
					$("#editcatName").val(names.substring(0, names.length-1));
					$.fn.zTree.init($("#edittreeDemo"), setting1, zNodes);
				});
		    }else{
		        
		        var zNodes=new Array();
				  for(var i=0;i<data.length;i++){
				      var node=null;
				      if(data[i].id==0){
				          node={
									id:data[i].id,pId:data[i].parentId, name:data[i].designation, open:true
						  };
				      }else{
				          node={
									id:data[i].id,pId:data[i].parentId, name:data[i].designation, open:false
						  };
				      }
					  zNodes.push(node);
				  }
				  $.fn.zTree.init($("#edittreeDemo"), setting1, zNodes);
		    }
			
		});
		
	}
  
  

  function doAddAuth(){
      if($("#addForm").valid()){
                  var url = 'checkauthexist.htm?isnew=1&&authName='+$("#desi").val()+"&CSRFToken="+$("#hi_token").val();
          $.ajax({
				         type: 'post',
				         url:url,
				         async:false,
				         success: function(data) {
				        	 if (data > 0){
				        		$("#desitip").html('<label for="desi" generated="true" class="error" style="display: inline-block;">角色名称重复</label>');
						     	$("#desi").addClass('error'); 
				        	 }else{
				        	   $("#desitip").html('');
				        	   $("#desi").removeClass('error'); 
				        	   $("#addForm").submit();
						      }
				         }
					 });
  					
      }
    
  }
  

  


  function doEditAuth(){
      if($("#editForm").valid()){
                  var url = 'checkauthexist.htm?isnew=1&&authName='+encodeURI(encodeURI($("#editdesi").val()))+"&CSRFToken="+$("#hi_token").val();
					$.ajax({
				         type: 'post',
				         url:url,
				         async:false,
				         success: function(data) { 
				        	 if (data > 1){
				        		$("#editdesitip").html('<label for="desi" generated="true" class="error" style="display: inline-block;">角色名称重复</label>');
						     	$("#editdesi").addClass('error'); 
				        	 }else{
				        	   $("#editdesitip").html('');
				        	   $("#editdesi").removeClass('error'); 
				        	   $("#editForm").submit();
						      }
				         }
					 });
           		
      }
    
  }
  
  
  function delauth(authId){
      showDeleteOneConfirmAlert('deleteAuthority.htm?CSRFToken=${token}&authId='+authId,'确定要删除此角色吗？');    
  }
  
  function batchAuthrity(){
      showDeleteBatchConfirmAlert('delForm','authorid','确定要删除所选中的角色吗？');
  }
  

  </script>
  </body>
</html>
