<!DOCTYPE html>
<html lang="zh-CN">
<head>
   <#assign basePath=request.contextPath>
  <meta charset="UTF-8">
  <title>会员中心 - 优惠券</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
  <link rel="stylesheet" href="${basePath}/css/style.css"/>
  <script src="http://pic.ofcard.com/astore/wei-store/js/jquery-1.10.1.js"></script>
</head>
<body>

<div class="common_tabs member_box row">
  <div class="col-8">
    <a href="${basePath}/customer/coupon.html?type=1" <#if type?? && type=='1'> class="selected"</#if>>
      未使用(${status1!''})
    </a>
  </div>
  <div class="col-8">
    <a href="${basePath}/customer/coupon.html?type=2" <#if type?? && type=='2'> class="selected"</#if>>
      已使用(${status2!''})
    </a>
  </div>
  <div class="col-8">
    <a href="${basePath}/customer/coupon.html?type=3" <#if type?? && type=='3'> class="selected"</#if>>
      已过期(${status3!''})
    </a>
  </div>
</div>

<div class="coupons-xin">
 <#if pb.list?size!=0>
  <#list pb.list as coupon>
  <div class="coupons-item <#if type?? && (type=='2' || type=='3')>update</#if>">
    <div class="coupons-body">
      <div class="body-left">
        <div class="title">
          <h1>
            <span>¥</span>
            <span class="num"><#if (coupon.couponRulesType=="1")>
                         ${((coupon.couponStraightDown.downPrice)!'0.00')}
            <#else>
                     ${((coupon.couponFullReduction.reductionPrice)!'0.00')?string.currency}
            </#if></span>
          </h1>
          <p><#if (coupon.couponRulesType=="1")>
                                        直减  ${((coupon.couponStraightDown.downPrice)!'0.00')}
            <#else>
                                        满${((coupon.couponFullReduction.fullPrice)!'0.00')?string.currency}减${((coupon.couponFullReduction.reductionPrice)!'0.00')?string.currency}
            </#if>
         </p>
        </div>
      </div>
      <div class="body-right">
        <dl>
          <dt><#if coupon.couponRulesType=="1" &&(coupon.couponStraightDown.downPrice)??>不限制<#elseif coupon.couponRulesType=="2" &&coupon.couponFullReduction.fullPrice??>满
                                        ${coupon.couponFullReduction.fullPrice?string.currency}
                                        </#if>
           </dt>
          <dd><label>适用范围：</label><#if (coupon.gplist??) >
                        <#list coupon.gplist as gp>
                                <#if gp.productName?? &&(gp.productName?length>16)>
                                    ${gp.productName?substring(0,16)}...&nbsp;
                                <#else>
                                    ${gp.productName!''}&nbsp;
                                </#if>
                            </a>
                        </#list>
                    </#if>
                    
                    <#if (coupon.gclist??) >
                        <#list coupon.gclist as gc>
                                <#if (gc.catName)?? && (gc.catName?length>16)>
                                    ${gc.catName?substring(0,16)}...&nbsp;
                                <#else>
                                    ${gc.catName}&nbsp;
                                </#if>
                            </a>
                        </#list>
                    </#if>
          </dd>
          <dd><label>有效期：</label>${coupon.couponStartTime?string("yyyy/MM/dd")}-
                                    ${coupon.couponEndTime?string("yyyy/MM/dd")}
          </dd>
          <dd><label>券号：</label>${coupon.codeNo?substring(0,10)}...</dd>
        </dl>
      </div>
      <span class="state">
                <#if coupon.codeStatus??>
                <#switch coupon.codeStatus>
                <#case '1'>
                                                              未使用
                <#break>
                <#case '2'>
                                                               已使用
                <#break>
                 <#case '3'>
                                                               已过期
                <#break>
                <#default>
                </#switch>
              </#if>
        </span>
    </div>
  </div>
  </#list>
 <#else>
  <div style="text-align:center; font-size:14px;">  暂无数据！</div>
</#if>
</div>

<div class="bottom-fixed p10">
  <a href="#<#--my_coupons_add.html-->" class="btn btn-full">+ 添加优惠券</a>
</div>

<script>
  $(function(){

  });
</script>
</body>
</html>