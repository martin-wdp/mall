<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>会员中心 - 我的积分</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
  <#assign basePath=request.contextPath>
  <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
  <script src="${basePath}/js/jquery-1.10.1.js"></script>
  <script src="${basePath}/js/idangerous.swiper.js"></script>
</head>
<body>

<div class="my_balance member_box">
  <div class="cover">
    <h2>${(customer.infoPointSum)!'0'}</h2>
    <p>当前积分</p>
  </div>
</div>

<div class="income_details member_box">
  <h2>收支明细</h2>
  <div class="tabs row">
    <!--<a href="${basePath}/customer/myintegral.html" <#if !type??>class="active"</#if>>全部</a>
    <a href="#" <#if type?? && type=='1'>class="active"</#if>>收入</a>
    <a href="#" <#if type?? && type=='0'>class="active"</#if>>支出</a>-->
    <a href="#" data-val='' class="active">全部</a>
    <a href="#" data-val='1'>收入</a>
    <a href="#" data-val='0'>支出</a>
  </div>
  <!--<div class="swiper-container">
    <div class="swiper-wrapper">
      <div class="swiper-slide">-->
        <div class="content-slide">
          <div class="income_list" id="items">
           <#if (pb.list?size!=0)>
              <#list pb.list as point>
                <#if point.point!=0 && point.point??>
                 <div class="income_item">
		              <dl>
		                <dt><span>${(point.pointDetail)!''}</span></dt>
		                <dd><span class="light">${point.createTime?string("yyyy-MM-dd HH:mm:ss")}</span></dd>
		              </dl>
		              <dl>
		                <!--<dt><span class="light">余额：${(customer.infoPointSum)!'0'}</span></dt>-->
		                <dd>
		                <span class="red">
		                 <#if point.pointType??>
                            <#if point.pointType=='1'>
									+${point.point}
                            <#else>
									-${point.point}
                            </#if>
                        </#if>
		                </span></dd>
		              </dl>
           		 </div>
                </#if>
               </#list>
            </#if>
          </div>
        </div>
      <p class="tc f16" id="showmore" onclick="show()" style="text-align:center"></p>
      </div>
     
 
  <input id="type" type="hidden" value="${(type)!''}" /> 
  <input id="basePath" type="hidden" value="${basePath}" /> 
<#include '/common/smart_menu.ftl' />

<script>
    $(".bar-bottom  a").removeClass("selected");
    $(".bar-bottom:eq(0) a:eq(3) ").addClass("selected");
  $(".tabs a").on('touchstart mousedown',function(e){
    e.preventDefault();
    $(".tabs .active").removeClass('active');
    $(this).addClass('active');
    $(this).attr("data-val");
    $("#type").val($(this).attr("data-val"))
    showType();
  });
  $(".tabs a").click(function(e){
    e.preventDefault()
  })
  
   /* 滑到筛选标签到顶部后，把标签固定在顶部 */
  $(window).scroll(function(){

    if($(this).scrollTop() >= 168){
      $('.income_details .tabs').css({
        position : 'fixed',
        width : '100%',
        left : '0',
        top : '0'
      });
      $('.income_details .content-slide').css('marginTop','45px');
    }
    else{
      $('.income_details .tabs').css({
        position : 'static'
      });
      $('.income_details .content-slide').css('marginTop','0');
    }
    if($(this).scrollTop() >= ($('body').height() - $(window).height() + 60)){
     show();
    }

  });
</script>
</body>
<script src="${basePath}/js/customer/integral.js"></script>
<script src="${basePath}/js/dataformat.js"></script>
</html>