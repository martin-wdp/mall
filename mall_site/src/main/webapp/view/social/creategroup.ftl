<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#assign basePath=request.contextPath>
<title>驾友网——创建小组页</title>
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/base.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/jqtransform.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/social/css/style.css" />
<script type="text/javascript" src="${basePath}/social/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/social/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/social/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/social/js/default.js"></script>
</head>

<body>
	<#include "header.ftl">
    
    <div class="wp clearfix">
    	<div class="left_cont fl">
        	<div class="create_wp">
        		<h3 class="m_tit">选择您要创建的小组类型</h3>
                <div class="team_sort">
                	<div class="ts_name">
                    	<input type="radio" name="group" checked="checked" value="0"/>
                        <label>公开小组</label>
                    </div><!--/ts_name-->
                    <div class="team_desc">
                    	<p>驾友网的热河成员都可见。</p>
                        <p>可以设置成员的加入方式。</p>
                        <p>非小组成员可以浏览小组话题。</p>
                        <p>以后可以变为私密小组。</p>
                    </div><!--/team_desc-->
                </div><!--/team_sort-->
                <div class="team_sort">
                	<div class="ts_name">
                    	<input type="radio" name="group" value="1"/>
                        <label>私密小组</label>
                    </div><!--/ts_name-->
                    <div class="team_desc">
                    	<p>驾友网的热河成员都可见。</p>
                        <p>可以设置成员的加入方式。</p>
                        <p>非小组成员可以浏览小组话题。</p>
                        <p>以后可以变为私密小组。</p>
                    </div><!--/team_desc-->
                </div><!--/team_sort-->
                <input class="group_btn" type="button" value="创建小组" onclick="grouptype()"/>
                <script type="text/javascript">
					$(function() {
						$(".team_sort").jqTransform();
					});
				</script>
            </div><!--/create_wp-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="group_explain">
            	<h4>真的要创建一个小组吗？</h4>
                <p>如果想就某一类话题（例如自助游、二手车、修车等）跟别人交流，可以创建一个小组。小组是对同一个话题感兴趣的人的聚集地。</p>
                <p>每个人最多可以管理和申请创建15个小组，最多可以参加250个小组。</p>
                <p>你感兴趣的话题很有可能正在被某个小组热烈讨论，建议您现在下面找找。</p>
            </div><!--/group_explain-->
        </div><!--/right_cont-->

    </div><!--/wp-->
    
   <#include "footer.ftl">
</body>

<script>
  function grouptype(){
    var type = $('input:radio[name="group"]:checked').val();
    window.location.href = "${basePath}/togrouptype.html?type="+type;
  }
</script>
</html>
