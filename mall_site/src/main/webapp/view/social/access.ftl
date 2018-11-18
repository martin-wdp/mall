<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#assign basePath=request.contextPath>
<title>驾友网——访问权限页</title>
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/base.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/jqtransform.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/style.css" />
<script type="text/javascript" src="${basePath}/social/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/social/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/social/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/social/js/default.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/social/group_comm.js"></script>
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
                    	<li class="current"><a href="javascript:;">访问权限</a></li>
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
        	<h3 class="rc_tit">访问权限</h3>
            <div class="manage_cont">
            	<div class="access_wp">
                	<h4>成员加入方式</h4>
                    <div class="join_way mt30">
                        <div class="rowElem clearfix">
                            <input type="radio" name="join" />
                            <label>需要小组管理员批准后才能加入小组</label>
                        </div><!--/rowElem-->
                        <div class="rowElem clearfix">
                            <input type="radio" name="join" />
                            <label>只有接受组员邀请才能加入小组</label>
                        </div><!--/rowElem-->
                        <div class="rowElem clearfix">
                            <input type="radio" name="join" checked="checked" />
                            <label>允许任何人加入小组</label>
                        </div><!--/rowElem-->
                    </div><!--/join_way-->
                    <div class="private_warning rowElem clearfix">
                    	<input type="checkbox" />
                        <label>设为<em>私密小组</em>
                        	<div class="pv_wp">
                            	<div class="pv_arrow"></div>
                            	<div class="pv_box">
                                	作为一个私密小组：
                                    <p>1.只有小组成员知道小组的存在；</p>
                                    <p>2.只有接受组员邀请才能加入小组。</p>
                                </div><!--/pv_box-->
                            </div><!--/pv_wp-->
                        </label>
                        <span>（一旦设置成为私密小组，以后<em>永远不能</em>变为公开小组，请谨慎选择）</span>
                    </div><!--/private_warning-->
                    <div class="rowElem clearfix ml30 mt50">
                    	<input type="button" value="确认修改" />
                    </div><!--/rowElem-->
                </div><!--/access_wp-->
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
