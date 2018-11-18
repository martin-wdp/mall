<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>创建小组-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
</head>
<body>
		<#if (topmap.temp)??>
			<#if (topmap.temp.tempId==1)>
				<#include "../index/topnew.ftl">
			<#elseif (topmap.temp.tempId==3)>
				<#include "../index/newheader.ftl">
			<#elseif (topmap.temp.tempId==9)>
				<#include "../index/newheader4.ftl">
			<#elseif (topmap.temp.tempId==10)>
				<#include "../index/newheader5.ftl">
			<#elseif (topmap.temp.tempId==11)>
				<#include "../index/newheader6.ftl">
			<#elseif (topmap.temp.tempId==12)>
				<#include "../index/newheader7.ftl">
			<#elseif (topmap.temp.tempId==13)>
				<#include "../index/newheader8s.ftl">
			<#elseif (topmap.temp.tempId==14)>
				<#include "../index/newheader9.ftl">
			<#elseif (topmap.temp.tempId==15)>
				<#include "../index/newheader10.ftl">
			<#elseif (topmap.temp.tempId==16)>
				<#include "../index/newheader11.ftl">
			<#elseif (topmap.temp.tempId==17)>
				<#include "../index/newheader12.ftl">
			<#elseif (topmap.temp.tempId==18)>
				<#include "../index/newheader13.ftl">
			<#elseif (topmap.temp.tempId==19)>
				<#include "../index/newheader14.ftl">
            <#elseif (topmap.temp.tempId==20)>
                <#include "../index/newheader15.ftl">
			<#else>
				<#include "../index/newheader3.ftl">
			</#if>
		</#if>
<div class="wp clearfix pt20">
    	<div class="left_cont fl">
        	<div class="create_wp">
        		<h3 class="m_tit">选择您要创建的小组类型</h3>
                <div class="team_sort">
                	<div class="ts_name">
                    	<input type="radio" name="group" checked="checked" value="0"/>
                        <label>公开小组</label>
                    </div><!--/ts_name-->
                    <div class="team_desc">
                    	<p>qpmall的任何成员都可见。</p>
                        <p>可以设置成员的加入方式。</p>
                        <p>非小组成员可以浏览小组话题。</p>
                        <p>以后可以变为私密小组。</p>
                    </div><!--/team_desc-->
                </div><!--/team_sort-->
                <div class="team_sort">
                	<div class="ts_name">
                    	<input type="radio" name="group"value="1" />
                        <label>私密小组</label>
                    </div><!--/ts_name-->
                    <div class="team_desc">
                    	<p>仅本小组成员可见。</p>
                        <p>可以设置成员的加入方式。</p>
                        <p>仅小组成员可以浏览小组话题。</p>
                        <p>不可以变为公开小组。</p>
                    </div><!--/team_desc-->
                </div><!--/team_sort-->
                <input class="group_btn" type="button" value="创建小组"  onclick="tocreate()"/>
                <script type="text/javascript">
					$(function() {
						$(".team_sort").jqTransform();
					});
					
					function tocreate(){
					   var type = $('input:radio[name="group"]:checked').val();
					   window.location.href = "${basePath}/togrouptype.html?type="+type;
				    }
				</script>
            </div><!--/create_wp-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="group_explain">
            	<h4>真的要创建一个小组吗？</h4>
                <p>如果想就某一类话题跟别人交流，可以创建一个小组。小组是对同一个话题感兴趣的人的聚集地。</p>
                <p>每个人最多可以管理和申请创建15个小组，最多可以参加250个小组。</p>
                <p>你感兴趣的话题很有可能正在被某个小组热烈讨论，建议您现在下面找找。</p>
            </div><!--/group_explain-->
        </div><!--/right_cont-->
   </div>
   <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript">
		 $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });
</script>
</body>
</html>