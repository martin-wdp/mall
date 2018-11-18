<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
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
<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#import "../index/indexleft.ftl" as leftmenu>
        <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>
    <div class="app show_text" style="display: none;">
        <div class="app-container">
            <ol class="breadcrumb">
                <li >您所在的位置</li>
                <li>我的店铺</li>
                <li class="active" style="color: #07d;" >店铺管理</li>
            </ol>

            <div class="app-content">
                <div role="tabpanel">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#businessInfo" aria-controls="businessInfo" role="tab" data-toggle="tab">商家信息</a>
                        </li>
                        <li role="presentation">
                            <a href="#shopInfo" aria-controls="shopInfo" role="tab" data-toggle="tab">店铺信息</a>
                        </li>
                    </ul>
                    <div class="tab-content tabCon">
                        <div role="tabpanel" class="tab-pane active" id="businessInfo">
                            <div class="cont-item">
                                <div class="title">
                                    <h3>商家信息</h3>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">商家编号：</label>
                                        <div class="controls"><#if info??>${info.storeId!''}</#if></div>
                                    </div>
                                    <!--<div class="info-item">
                                        <label class="control-label">商家类型：</label>
                                        <div class="controls">虚卡</div>
                                    </div>-->
                                </div>
                            </div>

                            <div class="cont-item">
                                <div class="title clearfix">
                                    <h3 class="pull-left">公司营业执照信息（副本）</h3>
                                    <button class="btn btn-primary btn-sm modify-btn pull-right" type="button">修改</button>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">公司名称：</label>
                                        <div class="controls"><#if info??>${info.companyName!''}</#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">公司地址：</label>
                                        <div class="controls"><#if info??>${info.companyAddrDel!''}</#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">注册号(营业执照号)：</label>
                                        <div class="controls">${info.bussId!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">成立日期：</label>
                                        <div class="controls"><#if info.companyCreTime??>${(info.companyCreTime)}</#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">营业期限：</label>
                                        <div class="controls"><#if info.bussDate??>${(info.bussDate)?split(',')[0]!''}</#if> 至 <#if info.bussDate??>${(info.bussDate)?split(',')[1]!''}</#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">注册所在地：</label>
                                        <div class="controls">
                                                    ${address!''}
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">法定代表人姓名：</label>
                                        <div class="controls">${info.bussLegalName!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">身份证号：</label>
                                        <div class="controls">${info.bussLegalCardId!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">详细地址：</label>
                                        <div class="controls">
                                        <#if info.bussAddr??>
                                                    ${info.bussAddr}
                                                </#if>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">法人身份证电子版：</label>
                                        <div class="controls"><#if info.cardUrl?? &&info.cardUrl?length!=0 ><a href="${info.cardUrl!''}" style="color:#206EBC" target="_blank">[查看]</a></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">员工数量：</label>
                                        <div class="controls">${info.companyEmpNum!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">注册资本：</label>
                                        <div class="controls">${info.companyCapital!''}万元</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">营业执照副本电子版：</label>
                                        <div class="controls"><#if info.bussUrl?? &&info.bussUrl?length!=0 ><a href="${info.bussUrl!''}" onclick="" style="color:#206EBC" target="_blank">[查看]</a></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">经营范围：</label>
                                        <div style="width:300px;" class="controls">${info.bussRange!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">公司电话：</label>
                                        <div class="controls">${info.companyTel!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">电子邮箱：</label>
                                        <div class="controls">${info.companyEmail!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">公司联系地址：</label>
                                        <div class="controls">
                                                ${(info.companyAddrDel)!''}
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">公司紧急联系人：</label>
                                        <div class="controls">${info.companyContactName!''}</div>
                                    </div>
                                    <#--<div class="info-item">
                                        <label class="control-label">详细地址：</label>
                                       <div class="controls">
                                           <#if info.companyAddr??>
                                                ${(info.companyAddr)?split(',')[0]!''}
                                                ${(info.companyAddr)?split(',')[1]!''}
                                                ${(info.companyAddr)?split(',')[2]!''}
                                            </#if>
                                        </div>
                                    </div>-->
                                    <div class="info-item">
                                        <label class="control-label">公司紧急联系人手机：</label>
                                        <div class="controls">${info.companyContactTel!''}</div>
                                    </div>
                                </div>

                                <div class="info-cont info-edit com_edit">
                                    <form id="com_form" enctype="multipart/form-data" method="post" action="third/updateseller.htm">
                                        <input name="storeId" value="${info.storeId}" type="hidden" />
                                        <div class="info-item">
                                        <label class="control-label">公司名称：</label>
                                        <div class="controls">${info.companyName!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">注册号(营业执照号)：</label>
                                        <div class="controls">${info.bussId!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">成立日期：</label>
                                        <div class="controls">
                                            <input name="companyCreTime" class="form-control datepicker" data-provide="datepicker" value="<#if info.companyCreTime??>${(info.companyCreTime)}</#if>"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">营业期限：</label>
                                        <div class="controls">
                                            <input name="bussDate" class="form-control datepicker" data-provide="datepicker" value="<#if info.bussDate??>${(info.bussDate)?split(',')[0]!''}</#if>"/>
                                            <span>至</span>
                                            <input name="bussDate" class="form-control datepicker" data-provide="datepicker" value="<#if info.bussDate??>${(info.bussDate)?split(',')[1]!''}</#if>"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">注册所在地：</label>
                                        <div class="controls">
                                            <input type="hidden" name="bussAddr" id="bussAddr"/>
                                            <select name="bussAddrId" class="form-control sm-sel province">
                                                <option>请选择</option>
                                            </select>
                                            <select name="bussAddrId" class="form-control city sm-sel">
                                                <option>请选择</option>
                                            </select>
                                            <select name="bussAddrId" class="form-control district sm-sel">
                                                <option>请选择</option>
                                            </select>
                                            <#--<select name="bussAddrId" class="form-control sm-sel street">
                                                <option>请选择</option>
                                            </select>-->
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">法定代表人姓名：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" name="bussLegalName" value="${info.bussLegalName!''}"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">详细地址：</label>
                                        <div class="controls">
                                            <#if info.bussAddr??>
                                            ${(info.bussAddr)?split(',')[0]!''}
                                            ${(info.bussAddr)?split(',')[1]!''}
                                            ${(info.bussAddr)?split(',')[2]!''}
                                            </#if>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">法人身份证电子版：</label>
                                        <div class="controls">
                                            <input type="file" name="cardUrlE" id="cardUrlE"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">注册资本：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" id="companyCapital" name="companyCapital" value="${info.companyCapital!''}"/>
                                            万元
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">营业执照副本电子版：</label>
                                        <div class="controls">
                                            <input type="file" name="bussUrlE" id="bussUrlE"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">经营范围：</label>
                                        <div class="controls">
                                            <textarea type="text" class="form-control" name="bussRange" >${info.bussRange!''}</textarea>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">公司电话：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" name="companyTel" value="${info.companyTel!''}"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">公司联系地址：</label>
                                        <input type="hidden" name="companyAddr" id="companyAddr"/>
                                        <div class="controls">
                                            <select name="companyAddrId" class="form-control cprovince sm-sel">
                                                <option>请选择</option>
                                            </select>
                                            <select name="companyAddrId" class="form-control ccity sm-sel">
                                                <option>请选择</option>
                                            </select>
                                            <select name="companyAddrId" class="form-control cdistrict sm-sel">
                                                <option>请选择</option>
                                            </select>
                                            <select name="companyAddrId" class="form-control cstreet sm-sel">
                                                <option>请选择</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">公司紧急联系人：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" name="companyContactName" value="${info.companyContactName!''}"/>
                                        </div>
                                    </div>
                                    <#--<div class="info-item">
                                        <label class="control-label">公司详细地址：</label>
                                        <div class="controls">
                                            <#if info.companyAddr??>
                                                ${(info.companyAddr)?split(',')[0]!''}
                                                ${(info.companyAddr)?split(',')[1]!''}
                                                ${(info.companyAddr)?split(',')[2]!''}
                                            </#if>
                                        </div>
                                    </div>-->
                                    <div class="info-item">
                                        <label class="control-label">公司紧急联系人手机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" id="companyContactTel" name="companyContactTel" value="${info.companyContactTel!''}"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <div class="controls">
                                            <button class="btn btn-primary" id="com_btn" type="button">保存</button>
                                            <button class="btn btn-default cancel-btn" type="button" onclick="javascript:cancelUpdate('com')">取消</button>
                                        </div>
                                    </div>
                                   </form>
                                </div>
                            </div>

                            <div class="cont-item">
                                <div class="title clearfix">
                                    <h3 class="pull-left">公司组织机构代码证</h3>
                                    <#--<button class="btn btn-primary btn-sm pull-right modify-btn" type="button">修改</button>-->
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">组织机构代码：</label>
                                        <div class="controls">${info.bussDeptNo!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">组织机构代码证电子版：</label>
                                        <div class="controls"><#if info.companyResearchUrl?? &&info.companyResearchUrl?length!=0><a href="${info.companyResearchUrl!''}" target="_blank" onclick="" style="color:#206EBC">[查看]</a></#if></div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form id="ora_form" enctype="multipart/form-data" method="post" action="third/updateseller.htm">
                                        <input name="storeId" value="${info.storeId}" type="hidden" />
                                    <div class="info-item">
                                        <label class="control-label">组织机构代码：</label>
                                    <div class="controls">${info.bussDeptNo!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">组织机构代码证电子版：</label>
                                        <div class="controls">
                                            <input type="file" name="companyResearchUrlE" id="companyResearchUrlE"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <div class="controls">
                                            <button class="btn btn-primary" id="ora_btn" type="button">保存</button>
                                            <button class="btn btn-default cancel-btn" type="button">取消</button>
                                        </div>
                                    </div>
                                     </form>
                                </div>
                            </div>

                            <div class="cont-item">
                                <div class="title clearfix">
                                    <h3 class="pull-left">公司税务登记证</h3>
                                    <#--<button class="btn btn-primary btn-sm pull-right modify-btn" type="button">修改</button>-->
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">税务人识别号：</label>
                                        <div class="controls">${info.bussTaxPayerId!''}</div>
                                    </div>
                                    <!--<div class="info-item">
                                        <label class="control-label">纳税人类型：</label>
                                        <div class="controls">${info.bussTaxType!''}</div>
                                    </div>-->
                                    <div class="info-item">
                                        <label class="control-label">税负登记证号：</label>
                                        <div class="controls">${info.bussTaxRegistId!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">税务登记证电子版：</label>
                                        <div class="controls"><#if info.bussTaxRegistUrl?? &&info.bussTaxRegistUrl?length!=0><a target="_blank" href="${info.bussTaxRegistUrl!''}">[查看]</a></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">一般纳税人资格证电子版：</label>
                                        <div class="controls">
                                        <#if info.bussTaxCredUrl?? &&info.bussTaxCredUrl?length!=0><a target="_blank" href="${info.bussTaxCredUrl!''}" >[查看]</a></#if>
                                        </div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form id="tax_form" enctype="multipart/form-data" method="post" action="third/updateseller.htm">
                                        <input name="storeId" value="${info.storeId}" type="hidden" />
                                    <div class="info-item">
                                        <label class="control-label">税务人识别号：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" value="${info.bussTaxPayerId!''}" name="bussTaxPayerId"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">纳税人类型：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" value="${info.bussTaxType!''}" name="bussTaxType"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">纳税类型税码：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" value="${info.bussTaxRegistId!''}" name="bussTaxRegistId"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">税务登记证电子版：</label>
                                        <div class="controls">
                                            <input type="file" name="bussTaxRegistUrlE" id="bussTaxRegistUrlE"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">一般纳税人资格证电子版：</label>
                                        <div class="controls">
                                            <input type="file" name="bussTaxCredUrlE" id="bussTaxCredUrlE"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <div class="controls">
                                            <button class="btn btn-primary" id="tax_btn" type="button">保存</button>
                                            <button class="btn btn-default cancel-btn" type="button">取消</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>

                            <div class="cont-item">
                                <div class="title clearfix">
                                    <h3 class="pull-left">结算银行信息</h3>
                                    <button class="btn btn-primary btn-sm pull-right modify-btn" type="button">修改</button>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">银行开户名：</label>
                                        <div class="controls">${info.bankUsername!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">公司银行账号：</label>
                                        <div class="controls">${info.bankCardId!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">开户行支行名称：</label>
                                        <div class="controls">${info.bankName!''}</div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">开户行支行联行号：</label>
                                        <div class="controls">${info.bankId!''}</div>
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
                                    <div class="info-item">
                                        <label class="control-label">银行开户许可证电子版：</label>
                                        <div class="controls"><#if info.bankUrl?? &&info.bankUrl?length!=0><a target="_blank" href="${info.bankUrl!''}">[查看]</a></#if></div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form id="bank_form" enctype="multipart/form-data" method="post" action="third/updateseller.htm">
                                        <input name="storeId" value="${info.storeId}" type="hidden" />
                                    <div class="info-item">
                                        <label class="control-label">银行开户名：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" value="${info.bankUsername!''}" name="bankUsername"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">公司银行账号：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control"  value="${info.bankCardId!''}" name="bankCardId"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">开户行支行名称：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" value="${info.bankName!''}" name="bankName" />
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">开户行支行联行号：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" value="${info.bankId!''}" name="bankId" />
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">开户银行所在地：</label>
                                        <div class="controls">
                                            <input type="hidden" name="bankAddr" id="bankAddr"/>
                                            <select name="bankAddrId" class="form-control bprovince sm-sel" id="bp" >
                                                <option>请选择</option>
                                            </select>
                                            <select name="bankAddrId" class="form-control bcity sm-sel" id="bc" >
                                                <option>请选择</option>
                                            </select>
                                            <select name="bankAddrId" class="form-control bdistrict sm-sel" id="bd" >
                                                <option>请选择</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">银行开户许可证电子版：</label>
                                        <div class="controls">
                                            <input type="file" name="bankUrlE" id="bankUrlE"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <div class="controls">
                                            <button class="btn btn-primary" id="bank_btn" type="button">保存</button>
                                            <button class="btn btn-default cancel-btn" type="button">取消</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>

                            <div class="cont-item">
                                <div class="title clearfix">
                                    <h3 class="pull-left">客服设置</h3>
                                    <button class="btn btn-primary btn-sm pull-right modify-btn" type="button">修改</button>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">QQ：</label>
                                        <div class="controls">${info.serviceQq!''}</div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form id="customer_service_form" enctype="multipart/form-data" method="post" action="third/updateseller.htm">
                                        <input name="storeId" value="${info.storeId}" type="hidden">
                                    <div class="info-item">
                                        <label class="control-label">QQ：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control" name="serviceQq" id="serviceQq" value="${info.serviceQq!''}"/>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <div class="controls">
                                            <button class="btn btn-primary" id="customer_service_btn" type="button">保存</button>
                                            <button class="btn btn-default cancel-btn" type="button">取消</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="shopInfo">
                            <div class="cont-item">
                                <div class="title">
                                    <h3>店铺基本信息</h3>
                                </div>
                                <div class="info-cont">
                                <div class="info-item">
                                    <label class="control-label">店铺名称：</label>
                                    <div class="controls">${(info.storeName)!''}</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">店铺ID：</label>
                                    <div class="controls">${(info.storeId)!'0'}${(customerId)!'000'}</div>
                                </div>



                                <div class="title">
                                    <h3>店铺首页</h3>
                                </div>
                                <div class="info-cont">
                                    <div class="info-cont info-configure">
                                        <form id="storeIndexForm" action="${basePath}/updateStoreIndexState.htm" mothed="post">
                                            <div class="info-item">
                                                <label class="control-label">是否启用店铺首页：</label>
                                                <div class="controls radio radio-primary" style="margin-top:0;">
                                                    <label class="choose-label">
                                                        <input name="isStoreIndex"
                                                                   <#if !(info.isStoreIndex)??>checked="checked"</#if>
                                                                   <#if info.isStoreIndex?? && info.isStoreIndex=='0'>checked="checked"</#if> value="0" type="radio"/>不启用</label>
                                                    <label class="choose-label">
                                                        <input name="isStoreIndex"
                                                                                       <#if info.isStoreIndex?? && info.isStoreIndex=='1'>checked="checked"</#if> value="1" type="radio"/>启用</label>
                                                </div>
                                            </div>
                                            <div class="info-item">
                                                <div class="controls">
                                                    <button class="btn btn-primary" onclick="submitUpdate()" type="button">保存</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <script>

                                function submitUpdate(){
                                    var isStoreIndex = $("input[name='isStoreIndex']:checked").val() ;
                                    $.ajax({
                                        url: "${basePath}/updateStoreIndexStateAlax.htm",
                                        data:{isStoreIndex:isStoreIndex},
                                        success: function(data){
                                            if(data==1){
                                              $('.show_text_dia').html("修改成功！");
                                              $('#show_dialog').modal("show");
                                            }else{
                                              $('.show_text_dia').html("修改失败！");
                                              $('#show_dialog').modal("show");
                                            }
                                    }});
                                }

                            </script>


                    <#--遍历店铺各种联系人-店铺负责人、运营联系人、财务联系人-->
                    <#list seller as sell>
                        <#if (sell.type == '1' ) >
                            <div class="cont-item">
                                <div class="title">
                                    <h3>店铺负责人</h3>
                                    <button class="btn btn-primary btn-sm modify-btn" type="button">修改</button>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls"><#if sell??>${(sell.name)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls"><#if sell??>${(sell.pho)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">电子邮件：</label>
                                        <div class="controls"><#if sell??>${(sell.email)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">座机：</label>
                                        <div class="controls"><#if sell??>${(sell.tel)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">QQ：</label>
                                        <div class="controls"><#if sell??>${(sell.qq)!''}<#else><空></#if></div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form <#--id="com_form"--> class="form_fuze" enctype="multipart/form-data" method="post" action="third/updatestore.htm" onsubmit="return checkForm('fuzeUpdate');" >
                                        <input name="conId" value="${(sell.conId)!''}" type="hidden" />
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control nickname_fuzeUpdate" name="name" value="${(sell.name)!''}"/>
                                            <label>长度6-20字符</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control pho_fuzeUpdate" name="pho" value="${(sell.pho)!''}"/>
                                            <label>请输入11位有效手机号码</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>电子邮件：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control email_fuzeUpdate" name="email" value="${(sell.email)!''}"/>
                                            <label>如：123@ningpai.com</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>座机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control tel_fuzeUpdate" name="tel" value="${(sell.tel)!''}"/>
                                            <label>如：021-xxxxxxx</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>QQ：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control qqQQ_fuzeUpdate" name="qq" value="${(sell.qq)!''}"/>
                                            <label>请输入有效的QQ</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <div class="controls">
                                            <button class="btn btn-primary" id="btn_fuze" onclick="javascript:fuzeUpdateStore()" type="button">保存</button>
                                            <button class="btn btn-default cancel-btn" type="button">取消</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                        <#break>
                        <#elseif sell_index==(seller?size-1) && (sell.type != '1' )>
                            <div class="cont-item">
                                <div class="title">
                                    <h3>店铺负责人</h3>
                                    <button class="btn btn-primary btn-sm modify-btn" type="button">修改</button>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls"><空></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls"><空></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">电子邮件：</label>
                                        <div class="controls"><空></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">座机：</label>
                                        <div class="controls"><空></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">QQ：</label>
                                        <div class="controls"><空></div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form id="addform_fuze" class="addform_fuze" enctype="multipart/form-data" method="post" action="third/insertstore.htm" onsubmit="return checkForm('fuzeInsert');" >
                                        <input name="type" value="1" type="hidden" />
                                        <div class="info-item">
                                            <label class="control-label">姓名：</label>
                                            <div class="controls">
                                                <input type="text" class="form-control nickname_fuzeInsert" name="name" value=""/>
                                                <label>长度6-20字符</label>
                                            </div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label">手机：</label>
                                            <div class="controls">
                                                <input type="text" class="form-control pho_fuzeInsert" name="pho" value=""/>
                                                <label>请输入11位有效手机号码</label>
                                            </div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label"><b>*</b>电子邮件：</label>
                                            <div class="controls">
                                                <input type="text" class="form-control email_fuzeInsert" name="email" value=""/>
                                                <label>如：123@ningpai.com</label>
                                            </div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label"><b>*</b>座机：</label>
                                            <div class="controls">
                                                <input type="text" class="form-control tel_fuzeInsert" name="tel" value=""/>
                                                <label>如：021-xxxxxxx</label>
                                            </div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label"><b>*</b>QQ：</label>
                                            <div class="controls">
                                                <input type="text" class="form-control qqQQ_QQuzeInsert" name="qq" value=""/>
                                                <label>请输入有效的QQ</label>
                                            </div>
                                        </div>
                                        <div class="info-item">
                                            <div class="controls">
                                                <button class="btn btn-primary" <#--id="btn_fuze"--> onclick="javascript:fuzeInsertStore()" type="button">保存</button>
                                                <button class="btn btn-default cancel-btn" <#--onclick="javascript:cancelUpdate('fuze')"--> type="button">取消</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </#if>
                    </#list>

                    <#list seller as sell>
                        <#if (sell.type == '2' ) >
                            <div class="cont-item">
                                <div class="title">
                                    <h3>运营联系人</h3>
                                    <button class="btn btn-primary btn-sm modify-btn" type="button">修改</button>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls"><#if sell??>${(sell.name)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls"><#if sell??>${(sell.pho)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">电子邮件：</label>
                                        <div class="controls"><#if sell??>${(sell.email)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">座机：</label>
                                        <div class="controls"><#if sell??>${(sell.tel)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">QQ：</label>
                                        <div class="controls"><#if sell??>${(sell.qq)!''}<#else><空></#if></div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form id="com_form" class="form_yunying" enctype="multipart/form-data" method="post" action="third/updatestore.htm" onsubmit="return checkForm('yunyingUpdate');" >
                                        <input name="conId" value="${(sell.conId)!''}" type="hidden" />
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control nickname_yunyingUpdate" name="name" value="${(sell.name)!''}"/>
                                            <label>长度6-20字符</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control pho_yunyingUpdate" name="pho" value="${(sell.pho)!''}"/>
                                            <label>请输入11位有效手机号码</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>电子邮件：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control email_yunyingUpdate" name="email" value="${(sell.email)!''}"/>
                                            <label>如：123@ningpai.com</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>座机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control tel_yunyingUpdate" name="tel" value="${(sell.tel)!''}"/>
                                            <label>如：021-xxxxxxx</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>QQ：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control qqQQ_yunyingUpdate" name="qq" value="${(sell.qq)!''}"/>
                                            <label>请输入有效的QQ</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <div class="controls">
                                            <button class="btn btn-primary" id="btn_yunying" onclick="javascript:yunyingUpdateStore()" type="button">保存</button>
                                            <button class="btn btn-default cancel-btn" type="button">取消</button>
                                        </div>
                                    </div>
                                  </form>
                                </div>
                            </div>
                        <#break>
                        <#elseif sell_index==(seller?size-1) && (sell.type != '2' )>
                            <div class="cont-item">
                                <div class="title">
                                    <h3>运营联系人</h3>
                                    <button class="btn btn-primary btn-sm modify-btn" type="button">修改</button>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls"><空></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls"><空></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">电子邮件：</label>
                                        <div class="controls"><空></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">座机：</label>
                                        <div class="controls"><空></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">QQ：</label>
                                        <div class="controls"><空></div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form id="addform_yunying" class="addform_yunying" enctype="multipart/form-data" method="post" action="third/insertstore.htm" onsubmit="return checkForm('yunyingInsert');" >
                                        <input name="conId" value="${(sell.conId)!''}" type="hidden" />
                                        <div class="info-item">
                                            <label class="control-label">姓名：</label>
                                            <div class="controls">
                                                <input type="text" class="form-control nickname_yunyingInsert" name="name" value=""/>
                                                <label>长度6-20字符</label>
                                            </div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label">手机：</label>
                                            <div class="controls">
                                                <input type="text" class="form-control pho_yunyingInsert" name="pho" value=""/>
                                                <label>请输入11位有效手机号码</label>
                                            </div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label"><b>*</b>电子邮件：</label>
                                            <div class="controls">
                                                <input type="text" class="form-control email_yunyingInsert" name="email" value=""/>
                                                <label>如：123@ningpai.com</label>
                                            </div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label"><b>*</b>座机：</label>
                                            <div class="controls">
                                                <input type="text" class="form-control tel_yunyingInsert" name="tel" value=""/>
                                                <label>如：021-xxxxxxx</label>
                                            </div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label"><b>*</b>QQ：</label>
                                            <div class="controls">
                                                <input type="text" class="form-control qqQQ_yunyingInsert" name="qq" value=""/>
                                                <label>请输入有效的QQ</label>
                                            </div>
                                        </div>
                                        <div class="info-item">
                                            <div class="controls">
                                                <button class="btn btn-primary" id="btn_yunying" onclick="javascript:yunyingInsertStore()" type="button">保存</button>
                                                <button class="btn btn-default cancel-btn" type="button">取消</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </#if>
                        </#list>

                        <#list seller as sell>
                            <#if (sell.type == '3' ) >
                                <div class="cont-item">
                                <div class="title">
                                    <h3>售后联系人</h3>
                                    <button class="btn btn-primary btn-sm modify-btn" type="button">修改</button>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls"><#if sell??>${(sell.name)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls"><#if sell??>${(sell.pho)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">电子邮件：</label>
                                        <div class="controls"><#if sell??>${(sell.email)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">座机：</label>
                                        <div class="controls"><#if sell??>${(sell.tel)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">QQ：</label>
                                        <div class="controls"><#if sell??>${(sell.qq)!''}<#else><空></#if></div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form id="com_form" class="form_shouhou" enctype="multipart/form-data" method="post" action="third/updatestore.htm" onsubmit="return checkForm('shouhouUpdate');" >
                                        <input name="conId" value="${(sell.conId)!''}" type="hidden" />
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control nickname_shouhouUpdate" name="name" value="${(sell.name)!''}"/>
                                            <label>长度6-20字符</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control pho_shouhouUpdate" name="pho" value="${(sell.pho)!''}"/>
                                            <label>请输入11位有效手机号码</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>电子邮件：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control email_shouhouUpdate" name="email" value="${(sell.email)!''}"/>
                                            <label>如：123@ningpai.com</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>座机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control tel_shouhouUpdate" name="tel" value="${(sell.tel)!''}"/>
                                            <label>如：021-xxxxxxx</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>QQ：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control qqQQ_shouhouUpdate" name="qq" value="${(sell.qq)!''}"/>
                                            <label>请输入有效的QQ</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <div class="controls">
                                            <button class="btn btn-primary" onclick="javascript:shouhouUpdateStore()" type="button">保存</button>
                                            <button class="btn btn-default cancel-btn" type="button">取消</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                            <#break>
                            <#elseif sell_index==(seller?size-1) && (sell.type != '3' )>
                                <div class="cont-item">
                                    <div class="title">
                                        <h3>售后联系人</h3>
                                        <button class="btn btn-primary btn-sm modify-btn" type="button">修改</button>
                                    </div>
                                    <div class="info-cont">
                                        <div class="info-item">
                                            <label class="control-label">姓名：</label>
                                            <div class="controls"><空></div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label">手机：</label>
                                            <div class="controls"><空></div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label">电子邮件：</label>
                                            <div class="controls"><空></div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label">座机：</label>
                                            <div class="controls"><空></div>
                                        </div>
                                        <div class="info-item">
                                            <label class="control-label">QQ：</label>
                                            <div class="controls"><空></div>
                                        </div>
                                    </div>
                                    <div class="info-cont info-edit">
                                        <form id="addform_shouhou" class="addform_shouhou" enctype="multipart/form-data" method="post" action="third/insertstore.htm" onsubmit="return checkForm('shouhouInsert');" >
                                            <input name="conId" value="${(sell.conId)!''}" type="hidden" />
                                            <div class="info-item">
                                                <label class="control-label">姓名：</label>
                                                <div class="controls">
                                                    <input type="text" class="form-control nickname_shouhouInsert" name="name" value=""/>
                                                    <label>长度6-20字符</label>
                                                </div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label">手机：</label>
                                                <div class="controls">
                                                    <input type="text" class="form-control pho_shouhouInsert" name="pho" value=""/>
                                                    <label>请输入11位有效手机号码</label>
                                                </div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label"><b>*</b>电子邮件：</label>
                                                <div class="controls">
                                                    <input type="text" class="form-control email_shouhouInsert" name="email" value=""/>
                                                    <label>如：123@ningpai.com</label>
                                                </div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label"><b>*</b>座机：</label>
                                                <div class="controls">
                                                    <input type="text" class="form-control tel_shouhouInsert" name="tel" value=""/>
                                                    <label>如：021-xxxxxxx</label>
                                                </div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label"><b>*</b>QQ：</label>
                                                <div class="controls">
                                                    <input type="text" class="form-control qqQQ_shouhouInsert" name="qq" value=""/>
                                                    <label>请输入有效的QQ</label>
                                                </div>
                                            </div>
                                            <div class="info-item">
                                                <div class="controls">
                                                    <button class="btn btn-primary" onclick="javascript:shouhouInsertStore()" type="button">保存</button>
                                                    <button class="btn btn-default cancel-btn" type="button">取消</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                            </#if>
                           </#list>

                            <#list seller as sell>
                                <#if (sell.type == '4' ) >
                                    <div class="cont-item">
                                <div class="title">
                                    <h3>财务联系人</h3>
                                    <button class="btn btn-primary btn-sm modify-btn" type="button">修改</button>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls"><#if sell??>${(sell.name)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls"><#if sell??>${(sell.pho)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">电子邮件：</label>
                                        <div class="controls"><#if sell??>${(sell.email)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">座机：</label>
                                        <div class="controls"><#if sell??>${(sell.tel)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">QQ：</label>
                                        <div class="controls"><#if sell??>${(sell.qq)!''}<#else><空></#if></div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form id="com_form" class="form_caiwu" enctype="multipart/form-data" method="post" action="third/updatestore.htm" onsubmit="return checkForm('caiwuUpdate');" >
                                        <input name="conId" value="${(sell.conId)!''}" type="hidden" />
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control nickname_caiwuUpdate" name="name" value="${(sell.name)!''}"/>
                                            <label>长度6-20字符</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control pho_caiwuUpdate" name="pho" value="${(sell.pho)!''}"/>
                                            <label>请输入11位有效手机号码</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>电子邮件：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control email_caiwuUpdate" name="email" value="${(sell.email)!''}"/>
                                            <label>如：123@ningpai.com</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>座机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control tel_caiwuUpdate" name="tel" value="${(sell.tel)!''}"/>
                                            <label>如：021-xxxxxxx</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>QQ：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control qqQQ_caiwuUpdate" name="qq" value="${(sell.qq)!''}"/>
                                            <label>请输入有效的QQ</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <div class="controls">
                                            <button class="btn btn-primary" id="btn_caiwu" onclick="javascript:caiwuUpdateStore()" type="button">保存</button>
                                            <button class="btn btn-default cancel-btn" type="button">取消</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                               <#break>
                                <#elseif sell_index==(seller?size-1) && (sell.type != '4' )>
                                    <div class="cont-item">
                                        <div class="title">
                                            <h3>财务联系人</h3>
                                            <button class="btn btn-primary btn-sm modify-btn" type="button">修改</button>
                                        </div>
                                        <div class="info-cont">
                                            <div class="info-item">
                                                <label class="control-label">姓名：</label>
                                                <div class="controls"><空></div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label">手机：</label>
                                                <div class="controls"><空></div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label">电子邮件：</label>
                                                <div class="controls"><空></div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label">座机：</label>
                                                <div class="controls"><空></div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label">QQ：</label>
                                                <div class="controls"><空></div>
                                            </div>
                                        </div>
                                        <div class="info-cont info-edit">
                                            <form id="addform_caiwu" class="addform_caiwu" enctype="multipart/form-data" method="post" action="third/insertstore.htm" onsubmit="return checkForm('caiwuInsert');" >
                                                <input name="conId" value="${(sell.conId)!''}" type="hidden" />
                                                <div class="info-item">
                                                    <label class="control-label">姓名：</label>
                                                    <div class="controls">
                                                        <input type="text" class="form-control nickname_caiwuInsert" name="name" value=""/>
                                                        <label>长度6-20字符</label>
                                                    </div>
                                                </div>
                                                <div class="info-item">
                                                    <label class="control-label">手机：</label>
                                                    <div class="controls">
                                                        <input type="text" class="form-control pho_caiwuInsert" name="pho" value=""/>
                                                        <label>请输入11位有效手机号码</label>
                                                    </div>
                                                </div>
                                                <div class="info-item">
                                                    <label class="control-label"><b>*</b>电子邮件：</label>
                                                    <div class="controls">
                                                        <input type="text" class="form-control email_caiwuInsert" name="email" value=""/>
                                                        <label>如：123@ningpai.com</label>
                                                    </div>
                                                </div>
                                                <div class="info-item">
                                                    <label class="control-label"><b>*</b>座机：</label>
                                                    <div class="controls">
                                                        <input type="text" class="form-control tel_caiwuInsert" name="tel" value=""/>
                                                        <label>如：021-xxxxxxx</label>
                                                    </div>
                                                </div>
                                                <div class="info-item">
                                                    <label class="control-label"><b>*</b>QQ：</label>
                                                    <div class="controls">
                                                        <input type="text" class="form-control qqQQ_caiwuInsert" name="qq" value=""/>
                                                        <label>请输入有效的QQ</label>
                                                    </div>
                                                </div>
                                                <div class="info-item">
                                                    <div class="controls">
                                                        <button class="btn btn-primary" id="btn_caiwu" onclick="javascript:caiwuInsertStore()" type="button">保存</button>
                                                        <button class="btn btn-default cancel-btn" type="button">取消</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </#if>
                           </#list>

                            <#list seller as sell>
                                <#if (sell.type == '5' ) >
                                    <div class="cont-item">
                                <div class="title">
                                    <h3>技术联系人</h3>
                                    <button class="btn btn-primary btn-sm modify-btn" type="button">修改</button>
                                </div>
                                <div class="info-cont">
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls"><#if sell??>${(sell.name)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls"><#if sell??>${(sell.pho)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">电子邮件：</label>
                                        <div class="controls"><#if sell??>${(sell.email)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">座机：</label>
                                        <div class="controls"><#if sell??>${(sell.tel)!''}<#else><空></#if></div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">QQ：</label>
                                        <div class="controls"><#if sell??>${(sell.qq)!''}<#else><空></#if></div>
                                    </div>
                                </div>
                                <div class="info-cont info-edit">
                                    <form id="com_form" class="form_jishu" enctype="multipart/form-data" method="post" action="third/updatestore.htm" onsubmit="return checkForm('jishuUpdate');" >
                                        <input name="conId" value="${(sell.conId)!''}" type="hidden" />
                                    <div class="info-item">
                                        <label class="control-label">姓名：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control nickname_jishuUpdate" name="name" value="${(sell.name)!''}"/>
                                            <label>长度6-20字符</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label">手机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control pho_jishuUpdate" name="pho" value="${(sell.pho)!''}"/>
                                            <label>请输入11位有效手机号码</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>电子邮件：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control email_jishuUpdate" name="email" value="${(sell.email)!''}"/>
                                            <label>如：123@ningpai.com</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>座机：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control tel_jishuUpdate" name="tel" value="${(sell.tel)!''}"/>
                                            <label>如：021-xxxxxxx</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <label class="control-label"><b>*</b>QQ：</label>
                                        <div class="controls">
                                            <input type="text" class="form-control qqQQ_jishuUpdate" name="qq" value="${(sell.qq)!''}"/>
                                            <label>请输入有效的QQ</label>
                                        </div>
                                    </div>
                                    <div class="info-item">
                                        <div class="controls">
                                            <button class="btn btn-primary" id="btn_jishu" onclick="javascript:jishuUpdateStore()" type="button">保存</button>
                                            <button class="btn btn-default cancel-btn" type="button">取消</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                                <#break>
                                <#elseif sell_index==(seller?size-1) && (sell.type != '5' )>
                                    <div class="cont-item">
                                        <div class="title">
                                            <h3>技术联系人</h3>
                                            <button class="btn btn-primary btn-sm modify-btn" type="button">修改</button>
                                        </div>
                                        <div class="info-cont">
                                            <div class="info-item">
                                                <label class="control-label">姓名：</label>
                                                <div class="controls"><空></div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label">手机：</label>
                                                <div class="controls"><空></div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label">电子邮件：</label>
                                                <div class="controls"><空></div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label">座机：</label>
                                                <div class="controls"><空></div>
                                            </div>
                                            <div class="info-item">
                                                <label class="control-label">QQ：</label>
                                                <div class="controls"><空></div>
                                            </div>
                                        </div>
                                        <div class="info-cont info-edit">
                                            <form id="addform_jishu" class="addform_jishu" enctype="multipart/form-data" method="post" action="third/insertstore.htm" onsubmit="return checkForm('jishuInsert');" >
                                                <input name="conId" value="${(sell.conId)!''}" type="hidden" />
                                                <div class="info-item">
                                                    <label class="control-label">姓名：</label>
                                                    <div class="controls">
                                                        <input type="text" class="form-control nickname_jishuInsert" name="name" value=""/>
                                                        <label>长度6-20字符</label>
                                                    </div>
                                                </div>
                                                <div class="info-item">
                                                    <label class="control-label">手机：</label>
                                                    <div class="controls">
                                                        <input type="text" class="form-control pho_jishuInsert" name="pho" value=""/>
                                                        <label>请输入11位有效手机号码</label>
                                                    </div>
                                                </div>
                                                <div class="info-item">
                                                    <label class="control-label"><b>*</b>电子邮件：</label>
                                                    <div class="controls">
                                                        <input type="text" class="form-control email_jishuInsert" name="email" value=""/>
                                                        <label>如：123@ningpai.com</label>
                                                    </div>
                                                </div>
                                                <div class="info-item">
                                                    <label class="control-label"><b>*</b>座机：</label>
                                                    <div class="controls">
                                                        <input type="text" class="form-control tel_jishuInsert" name="tel" value=""/>
                                                        <label>如：021-xxxxxxx</label>
                                                    </div>
                                                </div>
                                                <div class="info-item">
                                                    <label class="control-label"><b>*</b>QQ：</label>
                                                    <div class="controls">
                                                        <input type="text" class="form-control qqQQ_jishuInsert" name="qq" value=""/>
                                                        <label>请输入有效的QQ</label>
                                                    </div>
                                                </div>
                                                <div class="info-item">
                                                    <div class="controls">
                                                        <button class="btn btn-primary" id="btn_jishu" onclick="javascript:jishuInsertStore()" type="button">保存</button>
                                                        <button class="btn btn-default cancel-btn" type="button">取消</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </#if>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
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


<div class="modal fade" id="show_dialog" role="dialog" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label show_text_dia"></label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/seller.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/strore.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/openstore.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/sellervalidate.js"></script>
<script type="text/javascript" src="${basePath}/js/common/common_alert.js"></script>



<!--模板相关-->
<div class="mask"></div>
<div class="dialog dia1" style="width:1210px; height:595px;"></div>
<script type="text/javascript">
    //用于修改银行的省市区的选择
        var bp, bc, bd;
        $("select[name='bankAddrId']").change(function(){
            bp = $("#bp").find('option:selected').text();
            bc = $("#bc").find('option:selected').text();
            bd = $("#bd").find('option:selected').text();
            var bankAddr=bp+","+bc+","+bd;
            $("#bankAddr").val(bankAddr);
        });

    $(function(){
    	$.material.init();
        /*加载省份*/
        loadCProvice();
        <#if info.companyAddrId??>
            selectLocationOptionC('${((info.companyAddrId)?split(',')[0])!''}','${((info.companyAddrId)?split(',')[1])!''}','${((info.companyAddrId)?split(',')[2])!''}','${(info.companyAddrId)?split(',')[3]!''}','cprovince','ccity','cdistrict','cstreet');
        </#if>
        loadProvice();
        <#if info.bussAddrId??>
            selectLocationOption('${((info.bussAddrId)?split(',')[0])!''}','${((info.bussAddrId)?split(',')[1])!''}','${((info.bussAddrId)?split(',')[2])!''}','${((info.bussAddrId)?split(',')[3])!''}','province','city','district','street');
        </#if>
        loadBProvice();
        <#if info.bankAddrId??>
            selectLocationOptionB('${((info.bankAddrId)?split(',')[0])!''}','${((info.bankAddrId)?split(',')[1])!''}','${((info.bankAddrId)?split(',')[2])!''}','${((info.bankAddrId)?split(',')[3])!''}','bprovince','bcity','bdistrict','bstreet');
        </#if>
       $(".tpl_img").click(function(){
            var cur = $(this);
            var ads = cur.find("img").attr("data-src");
            var img = '<img alt="" src="'+ads+'" />'
            $(".dialog").append(img);
        });
        $(".mask, .dialog").click(function(){
            $(".dialog img").remove();
            $(".mask, .dialog").hide();
        });

        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        })
    });
    if($("#temp").val=="temp"){
        $("#tempLi").addClass("cur");
        $("#tempDiv").addClass("show");
    }

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

</script>
<!--模板相关END-->
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />


</html>
