<#assign basePath=request.contextPath>
	
    <div class="PathInner" id="PathMenu" style="right:-2px;top:80%;">
      <div class="PathMain">
        <div class="Tmain">
          <div class="rotate" data-transform="rotate(0deg)"><span class="cover"></span></div>
        </div>
      </div>
      <div class="PathItem">
        <a class="link" href="${basePath}" title="回到首页">
          <span class="item" style="-moz-transform: rotate(0deg);" data-transform="rotate(0deg)"><i class="glyphicon glyphicon-home"></i></span>
        </a>
      </div>
      <div class="PathItem">
        <a class="link" href="${basePath}/myshoppingmcart.html" title="购物车">
          <span class="item" style="-moz-transform: rotate(0deg);" data-transform="rotate(0deg)"><i class="glyphicon glyphicon-shopping-cart"></i></span>
        </a>
      </div>
      <div class="PathItem">
        <a class="link" href="${basePath}/customer/index.html" title="会员中心">
          <span class="item" style="-moz-transform: rotate(0deg);" data-transform="rotate(0deg)"><i class="glyphicon glyphicon-user"></i></span>
        </a>
      </div>
      <div class="PathItem">
        <a class="link" href="${basePath}/customer/myorder.html" title="我的订单">
          <span class="item" style="-moz-transform: rotate(0deg);" data-transform="rotate(0deg)"><i class="glyphicon glyphicon-file"></i></span>
        </a>
      </div>
    </div>