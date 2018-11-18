<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#assign basePath=request.contextPath>
<title>驾友网——小组详情页</title>
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/base.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/jqtransform.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/style.css" />
<script type="text/javascript" src="${basePath}/social/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/social/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/social/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/social/js/default.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/social/group_comm.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/social/group_detail.js"></script>
</head>

<body>
  <#include "header.ftl">    
    <div class="wrapper clearfix">
    	<div class="left_cont fl">
    	<input type="text" id="customerpow" value="${(custome.customerPower)!''}">
    	<input type="text" id="customerid" value="${(custome.customerId)!''}">
    	<input type="text" id="addtype" value="${(group.limitAddType)!''}">
    	<input type="text" id="addcondition" value="${(group.limitCondition)!''}">
    	<input type="hidden" id="createid" value="${(group.groupCreateAuthorId)!''}">
    	<input type="text" id="groupid" value="${(group.groupId)!''}" name="groupId">
        	<div class="team_introduce clearfix">
            	<div class="fl intro_left tc">
                	<img alt="" src="${basePath}/social/images/images_20.jpg" />
                    <a class="mamage_group none" href="groupinfo.html?groupId=${group.groupId}" id="manager">管理小组</a>
                </div><!--/intro_left-->
                <div class="fl team_cont ml15">
                	<div class="tc_tit clearfix">
                    	<h3 class="teamName fl">${group.groupName}</h3>
                        <span class="t_num fl">${group.groupmember}个成员</span>     
                        <span class="fr mt5 mr5">创建于：${group.groupCreateTime?string('yyyy-MM-dd')}</span>
                        <div class="t_leader head_box fr mr20 mt5">组长：<a href="javascript:;">${group.groupCreateAuthorName}</a></div>
                    </div><!--/tc_tit-->
                    <p class="lh200 mt10">${group.groupRemark}</p>
                    <div class="addTeam mt20" id="join">
                    	<a class="add_team open_window" href="javascript:void(0);" onclick="addTeam()">加入小组</a>
                        <span>加入之后，你可以在这个小组发布分享和话题</span>
                    </div><!--/addTeam-->
                    
                    <a class="quit_group mt20" href="javascript:;" style="display:none;" id="out">退出小组</a>
                    
                    <div class="fr mt10 shareWp">
                    	<!-- Baidu Button BEGIN -->
                        <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
                            <span class="bds_more">分享到：</span>
                            <a class="bds_qzone"></a>
                            <a class="bds_tsina"></a>
                            <a class="bds_tqq"></a>
                            <a class="bds_renren"></a>
                            <a class="bds_t163"></a>
                            <a class="shareCount"></a>
                        </div>
                        <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=3618405" ></script>
                        <script type="text/javascript" id="bdshell_js"></script>
                        <script type="text/javascript">
                        document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
                        </script>
                        <!-- Baidu Button END -->
                    </div><!--/shareWp-->
                    <a class="invite_btn fr mt10 mr10 none" href="javascript:;">邀请好友加入</a>
                </div><!--/team_cont-->
            </div><!--/team_introduce-->
            
            <div class="group_albums mt20">
            	<div class="albums_tit clearfix">
                	<h3 class="tit fl">小组相册</h3>
                    <a class="all_img fl" href="javascript:;">（全部图片）</a>
                    <a class="update_img fr" href="javascript:;" id="upload">上传照片</a>
                </div><!--/albums_tit-->
                <ul class="albums_list mt20 clearfix">
                	<li>
                    	<a href="javascript:;"><img alt="" src="${basePath}/social/images/images_28.jpg" /></a>
                        <a class="ml10 mt5" href="javascript:;">春泥</a>
                        <div class="head_box ml10 mt10">来自：<a class="from_user" href="javascript:;">橘子ccc</a></div>
                    </li>
                    <li>
                    	<a href="javascript:;"><img alt="" src="${basePath}/social/images/images_26.jpg" /></a>
                        <a class="ml10 mt5" href="javascript:;">春泥</a>
                        <div class="head_box ml10 mt10">来自：<a class="from_user" href="javascript:;">橘子ccc</a></div>
                    </li>
                    <li>
                    	<a href="javascript:;"><img alt="" src="${basePath}/social/images/images_27.jpg" /></a>
                        <a class="ml10 mt5" href="javascript:;">春泥</a>
                        <div class="head_box ml10 mt10">来自：<a class="from_user" href="javascript:;">橘子ccc</a></div>
                    </li>
                    <li>
                    	<a href="javascript:;"><img alt="" src="${basePath}/social/images/images_28.jpg" /></a>
                        <a class="ml10 mt5" href="javascript:;">春泥</a>
                        <div class="head_box ml10 mt10">来自：<a class="from_user" href="javascript:;">橘子ccc</a></div>
                    </li>
                    <li>
                    	<a href="javascript:;"><img alt="" src="${basePath}/social/images/images_26.jpg" /></a>
                        <a class="ml10 mt5" href="javascript:;">春泥</a>
                        <div class="head_box ml10 mt10">来自：<a class="from_user" href="javascript:;">橘子ccc</a></div>
                    </li>
                </ul><!--/albums_list-->
            </div><!--/group_albums-->
            
            <div class="group_topic mt30">
            	<div class="topic_tit clearfix">
                	<h3 class="tit fl">小组话题</h3>
                    <div class="filter_wp fl">
                    	筛选：
                        <div class="down_wp">
                        	<em>全部</em>
                            <ul class="down_list">
                            	<li class="cur"><a href="javascript:;">全部</a></li>
                                <li><a href="javascript:;">最热</a></li>
                                <li><a href="javascript:;">精华</a></li>
                            </ul><!--/down_list-->
                        </div><!--/down_wp-->
                    </div><!--/filter_wp-->
                    <div class="sequence_wp fl">
                    	排序：
                        <div class="down_wp">
                        	<em>发布时间</em>
                            <ul class="down_list">
                            	<li class="cur"><a href="javascript:;">一个月内</a></li>
                                <li><a href="javascript:;">两个月内</a></li>
                                <li><a href="javascript:;">半年内</a></li>
                            </ul><!--/down_list-->
                        </div><!--/down_wp-->
                    </div><!--/sequence_wp-->
                    
                    <a class="post_topic fr" href="javascript:;" id="createtopic">发表话题</a>
                    <div class="topic_search clearfix fr mr10">
                    	<input class="ts_text fl" type="text" placeholder="话题搜索" />
                        <input class="ts_btn fl" type="button" value="" />
                    </div><!--/topic_search-->
                </div><!--/topic_tit-->
                
                <ul class="top_topic topic_list mt20">
                	<li class="special_top">
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项</a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                    <li class="g_top">
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项</a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                    <li>
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项</a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                    <li>
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项</a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                    <li>
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项</a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                </ul><!--/top_topic-->
                
                <ul class="general_topic topic_list mt20">
                	<li>
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项</a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                    <li>
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项</a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                    <li>
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项</a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                    <li>
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项<img alt="" src="${basePath}/social/images/music_icon.gif" /></a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                    <li>
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项<img alt="" src="${basePath}/social/images/hot_topic.gif" /></a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                    <li>
                    	<div class="topic_check clearfix">
                        	<input type="checkbox" />
                        </div><!--/topic_check-->
                    	<a class="tp_caption" href="javascript:;">[公告] 依恋衣恋小组 话题发布注意事项</a>
                        <div class="topicInfo clearfix mt10">
                        	<div class="tp_from head_box fl mr30">
                            	来自：<a href="javascript:;">日光星湖</a>
                            </div><!--/tp_from-->
                            <span class="fl tp_date">时间：2012-08-02 16:25</span>
                            <div class="fr reply_view">
                            	<a href="javascript:;">77回复</a> / <a href="javascript:;">541查看</a>
                            </div><!--/reply_view-->
                        </div><!--/topicInfo-->
                    </li>
                </ul><!--/general_topic-->
                
                <script type="text/javascript">
					$(function(){
						$(".topic_list").jqTransform();
						});
                </script>
                
                <div class="leader_wp mt20 clearfix none">
                	<a class="fl l_op" href="javascript:;">全选</a>
                    <a class="fl l_op" href="javascript:;">删除</a>
                    <a class="fl l_op" href="javascript:;">删除并拉黑</a>
                    <a class="fl l_op" href="javascript:;">禁止回应</a>
                    <div class="leader_box fl">
                    	<div class="down_wp">
                        	<em>置顶</em>
                            <ul class="down_list">
                            	<li class="cur"><a href="javascript:;">置顶</a></li>
                                <li><a href="javascript:;">取消置顶</a></li>
                                <li><a href="javascript:;">申请到首页</a></li>
                            </ul><!--/down_list-->
                        </div><!--/down_wp-->
                    </div><!--/leader_box-->
                    <div class="leader_box fl">
                    	<div class="down_wp">
                        	<em>热帖</em>
                            <ul class="down_list">
                            	<li class="cur"><a href="javascript:;">热帖</a></li>
                                <li><a href="javascript:;">精华</a></li>
                                <li><a href="javascript:;">取消热帖</a></li>
                                <li><a href="javascript:;">取消精华</a></li>
                            </ul><!--/down_list-->
                        </div><!--/down_wp-->
                    </div><!--/leader_box-->
                </div><!--/leader_wp-->
                
                <div class="pages tc mt30">
                	<a class="pg_prev" href="javascript:;">&lt;上一页</a>
                    <a class="cur" href="javascript:;">1</a>
                    <a href="javascript:;">2</a>
                    <a href="javascript:;">3</a>
                    <a href="javascript:;">4</a>
                    <a href="javascript:;">5</a>
                    <a href="javascript:;">6</a>
                    <a class="pg_next" href="javascript:;">下一页&gt;</a>
                </div><!--/pages-->
            </div><!--/group_topic-->
            
            <div class="user_detail clearfix">
                <a class="uhead fl" href="javascript:;"><img alt="" src="${basePath}/social/images/images_09.jpg" /></a>
                <div class="uinfo fl ml10 pt5">
                    <a class="uname" href="javascript:;">爱果果</a>
                    <p class="mt10">江苏 南京</p>
                    <p class="mt10"><span>座驾：凯美瑞</span><span class="ml10">驾龄：5年</span></p>
                </div><!--/uinfo-->
                <a class="attention fr" href="javascript:;">加关注</a>
            </div><!--/user_detail-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="latest_add">
            	<h3>最新加入<a href="javascript:;">（所有923个成员）</a></h3>
                <ul class="la_list clearfix mt20">
                	<li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                </ul><!--/la_list-->
            </div><!--/latest_add-->
            
            <div id="gg_slides" class="mt20" style="width:245px; height:375px; overflow:hidden;">
            	<div class="slides_container">
                	<div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_23.jpg" width="245" height="375" /></a></div>
                    <div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_23.jpg" width="245" height="375" /></a></div>
                    <div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_23.jpg" width="245" height="375" /></a></div>
                </div><!--/slides_container-->
            </div><!--/gg_slides-->
            <script>
				$(function(){
					var startSlide = 1;
					$('#gg_slides').slides({
						preload: true,
						generatePagination: true,
						play: 5000,
						pause: 2500,
						hoverPause: true,
						start: startSlide
					});
				});
			</script>
            
            <div class="hot_team mt20">
            	<h3>热门小组</h3>
                <ul class="ht_list clearfix mt20">
                	<li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                    <li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_22.jpg" />人生若只初见</a></li>
                </ul><!--/ht_list-->
            </div><!--/hot_team-->
        </div><!--/right_cont-->
    </div><!--/wrapper-->
    
      <#include "footer.ftl">
    
    <div class="mask"></div>
    <div class="dialog" id="dig1">
    	<a class="close" href="javascript:;"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20">加入小组成功</p>
    </div><!--/dialog-->
    <div class="dialog" id="dig2">
    	<a class="close" href="javascript:;"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20">添加失败</p>
    </div><!--/dialog-->
    <div class="dialog" id="dig3">
    	<a class="close" href="javascript:;"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20">此小组不允许任何人加入</p>
    </div><!--/dialog-->
    <div class="dialog" id="dig4">
    	<a class="close" href="javascript:;"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20">此小组只添加男性用户</p>
    </div><!--/dialog-->
    <div class="dialog" id="dig5">
    	<a class="close" href="javascript:;"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20">此小组只添加女性性用户</p>
    </div><!--/dialog-->
</body>
<script>
 $(document).ready(function(){
   var customerpower = $("#customerpow").val();
   var customerid = $("#customerid").val();
   var createid = $("#createid").val();
	   if(customerid == ''){
	     $("#join").show();
	     $("#out").hide();
	     $("#manager").hide();
	     $("#upload").hide();
	     $("#createtopic").hide();
	   }else{
		   if(customerpower == 0 && customerpower != '' ){
		     $("#join").hide();
		     $("#out").show();
		     $("#manager").hide();
		     $("#upload").show();
		     $("#createtopic").show();
		   }else if(customerpower == 1 || customerpower == 2){
		     $("#join").hide();
		     $("#out").hide();
		     $("#manager").show();
		     $("#upload").show();
		     $("#createtopic").show();
		   }
	   }
  });
</script>
</html>
