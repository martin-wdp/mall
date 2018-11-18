<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>创建店铺</title>
    <link rel="stylesheet" type="text/css" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/third.css" />
</head>
<body>
<div class="container">
    <div class="store">
        <div class="wrapper-app">
            <div id="header">
                <#include "../index/top.ftl">
            </div>
            <div class="addition">
                <ul class="process-nav process-03 clearfix">
                    <li class="current">1.在线协议</li>
                    <li>2.商家信息提交</li>
                    <li>3.店铺开通</li>
                </ul>
            </div>
            <div id="content" style="text-align: left;">
                <div class="create-wrapper">
                    请仔细阅读以下商城服务协议及规则：
                    <div class="agreement-cont">
                        <div style=" height: 500px; overflow-y:auto;overflow-x:hidden;">
                        ${basicest.bsetUseragreementuser!''}
                        </div>
                        <label class="agree-opt"><input type="checkbox" id="read_pro" />我已阅读并同意以上协议</label><span id="tip_span" class="hide">请先阅读入驻协议!</span>
                        <div class="create-opt">
                            <button class="btn btn-large btn-primary" type="button"  onclick="javascript:window.location.href='goenterpage.html'">上一步</button>
                            <button class="btn btn-large btn-primary" type="button"  onclick="confirmOpen();">下一步</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <#include "../index/foot.ftl">
    </div>
</div>



<#--提示框-->
<div class="modal fade" id="check_point" role="dialog" aria-hidden="true" >
    <input type="hidden" value="" class="exp_id_hide" />
    <input type="hidden" value="" class="flag_id_hide" />
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">请先阅读入驻协议!</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/openstore.js"></script>
<script>
	$(function(){
		$.material.init();
	});
</script>

</html>
