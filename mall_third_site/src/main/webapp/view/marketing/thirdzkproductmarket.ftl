<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>促销管理</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/summernote.css"/>
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>
<body>

<#--引入头部-->
<#include "../index/indextop.ftl">

<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
            <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>促销管理</li>
                <li class="active" style="color: #07d;">折扣促销</li>
            </ol>

            <div class="app-content">
                <div>
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="javascript:;">折扣促销列表</a>
                        </li>
                    </ul>
                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm" type="button" onclick="javascript:window.location.href='addzkmarketing.html'"><i class="glyphicon glyphicon-plus"></i>添加折扣</button>
                        </div>
                       <form id="delallform" method="post" action="newdelmarketings.htm" method="post" >
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="allunchecked(this,'marketingId')"/></th>
                                <th>活动名称</th>
                                <th>有效时间</th>
                                <th>促销类型</th>
                                <th>活动状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if pb.list??>
                  				<#list pb.list as market>
                  				<tr>
                                <td><input type="checkbox" name="marketingId" value="${market.marketingId}"/></td>
                                <td>${market.marketingName }</td>
                                <td>${market.marketingBegin?string("yyyy-MM-dd HH:mm:ss")} 至 ${market.marketingEnd?string("yyyy-MM-dd HH:mm:ss") }</td>
                                <td>${market.codexName }</td>
                                <td>
                                <#if (market.marketingBegin?string("yyyy-MM-dd HH:mm:ss"))?date("yyyy-MM-dd HH:mm:ss") gt nowdate?date("yyyy-MM-dd HH:mm:ss")>
                                    <span class="label label-default">未开始</span>
                                   <#elseif (market.marketingEnd?string("yyyy-MM-dd HH:mm:ss"))?date("yyyy-MM-dd HH:mm:ss") lt nowdate?date("yyyy-MM-dd HH:mm:ss")>
                                    <span class="label label-danger">已结束</span>
                                   <#elseif (market.marketingBegin?string("yyyy-MM-dd HH:mm:ss"))?date("yyyy-MM-dd HH:mm:ss") lt nowdate?date("yyyy-MM-dd HH:mm:ss") && (market.marketingEnd?string("yyyy-MM-dd HH:mm:ss"))?date("yyyy-MM-dd HH:mm:ss") gt nowdate?date("yyyy-MM-dd HH:mm:ss")>
                                    <span class="label label-primary">正在进行</span>
                                </#if>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary btn-sm" onclick="modifyMarketing(${market.marketingId},'ZK')">修改</button>
                                        <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:void(0);" onclick="deldpmarketing(${market.marketingId})">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                  				</#list>
                            </#if>
                            </tbody>
                        </table>
                       </form>
                        <div class="footer-operation">
                            <div class="ops-left">
                                <button class="btn btn-primary btn-xs" type="button" onclick="delmarketinglist()">批量删除</button>
                            </div>
	                         <#import "../pagination/pageBean.ftl" as page>
			                 <@page.pagination pb />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--<div class="service-wrap">-->
    <#--<span class="service-close">×</span>-->
    <#--<a href="javascript:;" class="service-box">联系客服</a>-->
<#--</div>-->

<div class="back-to-top">
    <a href="javascript:;"><i></i>返回顶部</a>
</div>

<#--<div class="notice-center">-->
    <#--<div class="notice-center-ring"><i></i></div>-->
    <#--<div class="notice-center-wrapper">-->
        <#--<div class="nt-header">-->
            <#--<h3>系统通知（<span>1</span>）</h3>-->
            <#--<a href="javascript:;" class="nt-close">收起》</a>-->
        <#--</div>-->
        <#--<ul class="nt-content">-->
            <#--<li>-->
                <#--<div class="nt-item unread">-->
                    <#--<p>刘仙升于2015-04-08 15:41:23申请提现1.00元，已提现成功，请注意查询您的银行账户。</p>-->
                    <#--<a href="javascript:;">查看提现记录 》</a>-->
                <#--</div>-->
            <#--</li>-->
        <#--</ul>-->
        <#--<div class="nt-footer">-->
            <#--<a href="javascript:;" class="mark-read">全部标记已读</a>-->
            <#--<a href="javascript:;" class="nt-all">查看全部信息</a>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--<div class="page-help-btn">帮助</div>-->
<div class="page-help-container">
    <div class="page-help-content">
        <p style="color:#f00;">不知道从哪里开始？</p>
        <p>完成掌柜任务，简简单单开店铺！</p>
        <p>点击开始》》<a href="javascript:;">掌柜成长之旅</a></p>
    </div>
    <div class="page-help-operation">
        <a href="javascript:;" class="btn btn-primary btn-sm">进入帮助中心</a>
        <a href="javascript:;" class="btn btn-default btn-sm">建议反馈</a>
    </div>
</div>

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/marketing/modifymarketing.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/common/common_checked.js"></script>
<script src="${basePath}/js/common/common_alert.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
<script type="text/javascript">
  //删除店铺
      function deldpmarketing(marketingId){
    	  showDeleteOneConfirmAlert("delnewmarketing.htm?marketingId="+marketingId);
      }
      
      function delmarketinglist(){
          showAjaxDeleteBatchConfirmAlert("delallform","marketingId");
      }
      $(function(){
      	$.material.init();
      });

      /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
      function show(){
          $(".show_text").fadeOut(1000).fadeIn(1000);
      }
      setTimeout("show()",1000);
</script>
</html>