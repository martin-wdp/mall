<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>店铺</title>
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <script src="${basePath}/js/pace.min.js"></script>
</head>
<body>
<div class="ui-header">
    <div class="wp">
        <h1 class="header-logo">
            <#--<a href="${basePath}/index.html">-->
            <a href="${basePath}/sellerinfo.html">

            <#if siteUrl?? && siteUrl.bsetThirdLogo??>
                <img alt="" src="${siteUrl.bsetThirdLogo}" style="max-width:130px;max-height:35px;"/>
            <#else>
                <img alt="" src="${basePath}/images/logo.jpg" style="max-width:130px;max-height:35px;"/>
            </#if>
            </a>
        </h1>
        <div class="header-nav">
            <ul class="clearfix" id="indextop">

            </ul>
        </div>
        <div class="header-user">
            <div class="dropdown hover">
                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                    <span onclick="ckeckThirdIndex(<#if infostore??>${infostore.storeId!''}</#if>)">
                        <#if customer??>
                            ${customer.customerNickname!''}
                        </#if>的小店
                    </span>
                    <input type="hidden" value="${customer.customerId}" id="custId" />
                    <span>设置</span>
                    <i class="caret"></i>
                </a>
                <ul class="dropdown-menu dropdown-menu-right">
                    <#--<li><a href="default.html">切换店铺</a></li>
                    <li class="divider"></li>
                    <li><a href="javascript:;">店铺设置</a></li>
                    <li><a href="javascript:;">账号设置</a></li>
                    <li class="divider"></li>-->
                    <li><span onclick="javascript:window.location.href='${basePath}/sellerinfo.html'"><#if customer??>${customer.customerUsername}<#else></#if></span></li>
                    <li><a href="${basePath}/logout.html">退出</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<#--店铺首页是否启用-->
<div class="modal fade" id="go_store" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <label class="control-label">该商家的店铺首页未启用！</label>
            </div>
            <div class="modal-footer">
                <#--<button class="btn btn-primary" onclick="delete_img()" type="button">确定</button>-->
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>



</body>
</html>
