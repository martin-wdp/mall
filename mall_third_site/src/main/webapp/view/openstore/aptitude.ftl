<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>创建店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" >
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/third.css" />
</head>

<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/sellervalidate.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/seller.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/openstore.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script>
    $(function(){
    	$.material.init();
        loadProvice();
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        })
    });
</script>
<style>
    .n_error{border-color:#f2445d!important;}

</style>
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
                    <form method="post" id="aptitude_from" action="${basePath}/saveaptitude.htm" enctype="multipart/form-data" class="form-horizontal">
                        <div class="form-item">
                            <h3>营业执照信息（副本）</h3>
                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>法定代表人姓名：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" maxlength="15" name="bussLegalName" id="bussLegalName" value="${(info.bussLegalName)!''}"/>
                                        <p id=bussLegalNameTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>身份证号：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" maxlength="18" name="bussLegalCardId" id="bussLegalCardId" value="${(info.bussLegalCardId)!''}"/>
                                        <#--<p  >身份证号错误</p>-->
                                        <p id=bussLegalCardIdTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>法人身份证电子版：</label>
                                    <div class="controls col-xs-7" style="margin-top:7px;">
                                        <input type="file" name="cardUrlE" id="cardUrlE"/><#--<a class="blu_bt" href="javascript:;">取消</a>-->
                                        <div class="alert">以下所需要上传电子版资质仅支持JPG、GIF、PNG格式的图片，大小不超过1M。请确保图片清晰，文字可辨并有清晰的红色公章</div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>营业执照号：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" maxlength="15" name="bussId" id="bussId" value="${(info.bussId)!''}"/>
                                        <p id=bussIdTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>营业执照所在地：</label>
                                    <div class="controls col-xs-7">
                                    	<div class="row">
                                    		<div class="col-xs-4">
                                    			<select name="bussAddrId" id="province" class="form-control sm-sel province" <#if (info.bussAddrId)??>value="${(info.bussAddrId)?split(',')[0]!''}"</#if>>
		                                            <option value="">请选择</option>
		                                        </select>
                                    		</div>
                                    		<div class="col-xs-4">
                                    			<select name="bussAddrId" id="city" class="form-control sm-sel city" <#if (info.bussAddrId)??> value="${(info.bussAddrId)?split(',')[1]!''}"</#if>>
		                                            <option value="">请选择</option>
		                                        </select>
                                    		</div>
                                    		<div class="col-xs-4">
                                    			<select name="bussAddrId" id="district" class="form-control sm-sel district" <#if (info.bussAddrId)??> value="${(info.bussAddrId)?split(',')[2]!''}"</#if>>
		                                            <option value="">请选择</option>
		                                        </select>
                                    		</div>
                                    	</div>
                                        <p id=companyEmpNumTip class=hide><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>营业执照详细地址：</label>
                                    <div class="controls col-xs-7">
                                        <input maxlength="30" name="bussAddr" id="bussAddr" value="${(info.bussAddr)!''}" type="text" class="form-control"/>
                                        <p id=bussAddrTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>成立日期：</label>
                                    <div class="controls col-xs-7">
                                        <input class="form-control datepicker companyCreTime" data-provide="datepicker"  value="${(info.companyCreTime)!''}" name="companyCreTime" id=""  />
                                        <p id=companyCreTimeTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>营业执照有效期：</label>
                                    <div class="controls col-xs-7">
                                    	<div class="row">
                                    		<div class="col-xs-5">
                                    			<input class="form-control datepicker bussDate" value="<#if info??&&info.bussDate??>${((info.bussDate)?split(',')[0])!''}</#if>" data-provide="datepicker" name="bussDate" id=""/>
                                    		</div>
                                    		<div class="col-xs-1 col-line">-</div>
                                    		<div class="col-xs-5">
                                    			<input class="form-control datepicker bussDatet" value="<#if info??&&info.bussDate??>${((info.bussDate)?split(',')[1])!''}</#if>" data-provide="datepicker" name="bussDate" id="" />
                                    		</div>
                                    	</div>
                                        <p id=bussDateTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>法定经营范围：</label>
                                    <div class="controls col-xs-7">
                                        <textarea name="bussRange" id="bussRange" class="form-control">${(info.bussRange)!''}</textarea>
                                        <p id=bussRangeTip  ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>营业执照电子版：</label>
                                    <div class="controls col-xs-7" style="margin-top:7px;">
                                        <input type="file" name="bussUrlE" id="bussUrlE"/>
                                        <div class="alert">以下所需要上传电子版资质仅支持JPG、GIF、PNG格式的图片，大小不超过1M。请确保图片清晰，文字可辨并有清晰的红色公章</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-item">
                            <h3>组织机构代码证</h3>
                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>组织机构代码：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" maxlength="10" name="bussDeptNo" id="bussDeptNo" value="${(info.bussDeptNo)!''}" class="form-control"/><span></span>.
                                        <p class="alert" id=bussDeptNoTip  ><i></i><i class=border></i><s></s><span></span></p>
                                        <div class="alert">由8位数字（或大写拉丁字母）本体代码和1位数字（或大写拉丁字母）校验码组成.例：0000XXXX-0</div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>组织机构代码证电子版：</label>
                                    <div class="controls col-xs-7" style="margin-top:7px;">
                                        <input type="file" name="companyResearchUrlE" id="companyResearchUrlE"/>
                                        <div class="alert">以下所需要上传电子版资质仅支持JPG、GIF、PNG格式的图片，大小不超过1M。请确保图片清晰，文字可辨并有清晰的红色公章</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-item">
                            <h3>一般纳税人证明</h3>
                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">一般纳税人证明：</label>
                                    <div class="controls col-xs-7" style="margin-top:7px;">
                                        <input type="file" name="bussTaxCredUrlE" id="bussTaxCredUrlE"/>
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
                                        <button class="btn btn-large btn-primary" type="button" onclick="javascript:window.location.href='engageinfo.html'">上一步</button>
                                        <button class="btn btn-large btn-primary apti_btn" type="button" >保存并下一步</button>
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
<script>
    $(function(){
        $("select[name='bussAddrId']").each(function(){
            var _val = $(this).attr("value"),
                    _this = $(this);
            _this.find("option").each(function(){
                if($(this).val() == _val) {
                    $(this)[0].selected = 'selected';
                    _this.change();
                };
            });
        });
    });
</script>
</body>



</html>
