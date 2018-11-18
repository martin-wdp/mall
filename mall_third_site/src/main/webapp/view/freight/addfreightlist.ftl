<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-运费模板添加</title>
<#assign basePath=request.contextPath />
<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" >
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>

<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/freight/freight.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${basePath}/js/bootstrap.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>

    <style type="text/css">
        .errorinput{
            border: 1px solid #cd0a0a;
            color: #cd0a0a;
        }
        .errortip{
            color: #cd0a0a;
        }

        .ct-num{
            color: #cd0a0a;
        }

        .ch_hot{float:left; line-height:30px; width:90px; padding:5px; cursor:pointer; border-radius:3px 3px 0px 0px;}
        .ch_hot_hover{background:#ddd;}
        .ch_hot_hover ul{display:block!important;}
        .ch_hot_hover .ch_close{display:block!important;}
        .ch_hot ul li{width:70px; float:left; line-height:30px; padding:5px;}
        .ch_hot ul{position:absolute; left:0px; top:40px; background:#ddd; display:none;  width:250px; border-radius:0px 0px 3px 3px; z-index:999;}
        .ch_hot .ch_close{position:absolute; right:5px; top:5px; color:#333; font-size:14px; font-family:"微软雅黑"; display:none;}
		.modal-backdrop {height: 100%;}
    </style>
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
                <li><a href="freighttemplatelistthird.htm?n=2&l=69">运费管理</a></li>
                <li class="active">新增运费模板</li>
            </ol>

            <form class="form-horizontal" action="addFreightthird.htm" method="post" id="freightForm">
                <input type="hidden" name="freightTemplateId" id="freightTemplateId" value="<#if freightTemplate??>${freightTemplate.freightTemplateId!''}</#if>" />
                <div class="app-content">
                    <div class="cont-item">
                        <div class="info-cont form-container">
                            <div class="form-group">
                                <label class="control-label col-xs-2">模板名称：</label>
                                <div class="controls col-xs-8">
                                    <input type="text" class="form-control" maxlength="20" name="freightTemplateName" id="freightTemplateName" value="<#if freightTemplate??>${freightTemplate.freightTemplateName!'' }</#if>"
                                    <#-- 禁止录入特殊字符-->
                                           onKeypress="if ((event.keyCode > 32 && event.keyCode < 48) || (event.keyCode > 57 && event.keyCode < 65) || (event.keyCode > 90 && event.keyCode < 97)) event.returnValue = false;"
                                            />
                                    <span class="spansendmail"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">发货地区：</label>
                                <div class="controls col-xs-8">
                                    <select class="form-control sm-sel" name="freightProvinceId" id="freightProvinceId" onchange="selectCity(this);">
                                        <option value="">请选择</option>
                                        <#list provinceList as province>
                                             <option value="${province.provinceId}">${province.provinceName}</option>
                                        </#list>
                                    </select>
                                    <select class="form-control sm-sel" name="freightCityId" id="city"  onchange="selectDistrict(this);">
                                        <option value="">请选择</option>
                                    </select>
                                    <select class="form-control sm-sel" name="freightCountyId" id="district">
                                        <option value="">请选择</option>
                                    </select>
                                    <span id="province"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">是否包邮：</label>
                                <div class="controls col-xs-8 checkWp radio radio-primary">
                                    <label class="choose-label"><input  name="freightPackageMail" value="0" type="radio" checked="checked"  onchange="checkMail(this,0);"/>买家承担费用</label>
                                    <label class="choose-label"><input  name="freightPackageMail" value="1" type="radio" id="sc_ct" onchange="checkMail(this,1);"/>卖家承担费用</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">计价方式：</label>
                                <div class="controls col-xs-8 checkWp radio radio-primary">
                                    <label class="choose-label"><input name="freightMethods" type="radio" value="0" checked="checked"  onchange="friehgtMethod(this);"/>按件计价</label>
                                    <label class="choose-label"><input name="freightMethods"  type="radio" value="1" onchange="friehgtMethod(this);"/>按重计价</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">运送方式：</label>
                                <div class="controls col-xs-10">
                                    <p class="text-danger" style="margin-top:10px;">除指定地区外，其余地区的运费采用“默认运费”。至少要选择一项运送方式。</p>
                                    <p><label class="choose-label"><span id="errType"></span></p>
                                    <ul id="maincontent">
                                    <#list companylist as company>
                                        <#if company.isDefault=='1'>
                                            <div class="radio radio-primary">
                                                <label><input type="radio" name="logComId" codename="${company.expCompany }"  id="${company.shoreExpId }" value="${company.shoreExpId }" onchange="checkchange(this);"/>${company.expName }</label>
                                            </div>
                                            <li class="test">
                                                <div class="edit-delivery">
                                                    <div id="detail-express${company.shoreExpId}"  class="delivery-info-bar" style="display: none;" >
                                                        默认运费：
                                                        <input class="area-group-values" type="hidden" id="${company.expCompany }_areas" name="${company.expCompany }_areas" value=""/>
                                                        <input class="form-control"      type="text" name="${company.expCompany }_start" value="1" id="${company.expCompany }_start"  /><span class="methods">件内，</span>
                                                        <input class="form-control"      type="text" name="${company.expCompany }_postage" value="" id="${company.expCompany }_postage"/>元，每增加
                                                        <input class="form-control"      type="text" name="${company.expCompany }_plus" value="1" id="${company.expCompany }_plus" /><span class="methods">件， 增加运费</span>
                                                        <input class="form-control"      type="text" name="${company.expCompany }_postageplus" value="" id="${company.expCompany }_postageplus"/>元

                                                        <table class="table" data-maxindex="0">
                                                            <thead>
                                                            <tr>
                                                                <th>运送到</th>
                                                                <th class="methodslist">首件（个）</th>
                                                                <th>首费（元）</th>
                                                                <th class="methodslist">续件（个）</th>
                                                                <th>续费（元）</th>
                                                                <th>操作</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="add_tr">
                                                            <tr data-group="n1" data-group-value="1" id="tbody_tr" data-code="${company.expCompany }" >
                                                                <td>
                                                                    <span title="" class="_city_Name" id="${company.expCompany }_cityName1">未选择城市</span>
                                                                    <a class="edit-cities "  onclick="editCity(this);" id="chooseedit"><i class="glyphicon glyphicon-pencil"></i></a>
                                                                    <input class="area-group-values" type="hidden" id="${company.expCompany }_areas_n1" name="${company.expCompany }_areas" value="" data-rule-arearequired="true" data-msg-arearequired="地区不能为空">
                                                                </td>
                                                                <td>
                                                                    <input value="1" name="${company.expCompany }_start" id="${company.expCompany }_start_n1" class="form-control" type="text"/>
                                                                </td>
                                                                <td>
                                                                    <input type="text" value=" " name="${company.expCompany }_postage" id="${company.expCompany }_postage_n1" class="form-control" >
                                                                </td>
                                                                <td>
                                                                    <input value="1" name="${company.expCompany }_plus" id="${company.expCompany }_plus_n1" class="form-control" type="text"/>
                                                                </td>
                                                                <td>
                                                                    <input value=" " name="${company.expCompany }_postageplus" id="${company.expCompany }_postageplus_n1" class="form-control" type="text"/>
                                                                </td>
                                                                <td>
                                                                    <button class="btn btn-primary btn-sm areaFeeDelete" type="button">删除</button>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                        <p style="margin-bottom: 0;">
                                                            <a href="javascript:void(0);" class="J_AddRule" logComId="${company.shoreExpId}" logCode="${company.expCompany }">为指定地区城市设置运费</a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </li>
                                        </#if>
                                    </#list>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group">
                            	<label class="label-control col-xs-2"></label>
                                <div class="controls col-xs-8">
                                    <button class="btn btn-primary" onclick="addTempForm()" type="button">保存</button>
                                    <button class="btn btn-default" onclick="history.back(-1)" type="button">返回</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
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

<#--选择城市弹出框-->
<div class="modal fade" id="chooseCities" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">选择城市</h4>
            </div>
            <div class="modal-body">
                <div class="cities-box" id="chooseCity">
                </div>
                <span style="color: red;" class="city_text"></span>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default"  type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary"  type="button" onclick="quecity();">确定</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script>
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
