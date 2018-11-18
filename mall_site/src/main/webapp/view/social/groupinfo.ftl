<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#assign basePath=request.contextPath>
<title>驾友网——小组信息页</title>
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/base.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/style.css" />
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/social/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/social/js/default.js"></script>
<script type="text/javascript" src="${basePath}/social/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_comm.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
</head>

<body>
	  <#include "header.ftl">
    
    <div class="container clearfix">
    	
        <div class="left_menu fl">
        	<h2 class="hide">管理小组</h2>
            <div class="menu_wp">
            	<div class="menu_box cur">
                	<h3><a href="javascript:;">基本信息</a></h3>
                    <ul class="menu_list">
                    	<li class="current"><a href="javascript:;">小组资料</a></li>
                        <li><a href="javascript:;">小组头像</a></li>
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
        	<h3 class="rc_tit">小组资料</h3>
            <div class="manage_cont">
            	<dl class="manage_dl clearfix ml20 mt10">
                	<dt>小组名称：</dt>
                    <dd>
                    	<div class="rowElem clearfix">
                        	<input class="mg_text" type="text" value="${groupInfo.groupName}"/>
                        </div><!--/rowElem-->
                        <p class="mg_info">不超过20个字符</p>
                    </dd>
                    <dt>小组简介：</dt>
                    <dd>
                    	<div class="rowElem clearfix">
                        	<textarea class="mg_ta">${groupInfo.groupRemark}</textarea>
                        </div><!--/rowElem-->
                        <p class="mg_info">简介不得超过500个字，你还可以输入 500</p>
                    </dd>
                    <dt>&nbsp;</dt>
                    <dd>
                    	<div class="rowElem clearfix">
                        	<input class="mg_btn" type="button" value="确认修改" />
                        </div><!--/rowElem-->
                    </dd>
                </dl><!--/groupInfo-->
                <script type="text/javascript">
					$(function() {
						$(".rowElem").jqTransform();
					});
				</script>
            </div><!--/manage_cont-->
        </div><!--/rig_cont-->
    </div><!--/container-->
    
      <#include "footer.ftl">

</body>
</html>
