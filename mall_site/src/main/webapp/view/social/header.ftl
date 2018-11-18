<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#assign basePath=request.contextPath>
<title>驾友网——小组主页</title>
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/base.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/style.css" />
<script type="text/javascript" src="${basePath}/social/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/social/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/social/js/jcarousellite_1.0.1.min.js"></script>
<script type="text/javascript" src="${basePath}/social/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_comm.js"></script>
</head>
<body>
<div class="header">
    	<div class="header_wp clearfix">
        	<a class="logo fl" href="javascript:;"><img alt="" src="${basePath}/social/images/logo.png" /></a>
            <ul class="nav fl clearfix">
            	<li><a href="${basePath}/index.html">首页</a></li>
                <li><a href="${basePath}/group.html">小组</a></li>
                <li><a href="javascript:;">话题</a></li>
                <li><a href="javascript:;">知道</a></li>
                <li><a href="javascript:;">优惠券</a></li>
            </ul><!--/nav-->
            
            <div class="user_box no_login clearfix fr" id="nologin">
            	<div class="other_login fl clearfix">
                	<a class="qq_lg hide fl" href="javascript:;">QQ登录</a>
                    <a class="sina_lg hide fl" href="javascript:;">sina登录</a>
                </div><!--/other_login-->
                <a class="login_btn fl" href="javascript:;">登录</a>
                <a class="reg_btn fl" href="javascript:;">注册</a>
            </div><!--/user_box-->
            
            <div class="user_box login clearfix fr none" id="login">
            	<a class="userWp mt20 fl" href="javascript:;"><img class="vm mr10" alt="" src="${basePath}/social/images/images_10.jpg" />归去来兮</a>
                <div class="user_operating fl mt20 ml5">
                	<a class="u_letters" title="消息提醒" href="systemmsg.html"><em>10</em></a>
                    <a class="setUp" title="个人设置" href="javascript:;"></a>
                    <a class="u_power" title="退出" href="javascript:;"></a>
                </div><!--/user_operating-->
            </div><!--/user_box-->
        </div><!--/header_wp-->
    </div><!--/header--> 
	
</body>
</html>