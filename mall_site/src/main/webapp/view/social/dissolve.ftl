<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>驾友网——解散小组页</title>
<link rel="stylesheet" type="text/css" href="../css/base.css" />
<link rel="stylesheet" type="text/css" href="../css/jqtransform.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/slides.min.jquery.js"></script>
<script type="text/javascript" src="../js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="../js/default.js"></script>
</head>

<body>
	<#include "header.ftl">
    
    <div class="container clearfix">
    	
        <div class="left_menu fl">
        	<h2 class="hide">管理小组</h2>
            <div class="menu_wp">
            	<div class="menu_box">
                	<h3><a href="javascript:;">基本信息</a></h3>
                    <ul class="menu_list">
                    	<li><a href="javascript:;">小组资料</a></li>
                        <li><a href="javascript:;">小组头像</a></li>
                        <li><a href="javascript:;">小组背景</a></li>
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
                <div class="menu_box cur">
                	<h3><a href="javascript:;">权限管理</a></h3>
                    <ul class="menu_list">
                    	<li><a href="javascript:;">访问权限</a></li>
                        <li><a href="javascript:;">删除权限</a></li>
                        <li><a href="javascript:;">管理员权限</a></li>
                        <li class="current"><a href="javascript:;">解散小组</a></li>
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
        	<h3 class="rc_tit">解散小组</h3>
            <div class="manage_cont">
            	<div class="rowElem clearfix mt20">
                	<input type="button" value="解散本小组" />
                </div><!--/rowElem-->
            </div><!--/manage_cont-->
            <script type="text/javascript">
				$(function() {
					$(".rowElem").jqTransform();
				});
			</script>
        </div><!--/rig_cont-->
    </div><!--/container-->
    
    <#include "footer.ftl">
   

</body>
</html>
