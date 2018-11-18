<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#assign basePath=request.contextPath>
<title>驾友网——公开小组页</title>
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
    
    <div class="wp clearfix">
    	<div class="left_cont fl">
    	<form id="creategroup" enctype="multipart/form-data"  modelAttribute="uploadForm">
    	<input type="hidden" name="type" value="${type}">
    	<input type="hidden" name="groupCreateAuthorId" value="${customeId}">
        	<div class="create_wp">
        		<h3 class="m_tit">申请创建一个公开小组</h3>
                <dl class="team_dl clearfix mt30 ml10">
                	<dt>小组名称：</dt>
                    <dd>
                    	<div class="rowElem clearfix">
                        	<input class="t_text" type="text" name="groupName" id="groupName" onkeyup="textLimt(this,15)"/>
                        </div><!--/rowElem-->
                         <p class="" style="display:none" id="namelength"><font color="red"><span id="namelimit"><span></font></p>
                    </dd>
                    <dt>选择头像：</dt>
                    <dd>  
						<div class="rowElem  jqtransformdone">
						 <input id="groupPic" type="file" name="groupPic"></br>
						 <p><span class=" LV_validation_message LV_invalid" id="groupHead"  style="display:none"><font color="red">请上传小组头像！</font></span></p>
						</div> 
					</dd>                   
                    <dt>小组介绍：</dt>
                    <dd>
                    	<div class="rowElem clearfix">
                        	<textarea class="t_ta" name="groupRemark" id="groupRemark" onkeyup="textLimt(this,500)" maxlenth="500"></textarea>
                        </div><!--/rowElem-->
                         <p class="" id="remarklength"><font color="red"><span id="remarklimit">简介不得超过500个字，你还可以输入 </span><span id="num">500</span></font></p>
                    </dd>
                    <dt>小组分类：</dt>
                    <dd>
                    	<ul class="rowElem ts_list clearfix">
                    	    <#list grouptypelist as groupType>
                        	 <li><input type="radio" name="groupTypeId"  value="${groupType.groupTypeId}"
                        	  <#if groupType_index = 0>checked="checked"
                        	  </#if>/><label>${groupType.groupTypeName}</label>
                        	 </li>
                            </#list>
                        </ul><!--/rowElem-->
                    </dd>
                    <dt>加入方式：</dt>
                    <dd>
                    	<ul class="rowElem join_sort">
                        	<li><input type="radio" name="limitAddType" checked="checked" value="1"/><label>需要小组管理人员批准后才能加入小组</label></li>
                            <li><input type="radio" name="limitAddType" value="2"/><label>只有接受组员邀请才能加入小组</label></li>
                            <li><input type="radio" name="limitAddType" value="0"/><label>允许任何人加入小组</label></li>
                        </ul><!--/join_sort-->
                    </dd>
                    <dt>加入条件：</dt>
                    <dd>
                    	<ul class="rowElem join_condition">
                        	<li><input type="radio" name="limitCondition" checked="checked" value="0"/><label>全部</label></li>
                            <li><input type="radio" name="limitCondition" value="1" /><label>只女性用户</label></li>
                            <li><input type="radio" name="limitCondition" value="2" /><label>只男性用户</label></li>
                        </ul><!--/join_condition-->
                    </dd>
                    <dt>&nbsp;</dt>
                    <dd>
                    	<div class="rowElem">
                        	<input type="checkbox" checked="checked" id="agreement" onclick="showAgree()"/>
                            <label class="agreement">我已认真阅读并同意<a href="javascript:;">《小组指导原则》</a></label><br/>
                        	<span id="unagreement" class="LV_validation_message LV_invalid" style="display:none"><font color="red">您尚未同意《小组指导原则》条款！</font></span>
                        </div><!--/rowElem-->
                    </dd>
                    <dt>&nbsp;</dt>
                    <dd><input class="group_btn" type="button" value="提交申请" id="save"/></dd>
                </dl><!--/team_dl-->
                <script type="text/javascript">
					$(function() {
						$(".rowElem").jqTransform();
					});
				</script>
            </div><!--/create_wp-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="group_explain">
            	<h4>小组创建</h4>
                <p>创建小组申请需要审核通过后才能完成创建，管理员会在3个工作日内审核申请，审核结果会用邮件方式通知您，请耐心等待。</p>
                <p>更多小组说明，请查看<a href="javascript:;">帮助中心</a>。</p>
                <p>小组的名称，介绍在创立后都可以随时更改。</p>
            </div><!--/group_explain-->
        </div><!--/right_cont-->

    </div><!--/wp-->
    
    <#include "footer.ftl">
    
    <div class="mask"></div>
    <div class="dialog" style="height:255px;" id="successDialog">
    	<a class="close" href="javascript:cls();" onclick="regroup()"></a>
    	<h4>提示</h4>
        <h3 class="m_tit tc mt30">恭喜您，小组创建申请已成功提交！</h3>
        <p class="tc f14 lh180 mt20">您的小组申请已成功提交，请耐心等待我们工作人员的审核，我们会在第一时间通过消息中心通知您审核结果，感谢您对驾友网的支持！</p>
        <div class="tc mt30 d_lk"><a href="${basePath}/group.html">返回小组列表</a>
        <a href="${basePath}/group.html">返回驾友网首页</a></div>
    </div><!--/dialog-->
</body>
</html>
