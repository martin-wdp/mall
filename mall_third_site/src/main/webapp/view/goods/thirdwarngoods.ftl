<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>商品管理</title>
    <#assign basePath=request.contextPath />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/third.css" />
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${basePath}/js/third.js"></script>
    <script type="text/javascript">
		function updateform(){
			   var swvalue = $("#swvalue").val();
			   var g=/^[0-9]\d*$/;
						if(swvalue != null && swvalue.trim().length != 0){
							if(isNaN(swvalue)==false && g.test(swvalue)==true){
						$('#updateForm').submit();
						$(".swValueTips").text("修改的库存预警下限成功!");
						$(".swValueTips").addClass("ui-state-highlight");
						$("#swvalue").addClass("ui-state-error");
							}else{		
								$(".swValueTips").text("输入的库存预警下限必须为数字并且为正整数!");
								$(".swValueTips").addClass("ui-state-highlight");
								$("#swvalue").addClass("ui-state-error");
								return;
							}						
						}else{
							$(".swValueTips").text("请输要设置的库存预警下限!");
							$(".swValueTips").addClass("ui-state-highlight");
							$("#swvalue").addClass("ui-state-error");
							return;
						}	
			    
		}

        /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
        function show(){
            $(".show_text").fadeOut(1000).fadeIn(1000);
        }
        setTimeout("show()",1000);
</script>
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
                <li>商品管理</li>
                <li class="active" style="color: #07d;">预警修改</li>
            </ol>

            <div class="app-content">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="javascript:;">修改预警</a>
                    </li>
                </ul>
                <div class="form-block">
                	<form action="updatestockgoods.htm" class="sub_delThirdOrder" method="post" id="updateForm">
                    <div class="info-item">
                        <label class="control-label"><b>*</b>设置预警值：</label>
                        <div class="controls">
                            <input class="form-control" type="text" name="swvalue" id="swvalue" value="${(warnstock.swvalue)!"" }"/>
                            <label class="swValueTips"></label>
                        </div>
                    </div>
                    </form>
                    <div class="info-item">
                        <div class="controls">
                            <button class="btn btn-primary" type="button" onclick="updateform();">提交</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../common/leftfooter.ftl">

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script>
    $(function(){
    	$.material.init();
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        })
    });
</script>
 <script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
 <#import "../common/selectindex.ftl" as selectindex>
	<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
</html>