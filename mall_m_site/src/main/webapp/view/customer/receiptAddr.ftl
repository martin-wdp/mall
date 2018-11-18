<!DOCTYPE html>
<html>
	<head>
    <#assign basePath=request.contextPath>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=0.9,user-scalable=no"/>
		<meta name="MobileOptimized" content="320">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<link rel="stylesheet" href="${basePath}/css/receiptAddr.css">
        <link rel="stylesheet" type="text/css" href="${basePath}/css/top.css">
		<title>收货地址管理</title>
	 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
	<body>
    <#include "../publicHeader2_ftl.ftl" />
    <#if (addresses??&&addresses?size>0)>
    <#list addresses as add>
    <div class="inpbox">
        <input type="hidden" value="${add.addressId!}">
        <p class="add1">
            <span class="sp1">${add.addressName!}</span>
            <span class="sp2">${add.addressMoblie!}</span><br>
            <span class="sp3">${add.province.provinceName!}市 ${add.city.cityName!}区 ${add.district.districtName!} ${add.addressDetail!}</span>
        </p>
        <p class="add2">
            <input id="${add.addressId!}" class="checkAddrBox" type="radio" name="checkDefault" <#if add.isDefault=="1" >checked="checked" </#if>><label style="text-indent:0;" for="${add.addressId!}">设为默认地址</label>
            <button class="edit" addressId="${add.addressId!}">编辑</button><button class="remove">删除</button>
        </p>
    </div>
    </#list>
    <#else >
        暂时没有收货地址
    </#if>

		<button class="btn" id="addNewAddress">添加新地址</button>
	</body>
	<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
	<script src="${basePath}/js/customer/receiptAddr.js"></script>
</html>
