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
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet"> 
	<link rel="stylesheet" href="<%=basePath%>js/kindeditor/plugins/code/prettify.css" />
	<link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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

            <h2 class="main_title">${pageNameChild}</h2>

            <div class="common_info order_details mt20">
              <ul class="nav nav-tabs" role="tablist">
                   <li role="presentation"><a href="initSetting.htm">菜单列表</a></li>
                   <li role="presentation"  class="active"><a href="allversion.htm">更新日志</a></li>
              </ul>
              <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="tab1">
      <div class="data_ctrl_area mb20">
                    <div class="data_ctrl_search pull-right"></div>
                    <div class="data_ctrl_brns pull-left">
                      <button type="button" class="btn btn-info" onclick="showlog();">
                        <i class="glyphicon glyphicon-plus"></i> 添加
                      </button>
                    </div>
                    <div class="clr"></div>
                  </div>


                  <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                      <th width="60">序号</th>
                      <th>版本号</th>
                      <th>更新时间</th>
                      <th>更新内容</th> 
                      <th width="100">操作</th>
                    </tr>
                    </thead>
                    <tbody> 
                    
                      <c:forEach items="${pageBean.list}" var="version" varStatus="i">
	                    <tr>
	                      <td>${i.count}</td>
	                      <td>${version.versionCode }</td> 
	                      <td>  <fmt:formatDate value="${version.versionTime }" pattern="yyyy-MM-dd HH:mm:ss" var="versionTime" /> ${versionTime }</td>
	                      <td>
	         			   ${version.versionContent }
	                      </td>
	                      <td><button type="button" class="btn btn-default" onclick="toEdit('${version.versionId }');">编辑</button></td>
	                    </tr>
                      </c:forEach>
                    
                    </tbody>
                  </table>
                  
                <div class="table_foot">
                  	<c:import url="../../page/paging.jsp">
				     <c:param name="pageBean" value="${pageBean }"/>
				     <c:param name="path" value="../../"></c:param>
				</c:import>
                  </div>
               
                </div>
                
           
              </div>
            </div>

          </div>
          
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
            <form role="form" class="form-horizontal" id="logForm">
              <div class="form-group">
                <label class="control-label col-sm-5">版本号：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <input type="text" class="form-control required" name="versionCode" id="versionCode">
                  <input type="hidden" name="CSRFToken" value="${token}"/>
                   <input type="hidden" name="versionId" id="versionId" value="${versionId}"/>
                  
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">更新时间：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-6">
                  <div class="input-group date form_datetime">
                    <input class="form-control required" type="text" value="" readonly id="versionTime" name="date">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">更新内容：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-17">
              <textarea id="versionContent"   name="versionContent"  class="required"></textarea>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="addlog();">保存</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

   


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
         <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script> 
    <script src="<%=basePath %>js/jqtreetable.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath%>js/kindeditor/kindeditor-min.js" type="text/javascript"></script>
    <script charset="utf-8" src="<%=basePath%>js/kindeditor/plugins/code/prettify.js"></script>
    <script src="<%=basePath%>js/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
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
    $("#logForm").validate();
    var editor;
    
  //富文本编辑器 
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="versionContent"]', {
			resizeType : 1,
			minHeight:200,
	 		minWidth:300,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			filterMode : false,
			cssPath : '<%=basePath%>js/kindeditor/plugins/code/prettify.css',
			uploadJson : '<%=basePath%>js/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '<%=basePath%>js/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterBlur : function() {
				this.sync();
			},
			items : [
			         'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			         'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			         'insertunorderedlist']
		});
	});
	 
});


function showlog(){
    $("#addLog").find(".modal-title").text('添加版本信息 ');
    $("#versionTime").val(CurentTime());
    $("#logForm").attr("action",'addversion.htm');
    $('#addLog').modal('show');
		$.getScript('<%=basePath%>js/kindeditor/kindeditor-min.js', function() {
			KindEditor.basePath = '<%=basePath%>js/kindeditor/';
			KindEditor.create('textarea[name="versionContent"]');
		});
	$(".ke-container").attr('style','');
}

function toEdit(id){
    var e;
    $("#addLog").find(".modal-title").text('修改版本信息 ');
    $("#logForm").attr("action",'updateversion.htm');
    $('#addLog').modal('show');
		$.getScript('<%=basePath%>js/kindeditor/kindeditor-min.js', function() {
			KindEditor.basePath = '<%=basePath%>js/kindeditor/';
		e=KindEditor.create('textarea[name="versionContent"]');
		});
	$(".ke-container").attr('style','');
	
	$.post("showversion.htm?versionId="+id+'&CSRFToken=${token}',function(data){
		$("#versionCode").val(data.versionCode);
		$("#versionTime").val(dateToComm(data.versionTime));
		$("#versionId").val(data.versionId); 
		$(".ke-edit-iframe").contents().find(".ke-content").html(data.versionContent);
	});
}  

function addlog(){
    		if($("#logForm").valid()){
    		    $("#logForm").submit();
    		}
}

function CurentTime()
{ 
    var now = new Date();
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss= now.getSeconds(); //秒
    var clock = year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day + " ";
   
    if(hh < 10)
        clock += "0";
       
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm; 
    
    clock += ":";
    if(ss < 10) clock +='0';
    clock +=ss;
    return(clock); 
} 


function dateToComm(d){
	 var datetime =new Date(d);
	 var year = datetime.getFullYear();
	 var month = datetime.getMonth()+1;
	 var date = datetime.getDate(); 
	 var hour = datetime.getHours(); 
	 var minutes = datetime.getMinutes(); 
	 var second = datetime.getSeconds();
	 
	 if(month<10){
	  month = "0" + month;
	 }
	 if(date<10){
	  date = "0" + date;
	 }
	 if(hour <10){
	  hour = "0" + hour;
	 }
	 if(minutes <10){
	  minutes = "0" + minutes;
	 }
	 if(second <10){
	  second = "0" + second ;
	 }
	 year = year.toString();
	 var time1 = year+"-"+month+"-"+date+" ";
	 var time2 = hour+":"+minutes+":"+second;//09年06月12日 17时18分
	 return time1+time2; 
	}

</script>	
  </body>
</html>
