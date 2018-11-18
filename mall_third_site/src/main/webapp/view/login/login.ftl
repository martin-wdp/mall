<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>${basicSet.bsetName}</title>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>
<body>
<div class="sm-container">
    <div class="header">
        <h1 class="header-logo">
            <#--<a href="${basePath}/index.html">-->
            <#if basicSet.bsetThirdLogo??>
                <img src="${basicSet.bsetThirdLogo}" alt="" style="max-width:130px;max-height:35px;"/>
            <#else>
                <img alt="" src="${basePath}/images/logo.jpg"/>
            </#if>
        </h1>
        <div class="header-title">登录</div>
        <div class="header-side">
            
        </div>
    </div>
    <div class="main-box main-login clearfix" style="background-image:url(${basicSet.thirdLoginImg!''});">
    	<div class="login-container">
    		<form class="form-horizontal">
    			<div class="login-block">
    				<div class="control-group">
	                <div class="controls lgn-user">
	                    <input onBlur="pwd()" type="text" class="form-control" name="name" id="managername" placeholder="账号"/>
	                    <p class="err_info username_tip"></p>
	                </div>
		            </div>
		            <div class="control-group">
		                <div class="controls lgn-psw">
		                    <input onBlur="pwd()" type="password" class="form-control"  name="password" id="managerpassword" oncopy="return false;" oncut="return false;" onpaste="return false" placeholder="密码"/>
		                    <p class="err_info password_tip"></p>
		                </div>
		            </div>
		            <div class="control-group">
		                <div class="controls lgn-code">
		                    <input type="text" class="form-control" name="captcha" id="captcha" placeholder="验证码" onfocus="getPatcha()"/><img  height="42" class="code_image" src="patchca.htm" alt="验证码" title="点击换一张图片" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " >
		                    <p class="err_info captcha_tip"></p>
		                </div>
		            </div>
		            <div class="control-group">
		                <div class="controls">
		                    <button class="btn btn-primary login-btn" type="button" onclick="login()">登&nbsp;&nbsp;&nbsp;录</button>


		                </div>
		            </div>
		            <div class="control-group">
		                <div class="controls">
		                    <label class="auto-login"><#--<input type="checkbox"/>三天内自动登录--></label>
		                    <a class="lost-pw"href="${basicSet.bsetAddress}/findpwd.html">忘记密码？</a>
		                    <a class="store-reg" href="${basePath}/register.html" >免费注册，零元开店</a>
		                </div>
		            </div>
    			</div> 
	        </form>	
    	</div>
    	
        <#--<div class="side-wrap">
            <#if basicSet.thirdLoginImg??>
                <img src="${basicSet.thirdLoginImg }" alt="" style="max-width:205px;max-height:280px;"/>
            <#else>
                <img src="${basePath}/images/images_01.jpg" alt=""/>
            </#if>
        </div>-->
    </div>

    <div class="footer">
       <p class="copyright"><#if sysBasic.temp1??>${sysBasic.temp1}</#if></p>
    </div>
</div>


<script type="text/javascript" src="${basePath}/js/login/login.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript">
    document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){ // enter 键
            login();
        }
    };
    //用户名 密码失去焦点 隐藏提示语句
    function pwd(){
        $('.password_tip').html('');
        $('.username_tip').html('');
    }

    //搜藏本站
    function addFavorite(title,url){
        var title = document.title;
        var url = location.href;
        if (window.sidebar) {
            window.sidebar.addPanel(title, url, "");
        } else if (document.all) {
            window.external.AddFavorite(url, title);
        } else {
            return true;
        }


    }

</script>
</body>
</html>
