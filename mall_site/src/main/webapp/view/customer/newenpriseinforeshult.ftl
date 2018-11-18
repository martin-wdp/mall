<#include "../include/common.ftl">
<@htmlHead title="账户信息-${topmap.systembase.bsetName}">
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/base.min.css"/>
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/pages.css"/>
<link rel="stylesheet" href="${basePath}/css/qpmalenterprise/qp_style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
</@htmlHead>
<#--<head>
    <title>账户信息-${topmap.systembase.bsetName}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="Keywords" content="${topmap.seo.meteKey}">
    <meta name="description" content="${topmap.seo.meteDes}">
&lt;#&ndash;
<#assign isEnterprise="${isEnterprise}">
&ndash;&gt;
<#assign basePath=request.contextPath>
    <link rel="stylesheet" href="${basePath}/css/qpmalenterprise/base.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/qpmalenterprise/pages.css"/>
    <link rel="stylesheet" href="${basePath}/css/qpmalenterprise/qp_style.css"/>
<#if (topmap.systembase.bsetHotline)??>
    <link rel="Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
    <link rel="Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
</head>-->
<@htmlBody>
<input type="hidden" name="CSRFToken" value="${token}">

<#--一引入头部 <#include "/index/topnew.ftl" />  -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==8)>
        <#include "../index/newtop3.ftl">
    <#elseif (topmap.temp.tempId==9)>
        <#include "../index/newtop4.ftl">
    <#elseif (topmap.temp.tempId==10)>
        <#include "../index/newtop7.ftl">
    <#elseif (topmap.temp.tempId==11)>
        <#include "../index/newtop6.ftl">
    <#elseif (topmap.temp.tempId==12)>
        <#include "../index/newtop7.ftl">
    <#elseif (topmap.temp.tempId==13)>
        <#include "../index/newtop8.ftl">
    <#elseif (topmap.temp.tempId==14)>
        <#include "../index/newtop9.ftl">
    <#elseif (topmap.temp.tempId==15)>
        <#include "../index/newtop8.ftl">
    <#elseif (topmap.temp.tempId==16)>
        <#include "../index/newtop11.ftl">
    <#elseif (topmap.temp.tempId==17)>
        <#include "../index/newtop12.ftl">
    <#elseif (topmap.temp.tempId==18)>
        <#include "../index/newtop13.ftl">
    <#elseif (topmap.temp.tempId==19)>
        <#include "../index/newtop14.ftl">
    <#elseif (topmap.temp.tempId==20)>
        <#include "../index/newtop15.ftl">
    <#else>
        <#include "../index/newtop.ftl"/>
    </#if>
</#if>
<#include "newtop.ftl"/>
<div style="background: #f5f5f5;">
    <div class="container clearfix pt20 pb10">
        <!--new_member_left-->
    <#include "newleftmenu.ftl"/>
        <div class="new_member-right">
            <div class="new_order_list">
                <div class="n-title">认证结果</div>
                <div class="tagMenu order-menu pr">

                    <div class="aco-link">
                        注：修改邮箱、手机请到<a href="${basePath}/customer/securitycenter.html">账户安全</a>
                    </div>
                </div>
                <div class="content">
                    <div class="layout">
			<div class="title"></div>
               <br/> <br/>

            <td align="center"><h2>${enInfo}</h2></td>
            <br/> <br/>
            <a href="${basePath}/showEnpriseInfo.htm" class="qpButton">查看我的认证信息</a>
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
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
    <#else>
        <#include "../index/newbottom.ftl" />
    </#if>
</#if>

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
            reader.onload = function (evt) {
                //prevDiv.attr("style","background:url("+evt.target.result+") no-repeat;width:100%;height:100%;");
                prevDiv.innerHTML = '<img src="' + evt.target.result + '" width="140" height="100"/>';
            }
            reader.readAsDataURL(file.files[0]);
        }
    }

    $(document).ready(function () {
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
    function forsubmit(){
        document.getElementById("updateEninfo").submit();
    }
</script>
</@htmlBody>
