<!DOCTYPE html>
<html lang="zh-cn">
  <head>
  	<#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
    <#if (sys.bsetName)??>
    	<title>${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="${basePath}/css/mui.min.css"/>
        <link rel="stylesheet" href="${basePath}/css/orderstyle.css"/>
        <link rel="stylesheet" href="${basePath}/css/address.css"/>

        <script src="${basePath}/js/jquery.js"></script>
</head>
        <body style="background:#f0f2f5;">
        <div class="mui-appbar">
        <h2 class="mui-text-center">收货地址</h2>
        <a href="javascript:history.go(-1);" class="back-btn"><i class="fa fa-angle-left"></i></a>
        </div>
        <div class="wrap">
        <div class="add-address">
            <a href="${basePath}/toAddAddress.htm"><button class="mui-btn mui-btn-danger mui-btn-flat"><i class="fa fa-plus"></i>添加收货地址</button></a>
        </div>
        <#if addresses??>
            <#list addresses as address>
                <div class="mui-panel">
                    <div class="ads-info">
                        <div class="mt-new">${address.addressName!''}&nbsp;&nbsp;&nbsp;${address.addressMobile!''}</div>
                        <div class="mc">${address.addressDetail}</div>
                    </div>
                    <div class="address-operation mui-clearfix">
                        <label class="choose-address"><input type="radio" class="addressId" name="addressId"<#if address.isDefault=='1'>checked="checked"</#if> value="${address.addressId}"/>送到这里去</label>
                        <div class="operation-wp">
                            <a href="${basePath}/toupdateAddress.htm?addressId=${address.addressId}"><i class="fa fa-pencil-square-o fa-lg"></i>修改</a>
                            <#if address.isDefault=='1'><#else><a href="${basePath}/delAddress.htm?addressId=${address.addressId}"><i class="fa fa-trash-o fa-lg"></i>删除</a></#if>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
        <form id="subForm" method="post">
            <input type="hidden" name="addressId" id="addressId">
        </form>
        <script src="${basePath}/js/jquery-1.11.1.min.js"></script>
        <script src="${basePath}/js/mui.min.js"></script>
        <script src="${basePath}/js/app.js"></script>
         <script>
            $(function(){
                $(".addressId").click(function(){
                    $("#addressId").val($(this).val());
                   $("#subForm").attr("action","${basePath}/changeAddress.htm").submit();
                })
            })
         </script>
        </body>
</html>