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
    <title>添加物流单</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
     <style type="text/css">
 	.express_board{position:relative;height:600px;margin:10px;padding:20px;}
  .express_board .express_img{position:absolute;left:20px;top:20px;}
	.express_container{position:relative;width:870px;height:480px;background:no-repeat left top 100% auto;}
  .express_form{display:inline-block;margin-right:50px;}
  .express_form{*display:inline;}
  .express_form select{margin-top:6px;}
  .express_label{display:block;height:25px;line-height:25px;padding:0 10px;border:1px solid #999;background:#fff;}
  .express_label .close_btn{display:none;width:15px;height:15px;line-height:15px;text-align:center;position:absolute;right:-8px;top:-8px;background:url(images/express_close_btn.png) no-repeat;cursor:pointer;}
  .express_label:hover .close_btn{display:block;}
  .express_label .resize_btn{display:block;width:10px;height:10px;background:#ccc;position:absolute;right:0;bottom:0;}
</style>
  </head>
  <body>
     <!-- 引用头 -->
   <jsp:include page="../page/header.jsp"></jsp:include>
   
    <div class="page_body container-fluid">
      <div class="row">

 		 <jsp:include page="../page/left.jsp"></jsp:include>
 		 
        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <form class="form-inline" id="addForm" method="post" action="" enctype="multipart/form-data">
          <input name="thirdId" value="0" type="hidden"/>  
          <div class="main_cont">  
           <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild}</h2>

            <div class="common_data p20">

              <div class="table_ctrl">
             
                  <div class="form-group mr20" style="margin-bottom: 10px">
                    <label class="control-label">单据名称：</label>
                    <select class="form-control" name="companyId" id="companyId" data-live-search="true">
	                    <c:forEach var="info" items="${companys}">
				      		 <option value="${info.logComId }">${info.name }</option>
				      	</c:forEach>
                    </select>
                    
                  </div>
                  <div class="form-group mr20"   style="margin-bottom: 10px">
                    <label class="control-label">单据尺寸：</label>
                    <input type="text" class="form-control w100 required"  name="logisticsSingleWidth" value="922" id="logisticsSingleWidth">
                    <span class="pt5">×</span>
                    <input type="text" class="form-control w100 required" name="logisticsSingleHeight" value="510"  id="logisticsSingleHeight">
                    <span class="pt5">mm</span>
                  </div>
                  <div class="form-group mr20"   style="margin-bottom: 10px">
                    <label class="control-label">单据背景图：</label>
                    <input type="hidden" name="logisticsSingleImg" class="required" id="logisticsSingleImg" value="<%=basePath%>images/yuantongsingle.jpg" />
                    <button type="button" class="btn btn-default" id="choose">选择图片</button>
                  </div>
                  <div class="form-group mr20"   style="margin-bottom: 10px">
                    <label class="control-label">添加打印项：</label>
                    <select  id="addPrint" class="form-control" data-live-search="true">
                      <option value="0">添加打印项</option>
				        <option value="sendName">发件人-姓名</option>
				        <%--<option value="sendArea1">发件人-地区1级</option>
				        <option value="sendArea2">发件人-地区2级</option>
				        <option value="sendArea3">发件人-地区3级</option>--%>
				        <option value="sendAddr">发件人-地址</option>
                        <option value="sendPhone">发件人-联系电话</option>
                        <%--<option value="sendMobile">发件人-手机号码</option>--%>
                        <%--<option value="sendPostcode">发件人-邮政编号</option>--%>
				        <option value="addresseeName">收件人-姓名</option>
				        <option value="addresseeArea1">收件人-地区1级</option>
				        <option value="addresseeArea2">收件人-地区2级</option>
				        <option value="addresseeArea3">收件人-地区3级</option>
				        <option value="addresseeAddr">收件人-地址</option>
				        <option value="addresseePhone">收件人-联系电话</option>
				       	<option value="goodsName">货品名称</option>
				       	<option value="addresseeMobile">收件人-手机号码</option> 
				       	<option value="adderseePostcode">收件人-邮政编号</option>
                    </select>
                  </div>
                  <div class="form-group mr20"  style="margin-bottom: 10px">
                    <button type="button" class="btn btn-primary" onclick="addLogistics();">保存快递单</button>
                    <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">返回快递单</button>
                  </div>
              </div>

              <div class="express_board">
                <img class="express_img" alt="" src="images/yuantongsingle.jpg">
                <div class="express_container">

                </div>
              </div>

            </div>

          </div>
        </form>
        </div>
      </div>
    </div>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/summernote.min.js"></script>
    <script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath%>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>js/drag.jquery.js"></script>
    <script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    <script>
      $(function(){
          $("#addForm").validate();
        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */

        $('#sendName').dragDrop({
          fixarea:[0,$('.express_container').width()-100,0,$('.express_container').height()-50]
        });
        $('#addPrint').change(function(){
          var theId = $(this).val();
          var canMove = true;
          if($('#'+ theId).length > 0){
            alert($(this).find('option:selected').text() + ' 已经存在，请勿重复添加。');
          }
          else {
            $('.express_container').append('<div id="' + theId + '" class="express_label">' + $(this).find('option:selected').text() + '<i class="close_btn">&nbsp;</i><i class="resize_btn"></i></div>');
            $('#' + theId).dragDrop({
              fixarea: [0, $('.express_container').width() - 100, 0, $('.express_container').height() - 50]
            });
            $('#' + theId + ' .close_btn').click(function () {
              $(this).parent().remove();
            });
            $('#' + theId + ' .resize_btn').click(function(){
              if(canMove) {
                $(this).bind('mousemove',function (e) {
                  $('#' + theId).width(e.pageX - $('#' + theId).offset().left - 15);
                  $('#' + theId).height(e.pageY - $('#' + theId).offset().top + 5);
                });
              }
              else{
                $(this).unbind('mousemove');
              }
              canMove = !canMove;
            });

          }
        });
        
        
      //更改宽度
		$("#logisticsSingleWidth").blur(function(){
			var lwidth=$(this).val();
			if (lwidth == "" || lwidth == "0" || isNaN(lwidth) || lwidth<=0)
		    {
                showTipAlert("宽度须大于0且不为空！");
		    } else {
                if (parseInt(lwidth) > 0 && parseInt(lwidth) <= $(".express_board").width()) {
                    $(".express_container").css("width", lwidth);
                    $(".express_img").css("width", lwidth);
                    $(".express_container").css("height",$("#logisticsSingleHeight").val());
                    $(".express_img").css("height",$("#logisticsSingleHeight").val());
                } else {
                    showTipAlert("宽度不能大于"+$(".express_board").width()+"px!");
                }
            }
		});
		//更改高度
		$("#logisticsSingleHeight").blur(function(){
			var lheight=$(this).val();
			if (lheight == "" || lheight == "0" || isNaN(lheight) || lheight <= 0 )
		    {
                showTipAlert("高度须大于0且不为空！");
		    }else{
                if (parseInt(lheight) > 0 && parseInt(lheight)  <= $(".express_board").height() )
                {
                    $(".express_container").css("height",lheight);
                    $(".express_img").css("height",lheight);
                    $(".express_container").css("width", $("#logisticsSingleWidth").val());
                    $(".express_img").css("width", $("#logisticsSingleWidth").val());
                } else{
                    showTipAlert("高度不能大于"+$(".express_board").height()+"px!");
                }
            }
		});
		
		
      });
      
      $("#choose").click(function(){
          art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=10000', {
              lock: true,
              opacity:0.3,
              width: '900px',
              height: '400px',
              title: '选择图片'
          });
      });
      
      //图片回调
      function saveChoooseImage(url) {
        if(typeof (url) != 'string') {  
            url = url[0];
        }
          if(url.indexOf(',')!=-1){
              url=url.substring(0,url.indexOf(','));
          }
        $("#logisticsSingleImg").val(url);
		$("#logisticsSingleImg").prop("name","logisticsSingleImg");
		$(".express_img").attr("src",url);
		$("#logisticsSingleWidth").blur();
		$("#logisticsSingleHeight").blur();
    } 
      
      function addLogistics(){
          if($("#addForm").valid()){
             	$.ajax({
        			type : 'post',
        			url : 'selectLogisticsSingle.htm?CSRFToken=${token}&thirdId=0&companyId='+$("#companyId option:selected").val(),
        			async : false,
        			success : function(data) {
        				if(data==""){
        					$("#logisticsSingleContent").val($("#express_container").html());
        					$("#addForm").attr("action","addlogisticssingle.htm?CSRFToken=${token}");
        					$("#addForm").submit();
        				
        				}else{
        				    showNoDeleteConfirmAlert('此物流已经有过模板，请选择其他物流进行设置。');
        				}
        			}
        		});
          }
      }
    </script>
  </body>
</html>
