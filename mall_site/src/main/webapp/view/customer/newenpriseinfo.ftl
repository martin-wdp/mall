<#include "../include/common.ftl">
<@htmlHead title="认证信息-${topmap.systembase.bsetName}">
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/base.min.css"/>
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/pages.css"/>
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/qp_style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
</@htmlHead>
   <#-- <head>
        <title>认证信息-${topmap.systembase.bsetName}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta name="Keywords" content="${topmap.seo.meteKey}">
        <meta name="description" content="${topmap.seo.meteDes}">
    <#assign basePath=request.contextPath>
    <#if (topmap.systembase.bsetHotline)??>
        <link rel="Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
    <#else>
        <link rel="Shortcut Icon" href="${basePath}/images/Paistore.ico">
    </#if>
        <link rel="stylesheet" href="${basePath}/css/qpmalenterprise/base.min.css"/>
        <link rel="stylesheet" href="${basePath}/css/qpmalenterprise/pages.css"/>
        <link rel="stylesheet" href="${basePath}/css/qpmalenterprise/qp_style.css"/>
        <link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
        <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
    </head>-->
<@htmlBody>
<input type="hidden" name="CSRFToken" value="${token}">

<#--一引入头部 <#include "/index/topnew.ftl" />  -->
<#include "../index/newtop7.ftl">
<#include "newtop.ftl"/>
<div style="background: #f5f5f5;">
    <div class="container clearfix pt20 pb10">
        <!--new_member_left-->
    <#include "newleftmenu.ftl"/>
        <div class="new_member-right">
            <div class="new_order_list">
                <div class="n-title">认证信息</div>
                <div class="tagMenu order-menu pr">
                    <ul class="menu clearfix">
                        <li class="current">基本信息</li>
                    </ul>
                    <div class="aco-link">
                        注：修改邮箱、手机请到<a href="${basePath}/customer/securitycenter.html">账户安全</a>
                    </div>
                </div>
                <div class="content">
                    <div class="layout">
                    <#if (qpEnterpriseCertificationInfo)??>
                        <form method="post" action="${basePath}/updateEnpriseInfo.htm" id="updateEninfo" enctype="multipart/form-data">
                            <input type="hidden" id="enterprise_id" name="enterprise_id" value="${qpEnterpriseCertificationInfo.enterprise_id}"/>
                            <table class="auth-table">
                                <tr>
                                    <td class="auth-td-title">
                                        <span>*</span>汽修厂名称：
                                    </td>
                                    <td class="auth-td-content">
                                        ${qpEnterpriseCertificationInfo.company_name}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        <span>*</span>会员等级：
                                    </td>
                                    <td class="auth-td-content">
                                        <#if vip?? && vip !="1" >普通会员<#else>企业会员</#if><br/>
                                    </td>
                                <tr>
                                    <td class="auth-td-title">
                                        <span>*</span>类别：
                                    </td>
                                    <td class="auth-td-content">
                                        <#if qpEnterpriseCertificationInfo.company_type=="1" >
                                            维修厂
                                        </#if>
                                        <#if qpEnterpriseCertificationInfo.company_type=="2">
                                            4S店</#if>
                                        <#if qpEnterpriseCertificationInfo.company_type=="3" >
                                            快修连锁
                                        </#if>
                                        <#if qpEnterpriseCertificationInfo.company_type=="4">
                                            经销商
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        <span>*</span>门头照片：
                                    </td>
                                    <td class="auth-td-content">
                                        <ul class="qpPap clearfix">
                                            <#if (qpEnterpriseCertificationInfo.company_pic_url?split("##")?size>=2)>
                                                <li>
                                                    <div class="qp-papers" id="company_pic1div" align="center">
                                                        <img src="${qpEnterpriseCertificationInfo.company_pic_url?split("##")[0]}"
                                                             width="140px" height="96px" alt="门头照片"/>
                                                    </div>
                                                </li>
                                            </#if>
                                            <#if (qpEnterpriseCertificationInfo.company_pic_url?split("##")?size>=3)>
                                                <li>
                                                    <div class="qp-papers" id="company_pic2div" align="center">
                                                        <img src="${qpEnterpriseCertificationInfo.company_pic_url?split("##")[1]}"
                                                             width="140px" height="96px" alt="门头照片"/>
                                                    </div>
                                                </li>
                                            </#if>
                                            <#if (qpEnterpriseCertificationInfo.company_pic_url?split("##")?size>=4)>
                                                <li>
                                                    <div class="qp-papers" id="company_pic3div" align="center">
                                                        <img src="${qpEnterpriseCertificationInfo.company_pic_url?split("##")[2]}"
                                                             width="140px" height="96px" alt="门头照片"/>
                                                    </div>
                                                </li>
                                            </#if>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        <span>*</span>所属区域：
                                    </td>
                                    <td class="auth-td-content">
                                        <input type="hidden" id="provinceinit" value="${(qpEnterpriseCertificationInfo.enterprise_province)!''}"/>
                                        <input type="hidden" id="cityinit" value="${(qpEnterpriseCertificationInfo.enterprise_city)!''}"/>
                                        <input type="hidden" id="districtinit" value="${(qpEnterpriseCertificationInfo.enterprise_county)!''}"/>
                                        <select name="enterprise_province" id="province"
                                                class="form-control sm-sel province"
                                                onchange="loadCity(this.value)" disabled>
                                            <option value="">请选择</option>
                                        </select>

                                        <select name="enterprise_city" id="city" class="form-control sm-sel city"
                                                onchange="loadDistrict(this.value)" disabled>
                                            <option value="">请选择</option>
                                        </select>
                                        <select name="enterprise_county" id="district"
                                                class="form-control sm-sel district" disabled>
                                            <option value="">请选择</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        <span>*</span>详细地址：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.company_contact_addr)!}
                                    </td>
                                </tr>
                            <#if vip?? && vip !="1" >
                                <tr>
                                    <td class="auth-td-title">
                                        法人：
                                    </td>
                                    <td class="auth-td-content">
                                        <input type="text" class="qNomarl-text" name="buss_legal_name" value="${(qpEnterpriseCertificationInfo.buss_legal_name)!}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        身份证号：
                                    </td>
                                    <td class="auth-td-content">
                                        <input type="text" class="qNomarl-text" name="buss_legal_card_id" value="${qpEnterpriseCertificationInfo.buss_legal_card_id}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        注册资本：
                                    </td>
                                    <td class="auth-td-content">
                                        <input type="text" class="qNomarl-text" name="company_capital" id="company_capital" placeholder="单位“万元”，请输入数字"
                                               value="${(qpEnterpriseCertificationInfo.company_capital)!}"/>
                                        <p class="qTip" id="company_capitalinit">请输入数字(可以选择不输入)</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        经营品牌：
                                    </td>
                                    <td class="auth-td-content">
                                        <input type="text" class="qNomarl-text" placeholder="如‘大众’、‘奥迪’。" name="buss_brand" value="${(qpEnterpriseCertificationInfo.buss_brand)!}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        经营范围：
                                    </td>
                                    <td class="auth-td-content">
                                        <input type="text" class="qNomarl-text" style="width:440px;" name="buss_range" value="${(qpEnterpriseCertificationInfo.buss_range)!}"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="auth-td-title">
                                        企业资质：
                                    </td>
                                    <td class="auth-td-content">
                                        <input type="text" class="qNomarl-text" name="enterprise_aptitude" value="${(qpEnterpriseCertificationInfo.enterprise_aptitude)!}"/>
                                    </td>
                                </tr>
                            <#else>
                                <tr>
                                    <td class="auth-td-title">
                                        法人：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.buss_legal_name)!}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        身份证号：
                                    </td>
                                    <td class="auth-td-content">
                                        ${qpEnterpriseCertificationInfo.buss_legal_card_id}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        注册资本：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.company_capital)!}
                                    </td>
                                </tr>
                                    <td class="auth-td-title">
                                        经营品牌：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.buss_brand)!}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        经营范围：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.buss_range)!}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        企业资质：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.enterprise_aptitude)!}
                                    </td>
                                </tr>
                            </#if>
                                <tr>
                                    <td class="auth-td-title" >
                                        <span>*</span>上传证件照：
                                    </td>
                                    <td class="auth-td-content">
                                        <ul class="qpPap clearfix">
                                            <li>
                                                <p><#--<input type="file" id="buss_url" name="buss_url"
                                          onchange="picUploadRe(this,'buss_urldiv')"/>--></p>

                                                <div class="qp-papers" id="buss_urldiv" align="center">
                                                    <img src="${(qpEnterpriseCertificationInfo.busscard_url)!}"
                                                         width="140" height="100" alt="营业执照副本"/>
                                                </div>

                                                <p>营业执照副本</p>
                                            </li>
                                            <li>
                                                <p><#--<input type="file" id="buss_dept_no" name="buss_dept_no"
                                          onchange="picUploadRe(this,'buss_dept_nodiv')"/>--></p>

                                                <div class="qp-papers" id="buss_dept_nodiv" align="center">
                                                    <img src="${(qpEnterpriseCertificationInfo.buss_dept_no_url)!}"
                                                         width="140" height="100" alt="组织机构代码"/>
                                                </div>
                                                <p onclick="clearPic('buss_dept_no')">组织机构代码</p>
                                            </li>
                                            <li>
                                                <p><#--<input type="file" id="buss_tax_regist" name="buss_tax_regist"
                                          onchange="picUploadRe(this,'buss_tax_registdiv')"/>--></p>

                                                <div class="qp-papers" id="buss_tax_registdiv" align="center">
                                                    <img src="${(qpEnterpriseCertificationInfo.buss_tax_regist_url)!}"
                                                         width="140" height="100" alt="税务登记证"/>
                                                </div>
                                                <p onclick="clearPic('buss_tax_regist')">税务登记证</p>
                                            </li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        <span>*</span>联系人：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.company_contact_name)!}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        <span>*</span>联系电话：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.company_contact_moble)!}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        <span>*</span>Email：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.company_email)!}
                                    </td>
                                </tr>
                            <#if vip??&&vip!="1">
                                <tr>
                                    <td class="auth-td-title">
                                        固定电话：
                                    </td>
                                    <td class="auth-td-content">
                                        <input type="text" class="qNomarl-text" name="company_contact_tel" value="${(qpEnterpriseCertificationInfo.company_contact_tel)!}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        传真：
                                    </td>
                                    <td class="auth-td-content">
                                        <input type="text" class="qNomarl-text" name="company_contact_cz" value="${(qpEnterpriseCertificationInfo.company_contact_cz)!}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        邮编：
                                    </td>
                                    <td class="auth-td-content">
                                        <input type="text" class="qNomarl-text" name="company_contact_yb" value="${(qpEnterpriseCertificationInfo.company_contact_yb)!}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">&nbsp;</td>
                                    <td class="auth-td-content"><a href="#" class="qpButton" onclick="forsubmit()">提交<a></td>
                                </tr>
                            <#else>
                                <tr>
                                    <td class="auth-td-title">
                                        固定电话：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.company_contact_tel)!}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        传真：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.company_contact_cz)!}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="auth-td-title">
                                        邮编：
                                    </td>
                                    <td class="auth-td-content">
                                        ${(qpEnterpriseCertificationInfo.company_contact_yb)!}
                                    </td>
                                </tr>
                            </#if>

                            </table>

                        </form>
                    <#elseif (qpEnterpriseCertificationInfoLast)??>
                        <div class="pt15 pb15 " style="font-size: 16px;font-family: 'microsoft YaHei';width: 100%;text-indent: 32px; ">
                            您上次的企业认证申请已被驳回（驳回原因：${qpEnterpriseCertificationInfoLast.audit_feedback!}）,请重新提交企业认证申请。
                        </div>
                        <div>
                            <a href="${basePath}/toValidateProtocol.htm" class="mt30" style="display:block;">
                                <img src="${basePath}/images/qpmalenterprise/qp_images/df.jpg"/>
                            </a>
                        </div>
                    <#else>
                        <div>
                            <a href="${basePath}/toValidateProtocol.htm" class="mt30" style="display:block;">
                                <img src="${basePath}/images/qpmalenterprise/qp_images/df.jpg"/>
                            </a>
                        </div>
                    </#if>
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
<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<#--引入底部 <#include "/index/bottom.ftl" /> -->
<#include "../index/newbottom.ftl" />

<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/scroller.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/userInfo.js"></script>

<script type="text/javascript" src="${basePath}/js/customer/dateclass.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/yearclass.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/education.js"></script>
<script>
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
            var prevDiv = document.getElementById(name);
            var reader = new FileReader();
            /*var size=file.files[0].size;*/
            reader.onload = function (evt) {
                //var wi= evt.width();
                prevDiv.style.backgroundImage = "url(" + evt.target.result + ")";
                prevDiv.style.backgroundSize = "140px 96px";
            }
            reader.readAsDataURL(file.files[0]);
        }
    }

    $(document).ready(function(){


    });
    $(document).ready(function () {
        //左边菜单的选中样式调整
        $('.item_title').each(function(){
            $(this).click(function(){
                $(this).next().toggle('fast',function(){
                    if($(this).is(':visible')){
                        $(this).prev().removeClass('up');
                        $(this).prev().addClass('down');
                    }
                    else{
                        $(this).prev().removeClass('down');
                        $(this).prev().addClass('up');
                    }
                });
            });
        });
        $(".pro_sort").addClass("pro_sort_close");
        $(".new_member_left div:eq(3) ul li:eq(6)").addClass("cur");

        loadProvice();
        loadALLCity();
        loadAllDistrict();
       /* var provinceinit = document.getElementById("provinceinit").value;
        var cityinit = document.getElementById("cityinit").value;
        var districtinit = document.getElementById("districtinit").value;
        document.getElementById("province").selectedIndex = provinceinit;
        document.getElementById("city").selectedIndex = cityinit;
        document.getElementById("district").selectedIndex = districtinit;*/
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
        document.getElementById("updateEninfo").submit();
    }
</script>
</@htmlBody>
