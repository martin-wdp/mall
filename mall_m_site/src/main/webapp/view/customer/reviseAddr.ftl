<!DOCTYPE html>
<html>
<head>
<#assign basePath = request.contextPath>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.9,user-scalable=no"/>
    <meta name="MobileOptimized" content="320">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${basePath}/css/reviseAddr.css?v=20160521">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/top.css">
    <title><#if orderaddress??>修改收货地址<#else>添加收货地址 </#if></title>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>
<#include "../publicHeader2_ftl.ftl" />
<form action="${basePath}/customer/<#if orderaddress??>uodateAddressNewM<#else>addAddressNewM</#if>.htm"
      id="addNewAddress">
    <input type="hidden" name="customerId" value="${cust.customerId!}"/>
    <input type="hidden" name="infoProvince" value="<#if orderaddress??>${orderaddress.infoProvince!}<#else>1</#if>"/>
    <input type="hidden" name="infoCity" value="<#if orderaddress??>${orderaddress.infoCity!}<#else>1</#if>"/>
    <input type="hidden" name="infoCounty" value="<#if orderaddress??>${orderaddress.infoCounty!}<#else>1</#if>"/>
<#if needUrl??><input type="hidden" name="url" value="${needUrl}"/></#if>
<#if orderaddress??>
    <input type="hidden" name="addressId" value="${orderaddress.addressId}"/>
</#if>
    <div class="inpbox bgcolor">
        <p class="must" data-mes="请输入收货人姓名" <#if orderaddress??>num="1"</#if>><label>收货人：</label><input id="user"
                                                                                                        type="text"
                                                                                                        name="addressName"
                                                                                                        <#if orderaddress??>value="${orderaddress.addressName!}"</#if>
                                                                                                        placeholder="填写收货人姓名">
        </p>

        <p class="must" data-mes="请输入联系人手机号" <#if orderaddress??>num="1"</#if> style="border-bottom: none;">
            <label>联系方式：</label><input id="mobile"
                                       name="addressMoblie"
                                       <#if orderaddress??>value="${orderaddress.addressMoblie!}"</#if>
                                       type="text"
                                       placeholder="填写收货人手机号码">
        </p>
    </div>
    <div class="inpbox bgcolor comAddr">
        <p id="area" class="pborder" data-mes="请选择区域" style="height:66px;" num="1"><label>所属区域：</label>
            <map><span id="Province"><#if orderaddress??>${orderaddress.province.provinceName!}<#else>
                北京</#if></span><span id="prefecture">
            <#if orderaddress??>${orderaddress.city.cityName!}<#else>北京市</#if></span>
                <span id="county"><#if orderaddress??>${orderaddress.district.districtName!}<#else>东城区</#if></span>
            </map>
            <img src="${basePath}/images/qp_sxq.png" width="20"></p>
        <p class="must" data-mes="请输入详细地址" <#if orderaddress??>num="1"</#if>><label>详细地址：</label><input id="addr"
                                                                                                        type="text"
                                                                                                        name="addressDetail"
                                                                                                        <#if orderaddress??>value="${orderaddress.addressDetail!}"</#if>
                                                                                                        placeholder="填写您的详细地址">
        </p>

        <p class="must" data-mes="请输入邮政编码" <#if orderaddress??>num="1"</#if>><label>邮政编码：</label><input id="postcode"
                                                                                                        name="addressZip"
                                                                                                        type="text"
                                                                                                        <#if orderaddress??>value="${orderaddress.addressZip!}"</#if>
                                                                                                        placeholder="填写邮政编码">
        </p>

        <p class="acquiesce"><input id="check" name="checkbox" value="1" type="checkbox"
                                    <#if orderaddress??&&orderaddress.isDefault=="1">checked="checked"</#if>><label
                for="check">设置为默认收货地址</label></p>
    </div>
</form>
<button id="sub">提交</button>
<div class="addrBox dis">
    <div class="district">
        <p><span>省 | 市 | 自治区</span><span>地级市</span><span>区县</span><i id="line" data-left="40%"></i></p>

        <div class="city">
            <ul data-count="0" id="addressInit">
                <li>北京</li>
                <li>上海</li>
                <li>黑龙江</li>
                <li>河北</li>
                <li>山东</li>
                <li>沈阳</li>
                <li>北京</li>
                <li>上海</li>
                <li>黑龙江</li>
                <li>河北</li>
                <li>山东</li>
                <li>沈阳</li>
            </ul>
        </div>
    </div>
</div>
</body>
<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
<script src="${basePath}/js/customer/public.js"></script>
<script src="${basePath}/js/customer/reviseAddr.js"></script>
<script src="${basePath}/js/customer/selectAddress.js"></script>
</html>
