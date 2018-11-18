<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#assign basePath=request.contextPath>
<title>驾友网——小组列表页</title>
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/base.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/style.css" />
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/social/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/social/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_comm.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${basePath}/social/js/jquery.jqtransform.js"></script>
</head>

<body>
	<div>
		<#include "header.ftl" />
	</div>
    
    <div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<div class="groupBox">
        		<input type="hidden" id="customerid" value="${customeId!''}"/>
            	<p><em>驾友网社区：</em>共<span>${groupcount}</span>个小组，<span>${groupmember}</span>个成员。欢迎加入我们，获取最新车资讯。</p>
                <div class="group_search mt10">
                	<em>小组搜索：</em>
                    <input class="g_search_text" type="text" />
                    <input class="g_search_btn" type="button" value="搜索" />
                </div><!--/group_search-->
            </div><!--/groupBox-->
            
            <div class="all_team mt20">
            	<div class="tit_wp clearfix">
                	<h3 class="fl">
                	<#if groupTypeName??>
                	  ${groupTypeName}小组
					<#else>全部小组               	
                	</#if>
                	</h3>
                    <div class="team_filter down_wp fr">
                    	<em>成员最多</em>
                        <ul class="tf_list down_list mt5 mb5">
                        	<li class="cur" value="1"><a href="javascript:;">成员最多</a></li>
                            <li value="2"><a href="javascript:;">话题最多</a></li>
                            <li value="3"><a href="javascript:;">最新创建</a></li>
                        </ul><!--/tf_list-->
                    </div><!--/team_filter-->
                </div><!--/tit_wp-->
                
                <ul class="team_list clearfix mt20">
                	
                    <#if (pb.list)??>
                      <#list pb.list as group>
                        <li class="clearfix">
                    	<a class="fl team_img" href="groupdetail/${group.groupId}.html"><img alt="" src="${basePath}/social/images/images_18.jpg" title="${group.groupName}"/></a>
                        <div class="team_info fl ml10">
                        	<a class="team_name" href="groupdetail/${group.groupId}.html">${group.groupName}</a>
                            <p class="member_num mt5"><a href="groupdetail/${group.groupId}.html">${group.groupmember}</a></p>
                            <p class="team_intro">${group.groupRemark}</p>
                        </div><!--/team_info-->
                    </li>
                      </#list>
                    </#if>
                </ul><!--/team_list-->
                
                <div class="pages tc mt30">
                    
                	<a class="pg_prev" href="javascript:void(0);" style="color:#999;visibility:hidden">&lt;上一页</a>
                    <a class="cur" href="javascript:;">1</a>
                    <a href="javascript:;">2</a>
                    <a href="javascript:;">3</a>
                    <a href="javascript:;">4</a>
                    <a href="javascript:;">5</a>
                    <a href="javascript:;">6</a>
                    <a class="pg_next" href="javascript:;">下一页&gt;</a>
                </div><!--/pages-->
            </div><!--/all_team-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<a class="create_team" href="javascript:void(0);" onclick="creategroup()">创建小组</a>
            
            <div class="my_group none" id="my_group"><a href="myjoinedgroup.html;" id="manager">我管理/加入的小组</a></div>
            
            <div class="group_sort mt30">
            	<h3>小组分类</h3>
                <div class="g_sort mt10">
                	<ul class="g_sort_list clearfix">
                    	<li><a href="groupList.html">全部</a></li>
                        <#if grouptypelist??>
	                        <#list grouptypelist as groupType>
	                        	<li><a href="groupList.html?groupTypeId=${groupType.groupTypeId}">${groupType.groupTypeName}</a></li>
	                        </#list>
                        </#if>
                    </ul><!--/g_sort_list-->
                </div><!--/g_sort-->
            </div><!--/group_sort-->
            
            <div id="gg_slides" class="mt20">
            	<div class="slides_container">
                	<div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_17.jpg" width="243" height="284" /></a></div>
                    <div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_17.jpg" width="243" height="284" /></a></div>
                    <div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_17.jpg" width="243" height="284" /></a></div>
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
        </div><!--/right_cont-->
    </div><!--/wrapper-->
    
    <div>
		<#include "footer.ftl" />
	</div>
</body>
</html>
