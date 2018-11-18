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
    <style type="text/css">
        .ch_hot_hover {
            background: #ddd;
        }
        .errortip{
            color: #cd0a0a;
        }
    </style>

    <script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${basePath}/js/common.js"></script>
    <script type="text/javascript" src="${basePath}/js/freight/freight.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/bootstrap.js"></script>
    <script src="${basePath}/js/ripples.min.js"></script>
	<script src="${basePath}/js/material.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/app.js"></script>

    <script type="text/javascript">
        $(function(){

            //选中卖家承担运费 隐藏选择运费DIV  禁止掉选择物流的check选择框
           /* if($('input[id="sc_ct"]').filter(':checked').val()==1){
                $('.delivery-info-bar').css("display","none");
                $('input[name="logComId"]').attr("disabled","disabled");

            }*/
        });
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
                <li><a href="freighttemplatelistthird.htm?n=2&l=69">运费管理</a></li>
                <li class="active">修改运费模板</li>
            </ol>

            <form class="form-horizontal" action="savefreightthird.htm" method="post" id="freightFormUpdate">
                <input type="hidden" name="freightTemplateId" id="freightTemplateId" value="${freightTemplate.freightTemplateId }"/>
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
                                    <select class="form-control sm-sel staff_text" name="freightProvinceId" id="freightProvinceId" onchange="selectCity(this);">
                                        <option value="">请选择</option>
                                    <#list provinceList as province>
                                        <option value="${province.provinceId}" <#if province.provinceId==freightTemplate.freightProvinceId>selected="selected"</#if>>${province.provinceName}</option>
                                    </#list>
                                    </select>
                                    <select class="form-control sm-sel staff_text" name="freightCityId" id="city"  onchange="selectDistrict(this);">
                                        <option value="">请选择</option>
                                    <#list cityList as city>
                                        <option value="${city.cityId}" <#if city.cityId==freightTemplate.freightCityId>selected="selected"</#if>/>${city.cityName}</option>
                                    </#list>
                                    </select>
                                    <select class="form-control sm-sel staff_text" name="freightCountyId" id="district">
                                        <option value="">请选择</option>
                                    <#list districtList as district>
                                        <option value="${district.districtId}" <#if district.districtId==freightTemplate.freightCountyId>selected="selected"</#if>/>${district.districtName}</option>
                                    </#list>
                                    </select>
                                    <span id="province"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">是否包邮：</label>
                                <div class="controls col-xs-8 checkWp radio radio-primary">
                                    <label class="choose-label"><input  name="freightPackageMail" value="0" type="radio" <#if freightTemplate.freightPackageMail="0"> checked="checked" </#if> onchange="checkMail(this);"/>买家承担费用</label>
                                    <label class="choose-label"><input  name="freightPackageMail" value="1" id="sc_ct" type="radio" <#if freightTemplate.freightPackageMail="1"> checked="checked" </#if> onchange="checkMail(this);"/>卖家承担费用</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2">计价方式：</label>
                                <div class="controls col-xs-8 checkWp radio radio-primary">
                                    <label class="choose-label"><input name="freightMethods"  type="radio" value="0" <#if freightTemplate.freightMethods="0"> checked="checked" </#if>  onchange="friehgtMethod(this);"/>按件计价</label>
                                    <label class="choose-label"><input name="freightMethods"  type="radio" value="1" <#if freightTemplate.freightMethods="1"> checked="checked" </#if>  onchange="friehgtMethod(this);"/>按重计价</label>
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
                                                <label><input type="radio" name="logComId" codename="${company.expCompany }"  id="${company.shoreExpId }" value="${company.shoreExpId }"  onchange="checkchange(this);"
                                                    <#list freightTemplate.freightExpressList as expresslist>
                                                        <#if company.shoreExpId==expresslist.logComId> checked="checked"</#if>
                                                    </#list>
                                                        />${company.expName }</label>
                                            </div>
                                            <li class="test">
                                                <div class="edit-delivery">
                                                    <input type="hidden" id="company_shoreExpId" value="${company.shoreExpId}"/>
                                                    <div id="detail-express${company.shoreExpId}"  class="delivery-info-bar" data-delivery="express" style=" display: none;
                                                        <#assign tempp="0"/>
                                                        <#if tempp=="0">
                                                            <#list freightTemplate.freightExpressList as expresslist>

                                                                <#if company.shoreExpId==expresslist.logComId>
                                                                    <#assign tempp="1"/>
                                                                        display:block;
                                                                </#if>

                                                            </#list>
                                                        </#if>

                                                            ">
                                                        <#assign temp1="0"/>
                                                        <#list freightTemplate.freightExpressList as expresslist>
                                                            <#if company.shoreExpId==expresslist.logComId>
                                                                <#assign temp1="1"/>
                                                                默认运费：
                                                                <input class="area-group-values" type="hidden" id="${company.expCompany }_areas" name="${company.expCompany }_areas" value="">
                                                                <input class="form-control" type="text" name="${company.expCompany }_start" value="${expresslist.expressStart }" id="${company.expCompany }_start" />


                                                                <span class="methods">
                                                                    <#if freightTemplate.freightMethods="1">
                                                                        g
                                                                    </#if>
                                                                    <#if freightTemplate.freightMethods="0">
                                                                        件
                                                                    </#if>
                                                                    内，
                                                                </span>



                                                                <input class="form-control" type="text" name="${company.expCompany }_postage" value="${expresslist.expressPostage }" id="${company.expCompany }_postage"/>
                                                                元，每增加1
                                                                <input class="form-control" type="text"  name="${company.expCompany }_plus" value="${expresslist.expressPlusN1 }" id="${company.expCompany }_plus" />
                                                                <span class="methods">
                                                                    <#if freightTemplate.freightMethods="1">
                                                                        g
                                                                    </#if>
                                                                    <#if freightTemplate.freightMethods="0">
                                                                        件
                                                                    </#if>
                                                                    ， 增加运费</span>
                                                                <input class="form-control" type="text" name="${company.expCompany }_postageplus" value="${expresslist.expressPostageplus }" id="${company.expCompany }_postageplus" />
                                                                元
                                                            </#if>
                                                        </#list>
                                                        <#if  temp1=="0">
                                                            默认运费：
                                                            <input class="area-group-values" type="hidden" id="${company.expCompany }_areas" name="${company.expCompany }_areas" value=""/>
                                                            <input class="form-control" type="text" name="${company.expCompany }_start" value="1" id="${company.expCompany }_start" />
                                                            <#if freightTemplate.freightMethods="1">
                                                                g
                                                            </#if>
                                                            <#if freightTemplate.freightMethods="0">
                                                                件
                                                            </#if>
                                                            内，
                                                            <input class="form-control" type="text" name="${company.expCompany }_postage" value="" id="${company.expCompany }_postage"/>
                                                            元，每增加
                                                            <input class="form-control" type="text"  name="${company.expCompany }_plus" value="1" id="${company.expCompany }_plus" />
                                                            件，增加运费
                                                            <input class="form-control" type="text" name="${company.expCompany }_postageplus" value="" id="${company.expCompany }_postageplus" />
                                                            元
                                                        </#if>

                                                        <table class="table" data-maxindex="0">
                                                            <thead>
                                                            <tr>
                                                                <th>运送到</th>
                                                                <th class="methodslist">首<#if freightTemplate.freightMethods="1">重（g）</#if><#if freightTemplate.freightMethods="0">件（个）</#if></th>
                                                                <th>首费（元）</th>
                                                                <th class="methodslist"><#if freightTemplate.freightMethods="1">重（g）</#if><#if freightTemplate.freightMethods="0">件（个）</#if>续</th>
                                                                <th>续费（元）</th>
                                                                <th>操作</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="add_tr">

                                                                <#assign temp2="0"/>
                                                                <#list freightTemplate.freightExpressList as expresslist>
                                                                    <#if company.shoreExpId==expresslist.logComId>
                                                                        <#assign temp2="1"/>
                                                                        <#list expresslist.freightExpressAll as expressAll>
                                                                        <tr data-group="n${expressAll_index+1 }"  data-group-value="${expressAll_index+1 }" data-code="${company.expCompany }" >
                                                                            <td>
                                                                                <span title="<#if expressAll.allCityName??>${expressAll.allCityName}</#if>" id="${company.expCompany }_cityName${expressAll_index+1}" >
                                                                                <#if expressAll.allCityName??>
                                                                                    <#if expressAll.allCityName?length gt 7>
                                                                                        ${expressAll.allCityName?substring(0,7)}.....
                                                                                    <#else>
                                                                                         ${expressAll.allCityName}
                                                                                    </#if>
                                                                                <#else>
                                                                                    未选择城市
                                                                                </#if>
                                                                                </span>
                                                                                <a class="edit-cities"  onclick="editCity(this);" id="chooseedit"><i class="glyphicon glyphicon-pencil"></i></a>
                                                                                <input class="area-group-values" type="hidden" id="${company.expCompany }_areas_n${expressAll_index+1}" name="${company.expCompany }_areas" value="${expressAll.expressArea }">
                                                                                <input type="hidden" value="${expressAll.expressArea }" id="tempCityId"/>
                                                                                <input type="hidden" value="<#if expressAll.allCityName??>${expressAll.allCityName}<#else>未选择城市</#if>" id="tempCityName"/>

                                                                            </td>
                                                                            <td>
                                                                                <input value="${expressAll.expressStart }" name="${company.expCompany }_start" id="${company.expCompany }_start_n${expressAll_index+1}" class="form-control" type="text"/>
                                                                            </td>
                                                                            <td>
                                                                                <input value="${expressAll.expressPostage }" name="${company.expCompany }_postage" id="${company.expCompany }_postage_n${expressAll_index+1}" class="form-control" type="text"/>
                                                                            </td>
                                                                            <td>
                                                                                <input value="${expressAll.expressPlusN1 }" name="${company.expCompany }_plus" id="${company.expCompany }_plus_n${expressAll_index+1}" class="form-control" type="text"/>
                                                                            </td>
                                                                            <td>
                                                                                <input value="${expressAll.expressPostageplus }" name="${company.expCompany }_postageplus" id="${company.expCompany }_postageplus_n${expressAll_index+1}" class="form-control" type="text"/>
                                                                            </td>
                                                                            <td>
                                                                                <button class="btn btn-primary btn-sm areaFeeDelete" type="button">删除</button>
                                                                            </td>
                                                                        </tr>
                                                                        </#list>
                                                                    </#if>
                                                                </#list>
                                                                <#if temp2=="0">
                                                                <tr data-group="n1" data-group-value="1" data-code="${company.expCompany }">
                                                                    <td>
                                                                        <span title="" id="${company.expCompany }_cityName1"> 未选择城市</span>
                                                                        <a class="edit-cities"  onclick="editCity(this);" id="chooseedit"><i class="glyphicon glyphicon-pencil"></i></a>
                                                                        <input class="area-group-values" type="hidden" id="${company.expCompany }_areas_n1" name="${company.expCompany }_areas" value="" data-rule-arearequired="true" data-msg-arearequired="地区不能为空">
                                                                    </td>
                                                                    <td>
                                                                        <input value="1" name="${company.expCompany }_start" id="${company.expCompany }_start_n1" class="form-control" type="text"/>
                                                                    </td>
                                                                    <td>
                                                                        <input value=" " name="${company.expCompany }_postage" id="${company.expCompany }_postage_n1" class="form-control" type="text"/>
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
                                                                </#if>
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
                            	<label class="control-label col-xs-2"></label>
                                <div class="controls col-xs-8">
                                    <button class="btn btn-primary" onclick="subTempForm()" type="button">保存</button>
                                    <button class="btn btn-default" type="button" onclick="history.back(-1);">返回</button>
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
                <div class="cities-box">
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
