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
                    <form method="post" id="aptitude_from" action="${basePath}/savetax.htm" enctype="multipart/form-data" class="form-horizontal">
                        <input type="hidden" id="bankAddr" name="bankAddr"/>
                        <div class="form-item">
                            <h3>开户行银行许可证</h3>
                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>银行开户名：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" name="bankUsername" id="bankUsername" value="${(info.companyName)!''}"/>
                                        <p id=bankUsernameTip ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3">公司银行账号：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" name="bankCardId"
                                               onkeyup="value=value.replace(/[^\d]/g,'') "
                                               onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
                                               value="${(info.bankCardId)!''}"/>
                                        <p id=bankCardIdTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3">开户银行支行名称：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" name="bankName"  value="${(info.bankName)!''}"/>
                                        <p id=bankNameTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b></b>支行银行号：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" name="bankId" id="bankId" value="${(info.bankId)!''}"/>
                                        <p id=bankIdTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b></b>开户银行所在地：</label>
                                    <div class="controls col-xs-7">
                                    	<div class="row">
                                    		<div class="col-xs-4">
                                    			<select  id="province" class="form-control province" >
		                                            <option value="">请选择</option>
		                                        </select>
                                    		</div>
                                    		<div class="col-xs-4">
                                    			<select  id="city" class="form-control city" >
		                                            <option value="">请选择</option>
		                                        </select>
                                    		</div>
                                    		<div class="col-xs-4">
                                    			<select  id="district" class="form-control district" >
		                                            <option value="">请选择</option>
		                                        </select>
                                    		</div>
                                    	</div>
                                        <p id=companyEmpNumTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b></b>开户银行许可证电子版：</label>
                                    <div class="controls col-xs-7" style="margin-top:7px;">
                                        <input type="file" name="bankUrlE" id="bankUrlE"/>
                                        <div class="alert">以下所需要上传电子版资质仅支持JPG、GIF、PNG格式的图片，大小不超过1M。请确保图片清晰，文字可辨并有清晰的红色公章</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-item">
                            <h3>税务登记证</h3>
                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>税务登记证号：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" name="bussTaxRegistId" id="bussTaxRegistId" value="${(info.bussTaxRegistId)!''}"/>
                                        <p id=bussTaxRegistIdTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>纳税人识别号：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" name="bussTaxPayerId" id="bussTaxPayerId" value="${(info.bussTaxPayerId)!''}"/>
                                        <p id=bussTaxPayerIdTip   ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>税务登记证电子版：</label>
                                    <div class="controls col-xs-7" style="margin-top:7px;">
                                        <input type="file" name="bussTaxRegistUrlE" id="bussTaxRegistUrlE"/>
                                        <div class="alert">以下所需要上传电子版资质仅支持JPG、GIF、PNG格式的图片，大小不超过1M。请确保图片清晰，文字可辨并有清晰的红色公章</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="form-wp">
                                <div class="form-group">
                                	<label class="col-xs-3"></label>
                                    <div class="controls col-xs-7">
                                        <button class="btn btn-large btn-primary" type="button" onclick="javascript:window.location.href='${basePath}/aptitude.html'">上一步</button>
                                        <button class="btn btn-large btn-primary tax_btn" type="button" >保存并下一步</button>
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
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/sellervalidate.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/seller.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/openstore.js"></script>
<script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script>
    $(function(){
    	$.material.init();
        loadProvice();
    });
    function changeCity(){
        var bp = $("#province").find('option:selected').text();
        var bc = $("#city").find('option:selected').text();
        var bd = $("#district").find('option:selected').text();
        var bankAddr=bp+","+bc+","+bd;
    }
</script>
</html>
