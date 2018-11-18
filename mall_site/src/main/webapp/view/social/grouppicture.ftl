<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>驾友网——小组头像页</title>
<link rel="stylesheet" type="text/css" href="../css/base.css" />
<link rel="stylesheet" type="text/css" href="../css/jqtransform.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/slides.min.jquery.js"></script>
<script type="text/javascript" src="../js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="../js/default.js"></script>
</head>

<body>
	<div class="header">
    	<div class="header_wp clearfix">
        	<a class="logo fl" href="javascript:;"><img alt="" src="../images/logo.png" /></a>
            <ul class="nav fl clearfix">
            	<li><a href="javascript:;">首页</a></li>
                <li><a href="javascript:;">小组</a></li>
                <li><a href="javascript:;">话题</a></li>
                <li><a href="javascript:;">知道</a></li>
                <li><a href="javascript:;">优惠券</a></li>
            </ul><!--/nav-->
            
            <div class="user_box no_login clearfix fr">
            	<div class="other_login fl clearfix">
                	<a class="qq_lg hide fl" href="javascript:;">QQ登录</a>
                    <a class="sina_lg hide fl" href="javascript:;">sina登录</a>
                </div><!--/other_login-->
                <a class="login_btn fl" href="javascript:;">登录</a>
                <a class="reg_btn fl" href="javascript:;">注册</a>
            </div><!--/user_box-->
            
            <div class="user_box login clearfix fr none">
            	<a class="userWp mt20 fl" href="javascript:;"><img class="vm mr10" alt="" src="../images/images_10.jpg" />归去来兮</a>
                <div class="user_operating fl mt20 ml5">
                	<a class="u_letters" href="javascript:;"><em>10</em></a>
                    <a class="setUp" href="javascript:;"></a>
                    <a class="u_power" href="javascript:;"></a>
                </div><!--/user_operating-->
            </div><!--/user_box-->
        </div><!--/header_wp-->
    </div><!--/header-->
    
    <div class="container clearfix">
    	
        <div class="left_menu fl">
        	<h2 class="hide">管理小组</h2>
            <div class="menu_wp">
            	<div class="menu_box cur">
                	<h3><a href="javascript:;">基本信息</a></h3>
                    <ul class="menu_list">
                    	<li><a href="javascript:;">小组资料</a></li>
                        <li class="current"><a href="javascript:;">小组头像</a></li>
                        <li><a href="javascript:;">小组背景</a></li>
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
                <div class="menu_box">
                	<h3><a href="javascript:;">权限管理</a></h3>
                    <ul class="menu_list">
                    	<li><a href="javascript:;">访问权限</a></li>
                        <li><a href="javascript:;">删除权限</a></li>
                        <li><a href="javascript:;">管理员权限</a></li>
                        <li><a href="javascript:;">解散小组</a></li>
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
                <div class="menu_box">
                	<h3><a href="javascript:;">成员管理</a></h3>
                    <ul class="menu_list">
                    	<li><a href="javascript:;">小组成员</a></li>
                        <li><a href="javascript:;">黑名单</a></li>
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
                <div class="menu_box">
                	<h3><a href="javascript:;">禁止回应的话题</a></h3>
                    <ul class="menu_list">
                    	<li><a href="javascript:;">禁止回应的话题</a></li>
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
                <div class="menu_box">
                	<h3><a href="javascript:;">回收站</a></h3>
                    <ul class="menu_list">
                    	<li><a href="javascript:;">回收站</a></li>
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
            </div><!--/menu_wp-->
        </div><!--/left_menu-->
		
        <div class="rig_cont fl">
        	<h3 class="rc_tit">小组头像</h3>
            <div class="manage_cont">
            	<div class="pic_cont mt10">
                    <div class="head_picture fl"><img alt="" src="../images/default_picture.jpg" /></div>
                    <div class="pic_wp fl ml20">
                        <p class="pic_txt">选择喜欢的图片作为小组头像</p>
                        <a class="upload_wp" href="javascript:;"><input class="pic_upload" type="file" /></a>
                        <p class="pic_info">可以上传jpg、gif或png格式的图片，且图片文件小于5M；</p>
                        <p class="pic_info">保存成功后如果左边头像预览图不更新，可以刷新页面后查看。</p>
                    </div><!--/pic_wp-->
                </div><!--/pic_cont-->
            </div><!--/manage_cont-->
        </div><!--/rig_cont-->
    </div><!--/container-->
    
    <div class="footer mt50">
    	<div class="footer_wp">
        	<div class="clearfix">
            	<ul class="ft_nav clearfix fl">
                	<li><a href="javascript:;">关于我们</a></li>
                    <li><a href="javascript:;">广告指南</a></li>
                    <li><a href="javascript:;">投稿指南</a></li>
                    <li><a href="javascript:;">联系我们</a></li>
                    <li><a href="javascript:;">帮助中心</a></li>
                    <li><a href="javascript:;">意见反馈</a></li>
                </ul><!--/ft_nav-->
                <div class="follow_us fr">
                	关注我们：
                    <a class="sina_wb" href="javascript:;"><img alt="" src="../images/sina_weibo.gif" /></a>
                    <a class="sina_wb" href="javascript:;"><img alt="" src="../images/qq_weibo.gif" /></a>
                    <a class="sina_wb" href="javascript:;"><img alt="" src="../images/qq.gif" /></a>
                    <a class="sina_wb" href="javascript:;"><img alt="" src="../images/douban.gif" /></a>
                    <a class="sina_wb" href="javascript:;"><img alt="" src="../images/renren.gif" /></a>
                </div><!--/follow_us-->
            </div>
            <p class="copyright mt20">© 2009－2011 jiayou.in | 驾友.com, all rights reserved  版权所有 © 江苏鼎昌科技有限公司 2010 苏ICP B2-20110088</p>
            <p class="p_record mt10">南京市公安局雨花分局备案编号：<span>1101050728</span></p>
        </div><!--/footer_wp-->
    </div><!--/footer-->

</body>
</html>
