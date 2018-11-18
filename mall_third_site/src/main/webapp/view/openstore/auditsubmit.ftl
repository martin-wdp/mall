<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>创建店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>

</head>
<body>
<div class="container">
    <div class="store">
        <div class="wrapper-app">
            <div id="header">
            <#include "../index/top.ftl">
            <ul class="process-nav process-03 clearfix">
                <li>1.在线协议</li>
                <li>2.商家信息提交</li>
                <li class="current">3.店铺开通</li>
            </ul>
            </div>

            <div id="content">
                <div class="create-wrapper">
                    <div class="explain-con">
                        <i class="success-icon"></i>
                        <div class="explain-info">
                            <p>已经收到贵公司提交的在线入驻审核信息，等待我们分配审核人员；</p>
                            <p>请及时登录系统查看入驻审核情况。</p>
                        </div>
                    </div>
                </div>
                <a onclick="javascript:window.location.href='login.html'" style="margin-left: 300px; margin-top:200px; cursor: pointer;">返回店铺登录页面>></a>
            </div>
        </div>
        <div class="footer">
        <#include "../index/foot.ftl">
        </div>
    </div>
</div>
</body>
<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>


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
</html>
