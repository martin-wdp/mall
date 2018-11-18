<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>创建店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
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
                <ul class="process-nav process-03 clearfix">
                    <li>1.在线协议</li>
                    <li class="current">2.商家信息提交</li>
                    <li>3.店铺开通</li>
                </ul>
            </div>

            <div id="content">
                <div class="create-wrapper">
                    <form  class="form-horizontal" method="post" id="enga_from" action="${basePath}/saveengageinfo.htm" enctype="multipart/form-data">
                        <div class="form-wp">
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>公司名称：</label>
                                <div class="controls col-xs-7">
                                    <input type="text" name="companyName" id="companyName" value="${(info.companyName)!''}"  class="form-control"/>
                                    <p id="companyNameTip"  ><i></i><i class=border></i><s></s><span></span></p>
                                    <div class="alert">是店铺所属的公司名称，认证后不可修改</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>公司地址：</label>
                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control" name="companyAddrDel" id="companyAddrDel" value="${(info.companyAddrDel)!''}"/>
                                    <p id=companyAddrDelTip  ><i></i><i class=border></i><s></s><span></span></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>公司电话：</label>
                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control" name="companyTel" id="companyTel" value="${(info.companyTel)!''}"/>
                                    <span>例如:025-81144376</span>
                                    <p id=companyTelTip  ><i></i><i class=border></i><s></s><span></span></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>电子邮箱：</label>
                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control" name="companyEmail" id="companyEmail" value="${(info.companyEmail)!''}" />
                                    <p id=companyEmailTip  ><i></i><i class=border></i><s></s><span></span></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3">员工总数：</label>
                                <div class="controls col-xs-3">
                                    <input type="text" class="form-control"  name="companyEmpNum" value="${(info.companyEmpNum)!''}"  onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',
                                         clipboardData.getData('text').replace(/[^\d]/g,''))"   />
                                    <p id=companyEmpNumTip  ><i></i><i class=border></i><s></s><span></span></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>注册资金：</label>
                                <div class="controls col-xs-3">
                                	<div class="input-group">
                                		<input type="text" id="companyCapital" class="form-control sm-control" class="glinput" name="companyCapital"  onkeyup="value=value.replace(/[^\d]/g,'') "  onbeforepaste="clipboardData.setData('text',
                                         clipboardData.getData('text').replace(/[^\d]/g,''))"  value="${(info.companyCapital)!''}"/>
                                        <span class="input-group-addon">万元</span>
                                	</div>
                                    <p id=companyCapitalTip  ><i></i><i class=border></i><s></s><span></span></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3"><b>*</b>联系人姓名：</label>
                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control" name="companyContactName" id="companyContactName" value="${(info.companyContactName)!''}"/>
                                    <p id=companyContactNameTip  ><i></i><i class=border></i><s></s><span></span></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3">联系人电话：</label>
                                <div class="controls col-xs-7">
                                    <input type="text" class="form-control companyContactTel"  name="companyContactTel"  value="${(info.companyContactTel)!''}" onkeyup="value=value.replace(/[^\d]/g,'') "  onbeforepaste="clipboardData.setData('text',
                                         clipboardData.getData('text').replace(/[^\d]/g,''))"  />
                                    <p id=companyContactTelTip  ><i></i><i class=border></i><s></s><span></span></p>
                                </div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-xs-3"></label>
                            	<div class="controls col-xs-7">
                                    <button class="btn btn-large btn-primary" type="button" onclick="javascript:window.location.href='showprotocol.html'">上一步</button>
                                    <button class="btn btn-large btn-primary enga_btn"  type="button">保存并下一步</button>
                                </div>
                            </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="footer">
        <#include "../index/foot.ftl">
        </div>
    </div>
</div>
</body>
    <script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${basePath}/js/sell/sellervalidate.js"></script>
    <script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/ripples.min.js"></script>
	<script src="${basePath}/js/material.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/app.js"></script>
    <script type="text/javascript" src="${basePath}/js/third.js"></script>
    <script type="text/javascript" src="${basePath}/js/sell/openstore.js"></script>
    <script>
		$(function(){
			$.material.init();
		});
	</script>
</html>
