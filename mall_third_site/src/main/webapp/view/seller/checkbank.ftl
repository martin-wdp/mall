<!doctype html>
<html lang="zh-CN">
<#assign basePath=request.contextPath>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>我的店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet"> 
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>
<body>

<#--引入头部-->
<#include "../index/indextop.ftl">
<input type="hidden" value="${basePath}" id="basePath"/>

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
                <li><a href="javascript:;">我的店铺</a></li>
                <li class="active">银行账号验证</li>
            </ol>

            <div class="app-content">
                <ul class="process-nav process-04 clearfix">
                    <li class="current">申请验证</li>
                    <li>待打款</li>
                    <li>已打款</li>
                    <li>验证结果</li>
                </ul>

                <div class="box-wp">
                    <div class="cont-item">
                        <div class="title">
                            <h3>银行账户信息确认</h3>
                        </div>
                        <div class="info-cont">
                            <p class="text-success">使用相同银行信息的多家店铺，只需其中一家店铺通过验证即可。</p>
                            <div class="bg-info account-info-box">
                                <h4>收款银行结算账号信息</h4>
                                <div class="info-item">
                                    <label class="control-label">银行开户名：</label>
                                    <div class="controls">${info.bankUsername!''}</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">公司银行账号：</label>
                                    <div class="controls">${info.bankCardId!''}</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">开户支行联行号：</label>
                                    <div class="controls">${info.bankId!''}</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">开户行支行名称：</label>
                                    <div class="controls">${info.bankName!''}</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">开户银行所在地：</label>
                                    <div class="controls">
                                    <#if info.bankAddr??>
                                        ${(info.bankAddr)?split(',')[0]!''}
                                        ${(info.bankAddr)?split(',')[1]!''}
                                        ${(info.bankAddr)?split(',')[2]!''}
                                    </#if>
                                    </div>
                                </div>
                            </div>

                            <label class="choose-label"><input id="chk_bx" type="checkbox"/>确认以上银行信息无误</label>

                            <div class="bt-operation">
                                <button class="btn btn-primary" type="button" onclick="javascript:checkbank()">申请验证</button>
                                <label>银行信息有误，<a href="sellerinfo.html?n=2&l=17">《点此申请修改银行信息》</a></label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<#--没有选中行提示框-->
<div class="modal fade" id="select-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="control-label">请确认您的信息！</label>
                </div>
            </div>
            <div class="modal-footer">
            <#--<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>-->
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<!--模板相关END-->
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script>
    function checkbank(){
        if($("#chk_bx").prop("checked")){
            location.href="tobankcheck.html";
        }else{
            $("#select-tip").modal('show');
        }
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
</body>
</html>