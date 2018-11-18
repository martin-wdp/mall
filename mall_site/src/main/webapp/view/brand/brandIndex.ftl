<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!'按品牌查找'}">
<link href="${basePath}/css/brand/qp_style.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/brand/sucaijiayuan.css"/>
<style>
    .customer_service:hover, .feedback:hover {background:#c33;}
    .customer_service:hover em, .feedback:hover em {font-size:12px; position:relative; top:3px; font-style:normal;}
    .customer_service b {background:url(${basePath}/index_seven/images/-01.png) #fff no-repeat center center!important;}
    .feedback b {background:url(${basePath}/index_seven/images/-02.png) #fff no-repeat center center!important;}
    .customer-box {position: absolute;background: #efefef;width: 146px;min-height: 150px;border: 1px solid #ccc;padding: 9px;right: 50px;display: none;}
    .sideBar .customer-box a {width:auto; height:auto; background-color:transparent; border:none; line-height:normal;}
    .close-cs {position: absolute!important;top: 10px;right: 10px;background: url(${basePath}/index_seven/images/agree_close1.gif) no-repeat;width: 10px!important;height: 10px!important;display: block;}
    .customer-box p {overflow:hidden; margin-top:10px;}
    .customer-box p .qq_name {float:right;}
    .customer-box p .qq_img {float:left;}
    .sideBar a span {font-size:12px!important; padding:2px 5px; width:28px!important; line-height:normal!important;}
</style>
</@htmlHead>
<@htmlBody>
<#include "../index/newheader7.ftl">
<div class="qp-container">
    <div class="qpSea mt20">
        <div class="title"><span>A</span>按品牌首字母查找</div>
        <ul>
            <li class="cur"><a href="#">全部</a></li>
            <#list brandlist as detitleCarStyleBean>
                <li><a href="#${detitleCarStyleBean.key}">${detitleCarStyleBean.key}</a></li>
            </#list>
        </ul>
    </div>
    <#list brandlist as detitleCarStyleBean>
        <div class="qpList">
            <div class="title" id="${detitleCarStyleBean.key}" style="text-indent:10px;">${detitleCarStyleBean.key}</div>
            <ul class="qpNet clearfix">
                <#list detitleCarStyleBean.vlist3 as brandName>
                    <li>
                        <a href="#" class="seacherClick" >
                            <#assign str="">
                            <img src="${brandName.brandLogo!'${basePath}/images/brand/imageload.jpg'}" alt="" width="150" height="60">
                            <p>${brandName.brandName}</p>
                        </a>
                    </li>
                </#list>
            </ul>
        </div>
    </#list>
</div>
<form id="searchForm" method="post" action="${basePath}/goods/searchproduct2.html?title=">
    <input type="hidden" name="brands" id="brandSeacher" value="">
</form>
<#include "../index/newbottom.ftl">
<div class="actGotop"><a href="javascript:" title="返回顶部"></a></div>
<script type="text/javascript">
    $(function(){
        $(window).scroll(function() {
            if($(window).scrollTop() >= 100){ //向下滚动像素大于这个值时，即出现小火箭~
                $('.actGotop').fadeIn(300); //火箭淡入的时间，越小出现的越快~
            }else{
                $('.actGotop').fadeOut(300); //火箭淡出的时间，越小消失的越快~
            }
        });
        $('.actGotop').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);}); //火箭动画停留时间，越小消失的越快~

        $(".seacherClick").click(function(){
            var val = $(this).find("p").html();
            $("#brandSeacher").val(val);
            $("#searchForm").attr("action","${basePath}/goods/searchproduct2.html?title="+val);
            $("#searchForm").submit();
        })

    });
</script>
</@htmlBody>

