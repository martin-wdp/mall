<#include "../../include/common.ftl">
<@htmlHead title='企业认证-${topmap.systembase.bsetName}'>
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/base.min.css"/>
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/pages.css"/>
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/qp_style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
<style>
    /* 弹窗 */
    .mask {
        width: 100%;
        height: 100%;
        background: #000;
        opacity: 0.5;
        filter: alpha(opacity=50);
        position: fixed;
        top: 0;
        left: 0;
        z-index: 9998;
        display: none;
    }

    .qpCusto {
        position: fixed;
        background: #fff;
        z-index: 9999;
        width: 440px;
        min-height: 230px;
        padding: 5px;
        display: none;
    }

    .dia_intro em {
        display: inline-block;
        font-family: microsoft YaHei;
        font-size: 18px;
        color: #575757;
        -webkit-transform: rotate(-10deg);
        -moz-transform: rotate(-10deg);
    }

    .agreement_dia {
        width: 910px;
        height: 490px;
        border: 5px solid rgba(238, 238, 238, .5);
        padding: 0;
    }

    .agreement_dia .dia_tit {
        background: #eee;
        text-align: center;
        font-size: 14px;
        font-weight: 700;
        color: #666;
    }

    .agreement_dia .dia_close {
        position: absolute;
        top: 8px;
        right: 20px;
        margin-top: 0;
        background: url(${basePath}/images/agree_close.gif) no-repeat;
    }

    .agreement_wp {
        height: 360px;
        overflow-y: scroll;
        padding: 10px 20px;
    }

    .agreement_wp h4 {
        font-weight: 700;
        line-height: 180%;
    }

    .agreement_wp p {
        line-height: 180%;
    }

    .agree_btn {
        display: inline-block;
        zoom: 1;
        *display: inline;
        width: 200px;
        height: 30px;
        line-height: 29px;
        background: url(${basePath}/images/agree_btn.gif) no-repeat;
        font-family: microsoft YaHei;
        font-size: 16px;
        color: #fff !important;
    }

    .bluee {
        color: #005aa0;
    }

    #login_name {
        width: 18px;
        height: 18px;
        background: url(images/user.png) no-repeat;
        position: absolute;
        top: 10px;
        left: 10px;
    }

    #login_code {
        width: 18px;
        height: 22px;
        background: url(images/code.png) no-repeat;
        position: absolute;
        top: 8px;
        left: 10px;
    }

    input:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px white inset;
        -webkit-text-fill-color: #333;
    }

    .n_rg {
        position: absolute;
        width: 74px;
        height: 38px;
        line-height: 38px;
        top: 0px;
        left: 10px;
        color: #666;
        font-size: 14px;
    }

    .n_row .n_text {
        width: 100%;
        height: 36px;
        border: 1px solid #d3d3d3;
        border-radius: 3px;
        line-height: 36px;
        text-indent: 74px;
        *width: 250px;
        *text-indent: 0;
    }

    .n_row .form_tips {
        display: none;
        position: absolute;
        left: 0;
        top: 41px;
        height: 16px;
        line-height: 16px;
        padding-left: 25px;
        background: url(images/tips_icon.png) no-repeat left top;
        color: #969696;
    }
</style>
</@htmlHead>
<@htmlBody>
<input type="hidden" name="CSRFToken" value="${token}">

<#--一引入头部 <#include "/index/topnew.ftl" />  -->
<#include "../../index/newtop7.ftl">
<#include "../../customer/newtop.ftl"/>
<div style="background: #f5f5f5;">
    <div class="container clearfix pt20 pb10">
        <!--new_member_left-->
    <#include "../../customer/newleftmenu.ftl"/>
        <div class="new_member-right">
            <div class="new_order_list">
                <div class="n-title">企业认证</div>
            <#--<div class="tagMenu order-menu pr">
                <ul class="menu clearfix">
                    <li class="current">企业认证申请</li>
                </ul>
                <div class="aco-link">
                    注：修改邮箱、手机请到<a href="${basePath}/customer/securitycenter.html">账户安全</a>
                </div>
            </div>-->
                <div class="content">
                    <div class="layout">
                        <div class="qp-fill-infor">

                        <#--<div style="float: left">填写验证信息</div>
                        <div class="title" style="float: right;font-size: 20px;"><input type="button" value="返回上一页"
                                                                       onclick="javascript:history.back();"/></div>-->


                            <form method="post" id="authentication_from" action="${basePath}/saveEnCertInfo.htm"
                                  enctype="multipart/form-data">
                                <p class="tit-top mb20">公司信息</p>
                            <#if qpEnterpriseCertificationInfo??>
                                <table class="auther-list">
                                    <tr>
                                        <td class="qp-name"><span>*</span>汽修厂名称：</td>
                                        <td>
                                            <input type="text" class="qLong-text" name="company_name" id="company_name"
                                                   placeholder="请填写营业执照名称" value="${qpEnterpriseCertificationInfo.company_name}"/><br/>

                                            <p class="qTip" id="company_nameinfo">请填写营业执照名称，否则不予通过审核。</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name"><span>*</span>类别：</td>
                                        <td>
                                            <label class="w100"><input type="radio" name="company_type"
                                                <#if qpEnterpriseCertificationInfo.company_type=="1">
                                                                       checked="checked"
                                                </#if> value="1">&nbsp;维修厂</label>
                                            <label class="w100"><input type="radio" name="company_type"
                                                <#if qpEnterpriseCertificationInfo.company_type=="2">
                                                                       checked="checked"</#if>
                                                                       value="2">&nbsp;4S店</label>
                                            <label class="w100"><input type="radio" name="company_type"
                                                <#if qpEnterpriseCertificationInfo.company_type=="3">
                                                                       checked="checked"
                                                </#if>  value="3">&nbsp;快修连锁</label>
                                            <label class="w100"><input type="radio" name="company_type"
                                                <#if qpEnterpriseCertificationInfo.company_type=="4">
                                                                       checked="checked"
                                                </#if>  value="4">&nbsp;经销商</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name"><span>*</span>门头照片：</td>
                                        <td>
                                            <#if (qpEnterpriseCertificationInfo.company_pic_url?split("##")?size>=2)>
                                                <input type="hidden" name="company_pic1init" id="company_pic1init"
                                                       value="${qpEnterpriseCertificationInfo.company_pic_url?split("##")[0]}">
                                            </#if>
                                            <#if (qpEnterpriseCertificationInfo.company_pic_url?split("##")?size>=3)>
                                                <input type="hidden" name="company_pic3init" id="company_pic2init"
                                                       value="${qpEnterpriseCertificationInfo.company_pic_url?split("##")[1]}">
                                            </#if>
                                            <#if (qpEnterpriseCertificationInfo.company_pic_url?split("##")?size>=4)>
                                                <input type="hidden" name="company_pic3init" id="company_pic3init"
                                                       value="${qpEnterpriseCertificationInfo.company_pic_url?split("##")[2]}">
                                            </#if>
                                            <ul class="qpPap clearfix">
                                                <li>
                                                    <div class="qp-papers" id="company_pic1div" align="center"
                                                        <#if (qpEnterpriseCertificationInfo.company_pic_url?split("##")?size>=2)>
                                                         style="background-image: url(${qpEnterpriseCertificationInfo.company_pic_url?split("##")[0]}); background-size: 140px 96px"
                                                        <#else>
                                                         style="background-image: url(${basePath}/images/qpmalenterprise/qp_images/photo.jpg);background-size: 140px 96px"
                                                        </#if>>
                                                        <input type="file" id="company_pic1" name="company_pic1"
                                                               onchange="picUploadRe(this,'company_pic1')"/>
                                                    </div>
                                                    <a onclick="clearpic('company_pic1')">点击清除图片</a>
                                                </li>
                                                <li>


                                                    <div class="qp-papers" id="company_pic2div" align="center"
                                                        <#if (qpEnterpriseCertificationInfo.company_pic_url?split("##")?size>=3)>
                                                         style="background-image: url(${qpEnterpriseCertificationInfo.company_pic_url?split("##")[1]}); background-size: 140px 96px"
                                                        <#else>
                                                         style="background-image: url(${basePath}/images/qpmalenterprise/qp_images/photo.jpg); background-size: 140px 96px"
                                                        </#if>
                                                            >
                                                        <input type="file" id="company_pic2" name="company_pic2"
                                                               onchange="picUploadRe(this,'company_pic2')"/>
                                                    </div>
                                                    <a onclick="clearpic('company_pic2')">点击清除图片</a>
                                                </li>
                                                <li>

                                                    <div class="qp-papers" id="company_pic3div" align="center"
                                                        <#if qpEnterpriseCertificationInfo.company_pic_url?split("##")?size==4>
                                                         style="background-image: url(${qpEnterpriseCertificationInfo.company_pic_url?split("##")[2]});background-size: 140px 96px"
                                                         ;
                                                        <#else>
                                                         style="background-image: url(${basePath}/images/qpmalenterprise/qp_images/photo.jpg); background-size: 140px 96px"
                                                        </#if>
                                                            >
                                                        <input type="file" id="company_pic3" name="company_pic3"
                                                               onchange="picUploadRe(this,'company_pic3')"/>
                                                    </div>
                                                    <a onclick="clearpic('company_pic3')">点击清除图片</a>
                                                </li>

                                            </ul>
                                            <p class="qTipinit" id="company_picinfo">请上传清晰照片(最少1张最多3张)。</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name"><span>*</span>所属区域：</td>
                                        <td>
                                            <select name="enterprise_province" id="province" class="form-control sm-sel province"
                                                    onchange="loadCity(this.value)">
                                                <option value="">请选择</option>
                                            </select>
                                            <select name="enterprise_city" id="city" class="form-control sm-sel city"
                                                    onchange="loadDistrict(this.value)">
                                                <option value="">请选择</option>
                                            </select>
                                            <select name="enterprise_county" id="district" class="form-control sm-sel district">
                                                <option value="">请选择</option>
                                            </select>

                                            <p class="qTip" id="districtinfo">省，市，区全部必选。</p>
                                        <#--<span>必填</span>-->
                                        <#--<p class="qTipred"  id="company_nameinfo" style="display: none">必填</p>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;"><span>*</span>详细地址：</td>
                                        <td><input type="text" class="qNomarl-text" style="width:440px;" name="company_contact_addr"
                                                   id="company_contact_addr"
                                                   placeholder="详细地址" value="${(qpEnterpriseCertificationInfo.company_contact_addr)!}"/>

                                            <p class="qTip" id="company_contact_addrdetinfo">请填写详细地址。</p>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">法人：</td>
                                        <td><input type="text" class="qNomarl-text" name="buss_legal_name"
                                                   value="${(qpEnterpriseCertificationInfo.buss_legal_name)!}"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">身份证号：</td>
                                        <td><input type="text" class="qNomarl-text" name="buss_legal_card_id"
                                                   value="${qpEnterpriseCertificationInfo.buss_legal_card_id}"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">注册资本：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_capital" id="company_capital" placeholder="单位“万元”，请输入数字"
                                                   value="${(qpEnterpriseCertificationInfo.company_capital)!}"/>
                                            <p class="qTip" id="company_capitalinit">请输入数字(可以选择不输入)</p></td>

                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">经营品牌：</td>
                                        <td><input type="text" class="qNomarl-text" placeholder="如‘大众’、‘奥迪’。" name="buss_brand"
                                                   value="${(qpEnterpriseCertificationInfo.buss_brand)!}"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">经营范围：</td>
                                        <td><input type="text" class="qNomarl-text" style="width:440px;" name="buss_range"
                                                   value="${(qpEnterpriseCertificationInfo.buss_range)!}"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">企业资质：</td>
                                        <td><input type="text" class="qNomarl-text" name="enterprise_aptitude"
                                                   value="${(qpEnterpriseCertificationInfo.enterprise_aptitude)!}"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;"><span>*</span>上传证件照：</td>
                                        <td>
                                            <input type="hidden" name="buss_urlinit" id="buss_urlinit"
                                                   value="${(qpEnterpriseCertificationInfo.busscard_url)!}">
                                            <input type="hidden" name="buss_dept_noinit" id="buss_dept_noinit"
                                                   value="${(qpEnterpriseCertificationInfo.buss_dept_no_url)!}">
                                            <input type="hidden" name="buss_tax_registinit" id="buss_tax_registinit"
                                                   value="${(qpEnterpriseCertificationInfo.buss_tax_regist_url)!}">
                                            <ul class="qpPap clearfix">
                                                <li>
                                                    <div class="qp-papers" id="buss_urldiv" align="center"
                                                         style="background-image: url(${(qpEnterpriseCertificationInfo.busscard_url)!}); background-size: 140px 96px">
                                                        <input type="file" id="buss_url" name="buss_url"
                                                               onchange="picUploadRe(this,'buss_url')"/>
                                                    </div>

                                                    <p>营业执照副本</p>
                                                </li>
                                                <li>
                                                    <div class="qp-papers" id="buss_dept_nodiv" align="center"
                                                         style="background-image: url(${(qpEnterpriseCertificationInfo.buss_dept_no_url)!}); background-size: 140px 96px">
                                                        <input type="file" id="buss_dept_no" name="buss_dept_no"
                                                               onchange="picUploadRe(this,'buss_dept_no')"/>
                                                    </div>
                                                    <p onclick="clearPic('buss_dept_no')">组织机构代码</p>
                                                </li>
                                                <li>
                                                    <div class="qp-papers" id="buss_tax_registdiv" align="center"
                                                         style="background-image: url(${(qpEnterpriseCertificationInfo.buss_tax_regist_url)!}); background-size: 140px 96px">
                                                        <input type="file" id="buss_tax_regist" name="buss_tax_regist"
                                                               onchange="picUploadRe(this,'buss_tax_regist')"/>
                                                    </div>
                                                    <p onclick="clearPic('buss_tax_regist')">税务登记证</p>
                                                </li>
                                            </ul>
                                            <p class="qTip" id="buss_tax_registinfo">请上传全部证件照片</p>
                                        </td>
                                    </tr>
                                </table>
                            <#else>
                                <table class="auther-list">
                                    <tr>
                                        <td class="qp-name"><span>*</span>汽修厂名称：</td>
                                        <td>
                                            <input type="text" class="qLong-text" name="company_name" id="company_name"
                                                   placeholder="请填写营业执照名称"/><br/>

                                            <p class="qTip" id="company_nameinfo">请填写营业执照名称，否则不予通过审核。</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name"><span>*</span>类别：</td>
                                        <td>
                                            <label class="w100"><input type="radio" checked="checked" name="company_type" value="1">&nbsp;维修厂</label>
                                            <label class="w100"><input type="radio" name="company_type" value="2">&nbsp;4S店</label>
                                            <label class="w100"><input type="radio" name="company_type" value="3">&nbsp;快修连锁</label>
                                            <label class="w100"><input type="radio" name="company_type" value="4">&nbsp;经销商</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name"><span>*</span>门头照片：</td>
                                        <td>
                                            <ul class="qpPap clearfix">
                                                <li>
                                                    <div class="qp-papers" id="company_pic1div" align="center"
                                                         style="background-image: url(${basePath}/images/qpmalenterprise/qp_images/photo.jpg);">
                                                        <input type="file" id="company_pic1" name="company_pic1"
                                                               onchange="picUploadRe(this,'company_pic1')"/>
                                                    </div>
                                                    <a onclick="clearpic('company_pic1')">点击清除图片</a>
                                                </li>
                                                <li>


                                                    <div class="qp-papers" id="company_pic2div" align="center"
                                                         style="background-image: url(${basePath}/images/qpmalenterprise/qp_images/photo.jpg);">
                                                        <input type="file" id="company_pic2" name="company_pic2"
                                                               onchange="picUploadRe(this,'company_pic2')"/>

                                                    </div>
                                                    <a onclick="clearpic('company_pic2')">点击清除图片</a>
                                                </li>
                                                <li>


                                                    <div class="qp-papers" id="company_pic3div" align="center"
                                                         style="background-image: url(${basePath}/images/qpmalenterprise/qp_images/photo.jpg);">
                                                        <input type="file" id="company_pic3" name="company_pic3"
                                                               onchange="picUploadRe(this,'company_pic3')"/>
                                                    </div>
                                                    <a onclick="clearpic('company_pic3')">点击清除图片</a>
                                                </li>

                                            </ul>
                                            <p class="qTipinit" id="company_picinfo">请上传清晰照片(最少1张最多3张)。</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name"><span>*</span>所属区域：</td>
                                        <td>
                                            <select name="enterprise_province" id="province" class="form-control sm-sel province"
                                                    onchange="loadCity(this.value)">
                                                <option value="">请选择</option>
                                            </select>
                                            <select name="enterprise_city" id="city" class="form-control sm-sel city"
                                                    onchange="loadDistrict(this.value)">
                                                <option value="">请选择</option>
                                            </select>
                                            <select name="enterprise_county" id="district" class="form-control sm-sel district">
                                                <option value="">请选择</option>
                                            </select>

                                            <p class="qTip" id="districtinfo">省，市，区全部必选。</p>
                                        <#--<span>必填</span>-->
                                        <#--<p class="qTipred"  id="company_nameinfo" style="display: none">必填</p>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;"><span>*</span>详细地址：</td>
                                        <td><input type="text" class="qNomarl-text" style="width:440px;" name="company_contact_addr"
                                                   id="company_contact_addr"
                                                   placeholder="详细地址"/>

                                            <p class="qTip" id="company_contact_addrdetinfo">请填写详细地址。</p>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">法人：</td>
                                        <td><input type="text" class="qNomarl-text" name="buss_legal_name"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">身份证号：</td>
                                        <td><input type="text" class="qNomarl-text" name="buss_legal_card_id"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">注册资本：</td>
                                        <td><input type="text" class="qNomarl-text" placeholder="单位“万元”，请输入数字" name="company_capital" id="company_capital"/>
                                            <p class="qTip" id="company_capitalinit">请输入数字(可以选择不输入)</p></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">经营品牌：</td>
                                        <td><input type="text" class="qNomarl-text" placeholder="如‘大众’、‘奥迪’。" name="buss_brand"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">经营范围：</td>
                                        <td><input type="text" class="qNomarl-text" style="width:440px;" name="buss_range"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">企业资质：</td>
                                        <td><input type="text" class="qNomarl-text" name="enterprise_aptitude"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;"><span>*</span>上传证件照：</td>
                                        <td>
                                            <ul class="qpPap clearfix">
                                                <li>
                                                    <div class="qp-papers" id="buss_urldiv" align="center"
                                                         style="background-image: url(${basePath}/images/qpmalenterprise/qp_images/photo.jpg);">
                                                        <input type="file" id="buss_url" name="buss_url"
                                                               onchange="picUploadRe(this,'buss_url')"/>
                                                    <#--<img src="${basePath}/images/qpmalenterprise/qp_images/add-photo.jpg"
                                                         width="140" height="100" alt="门头照片"/>-->
                                                    </div>

                                                    <p>营业执照副本</p>
                                                </li>
                                                <li>

                                                    <div class="qp-papers" id="buss_dept_nodiv" align="center"
                                                         style="background-image: url(${basePath}/images/qpmalenterprise/qp_images/photo.jpg) ;">
                                                        <input type="file" id="buss_dept_no" name="buss_dept_no"
                                                               onchange="picUploadRe(this,'buss_dept_no')"/>
                                                    </div>
                                                    <p onclick="clearPic('buss_dept_no')">组织机构代码</p>
                                                </li>
                                                <li>


                                                    <div class="qp-papers" id="buss_tax_registdiv" align="center"
                                                         style="background-image: url(${basePath}/images/qpmalenterprise/qp_images/photo.jpg) ;">
                                                        <input type="file" id="buss_tax_regist" name="buss_tax_regist"
                                                               onchange="picUploadRe(this,'buss_tax_regist')"/>
                                                    </div>
                                                    <p onclick="clearPic('buss_tax_regist')">税务登记证</p>
                                                </li>
                                            </ul>
                                            <p class="qTip" id="buss_tax_registinfo">请上传全部证件照片</p>
                                        </td>
                                    </tr>
                                </table>
                            </#if>
                                <p class="tit-top mb20">联系方式</p>
                            <#if qpEnterpriseCertificationInfo??>
                                <table class="auther-list">
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;"><span>*</span>联系人：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_contact_name"
                                                   id="company_contact_name"
                                                   value="${(qpEnterpriseCertificationInfo.company_contact_name)!}"/>

                                            <p class="qTip" id="company_contact_nameinfo">请填写联系人</p></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;"><span>*</span>联系电话：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_contact_moble"
                                                   id="company_contact_moble"
                                                   value="${(qpEnterpriseCertificationInfo.company_contact_moble)!}"/>

                                            <p class="qTip" id="company_contact_mobleinfo">请填写正确的手机号</p></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;"><span>*</span>Email：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_email" id="company_email"
                                                   value="${(qpEnterpriseCertificationInfo.company_email)!}"/>

                                            <p class="qTip" id="company_emailinfo">请填写正确的电子邮箱</p></td>
                                    </tr>
                                <#--<tr>
                                    <td class="qp-name" style="padding-top:15px;">通信地址：</td>
                                    <td><input type="text" class="qNomarl-text" style="width:440px;" name="company_contact_addr"/>
                                    </td>
                                </tr>-->
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">固定电话：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_contact_tel"
                                                   value="${(qpEnterpriseCertificationInfo.company_contact_tel)!}"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">传真：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_contact_cz"
                                                   value="${(qpEnterpriseCertificationInfo.company_contact_cz)!}"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">邮编：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_contact_yb"
                                                   value="${(qpEnterpriseCertificationInfo.company_contact_yb)!}"/></td>
                                    </tr>

                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">&nbsp;</td>
                                        <td><input type="checkbox" checked="checked" id="checkContent111" value="1"/>我已阅读并同意<a
                                                href="javascript:;" style="color: #0066cc"
                                                onmouseover="this.style.color='#ca2f2b'"
                                                onmouseout="this.style.color='#0066cc'"
                                                onclick="openStaticPopup()">《千品猫维修用户认证协议》</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">&nbsp;</td>
                                        <td><a href="#" class="qpButton" onclick="forsubmit()">提交认证信息<a></td>
                                    </tr>
                                </table>
                            <#else>
                                <table class="auther-list">
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;"><span>*</span>联系人：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_contact_name"
                                                   id="company_contact_name"/>

                                            <p class="qTip" id="company_contact_nameinfo">请填写联系人</p></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;"><span>*</span>联系电话：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_contact_moble"
                                                   id="company_contact_moble"/>

                                            <p class="qTip" id="company_contact_mobleinfo">请填写正确的联系手机号</p></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;"><span>*</span>Email：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_email" id="company_email"/>

                                            <p class="qTip" id="company_emailinfo">请填写正确的电子邮箱</p></td>
                                    </tr>
                                <#--<tr>
                                    <td class="qp-name" style="padding-top:15px;">通信地址：</td>
                                    <td><input type="text" class="qNomarl-text" style="width:440px;" name="company_contact_addr"/>
                                    </td>
                                </tr>-->
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">固定电话：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_contact_tel"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">传真：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_contact_cz"/></td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">邮编：</td>
                                        <td><input type="text" class="qNomarl-text" name="company_contact_yb"/></td>
                                    </tr>

                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">&nbsp;</td>
                                        <td><input type="checkbox" checked="checked" id="checkContent111" value="1"/>我已阅读并同意<a
                                                href="javascript:;" style="color: #0066cc"
                                                onmouseover="this.style.color='#ca2f2b'"
                                                onmouseout="this.style.color='#0066cc'"
                                                onclick="openStaticPopup()">《千品猫维修用户认证协议》</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="qp-name" style="padding-top:15px;">&nbsp;</td>
                                        <td><a href="#" class="qpButton" onclick="forsubmit()">提交认证信息<a></td>
                                    </tr>
                                </table>
                            </#if>
                            </form>
                        </div>

                    </div>


                </div>
            </div>

        </div>
    </div>
</div>
</div>

<div class="mask"></div>
<div class="member-dialog dia2">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>

                <div class="fl tl">
                    <p class="f16 red" id="con_00">修改成功！</p>

                    <div class="m-btn mt20">
                        <a href="javascript:cls();">确定</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="mask" style="display:none"></div>
<div class="qpCustomer" style="display: none">
    <div class="dia_tit" align="center">
    ${(topmap.systembase.bsetName)!''}企业认证协议
        <a class="dia_close" href="javascript:;" onclick="cls()">&nbsp;&nbsp;</a>
    </div>
    <div class="dia_cont">
        <div class="agreement_wp">
        ${parameter.bsetEnterpriseagreement!}
            <div class="mt20 tc"><a class="agree_btn" href="javascript:;" onclick="agreeonProtocol1();">同意并继续</a></div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<#--引入底部 <#include "/index/bottom.ftl" /> -->
<#include "../../index/newbottom.ftl" />
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/scroller.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/userInfo.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/dateclass.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/yearclass.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/education.js"></script>
<script type="text/javascript">
    function openStaticPopup() {
        $(".qpCustomer").fadeIn();
//
    }
    function agreeonProtocol1() {
        $("#checkContent111").prop("checked", true);
        cls();
    }
    function cls() {

        $(".qpCustomer").fadeOut();

    }
    function qpdia() {
        $(".mask").show();
        $(".qpCustomer").show();
    }
    /**
     * 实现图片上传的预览显示效果
     * @param file
     * @param name
     */
    function picUploadRe(file, name) {
        if (file.files && file.files[0]) {
            var prevDiv = document.getElementById(name+"div");
            var reader = new FileReader();
            /*var size=file.files[0].size;*/
            reader.onload = function (evt) {
                //var wi= evt.width();
                prevDiv.style.backgroundImage = "url(" + evt.target.result + ")";
                prevDiv.style.backgroundSize = "140px 96px";
            }
            if(document.getElementById(name + "init")!=null&&
                    document.getElementById(name + "init")!="") {
                document.getElementById(name + "init").value = "";
            }
            reader.readAsDataURL(file.files[0]);
        }
    }

    $(document).ready(function () {
        loadProvice();
        loadALLCity();
        loadAllDistrict();
        $.ajax({
            type: 'get',
            url: 'initMyAddressAjax.htm',
            async: false,
            success: function (data) {
                if (data!=null&&data.length != 0) {
                    $("#province").val(data[0]);
                    $("#city").val(data[1]);
                    $("#district").val(data[2]);
                }
            }
        });
    });

    /**
     * 加载省份
     */
    function loadProvice() {
        //var options = "<option value='' >"+"请选择"+"</option>";
        $.ajax({
            type: 'post',
            url: 'getQPAllProvince.htm',
            async: false,
            success: function (data) {
                var options = "";
                options += "<option value=''>" + "请选择" + "</option>";
                for (var i = 0; i < data.length; i++) {
                    var province = data[i];
                    options += "<option value='" + province.provinceId + "'>" + province.provinceName + "</option>";
                }
                $(".province").html(options);
            }
        });
    }
    /**
     * 加载所有城市
     *
     */
    function loadALLCity() {
        //var paramStr = "provinceId=" + pid;
        $.ajax({
            type: 'post',
            url: 'getQPAllCities.htm',
            async: false,
            success: function (data) {
                if (data.length != 0) {
                    var options = "";
                    options += "<option value=''>" + "请选择" + "</option>";

                    for (var i = 0; i < data.length; i++) {
                        var city = data[i];
                        options += "<option value='" + city.cityId + "'>" + city.cityName + "</option>";
                    }
                    $(".city").html(options);
                } else {
                    $(".city").html("<option value='' >" + "请选择" + "</option>");
                    $(".district").html("<option value='' >" + "请选择" + "</option>");
                    /*$("#street").html("<option value='' >"+"请选择"+"</option>");*/
                }
            }
        });
    }
    /**
     * 加载所有区
     *
     */
    function loadAllDistrict() {
        $.ajax({
            type: 'post',
            url: 'getQPAllDistrict.htm',
            async: false,
            success: function (data) {
                if (data.length != 0) {
                    var options = "";
                    options += "<option value=''>" + "请选择" + "</option>";

                    for (var i = 0; i < data.length; i++) {
                        var district = data[i];
                        options += "<option value='" + district.districtId + "'>" + district.districtName + "</option>";
                    }
                    $('.district').html(options);
                } else {
                    $(".district").html("<option value='' >" + "请选择" + "</option>");
                }
            }
        });
    }
    /**
     * 加载城市
     * @param pid 省份编号
     */
    function loadCity(pid) {
        var paramStr = "provinceId=" + pid;
        if (pid == "") {
            $.ajax({
                type: 'post',
                url: 'getQPAllCities.htm',
                async: false,
                success: function (data) {
                    if (data.length != 0) {
                        var options = "";
                        options += "<option value=''>" + "请选择" + "</option>";

                        for (var i = 0; i < data.length; i++) {
                            var city = data[i];
                            options += "<option value='" + city.cityId + "'>" + city.cityName + "</option>";
                        }
                        $(".city").html(options);
                    } else {
                        $(".city").html("<option value='' >" + "请选择" + "</option>");
                        $(".district").html("<option value='' >" + "请选择" + "</option>");
                        /*$("#street").html("<option value='' >"+"请选择"+"</option>");*/
                    }
                }
            });
        } else {
            $.ajax({
                type: 'post',
                url: 'getQPAllCityByPid.htm',
                data: paramStr,
                async: false,
                success: function (data) {
                    if (data.length != 0) {
                        var options = "";
                        options += "<option value=''>" + "请选择" + "</option>";

                        for (var i = 0; i < data.length; i++) {
                            var city = data[i];
                            options += "<option value='" + city.cityId + "'>" + city.cityName + "</option>";
                        }
                        $(".city").html(options);
                    } else {
                        $(".city").html("<option value='' >" + "请选择" + "</option>");
                        $(".district").html("<option value='' >" + "请选择" + "</option>");
                        /*$("#street").html("<option value='' >"+"请选择"+"</option>");*/
                    }
                }
            });
        }

    }
    function loadDistrict(pid) {
        var paramStr = "cityId=" + pid;
        if (pid == "") {
            $.ajax({
                type: 'post',
                url: 'getQPAllDistrict.htm',
                async: false,
                success: function (data) {
                    if (data.length != 0) {
                        var options = "";
                        options += "<option value=''>" + "请选择" + "</option>";

                        for (var i = 0; i < data.length; i++) {
                            var district = data[i];
                            options += "<option value='" + district.districtId + "'>" + district.districtName + "</option>";
                        }
                        $('.district').html(options);
                    } else {
                        $(".district").html("<option value='' >" + "请选择" + "</option>");
                    }
                }
            });
        } else {
            $.ajax({
                type: 'post',
                url: 'getQPAllDistrictByCid.htm',
                data: paramStr,
                async: false,
                success: function (data) {
                    if (data.length != 0) {
                        var options = "";
                        options += "<option value=''>" + "请选择" + "</option>";

                        for (var i = 0; i < data.length; i++) {
                            var district = data[i];
                            options += "<option value='" + district.districtId + "'>" + district.districtName + "</option>";
                        }
                        $('.district').html(options);
                    } else {
                        $(".district").html("<option value='' >" + "请选择" + "</option>");
                    }
                }
            });
        }

    }
    function forsubmit() {
        var checkContent111 = document.getElementById("checkContent111");
        if (!checkContent111.checked) {
            alert("请先阅读《千品猫维修用户认证协议》!");
        } else if (checkinfo()) {
            //alert("*开头的是必填选项，请填写必填项！");
        } else {
            document.getElementById("authentication_from").submit();
        }

    }
    //手机号码正则验证函数
    function checkMobileaddNew(mobilePhone) {
        var sMobile = mobilePhone == null ? "" : mobilePhone.trim();
        //var re1=/^[0][1-9]{2,3}-[0-9]{5,10}$/;
        var re2 = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if (re2.test(sMobile)) {
            return true;
        } else {
            return false;

        }

    }
    function checkinfo() {
        var flag = false;
        var company_name = document.getElementById("company_name").value;
        if(!(document.getElementById("company_capital").value==null||document.getElementById("company_capital").value==""||
                (/(^\d*$)|(^\S+\s+\S+$)/.test(document.getElementById("company_capital").value))) ){
            document.getElementById("company_capitalinit").className = "qTipred";
        }else{
            document.getElementById("company_capitalinit").className = "qTip";
        }
        if (company_name == null || company_name == "") {
            document.getElementById("company_nameinfo").className = "qTipred";
//            document.getElementById("company_nameinfo").style.color="red";
            flag = true;
        }else{
            document.getElementById("company_nameinfo").className = "qTip";
        }
//        alert(document.getElementById("province").value == "");
        if ((document.getElementById("company_pic1").value == "" && document.getElementById("company_pic2").value == "" &&
                document.getElementById("company_pic3").value == "") && (
                (document.getElementById("company_pic1init")==null||document.getElementById("company_pic1init").value == "")
                && (document.getElementById("company_pic2init")==null||document.getElementById("company_pic2init").value == "") &&
                (document.getElementById("company_pic3init")==null||document.getElementById("company_pic3init").value == ""))) {
            document.getElementById("company_picinfo").className = "qTipred";
            flag = true;
        }else{
            document.getElementById("company_picinfo").className = "qTipinit";
        }
        if (document.getElementById("province").value == "" || document.getElementById("city").value == ""
                || document.getElementById("district").value == "") {
            document.getElementById("districtinfo").className = "qTipred";
            flag = true;
        }else{
            document.getElementById("districtinfo").className = "qTip";
        }
        if ((document.getElementById("buss_url").value == "" || document.getElementById("buss_dept_no").value == ""
                || document.getElementById("buss_tax_regist").value == "") &&
                (document.getElementById("buss_urlinit")==null||document.getElementById("buss_urlinit").value == "" ||
                document.getElementById("buss_dept_noinit")==null||document.getElementById("buss_dept_noinit").value == ""
                || document.getElementById("buss_tax_registinit")==null||document.getElementById("buss_tax_registinit").value == "")) {
            document.getElementById("buss_tax_registinfo").className = "qTipred";
            flag = true;
        }else{
            document.getElementById("buss_tax_registinfo").className = "qTip";
        }

        if (document.getElementById("company_contact_name").value == "") {
            document.getElementById("company_contact_nameinfo").className = "qTipred";
            flag = true;
        }else{
            document.getElementById("company_contact_nameinfo").className = "qTip";
        }
        if (document.getElementById("company_contact_moble").value == "" || !checkMobileaddNew(document.getElementById("company_contact_moble").value)) {
            document.getElementById("company_contact_mobleinfo").className = "qTipred";
            flag = true;
        }else{
            document.getElementById("company_contact_mobleinfo").className = "qTip";
        }
        if (document.getElementById("company_email").value == ""||!isValidMail(document.getElementById("company_email").value)) {
            document.getElementById("company_emailinfo").className = "qTipred";
            flag = true;
        }else{
            document.getElementById("company_emailinfo").className = "qTip";
        }
        if (document.getElementById("company_contact_addr").value == "") {
            document.getElementById("company_contact_addrdetinfo").className = "qTipred";
            flag = true;
        }else{
            document.getElementById("company_contact_addrdetinfo").className = "qTip";
        }
        return flag;
    }
    function isValidMail(emal) {
    　　      var Regex = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;
　　      if (Regex.test(emal)) {
        　return true;
        } else {
            return false;
        }
    }
    function clearpic(divname) {
        document.getElementById(divname + "div").style.backgroundImage = "url(${basePath}/images/qpmalenterprise/qp_images/photo.jpg)";
        document.getElementById(divname + "div").style.backgroundSize = "140px 96px";
        document.getElementById(divname + "div").style.backgroundSize = "140px 96px";
        document.getElementById(divname).outerHTML = document.getElementById(divname).outerHTML;
        if(document.getElementById(divname + "init")!=null&&document.getElementById(divname + "init")!="") {
            document.getElementById(divname + "init").value = "";
        }
    }
</script>
</@htmlBody>