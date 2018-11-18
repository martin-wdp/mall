<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>会员中心 - 首页</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
    <#assign basePath=request.contextPath>
  <script src="http://pic.ofcard.com/astore/wei-store/js/jquery-1.10.1.js"></script>
  <script src="http://pic.ofcard.com/astore/wei-store/js/idangerous.swiper.js"></script>
</head>
<body>

<div class="member_brief member_box">
  <div class="cover">
    <div class="avatar">
      <a href="${basePath}/myaccount.html">
        <img alt="" src="../images/avatar.png">
      </a>
    </div>
    <h2>${cust.customerNickname!''}</h2>
    <div class="message">
      <a href="notice1.html">
        <i class="iconfont icon-icxiaoxi"></i>
        <span class="badge">10</span>
      </a>
    </div>
  </div>
  <div class="data row">
    <div class="col-12">
      <p>
        <a href="my_collections.html">
          <span>48</span>
          我的收藏
        </a>
      </p>
    </div>
    <div class="col-12">
      <p>
        <a href="visit_history.html">
          <span>48</span>
          浏览过的
        </a>
      </p>
    </div>
  </div>
</div>

<div class="order_area member_box">
  <div class="top row">
    <div class="col-6">
      <a href="${basePath}/customer/myorder-0-1.html">
        <p class="order_state_1">
          <i class="iconfont icon-daifukuan"></i>
          待付款
        </p>
      </a>
    </div>
    <div class="col-6">
      <a href="${basePath}/customer/myorder-3-1.html">
        <p class="order_state_2">
          <i class="iconfont icon-daishouhuo"></i>
          待收货
        </p>
      </a>
    </div>
    <div class="col-6">
      <a href="${basePath}/customer/myorder-4-1.html">
        <p class="order_state_3">
          <i class="iconfont icon-klmpingjia"></i>
          待评价
        </p>
      </a>
    </div>
    <div class="col-6">
      <a href="#">
        <p class="order_state_4">
          <i class="iconfont icon-tuikuanshouhou"></i>
          退款/退货
        </p>
      </a>
    </div>
  </div>
  <div class="common_line row">
    <a href="${basePath}/customer/myorder.html">
      <em class="order_icon"></em>
      <h4>全部订单</h4>
      <i class="ion-chevron-right"></i>
    </a>
  </div>
</div>

<div class="member_box">
  <div class="common_line row">
    <a href="my_balance.html">
      <em class="balance_icon"></em>
      <h4>我的余额</h4>
      <i class="ion-chevron-right"></i>
    </a>
  </div>
  <div class="common_line row">
    <a href="${basePath}/customer/myintegral.html">
      <em class="point_icon"></em>
      <h4>我的积分</h4>
      <i class="ion-chevron-right"></i>
    </a>
  </div>
  <div class="common_line row">
    <a href="${basePath}/customer/coupon.html">
      <em class="coupon_icon"></em>
      <h4>我的优惠券</h4>
      <i class="ion-chevron-right"></i>
    </a>
  </div>
</div>

<div class="member_box">
  <div class="common_line row">
    <a href="javascript:;">
      <em class="like_icon"></em>
      <h4>猜你喜欢</h4>
    </a>
  </div>
  <div class="slide_goods">
    <div class="swiper-container">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
          <div class="like_good">
            <a href="#">
              <div class="img">
                <img alt="" src="../images/propic-01.jpg">
              </div>
              <p class="name">
                牛皮纸空白内页软面抄...
              </p>
              <p class="price">
                ￥55.00
              </p>
            </a>
          </div>
        </div>
        <div class="swiper-slide">
          <div class="like_good">
            <a href="#">
              <div class="img">
                <img alt="" src="../images/propic-02.jpg">
              </div>
              <p class="name">
                牛皮纸空白内页软面抄...
              </p>
              <p class="price">
                ￥55.00
              </p>
            </a>
          </div>
        </div>
        <div class="swiper-slide">
          <div class="like_good">
            <a href="#">
              <div class="img">
                <img alt="" src="../images/propic-03.jpg">
              </div>
              <p class="name">
                牛皮纸空白内页软面抄...
              </p>
              <p class="price">
                ￥55.00
              </p>
            </a>
          </div>
        </div>
        <div class="swiper-slide">
          <div class="like_good">
            <a href="#">
              <div class="img">
                <img alt="" src="../images/propic-01.jpg">
              </div>
              <p class="name">
                牛皮纸空白内页软面抄...
              </p>
              <p class="price">
                ￥55.00
              </p>
            </a>
          </div>
        </div>
        <div class="swiper-slide">
          <div class="like_good">
            <a href="#">
              <div class="img">
                <img alt="" src="../images/propic-02.jpg">
              </div>
              <p class="name">
                牛皮纸空白内页软面抄...
              </p>
              <p class="price">
                ￥55.00
              </p>
            </a>
          </div>
        </div>
        <div class="swiper-slide">
          <div class="like_good">
            <a href="#">
              <div class="img">
                <img alt="" src="../images/propic-03.jpg">
              </div>
              <p class="name">
                牛皮纸空白内页软面抄...
              </p>
              <p class="price">
                ￥55.00
              </p>
            </a>
          </div>
        </div>
        <div class="swiper-slide">
          <div class="like_good">
            <a href="#">
              <div class="img">
                <img alt="" src="../images/propic-01.jpg">
              </div>
              <p class="name">
                牛皮纸空白内页软面抄...
              </p>
              <p class="price">
                ￥55.00
              </p>
            </a>
          </div>
        </div>
        <div class="swiper-slide">
          <div class="like_good">
            <a href="#">
              <div class="img">
                <img alt="" src="../images/propic-02.jpg">
              </div>
              <p class="name">
                牛皮纸空白内页软面抄...
              </p>
              <p class="price">
                ￥55.00
              </p>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<#--
<div class="bar-bottom">
  <a class="bar-item" href="${basePath}/main.html"><i class="bar-bottom-i home"></i>首页</a>
  <a class="bar-item" href="${basePath}/cates.html"><i class="bar-bottom-i class"></i>分类</a>
  <a class="bar-item" href="${basePath}/myshoppingmcart.html"><i class="bar-bottom-i cart"></i>购物车</a>
  <a class="bar-item selected" href="${basePath}/customer/index.html"><i class="bar-bottom-i mine"></i>我的</a>
</div>
-->
<#include '/common/smart_menu.ftl' />
<script>
  var mySwiper = new Swiper('.swiper-container',{
    slidesPerView: 'auto'
  });
  $(".bar-bottom a").removeClass("selected");
      $(".bar-bottom a:eq(3)").addClass("selected");
</script>
</body>
</html>