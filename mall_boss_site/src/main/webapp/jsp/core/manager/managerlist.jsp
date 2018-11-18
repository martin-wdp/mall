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
    <title>管理员列表</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
     <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
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
   <jsp:include page="../../page/header.jsp"></jsp:include>
     
    <div class="page_body container-fluid">
      <div class="row">

   		<jsp:include page="../../page/left.jsp"></jsp:include>

        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <div class="main_cont">
              <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild}<small>(共${pageBean.rows }条)</small></h2>

            <div class="common_data p20">
              <div class="filter_area">
                 <form role="form" class="form-inline" action="initManager.htm" method="post" id="searchForm">
                  <input type="hidden" value="searchForm" id="formId">
                  <input type="hidden" value="initManager.htm" id="formName">
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">管理员用户名</span>
                      <input type="text" class="form-control" name="username"  value="${pageBean.objectBean.username }" >
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
                  <button type="button" class="btn btn-info" onclick="addmanager();">
                    <i class="glyphicon glyphicon-plus"></i> 添加
                  </button>
                  <button type="button" class="btn btn-info" onclick="batchManager();">
                    <i class="glyphicon glyphicon-trash"></i> 删除
                  </button>
                  <button type="button" class="btn btn-info" onclick="refresh();">
                    <i class="glyphicon glyphicon-refresh"></i> 刷新
                  </button>
                </div>
                <div class="clr"></div>
              </div>
              
              <form action="deleteallmanager.htm" method="post" id="delForm">
              	<input type="hidden" name="CSRFToken" value="${token}">
              <table class="table table-striped table-hover">
                
                <thead>
                <tr>
                  <th width="25"><input type="checkbox"  onclick="allunchecked(this,'managerid');"></th>
                  <th>管理员用户名</th>
                  <th>是否启用</th>
                  <th class="w100">最后登录时间</th>
                  <th width="150">操作</th>
                </tr> 
                </thead>
                <tbody>
              <c:forEach items="${pageBean.list}" var="manager" varStatus="i">  
                <tr>
                  <td width="50"><input type="checkbox" name="managerid"  value="${manager.id }"></td>
                  <td>${manager.username }</td>
                  <td>
                 		<c:if test="${manager.flag==0}">
						<a href="javascript:;" class="label label-success">是</a>
						</c:if>
						<c:if test="${manager.flag!=0}">
						<a href="javascript:;" class="label label-default">否</a>
						</c:if>
                  </td>
                  <td>  <fmt:formatDate value="${manager.loginTime }" pattern="yyyy-MM-dd HH:mm:ss" var="logindate" />${logindate }</td>
                  <td>
                    <div class="btn-group">
                      <button type="button" class="btn btn-default" onclick="openedit(${manager.id });">编辑</button>
                      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                      </button>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript:void(0);" onclick="delmanager(${manager.id});">删除</a></li> 
                      </ul>
                    </div>
                  </td>
                </tr>
               </c:forEach>
                </tbody>
              </table>
      </form>
              <div class="table_foot">
              	<c:import url="../../page/searchPag.jsp">
				     <c:param name="pageBean" value="${pageBean }"/>
				     <c:param name="path" value="../../"></c:param>
				</c:import>
              </div>

            </div>

          </div>
          
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="addManager"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加管理员</h4>
          </div>
          <div class="modal-body">
            <form role="form" class="form-horizontal" id="addForm" method="post" enctype="multipart/form-data">
              <div class="form-group" id="user">
                <label class="control-label col-sm-5"> <span class="text-danger">*</span>用户名：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-16">
                <input type="hidden" name="id" id="id" value=""  />
                  <input type="text" class="form-control required" name="username" id="username">
                    <span id="usertip"></span>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">头像：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <img height="50" alt="" src="" id="img" style="height:50px;width:50px;"><input style="margin-left:5px;" type="button" id="choose" value="选择"/>
                	<input type="hidden" name="photoImg" id="photoImg"/>
                </div>
                
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">手机号码：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-16">
                  <input type="text" class="form-control mobile" name="mobile" id="mobile">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">是否启用：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-16">
                  <label class="radio-inline">
                    <input type="radio" name="flag" id="open1" value="0" checked> 是
                  </label>
                  <label class="radio-inline">
                    <input type="radio" name="flag" id="open2" value="1"> 否
                  </label>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5"> <span class="text-danger">*</span>角色：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-16">
                  <select class="w200" data-live-search="true" name="authorityId" id="auth_list_s">
                    
                  </select>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="submanager();">保存</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

 <!-- Modal -->
 <div class="modal fade" id="editManager"  role="dialog">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                 <h4 class="modal-title">编辑管理员</h4>
             </div>
             <div class="modal-body">
                 <form role="form" class="form-horizontal" id="editForm" method="post" enctype="multipart/form-data">
                     <div class="form-group" id="edituser">
                         <label class="control-label col-sm-5"> <span class="text-danger">*</span>用户名：</label>
                         <div class="col-sm-1"></div>
                         <div class="col-sm-16">
                             <input type="hidden" name="id" id="editid" value=""  />
                             <input type="text" class="form-control required" name="username" id="editusername">
                             <span id="editusertip"></span>
                         </div>
                     </div>
                     <div class="form-group">
                         <label class="control-label col-sm-5">头像：</label>
                         <div class="col-sm-1"></div>
                         <div class="col-sm-12">
                             <img height="50" alt="" src="" id="editimg" style="height:50px;width:50px;"><input style="margin-left:5px;" type="button" id="editchoose" value="选择"/>
                             <input type="hidden" name="photoImg" id="editphotoImg"/>
                         </div>

                     </div>
                     <div class="form-group">
                         <label class="control-label col-sm-5">手机号码：</label>
                         <div class="col-sm-1"></div>
                         <div class="col-sm-16">
                             <input type="text" class="form-control mobile" name="mobile" id="editmobile">
                         </div>
                     </div>
                     <div class="form-group">
                         <label class="control-label col-sm-5">是否启用：</label>
                         <div class="col-sm-1"></div>
                         <div class="col-sm-16">
                             <label class="radio-inline">
                                 <input type="radio" name="flag" id="editopen1" value="0" checked> 是
                             </label>
                             <label class="radio-inline">
                                 <input type="radio" name="flag" id="editopen2" value="1"> 否
                             </label>
                         </div>
                     </div>
                     <div class="form-group">
                         <label class="control-label col-sm-5"> <span class="text-danger">*</span>角色：</label>
                         <div class="col-sm-1"></div>
                         <div class="col-sm-16">
                             <select class="w200" data-live-search="true" name="authorityId" id="editauth_list_s">

                             </select>
                         </div>
                     </div>
                 </form>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-primary" onclick="subEditManager();">保存</button>
                 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
             </div>
         </div>
     </div>
 </div>

 <input type="hidden" value="${token }" id="hi_token"/>
 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    <script>
      $(function(){
      	$("#addForm").validate();
        /* 为选定的select下拉菜单开启搜索提示 */
     
        
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

          $("#editchoose").click(function(){
              i=1;
              art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=10000', {
                  lock: true,
                  opacity:0.3,
                  width: '900px',
                  height: '400px',
                  title: '选择图片'
              });
          });
      });
      
      function addmanager(){
            $("#username").val('');
			$("#mobile").val('');
			$("#img").attr("src",'');
			$("#photoImg").val('');
		 	$("#id").val('');
		 	$("input[name=flag]:eq(0)").attr("checked",'checked');
		 	$('#addManager').find('.modal-title').text('添加管理员');
      	    $('#addForm').attr('action', 'addManager.htm?CSRFToken=${token}');
            loadAuthority();
            if($("#pass1").length==0){
            	  addgrouphtm();
            }
        	$("#addForm").validate();
            $('#addManager').modal('show');
      }
     
      
     
      function addgrouphtm(){
          var htm=' <div class="form-group" id="pass1">';
             htm+=' <label class="control-label col-sm-5"> <span class="text-danger">*</span>密码：</label>';
              htm+='<div class="col-sm-1"></div>';
              htm+=' <div class="col-sm-16">';
              htm+='   <input type="password" class="form-control required"  maxlength="16" minlength="6"  name="userkey" id="password" >';
              htm+='  </div>';
              htm+=' </div>';
              htm+='<div class="form-group" id="pass2">';
              htm+=' <label class="control-label col-sm-5"> <span class="text-danger">*</span>重复密码：</label>';
              htm+='  <div class="col-sm-1"></div>';
              htm+=' <div class="col-sm-16">';
              htm+='   <input type="password" class="form-control required" equalTo="#password" name="repassword" id="repassword">';
               htm+='  </div>';
              htm+='  </div>';
              $("#user").after(htm);
              
      }
      var num=0;
      function submanager(){
          if($("#addForm").valid()){
              $.ajax({
                  type: 'post',
                  url:'checkManagerExist.htm?username='+$("#username").val()+"&CSRFToken=${token}",
                  async:false,
                  success: function(data) {
                          if (data!=null&&data!='') {
                                  $("#usertip").html('<label for="username" generated="true" class="error" style="display: inline-block;">管理员名称已存在</label>');
                                  $("#username").addClass('error');
                          } else {
                              $("#usertip").html('');
                              $("#username").removeClass('error');
                              $("#addForm").submit();
                          }
                  }
              });
          }
      }

      function subEditManager(){
          var managerId=$("#editid").val();
          if($("#editForm").valid()){
              $.ajax({
                  type: 'post',
                  url:'checkManagerExist.htm?username='+$("#editusername").val()+"&CSRFToken=${token}",
                  async:false,
                  success: function(data) {
                      if (data != null && data != "") {
                              if(managerId == data.id){
                                  $("#editusertip").html('');
                                  $("#editusername").removeClass('error');
                                  $("#editForm").submit();
                              }else{
                                  $("#editusertip").html('<label for="username" generated="true" class="error" style="display: inline-block;">管理员名称已存在</label>');
                                  $("#editusername").addClass('error');
                              }
                      } else {
                          $("#editusertip").html('');
                          $("#editusername").removeClass('error');
                          $("#editForm").submit();
                      }

                  }
              });
          }
      }
      function saveChoooseImage(url) {
          if(typeof (url) != 'string') {    
              url = url[0];
          }
          if(url.indexOf(',')!=-1){
              url=url.substring(0,url.indexOf(','));  
          }
          $("#img").attr("src",url);
          $("#photoImg").val(url);

      }

      function saveChoooseImage(url) {
          if(typeof (url) != 'string') {
              url = url[0];
          }
          if(url.indexOf(',')!=-1){
              url=url.substring(0,url.indexOf(','));
          }
          $("#img").attr("src",url);
          $("#photoImg").val(url);
          $("#editimg").attr("src",url);
          $("#editphotoImg").val(url);

      }
      
      
  	/**加载权限列表*/
  	function loadAuthority(){
  	 	$.post("queryAllAuthority.htm?CSRFToken=${token}",function(data){
  			var options = "";
  			for( var i=0; i<data.length; i++){
  				var auth=data[i];
  				options +=  "<option value='"+auth.id+"'>"+auth.designation+"</option>"; 
  			}
  			$('#auth_list_s').html(options);
  			 /* 为选定的select下拉菜单开启搜索提示 END */
  			 $('select[data-live-search="true"]').select2();
  		}); 
  	  
      
  	}

      /**加载编辑权限列表*/
      function loadEditAuthority(){
          $.post("queryAllAuthority.htm?CSRFToken=${token}",function(data){
              var options = "";
              for( var i=0; i<data.length; i++){
                  var auth=data[i];
                  options +=  "<option value='"+auth.id+"'>"+auth.designation+"</option>";
              }
              $('#editauth_list_s').html(options);
              /* 为选定的select下拉菜单开启搜索提示 END */
              $('select[data-live-search="true"]').select2();
          });


      }
  	
  	function openedit(authorId){
  	    $("#editusername").val('');
		$("#editmobile").val('');
		$("#editimg").attr("src",'');
		$("#editphotoImg").val('');
	 	$("#editid").val('');
	 	$("input[name=flag]:eq(0)").attr("checked",'checked'); 
		$('#editForm').attr('action', 'updateManager.htm?CSRFToken=${token}');
  	 	 doSearchManager(authorId);
        loadEditAuthority();
  	 	$('#editManager').modal('show');
  	 	$('#editid').val(authorId);

  	 	$("#pass1").remove();
  	 	$("#pass2").remove();
  		$("#editForm").validate();
  	}
  	
  	
  	function doSearchManager(id){
		$.post("queryManagerById.htm?id="+id+"&CSRFToken=${token}",function(data){
			$.post("queryAuthorByManagerId.htm?mid="+data.id+"&CSRFToken=${token}",function(auth){
				$("#editusername").val(data.username);
				$("#editmobile").val(data.mobile);
				$("#editimg").attr("src",data.photoImg);
				$("#editphotoImg").val(data.photoImg);
			 	 $("#editid").val(data.id);
			 	$("input[name=flag]:eq("+data.flag+")").attr("checked",'checked'); 
			 	$("#editauth_list_s").val(auth.authorityId);
			    $('select[data-live-search="true"]').select2();   
			});
		});
	}

      function doEditSearchManager(id){
          $.post("queryManagerById.htm?id="+id+"&CSRFToken=${token}",function(data){
              $.post("queryAuthorByManagerId.htm?mid="+data.id+"&CSRFToken=${token}",function(auth){
                  $("#editusername").val(data.username);
                  $("#editmobile").val(data.mobile);
                  $("#editimg").attr("src",data.photoImg);
                  $("#editphotoImg").val(data.photoImg);
                  $("#editid").val(data.id);
                  $("input[name=flag]:eq("+data.flag+")").attr("checked",'checked');
                  $("#editauth_list_s").val(auth.authorityId);
                  $('select[data-live-search="true"]').select2();
              });
          });
      }
  	
  	function delmanager(id){
  	   showDeleteOneConfirmAlert('deleteManager.htm?CSRFToken=${token}&managerId='+id,'确定要删除此管理员吗？');    
  	}
  	
    function batchManager(){
        showDeleteBatchConfirmAlert('delForm','managerid','确定要删除所选中的管理员吗？');
    }
    
    </script>
  </body>
</html>
