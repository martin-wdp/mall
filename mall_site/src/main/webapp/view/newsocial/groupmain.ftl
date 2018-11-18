<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>${topmap.systembase.bsetName}</title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/base.min.css"/>
<link type="text/css" rel="stylesheet" href="${basePath}/css/group.css"/>
<style type="text/css">
.group_name:hover{ text-decoration:none;}
.other_group_list li a:hover{ text-decoration:none;}
.all_group:hover{ text-decoration:none;}
</style>
</head>

<body>
	<#include "../common/header.ftl" />
	<input type="hidden" id="customerid" value="${cusId!''}"/>
	<div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<div class="groupBox">
            	<p><em>社区：</em>共<span>${groupcount!'0'}</span>个小组，<span>${groupmember!'0'}</span>个成员。欢迎加入我们。</p>
                <form id="group_search_form" action="groupsearchlist.html" method="post">
                <div class="group_search mt10">
                	<em>小组搜索：</em>
                    <input class="g_search_text" type="text" name="groupName" />
                    <input class="g_search_btn" type="submit" value="搜索" />
                </div><!--/group_search-->
                </form>
            </div><!--/groupBox-->  
            <div class="hot_group mt20">
            	<h3 class="tit">活跃小组</h3>
            	 <div class="group_wp mt20 clearfix">
                   <#if activelist??>
                    <#list activelist as active>
		                	<div class="group_box clearfix fl">
		                    	<a class="group_img fl" href="groupdetail/${active.groupId}.html"><img  height="120px" width="120px" alt="${active.groupName}" src="${active.groupHead}" title="${active.groupName}"/></a>
		                        <div class="group_info fl ml10">
		                        	<a class="group_name" href="groupdetail/${active.groupId}.html">${active.groupName}</a>
		                            <div class="g_info mt10">
		                            	<a class="m_num" href="groupdetail/${active.groupId}.html">${(active.groupmember)!'0'}</a>
		                                <a class="c_num" href="groupdetail/${active.groupId}.html">${(active.topicCount)!"0"}</a>
		                            </div><!--/g_info-->
		                            <p>
		                           <#if (active.groupRemark)?length gt 50>
		                                ${(active.groupRemark)?substring(0,50)}
		                             <#else>${active.groupRemark}
		                           </#if>
		                            </p>
		                        </div><!--/group_info-->
		                    </div><!--/group_box-->
		            </#list>
                   </#if>  
                </div><!--/group_wp-->
      
            </div><!--/hot_group-->
            
            <div class="other_group mt20">
            	<div class="tit_wp clearfix">
                	<h3 class="tit fl">其他小组</h3>
                    <a class="fr all_group" href="grouplist.html">查看全部小组</a>
                </div><!--/tit_wp-->
               <ul class="other_group_list mt30 clearfix" style="width:950px">
                  <#if otherlist??>
	                  <#list otherlist as other>
	                	<li><a href="groupdetail/${other.groupId}.html"><img  height="50px" width="50px" alt="${other.groupName}" src="${other.groupHead}" title="${other.groupName}"/>
	                		<#if (other.groupName)?length gt 4>
		                                ${(other.groupName)?substring(0,4)}
		                       <#else>${other.groupName}
		                   </#if>
	                	</a></li>
	                  </#list>
                  </#if>
                </ul><!--/other_group_list-->
            </div><!--/other_group-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<a class="create_team" href="javascript:void(0);" onclick="creategroup()">创建小组</a>
            <#if cusId??>
            <div class="my_group"><a href="myjoinedgroup.html">我管理/加入的小组</a></div>
            </#if>
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
                	<div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/images/images_17.jpg" width="290" height="284" /></a></div>
                </div><!--/slides_container-->
            </div><!--/gg_slides-->
            
     
        </div><!--/right_cont-->
    </div><!--/wrapper-->
    
     <div class="mask"></div>
     <div class="dialog dia1" style="width:450px; height:170px;">
    	<a class="close" href="javascript:();"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20" style="padding:20px;" id ="message"></p>   
          
        <div class="tc mt10 d_lk"><a class="cancel_btn" href="javascript:();">关闭</a></div>
    </div><!--/dialog-->
    
   <#include "../common/footer.ftl" />
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_comm.js"></script>
 <script type="text/javascript">
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
				
	 	$(function(){
			//选中首页社区
			$(".sort_list li").removeClass("cur");
			$(".sort_list li:eq(1)").addClass("cur");
        });
	  </script>
</body>
</html>