<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!'按分类查找'}">
<link rel="stylesheet" href="${basePath}/index_seven/css/style.css"/>
<link rel="stylesheet" href="${basePath}/css/brand/sucaijiayuan.css"/>
<link href="${basePath}/css/brand/qp_style.css" rel="stylesheet">
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
    <div class="upKeep">
        <ul class="clearfix">
        <#list firstLevelGoodsCate as goodsfirstCate>
            <li>
                <a href="#${goodsfirstCate.catName}">
                    <img src="${goodsfirstCate.catImage}" >
                    <p>${goodsfirstCate.catName}</p>
                </a>
            </li>
        </#list>
        </ul>
    </div>
    <div class="qpSearch">
        <div class="sous clearfix" style="position:relative;">
            <span>输入关键词查找商品类别 ：</span>
            <input type="text" id="seachVal">
            <button></button>
        <#--<div class="search-re">
            <a href="#">润滑油<s>×</s></a>
        </div>-->
        </div>
    </div>
    <div class="qpSearchRe">
        <div class="qResult">
        </div>
        <p>共<span id="valcount"></span>个符合条件的搜索结果</p>
    </div>
    <#list GoodsCatelist as goodsCate>
        <#if goodsCate.catParentId?? && goodsCate.catParentId==0 && goodsCate.catName !="其它">
            <div class="qpList">
                <div id="${goodsCate.catName}" class="title">${goodsCate.catName}</div>
                <div class="clearfix">
                    <#list GoodsCatelist as goodsCates>
                        <#if goodsCates.catParentId?? && goodsCates.catParentId==goodsCate.catId>
                            <div class="Oil">
                                <div class="dope">${goodsCates.catName}</div>
                                <ul>
                                    <#list GoodsCatelist as goodsCatess>
                                        <#if goodsCatess.catParentId?? && goodsCatess.catParentId==goodsCates.catId>
                                            <li><a href="${basePath}/list/${goodsCatess.catId}-${goodsCates.catParentId}.html">${goodsCatess.catName}</a></li>
                                        </#if>
                                    </#list >
                                </ul>
                            </div>
                        </#if>
                    </#list>
                </div>
            </div>
        </#if>
    </#list>
</div>
<div class="actGotop"><a href="javascript:;" title="返回顶部"></a></div>
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
    });

    $(".sous").find("button").click(function(){
        var  seachVal=$("#seachVal").val();
        if(seachVal=="")
        {
            $(".qpSearchRe").hide();
            return;
        }
        $.getJSON("${basePath}/findGoodsCateByName.htm",{"name":seachVal},function(data){
            var tt = "";
            $.each(data, function(k, v) {
                if(k==0){
                    tt+='<a href="${basePath}/list/'+v.catId+'-'+v.catParentId+'.html" class="cur">'+v.catName+'</a>'
                }else{
                    tt+='<a href="${basePath}/list/'+v.catId+'-'+v.catParentId+'.html">'+v.catName+'</a>'
                }
            })
            $(".qResult").html(tt);
            $("#valcount").html(data.length)
        })
        $(".qpSearchRe").show();
    })
</script>
<#include "../index/newbottom.ftl">
</@htmlBody>

