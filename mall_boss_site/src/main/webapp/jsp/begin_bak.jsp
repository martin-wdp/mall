<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title></title>

  <!-- Bootstrap -->
  <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
  <link href="<%=basePath %>css/signin.css" rel="stylesheet">
  <script src="<%=basePath %>js/jquery.min.js"></script>
  <script type="text/javascript">
  document.onkeydown=function(event){
      var e = event || window.event || arguments.callee.caller.arguments[0];
       if(e && e.keyCode==13){ // enter 键
           //要做的事情
           login();
      }
  }; 
  
  
  
  var patchaFlag;
  $(function(){
  	//获取验证码开关
  	$.ajax({
  		url: "getLoginPatcha.htm", 
  		success: function(data){  
  			if(data==0){
  				$("#geetestDiv").hide();
  				$("#patchcaFlag").val(data);
  			}
  		}});
  	
  	//获取登录logo
  	$.ajax({
		url: "ajaxGetSysBasic.htm", 
		success: function(data){
			if(data.loginLogo!=""){
				$("#lgLogo").attr("src",data.loginLogo);
			}
			if(data.temp1!=""){
				$("#copyright").text(data.temp1);
			}
			if(data.temp2!=""){
				$("#beian").text(data.temp2);
			}
			if(data.bsetName!=null&&data.bsetName!=""){
			$("title").html("后台登录 - "+data.bsetName);
			}
		}});
	
	
  });
  
  	function login(){
  	  $("#login_error").css({"display":"none"});
  	 $("#managername").parents(".form-group").removeClass("has-error");
  	 $("#managerpassword").parents(".form-group").removeClass("has-error");
  	    patchaFlag = $("#patchcaFlag").val();
  	if( patchaFlag!="" && patchaFlag==0){
	  	    $.ajax({
				url: "iflogin.htm?name="+$("#managername").val()+"+&password="+$("#managerpassword").val(), 
				context: document.body, 
				success: function(data){
	  			if(data == 0){
	  				//用户名错误
	  				$("#login_error").css({"display":"block"});
	  				$("#managername").parents(".form-group").addClass("has-error");
	  				
	  			}else if(data == 2){
	  			   //密码错误
	  			   $("#login_error").css({"display":"block"});
	  			}else if(data == 3){
	  			   //用户名错误
	  				$("#login_error").css({"display":"block"});
	  			}else{
	      			window.location.href="index.htm";
	  			}
			}});
  	   }else{
				 $.ajax({
					url: "iflogin.htm?name="+$("#managername").val()+"+&password="+$("#managerpassword").val(), 
					context: document.body, 
					success: function(data){
		    			if(data == 0){
		    			    //用户名错误
			  				$("#login_error").css({"display":"block"});
			  				$("#managername").parents(".form-group").addClass("has-error");
			  				/*如果失败修更新验证码**/
			  				  $('.gt_refresh_button')[0].click();
		    			}else if(data == 2){
		    			    //密码错误
			  				$("#login_error").css({"display":"block"});
			  				$("#managerpassword").parents(".form-group").addClass("has-error");
			  				/*如果失败修更新验证码*/
			  				  $('.gt_refresh_button')[0].click();
		    			}else if(data == 3){
		    			    //用户名错误*/
			  				$("#login_error").css({"display":"block"});
			  				$("#managername").parents(".form-group").addClass("has-error");
			  				$("#managerpassword").parents(".form-group").addClass("has-error");
			  			   $('.gt_refresh_button')[0].click();
		    			}else{
		    			     	var challenge = $(".geetest_challenge").val();
		    					var validate = $(".geetest_validate").val();
		    					var seccode = $(".geetest_seccode").val();
		    					$.ajax({
		    						url : "verifyLoginServlet.htm",
		    						type : "post",
		    						data : {
		    							geetest_challenge : challenge,
		    							geetest_validate : validate,
		    							geetest_seccode : seccode
		    						},
		    							success : function(result) {
		    							   if(result==1){
		    							       window.location.href="index.htm";
				        			   
										   }else if(result==0){
										       $("#code_old").css({"display":"block"});
										   }else if(result==-1){
										       $("#code_error").css({"display":"block"});
										       $('.gt_refresh_button')[0].click();
										   }
								
								}
							});
		    			}
		  		}}); 
		  
		   
				
  	        
  		}
  	}
  	
  //随机生成一张背景图片
  	function changeBgimg(){  
  		var n = Math.floor(Math.random()*45)+1;
  		$("body").css("backgroundImage",'url(<%=basePath%>images/bgimg/'+n+'.jpg)'); 
  		$("body").css("background-repeat",'no-repeat'); 
  		$("body").css("background-size",'100% auto'); 
  	}
  	$(function(){changeBgimg();});
  	
  </script> 
</head>
<body>  
<div class="container">
<input type="hidden" id="patchcaFlag"/>  
  <div class="login_box">
  <form class="form-signin form-horizontal" id="lForm">
    <h2 class="form-signin-heading"><img alt="" src="" width="165" max-height="39" id="lgLogo">  </h2>
    <div class="form-group">
      <div class="input-group">
        <span class="input-group-addon"><i class="icon iconfont">&#xe616;</i></span>
     	 <input type="text" class="form-control" placeholder="用户名/邮箱"  name="name" id="managername">  
      </div>
    </div>
    <div class="form-group">
      <div class="input-group">
        <span class="input-group-addon"><i class="icon iconfont">&#xe617;</i></span>
         <input type="password" class="form-control" placeholder="密码"  name="password" id="managerpassword">
      </div>
    </div>
    <div class="form-group">
      <div class="alert alert-danger alert-dismissible fade in" role="alert" id="login_error"  style="display:none;">
        <button type="button" class="close"  onclick="hidediv(this);"  aria-label="Close"><span aria-hidden="true">×</span></button>
        <strong>用户名或密码</strong> 填写有误! <a href="javascript:;" data-container="body" data-toggle="popover" data-placement="right" data-trigger="hover" data-content="请联系我们的客服为您重置密码，请将您的身份认证信息准备齐全。">忘记密码？</a>
      </div>
       <div class="alert alert-danger alert-dismissible fade in" role="alert" id="code_old" style="display:none;">
        <button type="button" class="close" onclick="hidediv(this);" aria-label="Close"><span aria-hidden="true">×</span></button>
        <strong>验证插件版本太旧，请升级</strong>!
      </div>
       <div class="alert alert-danger alert-dismissible fade in" role="alert" id="code_error" style="display:none;">
        <button type="button" class="close" onclick="hidediv(this);" aria-label="Close"><span aria-hidden="true">×</span></button>
        <strong>请拖动滑块完成验证</strong>!
      </div>
    </div>
     <div class="form-group" id="geetestDiv" style="float:right">
		  <jsp:useBean id="geetestSdk" class="com.ningpai.util.GeetestLib"
					   scope="request" />
		  <%
			  // todo: use the captcha_id to init the geetestSdk
			  geetestSdk.setCaptchaId("7e15a7ee9e6a0f3dd3afbb3a84bb9394");
			  geetestSdk.setProductType("");
			  geetestSdk.setSubmitBtnId("submit-button");
		  %>
		  <%
			  if (geetestSdk.preProcess() != 1) {
		  %>
		 <div class="alert alert-danger alert-dismissible fade in" role="alert">
			 <button type="button" class="close" onclick="hidediv(this);" aria-label="Close"><span aria-hidden="true">×</span></button>
			 <strong>服务器异常</strong>!
		 </div>
		  <%
		  } else {
		  %>
		  <%=geetestSdk.getGtFrontSource()%>
		  <%
		  }
		  %> 
	  </div>
    <div class="form-group" style="margin-top:70px;">
      <button class="btn btn-lg btn-primary btn-block" type="button" onclick="login();">登录</button>
    </div>

  </form>
  </div>

  <div class="bottom_links"  style="background-color:#333;padding-top:10px;">
    <ul style="color:#ffffff;">
      <li><a href="javascript:;">© <span id="copyright"></span></a></li>
      <li><a href="javascript:;"><span id="beian"></span></a></li>
      <li><a href="javascript:changeBgimg();">切换背景</a></li>
    </ul>  
  </div>

</div> <!-- /container -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script>
  $(function () {
    $("#geetestDiv .float").css("float","right");
    $('[data-toggle="popover"]').popover();
  });
  function hidediv(obj){
      $(obj).parent().hide();
  }
</script>
</body>
</html>