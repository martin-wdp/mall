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
    <title>移动站点信息</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    .curtain_wp p {
		position: absolute;
		width: 100%;
		left: 0;
		bottom: 10%;
		text-align: center;
		color: #fff;
		font-family: Arial;
		font-size: 16px;
		}
		
		#copyright{
			color:#FFFFFF;
		}
</style>
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

           <h2 class="main_title">${pageNameChild}</h2>


              <div class="common_data p20">
                  <div class="alert alert-warning alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <strong>注意!</strong> 移动端站点设置，配置移动端页面站点信息，站点地址不需要修改，若修改不当，会影响移动端页面显示效果，在不了解的情况下请联系我们的工作人员进行修改。
                  </div>
            <div class="common_form p20" style="width:1000px;">
          <div id="tabhide">
            <div style="float:left;width:550px;">
      		   <form class="form-horizontal" action="updateMobSiteBasic.htm" method="post" id="addForm" >
	      		   <input type="hidden" name="siteBasicId" id="siteBasicId" value="${mobSiteBasic.siteBasicId }" />
	          	  <input type="hidden" name="CSRFToken" id="CSRFToken" 	value="${token}">
              <div class="form-group">
                <label class="control-label col-sm-6"><span class="text-danger">*</span>站点地址：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-8">
                  <input type="text" name="siteAddress" id="up_siteAddress"value="${mobSiteBasic.siteAddress }" class="form-control required" />
                </div>
              </div>
              <div class="form-group"> 
                <label class="control-label col-sm-6"><span class="text-danger">*</span>英文名称：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-8">
                  <input type="text" name="ename" id="up_ename" value="${mobSiteBasic.ename }"  class="form-control required" />
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-6"><span class="text-danger">*</span>技术支持：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-8">
                <input type="text" name="technicalSupport" id="up_technicalSupport"
							value="${mobSiteBasic.technicalSupport }" class="form-control required" >
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-6">微信转发时Logo：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-4">
                         <input type="button" id="choose" value="选择图库图片"/>
						<div style="margin-top: 10px;">
							<img id="temp1img" alt="" src="${mobSiteBasic.temp1 }"  height="55">
						</div>
						<input type="hidden" id="up_temp1" name="temp1" value="${mobSiteBasic.temp1 }"/>
                </div>
                <div class="col-sm-3"></div>
              </div>  
             
         	  <input type="hidden" id="temp2" value="${mobSiteBasic.temp2 }"/>
              <div class="form-group">
                <label class="control-label col-sm-6">是否显示缓冲页：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                
                <label class="radio-inline">
                    <input type="radio" name="isShowBuffer" value="0" class="radio1"
							<c:if test="${empty mobSiteBasic.isShowBuffer }">checked="checked"</c:if>
							<c:if test="${mobSiteBasic.isShowBuffer==0 }">checked="checked"</c:if>  
							onChange="changeRadio()" /><span class="label label-default">不显示</span>
                  </label>
                  <label class="radio-inline">
                    	<input type="radio" name="isShowBuffer" value="1" class="radio2"
							<c:if test="${mobSiteBasic.isShowBuffer==1 }">checked="checked"</c:if>
							onChange="changeRadio()" /><span class="label label-success">显示</span>
                  </label>
                  
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div id="tab1">
              <div class="form-group">
                <label class="control-label col-sm-6">缓冲页背景色：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-4">
                  <input type="text" class="form-control"   name="backgroudColor"  id="opacity" value="${mobSiteBasic.backgroudColor }">
                </div>
              </div>      
           
            <div class="form-group">
                <label class="control-label col-sm-6">缓冲页Logo：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-4">
                  <input type="button" id="choose1" value="选择图库图片"/>
                  <img id="chooseimg" alt="" src="${mobSiteBasic.backgroudImage }" height="55">
                  <input type="hidden" id="up_backgroudImage" name="backgroudImage" value="${mobSiteBasic.backgroudImage }">
                </div>
              </div>    
              
               <div class="form-group">
                <label class="control-label col-sm-6">缓冲页版权：</label>
                <div class="col-sm-1">©</div>
                <div class="col-sm-4">
                  <input type="text" class="form-control" name="copyright" id="up_copyright" value="${mobSiteBasic.copyright}" onblur="changeCopyright(this);">
                </div>
              </div>    
           </div>
              <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="update();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="quxiao();">取消</button>
          </div>
            </form>
            </div>
         
            </div>
            
             <div id="tabshow">
            <div style="float:left;width:550px;">
      		   <form class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-6"><span class="text-danger">*</span>站点地址：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-8">
                 <label class="radio-inline"> ${mobSiteBasic.siteAddress }</label>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-6"><span class="text-danger">*</span>英文名称：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-8">
                <label class="radio-inline"> ${mobSiteBasic.ename }</label>
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-6"><span class="text-danger">*</span>技术支持：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-8">
              <label class="radio-inline"> ${mobSiteBasic.technicalSupport }</label>
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-6">微信转发时Logo：</label>
                <div class="col-sm-2"></div>
                <div class="col-sm-4">
						<div style="margin-top: 10px;">
							<img id="temp1img" alt="" src="${mobSiteBasic.temp1 }"  height="55">
						</div>
						
                </div>
                <div class="col-sm-3"></div>
              </div>  
             
         	  <input type="hidden" id="temp2" value="${mobSiteBasic.temp2 }"/>
              <div class="form-group">
                <label class="control-label col-sm-6">是否显示缓冲页：</label>
                 <div class="col-sm-1"></div>
                <div class="col-sm-10">
                
	                <label class="radio-inline">
	                <c:if test="${mobSiteBasic.isShowBuffer==0 }">
	                  <span class="label label-default">不显示</span>
	                </c:if>
	                 <c:if test="${mobSiteBasic.isShowBuffer==1 }">
	                  <span class="label label-success">显示</span>
	                </c:if>
                  </label>
                 
                  
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div id="tab1" style="<c:if test="${mobSiteBasic.isShowBuffer==0 }">display:none;</c:if>">
              <div class="form-group">
                <label class="control-label col-sm-6">缓冲页背景色：</label>
                <div class="col-sm-2"></div>
                <div class="col-sm-4">
                  <div style="width: 70px; height: 30px; background-color:${mobSiteBasic.backgroudColor }">${mobSiteBasic.backgroudColor }</div>
                </div>
              </div>      
           
            <div class="form-group">
                <label class="control-label col-sm-6">缓冲页Logo：</label>
                <div class="col-sm-2"></div> 
                <div class="col-sm-4">
                  <img id="chooseimg" alt="" src="${mobSiteBasic.backgroudImage }" height="55">
                </div>
              </div>      
              
               <div class="form-group">
                <label class="control-label col-sm-6">缓冲页版权：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-4">
               <label class="radio-inline">©${mobSiteBasic.copyright}</label>
                </div>
              </div>  
              
           </div>
                <div class="modal-footer">
	            <button type="button" class="btn btn-primary" onclick="toupdate();">修改</button>
	          </div>  
            </form>
            </div>
            </div>
            
             
            <div class="curtain_wp" id="content"  style="<c:if test="${mobSiteBasic.isShowBuffer==0 }">display:none;</c:if>text-align:center;height:500px;width:300px;background-color:${mobSiteBasic.backgroudColor };float:right;"  > 
            	<img src="${mobSiteBasic.backgroudImage }" id="viewimg" style="width:150px;margin-top:80px;"/>
            	<div id="copyright" style="margin-top:280px;">©${mobSiteBasic.copyright}</div>
			</div>
            	
          
      		  
            </div>
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
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath%>js/minicolors/jquery.minicolors.js" type="text/javascript"></script>
	<link rel="stylesheet" href="<%=basePath%>js/minicolors/jquery.minicolors.css" type="text/css"/>
    <script type="text/javascript">
  
    var i=0;
    $(function(){
        $("#tabhide").hide();
        $("#tabshow").show();
		$("#addForm").validate();
		changeRadio();
		
		//设置取色器
		$('#opacity').minicolors({
			//control: $(this).attr('data-control') || 'hue',
			control: 'brightness',
			defaultValue: $(this).attr('data-defaultValue') || '',
			inline: $(this).attr('data-inline') === 'true',
			letterCase: $(this).attr('data-letterCase') || 'lowercase',
			position: $(this).attr('data-position') || 'bottom left',
			change: function(hex, opacity) {
				if( !hex ) return;
				var color = hex;
				if( opacity ) hex += ', ' + opacity;
				try {
					$("#content").css("background-color",color);
					console.log(hex);
				} catch(e) {}
			},
			theme: 'bootstrap'
		});
        
	
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
        
		$("#choose1").click(function(){
            i=2;
            art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=10000', {
                lock: true,
                opacity:0.3,
                width: '900px',
                height: '400px',
                title: '选择图片'
            });
        });
		
	  
	///$('.main_cont').css('minHeight',$(window).height() +400 + 'px');
    });
    
    //图片回调
    function saveChoooseImage(url) {
      if(typeof (url) != 'string') { 
          url = url[0];
      }
      if(url.indexOf(',')!=-1){
          url=url.substring(0,url.indexOf(','));
      }
      if(i==1){
          $("#temp1img").attr("src",url);
          $("#up_temp1").val(url);
      }
      if(i==2){
          $("#chooseimg").attr("src",url);
          $("#viewimg").attr("src",url);
          $("#up_backgroudImage").val(url);
      }

  } 
    
	function changeRadio(){
		var val=$('input:radio[name="isShowBuffer"]:checked').val();
		if(val==1){
			
			$('.main_cont').css('minHeight','920px');
			$("#tab1").show();
			$("#content").show();
		}else{
			$("#tab1").hide();
			$("#content").hide();
		}
	}

	
    function toupdate(){
        window.location.href="registerMarketing.htm?flag=1";
    }
    
    function update(){
        if($("#addForm").valid()){
           $("#addForm").submit();
        }
    }
    
		function changeCopyright(obj){
		     $("#copyright").html('©'+$(obj).val());
		}
		
		function toupdate(){
		    $("#tabhide").show();
	        $("#tabshow").hide();
		}
	
		
		function quxiao(){
		    window.location.href="showMobSiteBasic.htm";
		}
    </script>
  </body>
</html>
