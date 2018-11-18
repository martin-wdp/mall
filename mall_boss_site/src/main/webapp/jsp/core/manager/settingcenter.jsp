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
    <title>控制面板</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link rel="<%=basePath %>stylesheet" href="css/font-awesome.min.css">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet"> 
	
  </head>
  <body>
   
      <!-- 引用头 -->
   <jsp:include page="../../page/header.jsp"></jsp:include>
   
   
    <div class="page_body container-fluid">
      <div class="row">
       
       <jsp:include page="../../page/left.jsp"></jsp:include> 
       
       
        <div class="col-lg-20 col-md-19 col-sm-18 main">  
          <div class="main_cont">
            <ol class="breadcrumb Noprint">
			    <li>首页</li>
			    <li class="active">菜单列表</li>
			</ol>


            <h2 class="main_title">菜单列表</h2>

            <div class="common_info order_details mt20">
              <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="initSetting.htm">菜单列表</a></li>
                <li role="presentation"><a href="allversion.htm">更新日志</a></li>
              </ul>
              <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="tab1">

                  <div class="data_ctrl_area mb20">
                    <div class="data_ctrl_search pull-right"></div>
                    <div class="data_ctrl_brns pull-left">
                      <button type="button" class="btn btn-info" onclick="addmenu();">
                        <i class="glyphicon glyphicon-plus"></i> 添加
                      </button>
                     <!--  <button type="button" class="btn btn-info" onclick="delallmenu();">
                        <i class="glyphicon glyphicon-trash"></i> 删除
                      </button> -->
                    </div>
                    <div class="clr"></div>
                  </div>

                  <table class="treetable table table-striped table-hover">
                    <thead>
                    <tr>
                      <th width="10"></th>
                      <th>菜单名称</th>
                      <th width="50">排序</th>
                      <th width="150">操作</th>
                    </tr>
                    </thead>
                    <tbody id="treetable">
                   
                    
                    </tbody>
                  </table>

                </div>
                
             
              </div>
            </div>

          </div>
          
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="addMenu"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加菜单</h4>
          </div>
      <form role="form" class="form-horizontal" id="saveCate" action="savePage.htm?active=0&CSRFToken=${token }" method="post" enctype="multipart/form-data">
          <div class="modal-body">
            	<fieldset>
              <div class="form-group">
                <label class="control-label col-sm-5">页面名称：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <input type="hidden" name="id" id="update_id" class="form-control">
                  <input type="text" name="designation" id="update_menu_designation" class="form-control required">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">上级页面：</label>
              <div class="col-sm-1"></div>
                <div class="col-sm-12">
                 <input type="hidden" name="parentId" id="addparentId" value="0"/>
                 <input id="cateParentId" value="--无--" readonly="readonly"  class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5"></label>
                <div class="col-sm-1"></div>
                <div class="col-sm-8">
                  <div class="city_area">
                    <ul id="treeDemo" class="ztree"></ul>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">页面地址：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <input type="text"  name="url" id="update_menu_url"  class="form-control required">
                </div>
              </div>
              <div class="form-group">  
                <label class="control-label col-sm-5">排序：</label>
                <div class="col-sm-1"></div> 
                <div class="col-sm-12">
                  <input type="text" class="form-control w100 digits required"  name="sort" maxlength="3" id="update_menu_sort">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">页面类型：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <label class="radio-inline">
                    <input type="radio" name="type" id="update_menu_type0" value="0" checked="checked"> 节点
                  </label>
                  <label class="radio-inline">
                    <input type="radio" name="type" id="update_menu_type1" value="1"> 页面
                  </label>
                  <label class="radio-inline">
                    <input type="radio" name="type" id="update_menu_type2" value="2"> 按钮
                  </label>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">未选中状态图片：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <p class="pt5">	<img class="vm mr10" id="img1" width="30" height="30" />
						<input name="imgUrl" id="imgUrl" type="hidden" />
		            	<input type="button" id="choose1" value="选择"/></p>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">选中状态图片：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <p class="pt5"><img class="vm mr10" id="img2" width="30" height="30" />
						<input name="imgUrlSelected" id="photoImg2" type="hidden" />
		            	<input type="button" id="choose2" value="选择"/></p></p>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">页面描述：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <input type="text" class="form-control" name="characterization" id="update_menu_characterization">
                </div>
                
              </div>
              </fieldset>
            
          </div>
          <div class="modal-footer">
            <input type="submit" class="btn btn-primary" value="保存"/>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
          </form>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="addLog"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加版本信息</h4>
          </div>
          <div class="modal-body">
            <form role="form" class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-5">版本号：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <input type="text" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">更新时间：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-6">
                  <div class="input-group date form_datetime">
                    <input class="form-control" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">更新内容：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-17">
                  <div class="summernote"></div>
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

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script> 
    <script src="<%=basePath %>js/jqtreetable.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    
    <script>
    var i=1;
      $(function(){
      	$("#saveCate").validate();
      	
          var condition ='';
  	    var searchText = '';
          $.get("queryMenuVoList.htm?condition=" + condition + "&searchText=" + searchText, 
          	    function (data)
          	    {
              		if(data!=null){
              		    calcSonCate(data.list);
              		  }
              		var map = "";
              		$(".caidan").each(function(){
              		    if($(this).text()==''){
              		      map+="0,";
              		    }else{
              		      map+=$(this).text()+",";
              		    }

              		});
              	    var maps=map.substring(0,map.length-1).split(",");
             
              		 $("#treetable").jqTreeTable(maps, {
                         openImg: "<%=basePath %>images/TreeTable/tv-collapsable.gif",
                         shutImg: "<%=basePath %>images/TreeTable/tv-expandable.gif",
                         leafImg: "<%=basePath %>images/TreeTable/tv-item.gif",
                         lastOpenImg: "<%=basePath %>images/TreeTable/tv-collapsable-last.gif",
                         lastShutImg: "<%=basePath %>images/TreeTable/tv-expandable-last.gif",
                         lastLeafImg: "<%=basePath %>images/TreeTable/tv-item-last.gif",
                         vertLineImg: "<%=basePath %>images/TreeTable/vertline.gif",
                         blankImg: "<%=basePath %>images/TreeTable/blank.gif",
                         collapse: true,
                         column: 1,
                         striped: true,
                         highlight: true,
                         state:false 
                       }); 
              		  
          	    });
       
          
         
    		 
        /* 下面是另一种带折叠的表格，没有分页 END */

        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
      

        /* 富文本编辑框 */
        $('.summernote').summernote({
          height: 300,
          tabsize: 2,
          lang: 'zh-CN'
        });

        /* 下面是表单里面的日期时间选择相关的 */
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00:00',
          weekStart : 1,
          autoclose : true,
          language : 'zh-CN',
          pickerPosition : 'bottom-left',
          todayBtn : true,
          viewSelect : 'hour'
        });
        $('.form_date').datetimepicker({
          format : 'yyyy-mm-dd',
          weekStart : 1,
          autoclose : true,
          language : 'zh-CN',
          pickerPosition : 'bottom-left',
          minView : 2,
          todayBtn : true
        });
        /* 下面是表单里面的日期时间选择相关的 END */

        
        
        $("#choose1").click(function(){
            i=1;
            art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=10000', {
                lock: true,
                opacity:0.3,
                width: '900px',
                height: '400px',
                title: '选择图片'
            });
        });
        
        $("#choose2").click(function(){
            art.dialog.open('queryImageManageByPbAndCidForChoose.htm?location=2&CSRFToken=${token}&size=10000', {
                lock: true,
                opacity:0.3,
                width: '900px',
                height: '400px',
                title: '选择图片'
            });
        });
        
        
       
      });
   	  //当前排序
 	 var index=1;
      function calcSonCate(data){
          for (var i = 0; i < data.length; i++){
            
              var htm='';
                  htm+=' <tr ';
                  if(data[i].parentId!='0'){ 
                	  htm+='class="collapsed"';
                  }
                  htm+='>';
                  htm+='<td>'; 
                  htm+='<input type="checkbox" value="'+data[i].id+'" name="pageId">';
                  htm+='</td>';
                  htm+='<td style="text-align:left;"> <span style="display:none;" id="mep'+data[i].id+'">'+index+'</span>&nbsp;&nbsp;'+data[i].designation+'<span class="caidan" style="display:none;" id="me'+data[i].id+'"></span><span style="display:none;" id="mes'+data[i].id+'"></span></td>';
                 if(data[i].sort!='0'){
                     if(data[i].url!='0'){
                         htm+='<td>'+data[i].url+'</td>';
                     }else{
                    	 if(data[i].sort==null||data[i].sort==''){
                    		  htm+='<td></td>'; 
                    	 }else{
                    		  htm+='<td>'+data[i].sort+'</td>'; 
                    	 }
                       
                     }
                   
                 }else{
                     htm+='<td></td>'; 
                 }
                   
                  htm+='<td>';
                  htm+='<div class="btn-group">';
                  htm+=' <button type="button" class="btn btn-default" onclick="editmenu('+data[i].id+');">编辑</button>';
                  htm+='<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">';
                  htm+='  <span class="caret"></span>';
                  htm+=' <span class="sr-only">Toggle Dropdown</span>';
                  htm+='</button>';
                  htm+=' <ul class="dropdown-menu" role="menu">';
                 // htm+='   <li><a href="javascript:void(0);" onclick="delpage('+data[i].id+')">删除</a></li>';
                  htm+=' </ul>';
                  htm+='  </div>'; 
                  htm+=' </td>';
                  htm+='</tr>';
                  $("#treetable").append(htm);
                  $("#me"+data[i].id).text($("#mep"+data[i].parentId).text());
                  if(data[i].menuVos!=null&&data[i].menuVos.length!=0){
                      $("#mes"+data[i].id).text(1);
                  }else{
                      $("#mes"+data[i].id).text(0);
                  }
                  index++;
                  calcSonCate(data[i].menuVos);
                  
               
                 
          }
          
          
      }
      
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
      function addmenu(){
          $("#update_id").val('');
	        $("#update_menu_designation").val('');
	        $("#addparentId").val('');
	        $("#update_menu_url").val('');
	        $("#update_menu_sort").val('');
	        $("#img2").attr("src",'');
	        $("#photoImg2").val('');
	        $("#img1").attr("src",''); 
	        $("#imgUrl").val('');
	        $("#update_menu_type0").attr("checked",true);
	        $("#update_menu_type1").attr("checked",false);
	        $("#update_menu_type2").attr("checked",false);
	        $("#update_menu_characterization").val('');
         /*查询商品分类放在树形控件中*/
	        $.get("queryAllMenuVo.htm?CSRFToken=${token }", function (data)
	        {
	            var zNodes = new Array();
	            for (var i = 0; i < data.length; i++)
	            {
	                var node = {
	                    id : data[i].id, pId : data[i].parentId, name : data[i].designation, open : false 
	                };
	                zNodes.push(node);
	            }
	            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	        });
	        $('#addMenu').find("h4").text('添加菜单');
	        $('#addMenu').find("form").attr("action",'savePage.htm?active=0&CSRFToken=${token }');
	        $('#addMenu').modal('show');
      }
      
      
      
      function editmenu(cateId)
  	{
  	    $.get("queryPageById.htm?pageId=" + cateId+"&CSRFToken=${token}", function (data)
  	    {
  	    	$("#update_id").val(data.id);
  	        $("#update_menu_designation").val(data.designation);
  	        $("#addparentId").val(data.parentId);
  	        $("#update_menu_url").val(data.url);
  	        $("#update_menu_sort").val(data.sort);
  	        $("#img2").attr("src",data.imgUrlSelected);
  	        $("#photoImg2").val(data.imgUrlSelected);
  	        $("#img1").attr("src",data.imgUrl); 
  	        $("#imgUrl").val(data.imgUrl);
  	        if(data.type=="0"){
  	        	$("#update_menu_type0").attr("checked",true);
  	        }else if(data.type=="1"){ 
  	        	$("#update_menu_type1").attr("checked",true);
  	        }else{
  	        	$("#update_menu_type2").attr("checked",true);
  	        }
  	        $("#update_menu_characterization").val(data.characterization);
  	        /*查询商品分类放在树形控件中*/
  	         $.get("queryAllMenuVo.htm?CSRFToken=${token }", function (data)
	        {
	            var zNodes = new Array();
	            for (var i = 0; i < data.length; i++)
	            {
	                //设置父分类名称
	                if (data[i].id == $("#addparentId").val()) {
	                    $("#cateParentId").val(data[i].designation);
	                }
	                var node = {
	                    id : data[i].id, pId : data[i].parentId, name : data[i].designation, open : false 
	                };
	                zNodes.push(node);
	            }
	            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	        });
  	       $('#addMenu').find("h4").text('修改菜单');
  	       $('#addMenu').find("form").attr("action",'updatePage.htm.htm?active=0&CSRFToken=${token }');
	        $('#addMenu').modal('show');
  	    });
  	}
      
      function saveChoooseImage(url) {
          if(typeof (url) != 'string') {
              url = url[0];
          }
          if(url.indexOf(',')!=-1){
              url=url.substring(0,url.indexOf(','));  
          }
          $("#img1").attr("src",url);
          $("#imgUrl").val(url);

      }
    function saveChoooseImage2(url) {
        if(typeof (url) != 'string') {
            url = url[0];
        }
        if(url.indexOf(',')!=-1){
            url=url.substring(0,url.indexOf(','));
        }
        $("#img2").attr("src",url);
        $("#photoImg2").val(url);

    }
      
  	function onClick(event, treeId, treeNode, clickFlag) 
	{
	    $("#addparentId").val(treeNode.id);
	    $("#cateParentId").val(treeNode.name);
	}
    
  	function delpage(id){
  	//  alert($('mes'+id).text());  
  	//查询是否含有子分类
  	var flag = $("#mes"+id).text();
	  	if(flag==0){
	  	  showDeleteOneConfirmAlert('delPage.htm.htm?active=0&CSRFToken=${token}&pageId='+id);    
	  	}else{ 
	  	    //存在子分类
	  	  showNoDeleteConfirmAlert('此分类下含有子分类，不可删除！');
	  	}
  	
  	}
  	
  	function delallmenu(){
  	  var checked=0;
  	  var id;
	  	$("input[name='pageId']:checkbox:checked").each(function(){ 
	  	  checked++;
	  	  id=$(this).val();
	  	  }) ;
	  	if(checked==1){
	  	  showDeleteOneConfirmAlert('batchDelPage.htm?active=0&CSRFToken=${token}&pageId='+id);    
	  	}else{
	  	  showNoDeleteConfirmAlert('请选择一个分类，不可多选！');       
	  	}
  	    
  	}
    </script>
  </body>
</html>
