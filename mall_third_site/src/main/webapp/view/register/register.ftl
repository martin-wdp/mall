<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>注册</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/style.css"/>
    <style type="text/css">
        .Code_tips,.tips{color: red;}

    </style>
</head>
<body>
<div class="sm-container"> <div class="header">
    <h1 class="header-logo"><a href="index.html"><img alt="" src="../images/logo.jpg"/></a></h1>
    <div class="header-title">免费注册</div>
    <div class="header-side">
        <a href="login.html">登录</a>
    </div>
</div>

    <div class="main-box main-register clearfix">
        <form class="form-horizontal" action="${basePath}/addcustomer.html" method="post" id="userform">
            <input type="hidden" name="cusId" id="cusId" value="" />
            <div class="control-group">
                <label class="control-label"><b>*</b>手机号码：</label>
                <input type="hidden" value="" name="username" clas="hi_name"  />
                <div class="controls">
                    <input type="text"  maxLength="11" name="customerUsername" id="act_user" class="form-control" placeholder="今后使用手机号登录"/>
                    <span class="tips" id="phone_tips"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"><b>*</b>短信校验码：</label>
                <div class="controls">
                    <div class="input-group">
                        <input type="text" class="form-control"/>
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" onclick="getCode()" id="sendCode">获取</button>
                        </span>
                    </div>
                    <div class="Code_tips">
                        短信校验码已发出，如果没有收到，您可以在<span class="timeleft"></span>秒后要求系统重新发送
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"><b>*</b>设置密码：</label>
                <input type="hidden" value="" name="password" clas="hi_psd" />
                <div class="controls">
                    <input type="password" maxLength="20" name="customerPassword" id="set_psd" class="form-control" placeholder="6~20个字符"/>
                    <span class="tips" id="pwd_tips"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"><b>*</b>确认密码：</label>
                <div class="controls">
                    <input type="password"  maxLength="20" name="psdConfirm" class="form-control" id="conf_psd" placeholder="再输一次"/>
                    <span class="tips" id="pwds_tips"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"><b>*</b>个人昵称：</label>
                <div class="controls">
                    <input type="text" class="form-control" name="customerNickname" id="customerNickname" placeholder="行不更名，坐不改姓"/>
                    <span class="tips" id="nickname_tips"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"><b>*</b>推荐码：</label>
                <div class="controls">
                    <input type="text" class="form-control" placeholder="没有就不用填"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <button class="btn btn-primary login-btn" onclick="reg()" type="button">确认注册</button>
                </div>
            </div>
        </form>
    </div>

    <div class="footer">
        <p class="copyright">© qianmi.com</p>
    </div>

    <script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/app.js"></script>
    <script type="text/javascript" src="${basePath}/js/register/register.js"></script>

</body>
</html>
