<#assign basePath=request.contextPath>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
<#if (sys.bsetName)??>
    <title>${sys.bsetName}</title>
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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>
<#include "../publicHeader4_ftl.ftl"/>

<div class="navCar container-fluid">
    <div class="row">
        <div class="textCent col-xs-6">
            <span id="sel_vin_btn" class="nav_selectCar">选车型</span>
            <input type="hidden" id="customerId" value="${customerId?default("")}">
        </div>
        <div class="textCent col-xs-6">
            <span id="sel_hand_btn">手动选车型</span>
        </div>
    </div>
</div>

<div class="vinBox">
    <div class="vinSearch">
        <input type='text' id="selectV" placeholder='请在此处输入VIN码'/>
        <button class='btn_search btn_search2'></button>
        <span id="errmsg" style="color: red;"></span>
    </div>
    <div class="selec">
        <p>选择记录</p>
        <ul id="selectRecode">

        </ul>
    </div>
</div>

<div class="handBox">
    <div class="left_list">
        <ul>
            <li><a id="brandBox" class="border_left" href="javascript:void();">品牌</a></li>
            <li><a id="car">车系</a></li>
            <li><a id="motorcycleType">车型</a></li>
            <li style="line-height:20px;"><a id="gearEng" style="padding-top:25px;">发动机<br/>变速箱</a></li>
            <li><a id="modelYear">年款</a></li>
        </ul>
    </div>
    <div class="right_list">
        <div class="brandBox">
            <div class="brand" data-name="品牌" data="brandBox-car" data-chr="b">
                <div class="block">
                    <p id="a">A</p>
                    <ul>
                        <li>奥迪</li>
                        <li>阿斯顿马丁</li>
                        <li>阿尔法</li>
                    </ul>
                </div>
            </div><!--brand结束-->
            <div class="letter">
                <p></p>
                <ul id="Letter">

                </ul>
            </div>
        </div>
        <div class="car dis" data-name="车系" data="car-motorcycleType" data-chr="c" id="cartype">
            <div class="block">
                <p>一汽奥迪</p>
                <ul>
                    <li>A6</li>
                    <li>A4</li>
                    <li>A3</li>
                </ul>
            </div>
        </div><!--car结束-->
        <div class="motorcycleType dis" data-name="车型" data="motorcycleType-gearEng" data-chr="d" id="carSystem">
            <div class="block">
                <ul>
                    <li>豪华型</li>
                    <li>舒适型</li>
                    <li>运动型</li>
                </ul>
            </div>
        </div><!--motorcycleType结束-->
        <div class="gearEng dis" data-name="发动机" data="gearEng-modelYear" data-chr="e" id="carType">
            <div class="block">
                <ul>
                    <li>2.0L</li>
                    <li>2.8L</li>
                    <li>3.0T</li>
                </ul>
            </div>
        </div><!--gearEng结束-->
        <div class="modelYear dis" data-name="年款" data="odelYear" data-chr="a" id="carEngine">
            <div class="block">
                <ul>
                    <li>2015年</li>
                    <li>2014年</li>
                    <li>2013年</li>
                    <li>2012年</li>
                    <li>2011年</li>
                    <li>2010年</li>
                    <li>2009年</li>
                    <li>2008年</li>
                    <li>2007年</li>
                    <li>2006年</li>
                </ul>
            </div>
            <a id="moreYear">查找更早年份 > </a>
        </div><!--modelYear结束-->
    </div>
    <div style="height:80px;"></div>
    <div class="bottomMes">
        <img src="${basePath}/images/app_cat.png" style="height:100%;">
        联系客服：<strong><a href="tel:4000876599">400-087-6599</a></strong>
    </div>
</div>

<div class="sucMes dis">
    <div class="mess">
        <img src="${basePath}/images/qp_cxcg.png" width="50">
        <h4>恭喜您，成功选择爱车</h4>
        <p id="cartype2"></p>
        <span><i id="timeJump">3</i> 秒自动跳转</span>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${basePath}/js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${basePath}/js/bootstrap.min.js"></script>
<script src="${basePath}/js/fastclick.min.js"></script>
<script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
<script src="${basePath}/js/jquery.keleyi.js"></script>
<script src="${basePath}/js/customer/wxforward.js"></script>
<script src="${basePath}/js/publicModel.js"></script>
<script src="${basePath}/js/motocycle.js"></script>
</body>
</html>
