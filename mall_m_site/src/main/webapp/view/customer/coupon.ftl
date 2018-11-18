<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <!-- Bootstrap -->
<#assign basePath=request.contextPath>
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
    <script src="${basePath}/js/jquery.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <style>
        #keleyi-menu {
            display: none;
        }
    </style>
<#if (sys.bsetName)??>
    <title>我的优惠券-${(sys.bsetName)!''}</title>
    <input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    <input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
<#else>
    <title>我的优惠券-${(seo.mete)!''}</title>
    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    <input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
</#if>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${basePath}/js/html5shiv.min.js"></script>
    <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>
<#include "../publicHeader2_ftl.ftl" />
<div class="order_filter order_filter2">
    <ul>
        <!--<li <#if !type??> class="current"</#if> ><a href="${basePath}/customer/coupon.html">全部</a></li>-->
        <li <#if type??&& type=='1'> class="current"</#if> ><a href="${basePath}/customer/coupon.html?type=1">未使用</a></li>
        <li <#if type?? && type=='2'> class="current"</#if> ><a
                href="${basePath}/customer/coupon.html?type=2">使用过</a></li>
        <li <#if type?? && type=='3'> class="current"</#if> ><a
                href="${basePath}/customer/coupon.html?type=3">已过期</a></li>
    </ul>
</div>
<div class="ocoupon_list">
    <ul>

        <#if pb.list??&&pb.list?size!=0>
            <#list pb.list as coupon >
                <li>
                    <p class="coup_left"><i><#if (coupon.couponRulesType=="1")>
                        直减
                    <#else>
                        满减
                    </#if></i></p>

                    <div class="coup_right">
                        <span class="coup_money"><#if (coupon.couponRulesType=="1")>${((coupon.couponStraightDown.downPrice)!'0')?string}
                        <#else>${((coupon.couponFullReduction.reductionPrice)!'0')?string}
                        </#if></span>
                        <span class="coup_mes">
                            <#if (coupon.couponRulesType=="1")>
                                直减	${((coupon.couponStraightDown.downPrice)!'0')?string}
                            <#else>
                                满<em>${((coupon.couponFullReduction.fullPrice)!'0')?string}</em>
                                减${((coupon.couponFullReduction.reductionPrice)!'0')?string}
                            </#if>
                            </span>

                        <p class="coup_time">有效期至<i>${coupon.couponEndTime?string("yyyy年MM月dd日")}</i>止</p>
                    </div>
                </li>
            </#list>
    </ul>


<#else>
    <#--<div style="text-align:center; font-size:14px;"> 暂无数据！</div>-->
    <div style="width:100%;text-align:center;margin-top:20px;font-size:16px;">
        <img src="${basePath}/images/no-pros.png" style="width:50%;"/>
        <br>
        暂无！
    </div>


        </#if>
</div>
<!-- /order_list -->
<div class="foot">
    <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
</div>
<!-- /foot -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${basePath}/js/bootstrap.min.js"></script>
<script src="${basePath}/js/fastclick.min.js"></script>
<script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
<script src="${basePath}/js/customer/wxforward.js"></script>
<script src="${basePath}/js/publicModel.js"></script>

<script>
    var n = GetQueryString("type");
    if(n == 2|| n ==3){
        $(".coup_left").css("background-image","url(../images/qp_coup_no0.png)");
        $(".coup_right").css("background-image","url(../images/qp_coup_no.png)");
    }

</script>
</body>
</html>
