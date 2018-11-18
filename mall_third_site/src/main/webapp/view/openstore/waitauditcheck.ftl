<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>创建店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
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
                            <p>贵公司的店铺申请材料已经被打回</p>
                            <p>打回原因：<#if storeinfo.refuseContent??>${storeinfo.refuseContent}</#if></p>
                        </div>
                    </div>
                </div>
                <a onclick="javascript:window.location.href='goenterpage.html'" style="margin-left: 300px; margin-top:200px; cursor: pointer;">返回店铺进入页面>></a>
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
<script type="text/javascript" src="${basePath}/js/app.js"></script>


<script>
    $(function(){
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        })
    });
</script>
</html>
