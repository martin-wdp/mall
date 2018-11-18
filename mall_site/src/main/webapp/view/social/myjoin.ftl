<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#assign basePath=request.contextPath>
<title>驾友网——小组首页</title>
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/base.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/style.css" />
<script type="text/javascript" src="${basePath}/social/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/social/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/social/js/jcarousellite_1.0.1.min.js"></script>
<script type="text/javascript" src="${basePath}/social/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_comm.js"></script>
</head>

<body>
	<div>
		<#include "header.ftl" />
	</div>
    <div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<div class="groupBox">
        	    <input type="hidden" id="customerid" value="${customeId!''}"/>
            	<p><em>驾友网社区：</em>共<span>365</span>个小组，<span>5687</span>个成员。欢迎加入我们，获取最新车资讯。</p>
                <div class="group_search mt10">
                	<em>小组搜索：</em>
                    <input class="g_search_text" type="text" />
                    <input class="g_search_btn" type="button" value="搜索" />
                </div><!--/group_search-->
            </div><!--/groupBox-->
            
            <div class="hot_group mt20">
            	<h3 class="tit">我管理的小组</h3>
                <div class="group_wp mt20 clearfix">
                	<div class="group_box clearfix fl">
                    	<a class="group_img fl" href="javascript:;"><img alt="" src="${basePath}/social/images/images_02.jpg" /></a>
                        <div class="group_info fl ml10">
                        	<a class="group_name" href="javascript:;">汽车用品大收罗</a>
                            <div class="g_info mt10">
                            	<a class="m_num" href="javascript:;">6577</a>
                                <a class="c_num" href="javascript:;">15044</a>
                            </div><!--/g_info-->
                            <p>汽车服务,汽车养护,汽车装饰,汽车用品加盟优惠还送大礼!赶快参加吧!!!汽车用品,值得您的信赖!</p>
                        </div><!--/group_info-->
                    </div><!--/group_box-->
                   
                </div><!--/group_wp-->
            </div><!--/hot_group-->
            
            <div class="other_group mt20">
            	<div class="tit_wp clearfix">
                	<h3 class="tit fl">我加入的小组</h3>
                </div><!--/tit_wp-->
                <ul class="other_group_list mt30 clearfix">
                	<li><a href="javascript:;"><img alt="" src="${basePath}/social/images/images_16.jpg" />爱猫俱乐部</a></li>
                </ul><!--/other_group_list-->
            </div><!--/other_group-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<a class="create_team" href="javascript:void(0);" onclick="creategroup()">创建小组</a>
            
            <div class="my_group none" id="my_group">我<a href="javascript:;">管理</a> / <a href="javascript:;">加入</a>的小组</div>
            
            <div class="group_sort mt30">
            	<h3>小组分类</h3>
                <div class="g_sort mt10">
                	<ul class="g_sort_list clearfix">
                    	<li><a href="javascript:;">全部</a></li>
                        <#list grouptypelist as groupType>
                        	<li><a href="groupList.html?groupTypeId=${groupType.groupTypeId}">${groupType.groupTypeName}</a></li>
                        </#list>
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
    
    <#include "footer.ftl">
    <script>
     function creategroup(){
        window.location.href = "${basePath}/togroupcreate.html";
      }    
    </script>
</body>
</html>
